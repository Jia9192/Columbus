package com.galileo.db.levelDB.common

import java.util
import java.util.Arrays


final class Key {

  private var data : WrappedByteArray= null

  def this(data: WrappedByteArray) {
    this()
    this.data = data
  }

  def getBytes: Array[Byte] = {
    val key = data.getBytes
    if (key == null)
      return null
    util.Arrays.copyOf(key, key.length)
  }
}

object Key{

  def of(bytes: Array[Byte]): Key = {
    var key : Array[Byte] = null
    if (bytes != null)
      key = util.Arrays.copyOf(bytes, bytes.length)
    new Key(WrappedByteArray.of(key))
  }
}
