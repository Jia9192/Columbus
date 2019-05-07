package com.galileo.core.exception

class ValidateScheduleException(msg : String ) extends TronException(msg){

  def this(){
    this("")
  }
}
