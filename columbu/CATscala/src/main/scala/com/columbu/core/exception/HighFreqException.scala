package com.galileo.core.exception

class HighFreqException(msg : String) extends TronException(msg){

  def this(){
    this("")
  }
}
