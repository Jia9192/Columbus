package com.galileo.core.exception

class HeaderNotFound(msg : String ) extends StoreException(msg){

  def this(){
    this("")
  }
}
