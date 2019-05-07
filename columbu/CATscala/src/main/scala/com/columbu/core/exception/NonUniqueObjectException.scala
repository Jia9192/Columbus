package com.galileo.core.exception

class NonUniqueObjectException(msg : String ,cause: Throwable) extends TronException(msg, cause){

  def this(){
    this("", null)
  }

  def this(msg : String ){
    this(msg, null)
  }

  def this(cause: Throwable){
    this("", cause)
  }
}
