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
/*    */ public class DragonControllerFly
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private boolean b;
/*    */   private PathEntity c;
/*    */   private Vec3D d;
/*    */   
/*    */   public DragonControllerFly(EntityEnderDragon var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 24 */     if (this.b || this.c == null) {
/* 25 */       this.b = false;
/* 26 */       j();
/*    */     } else {
/* 28 */       BlockPosition var0 = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a);
/* 29 */       if (!var0.a(this.a.getPositionVector(), 10.0D)) {
/* 30 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 37 */     this.b = true;
/* 38 */     this.c = null;
/* 39 */     this.d = null;
/*    */   }
/*    */   
/*    */   private void j() {
/* 43 */     int var0 = this.a.eI();
/* 44 */     Vec3D var1 = this.a.x(1.0F);
/* 45 */     int var2 = this.a.p(-var1.x * 40.0D, 105.0D, -var1.z * 40.0D);
/*    */     
/* 47 */     if (this.a.getEnderDragonBattle() == null || this.a.getEnderDragonBattle().c() <= 0) {
/*    */       
/* 49 */       var2 -= 12;
/* 50 */       var2 &= 0x7;
/* 51 */       var2 += 12;
/*    */     } else {
/*    */       
/* 54 */       var2 %= 12;
/* 55 */       if (var2 < 0) {
/* 56 */         var2 += 12;
/*    */       }
/*    */     } 
/*    */     
/* 60 */     this.c = this.a.a(var0, var2, (PathPoint)null);
/*    */     
/* 62 */     k();
/*    */   }
/*    */   
/*    */   private void k() {
/* 66 */     if (this.c != null) {
/* 67 */       this.c.a();
/* 68 */       if (!this.c.c()) {
/* 69 */         double var1; BaseBlockPosition var0 = this.c.g();
/* 70 */         this.c.a();
/*    */ 
/*    */         
/*    */         do {
/* 74 */           var1 = (var0.getY() + this.a.getRandom().nextFloat() * 20.0F);
/* 75 */         } while (var1 < var0.getY());
/*    */         
/* 77 */         this.d = new Vec3D(var0.getX(), var1, var0.getZ());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 85 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerFly> getControllerPhase() {
/* 90 */     return DragonControllerPhase.TAKEOFF;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */