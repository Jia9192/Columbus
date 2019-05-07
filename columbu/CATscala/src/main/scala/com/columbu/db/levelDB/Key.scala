package com.galileo.db.levelDB

import org.apache.commons.lang3.ArrayUtils
import java.util

class Key {



  /**
    * data could not be null
    */
  private var data = new Array[Byte](0)

  /**
    *
    * @param data
    */
  def this(data: Array[Byte]){
    this()
    if (data != null && data.length != 0) {
      this.data = new Array[Byte](data.length)
      System.arraycopy(data, 0, this.data, 0, data.length)
    }
  }

  /**
    *
    * @param key
    */
  def this(key: Key) {
    this()
    this.data = new Array[Byte](key.getData.length)
    System.arraycopy(key.getData, 0, this.data, 0, this.data.length)
  }

  /**
    *
    * @return
    */
  override def clone = new Key(this)

  def getData: Array[Byte] = data

  override def equals(o: Any): Boolean = {
    if (this equals  o) return true
    if (o == null || (getClass ne o.getClass)) return false
    val key = o.asInstanceOf[Key]
    if (util.Arrays.equals(key.getData, this.data)) return true
    false
  }

  override def hashCode: Int = if (data != null) ArrayUtils.hashCode(data)
  else 0


}

object Key{

  private val MAX_KEY_LENGTH = 32
  private val MIN_KEY_LENGTH = 1
  /**
    *
    * @param data
    * @return
    */
  def create(data: Array[Byte]) = new Key(data)
}
