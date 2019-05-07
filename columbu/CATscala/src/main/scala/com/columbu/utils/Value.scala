package com.galileo.utils

import com.cedarsoftware.util.DeepEquals
import java.math.BigInteger
import java.util.Arrays
import java.util.List
import java.util.function.ToIntFunction
import org.spongycastle.util.encoders.Hex

/**
 * Class to encapsulate an object and provide utilities for conversion
 */
class Value {
  
  private var value : AnyRef = null
  private var serializable : Array[Byte] = null
  private var sha3 : Array[Byte] = null
  private var decoded : Boolean = false
  
  def this(obj : AnyRef) {
    this()
    this.decoded = true
    if (obj != null) {
      if (obj.isInstanceOf[Value]) {
        this.value = obj.asInstanceOf[Value].asObj
      } else {
        this.value = obj
      }
    }
  }
  
  
  def fromSerEncoded(data : Array[Byte]) : Value = {
    if (data != null && data.length != 0) {
      var v : Value = new Value()
      v.init(data)
      return v
    }
    return null
  }
  
  def init(serializable : Array[Byte]) : Unit = this.serializable = serializable
  
  def withHash(hash : Array[Byte]) : Value = {
    sha3 = hash
    return this
  }

  /* *****************
   *      Convert
   * *****************/

  def asObj : AnyRef = value
  
  def asList : List[AnyRef] = {
    var valueArray : Array[AnyRef] = value.asInstanceOf[Array[AnyRef]]
    return Arrays.asList(valueArray)
  }
  
  def asInt : Int = {
    if (isInt) {
      return value.asInstanceOf[Integer]
    } else if (isBytes) {
      return new BigInteger(1, asBytes).intValue()
    }
    return 0
  }
  
  def asLong : Long = {
    if (isLong) {
      return value.asInstanceOf[Long]
    } else if (isBytes) {
      return new BigInteger(1, asBytes).longValueExact()
    }
    return 0
  }
  
  def asBigInt : BigInteger = {
    return value.asInstanceOf[BigInteger]
  }
  
  def asString : String = {
    if (isBytes) {
      return new String(value.asInstanceOf[Array[Byte]])
    } else if (isString) {
      return value.asInstanceOf[String]
    }
    return ""
  }
  
  def asBytes : Array[Byte] = {
    if (isBytes) {
      return value.asInstanceOf[Array[Byte]]
    } else if (isString) {
      return asString.getBytes()
    }
    return ByteUtil.EMPTY_BYTE_ARRAY
  }
  
  def asSlice : Array[Int] = value.asInstanceOf[Array[Int]]
  
  def get(index : Int) : Value = {
    if (isList) {
      // Guard for OutOfBounds
      if (asList.size() <= index) {
        return new Value(null)
      }
      if (index < 0) {
        throw new RuntimeException("Negative index not allowed")
      }
      return new Value(asList.get(index))
    }
    // If this wasn't a slice you probably shouldn't be using this function
    return new Value(null)
  }


  /* *****************
   *      Utility
   * *****************/
  def cmp(o : Value) : Boolean = DeepEquals.deepEquals(this, o)
  
  

  /* *****************
   *      Checks
   * *****************/
  
  def isList : Boolean = {
    return value != null && value.getClass().isArray() && !value.getClass().getComponentType().isPrimitive()
  }
  def isString : Boolean = value.isInstanceOf[String]
  def isInt : Boolean = value.isInstanceOf[Integer]
  def isLong : Boolean = value.isInstanceOf[Long]
  def isBigInt : Boolean = value.isInstanceOf[BigInteger]
  def isBytes : Boolean = value.isInstanceOf[Array[Byte]]

  def isReadableString : Boolean = {
    var readableChars : Int = 0
    var data : Array[Byte] = value.asInstanceOf[Array[Byte]]
    if (data.length == 1 && data(0) > 31 && data(0) < 126) {
      return true;
    }
    data.foreach {
      aData => 
        if (aData > 32 && aData < 126) {
        	readableChars = readableChars + 1 
        }
    }
    
    return readableChars.asInstanceOf[Double] / data.length.asInstanceOf[Double] > 0.55
  }
  
  // it's only if the isBytes() = true;
  def isHexString : Boolean = {
    var hexChars : Int = 0
    var data : Array[Byte] = value.asInstanceOf[Array[Byte]]
    
    data.foreach {
      aData => 
        if ((aData >= 48 && aData <= 57) || (aData >= 97 && aData <= 102)) {
        	hexChars = hexChars + 1 
        }
    }
    return hexChars.asInstanceOf[Double] / data.length.asInstanceOf[Double] > 0.9
  }
  
  def isHashCode : Boolean = {
		  this.asBytes.length == 32
  }
  
  def isNull : Boolean = {
		  value == null
  }
  
  def isEmpty : Boolean = {
		 if (isNull) {
      return true
    }
    if (isBytes && asBytes.length == 0) {
      return true
    }
    if (isList && asList.isEmpty()) {
      return true
    }
    return isString && asString.equals("")
  }
  
  def length : Int = {
		 if (isList) {
      return asList.size();
    } else if (isBytes) {
      return asBytes.length;
    } else if (isString) {
      return asString.length();
    }
    return 0
  }
  
  override def toString : String = {
    var stringBuilder : StringBuilder = new StringBuilder()
    
    if (isList) {
      var list : Array[AnyRef] = value.asInstanceOf[Array[AnyRef]]

      // special case - key/value node
      if (list.length == 2) {
        stringBuilder.append("[ ");
        var key : Value = new Value(list(0))
        
        var keyNibbles : Array[Byte] = CompactEncoder.binToNibblesNoTerminator(key.asBytes)
        var keyString : String = ByteUtil.nibblesToPrettyString(keyNibbles)
        stringBuilder.append(keyString)

        stringBuilder.append(",")

        var temp : Value = new Value(list(1))
        
        stringBuilder.append(temp.toString())

        stringBuilder.append(" ]");
        return stringBuilder.toString()
      }
      stringBuilder.append(" [");

      for (i <- 0 until list.length) {
        var valu : Value = new Value(list(i))
        if (valu.isString || valu.isEmpty) {
          stringBuilder.append("'").append(valu.toString()).append("'")
        } else {
          stringBuilder.append(valu.toString())
        }
        if (i < list.length - 1) {
          stringBuilder.append(", ");
        }
      }
      stringBuilder.append("] ");

      return stringBuilder.toString()
    } else if (isEmpty) {
      return ""
    } else if (isBytes) {
      var output : StringBuilder = new StringBuilder()
      if (isHashCode) {
        output.append(Hex.toHexString(asBytes))
      } else if (isReadableString) {
        output.append("'")
        asBytes.foreach {
          oneByte => 
            if (oneByte < 16) {
              output.append("\\x").append(ByteUtil.oneByteToHexString(oneByte))
            } else {
              output.append(Character.valueOf(oneByte.asInstanceOf[Char]))
            }
        }
        output.append("'")
        return output.toString()
      }
      return Hex.toHexString(this.asBytes)
    } else if (isString) {
      return asString
    }
    return "Unexpected type"
  }
  
  def countBranchNodes : Int = {
		 if (this.isList) {
		   var temp : ToIntFunction[AnyRef] = new ToIntFunction[AnyRef]() {
		     override def applyAsInt(obj : AnyRef) : Int = {
		       return (new Value(obj)).countBranchNodes
		     }
		   }
      return this.asList.stream().mapToInt(temp).sum()
    } else if (this.isBytes) {
      this.asBytes
    }
    return 0
  }
  
  


  
}