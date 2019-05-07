package com.galileo.db.levelDB;

import com.galileo.core.gvm.vm.DataWord;
import com.galileo.core.gvm.vm.program.Storage;
import com.galileo.core.capsule.AccountCapsule;
import com.galileo.core.capsule.BlockCapsule;
import com.galileo.core.capsule.BytesCapsule;
import com.galileo.core.capsule.ContractCapsule;
import com.galileo.core.capsule.ProposalCapsule;
import com.galileo.core.capsule.TransactionCapsule;
import com.galileo.core.capsule.VotesCapsule;
import com.galileo.core.capsule.WitnessCapsule;
import com.galileo.db.levelDB.store.Manager;
import com.galileo.protos.protos.Protocol;

public interface Deposit {

  Manager getDbManager();

  AccountCapsule createAccount(byte[] address, Protocol.AccountType type);

  AccountCapsule createAccount(byte[] address, String accountName, Protocol.AccountType type);

  AccountCapsule getAccount(byte[] address);

  WitnessCapsule getWitness(byte[] address);

  VotesCapsule getVotesCapsule(byte[] address);

  ProposalCapsule getProposalCapsule(byte[] id);

  BytesCapsule getDynamic(byte[] bytesKey);

  void deleteContract(byte[] address);

  void createContract(byte[] address, ContractCapsule contractCapsule);

  ContractCapsule getContract(byte[] address);

  void saveCode(byte[] codeHash, byte[] code);

  byte[] getCode(byte[] codeHash);

  //byte[] getCodeHash(byte[] address);

  void putStorageValue(byte[] address, DataWord key, DataWord value);

  DataWord getStorageValue(byte[] address, DataWord key);

  Storage getStorage(byte[] address);

  long getBalance(byte[] address);

  long addBalance(byte[] address, long value);


  Deposit newDepositChild();

  void setParent(Deposit deposit);

  void flush();

  void commit();

  void putAccount(Key key, Value value);

  void putTransaction(Key key, Value value);

  void putBlock(Key key, Value value);

  void putWitness(Key key, Value value);

  void putCode(Key key, Value value);

  void putContract(Key key, Value value);

  void putStorage(Key key, Storage cache);

  void putVotes(Key key, Value value);

  void putProposal(Key key, Value value);

  void putDynamicProperties(Key key, Value value);

  void putAccountValue(byte[] address, AccountCapsule accountCapsule);

  void putVoteValue(byte[] address, VotesCapsule votesCapsule);

  void putProposalValue(byte[] address, ProposalCapsule proposalCapsule);

  void putDynamicPropertiesWithLatestProposalNum(long num);

  long getLatestProposalNum();

  long getWitnessAllowanceFrozenTime();

  long getMaintenanceTimeInterval();

  long getNextMaintenanceTime();

  TransactionCapsule getTransaction(byte[] trxHash);

  BlockCapsule getBlock(byte[] blockHash);

}
