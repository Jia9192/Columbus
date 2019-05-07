package com.galileo.p2p.net.message

import java.util
import com.galileo.protos.protos.Protocol
import com.galileo.protos.protos.Protocol.Transactions
import com.galileo.protos.protos.Protocol.Transaction
import com.galileo.core.enumeration.MessageTypes
import scala.collection.JavaConverters._

class TransactionsMessage extends TronMessage{

  private var transactions: Transactions = null

  def this(trxs: util.List[Transaction]){
    this()
    val builder = Protocol.Transactions.newBuilder
    trxs.asScala.foreach(trx => builder.addTransactions(trx))
    this.transactions = builder.build
    this.`type` = MessageTypes.TRXS.asByte
    this.data = this.transactions.toByteArray
  }

  def this(data: Array[Byte]) {
    this()
    this.`type` = MessageTypes.TRXS.asByte
    this.data = data
    this.transactions = Protocol.Transactions.parseFrom(data)
  }

  def getTransactions: Protocol.Transactions = transactions

  override def toString: String = new StringBuilder().append(super.toString).append("trx size: ").append(this.transactions.getTransactionsList.size).toString

  def getAnswerMessage = null

}
