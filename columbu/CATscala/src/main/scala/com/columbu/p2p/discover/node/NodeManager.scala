package com.galileo.p2p.discover.node

import com.galileo.p2p.udp.handler.EventHandler
import java.net.InetAddress
import java.net.InetSocketAddress
import java.util
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.function.Consumer
import java.util.function.Predicate
import com.galileo.core.enumeration.State
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.galileo.p2p.udp.handler.EventHandler
import com.galileo.p2p.udp.handler.UdpEvent
import com.galileo.p2p.udp.message.discover.FindNodeMessage
import com.galileo.p2p.udp.message.discover.NeighborsMessage
import com.galileo.p2p.udp.message.discover.PingMessage
import com.galileo.p2p.udp.message.discover.PongMessage
import com.galileo.p2p.discover.DiscoverListener
import com.galileo.p2p.discover.RefreshTask
import com.galileo.core.enumeration.State
import com.galileo.p2p.discover.node.statistics.NodeStatistics
import com.galileo.p2p.discover.table.NodeTable
import com.galileo.utils.CollectionUtils
import com.galileo.core.config.args.Args
import com.galileo.db.levelDB.store.Manager
import com.galileo.core.enumeration.UdpMessageTypeEnum._
import scala.collection.JavaConversions._
import scala.util.control.Breaks

@Component
class NodeManager extends EventHandler{

  private val logger = LoggerFactory.getLogger(getClass())

  private val args: Args = Args.getInstance
  private var dbManager: Manager = null
  private val LISTENER_REFRESH_RATE = 1000L
  private val DB_COMMIT_RATE = 1 * 60 * 1000L
  private val MAX_NODES = 2000
  private val NODES_TRIM_THRESHOLD = 3000
  private var messageSender: Consumer[UdpEvent] = null
  private var table: NodeTable = null
  private var homeNode: Node = null
  private val nodeHandlerMap: util.Map[String, NodeHandler] = new ConcurrentHashMap[String, NodeHandler]
  private val bootNodes: util.List[Node] = new util.ArrayList[Node]
  private val inboundOnlyFromKnownNodes = false
  private var discoveryEnabled = false
  private val listeners: util.Map[DiscoverListener, NodeManager#ListenerHandler] = new util.IdentityHashMap[DiscoverListener, NodeManager#ListenerHandler]
  private var inited = false
  private val logStatsTimer = new util.Timer
  private val nodeManagerTasksTimer = new util.Timer("NodeManagerTasks")
  private var pongTimer: ScheduledExecutorService = null

  @Autowired
  def this(dbManager: Manager) {
    this()
    this.dbManager = dbManager
    discoveryEnabled = args.isNodeDiscoveryEnable
    homeNode = new Node(RefreshTask.getNodeId, args.getNodeExternalIp, args.getNodeListenPort)

    for (boot <- args.getSeedNode.getIpList) {
      bootNodes.add(Node.instanceOf(boot))
    }
    logger.info("homeNode : {}", homeNode)
    logger.info("bootNodes : size= {}", bootNodes.size)
    table = new NodeTable(homeNode)
    logStatsTimer.scheduleAtFixedRate(new LogTimerTask(), 1 * 1000L, 60 * 1000L)
    this.pongTimer = Executors.newSingleThreadScheduledExecutor
  }

  def getPongTimer: ScheduledExecutorService = pongTimer

  def channelActivated() {
    if (!inited) {
      inited = true
      nodeManagerTasksTimer.scheduleAtFixedRate(new util.TimerTask() {
        def run() {
          processListeners()
        }
      }, LISTENER_REFRESH_RATE, LISTENER_REFRESH_RATE)
      if (args.isNodeDiscoveryPersist) {
        dbRead()
        nodeManagerTasksTimer.scheduleAtFixedRate(new util.TimerTask() {
          def run() {
            dbWrite()
          }
        }, DB_COMMIT_RATE, DB_COMMIT_RATE)
      }
      for (node <- bootNodes) {
        getNodeHandler(node)
      }
    }
  }

  def isNodeAlive(nodeHandler: NodeHandler): Boolean = nodeHandler.getState == State.Alive || nodeHandler.getState == State.Active || nodeHandler.getState == State.EvictCandidate

  private def dbRead() {
    val nodes = this.dbManager.readNeighbours
    logger.info("Reading Node statistics from PeersStore: " + nodes.size + " nodes.")
    nodes.foreach(node => getNodeHandler(node).getNodeStatistics.setPersistedReputation(node.getReputation))
  }

  private def dbWrite() {
    val batch = new util.HashSet[Node]
    this synchronized {
      for (nodeHandler <- nodeHandlerMap.values) {
        val reputation = nodeHandler.getNodeStatistics.getReputation
        nodeHandler.getNode.setReputation(reputation)
        batch.add(nodeHandler.getNode)
      }
    }
    logger.info("Write Node statistics to PeersStore: " + batch.size + " nodes.")
    dbManager.clearAndWriteNeighbours(batch)
  }

  def setMessageSender(messageSender: Consumer[UdpEvent]) {
    this.messageSender = messageSender
  }

  private def getKey(n: Node) : String ={
    return getKey(new InetSocketAddress(n.getHost, n.getPort))
  }

  private def getKey(address: InetSocketAddress): String = {
    val addr = address.getAddress
    var addStr:String = null
    if (addr == null){
      addStr = address.getHostString
    } else {
      addStr = addr.getHostAddress
    }
    return addStr + ":" + address.getPort
  }

  def getNodeHandler(n: Node): NodeHandler = {
    val key = getKey(n)
    var ret = nodeHandlerMap.get(key)
    if (ret == null) {
      trimTable()
      ret = new NodeHandler(n, this)
      nodeHandlerMap.put(key, ret)
    }
    else if (ret.getNode.isDiscoveryNode && !n.isDiscoveryNode) ret.setNode(n)
    ret
  }

  private def trimTable() {
    if (nodeHandlerMap.size > NODES_TRIM_THRESHOLD) {
      val sorted = new util.ArrayList[NodeHandler](nodeHandlerMap.values)
      // reverse sort by reputation
      sorted.sortBy(o => o.getNodeStatistics.getReputation).reverse
      val loop = new Breaks()
      loop.breakable {
        for (handler <- sorted) {
          nodeHandlerMap.remove(getKey(handler.getNode))
          if (nodeHandlerMap.size <= MAX_NODES){
            loop.break()
          }
        }
      }
    }
  }

  def hasNodeHandler(n: Node): Boolean = nodeHandlerMap.containsKey(getKey(n))

  def getTable: NodeTable = table

  def getNodeStatistics(n: Node): NodeStatistics = getNodeHandler(n).getNodeStatistics

  def handleEvent(udpEvent: UdpEvent) {
    val m = udpEvent.getMessage
    val sender = udpEvent.getAddress
    val n = new Node(m.getFrom.getId, sender.getHostString, sender.getPort)
    if (inboundOnlyFromKnownNodes && !hasNodeHandler(n)) {
      logger.warn("Receive packet from unknown node {}.", sender.getAddress)
      return
    }
    val nodeHandler = getNodeHandler(n)
    nodeHandler.getNodeStatistics.messageStatistics.addUdpInMessage(m.getType)
    m.getType match {
      case DISCOVER_PING =>
        nodeHandler.handlePing(m.asInstanceOf[PingMessage])
      case DISCOVER_PONG =>
        nodeHandler.handlePong(m.asInstanceOf[PongMessage])
      case DISCOVER_FIND_NODE =>
        nodeHandler.handleFindNode(m.asInstanceOf[FindNodeMessage])
      case DISCOVER_NEIGHBORS =>
        nodeHandler.handleNeighbours(m.asInstanceOf[NeighborsMessage])
    }
  }

  def sendOutbound(udpEvent: UdpEvent) {
    if (discoveryEnabled && messageSender != null) messageSender.accept(udpEvent)
  }

  def getNodes(minReputation: Int): util.List[NodeHandler] = {
    val ret = new util.ArrayList[NodeHandler]
    for (nodeHandler <- nodeHandlerMap.values) {
      if (nodeHandler.getNodeStatistics.getReputation >= minReputation) ret.add(nodeHandler)
    }
    return ret
  }

  def getNodes(predicate: Predicate[NodeHandler], limit: Int): util.List[NodeHandler] = {
    val filtered = new util.ArrayList[NodeHandler]
    this synchronized {
      for (handler <- nodeHandlerMap.values) {
        if (predicate.test(handler)) filtered.add(handler)
      }
    }
    logger.debug("nodeHandlerMap size "+nodeHandlerMap.size+" filter peer  size " + filtered.size)
    filtered.sortBy( o => o.getNodeStatistics.getReputation).reverse
    CollectionUtils.truncate(filtered, limit)
  }

  def dumpActiveNodes: util.List[NodeHandler] = {
    val handlers = new util.ArrayList[NodeHandler]
    for (handler <- this.nodeHandlerMap.values) {
      if (isNodeAlive(handler)){
        handlers.add(handler)
      }
    }
    return handlers
  }

  private def processListeners() {

    for (handler <- listeners.values) {
      try{
        handler.checkAll()
      }catch {
        case e: Exception => {
          logger.error("Exception processing listener: " + handler, e)
        }
      }
    }
  }

  def addDiscoverListener(listener: DiscoverListener, filter: Predicate[NodeStatistics]) {
    listeners.put(listener, new ListenerHandler(listener, filter))
  }

  def dumpAllStatistics: String = {
    val l = new util.ArrayList[NodeHandler](nodeHandlerMap.values)
    l.sortBy(o => o.getNodeStatistics.getReputation).reverse
    val sb = new StringBuilder
    var zeroReputCount = 0
    for (nodeHandler <- l) {
      if (nodeHandler.getNodeStatistics.getReputation > 0) sb.append(nodeHandler).append("\t").append(nodeHandler.getNodeStatistics).append("\n")
      else zeroReputCount += 1
    }
    sb.append("0 reputation: ").append(zeroReputCount).append(" nodes.\n")
    sb.toString
  }

  def getPublicHomeNode: Node = {
    return homeNode
  }

  def close() {
    try {
      nodeManagerTasksTimer.cancel()
      pongTimer.shutdownNow
      logStatsTimer.cancel()
    }
    catch {
      case e: Exception => {
        logger.warn("close failed.", e)
      }
    }
  }

  private class ListenerHandler(var listener: DiscoverListener, var filter: Predicate[NodeStatistics]) {

    private val discoveredNodes: util.Map[NodeHandler, AnyRef] = new util.IdentityHashMap[NodeHandler, AnyRef]

    def checkAll() {
      for (handler <- nodeHandlerMap.values) {
        val has: Boolean = discoveredNodes.containsKey(handler)
        val test: Boolean = filter.test(handler.getNodeStatistics)
        if (!has && test) {
          listener.nodeAppeared(handler)
          discoveredNodes.put(handler, null)
        }
        else if (has && !test) {
          listener.nodeDisappeared(handler)
          discoveredNodes.remove(handler)
        }
      }
    }
  }

  private class LogTimerTask extends util.TimerTask{
    override def run(){
      logger.trace("Statistics:\n " + dumpAllStatistics)
    }
  }

}
