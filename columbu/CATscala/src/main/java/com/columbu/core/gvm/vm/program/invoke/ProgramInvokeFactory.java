/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */
package com.galileo.core.gvm.vm.program.invoke;

import com.galileo.core.gvm.vm.DataWord;
import com.galileo.core.gvm.vm.program.InternalTransaction;
import com.galileo.core.gvm.vm.program.InternalTransaction.ExecutorType;
import com.galileo.core.gvm.vm.program.Program;
import com.galileo.db.levelDB.Deposit;
import com.galileo.core.exception.ContractValidateException;
import com.galileo.protos.protos.Protocol.Block;
import com.galileo.protos.protos.Protocol.Transaction;

/**
 * @author Roman Mandeleil
 * @since 19.12.2014
 */
public interface ProgramInvokeFactory {

  ProgramInvoke createProgramInvoke(InternalTransaction.TrxType trxType, ExecutorType executorType,
      Transaction tx, Block block, Deposit deposit, long vmStartInUs, long vmShouldEndInUs,
      long energyLimit) throws ContractValidateException;

  ProgramInvoke createProgramInvoke(Program program, DataWord toAddress, DataWord callerAddress,
      DataWord inValue,
      long balanceInt, byte[] dataIn, Deposit deposit, boolean staticCall, boolean byTestingSuite,
      long vmStartInUs, long vmShouldEndInUs, long energyLimit);


}
