package com.galileo.utils

import com.google.common.primitives.Longs
import org.apache.commons.codec.DecoderException
import org.apache.commons.codec.binary.Hex

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TypeConversion {
  
  private val logger : Logger = LoggerFactory.getLogger(getClass())
  
  
  def longToBytes(x : Long) : Array[Byte] = Longs.toByteArray(x)
  
  def bytesToLong(bytes : Array[Byte]) : Long = Longs.fromByteArray(bytes)
  
  def bytesToHexString(src : Array[Byte]) : String = Hex.encodeHexString(src)
  
  def hexStringToBytes(hexString : String) : Array[Byte] = {
    try {
      return Hex.decodeHex(hexString)
    } catch {
      case e: DecoderException => logger.debug(e.getMessage(), e); return null
    }
  }
  
  def increment(bytes : Array[Byte]) : Boolean = {
    val startIndex : Int = 0
    var i = bytes.length - 1
    var flag = true
      
    while (true && i >= startIndex) {
      bytes(i) = (bytes(i) + 1).asInstanceOf[Byte]
      if (bytes(i) != 0) {
        flag = false
      }
      i = i - 1
    }
    return (i >= startIndex || bytes(startIndex) != 0)
  }
  
}