package com.galileo.db.levelDB.store

import java.io.Serializable
import java.util
import org.spongycastle.util.encoders.Hex
import com.galileo.utils.FastByteComparisons

class ByteArrayWrapper extends Comparable[ByteArrayWrapper] with Serializable{

  private var data : Array[Byte] = null
  private var hashCodeP: Int = 0

  /**
    * constructor.
    */
  def this(data: Array[Byte]){
    this()
    if (data == null) throw new NullPointerException("Data must not be null")
    this.data = data
    this.hashCodeP = util.Arrays.hashCode(data)
  }


  /**
    * equals Objects.
    */
  override def equals(other: Any): Boolean = {
    if (other == null || (this.getClass ne other.getClass)) return false
    val otherData = other.asInstanceOf[ByteArrayWrapper].getData
    FastByteComparisons.compareTo(data, 0, data.length, otherData, 0, otherData.length) == 0
  }

  override def hashCode: Int = hashCodeP

  override def compareTo(o: ByteArrayWrapper): Int = FastByteComparisons.compareTo(data, 0, data.length, o.getData, 0, o.getData.length)

  def getData: Array[Byte] = data

  override def toString: String = Hex.toHexString(data)
}
