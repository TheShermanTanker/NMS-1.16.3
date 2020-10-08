/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ public final class TimeoutException
/*    */   extends Exception
/*    */ {
/*  6 */   public static final TimeoutException INSTANCE = new TimeoutException();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 16 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\TimeoutException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */