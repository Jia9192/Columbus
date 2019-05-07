package com.galileo.utils

import java.util.Arrays
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.ToIntFunction

class SlidingWindowCounter {
  
  @volatile
  private var slotBaseCounter : SlotBaseCounter = null
  @volatile
  private var windowSize : Int = 0
  @volatile
  private var head : Int = 0
  
  def this(windowSizeValue : Int) {
    this()
    resizeWindow(windowSizeValue)
  }
  
  def resizeWindow(windowSizeValue : Int) : Unit = {
    this.synchronized {
      this.windowSize = windowSizeValue
      this.slotBaseCounter = new SlotBaseCounter(windowSizeValue)
      this.head = 0
    }
  }
  
  def increase : Unit = {
    slotBaseCounter.increaseSlot(head)
  }
  
  def totalAndAdvance : Int = {
    var total : Int = totalCount
    advance
    return total
  }
  
  def advance : Unit = {
    var tail : Int = (head + 1) % windowSize
    slotBaseCounter.wipeSlot(tail)
    head = tail
  }
  
  def totalCount : Int = {
    return slotBaseCounter.totalCount
  }
  
  override def toString : String =  {
    return "total = " + totalCount + " head = " + head + " >> " + slotBaseCounter
  }
  
}

protected class SlotBaseCounter {
  
  private var slotSize : Int = 0
  private var slotCounter : Array[AtomicInteger] = null
  
  def this(slotSizeValue : Int) {
    this()
    var slotSizeValu = slotSizeValue
    if (slotSizeValu < 1) {
      slotSizeValu = 1
    }
    this.slotSize = slotSizeValu
    this.slotCounter = new Array[AtomicInteger](slotSizeValu)
    for (i <- 0 until this.slotSize) {
      slotCounter(i) = new AtomicInteger(0)
    }
  }
  
  def increaseSlot(slotSizevalue : Int) = {
    slotCounter(slotSizevalue).incrementAndGet()
  }
  
  def wipeSlot(slotSizevalue : Int) = {
    slotCounter(slotSizevalue).set(0)
  }
  
  def totalCount : Int = {
    var temp : ToIntFunction[AtomicInteger] = new ToIntFunction[AtomicInteger](){
      override def applyAsInt(value : AtomicInteger) : Int = {
        return value.get()
      }
    }
    return Arrays.stream(slotCounter).mapToInt(temp).sum()
  }
  
  override def toString : String =  {
    return Arrays.toString(slotCounter.asInstanceOf[Array[AnyRef]])
  }
  
}

