package com.galileo.p2p.discover.table

import java.util.Comparator

class DistanceComparator extends Comparator[NodeEntry]{

  private var targetId: Array[Byte] = null

  def this(targetId: Array[Byte]) {
    this()
    this.targetId = targetId
  }

  def compare(e1: NodeEntry, e2: NodeEntry): Int = {
    val d1 = NodeEntry.distance(targetId, e1.getNode.getId)
    val d2 = NodeEntry.distance(targetId, e2.getNode.getId)
    if (d1 > d2) {
      return 1
    } else if (d1 < d2) {
      return -1
    } else {
      return 0
    }
  }
}
