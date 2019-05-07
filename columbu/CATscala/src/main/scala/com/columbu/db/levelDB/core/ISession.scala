package com.galileo.db.levelDB.core

trait ISession extends AutoCloseable{

  def commit(): Unit

  def revoke(): Unit

  def merge(): Unit

  def destroy(): Unit

  override def close(): Unit
}
