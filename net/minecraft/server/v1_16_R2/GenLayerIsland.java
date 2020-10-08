/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerIsland
/*    */   implements AreaTransformer4
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 11 */     if (GenLayers.b(var5) && (!GenLayers.b(var4) || !GenLayers.b(var3) || !GenLayers.b(var1) || !GenLayers.b(var2))) {
/* 12 */       int var6 = 1;
/* 13 */       int var7 = 1;
/* 14 */       if (!GenLayers.b(var4) && var0.a(var6++) == 0) {
/* 15 */         var7 = var4;
/*    */       }
/* 17 */       if (!GenLayers.b(var3) && var0.a(var6++) == 0) {
/* 18 */         var7 = var3;
/*    */       }
/* 20 */       if (!GenLayers.b(var1) && var0.a(var6++) == 0) {
/* 21 */         var7 = var1;
/*    */       }
/* 23 */       if (!GenLayers.b(var2) && var0.a(var6++) == 0) {
/* 24 */         var7 = var2;
/*    */       }
/* 26 */       if (var0.a(3) == 0) {
/* 27 */         return var7;
/*    */       }
/* 29 */       return (var7 == 4) ? 4 : var5;
/*    */     } 
/*    */     
/* 32 */     if (!GenLayers.b(var5) && (GenLayers.b(var4) || GenLayers.b(var1) || GenLayers.b(var3) || GenLayers.b(var2)) && 
/* 33 */       var0.a(5) == 0) {
/* 34 */       if (GenLayers.b(var4)) {
/* 35 */         return (var5 == 4) ? 4 : var4;
/*    */       }
/*    */       
/* 38 */       if (GenLayers.b(var1)) {
/* 39 */         return (var5 == 4) ? 4 : var1;
/*    */       }
/*    */       
/* 42 */       if (GenLayers.b(var3)) {
/* 43 */         return (var5 == 4) ? 4 : var3;
/*    */       }
/*    */       
/* 46 */       if (GenLayers.b(var2)) {
/* 47 */         return (var5 == 4) ? 4 : var2;
/*    */       }
/*    */     } 
/*    */     
/* 51 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */