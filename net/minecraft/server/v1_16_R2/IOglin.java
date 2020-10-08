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
/*    */ public interface IOglin
/*    */ {
/*    */   static boolean a(EntityLiving var0, EntityLiving var1) {
/* 17 */     float var2, var3 = (float)var0.b(GenericAttributes.ATTACK_DAMAGE);
/* 18 */     if (!var0.isBaby() && (int)var3 > 0) {
/* 19 */       var2 = var3 / 2.0F + var0.world.random.nextInt((int)var3);
/*    */     } else {
/* 21 */       var2 = var3;
/*    */     } 
/*    */     
/* 24 */     boolean var4 = var1.damageEntity(DamageSource.mobAttack(var0), var2);
/* 25 */     if (var4) {
/* 26 */       var0.a(var0, var1);
/* 27 */       if (!var0.isBaby()) {
/* 28 */         b(var0, var1);
/*    */       }
/*    */     } 
/* 31 */     return var4;
/*    */   }
/*    */   
/*    */   static void b(EntityLiving var0, EntityLiving var1) {
/* 35 */     double var2 = var0.b(GenericAttributes.ATTACK_KNOCKBACK);
/* 36 */     double var4 = var1.b(GenericAttributes.KNOCKBACK_RESISTANCE);
/* 37 */     double var6 = var2 - var4;
/* 38 */     if (var6 <= 0.0D) {
/*    */       return;
/*    */     }
/*    */     
/* 42 */     double var8 = var1.locX() - var0.locX();
/* 43 */     double var10 = var1.locZ() - var0.locZ();
/* 44 */     float var12 = (var0.world.random.nextInt(21) - 10);
/* 45 */     double var13 = var6 * (var0.world.random.nextFloat() * 0.5F + 0.2F);
/* 46 */     Vec3D var15 = (new Vec3D(var8, 0.0D, var10)).d().a(var13).b(var12);
/*    */     
/* 48 */     double var16 = var6 * var0.world.random.nextFloat() * 0.5D;
/* 49 */     var1.i(var15.x, var16, var15.z);
/* 50 */     var1.velocityChanged = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IOglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */