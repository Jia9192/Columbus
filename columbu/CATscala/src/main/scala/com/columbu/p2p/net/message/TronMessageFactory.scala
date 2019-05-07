package com.galileo.p2p.net.message

import org.apache.commons.lang3.ArrayUtils
import com.galileo.p2p.message.MessageFactory
import com.galileo.core.exception.P2pException
import com.galileo.core.enumeration.MessageTypes
import com.galileo.core.enumeration.MessageTypes._

class TronMessageFactory extends MessageFactory{

  @throws[Exception]
  def create(data: Array[Byte]): TronMessage = {
    try {
      val `type` = data(0)
      val rawData = ArrayUtils.subarray(data, 1, data.length)
      create(`type`, rawData)

    }catch {
      case e: P2pException => {
        throw e
      }
      case e: Exception => {
        throw new P2pException(P2pException.TypeEnum.PARSE_MESSAGE_FAILED, "type=" + data(0) + ", len=" + data.length)
      }
    }
  }


  @throws[Exception]
  private def create(`type`: Byte, packed: Array[Byte]) = {
    val receivedTypes = MessageTypes.fromByte(`type`)
    if (receivedTypes == null) throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, "type=" + `type` + ", len=" + packed.length)
    receivedTypes match {
      case TRX =>
        new TransactionMessage(packed)
      case BLOCK =>
        new BlockMessage(packed)
      case TRXS =>
        new TransactionsMessage(packed)
      case BLOCKS =>
        new BlocksMessage(packed)
      case INVENTORY =>
        new InventoryMessage(packed)
      case FETCH_INV_DATA =>
        new FetchInvDataMessage(packed)
      case SYNC_BLOCK_CHAIN =>
        new SyncBlockChainMessage(packed)
      case BLOCK_CHAIN_INVENTORY =>
        new ChainInventoryMessage(packed)
      case ITEM_NOT_FOUND =>
        new ItemNotFound()
      case FETCH_BLOCK_HEADERS =>
        new FetchBlockHeadersMessage(packed)
      case TRX_INVENTORY =>
        new TransactionInventoryMessage(packed)
      case _ =>
        throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, receivedTypes.toString + ", len=" + packed.length)
    }
  }

}
