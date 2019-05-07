package com.galileo.db.levelDB

import java.util
import org.apache.commons.lang3.ArrayUtils
import com.galileo.core.capsule.AccountCapsule
import com.galileo.core.capsule.AssetIssueCapsule
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.BytesCapsule
import com.galileo.core.capsule.CodeCapsule
import com.galileo.core.capsule.ContractCapsule
import com.galileo.core.capsule.ProposalCapsule
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.capsule.VotesCapsule
import com.galileo.core.capsule.WitnessCapsule
import com.galileo.core.exception.BadItemException

class Value {

  private var `type` : Type = null
  private var any : Array[Byte]= null

  /**
    * @param any
    */
  def this(any: Array[Byte], `type`: Type){
    this()
    if (any != null && any.length > 0) {
      this.any = new Array[Byte](any.length)
      System.arraycopy(any, 0, this.any, 0, any.length)
      this.`type` = `type`.clone
    }
  }

  /**
    * @param any
    * @param type
    */
  def this(any: Array[Byte], `type`: Int){
    this()
    if (any != null && any.length > 0) {
      this.any = new Array[Byte](any.length)
      System.arraycopy(any, 0, this.any, 0, any.length)
      this.`type` = new Type(`type`)
    }
  }

  /**
    * @param value
    */
  def this(value: Value){
    this()
    if (value.getAny != null && value.getAny.length > 0) {
      this.any = new Array[Byte](value.getAny.length)
      System.arraycopy(value.getAny, 0, this.any, 0, value.getAny.length)
      this.`type` = value.getType.clone
    }
  }

  /**
    * @return
    */
  override def clone = new Value(this)

  def getAny: Array[Byte] = any

  def getType: Type = `type`

  /**
    * @param type
    */
  def setType(`type`: Type): Unit = {
    this.`type` = `type`
  }

  def addType(`type`: Type): Unit = {
    this.`type`.addType(`type`)
  }

  def addType(`type`: Int): Unit = {
    this.`type`.addType(`type`)
  }

  def getAccount: AccountCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new AccountCapsule(any)
  }

  def getBytes: BytesCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new BytesCapsule(any)
  }

  def getTransaction: TransactionCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    try
      new TransactionCapsule(any)
    catch {
      case e: BadItemException =>
        null
    }
  }

  def getBlock: BlockCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    try
      new BlockCapsule(any)
    catch {
      case e: Exception =>
        null
    }
  }

  def getWitness: WitnessCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new WitnessCapsule(any)
  }

  def getVotes: VotesCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new VotesCapsule(any)
  }

  def getBlockIndex: BytesCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new BytesCapsule(any)
  }

  def getCode: CodeCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new CodeCapsule(any)
  }

  def getContract: ContractCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new ContractCapsule(any)
  }


  def getAssetIssue: AssetIssueCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new AssetIssueCapsule(any)
  }

  def getProposal: ProposalCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new ProposalCapsule(any)
  }

  def getDynamicProperties: BytesCapsule = {
    if (ArrayUtils.isEmpty(any)) return null
    new BytesCapsule(any)
  }

  override def equals(obj: Any): Boolean = {
    if (this equals  obj) return true
    if (obj == null || (obj.getClass ne getClass)) return false
    val V = obj.asInstanceOf[Value]
    if (util.Arrays.equals(this.any, V.getAny)) return true
    false
  }

  override def hashCode: Int = new Integer(`type`.hashCode + util.Arrays.hashCode(any)).hashCode


}

object Value{
  def create(any: Array[Byte], `type`: Int) = new Value(any, `type`)

  def create(any: Array[Byte], `type`: Type) = new Value(any, `type`)

  def create(any: Array[Byte]) = new Value(any, 0)
}
