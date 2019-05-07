package com.galileo.core.exception

class ContractExeException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
