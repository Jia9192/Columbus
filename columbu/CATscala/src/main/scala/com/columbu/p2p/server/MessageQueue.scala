package com.galileo.p2p.server

import io.netty.channel.{ChannelFuture, ChannelFutureListener, ChannelHandlerContext}
import java.util
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.message.Message
import com.galileo.p2p.message.PingMessage
import com.galileo.protos.protos.Protocol.ReasonCode
import scala.util.control.Breaks

@Component
@Scope("prototype")
class MessageQueue {

  private val logger: Logger = LoggerFactory.getLogger(getClass())

  private var sendMsgFlag: Boolean = false
  private var sendTime: Long = 0L
  private var sendMsgThread: Thread = null
  private var channel: Channel = null
  private var ctx: ChannelHandlerContext = null
  private val requestQueue: util.Queue[MessageRoundtrip] = new ConcurrentLinkedQueue[MessageRoundtrip]
  private val msgQueue: BlockingQueue[Message] = new LinkedBlockingQueue[Message]
  private val sendTimer: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor(r => new Thread(r, "sendTimer"))
  private var sendTask: ScheduledFuture[_] = null

  def activate(ctx: ChannelHandlerContext) {
    this.ctx = ctx
    sendMsgFlag = true
    sendTask = sendTimer.scheduleAtFixedRate(() => {
      try{
        if (sendMsgFlag) send()
      } catch {
        case e: Exception => {
          logger.error("Unhandled exception", e)
        }
      }
    }, 10, 10, TimeUnit.MILLISECONDS)
    sendMsgThread = new Thread(() => {
      val loop = new Breaks()
      while (sendMsgFlag)
        try{
          loop.breakable{
            if (msgQueue.isEmpty) {
              Thread.sleep(10)
              loop.break()
            }
          }
          val msg: Message = msgQueue.take
          ctx.writeAndFlush(msg.getSendData).addListener(new ChannelFutureListener() {
            override def operationComplete(future: ChannelFuture){
              if (!future.isSuccess()) {
                logger.error("Fail send to "+ctx.channel().remoteAddress()+", "+msg)
              }
            }
          })
        }catch {
          case e: Exception => {
            logger.error("Fail send to "+ctx.channel.remoteAddress+", error info: +"+e.getMessage)
          }
        }
    })
    sendMsgThread.setName("sendMsgThread-" + ctx.channel.remoteAddress)
    sendMsgThread.start()
  }

  def setChannel(channel: Channel) {
    this.channel = channel
  }

  def sendMessage(msg: Message): Boolean = {
    if (msg.isInstanceOf[PingMessage] && sendTime > System.currentTimeMillis - 10000) {
      return false
    }
    logger.info("Send to "+ctx.channel.remoteAddress+",  "+msg)
    channel.getNodeStatistics.messageStatistics.addTcpOutMessage(msg)
    sendTime = System.currentTimeMillis
    if (msg.getAnswerMessage != null) {
      requestQueue.add(new MessageRoundtrip(msg))
    }
    else {
      msgQueue.offer(msg)
    }
    return true
  }

  def receivedMessage(msg: Message) {
    logger.info("Receive from "+ctx.channel.remoteAddress+", " + msg)
    channel.getNodeStatistics.messageStatistics.addTcpInMessage(msg)
    val messageRoundtrip: MessageRoundtrip = requestQueue.peek
    if (messageRoundtrip != null && (messageRoundtrip.getMsg.getAnswerMessage eq msg.getClass)) {
      requestQueue.remove
    }
  }

  def close() {
    sendMsgFlag = false
    if (sendTask != null && !sendTask.isCancelled) {
      sendTask.cancel(false)
      sendTask = null
    }
    if (sendMsgThread != null) {
      try {
        sendMsgThread.join(20)
        sendMsgThread = null
      }
      catch {
        case e: Exception => {
          logger.warn("Join send thread failed, peer {}", ctx.channel.remoteAddress)
        }
      }
    }
  }

  private def send() {
    val messageRoundtrip: MessageRoundtrip = requestQueue.peek
    if (!sendMsgFlag || messageRoundtrip == null) {
      return
    }
    if (messageRoundtrip.getRetryTimes > 0 && !messageRoundtrip.hasToRetry) {
      return
    }
    if (messageRoundtrip.getRetryTimes > 0) {
      channel.getNodeStatistics.nodeDisconnectedLocal(ReasonCode.PING_TIMEOUT)
      logger.warn("Wait "+messageRoundtrip.getMsg.getAnswerMessage+" timeout. close channel " + ctx.channel.remoteAddress)
      channel.close()
      return
    }
    val msg: Message = messageRoundtrip.getMsg
    ctx.writeAndFlush(msg.getSendData).addListener(new ChannelFutureListener() {
      override def operationComplete(future: ChannelFuture){
        if (!future.isSuccess()) {
          logger.error("Fail send to "+ctx.channel().remoteAddress()+", "+msg)
        }
      }
    })
    messageRoundtrip.incRetryTimes()
    messageRoundtrip.saveTime()
  }

}
