package com.galileo.core.exception

class BalanceInsufficientException(msg : String ) extends TronException(msg){

  def this(){
    this("")
  }
}
