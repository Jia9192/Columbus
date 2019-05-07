package com.galileo.core.exception

class BadTransactionException(message: String, cause: Throwable) extends TronException(message,cause){

  def this(){
    this("", null)
  }

  def this(message: String){
    this(message, null)
  }
}
