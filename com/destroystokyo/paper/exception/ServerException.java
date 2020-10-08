/*    */ package com.destroystokyo.paper.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerException
/*    */   extends Exception
/*    */ {
/*    */   public ServerException(String message) {
/*  9 */     super(message);
/*    */   }
/*    */   
/*    */   public ServerException(String message, Throwable cause) {
/* 13 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public ServerException(Throwable cause) {
/* 17 */     super(cause);
/*    */   }
/*    */   
/*    */   protected ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
/* 21 */     super(message, cause, enableSuppression, writableStackTrace);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\exception\ServerException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */