package com.galileo.db.levelDB.core

import com.google.protobuf.InvalidProtocolBufferException
import java.util.Map.Entry
import com.galileo.utils.Quitable
import com.galileo.core.exception.BadItemException
import com.galileo.core.exception.ItemNotFoundException

trait ITronChainBase[T] extends Iterable[Entry[Array[Byte], T]] with Quitable{

  /**
    * reset the database.
    */
  def reset(): Unit

  /**
    * close the database.
    */
  override def close(): Unit

  def put(key: Array[Byte], item: T): Unit

  def delete(key: Array[Byte]): Unit

  @throws[InvalidProtocolBufferException]
  @throws[ItemNotFoundException]
  @throws[BadItemException]
  def get(key: Array[Byte]): T

  def getUnchecked(key: Array[Byte]): T

  def has(key: Array[Byte]): Boolean

  def getName: String

  def getDbName: String
}
