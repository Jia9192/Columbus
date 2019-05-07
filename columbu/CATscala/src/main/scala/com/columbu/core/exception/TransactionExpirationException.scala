package com.galileo.core.exception

class TransactionExpirationException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
