package com.galileo.p2p.message

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import java.util
import org.apache.commons.lang3.ArrayUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.galileo.utils.Sha256Hash
import com.galileo.core.enumeration.MessageTypes

abstract class Message {

  private val logger: Logger = LoggerFactory.getLogger(getClass())

  var data: Array[Byte] = null
  var `type`:Byte = 0

  def this(packed: Array[Byte]) {
    this()
    this.data = packed
  }

  def this(`type`: Byte, packed: Array[Byte]) {
    this()
    this.`type` = `type`
    this.data = packed
  }

  def getSendData: ByteBuf = Unpooled.wrappedBuffer(ArrayUtils.add(this.getData, 0, `type`))

  def getMessageId: Sha256Hash = Sha256Hash.of(getData)

  def getData: Array[Byte] = this.data

  def getType: MessageTypes = MessageTypes.fromByte(this.`type`)

  def getAnswerMessage: Class[_]

  override def toString: String = "type: " + getType + "\n"

  override def hashCode: Int = util.Arrays.hashCode(data)

  override def equals(o: Any): Boolean = {
    if (this == o) return true
    if (!o.isInstanceOf[Message]) return false
    val message = o.asInstanceOf[Message]
    util.Arrays.equals(data, message.data)
  }

}
