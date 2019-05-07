package com.galileo.p2p.net.peer

import com.galileo.core.config.Parameter.NetConstants.MAX_INVENTORY_SIZE_IN_MINUTES
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.util
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.LinkedBlockingQueue
import javafx.util.Pair

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.message.HelloMessage
import com.galileo.p2p.message.Message
import com.galileo.p2p.server.Channel
import com.galileo.utils.Sha256Hash
import com.galileo.utils.Time
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.core.config.Parameter.NodeConstant
import com.galileo.core.config.args.Args
import com.galileo.p2p.net.node.Item
import org.slf4j.LoggerFactory

import scala.beans.BeanProperty

@Component
@Scope("prototype")
class PeerConnection extends Channel{

  private val logger = LoggerFactory.getLogger(getClass())

  private val syncBlockIdCache: Cache[Sha256Hash, Integer] = CacheBuilder.newBuilder.maximumSize(2 * NodeConstant.SYNC_FETCH_BATCH_NUM).build[Sha256Hash, Integer]()

  @BeanProperty
  var lastSyncBlockId: BlockId = null
  @BeanProperty
  var remainNum: Long = 0L
  @BeanProperty
  var lastBlockUpdateTime: Long = System.currentTimeMillis

  private var syncFlag: Boolean = true
  private var helloMessage: HelloMessage = null
  //broadcast
  private var invToUs: util.Queue[Sha256Hash] = new LinkedBlockingQueue[Sha256Hash]
  private var invWeAdv: util.Queue[Sha256Hash] = new LinkedBlockingQueue[Sha256Hash]
  private val advObjSpreadToUs: util.Map[Sha256Hash, Long] = new ConcurrentHashMap[Sha256Hash, Long]
  private val advObjWeSpread: util.Map[Sha256Hash, Long] = new ConcurrentHashMap[Sha256Hash, Long]
  private var advObjWeRequested: util.Map[Item, Long] = new ConcurrentHashMap[Item, Long]
  private var advInhibit: Boolean = false

  def getAdvObjSpreadToUs: util.Map[Sha256Hash, Long] = advObjSpreadToUs

  def getAdvObjWeSpread: util.Map[Sha256Hash, Long] = advObjWeSpread

  def isAdvInhibit: Boolean = advInhibit

  def setAdvInhibit(advInhibit: Boolean) {
    this.advInhibit = advInhibit
  }

  //sync chain
  private var headBlockWeBothHave: BlockId = new BlockId
  private var headBlockTimeWeBothHave: Long = 0L
  private val syncBlockToFetch: util.Deque[BlockId] = new ConcurrentLinkedDeque[BlockId]
  private var syncBlockRequested: util.Map[BlockId, Long] = new ConcurrentHashMap[BlockId, Long]
  private var syncChainRequested: Pair[util.Deque[BlockId], Long] = null

  def getSyncChainRequested: Pair[util.Deque[BlockId], Long] = syncChainRequested

  def getSyncBlockIdCache: Cache[Sha256Hash, Integer] = syncBlockIdCache

  def setSyncChainRequested(syncChainRequested: Pair[util.Deque[BlockId], Long]) {
    this.syncChainRequested = syncChainRequested
  }

  def getSyncBlockRequested: util.Map[BlockId, Long] = syncBlockRequested

  def setSyncBlockRequested(syncBlockRequested: ConcurrentHashMap[BlockId, Long]) {
    this.syncBlockRequested = syncBlockRequested
  }

  def getUnfetchSyncNum: Long = unfetchSyncNum

  def setUnfetchSyncNum(unfetchSyncNum: Long) {
    this.unfetchSyncNum = unfetchSyncNum
  }

  private var unfetchSyncNum: Long = 0L
  private var needSyncFromPeer: Boolean = false
  private var needSyncFromUs: Boolean = false

  def getBlockInProc: util.Set[BlockId] = blockInProc

  def setBlockInProc(blockInProc: util.Set[BlockId]) {
    this.blockInProc = blockInProc
  }

  private var banned: Boolean = false
  private var blockInProc: util.Set[BlockId] = new util.HashSet[BlockId]

  def getAdvObjWeRequested: util.Map[Item, Long] = advObjWeRequested

  def setAdvObjWeRequested(advObjWeRequested: ConcurrentHashMap[Item, Long]) {
    this.advObjWeRequested = advObjWeRequested
  }

  def setHelloMessage(helloMessage: HelloMessage) {
    this.helloMessage = helloMessage
  }

  def getHelloMessage: HelloMessage = this.helloMessage

  def cleanInvGarbage() {
    val oldestTimestamp: Long = Time.getCurrentMillis - MAX_INVENTORY_SIZE_IN_MINUTES * 60 * 1000
    var iterator: util.Iterator[util.Map.Entry[Sha256Hash, Long]] = this.advObjSpreadToUs.entrySet.iterator
    removeIterator(iterator, oldestTimestamp)
    iterator = this.advObjWeSpread.entrySet.iterator
    removeIterator(iterator, oldestTimestamp)
  }

  private def removeIterator(iterator: util.Iterator[util.Map.Entry[Sha256Hash, Long]], oldestTimestamp: Long) {
    while (iterator.hasNext) {
      val entry: util.Map.Entry[_, _] = iterator.next
      val ts: Long = entry.getValue.asInstanceOf[Long]
      if (ts < oldestTimestamp) iterator.remove()
    }
  }

  def isAdvInvFull: Boolean = advObjSpreadToUs.size > MAX_INVENTORY_SIZE_IN_MINUTES * 60 * Args.getInstance.getNetMaxTrxPerSecond

  def isBanned: Boolean = banned

  def setBanned(banned: Boolean) {
    this.banned = banned
  }

  def getHeadBlockWeBothHave: BlockId = headBlockWeBothHave

  def setHeadBlockWeBothHave(headBlockWeBothHave: BlockId) {
    this.headBlockWeBothHave = headBlockWeBothHave
  }

  def getHeadBlockTimeWeBothHave: Long = headBlockTimeWeBothHave

  def setHeadBlockTimeWeBothHave(headBlockTimeWeBothHave: Long) {
    this.headBlockTimeWeBothHave = headBlockTimeWeBothHave
  }

  def getSyncBlockToFetch: util.Deque[BlockId] = syncBlockToFetch

  def isNeedSyncFromPeer: Boolean = needSyncFromPeer

  def setNeedSyncFromPeer(needSyncFromPeer: Boolean) {
    this.needSyncFromPeer = needSyncFromPeer
  }

  def isNeedSyncFromUs: Boolean = needSyncFromUs

  def setNeedSyncFromUs(needSyncFromUs: Boolean) {
    this.needSyncFromUs = needSyncFromUs
  }

  def getInvToUs: util.Queue[Sha256Hash] = invToUs

  def setInvToUs(invToUs: util.Queue[Sha256Hash]) {
    this.invToUs = invToUs
  }

  def getInvWeAdv: util.Queue[Sha256Hash] = invWeAdv

  def setInvWeAdv(invWeAdv: util.Queue[Sha256Hash]) {
    this.invWeAdv = invWeAdv
  }

  def getSyncFlag: Boolean = syncFlag

  def setSyncFlag(syncFlag: Boolean) {
    this.syncFlag = syncFlag
  }

  def logSyncStats: String = {
    var logMsg =
      """
        |Peer %s: [ %18s, ping %6s ms]-----------
        |connect time: %s
        |last know block num: %s
        |needSyncFromPeer:%b
        |needSyncFromUs:%b
        |syncToFetchSize:%d
        |syncToFetchSizePeekNum:%d
        |syncBlockRequestedSize:%d
        |unFetchSynNum:%d
        |syncChainRequested:%s
        |blockInPorc:%d
        |
        |
        |
      """
    logMsg = logMsg.format(
      this.getNode.getHost + ":" + this.getNode.getPort,
      this.getNode.getHexIdShort,
      this.getPeerStats.getAvgLatency.toInt,
      Time.getTimeString(super.getStartTime),
      headBlockWeBothHave.getNum,
      isNeedSyncFromPeer,
      isNeedSyncFromUs,
      syncBlockToFetch.size,
      if (syncBlockToFetch.size > 0) syncBlockToFetch.peek.getNum else -1,
      syncBlockRequested.size,
      unfetchSyncNum,
      if (syncChainRequested == null) "NULL" else Time.getTimeString(syncChainRequested.getValue),
      blockInProc.size)
    return logMsg + nodeStatistics.toString + "\n"
  }

  def isBusy: Boolean = !idle

  def idle: Boolean = advObjWeRequested.isEmpty && syncBlockRequested.isEmpty && syncChainRequested == null

  def sendMessage(message: Message) {
    msgQueue.sendMessage(message)
  }

}
