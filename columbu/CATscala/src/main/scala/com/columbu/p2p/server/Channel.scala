package com.galileo.p2p.server

import io.netty.channel.{ChannelFuture, ChannelFutureListener, ChannelHandlerContext, ChannelPipeline}
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import io.netty.handler.timeout.ReadTimeoutException
import io.netty.handler.timeout.ReadTimeoutHandler
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.util.concurrent.TimeUnit

import com.galileo.core.enumeration.TronState
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import com.galileo.p2p.discover.node.Node
import com.galileo.p2p.discover.node.NodeManager
import com.galileo.p2p.discover.node.statistics.NodeStatistics
import com.galileo.p2p.message.DisconnectMessage
import com.galileo.p2p.message.HelloMessage
import com.galileo.p2p.message.MessageCodec
import com.galileo.p2p.message.StaticMessages
import com.galileo.db.levelDB.store.ByteArrayWrapper
import com.galileo.core.exception.P2pException
import com.galileo.p2p.net.peer.PeerConnectionDelegate
import com.galileo.p2p.net.peer.TronHandler
import com.galileo.protos.protos.Protocol.ReasonCode

import scala.beans.BeanProperty

@Component
@Scope("prototype")
class Channel {

  private val logger = LoggerFactory.getLogger(getClass)
  @Autowired
  protected var msgQueue: MessageQueue = null
  @Autowired
  private val messageCodec: MessageCodec = null
  @Autowired
  private val nodeManager: NodeManager = null
//  @Autowired
//  private val staticMessages: StaticMessages = null
  @Autowired
  private val stats: WireTrafficStats = null
  @Autowired
  private val handshakeHandler: HandshakeHandler = null
  @Autowired
  private val p2pHandler: P2pHandler = null
  @Autowired
  private val tronHandler: TronHandler = null
  private var channelManager: ChannelManager = null
  private var ctx: ChannelHandlerContext = null
  private var inetSocketAddress: InetSocketAddress = null
  private var node: Node = null
  private var startTime = 0L
  private var peerDel: PeerConnectionDelegate = null
  private var tronState = TronState.INIT
  protected var nodeStatistics: NodeStatistics = null
  @BeanProperty
  var isActive = false
  @BeanProperty
  var isDisconnect = false
  private var remoteId: String = null
  private val peerStats: PeerStatistics = new PeerStatistics
  @BeanProperty
  var isTrustPeer = false

  def init(pipeline: ChannelPipeline, remoteId: String, discoveryMode: Boolean, channelManager: ChannelManager, peerDel: PeerConnectionDelegate) {
    this.channelManager = channelManager
    this.remoteId = remoteId
    if(remoteId != null && !remoteId.isEmpty){
      this.isActive = true
    }
    startTime = System.currentTimeMillis
    //TODO: use config here
    pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(60, TimeUnit.SECONDS))
    pipeline.addLast(stats.tcp)
    pipeline.addLast("protoPender", new ProtobufVarint32LengthFieldPrepender)
    pipeline.addLast("lengthDecode", new TrxProtobufVarint32FrameDecoder(this))
    //handshake first
    pipeline.addLast("handshakeHandler", handshakeHandler)
    this.peerDel = peerDel
    messageCodec.setChannel(this)
    msgQueue.setChannel(this)
    handshakeHandler.setChannel(this, remoteId)
    p2pHandler.setChannel(this)
    tronHandler.setChannel(this)
    p2pHandler.setMsgQueue(msgQueue)
    tronHandler.setMsgQueue(msgQueue)
    tronHandler.setPeerDel(peerDel)
  }

  def publicHandshakeFinished(ctx: ChannelHandlerContext, msg: HelloMessage) {
    this.isTrustPeer = channelManager.getTrustPeers.containsKey(getInetAddress)
    ctx.pipeline.remove(handshakeHandler)
    msgQueue.activate(ctx)
    ctx.pipeline.addLast("messageCodec", messageCodec)
    ctx.pipeline.addLast("p2p", p2pHandler)
    ctx.pipeline.addLast("data", tronHandler)
    setStartTime(msg.getTimestamp)
    setTronState(TronState.HANDSHAKE_FINISHED)
    getNodeStatistics.p2pHandShake.add()
    logger.info("Finish handshake with {}.", ctx.channel.remoteAddress)
  }

  /**
    * Set node and register it in NodeManager if it is not registered yet.
    */
  def initNode(nodeId: Array[Byte], remotePort: Int) {
    node = new Node(nodeId, inetSocketAddress.getHostString, remotePort)
    nodeStatistics = nodeManager.getNodeStatistics(node)
    nodeManager.getNodeHandler(node).setNode(node)
  }

  def disconnect(reason: ReasonCode) {
    this.isDisconnect = true
    channelManager.processDisconnect(this, reason)
    val msg = new DisconnectMessage(reason)
    logger.info("Send to "+ctx.channel.remoteAddress+", "+ msg)
    getNodeStatistics.nodeDisconnectedLocal(reason)
    ctx.writeAndFlush(msg.getSendData).addListener(new ChannelFutureListener(){
      override def operationComplete(future: ChannelFuture){
        close()
      }
    })
  }

  def processException(throwable: Throwable) {
    var baseThrowable = throwable
    while (baseThrowable.getCause != null) baseThrowable = baseThrowable.getCause
    val errMsg = throwable.getMessage
    val address = ctx.channel.remoteAddress
    if (throwable.isInstanceOf[ReadTimeoutException]) logger.error("Read timeout, " + address)
    else if (baseThrowable.isInstanceOf[P2pException]) logger.error("type: "+baseThrowable.asInstanceOf[P2pException].getType+", info: "+baseThrowable.getMessage+", "+address)
    else if (errMsg != null && errMsg.contains("Connection reset by peer")) logger.error(errMsg+", "+address)
    else logger.error("exception caught, " + address, throwable)
    close()
  }

  def close() {
    this.isDisconnect = true
    p2pHandler.close()
    msgQueue.close()
    ctx.close
  }

  def getPeerStats: PeerStatistics = peerStats

  def getNode: Node = node

  def getNodeId: Array[Byte] = if (node == null) null
    else node.getId

  def getNodeIdWrapper: ByteArrayWrapper = if (node == null) null
    else new ByteArrayWrapper(node.getId)

  def getPeerId: String = if (node == null) "<null>"
    else node.getHexId

  def setChannelHandlerContext(ctx: ChannelHandlerContext) {
    this.ctx = ctx
    this.inetSocketAddress = if (ctx == null) null
    else ctx.channel.remoteAddress.asInstanceOf[InetSocketAddress]
  }

  def getChannelHandlerContext: ChannelHandlerContext = this.ctx

  def getInetAddress: InetAddress = if (ctx == null) null
    else ctx.channel.remoteAddress.asInstanceOf[InetSocketAddress].getAddress

  def getNodeStatistics: NodeStatistics = nodeStatistics

  def setStartTime(startTime: Long) {
    this.startTime = startTime
  }

  def getStartTime: Long = startTime

  def setTronState(tronState: TronState) {
    this.tronState = tronState
  }

  def getTronState: TronState = tronState

  def isProtocolsInitialized: Boolean = tronState.ordinal > TronState.INIT.ordinal

  override def equals(o: Any): Boolean = {
    if (this == o){
      return true
    }
    if (o == null || (getClass ne o.getClass)){
      return false
    }
    val channel = o.asInstanceOf[Channel]
    if (inetSocketAddress != null){
      if(!inetSocketAddress.equals(channel.inetSocketAddress)){
        return false
      }
    } else {
      if(channel.inetSocketAddress != null){
        return false
      }
    }
    if (node != null){
      if(!node.equals(channel.node)){
          return false;
        }
      }else{
      if(channel.node != null){
          return false;
        }
    }
    return this == channel
  }

  override def hashCode: Int = {
    var result = if (inetSocketAddress != null) inetSocketAddress.hashCode
    else 0
    result = 31 * result + (if (node != null) node.hashCode
    else 0)
    return result
  }

  override def toString: String = {
    return String.format("%s | %s", inetSocketAddress, getPeerId)
  }

}
