package com.galileo.core.exception

class CancelException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
