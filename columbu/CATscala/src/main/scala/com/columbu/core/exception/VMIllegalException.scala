package com.galileo.core.exception

class VMIllegalException(msg : String ) extends TronException(msg){

  def this(){
    this("")
  }
}
