package com.galileo.p2p.udp.message.discover

import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.google.protobuf.ByteString
import com.galileo.p2p.udp.message.Message
import com.galileo.p2p.discover.node.Node
import com.galileo.utils.ByteArray
import com.galileo.core.config.args.Args
import com.galileo.protos.protos.Discover
import com.galileo.protos.protos.Discover.Endpoint

class PongMessage extends Message{

  private var pongMessage: Discover.PongMessage = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_PONG
    this.data = data
    this.pongMessage = Discover.PongMessage.parseFrom(data)
  }

  def this(from: Node) {
    this()
    this.`type` = UdpMessageTypeEnum.DISCOVER_PONG
    val toEndpoint = Endpoint.newBuilder.setAddress(ByteString.copyFrom(ByteArray.fromString(from.getHost))).setPort(from.getPort).setNodeId(ByteString.copyFrom(from.getId)).build
    this.pongMessage = Discover.PongMessage.newBuilder.setFrom(toEndpoint).setEcho(Args.getInstance.getNodeP2pVersion).setTimestamp(System.currentTimeMillis).build
    this.data = this.pongMessage.toByteArray
  }

  def getVersion: Int = this.pongMessage.getEcho

  override def getFrom: Node = {
    Message.getNode(pongMessage.getFrom)
  }

  override def toString: String = {
    "[pongMessage: " + pongMessage
  }

}
