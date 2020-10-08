/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class InstantMobEffect extends MobEffectList {
/*    */   public InstantMobEffect(MobEffectInfo var0, int var1) {
/*  5 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInstant() {
/* 10 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(int var0, int var1) {
/* 15 */     return (var0 >= 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InstantMobEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */