package com.galileo.p2p.discover.dht

import java.util
import scala.collection.JavaConversions._

class Bucket {

  var MAX_KADEMLIA_K = 5
  private var leftBucket: Bucket = null
  private var rightBucket: Bucket = null
  private var name: String = null
  var peers = new util.ArrayList[Peer]

  def this(name: String) {
    this()
    this.name = name
  }

  def add(peer: Peer) {
    if (peer == null) throw new Error("Not a leaf")
    if (peers == null) {
      if (peer.nextBit(name) == 1){
        leftBucket.add(peer)
      } else {
        rightBucket.add(peer)
      }
      return
    }
    peers.add(peer)
    if (peers.size > MAX_KADEMLIA_K) splitBucket()
  }

  def splitBucket() {
    leftBucket = new Bucket(name + "1")
    rightBucket = new Bucket(name + "0")
    for (id <- peers) {
      if (id.nextBit(name) == 1){
        leftBucket.add(id)
      } else {
        rightBucket.add(id)
      }
    }
    this.peers = null
  }

  def left: Bucket = leftBucket

  def right: Bucket = rightBucket

  override def toString: String = {
    val sb = new StringBuilder
    sb.append(name).append("\n")
    if (peers == null) return sb.toString
    for (id <- peers) {
      sb.append(id.toBinaryString).append("\n")
    }
    sb.toString
  }

  def traverseTree(doOnTree: DoOnTree) {
    if (leftBucket != null) leftBucket.traverseTree(doOnTree)
    if (rightBucket != null) rightBucket.traverseTree(doOnTree)
    doOnTree.call(this)
  }

  def getName: String = {
    return name
  }

  def getPeers: util.List[Peer] = {
    return peers
  }

}
