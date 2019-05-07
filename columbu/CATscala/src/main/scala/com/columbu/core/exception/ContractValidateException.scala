package com.galileo.core.exception

class ContractValidateException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
