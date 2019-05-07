package com.galileo.p2p.udp.message

import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.galileo.core.enumeration.UdpMessageTypeEnum._
import org.apache.commons.lang3.ArrayUtils
import com.galileo.p2p.udp.message.backup.KeepAliveMessage
import com.galileo.p2p.udp.message.discover.FindNodeMessage
import com.galileo.p2p.udp.message.discover.NeighborsMessage
import com.galileo.p2p.udp.message.discover.PingMessage
import com.galileo.p2p.udp.message.discover.PongMessage
import com.galileo.p2p.discover.node.Node
import com.galileo.utils.ByteArray
import com.galileo.utils.Sha256Hash
import com.galileo.core.exception.P2pException
import com.galileo.protos.protos.Discover.Endpoint

abstract class Message {

  protected var `type`: UdpMessageTypeEnum = null
  protected var data: Array[Byte] = null

  def this(`type`: UdpMessageTypeEnum, data: Array[Byte]) {
    this()
    this.`type` = `type`
    this.data = data
  }

  def getType: UdpMessageTypeEnum = this.`type`

  def getData: Array[Byte] = this.data

  def getSendData: Array[Byte] = ArrayUtils.add(this.data, 0, `type`.getType)

  def getMessageId: Sha256Hash = Sha256Hash.of(getData)

  def getFrom: Node

  override def toString: String = {
    "[Message Type: " + getType + ", len: " + (if (data == null) 0 else data.length) + "]"
  }

  override def equals(obj: Any): Boolean = {
    super.equals(obj)
  }

  override def hashCode: Int = getMessageId.hashCode

}

object Message  {

  def getNode(endpoint: Endpoint): Node = {
    new Node(endpoint.getNodeId.toByteArray, ByteArray.toStr(endpoint.getAddress.toByteArray), endpoint.getPort)
  }

  @throws[Exception]
  def parse(encode: Array[Byte]): Message = {
    val `type` = encode(0)
    val data = ArrayUtils.subarray(encode, 1, encode.length)
    UdpMessageTypeEnum.fromByte(`type`) match {
      case DISCOVER_PING =>
        new PingMessage(data)
      case DISCOVER_PONG =>
        new PongMessage(data)
      case DISCOVER_FIND_NODE =>
        new FindNodeMessage(data)
      case DISCOVER_NEIGHBORS =>
        new NeighborsMessage(data)
      case BACKUP_KEEP_ALIVE =>
        new KeepAliveMessage(data)
      case _ =>
        throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, "type=" + `type`)
    }
  }

}
