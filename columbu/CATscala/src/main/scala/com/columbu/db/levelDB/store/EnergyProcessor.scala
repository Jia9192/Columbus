package com.galileo.db.levelDB.store

import java.lang.Long.max
import com.galileo.core.capsule.AccountCapsule
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.config.Parameter.ChainConstant
import com.galileo.core.exception.AccountResourceInsufficientException
import com.galileo.core.exception.ContractValidateException
import org.slf4j.LoggerFactory

import scala.util.control.Breaks

class EnergyProcessor extends ResourceProcessor{
  private val logger = LoggerFactory.getLogger(getClass())

  def this(manager: Manager) {
    this()
    this.dbManager = manager
    this.precision = ChainConstant.PRECISION
    this.windowSize = ChainConstant.WINDOW_SIZE_MS / ChainConstant.BLOCK_PRODUCED_INTERVAL
  }

  override def updateUsage(accountCapsule: AccountCapsule): Unit = {
    val now = dbManager.getWitnessController.getHeadSlot
    updateUsage(accountCapsule, now)
  }

  private def updateUsage(accountCapsule: AccountCapsule, now: Long): Unit = {
    val accountResource = accountCapsule.getAccountResource
    val oldEnergyUsage = accountResource.getEnergyUsage
    val latestConsumeTime = accountResource.getLatestConsumeTimeForEnergy
    accountCapsule.setEnergyUsage(increase(oldEnergyUsage, 0, latestConsumeTime, now))
  }

  @throws[ContractValidateException]
  @throws[AccountResourceInsufficientException]
  override def consume(trx: TransactionCapsule, trace: TransactionTrace): Unit = {
    val contracts = trx.getInstance.getRawData.getContractList
    import scala.collection.JavaConversions._
    val loop = new Breaks()
    for (contract <- contracts) { //todo
      //      if (contract.isPrecompiled()) {
      //        continue;
      //      }
      //todo
      //      long energy = trx.getReceipt().getEnergy();
      loop.breakable{
        val energy = 100L
        logger.debug("trxId {},energy cost :{}", trx.getTransactionId, energy)
        val address = TransactionCapsule.getOwner(contract)
        val accountCapsule = dbManager.getAccountStore.get(address)
        if (accountCapsule == null) throw new ContractValidateException("account not exists")
        val now = dbManager.getWitnessController.getHeadSlot
        //      int creatorRatio = contract.getUserEnergyConsumeRatio();
        val creatorRatio = 50
        val creatorEnergy = energy * creatorRatio / 100
        val contractProvider = dbManager.getAccountStore.get(contract.getProvider.toByteArray)
        if (!useEnergy(contractProvider, creatorEnergy, now)) throw new ContractValidateException("creator has not enough energy[" + creatorEnergy + "]")
        val userEnergy = energy * (100 - creatorRatio) / 100
        //1.The creator and the use of this have sufficient resources
        if (useEnergy(accountCapsule, userEnergy, now))
          loop.break() //todo: continue is not supported
        //     todo  long feeLimit = getUserFeeLimit();
        val feeLimit = 1000000
        //sun
        val fee = calculateFee(userEnergy)
        if (fee > feeLimit) throw new AccountResourceInsufficientException("Account has Insufficient Energy[" + userEnergy + "] and feeLimit[" + feeLimit + "] is not enough to trigger this contract")
        //2.The creator of this have sufficient resources
        if (useFee(accountCapsule, fee, trace))
          loop.break()//todo: continue is not supported
        throw new AccountResourceInsufficientException("Account has insufficient Energy[" + userEnergy + "] and balance[" + fee + "] to trigger this contract")
      }
    }
  }

  private def calculateFee(userEnergy: Long) = userEnergy * 30 // 30 drop / macroSecond, move to dynamicStore later


  private def useFee(accountCapsule: AccountCapsule, fee: Long, trace: TransactionTrace) = if (consumeFee(accountCapsule, fee)) {
    trace.setNetBill(0, fee)
    true
  }
  else false

  def useEnergy(accountCapsule: AccountCapsule, energy: Long, now: Long): Boolean = {
    val energyUsage = accountCapsule.getEnergyUsage
    var latestConsumeTime = accountCapsule.getAccountResource.getLatestConsumeTimeForEnergy
    val energyLimit = calculateGlobalEnergyLimit(accountCapsule.getAccountResource.getFrozenBalanceForEnergy.getFrozenBalance)
    var newEnergyUsage = increase(energyUsage, 0, latestConsumeTime, now)
    if (energy > (energyLimit - newEnergyUsage)) return false
    latestConsumeTime = now
    val latestOperationTime = dbManager.getHeadBlockTimeStamp
    newEnergyUsage = increase(newEnergyUsage, energy, latestConsumeTime, now)
    accountCapsule.setEnergyUsage(newEnergyUsage)
    accountCapsule.setLatestOperationTime(latestOperationTime)
    accountCapsule.setLatestConsumeTimeForEnergy(latestConsumeTime)
    dbManager.getAccountStore.put(accountCapsule.createDbKey, accountCapsule)
    true
  }


  def calculateGlobalEnergyLimit(frozeBalance: Long): Long = {
    if (frozeBalance < 1000000L)
      return 0
    val energyWeight = frozeBalance / 1000000L
    val totalEnergyLimit = dbManager.getDynamicPropertiesStore.getTotalEnergyLimit
    val totalEnergyWeight = dbManager.getDynamicPropertiesStore.getTotalEnergyWeight
    assert(totalEnergyWeight > 0)
    (energyWeight * (totalEnergyLimit.toDouble / totalEnergyWeight)).toLong
  }

  def getAccountLeftEnergyFromFreeze(accountCapsule: AccountCapsule): Long = {
    val now = dbManager.getWitnessController.getHeadSlot
    val energyUsage = accountCapsule.getEnergyUsage
    val latestConsumeTime = accountCapsule.getAccountResource.getLatestConsumeTimeForEnergy
    val energyLimit = calculateGlobalEnergyLimit(accountCapsule.getAccountResource.getFrozenBalanceForEnergy.getFrozenBalance)
    val newEnergyUsage = increase(energyUsage, 0, latestConsumeTime, now)
    max(energyLimit - newEnergyUsage, 0) // us

  }
}
