/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class MobEffectAbsorption
/*    */   extends MobEffectList
/*    */ {
/*    */   protected MobEffectAbsorption(MobEffectInfo var0, int var1) {
/*  8 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EntityLiving var0, AttributeMapBase var1, int var2) {
/* 13 */     var0.setAbsorptionHearts(var0.getAbsorptionHearts() - (4 * (var2 + 1)));
/* 14 */     super.a(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityLiving var0, AttributeMapBase var1, int var2) {
/* 19 */     var0.setAbsorptionHearts(var0.getAbsorptionHearts() + (4 * (var2 + 1)));
/* 20 */     super.b(var0, var1, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffectAbsorption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */