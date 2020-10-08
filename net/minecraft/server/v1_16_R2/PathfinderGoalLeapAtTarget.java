/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalLeapAtTarget
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityInsentient a;
/*    */   private EntityLiving b;
/*    */   private final float c;
/*    */   
/*    */   public PathfinderGoalLeapAtTarget(EntityInsentient var0, float var1) {
/* 16 */     this.a = var0;
/* 17 */     this.c = var1;
/* 18 */     a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     if (this.a.isVehicle()) {
/* 24 */       return false;
/*    */     }
/* 26 */     this.b = this.a.getGoalTarget();
/* 27 */     if (this.b == null) {
/* 28 */       return false;
/*    */     }
/* 30 */     double var0 = this.a.h(this.b);
/* 31 */     if (var0 < 4.0D || var0 > 16.0D) {
/* 32 */       return false;
/*    */     }
/* 34 */     if (!this.a.isOnGround()) {
/* 35 */       return false;
/*    */     }
/* 37 */     if (this.a.getRandom().nextInt(5) != 0) {
/* 38 */       return false;
/*    */     }
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 45 */     return !this.a.isOnGround();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 51 */     Vec3D var0 = this.a.getMot();
/* 52 */     Vec3D var1 = new Vec3D(this.b.locX() - this.a.locX(), 0.0D, this.b.locZ() - this.a.locZ());
/* 53 */     if (var1.g() > 1.0E-7D) {
/* 54 */       var1 = var1.d().a(0.4D).e(var0.a(0.2D));
/*    */     }
/*    */     
/* 57 */     this.a.setMot(var1.x, this.c, var1.z);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalLeapAtTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */