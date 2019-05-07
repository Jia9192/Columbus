package com.galileo.p2p.net.node;

import static com.galileo.core.config.Parameter.ChainConstant.BLOCK_PRODUCED_INTERVAL;
import static com.galileo.core.config.Parameter.ChainConstant.BLOCK_SIZE;

import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import com.galileo.core.overlay.message.Message;
import com.galileo.utils.Sha256Hash;
import com.galileo.core.capsule.BlockCapsule;
import com.galileo.core.capsule.BlockCapsule.BlockId;
import com.galileo.core.capsule.TransactionCapsule;
import com.galileo.core.config.Parameter.NodeConstant;
import com.galileo.db.levelDB.store.Manager;
import com.galileo.core.exception.AccountResourceInsufficientException;
import com.galileo.core.exception.BadBlockException;
import com.galileo.core.exception.BadItemException;
import com.galileo.core.exception.BadNumberBlockException;
import com.galileo.core.exception.BadTransactionException;
import com.galileo.core.exception.ContractExeException;
import com.galileo.core.exception.ContractSizeNotEqualToOneException;
import com.galileo.core.exception.ContractValidateException;
import com.galileo.core.exception.DupTransactionException;
import com.galileo.core.exception.ItemNotFoundException;
import com.galileo.core.exception.NonCommonBlockException;
import com.galileo.core.exception.ReceiptCheckErrException;
import com.galileo.core.exception.StoreException;
import com.galileo.core.exception.TaposException;
import com.galileo.core.exception.TooBigTransactionException;
import com.galileo.core.exception.TooBigTransactionResultException;
import com.galileo.core.exception.TransactionExpirationException;
import com.galileo.core.exception.TronException;
import com.galileo.core.exception.UnLinkedBlockException;
import com.galileo.core.exception.VMIllegalException;
import com.galileo.core.exception.ValidateScheduleException;
import com.galileo.core.exception.ValidateSignatureException;
import com.galileo.p2p.net.message.BlockMessage;
import com.galileo.p2p.net.message.MessageTypes;
import com.galileo.p2p.net.message.TransactionMessage;

@Slf4j
public class NodeDelegateImpl implements NodeDelegate {

  private Manager dbManager;

  public NodeDelegateImpl(Manager dbManager) {
    this.dbManager = dbManager;
  }

  @Override
  public synchronized LinkedList<Sha256Hash> handleBlock(BlockCapsule block, boolean syncMode)
      throws BadBlockException, UnLinkedBlockException, InterruptedException, NonCommonBlockException {

    if (block.getInstance().getSerializedSize() > BLOCK_SIZE + 100) {
      throw new BadBlockException("block size over limit");
    }

    long gap = block.getTimeStamp() - System.currentTimeMillis();
    if (gap >= BLOCK_PRODUCED_INTERVAL) {
      throw new BadBlockException("block time error");
    }
    try {
      dbManager.preValidateTransactionSign(block);
      dbManager.pushBlock(block);
      if (!syncMode) {
        List<TransactionCapsule> trx = null;
        trx = block.getTransactions();
        return trx.stream()
            .map(TransactionCapsule::getTransactionId)
            .collect(Collectors.toCollection(LinkedList::new));
      } else {
        return null;
      }

    } catch (AccountResourceInsufficientException e) {
      throw new BadBlockException("AccountResourceInsufficientException," + e.getMessage());
    } catch (ValidateScheduleException e) {
      throw new BadBlockException("validate schedule exception," + e.getMessage());
    } catch (ValidateSignatureException e) {
      throw new BadBlockException("validate signature exception," + e.getMessage());
    } catch (ContractValidateException e) {
      throw new BadBlockException("ContractValidate exception," + e.getMessage());
    } catch (ContractExeException e) {
      throw new BadBlockException("Contract Execute exception," + e.getMessage());
    } catch (TaposException e) {
      throw new BadBlockException("tapos exception," + e.getMessage());
    } catch (DupTransactionException e) {
      throw new BadBlockException("DupTransaction exception," + e.getMessage());
    } catch (TooBigTransactionException e) {
      throw new BadBlockException("TooBigTransaction exception," + e.getMessage());
    } catch (TooBigTransactionResultException e) {
      throw new BadBlockException("TooBigTransaction exception," + e.getMessage());
    } catch (TransactionExpirationException e) {
      throw new BadBlockException("Expiration exception," + e.getMessage());
    } catch (BadNumberBlockException e) {
      throw new BadBlockException("bad number exception," + e.getMessage());
    } catch (ReceiptCheckErrException e) {
      throw new BadBlockException("TransactionTrace Exception," + e.getMessage());
    } catch (VMIllegalException e) {
      throw new BadBlockException(e.getMessage());
    }

  }


  @Override
  public boolean handleTransaction(TransactionCapsule trx) throws BadTransactionException {
    if (dbManager.getDynamicPropertiesStore().supportVM()) {
      trx.resetResult();
    }
    logger.debug("handle transaction");
    if (dbManager.getTransactionIdCache().getIfPresent(trx.getTransactionId()) != null) {
      logger.warn("This transaction has been processed");
      return false;
    } else {
      dbManager.getTransactionIdCache().put(trx.getTransactionId(), true);
    }
    try {
      dbManager.pushTransaction(trx);
    } catch (ContractSizeNotEqualToOneException e) {
      logger.info("Contract validate failed" + e.getMessage());
      throw new BadTransactionException();
    } catch (ContractValidateException e) {
      logger.info("Contract validate failed" + e.getMessage());
      //throw new BadTransactionException();
      return false;
    } catch (ContractExeException e) {
      logger.info("Contract execute failed" + e.getMessage());
      //throw new BadTransactionException();
      return false;
    } catch (ValidateSignatureException e) {
      logger.info("ValidateSignatureException" + e.getMessage());
      throw new BadTransactionException();
    } catch (AccountResourceInsufficientException e) {
      logger.info("AccountResourceInsufficientException" + e.getMessage());
      return false;
    } catch (DupTransactionException e) {
      logger.info("Dup trans" + e.getMessage());
      return false;
    } catch (TaposException e) {
      logger.info("Tapos error" + e.getMessage());
      return false;
    } catch (TooBigTransactionException e) {
      logger.info("Too big transaction" + e.getMessage());
      return false;
    } catch (TransactionExpirationException e) {
      logger.info("Expiration transaction" + e.getMessage());
      return false;
    } catch (ReceiptCheckErrException e) {
      logger.info("ReceiptCheckErrException Exception" + e.getMessage());
      return false;
    } catch (VMIllegalException e) {
      logger.warn(e.getMessage());
      throw new BadTransactionException();
    } catch (TooBigTransactionResultException e) {
      logger.info("Too big transactionresult" + e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public LinkedList<BlockId> getLostBlockIds(List<BlockId> blockChainSummary)
      throws StoreException {

    if (dbManager.getHeadBlockNum() == 0) {
      return new LinkedList<>();
    }

    BlockId unForkedBlockId;

    if (blockChainSummary.isEmpty() ||
        (blockChainSummary.size() == 1
            && blockChainSummary.get(0).equals(dbManager.getGenesisBlockId()))) {
      unForkedBlockId = dbManager.getGenesisBlockId();
    } else if (blockChainSummary.size() == 1
        && blockChainSummary.get(0).getNum() == 0) {
      return new LinkedList(Arrays.asList(dbManager.getGenesisBlockId()));
    } else {

      Collections.reverse(blockChainSummary);
      unForkedBlockId = blockChainSummary.stream()
          .filter(blockId -> containBlockInMainChain(blockId))
          .findFirst().orElse(null);
      if (unForkedBlockId == null) {
        return new LinkedList<>();
      }
    }

    long unForkedBlockIdNum = unForkedBlockId.getNum();
    long len = Longs
        .min(dbManager.getHeadBlockNum(), unForkedBlockIdNum + NodeConstant.SYNC_FETCH_BATCH_NUM);

    LinkedList<BlockId> blockIds = new LinkedList<>();
    for (long i = unForkedBlockIdNum; i <= len; i++) {
      BlockId id = dbManager.getBlockIdByNum(i);
      blockIds.add(id);
    }
    return blockIds;
  }

  @Override
  public Deque<BlockId> getBlockChainSummary(BlockId beginBlockId, Deque<BlockId> blockIdsToFetch)
      throws TronException {

    Deque<BlockId> retSummary = new LinkedList<>();
    List<BlockId> blockIds = new ArrayList<>(blockIdsToFetch);
    long highBlkNum;
    long highNoForkBlkNum;
    long syncBeginNumber = dbManager.getSyncBeginNumber();
    long lowBlkNum = syncBeginNumber < 0 ? 0 : syncBeginNumber;

    LinkedList<BlockId> forkList = new LinkedList<>();

    if (!beginBlockId.equals(getGenesisBlock().getBlockId())) {
      if (containBlockInMainChain(beginBlockId)) {
        highBlkNum = beginBlockId.getNum();
        if (highBlkNum == 0) {
          throw new TronException(
              "This block don't equal my genesis block hash, but it is in my DB, the block id is :"
                  + beginBlockId.getString());
        }
        highNoForkBlkNum = highBlkNum;
        if (beginBlockId.getNum() < lowBlkNum) {
          lowBlkNum = beginBlockId.getNum();
        }
      } else {
        forkList = dbManager.getBlockChainHashesOnFork(beginBlockId);
        if (forkList.isEmpty()) {
          throw new UnLinkedBlockException(
              "We want to find forkList of this block: " + beginBlockId.getString()
                  + " ,but in KhasoDB we can not find it, It maybe a very old beginBlockId, we are sync once,"
                  + " we switch and pop it after that time. ");
        }
        highNoForkBlkNum = forkList.peekLast().getNum();
        forkList.pollLast();
        Collections.reverse(forkList);
        highBlkNum = highNoForkBlkNum + forkList.size();
        if (highNoForkBlkNum < lowBlkNum) {
          throw new UnLinkedBlockException(
              "It is a too old block that we take it as a forked block long long ago"
                  + "\n lowBlkNum:" + lowBlkNum
                  + "\n highNoForkBlkNum" + highNoForkBlkNum);
        }
      }
    } else {
      highBlkNum = dbManager.getHeadBlockNum();
      highNoForkBlkNum = highBlkNum;

    }

    if (!blockIds.isEmpty() && highBlkNum != blockIds.get(0).getNum() - 1) {
      logger.error("Check ERROR: highBlkNum:" + highBlkNum + ",blockIdToSyncFirstNum is "
          + blockIds.get(0).getNum() + ",blockIdToSyncEnd is " + blockIds.get(blockIds.size() - 1)
          .getNum());
    }

    long realHighBlkNum = highBlkNum + blockIds.size();
    do {
      if (lowBlkNum <= highNoForkBlkNum) {
        retSummary.offer(dbManager.getBlockIdByNum(lowBlkNum));
      } else if (lowBlkNum <= highBlkNum) {
        retSummary.offer(forkList.get((int) (lowBlkNum - highNoForkBlkNum - 1)));
      } else {
        retSummary.offer(blockIds.get((int) (lowBlkNum - highBlkNum - 1)));
      }
      lowBlkNum += (realHighBlkNum - lowBlkNum + 2) / 2;
    } while (lowBlkNum <= realHighBlkNum);

    return retSummary;
  }

  @Override
  public Message getData(Sha256Hash hash, MessageTypes type)
      throws StoreException {
    switch (type) {
      case BLOCK:
        return new BlockMessage(dbManager.getBlockById(hash));
      case TRX:
        TransactionCapsule tx = dbManager.getTransactionStore().get(hash.getBytes());
        if (tx != null) {
          return new TransactionMessage(tx.getData());
        }
        throw new ItemNotFoundException("transaction is not found");
      default:
        throw new BadItemException("message type not block or trx.");
    }
  }

  @Override
  public void syncToCli(long unSyncNum) {
    logger.info("There are " + unSyncNum + " blocks we need to sync.");
    if (unSyncNum == 0) {
      logger.info("Sync Block Completed !!!");
    }
    dbManager.setSyncMode(unSyncNum == 0);
  }

  @Override
  public long getBlockTime(BlockId id) {
    try {
      return dbManager.getBlockById(id).getTimeStamp();
    } catch (BadItemException e) {
      return dbManager.getGenesisBlock().getTimeStamp();
    } catch (ItemNotFoundException e) {
      return dbManager.getGenesisBlock().getTimeStamp();
    }
  }

  @Override
  public BlockId getHeadBlockId() {
    return dbManager.getHeadBlockId();
  }

  @Override
  public BlockId getSolidBlockId() {
    return dbManager.getSolidBlockId();
  }

  @Override
  public long getHeadBlockTimeStamp() {
    return dbManager.getHeadBlockTimeStamp();
  }

  @Override
  public boolean containBlock(BlockId id) {
    return dbManager.containBlock(id);
  }

  @Override
  public boolean containBlockInMainChain(BlockId id) {
    return dbManager.containBlockInMainChain(id);
  }

  @Override
  public boolean contain(Sha256Hash hash, MessageTypes type) {
    if (type.equals(MessageTypes.BLOCK)) {
      return dbManager.containBlock(hash);
    } else if (type.equals(MessageTypes.TRX)) {
      return dbManager.getTransactionStore().has(hash.getBytes());
    }
    return false;
  }

  @Override
  public BlockCapsule getGenesisBlock() {
    return dbManager.getGenesisBlock();
  }

  @Override
  public boolean canChainRevoke(long num) {
    return num >= dbManager.getSyncBeginNumber();
  }
}
