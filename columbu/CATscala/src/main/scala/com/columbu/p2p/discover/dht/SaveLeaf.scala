package com.galileo.p2p.discover.dht

import java.util

class SaveLeaf extends DoOnTree {

  private var leafs: util.List[Bucket] = new util.ArrayList[Bucket]

  def call(bucket: Bucket) {
    if (bucket.peers != null) leafs.add(bucket)
  }

  def getLeafs: util.List[Bucket] = leafs

  def setLeafs(leafs: util.List[Bucket]) {
    this.leafs = leafs
  }

}