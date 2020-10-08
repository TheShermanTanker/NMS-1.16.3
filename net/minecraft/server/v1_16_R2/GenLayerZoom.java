/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GenLayerZoom
/*    */   implements AreaTransformer2
/*    */ {
/*  8 */   NORMAL,
/*  9 */   FUZZY
/*    */   {
/*    */     protected int a(AreaContextTransformed<?> var0, int var1, int var2, int var3, int var4) {
/* 12 */       return var0.a(var1, var2, var3, var4);
/*    */     }
/*    */   };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 21 */     return var0 >> 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 26 */     return var0 >> 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(AreaContextTransformed<?> var0, Area var1, int var2, int var3) {
/* 31 */     int var4 = var1.a(a(var2), b(var3));
/*    */     
/* 33 */     var0.a((var2 >> 1 << 1), (var3 >> 1 << 1));
/*    */     
/* 35 */     int var5 = var2 & 0x1;
/* 36 */     int var6 = var3 & 0x1;
/* 37 */     if (var5 == 0 && var6 == 0) {
/* 38 */       return var4;
/*    */     }
/*    */     
/* 41 */     int var7 = var1.a(a(var2), b(var3 + 1));
/* 42 */     int var8 = var0.a(var4, var7);
/* 43 */     if (var5 == 0 && var6 == 1) {
/* 44 */       return var8;
/*    */     }
/* 46 */     int var9 = var1.a(a(var2 + 1), b(var3));
/* 47 */     int var10 = var0.a(var4, var9);
/* 48 */     if (var5 == 1 && var6 == 0) {
/* 49 */       return var10;
/*    */     }
/*    */     
/* 52 */     int var11 = var1.a(a(var2 + 1), b(var3 + 1));
/*    */     
/* 54 */     return a(var0, var4, var9, var7, var11);
/*    */   }
/*    */   
/*    */   protected int a(AreaContextTransformed<?> var0, int var1, int var2, int var3, int var4) {
/* 58 */     if (var2 == var3 && var3 == var4) {
/* 59 */       return var2;
/*    */     }
/* 61 */     if (var1 == var2 && var1 == var3) {
/* 62 */       return var1;
/*    */     }
/* 64 */     if (var1 == var2 && var1 == var4) {
/* 65 */       return var1;
/*    */     }
/* 67 */     if (var1 == var3 && var1 == var4) {
/* 68 */       return var1;
/*    */     }
/* 70 */     if (var1 == var2 && var3 != var4) {
/* 71 */       return var1;
/*    */     }
/* 73 */     if (var1 == var3 && var2 != var4) {
/* 74 */       return var1;
/*    */     }
/* 76 */     if (var1 == var4 && var2 != var3) {
/* 77 */       return var1;
/*    */     }
/* 79 */     if (var2 == var3 && var1 != var4) {
/* 80 */       return var2;
/*    */     }
/* 82 */     if (var2 == var4 && var1 != var3) {
/* 83 */       return var2;
/*    */     }
/* 85 */     if (var3 == var4 && var1 != var2) {
/* 86 */       return var3;
/*    */     }
/*    */     
/* 89 */     return var0.a(var1, var2, var3, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerZoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */