package com.galileo.core.exception

class RevokingStoreIllegalStateException(message: String, cause: Throwable) extends TronRuntimeException(message, cause){

  def this(){
    this("", null)
  }

  def this(msg : String ){
    this(msg, null)
  }

  def this(cause: Throwable){
    this("",cause)
  }
}

object RevokingStoreIllegalStateException{
  val serialVersionUID : Long = -1848914673093119416L
}
