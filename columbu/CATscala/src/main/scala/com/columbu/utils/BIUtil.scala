package com.galileo.utils

import java.math.BigInteger

object BIUtil {
  
  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is less than valueB is zero
   */
  def isLessThan(valueA : BigInteger, valueB : BigInteger) : Boolean = {
    return valueA.compareTo(valueB) < 0
  }
  
  /**
   * @param value - not null
   * @return true - if the param is zero
   */
  def isZero(value : BigInteger) : Boolean = {
    return value.compareTo(BigInteger.ZERO) == 0
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is equal to valueB is zero
   */
  def isEqual(valueA : BigInteger, valueB : BigInteger) : Boolean = {
    return valueA.compareTo(valueB) == 0
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is not equal to valueB is zero
   */
  def isNotEqual(valueA : BigInteger, valueB : BigInteger) : Boolean = {
    return !isEqual(valueA, valueB)
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is more than valueB is zero
   */
  def isMoreThan(valueA : BigInteger, valueB : BigInteger) : Boolean = {
    return valueA.compareTo(valueB) > 0
  }


  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return sum - valueA + valueB
   */
  def sum(valueA : BigInteger, valueB : BigInteger) : BigInteger = {
    return valueA.add(valueB)
  }


  /**
   * @param data = not null
   * @return new positive BigInteger
   */
  def toBI(data : Array[Byte]) : BigInteger = {
    return new BigInteger(1, data)
  }

  /**
   * @param data = not null
   * @return new positive BigInteger
   */
  def toBI(data : Long) : BigInteger = {
    return BigInteger.valueOf(data)
  }

  def isPositive(value : BigInteger) : Boolean = {
    return value.signum() > 0
  }
  
  def isPositive(covers : BigInteger, value : BigInteger) : Boolean = {
    return covers.compareTo(value) < 0
  }

  def max(first : BigInteger, second : BigInteger) : BigInteger = {
    if (first.compareTo(second) < 0 ) return second  
    else return first 
  }

  /**
   * Returns a result of safe addition of two {@code int} values
   * {@code Integer.MAX_VALUE} is returned if overflow occurs
   */
  def addSafely(a : Int, b : Int) : Int = {
    var res : Long = a + b
    if(res > Integer.MAX_VALUE) {
    	return Integer.MAX_VALUE
    } else {
    	return res.asInstanceOf[Int]
    }
  }
  
  

  
}
