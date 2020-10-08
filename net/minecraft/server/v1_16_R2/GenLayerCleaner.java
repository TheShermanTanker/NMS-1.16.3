/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerCleaner
/*    */   implements AreaTransformer5
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1) {
/* 11 */     return GenLayers.b(var1) ? var1 : (var0.a(299999) + 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerCleaner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */