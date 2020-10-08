/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerDying
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private Vec3D b;
/*    */   private int c;
/*    */   
/*    */   public DragonControllerDying(EntityEnderDragon var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b() {
/* 22 */     if (this.c++ % 10 == 0) {
/* 23 */       float var0 = (this.a.getRandom().nextFloat() - 0.5F) * 8.0F;
/* 24 */       float var1 = (this.a.getRandom().nextFloat() - 0.5F) * 4.0F;
/* 25 */       float var2 = (this.a.getRandom().nextFloat() - 0.5F) * 8.0F;
/* 26 */       this.a.world.addParticle(Particles.EXPLOSION_EMITTER, this.a.locX() + var0, this.a.locY() + 2.0D + var1, this.a.locZ() + var2, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 32 */     this.c++;
/*    */     
/* 34 */     if (this.b == null) {
/* 35 */       BlockPosition blockPosition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, WorldGenEndTrophy.a);
/* 36 */       this.b = Vec3D.c(blockPosition);
/*    */     } 
/*    */     
/* 39 */     double var0 = this.b.c(this.a.locX(), this.a.locY(), this.a.locZ());
/* 40 */     if (var0 < 100.0D || var0 > 22500.0D || this.a.positionChanged || this.a.v) {
/* 41 */       this.a.setHealth(0.0F);
/*    */     } else {
/* 43 */       this.a.setHealth(1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 49 */     this.b = null;
/* 50 */     this.c = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 55 */     return 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 61 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerDying> getControllerPhase() {
/* 66 */     return DragonControllerPhase.DYING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerDying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */