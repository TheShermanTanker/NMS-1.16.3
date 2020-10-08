/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class MobEffectHealthBoost
/*    */   extends MobEffectList
/*    */ {
/*    */   public MobEffectHealthBoost(MobEffectInfo var0, int var1) {
/*  8 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EntityLiving var0, AttributeMapBase var1, int var2) {
/* 13 */     super.a(var0, var1, var2);
/* 14 */     if (var0.getHealth() > var0.getMaxHealth())
/* 15 */       var0.setHealth(var0.getMaxHealth()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffectHealthBoost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */