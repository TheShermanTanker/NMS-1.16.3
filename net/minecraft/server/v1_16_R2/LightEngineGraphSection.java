/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public abstract class LightEngineGraphSection
/*    */   extends LightEngineGraph {
/*    */   protected LightEngineGraphSection(int i, int j, int k) {
/*  6 */     super(i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(long i) {
/* 11 */     return (i == Long.MAX_VALUE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(long i, int j, boolean flag) {
/* 17 */     int x = (int)(i >> 42L);
/* 18 */     int y = (int)(i << 44L >> 44L);
/* 19 */     int z = (int)(i << 22L >> 42L);
/*    */     
/* 21 */     for (int k = -1; k <= 1; k++) {
/* 22 */       for (int l = -1; l <= 1; l++) {
/* 23 */         for (int i1 = -1; i1 <= 1; i1++) {
/* 24 */           if (k != 0 || l != 0 || i1 != 0) {
/* 25 */             long j1 = ((x + k) & 0x3FFFFFL) << 42L | (y + l) & 0xFFFFFL | ((z + i1) & 0x3FFFFFL) << 20L;
/*    */ 
/*    */             
/* 28 */             b(i, j1, j, flag);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int a(long i, long j, int k) {
/* 38 */     int l = k;
/*    */ 
/*    */     
/* 41 */     int x = (int)(i >> 42L);
/* 42 */     int y = (int)(i << 44L >> 44L);
/* 43 */     int z = (int)(i << 22L >> 42L);
/*    */     
/* 45 */     for (int i1 = -1; i1 <= 1; i1++) {
/* 46 */       for (int j1 = -1; j1 <= 1; j1++) {
/* 47 */         for (int k1 = -1; k1 <= 1; k1++) {
/* 48 */           long l1 = ((x + i1) & 0x3FFFFFL) << 42L | (y + j1) & 0xFFFFFL | ((z + k1) & 0x3FFFFFL) << 20L;
/*    */           
/* 50 */           if (l1 == i) {
/* 51 */             l1 = Long.MAX_VALUE;
/*    */           }
/*    */           
/* 54 */           if (l1 != j) {
/* 55 */             int i2 = b(l1, i, c(l1));
/*    */             
/* 57 */             if (l > i2) {
/* 58 */               l = i2;
/*    */             }
/*    */             
/* 61 */             if (l == 0) {
/* 62 */               return l;
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return l;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int b(long i, long j, int k) {
/* 74 */     return (i == Long.MAX_VALUE) ? b(j) : (k + 1);
/*    */   }
/*    */   
/*    */   protected abstract int b(long paramLong);
/*    */   
/*    */   public void b(long i, int j, boolean flag) {
/* 80 */     a(Long.MAX_VALUE, i, j, flag);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineGraphSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */