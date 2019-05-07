package com.galileo.utils

import java.util.ArrayList
import java.util.Collection
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Set
import java.util.function.Function
import java.util.function.Predicate
import java.util.Iterator

object CollectionUtils {
  
  def collectList[K, V](items : Collection[K], collector : Function[K, V]) : List[V] = {
    var collected : List[V] = new ArrayList[V](items.size())
      
    var iter : Iterator[K]  = items.iterator
    while (iter.hasNext) {
      collected.add(collector.apply(iter.next))
    }
    return collected
  }

  def collectSet[K, V](items : Collection[K], collector : Function[K, V]) : Set[V] = {
    var collected : Set[V] = new HashSet[V]()
    var iter : Iterator[K]  = items.iterator
    while (iter.hasNext) {
      collected.add(collector.apply(iter.next))
    }
    return collected
  }
  
  def truncate[T](items : List[T], limit : Int) : List[T] = {
    var flag = true 
    if (limit > items.size()) {
      return new ArrayList[T](items)
    }
    var truncated : List[T] = new ArrayList[T](limit)
    val iter : Iterator[T]  = items.iterator
    while (iter.hasNext && flag) {
      truncated.add(iter.next)
      if(truncated.size() == limit) {
        flag = false  
      }
    }
    return truncated
  }
  
  def truncateRandom[T](items : List[T], limit : Int, confirm : Int) : List[T] = {
    var flag = true 
    if (limit > items.size()) {
      return new ArrayList[T](items)
    }
    
    var truncated : List[T] = new ArrayList[T](limit)
    if (confirm >= limit) {
      val iter : Iterator[T]  = items.iterator
      while (iter.hasNext && flag) {
        truncated.add(iter.next)
        if(truncated.size() == limit) {
          flag = false  
        }
      }
    } else {
      if (confirm > 0) {
          truncated.addAll(items.subList(0, confirm))
      }
      var endList : List[T] = items.subList(confirm, items.size())
      Collections.shuffle(endList)
      for (i <- 0 until 10) {
        truncated.add(endList.get(i))
      }
    }
    return truncated
  }
  
  def selectList[T](items : Collection[T], predicate : Predicate[T]) : List[T] = {
    var selected : List[T] = new ArrayList[T]()
    val iter : Iterator[T]  = items.iterator
    while (iter.hasNext) {
      if(predicate.test(iter.next)) {
        selected.add(iter.next)
      }
    }
    return selected
  }
  
  def selectSet[T](items : Collection[T], predicate : Predicate[T]) : Set[T] = {
    var selected : Set[T] = new HashSet[T]()
    val iter : Iterator[T]  = items.iterator
    while (iter.hasNext) {
      if(predicate.test(iter.next)) {
        selected.add(iter.next)
      }
    }
    return selected
  }
  

  
}