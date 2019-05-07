package com.galileo.p2p.net.peer

import com.galileo.p2p.message.Message
import com.galileo.utils.Sha256Hash
import com.galileo.p2p.net.message.TronMessage

abstract class PeerConnectionDelegate {
  def onMessage(peer: PeerConnection, msg: TronMessage)

  def getMessage(msgId: Sha256Hash): Message

  def onConnectPeer(peer: PeerConnection)

  def onDisconnectPeer(peer: PeerConnection)

}
