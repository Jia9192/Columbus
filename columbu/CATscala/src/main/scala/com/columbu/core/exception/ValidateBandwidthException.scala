package com.galileo.core.exception

class ValidateBandwidthException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }

}
