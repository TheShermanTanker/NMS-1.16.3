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
/*    */ public class DragonControllerLandedSearch
/*    */   extends AbstractDragonControllerLanded
/*    */ {
/* 15 */   private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(150.0D);
/*    */   
/*    */   private final PathfinderTargetCondition c;
/*    */   private int d;
/*    */   
/*    */   public DragonControllerLandedSearch(EntityEnderDragon var0) {
/* 21 */     super(var0);
/*    */     
/* 23 */     this.c = (new PathfinderTargetCondition()).a(20.0D).a(var1 -> (Math.abs(var1.locY() - var0.locY()) <= 10.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 28 */     this.d++;
/* 29 */     EntityLiving var0 = this.a.world.a(this.c, this.a, this.a.locX(), this.a.locY(), this.a.locZ());
/*    */     
/* 31 */     if (var0 != null) {
/* 32 */       if (this.d > 25) {
/* 33 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_ATTACKING);
/*    */       } else {
/* 35 */         Vec3D var1 = (new Vec3D(var0.locX() - this.a.locX(), 0.0D, var0.locZ() - this.a.locZ())).d();
/* 36 */         Vec3D var2 = (new Vec3D(MathHelper.sin(this.a.yaw * 0.017453292F), 0.0D, -MathHelper.cos(this.a.yaw * 0.017453292F))).d();
/* 37 */         float var3 = (float)var2.b(var1);
/* 38 */         float var4 = (float)(Math.acos(var3) * 57.2957763671875D) + 0.5F;
/*    */         
/* 40 */         if (var4 < 0.0F || var4 > 10.0F) {
/* 41 */           double var5 = var0.locX() - this.a.bo.locX();
/* 42 */           double var7 = var0.locZ() - this.a.bo.locZ();
/* 43 */           double var9 = MathHelper.a(MathHelper.g(180.0D - MathHelper.d(var5, var7) * 57.2957763671875D - this.a.yaw), -100.0D, 100.0D);
/*    */           
/* 45 */           this.a.bt *= 0.8F;
/*    */           
/* 47 */           float var11 = MathHelper.sqrt(var5 * var5 + var7 * var7) + 1.0F;
/* 48 */           float var12 = var11;
/* 49 */           if (var11 > 40.0F) {
/* 50 */             var11 = 40.0F;
/*    */           }
/* 52 */           this.a.bt = (float)(this.a.bt + var9 * (0.7F / var11 / var12));
/* 53 */           this.a.yaw += this.a.bt;
/*    */         } 
/*    */       } 
/* 56 */     } else if (this.d >= 100) {
/* 57 */       var0 = this.a.world.a(b, this.a, this.a.locX(), this.a.locY(), this.a.locZ());
/* 58 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.TAKEOFF);
/* 59 */       if (var0 != null) {
/* 60 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.CHARGING_PLAYER);
/* 61 */         ((DragonControllerCharge)this.a.getDragonControllerManager().<DragonControllerCharge>b(DragonControllerPhase.CHARGING_PLAYER)).a(new Vec3D(var0.locX(), var0.locY(), var0.locZ()));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 68 */     this.d = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLandedSearch> getControllerPhase() {
/* 73 */     return DragonControllerPhase.SITTING_SCANNING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerLandedSearch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */