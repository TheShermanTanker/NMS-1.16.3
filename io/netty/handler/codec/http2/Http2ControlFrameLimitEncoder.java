/*     */ package io.netty.handler.codec.http2;
/*     */ 
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelPromise;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
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
/*     */ final class Http2ControlFrameLimitEncoder
/*     */   extends DecoratingHttp2ConnectionEncoder
/*     */ {
/*  31 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(Http2ControlFrameLimitEncoder.class);
/*     */   private final int maxOutstandingControlFrames;
/*     */   
/*  34 */   private final ChannelFutureListener outstandingControlFramesListener = new ChannelFutureListener()
/*     */     {
/*     */       public void operationComplete(ChannelFuture future) {
/*  37 */         Http2ControlFrameLimitEncoder.this.outstandingControlFrames--;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private Http2LifecycleManager lifecycleManager;
/*     */   
/*     */   Http2ControlFrameLimitEncoder(Http2ConnectionEncoder delegate, int maxOutstandingControlFrames) {
/*  45 */     super(delegate);
/*  46 */     this.maxOutstandingControlFrames = ObjectUtil.checkPositive(maxOutstandingControlFrames, "maxOutstandingControlFrames");
/*     */   }
/*     */   private int outstandingControlFrames;
/*     */   private boolean limitReached;
/*     */   
/*     */   public void lifecycleManager(Http2LifecycleManager lifecycleManager) {
/*  52 */     this.lifecycleManager = lifecycleManager;
/*  53 */     super.lifecycleManager(lifecycleManager);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture writeSettingsAck(ChannelHandlerContext ctx, ChannelPromise promise) {
/*  58 */     ChannelPromise newPromise = handleOutstandingControlFrames(ctx, promise);
/*  59 */     if (newPromise == null) {
/*  60 */       return (ChannelFuture)promise;
/*     */     }
/*  62 */     return super.writeSettingsAck(ctx, newPromise);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelFuture writePing(ChannelHandlerContext ctx, boolean ack, long data, ChannelPromise promise) {
/*  68 */     if (ack) {
/*  69 */       ChannelPromise newPromise = handleOutstandingControlFrames(ctx, promise);
/*  70 */       if (newPromise == null) {
/*  71 */         return (ChannelFuture)promise;
/*     */       }
/*  73 */       return super.writePing(ctx, ack, data, newPromise);
/*     */     } 
/*  75 */     return super.writePing(ctx, ack, data, promise);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelFuture writeRstStream(ChannelHandlerContext ctx, int streamId, long errorCode, ChannelPromise promise) {
/*  81 */     ChannelPromise newPromise = handleOutstandingControlFrames(ctx, promise);
/*  82 */     if (newPromise == null) {
/*  83 */       return (ChannelFuture)promise;
/*     */     }
/*  85 */     return super.writeRstStream(ctx, streamId, errorCode, newPromise);
/*     */   }
/*     */   
/*     */   private ChannelPromise handleOutstandingControlFrames(ChannelHandlerContext ctx, ChannelPromise promise) {
/*  89 */     if (!this.limitReached) {
/*  90 */       if (this.outstandingControlFrames == this.maxOutstandingControlFrames)
/*     */       {
/*  92 */         ctx.flush();
/*     */       }
/*  94 */       if (this.outstandingControlFrames == this.maxOutstandingControlFrames) {
/*  95 */         this.limitReached = true;
/*  96 */         Http2Exception exception = Http2Exception.connectionError(Http2Error.ENHANCE_YOUR_CALM, "Maximum number %d of outstanding control frames reached", new Object[] {
/*  97 */               Integer.valueOf(this.maxOutstandingControlFrames) });
/*  98 */         logger.info("Maximum number {} of outstanding control frames reached. Closing channel {}", new Object[] {
/*  99 */               Integer.valueOf(this.maxOutstandingControlFrames), ctx.channel(), exception
/*     */             });
/*     */         
/* 102 */         this.lifecycleManager.onError(ctx, true, exception);
/* 103 */         ctx.close();
/*     */       } 
/* 105 */       this.outstandingControlFrames++;
/*     */ 
/*     */ 
/*     */       
/* 109 */       return promise.unvoid().addListener((GenericFutureListener)this.outstandingControlFramesListener);
/*     */     } 
/* 111 */     return promise;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\Http2ControlFrameLimitEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */