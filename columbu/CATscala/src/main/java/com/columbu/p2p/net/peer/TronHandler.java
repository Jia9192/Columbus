package com.galileo.p2p.net.peer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.galileo.core.overlay.server.Channel;
import com.galileo.core.overlay.server.MessageQueue;
import com.galileo.p2p.net.message.TronMessage;

@Component
@Scope("prototype")
public class TronHandler extends SimpleChannelInboundHandler<TronMessage> {

  protected PeerConnection peer;

  private MessageQueue msgQueue = null;

  public PeerConnectionDelegate peerDel;

  public void setPeerDel(PeerConnectionDelegate peerDel) {
    this.peerDel = peerDel;
  }

  @Override
  public void channelRead0(final ChannelHandlerContext ctx, TronMessage msg) {
    msgQueue.receivedMessage(msg);
    peerDel.onMessage(peer, msg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    peer.processException(cause);
  }

  public void setMsgQueue(MessageQueue msgQueue) {
    this.msgQueue = msgQueue;
  }

  public void setChannel(Channel channel) {
    this.peer = (PeerConnection) channel;
  }

}