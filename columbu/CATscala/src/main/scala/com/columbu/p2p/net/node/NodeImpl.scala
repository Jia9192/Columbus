package com.galileo.p2p.net.node

import com.galileo.core.config.Parameter.ChainConstant.BLOCK_PRODUCED_INTERVAL
import com.galileo.core.config.Parameter.NetConstants.MAX_TRX_PER_PEER
import com.galileo.core.config.Parameter.NetConstants.MSG_CACHE_DURATION_IN_BLOCKS
import com.galileo.core.config.Parameter.NodeConstant.MAX_BLOCKS_SYNC_FROM_ONE_PEER
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import com.google.common.util.concurrent.ThreadFactoryBuilder
import java.util
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import javafx.util.Pair

import org.apache.commons.collections4.CollectionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.galileo.p2p.discover.node.statistics.MessageCount
import com.galileo.p2p.message.Message
import com.galileo.core.enumeration.TronState
import com.galileo.p2p.server.SyncPool
import com.galileo.utils.ExecutorLoop
import com.galileo.utils.Sha256Hash
import com.galileo.utils.SlidingWindowCounter
import com.galileo.utils.Time
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.config.Parameter.ChainConstant
import com.galileo.core.config.Parameter.NetConstants
import com.galileo.core.config.Parameter.NodeConstant
import com.galileo.core.config.args.Args
import com.galileo.core.exception.BadBlockException
import com.galileo.core.exception.BadTransactionException
import com.galileo.core.exception.NonCommonBlockException
import com.galileo.core.exception.StoreException
import com.galileo.core.exception.TraitorPeerException
import com.galileo.core.exception.TronException
import com.galileo.core.exception.UnLinkedBlockException
import com.galileo.p2p.net.message.BlockMessage
import com.galileo.p2p.net.message.ChainInventoryMessage
import com.galileo.p2p.net.message.FetchInvDataMessage
import com.galileo.p2p.net.message.InventoryMessage
import com.galileo.p2p.net.message.ItemNotFound
import com.galileo.core.enumeration.MessageTypes
import com.galileo.core.enumeration.MessageTypes._
import com.galileo.p2p.net.message.SyncBlockChainMessage
import com.galileo.p2p.net.message.TransactionMessage
import com.galileo.p2p.net.message.TransactionsMessage
import com.galileo.p2p.net.message.TronMessage
import com.galileo.p2p.net.peer.{PeerConnection, PeerConnectionDelegate}
import com.galileo.protos.protos.Protocol
import com.galileo.protos.protos.Protocol.Inventory.InventoryType
import com.galileo.protos.protos.Protocol.{ReasonCode, Transaction}
import org.slf4j.LoggerFactory
import scala.collection.JavaConverters._
import scala.util.control.Breaks


@Component
class NodeImpl extends PeerConnectionDelegate with Node{

  private val logger = LoggerFactory.getLogger(getClass())

  @Autowired
  private var pool: SyncPool = null

  private val trxCount: MessageCount = new MessageCount
  private val TrxCache: Cache[Sha256Hash, TransactionMessage] = CacheBuilder.newBuilder.maximumSize(50000).expireAfterWrite(1, TimeUnit.HOURS).initialCapacity(50000).recordStats.build.asInstanceOf[Cache[Sha256Hash, TransactionMessage]]
  private val BlockCache: Cache[Sha256Hash, BlockMessage] = CacheBuilder.newBuilder.maximumSize(10).expireAfterWrite(60, TimeUnit.SECONDS).recordStats.build.asInstanceOf[Cache[Sha256Hash, BlockMessage]]
  private val fetchWaterLine: SlidingWindowCounter = new SlidingWindowCounter(BLOCK_PRODUCED_INTERVAL * MSG_CACHE_DURATION_IN_BLOCKS / 100)
  private val maxTrxsSize: Int = 1000000
  private val maxTrxsCnt: Int = 100
  private val blockUpdateTimeout: Long = 20000
  private val logExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private val trxsHandlePool: ExecutorService = Executors.newFixedThreadPool(Args.getInstance.getValidateSignThreadNum, new ThreadFactoryBuilder().setNameFormat("TrxsHandlePool-%d").build)

  private val freshBlockId: util.Queue[BlockCapsule.BlockId] = new ConcurrentLinkedQueue[BlockCapsule.BlockId]() {
    override def offer(blockId: BlockCapsule.BlockId): Boolean = {
      if (size > 200) super.poll
      return super.offer(blockId)
    }
  }
  private val syncMap: ConcurrentHashMap[Sha256Hash, PeerConnection] = new ConcurrentHashMap[Sha256Hash, PeerConnection]
  private val fetchMap: ConcurrentHashMap[Sha256Hash, PeerConnection] = new ConcurrentHashMap[Sha256Hash, PeerConnection]
  private var del: NodeDelegate = null
  private var isAdvertiseActive: Boolean = false
  private var isFetchActive: Boolean = false
  private val disconnectInactiveExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private val cleanInventoryExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  //broadcast
  private val advObjToSpread: ConcurrentHashMap[Sha256Hash, InventoryType] = new ConcurrentHashMap[Sha256Hash, InventoryType]
  private val advObjWeRequested: util.HashMap[Sha256Hash, Long] = new util.HashMap[Sha256Hash, Long]
  private val advObjToFetch: ConcurrentHashMap[Sha256Hash, PriorItem] = new ConcurrentHashMap[Sha256Hash, PriorItem]
  private val broadPool: ExecutorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
    def newThread(r: Runnable): Thread = return new Thread(r, "broad-msg")
  })
  private val syncBlockIdWeRequested: Cache[BlockId, Long] = CacheBuilder.newBuilder.maximumSize(10000).expireAfterWrite(1, TimeUnit.HOURS).initialCapacity(10000).recordStats.build.asInstanceOf[Cache[BlockId, Long]]
  private var unSyncNum: Long = 0L
  private val blockWaitToProc: util.Map[BlockMessage, PeerConnection] = new ConcurrentHashMap[BlockMessage, PeerConnection]
  private val blockJustReceived: util.Map[BlockMessage, PeerConnection] = new ConcurrentHashMap[BlockMessage, PeerConnection]
  private var loopSyncBlockChain: ExecutorLoop[SyncBlockChainMessage] = null
  private var loopFetchBlocks: ExecutorLoop[FetchInvDataMessage] = null
  private var loopAdvertiseInv: ExecutorLoop[Message] = null
  private val fetchSyncBlocksExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private val handleSyncBlockExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private val fetchWaterLineExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor
  private var isHandleSyncBlockActive: Boolean = false
  private val fetchSequenceCounter: AtomicLong = new AtomicLong(0L)
  private var isSuspendFetch: Boolean = false
  private var isFetchSyncActive: Boolean = false

  def onMessage(peer: PeerConnection, msg: TronMessage) {
    msg.getType match {
      case BLOCK =>
        onHandleBlockMessage(peer, msg.asInstanceOf[BlockMessage])
      case TRX =>
        onHandleTransactionMessage(peer, msg.asInstanceOf[TransactionMessage])
      case TRXS =>
        onHandleTransactionsMessage(peer, msg.asInstanceOf[TransactionsMessage])
      case SYNC_BLOCK_CHAIN =>
        onHandleSyncBlockChainMessage(peer, msg.asInstanceOf[SyncBlockChainMessage])
      case FETCH_INV_DATA =>
        onHandleFetchDataMessage(peer, msg.asInstanceOf[FetchInvDataMessage])
      case BLOCK_CHAIN_INVENTORY =>
        onHandleChainInventoryMessage(peer, msg.asInstanceOf[ChainInventoryMessage])
      case INVENTORY =>
        onHandleInventoryMessage(peer, msg.asInstanceOf[InventoryMessage])
      case _ =>
        throw new IllegalArgumentException("No such message")
    }
  }

  def getMessage(msgId: Sha256Hash): Message = return null

  def setNodeDelegate(nodeDel: NodeDelegate) {
    this.del = nodeDel
  }

  // for test only
  def setPool(pool: SyncPool) {
    this.pool = pool
  }

  def broadcast(msg: Message) {
    var `type`: InventoryType = null
    if (msg.isInstanceOf[BlockMessage]) {
      logger.info("Ready to broadcast block {}", (msg.asInstanceOf[BlockMessage]).getBlockId)
      freshBlockId.offer((msg.asInstanceOf[BlockMessage]).getBlockId)
      BlockCache.put(msg.getMessageId, msg.asInstanceOf[BlockMessage])
      `type` = InventoryType.BLOCK
    }
    else if (msg.isInstanceOf[TransactionMessage]) {
      TrxCache.put(msg.getMessageId, msg.asInstanceOf[TransactionMessage])
      `type` = InventoryType.TRX
    }
    else return
    advObjToSpread synchronized {
      advObjToSpread.put(msg.getMessageId, `type`)
    }
  }

  def listen() {
    pool.init(this)
    isAdvertiseActive = true
    isFetchActive = true
    activeTronPump()
  }

  def close() {
    getActivePeer.asScala.foreach(peer => disconnectPeer(peer, ReasonCode.REQUESTED))
  }

  private def activeTronPump() {
    loopAdvertiseInv = new ExecutorLoop[Message](2, 10, b => {
      for (peer:PeerConnection <- getActivePeer.asScala) {
        if (!peer.isNeedSyncFromUs) {
          logger.info("Advertise adverInv to " + peer)
          peer.sendMessage(b)
        }
      }
    }, throwable => logger.error("Unhandled exception: ", throwable))
    // fetch blocks
    loopFetchBlocks = new ExecutorLoop[FetchInvDataMessage](2, 10, c => {
      logger.info("loop fetch blocks");
      if (fetchMap.containsKey(c.getMessageId)) fetchMap.get(c.getMessageId).sendMessage(c)
    }, throwable => logger.error("Unhandled exception: ", throwable))
    // sync block chain
    loopSyncBlockChain = new ExecutorLoop[SyncBlockChainMessage](2, 10, d => {
      if (syncMap.containsKey(d.getMessageId)) syncMap.get(d.getMessageId).sendMessage(d)
    }, throwable => logger.error("Unhandled exception: ", throwable))

    val runnable1: Runnable = () => {
      while (isAdvertiseActive) {
        consumerAdvObjToSpread();
      }
    }
    broadPool.submit(runnable1)
    val runnable2: Runnable = () => {
      while (isFetchActive) {
        consumerAdvObjToFetch();
      }
    }
    broadPool.submit(runnable2)
    handleSyncBlockExecutor.scheduleWithFixedDelay(() => {
      try {
          if (isHandleSyncBlockActive) {
            isHandleSyncBlockActive = false
            handleSyncBlock()
          }
      } catch {
        case t: Throwable => {
          logger.error("Unhandled exception", t)
        }
      }
    }, 10, 1, TimeUnit.SECONDS)
    //terminate inactive loop
    disconnectInactiveExecutor.scheduleWithFixedDelay(() => {
      try {
        disconnectInactive()
      } catch {
        case t: Throwable => {
          logger.error("Unhandled exception", t)
        }
      }
    }, 30000, BLOCK_PRODUCED_INTERVAL / 2, TimeUnit.MILLISECONDS)
    logExecutor.scheduleWithFixedDelay(() => {
      try {
        logNodeStatus()
      } catch {
        case t: Throwable => {
          logger.error("Exception in log worker", t)
        }
      }
    }, 10, 10, TimeUnit.SECONDS)
    cleanInventoryExecutor.scheduleWithFixedDelay(() => {
      try {
        getActivePeer.asScala.foreach(p => p.cleanInvGarbage())
      } catch {
        case t: Throwable => {
          logger.error("Unhandled exception", t)
        }
      }
    }, 2, NetConstants.MAX_INVENTORY_SIZE_IN_MINUTES / 2, TimeUnit.MINUTES)
    fetchSyncBlocksExecutor.scheduleWithFixedDelay(() => {
      try {
        if (isFetchSyncActive){
          if (!isSuspendFetch){
            startFetchSyncBlock()
          }
        } else {
          logger.debug("suspend")
        }
        isFetchSyncActive = false
      } catch {
        case t: Throwable => {
          logger.error("Unhandled exception", t)
        }
      }
    }, 10, 1, TimeUnit.SECONDS)
    //fetchWaterLine:
    fetchWaterLineExecutor.scheduleWithFixedDelay(() => {
      try {
        fetchWaterLine.advance
      } catch {
        case t: Throwable => {
          logger.error("Unhandled exception", t)
        }
      }
    }, 1000, 100, TimeUnit.MILLISECONDS)
  }

  private def consumerAdvObjToFetch() {
    val filterActivePeer: util.Collection[PeerConnection] = getActivePeer.asScala.filter(p => !p.isBusy).toList.asJavaCollection
    if (advObjToFetch.isEmpty || filterActivePeer.isEmpty) {
      try {
        Thread.sleep(100)
        return
      } catch {
        case e: InterruptedException => {
          logger.debug(e.getMessage, e)
        }
      }
    }
    val sendPackage: InvToSend = new InvToSend
    val now: Long = Time.getCurrentMillis
    advObjToFetch.values().asScala.toList.sortWith{case (a,b) => a.compareTo(b)}.foreach(idToFetch => {
      val hash = idToFetch.getHash
      if (idToFetch.getTime < now - MSG_CACHE_DURATION_IN_BLOCKS * BLOCK_PRODUCED_INTERVAL) {
        logger.info("This obj is too late to fetch, type: "+idToFetch.getType+" hash: " + idToFetch.getHash)
        advObjToFetch.remove(hash)
        return
      }
      val headOption = filterActivePeer.asScala.filter(peer => peer.getAdvObjSpreadToUs.containsKey(hash) && sendPackage.getSize(peer) < MAX_TRX_PER_PEER)
          .toList.sortWith{ case (a,b) => sendPackage.getSize(a)<sendPackage.getSize(b)}.headOption
      if(headOption != None){
        val peer = headOption.asInstanceOf[PeerConnection]
        sendPackage.add(idToFetch, peer)
        peer.getAdvObjWeRequested.put(idToFetch.getItem, now)
        advObjToFetch.remove(hash)
      }
    })
    sendPackage.sendFetch()
  }

  private def consumerAdvObjToSpread() {
    if (advObjToSpread.isEmpty) {
      try {
        Thread.sleep(100)
        return
      }
      catch {
        case e: InterruptedException => {
          logger.debug(e.getMessage, e)
        }
      }
    }
    val sendPackage: InvToSend = new InvToSend
    val spread: util.HashMap[Sha256Hash, InventoryType] = new util.HashMap[Sha256Hash, InventoryType]
    advObjToSpread synchronized {
      spread.putAll(advObjToSpread)
      advObjToSpread.clear()
    }
    for (inventoryType:InventoryType <- spread.values.asScala) {
      if (inventoryType == InventoryType.TRX) {
        trxCount.add()
      }
    }
    getActivePeer.asScala.filter(peer => !peer.isNeedSyncFromUs).foreach(peer =>{
      spread.entrySet().asScala.filter(idToSpread => !peer.getAdvObjSpreadToUs.containsKey(idToSpread.getKey()) && !peer.getAdvObjWeSpread.containsKey(idToSpread.getKey()))
          .foreach(idToSpread => {
            peer.getAdvObjWeSpread.put(idToSpread.getKey(), Time.getCurrentMillis);
            sendPackage.add(idToSpread, peer);
        })
    })
    sendPackage.sendInv()
  }

  private def handleSyncBlock() {
    if (isSuspendFetch){
      isSuspendFetch = false
    }
    val isBlockProc: Array[Boolean] = Array(true)
    while (isBlockProc(0)) {
      isBlockProc(0) = false
      blockJustReceived synchronized {
        blockWaitToProc.putAll(blockJustReceived)
        blockJustReceived.clear()
      }
      blockWaitToProc.asScala.foreach(e=>{
        val (msg, peerConnection) = e
        if (peerConnection.isDisconnect) {
          logger.error("Peer "+peerConnection.getNode.getHost+" is disconnect, drop block " + msg.getBlockId.getString)
          blockWaitToProc.remove(msg)
          syncBlockIdWeRequested.invalidate(msg.getBlockId)
          isFetchSyncActive = true
          return
        }
        freshBlockId synchronized {
          val isFound = Array(false)
          getActivePeer.asScala.filter(peer => !peer.getSyncBlockToFetch.isEmpty() && peer.getSyncBlockToFetch.peek().equals(msg.getBlockId))
            .foreach(peer => {
            peer.getSyncBlockToFetch.pop()
            peer.getBlockInProc.add(msg.getBlockId)
            isFound(0) = true
          })
          if (isFound(0)) {
            blockWaitToProc.remove(msg)
            isBlockProc(0) = true
            if (freshBlockId.contains(msg.getBlockId) || processSyncBlock(msg.getBlockCapsule)){
              finishProcessSyncBlock(msg.getBlockCapsule)
            }
          }
        }
      })
    }
  }

  private def logNodeStatus() {
    val sb: StringBuilder = new StringBuilder("LocalNode stats:\n")
    sb.append("============\n")
    var logMsg = "MyHeadBlockNum: %d\n" + "advObjToSpread: %d\n" + "advObjToFetch: %d\n" + "advObjWeRequested: %d\n" + "unSyncNum: %d\n" + "blockWaitToProc: %d\n" + "blockJustReceived: %d\n" + "syncBlockIdWeRequested: %d\n"
    logMsg = logMsg.format(del.getHeadBlockId.getNum, advObjToSpread.size, advObjToFetch.size, advObjWeRequested.size, getUnSyncNum, blockWaitToProc.size, blockJustReceived.size, syncBlockIdWeRequested.size)
    sb.append(logMsg)
    logger.info(sb.toString)
  }

  def disconnectInactive() {
    getActivePeer.asScala.foreach(peer => {
      val isDisconnected = Array(false)
      if (peer.isNeedSyncFromPeer && peer.getLastBlockUpdateTime < System.currentTimeMillis - blockUpdateTimeout) {
        logger.warn("Peer {} not sync for a long time.", peer.getInetAddress)
        isDisconnected(0) = true
      }
      var headOption = peer.getAdvObjWeRequested.values.asScala.filter(time => time < Time.getCurrentMillis - NetConstants.ADV_TIME_OUT).headOption
      if(headOption != None){
        isDisconnected(0) = true
      }
      if (!isDisconnected(0)) {
        headOption = peer.getSyncBlockRequested.values.asScala.filter(time => time < Time.getCurrentMillis - NetConstants.SYNC_TIME_OUT).headOption
        if(headOption == None){
          isDisconnected(0) = true
        }
      }
      if (isDisconnected(0)) {
        disconnectPeer(peer, ReasonCode.TIME_OUT)
      }
    })
  }

  private def onHandleInventoryMessage(peer: PeerConnection, msg: InventoryMessage) {
    val loop = new Breaks()
    for (id:Sha256Hash <- msg.getHashList.asScala) {
      loop.breakable {
        if (msg.getInventoryType == InventoryType.TRX && TrxCache.getIfPresent(id) != null) {
          logger.info("{} {} from peer {} Already exist.", msg.getInventoryType, id, peer.getNode.getHost)
          loop.break()
        }
      }
      val spreaded: Array[Boolean] = Array(false)
      val requested: Array[Boolean] = Array(false)
      getActivePeer.asScala.foreach(p => {
        if (p.getAdvObjWeSpread.containsKey(id)) {
          spreaded(0)= true
        }
        if (p.getAdvObjWeRequested.containsKey(new Item(id, msg.getInventoryType))){
          requested(0)= true
        }
      })
      if (!spreaded(0) && !peer.isNeedSyncFromPeer && !peer.isNeedSyncFromUs) {
        //avoid TRX flood attack here.
        if (msg.getInventoryType == InventoryType.TRX && (peer.isAdvInvFull || isFlooded)) {
          logger.warn("A peer is flooding us, stop handle inv, the peer is: " + peer)
          return
        }
        peer.getAdvObjSpreadToUs.put(id, System.currentTimeMillis)
        if (!requested(0)) {
          val targetPriorItem: PriorItem = this.advObjToFetch.get(id)
          if (targetPriorItem != null) {
            //another peer tell this trx to us, refresh its time.
            targetPriorItem.refreshTime()
          }
          else {
            fetchWaterLine.increase
            this.advObjToFetch.put(id, new PriorItem(new Item(id, msg.getInventoryType), fetchSequenceCounter.incrementAndGet))
          }
        }
      }
    }
  }

  private def isFlooded: Boolean = {
    return fetchWaterLine.totalCount > BLOCK_PRODUCED_INTERVAL * Args.getInstance.getNetMaxTrxPerSecond * MSG_CACHE_DURATION_IN_BLOCKS / 1000
  }

  def syncFrom(myHeadBlockHash: Sha256Hash) {
    try {
      while (getActivePeer.isEmpty) {
        {
          logger.info("other peer is nil, please wait ... ")
          Thread.sleep(10000L)
        }
      }
    }
    catch {
      case e: InterruptedException => {
        logger.debug(e.getMessage, e)
      }
    }
    logger.info("wait end")
  }

  private def onHandleBlockMessage(peer: PeerConnection, blkMsg: BlockMessage) {
    val advObjWeRequested: util.Map[Item, Long] = peer.getAdvObjWeRequested
    val syncBlockRequested: util.Map[BlockCapsule.BlockId, Long] = peer.getSyncBlockRequested
    val blockId: BlockCapsule.BlockId = blkMsg.getBlockId
    val item: Item = new Item(blockId, InventoryType.BLOCK)
    var syncFlag: Boolean = false
    if (syncBlockRequested.containsKey(blockId)) {
      if (!peer.getSyncFlag) {
        logger.info("Received a block {} from no need sync peer {}", blockId.getNum, peer.getNode.getHost)
        return
      }
      peer.getSyncBlockRequested.remove(blockId)
      blockJustReceived synchronized {
        blockJustReceived.put(blkMsg, peer)
      }
      isHandleSyncBlockActive = true
      syncFlag = true
      if (!peer.isBusy) {
        if (peer.getUnfetchSyncNum > 0 && peer.getSyncBlockToFetch.size <= NodeConstant.SYNC_FETCH_BATCH_NUM) {
          syncNextBatchChainIds(peer)
        }
        else {
          isFetchSyncActive = true
        }
      }
    }
    if (advObjWeRequested.containsKey(item)) {
      advObjWeRequested.remove(item)
      if (!syncFlag) {
        processAdvBlock(peer, blkMsg.getBlockCapsule)
        startFetchItem()
      }
    }
  }

  private def processAdvBlock(peer: PeerConnection, block: BlockCapsule) {
    freshBlockId synchronized {
      if (!freshBlockId.contains(block.getBlockId)) {
        try {
          var trxIds = del.handleBlock(block, false).asInstanceOf[util.LinkedList[Sha256Hash]]
          freshBlockId.offer(block.getBlockId)
          trxIds.asScala.foreach(trxId => advObjToFetch.remove(trxId))
          getActivePeer.asScala.filter(p => p.getAdvObjSpreadToUs.containsKey(block.getBlockId())).foreach(p => updateBlockWeBothHave(p, block))
          broadcast(new BlockMessage(block))
        }
        catch {
          case e: BadBlockException => {
            logger.error("We get a bad block {}, from {}, reason is {} ", block.getBlockId.getString, peer.getNode.getHost, e.getMessage)
            disconnectPeer(peer, ReasonCode.BAD_BLOCK)
          }
          case e: UnLinkedBlockException => {
            logger.error("We get a unlinked block {}, from {}, head is {}", block.getBlockId.getString, peer.getNode.getHost, del.getHeadBlockId.getString)
            startSyncWithPeer(peer)
          }
          case e: NonCommonBlockException => {
            logger.error("We get a block {} that do not have the most recent common ancestor with the main chain, from {}, reason is {} ", block.getBlockId.getString, peer.getNode.getHost, e.getMessage)
            disconnectPeer(peer, ReasonCode.FORKED)
          }
          case e: InterruptedException => {
            Thread.currentThread.interrupt()
          }
        }
      }
    }
  }

  private def processSyncBlock(block: BlockCapsule): Boolean = {
    var isAccept: Boolean = false
    var reason: Protocol.ReasonCode = null
    try {
      try {
        del.handleBlock(block, true)
      }
      catch {
        case e: InterruptedException => {
          Thread.currentThread.interrupt()
        }
      }
      freshBlockId.offer(block.getBlockId)
      logger.info("Success handle block {}", block.getBlockId.getString)
      isAccept = true
    }
    catch {
      case e: BadBlockException => {
        logger.error("We get a bad block "+block.getBlockId.getString+", reason is " + e.getMessage)
        reason = ReasonCode.BAD_BLOCK
      }
      case e: UnLinkedBlockException => {
        logger.error("We get a unlinked block "+block.getBlockId.getString+", head is " + del.getHeadBlockId.getString)
        reason = ReasonCode.UNLINKABLE
      }
      case e: NonCommonBlockException => {
        logger.error("We get a block "+block.getBlockId.getString+" that do not have the most recent common ancestor with the main chain, head is " + del.getHeadBlockId.getString)
        reason = ReasonCode.FORKED
      }
    }
    if (!isAccept) {
      val finalReason: Protocol.ReasonCode = reason
      getActivePeer.asScala.filter(peer => peer.getBlockInProc.contains(block.getBlockId())).foreach(peer => disconnectPeer(peer, finalReason))
    }
    isHandleSyncBlockActive = true
    return isAccept
  }

  private def finishProcessSyncBlock(block: BlockCapsule) {
    getActivePeer.asScala.foreach(peer => {
      if (peer.getSyncBlockToFetch.isEmpty()
        && peer.getBlockInProc.isEmpty()
        && !peer.isNeedSyncFromPeer
        && !peer.isNeedSyncFromUs) {
        startSyncWithPeer(peer)
      } else if (peer.getBlockInProc.remove(block.getBlockId())) {
        updateBlockWeBothHave(peer, block);
        if (peer.getSyncBlockToFetch.isEmpty()) {
          syncNextBatchChainIds(peer)
        }
      }
    })
  }

  private[node] def isTrxExist(trxMsg: TransactionMessage): Boolean = {
    if (TrxCache.getIfPresent(trxMsg.getMessageId) != null) {
      return true
    }
    TrxCache.put(trxMsg.getMessageId, trxMsg)
    return false
  }

  private def onHandleTransactionMessage(peer: PeerConnection, trxMsg: TransactionMessage) {
    try {
      val item: Item = new Item(trxMsg.getMessageId, InventoryType.TRX)
      if (!peer.getAdvObjWeRequested.containsKey(item)) {
        throw new TraitorPeerException("We don't send fetch request to" + peer)
      }
      peer.getAdvObjWeRequested.remove(item)
      if (isTrxExist(trxMsg)) {
        logger.info("Trx "+trxMsg.getMessageId+" from Peer "+peer.getNode.getHost+" already processed.")
        return
      }
      val transactionCapsule: TransactionCapsule = trxMsg.getTransactionCapsule
      if (del.handleTransaction(transactionCapsule)) {
        broadcast(trxMsg)
      }
    }
    catch {
      case e: TraitorPeerException => {
        logger.error(e.getMessage)
        banTraitorPeer(peer, ReasonCode.BAD_PROTOCOL)
      }
      case e: BadTransactionException => {
        banTraitorPeer(peer, ReasonCode.BAD_TX)
      }
    }
  }

  private def onHandleTransactionsMessage(peer: PeerConnection, msg: TransactionsMessage) {
    for (trans:Transaction <- msg.getTransactions.getTransactionsList.asScala) {
      val runnable: Runnable = () => {
        onHandleTransactionMessage(peer, new TransactionMessage(trans))
      }
      trxsHandlePool.submit(runnable)
    }
  }

  private def checkSyncBlockChainMessage(peer: PeerConnection, syncMsg: SyncBlockChainMessage): Boolean = {
    val lastBlockNum: Long = syncMsg.getBlockIds.get(syncMsg.getBlockIds.size - 1).getNum
    val lastSyncBlockId: BlockCapsule.BlockId = peer.getLastSyncBlockId
    if (lastSyncBlockId != null && lastBlockNum < lastSyncBlockId.getNum) {
      logger.warn("Peer "+peer.getInetAddress+" receive bad SyncBlockChain message, firstNum "+lastBlockNum+" lastSyncNum " + lastSyncBlockId.getNum)
      return false
    }
    return true
  }

  private def onHandleSyncBlockChainMessage(peer: PeerConnection, syncMsg: SyncBlockChainMessage) {
    peer.setTronState(TronState.SYNCING)
    val headBlockId: BlockCapsule.BlockId = del.getHeadBlockId
    var remainNum: Long = 0
    var blockIds = new util.LinkedList[BlockCapsule.BlockId]
    val summaryChainIds: util.List[BlockCapsule.BlockId] = syncMsg.getBlockIds
    if (!checkSyncBlockChainMessage(peer, syncMsg)) {
      disconnectPeer(peer, ReasonCode.BAD_PROTOCOL)
      return
    }
    try {
      blockIds = del.getLostBlockIds(summaryChainIds).asInstanceOf[util.LinkedList[BlockCapsule.BlockId]]
    }
    catch {
      case e: StoreException => {
        logger.error(e.getMessage)
      }
    }
    if (blockIds.isEmpty) {
      if (CollectionUtils.isNotEmpty(summaryChainIds) && !del.canChainRevoke(summaryChainIds.get(0).getNum)) {
        logger.info("Node sync block fail, disconnect peer "+peer+", no block " + summaryChainIds.get(0).getString)
        peer.disconnect(ReasonCode.SYNC_FAIL)
        return
      }
      else {
        peer.setNeedSyncFromUs(false)
      }
    }
    else if (blockIds.size == 1 && !summaryChainIds.isEmpty && (summaryChainIds.contains(blockIds.peekFirst) || blockIds.peek.getNum == 0)) {
      peer.setNeedSyncFromUs(false)
    }
    else {
      peer.setNeedSyncFromUs(true)
      remainNum = del.getHeadBlockId.getNum - blockIds.peekLast.getNum
    }
    if (!peer.isNeedSyncFromPeer && CollectionUtils.isNotEmpty(summaryChainIds) && !del.contain(Iterables.getLast(summaryChainIds), MessageTypes.BLOCK) && del.canChainRevoke(summaryChainIds.get(0).getNum)) {
      startSyncWithPeer(peer)
    }
    if (blockIds.peekLast == null) {
      peer.setLastSyncBlockId(headBlockId)
    }
    else {
      peer.setLastSyncBlockId(blockIds.peekLast)
    }
    peer.setRemainNum(remainNum)
    peer.sendMessage(new ChainInventoryMessage(blockIds, remainNum))
  }

  private def checkFetchInvDataMsg(peer: PeerConnection, fetchInvDataMsg: FetchInvDataMessage): Boolean = {
    val `type`: MessageTypes = fetchInvDataMsg.getInvMessageType
    if (`type` eq MessageTypes.TRX) {
      val elementCount: Int = peer.getNodeStatistics.messageStatistics.tronInTrxFetchInvDataElement.getCount(10)
      val maxCount: Int = trxCount.getCount(60)
      if (elementCount > maxCount) {
        logger.warn("Check FetchInvDataMsg failed: Peer "+peer.getInetAddress+" request count "+elementCount+" in 10s gt trx count "+maxCount+" generate in 60s")
        return false
      }
      for (hash:Sha256Hash <- fetchInvDataMsg.getHashList.asScala) {
        if (!peer.getAdvObjWeSpread.containsKey(hash)) {
          logger.warn("Check FetchInvDataMsg failed: Peer "+peer.getInetAddress+" get trx "+hash+" we not spread.")
          return false
        }
      }
    } else {
      var isAdv: Boolean = true
      val loop = new Breaks()
      loop.breakable {
        for (hash:Sha256Hash <- fetchInvDataMsg.getHashList.asScala) {
          if (!peer.getAdvObjWeSpread.containsKey(hash)) {
            isAdv = false
            loop.break()
          }
        }
      }
      if (isAdv) {
        val tronOutAdvBlock: MessageCount = peer.getNodeStatistics.messageStatistics.tronOutAdvBlock
        tronOutAdvBlock.add(fetchInvDataMsg.getHashList.size)
        val outBlockCountIn1min: Int = tronOutAdvBlock.getCount(60)
        val producedBlockIn2min: Int = 120000 / ChainConstant
        .BLOCK_PRODUCED_INTERVAL
        if (outBlockCountIn1min > producedBlockIn2min) {
          logger.warn("Check FetchInvDataMsg failed: Peer "+peer.getInetAddress+" outBlockCount "+outBlockCountIn1min+" producedBlockIn2min " + producedBlockIn2min)
          return false
        }
      }
      else {
        if (!peer.isNeedSyncFromUs) {
          logger.warn("Check FetchInvDataMsg failed: Peer {} not need sync from us.", peer.getInetAddress)
          return false
        }
        for (hash:Sha256Hash <- fetchInvDataMsg.getHashList.asScala) {
          val blockNum: Long = new BlockCapsule.BlockId(hash).getNum
          val minBlockNum: Long = peer.getLastSyncBlockId.getNum - 2 * NodeConstant.SYNC_FETCH_BATCH_NUM
          if (blockNum < minBlockNum) {
            logger.warn("Check FetchInvDataMsg failed: Peer "+peer.getInetAddress+" blockNum "+blockNum+" lt minBlockNum "+minBlockNum)
            return false
          }
          if (peer.getSyncBlockIdCache.getIfPresent(hash) != null) {
            logger.warn("Check FetchInvDataMsg failed: Peer "+peer.getInetAddress+" blockNum "+blockNum+" hash "+hash+" is exist")
            return false
          }
          peer.getSyncBlockIdCache.put(hash, 1)
        }
      }
    }
    return true
  }

  private def onHandleFetchDataMessage(peer: PeerConnection, fetchInvDataMsg: FetchInvDataMessage) {
    if (!checkFetchInvDataMsg(peer, fetchInvDataMsg)) {
      disconnectPeer(peer, ReasonCode.BAD_PROTOCOL)
      return
    }
    val `type`: MessageTypes = fetchInvDataMsg.getInvMessageType
    var block: BlockCapsule = null
    var transactions: util.List[Protocol.Transaction] = Lists.newArrayList()
    var size: Int = 0
    for (hash:Sha256Hash <- fetchInvDataMsg.getHashList.asScala) {
      var msg: Message = null
      if (`type` eq MessageTypes.BLOCK) {
        msg = BlockCache.getIfPresent(hash)
      }
      else {
        msg = TrxCache.getIfPresent(hash)
      }
      if (msg == null) {
        try {
          msg = del.getData(hash, `type`)
        }
        catch {
          case e: StoreException => {
            logger.error("fetch message "+`type`+" "+hash+" failed.")
            peer.sendMessage(new ItemNotFound)
            //todo,should be disconnect?
            return
          }
        }
      }
      if (`type` == MessageTypes.BLOCK) {
        block = (msg.asInstanceOf[BlockMessage]).getBlockCapsule
        peer.sendMessage(msg)
      }
      else {
        transactions.add((msg.asInstanceOf[TransactionMessage]).getTransactionCapsule.getInstance)
        size += (msg.asInstanceOf[TransactionMessage]).getTransactionCapsule.getInstance.getSerializedSize
        if (transactions.size % maxTrxsCnt == 0 || size > maxTrxsSize) {
          peer.sendMessage(new TransactionsMessage(transactions))
          transactions = Lists.newArrayList()
          size = 0
        }
      }
    }
    if (block != null) {
      updateBlockWeBothHave(peer, block)
    }
    if (transactions.size > 0) {
      peer.sendMessage(new TransactionsMessage(transactions))
    }
  }

  private def banTraitorPeer(peer: PeerConnection, reason: Protocol.ReasonCode) {
    disconnectPeer(peer, reason) //TODO: ban it
  }

  private def onHandleChainInventoryMessage(peer: PeerConnection, msg: ChainInventoryMessage) {
    try
        if (peer.getSyncChainRequested != null) {
          val blockIdWeGet: util.Deque[BlockId] = new util.LinkedList[BlockId](msg.getBlockIds)
          if (blockIdWeGet.size > 0) peer.setNeedSyncFromPeer(true)
          //check if the peer is a traitor
          if (!blockIdWeGet.isEmpty) {
            var num: Long = blockIdWeGet.peek.getNum
            for (id:BlockId <- blockIdWeGet.asScala) {
              if (id.getNum != {
                num += 1;
                num - 1
              }) throw new TraitorPeerException("We get a not continuous block inv from " + peer)
            }
            if (peer.getSyncChainRequested.getKey.isEmpty) if (blockIdWeGet.peek.getNum != 1) throw new TraitorPeerException("We want a block inv starting from beginning from " + peer)
            else if (!peer.getSyncChainRequested.getKey.contains(blockIdWeGet.peek)) throw new TraitorPeerException(String.format("We get a unlinked block chain from " + peer + "\n Our head is " + peer.getSyncChainRequested.getKey.getLast.getString + "\n Peer give us is " + blockIdWeGet.peek.getString))
            if (del.getHeadBlockId.getNum > 0) {
              val maxRemainTime: Long = ChainConstant.CLOCK_MAX_DELAY + System.currentTimeMillis - del.getBlockTime(del.getSolidBlockId)
              val maxFutureNum: Long = maxRemainTime / BLOCK_PRODUCED_INTERVAL + del.getSolidBlockId.getNum
              if (blockIdWeGet.peekLast.getNum + msg.getRemainNum > maxFutureNum) throw new TraitorPeerException("Block num " + blockIdWeGet.peekLast.getNum + "+" + msg.getRemainNum + " is gt future max num " + maxFutureNum + " from " + peer + ", maybe the local clock is not right.")
            }
          }
          peer.setSyncChainRequested(null)
          if ((msg.getRemainNum == 0) && (blockIdWeGet.isEmpty || (blockIdWeGet.size == 1 && del.containBlock(blockIdWeGet.peek))) && peer.getSyncBlockToFetch.isEmpty && peer.getUnfetchSyncNum == 0) {
            peer.setNeedSyncFromPeer(false)
            unSyncNum = getUnSyncNum
            if (unSyncNum == 0) del.syncToCli(0)
            return
          }
          if (!blockIdWeGet.isEmpty && peer.getSyncBlockToFetch.isEmpty) {
            var isFound: Boolean = false
            val loop = new Breaks()
            loop.breakable {
              for (peerToCheck:PeerConnection <- getActivePeer.asScala) {
                if (!peerToCheck.equals(peer) && !peerToCheck.getSyncBlockToFetch.isEmpty && peerToCheck.getSyncBlockToFetch.peekFirst == blockIdWeGet.peekFirst) {
                  isFound = true
                  loop.break()
                }
              }
            }
            if (!isFound) while (!blockIdWeGet.isEmpty && del.containBlock(blockIdWeGet.peek)) {
                updateBlockWeBothHave(peer, blockIdWeGet.peek)
                blockIdWeGet.poll
              }
          } else if (!blockIdWeGet.isEmpty) {
            val loop = new Breaks()
            loop.breakable {
              while (!peer.getSyncBlockToFetch.isEmpty){
                if (!peer.getSyncBlockToFetch.peekLast.equals(blockIdWeGet.peekFirst)){
                  peer.getSyncBlockToFetch.pollLast
                } else {
                  loop.break()
                }
              }
            }
            if (peer.getSyncBlockToFetch.isEmpty && del.containBlock(blockIdWeGet.peek)){
              updateBlockWeBothHave(peer, blockIdWeGet.peek)
            }
            blockIdWeGet.poll
          }
          peer.setUnfetchSyncNum(msg.getRemainNum)
          peer.getSyncBlockToFetch.addAll(blockIdWeGet)
          freshBlockId synchronized {
            while (!peer.getSyncBlockToFetch.isEmpty && freshBlockId.contains(peer.getSyncBlockToFetch.peek)) {
              val blockId: BlockCapsule.BlockId = peer.getSyncBlockToFetch.pop
              updateBlockWeBothHave(peer, blockId)
              logger.info("Block "+blockId.getString+" from "+peer.getNode.getHost+" is processed")
            }
          }
          if ((msg.getRemainNum == 0) && peer.getSyncBlockToFetch.isEmpty) peer.setNeedSyncFromPeer(false)
          val newUnSyncNum: Long = getUnSyncNum
          if (unSyncNum != newUnSyncNum) {
            unSyncNum = newUnSyncNum
            del.syncToCli(unSyncNum)
          }
          if (msg.getRemainNum == 0) if (!peer.getSyncBlockToFetch.isEmpty) isFetchSyncActive = true
          else {
            //let peer know we are sync.
            syncNextBatchChainIds(peer)
          }
          else if (peer.getSyncBlockToFetch.size > NodeConstant.SYNC_FETCH_BATCH_NUM) isFetchSyncActive = true
          else syncNextBatchChainIds(peer)
        }
        else throw new TraitorPeerException("We don't send sync request to " + peer)

    catch {
      case e: TraitorPeerException => {
        logger.error(e.getMessage)
        banTraitorPeer(peer, ReasonCode.BAD_PROTOCOL)
      }
      case e: StoreException => {
        //we try update our both known block but this block is null
        //because when we try to switch to this block's fork then fail.
        //The reason only is we get a bad block which peer broadcast to us.
        logger.error(e.getMessage)
        banTraitorPeer(peer, ReasonCode.BAD_BLOCK)
      }
    }
  }

  private def startFetchItem() {
  }

  private def getUnSyncNum: Long = {
    if (getActivePeer.isEmpty) return 0
    return getActivePeer.asScala.map(peer => peer.getUnfetchSyncNum + peer.getSyncBlockToFetch.size()).max
  }

  private def startFetchSyncBlock() {
    val send: util.HashMap[PeerConnection, util.List[BlockCapsule.BlockId]] = new util.HashMap[PeerConnection, util.List[BlockCapsule.BlockId]]
    val request: util.HashSet[BlockCapsule.BlockId] = new util.HashSet[BlockCapsule.BlockId]
    getActivePeer.asScala.filter(peer => peer.isNeedSyncFromPeer && !peer.isBusy).foreach(peer => {
      if (!send.containsKey(peer)) send.put(peer, new util.LinkedList[BlockCapsule.BlockId])
      val loop = new Breaks()
      loop.breakable{
        for (blockId:BlockId <- peer.getSyncBlockToFetch.asScala) {
          if (!request.contains(blockId) && (!syncBlockIdWeRequested.getIfPresent(blockId).isInstanceOf[Long])) {
            send.get(peer).add(blockId)
            request.add(blockId)
            if (send.get(peer).size > MAX_BLOCKS_SYNC_FROM_ONE_PEER) {
              loop.break()
            }
          }
        }
      }
    })
    send.asScala.foreach(e => {
      val (peer, blockIds) = e
      blockIds.asScala.foreach(blockId => {
        syncBlockIdWeRequested.put(blockId, System.currentTimeMillis())
        peer.getSyncBlockRequested.put(blockId, System.currentTimeMillis())
      })
      val ids = new util.LinkedList[Sha256Hash]
      ids.addAll(blockIds)
      if (!ids.isEmpty){
        peer.sendMessage(new FetchInvDataMessage(ids, InventoryType.BLOCK))
      }
    })
    send.clear()
  }

  private def updateBlockWeBothHave(peer: PeerConnection, block: BlockCapsule) {
    logger.info("update peer "+peer.getNode.getHost+" block both we have " + block.getBlockId.getString)
    peer.setHeadBlockWeBothHave(block.getBlockId)
    peer.setHeadBlockTimeWeBothHave(block.getTimeStamp)
    peer.setLastBlockUpdateTime(System.currentTimeMillis)
  }

  @throws[StoreException]
  private def updateBlockWeBothHave(peer: PeerConnection, blockId: BlockCapsule.BlockId) {
    logger.info("update peer "+peer.getNode.getHost+" block both we have, " + blockId.getString)
    peer.setHeadBlockWeBothHave(blockId)
    val time: Long = (del.getData(blockId, MessageTypes.BLOCK).asInstanceOf[BlockMessage]).getBlockCapsule.getTimeStamp
    peer.setHeadBlockTimeWeBothHave(time)
    peer.setLastBlockUpdateTime(System.currentTimeMillis)
  }

  private def getActivePeer: util.Collection[PeerConnection] = {
    return pool.getActivePeers
  }

  private def startSyncWithPeer(peer: PeerConnection) {
    peer.setNeedSyncFromPeer(true)
    peer.getSyncBlockToFetch.clear()
    peer.setUnfetchSyncNum(0)
    updateBlockWeBothHave(peer, del.getGenesisBlock)
    peer.setBanned(false)
    syncNextBatchChainIds(peer)
  }

  private def syncNextBatchChainIds(peer: PeerConnection) {
    if (peer.getSyncChainRequested != null) {
      logger.info("Peer {} is in sync.", peer.getNode.getHost)
      return
    }
    try {
      val chainSummary: util.Deque[BlockCapsule.BlockId] = del.getBlockChainSummary(peer.getHeadBlockWeBothHave, peer.getSyncBlockToFetch)
      peer.setSyncChainRequested(new Pair[util.Deque[BlockCapsule.BlockId], Long](chainSummary, System.currentTimeMillis))
      peer.sendMessage(new SyncBlockChainMessage(chainSummary.asInstanceOf[util.LinkedList[BlockCapsule.BlockId]]))
    }
    catch {
      case e: TronException => {
        logger.error("Peer "+peer.getNode.getHost+" sync next batch chainIds failed, error: "+e.getMessage)
        disconnectPeer(peer, ReasonCode.FORKED)
      }
    }
  }

  def onConnectPeer(peer: PeerConnection) {
    if (peer.getHelloMessage.getHeadBlockId.getNum > del.getHeadBlockId.getNum) {
      peer.setTronState(TronState.SYNCING)
      startSyncWithPeer(peer)
    }
    else {
      peer.setTronState(TronState.SYNC_COMPLETED)
    }
  }

  def onDisconnectPeer(peer: PeerConnection) {
    if (!peer.getSyncBlockRequested.isEmpty) {
      peer.getSyncBlockRequested.asScala.keys.foreach(blockId => syncBlockIdWeRequested.invalidate(blockId))
      isFetchSyncActive = true
    }
    if (!peer.getAdvObjWeRequested.isEmpty) peer.getAdvObjWeRequested.asScala.keys.foreach(item => {
      if (getActivePeer.asScala.filter(peerConnection => !peerConnection.equals(peer)).filter(peerConnection => peerConnection.getInvToUs.contains(item.getHash())).headOption!=null){
        advObjToFetch.put(item.getHash, new PriorItem(item, fetchSequenceCounter.incrementAndGet))
      }
    })
  }

  def shutDown() {
    logExecutor.shutdown()
    trxsHandlePool.shutdown()
    disconnectInactiveExecutor.shutdown()
    cleanInventoryExecutor.shutdown()
    broadPool.shutdown()
    loopSyncBlockChain.shutdown
    loopFetchBlocks.shutdown
    loopAdvertiseInv.shutdown
    fetchSyncBlocksExecutor.shutdown()
    handleSyncBlockExecutor.shutdown()
  }

  private def disconnectPeer(peer: PeerConnection, reason: Protocol.ReasonCode) {
    peer.setSyncFlag(false)
    peer.disconnect(reason)
  }

}
