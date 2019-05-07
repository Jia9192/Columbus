package com.galileo.p2p.server

import io.netty.channel._
import io.netty.channel.socket.nio.NioSocketChannel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.net.node.NodeImpl
import com.galileo.p2p.net.peer.PeerConnection

@Component
@Scope("prototype")
class TronChannelInitializer extends ChannelInitializer[NioSocketChannel]{

  private val logger: Logger = LoggerFactory.getLogger(getClass())

  @Autowired
  private val ctx: ApplicationContext = null
  @Autowired
  private[server] val channelManager: ChannelManager = null
  private var p2pNode: NodeImpl = null
  private var remoteId: String = null
  private var peerDiscoveryMode: Boolean = false

  def this(remoteId: String) {
    this()
    this.remoteId = remoteId
  }

  @throws[Exception]
  def initChannel(ch: NioSocketChannel) {
    try{
      val channel: Channel = ctx.getBean(classOf[PeerConnection])
      channel.init(ch.pipeline, remoteId, peerDiscoveryMode, channelManager, p2pNode)
      // limit the size of receiving buffer to 1024
      ch.config.setRecvByteBufAllocator(new FixedRecvByteBufAllocator(256 * 1024))
      ch.config.setOption(ChannelOption.SO_RCVBUF, new java.lang.Integer(256 * 1024))
      ch.config.setOption(ChannelOption.SO_BACKLOG, new java.lang.Integer(1024))
      // be aware of channel closing
      ch.closeFuture.addListener(new ChannelFutureListener(){
        override def operationComplete(future: ChannelFuture){
          logger.info("Close channel:" + channel);
          if (!peerDiscoveryMode){
            channelManager.notifyDisconnect(channel)
          }
        }
      })
    }catch {
      case e: Exception => {
        logger.error("Unexpected error: ", e)
      }
    }
  }

  private def isInbound: Boolean = {
    return remoteId == null || remoteId.isEmpty
  }

  def setPeerDiscoveryMode(peerDiscoveryMode: Boolean) {
    this.peerDiscoveryMode = peerDiscoveryMode
  }

  def setNodeImpl(p2pNode: NodeImpl) {
    this.p2pNode = p2pNode
  }
}
