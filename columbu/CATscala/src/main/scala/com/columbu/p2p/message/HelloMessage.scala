package com.galileo.p2p.message

import com.google.protobuf.ByteString
import com.galileo.p2p.discover.node.Node
import com.galileo.utils.ByteArray
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.config.args.Args
import com.galileo.core.enumeration.MessageTypes
import com.galileo.protos.protos.Discover.Endpoint
import com.galileo.protos.protos.Protocol

class HelloMessage extends P2pMessage{

  private var helloMessage: Protocol.HelloMessage = null

  def this(`type`: Byte, rawData: Array[Byte]) {
    this()
    this.`type` = `type`
    this.data = rawData
    this.helloMessage = Protocol.HelloMessage.parseFrom(rawData)
  }

  def this(from: Node, timestamp: Long, genesisBlockId: BlockCapsule.BlockId, solidBlockId: BlockCapsule.BlockId, headBlockId: BlockCapsule.BlockId){
    this()
    val fromEndpoint = Endpoint.newBuilder.setNodeId(ByteString.copyFrom(from.getId)).setPort(from.getPort).setAddress(ByteString.copyFrom(ByteArray.fromString(from.getHost))).build
    val gBlockId = Protocol.HelloMessage.BlockId.newBuilder.setHash(genesisBlockId.getByteString).setNumber(genesisBlockId.getNum).build
    val sBlockId = Protocol.HelloMessage.BlockId.newBuilder.setHash(solidBlockId.getByteString).setNumber(solidBlockId.getNum).build
    val hBlockId = Protocol.HelloMessage.BlockId.newBuilder.setHash(headBlockId.getByteString).setNumber(headBlockId.getNum).build
    val builder = Protocol.HelloMessage.newBuilder
    builder.setFrom(fromEndpoint)
    builder.setVersion(Args.getInstance.getNodeP2pVersion)
    builder.setTimestamp(timestamp)
    builder.setGenesisBlockId(gBlockId)
    builder.setSolidBlockId(sBlockId)
    builder.setHeadBlockId(hBlockId)
    this.helloMessage = builder.build
    this.`type` = MessageTypes.P2P_HELLO.asByte
    this.data = this.helloMessage.toByteArray
  }

  def getVersion: Int = this.helloMessage.getVersion

  def getTimestamp: Long = this.helloMessage.getTimestamp

  def getFrom: Node = {
    val from = this.helloMessage.getFrom
    new Node(from.getNodeId.toByteArray, ByteArray.toStr(from.getAddress.toByteArray), from.getPort)
  }

  def getGenesisBlockId = new BlockCapsule.BlockId(this.helloMessage.getGenesisBlockId.getHash, this.helloMessage.getGenesisBlockId.getNumber)

  def getSolidBlockId = new BlockCapsule.BlockId(this.helloMessage.getSolidBlockId.getHash, this.helloMessage.getSolidBlockId.getNumber)

  def getHeadBlockId = new BlockCapsule.BlockId(this.helloMessage.getHeadBlockId.getHash, this.helloMessage.getHeadBlockId.getNumber)

  def getAnswerMessage = null

  override def toString: String = new StringBuilder().append(super.toString).append(helloMessage.toString).toString

}
