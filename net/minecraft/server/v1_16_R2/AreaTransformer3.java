/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface AreaTransformer3
/*    */   extends AreaTransformer
/*    */ {
/*    */   default <R extends Area> AreaFactory<R> a(AreaContextTransformed<R> var0, AreaFactory<R> var1, AreaFactory<R> var2) {
/* 10 */     return () -> {
/*    */         R var3 = var0.make();
/*    */         R var4 = var1.make();
/*    */         return var2.a((), (Area)var3, (Area)var4);
/*    */       };
/*    */   }
/*    */   
/*    */   int a(WorldGenContext paramWorldGenContext, Area paramArea1, Area paramArea2, int paramInt1, int paramInt2);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaTransformer3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */