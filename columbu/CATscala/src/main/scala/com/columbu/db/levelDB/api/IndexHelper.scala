package com.galileo.db.levelDB.api

import javax.annotation.PostConstruct
import javax.annotation.Resource
import com.galileo.core.capsule.AccountCapsule
import com.galileo.core.capsule.AssetIssueCapsule
import com.galileo.core.capsule.BlockCapsule
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.core.capsule.WitnessCapsule
import com.galileo.db.levelDB.api.index.Index
import com.galileo.protos.protos.{Contract, Protocol}


import scala.beans.BeanProperty


class IndexHelper {

  @BeanProperty
  @Resource val transactionIndex : Index.Iface[Protocol.Transaction] = null
  @BeanProperty
  @Resource val blockIndex : Index.Iface[Protocol.Block]= null
  @BeanProperty
  @Resource val witnessIndex : Index.Iface[Protocol.Witness]= null
  @BeanProperty
  @Resource val accountIndex : Index.Iface[Protocol.Account]= null
  @BeanProperty
  @Resource val assetIssueIndex : Index.Iface[Contract.AssetIssueContract] = null

  @PostConstruct def init(): Unit = {
    transactionIndex.fill()
    //blockIndex.fill();
    //witnessIndex.fill();
    //accountIndex.fill();
    //assetIssueIndex.fill();
  }

  private def add[T](index: Index.Iface[T], bytes: Array[Byte]): Unit = {
    index.add(bytes)
  }

  def add(t: Protocol.Transaction): Unit = {
    add(transactionIndex, getKey(t))
  }

  def add(b: Protocol.Block): Unit = {
    //add(blockIndex, getKey(b));
  }

  def add(w: Protocol.Witness): Unit = {
    //add(witnessIndex, getKey(w));
  }

  def add(a: Protocol.Account): Unit = {
    //add(accountIndex, getKey(a));
  }

  def add(a: Contract.AssetIssueContract): Unit = {
    //add(assetIssueIndex, getKey(a));
  }

  private def update[T](index: Index.Iface[T], bytes: Array[Byte]): Unit = {
    index.update(bytes)
  }

  def update(t: Protocol.Transaction): Unit = {
    update(transactionIndex, getKey(t))
  }

  def update(b: Protocol.Block): Unit = {
    // update(blockIndex, getKey(b));
  }

  def update(w: Protocol.Witness): Unit = {
    //update(witnessIndex, getKey(w));
  }

  def update(a: Protocol.Account): Unit = {
    //update(accountIndex, getKey(a));
  }

  def update(a: Contract.AssetIssueContract): Unit = {
    //update(assetIssueIndex, getKey(a));
  }

  private def remove[T](index: Index.Iface[T], bytes: Array[Byte]): Unit = {
    index.remove(bytes)
  }

  def remove(t: Protocol.Transaction): Unit = {
    remove(transactionIndex, getKey(t))
  }

  def remove(b: Protocol.Block): Unit = {
    //remove(blockIndex, getKey(b));
  }

  def remove(w: Protocol.Witness): Unit = {
    //remove(witnessIndex, getKey(w));
  }

  def remove(a: Protocol.Account): Unit = {
    //remove(accountIndex, getKey(a));
  }

  def remove(a: Contract.AssetIssueContract): Unit = {
    //remove(assetIssueIndex, getKey(a));
  }

  private def getKey(t: Protocol.Transaction) = new TransactionCapsule(t).getTransactionId.getBytes

  private def getKey(b: Protocol.Block) = new BlockCapsule(b).getBlockId.getBytes

  private def getKey(w: Protocol.Witness) = new WitnessCapsule(w).createDbKey

  private def getKey(a: Protocol.Account) = new AccountCapsule(a).createDbKey

  private def getKey(a: Contract.AssetIssueContract) = new AssetIssueCapsule(a).createDbKey
}
