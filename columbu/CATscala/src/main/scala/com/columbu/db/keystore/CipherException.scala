package com.galileo.db.keystore

class CipherException(message: String, cause: Throwable) extends Exception(message, cause){

  def this(msg : String){
    this(msg, null)
  }

  def this(cause: Throwable){
    this("", cause)
  }
}
