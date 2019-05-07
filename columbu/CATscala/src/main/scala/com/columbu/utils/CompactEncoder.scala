package com.galileo.utils

import java.util.Arrays.copyOf
import java.util.Arrays.copyOfRange
import org.spongycastle.util.Arrays.concatenate
import org.spongycastle.util.encoders.Hex.encode
import com.galileo.utils.ByteUtil.appendByte

import java.io.ByteArrayOutputStream
import java.util.Arrays
import java.util.HashMap
import java.util.Map

object CompactEncoder {
  
  private val TERMINATOR : Byte = 16
  private var hexMap : Map[Character, Byte] = new HashMap[Character, Byte]()
  
  {
    hexMap.put('0', 0x0)
    hexMap.put('1', 0x1)
    hexMap.put('2', 0x2)
    hexMap.put('3', 0x3)
    hexMap.put('4', 0x4)
    hexMap.put('5', 0x5)
    hexMap.put('6', 0x6)
    hexMap.put('7', 0x7)
    hexMap.put('8', 0x8)
    hexMap.put('9', 0x9)
    hexMap.put('a', 0xa)
    hexMap.put('b', 0xb)
    hexMap.put('c', 0xc)
    hexMap.put('d', 0xd)
    hexMap.put('e', 0xe)
    hexMap.put('f', 0xf)
  }


  /**
   * Pack nibbles to binary
   *
   * @param nibbles sequence. may have a terminator
   * @return hex-encoded byte array
   */
  def packNibbles(nibbles : Array[Byte]) : Array[Byte] = {
    var nibblesValue = nibbles
    var terminator : Int = 0

    if (nibblesValue(nibblesValue.length - 1) == TERMINATOR) {
      terminator = 1;
      nibblesValue = copyOf(nibblesValue, nibblesValue.length - 1)
    }
    var oddlen : Int = nibblesValue.length % 2
    var flag : Int = 2 * terminator + oddlen
    if (oddlen != 0) {
      var flags : Array[Byte] = Array[Byte](flag.asInstanceOf[Byte])
      nibblesValue = concatenate(flags, nibblesValue)
    } else {
      var flags : Array[Byte] = Array[Byte](flag.asInstanceOf[Byte], 0)
      nibblesValue = concatenate(flags, nibblesValue)
    }
    var buffer : ByteArrayOutputStream = new ByteArrayOutputStream()
    for (i <- 0 until nibbles.length ) {
      if (i % 2 == 0) {
        buffer.write(16 * nibblesValue(i) + nibblesValue(i + 1))
      }
    }
    return buffer.toByteArray()
  }
  
  def hasTerminator(packedKey : Array[Byte]) : Boolean = {
    return ((packedKey(0) >> 4) & 2) != 0
  }

  /**
   * Unpack a binary string to its nibbles equivalent
   *
   * @param str of binary data
   * @return array of nibbles in byte-format
   */
  def unpackToNibbles(str : Array[Byte]) : Array[Byte] = {
    var base : Array[Byte] = binToNibbles(str)
    base = copyOf(base, base.length - 1)
    if (base(0) >= 2) {
      base = appendByte(base, TERMINATOR)
    }
    if (base(0) % 2 != 0) {
      base = copyOfRange(base, 1, base.length)
    } else {
      base = copyOfRange(base, 2, base.length)
    }
    return base
  }
  
  /**
   * Transforms a binary array to hexadecimal format + terminator
   *
   * @param str byte[]
   * @return array with each individual nibble adding a terminator at the end
   */
  def binToNibbles(str : Array[Byte]) : Array[Byte] = {
    var hexEncoded : Array[Byte] = encode(str)
    var hexEncodedTerminated : Array[Byte] = Arrays.copyOf(hexEncoded, hexEncoded.length + 1)
    
    for (i <- 0 until hexEncoded.length) {
      var b : Byte = hexEncodedTerminated(i)
      hexEncodedTerminated(i) = hexMap.get(b.asInstanceOf[Character])
    }

    hexEncodedTerminated(hexEncodedTerminated.length - 1) = TERMINATOR
    return hexEncodedTerminated
  }
  
  def binToNibblesNoTerminator(str : Array[Byte]) : Array[Byte] = {
    var hexEncoded : Array[Byte] = encode(str)
    
    for (i <- 0 until hexEncoded.length) {
      var b : Byte = hexEncoded(i)
      hexEncoded(i) = hexMap.get(b.asInstanceOf[Character])
    }
    return hexEncoded
  }
  
  
}