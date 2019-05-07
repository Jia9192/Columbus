package com.galileo.p2p.discover

import java.util
import com.galileo.p2p.discover.node.Node
import com.galileo.p2p.discover.node.NodeManager

class RefreshTask extends DiscoverTask{

  def this(nodeManager: NodeManager) {
    this()
    this.nodeManager = nodeManager
    this.nodeId = nodeManager.getPublicHomeNode.getId
  }

  override def run() {
    discover(RefreshTask.getNodeId, 0, new util.ArrayList[Node])
  }

}

object RefreshTask{

  def getNodeId: Array[Byte] = {
    val gen = new util.Random
    val id = new Array[Byte](64)
    gen.nextBytes(id)
    return id
  }

}
