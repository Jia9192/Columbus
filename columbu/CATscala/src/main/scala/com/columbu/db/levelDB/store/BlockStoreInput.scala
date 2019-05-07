package com.galileo.db.levelDB.store

import com.galileo.protos.protos.Protocol
import com.galileo.protos.protos.Protocol.Block


trait BlockStoreInput {

  def getBestBlock: Protocol.Block

  def getChainBlockByNumber(blockNumber: Long): Protocol.Block

  def isBlockExist(hash: Array[Byte]): Boolean

  def getBlockByHash(hash: Array[Byte]): Protocol.Block
}
