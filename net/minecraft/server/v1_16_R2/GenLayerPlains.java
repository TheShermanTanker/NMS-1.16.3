/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerPlains
/*    */   implements AreaTransformer6
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1) {
/* 11 */     if (var0.a(57) == 0 && var1 == 1) {
/* 12 */       return 129;
/*    */     }
/* 14 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerPlains.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */