package com.galileo.p2p.net.node

import com.galileo.utils.Sha256Hash
import com.galileo.protos.protos.Protocol.Inventory.InventoryType

import scala.beans.BeanProperty

class Item {

  @BeanProperty
  var hash: Sha256Hash = null

  private var inventoryType: InventoryType = null

  def getType():InventoryType = this.inventoryType

  def setType(inventoryType: InventoryType){
    this.inventoryType = inventoryType
  }

  def this(hash: Sha256Hash, inventoryType: InventoryType) {
    this()
    this.hash = hash
    this.inventoryType = inventoryType
  }

  override def equals(o: Any): Boolean = {
    if (this == o) return true
    if (o == null || (this.getClass != o.getClass)) return false
    val item: Item = o.asInstanceOf[Item]
    hash == item.getHash && inventoryType == item.getType
  }

  override def hashCode: Int = hash.hashCode
}
