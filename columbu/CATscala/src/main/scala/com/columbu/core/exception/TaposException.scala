package com.galileo.core.exception

class TaposException(message: String, cause: Throwable) extends Exception(message, cause){

  def this(){
    this("", null)
  }

  def this(msg : String ){
    this(msg, null)
  }
}
