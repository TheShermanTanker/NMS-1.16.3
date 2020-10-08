/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface AreaTransformer6
/*    */   extends AreaTransformer2, AreaTransformerOffset1
/*    */ {
/*    */   int a(WorldGenContext paramWorldGenContext, int paramInt);
/*    */   
/*    */   default int a(AreaContextTransformed<?> var0, Area var1, int var2, int var3) {
/* 12 */     int var4 = var1.a(a(var2 + 1), b(var3 + 1));
/* 13 */     return a(var0, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaTransformer6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */