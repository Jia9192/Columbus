package com.galileo.utils

import java.util.Collection
import java.util.HashSet
import java.util.Iterator
import java.util.Set

import com.galileo.db.levelDB.store.ByteArrayWrapper

class ByteArraySet(value : Set[ByteArrayWrapper]) extends Set[Array[Byte]] {
  
  private var delegate : Set[ByteArrayWrapper] = value

  def this() {
    this(new HashSet[ByteArrayWrapper]())
  }
  
  
  
  override def size : Int = delegate.size()
  
  override def isEmpty : Boolean = delegate.isEmpty()
  
  override def contains(o : AnyRef) : Boolean = {
    var temp : Array[Byte] = o.asInstanceOf[Array[Byte]]
    return delegate.contains(new ByteArrayWrapper(temp))
  }
  
  override def iterator() : Iterator[Array[Byte]] = {
    return new Iterator[Array[Byte]]() {
      var it : Iterator[ByteArrayWrapper] = delegate.iterator()

      override def hasNext : Boolean = it.hasNext()
      
      override def next : Array[Byte] = it.next().getData
      
      override def remove : Unit = it.remove()
    }
  }
  
  override def toArray() : Array[AnyRef] = {
    var ret : Array[Array[Byte]]= Array.ofDim[Byte](size(), 0)
    
    var arr : Array[ByteArrayWrapper] = delegate.toArray(new Array[ByteArrayWrapper](size()))
    var i = 0
    for (x <- arr) {
      ret(i) = arr(i).getData
      i = i + 1
    }
    return ret.asInstanceOf[Array[AnyRef]]
  }
  
  override def toArray[T](a: Array[T with AnyRef]): Array[T with AnyRef] = {
    return toArray().asInstanceOf[Array[T with AnyRef]]
  }

	override def add(bytes : Array[Byte]) : Boolean = delegate.add(new ByteArrayWrapper(bytes))
	
	override def remove(o : AnyRef) : Boolean = {
	  var temp : Array[Byte] = o.asInstanceOf[Array[Byte]]
	  return delegate.remove(new ByteArrayWrapper(temp))
	}
	
	override def containsAll(c : Collection[_]) : Boolean = throw new RuntimeException("Not implemented")

	override def addAll(c : Collection[_ <: Array[Byte]]) : Boolean = {
	  var ref = false
	  
	  var temp : Collection[Array[Byte]] = c.asInstanceOf[Collection[Array[Byte]]]
	  var it : Iterator[Array[Byte]] = temp.iterator
		while(it.hasNext()) {
		  ref = ref | add(it.next())
		}
	  return ref
	}
	
	override def retainAll(c : Collection[_]) : Boolean = throw new RuntimeException("Not implemented")
	
	override def removeAll(c : Collection[_]) : Boolean = throw new RuntimeException("Not implemented")
	
	override def clear() : Unit = delegate.clear()
	
	override def equals(o : Any) : Boolean = throw new RuntimeException("Not implemented")
	
	override def hashCode() : Int = throw new RuntimeException("Not implemented")

}
