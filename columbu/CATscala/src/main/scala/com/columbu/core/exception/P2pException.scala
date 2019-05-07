package com.galileo.core.exception

class P2pException(typeEnum : P2pException.TypeEnum.Value ,msg : String ) extends Exception(msg){

  private var typeP : P2pException.TypeEnum.Value = typeEnum

  def this(){
    this(P2pException.TypeEnum.DEFAULT, "")
  }

  def getType() : P2pException.TypeEnum.Value = this.typeP


}
object P2pException{
  object TypeEnum extends Enumeration{
    type TypeEnum = Value
    val NO_SUCH_MESSAGE = Value(1,"No such message")
    val PARSE_MESSAGE_FAILED = Value(2, "Parse message failed")
    val MESSAGE_WITH_WRONG_LENGTH = Value(3, "Message with wrong length")
    val DEFAULT = Value(100, "default exception")
  }
}
