package com.galileo.main

import org.springframework.beans.factory.support.DefaultListableBeanFactory
import com.galileo.core.appManage.Application
import com.galileo.core.appManage.ApplicationFactory
import com.galileo.core.appManage.TronApplicationContext
import com.galileo.core.Constant
import com.galileo.core.config.DefaultConfig
import com.galileo.core.config.args.Args
import com.galileo.core.service.RpcApiService
import com.galileo.core.service.WitnessService
import com.galileo.core.service.http.FullNodeHttpApiService
import org.slf4j.LoggerFactory

object FullNode {

  private val logger = LoggerFactory.getLogger(this.getClass())

  @throws[InterruptedException]
  def main(args: Array[String]) {
    logger.info("Full node running.")
    var argss = Array("-p","51879CD95930E6FF04B75D7F290C3FFE0AE76CBBF4B7763BEC02CAB7038E48B8 ","--witness","-c","D:\\IDEA\\IdeaSourceData\\galileo4scala\\src\\main\\resources\\config.conf")
    Args.setParam(argss, Constant.TESTNET_CONF)
    val cfgArgs: Args = Args.getInstance
    if (cfgArgs.isHelp) {
      logger.info("Here is the help message.")
      return
    }
    if (Args.getInstance.isDebug) logger.info("in debug mode, it won't check energy time")
    else logger.info("not in debug mode, it will check energy time")
    val beanFactory: DefaultListableBeanFactory = new DefaultListableBeanFactory
    beanFactory.setAllowCircularReferences(false)
    val context: TronApplicationContext = new TronApplicationContext(beanFactory)
    context.register(classOf[DefaultConfig])
    context.refresh()
    val appT: Application = ApplicationFactory.create(context)
    shutdown(appT)
    // grpc api server
    val rpcApiService: RpcApiService = context.getBean(classOf[RpcApiService])
    appT.addService(rpcApiService)
    if (cfgArgs.isWitness) appT.addService(new WitnessService(appT, context))
    // http api server
    val httpApiService: FullNodeHttpApiService = context.getBean(classOf[FullNodeHttpApiService])
    appT.addService(httpApiService)
    appT.initServices(cfgArgs)
    appT.startServices()
    appT.startup()
    rpcApiService.blockUntilShutdown()
  }

  def shutdown(app: Application) {
    logger.info("********register application shutdown hook********")
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() {
        app.shutdown()
      }
    })
  }

}
