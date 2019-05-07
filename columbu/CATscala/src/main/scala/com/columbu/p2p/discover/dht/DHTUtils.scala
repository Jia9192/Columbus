package com.galileo.p2p.discover.dht

import java.util
import scala.collection.JavaConversions._

class DHTUtils {

  def printAllLeafs(root: Bucket) {
    val saveLeaf = new SaveLeaf
    root.traverseTree(saveLeaf)

    for (bucket <- saveLeaf.getLeafs) {
      System.out.println(bucket)
    }
  }

  def getAllLeafs(root: Bucket): util.List[Bucket] = {
    val saveLeaf = new SaveLeaf
    root.traverseTree(saveLeaf)
    saveLeaf.getLeafs
  }

}
