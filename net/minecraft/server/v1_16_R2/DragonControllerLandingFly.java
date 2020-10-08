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
/*    */ public class DragonControllerLandingFly
/*    */   extends AbstractDragonController
/*    */ {
/* 17 */   private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(128.0D);
/*    */   
/*    */   private PathEntity c;
/*    */   private Vec3D d;
/*    */   
/*    */   public DragonControllerLandingFly(EntityEnderDragon var0) {
/* 23 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLandingFly> getControllerPhase() {
/* 28 */     return DragonControllerPhase.LANDING_APPROACH;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 33 */     this.c = null;
/* 34 */     this.d = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 39 */     double var0 = (this.d == null) ? 0.0D : this.d.c(this.a.locX(), this.a.locY(), this.a.locZ());
/* 40 */     if (var0 < 100.0D || var0 > 22500.0D || this.a.positionChanged || this.a.v) {
/* 41 */       j();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 48 */     return this.d;
/*    */   }
/*    */   
/*    */   private void j() {
/* 52 */     if (this.c == null || this.c.c()) {
/* 53 */       int var3, var0 = this.a.eI();
/* 54 */       BlockPosition var1 = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a);
/* 55 */       EntityHuman var2 = this.a.world.a(b, var1.getX(), var1.getY(), var1.getZ());
/*    */ 
/*    */       
/* 58 */       if (var2 != null) {
/* 59 */         Vec3D vec3D = (new Vec3D(var2.locX(), 0.0D, var2.locZ())).d();
/* 60 */         var3 = this.a.p(-vec3D.x * 40.0D, 105.0D, -vec3D.z * 40.0D);
/*    */       } else {
/* 62 */         var3 = this.a.p(40.0D, var1.getY(), 0.0D);
/*    */       } 
/*    */       
/* 65 */       PathPoint var4 = new PathPoint(var1.getX(), var1.getY(), var1.getZ());
/*    */       
/* 67 */       this.c = this.a.a(var0, var3, var4);
/*    */       
/* 69 */       if (this.c != null) {
/* 70 */         this.c.a();
/*    */       }
/*    */     } 
/*    */     
/* 74 */     k();
/*    */     
/* 76 */     if (this.c != null && this.c.c()) {
/* 77 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.LANDING);
/*    */     }
/*    */   }
/*    */   
/*    */   private void k() {
/* 82 */     if (this.c != null && !this.c.c()) {
/* 83 */       double var5; BaseBlockPosition var0 = this.c.g();
/*    */       
/* 85 */       this.c.a();
/* 86 */       double var1 = var0.getX();
/* 87 */       double var3 = var0.getZ();
/*    */ 
/*    */       
/*    */       do {
/* 91 */         var5 = (var0.getY() + this.a.getRandom().nextFloat() * 20.0F);
/* 92 */       } while (var5 < var0.getY());
/*    */       
/* 94 */       this.d = new Vec3D(var1, var5, var3);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerLandingFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */