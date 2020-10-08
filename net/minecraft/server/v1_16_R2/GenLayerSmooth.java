/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum GenLayerSmooth
/*    */   implements AreaTransformer7
/*    */ {
/*  7 */   INSTANCE;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 11 */     boolean var6 = (var2 == var4);
/* 12 */     boolean var7 = (var1 == var3);
/*    */     
/* 14 */     if (var6 == var7) {
/* 15 */       if (var6) {
/* 16 */         return (var0.a(2) == 0) ? var4 : var1;
/*    */       }
/* 18 */       return var5;
/*    */     } 
/* 20 */     return var6 ? var4 : var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */