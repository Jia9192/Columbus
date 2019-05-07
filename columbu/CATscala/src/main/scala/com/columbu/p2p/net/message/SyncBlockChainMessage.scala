package com.galileo.p2p.net.message

import java.util
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.protos.protos.Protocol.BlockInventory
import com.galileo.protos.protos.Protocol.BlockInventory.Type
import scala.collection.mutable.StringBuilder
import com.galileo.core.enumeration.MessageTypes

class SyncBlockChainMessage extends BlockInventoryMessage {

  def this(data: Array[Byte]) {
    this()
    this.data = data
    this.blockInventory = BlockInventory.parseFrom(data)
    this.`type` = MessageTypes.SYNC_BLOCK_CHAIN.asByte
  }

  def this(blockIds: util.List[BlockId]) {
    this()
    this.init(blockIds,Type.SYNC)
    this.`type` = MessageTypes.SYNC_BLOCK_CHAIN.asByte
  }

  override def toString: String = {
    val blockIdList = super.getBlockIds
    val sb:StringBuilder = new StringBuilder
    val size = blockIdList.size
    sb.append(super.toString).append("size: ").append(size)
    if (size >= 1) {
      sb.append(", start block: " + blockIdList.get(0).getString)
      if (size > 1) sb.append(", end block " + blockIdList.get(blockIdList.size - 1).getString)
    }
    sb.toString
  }

  override def getAnswerMessage: Class[_] = classOf[ChainInventoryMessage]
}
