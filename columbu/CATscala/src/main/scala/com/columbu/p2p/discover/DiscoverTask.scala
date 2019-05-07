package com.galileo.p2p.discover

import java.util
import com.galileo.p2p.discover.node.Node
import com.galileo.p2p.discover.node.NodeManager
import org.slf4j.LoggerFactory
import com.galileo.p2p.discover.table.KademliaOptions
import scala.collection.JavaConversions._
import scala.util.control.Breaks

class DiscoverTask extends Runnable{

  private val logger = LoggerFactory.getLogger(getClass())

  var nodeManager: NodeManager = null
  var nodeId: Array[Byte] = null

  def this(nodeManager: NodeManager) {
    this()
    this.nodeManager = nodeManager
    nodeId = nodeManager.getPublicHomeNode.getId
  }

  def run() {
    discover(nodeId, 0, new util.ArrayList[Node])
  }

  def discover(nodeId: Array[Byte], round: Int, prevTried: util.List[Node]) {
    val loop = new Breaks()
    try{
      if (round == KademliaOptions.MAX_STEPS) {
        logger.debug("Node table contains ["+nodeManager.getTable.getNodesCount+"] peers")
        var logMsg = "(KademliaOptions.MAX_STEPS) Terminating discover after %d rounds.".format(round)
        logger.debug(logMsg)
        logMsg = "Nodes discovered %d ".format(nodeManager.getTable.getNodesCount)
        logger.trace(logMsg+"\n"+dumpNodes)
        return
      }
      val closest = nodeManager.getTable.getClosestNodes(nodeId)
      val tried = new util.ArrayList[Node]
      loop.breakable {
        for (n <- closest) {
          if (!tried.contains(n) && !prevTried.contains(n)) {
            try {
              nodeManager.getNodeHandler(n).sendFindNode(nodeId)
              tried.add(n)
              wait(50)
            } catch {
              case e: InterruptedException => {
              }
              case ex: Exception => {
                logger.error("Unexpected Exception " + ex, ex)
              }
            }
          }
          if (tried.size == KademliaOptions.ALPHA) {
            loop.break()
          }
        }
      }
      if (tried.isEmpty) {
        var logMsg = "(tried.isEmpty()) Terminating discover after %d rounds.".format(round)
        logger.debug(logMsg)
        logMsg = "Nodes discovered %d ".format(nodeManager.getTable.getNodesCount)
        logger.trace(logMsg+"\n"+dumpNodes)
        return
      }
      tried.addAll(prevTried)
      discover(nodeId, round + 1, tried)
    } catch {
      case ex: Exception => {
        logger.error("" + ex)
      }
    }
  }

  private def dumpNodes: String = {
    var ret: String = ""
    for (entry <- nodeManager.getTable.getAllNodes) {
      ret += "    " + entry.getNode + "\n"
    }
    return ret
  }

}
