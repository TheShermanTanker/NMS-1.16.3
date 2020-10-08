/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public abstract class RemoteConnectionThread
/*    */   implements Runnable
/*    */ {
/* 11 */   private static final Logger LOGGER = LogManager.getLogger();
/* 12 */   private static final AtomicInteger e = new AtomicInteger(0);
/*    */   
/*    */   protected volatile boolean a;
/*    */   protected final String b;
/*    */   @Nullable
/*    */   protected Thread c;
/*    */   
/*    */   protected RemoteConnectionThread(String var0) {
/* 20 */     this.b = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized boolean a() {
/* 27 */     if (this.a) {
/* 28 */       return true;
/*    */     }
/* 30 */     this.a = true;
/* 31 */     this.c = new Thread(this, this.b + " #" + e.incrementAndGet());
/* 32 */     this.c.setUncaughtExceptionHandler(new ThreadNamedUncaughtExceptionHandler(LOGGER));
/* 33 */     this.c.start();
/* 34 */     LOGGER.info("Thread {} started", this.b);
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public synchronized void b() {
/* 39 */     this.a = false;
/* 40 */     if (null == this.c) {
/*    */       return;
/*    */     }
/* 43 */     int var0 = 0;
/* 44 */     while (this.c.isAlive()) {
/*    */       
/*    */       try {
/* 47 */         this.c.join(1000L);
/* 48 */         var0++;
/* 49 */         if (var0 >= 5) {
/*    */ 
/*    */ 
/*    */           
/* 53 */           LOGGER.warn("Waited {} seconds attempting force stop!", Integer.valueOf(var0)); continue;
/* 54 */         }  if (this.c.isAlive()) {
/* 55 */           LOGGER.warn("Thread {} ({}) failed to exit after {} second(s)", this, this.c.getState(), Integer.valueOf(var0), new Exception("Stack:"));
/*    */           
/* 57 */           this.c.interrupt();
/*    */         } 
/* 59 */       } catch (InterruptedException interruptedException) {}
/*    */     } 
/*    */ 
/*    */     
/* 63 */     LOGGER.info("Thread {} stopped", this.b);
/* 64 */     this.c = null;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 68 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RemoteConnectionThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */