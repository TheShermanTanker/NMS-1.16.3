/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ 
/*    */ public class RegionFileBitSet
/*    */ {
/*  7 */   private final BitSet a = new BitSet(); private final BitSet getBitset() { return this.a; }
/*    */ 
/*    */   
/*    */   public final void allocate(int from, int length) {
/* 11 */     a(from, length);
/*    */   } public void a(int i, int j) {
/* 13 */     this.a.set(i, i + j);
/*    */   }
/*    */   public final void free(int from, int length) {
/* 16 */     b(from, length);
/*    */   } public void b(int i, int j) {
/* 18 */     this.a.clear(i, i + j);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void copyFrom(RegionFileBitSet other) {
/* 23 */     BitSet thisBitset = getBitset();
/* 24 */     BitSet otherBitset = other.getBitset();
/*    */     
/* 26 */     for (int i = 0; i < Math.max(thisBitset.size(), otherBitset.size()); i++) {
/* 27 */       thisBitset.set(i, otherBitset.get(i));
/*    */     }
/*    */   }
/*    */   
/*    */   public final boolean tryAllocate(int from, int length) {
/* 32 */     BitSet bitset = getBitset();
/* 33 */     int firstSet = bitset.nextSetBit(from);
/* 34 */     if (firstSet > 0 && firstSet < from + length) {
/* 35 */       return false;
/*    */     }
/* 37 */     bitset.set(from, from + length);
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   public final int allocateNewSpace(int requiredLength) {
/* 42 */     return a(requiredLength);
/*    */   } public int a(int i) {
/* 44 */     int j = 0;
/*    */     
/*    */     while (true) {
/* 47 */       int k = this.a.nextClearBit(j);
/* 48 */       int l = this.a.nextSetBit(k);
/*    */       
/* 50 */       if (l == -1 || l - k >= i) {
/* 51 */         a(k, i);
/* 52 */         return k;
/*    */       } 
/*    */       
/* 55 */       j = l;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegionFileBitSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */