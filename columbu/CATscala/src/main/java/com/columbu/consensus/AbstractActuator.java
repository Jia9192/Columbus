package com.galileo.consensus;

import com.google.protobuf.Any;
import com.galileo.db.levelDB.Deposit;
import com.galileo.db.levelDB.store.Manager;

public abstract class AbstractActuator implements Actuator {

  protected Any contract;
  protected Manager dbManager;

  public Deposit getDeposit() {
    return deposit;
  }

  public void setDeposit(Deposit deposit) {
    this.deposit = deposit;
  }

  protected Deposit deposit;

  AbstractActuator(Any contract, Manager dbManager) {
    this.contract = contract;
    this.dbManager = dbManager;
  }
}
