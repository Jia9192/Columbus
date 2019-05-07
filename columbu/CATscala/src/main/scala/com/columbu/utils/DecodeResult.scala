package com.galileo.utils

import java.io.Serializable
import java.util.Arrays
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.function.Function

import org.spongycastle.util.encoders.Hex

class DecodeResult extends Serializable {
  
  private var pos : Int = 0
  private var decoded : AnyRef = null
  
  def this(posValue : Int, decodedValue : AnyRef) {
    this()
    this.pos = posValue
    this.decoded = decodedValue
  }
  
  def getPos : Int = pos
  
  def getDecoded : AnyRef = decoded
  
  override def toString : String = asString(this.decoded)
  
  def asString(decoded : AnyRef) : String = {
   if (decoded.isInstanceOf[String]) {
     return decoded.asInstanceOf[String]
   } else if (decoded.isInstanceOf[Array[Byte]]) {
     return Hex.toHexString(decoded.asInstanceOf[Array[Byte]])
   } else if (decoded.isInstanceOf[Array[AnyRef]]) {
     var temp : Function[AnyRef, String] = new Function[AnyRef, String]() {
       override def apply(value : AnyRef) : String = {
         return asString(value)
       }
     }
     
     var tem : Stream[String] = Arrays.stream(decoded.asInstanceOf[Array[AnyRef]]).map(temp)
     return tem.collect(Collectors.joining())
   }
   throw new RuntimeException("Not a valid type. Should not occur")
  }
  
  
  
}