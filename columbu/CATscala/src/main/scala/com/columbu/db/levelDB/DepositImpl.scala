package com.galileo.db.levelDB

import com.galileo.core.gvm.utils.MUtil.convertToTronAddress
import com.google.common.primitives.Longs
import com.google.protobuf.ByteString
import java.util
import com.galileo.core.gvm.vm.DataWord
import com.galileo.core.gvm.vm.program.Storage
import com.galileo.utils.ByteArray
import com.galileo.utils.StringUtil
import com.galileo.core.capsule.AccountCapsule
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.BytesCapsule
import com.galileo.core.capsule.ContractCapsule
import com.galileo.core.capsule.ProposalCapsule
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.capsule.VotesCapsule
import com.galileo.core.capsule.WitnessCapsule
import com.galileo.db.levelDB.store.Manager
import com.galileo.core.exception.BadItemException
import com.galileo.core.exception.ItemNotFoundException
import com.galileo.protos.protos.Protocol

class DepositImpl extends Deposit{

  private var dbManager : Manager= null
  private var parent : Deposit= null

  private val accountCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val transactionCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val blockCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val witnessCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val blockIndexCache : util.HashMap[Key, Value] = new util.HashMap[Key, Value]
  private val codeCache : util.HashMap[Key, Value] = new util.HashMap[Key, Value]
  private val contractCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]

  private val votesCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val proposalCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val dynamicPropertiesCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val accountContractIndexCache : util.HashMap[Key, Value]= new util.HashMap[Key, Value]
  private val storageCache: util.HashMap[Key, Storage] = new util.HashMap[Key, Storage]

  def this(dbManager: Manager, parent: DepositImpl) {
    this()
    init(dbManager, parent)
  }

  protected def init(dbManager: Manager, parent: DepositImpl): Unit = {
    this.dbManager = dbManager
    this.parent = parent
  }

  override def getDbManager: Manager = dbManager

  private def getBlockStore = dbManager.getBlockStore

  private def getTransactionStore = dbManager.getTransactionStore

  private def getContractStore = dbManager.getContractStore

  private def getWitnessStore = dbManager.getWitnessStore

  private def getVotesStore = dbManager.getVotesStore

  private def getProposalStore = dbManager.getProposalStore

  private def getDynamicPropertiesStore = dbManager.getDynamicPropertiesStore

  private def getAccountStore = dbManager.getAccountStore

  private def getCodeStore = dbManager.getCodeStore

  private def getStorageRowStore = dbManager.getStorageRowStore

  private def getAssetIssueStore = dbManager.getAssetIssueStore

  override def newDepositChild = new DepositImpl(dbManager, this)

  override def createAccount(address: Array[Byte], `type`: Protocol.AccountType): AccountCapsule = {
    val key = new Key(address)
    val account = new AccountCapsule(ByteString.copyFrom(address), `type`)
    accountCache.put(key, new Value(account.getData, 1 << 1))
    account
  }

  override def createAccount(address: Array[Byte], accountName: String, `type`: Protocol.AccountType): AccountCapsule = {
    val key = new Key(address)
    val account = new AccountCapsule(ByteString.copyFrom(address), ByteString.copyFromUtf8(accountName), `type`)
    accountCache.put(key, new Value(account.getData, 1 << 1))
    account
  }

  override def getAccount(address: Array[Byte]): AccountCapsule = {
    val key = new Key(address)
    if (accountCache.containsKey(key))
      return accountCache.get(key).getAccount
    var accountCapsule : AccountCapsule= null
    if (parent != null)
      accountCapsule = parent.getAccount(address)
    else accountCapsule = getAccountStore.get(address)
    if (accountCapsule != null)
      accountCache.put(key, new Value(accountCapsule.getData,0))

    accountCapsule
  }

  override def getWitness(address: Array[Byte]): WitnessCapsule = {
    val key = new Key(address)
    if (witnessCache.containsKey(key))
      return witnessCache.get(key).getWitness
    var witnessCapsule : WitnessCapsule= null
    if (parent != null)
      witnessCapsule = parent.getWitness(address)
    else witnessCapsule = getWitnessStore.get(address)
    if (witnessCapsule != null) witnessCache.put(key, new Value(witnessCapsule.getData,0))
    witnessCapsule
  }


  override def getVotesCapsule(address: Array[Byte]): VotesCapsule = {
    val key = new Key(address)
    if (votesCache.containsKey(key)) return votesCache.get(key).getVotes
    var votesCapsule : VotesCapsule= null
    if (parent != null)
      votesCapsule = parent.getVotesCapsule(address)
    else votesCapsule = getVotesStore.get(address)
    if (votesCapsule != null)
      votesCache.put(key, new Value(votesCapsule.getData,0))
    votesCapsule
  }


  override def getProposalCapsule(id: Array[Byte]): ProposalCapsule = {
    val key = new Key(id)
    if (proposalCache.containsKey(key)) return proposalCache.get(key).getProposal
    var proposalCapsule : ProposalCapsule = null
    if (parent != null)
      proposalCapsule = parent.getProposalCapsule(id)
    else try
      proposalCapsule = getProposalStore.get(id)
    catch {
      case e: ItemNotFoundException =>
        proposalCapsule = null
    }
    if (proposalCapsule != null)
      proposalCache.put(key, new Value(proposalCapsule.getData,0))
    proposalCapsule
  }

  // just for depositRoot
  override def deleteContract(address: Array[Byte]): Unit = {
    getCodeStore.delete(address)
    getAccountStore.delete(address)
    getContractStore.delete(address)
  }

  override def createContract(address: Array[Byte], contractCapsule: ContractCapsule): Unit = {
    val key = new Key(address)
    val value = new Value(contractCapsule.getData, 1 << 1)
    contractCache.put(key, value)
  }

  override def getContract(address: Array[Byte]): ContractCapsule = {
    val key = new Key(address)
    if (contractCache.containsKey(key))
      return contractCache.get(key).getContract
    var contractCapsule : ContractCapsule = null
    if (parent != null)
      contractCapsule = parent.getContract(address)
    else contractCapsule = getContractStore.get(address)
    if (contractCapsule != null)
      contractCache.put(key, new Value(contractCapsule.getData, 0))
    contractCapsule
  }

  override def saveCode(codeHash: Array[Byte], code: Array[Byte]): Unit = {
    val key = new Key(codeHash)
    val value = new Value(code, 1 << 1)
    codeCache.put(key, value)
  }

  override def getCode(addr: Array[Byte]): Array[Byte] = {
    val key = new Key(addr)
    if (codeCache.containsKey(key))
      return codeCache.get(key).getCode.getData
    var code : Array[Byte] = null
    if (parent != null) code = parent.getCode(addr)
    else if (null == getCodeStore.get(addr))
      code = null
    else code = getCodeStore.get(addr).getData
    if (code != null) codeCache.put(key, new Value(code, 0))
    code
  }

  override def getStorage(address: Array[Byte]): Storage = {
    val key = new Key(address)
    if (storageCache.containsKey(key))
      return storageCache.get(key)
    var storage : Storage= null
    if (this.parent != null) storage = parent.getStorage(address)
    else storage = new Storage(address, dbManager.getStorageRowStore)
    storage
  }

  override def putStorageValue(address: Array[Byte], key: DataWord, value: DataWord): Unit = {
    var addressP = convertToTronAddress(address)
    if (getAccount(addressP) == null) return
    val addressKey = new Key(addressP)
    var storage : Storage= null
    if (storageCache.containsKey(addressKey))
      storage = storageCache.get(addressKey)
    else {
      storage = getStorage(addressP)
      storageCache.put(addressKey, storage)
    }
    storage.put(key, value)
  }

  override def getStorageValue(address: Array[Byte], key: DataWord): DataWord = {
    var addressP : Array[Byte] = convertToTronAddress(address)
    if (getAccount(addressP) == null)
      return null
    val addressKey = Key.create(address)
    var storage : Storage= null
    if (storageCache.containsKey(addressKey))
      storage = storageCache.get(addressKey)
    else {
      storage = getStorage(address)
      storageCache.put(addressKey, storage)
    }
    storage.getValue(key)
  }

  override def getBalance(address: Array[Byte]): Long = {
    val accountCapsule = getAccount(address)
    if (accountCapsule == null) 0L
    else accountCapsule.getBalance
  }

  override def addBalance(address: Array[Byte], value: Long): Long = {
    var accountCapsule = getAccount(address)
    if (accountCapsule == null) accountCapsule = createAccount(address, Protocol.AccountType.Normal)
    val balance = accountCapsule.getBalance
    if (value == 0) return balance
    if (value < 0 && balance < -value) throw new RuntimeException(StringUtil.createReadableString(accountCapsule.createDbKey) + " insufficient balance")
    accountCapsule.setBalance(Math.addExact(balance, value))
    val key = Key.create(address)
    val V = Value.create(accountCapsule.getData, Type.VALUE_TYPE_DIRTY | accountCache.get(key).getType.getType)
    accountCache.put(key, V)
    accountCapsule.getBalance
  }

  override def getTransaction(trxHash: Array[Byte]): TransactionCapsule = {
    val key = Key.create(trxHash)
    if (transactionCache.containsKey(key))
      return transactionCache.get(key).getTransaction
    var transactionCapsule : TransactionCapsule= null
    if (parent != null)
      transactionCapsule = parent.getTransaction(trxHash)
    else try
      transactionCapsule = getTransactionStore.get(trxHash)
    catch {
      case e: BadItemException =>
        transactionCapsule = null
    }
    if (transactionCapsule != null) transactionCache.put(key, Value.create(transactionCapsule.getData))
    transactionCapsule
  }

  override def getBlock(blockHash: Array[Byte]): BlockCapsule = {
    val key = Key.create(blockHash)
    if (blockCache.containsKey(key))
      return blockCache.get(key).getBlock
    var ret : BlockCapsule = null
    try
        if (parent != null)
          ret = parent.getBlock(blockHash)
        else ret = getBlockStore.get(blockHash)
    catch {
      case e: Exception =>
        ret = null
    }
    if (ret != null) blockCache.put(key, Value.create(ret.getData))
    ret
  }

  override def putAccount(key: Key, value: Value): Unit = {
    accountCache.put(key, value)
  }

  override def putTransaction(key: Key, value: Value): Unit = {
    transactionCache.put(key, value)
  }

  override def putBlock(key: Key, value: Value): Unit = {
    blockCache.put(key, value)
  }

  override def putWitness(key: Key, value: Value): Unit = {
    witnessCache.put(key, value)
  }

  override def putCode(key: Key, value: Value): Unit = {
    codeCache.put(key, value)
  }

  override def putContract(key: Key, value: Value): Unit = {
    contractCache.put(key, value)
  }

  //  @Override
  //  public void putStorage(Key key, Value value) {
  //    storageCache.put(key, value);
  //  }

  override def putStorage(key: Key, cache: Storage): Unit = {
    storageCache.put(key, cache)
  }

  override def putVotes(key: Key, value: Value): Unit = {
    votesCache.put(key, value)
  }

  override def putProposal(key: Key, value: Value): Unit = {
    proposalCache.put(key, value)
  }

  override def putDynamicProperties(key: Key, value: Value): Unit = {
    dynamicPropertiesCache.put(key, value)
  }

  override def getLatestProposalNum: Long = Longs.fromByteArray(getDynamic(DepositImpl.LATEST_PROPOSAL_NUM).getData)

  override def getWitnessAllowanceFrozenTime: Long = {
    val frozenTime = getDynamic(DepositImpl.WITNESS_ALLOWANCE_FROZEN_TIME).getData
    if (frozenTime.length >= 8) return Longs.fromByteArray(getDynamic(DepositImpl.WITNESS_ALLOWANCE_FROZEN_TIME).getData)
    val result = new Array[Byte](8)
    System.arraycopy(frozenTime, 0, result, 8 - frozenTime.length, frozenTime.length)
    Longs.fromByteArray(result)
  }

  override def getMaintenanceTimeInterval: Long = Longs.fromByteArray(getDynamic(DepositImpl.MAINTENANCE_TIME_INTERVAL).getData)

  override def getNextMaintenanceTime: Long = Longs.fromByteArray(getDynamic(DepositImpl.NEXT_MAINTENANCE_TIME).getData)

  override def getDynamic(word: Array[Byte]): BytesCapsule = {
    val key = Key.create(word)
    if (dynamicPropertiesCache.containsKey(key)) return dynamicPropertiesCache.get(key).getDynamicProperties
    var bytesCapsule : BytesCapsule = null
    if (parent != null) bytesCapsule = parent.getDynamic(word)
    else try
      bytesCapsule = getDynamicPropertiesStore.get(word)
    catch {
      case e: BadItemException =>
        bytesCapsule = null
      case e: ItemNotFoundException =>
        bytesCapsule = null
    }
    if (bytesCapsule != null) dynamicPropertiesCache.put(key, Value.create(bytesCapsule.getData))
    bytesCapsule
  }

  private def commitAccountCache(deposit: Deposit): Unit = {
    accountCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isCreate || value.getType.isDirty) if (deposit != null) deposit.putAccount(key, value)
        else getAccountStore.put(key.getData, value.getAccount)
      }

      foo(key, value)
    })
  }

  private def commitTransactionCache(deposit: Deposit): Unit = {
    transactionCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) if (deposit != null) deposit.putTransaction(key, value)
        else getTransactionStore.put(key.getData, value.getTransaction)
      }

      foo(key, value)
    })
  }

  private def commitBlockCache(deposit: Deposit): Unit = {
    blockCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putBlock(key, value)
          }
          else {
            getBlockStore.put(key.getData, value.getBlock)
          }
        }
      }

      foo(key, value)
    })
  }

  private def commitWitnessCache(deposit: Deposit): Unit = {
    witnessCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putWitness(key, value)
          }
          else {
            getWitnessStore.put(key.getData, value.getWitness)
          }
        }
      }

      foo(key, value)
    })
  }

  private def commitCodeCache(deposit: Deposit): Unit = {
    codeCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putCode(key, value)
          }
          else {
            getCodeStore.put(key.getData, value.getCode)
          }
        }
      }

      foo(key, value)
    })
  }

  private def commitContractCache(deposit: Deposit): Unit = {
    contractCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putContract(key, value)
          }
          else {
            getContractStore.put(key.getData, value.getContract)
          }
        }
      }

      foo(key, value)
    })
  }

  private def commitStorageCache(deposit: Deposit): Unit = {
    storageCache.forEach((key: Key, value: Storage) => {
      def foo(key: Key, value: Storage) = {
        if (deposit != null) { // write to parent cache
          deposit.putStorage(key, value)
        }
        else { // persistence
          value.commit()
        }
      }

      foo(key, value)
    })
  }

  private def commitVoteCache(deposit: Deposit): Unit = {
    votesCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putVotes(key, value)
          }
          else {
            getVotesStore.put(key.getData, value.getVotes)
          }
        }
      }

      foo(key, value)
    })
  }

  private def commitProposalCache(deposit: Deposit): Unit = {
    proposalCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putProposal(key, value)
          }
          else {
            getProposalStore.put(key.getData, value.getProposal)
          }
        }
      }

      foo(key, value)
    })
  }

  private def commitDynamicPropertiesCache(deposit: Deposit): Unit = {
    dynamicPropertiesCache.forEach((key: Key, value: Value) => {
      def foo(key: Key, value: Value) = {
        if (value.getType.isDirty || value.getType.isCreate) {
          if (deposit != null) {
            deposit.putDynamicProperties(key, value)
          }
          else {
            getDynamicPropertiesStore.put(key.getData, value.getDynamicProperties)
          }
        }
      }

      foo(key, value)
    })
  }


  override def putAccountValue(address: Array[Byte], accountCapsule: AccountCapsule): Unit = {
    val key = new Key(address)
    accountCache.put(key, new Value(accountCapsule.getData, Type.VALUE_TYPE_CREATE))
  }

  override def putVoteValue(address: Array[Byte], votesCapsule: VotesCapsule): Unit = {
    val key = new Key(address)
    votesCache.put(key, new Value(votesCapsule.getData, Type.VALUE_TYPE_CREATE))
  }

  override def putProposalValue(address: Array[Byte], proposalCapsule: ProposalCapsule): Unit = {
    val key = new Key(address)
    proposalCache.put(key, new Value(proposalCapsule.getData, Type.VALUE_TYPE_CREATE))
  }

  override def putDynamicPropertiesWithLatestProposalNum(num: Long): Unit = {
    val key = new Key(DepositImpl.LATEST_PROPOSAL_NUM)
    dynamicPropertiesCache.put(key, new Value(new BytesCapsule(ByteArray.fromLong(num)).getData, Type.VALUE_TYPE_CREATE))
  }

  override def commit(): Unit = {
    var deposit : Deposit= null
    if (parent != null)
      deposit = parent
    commitAccountCache(deposit)
    commitTransactionCache(deposit)
    commitBlockCache(deposit)
    commitWitnessCache(deposit)
    commitCodeCache(deposit)
    commitContractCache(deposit)
    commitStorageCache(deposit)
    commitVoteCache(deposit)
    commitProposalCache(deposit)
    commitDynamicPropertiesCache(deposit)
    // commitAccountContractIndex(deposit);
  }

  override def flush(): Unit = {
    throw new RuntimeException("Not supported")
  }

  override def setParent(deposit: Deposit): Unit = {
    parent = deposit
  }

  def createRoot(dbManager: Manager) = new DepositImpl(dbManager, null)

}
object DepositImpl{

  private val LATEST_PROPOSAL_NUM = "LATEST_PROPOSAL_NUM".getBytes
  private val WITNESS_ALLOWANCE_FROZEN_TIME = "WITNESS_ALLOWANCE_FROZEN_TIME".getBytes
  private val MAINTENANCE_TIME_INTERVAL = "MAINTENANCE_TIME_INTERVAL".getBytes
  private val NEXT_MAINTENANCE_TIME = "NEXT_MAINTENANCE_TIME".getBytes
}
