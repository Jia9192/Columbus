package com.galileo.p2p.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.DefaultMessageSizeEstimator
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LoggingHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import com.galileo.core.config.args.Args
import com.galileo.p2p.net.node.NodeImpl

@Component
class PeerServer {

  private val logger: Logger = LoggerFactory.getLogger(getClass())

  private val args: Args = Args.getInstance
  private var ctx: ApplicationContext = null
  var tronChannelInitializer: TronChannelInitializer = null
  private var listening: Boolean = false
  @Autowired
  private val p2pNode: NodeImpl = null
  var bossGroup: EventLoopGroup = null
  var workerGroup: EventLoopGroup = null
  var channelFuture: ChannelFuture = null

  def this(args: Args, ctx: ApplicationContext) {
    this()
    this.ctx = ctx
  }

  def start(port: Int) {
    bossGroup = new NioEventLoopGroup(1)
    workerGroup = new NioEventLoopGroup(args.getTcpNettyWorkThreadNum)
    tronChannelInitializer = ctx.getBean(classOf[TronChannelInitializer], "")
    tronChannelInitializer.setNodeImpl(p2pNode)
    try{
      val b: ServerBootstrap = new ServerBootstrap
      b.group(bossGroup, workerGroup)
      b.channel(classOf[NioServerSocketChannel])
      b.option(ChannelOption.SO_KEEPALIVE, new java.lang.Boolean(true))
      b.option(ChannelOption.MESSAGE_SIZE_ESTIMATOR, DefaultMessageSizeEstimator.DEFAULT)
      b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, new java.lang.Integer(this.args.getNodeConnectionTimeout))
      b.handler(new LoggingHandler)
      b.childHandler(tronChannelInitializer)
      // Start the client.
      logger.info("TCP listener started, bind port " + port)
      channelFuture = b.bind(port).sync
      listening = true
      // Wait until the connection is closed.
      channelFuture.channel.closeFuture.sync
      logger.info("TCP listener is closed")
    }catch {
      case e: Exception => {
        logger.error("Start TCP server failed.", e)
      }
    } finally {
      workerGroup.shutdownGracefully
      bossGroup.shutdownGracefully
      listening = false
    }
  }

  def close() {
    if (listening && channelFuture != null && channelFuture.channel.isOpen){
      try{
        logger.info("Closing TCP server...")
        channelFuture.channel.close.sync
      }catch {
        case e: Exception => {
          logger.warn("Closing TCP server failed.", e)
        }
      }
    }
  }

}
