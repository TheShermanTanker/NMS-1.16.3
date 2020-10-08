/*    */ package com.tuinity.tuinity.util;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ public final class TickThread
/*    */   extends Thread {
/*  8 */   public static final boolean STRICT_THREAD_CHECKS = Boolean.getBoolean("tuinity.strict-thread-checks");
/*    */   
/*    */   static {
/* 11 */     if (STRICT_THREAD_CHECKS)
/* 12 */       MinecraftServer.LOGGER.warn("Strict thread checks enabled - performance may suffer"); 
/*    */   }
/*    */   public final int id;
/*    */   
/*    */   public static void softEnsureTickThread(String reason) {
/* 17 */     if (!STRICT_THREAD_CHECKS) {
/*    */       return;
/*    */     }
/* 20 */     ensureTickThread(reason);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void ensureTickThread(String reason) {
/* 25 */     if (!Bukkit.isPrimaryThread()) {
/* 26 */       MinecraftServer.LOGGER.fatal("Thread " + Thread.currentThread().getName() + " failed main thread check: " + reason, new Throwable());
/* 27 */       throw new IllegalStateException(reason);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TickThread(Runnable run, String name, int id) {
/* 34 */     super(run, name);
/* 35 */     this.id = id;
/*    */   }
/*    */   
/*    */   public static TickThread getCurrentTickThread() {
/* 39 */     return (TickThread)Thread.currentThread();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\tuinity\tuinit\\util\TickThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */