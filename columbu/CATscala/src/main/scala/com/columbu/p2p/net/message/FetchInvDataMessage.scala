package com.galileo.p2p.net.message

import java.util
import com.galileo.utils.Sha256Hash
import com.galileo.protos.protos.Protocol.Inventory
import com.galileo.protos.protos.Protocol.Inventory.InventoryType
import com.galileo.core.enumeration.MessageTypes

class FetchInvDataMessage extends InventoryMessage{

  def this(packed: Array[Byte]) {
    this()
    this.data = data
    this.inv = Inventory.parseFrom(data)
    this.`type` = MessageTypes.FETCH_INV_DATA.asByte
  }

  def this(inv: Inventory) {
    this()
    this.inv = inv
    this.`type` = MessageTypes.FETCH_INV_DATA.asByte
  }

  def this(hashList: util.List[Sha256Hash], `type`: InventoryType) {
    this()
    this.init(hashList,`type`)
    this.`type` = MessageTypes.FETCH_INV_DATA.asByte
  }

}
