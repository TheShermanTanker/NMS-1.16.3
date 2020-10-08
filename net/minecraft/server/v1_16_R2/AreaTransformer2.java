/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ 
/*   */ 
/*   */ public interface AreaTransformer2
/*   */   extends AreaTransformer
/*   */ {
/*   */   default <R extends Area> AreaFactory<R> a(AreaContextTransformed<R> var0, AreaFactory<R> var1) {
/* 9 */     return () -> {
/*   */         R var2 = var0.make();
/*   */         return var1.a((), (Area)var2);
/*   */       };
/*   */   }
/*   */   
/*   */   int a(AreaContextTransformed<?> paramAreaContextTransformed, Area paramArea, int paramInt1, int paramInt2);
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AreaTransformer2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */