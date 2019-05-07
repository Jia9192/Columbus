package com.galileo.p2p.discover.node.statistics

import java.lang.Math.min
import java.util

import com.galileo.protos.protos.Protocol.ReasonCode

import scala.collection.JavaConversions._
import scala.util.control.Breaks

class Reputation {

  abstract class Score[T](var t: T) extends Comparable[Score[_]] {
    private[statistics] def calculate(baseScore: Int): Int

    def isContinue = true

    def getOrder = 0

    def compareTo(score: Score[_]): Int = {
      if (getOrder > score.getOrder) return 1
      else if (getOrder < score.getOrder) return -1
      0
    }
  }

  class DiscoverScore(val messageStatistics: MessageStatistics) extends Score[MessageStatistics](messageStatistics) {
    private[statistics] def calculate(baseScore: Int) = {
      var discoverReput = baseScore
      discoverReput += min(t.discoverInPong.getTotalCount.toInt, 1) * (if (t.discoverOutPing.getTotalCount == t.discoverInPong.getTotalCount) 101 else 1)
      discoverReput += min(t.discoverInNeighbours.getTotalCount.toInt, 1) * (if (t.discoverOutFindNode.getTotalCount == t.discoverInNeighbours.getTotalCount) 10 else 1)
      discoverReput
    }

    override def isContinue: Boolean = t.discoverOutPing.getTotalCount == t.discoverInPong.getTotalCount && t.discoverInNeighbours.getTotalCount <= t.discoverOutFindNode.getTotalCount
  }

  class TcpScore(val nodeStatistics: NodeStatistics) extends Score[NodeStatistics](nodeStatistics) {

    private[statistics] def calculate(baseScore: Int): Int = {
      var reput = baseScore
      if (t.p2pHandShake.getTotalCount > 0){
        reput +=  10
      }
      reput += min(t.tcpFlow.getTotalCount.toInt / 10240, 20)
      if (t.messageStatistics.p2pOutPing.getTotalCount == t.messageStatistics.p2pInPong.getTotalCount){
        reput +=  10
      }
      return reput
    }

  }

  class DisConnectScore(val nodeStatistics: NodeStatistics) extends Score[NodeStatistics](nodeStatistics) {

    private[statistics] def calculate(bs: Int): Int = {
      var baseScore = bs.toDouble
      if (t.wasDisconnected) if (t.getTronLastLocalDisconnectReason == null && t.getTronLastRemoteDisconnectReason == null) {
        // means connection was dropped without reporting any reason - bad
        baseScore *= 0.8
      }
      else if (t.getTronLastLocalDisconnectReason ne ReasonCode.REQUESTED) {
        // the disconnect was not initiated by discover mode
        if ((t.getTronLastRemoteDisconnectReason eq ReasonCode.TOO_MANY_PEERS) || (t.getTronLastLocalDisconnectReason eq ReasonCode.TOO_MANY_PEERS) || (t.getTronLastRemoteDisconnectReason eq ReasonCode.TOO_MANY_PEERS_WITH_SAME_IP) || (t.getTronLastLocalDisconnectReason eq ReasonCode.TOO_MANY_PEERS_WITH_SAME_IP) || (t.getTronLastRemoteDisconnectReason eq ReasonCode.DUPLICATE_PEER) || (t.getTronLastLocalDisconnectReason eq ReasonCode.DUPLICATE_PEER) || (t.getTronLastRemoteDisconnectReason eq ReasonCode.TIME_OUT) || (t.getTronLastLocalDisconnectReason eq ReasonCode.TIME_OUT) || (t.getTronLastRemoteDisconnectReason eq ReasonCode.PING_TIMEOUT) || (t.getTronLastLocalDisconnectReason eq ReasonCode.PING_TIMEOUT) || (t.getTronLastRemoteDisconnectReason eq ReasonCode.CONNECT_FAIL) || (t.getTronLastLocalDisconnectReason eq ReasonCode.CONNECT_FAIL)) {
          // The peer is popular, but we were unlucky
          baseScore *= 0.9
        }
        else if (t.getTronLastLocalDisconnectReason eq ReasonCode.RESET) baseScore *= 0.95
        else if (t.getTronLastRemoteDisconnectReason ne ReasonCode.REQUESTED) {
          // other disconnect reasons
          baseScore *= 0.7
        }
      }
      if (t.getDisconnectTimes > 20){
        return 0
      }
      val score = baseScore - Math.pow(2, t.getDisconnectTimes).toInt * (if (t.getDisconnectTimes > 0) 10 else 0)
      return score.toInt
    }

  }

  class OtherScore(val nodeStatistics: NodeStatistics) extends Score[NodeStatistics](nodeStatistics) {

    private[statistics] def calculate(baseScore: Int): Int = {
      if (t.discoverMessageLatency.getAvrg.toInt == 0){
        return baseScore
      } else {
        return min(1000 / t.discoverMessageLatency.getAvrg, 20).toInt + baseScore
      }
    }

  }

  private val scoreList = new util.ArrayList[Reputation#Score[_]]

  def this(nodeStatistics: NodeStatistics){
    this()
    val discoverScore = new DiscoverScore(nodeStatistics.messageStatistics)
    val otherScore = new OtherScore(nodeStatistics)
    val tcpScore = new TcpScore(nodeStatistics)
    val disconnectScore = new DisConnectScore(nodeStatistics)
    scoreList.add(discoverScore)
    scoreList.add(tcpScore)
    scoreList.add(otherScore)
    scoreList.add(disconnectScore)
  }

  def calculate: Int = {
    var scoreNumber = 0
    val loop = new Breaks()
    loop.breakable {
      for (score <- scoreList) {
        scoreNumber = score.calculate(scoreNumber)
        if (!score.isContinue) {
          loop.break()
        }
      }
    }
    if (scoreNumber > 0){
       return scoreNumber
    } else {
      return 0
    }
  }

}
