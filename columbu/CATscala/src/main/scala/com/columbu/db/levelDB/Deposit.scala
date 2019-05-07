package com.galileo.db.levelDB

import com.galileo.core.gvm.vm.DataWord
import com.galileo.core.gvm.vm.program.Storage
import com.galileo.core.capsule.AccountCapsule
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.BytesCapsule
import com.galileo.core.capsule.ContractCapsule
import com.galileo.core.capsule.ProposalCapsule
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.capsule.VotesCapsule
import com.galileo.core.capsule.WitnessCapsule
import com.galileo.db.levelDB.store.Manager
import com.galileo.protos.protos.Protocol

trait Deposit {
  def getDbManager: Manager

  def createAccount(address: Array[Byte], `type`: Protocol.AccountType): AccountCapsule

  def createAccount(address: Array[Byte], accountName: String, `type`: Protocol.AccountType): AccountCapsule

  def getAccount(address: Array[Byte]): AccountCapsule

  def getWitness(address: Array[Byte]): WitnessCapsule

  def getVotesCapsule(address: Array[Byte]): VotesCapsule

  def getProposalCapsule(id: Array[Byte]): ProposalCapsule

  def getDynamic(bytesKey: Array[Byte]): BytesCapsule

  def deleteContract(address: Array[Byte]): Unit

  def createContract(address: Array[Byte], contractCapsule: ContractCapsule): Unit

  def getContract(address: Array[Byte]): ContractCapsule

  def saveCode(codeHash: Array[Byte], code: Array[Byte]): Unit

  def getCode(codeHash: Array[Byte]): Array[Byte]

  //byte[] getCodeHash(byte[] address);

  def putStorageValue(address: Array[Byte], key: DataWord, value: DataWord): Unit

  def getStorageValue(address: Array[Byte], key: DataWord): DataWord

  def getStorage(address: Array[Byte]): Storage

  def getBalance(address: Array[Byte]): Long

  def addBalance(address: Array[Byte], value: Long): Long


  def newDepositChild: Deposit

  def setParent(deposit: Deposit): Unit

  def flush(): Unit

  def commit(): Unit

  def putAccount(key: Key, value: Value): Unit

  def putTransaction(key: Key, value: Value): Unit

  def putBlock(key: Key, value: Value): Unit

  def putWitness(key: Key, value: Value): Unit

  def putCode(key: Key, value: Value): Unit

  def putContract(key: Key, value: Value): Unit

  def putStorage(key: Key, cache: Storage): Unit

  def putVotes(key: Key, value: Value): Unit

  def putProposal(key: Key, value: Value): Unit

  def putDynamicProperties(key: Key, value: Value): Unit

  def putAccountValue(address: Array[Byte], accountCapsule: AccountCapsule): Unit

  def putVoteValue(address: Array[Byte], votesCapsule: VotesCapsule): Unit

  def putProposalValue(address: Array[Byte], proposalCapsule: ProposalCapsule): Unit

  def putDynamicPropertiesWithLatestProposalNum(num: Long): Unit

  def getLatestProposalNum: Long

  def getWitnessAllowanceFrozenTime: Long

  def getMaintenanceTimeInterval: Long

  def getNextMaintenanceTime: Long

  def getTransaction(trxHash: Array[Byte]): TransactionCapsule

  def getBlock(blockHash: Array[Byte]): BlockCapsule

}
