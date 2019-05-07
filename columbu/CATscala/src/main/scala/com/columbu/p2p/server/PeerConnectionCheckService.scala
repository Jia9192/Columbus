package com.galileo.p2p.server

import java.util
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.galileo.p2p.discover.node.statistics.NodeStatistics
import com.galileo.utils.CollectionUtils
import com.galileo.core.config.args.Args
import com.galileo.db.levelDB.store.Manager
import com.galileo.p2p.net.peer.PeerConnection
import com.galileo.protos.protos.Protocol.ReasonCode
import org.slf4j.LoggerFactory
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

@Service
class PeerConnectionCheckService {

  private val logger = LoggerFactory.getLogger(getClass())

  val CHECK_TIME: Long = 5 * 60 * 1000L
  private val disconnectNumberFactor: Double = Args.getInstance.getDisconnectNumberFactor
  private val maxConnectNumberFactor: Double = Args.getInstance.getMaxConnectNumberFactor
  @Autowired
  private val pool: SyncPool = null
  @Autowired
  private val channelManager: ChannelManager = null
  @Autowired
  private val manager: Manager = null
  private val scheduledExecutorService: ScheduledExecutorService = Executors.newScheduledThreadPool(2, r => new Thread(r, "check-peer-connect"))

  @PostConstruct def check() {
    logger.info("start the PeerConnectionCheckService")
    scheduledExecutorService.scheduleWithFixedDelay(new CheckDataTransferTask, 5, 5, TimeUnit.MINUTES)
    if (Args.getInstance.isOpenFullTcpDisconnect) scheduledExecutorService.scheduleWithFixedDelay(new CheckConnectNumberTask, 4, 1, TimeUnit.MINUTES)
  }

  @PreDestroy def destroy() {
    scheduledExecutorService.shutdown()
  }

  private class CheckDataTransferTask extends Runnable {
    def run() {
      val peerConnectionList: util.List[PeerConnection] = pool.getActivePeers
      val willDisconnectPeerList: util.List[PeerConnection] = new util.ArrayList[PeerConnection]

      for (peerConnection <- peerConnectionList) {
        val nodeStatistics: NodeStatistics = peerConnection.getNodeStatistics
        if (!nodeStatistics.nodeIsHaveDataTransfer && System.currentTimeMillis - peerConnection.getStartTime >= CHECK_TIME && !peerConnection.isTrustPeer && !nodeStatistics.isPredefined) {
          //if xxx minutes not have data transfer,disconnect the peer,exclude trust peer and active peer
          willDisconnectPeerList.add(peerConnection)
        }
        nodeStatistics.resetTcpFlow()
      }
      if (!willDisconnectPeerList.isEmpty && peerConnectionList.size > Args.getInstance.getNodeMaxActiveNodes * maxConnectNumberFactor) {
        util.Collections.shuffle(willDisconnectPeerList)
        var i: Int = 0
        while (i < willDisconnectPeerList.size * disconnectNumberFactor) {
          {
            logger.error(willDisconnectPeerList.get(i).getInetAddress+" not have data transfer, disconnect the peer")
            willDisconnectPeerList.get(i).disconnect(ReasonCode.TOO_MANY_PEERS)
          }
          {
            i += 1; i - 1
          }
        }
      }
    }
  }

  private class CheckConnectNumberTask extends Runnable {
    def run() {
      if (pool.getActivePeers.size >= Args.getInstance.getNodeMaxActiveNodes) {
        logger.warn("connection pool is full")
        var peerList: util.List[PeerConnection] = new util.ArrayList[PeerConnection]
        for (peer <- pool.getActivePeers) {
          if (!peer.isTrustPeer && !peer.getNodeStatistics.isPredefined) peerList.add(peer)
        }
        if (peerList.size >= 2) {
          peerList = peerList.sortBy(o=>o.getNodeStatistics.getReputation)
          peerList = CollectionUtils.truncateRandom(peerList, 2, 1)
        }
        for (peerConnection <- peerList) {
          logger.warn("connection pool is full, disconnect the peer : "+peerConnection.getInetAddress)
          peerConnection.disconnect(ReasonCode.RESET)
        }
      }
    }
  }
}
