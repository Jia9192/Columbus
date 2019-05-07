package com.galileo.utils

import java.io.UnsupportedEncodingException
import java.math.BigInteger

object Base58 {

  val ALPHABET: Array[Char] = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray
  private val INDEXES = new Array[Int](128)

  {
    var i = 0
    for(i <- 0 until INDEXES.length){
      INDEXES(i) = -1
    }
    var j = 0
    for(j <- 0 until ALPHABET.length){
      INDEXES(ALPHABET(j)) = j
    }

  }

  /**
    * Encodes the given bytes in base58. No checksum is appended.
    */
  def encode(input: Array[Byte]): String = {
    if (input.length == 0)
      return ""
    var inputData = copyOfRange(input, 0, input.length)
    // Count leading zeroes.
    var zeroCount = 0
    while ( {
      zeroCount < inputData.length && inputData(zeroCount) == 0
    }) zeroCount += 1
    // The actual encoding.
    val temp = new Array[Byte](inputData.length * 2)
    var j = temp.length
    var startAt = zeroCount
    while ( {
      startAt < inputData.length
    }) {
      val mod = divmod58(inputData, startAt)
      if (inputData(startAt) == 0) startAt += 1
      temp({
        j -= 1; j
      }) = ALPHABET(mod).toByte
    }
    // Strip extra '1' if there are some after decoding.
    while ( {
      j < temp.length && temp(j) == ALPHABET(0)
    }) j += 1
    // Add as many leading '1' as there were leading zeros.
    while ( {
      {
        zeroCount -= 1; zeroCount
      } >= 0
    }) temp({
      j -= 1; j
    }) = ALPHABET(0).toByte
    val output = copyOfRange(temp, j, temp.length)
    try
      new String(output, "US-ASCII")
    catch {
      case e: UnsupportedEncodingException =>
        throw new RuntimeException(e) // Cannot happen.

    }
  }

  @throws[IllegalArgumentException]
  def decode(input: String): Array[Byte] = {
    if (input.length == 0) return new Array[Byte](0)
    val input58 = new Array[Byte](input.length)
    // Transform the String to a base58 byte sequence
    var i = 0
    while ( {
      i < input.length
    }) {
      val c = input.charAt(i)
      var digit58 = -1
      if (c >= 0 && c < 128) digit58 = INDEXES(c)
      if (digit58 < 0) throw new IllegalArgumentException("Illegal character " + c + " at " + i)
      input58(i) = digit58.toByte

      {
        i += 1; i
      }
    }
    // Count leading zeroes
    var zeroCount = 0
    while ( {
      zeroCount < input58.length && input58(zeroCount) == 0
    }) zeroCount += 1
    // The encoding
    val temp = new Array[Byte](input.length)
    var j = temp.length
    var startAt = zeroCount
    while ( {
      startAt < input58.length
    }) {
      val mod = divmod256(input58, startAt)
      if (input58(startAt) == 0) startAt += 1
      temp({
        j -= 1; j
      }) = mod
    }
    // Do no add extra leading zeroes, move j to first non null byte.
    while ( {
      j < temp.length && temp(j) == 0
    }) j += 1
    copyOfRange(temp, j - zeroCount, temp.length)
  }

  @throws[IllegalArgumentException]
  def decodeToBigInteger(input: String) = new BigInteger(1, decode(input))

  //
  // number -> number / 58, returns number % 58
  //
  private def divmod58(number: Array[Byte], startAt: Int) = {
    var remainder = 0
    var i = startAt
    while ( {
      i < number.length
    }) {
      val digit256 = number(i).toInt & 0xFF
      val temp = remainder * 256 + digit256
      number(i) = (temp / 58).toByte
      remainder = temp % 58

      {
        i += 1; i - 1
      }
    }
    remainder.toByte
  }

  // number -> number / 256, returns number % 256
  private def divmod256(number58: Array[Byte], startAt: Int) = {
    var remainder = 0
    var i = startAt
    while ( {
      i < number58.length
    }) {
      val digit58 = number58(i).toInt & 0xFF
      val temp = remainder * 58 + digit58
      number58(i) = (temp / 256).toByte
      remainder = temp % 256

      {
        i += 1; i - 1
      }
    }
    remainder.toByte
  }

  private def copyOfRange(source: Array[Byte], from: Int, to: Int) = {
    val range = new Array[Byte](to - from)
    System.arraycopy(source, from, range, 0, range.length)
    range
  }
}
