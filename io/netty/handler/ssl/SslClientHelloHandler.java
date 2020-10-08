/*     */ package io.netty.handler.ssl;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufUtil;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelOutboundHandler;
/*     */ import io.netty.channel.ChannelPromise;
/*     */ import io.netty.handler.codec.ByteToMessageDecoder;
/*     */ import io.netty.handler.codec.DecoderException;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.FutureListener;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.List;
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
/*     */ public abstract class SslClientHelloHandler<T>
/*     */   extends ByteToMessageDecoder
/*     */   implements ChannelOutboundHandler
/*     */ {
/*  40 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(SslClientHelloHandler.class);
/*     */   
/*     */   private boolean handshakeFailed;
/*     */   
/*     */   private boolean suppressRead;
/*     */   private boolean readPending;
/*     */   private ByteBuf handshakeBuffer;
/*     */   
/*     */   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
/*  49 */     if (!this.suppressRead && !this.handshakeFailed) {
/*     */       try {
/*  51 */         int readerIndex = in.readerIndex();
/*  52 */         int readableBytes = in.readableBytes();
/*  53 */         int handshakeLength = -1;
/*     */ 
/*     */         
/*  56 */         while (readableBytes >= 5) {
/*  57 */           int len, majorVersion, contentType = in.getUnsignedByte(readerIndex);
/*  58 */           switch (contentType) {
/*     */             
/*     */             case 20:
/*     */             case 21:
/*  62 */               len = SslUtils.getEncryptedPacketLength(in, readerIndex);
/*     */ 
/*     */               
/*  65 */               if (len == -2) {
/*  66 */                 this.handshakeFailed = true;
/*     */                 
/*  68 */                 NotSslRecordException e = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(in));
/*  69 */                 in.skipBytes(in.readableBytes());
/*  70 */                 ctx.fireUserEventTriggered(new SniCompletionEvent(e));
/*  71 */                 SslUtils.handleHandshakeFailure(ctx, e, true);
/*  72 */                 throw e;
/*     */               } 
/*  74 */               if (len == -1) {
/*     */                 return;
/*     */               }
/*     */ 
/*     */               
/*  79 */               select(ctx, null);
/*     */               return;
/*     */             case 22:
/*  82 */               majorVersion = in.getUnsignedByte(readerIndex + 1);
/*     */               
/*  84 */               if (majorVersion == 3) {
/*  85 */                 int packetLength = in.getUnsignedShort(readerIndex + 3) + 5;
/*     */ 
/*     */                 
/*  88 */                 if (readableBytes < packetLength) {
/*     */                   return;
/*     */                 }
/*  91 */                 if (packetLength == 5) {
/*  92 */                   select(ctx, null);
/*     */                   
/*     */                   return;
/*     */                 } 
/*  96 */                 int endOffset = readerIndex + packetLength;
/*     */ 
/*     */                 
/*  99 */                 if (handshakeLength == -1) {
/* 100 */                   if (readerIndex + 4 > endOffset) {
/*     */                     return;
/*     */                   }
/*     */ 
/*     */                   
/* 105 */                   int handshakeType = in.getUnsignedByte(readerIndex + 5);
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 110 */                   if (handshakeType != 1) {
/* 111 */                     select(ctx, null);
/*     */ 
/*     */                     
/*     */                     return;
/*     */                   } 
/*     */                   
/* 117 */                   handshakeLength = in.getUnsignedMedium(readerIndex + 5 + 1);
/*     */ 
/*     */ 
/*     */                   
/* 121 */                   readerIndex += 4;
/* 122 */                   packetLength -= 4;
/*     */                   
/* 124 */                   if (handshakeLength + 4 + 5 <= packetLength) {
/*     */ 
/*     */                     
/* 127 */                     readerIndex += 5;
/* 128 */                     select(ctx, in.retainedSlice(readerIndex, handshakeLength));
/*     */                     return;
/*     */                   } 
/* 131 */                   if (this.handshakeBuffer == null) {
/* 132 */                     this.handshakeBuffer = ctx.alloc().buffer(handshakeLength);
/*     */                   } else {
/*     */                     
/* 135 */                     this.handshakeBuffer.clear();
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */ 
/*     */                 
/* 141 */                 this.handshakeBuffer.writeBytes(in, readerIndex + 5, packetLength - 5);
/*     */                 
/* 143 */                 readerIndex += packetLength;
/* 144 */                 readableBytes -= packetLength;
/* 145 */                 if (handshakeLength <= this.handshakeBuffer.readableBytes()) {
/* 146 */                   ByteBuf clientHello = this.handshakeBuffer.setIndex(0, handshakeLength);
/* 147 */                   this.handshakeBuffer = null;
/*     */                   
/* 149 */                   select(ctx, clientHello);
/*     */                   return;
/*     */                 } 
/*     */                 continue;
/*     */               } 
/*     */               break;
/*     */           } 
/*     */           
/* 157 */           select(ctx, null);
/*     */           
/*     */           return;
/*     */         } 
/* 161 */       } catch (NotSslRecordException e) {
/*     */         
/* 163 */         throw e;
/* 164 */       } catch (Exception e) {
/*     */         
/* 166 */         if (logger.isDebugEnabled()) {
/* 167 */           logger.debug("Unexpected client hello packet: " + ByteBufUtil.hexDump(in), e);
/*     */         }
/* 169 */         select(ctx, null);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void releaseHandshakeBuffer() {
/* 175 */     releaseIfNotNull(this.handshakeBuffer);
/* 176 */     this.handshakeBuffer = null;
/*     */   }
/*     */   
/*     */   private static void releaseIfNotNull(ByteBuf buffer) {
/* 180 */     if (buffer != null) {
/* 181 */       buffer.release();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void select(final ChannelHandlerContext ctx, ByteBuf clientHello) throws Exception {
/*     */     try {
/* 188 */       Future<T> future = lookup(ctx, clientHello);
/* 189 */       if (future.isDone()) {
/* 190 */         onLookupComplete(ctx, future);
/*     */       } else {
/* 192 */         this.suppressRead = true;
/* 193 */         final ByteBuf finalClientHello = clientHello;
/* 194 */         future.addListener((GenericFutureListener)new FutureListener<T>()
/*     */             {
/*     */               public void operationComplete(Future<T> future) {
/* 197 */                 SslClientHelloHandler.releaseIfNotNull(finalClientHello);
/*     */                 try {
/* 199 */                   SslClientHelloHandler.this.suppressRead = false;
/*     */                   try {
/* 201 */                     SslClientHelloHandler.this.onLookupComplete(ctx, future);
/* 202 */                   } catch (DecoderException err) {
/* 203 */                     ctx.fireExceptionCaught((Throwable)err);
/* 204 */                   } catch (Exception cause) {
/* 205 */                     ctx.fireExceptionCaught((Throwable)new DecoderException(cause));
/* 206 */                   } catch (Throwable cause) {
/* 207 */                     ctx.fireExceptionCaught(cause);
/*     */                   } 
/*     */                 } finally {
/* 210 */                   if (SslClientHelloHandler.this.readPending) {
/* 211 */                     SslClientHelloHandler.this.readPending = false;
/* 212 */                     ctx.read();
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             });
/*     */ 
/*     */         
/* 219 */         clientHello = null;
/*     */       } 
/* 221 */     } catch (Throwable cause) {
/* 222 */       PlatformDependent.throwException(cause);
/*     */     } finally {
/* 224 */       releaseIfNotNull(clientHello);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
/* 230 */     releaseHandshakeBuffer();
/*     */     
/* 232 */     super.handlerRemoved0(ctx);
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
/*     */   public void read(ChannelHandlerContext ctx) throws Exception {
/* 270 */     if (this.suppressRead) {
/* 271 */       this.readPending = true;
/*     */     } else {
/* 273 */       ctx.read();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 279 */     ctx.bind(localAddress, promise);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 285 */     ctx.connect(remoteAddress, localAddress, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 290 */     ctx.disconnect(promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 295 */     ctx.close(promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 300 */     ctx.deregister(promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 305 */     ctx.write(msg, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush(ChannelHandlerContext ctx) throws Exception {
/* 310 */     ctx.flush();
/*     */   }
/*     */   
/*     */   protected abstract Future<T> lookup(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf) throws Exception;
/*     */   
/*     */   protected abstract void onLookupComplete(ChannelHandlerContext paramChannelHandlerContext, Future<T> paramFuture) throws Exception;
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\ssl\SslClientHelloHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */