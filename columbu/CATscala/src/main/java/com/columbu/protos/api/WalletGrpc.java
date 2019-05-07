package com.galileo.protos.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: api/api.proto")
public final class WalletGrpc {

  private WalletGrpc() {}

  public static final String SERVICE_NAME = "protocol.Wallet";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.protos.Protocol.Account> getGetAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccount",
      requestType = com.galileo.protos.protos.Protocol.Account.class,
      responseType = com.galileo.protos.protos.Protocol.Account.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.protos.Protocol.Account> getGetAccountMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.protos.Protocol.Account> getGetAccountMethod;
    if ((getGetAccountMethod = WalletGrpc.getGetAccountMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAccountMethod = WalletGrpc.getGetAccountMethod) == null) {
          WalletGrpc.getGetAccountMethod = getGetAccountMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.protos.Protocol.Account>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccount"))
                  .build();
          }
        }
     }
     return getGetAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.protos.Protocol.Account> getGetAccountByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountById",
      requestType = com.galileo.protos.protos.Protocol.Account.class,
      responseType = com.galileo.protos.protos.Protocol.Account.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.protos.Protocol.Account> getGetAccountByIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.protos.Protocol.Account> getGetAccountByIdMethod;
    if ((getGetAccountByIdMethod = WalletGrpc.getGetAccountByIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAccountByIdMethod = WalletGrpc.getGetAccountByIdMethod) == null) {
          WalletGrpc.getGetAccountByIdMethod = getGetAccountByIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.protos.Protocol.Account>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAccountById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountById"))
                  .build();
          }
        }
     }
     return getGetAccountByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTransaction",
      requestType = com.galileo.protos.protos.Contract.TransferContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateTransactionMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferContract, com.galileo.protos.protos.Protocol.Transaction> getCreateTransactionMethod;
    if ((getCreateTransactionMethod = WalletGrpc.getCreateTransactionMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateTransactionMethod = WalletGrpc.getCreateTransactionMethod) == null) {
          WalletGrpc.getCreateTransactionMethod = getCreateTransactionMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.TransferContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.TransferContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateTransaction"))
                  .build();
          }
        }
     }
     return getCreateTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateTransaction2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTransaction2",
      requestType = com.galileo.protos.protos.Contract.TransferContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateTransaction2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateTransaction2Method;
    if ((getCreateTransaction2Method = WalletGrpc.getCreateTransaction2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateTransaction2Method = WalletGrpc.getCreateTransaction2Method) == null) {
          WalletGrpc.getCreateTransaction2Method = getCreateTransaction2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.TransferContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateTransaction2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.TransferContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateTransaction2"))
                  .build();
          }
        }
     }
     return getCreateTransaction2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Transaction,
      com.galileo.protos.api.GrpcAPI.Return> getBroadcastTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BroadcastTransaction",
      requestType = com.galileo.protos.protos.Protocol.Transaction.class,
      responseType = com.galileo.protos.api.GrpcAPI.Return.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Transaction,
      com.galileo.protos.api.GrpcAPI.Return> getBroadcastTransactionMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Transaction, com.galileo.protos.api.GrpcAPI.Return> getBroadcastTransactionMethod;
    if ((getBroadcastTransactionMethod = WalletGrpc.getBroadcastTransactionMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getBroadcastTransactionMethod = WalletGrpc.getBroadcastTransactionMethod) == null) {
          WalletGrpc.getBroadcastTransactionMethod = getBroadcastTransactionMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.Transaction, com.galileo.protos.api.GrpcAPI.Return>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "BroadcastTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.Return.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("BroadcastTransaction"))
                  .build();
          }
        }
     }
     return getBroadcastTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountUpdateContract,
      com.galileo.protos.protos.Protocol.Transaction> getUpdateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAccount",
      requestType = com.galileo.protos.protos.Contract.AccountUpdateContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountUpdateContract,
      com.galileo.protos.protos.Protocol.Transaction> getUpdateAccountMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountUpdateContract, com.galileo.protos.protos.Protocol.Transaction> getUpdateAccountMethod;
    if ((getUpdateAccountMethod = WalletGrpc.getUpdateAccountMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateAccountMethod = WalletGrpc.getUpdateAccountMethod) == null) {
          WalletGrpc.getUpdateAccountMethod = getUpdateAccountMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.AccountUpdateContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AccountUpdateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAccount"))
                  .build();
          }
        }
     }
     return getUpdateAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.SetAccountIdContract,
      com.galileo.protos.protos.Protocol.Transaction> getSetAccountIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetAccountId",
      requestType = com.galileo.protos.protos.Contract.SetAccountIdContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.SetAccountIdContract,
      com.galileo.protos.protos.Protocol.Transaction> getSetAccountIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.SetAccountIdContract, com.galileo.protos.protos.Protocol.Transaction> getSetAccountIdMethod;
    if ((getSetAccountIdMethod = WalletGrpc.getSetAccountIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getSetAccountIdMethod = WalletGrpc.getSetAccountIdMethod) == null) {
          WalletGrpc.getSetAccountIdMethod = getSetAccountIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.SetAccountIdContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "SetAccountId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.SetAccountIdContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("SetAccountId"))
                  .build();
          }
        }
     }
     return getSetAccountIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountUpdateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateAccount2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAccount2",
      requestType = com.galileo.protos.protos.Contract.AccountUpdateContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountUpdateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateAccount2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountUpdateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateAccount2Method;
    if ((getUpdateAccount2Method = WalletGrpc.getUpdateAccount2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateAccount2Method = WalletGrpc.getUpdateAccount2Method) == null) {
          WalletGrpc.getUpdateAccount2Method = getUpdateAccount2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.AccountUpdateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateAccount2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AccountUpdateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAccount2"))
                  .build();
          }
        }
     }
     return getUpdateAccount2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.VoteWitnessContract,
      com.galileo.protos.protos.Protocol.Transaction> getVoteWitnessAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteWitnessAccount",
      requestType = com.galileo.protos.protos.Contract.VoteWitnessContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.VoteWitnessContract,
      com.galileo.protos.protos.Protocol.Transaction> getVoteWitnessAccountMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.VoteWitnessContract, com.galileo.protos.protos.Protocol.Transaction> getVoteWitnessAccountMethod;
    if ((getVoteWitnessAccountMethod = WalletGrpc.getVoteWitnessAccountMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getVoteWitnessAccountMethod = WalletGrpc.getVoteWitnessAccountMethod) == null) {
          WalletGrpc.getVoteWitnessAccountMethod = getVoteWitnessAccountMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.VoteWitnessContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "VoteWitnessAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.VoteWitnessContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("VoteWitnessAccount"))
                  .build();
          }
        }
     }
     return getVoteWitnessAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateSettingContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateSettingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateSetting",
      requestType = com.galileo.protos.protos.Contract.UpdateSettingContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateSettingContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateSettingMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateSettingContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateSettingMethod;
    if ((getUpdateSettingMethod = WalletGrpc.getUpdateSettingMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateSettingMethod = WalletGrpc.getUpdateSettingMethod) == null) {
          WalletGrpc.getUpdateSettingMethod = getUpdateSettingMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UpdateSettingContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateSetting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UpdateSettingContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateSetting"))
                  .build();
          }
        }
     }
     return getUpdateSettingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.VoteWitnessContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getVoteWitnessAccount2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteWitnessAccount2",
      requestType = com.galileo.protos.protos.Contract.VoteWitnessContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.VoteWitnessContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getVoteWitnessAccount2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.VoteWitnessContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getVoteWitnessAccount2Method;
    if ((getVoteWitnessAccount2Method = WalletGrpc.getVoteWitnessAccount2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getVoteWitnessAccount2Method = WalletGrpc.getVoteWitnessAccount2Method) == null) {
          WalletGrpc.getVoteWitnessAccount2Method = getVoteWitnessAccount2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.VoteWitnessContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "VoteWitnessAccount2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.VoteWitnessContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("VoteWitnessAccount2"))
                  .build();
          }
        }
     }
     return getVoteWitnessAccount2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AssetIssueContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateAssetIssueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAssetIssue",
      requestType = com.galileo.protos.protos.Contract.AssetIssueContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AssetIssueContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateAssetIssueMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AssetIssueContract, com.galileo.protos.protos.Protocol.Transaction> getCreateAssetIssueMethod;
    if ((getCreateAssetIssueMethod = WalletGrpc.getCreateAssetIssueMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateAssetIssueMethod = WalletGrpc.getCreateAssetIssueMethod) == null) {
          WalletGrpc.getCreateAssetIssueMethod = getCreateAssetIssueMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.AssetIssueContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateAssetIssue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AssetIssueContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAssetIssue"))
                  .build();
          }
        }
     }
     return getCreateAssetIssueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AssetIssueContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateAssetIssue2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAssetIssue2",
      requestType = com.galileo.protos.protos.Contract.AssetIssueContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AssetIssueContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateAssetIssue2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AssetIssueContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateAssetIssue2Method;
    if ((getCreateAssetIssue2Method = WalletGrpc.getCreateAssetIssue2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateAssetIssue2Method = WalletGrpc.getCreateAssetIssue2Method) == null) {
          WalletGrpc.getCreateAssetIssue2Method = getCreateAssetIssue2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.AssetIssueContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateAssetIssue2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AssetIssueContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAssetIssue2"))
                  .build();
          }
        }
     }
     return getCreateAssetIssue2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessUpdateContract,
      com.galileo.protos.protos.Protocol.Transaction> getUpdateWitnessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateWitness",
      requestType = com.galileo.protos.protos.Contract.WitnessUpdateContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessUpdateContract,
      com.galileo.protos.protos.Protocol.Transaction> getUpdateWitnessMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessUpdateContract, com.galileo.protos.protos.Protocol.Transaction> getUpdateWitnessMethod;
    if ((getUpdateWitnessMethod = WalletGrpc.getUpdateWitnessMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateWitnessMethod = WalletGrpc.getUpdateWitnessMethod) == null) {
          WalletGrpc.getUpdateWitnessMethod = getUpdateWitnessMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.WitnessUpdateContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateWitness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.WitnessUpdateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateWitness"))
                  .build();
          }
        }
     }
     return getUpdateWitnessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessUpdateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateWitness2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateWitness2",
      requestType = com.galileo.protos.protos.Contract.WitnessUpdateContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessUpdateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateWitness2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessUpdateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateWitness2Method;
    if ((getUpdateWitness2Method = WalletGrpc.getUpdateWitness2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateWitness2Method = WalletGrpc.getUpdateWitness2Method) == null) {
          WalletGrpc.getUpdateWitness2Method = getUpdateWitness2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.WitnessUpdateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateWitness2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.WitnessUpdateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateWitness2"))
                  .build();
          }
        }
     }
     return getUpdateWitness2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountCreateContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAccount",
      requestType = com.galileo.protos.protos.Contract.AccountCreateContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountCreateContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateAccountMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountCreateContract, com.galileo.protos.protos.Protocol.Transaction> getCreateAccountMethod;
    if ((getCreateAccountMethod = WalletGrpc.getCreateAccountMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateAccountMethod = WalletGrpc.getCreateAccountMethod) == null) {
          WalletGrpc.getCreateAccountMethod = getCreateAccountMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.AccountCreateContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AccountCreateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAccount"))
                  .build();
          }
        }
     }
     return getCreateAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateAccount2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAccount2",
      requestType = com.galileo.protos.protos.Contract.AccountCreateContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateAccount2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.AccountCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateAccount2Method;
    if ((getCreateAccount2Method = WalletGrpc.getCreateAccount2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateAccount2Method = WalletGrpc.getCreateAccount2Method) == null) {
          WalletGrpc.getCreateAccount2Method = getCreateAccount2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.AccountCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateAccount2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AccountCreateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAccount2"))
                  .build();
          }
        }
     }
     return getCreateAccount2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessCreateContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateWitnessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateWitness",
      requestType = com.galileo.protos.protos.Contract.WitnessCreateContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessCreateContract,
      com.galileo.protos.protos.Protocol.Transaction> getCreateWitnessMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessCreateContract, com.galileo.protos.protos.Protocol.Transaction> getCreateWitnessMethod;
    if ((getCreateWitnessMethod = WalletGrpc.getCreateWitnessMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateWitnessMethod = WalletGrpc.getCreateWitnessMethod) == null) {
          WalletGrpc.getCreateWitnessMethod = getCreateWitnessMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.WitnessCreateContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateWitness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.WitnessCreateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateWitness"))
                  .build();
          }
        }
     }
     return getCreateWitnessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateWitness2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateWitness2",
      requestType = com.galileo.protos.protos.Contract.WitnessCreateContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateWitness2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WitnessCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getCreateWitness2Method;
    if ((getCreateWitness2Method = WalletGrpc.getCreateWitness2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateWitness2Method = WalletGrpc.getCreateWitness2Method) == null) {
          WalletGrpc.getCreateWitness2Method = getCreateWitness2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.WitnessCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateWitness2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.WitnessCreateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateWitness2"))
                  .build();
          }
        }
     }
     return getCreateWitness2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferAssetContract,
      com.galileo.protos.protos.Protocol.Transaction> getTransferAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferAsset",
      requestType = com.galileo.protos.protos.Contract.TransferAssetContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferAssetContract,
      com.galileo.protos.protos.Protocol.Transaction> getTransferAssetMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferAssetContract, com.galileo.protos.protos.Protocol.Transaction> getTransferAssetMethod;
    if ((getTransferAssetMethod = WalletGrpc.getTransferAssetMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getTransferAssetMethod = WalletGrpc.getTransferAssetMethod) == null) {
          WalletGrpc.getTransferAssetMethod = getTransferAssetMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.TransferAssetContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "TransferAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.TransferAssetContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("TransferAsset"))
                  .build();
          }
        }
     }
     return getTransferAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferAssetContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getTransferAsset2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TransferAsset2",
      requestType = com.galileo.protos.protos.Contract.TransferAssetContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferAssetContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getTransferAsset2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TransferAssetContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getTransferAsset2Method;
    if ((getTransferAsset2Method = WalletGrpc.getTransferAsset2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getTransferAsset2Method = WalletGrpc.getTransferAsset2Method) == null) {
          WalletGrpc.getTransferAsset2Method = getTransferAsset2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.TransferAssetContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "TransferAsset2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.TransferAssetContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("TransferAsset2"))
                  .build();
          }
        }
     }
     return getTransferAsset2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract,
      com.galileo.protos.protos.Protocol.Transaction> getParticipateAssetIssueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParticipateAssetIssue",
      requestType = com.galileo.protos.protos.Contract.ParticipateAssetIssueContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract,
      com.galileo.protos.protos.Protocol.Transaction> getParticipateAssetIssueMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract, com.galileo.protos.protos.Protocol.Transaction> getParticipateAssetIssueMethod;
    if ((getParticipateAssetIssueMethod = WalletGrpc.getParticipateAssetIssueMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getParticipateAssetIssueMethod = WalletGrpc.getParticipateAssetIssueMethod) == null) {
          WalletGrpc.getParticipateAssetIssueMethod = getParticipateAssetIssueMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ParticipateAssetIssue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ParticipateAssetIssueContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ParticipateAssetIssue"))
                  .build();
          }
        }
     }
     return getParticipateAssetIssueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getParticipateAssetIssue2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParticipateAssetIssue2",
      requestType = com.galileo.protos.protos.Contract.ParticipateAssetIssueContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getParticipateAssetIssue2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getParticipateAssetIssue2Method;
    if ((getParticipateAssetIssue2Method = WalletGrpc.getParticipateAssetIssue2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getParticipateAssetIssue2Method = WalletGrpc.getParticipateAssetIssue2Method) == null) {
          WalletGrpc.getParticipateAssetIssue2Method = getParticipateAssetIssue2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ParticipateAssetIssueContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ParticipateAssetIssue2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ParticipateAssetIssueContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ParticipateAssetIssue2"))
                  .build();
          }
        }
     }
     return getParticipateAssetIssue2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.FreezeBalanceContract,
      com.galileo.protos.protos.Protocol.Transaction> getFreezeBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FreezeBalance",
      requestType = com.galileo.protos.protos.Contract.FreezeBalanceContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.FreezeBalanceContract,
      com.galileo.protos.protos.Protocol.Transaction> getFreezeBalanceMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.FreezeBalanceContract, com.galileo.protos.protos.Protocol.Transaction> getFreezeBalanceMethod;
    if ((getFreezeBalanceMethod = WalletGrpc.getFreezeBalanceMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getFreezeBalanceMethod = WalletGrpc.getFreezeBalanceMethod) == null) {
          WalletGrpc.getFreezeBalanceMethod = getFreezeBalanceMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.FreezeBalanceContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "FreezeBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.FreezeBalanceContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("FreezeBalance"))
                  .build();
          }
        }
     }
     return getFreezeBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.FreezeBalanceContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getFreezeBalance2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FreezeBalance2",
      requestType = com.galileo.protos.protos.Contract.FreezeBalanceContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.FreezeBalanceContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getFreezeBalance2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.FreezeBalanceContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getFreezeBalance2Method;
    if ((getFreezeBalance2Method = WalletGrpc.getFreezeBalance2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getFreezeBalance2Method = WalletGrpc.getFreezeBalance2Method) == null) {
          WalletGrpc.getFreezeBalance2Method = getFreezeBalance2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.FreezeBalanceContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "FreezeBalance2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.FreezeBalanceContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("FreezeBalance2"))
                  .build();
          }
        }
     }
     return getFreezeBalance2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeBalanceContract,
      com.galileo.protos.protos.Protocol.Transaction> getUnfreezeBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnfreezeBalance",
      requestType = com.galileo.protos.protos.Contract.UnfreezeBalanceContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeBalanceContract,
      com.galileo.protos.protos.Protocol.Transaction> getUnfreezeBalanceMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeBalanceContract, com.galileo.protos.protos.Protocol.Transaction> getUnfreezeBalanceMethod;
    if ((getUnfreezeBalanceMethod = WalletGrpc.getUnfreezeBalanceMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUnfreezeBalanceMethod = WalletGrpc.getUnfreezeBalanceMethod) == null) {
          WalletGrpc.getUnfreezeBalanceMethod = getUnfreezeBalanceMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UnfreezeBalanceContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UnfreezeBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UnfreezeBalanceContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeBalance"))
                  .build();
          }
        }
     }
     return getUnfreezeBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeBalanceContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUnfreezeBalance2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnfreezeBalance2",
      requestType = com.galileo.protos.protos.Contract.UnfreezeBalanceContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeBalanceContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUnfreezeBalance2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeBalanceContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getUnfreezeBalance2Method;
    if ((getUnfreezeBalance2Method = WalletGrpc.getUnfreezeBalance2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUnfreezeBalance2Method = WalletGrpc.getUnfreezeBalance2Method) == null) {
          WalletGrpc.getUnfreezeBalance2Method = getUnfreezeBalance2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UnfreezeBalanceContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UnfreezeBalance2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UnfreezeBalanceContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeBalance2"))
                  .build();
          }
        }
     }
     return getUnfreezeBalance2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeAssetContract,
      com.galileo.protos.protos.Protocol.Transaction> getUnfreezeAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnfreezeAsset",
      requestType = com.galileo.protos.protos.Contract.UnfreezeAssetContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeAssetContract,
      com.galileo.protos.protos.Protocol.Transaction> getUnfreezeAssetMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeAssetContract, com.galileo.protos.protos.Protocol.Transaction> getUnfreezeAssetMethod;
    if ((getUnfreezeAssetMethod = WalletGrpc.getUnfreezeAssetMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUnfreezeAssetMethod = WalletGrpc.getUnfreezeAssetMethod) == null) {
          WalletGrpc.getUnfreezeAssetMethod = getUnfreezeAssetMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UnfreezeAssetContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UnfreezeAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UnfreezeAssetContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeAsset"))
                  .build();
          }
        }
     }
     return getUnfreezeAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeAssetContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUnfreezeAsset2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnfreezeAsset2",
      requestType = com.galileo.protos.protos.Contract.UnfreezeAssetContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeAssetContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUnfreezeAsset2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UnfreezeAssetContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getUnfreezeAsset2Method;
    if ((getUnfreezeAsset2Method = WalletGrpc.getUnfreezeAsset2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUnfreezeAsset2Method = WalletGrpc.getUnfreezeAsset2Method) == null) {
          WalletGrpc.getUnfreezeAsset2Method = getUnfreezeAsset2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UnfreezeAssetContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UnfreezeAsset2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UnfreezeAssetContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeAsset2"))
                  .build();
          }
        }
     }
     return getUnfreezeAsset2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WithdrawBalanceContract,
      com.galileo.protos.protos.Protocol.Transaction> getWithdrawBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawBalance",
      requestType = com.galileo.protos.protos.Contract.WithdrawBalanceContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WithdrawBalanceContract,
      com.galileo.protos.protos.Protocol.Transaction> getWithdrawBalanceMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WithdrawBalanceContract, com.galileo.protos.protos.Protocol.Transaction> getWithdrawBalanceMethod;
    if ((getWithdrawBalanceMethod = WalletGrpc.getWithdrawBalanceMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getWithdrawBalanceMethod = WalletGrpc.getWithdrawBalanceMethod) == null) {
          WalletGrpc.getWithdrawBalanceMethod = getWithdrawBalanceMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.WithdrawBalanceContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "WithdrawBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.WithdrawBalanceContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("WithdrawBalance"))
                  .build();
          }
        }
     }
     return getWithdrawBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WithdrawBalanceContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getWithdrawBalance2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawBalance2",
      requestType = com.galileo.protos.protos.Contract.WithdrawBalanceContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WithdrawBalanceContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getWithdrawBalance2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.WithdrawBalanceContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getWithdrawBalance2Method;
    if ((getWithdrawBalance2Method = WalletGrpc.getWithdrawBalance2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getWithdrawBalance2Method = WalletGrpc.getWithdrawBalance2Method) == null) {
          WalletGrpc.getWithdrawBalance2Method = getWithdrawBalance2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.WithdrawBalanceContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "WithdrawBalance2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.WithdrawBalanceContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("WithdrawBalance2"))
                  .build();
          }
        }
     }
     return getWithdrawBalance2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateAssetContract,
      com.galileo.protos.protos.Protocol.Transaction> getUpdateAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAsset",
      requestType = com.galileo.protos.protos.Contract.UpdateAssetContract.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateAssetContract,
      com.galileo.protos.protos.Protocol.Transaction> getUpdateAssetMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateAssetContract, com.galileo.protos.protos.Protocol.Transaction> getUpdateAssetMethod;
    if ((getUpdateAssetMethod = WalletGrpc.getUpdateAssetMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateAssetMethod = WalletGrpc.getUpdateAssetMethod) == null) {
          WalletGrpc.getUpdateAssetMethod = getUpdateAssetMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UpdateAssetContract, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UpdateAssetContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAsset"))
                  .build();
          }
        }
     }
     return getUpdateAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateAssetContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateAsset2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAsset2",
      requestType = com.galileo.protos.protos.Contract.UpdateAssetContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateAssetContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateAsset2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.UpdateAssetContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getUpdateAsset2Method;
    if ((getUpdateAsset2Method = WalletGrpc.getUpdateAsset2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getUpdateAsset2Method = WalletGrpc.getUpdateAsset2Method) == null) {
          WalletGrpc.getUpdateAsset2Method = getUpdateAsset2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.UpdateAssetContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "UpdateAsset2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.UpdateAssetContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAsset2"))
                  .build();
          }
        }
     }
     return getUpdateAsset2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProposalCreate",
      requestType = com.galileo.protos.protos.Contract.ProposalCreateContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalCreateMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalCreateMethod;
    if ((getProposalCreateMethod = WalletGrpc.getProposalCreateMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getProposalCreateMethod = WalletGrpc.getProposalCreateMethod) == null) {
          WalletGrpc.getProposalCreateMethod = getProposalCreateMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ProposalCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ProposalCreate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ProposalCreateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ProposalCreate"))
                  .build();
          }
        }
     }
     return getProposalCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalApproveContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalApproveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProposalApprove",
      requestType = com.galileo.protos.protos.Contract.ProposalApproveContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalApproveContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalApproveMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalApproveContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalApproveMethod;
    if ((getProposalApproveMethod = WalletGrpc.getProposalApproveMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getProposalApproveMethod = WalletGrpc.getProposalApproveMethod) == null) {
          WalletGrpc.getProposalApproveMethod = getProposalApproveMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ProposalApproveContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ProposalApprove"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ProposalApproveContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ProposalApprove"))
                  .build();
          }
        }
     }
     return getProposalApproveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalDeleteContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProposalDelete",
      requestType = com.galileo.protos.protos.Contract.ProposalDeleteContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalDeleteContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalDeleteMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ProposalDeleteContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getProposalDeleteMethod;
    if ((getProposalDeleteMethod = WalletGrpc.getProposalDeleteMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getProposalDeleteMethod = WalletGrpc.getProposalDeleteMethod) == null) {
          WalletGrpc.getProposalDeleteMethod = getProposalDeleteMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ProposalDeleteContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ProposalDelete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ProposalDeleteContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ProposalDelete"))
                  .build();
          }
        }
     }
     return getProposalDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.BuyStorageContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getBuyStorageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuyStorage",
      requestType = com.galileo.protos.protos.Contract.BuyStorageContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.BuyStorageContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getBuyStorageMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.BuyStorageContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getBuyStorageMethod;
    if ((getBuyStorageMethod = WalletGrpc.getBuyStorageMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getBuyStorageMethod = WalletGrpc.getBuyStorageMethod) == null) {
          WalletGrpc.getBuyStorageMethod = getBuyStorageMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.BuyStorageContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "BuyStorage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.BuyStorageContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("BuyStorage"))
                  .build();
          }
        }
     }
     return getBuyStorageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.BuyStorageBytesContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getBuyStorageBytesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuyStorageBytes",
      requestType = com.galileo.protos.protos.Contract.BuyStorageBytesContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.BuyStorageBytesContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getBuyStorageBytesMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.BuyStorageBytesContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getBuyStorageBytesMethod;
    if ((getBuyStorageBytesMethod = WalletGrpc.getBuyStorageBytesMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getBuyStorageBytesMethod = WalletGrpc.getBuyStorageBytesMethod) == null) {
          WalletGrpc.getBuyStorageBytesMethod = getBuyStorageBytesMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.BuyStorageBytesContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "BuyStorageBytes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.BuyStorageBytesContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("BuyStorageBytes"))
                  .build();
          }
        }
     }
     return getBuyStorageBytesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.SellStorageContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getSellStorageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SellStorage",
      requestType = com.galileo.protos.protos.Contract.SellStorageContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.SellStorageContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getSellStorageMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.SellStorageContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getSellStorageMethod;
    if ((getSellStorageMethod = WalletGrpc.getSellStorageMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getSellStorageMethod = WalletGrpc.getSellStorageMethod) == null) {
          WalletGrpc.getSellStorageMethod = getSellStorageMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.SellStorageContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "SellStorage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.SellStorageContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("SellStorage"))
                  .build();
          }
        }
     }
     return getSellStorageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeCreate",
      requestType = com.galileo.protos.protos.Contract.ExchangeCreateContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeCreateContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeCreateMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeCreateMethod;
    if ((getExchangeCreateMethod = WalletGrpc.getExchangeCreateMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getExchangeCreateMethod = WalletGrpc.getExchangeCreateMethod) == null) {
          WalletGrpc.getExchangeCreateMethod = getExchangeCreateMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ExchangeCreateContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ExchangeCreate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ExchangeCreateContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeCreate"))
                  .build();
          }
        }
     }
     return getExchangeCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeInjectContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeInjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeInject",
      requestType = com.galileo.protos.protos.Contract.ExchangeInjectContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeInjectContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeInjectMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeInjectContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeInjectMethod;
    if ((getExchangeInjectMethod = WalletGrpc.getExchangeInjectMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getExchangeInjectMethod = WalletGrpc.getExchangeInjectMethod) == null) {
          WalletGrpc.getExchangeInjectMethod = getExchangeInjectMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ExchangeInjectContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ExchangeInject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ExchangeInjectContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeInject"))
                  .build();
          }
        }
     }
     return getExchangeInjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeWithdrawContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeWithdraw",
      requestType = com.galileo.protos.protos.Contract.ExchangeWithdrawContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeWithdrawContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeWithdrawMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeWithdrawContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeWithdrawMethod;
    if ((getExchangeWithdrawMethod = WalletGrpc.getExchangeWithdrawMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getExchangeWithdrawMethod = WalletGrpc.getExchangeWithdrawMethod) == null) {
          WalletGrpc.getExchangeWithdrawMethod = getExchangeWithdrawMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ExchangeWithdrawContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ExchangeWithdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ExchangeWithdrawContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeWithdraw"))
                  .build();
          }
        }
     }
     return getExchangeWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeTransactionContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeTransaction",
      requestType = com.galileo.protos.protos.Contract.ExchangeTransactionContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeTransactionContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeTransactionMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.ExchangeTransactionContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getExchangeTransactionMethod;
    if ((getExchangeTransactionMethod = WalletGrpc.getExchangeTransactionMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getExchangeTransactionMethod = WalletGrpc.getExchangeTransactionMethod) == null) {
          WalletGrpc.getExchangeTransactionMethod = getExchangeTransactionMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.ExchangeTransactionContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ExchangeTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.ExchangeTransactionContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeTransaction"))
                  .build();
          }
        }
     }
     return getExchangeTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.NodeList> getListNodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListNodes",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.NodeList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.NodeList> getListNodesMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.NodeList> getListNodesMethod;
    if ((getListNodesMethod = WalletGrpc.getListNodesMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getListNodesMethod = WalletGrpc.getListNodesMethod) == null) {
          WalletGrpc.getListNodesMethod = getListNodesMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.NodeList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ListNodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NodeList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListNodes"))
                  .build();
          }
        }
     }
     return getListNodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetAssetIssueByAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetIssueByAccount",
      requestType = com.galileo.protos.protos.Protocol.Account.class,
      responseType = com.galileo.protos.api.GrpcAPI.AssetIssueList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetAssetIssueByAccountMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetAssetIssueByAccountMethod;
    if ((getGetAssetIssueByAccountMethod = WalletGrpc.getGetAssetIssueByAccountMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAssetIssueByAccountMethod = WalletGrpc.getGetAssetIssueByAccountMethod) == null) {
          WalletGrpc.getGetAssetIssueByAccountMethod = getGetAssetIssueByAccountMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.api.GrpcAPI.AssetIssueList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAssetIssueByAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.AssetIssueList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueByAccount"))
                  .build();
          }
        }
     }
     return getGetAssetIssueByAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.api.GrpcAPI.AccountNetMessage> getGetAccountNetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountNet",
      requestType = com.galileo.protos.protos.Protocol.Account.class,
      responseType = com.galileo.protos.api.GrpcAPI.AccountNetMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.api.GrpcAPI.AccountNetMessage> getGetAccountNetMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.api.GrpcAPI.AccountNetMessage> getGetAccountNetMethod;
    if ((getGetAccountNetMethod = WalletGrpc.getGetAccountNetMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAccountNetMethod = WalletGrpc.getGetAccountNetMethod) == null) {
          WalletGrpc.getGetAccountNetMethod = getGetAccountNetMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.api.GrpcAPI.AccountNetMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAccountNet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.AccountNetMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountNet"))
                  .build();
          }
        }
     }
     return getGetAccountNetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.api.GrpcAPI.AccountResourceMessage> getGetAccountResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountResource",
      requestType = com.galileo.protos.protos.Protocol.Account.class,
      responseType = com.galileo.protos.api.GrpcAPI.AccountResourceMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account,
      com.galileo.protos.api.GrpcAPI.AccountResourceMessage> getGetAccountResourceMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.api.GrpcAPI.AccountResourceMessage> getGetAccountResourceMethod;
    if ((getGetAccountResourceMethod = WalletGrpc.getGetAccountResourceMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAccountResourceMethod = WalletGrpc.getGetAccountResourceMethod) == null) {
          WalletGrpc.getGetAccountResourceMethod = getGetAccountResourceMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.Account, com.galileo.protos.api.GrpcAPI.AccountResourceMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAccountResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Account.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.AccountResourceMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountResource"))
                  .build();
          }
        }
     }
     return getGetAccountResourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Contract.AssetIssueContract> getGetAssetIssueByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetIssueByName",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Contract.AssetIssueContract.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Contract.AssetIssueContract> getGetAssetIssueByNameMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Contract.AssetIssueContract> getGetAssetIssueByNameMethod;
    if ((getGetAssetIssueByNameMethod = WalletGrpc.getGetAssetIssueByNameMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAssetIssueByNameMethod = WalletGrpc.getGetAssetIssueByNameMethod) == null) {
          WalletGrpc.getGetAssetIssueByNameMethod = getGetAssetIssueByNameMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Contract.AssetIssueContract>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAssetIssueByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.AssetIssueContract.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueByName"))
                  .build();
          }
        }
     }
     return getGetAssetIssueByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.protos.Protocol.Block> getGetNowBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNowBlock",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.protos.Protocol.Block.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.protos.Protocol.Block> getGetNowBlockMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.Block> getGetNowBlockMethod;
    if ((getGetNowBlockMethod = WalletGrpc.getGetNowBlockMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetNowBlockMethod = WalletGrpc.getGetNowBlockMethod) == null) {
          WalletGrpc.getGetNowBlockMethod = getGetNowBlockMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.Block>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetNowBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Block.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNowBlock"))
                  .build();
          }
        }
     }
     return getGetNowBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.BlockExtention> getGetNowBlock2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNowBlock2",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.BlockExtention> getGetNowBlock2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.BlockExtention> getGetNowBlock2Method;
    if ((getGetNowBlock2Method = WalletGrpc.getGetNowBlock2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetNowBlock2Method = WalletGrpc.getGetNowBlock2Method) == null) {
          WalletGrpc.getGetNowBlock2Method = getGetNowBlock2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.BlockExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetNowBlock2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNowBlock2"))
                  .build();
          }
        }
     }
     return getGetNowBlock2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.protos.Protocol.Block> getGetBlockByNumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByNum",
      requestType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      responseType = com.galileo.protos.protos.Protocol.Block.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.protos.Protocol.Block> getGetBlockByNumMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.protos.Protocol.Block> getGetBlockByNumMethod;
    if ((getGetBlockByNumMethod = WalletGrpc.getGetBlockByNumMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByNumMethod = WalletGrpc.getGetBlockByNumMethod) == null) {
          WalletGrpc.getGetBlockByNumMethod = getGetBlockByNumMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.protos.Protocol.Block>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockByNum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Block.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByNum"))
                  .build();
          }
        }
     }
     return getGetBlockByNumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.BlockExtention> getGetBlockByNum2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByNum2",
      requestType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.BlockExtention> getGetBlockByNum2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.BlockExtention> getGetBlockByNum2Method;
    if ((getGetBlockByNum2Method = WalletGrpc.getGetBlockByNum2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByNum2Method = WalletGrpc.getGetBlockByNum2Method) == null) {
          WalletGrpc.getGetBlockByNum2Method = getGetBlockByNum2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.BlockExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockByNum2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByNum2"))
                  .build();
          }
        }
     }
     return getGetBlockByNum2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionCountByBlockNum",
      requestType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod;
    if ((getGetTransactionCountByBlockNumMethod = WalletGrpc.getGetTransactionCountByBlockNumMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetTransactionCountByBlockNumMethod = WalletGrpc.getGetTransactionCountByBlockNumMethod) == null) {
          WalletGrpc.getGetTransactionCountByBlockNumMethod = getGetTransactionCountByBlockNumMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.NumberMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetTransactionCountByBlockNum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionCountByBlockNum"))
                  .build();
          }
        }
     }
     return getGetTransactionCountByBlockNumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Block> getGetBlockByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockById",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Protocol.Block.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Block> getGetBlockByIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Block> getGetBlockByIdMethod;
    if ((getGetBlockByIdMethod = WalletGrpc.getGetBlockByIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByIdMethod = WalletGrpc.getGetBlockByIdMethod) == null) {
          WalletGrpc.getGetBlockByIdMethod = getGetBlockByIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Block>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Block.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockById"))
                  .build();
          }
        }
     }
     return getGetBlockByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BlockLimit,
      com.galileo.protos.api.GrpcAPI.BlockList> getGetBlockByLimitNextMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByLimitNext",
      requestType = com.galileo.protos.api.GrpcAPI.BlockLimit.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BlockLimit,
      com.galileo.protos.api.GrpcAPI.BlockList> getGetBlockByLimitNextMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BlockLimit, com.galileo.protos.api.GrpcAPI.BlockList> getGetBlockByLimitNextMethod;
    if ((getGetBlockByLimitNextMethod = WalletGrpc.getGetBlockByLimitNextMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByLimitNextMethod = WalletGrpc.getGetBlockByLimitNextMethod) == null) {
          WalletGrpc.getGetBlockByLimitNextMethod = getGetBlockByLimitNextMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BlockLimit, com.galileo.protos.api.GrpcAPI.BlockList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockByLimitNext"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockLimit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLimitNext"))
                  .build();
          }
        }
     }
     return getGetBlockByLimitNextMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BlockLimit,
      com.galileo.protos.api.GrpcAPI.BlockListExtention> getGetBlockByLimitNext2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByLimitNext2",
      requestType = com.galileo.protos.api.GrpcAPI.BlockLimit.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockListExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BlockLimit,
      com.galileo.protos.api.GrpcAPI.BlockListExtention> getGetBlockByLimitNext2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BlockLimit, com.galileo.protos.api.GrpcAPI.BlockListExtention> getGetBlockByLimitNext2Method;
    if ((getGetBlockByLimitNext2Method = WalletGrpc.getGetBlockByLimitNext2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByLimitNext2Method = WalletGrpc.getGetBlockByLimitNext2Method) == null) {
          WalletGrpc.getGetBlockByLimitNext2Method = getGetBlockByLimitNext2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BlockLimit, com.galileo.protos.api.GrpcAPI.BlockListExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockByLimitNext2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockLimit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockListExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLimitNext2"))
                  .build();
          }
        }
     }
     return getGetBlockByLimitNext2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.BlockList> getGetBlockByLatestNumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByLatestNum",
      requestType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.BlockList> getGetBlockByLatestNumMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.BlockList> getGetBlockByLatestNumMethod;
    if ((getGetBlockByLatestNumMethod = WalletGrpc.getGetBlockByLatestNumMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByLatestNumMethod = WalletGrpc.getGetBlockByLatestNumMethod) == null) {
          WalletGrpc.getGetBlockByLatestNumMethod = getGetBlockByLatestNumMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.BlockList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockByLatestNum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLatestNum"))
                  .build();
          }
        }
     }
     return getGetBlockByLatestNumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.BlockListExtention> getGetBlockByLatestNum2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockByLatestNum2",
      requestType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockListExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage,
      com.galileo.protos.api.GrpcAPI.BlockListExtention> getGetBlockByLatestNum2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.BlockListExtention> getGetBlockByLatestNum2Method;
    if ((getGetBlockByLatestNum2Method = WalletGrpc.getGetBlockByLatestNum2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetBlockByLatestNum2Method = WalletGrpc.getGetBlockByLatestNum2Method) == null) {
          WalletGrpc.getGetBlockByLatestNum2Method = getGetBlockByLatestNum2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.api.GrpcAPI.BlockListExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetBlockByLatestNum2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockListExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLatestNum2"))
                  .build();
          }
        }
     }
     return getGetBlockByLatestNum2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Transaction> getGetTransactionByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionById",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Transaction> getGetTransactionByIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Transaction> getGetTransactionByIdMethod;
    if ((getGetTransactionByIdMethod = WalletGrpc.getGetTransactionByIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetTransactionByIdMethod = WalletGrpc.getGetTransactionByIdMethod) == null) {
          WalletGrpc.getGetTransactionByIdMethod = getGetTransactionByIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetTransactionById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionById"))
                  .build();
          }
        }
     }
     return getGetTransactionByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.CreateSmartContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getDeployContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeployContract",
      requestType = com.galileo.protos.protos.Contract.CreateSmartContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.CreateSmartContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getDeployContractMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.CreateSmartContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getDeployContractMethod;
    if ((getDeployContractMethod = WalletGrpc.getDeployContractMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getDeployContractMethod = WalletGrpc.getDeployContractMethod) == null) {
          WalletGrpc.getDeployContractMethod = getDeployContractMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.CreateSmartContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "DeployContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.CreateSmartContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("DeployContract"))
                  .build();
          }
        }
     }
     return getDeployContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.SmartContract> getGetContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetContract",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Protocol.SmartContract.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.SmartContract> getGetContractMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.SmartContract> getGetContractMethod;
    if ((getGetContractMethod = WalletGrpc.getGetContractMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetContractMethod = WalletGrpc.getGetContractMethod) == null) {
          WalletGrpc.getGetContractMethod = getGetContractMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.SmartContract>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.SmartContract.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetContract"))
                  .build();
          }
        }
     }
     return getGetContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TriggerSmartContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getTriggerContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TriggerContract",
      requestType = com.galileo.protos.protos.Contract.TriggerSmartContract.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TriggerSmartContract,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getTriggerContractMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Contract.TriggerSmartContract, com.galileo.protos.api.GrpcAPI.TransactionExtention> getTriggerContractMethod;
    if ((getTriggerContractMethod = WalletGrpc.getTriggerContractMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getTriggerContractMethod = WalletGrpc.getTriggerContractMethod) == null) {
          WalletGrpc.getTriggerContractMethod = getTriggerContractMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Contract.TriggerSmartContract, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "TriggerContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Contract.TriggerSmartContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("TriggerContract"))
                  .build();
          }
        }
     }
     return getTriggerContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.WitnessList> getListWitnessesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListWitnesses",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.WitnessList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.WitnessList> getListWitnessesMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.WitnessList> getListWitnessesMethod;
    if ((getListWitnessesMethod = WalletGrpc.getListWitnessesMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getListWitnessesMethod = WalletGrpc.getListWitnessesMethod) == null) {
          WalletGrpc.getListWitnessesMethod = getListWitnessesMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.WitnessList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ListWitnesses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.WitnessList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListWitnesses"))
                  .build();
          }
        }
     }
     return getListWitnessesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.ProposalList> getListProposalsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListProposals",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.ProposalList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.ProposalList> getListProposalsMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.ProposalList> getListProposalsMethod;
    if ((getListProposalsMethod = WalletGrpc.getListProposalsMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getListProposalsMethod = WalletGrpc.getListProposalsMethod) == null) {
          WalletGrpc.getListProposalsMethod = getListProposalsMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.ProposalList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ListProposals"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.ProposalList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListProposals"))
                  .build();
          }
        }
     }
     return getListProposalsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage,
      com.galileo.protos.api.GrpcAPI.ProposalList> getGetPaginatedProposalListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPaginatedProposalList",
      requestType = com.galileo.protos.api.GrpcAPI.PaginatedMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.ProposalList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage,
      com.galileo.protos.api.GrpcAPI.ProposalList> getGetPaginatedProposalListMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage, com.galileo.protos.api.GrpcAPI.ProposalList> getGetPaginatedProposalListMethod;
    if ((getGetPaginatedProposalListMethod = WalletGrpc.getGetPaginatedProposalListMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetPaginatedProposalListMethod = WalletGrpc.getGetPaginatedProposalListMethod) == null) {
          WalletGrpc.getGetPaginatedProposalListMethod = getGetPaginatedProposalListMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.PaginatedMessage, com.galileo.protos.api.GrpcAPI.ProposalList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetPaginatedProposalList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.PaginatedMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.ProposalList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPaginatedProposalList"))
                  .build();
          }
        }
     }
     return getGetPaginatedProposalListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Proposal> getGetProposalByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetProposalById",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Protocol.Proposal.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Proposal> getGetProposalByIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Proposal> getGetProposalByIdMethod;
    if ((getGetProposalByIdMethod = WalletGrpc.getGetProposalByIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetProposalByIdMethod = WalletGrpc.getGetProposalByIdMethod) == null) {
          WalletGrpc.getGetProposalByIdMethod = getGetProposalByIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Proposal>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetProposalById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Proposal.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetProposalById"))
                  .build();
          }
        }
     }
     return getGetProposalByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.ExchangeList> getListExchangesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListExchanges",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.ExchangeList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.ExchangeList> getListExchangesMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.ExchangeList> getListExchangesMethod;
    if ((getListExchangesMethod = WalletGrpc.getListExchangesMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getListExchangesMethod = WalletGrpc.getListExchangesMethod) == null) {
          WalletGrpc.getListExchangesMethod = getListExchangesMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.ExchangeList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "ListExchanges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.ExchangeList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListExchanges"))
                  .build();
          }
        }
     }
     return getListExchangesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage,
      com.galileo.protos.api.GrpcAPI.ExchangeList> getGetPaginatedExchangeListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPaginatedExchangeList",
      requestType = com.galileo.protos.api.GrpcAPI.PaginatedMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.ExchangeList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage,
      com.galileo.protos.api.GrpcAPI.ExchangeList> getGetPaginatedExchangeListMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage, com.galileo.protos.api.GrpcAPI.ExchangeList> getGetPaginatedExchangeListMethod;
    if ((getGetPaginatedExchangeListMethod = WalletGrpc.getGetPaginatedExchangeListMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetPaginatedExchangeListMethod = WalletGrpc.getGetPaginatedExchangeListMethod) == null) {
          WalletGrpc.getGetPaginatedExchangeListMethod = getGetPaginatedExchangeListMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.PaginatedMessage, com.galileo.protos.api.GrpcAPI.ExchangeList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetPaginatedExchangeList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.PaginatedMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.ExchangeList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPaginatedExchangeList"))
                  .build();
          }
        }
     }
     return getGetPaginatedExchangeListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Exchange> getGetExchangeByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetExchangeById",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Protocol.Exchange.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.Exchange> getGetExchangeByIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Exchange> getGetExchangeByIdMethod;
    if ((getGetExchangeByIdMethod = WalletGrpc.getGetExchangeByIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetExchangeByIdMethod = WalletGrpc.getGetExchangeByIdMethod) == null) {
          WalletGrpc.getGetExchangeByIdMethod = getGetExchangeByIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.Exchange>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetExchangeById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Exchange.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetExchangeById"))
                  .build();
          }
        }
     }
     return getGetExchangeByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.protos.Protocol.ChainParameters> getGetChainParametersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetChainParameters",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.protos.Protocol.ChainParameters.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.protos.Protocol.ChainParameters> getGetChainParametersMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.ChainParameters> getGetChainParametersMethod;
    if ((getGetChainParametersMethod = WalletGrpc.getGetChainParametersMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetChainParametersMethod = WalletGrpc.getGetChainParametersMethod) == null) {
          WalletGrpc.getGetChainParametersMethod = getGetChainParametersMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.ChainParameters>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetChainParameters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.ChainParameters.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetChainParameters"))
                  .build();
          }
        }
     }
     return getGetChainParametersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetAssetIssueListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetIssueList",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.AssetIssueList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetAssetIssueListMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetAssetIssueListMethod;
    if ((getGetAssetIssueListMethod = WalletGrpc.getGetAssetIssueListMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetAssetIssueListMethod = WalletGrpc.getGetAssetIssueListMethod) == null) {
          WalletGrpc.getGetAssetIssueListMethod = getGetAssetIssueListMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.AssetIssueList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetAssetIssueList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.AssetIssueList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueList"))
                  .build();
          }
        }
     }
     return getGetAssetIssueListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage,
      com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPaginatedAssetIssueList",
      requestType = com.galileo.protos.api.GrpcAPI.PaginatedMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.AssetIssueList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage,
      com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.PaginatedMessage, com.galileo.protos.api.GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod;
    if ((getGetPaginatedAssetIssueListMethod = WalletGrpc.getGetPaginatedAssetIssueListMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetPaginatedAssetIssueListMethod = WalletGrpc.getGetPaginatedAssetIssueListMethod) == null) {
          WalletGrpc.getGetPaginatedAssetIssueListMethod = getGetPaginatedAssetIssueListMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.PaginatedMessage, com.galileo.protos.api.GrpcAPI.AssetIssueList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetPaginatedAssetIssueList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.PaginatedMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.AssetIssueList.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPaginatedAssetIssueList"))
                  .build();
          }
        }
     }
     return getGetPaginatedAssetIssueListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.NumberMessage> getTotalTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalTransaction",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.NumberMessage> getTotalTransactionMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.NumberMessage> getTotalTransactionMethod;
    if ((getTotalTransactionMethod = WalletGrpc.getTotalTransactionMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getTotalTransactionMethod = WalletGrpc.getTotalTransactionMethod) == null) {
          WalletGrpc.getTotalTransactionMethod = getTotalTransactionMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.NumberMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "TotalTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("TotalTransaction"))
                  .build();
          }
        }
     }
     return getTotalTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.NumberMessage> getGetNextMaintenanceTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNextMaintenanceTime",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.NumberMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.NumberMessage> getGetNextMaintenanceTimeMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.NumberMessage> getGetNextMaintenanceTimeMethod;
    if ((getGetNextMaintenanceTimeMethod = WalletGrpc.getGetNextMaintenanceTimeMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetNextMaintenanceTimeMethod = WalletGrpc.getGetNextMaintenanceTimeMethod) == null) {
          WalletGrpc.getGetNextMaintenanceTimeMethod = getGetNextMaintenanceTimeMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.NumberMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetNextMaintenanceTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNextMaintenanceTime"))
                  .build();
          }
        }
     }
     return getGetNextMaintenanceTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.TransactionSign,
      com.galileo.protos.protos.Protocol.Transaction> getGetTransactionSignMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionSign",
      requestType = com.galileo.protos.protos.Protocol.TransactionSign.class,
      responseType = com.galileo.protos.protos.Protocol.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.TransactionSign,
      com.galileo.protos.protos.Protocol.Transaction> getGetTransactionSignMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.TransactionSign, com.galileo.protos.protos.Protocol.Transaction> getGetTransactionSignMethod;
    if ((getGetTransactionSignMethod = WalletGrpc.getGetTransactionSignMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetTransactionSignMethod = WalletGrpc.getGetTransactionSignMethod) == null) {
          WalletGrpc.getGetTransactionSignMethod = getGetTransactionSignMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.TransactionSign, com.galileo.protos.protos.Protocol.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetTransactionSign"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.TransactionSign.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionSign"))
                  .build();
          }
        }
     }
     return getGetTransactionSignMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.TransactionSign,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getGetTransactionSign2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionSign2",
      requestType = com.galileo.protos.protos.Protocol.TransactionSign.class,
      responseType = com.galileo.protos.api.GrpcAPI.TransactionExtention.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.TransactionSign,
      com.galileo.protos.api.GrpcAPI.TransactionExtention> getGetTransactionSign2Method() {
    io.grpc.MethodDescriptor<com.galileo.protos.protos.Protocol.TransactionSign, com.galileo.protos.api.GrpcAPI.TransactionExtention> getGetTransactionSign2Method;
    if ((getGetTransactionSign2Method = WalletGrpc.getGetTransactionSign2Method) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetTransactionSign2Method = WalletGrpc.getGetTransactionSign2Method) == null) {
          WalletGrpc.getGetTransactionSign2Method = getGetTransactionSign2Method = 
              io.grpc.MethodDescriptor.<com.galileo.protos.protos.Protocol.TransactionSign, com.galileo.protos.api.GrpcAPI.TransactionExtention>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetTransactionSign2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.TransactionSign.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.TransactionExtention.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionSign2"))
                  .build();
          }
        }
     }
     return getGetTransactionSign2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.api.GrpcAPI.BytesMessage> getCreateAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAddress",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.api.GrpcAPI.BytesMessage> getCreateAddressMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.api.GrpcAPI.BytesMessage> getCreateAddressMethod;
    if ((getCreateAddressMethod = WalletGrpc.getCreateAddressMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateAddressMethod = WalletGrpc.getCreateAddressMethod) == null) {
          WalletGrpc.getCreateAddressMethod = getCreateAddressMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.api.GrpcAPI.BytesMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "CreateAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAddress"))
                  .build();
          }
        }
     }
     return getCreateAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EasyTransferMessage,
      com.galileo.protos.api.GrpcAPI.EasyTransferResponse> getEasyTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EasyTransfer",
      requestType = com.galileo.protos.api.GrpcAPI.EasyTransferMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.EasyTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EasyTransferMessage,
      com.galileo.protos.api.GrpcAPI.EasyTransferResponse> getEasyTransferMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EasyTransferMessage, com.galileo.protos.api.GrpcAPI.EasyTransferResponse> getEasyTransferMethod;
    if ((getEasyTransferMethod = WalletGrpc.getEasyTransferMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getEasyTransferMethod = WalletGrpc.getEasyTransferMethod) == null) {
          WalletGrpc.getEasyTransferMethod = getEasyTransferMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EasyTransferMessage, com.galileo.protos.api.GrpcAPI.EasyTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "EasyTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EasyTransferMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EasyTransferResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("EasyTransfer"))
                  .build();
          }
        }
     }
     return getEasyTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage,
      com.galileo.protos.api.GrpcAPI.EasyTransferResponse> getEasyTransferByPrivateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EasyTransferByPrivate",
      requestType = com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.EasyTransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage,
      com.galileo.protos.api.GrpcAPI.EasyTransferResponse> getEasyTransferByPrivateMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage, com.galileo.protos.api.GrpcAPI.EasyTransferResponse> getEasyTransferByPrivateMethod;
    if ((getEasyTransferByPrivateMethod = WalletGrpc.getEasyTransferByPrivateMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getEasyTransferByPrivateMethod = WalletGrpc.getEasyTransferByPrivateMethod) == null) {
          WalletGrpc.getEasyTransferByPrivateMethod = getEasyTransferByPrivateMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage, com.galileo.protos.api.GrpcAPI.EasyTransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "EasyTransferByPrivate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EasyTransferResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("EasyTransferByPrivate"))
                  .build();
          }
        }
     }
     return getEasyTransferByPrivateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage> getGenerateAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GenerateAddress",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage> getGenerateAddressMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage> getGenerateAddressMethod;
    if ((getGenerateAddressMethod = WalletGrpc.getGenerateAddressMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGenerateAddressMethod = WalletGrpc.getGenerateAddressMethod) == null) {
          WalletGrpc.getGenerateAddressMethod = getGenerateAddressMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GenerateAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GenerateAddress"))
                  .build();
          }
        }
     }
     return getGenerateAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.TransactionInfo> getGetTransactionInfoByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionInfoById",
      requestType = com.galileo.protos.api.GrpcAPI.BytesMessage.class,
      responseType = com.galileo.protos.protos.Protocol.TransactionInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage,
      com.galileo.protos.protos.Protocol.TransactionInfo> getGetTransactionInfoByIdMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.TransactionInfo> getGetTransactionInfoByIdMethod;
    if ((getGetTransactionInfoByIdMethod = WalletGrpc.getGetTransactionInfoByIdMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getGetTransactionInfoByIdMethod = WalletGrpc.getGetTransactionInfoByIdMethod) == null) {
          WalletGrpc.getGetTransactionInfoByIdMethod = getGetTransactionInfoByIdMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.BytesMessage, com.galileo.protos.protos.Protocol.TransactionInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Wallet", "GetTransactionInfoById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BytesMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.TransactionInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionInfoById"))
                  .build();
          }
        }
     }
     return getGetTransactionInfoByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WalletStub newStub(io.grpc.Channel channel) {
    return new WalletStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WalletBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WalletBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WalletFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WalletFutureStub(channel);
  }

  /**
   */
  public static abstract class WalletImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAccount(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Account> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountMethod(), responseObserver);
    }

    /**
     */
    public void getAccountById(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Account> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *Please use CreateTransaction2 instead of this function.
     * </pre>
     */
    public void createTransaction(com.galileo.protos.protos.Contract.TransferContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateTransaction.
     * </pre>
     */
    public void createTransaction2(com.galileo.protos.protos.Contract.TransferContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTransaction2Method(), responseObserver);
    }

    /**
     */
    public void broadcastTransaction(com.galileo.protos.protos.Protocol.Transaction request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.Return> responseObserver) {
      asyncUnimplementedUnaryCall(getBroadcastTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     *Please use UpdateAccount2 instead of this function.
     * </pre>
     */
    public void updateAccount(com.galileo.protos.protos.Contract.AccountUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAccountMethod(), responseObserver);
    }

    /**
     */
    public void setAccountId(com.galileo.protos.protos.Contract.SetAccountIdContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getSetAccountIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAccount.
     * </pre>
     */
    public void updateAccount2(com.galileo.protos.protos.Contract.AccountUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAccount2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use VoteWitnessAccount2 instead of this function.
     * </pre>
     */
    public void voteWitnessAccount(com.galileo.protos.protos.Contract.VoteWitnessContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteWitnessAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     *modify the consume_user_resource_percent
     * </pre>
     */
    public void updateSetting(com.galileo.protos.protos.Contract.UpdateSettingContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateSettingMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of VoteWitnessAccount.
     * </pre>
     */
    public void voteWitnessAccount2(com.galileo.protos.protos.Contract.VoteWitnessContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteWitnessAccount2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use CreateAssetIssue2 instead of this function.
     * </pre>
     */
    public void createAssetIssue(com.galileo.protos.protos.Contract.AssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAssetIssueMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateAssetIssue.
     * </pre>
     */
    public void createAssetIssue2(com.galileo.protos.protos.Contract.AssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAssetIssue2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use UpdateWitness2 instead of this function.
     * </pre>
     */
    public void updateWitness(com.galileo.protos.protos.Contract.WitnessUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateWitnessMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UpdateWitness.
     * </pre>
     */
    public void updateWitness2(com.galileo.protos.protos.Contract.WitnessUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateWitness2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use CreateAccount2 instead of this function.
     * </pre>
     */
    public void createAccount(com.galileo.protos.protos.Contract.AccountCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateAccount.
     * </pre>
     */
    public void createAccount2(com.galileo.protos.protos.Contract.AccountCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAccount2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use CreateWitness2 instead of this function.
     * </pre>
     */
    public void createWitness(com.galileo.protos.protos.Contract.WitnessCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateWitnessMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateWitness.
     * </pre>
     */
    public void createWitness2(com.galileo.protos.protos.Contract.WitnessCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateWitness2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use TransferAsset2 instead of this function.
     * </pre>
     */
    public void transferAsset(com.galileo.protos.protos.Contract.TransferAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of TransferAsset.
     * </pre>
     */
    public void transferAsset2(com.galileo.protos.protos.Contract.TransferAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferAsset2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use ParticipateAssetIssue2 instead of this function.
     * </pre>
     */
    public void participateAssetIssue(com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getParticipateAssetIssueMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of ParticipateAssetIssue.
     * </pre>
     */
    public void participateAssetIssue2(com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getParticipateAssetIssue2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use FreezeBalance2 instead of this function.
     * </pre>
     */
    public void freezeBalance(com.galileo.protos.protos.Contract.FreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getFreezeBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of FreezeBalance.
     * </pre>
     */
    public void freezeBalance2(com.galileo.protos.protos.Contract.FreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getFreezeBalance2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use UnfreezeBalance2 instead of this function.
     * </pre>
     */
    public void unfreezeBalance(com.galileo.protos.protos.Contract.UnfreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getUnfreezeBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeBalance.
     * </pre>
     */
    public void unfreezeBalance2(com.galileo.protos.protos.Contract.UnfreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getUnfreezeBalance2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use UnfreezeAsset2 instead of this function.
     * </pre>
     */
    public void unfreezeAsset(com.galileo.protos.protos.Contract.UnfreezeAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getUnfreezeAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeAsset.
     * </pre>
     */
    public void unfreezeAsset2(com.galileo.protos.protos.Contract.UnfreezeAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getUnfreezeAsset2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use WithdrawBalance2 instead of this function.
     * </pre>
     */
    public void withdrawBalance(com.galileo.protos.protos.Contract.WithdrawBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of WithdrawBalance.
     * </pre>
     */
    public void withdrawBalance2(com.galileo.protos.protos.Contract.WithdrawBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawBalance2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use UpdateAsset2 instead of this function.
     * </pre>
     */
    public void updateAsset(com.galileo.protos.protos.Contract.UpdateAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAsset.
     * </pre>
     */
    public void updateAsset2(com.galileo.protos.protos.Contract.UpdateAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAsset2Method(), responseObserver);
    }

    /**
     */
    public void proposalCreate(com.galileo.protos.protos.Contract.ProposalCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalCreateMethod(), responseObserver);
    }

    /**
     */
    public void proposalApprove(com.galileo.protos.protos.Contract.ProposalApproveContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalApproveMethod(), responseObserver);
    }

    /**
     */
    public void proposalDelete(com.galileo.protos.protos.Contract.ProposalDeleteContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalDeleteMethod(), responseObserver);
    }

    /**
     */
    public void buyStorage(com.galileo.protos.protos.Contract.BuyStorageContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyStorageMethod(), responseObserver);
    }

    /**
     */
    public void buyStorageBytes(com.galileo.protos.protos.Contract.BuyStorageBytesContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyStorageBytesMethod(), responseObserver);
    }

    /**
     */
    public void sellStorage(com.galileo.protos.protos.Contract.SellStorageContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getSellStorageMethod(), responseObserver);
    }

    /**
     */
    public void exchangeCreate(com.galileo.protos.protos.Contract.ExchangeCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeCreateMethod(), responseObserver);
    }

    /**
     */
    public void exchangeInject(com.galileo.protos.protos.Contract.ExchangeInjectContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeInjectMethod(), responseObserver);
    }

    /**
     */
    public void exchangeWithdraw(com.galileo.protos.protos.Contract.ExchangeWithdrawContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeWithdrawMethod(), responseObserver);
    }

    /**
     */
    public void exchangeTransaction(com.galileo.protos.protos.Contract.ExchangeTransactionContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getExchangeTransactionMethod(), responseObserver);
    }

    /**
     */
    public void listNodes(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NodeList> responseObserver) {
      asyncUnimplementedUnaryCall(getListNodesMethod(), responseObserver);
    }

    /**
     */
    public void getAssetIssueByAccount(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetIssueByAccountMethod(), responseObserver);
    }

    /**
     */
    public void getAccountNet(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AccountNetMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountNetMethod(), responseObserver);
    }

    /**
     */
    public void getAccountResource(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AccountResourceMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountResourceMethod(), responseObserver);
    }

    /**
     */
    public void getAssetIssueByName(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Contract.AssetIssueContract> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetIssueByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     *Please use GetNowBlock2 instead of this function.
     * </pre>
     */
    public void getNowBlock(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNowBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetNowBlock.
     * </pre>
     */
    public void getNowBlock2(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNowBlock2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use GetBlockByNum2 instead of this function.
     * </pre>
     */
    public void getBlockByNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByNumMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByNum.
     * </pre>
     */
    public void getBlockByNum2(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByNum2Method(), responseObserver);
    }

    /**
     */
    public void getTransactionCountByBlockNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionCountByBlockNumMethod(), responseObserver);
    }

    /**
     */
    public void getBlockById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *Please use GetBlockByLimitNext2 instead of this function.
     * </pre>
     */
    public void getBlockByLimitNext(com.galileo.protos.api.GrpcAPI.BlockLimit request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByLimitNextMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLimitNext.
     * </pre>
     */
    public void getBlockByLimitNext2(com.galileo.protos.api.GrpcAPI.BlockLimit request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockListExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByLimitNext2Method(), responseObserver);
    }

    /**
     * <pre>
     *Please use GetBlockByLatestNum2 instead of this function.
     * </pre>
     */
    public void getBlockByLatestNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByLatestNumMethod(), responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLatestNum.
     * </pre>
     */
    public void getBlockByLatestNum2(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockListExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByLatestNum2Method(), responseObserver);
    }

    /**
     */
    public void getTransactionById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionByIdMethod(), responseObserver);
    }

    /**
     */
    public void deployContract(com.galileo.protos.protos.Contract.CreateSmartContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getDeployContractMethod(), responseObserver);
    }

    /**
     */
    public void getContract(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.SmartContract> responseObserver) {
      asyncUnimplementedUnaryCall(getGetContractMethod(), responseObserver);
    }

    /**
     */
    public void triggerContract(com.galileo.protos.protos.Contract.TriggerSmartContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getTriggerContractMethod(), responseObserver);
    }

    /**
     */
    public void listWitnesses(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.WitnessList> responseObserver) {
      asyncUnimplementedUnaryCall(getListWitnessesMethod(), responseObserver);
    }

    /**
     */
    public void listProposals(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ProposalList> responseObserver) {
      asyncUnimplementedUnaryCall(getListProposalsMethod(), responseObserver);
    }

    /**
     */
    public void getPaginatedProposalList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ProposalList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPaginatedProposalListMethod(), responseObserver);
    }

    /**
     */
    public void getProposalById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Proposal> responseObserver) {
      asyncUnimplementedUnaryCall(getGetProposalByIdMethod(), responseObserver);
    }

    /**
     */
    public void listExchanges(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ExchangeList> responseObserver) {
      asyncUnimplementedUnaryCall(getListExchangesMethod(), responseObserver);
    }

    /**
     */
    public void getPaginatedExchangeList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ExchangeList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPaginatedExchangeListMethod(), responseObserver);
    }

    /**
     */
    public void getExchangeById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Exchange> responseObserver) {
      asyncUnimplementedUnaryCall(getGetExchangeByIdMethod(), responseObserver);
    }

    /**
     */
    public void getChainParameters(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.ChainParameters> responseObserver) {
      asyncUnimplementedUnaryCall(getGetChainParametersMethod(), responseObserver);
    }

    /**
     */
    public void getAssetIssueList(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetIssueListMethod(), responseObserver);
    }

    /**
     */
    public void getPaginatedAssetIssueList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPaginatedAssetIssueListMethod(), responseObserver);
    }

    /**
     */
    public void totalTransaction(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalTransactionMethod(), responseObserver);
    }

    /**
     */
    public void getNextMaintenanceTime(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNextMaintenanceTimeMethod(), responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Please use GetTransactionSign2 instead of this function.
     * </pre>
     */
    public void getTransactionSign(com.galileo.protos.protos.Protocol.TransactionSign request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionSignMethod(), responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Use this function instead of GetTransactionSign.
     * </pre>
     */
    public void getTransactionSign2(com.galileo.protos.protos.Protocol.TransactionSign request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionSign2Method(), responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void createAddress(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BytesMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void easyTransfer(com.galileo.protos.api.GrpcAPI.EasyTransferMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.EasyTransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEasyTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void easyTransferByPrivate(com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.EasyTransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEasyTransferByPrivateMethod(), responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void generateAddress(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGenerateAddressMethod(), responseObserver);
    }

    /**
     */
    public void getTransactionInfoById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.TransactionInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionInfoByIdMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.Account,
                com.galileo.protos.protos.Protocol.Account>(
                  this, METHODID_GET_ACCOUNT)))
          .addMethod(
            getGetAccountByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.Account,
                com.galileo.protos.protos.Protocol.Account>(
                  this, METHODID_GET_ACCOUNT_BY_ID)))
          .addMethod(
            getCreateTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.TransferContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_CREATE_TRANSACTION)))
          .addMethod(
            getCreateTransaction2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.TransferContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_CREATE_TRANSACTION2)))
          .addMethod(
            getBroadcastTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.Transaction,
                com.galileo.protos.api.GrpcAPI.Return>(
                  this, METHODID_BROADCAST_TRANSACTION)))
          .addMethod(
            getUpdateAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.AccountUpdateContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_UPDATE_ACCOUNT)))
          .addMethod(
            getSetAccountIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.SetAccountIdContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_SET_ACCOUNT_ID)))
          .addMethod(
            getUpdateAccount2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.AccountUpdateContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_UPDATE_ACCOUNT2)))
          .addMethod(
            getVoteWitnessAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.VoteWitnessContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_VOTE_WITNESS_ACCOUNT)))
          .addMethod(
            getUpdateSettingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UpdateSettingContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_UPDATE_SETTING)))
          .addMethod(
            getVoteWitnessAccount2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.VoteWitnessContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_VOTE_WITNESS_ACCOUNT2)))
          .addMethod(
            getCreateAssetIssueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.AssetIssueContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_CREATE_ASSET_ISSUE)))
          .addMethod(
            getCreateAssetIssue2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.AssetIssueContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_CREATE_ASSET_ISSUE2)))
          .addMethod(
            getUpdateWitnessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.WitnessUpdateContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_UPDATE_WITNESS)))
          .addMethod(
            getUpdateWitness2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.WitnessUpdateContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_UPDATE_WITNESS2)))
          .addMethod(
            getCreateAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.AccountCreateContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_CREATE_ACCOUNT)))
          .addMethod(
            getCreateAccount2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.AccountCreateContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_CREATE_ACCOUNT2)))
          .addMethod(
            getCreateWitnessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.WitnessCreateContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_CREATE_WITNESS)))
          .addMethod(
            getCreateWitness2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.WitnessCreateContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_CREATE_WITNESS2)))
          .addMethod(
            getTransferAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.TransferAssetContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_TRANSFER_ASSET)))
          .addMethod(
            getTransferAsset2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.TransferAssetContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_TRANSFER_ASSET2)))
          .addMethod(
            getParticipateAssetIssueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ParticipateAssetIssueContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_PARTICIPATE_ASSET_ISSUE)))
          .addMethod(
            getParticipateAssetIssue2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ParticipateAssetIssueContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_PARTICIPATE_ASSET_ISSUE2)))
          .addMethod(
            getFreezeBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.FreezeBalanceContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_FREEZE_BALANCE)))
          .addMethod(
            getFreezeBalance2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.FreezeBalanceContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_FREEZE_BALANCE2)))
          .addMethod(
            getUnfreezeBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UnfreezeBalanceContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_UNFREEZE_BALANCE)))
          .addMethod(
            getUnfreezeBalance2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UnfreezeBalanceContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_UNFREEZE_BALANCE2)))
          .addMethod(
            getUnfreezeAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UnfreezeAssetContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_UNFREEZE_ASSET)))
          .addMethod(
            getUnfreezeAsset2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UnfreezeAssetContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_UNFREEZE_ASSET2)))
          .addMethod(
            getWithdrawBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.WithdrawBalanceContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_WITHDRAW_BALANCE)))
          .addMethod(
            getWithdrawBalance2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.WithdrawBalanceContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_WITHDRAW_BALANCE2)))
          .addMethod(
            getUpdateAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UpdateAssetContract,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_UPDATE_ASSET)))
          .addMethod(
            getUpdateAsset2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.UpdateAssetContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_UPDATE_ASSET2)))
          .addMethod(
            getProposalCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ProposalCreateContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_PROPOSAL_CREATE)))
          .addMethod(
            getProposalApproveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ProposalApproveContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_PROPOSAL_APPROVE)))
          .addMethod(
            getProposalDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ProposalDeleteContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_PROPOSAL_DELETE)))
          .addMethod(
            getBuyStorageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.BuyStorageContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_BUY_STORAGE)))
          .addMethod(
            getBuyStorageBytesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.BuyStorageBytesContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_BUY_STORAGE_BYTES)))
          .addMethod(
            getSellStorageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.SellStorageContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_SELL_STORAGE)))
          .addMethod(
            getExchangeCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ExchangeCreateContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_EXCHANGE_CREATE)))
          .addMethod(
            getExchangeInjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ExchangeInjectContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_EXCHANGE_INJECT)))
          .addMethod(
            getExchangeWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ExchangeWithdrawContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_EXCHANGE_WITHDRAW)))
          .addMethod(
            getExchangeTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.ExchangeTransactionContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_EXCHANGE_TRANSACTION)))
          .addMethod(
            getListNodesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.NodeList>(
                  this, METHODID_LIST_NODES)))
          .addMethod(
            getGetAssetIssueByAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.Account,
                com.galileo.protos.api.GrpcAPI.AssetIssueList>(
                  this, METHODID_GET_ASSET_ISSUE_BY_ACCOUNT)))
          .addMethod(
            getGetAccountNetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.Account,
                com.galileo.protos.api.GrpcAPI.AccountNetMessage>(
                  this, METHODID_GET_ACCOUNT_NET)))
          .addMethod(
            getGetAccountResourceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.Account,
                com.galileo.protos.api.GrpcAPI.AccountResourceMessage>(
                  this, METHODID_GET_ACCOUNT_RESOURCE)))
          .addMethod(
            getGetAssetIssueByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Contract.AssetIssueContract>(
                  this, METHODID_GET_ASSET_ISSUE_BY_NAME)))
          .addMethod(
            getGetNowBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.protos.Protocol.Block>(
                  this, METHODID_GET_NOW_BLOCK)))
          .addMethod(
            getGetNowBlock2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.BlockExtention>(
                  this, METHODID_GET_NOW_BLOCK2)))
          .addMethod(
            getGetBlockByNumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.NumberMessage,
                com.galileo.protos.protos.Protocol.Block>(
                  this, METHODID_GET_BLOCK_BY_NUM)))
          .addMethod(
            getGetBlockByNum2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.NumberMessage,
                com.galileo.protos.api.GrpcAPI.BlockExtention>(
                  this, METHODID_GET_BLOCK_BY_NUM2)))
          .addMethod(
            getGetTransactionCountByBlockNumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.NumberMessage,
                com.galileo.protos.api.GrpcAPI.NumberMessage>(
                  this, METHODID_GET_TRANSACTION_COUNT_BY_BLOCK_NUM)))
          .addMethod(
            getGetBlockByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Protocol.Block>(
                  this, METHODID_GET_BLOCK_BY_ID)))
          .addMethod(
            getGetBlockByLimitNextMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BlockLimit,
                com.galileo.protos.api.GrpcAPI.BlockList>(
                  this, METHODID_GET_BLOCK_BY_LIMIT_NEXT)))
          .addMethod(
            getGetBlockByLimitNext2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BlockLimit,
                com.galileo.protos.api.GrpcAPI.BlockListExtention>(
                  this, METHODID_GET_BLOCK_BY_LIMIT_NEXT2)))
          .addMethod(
            getGetBlockByLatestNumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.NumberMessage,
                com.galileo.protos.api.GrpcAPI.BlockList>(
                  this, METHODID_GET_BLOCK_BY_LATEST_NUM)))
          .addMethod(
            getGetBlockByLatestNum2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.NumberMessage,
                com.galileo.protos.api.GrpcAPI.BlockListExtention>(
                  this, METHODID_GET_BLOCK_BY_LATEST_NUM2)))
          .addMethod(
            getGetTransactionByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_GET_TRANSACTION_BY_ID)))
          .addMethod(
            getDeployContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.CreateSmartContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_DEPLOY_CONTRACT)))
          .addMethod(
            getGetContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Protocol.SmartContract>(
                  this, METHODID_GET_CONTRACT)))
          .addMethod(
            getTriggerContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Contract.TriggerSmartContract,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_TRIGGER_CONTRACT)))
          .addMethod(
            getListWitnessesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.WitnessList>(
                  this, METHODID_LIST_WITNESSES)))
          .addMethod(
            getListProposalsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.ProposalList>(
                  this, METHODID_LIST_PROPOSALS)))
          .addMethod(
            getGetPaginatedProposalListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.PaginatedMessage,
                com.galileo.protos.api.GrpcAPI.ProposalList>(
                  this, METHODID_GET_PAGINATED_PROPOSAL_LIST)))
          .addMethod(
            getGetProposalByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Protocol.Proposal>(
                  this, METHODID_GET_PROPOSAL_BY_ID)))
          .addMethod(
            getListExchangesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.ExchangeList>(
                  this, METHODID_LIST_EXCHANGES)))
          .addMethod(
            getGetPaginatedExchangeListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.PaginatedMessage,
                com.galileo.protos.api.GrpcAPI.ExchangeList>(
                  this, METHODID_GET_PAGINATED_EXCHANGE_LIST)))
          .addMethod(
            getGetExchangeByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Protocol.Exchange>(
                  this, METHODID_GET_EXCHANGE_BY_ID)))
          .addMethod(
            getGetChainParametersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.protos.Protocol.ChainParameters>(
                  this, METHODID_GET_CHAIN_PARAMETERS)))
          .addMethod(
            getGetAssetIssueListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.AssetIssueList>(
                  this, METHODID_GET_ASSET_ISSUE_LIST)))
          .addMethod(
            getGetPaginatedAssetIssueListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.PaginatedMessage,
                com.galileo.protos.api.GrpcAPI.AssetIssueList>(
                  this, METHODID_GET_PAGINATED_ASSET_ISSUE_LIST)))
          .addMethod(
            getTotalTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.NumberMessage>(
                  this, METHODID_TOTAL_TRANSACTION)))
          .addMethod(
            getGetNextMaintenanceTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.NumberMessage>(
                  this, METHODID_GET_NEXT_MAINTENANCE_TIME)))
          .addMethod(
            getGetTransactionSignMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.TransactionSign,
                com.galileo.protos.protos.Protocol.Transaction>(
                  this, METHODID_GET_TRANSACTION_SIGN)))
          .addMethod(
            getGetTransactionSign2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.protos.Protocol.TransactionSign,
                com.galileo.protos.api.GrpcAPI.TransactionExtention>(
                  this, METHODID_GET_TRANSACTION_SIGN2)))
          .addMethod(
            getCreateAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.api.GrpcAPI.BytesMessage>(
                  this, METHODID_CREATE_ADDRESS)))
          .addMethod(
            getEasyTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EasyTransferMessage,
                com.galileo.protos.api.GrpcAPI.EasyTransferResponse>(
                  this, METHODID_EASY_TRANSFER)))
          .addMethod(
            getEasyTransferByPrivateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage,
                com.galileo.protos.api.GrpcAPI.EasyTransferResponse>(
                  this, METHODID_EASY_TRANSFER_BY_PRIVATE)))
          .addMethod(
            getGenerateAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage>(
                  this, METHODID_GENERATE_ADDRESS)))
          .addMethod(
            getGetTransactionInfoByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.BytesMessage,
                com.galileo.protos.protos.Protocol.TransactionInfo>(
                  this, METHODID_GET_TRANSACTION_INFO_BY_ID)))
          .build();
    }
  }

  /**
   */
  public static final class WalletStub extends io.grpc.stub.AbstractStub<WalletStub> {
    private WalletStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletStub(channel, callOptions);
    }

    /**
     */
    public void getAccount(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Account> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountById(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Account> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use CreateTransaction2 instead of this function.
     * </pre>
     */
    public void createTransaction(com.galileo.protos.protos.Contract.TransferContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateTransaction.
     * </pre>
     */
    public void createTransaction2(com.galileo.protos.protos.Contract.TransferContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTransaction2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void broadcastTransaction(com.galileo.protos.protos.Protocol.Transaction request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.Return> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBroadcastTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use UpdateAccount2 instead of this function.
     * </pre>
     */
    public void updateAccount(com.galileo.protos.protos.Contract.AccountUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setAccountId(com.galileo.protos.protos.Contract.SetAccountIdContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetAccountIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAccount.
     * </pre>
     */
    public void updateAccount2(com.galileo.protos.protos.Contract.AccountUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAccount2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use VoteWitnessAccount2 instead of this function.
     * </pre>
     */
    public void voteWitnessAccount(com.galileo.protos.protos.Contract.VoteWitnessContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteWitnessAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *modify the consume_user_resource_percent
     * </pre>
     */
    public void updateSetting(com.galileo.protos.protos.Contract.UpdateSettingContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateSettingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of VoteWitnessAccount.
     * </pre>
     */
    public void voteWitnessAccount2(com.galileo.protos.protos.Contract.VoteWitnessContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteWitnessAccount2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use CreateAssetIssue2 instead of this function.
     * </pre>
     */
    public void createAssetIssue(com.galileo.protos.protos.Contract.AssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAssetIssueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateAssetIssue.
     * </pre>
     */
    public void createAssetIssue2(com.galileo.protos.protos.Contract.AssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAssetIssue2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use UpdateWitness2 instead of this function.
     * </pre>
     */
    public void updateWitness(com.galileo.protos.protos.Contract.WitnessUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateWitnessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UpdateWitness.
     * </pre>
     */
    public void updateWitness2(com.galileo.protos.protos.Contract.WitnessUpdateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateWitness2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use CreateAccount2 instead of this function.
     * </pre>
     */
    public void createAccount(com.galileo.protos.protos.Contract.AccountCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateAccount.
     * </pre>
     */
    public void createAccount2(com.galileo.protos.protos.Contract.AccountCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAccount2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use CreateWitness2 instead of this function.
     * </pre>
     */
    public void createWitness(com.galileo.protos.protos.Contract.WitnessCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateWitnessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of CreateWitness.
     * </pre>
     */
    public void createWitness2(com.galileo.protos.protos.Contract.WitnessCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateWitness2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use TransferAsset2 instead of this function.
     * </pre>
     */
    public void transferAsset(com.galileo.protos.protos.Contract.TransferAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of TransferAsset.
     * </pre>
     */
    public void transferAsset2(com.galileo.protos.protos.Contract.TransferAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferAsset2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use ParticipateAssetIssue2 instead of this function.
     * </pre>
     */
    public void participateAssetIssue(com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParticipateAssetIssueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of ParticipateAssetIssue.
     * </pre>
     */
    public void participateAssetIssue2(com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParticipateAssetIssue2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use FreezeBalance2 instead of this function.
     * </pre>
     */
    public void freezeBalance(com.galileo.protos.protos.Contract.FreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFreezeBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of FreezeBalance.
     * </pre>
     */
    public void freezeBalance2(com.galileo.protos.protos.Contract.FreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFreezeBalance2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use UnfreezeBalance2 instead of this function.
     * </pre>
     */
    public void unfreezeBalance(com.galileo.protos.protos.Contract.UnfreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnfreezeBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeBalance.
     * </pre>
     */
    public void unfreezeBalance2(com.galileo.protos.protos.Contract.UnfreezeBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnfreezeBalance2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use UnfreezeAsset2 instead of this function.
     * </pre>
     */
    public void unfreezeAsset(com.galileo.protos.protos.Contract.UnfreezeAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnfreezeAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeAsset.
     * </pre>
     */
    public void unfreezeAsset2(com.galileo.protos.protos.Contract.UnfreezeAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnfreezeAsset2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use WithdrawBalance2 instead of this function.
     * </pre>
     */
    public void withdrawBalance(com.galileo.protos.protos.Contract.WithdrawBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of WithdrawBalance.
     * </pre>
     */
    public void withdrawBalance2(com.galileo.protos.protos.Contract.WithdrawBalanceContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawBalance2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use UpdateAsset2 instead of this function.
     * </pre>
     */
    public void updateAsset(com.galileo.protos.protos.Contract.UpdateAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAsset.
     * </pre>
     */
    public void updateAsset2(com.galileo.protos.protos.Contract.UpdateAssetContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAsset2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void proposalCreate(com.galileo.protos.protos.Contract.ProposalCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void proposalApprove(com.galileo.protos.protos.Contract.ProposalApproveContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalApproveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void proposalDelete(com.galileo.protos.protos.Contract.ProposalDeleteContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void buyStorage(com.galileo.protos.protos.Contract.BuyStorageContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyStorageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void buyStorageBytes(com.galileo.protos.protos.Contract.BuyStorageBytesContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyStorageBytesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sellStorage(com.galileo.protos.protos.Contract.SellStorageContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellStorageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exchangeCreate(com.galileo.protos.protos.Contract.ExchangeCreateContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exchangeInject(com.galileo.protos.protos.Contract.ExchangeInjectContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeInjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exchangeWithdraw(com.galileo.protos.protos.Contract.ExchangeWithdrawContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exchangeTransaction(com.galileo.protos.protos.Contract.ExchangeTransactionContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExchangeTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listNodes(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NodeList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListNodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAssetIssueByAccount(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetIssueByAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountNet(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AccountNetMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountNetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountResource(com.galileo.protos.protos.Protocol.Account request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AccountResourceMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountResourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAssetIssueByName(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Contract.AssetIssueContract> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetIssueByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use GetNowBlock2 instead of this function.
     * </pre>
     */
    public void getNowBlock(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNowBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetNowBlock.
     * </pre>
     */
    public void getNowBlock2(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNowBlock2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use GetBlockByNum2 instead of this function.
     * </pre>
     */
    public void getBlockByNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByNumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByNum.
     * </pre>
     */
    public void getBlockByNum2(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByNum2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransactionCountByBlockNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionCountByBlockNumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBlockById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use GetBlockByLimitNext2 instead of this function.
     * </pre>
     */
    public void getBlockByLimitNext(com.galileo.protos.api.GrpcAPI.BlockLimit request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByLimitNextMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLimitNext.
     * </pre>
     */
    public void getBlockByLimitNext2(com.galileo.protos.api.GrpcAPI.BlockLimit request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockListExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByLimitNext2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Please use GetBlockByLatestNum2 instead of this function.
     * </pre>
     */
    public void getBlockByLatestNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByLatestNumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLatestNum.
     * </pre>
     */
    public void getBlockByLatestNum2(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockListExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByLatestNum2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransactionById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deployContract(com.galileo.protos.protos.Contract.CreateSmartContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeployContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getContract(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.SmartContract> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void triggerContract(com.galileo.protos.protos.Contract.TriggerSmartContract request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTriggerContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listWitnesses(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.WitnessList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListWitnessesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listProposals(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ProposalList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListProposalsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPaginatedProposalList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ProposalList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPaginatedProposalListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProposalById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Proposal> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetProposalByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listExchanges(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ExchangeList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListExchangesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPaginatedExchangeList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ExchangeList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPaginatedExchangeListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getExchangeById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Exchange> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetExchangeByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getChainParameters(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.ChainParameters> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetChainParametersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAssetIssueList(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetIssueListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPaginatedAssetIssueList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPaginatedAssetIssueListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void totalTransaction(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNextMaintenanceTime(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNextMaintenanceTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Please use GetTransactionSign2 instead of this function.
     * </pre>
     */
    public void getTransactionSign(com.galileo.protos.protos.Protocol.TransactionSign request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionSignMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Use this function instead of GetTransactionSign.
     * </pre>
     */
    public void getTransactionSign2(com.galileo.protos.protos.Protocol.TransactionSign request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionSign2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void createAddress(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BytesMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void easyTransfer(com.galileo.protos.api.GrpcAPI.EasyTransferMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.EasyTransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEasyTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void easyTransferByPrivate(com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.EasyTransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEasyTransferByPrivateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public void generateAddress(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGenerateAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransactionInfoById(com.galileo.protos.api.GrpcAPI.BytesMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.TransactionInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionInfoByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WalletBlockingStub extends io.grpc.stub.AbstractStub<WalletBlockingStub> {
    private WalletBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Account getAccount(com.galileo.protos.protos.Protocol.Account request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Account getAccountById(com.galileo.protos.protos.Protocol.Account request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use CreateTransaction2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction createTransaction(com.galileo.protos.protos.Contract.TransferContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateTransaction.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention createTransaction2(com.galileo.protos.protos.Contract.TransferContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateTransaction2Method(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.Return broadcastTransaction(com.galileo.protos.protos.Protocol.Transaction request) {
      return blockingUnaryCall(
          getChannel(), getBroadcastTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use UpdateAccount2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction updateAccount(com.galileo.protos.protos.Contract.AccountUpdateContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Transaction setAccountId(com.galileo.protos.protos.Contract.SetAccountIdContract request) {
      return blockingUnaryCall(
          getChannel(), getSetAccountIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAccount.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention updateAccount2(com.galileo.protos.protos.Contract.AccountUpdateContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAccount2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use VoteWitnessAccount2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction voteWitnessAccount(com.galileo.protos.protos.Contract.VoteWitnessContract request) {
      return blockingUnaryCall(
          getChannel(), getVoteWitnessAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *modify the consume_user_resource_percent
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention updateSetting(com.galileo.protos.protos.Contract.UpdateSettingContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateSettingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of VoteWitnessAccount.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention voteWitnessAccount2(com.galileo.protos.protos.Contract.VoteWitnessContract request) {
      return blockingUnaryCall(
          getChannel(), getVoteWitnessAccount2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use CreateAssetIssue2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction createAssetIssue(com.galileo.protos.protos.Contract.AssetIssueContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateAssetIssueMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateAssetIssue.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention createAssetIssue2(com.galileo.protos.protos.Contract.AssetIssueContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateAssetIssue2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use UpdateWitness2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction updateWitness(com.galileo.protos.protos.Contract.WitnessUpdateContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateWitnessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of UpdateWitness.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention updateWitness2(com.galileo.protos.protos.Contract.WitnessUpdateContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateWitness2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use CreateAccount2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction createAccount(com.galileo.protos.protos.Contract.AccountCreateContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateAccount.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention createAccount2(com.galileo.protos.protos.Contract.AccountCreateContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateAccount2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use CreateWitness2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction createWitness(com.galileo.protos.protos.Contract.WitnessCreateContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateWitnessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateWitness.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention createWitness2(com.galileo.protos.protos.Contract.WitnessCreateContract request) {
      return blockingUnaryCall(
          getChannel(), getCreateWitness2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use TransferAsset2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction transferAsset(com.galileo.protos.protos.Contract.TransferAssetContract request) {
      return blockingUnaryCall(
          getChannel(), getTransferAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of TransferAsset.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention transferAsset2(com.galileo.protos.protos.Contract.TransferAssetContract request) {
      return blockingUnaryCall(
          getChannel(), getTransferAsset2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use ParticipateAssetIssue2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction participateAssetIssue(com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request) {
      return blockingUnaryCall(
          getChannel(), getParticipateAssetIssueMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of ParticipateAssetIssue.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention participateAssetIssue2(com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request) {
      return blockingUnaryCall(
          getChannel(), getParticipateAssetIssue2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use FreezeBalance2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction freezeBalance(com.galileo.protos.protos.Contract.FreezeBalanceContract request) {
      return blockingUnaryCall(
          getChannel(), getFreezeBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of FreezeBalance.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention freezeBalance2(com.galileo.protos.protos.Contract.FreezeBalanceContract request) {
      return blockingUnaryCall(
          getChannel(), getFreezeBalance2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use UnfreezeBalance2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction unfreezeBalance(com.galileo.protos.protos.Contract.UnfreezeBalanceContract request) {
      return blockingUnaryCall(
          getChannel(), getUnfreezeBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeBalance.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention unfreezeBalance2(com.galileo.protos.protos.Contract.UnfreezeBalanceContract request) {
      return blockingUnaryCall(
          getChannel(), getUnfreezeBalance2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use UnfreezeAsset2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction unfreezeAsset(com.galileo.protos.protos.Contract.UnfreezeAssetContract request) {
      return blockingUnaryCall(
          getChannel(), getUnfreezeAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeAsset.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention unfreezeAsset2(com.galileo.protos.protos.Contract.UnfreezeAssetContract request) {
      return blockingUnaryCall(
          getChannel(), getUnfreezeAsset2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use WithdrawBalance2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction withdrawBalance(com.galileo.protos.protos.Contract.WithdrawBalanceContract request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of WithdrawBalance.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention withdrawBalance2(com.galileo.protos.protos.Contract.WithdrawBalanceContract request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawBalance2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use UpdateAsset2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction updateAsset(com.galileo.protos.protos.Contract.UpdateAssetContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAsset.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention updateAsset2(com.galileo.protos.protos.Contract.UpdateAssetContract request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAsset2Method(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention proposalCreate(com.galileo.protos.protos.Contract.ProposalCreateContract request) {
      return blockingUnaryCall(
          getChannel(), getProposalCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention proposalApprove(com.galileo.protos.protos.Contract.ProposalApproveContract request) {
      return blockingUnaryCall(
          getChannel(), getProposalApproveMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention proposalDelete(com.galileo.protos.protos.Contract.ProposalDeleteContract request) {
      return blockingUnaryCall(
          getChannel(), getProposalDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention buyStorage(com.galileo.protos.protos.Contract.BuyStorageContract request) {
      return blockingUnaryCall(
          getChannel(), getBuyStorageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention buyStorageBytes(com.galileo.protos.protos.Contract.BuyStorageBytesContract request) {
      return blockingUnaryCall(
          getChannel(), getBuyStorageBytesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention sellStorage(com.galileo.protos.protos.Contract.SellStorageContract request) {
      return blockingUnaryCall(
          getChannel(), getSellStorageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention exchangeCreate(com.galileo.protos.protos.Contract.ExchangeCreateContract request) {
      return blockingUnaryCall(
          getChannel(), getExchangeCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention exchangeInject(com.galileo.protos.protos.Contract.ExchangeInjectContract request) {
      return blockingUnaryCall(
          getChannel(), getExchangeInjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention exchangeWithdraw(com.galileo.protos.protos.Contract.ExchangeWithdrawContract request) {
      return blockingUnaryCall(
          getChannel(), getExchangeWithdrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention exchangeTransaction(com.galileo.protos.protos.Contract.ExchangeTransactionContract request) {
      return blockingUnaryCall(
          getChannel(), getExchangeTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.NodeList listNodes(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getListNodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.AssetIssueList getAssetIssueByAccount(com.galileo.protos.protos.Protocol.Account request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetIssueByAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.AccountNetMessage getAccountNet(com.galileo.protos.protos.Protocol.Account request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountNetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.AccountResourceMessage getAccountResource(com.galileo.protos.protos.Protocol.Account request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountResourceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Contract.AssetIssueContract getAssetIssueByName(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetIssueByNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use GetNowBlock2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Block getNowBlock(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetNowBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of GetNowBlock.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockExtention getNowBlock2(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetNowBlock2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use GetBlockByNum2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Block getBlockByNum(com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByNumMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByNum.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockExtention getBlockByNum2(com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByNum2Method(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.NumberMessage getTransactionCountByBlockNum(com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionCountByBlockNumMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Block getBlockById(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use GetBlockByLimitNext2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockList getBlockByLimitNext(com.galileo.protos.api.GrpcAPI.BlockLimit request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByLimitNextMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLimitNext.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockListExtention getBlockByLimitNext2(com.galileo.protos.api.GrpcAPI.BlockLimit request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByLimitNext2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Please use GetBlockByLatestNum2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockList getBlockByLatestNum(com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByLatestNumMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLatestNum.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockListExtention getBlockByLatestNum2(com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByLatestNum2Method(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Transaction getTransactionById(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention deployContract(com.galileo.protos.protos.Contract.CreateSmartContract request) {
      return blockingUnaryCall(
          getChannel(), getDeployContractMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.SmartContract getContract(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetContractMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention triggerContract(com.galileo.protos.protos.Contract.TriggerSmartContract request) {
      return blockingUnaryCall(
          getChannel(), getTriggerContractMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.WitnessList listWitnesses(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getListWitnessesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.ProposalList listProposals(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getListProposalsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.ProposalList getPaginatedProposalList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetPaginatedProposalListMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Proposal getProposalById(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetProposalByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.ExchangeList listExchanges(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getListExchangesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.ExchangeList getPaginatedExchangeList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetPaginatedExchangeListMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Exchange getExchangeById(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetExchangeByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.ChainParameters getChainParameters(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetChainParametersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.AssetIssueList getAssetIssueList(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetIssueListMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.AssetIssueList getPaginatedAssetIssueList(com.galileo.protos.api.GrpcAPI.PaginatedMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetPaginatedAssetIssueListMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.NumberMessage totalTransaction(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getTotalTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.api.GrpcAPI.NumberMessage getNextMaintenanceTime(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetNextMaintenanceTimeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Please use GetTransactionSign2 instead of this function.
     * </pre>
     */
    public com.galileo.protos.protos.Protocol.Transaction getTransactionSign(com.galileo.protos.protos.Protocol.TransactionSign request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionSignMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Use this function instead of GetTransactionSign.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.TransactionExtention getTransactionSign2(com.galileo.protos.protos.Protocol.TransactionSign request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionSign2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BytesMessage createAddress(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getCreateAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.EasyTransferResponse easyTransfer(com.galileo.protos.api.GrpcAPI.EasyTransferMessage request) {
      return blockingUnaryCall(
          getChannel(), getEasyTransferMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.EasyTransferResponse easyTransferByPrivate(com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage request) {
      return blockingUnaryCall(
          getChannel(), getEasyTransferByPrivateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage generateAddress(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGenerateAddressMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.TransactionInfo getTransactionInfoById(com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionInfoByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WalletFutureStub extends io.grpc.stub.AbstractStub<WalletFutureStub> {
    private WalletFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Account> getAccount(
        com.galileo.protos.protos.Protocol.Account request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Account> getAccountById(
        com.galileo.protos.protos.Protocol.Account request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use CreateTransaction2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> createTransaction(
        com.galileo.protos.protos.Contract.TransferContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateTransaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> createTransaction2(
        com.galileo.protos.protos.Contract.TransferContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTransaction2Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.Return> broadcastTransaction(
        com.galileo.protos.protos.Protocol.Transaction request) {
      return futureUnaryCall(
          getChannel().newCall(getBroadcastTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use UpdateAccount2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> updateAccount(
        com.galileo.protos.protos.Contract.AccountUpdateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> setAccountId(
        com.galileo.protos.protos.Contract.SetAccountIdContract request) {
      return futureUnaryCall(
          getChannel().newCall(getSetAccountIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAccount.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> updateAccount2(
        com.galileo.protos.protos.Contract.AccountUpdateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAccount2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use VoteWitnessAccount2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> voteWitnessAccount(
        com.galileo.protos.protos.Contract.VoteWitnessContract request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteWitnessAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *modify the consume_user_resource_percent
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> updateSetting(
        com.galileo.protos.protos.Contract.UpdateSettingContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateSettingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of VoteWitnessAccount.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> voteWitnessAccount2(
        com.galileo.protos.protos.Contract.VoteWitnessContract request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteWitnessAccount2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use CreateAssetIssue2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> createAssetIssue(
        com.galileo.protos.protos.Contract.AssetIssueContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAssetIssueMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateAssetIssue.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> createAssetIssue2(
        com.galileo.protos.protos.Contract.AssetIssueContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAssetIssue2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use UpdateWitness2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> updateWitness(
        com.galileo.protos.protos.Contract.WitnessUpdateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateWitnessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of UpdateWitness.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> updateWitness2(
        com.galileo.protos.protos.Contract.WitnessUpdateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateWitness2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use CreateAccount2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> createAccount(
        com.galileo.protos.protos.Contract.AccountCreateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateAccount.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> createAccount2(
        com.galileo.protos.protos.Contract.AccountCreateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAccount2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use CreateWitness2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> createWitness(
        com.galileo.protos.protos.Contract.WitnessCreateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateWitnessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of CreateWitness.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> createWitness2(
        com.galileo.protos.protos.Contract.WitnessCreateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateWitness2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use TransferAsset2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> transferAsset(
        com.galileo.protos.protos.Contract.TransferAssetContract request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of TransferAsset.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> transferAsset2(
        com.galileo.protos.protos.Contract.TransferAssetContract request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferAsset2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use ParticipateAssetIssue2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> participateAssetIssue(
        com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request) {
      return futureUnaryCall(
          getChannel().newCall(getParticipateAssetIssueMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of ParticipateAssetIssue.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> participateAssetIssue2(
        com.galileo.protos.protos.Contract.ParticipateAssetIssueContract request) {
      return futureUnaryCall(
          getChannel().newCall(getParticipateAssetIssue2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use FreezeBalance2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> freezeBalance(
        com.galileo.protos.protos.Contract.FreezeBalanceContract request) {
      return futureUnaryCall(
          getChannel().newCall(getFreezeBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of FreezeBalance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> freezeBalance2(
        com.galileo.protos.protos.Contract.FreezeBalanceContract request) {
      return futureUnaryCall(
          getChannel().newCall(getFreezeBalance2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use UnfreezeBalance2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> unfreezeBalance(
        com.galileo.protos.protos.Contract.UnfreezeBalanceContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUnfreezeBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeBalance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> unfreezeBalance2(
        com.galileo.protos.protos.Contract.UnfreezeBalanceContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUnfreezeBalance2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use UnfreezeAsset2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> unfreezeAsset(
        com.galileo.protos.protos.Contract.UnfreezeAssetContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUnfreezeAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of UnfreezeAsset.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> unfreezeAsset2(
        com.galileo.protos.protos.Contract.UnfreezeAssetContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUnfreezeAsset2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use WithdrawBalance2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> withdrawBalance(
        com.galileo.protos.protos.Contract.WithdrawBalanceContract request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of WithdrawBalance.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> withdrawBalance2(
        com.galileo.protos.protos.Contract.WithdrawBalanceContract request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawBalance2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use UpdateAsset2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> updateAsset(
        com.galileo.protos.protos.Contract.UpdateAssetContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of UpdateAsset.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> updateAsset2(
        com.galileo.protos.protos.Contract.UpdateAssetContract request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAsset2Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> proposalCreate(
        com.galileo.protos.protos.Contract.ProposalCreateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> proposalApprove(
        com.galileo.protos.protos.Contract.ProposalApproveContract request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalApproveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> proposalDelete(
        com.galileo.protos.protos.Contract.ProposalDeleteContract request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> buyStorage(
        com.galileo.protos.protos.Contract.BuyStorageContract request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyStorageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> buyStorageBytes(
        com.galileo.protos.protos.Contract.BuyStorageBytesContract request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyStorageBytesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> sellStorage(
        com.galileo.protos.protos.Contract.SellStorageContract request) {
      return futureUnaryCall(
          getChannel().newCall(getSellStorageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> exchangeCreate(
        com.galileo.protos.protos.Contract.ExchangeCreateContract request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> exchangeInject(
        com.galileo.protos.protos.Contract.ExchangeInjectContract request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeInjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> exchangeWithdraw(
        com.galileo.protos.protos.Contract.ExchangeWithdrawContract request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeWithdrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> exchangeTransaction(
        com.galileo.protos.protos.Contract.ExchangeTransactionContract request) {
      return futureUnaryCall(
          getChannel().newCall(getExchangeTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.NodeList> listNodes(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getListNodesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.AssetIssueList> getAssetIssueByAccount(
        com.galileo.protos.protos.Protocol.Account request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetIssueByAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.AccountNetMessage> getAccountNet(
        com.galileo.protos.protos.Protocol.Account request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountNetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.AccountResourceMessage> getAccountResource(
        com.galileo.protos.protos.Protocol.Account request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountResourceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Contract.AssetIssueContract> getAssetIssueByName(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetIssueByNameMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use GetNowBlock2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Block> getNowBlock(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNowBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of GetNowBlock.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockExtention> getNowBlock2(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNowBlock2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use GetBlockByNum2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Block> getBlockByNum(
        com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByNumMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByNum.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockExtention> getBlockByNum2(
        com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByNum2Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.NumberMessage> getTransactionCountByBlockNum(
        com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionCountByBlockNumMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Block> getBlockById(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use GetBlockByLimitNext2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockList> getBlockByLimitNext(
        com.galileo.protos.api.GrpcAPI.BlockLimit request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByLimitNextMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLimitNext.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockListExtention> getBlockByLimitNext2(
        com.galileo.protos.api.GrpcAPI.BlockLimit request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByLimitNext2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Please use GetBlockByLatestNum2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockList> getBlockByLatestNum(
        com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByLatestNumMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Use this function instead of GetBlockByLatestNum.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockListExtention> getBlockByLatestNum2(
        com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByLatestNum2Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> getTransactionById(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> deployContract(
        com.galileo.protos.protos.Contract.CreateSmartContract request) {
      return futureUnaryCall(
          getChannel().newCall(getDeployContractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.SmartContract> getContract(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetContractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> triggerContract(
        com.galileo.protos.protos.Contract.TriggerSmartContract request) {
      return futureUnaryCall(
          getChannel().newCall(getTriggerContractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.WitnessList> listWitnesses(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getListWitnessesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.ProposalList> listProposals(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getListProposalsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.ProposalList> getPaginatedProposalList(
        com.galileo.protos.api.GrpcAPI.PaginatedMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPaginatedProposalListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Proposal> getProposalById(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetProposalByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.ExchangeList> listExchanges(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getListExchangesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.ExchangeList> getPaginatedExchangeList(
        com.galileo.protos.api.GrpcAPI.PaginatedMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPaginatedExchangeListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Exchange> getExchangeById(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetExchangeByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.ChainParameters> getChainParameters(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetChainParametersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.AssetIssueList> getAssetIssueList(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetIssueListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.AssetIssueList> getPaginatedAssetIssueList(
        com.galileo.protos.api.GrpcAPI.PaginatedMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPaginatedAssetIssueListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.NumberMessage> totalTransaction(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.NumberMessage> getNextMaintenanceTime(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNextMaintenanceTimeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Please use GetTransactionSign2 instead of this function.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Transaction> getTransactionSign(
        com.galileo.protos.protos.Protocol.TransactionSign request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionSignMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     *Use this function instead of GetTransactionSign.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.TransactionExtention> getTransactionSign2(
        com.galileo.protos.protos.Protocol.TransactionSign request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionSign2Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BytesMessage> createAddress(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.EasyTransferResponse> easyTransfer(
        com.galileo.protos.api.GrpcAPI.EasyTransferMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getEasyTransferMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.EasyTransferResponse> easyTransferByPrivate(
        com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getEasyTransferByPrivateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Warning: do not invoke this interface provided by others.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage> generateAddress(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGenerateAddressMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.TransactionInfo> getTransactionInfoById(
        com.galileo.protos.api.GrpcAPI.BytesMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionInfoByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ACCOUNT = 0;
  private static final int METHODID_GET_ACCOUNT_BY_ID = 1;
  private static final int METHODID_CREATE_TRANSACTION = 2;
  private static final int METHODID_CREATE_TRANSACTION2 = 3;
  private static final int METHODID_BROADCAST_TRANSACTION = 4;
  private static final int METHODID_UPDATE_ACCOUNT = 5;
  private static final int METHODID_SET_ACCOUNT_ID = 6;
  private static final int METHODID_UPDATE_ACCOUNT2 = 7;
  private static final int METHODID_VOTE_WITNESS_ACCOUNT = 8;
  private static final int METHODID_UPDATE_SETTING = 9;
  private static final int METHODID_VOTE_WITNESS_ACCOUNT2 = 10;
  private static final int METHODID_CREATE_ASSET_ISSUE = 11;
  private static final int METHODID_CREATE_ASSET_ISSUE2 = 12;
  private static final int METHODID_UPDATE_WITNESS = 13;
  private static final int METHODID_UPDATE_WITNESS2 = 14;
  private static final int METHODID_CREATE_ACCOUNT = 15;
  private static final int METHODID_CREATE_ACCOUNT2 = 16;
  private static final int METHODID_CREATE_WITNESS = 17;
  private static final int METHODID_CREATE_WITNESS2 = 18;
  private static final int METHODID_TRANSFER_ASSET = 19;
  private static final int METHODID_TRANSFER_ASSET2 = 20;
  private static final int METHODID_PARTICIPATE_ASSET_ISSUE = 21;
  private static final int METHODID_PARTICIPATE_ASSET_ISSUE2 = 22;
  private static final int METHODID_FREEZE_BALANCE = 23;
  private static final int METHODID_FREEZE_BALANCE2 = 24;
  private static final int METHODID_UNFREEZE_BALANCE = 25;
  private static final int METHODID_UNFREEZE_BALANCE2 = 26;
  private static final int METHODID_UNFREEZE_ASSET = 27;
  private static final int METHODID_UNFREEZE_ASSET2 = 28;
  private static final int METHODID_WITHDRAW_BALANCE = 29;
  private static final int METHODID_WITHDRAW_BALANCE2 = 30;
  private static final int METHODID_UPDATE_ASSET = 31;
  private static final int METHODID_UPDATE_ASSET2 = 32;
  private static final int METHODID_PROPOSAL_CREATE = 33;
  private static final int METHODID_PROPOSAL_APPROVE = 34;
  private static final int METHODID_PROPOSAL_DELETE = 35;
  private static final int METHODID_BUY_STORAGE = 36;
  private static final int METHODID_BUY_STORAGE_BYTES = 37;
  private static final int METHODID_SELL_STORAGE = 38;
  private static final int METHODID_EXCHANGE_CREATE = 39;
  private static final int METHODID_EXCHANGE_INJECT = 40;
  private static final int METHODID_EXCHANGE_WITHDRAW = 41;
  private static final int METHODID_EXCHANGE_TRANSACTION = 42;
  private static final int METHODID_LIST_NODES = 43;
  private static final int METHODID_GET_ASSET_ISSUE_BY_ACCOUNT = 44;
  private static final int METHODID_GET_ACCOUNT_NET = 45;
  private static final int METHODID_GET_ACCOUNT_RESOURCE = 46;
  private static final int METHODID_GET_ASSET_ISSUE_BY_NAME = 47;
  private static final int METHODID_GET_NOW_BLOCK = 48;
  private static final int METHODID_GET_NOW_BLOCK2 = 49;
  private static final int METHODID_GET_BLOCK_BY_NUM = 50;
  private static final int METHODID_GET_BLOCK_BY_NUM2 = 51;
  private static final int METHODID_GET_TRANSACTION_COUNT_BY_BLOCK_NUM = 52;
  private static final int METHODID_GET_BLOCK_BY_ID = 53;
  private static final int METHODID_GET_BLOCK_BY_LIMIT_NEXT = 54;
  private static final int METHODID_GET_BLOCK_BY_LIMIT_NEXT2 = 55;
  private static final int METHODID_GET_BLOCK_BY_LATEST_NUM = 56;
  private static final int METHODID_GET_BLOCK_BY_LATEST_NUM2 = 57;
  private static final int METHODID_GET_TRANSACTION_BY_ID = 58;
  private static final int METHODID_DEPLOY_CONTRACT = 59;
  private static final int METHODID_GET_CONTRACT = 60;
  private static final int METHODID_TRIGGER_CONTRACT = 61;
  private static final int METHODID_LIST_WITNESSES = 62;
  private static final int METHODID_LIST_PROPOSALS = 63;
  private static final int METHODID_GET_PAGINATED_PROPOSAL_LIST = 64;
  private static final int METHODID_GET_PROPOSAL_BY_ID = 65;
  private static final int METHODID_LIST_EXCHANGES = 66;
  private static final int METHODID_GET_PAGINATED_EXCHANGE_LIST = 67;
  private static final int METHODID_GET_EXCHANGE_BY_ID = 68;
  private static final int METHODID_GET_CHAIN_PARAMETERS = 69;
  private static final int METHODID_GET_ASSET_ISSUE_LIST = 70;
  private static final int METHODID_GET_PAGINATED_ASSET_ISSUE_LIST = 71;
  private static final int METHODID_TOTAL_TRANSACTION = 72;
  private static final int METHODID_GET_NEXT_MAINTENANCE_TIME = 73;
  private static final int METHODID_GET_TRANSACTION_SIGN = 74;
  private static final int METHODID_GET_TRANSACTION_SIGN2 = 75;
  private static final int METHODID_CREATE_ADDRESS = 76;
  private static final int METHODID_EASY_TRANSFER = 77;
  private static final int METHODID_EASY_TRANSFER_BY_PRIVATE = 78;
  private static final int METHODID_GENERATE_ADDRESS = 79;
  private static final int METHODID_GET_TRANSACTION_INFO_BY_ID = 80;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WalletImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WalletImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ACCOUNT:
          serviceImpl.getAccount((com.galileo.protos.protos.Protocol.Account) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Account>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_BY_ID:
          serviceImpl.getAccountById((com.galileo.protos.protos.Protocol.Account) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Account>) responseObserver);
          break;
        case METHODID_CREATE_TRANSACTION:
          serviceImpl.createTransaction((com.galileo.protos.protos.Contract.TransferContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_CREATE_TRANSACTION2:
          serviceImpl.createTransaction2((com.galileo.protos.protos.Contract.TransferContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_BROADCAST_TRANSACTION:
          serviceImpl.broadcastTransaction((com.galileo.protos.protos.Protocol.Transaction) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.Return>) responseObserver);
          break;
        case METHODID_UPDATE_ACCOUNT:
          serviceImpl.updateAccount((com.galileo.protos.protos.Contract.AccountUpdateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_SET_ACCOUNT_ID:
          serviceImpl.setAccountId((com.galileo.protos.protos.Contract.SetAccountIdContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_UPDATE_ACCOUNT2:
          serviceImpl.updateAccount2((com.galileo.protos.protos.Contract.AccountUpdateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_VOTE_WITNESS_ACCOUNT:
          serviceImpl.voteWitnessAccount((com.galileo.protos.protos.Contract.VoteWitnessContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_UPDATE_SETTING:
          serviceImpl.updateSetting((com.galileo.protos.protos.Contract.UpdateSettingContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_VOTE_WITNESS_ACCOUNT2:
          serviceImpl.voteWitnessAccount2((com.galileo.protos.protos.Contract.VoteWitnessContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_CREATE_ASSET_ISSUE:
          serviceImpl.createAssetIssue((com.galileo.protos.protos.Contract.AssetIssueContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_CREATE_ASSET_ISSUE2:
          serviceImpl.createAssetIssue2((com.galileo.protos.protos.Contract.AssetIssueContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_UPDATE_WITNESS:
          serviceImpl.updateWitness((com.galileo.protos.protos.Contract.WitnessUpdateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_UPDATE_WITNESS2:
          serviceImpl.updateWitness2((com.galileo.protos.protos.Contract.WitnessUpdateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_CREATE_ACCOUNT:
          serviceImpl.createAccount((com.galileo.protos.protos.Contract.AccountCreateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_CREATE_ACCOUNT2:
          serviceImpl.createAccount2((com.galileo.protos.protos.Contract.AccountCreateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_CREATE_WITNESS:
          serviceImpl.createWitness((com.galileo.protos.protos.Contract.WitnessCreateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_CREATE_WITNESS2:
          serviceImpl.createWitness2((com.galileo.protos.protos.Contract.WitnessCreateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_TRANSFER_ASSET:
          serviceImpl.transferAsset((com.galileo.protos.protos.Contract.TransferAssetContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_TRANSFER_ASSET2:
          serviceImpl.transferAsset2((com.galileo.protos.protos.Contract.TransferAssetContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_PARTICIPATE_ASSET_ISSUE:
          serviceImpl.participateAssetIssue((com.galileo.protos.protos.Contract.ParticipateAssetIssueContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_PARTICIPATE_ASSET_ISSUE2:
          serviceImpl.participateAssetIssue2((com.galileo.protos.protos.Contract.ParticipateAssetIssueContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_FREEZE_BALANCE:
          serviceImpl.freezeBalance((com.galileo.protos.protos.Contract.FreezeBalanceContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_FREEZE_BALANCE2:
          serviceImpl.freezeBalance2((com.galileo.protos.protos.Contract.FreezeBalanceContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_UNFREEZE_BALANCE:
          serviceImpl.unfreezeBalance((com.galileo.protos.protos.Contract.UnfreezeBalanceContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_UNFREEZE_BALANCE2:
          serviceImpl.unfreezeBalance2((com.galileo.protos.protos.Contract.UnfreezeBalanceContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_UNFREEZE_ASSET:
          serviceImpl.unfreezeAsset((com.galileo.protos.protos.Contract.UnfreezeAssetContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_UNFREEZE_ASSET2:
          serviceImpl.unfreezeAsset2((com.galileo.protos.protos.Contract.UnfreezeAssetContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_WITHDRAW_BALANCE:
          serviceImpl.withdrawBalance((com.galileo.protos.protos.Contract.WithdrawBalanceContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_WITHDRAW_BALANCE2:
          serviceImpl.withdrawBalance2((com.galileo.protos.protos.Contract.WithdrawBalanceContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_UPDATE_ASSET:
          serviceImpl.updateAsset((com.galileo.protos.protos.Contract.UpdateAssetContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_UPDATE_ASSET2:
          serviceImpl.updateAsset2((com.galileo.protos.protos.Contract.UpdateAssetContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_PROPOSAL_CREATE:
          serviceImpl.proposalCreate((com.galileo.protos.protos.Contract.ProposalCreateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_PROPOSAL_APPROVE:
          serviceImpl.proposalApprove((com.galileo.protos.protos.Contract.ProposalApproveContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_PROPOSAL_DELETE:
          serviceImpl.proposalDelete((com.galileo.protos.protos.Contract.ProposalDeleteContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_BUY_STORAGE:
          serviceImpl.buyStorage((com.galileo.protos.protos.Contract.BuyStorageContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_BUY_STORAGE_BYTES:
          serviceImpl.buyStorageBytes((com.galileo.protos.protos.Contract.BuyStorageBytesContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_SELL_STORAGE:
          serviceImpl.sellStorage((com.galileo.protos.protos.Contract.SellStorageContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_EXCHANGE_CREATE:
          serviceImpl.exchangeCreate((com.galileo.protos.protos.Contract.ExchangeCreateContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_EXCHANGE_INJECT:
          serviceImpl.exchangeInject((com.galileo.protos.protos.Contract.ExchangeInjectContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_EXCHANGE_WITHDRAW:
          serviceImpl.exchangeWithdraw((com.galileo.protos.protos.Contract.ExchangeWithdrawContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_EXCHANGE_TRANSACTION:
          serviceImpl.exchangeTransaction((com.galileo.protos.protos.Contract.ExchangeTransactionContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_LIST_NODES:
          serviceImpl.listNodes((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NodeList>) responseObserver);
          break;
        case METHODID_GET_ASSET_ISSUE_BY_ACCOUNT:
          serviceImpl.getAssetIssueByAccount((com.galileo.protos.protos.Protocol.Account) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_NET:
          serviceImpl.getAccountNet((com.galileo.protos.protos.Protocol.Account) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AccountNetMessage>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_RESOURCE:
          serviceImpl.getAccountResource((com.galileo.protos.protos.Protocol.Account) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AccountResourceMessage>) responseObserver);
          break;
        case METHODID_GET_ASSET_ISSUE_BY_NAME:
          serviceImpl.getAssetIssueByName((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Contract.AssetIssueContract>) responseObserver);
          break;
        case METHODID_GET_NOW_BLOCK:
          serviceImpl.getNowBlock((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block>) responseObserver);
          break;
        case METHODID_GET_NOW_BLOCK2:
          serviceImpl.getNowBlock2((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockExtention>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_NUM:
          serviceImpl.getBlockByNum((com.galileo.protos.api.GrpcAPI.NumberMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_NUM2:
          serviceImpl.getBlockByNum2((com.galileo.protos.api.GrpcAPI.NumberMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockExtention>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_COUNT_BY_BLOCK_NUM:
          serviceImpl.getTransactionCountByBlockNum((com.galileo.protos.api.GrpcAPI.NumberMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_ID:
          serviceImpl.getBlockById((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_LIMIT_NEXT:
          serviceImpl.getBlockByLimitNext((com.galileo.protos.api.GrpcAPI.BlockLimit) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockList>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_LIMIT_NEXT2:
          serviceImpl.getBlockByLimitNext2((com.galileo.protos.api.GrpcAPI.BlockLimit) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockListExtention>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_LATEST_NUM:
          serviceImpl.getBlockByLatestNum((com.galileo.protos.api.GrpcAPI.NumberMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockList>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_LATEST_NUM2:
          serviceImpl.getBlockByLatestNum2((com.galileo.protos.api.GrpcAPI.NumberMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockListExtention>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_BY_ID:
          serviceImpl.getTransactionById((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_DEPLOY_CONTRACT:
          serviceImpl.deployContract((com.galileo.protos.protos.Contract.CreateSmartContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_GET_CONTRACT:
          serviceImpl.getContract((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.SmartContract>) responseObserver);
          break;
        case METHODID_TRIGGER_CONTRACT:
          serviceImpl.triggerContract((com.galileo.protos.protos.Contract.TriggerSmartContract) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_LIST_WITNESSES:
          serviceImpl.listWitnesses((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.WitnessList>) responseObserver);
          break;
        case METHODID_LIST_PROPOSALS:
          serviceImpl.listProposals((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ProposalList>) responseObserver);
          break;
        case METHODID_GET_PAGINATED_PROPOSAL_LIST:
          serviceImpl.getPaginatedProposalList((com.galileo.protos.api.GrpcAPI.PaginatedMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ProposalList>) responseObserver);
          break;
        case METHODID_GET_PROPOSAL_BY_ID:
          serviceImpl.getProposalById((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Proposal>) responseObserver);
          break;
        case METHODID_LIST_EXCHANGES:
          serviceImpl.listExchanges((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ExchangeList>) responseObserver);
          break;
        case METHODID_GET_PAGINATED_EXCHANGE_LIST:
          serviceImpl.getPaginatedExchangeList((com.galileo.protos.api.GrpcAPI.PaginatedMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.ExchangeList>) responseObserver);
          break;
        case METHODID_GET_EXCHANGE_BY_ID:
          serviceImpl.getExchangeById((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Exchange>) responseObserver);
          break;
        case METHODID_GET_CHAIN_PARAMETERS:
          serviceImpl.getChainParameters((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.ChainParameters>) responseObserver);
          break;
        case METHODID_GET_ASSET_ISSUE_LIST:
          serviceImpl.getAssetIssueList((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList>) responseObserver);
          break;
        case METHODID_GET_PAGINATED_ASSET_ISSUE_LIST:
          serviceImpl.getPaginatedAssetIssueList((com.galileo.protos.api.GrpcAPI.PaginatedMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AssetIssueList>) responseObserver);
          break;
        case METHODID_TOTAL_TRANSACTION:
          serviceImpl.totalTransaction((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage>) responseObserver);
          break;
        case METHODID_GET_NEXT_MAINTENANCE_TIME:
          serviceImpl.getNextMaintenanceTime((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.NumberMessage>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_SIGN:
          serviceImpl.getTransactionSign((com.galileo.protos.protos.Protocol.TransactionSign) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Transaction>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_SIGN2:
          serviceImpl.getTransactionSign2((com.galileo.protos.protos.Protocol.TransactionSign) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.TransactionExtention>) responseObserver);
          break;
        case METHODID_CREATE_ADDRESS:
          serviceImpl.createAddress((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BytesMessage>) responseObserver);
          break;
        case METHODID_EASY_TRANSFER:
          serviceImpl.easyTransfer((com.galileo.protos.api.GrpcAPI.EasyTransferMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.EasyTransferResponse>) responseObserver);
          break;
        case METHODID_EASY_TRANSFER_BY_PRIVATE:
          serviceImpl.easyTransferByPrivate((com.galileo.protos.api.GrpcAPI.EasyTransferByPrivateMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.EasyTransferResponse>) responseObserver);
          break;
        case METHODID_GENERATE_ADDRESS:
          serviceImpl.generateAddress((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.AddressPrKeyPairMessage>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_INFO_BY_ID:
          serviceImpl.getTransactionInfoById((com.galileo.protos.api.GrpcAPI.BytesMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.TransactionInfo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class WalletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WalletBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.galileo.protos.api.GrpcAPI.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Wallet");
    }
  }

  private static final class WalletFileDescriptorSupplier
      extends WalletBaseDescriptorSupplier {
    WalletFileDescriptorSupplier() {}
  }

  private static final class WalletMethodDescriptorSupplier
      extends WalletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WalletMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WalletGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WalletFileDescriptorSupplier())
              .addMethod(getGetAccountMethod())
              .addMethod(getGetAccountByIdMethod())
              .addMethod(getCreateTransactionMethod())
              .addMethod(getCreateTransaction2Method())
              .addMethod(getBroadcastTransactionMethod())
              .addMethod(getUpdateAccountMethod())
              .addMethod(getSetAccountIdMethod())
              .addMethod(getUpdateAccount2Method())
              .addMethod(getVoteWitnessAccountMethod())
              .addMethod(getUpdateSettingMethod())
              .addMethod(getVoteWitnessAccount2Method())
              .addMethod(getCreateAssetIssueMethod())
              .addMethod(getCreateAssetIssue2Method())
              .addMethod(getUpdateWitnessMethod())
              .addMethod(getUpdateWitness2Method())
              .addMethod(getCreateAccountMethod())
              .addMethod(getCreateAccount2Method())
              .addMethod(getCreateWitnessMethod())
              .addMethod(getCreateWitness2Method())
              .addMethod(getTransferAssetMethod())
              .addMethod(getTransferAsset2Method())
              .addMethod(getParticipateAssetIssueMethod())
              .addMethod(getParticipateAssetIssue2Method())
              .addMethod(getFreezeBalanceMethod())
              .addMethod(getFreezeBalance2Method())
              .addMethod(getUnfreezeBalanceMethod())
              .addMethod(getUnfreezeBalance2Method())
              .addMethod(getUnfreezeAssetMethod())
              .addMethod(getUnfreezeAsset2Method())
              .addMethod(getWithdrawBalanceMethod())
              .addMethod(getWithdrawBalance2Method())
              .addMethod(getUpdateAssetMethod())
              .addMethod(getUpdateAsset2Method())
              .addMethod(getProposalCreateMethod())
              .addMethod(getProposalApproveMethod())
              .addMethod(getProposalDeleteMethod())
              .addMethod(getBuyStorageMethod())
              .addMethod(getBuyStorageBytesMethod())
              .addMethod(getSellStorageMethod())
              .addMethod(getExchangeCreateMethod())
              .addMethod(getExchangeInjectMethod())
              .addMethod(getExchangeWithdrawMethod())
              .addMethod(getExchangeTransactionMethod())
              .addMethod(getListNodesMethod())
              .addMethod(getGetAssetIssueByAccountMethod())
              .addMethod(getGetAccountNetMethod())
              .addMethod(getGetAccountResourceMethod())
              .addMethod(getGetAssetIssueByNameMethod())
              .addMethod(getGetNowBlockMethod())
              .addMethod(getGetNowBlock2Method())
              .addMethod(getGetBlockByNumMethod())
              .addMethod(getGetBlockByNum2Method())
              .addMethod(getGetTransactionCountByBlockNumMethod())
              .addMethod(getGetBlockByIdMethod())
              .addMethod(getGetBlockByLimitNextMethod())
              .addMethod(getGetBlockByLimitNext2Method())
              .addMethod(getGetBlockByLatestNumMethod())
              .addMethod(getGetBlockByLatestNum2Method())
              .addMethod(getGetTransactionByIdMethod())
              .addMethod(getDeployContractMethod())
              .addMethod(getGetContractMethod())
              .addMethod(getTriggerContractMethod())
              .addMethod(getListWitnessesMethod())
              .addMethod(getListProposalsMethod())
              .addMethod(getGetPaginatedProposalListMethod())
              .addMethod(getGetProposalByIdMethod())
              .addMethod(getListExchangesMethod())
              .addMethod(getGetPaginatedExchangeListMethod())
              .addMethod(getGetExchangeByIdMethod())
              .addMethod(getGetChainParametersMethod())
              .addMethod(getGetAssetIssueListMethod())
              .addMethod(getGetPaginatedAssetIssueListMethod())
              .addMethod(getTotalTransactionMethod())
              .addMethod(getGetNextMaintenanceTimeMethod())
              .addMethod(getGetTransactionSignMethod())
              .addMethod(getGetTransactionSign2Method())
              .addMethod(getCreateAddressMethod())
              .addMethod(getEasyTransferMethod())
              .addMethod(getEasyTransferByPrivateMethod())
              .addMethod(getGenerateAddressMethod())
              .addMethod(getGetTransactionInfoByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
