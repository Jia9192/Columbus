package com.galileo.p2p.message

abstract class MessageFactory {

  @throws[Exception]
  protected def create(data: Array[Byte]): Message

}
