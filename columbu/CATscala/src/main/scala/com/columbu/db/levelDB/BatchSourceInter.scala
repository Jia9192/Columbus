package com.galileo.db.levelDB

import org.iq80.leveldb.WriteOptions
import java.util

trait BatchSourceInter[K,V] extends SourceInter[K, V] {

  def updateByBatch(rows: util.Map[K, V]): Unit

  def updateByBatch(rows: util.Map[K, V], writeOptions: WriteOptions): Unit
}
