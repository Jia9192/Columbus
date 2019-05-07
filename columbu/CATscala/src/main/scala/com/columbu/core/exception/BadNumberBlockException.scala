package com.galileo.core.exception

class BadNumberBlockException(msg : String ) extends TronException(msg){

  def this(){
    this("")
  }
}
