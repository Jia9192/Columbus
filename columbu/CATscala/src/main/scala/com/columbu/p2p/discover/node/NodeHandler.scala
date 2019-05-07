package com.galileo.p2p.discover.node

import java.net.InetSocketAddress
import java.util
import java.util.concurrent.TimeUnit
import com.galileo.core.enumeration.State
import com.galileo.p2p.discover.node.statistics.NodeStatistics
import org.slf4j.LoggerFactory
import org.spongycastle.util.encoders.Hex
import com.galileo.p2p.udp.handler.UdpEvent
import com.galileo.p2p.udp.message.Message
import com.galileo.p2p.udp.message.discover.FindNodeMessage
import com.galileo.p2p.udp.message.discover.NeighborsMessage
import com.galileo.p2p.udp.message.discover.PingMessage
import com.galileo.p2p.udp.message.discover.PongMessage
import com.galileo.core.config.args.Args
import scala.collection.JavaConversions._

class NodeHandler {

  private val logger = LoggerFactory.getLogger(getClass)

  private val PingTimeout = 15000

  private var sourceNode: Node = null
  private var node: Node = null
  private var state: State = null
  private var nodeManager: NodeManager = null
  private var nodeStatistics: NodeStatistics = null
  private var replaceCandidate: NodeHandler = null
  private var inetSocketAddress: InetSocketAddress = null
  private var waitForPong = false
  private var waitForNeighbors = false
  private var pingTrials = 3
  private var pingSent = 0L

  def this(node: Node, nodeManager: NodeManager) {
    this()
    this.node = node
    this.nodeManager = nodeManager
    this.inetSocketAddress = new InetSocketAddress(node.getHost, node.getPort)
    this.nodeStatistics = new NodeStatistics(node)
    changeState(State.Discovered)
  }

  def getInetSocketAddress: InetSocketAddress = inetSocketAddress

  def setSourceNode(sourceNode: Node) {
    this.sourceNode = sourceNode
  }

  def getSourceNode: Node = sourceNode

  def getNode: Node = node

  def getState: State = state

  def setNode(node: Node) {
    this.node = node
  }

  def getNodeStatistics: NodeStatistics = nodeStatistics

  private def challengeWith(replaceCandidate: NodeHandler) {
    this.replaceCandidate = replaceCandidate
    changeState(State.EvictCandidate)
  }

  // Manages state transfers
  def changeState(newer: State) {
    var newState = newer
    val oldState = state
    if (newState == State.Discovered){
      if (sourceNode != null && sourceNode.getPort != node.getPort){
        changeState(State.Dead)
      } else {
        sendPing()
      }
    }
    if (!node.isDiscoveryNode) {
      if (newState == State.Alive) {
        val evictCandidate = nodeManager.getTable.addNode(this.node)
        if (evictCandidate == null){
          newState = State.Active
        } else {
          val evictHandler: NodeHandler = nodeManager.getNodeHandler(evictCandidate)
          if (evictHandler.state ne State.EvictCandidate){
            evictHandler.challengeWith(this)
          }
        }
      }
      if (newState eq State.Active) if (oldState eq State.Alive) {
        // new node won the challenge
        nodeManager.getTable.addNode(node)
      }
      else if (oldState eq State.EvictCandidate) {
        // nothing to do here the node is already in the table
      }
      else {
        // wrong state transition
      }
      if (newState eq State.NonActive) if (oldState eq State.EvictCandidate) {
        // lost the challenge
        // Removing ourselves from the table
        nodeManager.getTable.dropNode(node)
        // Congratulate the winner
        replaceCandidate.changeState(State.Active)
      }
      else if (oldState eq State.Alive) {
        // ok the old node was better, nothing to do here
      }
      else {
        // wrong state transition
      }
    }
    if (newState eq State.EvictCandidate) {
      // trying to survive, sending ping and waiting for pong
      sendPing()
    }
    state = newState
  }

  def handlePing(msg: PingMessage) {
    if (!nodeManager.getTable.getNode.equals(node)) sendPong()
    if (msg.getVersion != Args.getInstance.getNodeP2pVersion){
      changeState(State.NonActive)
    } else if (state == State.NonActive || state == State.Dead){
      changeState(State.Discovered)
    }
  }

  def handlePong(msg: PongMessage) {
    if (waitForPong) {
      waitForPong = false
      getNodeStatistics.discoverMessageLatency.add(System.currentTimeMillis.toDouble - pingSent)
      getNodeStatistics.lastPongReplyTime.set(System.currentTimeMillis)
      node.setId(msg.getFrom.getId)
      if (msg.getVersion != Args.getInstance.getNodeP2pVersion) changeState(State.NonActive)
      else changeState(State.Alive)
    }
  }

  def handleNeighbours(msg: NeighborsMessage) {
    if (!waitForNeighbors) {
      logger.warn("Receive neighbors from {} without send find nodes.", node.getHost)
      return
    }
    waitForNeighbors = false
    for (n <- msg.getNodes) {
      if (!nodeManager.getPublicHomeNode.getHexId.equals(n.getHexId)){
        nodeManager.getNodeHandler(n)
      }
    }
  }

  def handleFindNode(msg: FindNodeMessage) {
    val closest = nodeManager.getTable.getClosestNodes(msg.getTargetId)
    sendNeighbours(closest)
  }

  def handleTimedOut() {
    waitForPong = false
    if ( {pingTrials -= 1; pingTrials} > 0){
      sendPing()
    } else {
      if (state eq State.Discovered){
        changeState(State.Dead)
      } else if (state eq State.EvictCandidate){
        changeState(State.NonActive)
      } else {
        // TODO just influence to reputation
      }
    }
  }

  def sendPing() {
    val ping = new PingMessage(nodeManager.getPublicHomeNode, getNode)
    waitForPong = true
    pingSent = System.currentTimeMillis
    sendMessage(ping)
    if (nodeManager.getPongTimer.isShutdown){
      return
    }
    nodeManager.getPongTimer.schedule(new PongServer(), PingTimeout, TimeUnit.MILLISECONDS)
  }

  def sendPong() {
    val pong = new PongMessage(nodeManager.getPublicHomeNode)
    sendMessage(pong)
  }

  def sendNeighbours(neighbours: util.List[Node]) {
    val neighbors = new NeighborsMessage(nodeManager.getPublicHomeNode, neighbours)
    sendMessage(neighbors)
  }

  def sendFindNode(target: Array[Byte]) {
    waitForNeighbors = true
    val findNode = new FindNodeMessage(nodeManager.getPublicHomeNode, target)
    sendMessage(findNode)
  }

  private def sendMessage(msg: Message) {
    nodeManager.sendOutbound(new UdpEvent(msg, getInetSocketAddress))
    nodeStatistics.messageStatistics.addUdpOutMessage(msg.getType)
  }

  override def toString: String = {
    return "NodeHandler[state: " + state + ", node: " + node.getHost + ":" + node.getPort + ", id=" + (if (node.getId.length > 0) Hex.toHexString(node.getId, 0, 4)
    else "empty") + "]"
  }

  private class PongServer extends Runnable {
    override def run() {
      try{
          if (waitForPong) {
            waitForPong = false
            handleTimedOut()
          }
      }catch {
        case t: Throwable => {
          logger.error("Unhandled exception", t)
        }
      }
    }
  }
}
