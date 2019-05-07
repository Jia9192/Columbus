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
package com.galileo.core.gvm.vm.program;

import static java.lang.StrictMath.min;
import static java.lang.String.format;
import static org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_ARRAY;
import static org.apache.commons.lang3.ArrayUtils.getLength;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.ArrayUtils.nullToEmpty;
import static com.galileo.core.gvm.utils.MUtil.convertToTronAddress;
import static com.galileo.core.gvm.utils.MUtil.transfer;
import static com.galileo.utils.BIUtil.isPositive;
import static com.galileo.utils.BIUtil.toBI;

import com.google.protobuf.ByteString;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.spongycastle.util.encoders.Hex;
import com.galileo.core.gvm.config.VMConfig;
import com.galileo.core.gvm.vm.DataWord;
import com.galileo.core.gvm.vm.EnergyCost;
import com.galileo.core.gvm.vm.MessageCall;
import com.galileo.core.gvm.vm.OpCode;
import com.galileo.core.gvm.vm.PrecompiledContracts;
import com.galileo.core.gvm.vm.VM;
import com.galileo.core.gvm.vm.program.invoke.ProgramInvoke;
import com.galileo.core.gvm.vm.program.invoke.ProgramInvokeFactory;
import com.galileo.core.gvm.vm.program.invoke.ProgramInvokeFactoryImpl;
import com.galileo.core.gvm.vm.program.listener.CompositeProgramListener;
import com.galileo.core.gvm.vm.program.listener.ProgramListenerAware;
import com.galileo.core.gvm.vm.program.listener.ProgramStorageChangeListener;
import com.galileo.core.gvm.vm.trace.ProgramTrace;
import com.galileo.core.gvm.vm.trace.ProgramTraceListener;
import com.galileo.db.levelDB.Deposit;
import com.galileo.utils.ByteUtil;
import com.galileo.utils.FastByteComparisons;
import com.galileo.utils.Utils;
import com.galileo.core.Wallet;
import com.galileo.consensus.TransferActuator;
import com.galileo.core.capsule.AccountCapsule;
import com.galileo.core.capsule.BlockCapsule;
import com.galileo.core.capsule.ContractCapsule;
import com.galileo.core.config.args.Args;
import com.galileo.core.exception.ContractValidateException;
import com.galileo.core.exception.TronException;
import com.galileo.protos.protos.Protocol;
import com.galileo.protos.protos.Protocol.SmartContract;

/**
 * @author Roman Mandeleil
 * @since 01.06.2014
 */

@Slf4j(topic = "Program")
public class Program {

  private static final int MAX_DEPTH = 64;
  //Max size for stack checks
  private static final int MAX_STACKSIZE = 1024;

  private BlockCapsule blockCap;

  private long nonce;
  private byte[] rootTransactionId;
  private Boolean isRootCallConstant;

  private InternalTransaction transaction;

  private ProgramInvoke invoke;
  private ProgramInvokeFactory programInvokeFactory = new ProgramInvokeFactoryImpl();

  private ProgramOutListener listener;
  private ProgramTraceListener traceListener;
  private ProgramStorageChangeListener storageDiffListener = new ProgramStorageChangeListener();
  private CompositeProgramListener programListener = new CompositeProgramListener();

  private Stack stack;
  private Memory memory;
  private ContractState contractState;
  private byte[] returnDataBuffer;

  private ProgramResult result = new ProgramResult();
  private ProgramTrace trace = new ProgramTrace();

  private byte[] ops;
  private int pc;
  private byte lastOp;
  private byte previouslyExecutedOp;
  private boolean stopped;

  private ProgramPrecompile programPrecompile;

  private final VMConfig config;

  public Program(byte[] ops, ProgramInvoke programInvoke) {
    this(ops, programInvoke, null);
  }

  public Program(byte[] ops, ProgramInvoke programInvoke, InternalTransaction transaction) {
    this(ops, programInvoke, transaction, VMConfig.getInstance(), null);
  }

  public Program(byte[] ops, ProgramInvoke programInvoke, InternalTransaction transaction,
      VMConfig config, BlockCapsule blockCap) {
    this(null, ops, programInvoke, transaction, config, blockCap);
  }

  public Program(byte[] codeHash, byte[] ops, ProgramInvoke programInvoke,
      InternalTransaction transaction, VMConfig config, BlockCapsule blockCap) {
    this.config = config;
    this.invoke = programInvoke;
    this.transaction = transaction;
    this.blockCap = blockCap;
    this.ops = nullToEmpty(ops);

    traceListener = new ProgramTraceListener(config.vmTrace());
    this.memory = setupProgramListener(new Memory());
    this.stack = setupProgramListener(new Stack());
    this.contractState = setupProgramListener(new ContractState(programInvoke));
    this.trace = new ProgramTrace(config, programInvoke);
    this.nonce = transaction.getNonce();
  }

  public byte[] getRootTransactionId() {
    return rootTransactionId.clone();
  }

  public void setRootTransactionId(byte[] rootTransactionId) {
    this.rootTransactionId = rootTransactionId.clone();
  }

  public long getNonce() {
    return nonce;
  }

  public void setNonce(long nonceValue) {
    nonce = nonceValue;
  }

  public Boolean getRootCallConstant() {
    return isRootCallConstant;
  }

  public void setRootCallConstant(Boolean rootCallConstant) {
    isRootCallConstant = rootCallConstant;
  }

  public ProgramPrecompile getProgramPrecompile() {
    if (programPrecompile == null) {
      programPrecompile = ProgramPrecompile.compile(ops);
    }
    return programPrecompile;
  }

  public int getCallDeep() {
    return invoke.getCallDeep();
  }

  /**
   *
   * @param energyLimit
   * @param senderAddress
   * @param transferAddress the address send trx to.
   * @param value           the trx value transferred in the internaltransaction
   * @param data
   * @param note
   * @param nonce
   * @return
   */
  private InternalTransaction addInternalTx(DataWord energyLimit, byte[] senderAddress,
      byte[] transferAddress,
      long value, byte[] data, String note, long nonce) {

    // todo: now, internal transaction needn't energylimit
    InternalTransaction result = null;
    if (transaction != null) {
      result = getResult().addInternalTransaction(transaction.getHash(), getCallDeep(),
          senderAddress, transferAddress, value, data, note, nonce);
    }

    return result;
  }

  private <T extends ProgramListenerAware> T setupProgramListener(T programListenerAware) {
    if (programListener.isEmpty()) {
      programListener.addListener(traceListener);
      programListener.addListener(storageDiffListener);
    }

    programListenerAware.setProgramListener(programListener);

    return programListenerAware;
  }

  public Map<DataWord, DataWord> getStorageDiff() {
    return storageDiffListener.getDiff();
  }

  public byte getOp(int pc) {
    return (getLength(ops) <= pc) ? 0 : ops[pc];
  }

  public byte getCurrentOp() {
    return isEmpty(ops) ? 0 : ops[pc];
  }

  /**
   * Last Op can only be set publicly (no getLastOp method), is used for logging.
   */
  public void setLastOp(byte op) {
    this.lastOp = op;
  }

  /**
   * Should be set only after the OP is fully executed.
   */
  public void setPreviouslyExecutedOp(byte op) {
    this.previouslyExecutedOp = op;
  }

  /**
   * Returns the last fully executed OP.
   */
  public byte getPreviouslyExecutedOp() {
    return this.previouslyExecutedOp;
  }

  public void stackPush(byte[] data) {
    stackPush(new DataWord(data));
  }

  public void stackPushZero() {
    stackPush(new DataWord(0));
  }

  public void stackPushOne() {
    DataWord stackWord = new DataWord(1);
    stackPush(stackWord);
  }

  public void stackPush(DataWord stackWord) {
    verifyStackOverflow(0, 1); //Sanity Check
    stack.push(stackWord);
  }

  public Stack getStack() {
    return this.stack;
  }

  public int getPC() {
    return pc;
  }

  public void setPC(DataWord pc) {
    this.setPC(pc.intValue());
  }

  public void setPC(int pc) {
    this.pc = pc;

    if (this.pc >= ops.length) {
      stop();
    }
  }

  public boolean isStopped() {
    return stopped;
  }

  public void stop() {
    stopped = true;
  }

  public void setHReturn(byte[] buff) {
    getResult().setHReturn(buff);
  }

  public void step() {
    setPC(pc + 1);
  }

  public byte[] sweep(int n) {

    if (pc + n > ops.length) {
      stop();
    }

    byte[] data = Arrays.copyOfRange(ops, pc, pc + n);
    pc += n;
    if (pc >= ops.length) {
      stop();
    }

    return data;
  }

  public DataWord stackPop() {
    return stack.pop();
  }

  /**
   * Verifies that the stack is at least <code>stackSize</code>
   *
   * @param stackSize int
   * @throws StackTooSmallException If the stack is smaller than <code>stackSize</code>
   */
  public void verifyStackSize(int stackSize) {
    if (stack.size() < stackSize) {
      throw Exception.tooSmallStack(stackSize, stack.size());
    }
  }

  public void verifyStackOverflow(int argsReqs, int returnReqs) {
    if ((stack.size() - argsReqs + returnReqs) > MAX_STACKSIZE) {
      throw new StackTooLargeException(
          "Expected: overflow " + MAX_STACKSIZE + " elements stack limit");
    }
  }

  public int getMemSize() {
    return memory.size();
  }

  public void memorySave(DataWord addrB, DataWord value) {
    memory.write(addrB.intValue(), value.getData(), value.getData().length, false);
  }

  public void memorySaveLimited(int addr, byte[] data, int dataSize) {
    memory.write(addr, data, dataSize, true);
  }

  public void memorySave(int addr, byte[] value) {
    memory.write(addr, value, value.length, false);
  }

  public void memoryExpand(DataWord outDataOffs, DataWord outDataSize) {
    if (!outDataSize.isZero()) {
      memory.extend(outDataOffs.intValue(), outDataSize.intValue());
    }
  }

  /**
   * Allocates a piece of memory and stores value at given offset address
   *
   * @param addr is the offset address
   * @param allocSize size of memory needed to write
   * @param value the data to write to memory
   */
  public void memorySave(int addr, int allocSize, byte[] value) {
    memory.extendAndWrite(addr, allocSize, value);
  }


  public DataWord memoryLoad(DataWord addr) {
    return memory.readWord(addr.intValue());
  }

  public DataWord memoryLoad(int address) {
    return memory.readWord(address);
  }

  public byte[] memoryChunk(int offset, int size) {
    return memory.read(offset, size);
  }

  /**
   * Allocates extra memory in the program for a specified size, calculated from a given offset
   *
   * @param offset the memory address offset
   * @param size the number of bytes to allocate
   */
  public void allocateMemory(int offset, int size) {
    memory.extend(offset, size);
  }


  public void suicide(DataWord obtainerAddress) {

    byte[] owner = convertToTronAddress(getContractAddress().getLast20Bytes());
    byte[] obtainer = convertToTronAddress(obtainerAddress.getLast20Bytes());
    long balance = getContractState().getBalance(owner);

    if (logger.isDebugEnabled()) {
      logger.debug("Transfer to: [{}] heritage: [{}]",
          Hex.toHexString(obtainer),
          balance);
    }

    increaseNonce();
    addInternalTx(null, owner, obtainer, balance, null, "suicide", nonce);

    if (FastByteComparisons.compareTo(owner, 0, 20, obtainer, 0, 20) == 0) {
      // if owner == obtainer just zeroing account according to Yellow Paper
      getContractState().addBalance(owner, -balance);
    } else {
      try {
        transfer(getContractState(), owner, obtainer, balance);
      } catch (ContractValidateException e) {
        throw new BytecodeExecutionException("transfer failure");
      }
    }
    getResult().addDeleteAccount(this.getContractAddress());
  }

  public Deposit getContractState() {
    return this.contractState;
  }

  @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
  public void createContract(DataWord value, DataWord memStart, DataWord memSize) {
    returnDataBuffer = null; // reset return buffer right before the call

    if (getCallDeep() == MAX_DEPTH) {
      stackPushZero();
      return;
    }

    byte[] senderAddress = convertToTronAddress(this.getContractAddress().getLast20Bytes());

    long endowment = value.value().longValueExact();
    if (getContractState().getBalance(senderAddress) < endowment) {
      stackPushZero();
      return;
    }

    // [1] FETCH THE CODE FROM THE MEMORY
    byte[] programCode = memoryChunk(memStart.intValue(), memSize.intValue());

    if (logger.isDebugEnabled()) {
      logger.debug("creating a new contract inside contract run: [{}]",
          Hex.toHexString(senderAddress));
    }

    byte[] newAddress = Wallet
        .generateContractAddress(rootTransactionId, nonce);

    AccountCapsule existingAddr = getContractState().getAccount(newAddress);
    boolean contractAlreadyExists = existingAddr != null;

    Deposit deposit = getContractState().newDepositChild();

    //In case of hashing collisions, check for any balance before createAccount()
    long oldBalance = deposit.getBalance(newAddress);

    SmartContract newSmartContract = SmartContract.newBuilder()
        .setContractAddress(ByteString.copyFrom(newAddress)).setConsumeUserResourcePercent(100)
        .setOriginAddress(ByteString.copyFrom(senderAddress)).build();
    deposit.createContract(newAddress, new ContractCapsule(newSmartContract));
    deposit.createAccount(newAddress, "CreatedByContract",
        Protocol.AccountType.Contract);

    deposit.addBalance(newAddress, oldBalance);
    // [4] TRANSFER THE BALANCE
    long newBalance = 0L;
    if (!byTestingSuite() && endowment > 0) {
      try {
        TransferActuator.validateForSmartContract(deposit, senderAddress, newAddress, endowment);
      } catch (ContractValidateException e) {
        throw new BytecodeExecutionException("validateForSmartContract failure");
      }
      deposit.addBalance(senderAddress, -endowment);
      newBalance = deposit.addBalance(newAddress, endowment);
    }

    // actual energy subtract
    DataWord energyLimit = this.getCreateEnergy(getEnergyLimitLeft());
    spendEnergy(energyLimit.longValue(), "internal call");

    increaseNonce();
    // [5] COOK THE INVOKE AND EXECUTE
    InternalTransaction internalTx = addInternalTx(null, senderAddress, newAddress, endowment,
        programCode, "create", nonce);
    long vmStartInUs = System.nanoTime() / 1000;
    ProgramInvoke programInvoke = programInvokeFactory.createProgramInvoke(
        this, new DataWord(newAddress), getContractAddress(), value,
        newBalance, null, deposit, false, byTestingSuite(), vmStartInUs,
        getVmShouldEndInUs(), energyLimit.longValueSafe());

    ProgramResult result = ProgramResult.createEmpty();

    if (contractAlreadyExists) {
      result.setException(new BytecodeExecutionException(
          "Trying to create a contract with existing contract address: 0x" + Hex
              .toHexString(newAddress)));
    } else if (isNotEmpty(programCode)) {
      VM vm = new VM(config);
      Program program = new Program(programCode, programInvoke, internalTx, config, this.blockCap);
      program.setRootTransactionId(this.rootTransactionId);
      program.setRootCallConstant(this.isRootCallConstant);
      vm.play(program);
      result = program.getResult();
      getTrace().merge(program.getTrace());
      // always commit nonce
      this.nonce = program.nonce;

    }

    // 4. CREATE THE CONTRACT OUT OF RETURN
    byte[] code = result.getHReturn();

    long saveCodeEnergy = getLength(code) * EnergyCost.getInstance().getCREATE_DATA();

    long afterSpend = programInvoke.getEnergyLimit() - result.getEnergyUsed() - saveCodeEnergy;
    if (!result.isRevert()) {
      if (afterSpend < 0) {
        result.setException(
            Program.Exception.notEnoughSpendEnergy("No energy to save just created contract code",
                saveCodeEnergy, programInvoke.getEnergyLimit() - result.getEnergyUsed()));
      } else {
        result.spendEnergy(saveCodeEnergy);
        deposit.saveCode(newAddress, code);
      }
    }

    getResult().merge(result);

    if (result.getException() != null || result.isRevert()) {
      logger.debug("contract run halted by Exception: contract: [{}], exception: [{}]",
          Hex.toHexString(newAddress),
          result.getException());

      internalTx.reject();
      result.rejectInternalTransactions();

      stackPushZero();

      if (result.getException() != null) {
        return;
      } else {
        returnDataBuffer = result.getHReturn();
      }
    } else {
      if (!byTestingSuite()) {
        deposit.commit();
      }

      // IN SUCCESS PUSH THE ADDRESS INTO THE STACK
      stackPush(new DataWord(newAddress));
    }

    // 5. REFUND THE REMAIN Energy
    refundEnergyAfterVM(energyLimit, result);
  }

  public void refundEnergyAfterVM(DataWord energyLimit, ProgramResult result) {

    long refundEnergy = energyLimit.longValueSafe() - result.getEnergyUsed();
    if (refundEnergy > 0) {
      refundEnergy(refundEnergy, "remain energy from the internal call");
      if (logger.isDebugEnabled()) {
        logger.debug("The remaining energy is refunded, account: [{}], energy: [{}] ",
            Hex.toHexString(convertToTronAddress(getContractAddress().getLast20Bytes())),
            refundEnergy);
      }
    }
  }

  /**
   * That method is for internal code invocations
   * <p/>
   * - Normal calls invoke a specified contract which updates itself - Stateless calls invoke code
   * from another contract, within the context of the caller
   *
   * @param msg is the message call object
   */
  public void callToAddress(MessageCall msg) {
    returnDataBuffer = null; // reset return buffer right before the call

    if (getCallDeep() == MAX_DEPTH) {
      stackPushZero();
      refundEnergy(msg.getEnergy().longValue(), " call deep limit reach");
      return;
    }

    byte[] data = memoryChunk(msg.getInDataOffs().intValue(), msg.getInDataSize().intValue());

    // FETCH THE SAVED STORAGE
    byte[] codeAddress = convertToTronAddress(msg.getCodeAddress().getLast20Bytes());
    byte[] senderAddress = convertToTronAddress(getContractAddress().getLast20Bytes());
    byte[] contextAddress = msg.getType().callIsStateless() ? senderAddress : codeAddress;

    if (logger.isDebugEnabled()) {
      logger.debug(msg.getType().name()
              + " for existing contract: address: [{}], outDataOffs: [{}], outDataSize: [{}]  ",
          Hex.toHexString(contextAddress), msg.getOutDataOffs().longValue(),
          msg.getOutDataSize().longValue());
    }

    //Repository track = getContractState().startTracking();
    Deposit deposit = getContractState().newDepositChild();

    // 2.1 PERFORM THE VALUE (endowment) PART
    long endowment = msg.getEndowment().value().longValueExact();
    long senderBalance = deposit.getBalance(senderAddress);
    if (senderBalance < endowment) {
      stackPushZero();
      refundEnergy(msg.getEnergy().longValue(), "refund energy from message call");
      return;
    }

    // FETCH THE CODE
    AccountCapsule accountCapsule = getContractState().getAccount(codeAddress);

    byte[] programCode =
        accountCapsule != null ? getContractState().getCode(codeAddress) : EMPTY_BYTE_ARRAY;

    long contextBalance = 0L;
    if (byTestingSuite()) {
      // This keeps track of the calls created for a test
      getResult().addCallCreate(data, contextAddress,
          msg.getEnergy().getNoLeadZeroesData(),
          msg.getEndowment().getNoLeadZeroesData());
    } else if (!ArrayUtils.isEmpty(senderAddress) && !ArrayUtils.isEmpty(contextAddress)
        && senderAddress != contextAddress && endowment > 0) {
      try {
        TransferActuator
            .validateForSmartContract(deposit, senderAddress, contextAddress, endowment);
      } catch (ContractValidateException e) {
        throw new BytecodeExecutionException("validateForSmartContract failure");
      }
      deposit.addBalance(senderAddress, -endowment);
      contextBalance = deposit.addBalance(contextAddress, endowment);
    }

    // CREATE CALL INTERNAL TRANSACTION
    increaseNonce();
    InternalTransaction internalTx = addInternalTx(null, senderAddress, contextAddress,
        endowment, data, "call", nonce);

    ProgramResult result = null;
    if (isNotEmpty(programCode)) {
      long vmStartInUs = System.nanoTime() / 1000;
      ProgramInvoke programInvoke = programInvokeFactory.createProgramInvoke(
          this, new DataWord(contextAddress),
          msg.getType().callIsDelegate() ? getCallerAddress() : getContractAddress(),
          msg.getType().callIsDelegate() ? getCallValue() : msg.getEndowment(),
          contextBalance, data, deposit, msg.getType().callIsStatic() || isStaticCall(),
          byTestingSuite(), vmStartInUs, getVmShouldEndInUs(), msg.getEnergy().longValueSafe());
      VM vm = new VM(config);
      Program program = new Program(null, programCode, programInvoke, internalTx, config,
          this.blockCap);
      program.setRootTransactionId(this.rootTransactionId);
      program.setRootCallConstant(this.isRootCallConstant);
      vm.play(program);
      result = program.getResult();

      getTrace().merge(program.getTrace());
      getResult().merge(result);
      // always commit nonce
      this.nonce = program.nonce;

      if (result.getException() != null || result.isRevert()) {
        logger.debug("contract run halted by Exception: contract: [{}], exception: [{}]",
            Hex.toHexString(contextAddress),
            result.getException());

        internalTx.reject();
        result.rejectInternalTransactions();

        // deposit.rollback();
        stackPushZero();

        if (result.getException() != null) {
          return;
        }
      } else {
        // 4. THE FLAG OF SUCCESS IS ONE PUSHED INTO THE STACK
        deposit.commit();
        stackPushOne();
      }

      if (byTestingSuite()) {
        logger.debug("Testing run, skipping storage diff listener");
      }
      // else if (Arrays.equals(transaction.getReceiveAddress(), internalTx.getReceiveAddress())) {
      //   storageDiffListener.merge(program.getStorageDiff());
      // }
    } else {
      // 4. THE FLAG OF SUCCESS IS ONE PUSHED INTO THE STACK
      deposit.commit();
      stackPushOne();
    }

    // 3. APPLY RESULTS: result.getHReturn() into out_memory allocated
    if (result != null) {
      byte[] buffer = result.getHReturn();
      int offset = msg.getOutDataOffs().intValue();
      int size = msg.getOutDataSize().intValue();

      memorySaveLimited(offset, buffer, size);

      returnDataBuffer = buffer;
    }

    // 5. REFUND THE REMAIN ENERGY
    if (result != null) {
      BigInteger refundEnergy = msg.getEnergy().value().subtract(toBI(result.getEnergyUsed()));
      if (isPositive(refundEnergy)) {
        refundEnergy(refundEnergy.longValueExact(), "remaining energy from the internal call");
        if (logger.isDebugEnabled()) {
          logger.debug("The remaining energy refunded, account: [{}], energy: [{}] ",
              Hex.toHexString(senderAddress),
              refundEnergy.toString());
        }
      }
    } else {
      refundEnergy(msg.getEnergy().longValue(), "remaining esnergy from the internal call");
    }
  }

  public void increaseNonce() {
    nonce++;
  }

  public void resetNonce() {
    nonce = 0;
  }

  public void spendEnergy(long energyValue, String opName) {
    if (getEnergylimitLeftLong() < energyValue) {
      throw new OutOfEnergyException(
          "Not enough energy for '%s' operation executing: curInvokeEnergyLimit[%d], curOpEnergy[%d], usedEnergy[%d]",
          opName, invoke.getEnergyLimit(), energyValue, getResult().getEnergyUsed());
    }
    getResult().spendEnergy(energyValue);
  }

  public void checkCPUTimeLimit(String opName) {

    if (Args.getInstance().isDebug()) {
      return;
    }
    if (Args.getInstance().isSolidityNode()) {
      return;
    }
    long vmNowInUs = System.nanoTime() / 1000;
    if (vmNowInUs > getVmShouldEndInUs()) {
      logger.info(
          "minTimeRatio: {}, maxTimeRatio: {}, vm should end time in us: {}, " +
              "vm now time in us: {}, vm start time in us: {}",
          Args.getInstance().getMinTimeRatio(), Args.getInstance().getMaxTimeRatio(),
          getVmShouldEndInUs(), vmNowInUs, getVmStartInUs());
      throw Exception.notEnoughTime(opName);
    }

  }

  public void spendAllEnergy() {
    spendEnergy(getEnergyLimitLeft().longValue(), "Spending all remaining");
  }

  public void refundEnergy(long energyValue, String cause) {
    logger.debug("[{}] Refund for cause: [{}], energy: [{}]", invoke.hashCode(), cause, energyValue);
    getResult().refundEnergy(energyValue);
  }

  public void futureRefundEnergy(long energyValue) {
    logger.debug("Future refund added: [{}]", energyValue);
    getResult().addFutureRefund(energyValue);
  }

  public void resetFutureRefund() {
    getResult().resetFutureRefund();
  }

  public void storageSave(DataWord word1, DataWord word2) {
    DataWord keyWord = word1.clone();
    DataWord valWord = word2.clone();
    getContractState()
        .putStorageValue(convertToTronAddress(getContractAddress().getLast20Bytes()), keyWord,
            valWord);
  }

  public byte[] getCode() {
    return ops.clone();
  }

  public byte[] getCodeAt(DataWord address) {
    byte[] code = invoke.getDeposit().getCode(convertToTronAddress(address.getLast20Bytes()));
    return nullToEmpty(code);
  }

  public DataWord getContractAddress() {
    return invoke.getContractAddress().clone();
  }

  public DataWord getBlockHash(int index) {
    if (index < this.getNumber().longValue()
        && index >= Math.max(256, this.getNumber().longValue()) - 256) {

      BlockCapsule blockCapsule = this.invoke.getBlockByNum(index);

      if (Objects.nonNull(blockCapsule)) {
        return new DataWord(blockCapsule.getBlockId().getBytes());
      } else {
        return DataWord.ZERO.clone();
      }
    } else {
      return DataWord.ZERO.clone();
    }

  }

  public DataWord getBalance(DataWord address) {
    long balance = getContractState().getBalance(convertToTronAddress(address.getLast20Bytes()));
    return new DataWord(balance);
  }

  public DataWord getOriginAddress() {
    return invoke.getOriginAddress().clone();
  }

  public DataWord getCallerAddress() {
    return invoke.getCallerAddress().clone();
  }

  public DataWord getDropPrice() {
    return new DataWord(1);
  }

  public long getEnergylimitLeftLong() {
    return invoke.getEnergyLimit() - getResult().getEnergyUsed();
  }

  public DataWord getEnergyLimitLeft() {
    return new DataWord(invoke.getEnergyLimit() - getResult().getEnergyUsed());
  }

  public long getVmShouldEndInUs() {
    return invoke.getVmShouldEndInUs();
  }

  public DataWord getCallValue() {
    return invoke.getCallValue().clone();
  }

  public DataWord getDataSize() {
    return invoke.getDataSize().clone();
  }

  public DataWord getDataValue(DataWord index) {
    return invoke.getDataValue(index);
  }

  public byte[] getDataCopy(DataWord offset, DataWord length) {
    return invoke.getDataCopy(offset, length);
  }

  public DataWord getReturnDataBufferSize() {
    return new DataWord(getReturnDataBufferSizeI());
  }

  private int getReturnDataBufferSizeI() {
    return returnDataBuffer == null ? 0 : returnDataBuffer.length;
  }

  public byte[] getReturnDataBufferData(DataWord off, DataWord size) {
    if ((long) off.intValueSafe() + size.intValueSafe() > getReturnDataBufferSizeI()) {
      return null;
    }
    return returnDataBuffer == null ? new byte[0] :
        Arrays.copyOfRange(returnDataBuffer, off.intValueSafe(),
            off.intValueSafe() + size.intValueSafe());
  }

  public DataWord storageLoad(DataWord key) {
    DataWord ret = getContractState()
        .getStorageValue(convertToTronAddress(getContractAddress().getLast20Bytes()), key.clone());
    return ret == null ? null : ret.clone();
  }

  public DataWord getPrevHash() {
    return invoke.getPrevHash().clone();
  }

  public DataWord getCoinbase() {
    return invoke.getCoinbase().clone();
  }

  public DataWord getTimestamp() {
    return invoke.getTimestamp().clone();
  }

  public DataWord getNumber() {
    return invoke.getNumber().clone();
  }

  public DataWord getDifficulty() {
    return invoke.getDifficulty().clone();
  }

  public boolean isStaticCall() {
    return invoke.isStaticCall();
  }

  public ProgramResult getResult() {
    return result;
  }

  public void setRuntimeFailure(RuntimeException e) {
    getResult().setException(e);
  }

  public String memoryToString() {
    return memory.toString();
  }

  public void fullTrace() {
    if (logger.isTraceEnabled() || listener != null) {

      StringBuilder stackData = new StringBuilder();
      for (int i = 0; i < stack.size(); ++i) {
        stackData.append(" ").append(stack.get(i));
        if (i < stack.size() - 1) {
          stackData.append("\n");
        }
      }

      if (stackData.length() > 0) {
        stackData.insert(0, "\n");
      }

      StringBuilder memoryData = new StringBuilder();
      StringBuilder oneLine = new StringBuilder();
      if (memory.size() > 320) {
        memoryData.append("... Memory Folded.... ")
            .append("(")
            .append(memory.size())
            .append(") bytes");
      } else {
        for (int i = 0; i < memory.size(); ++i) {

          byte value = memory.readByte(i);
          oneLine.append(ByteUtil.oneByteToHexString(value)).append(" ");

          if ((i + 1) % 16 == 0) {
            String tmp = format("[%4s]-[%4s]", Integer.toString(i - 15, 16),
                Integer.toString(i, 16)).replace(" ", "0");
            memoryData.append("").append(tmp).append(" ");
            memoryData.append(oneLine);
            if (i < memory.size()) {
              memoryData.append("\n");
            }
            oneLine.setLength(0);
          }
        }
      }
      if (memoryData.length() > 0) {
        memoryData.insert(0, "\n");
      }

      StringBuilder opsString = new StringBuilder();
      for (int i = 0; i < ops.length; ++i) {

        String tmpString = Integer.toString(ops[i] & 0xFF, 16);
        tmpString = tmpString.length() == 1 ? "0" + tmpString : tmpString;

        if (i != pc) {
          opsString.append(tmpString);
        } else {
          opsString.append(" >>").append(tmpString).append("");
        }

      }
      if (pc >= ops.length) {
        opsString.append(" >>");
      }
      if (opsString.length() > 0) {
        opsString.insert(0, "\n ");
      }

      logger.trace(" -- OPS --     {}", opsString);
      logger.trace(" -- STACK --   {}", stackData);
      logger.trace(" -- MEMORY --  {}", memoryData);
      logger.trace("\n  Spent Drop: [{}]/[{}]\n  Left Energy:  [{}]\n",
          getResult().getEnergyUsed(),
          invoke.getEnergyLimit(),
          getEnergyLimitLeft().longValue());

      StringBuilder globalOutput = new StringBuilder("\n");
      if (stackData.length() > 0) {
        stackData.append("\n");
      }

      if (pc != 0) {
        globalOutput.append("[Op: ").append(OpCode.code(lastOp).name()).append("]\n");
      }

      globalOutput.append(" -- OPS --     ").append(opsString).append("\n");
      globalOutput.append(" -- STACK --   ").append(stackData).append("\n");
      globalOutput.append(" -- MEMORY --  ").append(memoryData).append("\n");

      if (getResult().getHReturn() != null) {
        globalOutput.append("\n  HReturn: ").append(
            Hex.toHexString(getResult().getHReturn()));
      }

      // sophisticated assumption that msg.data != codedata
      // means we are calling the contract not creating it
      byte[] txData = invoke.getDataCopy(DataWord.ZERO, getDataSize());
      if (!Arrays.equals(txData, ops)) {
        globalOutput.append("\n  msg.data: ").append(Hex.toHexString(txData));
      }
      globalOutput.append("\n\n  Spent Energy: ").append(getResult().getEnergyUsed());

      if (listener != null) {
        listener.output(globalOutput.toString());
      }
    }
  }

  public void saveOpTrace() {
    if (this.pc < ops.length) {
      trace.addOp(ops[pc], pc, getCallDeep(), getEnergyLimitLeft(), traceListener.resetActions());
    }
  }

  public ProgramTrace getTrace() {
    return trace;
  }

  static String formatBinData(byte[] binData, int startPC) {
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < binData.length; i += 16) {
      ret.append(Utils.align("" + Integer.toHexString(startPC + (i)) + ":", ' ', 8, false));
      ret.append(Hex.toHexString(binData, i, min(16, binData.length - i))).append('\n');
    }
    return ret.toString();
  }

  public static String stringifyMultiline(byte[] code) {
    int index = 0;
    StringBuilder sb = new StringBuilder();
    BitSet mask = buildReachableBytecodesMask(code);
    ByteArrayOutputStream binData = new ByteArrayOutputStream();
    int binDataStartPC = -1;

    while (index < code.length) {
      final byte opCode = code[index];
      OpCode op = OpCode.code(opCode);

      if (!mask.get(index)) {
        if (binDataStartPC == -1) {
          binDataStartPC = index;
        }
        binData.write(code[index]);
        index++;
        if (index < code.length) {
          continue;
        }
      }

      if (binDataStartPC != -1) {
        sb.append(formatBinData(binData.toByteArray(), binDataStartPC));
        binDataStartPC = -1;
        binData = new ByteArrayOutputStream();
        if (index == code.length) {
          continue;
        }
      }

      sb.append(Utils.align("" + Integer.toHexString(index) + ":", ' ', 8, false));

      if (op == null) {
        sb.append("<UNKNOWN>: ").append(0xFF & opCode).append("\n");
        index++;
        continue;
      }

      if (op.name().startsWith("PUSH")) {
        sb.append(' ').append(op.name()).append(' ');

        int nPush = op.val() - OpCode.PUSH1.val() + 1;
        byte[] data = Arrays.copyOfRange(code, index + 1, index + nPush + 1);
        BigInteger bi = new BigInteger(1, data);
        sb.append("0x").append(bi.toString(16));
        if (bi.bitLength() <= 32) {
          sb.append(" (").append(new BigInteger(1, data).toString()).append(") ");
        }

        index += nPush + 1;
      } else {
        sb.append(' ').append(op.name());
        index++;
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  static class ByteCodeIterator {

    byte[] code;
    int pc;

    public ByteCodeIterator(byte[] code) {
      this.code = code;
    }

    public void setPC(int pc) {
      this.pc = pc;
    }

    public int getPC() {
      return pc;
    }

    public OpCode getCurOpcode() {
      return pc < code.length ? OpCode.code(code[pc]) : null;
    }

    public boolean isPush() {
      return getCurOpcode() != null && getCurOpcode().name().startsWith("PUSH");
    }

    public byte[] getCurOpcodeArg() {
      if (isPush()) {
        int nPush = getCurOpcode().val() - OpCode.PUSH1.val() + 1;
        byte[] data = Arrays.copyOfRange(code, pc + 1, pc + nPush + 1);
        return data;
      } else {
        return new byte[0];
      }
    }

    public boolean next() {
      pc += 1 + getCurOpcodeArg().length;
      return pc < code.length;
    }
  }

  static BitSet buildReachableBytecodesMask(byte[] code) {
    NavigableSet<Integer> gotos = new TreeSet<>();
    ByteCodeIterator it = new ByteCodeIterator(code);
    BitSet ret = new BitSet(code.length);
    int lastPush = 0;
    int lastPushPC = 0;
    do {
      ret.set(it.getPC()); // reachable bytecode
      if (it.isPush()) {
        lastPush = new BigInteger(1, it.getCurOpcodeArg()).intValue();
        lastPushPC = it.getPC();
      }
      if (it.getCurOpcode() == OpCode.JUMP || it.getCurOpcode() == OpCode.JUMPI) {
        if (it.getPC() != lastPushPC + 1) {
          // some PC arithmetic we totally can't deal with
          // assuming all bytecodes are reachable as a fallback
          ret.set(0, code.length);
          return ret;
        }
        int jumpPC = lastPush;
        if (!ret.get(jumpPC)) {
          // code was not explored yet
          gotos.add(jumpPC);
        }
      }
      if (it.getCurOpcode() == OpCode.JUMP || it.getCurOpcode() == OpCode.RETURN ||
          it.getCurOpcode() == OpCode.STOP) {
        if (gotos.isEmpty()) {
          break;
        }
        it.setPC(gotos.pollFirst());
      }
    } while (it.next());
    return ret;
  }

  public static String stringify(byte[] code) {
    int index = 0;
    StringBuilder sb = new StringBuilder();
    BitSet mask = buildReachableBytecodesMask(code);
    String binData = "";

    while (index < code.length) {
      final byte opCode = code[index];
      OpCode op = OpCode.code(opCode);

      if (op == null) {
        sb.append(" <UNKNOWN>: ").append(0xFF & opCode).append(" ");
        index++;
        continue;
      }

      if (op.name().startsWith("PUSH")) {
        sb.append(' ').append(op.name()).append(' ');

        int nPush = op.val() - OpCode.PUSH1.val() + 1;
        byte[] data = Arrays.copyOfRange(code, index + 1, index + nPush + 1);
        BigInteger bi = new BigInteger(1, data);
        sb.append("0x").append(bi.toString(16)).append(" ");

        index += nPush + 1;
      } else {
        sb.append(' ').append(op.name());
        index++;
      }
    }

    return sb.toString();
  }

  public void addListener(ProgramOutListener listener) {
    this.listener = listener;
  }

  public int verifyJumpDest(DataWord nextPC) {
    if (nextPC.bytesOccupied() > 4) {
      throw Exception.badJumpDestination(-1);
    }
    int ret = nextPC.intValue();
    if (!getProgramPrecompile().hasJumpDest(ret)) {
      throw Exception.badJumpDestination(ret);
    }
    return ret;
  }

  public void callToPrecompiledAddress(MessageCall msg,
      PrecompiledContracts.PrecompiledContract contract) {
    returnDataBuffer = null; // reset return buffer right before the call

    if (getCallDeep() == MAX_DEPTH) {
      stackPushZero();
      this.refundEnergy(msg.getEnergy().longValue(), " call deep limit reach");
      return;
    }

    // Repository track = getContractState().startTracking();
    Deposit deposit = getContractState().newDepositChild();

    byte[] senderAddress = convertToTronAddress(this.getContractAddress().getLast20Bytes());
    byte[] codeAddress = convertToTronAddress(msg.getCodeAddress().getLast20Bytes());
    byte[] contextAddress = msg.getType().callIsStateless() ? senderAddress : codeAddress;

    // todo: need check endowment > 0 and not exceed?? because of "senderBalance < endowment"
    long endowment = msg.getEndowment().value().longValueExact();
    long senderBalance = deposit.getBalance(senderAddress);
    if (senderBalance < endowment) {
      stackPushZero();
      this.refundEnergy(msg.getEnergy().longValue(), "refund energy from message call");
      return;
    }

    byte[] data = this.memoryChunk(msg.getInDataOffs().intValue(),
        msg.getInDataSize().intValue());

    // Charge for endowment - is not reversible by rollback
    if (!ArrayUtils.isEmpty(senderAddress) && !ArrayUtils.isEmpty(contextAddress)
        && senderAddress != contextAddress && msg.getEndowment().value().longValueExact() > 0) {
      try {
        transfer(deposit, senderAddress, contextAddress,
            msg.getEndowment().value().longValueExact());
      } catch (ContractValidateException e) {
        throw new BytecodeExecutionException("transfer failure");
      }
    }

    long requiredEnergy = contract.getEnergyForData(data);
    if (requiredEnergy > msg.getEnergy().longValue()) {
      // todo: new throw?? because it has done nothing, but outside don't know this
      // regard as consumed the energy
      this.refundEnergy(0, "call pre-compiled"); //matches cpp logic
      this.stackPushZero();
      // deposit.rollback();
    } else {
      // Delegate or not. if is delegated, we will use msg sender, otherwise use contract address
      contract.setCallerAddress(convertToTronAddress(msg.getType().callIsDelegate() ?
          getCallerAddress().getLast20Bytes() : getContractAddress().getLast20Bytes()));
      // this is the depositImpl, not contractState as above
      contract.setDeposit(deposit);
      contract.setResult(this.result);
      contract.setRootCallConstant(getRootCallConstant().booleanValue());
      Pair<Boolean, byte[]> out = contract.execute(data);

      if (out.getLeft()) { // success
        this.refundEnergy(msg.getEnergy().longValue() - requiredEnergy, "call pre-compiled");
        this.stackPushOne();
        returnDataBuffer = out.getRight();
        deposit.commit();
      } else {
        // spend all energy on failure, push zero and revert state changes
        this.refundEnergy(0, "call pre-compiled");
        this.stackPushZero();
        if (Objects.nonNull(this.result.getException())) {
          throw result.getException();
        }
        // deposit.rollback();
      }

      this.memorySave(msg.getOutDataOffs().intValue(), out.getRight());
    }
  }

  public boolean byTestingSuite() {
    return invoke.byTestingSuite();
  }

  public interface ProgramOutListener {

    void output(String out);
  }

  /**
   * Denotes problem when executing Ethereum bytecode. From blockchain and peer perspective this is
   * quite normal situation and doesn't mean exceptional situation in terms of the program
   * execution
   */
  @SuppressWarnings("serial")
  public static class BytecodeExecutionException extends RuntimeException {

    public BytecodeExecutionException(String message) {
      super(message);
    }
  }

  @SuppressWarnings("serial")
  public static class OutOfEnergyException extends BytecodeExecutionException {

    public OutOfEnergyException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class OutOfResourceException extends BytecodeExecutionException {

    public OutOfResourceException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class OutOfMemoryException extends BytecodeExecutionException {

    public OutOfMemoryException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class OutOfStorageException extends BytecodeExecutionException {

    public OutOfStorageException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class PrecompiledContractException extends BytecodeExecutionException {

    public PrecompiledContractException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class IllegalOperationException extends BytecodeExecutionException {

    public IllegalOperationException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class BadJumpDestinationException extends BytecodeExecutionException {

    public BadJumpDestinationException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class StackTooSmallException extends BytecodeExecutionException {

    public StackTooSmallException(String message, Object... args) {
      super(format(message, args));
    }
  }

  @SuppressWarnings("serial")
  public static class ReturnDataCopyIllegalBoundsException extends BytecodeExecutionException {

    public ReturnDataCopyIllegalBoundsException(DataWord off, DataWord size,
        long returnDataSize) {
      super(String
          .format(
              "Illegal RETURNDATACOPY arguments: offset (%s) + size (%s) > RETURNDATASIZE (%d)",
              off, size, returnDataSize));
    }
  }

  @SuppressWarnings("serial")
  public static class JVMStackOverFlowException extends BytecodeExecutionException {

    public JVMStackOverFlowException() {
      super("StackOverflowError:  exceed default JVM stack size!");
    }
  }

  @SuppressWarnings("serial")
  public static class StaticCallModificationException extends BytecodeExecutionException {

    public StaticCallModificationException() {
      super("Attempt to call a state modifying opcode inside STATICCALL");
    }
  }

  public static class Exception {

    public static OutOfEnergyException notEnoughOpEnergy(OpCode op, long opEnergy,
        long programEnergy) {
      return new OutOfEnergyException(
          "Not enough energy for '%s' operation executing: opEnergy[%d], programEnergy[%d];", op,
          opEnergy,
          programEnergy);
    }

    public static OutOfEnergyException notEnoughSpendEnergy(String hint, long needEnergy,
        long leftEnergy) {
      return new OutOfEnergyException(
          "Not enough energy for '%s' executing: needEnergy[%d], leftEnergy[%d];", hint, needEnergy,
          leftEnergy);
    }

    public static OutOfEnergyException notEnoughOpEnergy(OpCode op, DataWord opEnergy,
        DataWord programEnergy) {
      return notEnoughOpEnergy(op, opEnergy.longValue(), programEnergy.longValue());
    }


    public static OutOfResourceException notEnoughTime(String op) {
      return new OutOfResourceException(
          "CPU timeout for '%s' operation executing", op);
    }


    public static OutOfMemoryException memoryOverflow(OpCode op) {
      return new OutOfMemoryException("Out of Memory when '%s' operation executing", op.name());
    }

    public static OutOfStorageException notEnoughStorage() {
      return new OutOfStorageException("Not enough ContractState resource");
    }

    public static PrecompiledContractException contractValidateException(TronException e) {
      return new PrecompiledContractException(e.getMessage());
    }

    public static PrecompiledContractException contractExecuteException(TronException e) {
      return new PrecompiledContractException(e.getMessage());
    }

    public static OutOfEnergyException energyOverflow(BigInteger actualEnergy,
        BigInteger energyLimit) {
      return new OutOfEnergyException("Energy value overflow: actualEnergy[%d], energyLimit[%d];",
          actualEnergy.longValueExact(), energyLimit.longValueExact());
    }

    public static IllegalOperationException invalidOpCode(byte... opCode) {
      return new IllegalOperationException("Invalid operation code: opCode[%s];",
          Hex.toHexString(opCode, 0, 1));
    }

    public static BadJumpDestinationException badJumpDestination(int pc) {
      return new BadJumpDestinationException("Operation with pc isn't 'JUMPDEST': PC[%d];", pc);
    }

    public static StackTooSmallException tooSmallStack(int expectedSize, int actualSize) {
      return new StackTooSmallException("Expected stack size %d but actual %d;", expectedSize,
          actualSize);
    }
  }

  @SuppressWarnings("serial")
  public class StackTooLargeException extends BytecodeExecutionException {

    public StackTooLargeException(String message) {
      super(message);
    }
  }

  public DataWord getCallEnergy(OpCode op, DataWord requestedEnergy, DataWord availableEnergy) {
    return requestedEnergy.compareTo(availableEnergy) > 0 ? availableEnergy : requestedEnergy;
  }

  public DataWord getCreateEnergy(DataWord availableEnergy) {
    return availableEnergy;
  }

  /**
   * used mostly for testing reasons
   */
  public byte[] getMemory() {
    return memory.read(0, memory.size());
  }

  /**
   * used mostly for testing reasons
   */
  public void initMem(byte[] data) {
    this.memory.write(0, data, data.length, false);
  }

  public long getVmStartInUs() {
    return this.invoke.getVmStartInUs();
  }

}