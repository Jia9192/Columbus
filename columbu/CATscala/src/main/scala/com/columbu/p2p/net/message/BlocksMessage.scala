package com.galileo.p2p.net.message

import java.util
import org.apache.commons.collections4.CollectionUtils
import com.galileo.protos.protos.Protocol.Block
import com.galileo.protos.protos.Protocol.Items
import com.galileo.protos.protos.Protocol.Items._
import com.galileo.core.enumeration.MessageTypes

class BlocksMessage extends TronMessage {

  private var blocks: util.List[Block] = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = MessageTypes.BLOCKS.asByte
    this.data = data
    val items = Items.parseFrom(data)
    if (items.getType == ItemType.BLOCK){
      blocks = items.getBlocksList
    }
  }

  def getBlocks: util.List[Block] = blocks

  override def toString: String ={
    super.toString + "size: " + (if (CollectionUtils.isNotEmpty(blocks)) blocks.size else 0)
  }

  override def getAnswerMessage = null

}
