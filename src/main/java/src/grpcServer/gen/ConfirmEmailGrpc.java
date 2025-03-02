package src.grpcServer.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: src/main/java/src/grpcServer/grpc/main.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ConfirmEmailGrpc {

  private ConfirmEmailGrpc() {}

  public static final java.lang.String SERVICE_NAME = "grpc.ConfirmEmail";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<src.grpcServer.gen.Request,
      src.grpcServer.gen.Response> getCheckEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkEmail",
      requestType = src.grpcServer.gen.Request.class,
      responseType = src.grpcServer.gen.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<src.grpcServer.gen.Request,
      src.grpcServer.gen.Response> getCheckEmailMethod() {
    io.grpc.MethodDescriptor<src.grpcServer.gen.Request, src.grpcServer.gen.Response> getCheckEmailMethod;
    if ((getCheckEmailMethod = ConfirmEmailGrpc.getCheckEmailMethod) == null) {
      synchronized (ConfirmEmailGrpc.class) {
        if ((getCheckEmailMethod = ConfirmEmailGrpc.getCheckEmailMethod) == null) {
          ConfirmEmailGrpc.getCheckEmailMethod = getCheckEmailMethod =
              io.grpc.MethodDescriptor.<src.grpcServer.gen.Request, src.grpcServer.gen.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  src.grpcServer.gen.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  src.grpcServer.gen.Response.getDefaultInstance()))
              .setSchemaDescriptor(new ConfirmEmailMethodDescriptorSupplier("checkEmail"))
              .build();
        }
      }
    }
    return getCheckEmailMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfirmEmailStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfirmEmailStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfirmEmailStub>() {
        @java.lang.Override
        public ConfirmEmailStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfirmEmailStub(channel, callOptions);
        }
      };
    return ConfirmEmailStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfirmEmailBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfirmEmailBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfirmEmailBlockingStub>() {
        @java.lang.Override
        public ConfirmEmailBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfirmEmailBlockingStub(channel, callOptions);
        }
      };
    return ConfirmEmailBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfirmEmailFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfirmEmailFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfirmEmailFutureStub>() {
        @java.lang.Override
        public ConfirmEmailFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfirmEmailFutureStub(channel, callOptions);
        }
      };
    return ConfirmEmailFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkEmail(src.grpcServer.gen.Request request,
        io.grpc.stub.StreamObserver<src.grpcServer.gen.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckEmailMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ConfirmEmail.
   */
  public static abstract class ConfirmEmailImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ConfirmEmailGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ConfirmEmail.
   */
  public static final class ConfirmEmailStub
      extends io.grpc.stub.AbstractAsyncStub<ConfirmEmailStub> {
    private ConfirmEmailStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfirmEmailStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfirmEmailStub(channel, callOptions);
    }

    /**
     */
    public void checkEmail(src.grpcServer.gen.Request request,
        io.grpc.stub.StreamObserver<src.grpcServer.gen.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckEmailMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ConfirmEmail.
   */
  public static final class ConfirmEmailBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ConfirmEmailBlockingStub> {
    private ConfirmEmailBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfirmEmailBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfirmEmailBlockingStub(channel, callOptions);
    }

    /**
     */
    public src.grpcServer.gen.Response checkEmail(src.grpcServer.gen.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckEmailMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ConfirmEmail.
   */
  public static final class ConfirmEmailFutureStub
      extends io.grpc.stub.AbstractFutureStub<ConfirmEmailFutureStub> {
    private ConfirmEmailFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfirmEmailFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfirmEmailFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<src.grpcServer.gen.Response> checkEmail(
        src.grpcServer.gen.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_EMAIL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_EMAIL:
          serviceImpl.checkEmail((src.grpcServer.gen.Request) request,
              (io.grpc.stub.StreamObserver<src.grpcServer.gen.Response>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckEmailMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              src.grpcServer.gen.Request,
              src.grpcServer.gen.Response>(
                service, METHODID_CHECK_EMAIL)))
        .build();
  }

  private static abstract class ConfirmEmailBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConfirmEmailBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return src.grpcServer.gen.Main.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConfirmEmail");
    }
  }

  private static final class ConfirmEmailFileDescriptorSupplier
      extends ConfirmEmailBaseDescriptorSupplier {
    ConfirmEmailFileDescriptorSupplier() {}
  }

  private static final class ConfirmEmailMethodDescriptorSupplier
      extends ConfirmEmailBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ConfirmEmailMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ConfirmEmailGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfirmEmailFileDescriptorSupplier())
              .addMethod(getCheckEmailMethod())
              .build();
        }
      }
    }
    return result;
  }
}
