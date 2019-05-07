package com.galileo.p2p.message

import org.spongycastle.util.encoders.Hex
import com.galileo.core.enumeration.MessageTypes

class PingMessage extends P2pMessage{
  private val FIXED_PAYLOAD = Hex.decode("C0")

  {
    this.`type` = MessageTypes.P2P_PING.asByte
    this.data = FIXED_PAYLOAD
  }

  def this(`type`: Byte, rawData: Array[Byte]) {
    this()
    this.`type` = `type`
    this.data = rawData
  }

  override def getData = FIXED_PAYLOAD

  override def toString: String = super.toString

  def getAnswerMessage: Class[_] = classOf[PongMessage]

  override def getType: MessageTypes = MessageTypes.fromByte(this.`type`)

}
