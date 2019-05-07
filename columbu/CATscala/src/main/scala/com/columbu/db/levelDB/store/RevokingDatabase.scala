package com.galileo.db.levelDB.store

import com.galileo.core.exception.RevokingStoreIllegalStateException
import com.galileo.db.levelDB.common.IRevokingDB
import com.galileo.db.levelDB.core.ISession

trait RevokingDatabase {

  def buildSession: ISession

  def buildSession(forceEnable: Boolean): ISession

  def add(revokingDB: IRevokingDB): Unit

  @throws[RevokingStoreIllegalStateException]
  def merge(): Unit

  @throws[RevokingStoreIllegalStateException]
  def revoke(): Unit

  @throws[RevokingStoreIllegalStateException]
  def commit(): Unit

  @throws[RevokingStoreIllegalStateException]
  def pop(): Unit

  @throws[RevokingStoreIllegalStateException]
  def fastPop(): Unit

  def enable(): Unit

  def size: Int

  def check(): Unit

  def setMaxSize(maxSize: Int): Unit

  def disable(): Unit

  def shutdown(): Unit
}
