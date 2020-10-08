/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
/*    */ 
/*    */ public final class AreaLazy
/*    */   implements Area
/*    */ {
/*    */   private final AreaTransformer8 a;
/*    */   private final Long2IntLinkedOpenHashMap b;
/*    */   private final int c;
/*    */   
/*    */   public AreaLazy(Long2IntLinkedOpenHashMap var0, int var1, AreaTransformer8 var2) {
/* 13 */     this.b = var0;
/* 14 */     this.c = var1;
/* 15 */     this.a = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0, int var1) {
/* 20 */     long var2 = ChunkCoordIntPair.pair(var0, var1);
/* 21 */     synchronized (this.b) {
/* 22 */       int var5 = this.b.get(var2);
/* 23 */       if (var5 != Integer.MIN_VALUE) {
/* 24 */         return var5;
/*    */       }
/* 26 */       int var6 = this.a.apply(var0, var1);
/* 27 */       this.b.put(var2, var6);
/* 28 */       if (this.b.size() > this.c) {
/* 29 */         for (int var7 = 0; var7 < this.c / 16; var7++) {
/* 30 */           this.b.removeFirstInt();
/*    */         }
/*    */       }
/* 33 */       return var6;
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 38 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaLazy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */