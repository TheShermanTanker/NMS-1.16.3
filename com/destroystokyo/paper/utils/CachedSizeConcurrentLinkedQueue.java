/*    */ package com.destroystokyo.paper.utils;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentLinkedQueue;
/*    */ import java.util.concurrent.atomic.LongAdder;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class CachedSizeConcurrentLinkedQueue<E> extends ConcurrentLinkedQueue<E> {
/*  9 */   private final LongAdder cachedSize = new LongAdder();
/*    */ 
/*    */   
/*    */   public boolean add(@NotNull E e) {
/* 13 */     boolean result = super.add(e);
/* 14 */     if (result) {
/* 15 */       this.cachedSize.increment();
/*    */     }
/* 17 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public E poll() {
/* 23 */     E result = super.poll();
/* 24 */     if (result != null) {
/* 25 */       this.cachedSize.decrement();
/*    */     }
/* 27 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 32 */     return this.cachedSize.intValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\utils\CachedSizeConcurrentLinkedQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */