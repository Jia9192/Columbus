package com.galileo.p2p.udp.handler

import io.netty.buffer.Unpooled
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.socket.DatagramPacket
import io.netty.channel.socket.nio.NioDatagramChannel
import java.net.InetSocketAddress
import java.util.function.Consumer
import org.slf4j.LoggerFactory

class MessageHandler extends SimpleChannelInboundHandler[UdpEvent] with Consumer[UdpEvent]{

  private val logger = LoggerFactory.getLogger(getClass())
  private var channel: Channel = null
  private var eventHandler: EventHandler = null

  def this(channel: NioDatagramChannel, eventHandler: EventHandler) {
    this()
    this.channel = channel
    this.eventHandler = eventHandler
  }

  @throws[Exception]
  override def channelActive(ctx: ChannelHandlerContext) {
    eventHandler.channelActivated()
  }

  def channelRead0(ctx: ChannelHandlerContext, udpEvent: UdpEvent) {
    logger.debug("rcv udp msg type "+udpEvent.getMessage.getType+", len "+udpEvent.getMessage.getSendData.length+" from "+udpEvent.getAddress)
    eventHandler.handleEvent(udpEvent)
  }

  def accept(udpEvent: UdpEvent) {
    logger.debug("send udp msg type "+udpEvent.getMessage.getType+", len "+udpEvent.getMessage.getSendData.length+" to "+udpEvent.getAddress)
    val address = udpEvent.getAddress
    sendPacket(udpEvent.getMessage.getSendData, address)
  }

  def sendPacket(wire: Array[Byte], address: InetSocketAddress) {
    val packet = new DatagramPacket(Unpooled.copiedBuffer(wire), address)
    channel.write(packet)
    channel.flush
  }

  override def channelReadComplete(ctx: ChannelHandlerContext) {
    ctx.flush
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
    logger.info("exception caught, "+ctx.channel.remoteAddress+" " + cause.getMessage)
    ctx.close
  }

}
