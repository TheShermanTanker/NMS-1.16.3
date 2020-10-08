/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface AreaTransformer7
/*    */   extends AreaTransformer2, AreaTransformerOffset1
/*    */ {
/*    */   int a(WorldGenContext paramWorldGenContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/*    */   
/*    */   default int a(AreaContextTransformed<?> var0, Area var1, int var2, int var3) {
/* 12 */     return a(var0, var1
/*    */         
/* 14 */         .a(a(var2 + 1), b(var3 + 0)), var1
/* 15 */         .a(a(var2 + 2), b(var3 + 1)), var1
/* 16 */         .a(a(var2 + 1), b(var3 + 2)), var1
/* 17 */         .a(a(var2 + 0), b(var3 + 1)), var1
/* 18 */         .a(a(var2 + 1), b(var3 + 1)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaTransformer7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */