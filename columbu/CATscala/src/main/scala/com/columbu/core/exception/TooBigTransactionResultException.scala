package com.galileo.core.exception

class TooBigTransactionResultException(msg : String ) extends TronException(msg){

  def this(){
    this("too big transaction result")
  }
}
