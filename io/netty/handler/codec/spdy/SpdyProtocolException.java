/*    */ package io.netty.handler.codec.spdy;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpdyProtocolException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 7870000537743847264L;
/*    */   
/*    */   public SpdyProtocolException() {}
/*    */   
/*    */   public SpdyProtocolException(String message, Throwable cause) {
/* 31 */     super(message, cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SpdyProtocolException(String message) {
/* 38 */     super(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SpdyProtocolException(Throwable cause) {
/* 45 */     super(cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\spdy\SpdyProtocolException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */