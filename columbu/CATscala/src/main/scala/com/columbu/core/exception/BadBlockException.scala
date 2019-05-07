package com.galileo.core.exception

class BadBlockException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
