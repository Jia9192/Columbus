package com.galileo.utils

import java.util.List
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import java.util.Iterator
import java.util.concurrent.ThreadFactory

class ExecutorLoop[In] {
  
  private var queue : BlockingQueue[Runnable] = null
  private var exec : ThreadPoolExecutor = null
  private var consumer : Consumer[In] = null
  private var exceptionHandler : Consumer[Throwable] = null
  private var threadPoolName : String = null
  private var threadNumber : AtomicInteger = new AtomicInteger(1)
  
  
  def this(threads : Int, queueSize : Int, consumerValue : Consumer[In], exceptionHandlerValue : Consumer[Throwable]) {
    this()
    this.queue = new ExecutorLoop.LimitedQueue[Runnable](queueSize)
    this.consumer = consumerValue
    this.exceptionHandler = exceptionHandlerValue
    this.threadPoolName = "loop-" + ExecutorLoop.loopNum.getAndIncrement
    
    var temp : String = threadPoolName + "-" + threadNumber.getAndIncrement
    var r : ThreadFactory = new ThreadFactory() {
      override def newThread(value : Runnable) : Thread = {
        return new Thread(value, temp)
      }
    }
    this.exec = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS, queue, r)
  }
  
  def push(in : In) : Unit = {
    exec.execute(new Runnable() {
      def run : Unit = {
        try {
          consumer.accept(in)
        } catch {
          case e: Throwable => exceptionHandler.accept(e)
        }
      }
    });
  }
  
  def pushAll(list : List[In]) : Unit = {
    var iter : Iterator[In]  = list.iterator
    while (iter.hasNext) {
      push(iter.next)
    }
  }
  
  def setThreadPoolName(threadPoolNameValue : String) : ExecutorLoop[In] = {
    this.threadPoolName = threadPoolNameValue
    return this
  }
  
  def getQueue : BlockingQueue[Runnable] = queue
  
  def shutdown : Unit = {
    try {
      exec.shutdown()
    } catch {
      case e: Exception => e.printStackTrace() // TODO: handle error
    }
  }
  
  def isShutdown : Boolean = exec.isShutdown()
  
  
  @throws[InterruptedException]
  def join : Unit = {
    exec.shutdown()
    exec.awaitTermination(10, TimeUnit.MINUTES)
  }

}


object ExecutorLoop {
  private var loopNum : AtomicInteger = new AtomicInteger(1)
  
  private class LimitedQueue[E](maxSize : Int) extends LinkedBlockingQueue[E](maxSize) {
    
    override def offer(e : E) : Boolean = {
      try {
        put(e);
        return true
      } catch {
        case ie: InterruptedException => Thread.currentThread().interrupt()
      }
      return false
    }
    
  }
  
}




