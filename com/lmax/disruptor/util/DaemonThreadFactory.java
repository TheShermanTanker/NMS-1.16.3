/*    */ package com.lmax.disruptor.util;
/*    */ 
/*    */ import java.util.concurrent.ThreadFactory;
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
/*    */ public enum DaemonThreadFactory
/*    */   implements ThreadFactory
/*    */ {
/* 25 */   INSTANCE;
/*    */ 
/*    */ 
/*    */   
/*    */   public Thread newThread(Runnable r) {
/* 30 */     Thread t = new Thread(r);
/* 31 */     t.setDaemon(true);
/* 32 */     return t;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disrupto\\util\DaemonThreadFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */