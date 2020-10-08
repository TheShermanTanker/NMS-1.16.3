/*    */ package com.destroystokyo.paper.util.concurrent;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WeakSeqLock
/*    */ {
/* 12 */   protected final AtomicLong lock = new AtomicLong();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void acquireWrite() {
/* 20 */     this.lock.lazySet(this.lock.get() + 1L);
/*    */   }
/*    */   
/*    */   public boolean canRead(long read) {
/* 24 */     return ((read & 0x1L) == 0L);
/*    */   }
/*    */   
/*    */   public boolean tryAcquireWrite() {
/* 28 */     acquireWrite();
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void releaseWrite() {
/* 34 */     long lock = this.lock.get();
/* 35 */     this.lock.lazySet(lock + 1L);
/*    */   }
/*    */ 
/*    */   
/*    */   public void abortWrite() {
/* 40 */     long lock = this.lock.get();
/* 41 */     this.lock.lazySet(lock ^ 0x1L);
/*    */   }
/*    */   
/*    */   public long acquireRead() {
/* 45 */     int failures = 0;
/*    */     
/*    */     long curr;
/* 48 */     for (curr = this.lock.get(); !canRead(curr); curr = this.lock.get()) {
/*    */ 
/*    */       
/* 51 */       if (++failures > 5000) {
/* 52 */         Thread.yield();
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 58 */     return curr;
/*    */   }
/*    */   
/*    */   public boolean tryReleaseRead(long read) {
/* 62 */     return (this.lock.get() == read);
/*    */   }
/*    */   
/*    */   public long getSequentialCounter() {
/* 66 */     return this.lock.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\concurrent\WeakSeqLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */