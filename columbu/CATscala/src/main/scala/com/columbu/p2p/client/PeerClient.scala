package com.galileo.p2p.client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelOption
import io.netty.channel.DefaultMessageSizeEstimator
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import com.galileo.p2p.discover.node.NodeHandler
import com.galileo.p2p.server.TronChannelInitializer
import com.galileo.core.config.args.Args
import com.galileo.p2p.net.node.NodeImpl
import com.galileo.protos.protos.Protocol.ReasonCode

import scala.collection.JavaConversions._

@Component
class PeerClient {

  private val logger = LoggerFactory.getLogger(getClass)

  @Autowired
  private val ctx: ApplicationContext = null
  @Autowired
  @Lazy
  private val node: NodeImpl = null

  private var workerGroup: EventLoopGroup = null

  {
    this.workerGroup = new NioEventLoopGroup(0, new ThreadFactory() {
      val cnt = new AtomicInteger(0)
      def newThread(r: Runnable) = new Thread(r, "TronJClientWorker-" + cnt.getAndIncrement)
    })
  }

  def connect(host: String, port: Int, remoteId: String) {
    try{
      val f = connectAsync(host, port, remoteId, false)
      f.sync.channel.closeFuture.sync
    }catch {
      case e: Exception => {
        logger.info("PeerClient: Can't connect to " + host + ":" + port + " (" + e.getMessage + ")")
      }
    }
  }

  def connectAsync(nodeHandler: NodeHandler, discoveryMode: Boolean): ChannelFuture = {
    val node = nodeHandler.getNode
    connectAsync(node.getHost, node.getPort, node.getHexId, discoveryMode).addListener(new ChannelFutureListener() {
      @throws[Exception]
      def operationComplete(future: ChannelFuture) {
        if (!future.isSuccess) {
          logger.error("connect to "+node.getHost+":"+node.getPort+" fail,cause:"+future.cause.getMessage)
          nodeHandler.getNodeStatistics.nodeDisconnectedLocal(ReasonCode.CONNECT_FAIL)
          nodeHandler.getNodeStatistics.notifyDisconnect()
          future.channel.close
        }
      }
    })
  }

  def connectAsync(host: String, port: Int, remoteId: String, discoveryMode: Boolean): ChannelFuture = {
    logger.info("connect peer "+host+" "+port+" " + remoteId)
    val tronChannelInitializer = ctx.getBean(classOf[TronChannelInitializer], remoteId)
    tronChannelInitializer.setPeerDiscoveryMode(discoveryMode)
    tronChannelInitializer.setNodeImpl(node)
    val b = new Bootstrap
    b.group(this.workerGroup)
    b.channel(classOf[NioSocketChannel])
    b.option(ChannelOption.SO_KEEPALIVE,new java.lang.Boolean(true))
    b.option(ChannelOption.MESSAGE_SIZE_ESTIMATOR, DefaultMessageSizeEstimator.DEFAULT)
    b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, new java.lang.Integer(Args.getInstance.getNodeConnectionTimeout))
    b.remoteAddress(host, port)
    b.handler(tronChannelInitializer)
    // Start the client.
    b.connect
  }

  def close() {
    this.workerGroup.shutdownGracefully
    this.workerGroup.terminationFuture.syncUninterruptibly
  }

}
