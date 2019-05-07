package com.galileo.p2p.message

abstract class P2pMessage extends Message{

  def this(rawData: Array[Byte]) {
    this()
    this.data = rawData
  }

  def this(`type`: Byte, rawData: Array[Byte]) {
    this()
    this.`type` = `type`
    this.data = rawData
  }

}
