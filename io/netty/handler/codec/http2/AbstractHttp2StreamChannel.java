/*      */ package io.netty.handler.codec.http2;
/*      */ 
/*      */ import io.netty.buffer.ByteBufAllocator;
/*      */ import io.netty.channel.Channel;
/*      */ import io.netty.channel.ChannelConfig;
/*      */ import io.netty.channel.ChannelFuture;
/*      */ import io.netty.channel.ChannelFutureListener;
/*      */ import io.netty.channel.ChannelHandler;
/*      */ import io.netty.channel.ChannelHandlerContext;
/*      */ import io.netty.channel.ChannelId;
/*      */ import io.netty.channel.ChannelMetadata;
/*      */ import io.netty.channel.ChannelOutboundBuffer;
/*      */ import io.netty.channel.ChannelOutboundInvoker;
/*      */ import io.netty.channel.ChannelPipeline;
/*      */ import io.netty.channel.ChannelProgressivePromise;
/*      */ import io.netty.channel.ChannelPromise;
/*      */ import io.netty.channel.DefaultChannelConfig;
/*      */ import io.netty.channel.DefaultChannelPipeline;
/*      */ import io.netty.channel.EventLoop;
/*      */ import io.netty.channel.MessageSizeEstimator;
/*      */ import io.netty.channel.RecvByteBufAllocator;
/*      */ import io.netty.channel.VoidChannelPromise;
/*      */ import io.netty.util.DefaultAttributeMap;
/*      */ import io.netty.util.ReferenceCountUtil;
/*      */ import io.netty.util.concurrent.Future;
/*      */ import io.netty.util.concurrent.GenericFutureListener;
/*      */ import io.netty.util.internal.StringUtil;
/*      */ import io.netty.util.internal.logging.InternalLogger;
/*      */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*      */ import java.net.SocketAddress;
/*      */ import java.nio.channels.ClosedChannelException;
/*      */ import java.util.ArrayDeque;
/*      */ import java.util.Queue;
/*      */ import java.util.concurrent.RejectedExecutionException;
/*      */ import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/*      */ import java.util.concurrent.atomic.AtomicLongFieldUpdater;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class AbstractHttp2StreamChannel
/*      */   extends DefaultAttributeMap
/*      */   implements Http2StreamChannel
/*      */ {
/*   59 */   static final Http2FrameStreamVisitor WRITABLE_VISITOR = new Http2FrameStreamVisitor()
/*      */     {
/*      */       public boolean visit(Http2FrameStream stream) {
/*   62 */         AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
/*      */         
/*   64 */         childChannel.trySetWritable();
/*   65 */         return true;
/*      */       }
/*      */     };
/*      */   
/*   69 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AbstractHttp2StreamChannel.class);
/*      */   
/*   71 */   private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int MIN_HTTP2_FRAME_SIZE = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class FlowControlledFrameSizeEstimator
/*      */     implements MessageSizeEstimator
/*      */   {
/*   84 */     static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();
/*      */     
/*   86 */     private static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle()
/*      */       {
/*      */         public int size(Object msg) {
/*   89 */           return (msg instanceof Http2DataFrame) ? 
/*      */             
/*   91 */             (int)Math.min(2147483647L, ((Http2DataFrame)msg).initialFlowControlledBytes() + 9L) : 9;
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/*      */     public MessageSizeEstimator.Handle newHandle() {
/*   98 */       return HANDLE_INSTANCE;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*  103 */   private static final AtomicLongFieldUpdater<AbstractHttp2StreamChannel> TOTAL_PENDING_SIZE_UPDATER = AtomicLongFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "totalPendingSize");
/*      */ 
/*      */   
/*  106 */   private static final AtomicIntegerFieldUpdater<AbstractHttp2StreamChannel> UNWRITABLE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "unwritable");
/*      */   
/*      */   private static void windowUpdateFrameWriteComplete(ChannelFuture future, Channel streamChannel) {
/*  109 */     Throwable cause = future.cause();
/*  110 */     if (cause != null) {
/*      */       Throwable unwrappedCause;
/*      */       
/*  113 */       if (cause instanceof Http2FrameStreamException && (unwrappedCause = cause.getCause()) != null) {
/*  114 */         cause = unwrappedCause;
/*      */       }
/*      */ 
/*      */       
/*  118 */       streamChannel.pipeline().fireExceptionCaught(cause);
/*  119 */       streamChannel.unsafe().close(streamChannel.unsafe().voidPromise());
/*      */     } 
/*      */   }
/*      */   
/*  123 */   private final ChannelFutureListener windowUpdateFrameWriteListener = new ChannelFutureListener()
/*      */     {
/*      */       public void operationComplete(ChannelFuture future) {
/*  126 */         AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(future, AbstractHttp2StreamChannel.this);
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private enum ReadStatus
/*      */   {
/*  137 */     IDLE,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  142 */     IN_PROGRESS,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  147 */     REQUESTED;
/*      */   }
/*      */   
/*  150 */   private final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
/*  151 */   private final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();
/*      */ 
/*      */   
/*      */   private final ChannelId channelId;
/*      */   
/*      */   private final ChannelPipeline pipeline;
/*      */   
/*      */   private final Http2FrameCodec.DefaultHttp2FrameStream stream;
/*      */   
/*      */   private final ChannelPromise closePromise;
/*      */   
/*      */   private volatile boolean registered;
/*      */   
/*      */   private volatile long totalPendingSize;
/*      */   
/*      */   private volatile int unwritable;
/*      */   
/*      */   private Runnable fireChannelWritabilityChangedTask;
/*      */   
/*      */   private boolean outboundClosed;
/*      */   
/*      */   private int flowControlledBytes;
/*      */   
/*  174 */   private ReadStatus readStatus = ReadStatus.IDLE;
/*      */   
/*      */   private Queue<Object> inboundBuffer;
/*      */   
/*      */   private boolean firstFrameWritten;
/*      */   
/*      */   private boolean readCompletePending;
/*      */   
/*      */   AbstractHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream stream, int id, ChannelHandler inboundHandler) {
/*  183 */     this.stream = stream;
/*  184 */     stream.attachment = this;
/*  185 */     this.pipeline = (ChannelPipeline)new DefaultChannelPipeline(this)
/*      */       {
/*      */         protected void incrementPendingOutboundBytes(long size) {
/*  188 */           AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size, true);
/*      */         }
/*      */ 
/*      */         
/*      */         protected void decrementPendingOutboundBytes(long size) {
/*  193 */           AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size, true);
/*      */         }
/*      */       };
/*      */     
/*  197 */     this.closePromise = this.pipeline.newPromise();
/*  198 */     this.channelId = new Http2StreamChannelId(parent().id(), id);
/*      */     
/*  200 */     if (inboundHandler != null)
/*      */     {
/*  202 */       this.pipeline.addLast(new ChannelHandler[] { inboundHandler });
/*      */     }
/*      */   }
/*      */   
/*      */   private void incrementPendingOutboundBytes(long size, boolean invokeLater) {
/*  207 */     if (size == 0L) {
/*      */       return;
/*      */     }
/*      */     
/*  211 */     long newWriteBufferSize = TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, size);
/*  212 */     if (newWriteBufferSize > config().getWriteBufferHighWaterMark()) {
/*  213 */       setUnwritable(invokeLater);
/*      */     }
/*      */   }
/*      */   
/*      */   private void decrementPendingOutboundBytes(long size, boolean invokeLater) {
/*  218 */     if (size == 0L) {
/*      */       return;
/*      */     }
/*      */     
/*  222 */     long newWriteBufferSize = TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -size);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  228 */     if (newWriteBufferSize < config().getWriteBufferLowWaterMark() && parent().isWritable()) {
/*  229 */       setWritable(invokeLater);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void trySetWritable() {
/*  238 */     if (this.totalPendingSize < config().getWriteBufferLowWaterMark()) {
/*  239 */       setWritable(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void setWritable(boolean invokeLater) {
/*      */     while (true) {
/*  245 */       int oldValue = this.unwritable;
/*  246 */       int newValue = oldValue & 0xFFFFFFFE;
/*  247 */       if (UNWRITABLE_UPDATER.compareAndSet(this, oldValue, newValue)) {
/*  248 */         if (oldValue != 0 && newValue == 0) {
/*  249 */           fireChannelWritabilityChanged(invokeLater);
/*      */         }
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setUnwritable(boolean invokeLater) {
/*      */     while (true) {
/*  258 */       int oldValue = this.unwritable;
/*  259 */       int newValue = oldValue | 0x1;
/*  260 */       if (UNWRITABLE_UPDATER.compareAndSet(this, oldValue, newValue)) {
/*  261 */         if (oldValue == 0 && newValue != 0) {
/*  262 */           fireChannelWritabilityChanged(invokeLater);
/*      */         }
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fireChannelWritabilityChanged(boolean invokeLater) {
/*  270 */     final ChannelPipeline pipeline = pipeline();
/*  271 */     if (invokeLater) {
/*  272 */       Runnable task = this.fireChannelWritabilityChangedTask;
/*  273 */       if (task == null) {
/*  274 */         this.fireChannelWritabilityChangedTask = task = new Runnable()
/*      */           {
/*      */             public void run() {
/*  277 */               pipeline.fireChannelWritabilityChanged();
/*      */             }
/*      */           };
/*      */       }
/*  281 */       eventLoop().execute(task);
/*      */     } else {
/*  283 */       pipeline.fireChannelWritabilityChanged();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Http2FrameStream stream() {
/*  288 */     return this.stream;
/*      */   }
/*      */   
/*      */   void closeOutbound() {
/*  292 */     this.outboundClosed = true;
/*      */   }
/*      */   
/*      */   void streamClosed() {
/*  296 */     this.unsafe.readEOS();
/*      */ 
/*      */     
/*  299 */     this.unsafe.doBeginRead();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelMetadata metadata() {
/*  304 */     return METADATA;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelConfig config() {
/*  309 */     return (ChannelConfig)this.config;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOpen() {
/*  314 */     return !this.closePromise.isDone();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isActive() {
/*  319 */     return isOpen();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWritable() {
/*  324 */     return (this.unwritable == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelId id() {
/*  329 */     return this.channelId;
/*      */   }
/*      */ 
/*      */   
/*      */   public EventLoop eventLoop() {
/*  334 */     return parent().eventLoop();
/*      */   }
/*      */ 
/*      */   
/*      */   public Channel parent() {
/*  339 */     return parentContext().channel();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRegistered() {
/*  344 */     return this.registered;
/*      */   }
/*      */ 
/*      */   
/*      */   public SocketAddress localAddress() {
/*  349 */     return parent().localAddress();
/*      */   }
/*      */ 
/*      */   
/*      */   public SocketAddress remoteAddress() {
/*  354 */     return parent().remoteAddress();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture closeFuture() {
/*  359 */     return (ChannelFuture)this.closePromise;
/*      */   }
/*      */ 
/*      */   
/*      */   public long bytesBeforeUnwritable() {
/*  364 */     long bytes = config().getWriteBufferHighWaterMark() - this.totalPendingSize;
/*      */ 
/*      */ 
/*      */     
/*  368 */     if (bytes > 0L) {
/*  369 */       return isWritable() ? bytes : 0L;
/*      */     }
/*  371 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public long bytesBeforeWritable() {
/*  376 */     long bytes = this.totalPendingSize - config().getWriteBufferLowWaterMark();
/*      */ 
/*      */ 
/*      */     
/*  380 */     if (bytes > 0L) {
/*  381 */       return isWritable() ? 0L : bytes;
/*      */     }
/*  383 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public Channel.Unsafe unsafe() {
/*  388 */     return this.unsafe;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelPipeline pipeline() {
/*  393 */     return this.pipeline;
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBufAllocator alloc() {
/*  398 */     return config().getAllocator();
/*      */   }
/*      */ 
/*      */   
/*      */   public Channel read() {
/*  403 */     pipeline().read();
/*  404 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Channel flush() {
/*  409 */     pipeline().flush();
/*  410 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture bind(SocketAddress localAddress) {
/*  415 */     return pipeline().bind(localAddress);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture connect(SocketAddress remoteAddress) {
/*  420 */     return pipeline().connect(remoteAddress);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
/*  425 */     return pipeline().connect(remoteAddress, localAddress);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture disconnect() {
/*  430 */     return pipeline().disconnect();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture close() {
/*  435 */     return pipeline().close();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture deregister() {
/*  440 */     return pipeline().deregister();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
/*  445 */     return pipeline().bind(localAddress, promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
/*  450 */     return pipeline().connect(remoteAddress, promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
/*  455 */     return pipeline().connect(remoteAddress, localAddress, promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture disconnect(ChannelPromise promise) {
/*  460 */     return pipeline().disconnect(promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture close(ChannelPromise promise) {
/*  465 */     return pipeline().close(promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture deregister(ChannelPromise promise) {
/*  470 */     return pipeline().deregister(promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture write(Object msg) {
/*  475 */     return pipeline().write(msg);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture write(Object msg, ChannelPromise promise) {
/*  480 */     return pipeline().write(msg, promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
/*  485 */     return pipeline().writeAndFlush(msg, promise);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture writeAndFlush(Object msg) {
/*  490 */     return pipeline().writeAndFlush(msg);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelPromise newPromise() {
/*  495 */     return pipeline().newPromise();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelProgressivePromise newProgressivePromise() {
/*  500 */     return pipeline().newProgressivePromise();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture newSucceededFuture() {
/*  505 */     return pipeline().newSucceededFuture();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelFuture newFailedFuture(Throwable cause) {
/*  510 */     return pipeline().newFailedFuture(cause);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelPromise voidPromise() {
/*  515 */     return pipeline().voidPromise();
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  520 */     return id().hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/*  525 */     return (this == o);
/*      */   }
/*      */ 
/*      */   
/*      */   public int compareTo(Channel o) {
/*  530 */     if (this == o) {
/*  531 */       return 0;
/*      */     }
/*      */     
/*  534 */     return id().compareTo(o.id());
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  539 */     return parent().toString() + "(H2 - " + this.stream + ')';
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void fireChildRead(Http2Frame frame) {
/*  547 */     assert eventLoop().inEventLoop();
/*  548 */     if (!isActive()) {
/*  549 */       ReferenceCountUtil.release(frame);
/*  550 */     } else if (this.readStatus != ReadStatus.IDLE) {
/*      */ 
/*      */       
/*  553 */       assert this.inboundBuffer == null || this.inboundBuffer.isEmpty();
/*  554 */       RecvByteBufAllocator.Handle allocHandle = this.unsafe.recvBufAllocHandle();
/*  555 */       this.unsafe.doRead0(frame, allocHandle);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  560 */       if (allocHandle.continueReading()) {
/*  561 */         maybeAddChannelToReadCompletePendingQueue();
/*      */       } else {
/*  563 */         this.unsafe.notifyReadComplete(allocHandle, true);
/*      */       } 
/*      */     } else {
/*  566 */       if (this.inboundBuffer == null) {
/*  567 */         this.inboundBuffer = new ArrayDeque(4);
/*      */       }
/*  569 */       this.inboundBuffer.add(frame);
/*      */     } 
/*      */   }
/*      */   
/*      */   void fireChildReadComplete() {
/*  574 */     assert eventLoop().inEventLoop();
/*  575 */     assert this.readStatus != ReadStatus.IDLE || !this.readCompletePending;
/*  576 */     this.unsafe.notifyReadComplete(this.unsafe.recvBufAllocHandle(), false);
/*      */   }
/*      */   
/*      */   private final class Http2ChannelUnsafe implements Channel.Unsafe {
/*  580 */     private final VoidChannelPromise unsafeVoidPromise = new VoidChannelPromise(AbstractHttp2StreamChannel.this, false);
/*      */     
/*      */     private RecvByteBufAllocator.Handle recvHandle;
/*      */     
/*      */     private boolean writeDoneAndNoFlush;
/*      */     
/*      */     private boolean closeInitiated;
/*      */     
/*      */     private boolean readEOS;
/*      */     
/*      */     public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
/*  591 */       if (!promise.setUncancellable()) {
/*      */         return;
/*      */       }
/*  594 */       promise.setFailure(new UnsupportedOperationException());
/*      */     }
/*      */ 
/*      */     
/*      */     public RecvByteBufAllocator.Handle recvBufAllocHandle() {
/*  599 */       if (this.recvHandle == null) {
/*  600 */         this.recvHandle = AbstractHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
/*  601 */         this.recvHandle.reset(AbstractHttp2StreamChannel.this.config());
/*      */       } 
/*  603 */       return this.recvHandle;
/*      */     }
/*      */ 
/*      */     
/*      */     public SocketAddress localAddress() {
/*  608 */       return AbstractHttp2StreamChannel.this.parent().unsafe().localAddress();
/*      */     }
/*      */ 
/*      */     
/*      */     public SocketAddress remoteAddress() {
/*  613 */       return AbstractHttp2StreamChannel.this.parent().unsafe().remoteAddress();
/*      */     }
/*      */ 
/*      */     
/*      */     public void register(EventLoop eventLoop, ChannelPromise promise) {
/*  618 */       if (!promise.setUncancellable()) {
/*      */         return;
/*      */       }
/*  621 */       if (AbstractHttp2StreamChannel.this.registered) {
/*  622 */         promise.setFailure(new UnsupportedOperationException("Re-register is not supported"));
/*      */         
/*      */         return;
/*      */       } 
/*  626 */       AbstractHttp2StreamChannel.this.registered = true;
/*      */       
/*  628 */       promise.setSuccess();
/*      */       
/*  630 */       AbstractHttp2StreamChannel.this.pipeline().fireChannelRegistered();
/*  631 */       if (AbstractHttp2StreamChannel.this.isActive()) {
/*  632 */         AbstractHttp2StreamChannel.this.pipeline().fireChannelActive();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void bind(SocketAddress localAddress, ChannelPromise promise) {
/*  638 */       if (!promise.setUncancellable()) {
/*      */         return;
/*      */       }
/*  641 */       promise.setFailure(new UnsupportedOperationException());
/*      */     }
/*      */ 
/*      */     
/*      */     public void disconnect(ChannelPromise promise) {
/*  646 */       close(promise);
/*      */     }
/*      */ 
/*      */     
/*      */     public void close(final ChannelPromise promise) {
/*  651 */       if (!promise.setUncancellable()) {
/*      */         return;
/*      */       }
/*  654 */       if (this.closeInitiated) {
/*  655 */         if (AbstractHttp2StreamChannel.this.closePromise.isDone()) {
/*      */           
/*  657 */           promise.setSuccess();
/*  658 */         } else if (!(promise instanceof VoidChannelPromise)) {
/*      */           
/*  660 */           AbstractHttp2StreamChannel.this.closePromise.addListener((GenericFutureListener)new ChannelFutureListener()
/*      */               {
/*      */                 public void operationComplete(ChannelFuture future) {
/*  663 */                   promise.setSuccess();
/*      */                 }
/*      */               });
/*      */         } 
/*      */         return;
/*      */       } 
/*  669 */       this.closeInitiated = true;
/*      */       
/*  671 */       AbstractHttp2StreamChannel.this.readCompletePending = false;
/*      */       
/*  673 */       boolean wasActive = AbstractHttp2StreamChannel.this.isActive();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  680 */       if (AbstractHttp2StreamChannel.this.parent().isActive() && !this.readEOS && Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream.id())) {
/*  681 */         Http2StreamFrame resetFrame = (new DefaultHttp2ResetFrame(Http2Error.CANCEL)).stream(AbstractHttp2StreamChannel.this.stream());
/*  682 */         write(resetFrame, AbstractHttp2StreamChannel.this.unsafe().voidPromise());
/*  683 */         flush();
/*      */       } 
/*      */       
/*  686 */       if (AbstractHttp2StreamChannel.this.inboundBuffer != null) {
/*      */         while (true) {
/*  688 */           Object msg = AbstractHttp2StreamChannel.this.inboundBuffer.poll();
/*  689 */           if (msg == null) {
/*      */             break;
/*      */           }
/*  692 */           ReferenceCountUtil.release(msg);
/*      */         } 
/*  694 */         AbstractHttp2StreamChannel.this.inboundBuffer = null;
/*      */       } 
/*      */ 
/*      */       
/*  698 */       AbstractHttp2StreamChannel.this.outboundClosed = true;
/*  699 */       AbstractHttp2StreamChannel.this.closePromise.setSuccess();
/*  700 */       promise.setSuccess();
/*      */       
/*  702 */       fireChannelInactiveAndDeregister(voidPromise(), wasActive);
/*      */     }
/*      */ 
/*      */     
/*      */     public void closeForcibly() {
/*  707 */       close(AbstractHttp2StreamChannel.this.unsafe().voidPromise());
/*      */     }
/*      */ 
/*      */     
/*      */     public void deregister(ChannelPromise promise) {
/*  712 */       fireChannelInactiveAndDeregister(promise, false);
/*      */     }
/*      */ 
/*      */     
/*      */     private void fireChannelInactiveAndDeregister(final ChannelPromise promise, final boolean fireChannelInactive) {
/*  717 */       if (!promise.setUncancellable()) {
/*      */         return;
/*      */       }
/*      */       
/*  721 */       if (!AbstractHttp2StreamChannel.this.registered) {
/*  722 */         promise.setSuccess();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  733 */       invokeLater(new Runnable()
/*      */           {
/*      */             public void run() {
/*  736 */               if (fireChannelInactive) {
/*  737 */                 AbstractHttp2StreamChannel.this.pipeline.fireChannelInactive();
/*      */               }
/*      */ 
/*      */               
/*  741 */               if (AbstractHttp2StreamChannel.this.registered) {
/*  742 */                 AbstractHttp2StreamChannel.this.registered = false;
/*  743 */                 AbstractHttp2StreamChannel.this.pipeline.fireChannelUnregistered();
/*      */               } 
/*  745 */               AbstractHttp2StreamChannel.Http2ChannelUnsafe.this.safeSetSuccess(promise);
/*      */             }
/*      */           });
/*      */     }
/*      */     
/*      */     private void safeSetSuccess(ChannelPromise promise) {
/*  751 */       if (!(promise instanceof VoidChannelPromise) && !promise.trySuccess()) {
/*  752 */         AbstractHttp2StreamChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", promise);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void invokeLater(Runnable task) {
/*      */       try {
/*  769 */         AbstractHttp2StreamChannel.this.eventLoop().execute(task);
/*  770 */       } catch (RejectedExecutionException e) {
/*  771 */         AbstractHttp2StreamChannel.logger.warn("Can't invoke task later as EventLoop rejected it", e);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void beginRead() {
/*  777 */       if (!AbstractHttp2StreamChannel.this.isActive()) {
/*      */         return;
/*      */       }
/*  780 */       updateLocalWindowIfNeeded();
/*      */       
/*  782 */       switch (AbstractHttp2StreamChannel.this.readStatus) {
/*      */         case IDLE:
/*  784 */           AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.ReadStatus.IN_PROGRESS;
/*  785 */           doBeginRead();
/*      */           break;
/*      */         case IN_PROGRESS:
/*  788 */           AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.ReadStatus.REQUESTED;
/*      */           break;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private Object pollQueuedMessage() {
/*  796 */       return (AbstractHttp2StreamChannel.this.inboundBuffer == null) ? null : AbstractHttp2StreamChannel.this.inboundBuffer.poll();
/*      */     }
/*      */ 
/*      */     
/*      */     void doBeginRead() {
/*  801 */       while (AbstractHttp2StreamChannel.this.readStatus != AbstractHttp2StreamChannel.ReadStatus.IDLE) {
/*  802 */         Object message = pollQueuedMessage();
/*  803 */         if (message == null) {
/*  804 */           if (this.readEOS) {
/*  805 */             AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
/*      */           }
/*      */ 
/*      */           
/*  809 */           flush();
/*      */           break;
/*      */         } 
/*  812 */         RecvByteBufAllocator.Handle allocHandle = recvBufAllocHandle();
/*  813 */         allocHandle.reset(AbstractHttp2StreamChannel.this.config());
/*  814 */         boolean continueReading = false;
/*      */         do {
/*  816 */           doRead0((Http2Frame)message, allocHandle);
/*  817 */         } while ((this.readEOS || (continueReading = allocHandle.continueReading())) && (
/*  818 */           message = pollQueuedMessage()) != null);
/*      */         
/*  820 */         if (continueReading && AbstractHttp2StreamChannel.this.isParentReadInProgress() && !this.readEOS) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  825 */           AbstractHttp2StreamChannel.this.maybeAddChannelToReadCompletePendingQueue(); continue;
/*      */         } 
/*  827 */         notifyReadComplete(allocHandle, true);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     void readEOS() {
/*  833 */       this.readEOS = true;
/*      */     }
/*      */     
/*      */     private void updateLocalWindowIfNeeded() {
/*  837 */       if (AbstractHttp2StreamChannel.this.flowControlledBytes != 0) {
/*  838 */         int bytes = AbstractHttp2StreamChannel.this.flowControlledBytes;
/*  839 */         AbstractHttp2StreamChannel.this.flowControlledBytes = 0;
/*  840 */         ChannelFuture future = AbstractHttp2StreamChannel.this.write0(AbstractHttp2StreamChannel.this.parentContext(), (new DefaultHttp2WindowUpdateFrame(bytes)).stream(AbstractHttp2StreamChannel.this.stream));
/*      */ 
/*      */ 
/*      */         
/*  844 */         this.writeDoneAndNoFlush = true;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  849 */         if (future.isDone()) {
/*  850 */           AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(future, AbstractHttp2StreamChannel.this);
/*      */         } else {
/*  852 */           future.addListener((GenericFutureListener)AbstractHttp2StreamChannel.this.windowUpdateFrameWriteListener);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     void notifyReadComplete(RecvByteBufAllocator.Handle allocHandle, boolean forceReadComplete) {
/*  858 */       if (!AbstractHttp2StreamChannel.this.readCompletePending && !forceReadComplete) {
/*      */         return;
/*      */       }
/*      */       
/*  862 */       AbstractHttp2StreamChannel.this.readCompletePending = false;
/*      */       
/*  864 */       if (AbstractHttp2StreamChannel.this.readStatus == AbstractHttp2StreamChannel.ReadStatus.REQUESTED) {
/*  865 */         AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.ReadStatus.IN_PROGRESS;
/*      */       } else {
/*  867 */         AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.ReadStatus.IDLE;
/*      */       } 
/*      */       
/*  870 */       allocHandle.readComplete();
/*  871 */       AbstractHttp2StreamChannel.this.pipeline().fireChannelReadComplete();
/*      */ 
/*      */ 
/*      */       
/*  875 */       flush();
/*  876 */       if (this.readEOS) {
/*  877 */         AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     void doRead0(Http2Frame frame, RecvByteBufAllocator.Handle allocHandle) {
/*      */       int bytes;
/*  884 */       if (frame instanceof Http2DataFrame) {
/*  885 */         bytes = ((Http2DataFrame)frame).initialFlowControlledBytes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  892 */         AbstractHttp2StreamChannel.this.flowControlledBytes = AbstractHttp2StreamChannel.this.flowControlledBytes + bytes;
/*      */       } else {
/*  894 */         bytes = 9;
/*      */       } 
/*      */       
/*  897 */       allocHandle.attemptedBytesRead(bytes);
/*  898 */       allocHandle.lastBytesRead(bytes);
/*  899 */       allocHandle.incMessagesRead(1);
/*      */       
/*  901 */       AbstractHttp2StreamChannel.this.pipeline().fireChannelRead(frame);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void write(Object msg, ChannelPromise promise) {
/*  907 */       if (!promise.setUncancellable()) {
/*  908 */         ReferenceCountUtil.release(msg);
/*      */         
/*      */         return;
/*      */       } 
/*  912 */       if (!AbstractHttp2StreamChannel.this.isActive() || (AbstractHttp2StreamChannel.this
/*      */         
/*  914 */         .outboundClosed && (msg instanceof Http2HeadersFrame || msg instanceof Http2DataFrame))) {
/*  915 */         ReferenceCountUtil.release(msg);
/*  916 */         promise.setFailure(new ClosedChannelException());
/*      */         
/*      */         return;
/*      */       } 
/*      */       try {
/*  921 */         if (msg instanceof Http2StreamFrame) {
/*  922 */           Http2StreamFrame frame = validateStreamFrame((Http2StreamFrame)msg).stream(AbstractHttp2StreamChannel.this.stream());
/*  923 */           writeHttp2StreamFrame(frame, promise);
/*      */         } else {
/*  925 */           String msgStr = msg.toString();
/*  926 */           ReferenceCountUtil.release(msg);
/*  927 */           promise.setFailure(new IllegalArgumentException("Message must be an " + 
/*  928 */                 StringUtil.simpleClassName(Http2StreamFrame.class) + ": " + msgStr));
/*      */         }
/*      */       
/*  931 */       } catch (Throwable t) {
/*  932 */         promise.tryFailure(t);
/*      */       } 
/*      */     }
/*      */     private void writeHttp2StreamFrame(Http2StreamFrame frame, final ChannelPromise promise) {
/*      */       final boolean firstWrite;
/*  937 */       if (!AbstractHttp2StreamChannel.this.firstFrameWritten && !Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream().id()) && !(frame instanceof Http2HeadersFrame)) {
/*  938 */         ReferenceCountUtil.release(frame);
/*  939 */         promise.setFailure(new IllegalArgumentException("The first frame must be a headers frame. Was: " + frame
/*      */               
/*  941 */               .name()));
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  946 */       if (AbstractHttp2StreamChannel.this.firstFrameWritten) {
/*  947 */         firstWrite = false;
/*      */       } else {
/*  949 */         firstWrite = AbstractHttp2StreamChannel.this.firstFrameWritten = true;
/*      */       } 
/*      */       
/*  952 */       ChannelFuture f = AbstractHttp2StreamChannel.this.write0(AbstractHttp2StreamChannel.this.parentContext(), frame);
/*  953 */       if (f.isDone()) {
/*  954 */         if (firstWrite) {
/*  955 */           firstWriteComplete(f, promise);
/*      */         } else {
/*  957 */           writeComplete(f, promise);
/*      */         } 
/*      */       } else {
/*  960 */         final long bytes = AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(frame);
/*  961 */         AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(bytes, false);
/*  962 */         f.addListener((GenericFutureListener)new ChannelFutureListener()
/*      */             {
/*      */               public void operationComplete(ChannelFuture future) {
/*  965 */                 if (firstWrite) {
/*  966 */                   AbstractHttp2StreamChannel.Http2ChannelUnsafe.this.firstWriteComplete(future, promise);
/*      */                 } else {
/*  968 */                   AbstractHttp2StreamChannel.Http2ChannelUnsafe.this.writeComplete(future, promise);
/*      */                 } 
/*  970 */                 AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(bytes, false);
/*      */               }
/*      */             });
/*  973 */         this.writeDoneAndNoFlush = true;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void firstWriteComplete(ChannelFuture future, ChannelPromise promise) {
/*  978 */       Throwable cause = future.cause();
/*  979 */       if (cause == null) {
/*  980 */         promise.setSuccess();
/*      */       } else {
/*      */         
/*  983 */         closeForcibly();
/*  984 */         promise.setFailure(wrapStreamClosedError(cause));
/*      */       } 
/*      */     }
/*      */     
/*      */     private void writeComplete(ChannelFuture future, ChannelPromise promise) {
/*  989 */       Throwable cause = future.cause();
/*  990 */       if (cause == null) {
/*  991 */         promise.setSuccess();
/*      */       } else {
/*  993 */         Throwable error = wrapStreamClosedError(cause);
/*      */         
/*  995 */         if (error instanceof java.io.IOException) {
/*  996 */           if (AbstractHttp2StreamChannel.this.config.isAutoClose()) {
/*      */             
/*  998 */             closeForcibly();
/*      */           } else {
/*      */             
/* 1001 */             AbstractHttp2StreamChannel.this.outboundClosed = true;
/*      */           } 
/*      */         }
/* 1004 */         promise.setFailure(error);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private Throwable wrapStreamClosedError(Throwable cause) {
/* 1011 */       if (cause instanceof Http2Exception && ((Http2Exception)cause).error() == Http2Error.STREAM_CLOSED) {
/* 1012 */         return (new ClosedChannelException()).initCause(cause);
/*      */       }
/* 1014 */       return cause;
/*      */     }
/*      */     
/*      */     private Http2StreamFrame validateStreamFrame(Http2StreamFrame frame) {
/* 1018 */       if (frame.stream() != null && frame.stream() != AbstractHttp2StreamChannel.this.stream) {
/* 1019 */         String msgString = frame.toString();
/* 1020 */         ReferenceCountUtil.release(frame);
/* 1021 */         throw new IllegalArgumentException("Stream " + frame
/* 1022 */             .stream() + " must not be set on the frame: " + msgString);
/*      */       } 
/* 1024 */       return frame;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void flush() {
/* 1033 */       if (!this.writeDoneAndNoFlush || AbstractHttp2StreamChannel.this.isParentReadInProgress()) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1039 */       this.writeDoneAndNoFlush = false;
/* 1040 */       AbstractHttp2StreamChannel.this.flush0(AbstractHttp2StreamChannel.this.parentContext());
/*      */     }
/*      */ 
/*      */     
/*      */     public ChannelPromise voidPromise() {
/* 1045 */       return (ChannelPromise)this.unsafeVoidPromise;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public ChannelOutboundBuffer outboundBuffer() {
/* 1051 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     private Http2ChannelUnsafe() {}
/*      */   }
/*      */   
/*      */   private static final class Http2StreamChannelConfig
/*      */     extends DefaultChannelConfig
/*      */   {
/*      */     Http2StreamChannelConfig(Channel channel) {
/* 1062 */       super(channel);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageSizeEstimator getMessageSizeEstimator() {
/* 1067 */       return AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.INSTANCE;
/*      */     }
/*      */ 
/*      */     
/*      */     public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
/* 1072 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
/* 1077 */       if (!(allocator.newHandle() instanceof RecvByteBufAllocator.ExtendedHandle)) {
/* 1078 */         throw new IllegalArgumentException("allocator.newHandle() must return an object of type: " + RecvByteBufAllocator.ExtendedHandle.class);
/*      */       }
/*      */       
/* 1081 */       super.setRecvByteBufAllocator(allocator);
/* 1082 */       return (ChannelConfig)this;
/*      */     }
/*      */   }
/*      */   
/*      */   private void maybeAddChannelToReadCompletePendingQueue() {
/* 1087 */     if (!this.readCompletePending) {
/* 1088 */       this.readCompletePending = true;
/* 1089 */       addChannelToReadCompletePendingQueue();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void flush0(ChannelHandlerContext ctx) {
/* 1094 */     ctx.flush();
/*      */   }
/*      */   
/*      */   protected ChannelFuture write0(ChannelHandlerContext ctx, Object msg) {
/* 1098 */     ChannelPromise promise = ctx.newPromise();
/* 1099 */     ctx.write(msg, promise);
/* 1100 */     return (ChannelFuture)promise;
/*      */   }
/*      */   
/*      */   protected abstract boolean isParentReadInProgress();
/*      */   
/*      */   protected abstract void addChannelToReadCompletePendingQueue();
/*      */   
/*      */   protected abstract ChannelHandlerContext parentContext();
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\AbstractHttp2StreamChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */