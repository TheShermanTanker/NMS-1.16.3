/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class MobEffectAttackDamage
/*    */   extends MobEffectList
/*    */ {
/*    */   protected final double a;
/*    */   
/*    */   protected MobEffectAttackDamage(MobEffectInfo var0, int var1, double var2) {
/*  9 */     super(var0, var1);
/* 10 */     this.a = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(int var0, AttributeModifier var1) {
/* 15 */     return this.a * (var0 + 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffectAttackDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */