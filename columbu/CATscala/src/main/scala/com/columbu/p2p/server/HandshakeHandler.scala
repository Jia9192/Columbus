package com.galileo.p2p.server

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.net.InetSocketAddress
import java.util
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.spongycastle.util.encoders.Hex
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.discover.node.NodeManager
import com.galileo.p2p.message.DisconnectMessage
import com.galileo.p2p.message.HelloMessage
import com.galileo.p2p.message.P2pMessage
import com.galileo.p2p.message.P2pMessageFactory
import com.galileo.core.config.args.Args
import com.galileo.db.levelDB.store.Manager
import com.galileo.p2p.net.peer.PeerConnection
import com.galileo.protos.protos.Protocol.ReasonCode
import com.galileo.core.enumeration.MessageTypes._

@Component
@Scope("prototype")
class HandshakeHandler extends ByteToMessageDecoder{

  protected val logger: Logger = LoggerFactory.getLogger(getClass())

  private var remoteId: Array[Byte] = null
  protected var channel: Channel = null
  @Autowired
  protected var nodeManager: NodeManager = null
  @Autowired
  protected var channelManager: ChannelManager = null
  @Autowired
  protected var manager: Manager = null
  private val messageFactory = new P2pMessageFactory
  @Autowired
  private val syncPool: SyncPool = null

  @throws[Exception]
  override def channelActive(ctx: ChannelHandlerContext) {
    logger.info("channel active, "+ctx.channel.remoteAddress)
    channel.setChannelHandlerContext(ctx)
    if (remoteId.length == 64) {
      channel.initNode(remoteId, ctx.channel.remoteAddress.asInstanceOf[InetSocketAddress].getPort)
      sendHelloMsg(ctx, System.currentTimeMillis)
    }
  }

  @throws[Exception]
  protected def decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: util.List[AnyRef]) {
    val encoded = new Array[Byte](buffer.readableBytes)
    buffer.readBytes(encoded)
    val msg = messageFactory.create(encoded)
    logger.info("Handshake Receive from "+ctx.channel.remoteAddress+", "+msg)
    msg.getType match {
      case P2P_HELLO =>
        handleHelloMsg(ctx, msg.asInstanceOf[HelloMessage])
      case P2P_DISCONNECT =>
        if (channel.getNodeStatistics != null) channel.getNodeStatistics.nodeDisconnectedRemote(msg.asInstanceOf[DisconnectMessage].getReasonCode)
        channel.close()
      case _ =>
        channel.close()
    }
  }

  @throws[Exception]
  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
    channel.processException(cause)
  }

  def setChannel(channel: Channel, remoteId: String) {
    this.channel = channel
    this.remoteId = Hex.decode(remoteId)
  }

  protected def sendHelloMsg(ctx: ChannelHandlerContext, time: Long) {
    val message = new HelloMessage(nodeManager.getPublicHomeNode, time, manager.getGenesisBlockId, manager.getSolidBlockId, manager.getHeadBlockId)
    ctx.writeAndFlush(message.getSendData)
    channel.getNodeStatistics.messageStatistics.addTcpOutMessage(message)
    logger.info("Handshake Send to "+ctx.channel.remoteAddress+", "+message)
  }

  private def handleHelloMsg(ctx: ChannelHandlerContext, msg: HelloMessage) {
    channel.initNode(msg.getFrom.getId, msg.getFrom.getPort)
    if (remoteId.length != 64) {
      val address = ctx.channel.remoteAddress.asInstanceOf[InetSocketAddress].getAddress
      if (!channelManager.getTrustPeers.keySet.contains(address) && !syncPool.isCanConnect) {
        channel.disconnect(ReasonCode.TOO_MANY_PEERS)
        return
      }
    }
    if (msg.getVersion != Args.getInstance.getNodeP2pVersion) {
      logger.info("Peer "+ctx.channel.remoteAddress+" different p2p version, peer->"+msg.getVersion+", me->"+Args.getInstance.getNodeP2pVersion)
      channel.disconnect(ReasonCode.INCOMPATIBLE_VERSION)
      return
    }
    if (!util.Arrays.equals(manager.getGenesisBlockId.getBytes, msg.getGenesisBlockId.getBytes)) {
      logger.info("Peer "+ctx.channel.remoteAddress+" different genesis block, peer->"+msg.getGenesisBlockId.getString+", me->"+manager.getGenesisBlockId.getString)
      channel.disconnect(ReasonCode.INCOMPATIBLE_CHAIN)
      return
    }
    if (manager.getSolidBlockId.getNum >= msg.getSolidBlockId.getNum && !manager.containBlockInMainChain(msg.getSolidBlockId)) {
      logger.info("Peer "+ctx.channel.remoteAddress+" different solid block, peer->"+msg.getSolidBlockId.getString+", me->"+manager.getSolidBlockId.getString)
      channel.disconnect(ReasonCode.FORKED)
      return
    }
    channel.asInstanceOf[PeerConnection].setHelloMessage(msg)
    channel.getNodeStatistics.messageStatistics.addTcpInMessage(msg)
    channel.publicHandshakeFinished(ctx, msg)
    if (!channelManager.processPeer(channel)) return
    if (remoteId.length != 64) sendHelloMsg(ctx, msg.getTimestamp)
    syncPool.onConnect(channel)
  }

}
