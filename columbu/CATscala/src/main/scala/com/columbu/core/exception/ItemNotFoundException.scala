package com.galileo.core.exception

class ItemNotFoundException(msg : String , cause: Throwable) extends StoreException(msg,cause){

  def this(){
    this("", null)
  }

  def this(msg : String ){
    this(msg, null)
  }
}
