package com.galileo.p2p.net.node

import java.util
import com.galileo.p2p.message.Message
import com.galileo.utils.Sha256Hash
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.exception.BadBlockException
import com.galileo.core.exception.BadTransactionException
import com.galileo.core.exception.NonCommonBlockException
import com.galileo.core.exception.StoreException
import com.galileo.core.exception.TronException
import com.galileo.core.exception.UnLinkedBlockException
import com.galileo.core.enumeration.MessageTypes

trait NodeDelegate {

  @throws[BadBlockException]
  @throws[UnLinkedBlockException]
  @throws[InterruptedException]
  @throws[NonCommonBlockException]
  def handleBlock(block: BlockCapsule, syncMode: Boolean): util.List[Sha256Hash]

  @throws[BadTransactionException]
  def handleTransaction(trx: TransactionCapsule): Boolean

  @throws[StoreException]
  def getLostBlockIds(blockChainSummary: util.List[BlockCapsule.BlockId]): util.List[BlockCapsule.BlockId]

  @throws[TronException]
  def getBlockChainSummary(beginBLockId: BlockCapsule.BlockId, blockIds: util.Deque[BlockCapsule.BlockId]): util.Deque[BlockCapsule.BlockId]

  @throws[StoreException]
  def getData(msgId: Sha256Hash, messageType: MessageTypes): Message

  def syncToCli(unSyncNum: Long)

  def getBlockTime(id: BlockCapsule.BlockId): Long

  def getHeadBlockId: BlockCapsule.BlockId

  def getSolidBlockId: BlockCapsule.BlockId

  def contain(hash: Sha256Hash, messageType: MessageTypes): Boolean

  def containBlock(id: BlockCapsule.BlockId): Boolean

  def getHeadBlockTimeStamp: Long

  def containBlockInMainChain(id: BlockCapsule.BlockId): Boolean

  def getGenesisBlock: BlockCapsule

  def canChainRevoke(num: Long): Boolean
}
