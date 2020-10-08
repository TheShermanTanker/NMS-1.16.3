/*    */ package io.netty.handler.codec.http2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Http2EmptyDataFrameListener
/*    */   extends Http2FrameListenerDecorator
/*    */ {
/*    */   private final int maxConsecutiveEmptyFrames;
/*    */   private boolean violationDetected;
/*    */   private int emptyDataFrames;
/*    */   
/*    */   Http2EmptyDataFrameListener(Http2FrameListener listener, int maxConsecutiveEmptyFrames) {
/* 32 */     super(listener);
/* 33 */     this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositive(maxConsecutiveEmptyFrames, "maxConsecutiveEmptyFrames");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
/* 40 */     if (endOfStream || data.isReadable()) {
/* 41 */       this.emptyDataFrames = 0;
/* 42 */     } else if (this.emptyDataFrames++ == this.maxConsecutiveEmptyFrames && !this.violationDetected) {
/* 43 */       this.violationDetected = true;
/* 44 */       throw Http2Exception.connectionError(Http2Error.ENHANCE_YOUR_CALM, "Maximum number %d of empty data frames without end_of_stream flag received", new Object[] {
/*    */             
/* 46 */             Integer.valueOf(this.maxConsecutiveEmptyFrames)
/*    */           });
/*    */     } 
/* 49 */     return super.onDataRead(ctx, streamId, data, padding, endOfStream);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endStream) throws Http2Exception {
/* 55 */     this.emptyDataFrames = 0;
/* 56 */     super.onHeadersRead(ctx, streamId, headers, padding, endStream);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endStream) throws Http2Exception {
/* 62 */     this.emptyDataFrames = 0;
/* 63 */     super.onHeadersRead(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endStream);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\Http2EmptyDataFrameListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */