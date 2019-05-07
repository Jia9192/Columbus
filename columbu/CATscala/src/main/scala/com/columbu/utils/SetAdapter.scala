package com.galileo.utils

import java.util.Collection
import java.util.Iterator
import java.util.Map
import java.util.Set

class SetAdapter[E] extends Set[E] {
  
  private var DummyValue : AnyRef = new AnyRef()
  
  var delegate : Map[E, AnyRef] = null

  def this(value : Map[E, _]) {
    this()
    this.delegate = value.asInstanceOf[Map[E, AnyRef]]
  }
  
  
  
  override def size : Int = delegate.size()
  
  override def isEmpty : Boolean = delegate.isEmpty()
  
  override def contains(o : Any) : Boolean = delegate.containsKey(o)
  
  override def iterator : Iterator[E] = delegate.keySet().iterator()
  
  override def toArray : Array[AnyRef] = delegate.keySet().toArray()
  
  override def toArray[T](a : Array[T with AnyRef]) : Array[T with AnyRef] = {
		var result : Array[T with AnyRef] = null
    
	  var keysSet : Set[E]  = delegate.keySet()
    if (a.length <= keysSet.size) {
      result = new Array[T with AnyRef](keysSet.size) 
    } else {
      result = new Array[T with AnyRef](a.length) 
    }
    
    var it : Iterator[E] = keysSet.iterator
    var i = 0
		while(it.hasNext()) {
		  result(i) = it.next().asInstanceOf[T with AnyRef]
		  i = i+1
		}
    return result
  }
  
  override def add(e : E) : Boolean = delegate.put(e, DummyValue) == null
  
  override def remove(o : Any) : Boolean = delegate.remove(o) != null
  
  override def containsAll(c : Collection[_]) : Boolean = delegate.keySet().containsAll(c)
  
  override def addAll(c : Collection[_ <: E]) : Boolean = {
    var ret = false
    var iterator : Iterator[E] = c.iterator().asInstanceOf[Iterator[E]]
		
		while(iterator.hasNext()) {
		  ret = ret | add(iterator.next)
		}
    return ret
  }
  
  override def retainAll(c : Collection[_]) : Boolean = {
    throw new RuntimeException("Not implemented"); // TODO add later if required
  }
  
  override def removeAll(c : Collection[_]) : Boolean = {
    var ret = false
    
    var iterator : Iterator[AnyRef] = c.asInstanceOf[Collection[AnyRef]].iterator();
		while(iterator.hasNext()) {
		  ret = ret | remove(iterator.next)
		}
    return ret
  }
  
  override def clear : Unit = delegate.clear()


}
