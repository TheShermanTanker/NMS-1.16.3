/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerMushroomIsland
/*    */   implements AreaTransformer4
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 11 */     if (GenLayers.b(var5) && GenLayers.b(var4) && GenLayers.b(var1) && GenLayers.b(var3) && GenLayers.b(var2) && var0.a(100) == 0) {
/* 12 */       return 14;
/*    */     }
/* 14 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerMushroomIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */