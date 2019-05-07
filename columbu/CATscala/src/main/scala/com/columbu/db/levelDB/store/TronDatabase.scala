package com.galileo.db.levelDB.store

import com.galileo.db.levelDB.api.IndexHelper
import com.galileo.db.levelDB.core.ITronChainBase
import com.google.protobuf.InvalidProtocolBufferException
import org.springframework.beans.factory.annotation.Autowired
import com.galileo.db.levelDB.LevelDbDataSourceImpl
import com.galileo.core.config.args.Args
import com.galileo.core.exception.BadItemException
import com.galileo.core.exception.ItemNotFoundException

import scala.beans.BeanProperty
//import scala.runtime.Nothing$


abstract class TronDatabase[T] extends ITronChainBase[T]{

  protected var dbSource: LevelDbDataSourceImpl = null
  @BeanProperty var dbName :String = null

  @Autowired(required = false) protected var indexHelper: IndexHelper = null

  def this(dbName: String) {
    this()
    this.dbName = dbName
    dbSource = new LevelDbDataSourceImpl(Args.getInstance.getOutputDirectoryByDbName(dbName), dbName)
    dbSource.initDB()
  }


  def getDbSource: LevelDbDataSourceImpl = dbSource

  /**
    * reset the database.
    */
  override def reset(): Unit = {
    dbSource.resetDb()
  }

  /**
    * close the database.
    */
  override def close(): Unit = {
    dbSource.closeDB()
  }

  override def put(key: Array[Byte], item: T): Unit

  override def delete(key: Array[Byte]): Unit

  @throws[InvalidProtocolBufferException]
  @throws[ItemNotFoundException]
  @throws[BadItemException]
  override def get(key: Array[Byte]): T

  override def getUnchecked(key: Array[Byte]): T

  override def has(key: Array[Byte]): Boolean

  override def getName: String = this.getClass.getSimpleName

  override def iterator = throw new UnsupportedOperationException
}
