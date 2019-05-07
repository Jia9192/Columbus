package com.galileo.core.exception

class TraitorPeerException(message: String, cause: Throwable) extends TronException(message, cause){

  def this(){
    this("",null)
  }

  def this(msg : String ){
    this(msg, null)
  }
}
