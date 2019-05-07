package com.galileo.consensus;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.galileo.core.capsule.TransactionResultCapsule;
import com.galileo.core.exception.ContractExeException;
import com.galileo.core.exception.ContractValidateException;

public interface Actuator {

  boolean execute(TransactionResultCapsule result) throws ContractExeException;

  boolean validate() throws ContractValidateException;

  ByteString getOwnerAddress() throws InvalidProtocolBufferException;

  long calcFee();

}
