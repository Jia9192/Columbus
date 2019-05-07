package com.galileo.core.exception

class TronException(msg : String, cause: Throwable) extends Exception(msg, cause){

  def this(){
    this("", null)
  }

  def this(msg : String){
    this(msg, null)
  }

}
