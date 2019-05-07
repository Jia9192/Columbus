package com.galileo.p2p.net.message

import com.galileo.protos.protos.Protocol
import com.galileo.core.enumeration.MessageTypes

class FetchBlockHeadersMessage extends InventoryMessage{

  def this(data: Array[Byte]) {
    this()
    this.data = data
    this.inv = Protocol.Inventory.parseFrom(data)
    this.`type` = MessageTypes.FETCH_BLOCK_HEADERS.asByte
  }

  def this(inv: Protocol.Inventory) {
    this()
    this.inv = inv
    this.data = inv.toByteArray
    this.`type` = MessageTypes.FETCH_BLOCK_HEADERS.asByte
  }

}
