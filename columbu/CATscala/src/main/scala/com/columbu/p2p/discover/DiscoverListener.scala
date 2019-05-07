package com.galileo.p2p.discover

import com.galileo.p2p.discover.node.NodeHandler

trait DiscoverListener {

  def nodeAppeared(handler: NodeHandler)

  def nodeDisappeared(handler: NodeHandler)

}