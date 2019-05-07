package com.galileo.utils

import com.google.protobuf.ByteString;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.galileo.core.capsule.BlockCapsule;
import com.galileo.core.capsule.TransactionCapsule;
import com.galileo.core.config.Parameter.ChainConstant;
import com.galileo.db.levelDB.store.Manager;
import com.galileo.core.exception.ContractExeException;
import com.galileo.protos.protos.Protocol.Transaction.Contract.ContractType

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scala.beans.BeanProperty

@Component
class ForkController {
  
  private val logger : Logger = LoggerFactory.getLogger(getClass())
  
  private var manager : Manager = null
  @volatile
  private var slots : Array[Int] = new Array[Int](0)
  private var forked : Boolean = true
  
  
  def getManager : Manager = {
    return manager
  }
  
  def init(manager : Manager) : Unit = {
    this.manager = manager
    forked = true
  }
  
  def shouldBeForked : Boolean = {
    this.synchronized {
      if (forked) {
        if (logger.isDebugEnabled()) {
          logger.debug("*****shouldBeForked:" + true)
        }
        return true;
      }

      if (slots.length == 0) {
        return false
      }
      
      for (version <- slots) {
        if (version != ChainConstant.version) {
          if (logger.isDebugEnabled()) {
            logger.debug("*****shouldBeForked:" + false)
          }
          return false
        }
      }
      // todo add Maintenance or block number
      forked = true
      manager.getDynamicPropertiesStore().forked()
      if (logger.isDebugEnabled()) {
        logger.debug("*****shouldBeForked:" + true)
      }
      return true
    }
  }
  
  @throws[ContractExeException]
  def hardFork(capsule : TransactionCapsule) : Unit = {
    this.synchronized {
      var value : Int = capsule.getInstance().getRawData().getContractList().get(0).getType().getNumber()
      var hardFork : Boolean = shouldBeForked || value <= ForkController.DISCARD_SCOPE
      
      if (logger.isDebugEnabled()) {
        logger.debug("*****hardFork:" + hardFork)
      }
      if (!hardFork) {
        throw new ContractExeException("not yet hard forked")
      }
    }
  }
  
  def update(blockCapsule : BlockCapsule) : Unit = {
    this.synchronized {
      if (forked) { return }
      
      var witnesses : List[ByteString] = manager.getWitnessController().getActiveWitnesses()
      if (witnesses.size() != slots.length) {
        slots = new Array[Int](witnesses.size())
      }
      
      var witness : ByteString = blockCapsule.getWitnessAddress()
      var slot : Int = witnesses.indexOf(witness)
      if (slot < 0) { return }
      
      var version : Int = blockCapsule.getInstance().getBlockHeader().getRawData().getVersion()
      
      slots(slot) = version
      logger.info(
          "*******update hard fork:" + Arrays.toString(slots)
              + ",witness size:" + witnesses.size()
              + ",slot:" + slot
              + ",witness:" + ByteUtil.toHexString(witness.toByteArray())
              + ",version:" + version
      ); 
    }
  }
  
  def reset : Unit = {
    this.synchronized{
      Arrays.fill(slots, 0)
    }
  }
  
}

object ForkController {
  val DISCARD_SCOPE : Int = ContractType.UpdateAssetContract.getNumber()
  
}

