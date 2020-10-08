/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ 
/*    */ public interface ICrossbow
/*    */   extends IRangedEntity
/*    */ {
/*    */   void b(boolean paramBoolean);
/*    */   
/*    */   void a(EntityLiving paramEntityLiving, ItemStack paramItemStack, IProjectile paramIProjectile, float paramFloat);
/*    */   
/*    */   @Nullable
/*    */   EntityLiving getGoalTarget();
/*    */   
/*    */   void U_();
/*    */   
/*    */   default void b(EntityLiving var0, float var1) {
/* 30 */     EnumHand var2 = ProjectileHelper.a(var0, Items.CROSSBOW);
/* 31 */     ItemStack var3 = var0.b(var2);
/* 32 */     if (var0.a(Items.CROSSBOW)) {
/* 33 */       ItemCrossbow.a(var0.world, var0, var2, var3, var1, (14 - var0.world.getDifficulty().a() * 4));
/*    */     }
/* 35 */     U_();
/*    */   }
/*    */   
/*    */   default void a(EntityLiving var0, EntityLiving var1, IProjectile var2, float var3, float var4) {
/* 39 */     Entity var5 = var2;
/* 40 */     double var6 = var1.locX() - var0.locX();
/* 41 */     double var8 = var1.locZ() - var0.locZ();
/* 42 */     double var10 = MathHelper.sqrt(var6 * var6 + var8 * var8);
/* 43 */     double var12 = var1.e(0.3333333333333333D) - var5.locY() + var10 * 0.20000000298023224D;
/*    */     
/* 45 */     Vector3fa var14 = a(var0, new Vec3D(var6, var12, var8), var3);
/* 46 */     var2.shoot(var14.a(), var14.b(), var14.c(), var4, (14 - var0.world.getDifficulty().a() * 4));
/* 47 */     var0.playSound(SoundEffects.ITEM_CROSSBOW_SHOOT, 1.0F, 1.0F / (var0.getRandom().nextFloat() * 0.4F + 0.8F));
/*    */   }
/*    */   
/*    */   default Vector3fa a(EntityLiving var0, Vec3D var1, float var2) {
/* 51 */     Vec3D var3 = var1.d();
/* 52 */     Vec3D var4 = var3.c(new Vec3D(0.0D, 1.0D, 0.0D));
/* 53 */     if (var4.g() <= 1.0E-7D) {
/* 54 */       var4 = var3.c(var0.i(1.0F));
/*    */     }
/*    */     
/* 57 */     Quaternion var5 = new Quaternion(new Vector3fa(var4), 90.0F, true);
/* 58 */     Vector3fa var6 = new Vector3fa(var3);
/* 59 */     var6.a(var5);
/*    */     
/* 61 */     Quaternion var7 = new Quaternion(var6, var2, true);
/* 62 */     Vector3fa var8 = new Vector3fa(var3);
/* 63 */     var8.a(var7);
/* 64 */     return var8;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ICrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */