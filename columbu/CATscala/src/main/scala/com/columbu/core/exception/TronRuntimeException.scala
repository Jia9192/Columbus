package com.galileo.core.exception

class TronRuntimeException(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean) extends RuntimeException(message,cause,enableSuppression,writableStackTrace){

  def this(){
    this("",null, true, true)
  }

  def this(msg : String){
    this(msg,null, true, true)
  }

  def this(cause: Throwable){
    this("",cause, true, true)
  }

  def this(msg : String, cause: Throwable){
    this(msg,cause, true, true)
  }

}
