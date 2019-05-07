package com.galileo.p2p.discover

import com.galileo.p2p.discover.node.NodeManager
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import java.util.concurrent.TimeUnit

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.galileo.p2p.udp.handler.MessageHandler
import com.galileo.p2p.udp.handler.PacketDecoder
import com.galileo.p2p.server.WireTrafficStats
import com.galileo.core.config.args.Args

import scala.util.control.Breaks

@Component
class DiscoverServer {

  private val logger = LoggerFactory.getLogger(getClass())

  @Autowired
  private var nodeManager: NodeManager = null
  @Autowired
  private val stats: WireTrafficStats = null

  private val args: Args = Args.getInstance
  private val port: Int = args.getNodeListenPort
  private var channel: Channel = null
  private var discoveryExecutor: DiscoveryExecutor = null
  private var shutdown = false

  def this(nodeManager: NodeManager) {
    this()
    this.nodeManager = nodeManager
    if (args.isNodeDiscoveryEnable){
      if (port == 0){
        logger.error("Discovery can't be started while listen port == 0")
      } else  {
        new Thread(() => {
          try{
            start()
          }catch {
            case e: Exception => {
              logger.debug(e.getMessage, e)
              throw new RuntimeException(e)
            }
          }
        }, "DiscoverServer").start()
      }
    }
  }

  @throws[Exception]
  def start() {
    val group = new NioEventLoopGroup(args.getUdpNettyWorkThreadNum)
    try{
      discoveryExecutor = new DiscoveryExecutor(nodeManager)
      discoveryExecutor.start()
      val loop = new Breaks()
      loop.breakable {
        while (!shutdown) {
          val b = new Bootstrap
          b.group(group).channel(classOf[NioDatagramChannel]).handler(new ChannelInitializer[NioDatagramChannel]() {
            @throws[Exception]
            def initChannel(ch: NioDatagramChannel) {
              ch.pipeline.addLast(stats.udp)
              ch.pipeline.addLast(new ProtobufVarint32LengthFieldPrepender)
              ch.pipeline.addLast(new ProtobufVarint32FrameDecoder)
              ch.pipeline.addLast(new PacketDecoder)
              val messageHandler = new MessageHandler(ch, nodeManager)
              nodeManager.setMessageSender(messageHandler)
              ch.pipeline.addLast(messageHandler)
            }
          })
          channel = b.bind(port).sync.channel
          logger.info("Discovery server started, bind port " + port)
          channel.closeFuture.sync
          if (shutdown) {
            logger.info("Shutdown discovery server")
            loop.break()
          }
          logger.warn(" Restart discovery server after 5 sec pause...")
          Thread.sleep(5000)
        }
      }
    }catch {
      case e: Exception => {
        logger.error("Start discovery server with port "+port+" failed.", e)
      }
    } finally {
      group.shutdownGracefully().sync()
    }
  }

  def close() {
    logger.info("Closing discovery server...")
    shutdown = true
    if (channel != null){
      try{
        channel.close.await(10, TimeUnit.SECONDS)
      } catch {
        case e: Exception => {
          logger.info("Closing discovery server failed.", e)
        }
      }
    }
    if (discoveryExecutor != null){
      try{
        discoveryExecutor.close()
      }catch {
        case e: Exception => {
          logger.info("Closing discovery executor failed.", e)
        }
      }
    }
  }

}
