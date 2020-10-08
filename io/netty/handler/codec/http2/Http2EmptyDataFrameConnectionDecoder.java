/*    */ package io.netty.handler.codec.http2;
/*    */ 
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
/*    */ final class Http2EmptyDataFrameConnectionDecoder
/*    */   extends DecoratingHttp2ConnectionDecoder
/*    */ {
/*    */   private final int maxConsecutiveEmptyFrames;
/*    */   
/*    */   Http2EmptyDataFrameConnectionDecoder(Http2ConnectionDecoder delegate, int maxConsecutiveEmptyFrames) {
/* 28 */     super(delegate);
/* 29 */     this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositive(maxConsecutiveEmptyFrames, "maxConsecutiveEmptyFrames");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void frameListener(Http2FrameListener listener) {
/* 35 */     if (listener != null) {
/* 36 */       super.frameListener(new Http2EmptyDataFrameListener(listener, this.maxConsecutiveEmptyFrames));
/*    */     } else {
/* 38 */       super.frameListener(null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Http2FrameListener frameListener() {
/* 44 */     Http2FrameListener frameListener = frameListener0();
/*    */     
/* 46 */     if (frameListener instanceof Http2EmptyDataFrameListener) {
/* 47 */       return ((Http2EmptyDataFrameListener)frameListener).listener;
/*    */     }
/* 49 */     return frameListener;
/*    */   }
/*    */ 
/*    */   
/*    */   Http2FrameListener frameListener0() {
/* 54 */     return super.frameListener();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\Http2EmptyDataFrameConnectionDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */