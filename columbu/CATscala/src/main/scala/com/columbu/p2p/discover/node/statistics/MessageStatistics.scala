package com.galileo.p2p.discover.node.statistics

import com.galileo.core.enumeration.UdpMessageTypeEnum
import com.galileo.core.enumeration.UdpMessageTypeEnum._
import com.galileo.p2p.message.Message
import com.galileo.p2p.net.message.{FetchInvDataMessage, InventoryMessage, TransactionsMessage}
import com.galileo.core.enumeration.MessageTypes
import com.galileo.core.enumeration.MessageTypes.{P2P_HELLO, P2P_PING, P2P_PONG, P2P_DISCONNECT,SYNC_BLOCK_CHAIN, BLOCK_CHAIN_INVENTORY, INVENTORY, FETCH_INV_DATA, TRXS, TRX, BLOCK}

class MessageStatistics {

  //udp discovery
  val discoverInPing = new MessageCount
  val discoverOutPing = new MessageCount
  val discoverInPong = new MessageCount
  val discoverOutPong = new MessageCount
  val discoverInFindNode = new MessageCount
  val discoverOutFindNode = new MessageCount
  val discoverInNeighbours = new MessageCount
  val discoverOutNeighbours = new MessageCount
  //tcp p2p
  val p2pInHello = new MessageCount
  val p2pOutHello = new MessageCount
  val p2pInPing = new MessageCount
  val p2pOutPing = new MessageCount
  val p2pInPong = new MessageCount
  val p2pOutPong = new MessageCount
  val p2pInDisconnect = new MessageCount
  val p2pOutDisconnect = new MessageCount
  //tcp tron
  val tronInMessage = new MessageCount
  val tronOutMessage = new MessageCount
  val tronInSyncBlockChain = new MessageCount
  val tronOutSyncBlockChain = new MessageCount
  val tronInBlockChainInventory = new MessageCount
  val tronOutBlockChainInventory = new MessageCount
  val tronInTrxInventory = new MessageCount
  val tronOutTrxInventory = new MessageCount
  val tronInTrxInventoryElement = new MessageCount
  val tronOutTrxInventoryElement = new MessageCount
  val tronInBlockInventory = new MessageCount
  val tronOutBlockInventory = new MessageCount
  val tronInBlockInventoryElement = new MessageCount
  val tronOutBlockInventoryElement = new MessageCount
  val tronInTrxFetchInvData = new MessageCount
  val tronOutTrxFetchInvData = new MessageCount
  val tronInTrxFetchInvDataElement = new MessageCount
  val tronOutTrxFetchInvDataElement = new MessageCount
  val tronInBlockFetchInvData = new MessageCount
  val tronOutBlockFetchInvData = new MessageCount
  val tronInBlockFetchInvDataElement = new MessageCount
  val tronOutBlockFetchInvDataElement = new MessageCount
  val tronInTrx = new MessageCount
  val tronOutTrx = new MessageCount
  val tronInTrxs = new MessageCount
  val tronOutTrxs = new MessageCount
  val tronInBlock = new MessageCount
  val tronOutBlock = new MessageCount
  val tronOutAdvBlock = new MessageCount

  def addUdpInMessage(`type`: UdpMessageTypeEnum) {
    addUdpMessage(`type`, true)
  }

  def addUdpOutMessage(`type`: UdpMessageTypeEnum) {
    addUdpMessage(`type`, false)
  }

  def addTcpInMessage(msg: Message) {
    addTcpMessage(msg, true)
  }

  def addTcpOutMessage(msg: Message) {
    addTcpMessage(msg, false)
  }

  private def addUdpMessage(`type`: UdpMessageTypeEnum, flag: Boolean) {
    `type` match {
      case DISCOVER_PING =>
        if (flag) discoverInPing.add()
        else discoverOutPing.add()
      case DISCOVER_PONG =>
        if (flag) discoverInPong.add()
        else discoverOutPong.add()
      case DISCOVER_FIND_NODE =>
        if (flag) discoverInFindNode.add()
        else discoverOutFindNode.add()
      case DISCOVER_NEIGHBORS =>
        if (flag) discoverInNeighbours.add()
        else discoverOutNeighbours.add()
    }
  }

  private def addTcpMessage(msg: Message, flag: Boolean) {
    if (flag) tronInMessage.add()
    else tronOutMessage.add()
    msg.getType match {
      case P2P_HELLO =>
        if (flag) p2pInHello.add()
        else p2pOutHello.add()
      case P2P_PING =>
        if (flag) p2pInPing.add()
        else p2pOutPing.add()
      case P2P_PONG =>
        if (flag) p2pInPong.add()
        else p2pOutPong.add()
      case P2P_DISCONNECT =>
        if (flag) p2pInDisconnect.add()
        else p2pOutDisconnect.add()
      case SYNC_BLOCK_CHAIN =>
        if (flag) tronInSyncBlockChain.add()
        else tronOutSyncBlockChain.add()
      case BLOCK_CHAIN_INVENTORY =>
        if (flag) tronInBlockChainInventory.add()
        else tronOutBlockChainInventory.add()
      case INVENTORY =>
        val inventoryMessage = msg.asInstanceOf[InventoryMessage]
        val inventorySize = inventoryMessage.getInventory.getIdsCount
        if (flag) if (inventoryMessage.getInvMessageType eq MessageTypes.TRX) {
          tronInTrxInventory.add()
          tronInTrxInventoryElement.add(inventorySize)
        }
        else {
          tronInBlockInventory.add()
          tronInBlockInventoryElement.add(inventorySize)
        }
        else if (inventoryMessage.getInvMessageType eq MessageTypes.TRX) {
          tronOutTrxInventory.add()
          tronOutTrxInventoryElement.add(inventorySize)
        }
        else {
          tronOutBlockInventory.add()
          tronOutBlockInventoryElement.add(inventorySize)
        }
      case FETCH_INV_DATA =>
        val fetchInvDataMessage = msg.asInstanceOf[FetchInvDataMessage]
        val fetchSize = fetchInvDataMessage.getInventory.getIdsCount
        if (flag) if (fetchInvDataMessage.getInvMessageType eq MessageTypes.TRX) {
          tronInTrxFetchInvData.add()
          tronInTrxFetchInvDataElement.add(fetchSize)
        }
        else {
          tronInBlockFetchInvData.add()
          tronInBlockFetchInvDataElement.add(fetchSize)
        }
        else if (fetchInvDataMessage.getInvMessageType eq MessageTypes.TRX) {
          tronOutTrxFetchInvData.add()
          tronOutTrxFetchInvDataElement.add(fetchSize)
        }
        else {
          tronOutBlockFetchInvData.add()
          tronOutBlockFetchInvDataElement.add(fetchSize)
        }
      case TRXS =>
        val transactionsMessage = msg.asInstanceOf[TransactionsMessage]
        if (flag) {
          tronInTrxs.add()
          tronInTrx.add(transactionsMessage.getTransactions.getTransactionsCount)
        }
        else {
          tronOutTrxs.add()
          tronOutTrx.add(transactionsMessage.getTransactions.getTransactionsCount)
        }
      case TRX =>
        if (flag) tronInMessage.add()
        else tronOutMessage.add()
      case BLOCK =>
        if (flag) tronInBlock.add()
        tronOutBlock.add()
    }
  }

}
