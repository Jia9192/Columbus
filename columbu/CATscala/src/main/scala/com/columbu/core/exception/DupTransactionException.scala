package com.galileo.core.exception

class DupTransactionException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
