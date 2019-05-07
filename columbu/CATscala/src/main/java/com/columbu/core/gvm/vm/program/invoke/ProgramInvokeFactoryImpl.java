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

import static com.galileo.core.gvm.vm.program.InternalTransaction.TrxType.TRX_CONTRACT_CALL_TYPE;
import static com.galileo.core.gvm.vm.program.InternalTransaction.TrxType.TRX_CONTRACT_CREATION_TYPE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.galileo.core.gvm.vm.DataWord;
import com.galileo.core.gvm.vm.program.InternalTransaction;
import com.galileo.core.gvm.vm.program.InternalTransaction.ExecutorType;
import com.galileo.core.gvm.vm.program.Program;
import com.galileo.db.levelDB.Deposit;
import com.galileo.utils.ByteUtil;
import com.galileo.core.Wallet;
import com.galileo.core.capsule.ContractCapsule;
import com.galileo.core.exception.ContractValidateException;
import com.galileo.protos.protos.Contract;
import com.galileo.protos.protos.Contract.CreateSmartContract;
import com.galileo.protos.protos.Protocol.Block;
import com.galileo.protos.protos.Protocol.Transaction;

/**
 * @author Roman Mandeleil
 * @since 08.06.2014
 */
@Component("ProgramInvokeFactory")
public class ProgramInvokeFactoryImpl implements ProgramInvokeFactory {

  private static final Logger logger = LoggerFactory.getLogger("VM");

  // Invocation by the wire tx
  @Override
  public ProgramInvoke createProgramInvoke(InternalTransaction.TrxType trxType,
      ExecutorType executorType, Transaction tx, Block block, Deposit deposit, long vmStartInUs,
      long vmShouldEndInUs, long energyLimit) throws ContractValidateException {
    byte[] contractAddress;
    byte[] ownerAddress;
    long balance;
    byte[] data;
    byte[] lastHash = null;
    byte[] coinbase = null;
    long timestamp = 0L;
    long number = -1L;

    if (trxType == TRX_CONTRACT_CREATION_TYPE) {
      CreateSmartContract contract = ContractCapsule.getSmartContractFromTransaction(tx);
      contractAddress = Wallet.generateContractAddress(tx);
      ownerAddress = contract.getOwnerAddress().toByteArray();
      balance = deposit.getBalance(ownerAddress);
      data = ByteUtil.EMPTY_BYTE_ARRAY;
      long callValue = contract.getNewContract().getCallValue();

      switch (executorType) {
        case ET_NORMAL_TYPE:
        case ET_PRE_TYPE:
          if (null != block) {
            lastHash = block.getBlockHeader().getRawDataOrBuilder().getParentHash().toByteArray();
            coinbase = block.getBlockHeader().getRawDataOrBuilder().getWitnessAddress().toByteArray();
            timestamp = block.getBlockHeader().getRawDataOrBuilder().getTimestamp() / 1000;
            number = block.getBlockHeader().getRawDataOrBuilder().getNumber();
          }
          break;
        default:
          break;
      }

      return new ProgramInvokeImpl(contractAddress, ownerAddress, ownerAddress, balance, callValue, data,
          lastHash, coinbase, timestamp, number, deposit, vmStartInUs, vmShouldEndInUs,
          energyLimit);

    } else if (trxType == TRX_CONTRACT_CALL_TYPE) {
      Contract.TriggerSmartContract contract = ContractCapsule
          .getTriggerContractFromTransaction(tx);
      /***         ADDRESS op       ***/
      // YP: Get address of currently executing account.
      // byte[] address = tx.isContractCreation() ? tx.getContractAddress() : tx.getReceiveAddress();
      byte[] address = contract.getContractAddress().toByteArray();

      /***         ORIGIN op       ***/
      // YP: This is the sender of original transaction; it is never a contract.
      // byte[] origin = tx.getSender();
      byte[] origin = contract.getOwnerAddress().toByteArray();

      /***         CALLER op       ***/
      // YP: This is the address of the account that is directly responsible for this execution.
      //byte[] caller = tx.getSender();
      byte[] caller = contract.getOwnerAddress().toByteArray();

      /***         BALANCE op       ***/
      // byte[] balance = repository.getBalance(address).toByteArray();
      balance = deposit.getBalance(caller);

      /***        CALLVALUE op      ***/
      // byte[] callValue = nullToEmpty(tx.getValue());
      long callValue = contract.getCallValue();

      /***     CALLDATALOAD  op   ***/
      /***     CALLDATACOPY  op   ***/
      /***     CALLDATASIZE  op   ***/
      // byte[] data = tx.isContractCreation() ? ByteUtil.EMPTY_BYTE_ARRAY : nullToEmpty(tx.getData());
      data = contract.getData().toByteArray();

      // dropLimit = contract.getTrxEnergyLimitInUs().toByteArray();
      switch (executorType) {
        case ET_CONSTANT_TYPE:
          break;
        case ET_PRE_TYPE:
        case ET_NORMAL_TYPE:
          if (null != block) {
            /***    PREVHASH  op  ***/
            lastHash = block.getBlockHeader().getRawDataOrBuilder().getParentHash().toByteArray();
            /***   COINBASE  op ***/
            coinbase = block.getBlockHeader().getRawDataOrBuilder().getWitnessAddress().toByteArray();
            /*** TIMESTAMP  op  ***/
            timestamp = block.getBlockHeader().getRawDataOrBuilder().getTimestamp() / 1000;
            /*** NUMBER  op  ***/
            number = block.getBlockHeader().getRawDataOrBuilder().getNumber();
          }
          break;
        default:
          break;
      }

      return new ProgramInvokeImpl(address, origin, caller, balance, callValue, data,
          lastHash, coinbase, timestamp, number, deposit, vmStartInUs, vmShouldEndInUs,
          energyLimit);
    }
    throw new ContractValidateException("Unknown contract type");
  }

  /**
   * This invocation created for contract call contract
   */
  @Override
  public ProgramInvoke createProgramInvoke(Program program, DataWord toAddress,
      DataWord callerAddress,
      DataWord inValue, long balanceInt, byte[] dataIn,
      Deposit deposit, boolean isStaticCall, boolean byTestingSuite, long vmStartInUs,
      long vmShouldEndInUs, long energyLimit) {

    DataWord address = toAddress;
    DataWord origin = program.getOriginAddress();
    DataWord caller = callerAddress;
    DataWord balance = new DataWord(balanceInt);
    DataWord callValue = inValue;

    byte[] data = dataIn;
    DataWord lastHash = program.getPrevHash();
    DataWord coinbase = program.getCoinbase();
    DataWord timestamp = program.getTimestamp();
    DataWord number = program.getNumber();
    DataWord difficulty = program.getDifficulty();

    return new ProgramInvokeImpl(address, origin, caller, balance, callValue,
        data, lastHash, coinbase, timestamp, number, difficulty,
        deposit, program.getCallDeep() + 1, isStaticCall, byTestingSuite, vmStartInUs,
        vmShouldEndInUs, energyLimit);
  }

}
