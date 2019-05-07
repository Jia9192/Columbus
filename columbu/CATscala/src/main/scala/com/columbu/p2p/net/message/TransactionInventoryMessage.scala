package com.galileo.p2p.net.message

import java.util
import com.galileo.utils.Sha256Hash
import com.galileo.protos.protos.Protocol.Inventory
import com.galileo.protos.protos.Protocol.Inventory.InventoryType
import com.galileo.core.enumeration.MessageTypes

class TransactionInventoryMessage extends InventoryMessage{

  def this(packed: Array[Byte]) {
    this()
    this.`type` = MessageTypes.INVENTORY.asByte
    this.data = packed
    this.inv = Inventory.parseFrom(packed)
  }

  def this(inv: Inventory) {
    this()
    this.inv = inv
    this.`type` = MessageTypes.INVENTORY.asByte
    this.data = inv.toByteArray
  }

  def this(hashList: util.List[Sha256Hash]) {
    this()
    val invBuilder: Inventory.Builder = Inventory.newBuilder()
    import scala.collection.JavaConversions._
    for (hash <- hashList) {
      invBuilder.addIds(hash.getByteString)
    }
    invBuilder.setType(InventoryType.TRX)
    this.inv = invBuilder.build
    this.`type` = MessageTypes.INVENTORY.asByte
    this.data = inv.toByteArray
  }

}
