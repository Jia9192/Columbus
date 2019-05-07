package com.galileo.p2p.client

import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.Optional
import java.util.concurrent.TimeUnit
import com.galileo.protos.api.GrpcAPI.AssetIssueList
import com.galileo.protos.api.GrpcAPI.BytesMessage
import com.galileo.protos.api.GrpcAPI.EmptyMessage
import com.galileo.protos.api.GrpcAPI.NodeList
import com.galileo.protos.api.GrpcAPI.NumberMessage
import com.galileo.protos.api.WalletGrpc
import com.galileo.protos.protos.Contract
import com.galileo.protos.protos.Protocol.Account
import com.galileo.protos.protos.Protocol.Block
import com.galileo.protos.protos.Protocol.Transaction

class WalletGrpcClient {

  private var channel: ManagedChannel = null
  private var walletBlockingStub: WalletGrpc.WalletBlockingStub = null

  def this(host: String, port: Int) {
    this()
    channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
    walletBlockingStub = WalletGrpc.newBlockingStub(channel)
  }

  def this(host: String) {
    this()
    channel = ManagedChannelBuilder.forTarget(host).usePlaintext(true).build
    walletBlockingStub = WalletGrpc.newBlockingStub(channel)
  }

  @throws[InterruptedException]
  def shutdown() {
    channel.shutdown.awaitTermination(5, TimeUnit.SECONDS)
  }

  def queryAccount(address: Array[Byte]): Account = {
    val addressByteString = ByteString.copyFrom(address)
    val request = Account.newBuilder.setAddress(addressByteString).build
    walletBlockingStub.getAccount(request)
  }

  def createTransaction(contract: Contract.TransferContract): Transaction = walletBlockingStub.createTransaction(contract)

  def createTransferAssetTransaction(contract: Contract.TransferAssetContract): Transaction = walletBlockingStub.transferAsset(contract)

  def createParticipateAssetIssueTransaction(contract: Contract.ParticipateAssetIssueContract): Transaction = walletBlockingStub.participateAssetIssue(contract)

  def createAssetIssue(contract: Contract.AssetIssueContract): Transaction = walletBlockingStub.createAssetIssue(contract)

  def voteWitnessAccount(contract: Contract.VoteWitnessContract): Transaction = walletBlockingStub.voteWitnessAccount(contract)

  def createWitness(contract: Contract.WitnessCreateContract): Transaction = walletBlockingStub.createWitness(contract)

  def broadcastTransaction(signaturedTransaction: Transaction): Boolean = {
    val response = walletBlockingStub.broadcastTransaction(signaturedTransaction)
    response.getResult
  }

  def getBlock(blockNum: Long): Block = {
    if (blockNum < 0) return walletBlockingStub.getNowBlock(EmptyMessage.newBuilder.build)
    val builder = NumberMessage.newBuilder
    builder.setNum(blockNum)
    walletBlockingStub.getBlockByNum(builder.build)
  }

  def listNodes: Optional[NodeList] = {
    val nodeList = walletBlockingStub.listNodes(EmptyMessage.newBuilder.build)
    if (nodeList != null){
      return Optional.of(nodeList)
    }
    return Optional.empty()
  }

  def getAssetIssueByAccount(address: Array[Byte]): Optional[AssetIssueList] = {
    val addressByteString = ByteString.copyFrom(address)
    val request = Account.newBuilder.setAddress(addressByteString).build
    val assetIssueList = walletBlockingStub.getAssetIssueByAccount(request)
    if (assetIssueList != null){
      return Optional.of(assetIssueList)
    }
    return Optional.empty()
  }

  def getAssetIssueByName(assetName: String): Contract.AssetIssueContract = {
    val assetNameBs = ByteString.copyFrom(assetName.getBytes)
    val request = BytesMessage.newBuilder.setValue(assetNameBs).build
    walletBlockingStub.getAssetIssueByName(request)
  }

}
