/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MobEffectUtil
/*    */ {
/*    */   public static boolean a(EntityLiving var0) {
/* 17 */     return (var0.hasEffect(MobEffects.FASTER_DIG) || var0.hasEffect(MobEffects.CONDUIT_POWER));
/*    */   }
/*    */   
/*    */   public static int b(EntityLiving var0) {
/* 21 */     int var1 = 0, var2 = 0;
/* 22 */     if (var0.hasEffect(MobEffects.FASTER_DIG)) {
/* 23 */       var1 = var0.getEffect(MobEffects.FASTER_DIG).getAmplifier();
/*    */     }
/* 25 */     if (var0.hasEffect(MobEffects.CONDUIT_POWER)) {
/* 26 */       var2 = var0.getEffect(MobEffects.CONDUIT_POWER).getAmplifier();
/*    */     }
/*    */     
/* 29 */     return Math.max(var1, var2);
/*    */   }
/*    */   
/*    */   public static boolean c(EntityLiving var0) {
/* 33 */     return (var0.hasEffect(MobEffects.WATER_BREATHING) || var0.hasEffect(MobEffects.CONDUIT_POWER));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffectUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */