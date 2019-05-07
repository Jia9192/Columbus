package com.galileo.p2p.net.node

import com.galileo.p2p.message.Message
import com.galileo.utils.Quitable
import com.galileo.utils.Sha256Hash

trait Node extends Quitable {

  def setNodeDelegate(nodeDel: NodeDelegate)

  def broadcast(msg: Message)

  def listen()

  def syncFrom(myHeadBlockHash: Sha256Hash)

  @throws[InterruptedException]
  def close()

}
