package com.galileo.db.levelDB.core

import com.galileo.db.levelDB.common.DB


import scala.beans.BeanProperty

abstract class AbstractSnapshot[K, V] extends Snapshot{

  @BeanProperty protected var db: DB[K,V] = null

  @BeanProperty protected var previous: Snapshot = null

  override def advance = new SnapshotImpl(this)
}
