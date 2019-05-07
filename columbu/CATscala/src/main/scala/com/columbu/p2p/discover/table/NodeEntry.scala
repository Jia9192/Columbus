package com.galileo.p2p.discover.table

import com.galileo.p2p.discover.node.Node

import scala.util.control.Breaks

class NodeEntry {

  private var ownerId: Array[Byte] = null
  private var node: Node = null
  private var entryId:  String = null
  private var distance: Int = 0
  private var modified: Long = 0L

  def this(n: Node) {
    this()
    this.node = n
    this.ownerId = n.getId
    entryId = n.getHost
    distance = NodeEntry.distance(ownerId, n.getId)
    touch()
  }

  def this(ownerId: Array[Byte], n: Node) {
    this()
    this.node = n
    this.ownerId = ownerId
    entryId = n.getHost
    distance = NodeEntry.distance(ownerId, n.getId)
    touch()
  }

  def touch() {
    modified = System.currentTimeMillis
  }

  def getDistance: Int = distance

  def getId: String = entryId

  def getNode: Node = node

  def getModified: Long = modified

  override def equals(o: Any): Boolean = {
    var ret = false
    if (o != null && (this.getClass eq o.getClass)) {
      val e = o.asInstanceOf[NodeEntry]
      ret = this.getId == e.getId
    }
    ret
  }

  override def hashCode: Int = this.node.hashCode

}

object NodeEntry {

  def distance(ownerId: Array[Byte], targetId: Array[Byte]): Int = {
    val h1 = targetId
    val h2 = ownerId
    val hash = new Array[Byte](Math.min(h1.length, h2.length))
    var i = 0
    while (i < hash.length) {
      {
        hash(i) = (h1(i).toInt ^ h2(i).toInt).toByte
      }
      {
        i += 1; i - 1
      }
    }
    var d = KademliaOptions.BINS
    val loop = new Breaks()
    loop.breakable {
      for (b <- hash) {
        if (b == 0){
          d -= 8
        } else {
          var count = 0
          var i = 7
          loop.breakable {
            while (i >= 0) {
              {
                val a = (b & (1 << i)) == 0
                if (a) {
                  count += 1
                } else {
                  loop.break()
                }
              }
              {
                i -= 1;
                i + 1
              }
            }
          }
          d -= count
          loop.break()
        }
      }
    }
    return d
  }

}
