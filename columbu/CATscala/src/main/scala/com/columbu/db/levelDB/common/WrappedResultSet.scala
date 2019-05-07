package com.galileo.db.levelDB.common

import com.google.common.collect.Iterables
import com.googlecode.cqengine.query.option.QueryOptions
import com.googlecode.cqengine.resultset.ResultSet

abstract class WrappedResultSet[T] extends ResultSet[T] {

  private var resultSet : ResultSet[WrappedByteArray]= null

  def this(resultSet: ResultSet[WrappedByteArray]) {
    this()
    this.resultSet = resultSet
  }

  override def contains(`object`: T): Boolean = Iterables.contains(this, `object`)

  override def matches(`object`: T) = throw new UnsupportedOperationException

  override def getQuery = throw new UnsupportedOperationException

  override def getQueryOptions: QueryOptions = resultSet.getQueryOptions

  override def getRetrievalCost: Int = resultSet.getRetrievalCost

  override def getMergeCost: Int = resultSet.getMergeCost

  override def size: Int = resultSet.size

  override def close(): Unit = {
    resultSet.close()
  }
}
