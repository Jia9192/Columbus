package com.galileo.utils

import java.util.concurrent.locks.Lock

class ALock extends AutoCloseable{
  private var loc:Lock = null//todo

  def this(l: Lock) {
    this()
    this.loc = l
  }

  def lock: ALock = {
    this.loc.lock()
    this
  }

  override def close(): Unit = {
    this.loc.unlock()
  }
}
