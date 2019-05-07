package com.galileo.db.levelDB

import org.iq80.leveldb.WriteOptions

trait SourceInter[K,V] {

  def putData(key: K, `val`: V): Unit

  def putData(k: K, v: V, options: WriteOptions): Unit

  def getData(key: K): V


  def deleteData(key: K): Unit

  def deleteData(k: K, options: WriteOptions): Unit

  def flush: Boolean
}
