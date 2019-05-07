package com.galileo.p2p.discover.table

import java.util
import scala.util.control.Breaks
import scala.collection.JavaConversions._

class NodeBucket {

  private var depth:  Int = 0
  private var nodes: util.List[NodeEntry] = new util.ArrayList[NodeEntry]

  def this(depth: Int) {
    this()
    this.depth = depth
  }

  def getDepth: Int = depth

  def addNode(e: NodeEntry): NodeEntry = {
    if (!nodes.contains(e)){
      if (nodes.size >= KademliaOptions.BUCKET_SIZE){
        return getLastSeen
      } else {
        nodes.add(e)
      }
    }
    return null
  }

  private def getLastSeen: NodeEntry = {
    val sorted = nodes
    util.Collections.sort(sorted, new TimeComparator)
    return sorted.get(0)
  }

  def dropNode(entry: NodeEntry) {
    val loop = new Breaks()
    loop.breakable {
      for (e <- nodes) {
        if (e.getId == entry.getId) {
          nodes.remove(e)
          loop.break()
        }
      }
    }
  }

  def getNodesCount: Int = nodes.size

  def getNodes: util.List[NodeEntry] = nodes

}
