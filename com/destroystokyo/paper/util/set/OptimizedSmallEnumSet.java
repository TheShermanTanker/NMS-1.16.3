/*    */ package com.destroystokyo.paper.util.set;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class OptimizedSmallEnumSet<E extends Enum<E>>
/*    */ {
/*    */   private final Class<E> enumClass;
/*    */   private long backingSet;
/*    */   
/*    */   public OptimizedSmallEnumSet(Class<E> clazz) {
/* 14 */     if (clazz == null) {
/* 15 */       throw new IllegalArgumentException("Null class");
/*    */     }
/* 17 */     if (!clazz.isEnum()) {
/* 18 */       throw new IllegalArgumentException("Class must be enum, not " + clazz.getCanonicalName());
/*    */     }
/* 20 */     this.enumClass = clazz;
/*    */   }
/*    */   
/*    */   public boolean addUnchecked(E element) {
/* 24 */     int ordinal = element.ordinal();
/* 25 */     long key = 1L << ordinal;
/*    */     
/* 27 */     long prev = this.backingSet;
/* 28 */     this.backingSet = prev | key;
/*    */     
/* 30 */     return ((prev & key) == 0L);
/*    */   }
/*    */   
/*    */   public boolean removeUnchecked(E element) {
/* 34 */     int ordinal = element.ordinal();
/* 35 */     long key = 1L << ordinal;
/*    */     
/* 37 */     long prev = this.backingSet;
/* 38 */     this.backingSet = prev & (key ^ 0xFFFFFFFFFFFFFFFFL);
/*    */     
/* 40 */     return ((prev & key) != 0L);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 44 */     this.backingSet = 0L;
/*    */   }
/*    */   
/*    */   public int size() {
/* 48 */     return Long.bitCount(this.backingSet);
/*    */   }
/*    */   
/*    */   public void addAllUnchecked(Collection<E> enums) {
/* 52 */     for (Enum enum_ : enums) {
/* 53 */       if (enum_ == null) {
/* 54 */         throw new NullPointerException("Null element");
/*    */       }
/* 56 */       this.backingSet |= 1L << enum_.ordinal();
/*    */     } 
/*    */   }
/*    */   
/*    */   public long getBackingSet() {
/* 61 */     return this.backingSet;
/*    */   }
/*    */   
/*    */   public boolean hasCommonElements(OptimizedSmallEnumSet<E> other) {
/* 65 */     return ((other.backingSet & this.backingSet) != 0L);
/*    */   }
/*    */   
/*    */   public boolean hasElement(E element) {
/* 69 */     return ((this.backingSet & 1L << element.ordinal()) != 0L);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\set\OptimizedSmallEnumSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */