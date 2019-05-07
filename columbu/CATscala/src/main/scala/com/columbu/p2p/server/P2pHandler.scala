package com.galileo.p2p.server

import com.galileo.p2p.message.StaticMessages.PING_MESSAGE
import com.galileo.p2p.message.StaticMessages.PONG_MESSAGE
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.discover.node.statistics.MessageStatistics
import com.galileo.p2p.message.DisconnectMessage
import com.galileo.p2p.message.P2pMessage
import com.galileo.protos.protos.Protocol.ReasonCode
import com.galileo.core.enumeration.MessageTypes._
import org.slf4j.LoggerFactory

@Component
@Scope("prototype")
class P2pHandler extends SimpleChannelInboundHandler[P2pMessage]{

  private val logger = LoggerFactory.getLogger(getClass())

  private val pingTimer: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor(r => new Thread(r, "P2pPingTimer"))
  private var msgQueue: MessageQueue = null
  private var channel: Channel = null
  private var pingTask: ScheduledFuture[_] = null
  private var hasPing: Boolean = false
  private var sendPingTime: Long = 0L
  private var ctx: ChannelHandlerContext = null

  @throws[Exception]
  override def handlerAdded(ctx: ChannelHandlerContext) {
    this.ctx = ctx
    pingTask = pingTimer.scheduleAtFixedRate(() => {
      if (!hasPing) {
        sendPingTime = System.currentTimeMillis()
        hasPing = msgQueue.sendMessage(PING_MESSAGE)
      }
    }, 10, 10, TimeUnit.SECONDS)
  }

  def channelRead0(ctx: ChannelHandlerContext, msg: P2pMessage) {
    msgQueue.receivedMessage(msg)
    val messageStatistics: MessageStatistics = channel.getNodeStatistics.messageStatistics
    msg.getType match {
      case P2P_PING =>
        val count: Int = messageStatistics.p2pInPing.getCount(10)
        if (count > 3) {
          logger.warn("TCP attack found: {} with ping count({})", ctx.channel.remoteAddress, count)
          channel.disconnect(ReasonCode.BAD_PROTOCOL)
          return
        }
        msgQueue.sendMessage(PONG_MESSAGE)
      case P2P_PONG =>
        if (messageStatistics.p2pInPong.getTotalCount > messageStatistics.p2pOutPing.getTotalCount) {
          logger.warn("TCP attack found: "+ctx.channel.remoteAddress+" with ping count("+messageStatistics.p2pOutPing.getTotalCount+"), pong count("+messageStatistics.p2pInPong.getTotalCount+")")
          channel.disconnect(ReasonCode.BAD_PROTOCOL)
          return
        }
        hasPing = false
        channel.getNodeStatistics.lastPongReplyTime.set(System.currentTimeMillis)
        channel.getPeerStats.pong(sendPingTime)
      case P2P_DISCONNECT =>
        channel.getNodeStatistics.nodeDisconnectedRemote(msg.asInstanceOf[DisconnectMessage].getReasonCode)
        channel.close()
      case _ =>
        channel.close()
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
    channel.processException(cause)
  }

  def setMsgQueue(msgQueue: MessageQueue) {
    this.msgQueue = msgQueue
  }

  def setChannel(channel: Channel) {
    this.channel = channel
  }

  def close() {
    if (pingTask != null && !pingTask.isCancelled) {
      pingTask.cancel(false)
    }
  }
}
