package com.galileo.core.exception

class ContractSizeNotEqualToOneException(msg : String ) extends ContractValidateException(msg){

  def this(){
    this("")
  }
}
