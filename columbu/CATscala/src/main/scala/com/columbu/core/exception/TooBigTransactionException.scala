package com.galileo.core.exception

class TooBigTransactionException(msg : String ) extends TronException(msg){

  def this(){
    this("")
  }
}
