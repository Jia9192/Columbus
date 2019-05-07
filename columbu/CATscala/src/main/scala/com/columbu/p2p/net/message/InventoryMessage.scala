package com.galileo.p2p.net.message

import java.util
import com.galileo.utils.Sha256Hash
import com.galileo.protos.protos.Protocol
import com.galileo.protos.protos.Protocol.Inventory
import com.galileo.protos.protos.Protocol.Inventory.InventoryType
import com.galileo.core.enumeration.MessageTypes
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

class InventoryMessage extends TronMessage{

  protected var inv:Inventory = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = MessageTypes.INVENTORY.asByte
    this.data = data
    this.inv = Inventory.parseFrom(data)
  }

  def this(inv: Protocol.Inventory) {
    this()
    this.inv = inv
    this.`type` = MessageTypes.INVENTORY.asByte
    this.data = inv.toByteArray
  }

  def this(hashList: util.List[Sha256Hash], `type`: InventoryType){
    this()
    this.init(hashList,`type`)
    this.`type` = MessageTypes.INVENTORY.asByte

  }

  def init(hashList: util.List[Sha256Hash], `type`: InventoryType){
    val invBuilder = Inventory.newBuilder
    for (hash <- hashList) {
      invBuilder.addIds(hash.getByteString)
    }
    invBuilder.setType(`type`)
    this.inv = invBuilder.build
    this.data = inv.toByteArray
  }

  def getAnswerMessage = null

  def getInventory: Inventory = inv

  def getInvMessageType: MessageTypes = if (getInventoryType == InventoryType.BLOCK) MessageTypes.BLOCK
  else MessageTypes.TRX

  def getInventoryType: InventoryType = inv.getType

  override def toString: String = {
    val hashes = new util.LinkedList[Sha256Hash](getHashList)
    val builder = new StringBuilder
    builder.append(super.toString).append("invType: ").append(getInvMessageType).append(", size: ").append(hashes.size).append(", First hash: ").append(hashes.peekFirst)
    if (hashes.size > 1) builder.append(", End hash: ").append(hashes.peekLast)
    builder.toString
  }

  def getHashList: util.List[Sha256Hash] = {
    getInventory.getIdsList.asScala.map(hash => Sha256Hash.wrap(hash.toByteArray())).toList.asJava
  }
}
