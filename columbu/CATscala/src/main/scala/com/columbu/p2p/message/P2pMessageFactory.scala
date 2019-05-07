package com.galileo.p2p.message

import org.apache.commons.lang3.ArrayUtils
import com.galileo.core.exception.P2pException
import com.galileo.core.exception.P2pException.TypeEnum
import com.galileo.core.enumeration.MessageTypes
import com.galileo.core.enumeration.MessageTypes._

class P2pMessageFactory extends MessageFactory{

  @throws[Exception]
  def create(data: Array[Byte]): P2pMessage = {
    if (data.length <= 1) throw new P2pException(TypeEnum.MESSAGE_WITH_WRONG_LENGTH, "len=" + data.length + ", MessageType=" + (if (data.length == 1) data(0) else "unknow"))
    try{
      val `type` = data(0)
      val rawData = ArrayUtils.subarray(data, 1, data.length)
      create(`type`, rawData)
    }catch {
      case e: Exception => {
        if (e.isInstanceOf[P2pException]) throw e
        else throw new P2pException(P2pException.TypeEnum.PARSE_MESSAGE_FAILED, "type=" + data(0) + ", len=" + data.length)
      }
    }
  }

  @throws[Exception]
  private def create(`type`: Byte, rawData: Array[Byte]) = {
    val messageType = MessageTypes.fromByte(`type`)
    if (messageType == null) throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, "type=" + `type` + ", len=" + rawData.length)
    messageType match {
      case P2P_HELLO =>
        new HelloMessage(`type`, rawData)
      case P2P_DISCONNECT =>
        new DisconnectMessage(`type`, rawData)
      case P2P_PING =>
        new PingMessage(`type`, rawData)
      case P2P_PONG =>
        new PongMessage(`type`, rawData)
      case _ =>
        throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, messageType.toString + ", len=" + rawData.length)
    }
  }

}
