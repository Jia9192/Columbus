package com.galileo.core;

import com.google.protobuf.ByteString;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.galileo.protos.api.GrpcAPI.TransactionList;
import com.galileo.utils.ByteArray;
import com.galileo.core.capsule.TransactionInfoCapsule;
import com.galileo.db.levelDB.store.Manager;
import com.galileo.db.levelDB.api.StoreAPI;
import com.galileo.core.exception.BadItemException;
import com.galileo.core.exception.NonUniqueObjectException;
import com.galileo.protos.protos.Protocol.Transaction;
import com.galileo.protos.protos.Protocol.TransactionInfo;

@Slf4j
@Component
public class WalletSolidity {

  @Autowired
  private StoreAPI storeAPI;
  @Autowired
  private Manager dbManager;

  public Transaction getTransactionById(ByteString id) {
    try {
      Transaction transactionById = storeAPI
          .getTransactionById(ByteArray.toHexString(id.toByteArray()));
      return transactionById;
    } catch (NonUniqueObjectException e) {
      e.printStackTrace();
    }
    return null;
  }

  public TransactionInfo getTransactionInfoById(ByteString id) {
    if (Objects.isNull(id)) {
      return null;
    }
    TransactionInfoCapsule transactionInfoCapsule = null;
    try {
      transactionInfoCapsule = dbManager.getTransactionHistoryStore()
          .get(id.toByteArray());
    } catch (BadItemException e) {
    }
    if (transactionInfoCapsule != null) {
      return transactionInfoCapsule.getInstance();
    }
    return null;
  }

  public TransactionList getTransactionsFromThis(ByteString thisAddress, long offset, long limit) {
    List<Transaction> transactionsFromThis = storeAPI
        .getTransactionsFromThis(ByteArray.toHexString(thisAddress.toByteArray()), offset, limit);
    TransactionList transactionList = TransactionList.newBuilder()
        .addAllTransaction(transactionsFromThis).build();
    return transactionList;
  }

  public TransactionList getTransactionsToThis(ByteString toAddress, long offset, long limit) {
    List<Transaction> transactionsToThis = storeAPI
        .getTransactionsToThis(ByteArray.toHexString(toAddress.toByteArray()), offset, limit);
    TransactionList transactionList = TransactionList.newBuilder()
        .addAllTransaction(transactionsToThis).build();
    return transactionList;
  }
}
