/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerRiver
/*    */   implements AreaTransformer7
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 11 */     int var6 = c(var5);
/* 12 */     if (var6 == c(var4) && var6 == 
/* 13 */       c(var1) && var6 == 
/* 14 */       c(var2) && var6 == 
/* 15 */       c(var3))
/*    */     {
/* 17 */       return -1;
/*    */     }
/* 19 */     return 7;
/*    */   }
/*    */   
/*    */   private static int c(int var0) {
/* 23 */     if (var0 >= 2) {
/* 24 */       return 2 + (var0 & 0x1);
/*    */     }
/* 26 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerRiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */