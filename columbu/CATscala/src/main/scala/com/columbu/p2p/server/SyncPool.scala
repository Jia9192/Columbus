package com.galileo.p2p.server

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.google.common.collect.Lists
import java.net.InetAddress
import java.util
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Predicate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import com.galileo.p2p.client.PeerClient
import com.galileo.p2p.discover.node.Node
import com.galileo.p2p.discover.node.NodeHandler
import com.galileo.p2p.discover.node.NodeManager
import com.galileo.p2p.discover.node.statistics.NodeStatistics
import com.galileo.core.config.args.Args
import com.galileo.p2p.net.peer.PeerConnection
import com.galileo.p2p.net.peer.PeerConnectionDelegate
import scala.collection.JavaConversions._

@Component
class SyncPool {

  val logger: Logger = LoggerFactory.getLogger(getClass())

  private val factor: Double = Args.getInstance.getConnectFactor
  private val activeFactor: Double = Args.getInstance.getActiveConnectFactor
  private var activePeers: util.List[PeerConnection] = util.Collections.synchronizedList(new util.ArrayList[PeerConnection])
  private val passivePeersCount: AtomicInteger = new AtomicInteger(0)
  private val activePeersCount: AtomicInteger = new AtomicInteger(0)
  private val nodeHandlerCache: Cache[NodeHandler, Long] = CacheBuilder.newBuilder.maximumSize(1000).expireAfterWrite(180, TimeUnit.SECONDS).recordStats.build.asInstanceOf[Cache[NodeHandler, Long]]
  @Autowired
  private val nodeManager: NodeManager = null
  @Autowired
  private val ctx: ApplicationContext = null
  private var channelManager: ChannelManager = null
  private var peerDel: PeerConnectionDelegate = null
  private val args: Args = Args.getInstance
  private val maxActiveNodes: Int = args.getNodeMaxActiveNodes
  private val getMaxActivePeersWithSameIp: Int = args.getNodeMaxActiveNodesWithSameIp
  private val poolLoopExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private val logExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private var peerClient: PeerClient = null

  def init(peerDel: PeerConnectionDelegate) {
    this.peerDel = peerDel
    channelManager = ctx.getBean(classOf[ChannelManager])
    peerClient = ctx.getBean(classOf[PeerClient])
    for (node <- args.getActiveNodes) {
      nodeManager.getNodeHandler(node).getNodeStatistics.setPredefined(true)
    }
    poolLoopExecutor.scheduleWithFixedDelay(() => {
      try{
        fillUp()
      }catch {
        case t: Throwable => {
          logger.error("Exception in sync worker", t)
        }
      }
    }, 30000, 3600, TimeUnit.MILLISECONDS)
    logExecutor.scheduleWithFixedDelay(() => {
      try{
        logActivePeers()
      }catch {
        case t: Throwable => {

        }
      }
    }, 30, 10, TimeUnit.SECONDS)
  }

  private def fillUp() {
    val lackSize: Int = Math.max((maxActiveNodes * factor).toInt - activePeers.size, (maxActiveNodes * activeFactor - activePeersCount.get).toInt)
    if (lackSize <= 0) return
    val nodesInUse: util.Set[String] = new util.HashSet[String]
    channelManager.getActivePeers.foreach(channel => nodesInUse.add(channel.getPeerId))
    nodesInUse.add(nodeManager.getPublicHomeNode.getHexId)
    val newNodes: util.List[NodeHandler] = nodeManager.getNodes(new NodeSelector(nodesInUse), lackSize)
    newNodes.foreach(n => {
      peerClient.connectAsync(n, false);
      nodeHandlerCache.put(n, System.currentTimeMillis());
    })
  }

  // for test only
  def addActivePeers(p: PeerConnection) {
    activePeers.add(p)
  }

  private[server] def logActivePeers() {
    logger.info("-------- active connect channel "+activePeersCount.get)
    logger.info("-------- passive connect channel "+passivePeersCount.get)
    logger.info("-------- all connect channel "+channelManager.getActivePeers.size)
    for (channel <- channelManager.getActivePeers) {
      logger.info(channel.toString)
    }
    if (logger.isInfoEnabled) {
      val sb: StringBuilder = new StringBuilder("Peer stats:\n")
      sb.append("Active peers\n")
      sb.append("============\n")
      val activeSet: util.Set[Node] = new util.HashSet[Node]
      import scala.collection.JavaConversions._
      for (peer <- new util.ArrayList[PeerConnection](activePeers)) {
        sb.append(peer.logSyncStats).append('\n')
        activeSet.add(peer.getNode)
      }
      sb.append("Other connected peers\n")
      sb.append("============\n")
      import scala.collection.JavaConversions._
      for (peer <- new util.ArrayList[Channel](channelManager.getActivePeers)) {
        if (!activeSet.contains(peer.getNode)) sb.append(peer.getNode).append('\n')
      }
      logger.info(sb.toString)
    }
  }

  def getActivePeers: util.List[PeerConnection] = {
    val peers: util.List[PeerConnection] = Lists.newArrayList()
    activePeers.foreach(peer => {
      if (!peer.isDisconnect){
        peers.add(peer)
      }
    })
    return peers
  }

  def onConnect(peer: Channel) {
    if (!activePeers.contains(peer)) {
      if (!peer.isActive) {
        passivePeersCount.incrementAndGet
      }
      else {
        activePeersCount.incrementAndGet
      }
      activePeers.add(peer.asInstanceOf[PeerConnection])
      activePeers = activePeers.sortBy(o=>o.getPeerStats.getAvgLatency)
      peerDel.onConnectPeer(peer.asInstanceOf[PeerConnection])
    }
  }

  def onDisconnect(peer: Channel) {
    if (activePeers.contains(peer)) {
      if (!peer.isActive) {
        passivePeersCount.decrementAndGet
      }
      else {
        activePeersCount.decrementAndGet
      }
      activePeers.remove(peer)
      peerDel.onDisconnectPeer(peer.asInstanceOf[PeerConnection])
    }
  }

  def isCanConnect: Boolean = {
    if (passivePeersCount.get >= maxActiveNodes * (1 - activeFactor)) {
      return false
    }
    return true
  }

  def close() {
    try {
      poolLoopExecutor.shutdownNow
      logExecutor.shutdownNow
    }
    catch {
      case e: Exception => {
        logger.warn("Problems shutting down executor", e)
      }
    }
  }

  private class NodeSelector(var nodesInUse: util.Set[String]) extends Predicate[NodeHandler] {
    def test(handler: NodeHandler): Boolean = {
      if (handler.getNode.getHost == nodeManager.getPublicHomeNode.getHost && handler.getNode.getPort == nodeManager.getPublicHomeNode.getPort) {
        return false
      }
      if (nodesInUse != null && nodesInUse.contains(handler.getNode.getHexId)) {
        return false
      }
      if (handler.getNodeStatistics.getReputation >= NodeStatistics.REPUTATION_PREDEFINED) {
        return true
      }
      val inetAddress: InetAddress = handler.getInetSocketAddress.getAddress
      if (channelManager.getRecentlyDisconnected.getIfPresent(inetAddress) != null) {
        return false
      }
      if (channelManager.getBadPeers.getIfPresent(inetAddress) != null) {
        return false
      }
      if (channelManager.getConnectionNum(inetAddress) >= getMaxActivePeersWithSameIp) {
        return false
      }
      if (nodeHandlerCache.getIfPresent(handler).isInstanceOf[Long]) {
        return false
      }
      if (handler.getNodeStatistics.getReputation < 100) {
        return false
      }
      return true
    }
  }
}
