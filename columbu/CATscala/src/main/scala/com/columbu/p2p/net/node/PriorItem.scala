package com.galileo.p2p.net.node

import com.galileo.protos.protos.Protocol.Inventory.InventoryType
import com.galileo.utils.Sha256Hash
import com.galileo.utils.Time
import scala.beans.BeanProperty

class PriorItem {

  @BeanProperty
  var count = 0L
  @BeanProperty
  var item: Item = null
  @BeanProperty
  var time = 0L

  def getHash: Sha256Hash = item.getHash

  def getType: InventoryType = item.getType

  def this(item: Item, count: Long) {
    this()
    this.item = item
    this.count = count
    this.time = Time.getCurrentMillis
  }

  def refreshTime() {
    this.time = Time.getCurrentMillis
  }

  def compareTo(o: PriorItem): Boolean = {
    if (!this.item.getType.equals(o.getItem.getType)){
      return this.item.getType == InventoryType.BLOCK
    }
    return this.count < o.getCount
  }

}
