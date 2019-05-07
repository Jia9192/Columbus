package com.galileo.core.exception

class AccountResourceInsufficientException(msg : String) extends TronException(msg){

  def this(){
    this("Insufficient bandwidth and balance to create new account")
  }
}
