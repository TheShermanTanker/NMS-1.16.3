/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public abstract class ChunkMap
/*    */   extends LightEngineGraph
/*    */ {
/*    */   protected ChunkMap(int var0, int var1, int var2) {
/*  8 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(long var0) {
/* 13 */     return (var0 == ChunkCoordIntPair.a);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(long var0, int var2, boolean var3) {
/* 18 */     ChunkCoordIntPair var4 = new ChunkCoordIntPair(var0);
/* 19 */     int var5 = var4.x;
/* 20 */     int var6 = var4.z;
/* 21 */     for (int var7 = -1; var7 <= 1; var7++) {
/* 22 */       for (int var8 = -1; var8 <= 1; var8++) {
/* 23 */         long var9 = ChunkCoordIntPair.pair(var5 + var7, var6 + var8);
/* 24 */         if (var9 != var0)
/*    */         {
/*    */           
/* 27 */           b(var0, var9, var2, var3);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected int a(long var0, long var2, int var4) {
/* 34 */     int var5 = var4;
/* 35 */     ChunkCoordIntPair var6 = new ChunkCoordIntPair(var0);
/* 36 */     int var7 = var6.x;
/* 37 */     int var8 = var6.z;
/* 38 */     for (int var9 = -1; var9 <= 1; var9++) {
/* 39 */       for (int var10 = -1; var10 <= 1; var10++) {
/* 40 */         long var11 = ChunkCoordIntPair.pair(var7 + var9, var8 + var10);
/* 41 */         if (var11 == var0) {
/* 42 */           var11 = ChunkCoordIntPair.a;
/*    */         }
/* 44 */         if (var11 != var2) {
/* 45 */           int var13 = b(var11, var0, c(var11));
/* 46 */           if (var5 > var13) {
/* 47 */             var5 = var13;
/*    */           }
/* 49 */           if (var5 == 0) {
/* 50 */             return var5;
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 55 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int b(long var0, long var2, int var4) {
/* 60 */     if (var0 == ChunkCoordIntPair.a) {
/* 61 */       return b(var2);
/*    */     }
/* 63 */     return var4 + 1;
/*    */   }
/*    */   
/*    */   protected abstract int b(long paramLong);
/*    */   
/*    */   public void update(long var0, int var2, boolean var3) {
/* 69 */     a(ChunkCoordIntPair.a, var0, var2, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */