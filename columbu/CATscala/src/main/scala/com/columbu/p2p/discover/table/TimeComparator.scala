package com.galileo.p2p.discover.table

import java.util.Comparator

class TimeComparator extends Comparator[NodeEntry]{

  override def compare(e1: NodeEntry, e2: NodeEntry): Int = {
    val t1 = e1.getModified
    val t2 = e2.getModified
    if (t1 < t2) {
      return 1;
    } else if (t1 > t2) {
      return -1;
    } else {
      return 0;
    }
  }

}
