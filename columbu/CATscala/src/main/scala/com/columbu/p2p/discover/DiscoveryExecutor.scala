package com.galileo.p2p.discover

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import com.galileo.p2p.discover.node.NodeManager
import com.galileo.p2p.discover.table.KademliaOptions

class DiscoveryExecutor {

  private val discoverer = Executors.newSingleThreadScheduledExecutor
  private val refresher = Executors.newSingleThreadScheduledExecutor
  private var nodeManager: NodeManager = null

  def this(nodeManager: NodeManager) {
    this()
    this.nodeManager = nodeManager
  }

  def start() {
    discoverer.scheduleWithFixedDelay(new DiscoverTask(nodeManager), 1, KademliaOptions.DISCOVER_CYCLE, TimeUnit.SECONDS)
    refresher.scheduleWithFixedDelay(new RefreshTask(nodeManager), 1, KademliaOptions.BUCKET_REFRESH, TimeUnit.MILLISECONDS)
  }

  def close() {
    discoverer.shutdownNow
    refresher.shutdownNow
  }

}
