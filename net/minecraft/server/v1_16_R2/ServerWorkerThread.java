/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ public class ServerWorkerThread extends Thread {
/*  6 */   private static final AtomicInteger threadId = new AtomicInteger(1);
/*    */   public ServerWorkerThread(Runnable target, String poolName, int prioritityModifier) {
/*  8 */     super(target, "Worker-" + poolName + "-" + threadId.getAndIncrement());
/*  9 */     setPriority(5 + prioritityModifier);
/* 10 */     setDaemon(true);
/* 11 */     setUncaughtExceptionHandler(SystemUtils::onThreadError);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ServerWorkerThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */