/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
/*    */   private final Logger a;
/*    */   
/*    */   public DefaultUncaughtExceptionHandler(Logger var0) {
/*  9 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void uncaughtException(Thread var0, Throwable var1) {
/* 14 */     this.a.error("Caught previously unhandled exception :", var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefaultUncaughtExceptionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */