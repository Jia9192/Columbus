package com.galileo.p2p.udp.message.discover

import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.google.protobuf.ByteString
import com.galileo.p2p.udp.message.Message
import com.galileo.p2p.discover.node.Node
import com.galileo.utils.ByteArray
import com.galileo.protos.protos.Discover
import com.galileo.protos.protos.Discover.Endpoint
import com.galileo.protos.protos.Discover.FindNeighbours

class FindNodeMessage extends Message{

  private var findNeighbours: Discover.FindNeighbours = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_FIND_NODE
    this.data = data
    this.findNeighbours = Discover.FindNeighbours.parseFrom(data)
  }

  def this(from: Node, targetId: Array[Byte]) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_FIND_NODE
    val fromEndpoint = Endpoint.newBuilder.setAddress(ByteString.copyFrom(ByteArray.fromString(from.getHost))).setPort(from.getPort).setNodeId(ByteString.copyFrom(from.getId)).build
    this.findNeighbours = FindNeighbours.newBuilder.setFrom(fromEndpoint).setTargetId(ByteString.copyFrom(targetId)).setTimestamp(System.currentTimeMillis).build
    this.data = this.findNeighbours.toByteArray
  }

  def getTargetId: Array[Byte] = this.findNeighbours.getTargetId.toByteArray

  override def getFrom: Node = {
    Message.getNode(findNeighbours.getFrom)
  }

  override def toString: String = {
    "[findNeighbours: " + findNeighbours
  }
}
