package com.galileo.p2p.udp.handler

import java.net.InetSocketAddress

import com.galileo.p2p.udp.message.Message

import scala.beans.BeanProperty

class UdpEvent {

  @BeanProperty
  var message: Message = null

  @BeanProperty
  var address: InetSocketAddress = null

  def this(message: Message, address: InetSocketAddress) {
    this()
    this.message = message
    this.address = address
  }

}
