/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerLanding
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private Vec3D b;
/*    */   
/*    */   public DragonControllerLanding(EntityEnderDragon var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b() {
/* 23 */     Vec3D var0 = this.a.x(1.0F).d();
/* 24 */     var0.b(-0.7853982F);
/*    */     
/* 26 */     double var1 = this.a.bo.locX();
/* 27 */     double var3 = this.a.bo.e(0.5D);
/* 28 */     double var5 = this.a.bo.locZ();
/* 29 */     for (int var7 = 0; var7 < 8; var7++) {
/* 30 */       Random var8 = this.a.getRandom();
/* 31 */       double var9 = var1 + var8.nextGaussian() / 2.0D;
/* 32 */       double var11 = var3 + var8.nextGaussian() / 2.0D;
/* 33 */       double var13 = var5 + var8.nextGaussian() / 2.0D;
/* 34 */       Vec3D var15 = this.a.getMot();
/* 35 */       this.a.world.addParticle(Particles.DRAGON_BREATH, var9, var11, var13, -var0.x * 0.07999999821186066D + var15.x, -var0.y * 0.30000001192092896D + var15.y, -var0.z * 0.07999999821186066D + var15.z);
/* 36 */       var0.b(0.19634955F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 42 */     if (this.b == null) {
/* 43 */       this.b = Vec3D.c(this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a));
/*    */     }
/*    */     
/* 46 */     if (this.b.c(this.a.locX(), this.a.locY(), this.a.locZ()) < 1.0D) {
/* 47 */       ((DragonControllerLandedFlame)this.a.getDragonControllerManager().<DragonControllerLandedFlame>b(DragonControllerPhase.SITTING_FLAMING)).j();
/* 48 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_SCANNING);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 54 */     return 1.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float h() {
/* 59 */     float var0 = MathHelper.sqrt(Entity.c(this.a.getMot())) + 1.0F;
/* 60 */     float var1 = Math.min(var0, 40.0F);
/*    */     
/* 62 */     return var1 / var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 67 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 73 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLanding> getControllerPhase() {
/* 78 */     return DragonControllerPhase.LANDING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerLanding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */