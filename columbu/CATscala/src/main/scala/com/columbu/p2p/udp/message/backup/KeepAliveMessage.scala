package com.galileo.p2p.udp.message.backup

import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.galileo.p2p.udp.message.Message
import com.galileo.p2p.discover.node.Node
import com.galileo.protos.protos.Discover.BackupMessage

class KeepAliveMessage extends Message{

  private var backupMessage:BackupMessage = null

  def this(data: Array[Byte]) {
    this()
    this.`type` = UdpMessageTypeEnum.BACKUP_KEEP_ALIVE
    this.data = data
    this.backupMessage = BackupMessage.parseFrom(data)
  }

  def this(flag: Boolean, priority: Int) {
    this()
    this.`type` = UdpMessageTypeEnum.BACKUP_KEEP_ALIVE
    this.backupMessage = BackupMessage.newBuilder.setFlag(flag).setPriority(priority).build
    this.data = backupMessage.toByteArray
  }

  def getFlag: Boolean = backupMessage.getFlag

  def getPriority: Int = backupMessage.getPriority

  override def getFrom: Node = {
    return null
  }

}
