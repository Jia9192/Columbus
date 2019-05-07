package com.galileo.p2p.message

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.util
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.server.Channel
import com.galileo.core.exception.P2pException
import com.galileo.core.enumeration.MessageTypes
import com.galileo.p2p.net.message.TronMessageFactory

@Component
@Scope("prototype")
class MessageCodec extends ByteToMessageDecoder{

  private var channel: Channel = null
  private val p2pMessageFactory: P2pMessageFactory = new P2pMessageFactory
  private val tronMessageFactory: TronMessageFactory = new TronMessageFactory

  @throws[Exception]
  override def decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: util.List[AnyRef]) {
    val length = buffer.readableBytes
    val encoded = new Array[Byte](length)
    buffer.readBytes(encoded)
    try{
      val msg = createMessage(encoded)
      channel.getNodeStatistics.tcpFlow.add(length)
      out.add(msg)
    }catch {
      case e: Exception => {
        channel.processException(e)
      }
    }
  }

  def setChannel(channel: Channel) {
    this.channel = channel
  }

  @throws[Exception]
  private def createMessage(encoded: Array[Byte]): Message = {
    val `type` = encoded(0)
    if (MessageTypes.inP2pRange(`type`)) return p2pMessageFactory.create(encoded)
    if (MessageTypes.inTronRange(`type`)) return tronMessageFactory.create(encoded)
    throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, "type=" + encoded(0))
  }

}
