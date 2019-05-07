package com.galileo.p2p.udp.message.discover

import com.google.protobuf.ByteString
import java.util
import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.galileo.p2p.udp.message.Message
import com.galileo.p2p.discover.node.Node
import com.galileo.utils.ByteArray
import com.galileo.protos.protos.Discover
import com.galileo.protos.protos.Discover.Endpoint
import com.galileo.protos.protos.Discover.Neighbours
import scala.collection.JavaConverters._

class NeighborsMessage extends Message{

  private var neighbours: Discover.Neighbours = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_NEIGHBORS
    this.data = data
    this.neighbours = Discover.Neighbours.parseFrom(data)
  }

  def this(from: Node, neighbours: util.List[Node]) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_NEIGHBORS
    val builder = Neighbours.newBuilder.setTimestamp(System.currentTimeMillis)
    neighbours.asScala.foreach(neighbour => {
      val endpoint = Endpoint.newBuilder.setAddress(ByteString.copyFrom(ByteArray.fromString(neighbour.getHost))).setPort(neighbour.getPort).setNodeId(ByteString.copyFrom(neighbour.getId)).build
      builder.addNeighbours(endpoint)
    })
    val fromEndpoint = Endpoint.newBuilder.setAddress(ByteString.copyFrom(ByteArray.fromString(from.getHost))).setPort(from.getPort).setNodeId(ByteString.copyFrom(from.getId)).build
    builder.setFrom(fromEndpoint)
    this.neighbours = builder.build
    this.data = this.neighbours.toByteArray
  }

  def getNodes: util.List[Node] = {
    val nodes = new util.ArrayList[Node]
    neighbours.getNeighboursList.asScala.foreach(neighbour => nodes.add(new Node(neighbour.getNodeId().toByteArray(), ByteArray.toStr(neighbour.getAddress().toByteArray()), neighbour.getPort())))
    return nodes
  }

  override def getFrom: Node = {
    Message.getNode(neighbours.getFrom)
  }

  override def toString: String = {
    "[neighbours: " + neighbours
  }
}
