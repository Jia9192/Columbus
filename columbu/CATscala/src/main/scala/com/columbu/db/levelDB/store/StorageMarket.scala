package com.galileo.db.levelDB.store


import com.galileo.core.capsule.AccountCapsule
import com.galileo.core.config.Parameter.ChainConstant
import org.slf4j.LoggerFactory


class StorageMarket {

  private var dbManager : Manager = null
  private var supply = 1000000000000000L
  private val logger = LoggerFactory.getLogger(getClass())
  def this(manager: Manager) {
    this()
    this.dbManager = manager
  }

  private def exchange_to_supply(isTRX: Boolean, quant: Long) = {
    logger.info("isTRX: " + isTRX)
    val balance = if (isTRX) dbManager.getDynamicPropertiesStore.getTotalStoragePool
    else dbManager.getDynamicPropertiesStore.getTotalStorageReserved
    logger.info("balance: " + balance)
    val newBalance = balance + quant
    logger.info("balance + quant: " + (balance + quant))
    //    if (isTRX) {
    //      dbManager.getDynamicPropertiesStore().saveTotalStoragePool(newBalance);
    //    } else {
    //      dbManager.getDynamicPropertiesStore().saveTotalStorageReserved(newBalance);
    //    }
    val issuedSupply = -supply * (1.0 - Math.pow(1.0 + quant.toDouble / newBalance, 0.0005))
    logger.info("issuedSupply: " + issuedSupply)
    val out = issuedSupply.toLong
    supply += out
    out
  }

  private def exchange_to_supply2(isTRX: Boolean, quant: Long) = {
    logger.info("isTRX: " + isTRX)
    val balance = if (isTRX) dbManager.getDynamicPropertiesStore.getTotalStoragePool
    else dbManager.getDynamicPropertiesStore.getTotalStorageReserved
    logger.info("balance: " + balance)
    val newBalance = balance - quant
    logger.info("balance - quant: " + (balance - quant))
    val issuedSupply = -supply * (1.0 - Math.pow(1.0 + quant.toDouble / newBalance, 0.0005))
    logger.info("issuedSupply: " + issuedSupply)
    val out = issuedSupply.toLong
    supply += out
    out
  }

  private def exchange_from_supply(isTRX: Boolean, supplyQuant: Long) = {
    val balance : Long = if (isTRX) dbManager.getDynamicPropertiesStore.getTotalStoragePool
    else dbManager.getDynamicPropertiesStore.getTotalStorageReserved
    supply -= supplyQuant
    val exchangeBalance = balance * (Math.pow(1.0 + supplyQuant.toDouble / supply, 2000.0) - 1.0)
    logger.info("exchangeBalance: " + exchangeBalance)
    var out = exchangeBalance.toLong
    val newBalance = balance - out
    if (isTRX) {
      out = Math.round(exchangeBalance / 100000) * 100000
      logger.info("---out: " + out)
    }
    out
  }

  def exchange(from: Long, isTRX: Boolean): Long = {
    val relay = exchange_to_supply(isTRX, from)
    exchange_from_supply(!isTRX, relay)
  }

  def calculateTax(duration: Long, limit: Long): Long = { // todo: Support for change by the committee
    val ratePerYear = dbManager.getDynamicPropertiesStore.getStorageExchangeTaxRate / 100.0
    val millisecondPerYear = ChainConstant.MS_PER_YEAR.toDouble
    val feeRate = duration / millisecondPerYear * ratePerYear
    val storageTax = (limit * feeRate).toLong
    logger.info("storageTax: " + storageTax)
    storageTax
  }


  def tryPayTax(duration: Long, limit: Long): Long = {
    val storageTax = calculateTax(duration, limit)
    val tax = exchange(storageTax, false)
    logger.info("tax: " + tax)
    val newTotalTax = dbManager.getDynamicPropertiesStore.getTotalStorageTax + tax
    val newTotalPool = dbManager.getDynamicPropertiesStore.getTotalStoragePool - tax
    val newTotalReserved = dbManager.getDynamicPropertiesStore.getTotalStorageReserved + storageTax
    logger.info("reserved: " + dbManager.getDynamicPropertiesStore.getTotalStorageReserved)
    val eq = dbManager.getDynamicPropertiesStore.getTotalStorageReserved == 128L * 1024 * 1024 * 1024
    logger.info("reserved == 128GB: " + eq)
    logger.info("newTotalTax: " + newTotalTax + "  newTotalPool: " + newTotalPool + "  newTotalReserved: " + newTotalReserved)
    storageTax
  }

  def payTax(duration: Long, limit: Long): Long = {
    val storageTax = calculateTax(duration, limit)
    val tax = exchange(storageTax, false)
    logger.info("tax: " + tax)
    val newTotalTax = dbManager.getDynamicPropertiesStore.getTotalStorageTax + tax
    val newTotalPool = dbManager.getDynamicPropertiesStore.getTotalStoragePool - tax
    val newTotalReserved = dbManager.getDynamicPropertiesStore.getTotalStorageReserved + storageTax
    logger.info("reserved: " + dbManager.getDynamicPropertiesStore.getTotalStorageReserved)
    val eq = dbManager.getDynamicPropertiesStore.getTotalStorageReserved == 128L * 1024 * 1024 * 1024
    logger.info("reserved == 128GB: " + eq)
    logger.info("newTotalTax: " + newTotalTax + "  newTotalPool: " + newTotalPool + "  newTotalReserved: " + newTotalReserved)
    dbManager.getDynamicPropertiesStore.saveTotalStorageTax(newTotalTax)
    dbManager.getDynamicPropertiesStore.saveTotalStoragePool(newTotalPool)
    dbManager.getDynamicPropertiesStore.saveTotalStorageReserved(newTotalReserved)
    storageTax
  }

  def tryBuyStorageBytes(storageBought: Long): Long = {
    val relay = exchange_to_supply2(false, storageBought)
    exchange_from_supply(true, relay)
  }

  def tryBuyStorage(quant: Long): Long = exchange(quant, true)

  def trySellStorage(bytes: Long): Long = exchange(bytes, false)

  def buyStorageBytes(accountCapsule: AccountCapsule, storageBought: Long): AccountCapsule = {
    val now = dbManager.getHeadBlockTimeStamp
    val currentStorageLimit = accountCapsule.getStorageLimit
    val relay = exchange_to_supply2(false, storageBought)
    val quant = exchange_from_supply(true, relay)
    val newBalance = accountCapsule.getBalance - quant
    logger.info("newBalance： " + newBalance)
    val newStorageLimit = currentStorageLimit + storageBought
    logger.info("storageBought: " + storageBought + "  newStorageLimit: " + newStorageLimit)
    accountCapsule.setLatestExchangeStorageTime(now)
    accountCapsule.setStorageLimit(newStorageLimit)
    accountCapsule.setBalance(newBalance)
    dbManager.getAccountStore.put(accountCapsule.createDbKey, accountCapsule)
    val newTotalPool = dbManager.getDynamicPropertiesStore.getTotalStoragePool + quant
    val newTotalReserved = dbManager.getDynamicPropertiesStore.getTotalStorageReserved - storageBought
    logger.info("newTotalPool: " + newTotalPool + "  newTotalReserved: " + newTotalReserved)
    dbManager.getDynamicPropertiesStore.saveTotalStoragePool(newTotalPool)
    dbManager.getDynamicPropertiesStore.saveTotalStorageReserved(newTotalReserved)
    accountCapsule
  }


  def buyStorage(accountCapsule: AccountCapsule, quant: Long): Unit = {
    val now = dbManager.getHeadBlockTimeStamp
    val currentStorageLimit = accountCapsule.getStorageLimit
    val newBalance = accountCapsule.getBalance - quant
    logger.info("newBalance： " + newBalance)
    val storageBought = exchange(quant, true)
    val newStorageLimit = currentStorageLimit + storageBought
    logger.info("storageBought: " + storageBought + "  newStorageLimit: " + newStorageLimit)
    accountCapsule.setLatestExchangeStorageTime(now)
    accountCapsule.setStorageLimit(newStorageLimit)
    accountCapsule.setBalance(newBalance)
    dbManager.getAccountStore.put(accountCapsule.createDbKey, accountCapsule)
    val newTotalPool = dbManager.getDynamicPropertiesStore.getTotalStoragePool + quant
    val newTotalReserved = dbManager.getDynamicPropertiesStore.getTotalStorageReserved - storageBought
    logger.info("newTotalPool: " + newTotalPool + "  newTotalReserved: " + newTotalReserved)
    dbManager.getDynamicPropertiesStore.saveTotalStoragePool(newTotalPool)
    dbManager.getDynamicPropertiesStore.saveTotalStorageReserved(newTotalReserved)
  }

  def sellStorage(accountCapsule: AccountCapsule, bytes: Long): Unit = {
    val now = dbManager.getHeadBlockTimeStamp
    val currentStorageLimit = accountCapsule.getStorageLimit
    val quant = exchange(bytes, false)
    val newBalance = accountCapsule.getBalance + quant
    val newStorageLimit = currentStorageLimit - bytes
    logger.info("quant: " + quant + "  newStorageLimit: " + newStorageLimit)
    accountCapsule.setLatestExchangeStorageTime(now)
    accountCapsule.setStorageLimit(newStorageLimit)
    accountCapsule.setBalance(newBalance)
    dbManager.getAccountStore.put(accountCapsule.createDbKey, accountCapsule)
    val newTotalPool = dbManager.getDynamicPropertiesStore.getTotalStoragePool - quant
    val newTotalReserved = dbManager.getDynamicPropertiesStore.getTotalStorageReserved + bytes
    logger.info("newTotalPool: " + newTotalPool + "  newTotalReserved: " + newTotalReserved)
    dbManager.getDynamicPropertiesStore.saveTotalStoragePool(newTotalPool)
    dbManager.getDynamicPropertiesStore.saveTotalStorageReserved(newTotalReserved)
  }

  def getAccountLeftStorageInByteFromBought(accountCapsule: AccountCapsule): Long = accountCapsule.getStorageLimit - accountCapsule.getStorageUsage
}
