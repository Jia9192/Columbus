package com.galileo.p2p.discover.node.statistics

import java.util.concurrent.atomic.AtomicLong

import com.galileo.p2p.discover.node.Node
import com.galileo.core.config.args.Args
import com.galileo.protos.protos.Protocol.ReasonCode

import scala.beans.BeanProperty

class NodeStatistics {

  private val MIN_DATA_LENGTH: Long = Args.getInstance.getReceiveTcpMinDataLength
  var isPredefined = false
  private var persistedReputation: Int = 0
  @BeanProperty
  var disconnectTimes: Int = 0
  @BeanProperty
  var tronLastRemoteDisconnectReason: ReasonCode = null
  @BeanProperty
  var tronLastLocalDisconnectReason: ReasonCode = null
  private var lastDisconnectedTime: Long = 0
  private var firstDisconnectedTime: Long = 0
  val messageStatistics = new MessageStatistics
  val p2pHandShake = new MessageCount
  val tcpFlow = new MessageCount
  var discoverMessageLatency:SimpleStatter = null
  val lastPongReplyTime: AtomicLong = new AtomicLong(0L)
  private var reputation: Reputation = null

  def this(node: Node) {
    this()
    discoverMessageLatency = new SimpleStatter(node.getIdString)
    reputation = new Reputation(this)
  }

  def getReputation: Int = {
    var score = 0
    if (!isReputationPenalized) score += persistedReputation / 5 + reputation.calculate
    if (isPredefined) score += NodeStatistics.REPUTATION_PREDEFINED
    score
  }

  def getDisconnectReason: ReasonCode = {
    if (tronLastLocalDisconnectReason != null) return tronLastLocalDisconnectReason
    if (tronLastRemoteDisconnectReason != null) return tronLastRemoteDisconnectReason
    ReasonCode.UNKNOWN
  }

  def isReputationPenalized: Boolean = {
    if (wasDisconnected && (tronLastRemoteDisconnectReason eq ReasonCode.TOO_MANY_PEERS) && System.currentTimeMillis - lastDisconnectedTime < NodeStatistics.TOO_MANY_PEERS_PENALIZE_TIMEOUT) return true
    if (wasDisconnected && (tronLastRemoteDisconnectReason eq ReasonCode.DUPLICATE_PEER) && System.currentTimeMillis - lastDisconnectedTime < NodeStatistics.TOO_MANY_PEERS_PENALIZE_TIMEOUT) return true
    if (firstDisconnectedTime > 0 && (System.currentTimeMillis - firstDisconnectedTime) > NodeStatistics.CLEAR_CYCLE_TIME) {
      tronLastLocalDisconnectReason = null
      tronLastRemoteDisconnectReason = null
      disconnectTimes = 0
      persistedReputation = 0
      firstDisconnectedTime = 0
    }
    if ((tronLastLocalDisconnectReason eq ReasonCode.INCOMPATIBLE_PROTOCOL) || (tronLastRemoteDisconnectReason eq ReasonCode.INCOMPATIBLE_PROTOCOL) || (tronLastLocalDisconnectReason eq ReasonCode.BAD_PROTOCOL) || (tronLastRemoteDisconnectReason eq ReasonCode.BAD_PROTOCOL) || (tronLastLocalDisconnectReason eq ReasonCode.BAD_BLOCK) || (tronLastRemoteDisconnectReason eq ReasonCode.BAD_BLOCK) || (tronLastLocalDisconnectReason eq ReasonCode.BAD_TX) || (tronLastRemoteDisconnectReason eq ReasonCode.BAD_TX) || (tronLastLocalDisconnectReason eq ReasonCode.FORKED) || (tronLastRemoteDisconnectReason eq ReasonCode.FORKED) || (tronLastLocalDisconnectReason eq ReasonCode.UNLINKABLE) || (tronLastRemoteDisconnectReason eq ReasonCode.UNLINKABLE) || (tronLastLocalDisconnectReason eq ReasonCode.INCOMPATIBLE_CHAIN) || (tronLastRemoteDisconnectReason eq ReasonCode.INCOMPATIBLE_CHAIN) || (tronLastRemoteDisconnectReason eq ReasonCode.SYNC_FAIL) || (tronLastLocalDisconnectReason eq ReasonCode.SYNC_FAIL) || (tronLastRemoteDisconnectReason eq ReasonCode.INCOMPATIBLE_VERSION) || (tronLastLocalDisconnectReason eq ReasonCode.INCOMPATIBLE_VERSION)) {
      persistedReputation = 0
      return true
    }
    false
  }

  def nodeDisconnectedRemote(reason: ReasonCode) {
    lastDisconnectedTime = System.currentTimeMillis
    tronLastRemoteDisconnectReason = reason
  }

  def nodeDisconnectedLocal(reason: ReasonCode) {
    lastDisconnectedTime = System.currentTimeMillis
    tronLastLocalDisconnectReason = reason
  }

  def notifyDisconnect() {
    lastDisconnectedTime = System.currentTimeMillis
    if (firstDisconnectedTime <= 0) firstDisconnectedTime = lastDisconnectedTime
    if (tronLastLocalDisconnectReason eq ReasonCode.RESET) return
    disconnectTimes += 1
    persistedReputation = persistedReputation / 2
  }

  def wasDisconnected: Boolean = lastDisconnectedTime > 0

  def setPersistedReputation(persistedReputation: Int) {
    this.persistedReputation = persistedReputation
  }

  def setPredefined(isPredefined: Boolean) {
    this.isPredefined = isPredefined
  }

  override def toString: String = "NodeStat[reput: " + getReputation + "(" + persistedReputation + "), discover: " + messageStatistics.discoverInPong + "/" + messageStatistics.discoverOutPing + " " + messageStatistics.discoverOutPong + "/" + messageStatistics.discoverInPing + " " + messageStatistics.discoverInNeighbours + "/" + messageStatistics.discoverOutFindNode + " " + messageStatistics.discoverOutNeighbours + "/" + messageStatistics.discoverInFindNode + " " + discoverMessageLatency.getAvrg.toInt + "ms" + ", p2p: " + p2pHandShake + "/" + messageStatistics.p2pInHello + "/" + messageStatistics.p2pOutHello + " " + ", tron: " + messageStatistics.tronInMessage + "/" + messageStatistics.tronOutMessage + " " + (if (wasDisconnected) "X " + disconnectTimes
  else "") + (if (tronLastLocalDisconnectReason != null) "<=" + tronLastLocalDisconnectReason
  else " ") + (if (tronLastRemoteDisconnectReason != null) "=>" + tronLastRemoteDisconnectReason
  else " ") + ", tcp flow: " + tcpFlow.getTotalCount

  class SimpleStatter(val name: String) {
    private var last = .0
    private var sum = .0
    private var count = 0

    def add(value: Double) {
      last = value
      sum += value
      count += 1
    }

    def getLast: Double = last

    def getCount: Int = count

    def getSum: Double = sum

    def getAvrg: Double = if (count == 0) 0
    else sum / count

    def getName: String = name
  }

  def nodeIsHaveDataTransfer: Boolean = {
    return tcpFlow.getTotalCount > MIN_DATA_LENGTH
  }

  def resetTcpFlow() {
    tcpFlow.reset()
  }

}

object NodeStatistics {
  val REPUTATION_PREDEFINED:  Int = 100000
  val TOO_MANY_PEERS_PENALIZE_TIMEOUT: Long = 60 * 1000L
  val CLEAR_CYCLE_TIME: Long = 60 * 60 * 1000L
}
