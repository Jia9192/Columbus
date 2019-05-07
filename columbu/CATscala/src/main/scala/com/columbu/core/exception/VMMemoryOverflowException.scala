package com.galileo.core.exception

class VMMemoryOverflowException(msg : String) extends TronException(msg){

  def this(){
    this("VM memory overflow")
  }
}
