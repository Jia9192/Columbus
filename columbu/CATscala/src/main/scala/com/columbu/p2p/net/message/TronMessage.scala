package com.galileo.p2p.net.message

import com.galileo.p2p.message.Message

abstract class TronMessage extends Message{

  def this(rawData: Array[Byte]) {
    this()
    this.data = rawData
  }

  def this(`type`: Byte, rawData: Array[Byte]) {
    this(rawData)
    this.`type` = `type`
  }

}
