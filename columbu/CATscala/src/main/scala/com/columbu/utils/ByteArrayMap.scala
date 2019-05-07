package com.galileo.utils

import java.util.Collection
import java.util.HashMap
import java.util.Iterator
import java.util.Map
import java.util.Map.Entry
import java.util.Set

import com.google.common.collect.Maps;
import com.galileo.db.levelDB.store.ByteArrayWrapper

class ByteArrayMap[V](value : Map[ByteArrayWrapper, V]) extends Map[Array[Byte], V] {
  
  private var delegate : Map[ByteArrayWrapper, V] = value

  def this() {
    this(new HashMap[ByteArrayWrapper, V]())
  }
  
  
  override def size : Int = delegate.size()
  
  override def isEmpty : Boolean = delegate.isEmpty()
  
  override def containsKey(key : Any) : Boolean = {
    var k : Array[Byte] = key.asInstanceOf[Array[Byte]]
    return delegate.containsKey(new ByteArrayWrapper(k))
  }
  
  override def containsValue(value : Any) : Boolean = {
    return delegate.containsValue(value)
  }

  override def get(key : Any) : V = {
    var k : Array[Byte] = key.asInstanceOf[Array[Byte]]
    return delegate.get(new ByteArrayWrapper(k))
  }
  
  override def put(key : Array[Byte], value : V) : V = {
    return delegate.put(new ByteArrayWrapper(key), value)
  }
  
  override def remove(key : Any) : V = {
    var k : Array[Byte] = key.asInstanceOf[Array[Byte]]
    return delegate.remove(new ByteArrayWrapper(k))
  }
  
  override def putAll(m : Map[_ <: Array[Byte], _ <: V]) : Unit = {
    var iterator : Iterator[Entry[Array[Byte], V]] = m.asInstanceOf[Map[Array[Byte], V]].entrySet().iterator();
    while (iterator.hasNext()) {
      var next : Entry[Array[Byte], V] = iterator.next()
      delegate.put(new ByteArrayWrapper(next.getKey()), next.getValue())
    }
  }
  
  override def clear : Unit = delegate.clear()
  
  override def keySet : Set[Array[Byte]] = {
    return new ByteArraySet(new SetAdapter[ByteArrayWrapper](delegate))
  }
  
  override def values : Collection[V] = delegate.values()
  
  override def entrySet : Set[Entry[Array[Byte], V]]  = {
		return new MapEntrySet(delegate.entrySet())  
  }
  
  override def equals(o : Any) : Boolean = { delegate.equals(o) }
  
  override def hashCode : Int = delegate.hashCode()
  
  override def toString : String = delegate.toString()
  

  
  private class MapEntrySet(value : Set[Entry[ByteArrayWrapper, V]]) extends Set[Entry[Array[Byte], V]] {
    
    private var delegate : Set[Entry[ByteArrayWrapper, V]] = value
    
    
    override def size : Int = delegate.size
    
    override def isEmpty : Boolean = delegate.isEmpty
    
    override def contains(o : Any) : Boolean = {
      throw new RuntimeException("Not implemented")
    }

    override def iterator() : Iterator[Entry[Array[Byte], V]] = {
      var it : Iterator[Entry[ByteArrayWrapper, V]] = delegate.iterator()
      return new Iterator[Entry[Array[Byte], V]]() {
        
        override def hasNext : Boolean = it.hasNext()
        
        override def next() : Entry[Array[Byte], V] = {
          var next : Entry[ByteArrayWrapper, V] = it.next()
          return Maps.immutableEntry(next.getKey().getData, next.getValue())
        }
        
        override def remove : Unit = it.remove
        
      };
    }
    
    override def toArray : Array[AnyRef] = throw new RuntimeException("Not implemented")
    
    override def toArray[T](a : Array[T with AnyRef]) : Array[T with AnyRef] = {
      throw new RuntimeException("Not implemented")
    }
    
    override def add(vEntry : Entry[Array[Byte], V]) : Boolean = {
      throw new RuntimeException("Not implemented")
    }
    
    override def remove(o : Any) : Boolean = {
      throw new RuntimeException("Not implemented")
    }
    
    override def containsAll(c : Collection[_]) : Boolean = {
      throw new RuntimeException("Not implemented")
    }
    
    override def addAll(c : Collection[_ <: Entry[Array[Byte], V]]) : Boolean = {
      throw new RuntimeException("Not implemented")
    }
    
    override def retainAll(c : Collection[_]) : Boolean = {
      throw new RuntimeException("Not implemented")
    }
    
    override def removeAll(c : Collection[_]) : Boolean = {
      throw new RuntimeException("Not implemented")
    }

    override def clear : Unit = throw new RuntimeException("Not implemented")
    
  }
  
  

  
}
