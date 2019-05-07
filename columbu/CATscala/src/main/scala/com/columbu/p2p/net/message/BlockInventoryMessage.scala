package com.galileo.p2p.net.message

import java.util
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.protos.protos.Protocol.BlockInventory
import com.galileo.core.enumeration.MessageTypes
import scala.collection.JavaConverters._

class BlockInventoryMessage extends TronMessage{

  protected var blockInventory: BlockInventory = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = MessageTypes.BLOCK_INVENTORY.asByte
    this.data = data
    this.blockInventory = BlockInventory.parseFrom(data)
  }

  override def getAnswerMessage:Class[_] = null

  private def getBlockInventory = blockInventory

  def this(blockIds: util.List[BlockId], `type`: BlockInventory.Type){
    this()
    this.init(blockIds,`type`)
    this.`type` = MessageTypes.BLOCK_INVENTORY.asByte
  }

  def init(blockIds: util.List[BlockId], `type`: BlockInventory.Type){
    val invBuilder = BlockInventory.newBuilder
    blockIds.asScala.foreach(blockId => {
      val b = BlockInventory.BlockId.newBuilder
      b.setHash(blockId.getByteString)
      b.setNumber(blockId.getNum)
      invBuilder.addIds(b)
    })
    invBuilder.setType(`type`)
    this.blockInventory = invBuilder.build
    this.data = blockInventory.toByteArray
  }

  def getBlockIds: util.List[BlockId] ={
    getBlockInventory.getIdsList.asScala.map(blockId => new BlockId(blockId.getHash(), blockId.getNumber())).toList.asJava
  }

}
