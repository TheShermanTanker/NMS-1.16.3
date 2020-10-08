/*     */ package io.netty.handler.codec.http2;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Queue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Http2MultiplexHandler
/*     */   extends Http2ChannelDuplexHandler
/*     */ {
/*  88 */   static final ChannelFutureListener CHILD_CHANNEL_REGISTRATION_LISTENER = new ChannelFutureListener()
/*     */     {
/*     */       public void operationComplete(ChannelFuture future) {
/*  91 */         Http2MultiplexHandler.registerDone(future);
/*     */       }
/*     */     };
/*     */   
/*     */   private final ChannelHandler inboundStreamHandler;
/*     */   private final ChannelHandler upgradeStreamHandler;
/*  97 */   private final Queue<AbstractHttp2StreamChannel> readCompletePendingQueue = new MaxCapacityQueue<AbstractHttp2StreamChannel>(new ArrayDeque<AbstractHttp2StreamChannel>(8), 100);
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean parentReadInProgress;
/*     */ 
/*     */ 
/*     */   
/*     */   private int idCount;
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile ChannelHandlerContext ctx;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Http2MultiplexHandler(ChannelHandler inboundStreamHandler) {
/* 115 */     this(inboundStreamHandler, (ChannelHandler)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Http2MultiplexHandler(ChannelHandler inboundStreamHandler, ChannelHandler upgradeStreamHandler) {
/* 127 */     this.inboundStreamHandler = (ChannelHandler)ObjectUtil.checkNotNull(inboundStreamHandler, "inboundStreamHandler");
/* 128 */     this.upgradeStreamHandler = upgradeStreamHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void registerDone(ChannelFuture future) {
/* 135 */     if (!future.isSuccess()) {
/* 136 */       Channel childChannel = future.channel();
/* 137 */       if (childChannel.isRegistered()) {
/* 138 */         childChannel.close();
/*     */       } else {
/* 140 */         childChannel.unsafe().closeForcibly();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handlerAdded0(ChannelHandlerContext ctx) {
/* 147 */     if (ctx.executor() != ctx.channel().eventLoop()) {
/* 148 */       throw new IllegalStateException("EventExecutor must be EventLoop of Channel");
/*     */     }
/* 150 */     this.ctx = ctx;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handlerRemoved0(ChannelHandlerContext ctx) {
/* 155 */     this.readCompletePendingQueue.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 160 */     this.parentReadInProgress = true;
/* 161 */     if (msg instanceof Http2StreamFrame) {
/* 162 */       if (msg instanceof Http2WindowUpdateFrame) {
/*     */         return;
/*     */       }
/*     */       
/* 166 */       Http2StreamFrame streamFrame = (Http2StreamFrame)msg;
/*     */       
/* 168 */       Http2FrameCodec.DefaultHttp2FrameStream s = (Http2FrameCodec.DefaultHttp2FrameStream)streamFrame.stream();
/*     */       
/* 170 */       AbstractHttp2StreamChannel channel = (AbstractHttp2StreamChannel)s.attachment;
/* 171 */       if (msg instanceof Http2ResetFrame) {
/*     */ 
/*     */         
/* 174 */         channel.pipeline().fireUserEventTriggered(msg);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 179 */         channel.fireChildRead(streamFrame);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 184 */     if (msg instanceof Http2GoAwayFrame)
/*     */     {
/*     */       
/* 187 */       onHttp2GoAwayFrame(ctx, (Http2GoAwayFrame)msg);
/*     */     }
/*     */ 
/*     */     
/* 191 */     ctx.fireChannelRead(msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
/* 196 */     if (ctx.channel().isWritable())
/*     */     {
/*     */       
/* 199 */       forEachActiveStream(AbstractHttp2StreamChannel.WRITABLE_VISITOR);
/*     */     }
/*     */     
/* 202 */     ctx.fireChannelWritabilityChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
/* 207 */     if (evt instanceof Http2FrameStreamEvent) {
/* 208 */       Http2FrameStreamEvent event = (Http2FrameStreamEvent)evt;
/* 209 */       Http2FrameCodec.DefaultHttp2FrameStream stream = (Http2FrameCodec.DefaultHttp2FrameStream)event.stream();
/* 210 */       if (event.type() == Http2FrameStreamEvent.Type.State) {
/* 211 */         AbstractHttp2StreamChannel ch; ChannelFuture future; AbstractHttp2StreamChannel channel; switch (stream.state()) {
/*     */           case HALF_CLOSED_LOCAL:
/* 213 */             if (stream.id() != 1) {
/*     */               break;
/*     */             }
/*     */ 
/*     */ 
/*     */           
/*     */           case HALF_CLOSED_REMOTE:
/*     */           case OPEN:
/* 221 */             if (stream.attachment != null) {
/*     */               break;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 227 */             if (stream.id() == 1 && !isServer(ctx)) {
/*     */               
/* 229 */               if (this.upgradeStreamHandler == null) {
/* 230 */                 throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Client is misconfigured for upgrade requests", new Object[0]);
/*     */               }
/*     */               
/* 233 */               ch = new Http2MultiplexHandlerStreamChannel(stream, this.upgradeStreamHandler);
/* 234 */               ch.closeOutbound();
/*     */             } else {
/* 236 */               ch = new Http2MultiplexHandlerStreamChannel(stream, this.inboundStreamHandler);
/*     */             } 
/* 238 */             future = ctx.channel().eventLoop().register(ch);
/* 239 */             if (future.isDone()) {
/* 240 */               registerDone(future); break;
/*     */             } 
/* 242 */             future.addListener((GenericFutureListener)CHILD_CHANNEL_REGISTRATION_LISTENER);
/*     */             break;
/*     */           
/*     */           case CLOSED:
/* 246 */             channel = (AbstractHttp2StreamChannel)stream.attachment;
/* 247 */             if (channel != null) {
/* 248 */               channel.streamClosed();
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/*     */       return;
/*     */     } 
/* 258 */     ctx.fireUserEventTriggered(evt);
/*     */   }
/*     */ 
/*     */   
/*     */   Http2StreamChannel newOutboundStream() {
/* 263 */     return new Http2MultiplexHandlerStreamChannel((Http2FrameCodec.DefaultHttp2FrameStream)newStream(), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
/* 268 */     if (cause instanceof Http2FrameStreamException) {
/* 269 */       Http2FrameStreamException exception = (Http2FrameStreamException)cause;
/* 270 */       Http2FrameStream stream = exception.stream();
/* 271 */       AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
/*     */       
/*     */       try {
/* 274 */         childChannel.pipeline().fireExceptionCaught(cause.getCause());
/*     */       } finally {
/* 276 */         childChannel.unsafe().closeForcibly();
/*     */       } 
/*     */       return;
/*     */     } 
/* 280 */     ctx.fireExceptionCaught(cause);
/*     */   }
/*     */   
/*     */   private static boolean isServer(ChannelHandlerContext ctx) {
/* 284 */     return ctx.channel().parent() instanceof io.netty.channel.ServerChannel;
/*     */   }
/*     */   
/*     */   private void onHttp2GoAwayFrame(ChannelHandlerContext ctx, final Http2GoAwayFrame goAwayFrame) {
/*     */     try {
/* 289 */       final boolean server = isServer(ctx);
/* 290 */       forEachActiveStream(new Http2FrameStreamVisitor()
/*     */           {
/*     */             public boolean visit(Http2FrameStream stream) {
/* 293 */               int streamId = stream.id();
/* 294 */               if (streamId > goAwayFrame.lastStreamId() && Http2CodecUtil.isStreamIdValid(streamId, server)) {
/* 295 */                 AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
/*     */                 
/* 297 */                 childChannel.pipeline().fireUserEventTriggered(goAwayFrame.retainedDuplicate());
/*     */               } 
/* 299 */               return true;
/*     */             }
/*     */           });
/* 302 */     } catch (Http2Exception e) {
/* 303 */       ctx.fireExceptionCaught(e);
/* 304 */       ctx.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
/* 313 */     processPendingReadCompleteQueue();
/* 314 */     ctx.fireChannelReadComplete();
/*     */   }
/*     */   
/*     */   private void processPendingReadCompleteQueue() {
/* 318 */     this.parentReadInProgress = true;
/*     */ 
/*     */ 
/*     */     
/* 322 */     AbstractHttp2StreamChannel childChannel = this.readCompletePendingQueue.poll();
/* 323 */     if (childChannel != null) {
/*     */       try {
/*     */         do {
/* 326 */           childChannel.fireChildReadComplete();
/* 327 */           childChannel = this.readCompletePendingQueue.poll();
/* 328 */         } while (childChannel != null);
/*     */       } finally {
/* 330 */         this.parentReadInProgress = false;
/* 331 */         this.readCompletePendingQueue.clear();
/* 332 */         this.ctx.flush();
/*     */       } 
/*     */     } else {
/* 335 */       this.parentReadInProgress = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class Http2MultiplexHandlerStreamChannel
/*     */     extends AbstractHttp2StreamChannel {
/*     */     Http2MultiplexHandlerStreamChannel(Http2FrameCodec.DefaultHttp2FrameStream stream, ChannelHandler inboundHandler) {
/* 342 */       super(stream, ++Http2MultiplexHandler.this.idCount, inboundHandler);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isParentReadInProgress() {
/* 347 */       return Http2MultiplexHandler.this.parentReadInProgress;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void addChannelToReadCompletePendingQueue() {
/* 354 */       while (!Http2MultiplexHandler.this.readCompletePendingQueue.offer(this)) {
/* 355 */         Http2MultiplexHandler.this.processPendingReadCompleteQueue();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected ChannelHandlerContext parentContext() {
/* 361 */       return Http2MultiplexHandler.this.ctx;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\Http2MultiplexHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */