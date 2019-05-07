package com.galileo.p2p.net.message

import com.galileo.utils.Sha256Hash
import com.galileo.core.capsule.TransactionCapsule
import com.galileo.protos.protos.Protocol.Transaction
import com.galileo.core.enumeration.MessageTypes

class TransactionMessage extends TronMessage{
  private var transactionCapsule: TransactionCapsule = null

  def this(data: Array[Byte]) {
    this()
    this.transactionCapsule = new TransactionCapsule(data)
    this.data = data
    this.`type` = MessageTypes.TRX.asByte
  }

  def this(trx: Transaction) {
    this()
    this.transactionCapsule = new TransactionCapsule(trx)
    this.`type` = MessageTypes.TRX.asByte
    this.data = trx.toByteArray
  }

  override def toString: String = new StringBuilder().append(super.toString).append("messageId: ").append(super.getMessageId).toString

  override def getMessageId: Sha256Hash = this.transactionCapsule.getTransactionId

  def getAnswerMessage = null

  def getTransactionCapsule: TransactionCapsule = this.transactionCapsule

}
