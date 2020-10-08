/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import java.util.concurrent.ExecutionException;
/*    */ 
/*    */ public abstract class Waitable<T>
/*    */   implements Runnable {
/*    */   private enum Status {
/*  8 */     WAITING,
/*  9 */     RUNNING,
/* 10 */     FINISHED;
/*    */   }
/* 12 */   Throwable t = null;
/* 13 */   T value = null;
/* 14 */   Status status = Status.WAITING;
/*    */ 
/*    */   
/*    */   public final void run() {
/* 18 */     synchronized (this) {
/* 19 */       if (this.status != Status.WAITING) {
/* 20 */         throw new IllegalStateException("Invalid state " + this.status);
/*    */       }
/* 22 */       this.status = Status.RUNNING;
/*    */     } 
/*    */     try {
/* 25 */       this.value = evaluate();
/* 26 */     } catch (Throwable t) {
/* 27 */       this.t = t;
/*    */     } finally {
/* 29 */       synchronized (this) {
/* 30 */         this.status = Status.FINISHED;
/* 31 */         notifyAll();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract T evaluate();
/*    */   
/*    */   public synchronized T get() throws InterruptedException, ExecutionException {
/* 39 */     while (this.status != Status.FINISHED) {
/* 40 */       wait();
/*    */     }
/* 42 */     if (this.t != null) {
/* 43 */       throw new ExecutionException(this.t);
/*    */     }
/* 45 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\Waitable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */