package com.galileo.p2p.server

import com.galileo.protos.protos.Protocol.ReasonCode.DUPLICATE_PEER
import com.galileo.protos.protos.Protocol.ReasonCode.TOO_MANY_PEERS
import com.galileo.protos.protos.Protocol.ReasonCode.TOO_MANY_PEERS_WITH_SAME_IP
import com.galileo.protos.protos.Protocol.ReasonCode.UNKNOWN
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.net.InetAddress
import java.net.InetSocketAddress
import java.util
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.galileo.p2p.client.PeerClient
import com.galileo.core.config.args.Args
import com.galileo.db.levelDB.store.ByteArrayWrapper
import com.galileo.p2p.discover.node.Node
import com.galileo.protos.protos.Protocol.ReasonCode
import com.galileo.protos.protos.Protocol.ReasonCode._

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

@Component
class ChannelManager {

  private val logger = LoggerFactory.getLogger(getClass())
  private val activePeers = new ConcurrentHashMap[ByteArrayWrapper, Channel]
  private val badPeers = CacheBuilder.newBuilder.maximumSize(10000).expireAfterWrite(1, TimeUnit.HOURS).recordStats.build.asInstanceOf[Cache[InetAddress, ReasonCode]]
  private val recentlyDisconnected = CacheBuilder.newBuilder.maximumSize(1000).expireAfterWrite(30, TimeUnit.SECONDS).recordStats.build.asInstanceOf[Cache[InetAddress, ReasonCode]]
  @BeanProperty
  val trustPeers: util.Map[InetAddress, Node] = new ConcurrentHashMap[InetAddress, Node]
  private val args = Args.getInstance
  private val maxActivePeers = args.getNodeMaxActiveNodes
  private val getMaxActivePeersWithSameIp = args.getNodeMaxActiveNodesWithSameIp
  private var peerServer: PeerServer = null
  private var peerClient: PeerClient = null
  @Autowired
  private val syncPool: SyncPool = null

  def this(peerServer: PeerServer, peerClient: PeerClient) {
    this()
    this.peerServer = peerServer
    this.peerClient = peerClient
    if (this.args.getNodeListenPort > 0){
      new Thread(() => peerServer.start(Args.getInstance().getNodeListenPort()), "PeerServerThread").start()
    }

    for (node <- args.getPassiveNodes) {
      trustPeers.put(new InetSocketAddress(node.getHost, node.getPort).getAddress, node)
    }
    logger.info("Trust peer size "+trustPeers.size)
  }

  def processDisconnect(channel: Channel, reason: ReasonCode) {
    val inetAddress = channel.getInetAddress
    if (inetAddress == null) return
    reason match {
      case BAD_PROTOCOL =>
      case BAD_BLOCK =>
      case BAD_TX =>
        badPeers.put(channel.getInetAddress, reason)
      case _ =>
        recentlyDisconnected.put(channel.getInetAddress, reason)
    }
  }

  def notifyDisconnect(channel: Channel) {
    syncPool.onDisconnect(channel)
    activePeers.values.remove(channel)
    if (channel != null) {
      if (channel.getNodeStatistics != null) channel.getNodeStatistics.notifyDisconnect()
      val inetAddress = channel.getInetAddress
      if (inetAddress != null && recentlyDisconnected.getIfPresent(inetAddress) == null) recentlyDisconnected.put(channel.getInetAddress, UNKNOWN)
    }
  }

  def processPeer(peer: Channel): Boolean = {
    if (!trustPeers.containsKey(peer.getInetAddress)) {
      if (recentlyDisconnected.getIfPresent(peer) != null) {
        logger.info("Peer {} recently disconnected.", peer.getInetAddress)
        return false
      }
      if (badPeers.getIfPresent(peer) != null) {
        peer.disconnect(peer.getNodeStatistics.getDisconnectReason)
        return false
      }
      if (!peer.isActive && activePeers.size >= maxActivePeers) {
        peer.disconnect(TOO_MANY_PEERS)
        return false
      }
      if (getConnectionNum(peer.getInetAddress) >= getMaxActivePeersWithSameIp) {
        peer.disconnect(TOO_MANY_PEERS_WITH_SAME_IP)
        return false
      }
    }
    if (activePeers.containsKey(peer.getNodeIdWrapper)) {
      val channel = activePeers.get(peer.getNodeIdWrapper)
      if (channel.getStartTime > peer.getStartTime) {
        logger.info("Disconnect connection established later, "+channel.getNode)
        channel.disconnect(DUPLICATE_PEER)
      }
      else {
        peer.disconnect(DUPLICATE_PEER)
        return false
      }
    }
    activePeers.put(peer.getNodeIdWrapper, peer)
    logger.info("Add active peer "+peer+", total active peers: "+activePeers.size)
    true
  }

  def getConnectionNum(inetAddress: InetAddress): Int = {
    var cnt = 0
    for (channel <- activePeers.values) {
      if (channel.getInetAddress == inetAddress) cnt += 1
    }
    return cnt
  }

  def getActivePeers: util.Collection[Channel] = {
    return activePeers.values
  }

  def getRecentlyDisconnected: Cache[InetAddress, ReasonCode] = {
    return this.recentlyDisconnected
  }

  def getBadPeers: Cache[InetAddress, ReasonCode] = {
    return this.badPeers
  }

  def close() {
    peerServer.close()
    peerClient.close()
  }

}
