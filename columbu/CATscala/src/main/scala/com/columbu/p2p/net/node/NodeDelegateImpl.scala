package com.galileo.p2p.net.node

import com.galileo.core.config.Parameter.ChainConstant.BLOCK_PRODUCED_INTERVAL
import com.galileo.core.config.Parameter.ChainConstant.BLOCK_SIZE
import com.google.common.primitives.Longs
import java.util
import com.galileo.p2p.message.Message
import com.galileo.utils.Sha256Hash
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.config.Parameter.NodeConstant
import com.galileo.db.levelDB.store.Manager
import com.galileo.core.exception.AccountResourceInsufficientException
import com.galileo.core.exception.BadBlockException
import com.galileo.core.exception.BadItemException
import com.galileo.core.exception.BadNumberBlockException
import com.galileo.core.exception.BadTransactionException
import com.galileo.core.exception.ContractExeException
import com.galileo.core.exception.ContractSizeNotEqualToOneException
import com.galileo.core.exception.ContractValidateException
import com.galileo.core.exception.DupTransactionException
import com.galileo.core.exception.ItemNotFoundException
import com.galileo.core.exception.NonCommonBlockException
import com.galileo.core.exception.ReceiptCheckErrException
import com.galileo.core.exception.StoreException
import com.galileo.core.exception.TaposException
import com.galileo.core.exception.TooBigTransactionException
import com.galileo.core.exception.TooBigTransactionResultException
import com.galileo.core.exception.TransactionExpirationException
import com.galileo.core.exception.TronException
import com.galileo.core.exception.UnLinkedBlockException
import com.galileo.core.exception.VMIllegalException
import com.galileo.core.exception.ValidateScheduleException
import com.galileo.core.exception.ValidateSignatureException
import com.galileo.p2p.net.message.BlockMessage
import com.galileo.core.enumeration.MessageTypes
import com.galileo.core.enumeration.MessageTypes._
import com.galileo.p2p.net.message.TransactionMessage
import org.slf4j.LoggerFactory
import scala.collection.JavaConverters._

class NodeDelegateImpl extends NodeDelegate {

  private val logger = LoggerFactory.getLogger(getClass())

  private var dbManager: Manager = null

  def this(dbManager: Manager) {
    this()
    this.dbManager = dbManager
  }

  @throws[BadBlockException]
  @throws[UnLinkedBlockException]
  @throws[InterruptedException]
  @throws[NonCommonBlockException]
  override def handleBlock(block: BlockCapsule, syncMode: Boolean): util.List[Sha256Hash] = {
    if (block.getInstance.getSerializedSize > BLOCK_SIZE + 100) throw new BadBlockException("block size over limit")
    val gap = block.getTimeStamp - System.currentTimeMillis
    if (gap >= BLOCK_PRODUCED_INTERVAL) throw new BadBlockException("block time error")
    try{
      dbManager.preValidateTransactionSign(block)
      dbManager.pushBlock(block)
      if (!syncMode) {
        val trx: util.List[TransactionCapsule] = block.getTransactions
        // todo
        return trx.asScala.map(transactionCapsule=>transactionCapsule.getTransactionId).toList.asJava
      }
      else null
    }catch {
      case e: AccountResourceInsufficientException => {
        throw new BadBlockException("AccountResourceInsufficientException," + e.getMessage)
      }
      case e: ValidateScheduleException => {
        throw new BadBlockException("validate schedule exception," + e.getMessage)
      }
      case e: ValidateSignatureException => {
        throw new BadBlockException("validate signature exception," + e.getMessage)
      }
      case e: ContractValidateException => {
        throw new BadBlockException("ContractValidate exception," + e.getMessage)
      }
      case e: ContractExeException => {
        throw new BadBlockException("Contract Execute exception," + e.getMessage)
      }
      case e: TaposException => {
        throw new BadBlockException("tapos exception," + e.getMessage)
      }
      case e: DupTransactionException => {
        throw new BadBlockException("DupTransaction exception," + e.getMessage)
      }
      case e: TooBigTransactionException => {
        throw new BadBlockException("TooBigTransaction exception," + e.getMessage)
      }
      case e: TooBigTransactionResultException => {
        throw new BadBlockException("TooBigTransaction exception," + e.getMessage)
      }
      case e: TransactionExpirationException => {
        throw new BadBlockException("Expiration exception," + e.getMessage)
      }
      case e: BadNumberBlockException => {
        throw new BadBlockException("bad number exception," + e.getMessage)
      }
      case e: ReceiptCheckErrException => {
        throw new BadBlockException("TransactionTrace Exception," + e.getMessage)
      }
      case e: VMIllegalException => {
        throw new BadBlockException(e.getMessage)
      }
    }
  }

  @throws[BadTransactionException]
  def handleTransaction(trx: TransactionCapsule): Boolean = {
    if (dbManager.getDynamicPropertiesStore.supportVM) trx.resetResult()
    logger.debug("handle transaction")
    if (dbManager.getTransactionIdCache.getIfPresent(trx.getTransactionId) != null) {
      logger.warn("This transaction has been processed")
      return false
    }
    else dbManager.getTransactionIdCache.put(trx.getTransactionId, true)
    try
      dbManager.pushTransaction(trx)

    catch {
      case e: ContractSizeNotEqualToOneException => {
        logger.info("Contract validate failed" + e.getMessage)
        throw new BadTransactionException
      }
      case e: ContractValidateException => {
        logger.info("Contract validate failed" + e.getMessage)
        //throw new BadTransactionException();
        return false
      }
      case e: ContractExeException => {
        logger.info("Contract execute failed" + e.getMessage)
        //throw new BadTransactionException();
        return false
      }
      case e: ValidateSignatureException => {
        logger.info("ValidateSignatureException" + e.getMessage)
        throw new BadTransactionException
      }
      case e: AccountResourceInsufficientException => {
        logger.info("AccountResourceInsufficientException" + e.getMessage)
        return false
      }
      case e: DupTransactionException => {
        logger.info("Dup trans" + e.getMessage)
        return false
      }
      case e: TaposException => {
        logger.info("Tapos error" + e.getMessage)
        return false
      }
      case e: TooBigTransactionException => {
        logger.info("Too big transaction" + e.getMessage)
        return false
      }
      case e: TransactionExpirationException => {
        logger.info("Expiration transaction" + e.getMessage)
        return false
      }
      case e: ReceiptCheckErrException => {
        logger.info("ReceiptCheckErrException Exception" + e.getMessage)
        return false
      }
      case e: VMIllegalException => {
        logger.warn(e.getMessage)
        throw new BadTransactionException
      }
      case e: TooBigTransactionResultException => {
        logger.info("Too big transactionresult" + e.getMessage)
        return false
      }
    }
    true
  }

  @throws[StoreException]
  override def getLostBlockIds(blockChainSummary: util.List[BlockId]): util.List[BlockId] = {
    if (dbManager.getHeadBlockNum == 0) return new util.LinkedList[BlockId]
    var unForkedBlockId:BlockId = null
    if (blockChainSummary.isEmpty || (blockChainSummary.size == 1 && blockChainSummary.get(0) == dbManager.getGenesisBlockId)) unForkedBlockId = dbManager.getGenesisBlockId
    else if (blockChainSummary.size == 1 && blockChainSummary.get(0).getNum == 0) return new util.LinkedList[BlockId](util.Arrays.asList(dbManager.getGenesisBlockId))
    else {
      unForkedBlockId = blockChainSummary.asScala.filter(blockId => containBlockInMainChain(blockId)).head
      if (unForkedBlockId == null) return new util.LinkedList[BlockId]
    }
    val unForkedBlockIdNum = unForkedBlockId.getNum
    val len = Longs.min(dbManager.getHeadBlockNum, unForkedBlockIdNum + NodeConstant.SYNC_FETCH_BATCH_NUM)
    val blockIds = new util.LinkedList[BlockId]
    var i = unForkedBlockIdNum
    while (i <= len) {
      {
        val id = dbManager.getBlockIdByNum(i)
        blockIds.add(id)
      }
      {
        i += 1; i - 1
      }
    }
    blockIds
  }

  @throws[TronException]
  def getBlockChainSummary(beginBlockId: BlockId, blockIdsToFetch: util.Deque[BlockId]): util.Deque[BlockId] = {
    val retSummary = new util.LinkedList[BlockId]
    val blockIds = new util.ArrayList[BlockId](blockIdsToFetch)
    var highBlkNum = 0L
    var highNoForkBlkNum = 0L
    val syncBeginNumber = dbManager.getSyncBeginNumber
    var lowBlkNum = if (syncBeginNumber < 0) 0
    else syncBeginNumber
    var forkList = new util.LinkedList[BlockId]
    if (!beginBlockId.equals(getGenesisBlock.getBlockId))
      if (containBlockInMainChain(beginBlockId)) {
      highBlkNum = beginBlockId.getNum
      if (highBlkNum == 0) throw new TronException("This block don't equal my genesis block hash, but it is in my DB, the block id is :" + beginBlockId.getString)
      highNoForkBlkNum = highBlkNum
      if (beginBlockId.getNum < lowBlkNum) lowBlkNum = beginBlockId.getNum
    }
    else {
      forkList = dbManager.getBlockChainHashesOnFork(beginBlockId)
      if (forkList.isEmpty) throw new UnLinkedBlockException("We want to find forkList of this block: " + beginBlockId.getString + " ,but in KhasoDB we can not find it, It maybe a very old beginBlockId, we are sync once," + " we switch and pop it after that time. ")
      highNoForkBlkNum = forkList.peekLast.getNum
      forkList.pollLast
      java.util.Collections.reverse(forkList)
      highBlkNum = highNoForkBlkNum + forkList.size
      if (highNoForkBlkNum < lowBlkNum) throw new UnLinkedBlockException("It is a too old block that we take it as a forked block long long ago" + "\n lowBlkNum:" + lowBlkNum + "\n highNoForkBlkNum" + highNoForkBlkNum)
    }
    else {
      highBlkNum = dbManager.getHeadBlockNum
      highNoForkBlkNum = highBlkNum
    }
    if (!blockIds.isEmpty && highBlkNum != blockIds.get(0).getNum - 1) logger.error("Check ERROR: highBlkNum:" + highBlkNum + ",blockIdToSyncFirstNum is " + blockIds.get(0).getNum + ",blockIdToSyncEnd is " + blockIds.get(blockIds.size - 1).getNum)
    val realHighBlkNum = highBlkNum + blockIds.size
    do {
      if (lowBlkNum <= highNoForkBlkNum) retSummary.offer(dbManager.getBlockIdByNum(lowBlkNum))
      else if (lowBlkNum <= highBlkNum) retSummary.offer(forkList.get((lowBlkNum - highNoForkBlkNum - 1).toInt))
      else retSummary.offer(blockIds.get((lowBlkNum - highBlkNum - 1).toInt))
      lowBlkNum += (realHighBlkNum - lowBlkNum + 2) / 2
    } while (lowBlkNum <= realHighBlkNum)
    retSummary
  }

  @throws[StoreException]
  def getData(hash: Sha256Hash, `type`: MessageTypes): Message = `type` match {
    case BLOCK =>
      new BlockMessage(dbManager.getBlockById(hash))
    case TRX =>
      val tx = dbManager.getTransactionStore.get(hash.getBytes)
      if (tx != null) return new TransactionMessage(tx.getData)
      throw new ItemNotFoundException("transaction is not found")
    case _ =>
      throw new BadItemException("message type not block or trx.")
  }

  def syncToCli(unSyncNum: Long) {
    logger.info("There are " + unSyncNum + " blocks we need to sync.")
    if (unSyncNum == 0) {
      logger.info("Sync Block Completed !!!")
    }
    dbManager.setSyncMode(unSyncNum == 0)
  }

  def getBlockTime(id: BlockCapsule.BlockId): Long = {
    try {
      return dbManager.getBlockById(id).getTimeStamp
    }
    catch {
      case e: BadItemException => {
        return dbManager.getGenesisBlock.getTimeStamp
      }
      case e: ItemNotFoundException => {
        return dbManager.getGenesisBlock.getTimeStamp
      }
    }
  }

  def getHeadBlockId: BlockCapsule.BlockId = {
    return dbManager.getHeadBlockId
  }

  def getSolidBlockId: BlockCapsule.BlockId = {
    return dbManager.getSolidBlockId
  }

  def getHeadBlockTimeStamp: Long = {
    return dbManager.getHeadBlockTimeStamp
  }

  def containBlock(id: BlockCapsule.BlockId): Boolean = {
    return dbManager.containBlock(id)
  }

  def containBlockInMainChain(id: BlockCapsule.BlockId): Boolean = {
    return dbManager.containBlockInMainChain(id)
  }

  def contain(hash: Sha256Hash, `type`: MessageTypes): Boolean = {
    if (`type` == MessageTypes.BLOCK) {
      return dbManager.containBlock(hash)
    }
    else if (`type` == MessageTypes.TRX) {
      return dbManager.getTransactionStore.has(hash.getBytes)
    }
    return false
  }

  def getGenesisBlock: BlockCapsule = {
    return dbManager.getGenesisBlock
  }

  def canChainRevoke(num: Long): Boolean = {
    return num >= dbManager.getSyncBeginNumber
  }

}
