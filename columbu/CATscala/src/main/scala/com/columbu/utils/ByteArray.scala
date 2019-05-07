package com.galileo.utils

import com.google.common.primitives.Ints
import com.google.common.primitives.Longs
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.math.BigInteger
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.StringUtils
import org.spongycastle.util.encoders.Hex

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ByteArray {
  
  private val logger : Logger = LoggerFactory.getLogger(getClass())
  
  val EMPTY_BYTE_ARRAY : Array[Byte]= new Array[Byte](0)
  
  
  def toHexString(data : Array[Byte]) : String = {
    if (data == null) return ""
    else return Hex.toHexString(data)
  }
  
  /**
   * get bytes data from hex string data.
   */
  def fromHexString(data : String) : Array[Byte] = {
	  var dataValue = data
    if (dataValue == null) {
    	return EMPTY_BYTE_ARRAY
    }
    if (dataValue.startsWith("0x")) {
      dataValue = dataValue.substring(2)
    }
    if (dataValue.length() % 2 != 0) {
      dataValue = "0" + dataValue
    }
    return Hex.decode(dataValue)
  }
  

  /**
   * get long data from bytes data.
   */
  def toLong(b : Array[Byte]) : Long = {
    if (ArrayUtils.isEmpty(b)) {
    	return 0
    } else {
      return new BigInteger(1, b).longValue()
    }
  }

  /**
   * get int data from bytes data.
   */
  def toInt(b : Array[Byte]) : Int = {
    if (ArrayUtils.isEmpty(b)) {
    	return 0
    } else {
      return new BigInteger(1, b).intValue()
    }
  }

  /**
   * get bytes data from string data.
   */
  def fromString(s : String) : Array[Byte] = {
    if (StringUtils.isBlank(s)) {
    	return null
    } else {
      return s.getBytes()
    }
  }

  /**
   * get string data from bytes data.
   */
  def toStr(b : Array[Byte]) : String = {
    if (ArrayUtils.isEmpty(b)) {
    	return null
    } else {
      return new String(b)
    }
  }

  def fromLong(value : Long) : Array[Byte] = {
    return Longs.toByteArray(value)
  }

  def fromInt(value : Int) : Array[Byte] = {
    return Ints.toByteArray(value)
  }

  /**
   * get bytes data from object data.
   */
  def fromObject(obj : AnyRef) : Array[Byte] = {
    var bytes : Array[Byte] = null
    try {
    	var byteArrayOutputStream : ByteArrayOutputStream= new ByteArrayOutputStream()
    	var objectOutputStream : ObjectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
    	objectOutputStream.writeObject(obj)
      objectOutputStream.flush()
      bytes = byteArrayOutputStream.toByteArray()
      
    } catch {
      case e: IOException => 
        logger.error("objectToByteArray failed: " + e.getMessage(), e)
    }
    return bytes
  }

  /**
   * Generate a subarray of a given byte array.
   *
   * @param input the input byte array
   * @param start the start index
   * @param end the end index
   * @return a subarray of <tt>input</tt>, ranging from <tt>start</tt> (inclusively) to <tt>end</tt>
   * (exclusively)
   */
  def subArray(input : Array[Byte], start : Int, end : Int) : Array[Byte] = {
    var result : Array[Byte] = new Array[Byte](end - start)
    System.arraycopy(input, start, result, 0, end - start)
    return result
  }
  
  
  
  
}
