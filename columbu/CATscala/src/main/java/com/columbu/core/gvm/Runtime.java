package com.galileo.core.gvm;

import com.galileo.core.gvm.vm.program.InternalTransaction.TrxType;
import com.galileo.core.gvm.vm.program.ProgramResult;
import com.galileo.core.exception.ContractExeException;
import com.galileo.core.exception.ContractValidateException;
import com.galileo.core.exception.VMIllegalException;


public interface Runtime {

  boolean isCallConstant() throws ContractValidateException;

  void execute() throws ContractValidateException, ContractExeException, VMIllegalException;

  void go();

  TrxType getTrxType();

  void finalization();

  ProgramResult getResult();

  String getRuntimeError();
}
