package com.galileo.p2p.udp.handler

trait EventHandler {

  def channelActivated()

  def handleEvent(event: UdpEvent)

}
