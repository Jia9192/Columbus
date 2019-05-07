package com.galileo.utils

object FastByteComparisons {
  
  trait Comparer[T] {
    def compareTo(buffer1 : T, offset1 : Int, length1 : Int, buffer2 : T, offset2 : Int, length2 : Int) : Int
  }
  
  
  def isEqual(b1 : Array[Byte], b2 : Array[Byte]) : Boolean = {
    return b1.length == b2.length && compareTo(b1, 0, b1.length, b2, 0, b2.length) == 0
  }
  
  
  /**
   * Lexicographically compare two byte arrays.
   *
   * @param b1 buffer1
   * @param s1 offset1
   * @param l1 length1
   * @param b2 buffer2
   * @param s2 offset2
   * @param l2 length2
   * @return int
   */
  def compareTo(b1 : Array[Byte], s1 : Int, l1 : Int, b2 : Array[Byte], s2 : Int, l2 : Int) : Int = {
    
    var temp : Comparer[Array[Byte]] = (new FastByteComparisons.LexicographicalComparerHolder).BEST_COMPARER
    return temp.compareTo(b1, s1, l1, b2, s2, l2)
  }
  
  def lexicographicalComparerJavaImpl() : Comparer[Array[Byte]] = {
    var temp = (new FastByteComparisons.LexicographicalComparerHolder).PureJavaComparer.INSTANCE
    return temp.asInstanceOf[Comparer[Array[Byte]]]
  }
  
  /**
   * <p>Uses reflection to gracefully fall back to the Java implementation if
   * {@code Unsafe} isn't available.
   */
  private class LexicographicalComparerHolder {
    
    val UNSAFE_COMPARER_NAME = (new FastByteComparisons.LexicographicalComparerHolder).getClass.getName() + "$UnsafeComparer"
    
    val BEST_COMPARER : Comparer[Array[Byte]] = getBestComparer
    
    /**
     * Returns the Unsafe-using Comparer, or falls back to the pure-Java
     * implementation if unable to do so.
     */
    def getBestComparer : Comparer[Array[Byte]] = {
      try {
        var theClass : Class[_]  = Class.forName(UNSAFE_COMPARER_NAME)
        var comparer : Comparer[Array[Byte]] = theClass.getEnumConstants.asInstanceOf[Comparer[Array[Byte]]]
        return comparer
      } catch {
        case t: Throwable => return lexicographicalComparerJavaImpl()
      }
    }
    
    
    object PureJavaComparer extends Enumeration with Comparer[Array[Byte]] {
      val INSTANCE = Value//在这里定义具体的枚举实例
      
      override def compareTo(buffer1 : Array[Byte], offset1 : Int, length1 : Int, 
          buffer2 : Array[Byte], offset2 : Int, length2 : Int) : Int = {
        // Short circuit equal case
        if(buffer1 == buffer2 && offset1 == offset2 && length1 == length2) {
          return 0
        }
        val end1 = offset1 + length1
        val end2 = offset2 + length2
        for (i <- offset1 until  end1; j <- offset2 until  end2) {
          val a : Int = (buffer1(i) & 0xff);
          val b : Int = (buffer2(j) & 0xff);
          if (a != b) {
            return a - b;
          }
        }
        return length1 - length2
      }
    }

            
  }
  
}
