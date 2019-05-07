package com.galileo.p2p.udp.handler

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.socket.DatagramPacket
import io.netty.handler.codec.MessageToMessageDecoder
import java.util
import org.slf4j.LoggerFactory
import com.galileo.p2p.udp.message.Message

class PacketDecoder extends MessageToMessageDecoder[DatagramPacket]{

  private val logger = LoggerFactory.getLogger(getClass())
  private val MAXSIZE = 2048

  @throws[Exception]
  def decode(ctx: ChannelHandlerContext, packet: DatagramPacket, out: util.List[AnyRef]) {
    val buf = packet.content
    val length = buf.readableBytes
    if (length <= 1 || length >= MAXSIZE) {
      logger.error("UDP rcv bad packet, from "+ctx.channel.remoteAddress+" length = "+length)
      return
    }
    val encoded = new Array[Byte](length)
    buf.readBytes(encoded)
    try{
      val event = new UdpEvent(Message.parse(encoded), packet.sender)
      out.add(event)
    }catch {
      case e: Exception => {
        logger.error("Parse msg failed, type "+encoded(0)+", len "+encoded.length+", address "+packet.sender)
      }
    }
  }

}
