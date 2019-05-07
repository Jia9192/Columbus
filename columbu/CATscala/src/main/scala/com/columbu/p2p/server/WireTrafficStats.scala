package com.galileo.p2p.server

import com.google.common.util.concurrent.ThreadFactoryBuilder
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import io.netty.channel.socket.DatagramPacket
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import javax.annotation.PreDestroy
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WireTrafficStats extends Runnable{

  private val logger: Logger = LoggerFactory.getLogger(getClass())

  private var executor: ScheduledExecutorService = null
  val tcp: TrafficStatHandler = new TrafficStatHandler
  val udp: TrafficStatHandler = new TrafficStatHandler

  {
    executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("WireTrafficStats-%d").build)
    executor.scheduleAtFixedRate(this, 10, 10, TimeUnit.SECONDS)
  }

  override def run() {
  }

  @PreDestroy
  def close() {
    executor.shutdownNow
  }

  @ChannelHandler.Sharable
  class TrafficStatHandler extends ChannelDuplexHandler {
    var outSizeTot: Long = 0L
    var inSizeTot: Long = 0L
    val outSize: AtomicLong = new AtomicLong
    val inSize: AtomicLong = new AtomicLong
    val outPackets: AtomicLong = new AtomicLong
    val inPackets: AtomicLong = new AtomicLong
    var lastTime: Long = System.currentTimeMillis

    def stats: String = {
      val out: Long = outSize.getAndSet(0)
      val outPac: Long = outPackets.getAndSet(0)
      val in: Long = inSize.getAndSet(0)
      val inPac: Long = inPackets.getAndSet(0)
      outSizeTot += out
      inSizeTot += in
      val curTime: Long = System.currentTimeMillis
      val d: Long = curTime - lastTime
      val outSpeed: Long = out * 1000 / d
      val inSpeed: Long = in * 1000 / d
      lastTime = curTime
      return ""
    }

    @throws[Exception]
    override def channelRead(ctx: ChannelHandlerContext, msg: Any) {
      inPackets.incrementAndGet
      if (msg.isInstanceOf[ByteBuf]) inSize.addAndGet(msg.asInstanceOf[ByteBuf].readableBytes)
      else if (msg.isInstanceOf[DatagramPacket]) inSize.addAndGet(msg.asInstanceOf[DatagramPacket].content.readableBytes)
      super.channelRead(ctx, msg)
    }

    @throws[Exception]
    override def write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
      outPackets.incrementAndGet
      if (msg.isInstanceOf[ByteBuf]) outSize.addAndGet(msg.asInstanceOf[ByteBuf].readableBytes)
      else if (msg.isInstanceOf[DatagramPacket]) outSize.addAndGet(msg.asInstanceOf[DatagramPacket].content.readableBytes)
      super.write(ctx, msg, promise)
    }
  }

}
