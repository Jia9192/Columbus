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
 * <pre>
 * the api of tron's db
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: api/api.proto")
public final class DatabaseGrpc {

  private DatabaseGrpc() {}

  public static final String SERVICE_NAME = "protocol.Database";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.BlockReference> getGetBlockReferenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockReference",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.api.GrpcAPI.BlockReference.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.api.GrpcAPI.BlockReference> getGetBlockReferenceMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.BlockReference> getGetBlockReferenceMethod;
    if ((getGetBlockReferenceMethod = DatabaseGrpc.getGetBlockReferenceMethod) == null) {
      synchronized (DatabaseGrpc.class) {
        if ((getGetBlockReferenceMethod = DatabaseGrpc.getGetBlockReferenceMethod) == null) {
          DatabaseGrpc.getGetBlockReferenceMethod = getGetBlockReferenceMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.api.GrpcAPI.BlockReference>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Database", "getBlockReference"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.BlockReference.getDefaultInstance()))
                  .setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("getBlockReference"))
                  .build();
          }
        }
     }
     return getGetBlockReferenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.protos.Protocol.DynamicProperties> getGetDynamicPropertiesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDynamicProperties",
      requestType = com.galileo.protos.api.GrpcAPI.EmptyMessage.class,
      responseType = com.galileo.protos.protos.Protocol.DynamicProperties.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage,
      com.galileo.protos.protos.Protocol.DynamicProperties> getGetDynamicPropertiesMethod() {
    io.grpc.MethodDescriptor<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.DynamicProperties> getGetDynamicPropertiesMethod;
    if ((getGetDynamicPropertiesMethod = DatabaseGrpc.getGetDynamicPropertiesMethod) == null) {
      synchronized (DatabaseGrpc.class) {
        if ((getGetDynamicPropertiesMethod = DatabaseGrpc.getGetDynamicPropertiesMethod) == null) {
          DatabaseGrpc.getGetDynamicPropertiesMethod = getGetDynamicPropertiesMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.DynamicProperties>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Database", "GetDynamicProperties"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.DynamicProperties.getDefaultInstance()))
                  .setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("GetDynamicProperties"))
                  .build();
          }
        }
     }
     return getGetDynamicPropertiesMethod;
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
    if ((getGetNowBlockMethod = DatabaseGrpc.getGetNowBlockMethod) == null) {
      synchronized (DatabaseGrpc.class) {
        if ((getGetNowBlockMethod = DatabaseGrpc.getGetNowBlockMethod) == null) {
          DatabaseGrpc.getGetNowBlockMethod = getGetNowBlockMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.EmptyMessage, com.galileo.protos.protos.Protocol.Block>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Database", "GetNowBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Block.getDefaultInstance()))
                  .setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("GetNowBlock"))
                  .build();
          }
        }
     }
     return getGetNowBlockMethod;
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
    if ((getGetBlockByNumMethod = DatabaseGrpc.getGetBlockByNumMethod) == null) {
      synchronized (DatabaseGrpc.class) {
        if ((getGetBlockByNumMethod = DatabaseGrpc.getGetBlockByNumMethod) == null) {
          DatabaseGrpc.getGetBlockByNumMethod = getGetBlockByNumMethod = 
              io.grpc.MethodDescriptor.<com.galileo.protos.api.GrpcAPI.NumberMessage, com.galileo.protos.protos.Protocol.Block>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocol.Database", "GetBlockByNum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.api.GrpcAPI.NumberMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.galileo.protos.protos.Protocol.Block.getDefaultInstance()))
                  .setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("GetBlockByNum"))
                  .build();
          }
        }
     }
     return getGetBlockByNumMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DatabaseStub newStub(io.grpc.Channel channel) {
    return new DatabaseStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DatabaseBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DatabaseBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DatabaseFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DatabaseFutureStub(channel);
  }

  /**
   * <pre>
   * the api of tron's db
   * </pre>
   */
  public static abstract class DatabaseImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * for tapos
     * </pre>
     */
    public void getBlockReference(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockReference> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockReferenceMethod(), responseObserver);
    }

    /**
     */
    public void getDynamicProperties(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.DynamicProperties> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDynamicPropertiesMethod(), responseObserver);
    }

    /**
     */
    public void getNowBlock(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNowBlockMethod(), responseObserver);
    }

    /**
     */
    public void getBlockByNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByNumMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBlockReferenceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.api.GrpcAPI.BlockReference>(
                  this, METHODID_GET_BLOCK_REFERENCE)))
          .addMethod(
            getGetDynamicPropertiesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.protos.Protocol.DynamicProperties>(
                  this, METHODID_GET_DYNAMIC_PROPERTIES)))
          .addMethod(
            getGetNowBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.EmptyMessage,
                com.galileo.protos.protos.Protocol.Block>(
                  this, METHODID_GET_NOW_BLOCK)))
          .addMethod(
            getGetBlockByNumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.galileo.protos.api.GrpcAPI.NumberMessage,
                com.galileo.protos.protos.Protocol.Block>(
                  this, METHODID_GET_BLOCK_BY_NUM)))
          .build();
    }
  }

  /**
   * <pre>
   * the api of tron's db
   * </pre>
   */
  public static final class DatabaseStub extends io.grpc.stub.AbstractStub<DatabaseStub> {
    private DatabaseStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatabaseStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatabaseStub(channel, callOptions);
    }

    /**
     * <pre>
     * for tapos
     * </pre>
     */
    public void getBlockReference(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockReference> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockReferenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDynamicProperties(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.DynamicProperties> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDynamicPropertiesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNowBlock(com.galileo.protos.api.GrpcAPI.EmptyMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNowBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBlockByNum(com.galileo.protos.api.GrpcAPI.NumberMessage request,
        io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByNumMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * the api of tron's db
   * </pre>
   */
  public static final class DatabaseBlockingStub extends io.grpc.stub.AbstractStub<DatabaseBlockingStub> {
    private DatabaseBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatabaseBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatabaseBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * for tapos
     * </pre>
     */
    public com.galileo.protos.api.GrpcAPI.BlockReference getBlockReference(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockReferenceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.DynamicProperties getDynamicProperties(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetDynamicPropertiesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Block getNowBlock(com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetNowBlockMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.galileo.protos.protos.Protocol.Block getBlockByNum(com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByNumMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * the api of tron's db
   * </pre>
   */
  public static final class DatabaseFutureStub extends io.grpc.stub.AbstractStub<DatabaseFutureStub> {
    private DatabaseFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatabaseFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatabaseFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * for tapos
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.api.GrpcAPI.BlockReference> getBlockReference(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockReferenceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.DynamicProperties> getDynamicProperties(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDynamicPropertiesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Block> getNowBlock(
        com.galileo.protos.api.GrpcAPI.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNowBlockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.galileo.protos.protos.Protocol.Block> getBlockByNum(
        com.galileo.protos.api.GrpcAPI.NumberMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByNumMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BLOCK_REFERENCE = 0;
  private static final int METHODID_GET_DYNAMIC_PROPERTIES = 1;
  private static final int METHODID_GET_NOW_BLOCK = 2;
  private static final int METHODID_GET_BLOCK_BY_NUM = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DatabaseImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DatabaseImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BLOCK_REFERENCE:
          serviceImpl.getBlockReference((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.api.GrpcAPI.BlockReference>) responseObserver);
          break;
        case METHODID_GET_DYNAMIC_PROPERTIES:
          serviceImpl.getDynamicProperties((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.DynamicProperties>) responseObserver);
          break;
        case METHODID_GET_NOW_BLOCK:
          serviceImpl.getNowBlock((com.galileo.protos.api.GrpcAPI.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_NUM:
          serviceImpl.getBlockByNum((com.galileo.protos.api.GrpcAPI.NumberMessage) request,
              (io.grpc.stub.StreamObserver<com.galileo.protos.protos.Protocol.Block>) responseObserver);
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

  private static abstract class DatabaseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DatabaseBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.galileo.protos.api.GrpcAPI.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Database");
    }
  }

  private static final class DatabaseFileDescriptorSupplier
      extends DatabaseBaseDescriptorSupplier {
    DatabaseFileDescriptorSupplier() {}
  }

  private static final class DatabaseMethodDescriptorSupplier
      extends DatabaseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DatabaseMethodDescriptorSupplier(String methodName) {
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
      synchronized (DatabaseGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DatabaseFileDescriptorSupplier())
              .addMethod(getGetBlockReferenceMethod())
              .addMethod(getGetDynamicPropertiesMethod())
              .addMethod(getGetNowBlockMethod())
              .addMethod(getGetBlockByNumMethod())
              .build();
        }
      }
    }
    return result;
  }
}
