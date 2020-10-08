/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum LayerIsland
/*    */   implements AreaTransformer1
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2) {
/* 12 */     if (var1 == 0 && var2 == 0) {
/* 13 */       return 1;
/*    */     }
/*    */     
/* 16 */     return (var0.a(10) == 0) ? 1 : 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LayerIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */