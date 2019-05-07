package com.galileo.db.levelDB.store

import com.galileo.core.capsule.{AccountCapsule, TransactionCapsule}
import com.galileo.core.config.Parameter.ChainConstant
import com.galileo.core.exception.{AccountResourceInsufficientException, BalanceInsufficientException, ContractValidateException, TooBigTransactionResultException}

abstract class ResourceProcessor {

  protected var dbManager: Manager = null
  protected var precision = 0L
  protected var windowSize = 0L

  def this(manager: Manager) {
    this()
    this.dbManager = manager
    this.precision = ChainConstant.PRECISION
    this.windowSize = ChainConstant.WINDOW_SIZE_MS / ChainConstant.BLOCK_PRODUCED_INTERVAL
  }

  private[store] def updateUsage(accountCapsule: AccountCapsule): Unit

  @throws[ContractValidateException]
  @throws[AccountResourceInsufficientException]
  @throws[TooBigTransactionResultException]
  private[store] def consume(trx: TransactionCapsule, trace: TransactionTrace): Unit

  protected def increase(lastUsage: Long, usage: Long, lastTime: Long, now: Long): Long = {
    var averageLastUsage = divideCeil(lastUsage * precision, windowSize)
    val averageUsage = divideCeil(usage * precision, windowSize)
    if (lastTime != now) {
      assert(now > lastTime)
      if (lastTime + windowSize > now) {
        val delta = now - lastTime
        val decay = (windowSize - delta) / windowSize.toDouble
        averageLastUsage = averageLastUsage * decay.round
      }
      else averageLastUsage = 0
    }
    averageLastUsage += averageUsage
    getUsage(averageLastUsage)
  }

  private def divideCeil(numerator: Long, denominator: Long) = (numerator / denominator) + (if ((numerator % denominator) > 0) 1
  else 0)

  private def getUsage(usage: Long) = usage * windowSize / precision

  protected def consumeFee(accountCapsule: AccountCapsule, fee: Long): Boolean = try {
    val latestOperationTime = dbManager.getHeadBlockTimeStamp
    accountCapsule.setLatestOperationTime(latestOperationTime)
    dbManager.adjustBalance(accountCapsule, -fee)
    dbManager.adjustBalance(this.dbManager.getAccountStore.getBlackhole.createDbKey, +fee)
    true
  } catch {
    case e: BalanceInsufficientException =>
      false
  }
}
