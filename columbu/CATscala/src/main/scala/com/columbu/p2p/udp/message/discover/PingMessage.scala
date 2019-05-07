package com.galileo.p2p.udp.message.discover

import com.google.protobuf.ByteString
import com.galileo.p2p.udp.message.Message
import com.galileo.p2p.discover.node.Node
import com.galileo.utils.ByteArray
import com.galileo.core.config.args.Args
import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.galileo.protos.protos.Discover
import com.galileo.protos.protos.Discover.Endpoint

class PingMessage extends Message{

  private var pingMessage: Discover.PingMessage = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_PING
    this.data = data
    this.pingMessage = Discover.PingMessage.parseFrom(data)
  }

  def this(from: Node, to: Node) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_PING
    val fromEndpoint = Endpoint.newBuilder.setNodeId(ByteString.copyFrom(from.getId)).setPort(from.getPort).setAddress(ByteString.copyFrom(ByteArray.fromString(from.getHost))).build
    val toEndpoint = Endpoint.newBuilder.setNodeId(ByteString.copyFrom(to.getId)).setPort(to.getPort).setAddress(ByteString.copyFrom(ByteArray.fromString(to.getHost))).build
    this.pingMessage = Discover.PingMessage.newBuilder.setVersion(Args.getInstance.getNodeP2pVersion).setFrom(fromEndpoint).setTo(toEndpoint).setTimestamp(System.currentTimeMillis).build
    this.data = this.pingMessage.toByteArray
  }

  def getVersion: Int = this.pingMessage.getVersion

  def getTo: Node = {
    val to = this.pingMessage.getTo
    new Node(to.getNodeId.toByteArray, ByteArray.toStr(to.getAddress.toByteArray), to.getPort)
  }

  override def getFrom: Node = {
    Message.getNode(pingMessage.getFrom)
  }

  override def toString: String = "[pingMessage: " + pingMessage
}
