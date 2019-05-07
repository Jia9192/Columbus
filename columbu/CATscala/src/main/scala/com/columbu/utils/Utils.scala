package com.galileo.utils

import java.security.SecureRandom
import java.nio._
import java.nio.charset.Charset
import java.util.Arrays

//trait Utils {
//  
//  var random : SecureRandom = new SecureRandom()
//  
//}

object Utils {
  
  var random : SecureRandom = new SecureRandom()
  
  def getRandom : SecureRandom = Utils.random
  
  def getBytes(chars : Array[Char]) : Array[Byte] = {
    var cs : Charset = Charset.forName("UTF-8")
    var cb : CharBuffer = CharBuffer.allocate(chars.length)
    cb.put(chars);
    cb.flip();
    var bb : ByteBuffer = cs.encode(cb)

    return bb.array()
  }
  
  def getIdShort(Id : String) : String = {
    if (Id == null) {
      return "<null>"
    } else {
      return Id.substring(0, 8)
    }
  }
  
  def getChars(bytes : Array[Byte]) : Array[Char] = {
    var cs : Charset = Charset.forName("UTF-8")
    var bb : ByteBuffer = ByteBuffer.allocate(bytes.length)
    bb.put(bytes)
    bb.flip()
    var cb : CharBuffer = cs.decode(bb)

    return cb.array()
  }
  
  def clone(value : Array[Byte]) : Array[Byte] = {
    var clone : Array[Byte] = new Array[Byte](value.length)
    System.arraycopy(value, 0, clone, 0, value.length)
    return clone
  }
  
  def sizeToStr(size : Long) : String = {
    if (size < 2 * (1L << 10)) {
    	return size + "b"
    }
    
    var value : String = "%dKb"
    if (size < 2 * (1L << 20)) {
    	return value.format(size / (1L << 10))
    }
    if (size < 2 * (1L << 30)) {
      value = "%dMb"
    	return value.format(size / (1L << 20))
    }
    value = "%dGb"
    return value.format(size / (1L << 30))
  }
  
  def align(s : String, fillChar : Char, targetLen : Int, alignRight : Boolean) : String = {
    if (targetLen <= s.length()) return s
    var alignString : String = repeat("" + fillChar, targetLen - s.length())
    if (alignRight) {
      return alignString + s
    } else {
      return s + alignString
    }
  }
  
  def repeat(s : String, n : Int) : String = {
    if (s.length() == 1) {
      var bb : Array[Byte] = new Array[Byte](n)
      Arrays.fill(bb, s.getBytes()(0))
      return new String(bb);
    } else {
      var ret : StringBuilder = new StringBuilder()
      for (i <- 0 until n) {
        ret.append(s)
      }
      return ret.toString()
    }
  }
  
  
}