package com.galileo.p2p.net.message

import java.util
import com.galileo.core.capsule.BlockCapsule.BlockId
import com.galileo.protos.protos.Protocol
import com.galileo.protos.protos.Protocol.ChainInventory
import org.slf4j.LoggerFactory
import com.galileo.core.enumeration.MessageTypes
import scala.collection.JavaConverters._

class ChainInventoryMessage extends TronMessage{

  private val logger = LoggerFactory.getLogger(getClass())

  protected var chainInventory: ChainInventory = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = MessageTypes.BLOCK_CHAIN_INVENTORY.asByte
    this.data = data
    chainInventory = Protocol.ChainInventory.parseFrom(data)
  }

  def this(blockIds: util.List[BlockId], remainNum: Long){
    this()
    val invBuilder = ChainInventory.newBuilder
    blockIds.asScala.foreach(blockId => {
      val b = ChainInventory.BlockId.newBuilder
      b.setHash(blockId.getByteString)
      b.setNumber(blockId.getNum)
      invBuilder.addIds(b)
    })
    invBuilder.setRemainNum(remainNum)
    chainInventory = invBuilder.build
    this.`type` = MessageTypes.BLOCK_CHAIN_INVENTORY.asByte
    this.data = chainInventory.toByteArray
  }

  def getAnswerMessage = null

  private def getChainInventory = chainInventory

  def getBlockIds: util.List[BlockId] = {
    try
      return getChainInventory.getIdsList.asScala.map(blockId=>new BlockId(blockId.getHash(), blockId.getNumber())).toList.asJava
    catch {
      case e: Exception => {
        logger.info("breakPoint")
      }
    }
    null
  }

  def getRemainNum: Long = getChainInventory.getRemainNum

  override def toString: String = {
    val blockIdWeGet = new util.LinkedList[BlockId](getBlockIds)
    val sb = new StringBuilder(super.toString)
    val size = blockIdWeGet.size
    sb.append("size: ").append(size)
    if (size >= 1) {
      sb.append(", first blockId: ").append(blockIdWeGet.peek.getString)
      if (size > 1) sb.append(", end blockId: ").append(blockIdWeGet.peekLast.getString)
    }
    sb.toString
  }

}
