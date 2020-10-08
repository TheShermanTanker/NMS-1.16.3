/*    */ package com.destroystokyo.paper.util.map;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*    */ 
/*    */ public class Long2ObjectLinkedOpenHashMapFastCopy<V>
/*    */   extends Long2ObjectLinkedOpenHashMap<V> {
/*    */   public void copyFrom(Long2ObjectLinkedOpenHashMapFastCopy<V> map) {
/*  8 */     if (this.key.length != map.key.length) {
/*  9 */       this.key = null;
/* 10 */       this.key = new long[map.key.length];
/*    */     } 
/* 12 */     if (this.value.length != map.value.length) {
/* 13 */       this.value = null;
/*    */       
/* 15 */       this.value = new Object[map.value.length];
/*    */     } 
/* 17 */     if (this.link.length != map.link.length) {
/* 18 */       this.link = null;
/* 19 */       this.link = new long[map.link.length];
/*    */     } 
/* 21 */     System.arraycopy(map.key, 0, this.key, 0, map.key.length);
/* 22 */     System.arraycopy(map.value, 0, this.value, 0, map.value.length);
/* 23 */     System.arraycopy(map.link, 0, this.link, 0, map.link.length);
/* 24 */     this.size = map.size;
/* 25 */     this.mask = map.mask;
/* 26 */     this.first = map.first;
/* 27 */     this.last = map.last;
/* 28 */     this.n = map.n;
/* 29 */     this.maxFill = map.maxFill;
/* 30 */     this.containsNullKey = map.containsNullKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long2ObjectLinkedOpenHashMapFastCopy<V> clone() {
/* 35 */     Long2ObjectLinkedOpenHashMapFastCopy<V> clone = (Long2ObjectLinkedOpenHashMapFastCopy<V>)super.clone();
/* 36 */     clone.copyFrom(this);
/* 37 */     return clone;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\map\Long2ObjectLinkedOpenHashMapFastCopy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */