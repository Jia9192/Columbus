package com.galileo.p2p.message

import com.galileo.core.enumeration.MessageTypes
import com.galileo.protos.protos.Protocol

class DisconnectMessage extends P2pMessage{

  private var disconnectMessage: Protocol.DisconnectMessage = null

  def this(`type`: Byte, rawData: Array[Byte]) {
    this()
    this.`type` = `type`
    this.data = rawData
    this.disconnectMessage = Protocol.DisconnectMessage.parseFrom(this.data)
  }

  def this(reasonCode: Protocol.ReasonCode) {
    this()
    this.disconnectMessage = Protocol.DisconnectMessage.newBuilder.setReason(reasonCode).build.asInstanceOf[Protocol.DisconnectMessage]
    this.`type` = MessageTypes.P2P_DISCONNECT.asByte
    this.data = this.disconnectMessage.toByteArray
  }

  def getReason: Int = this.disconnectMessage.getReason.getNumber

  def getReasonCode: Protocol.ReasonCode = this.disconnectMessage.getReason

  override def toString: String = {
    return new StringBuilder().append(super.toString).append("reason: ").append(this.disconnectMessage.getReason).toString
  }

  override def getAnswerMessage = null

}
