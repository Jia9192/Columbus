package com.galileo.p2p.net.message

import com.galileo.utils.Sha256Hash
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.core.enumeration.MessageTypes

class BlockMessage extends TronMessage{

  private var block:BlockCapsule = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = MessageTypes.BLOCK.asByte
    this.data = data
    this.block = new BlockCapsule(data)
  }

  def this(block: BlockCapsule) {
    this()
    data = block.getData
    this.`type` = MessageTypes.BLOCK.asByte
    this.block = block
  }

  def getBlockId: BlockId = getBlockCapsule.getBlockId

  def getBlockCapsule: BlockCapsule = block

  def getAnswerMessage = null

  override def getMessageId: Sha256Hash = getBlockCapsule.getBlockId

  override def equals(obj: Any): Boolean = super.equals(obj)

  override def hashCode: Int = super.hashCode

  override def toString: String = new StringBuilder().append(super.toString).append(block.getBlockId.getString).append(", trx size: ").append(block.getTransactions.size).append("\n").toString

}
