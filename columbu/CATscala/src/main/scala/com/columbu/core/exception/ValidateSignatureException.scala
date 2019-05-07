package com.galileo.core.exception

class ValidateSignatureException(msg : String ) extends TronException(msg){

  def this(){
    this("")
  }
}
