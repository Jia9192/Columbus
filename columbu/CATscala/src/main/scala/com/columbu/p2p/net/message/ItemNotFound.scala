package com.galileo.p2p.net.message

import com.galileo.protos.protos.Protocol.Items
import com.galileo.core.enumeration.MessageTypes

class ItemNotFound extends TronMessage {

  private var notFound:Items = null

  {
    val itemsBuilder = Items.newBuilder
    itemsBuilder.setType(Items.ItemType.ERR)
    this.notFound = itemsBuilder.build
    this.`type` = MessageTypes.ITEM_NOT_FOUND.asByte
    this.data = notFound.toByteArray
  }

  override def toString = "item not found"

  def getAnswerMessage = null

}
