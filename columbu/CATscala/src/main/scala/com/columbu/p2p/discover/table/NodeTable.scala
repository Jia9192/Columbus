package com.galileo.p2p.discover.table

import java.util
import com.galileo.p2p.discover.node.Node
import org.slf4j.LoggerFactory
import scala.collection.JavaConversions._
import scala.util.control.Breaks

class NodeTable {

  private val logger = LoggerFactory.getLogger(getClass())
  private var node: Node = null
  private var buckets: Array[NodeBucket] = null
  private var nodes: util.List[NodeEntry] = null
  private val evictedCandidates: util.Map[Node, Node] = new util.HashMap[Node, Node]
  private val expectedPongs: util.Map[Node, util.Date] = new util.HashMap[Node, util.Date]

  def this(n: Node) {
    this()
    this.node = n
    initialize()
  }

  def getNode: Node = return node

  def initialize() {
    nodes = new util.ArrayList[NodeEntry]
    buckets = new Array[NodeBucket](KademliaOptions.BINS)
    var i: Int = 0
    while (i < KademliaOptions.BINS) {
      {
        buckets(i) = new NodeBucket(i)
      }
      {
        i += 1; i - 1
      }
    }
  }

  def addNode(n: Node): Node = {
    val e: NodeEntry = new NodeEntry(node.getId, n)
    if (nodes.contains(e)) {
      nodes.foreach(nodeEntry => {
        if (nodeEntry.equals(e)) nodeEntry.touch()
      })
      return null
    }
    val lastSeen: NodeEntry = buckets(getBucketId(e)).addNode(e)
    if (lastSeen != null) {
      return lastSeen.getNode
    }
    if (!nodes.contains(e)) {
      nodes.add(e)
    }
    return null
  }

  def dropNode(n: Node) {
    val e: NodeEntry = new NodeEntry(node.getId, n)
    buckets(getBucketId(e)).dropNode(e)
    nodes.remove(e)
  }

  def contains(n: Node): Boolean = {
    val e: NodeEntry = new NodeEntry(node.getId, n)
    for (b <- buckets) {
      if (b.getNodes.contains(e)) {
        return true
      }
    }
    return false
  }

  def touchNode(n: Node) {
    val e: NodeEntry = new NodeEntry(node.getId, n)
    val loop = new Breaks()
    loop.breakable {
      for (b <- buckets) {
        if (b.getNodes.contains(e)) {
          b.getNodes.get(b.getNodes.indexOf(e)).touch()
          loop.break()
        }
      }
    }
  }

  def getBucketsCount: Int = {
    var i: Int = 0
    for (b <- buckets) {
      if (b.getNodesCount > 0) {
        i += 1
      }
    }
    return i
  }

  def getBuckets: Array[NodeBucket] = {
    return buckets
  }

  def getBucketId(e: NodeEntry): Int = {
    val id: Int = e.getDistance - 1
    return if (id < 0) 0
    else id
  }

  def getNodesCount: Int = {
    return nodes.size
  }

  def getAllNodes: util.List[NodeEntry] = {
    val nodes: util.List[NodeEntry] = new util.ArrayList[NodeEntry]
    for (b <- buckets) {
      for (e <- b.getNodes) {
        if (!e.getNode.equals(node)) {
          nodes.add(e)
        }
      }
    }
    return nodes
  }

  def getClosestNodes(targetId: Array[Byte]): util.List[Node] = {
    var closestEntries: util.List[NodeEntry] = getAllNodes
    val closestNodes: util.List[Node] = new util.ArrayList[Node]
    util.Collections.sort(closestEntries, new DistanceComparator(targetId))
    if (closestEntries.size > KademliaOptions.BUCKET_SIZE) {
      closestEntries = closestEntries.subList(0, KademliaOptions.BUCKET_SIZE)
    }
    for (e <- closestEntries) {
      if (!e.getNode.isDiscoveryNode) {
        closestNodes.add(e.getNode)
      }
    }
    return closestNodes
  }

}
