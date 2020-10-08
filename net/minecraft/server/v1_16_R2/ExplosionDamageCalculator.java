/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExplosionDamageCalculator
/*    */ {
/*    */   public Optional<Float> a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, Fluid var4) {
/* 11 */     if (var3.isAir() && var4.isEmpty()) {
/* 12 */       return Optional.empty();
/*    */     }
/* 14 */     return Optional.of(Float.valueOf(Math.max(var3.getBlock().getDurability(), var4.i())));
/*    */   }
/*    */   
/*    */   public boolean a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, float var4) {
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ExplosionDamageCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */