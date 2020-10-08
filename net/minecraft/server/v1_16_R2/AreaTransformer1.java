/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface AreaTransformer1
/*    */ {
/*    */   default <R extends Area> AreaFactory<R> a(AreaContextTransformed<R> var0) {
/* 10 */     return () -> var0.a(());
/*    */   }
/*    */   
/*    */   int a(WorldGenContext paramWorldGenContext, int paramInt1, int paramInt2);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaTransformer1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */