package com.galileo.p2p.client

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import com.galileo.protos.api.DatabaseGrpc
import com.galileo.protos.api.GrpcAPI.EmptyMessage
import com.galileo.protos.api.GrpcAPI.NumberMessage
import com.galileo.protos.protos.Protocol.Block
import com.galileo.protos.protos.Protocol.DynamicProperties

class DatabaseGrpcClient {

  private var channel: ManagedChannel = null
  private var databaseBlockingStub: DatabaseGrpc.DatabaseBlockingStub = null

  def this(host: String, port: Int) {
    this()
    channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
    databaseBlockingStub = DatabaseGrpc.newBlockingStub(channel)
  }

  def this(host: String) {
    this()
    channel = ManagedChannelBuilder.forTarget(host).usePlaintext(true).build
    databaseBlockingStub = DatabaseGrpc.newBlockingStub(channel)
  }

  def getBlock(blockNum: Long): Block = {
    if (blockNum < 0) return databaseBlockingStub.getNowBlock(EmptyMessage.newBuilder.build)
    val builder = NumberMessage.newBuilder
    builder.setNum(blockNum)
    databaseBlockingStub.getBlockByNum(builder.build)
  }

  def shutdown() {
    channel.shutdown
  }

  def getDynamicProperties: DynamicProperties = databaseBlockingStub.getDynamicProperties(EmptyMessage.newBuilder.build)

}
