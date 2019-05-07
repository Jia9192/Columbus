package com.galileo.utils

import java.util.Optional
import java.util.function.Consumer

import com.galileo.db.levelDB.core.ISession

final class SessionOptional {
  
  private var value : Optional[ISession] = Optional.empty()
  

  def setValue(value : ISession) : SessionOptional = {
    this.synchronized {
      if (!this.value.isPresent()) {
        this.value = Optional.of(value);
      }
      return this
    }
  }
  
  def valid : Boolean = {
    this.synchronized {
      value.isPresent()
    }
  }
  
  def reset : Unit = {
    this.synchronized {
      var temp : Consumer[ISession] = new Consumer[ISession]() {
        override def accept(iSession : ISession) : Unit = {
          iSession.destroy()
        }
      }
      value.ifPresent(temp)
      value = Optional.empty()
    }
  }
  
  private object OptionalEnum extends Enumeration {
    
    val INSTANCE = Value
    
    private var instance : SessionOptional = new SessionOptional()
    
    def getInstance : SessionOptional = instance
  }
  
}

final object SessionOptional {
  
  private val value : SessionOptional = (new SessionOptional().OptionalEnum).getInstance
  
  def instance : SessionOptional = {
    return value
  }
  
}