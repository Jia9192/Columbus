package com.galileo.p2p.net.node

import com.galileo.core.capsule.BlockCapsule
import com.galileo.p2p.net.message.FetchInvDataMessage
import com.galileo.p2p.net.message.InventoryMessage
import com.galileo.p2p.net.peer.PeerConnection
import com.galileo.protos.protos.Protocol
import com.galileo.protos.protos.Protocol.Inventory
import com.galileo.utils.Sha256Hash
import java.util
import java.util.Comparator

import scala.collection.JavaConverters._

class InvToSend {

  private val send: util.HashMap[PeerConnection, util.HashMap[Inventory.InventoryType, util.LinkedList[Sha256Hash]]] = new util.HashMap[PeerConnection, util.HashMap[Inventory.InventoryType, util.LinkedList[Sha256Hash]]]

  def clear() {
    this.send.clear()
  }

  def add(id: util.Map.Entry[Sha256Hash, Inventory.InventoryType], peer: PeerConnection) {
    if (send.containsKey(peer) && !send.get(peer).containsKey(id.getValue)) send.get(peer).put(id.getValue, new util.LinkedList[Sha256Hash])
    else if (!send.containsKey(peer)) {
      send.put(peer, new util.HashMap[Inventory.InventoryType, util.LinkedList[Sha256Hash]])
      send.get(peer).put(id.getValue, new util.LinkedList[Sha256Hash])
    }
    send.get(peer).get(id.getValue).offer(id.getKey)
  }

  def add(id: PriorItem, peer: PeerConnection) {
    if (send.containsKey(peer) && !send.get(peer).containsKey(id.getType)) send.get(peer).put(id.getType, new util.LinkedList[Sha256Hash])
    else if (!send.containsKey(peer)) {
      send.put(peer, new util.HashMap[Inventory.InventoryType, util.LinkedList[Sha256Hash]])
      send.get(peer).put(id.getType, new util.LinkedList[Sha256Hash])
    }
    send.get(peer).get(id.getType).offer(id.getHash)
  }

  def getSize(peer: PeerConnection): Int = {
    if (send.containsKey(peer)){
      return send.get(peer).values.asScala.map(list=>list.size()).sum
    }
    return 0
  }

  def sendInv() {
    val sendMap = send.asScala
    sendMap.keys.foreach(peer=>{
      val typeMap = sendMap.get(peer).asInstanceOf[util.HashMap[Inventory.InventoryType, util.LinkedList[Sha256Hash]]].asScala
      typeMap.keys.foreach(key=>{
        var value = typeMap.get(key).asInstanceOf[util.LinkedList[Sha256Hash]]
        if (key.equals(Protocol.Inventory.InventoryType.BLOCK)) {
          value.sort(Comparator.comparingLong(v=>new BlockCapsule.BlockId(v).getNum()))
        }
        peer.sendMessage(new InventoryMessage(value, key))
      })
    })
  }

  def sendFetch() {
    val sendMap = send.asScala
    sendMap.keys.foreach(peer=>{
        val typeMap = sendMap.get(peer).asInstanceOf[util.HashMap[Inventory.InventoryType, util.LinkedList[Sha256Hash]]].asScala
        typeMap.keys.foreach(key=>{
          var value = typeMap.get(key).asInstanceOf[util.LinkedList[Sha256Hash]]
          if (key.equals(Protocol.Inventory.InventoryType.BLOCK)) {
            value.sort(Comparator.comparingLong(v=>new BlockCapsule.BlockId(v).getNum()))
          }
          peer.sendMessage(new FetchInvDataMessage(value, key))
        })
      })
  }

}
