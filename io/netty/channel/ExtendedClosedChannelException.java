/*    */ package io.netty.channel;
/*    */ 
/*    */ import java.nio.channels.ClosedChannelException;
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
/*    */ final class ExtendedClosedChannelException
/*    */   extends ClosedChannelException
/*    */ {
/*    */   ExtendedClosedChannelException(Throwable cause) {
/* 23 */     if (cause != null) {
/* 24 */       initCause(cause);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Throwable fillInStackTrace() {
/* 30 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channel\ExtendedClosedChannelException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */