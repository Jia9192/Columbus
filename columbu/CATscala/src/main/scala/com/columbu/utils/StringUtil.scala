package com.galileo.utils

import com.google.protobuf.ByteString
import java.util.Collection
import java.util.List
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.function.Function
import com.galileo.core.Wallet

object StringUtil {
  
  /**
   * n-bits hex string.
   *
   * @param str target string
   * @param bits string bits
   */
  def isHexString(str : String, bits : Int) : Boolean = {
    var value = "^[A-Fa-f0-9]{%d}$"
    var regex : String = value.format(bits)
    return str.matches(regex)
  }
  
  def createDbKey(string : ByteString) : Array[Byte] = string.toByteArray()
  
  def createReadableString(bytes : Array[Byte]) : String = ByteArray.toHexString(bytes)
  
  def createReadableString(string : ByteString) : String = createReadableString(string.toByteArray())
  
  def getAddressStringList(collection : Collection[ByteString]) : List[String] = {
    var temp : Function[ByteString, String] = new Function[ByteString, String]() {
      override def apply(bytes : ByteString) : String = {
        return Wallet.encode58Check(bytes.toByteArray())
      }
    }
    
    var tem : Stream[String] = collection.stream().map(temp)
    return tem.collect(Collectors.toList())
  }
  
  def getAddressStringListFromByteArray(collection : Collection[Array[Byte]]) : List[String] = {
    var temp : Function[Array[Byte], String] = new Function[Array[Byte], String]() {
      override def apply(bytes : Array[Byte]) : String = {
        return createReadableString(bytes)
      }
    }
    var tem : Stream[String] = collection.stream().map(temp)
    return tem.collect(Collectors.toList())
  }
  
  def hexString2ByteString(hexString : String) : ByteString = {
		  ByteString.copyFrom(ByteArray.fromHexString(hexString))
  }
  
  

  
}