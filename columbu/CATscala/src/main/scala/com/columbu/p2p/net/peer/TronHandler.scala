package com.galileo.p2p.net.peer

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.server.Channel
import com.galileo.p2p.server.MessageQueue
import com.galileo.p2p.net.message.TronMessage

@Component
@Scope("prototype")
class TronHandler extends SimpleChannelInboundHandler[TronMessage]{

  protected var peer: PeerConnection = null
  private var msgQueue: MessageQueue = null
  var peerDel: PeerConnectionDelegate = null

  def setPeerDel(peerDel: PeerConnectionDelegate) {
    this.peerDel = peerDel
  }

  def channelRead0(ctx: ChannelHandlerContext, msg: TronMessage) {
    msgQueue.receivedMessage(msg)
    peerDel.onMessage(peer, msg)
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
    peer.processException(cause)
  }

  def setMsgQueue(msgQueue: MessageQueue) {
    this.msgQueue = msgQueue
  }

  def setChannel(channel: Channel) {
    this.peer = channel.asInstanceOf[PeerConnection]
  }

}
