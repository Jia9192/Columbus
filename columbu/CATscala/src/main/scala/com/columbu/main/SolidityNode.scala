package com.galileo.main

import com.google.common.collect.Maps
import java.util.Map
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicLong
import org.springframework.context.ApplicationContext
import org.springframework.util.StringUtils
import com.galileo.core.appManage.Application
import com.galileo.core.appManage.ApplicationFactory
import com.galileo.core.appManage.TronApplicationContext
import com.galileo.p2p.client.DatabaseGrpcClient
import com.galileo.p2p.discover.DiscoverServer
import com.galileo.p2p.discover.node.NodeManager
import com.galileo.p2p.server.ChannelManager
import com.galileo.core.Constant
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.TransactionInfoCapsule
import com.galileo.core.config.DefaultConfig
import com.galileo.core.config.args.Args
import com.galileo.db.levelDB.store.Manager
import com.galileo.core.service.RpcApiService
import com.galileo.core.service.http.solidity.SolidityNodeHttpApiService
import com.galileo.protos.protos.Protocol.Block
import org.slf4j.LoggerFactory
import util.control.Breaks._

class SolidityNode {

  private val logger = LoggerFactory.getLogger(getClass())

  private var dbManager: Manager = null
  private var cfgArgs: Args = null
  private var databaseGrpcClient: DatabaseGrpcClient = null
  private val ID: AtomicLong = new AtomicLong
  private val blockMap: Map[Long,Block] = Maps.newConcurrentMap()
  private val blockQueue: LinkedBlockingDeque[Block] = new LinkedBlockingDeque[Block](10000)
  private val blockBakQueue: LinkedBlockingDeque[Block] = new LinkedBlockingDeque[Block](10000)
  private var remoteLastSolidityBlockNum: Long = 0
  private var lastSolidityBlockNum: Long = 0L
  private val startTime: Long = System.currentTimeMillis
  private var syncFlag: Boolean = true
  private val flag: Boolean = true

  def this(dbManager: Manager, cfgArgs: Args) {
    this()
    this.dbManager = dbManager
    this.cfgArgs = cfgArgs
    lastSolidityBlockNum = dbManager.getDynamicPropertiesStore.getLatestSolidifiedBlockNum
    ID.set(lastSolidityBlockNum)
    databaseGrpcClient = new DatabaseGrpcClient(cfgArgs.getTrustNodeAddr)
    remoteLastSolidityBlockNum = getLastSolidityBlockNum
  }

  private def start() {
    try{
      var i: Int = 0
      while (i < 50) {
        {
          new Thread(){
             override def run(){
              getSyncBlock()
            }
          }.start()
        }
        {
          i += 1;
          i - 1
        }
      }
      new Thread(){
        override def run(){
          getAdvBlock()
        }
      }.start()
      new Thread(){
        override def run(){
          pushBlock()
        }
      }.start()
      new Thread(){
        override def run(){
          processBlock()
        }
      }.start()
      new Thread(){
        override def run(){
          processTrx()
        }
      }.start()
      logger.info("Success to start solid node, lastSolidityBlockNum: "+lastSolidityBlockNum+", ID: "+ID.get+", remoteLastSolidityBlockNum: "+remoteLastSolidityBlockNum+".")

    }catch {
      case e: Exception => {
        logger.error("Failed to start solid node, address: "+cfgArgs.getTrustNodeAddr+".")
        System.exit(0)
      }
    }
  }

  private def getSyncBlock() {
    var blockNum: Long = getNextSyncBlockId
    while (syncFlag)
      try
        breakable{
          if (blockNum == 0) break()
          if (blockMap.size > 10000) {
            sleep(1000)
            break()
          }
          val block: Block = getBlockByNum(blockNum)
          blockMap.put(blockNum, block)
          logger.info("Success to get sync block: "+blockNum+".")
          blockNum = getNextSyncBlockId
        }
      catch {
        case e: Exception => {
          logger.error("Failed to get sync block "+blockNum+".")
          sleep(1000)
        }
      }
    logger.warn("Get sync block thread "+Thread.currentThread.getName+" exit.")
  }

  private[main] def getNextSyncBlockId: Long = {
    if (!syncFlag) return 0
    if (ID.get < remoteLastSolidityBlockNum) return ID.incrementAndGet
    val lastNum: Long = getLastSolidityBlockNum
    if (lastNum - remoteLastSolidityBlockNum > 50) {
      remoteLastSolidityBlockNum = lastNum
      return ID.incrementAndGet
    }
    logger.warn("Sync mode switch to adv, ID = "+ID.get+", lastNum = "+lastNum+", remoteLastSolidityBlockNum = "+remoteLastSolidityBlockNum)
    syncFlag = false
    0
  }

  private def getAdvBlock() {
    while (syncFlag) sleep(5000)
    logger.warn("Get adv block thread start.")
    var blockNum: Long = ID.incrementAndGet
    while (flag)
      try
        breakable{
          if (blockNum > remoteLastSolidityBlockNum) {
            sleep(3000)
            remoteLastSolidityBlockNum = getLastSolidityBlockNum
            break()
          }
          val block: Block = getBlockByNum(blockNum)
          blockMap.put(blockNum, block)
          logger.info("Success to get adv block: "+blockNum+".")
          blockNum = ID.incrementAndGet
        }
      catch {
        case e: Exception => {
          logger.error("Failed to get adv block "+blockNum+".")
          sleep(1000)
        }
      }
  }

  @throws[Exception]
  private def getBlockByNum(blockNum: Long): Block = {
    val block: Block = databaseGrpcClient.getBlock(blockNum)
    if (block.getBlockHeader.getRawData.getNumber != blockNum) {
      logger.warn("Get adv block id not the same , "+blockNum+", "+block.getBlockHeader.getRawData.getNumber+".")
      throw new Exception
    }
    block
  }

  private def getLastSolidityBlockNum: Long = {
    var blockNum: Long = 0
    while (true)
      try{
        blockNum = databaseGrpcClient.getDynamicProperties.getLastSolidityBlockNum
        logger.info("Get last remote solid blockNum: "+remoteLastSolidityBlockNum+".")
      }catch {
        case e: Exception => {
          logger.error("Failed to get last solid blockNum: "+remoteLastSolidityBlockNum+".")
          sleep(1000)
        }
      }
    return blockNum
  }

  private def pushBlock() {
    while (flag)
      try
        breakable{
          val block: Block = blockMap.remove(lastSolidityBlockNum + 1)
          if (block == null) {
            sleep(1000)
            break()
          }
          blockQueue.put(block)
          lastSolidityBlockNum += 1
        }
      catch {
        case e: Exception => {
        }
      }
  }

  private def processBlock() {
    while (flag)
      try{
        var block: Block = blockQueue.take
        block = loopProcessBlock(block)
        blockBakQueue.put(block)
        logger.info("Success to process block: "+block.getBlockHeader.getRawData.getNumber()+", blockMapSize: "+blockMap.size+", blockQueueSize: "+blockQueue.size+", blockBakQueue: "+blockBakQueue.size+", cost "+(System.currentTimeMillis - startTime)+".")
      }catch {
        case e: Exception => {
          logger.error(e.getMessage)
          sleep(100)
        }
    }
  }

  //todo 如果块处理异常，就一直循环，直到正确为止
  private def loopProcessBlock(block: Block):Block= {
    var copyBlock:Block = block
    while (true) {
      val blockNum: Long = copyBlock.getBlockHeader.getRawData.getNumber
      try{
        dbManager.pushVerifiedBlock(new BlockCapsule(copyBlock))
        dbManager.getDynamicPropertiesStore.saveLatestSolidifiedBlockNum(blockNum)
        return copyBlock
      }catch {
        case e: Exception => {
          logger.error("Failed to process block "+blockNum+".")
          try{
            sleep(100)
            copyBlock = databaseGrpcClient.getBlock(blockNum)
          }catch {
            case e1: Exception => {
              logger.error(e1.getMessage)
            }
          }
        }
      }
    }
    return copyBlock
  }

  private def processTrx() {
    while (flag)
      try{
        val block: Block = blockBakQueue.take
        val blockCapsule: BlockCapsule = new BlockCapsule(block)
        import scala.collection.JavaConversions._
        for (trx <- blockCapsule.getTransactions) {
          breakable {
            var ret: TransactionInfoCapsule = null
            try
              ret = dbManager.getTransactionHistoryStore.get(trx.getTransactionId.getBytes)
            catch {
              case ex: Exception => {
                logger.warn("Failed to get trx: "+trx.getTransactionId, ex)
                break()
              }
            }
            ret.setBlockNumber(blockCapsule.getNum)
            ret.setBlockTimeStamp(blockCapsule.getTimeStamp)
            dbManager.getTransactionHistoryStore.put(trx.getTransactionId.getBytes, ret)
          }
        }
      }catch {
        case e: Exception => {
          logger.error(e.getMessage)
          sleep(100)
        }
    }
  }

  def sleep(time: Long) {
    try {
      Thread.sleep(time)
    }
    catch {
      case e1: Exception => {
      }
    }
  }

}

object SolidityNode {

  private val logger = LoggerFactory.getLogger(getClass())

  @throws[InterruptedException]
  def main(args: Array[String]) {
    logger.info("Solidity node running.")
    Args.setParam(args, Constant.TESTNET_CONF)
    val cfgArgs: Args = Args.getInstance
    if (StringUtils.isEmpty(cfgArgs.getTrustNodeAddr)) {
      logger.error("Trust node not set.")
      return
    }
    cfgArgs.setSolidityNode(true)
    val context: ApplicationContext = new TronApplicationContext(classOf[DefaultConfig])
    if (cfgArgs.isHelp) {
      logger.info("Here is the help message.")
      return
    }
    val appT: Application = ApplicationFactory.create(context)
    FullNode.shutdown(appT)
    //appT.init(cfgArgs);
    val rpcApiService: RpcApiService = context.getBean(classOf[RpcApiService])
    appT.addService(rpcApiService)
    //http
    val httpApiService: SolidityNodeHttpApiService = context.getBean(classOf[SolidityNodeHttpApiService])
    appT.addService(httpApiService)
    appT.initServices(cfgArgs)
    appT.startServices()
    //    appT.startup();
    //Disable peer discovery for solidity node
    val discoverServer: DiscoverServer = context.getBean(classOf[DiscoverServer])
    discoverServer.close()
    val channelManager: ChannelManager = context.getBean(classOf[ChannelManager])
    channelManager.close()
    val nodeManager: NodeManager = context.getBean(classOf[NodeManager])
    nodeManager.close()
    val node: SolidityNode = new SolidityNode(appT.getDbManager, cfgArgs)
    node.start()
    rpcApiService.blockUntilShutdown()
  }
}
