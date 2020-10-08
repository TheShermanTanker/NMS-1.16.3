/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExplosionDamageCalculatorEntity
/*    */   extends ExplosionDamageCalculator
/*    */ {
/*    */   private final Entity a;
/*    */   
/*    */   public ExplosionDamageCalculatorEntity(Entity var0) {
/* 14 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Float> a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, Fluid var4) {
/* 19 */     return super.a(var0, var1, var2, var3, var4).map(var5 -> Float.valueOf(this.a.a(var0, var1, var2, var3, var4, var5.floatValue())));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, float var4) {
/* 24 */     return this.a.a(var0, var1, var2, var3, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ExplosionDamageCalculatorEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */