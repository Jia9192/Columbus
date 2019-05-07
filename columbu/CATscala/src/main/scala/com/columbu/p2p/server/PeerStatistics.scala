package com.galileo.p2p.server

class PeerStatistics {

  private var avgLatency: Double = 0
  private var pingCount: Long = 0

  def pong(pingStamp: Long) {
    val latency: Long = System.currentTimeMillis - pingStamp
    avgLatency = ((avgLatency * pingCount) + latency) / {pingCount += 1; pingCount }
  }

  def getAvgLatency: Double = avgLatency
}
