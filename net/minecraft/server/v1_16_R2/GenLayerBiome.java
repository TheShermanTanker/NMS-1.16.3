/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class GenLayerBiome
/*    */   implements AreaTransformer5
/*    */ {
/*  7 */   private static final int[] a = new int[] { 2, 4, 3, 6, 1, 5 };
/*  8 */   private static final int[] b = new int[] { 2, 2, 2, 35, 35, 1 };
/*  9 */   private static final int[] c = new int[] { 4, 29, 3, 1, 27, 6 };
/* 10 */   private static final int[] d = new int[] { 4, 3, 5, 1 };
/* 11 */   private static final int[] e = new int[] { 12, 12, 12, 30 };
/*    */   
/* 13 */   private int[] f = b;
/*    */   
/*    */   public GenLayerBiome(boolean var0) {
/* 16 */     if (var0) {
/* 17 */       this.f = a;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1) {
/* 23 */     int var2 = (var1 & 0xF00) >> 8;
/* 24 */     var1 &= 0xFFFFF0FF;
/*    */     
/* 26 */     if (GenLayers.a(var1) || var1 == 14) {
/* 27 */       return var1;
/*    */     }
/*    */     
/* 30 */     switch (var1) {
/*    */       case 1:
/* 32 */         if (var2 > 0) {
/* 33 */           return (var0.a(3) == 0) ? 39 : 38;
/*    */         }
/* 35 */         return this.f[var0.a(this.f.length)];
/*    */       case 2:
/* 37 */         if (var2 > 0) {
/* 38 */           return 21;
/*    */         }
/* 40 */         return c[var0.a(c.length)];
/*    */       case 3:
/* 42 */         if (var2 > 0) {
/* 43 */           return 32;
/*    */         }
/* 45 */         return d[var0.a(d.length)];
/*    */       case 4:
/* 47 */         return e[var0.a(e.length)];
/*    */     } 
/* 49 */     return 14;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */