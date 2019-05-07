package com.galileo.core.exception

class BadItemException(message: String, cause: Throwable) extends StoreException(message, cause){

  def this(){
    this("",null)
  }

  def this(message: String){
    this(message ,null)
  }
}
