/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ISteerable
/*    */ {
/*    */   boolean O_();
/*    */   
/*    */   void a_(Vec3D paramVec3D);
/*    */   
/*    */   float N_();
/*    */   
/*    */   default boolean a(EntityInsentient var0, SaddleStorage var1, Vec3D var2) {
/* 14 */     if (!var0.isAlive()) {
/* 15 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 19 */     Entity var3 = var0.getPassengers().isEmpty() ? null : var0.getPassengers().get(0);
/* 20 */     if (!var0.isVehicle() || !var0.er() || !(var3 instanceof EntityHuman)) {
/* 21 */       var0.G = 0.5F;
/* 22 */       var0.aE = 0.02F;
/* 23 */       a_(var2);
/* 24 */       return false;
/*    */     } 
/*    */     
/* 27 */     var0.yaw = var3.yaw;
/* 28 */     var0.lastYaw = var0.yaw;
/* 29 */     var0.pitch = var3.pitch * 0.5F;
/* 30 */     var0.setYawPitch(var0.yaw, var0.pitch);
/* 31 */     var0.aA = var0.yaw;
/* 32 */     var0.aC = var0.yaw;
/*    */     
/* 34 */     var0.G = 1.0F;
/* 35 */     var0.aE = var0.dM() * 0.1F;
/*    */     
/* 37 */     if (var1.boosting && 
/* 38 */       var1.currentBoostTicks++ > var1.boostTicks) {
/* 39 */       var1.boosting = false;
/*    */     }
/*    */ 
/*    */     
/* 43 */     if (var0.cr()) {
/* 44 */       float var4 = N_();
/* 45 */       if (var1.boosting) {
/* 46 */         var4 += var4 * 1.15F * MathHelper.sin(var1.currentBoostTicks / var1.boostTicks * 3.1415927F);
/*    */       }
/* 48 */       var0.q(var4);
/* 49 */       a_(new Vec3D(0.0D, 0.0D, 1.0D));
/* 50 */       var0.aU = 0;
/*    */     } else {
/* 52 */       var0.a(var0, false);
/* 53 */       var0.setMot(Vec3D.ORIGIN);
/*    */     } 
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ISteerable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */