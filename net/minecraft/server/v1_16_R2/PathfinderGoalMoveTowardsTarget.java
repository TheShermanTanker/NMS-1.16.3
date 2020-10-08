/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalMoveTowardsTarget
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreature a;
/*    */   private EntityLiving b;
/*    */   private double c;
/*    */   private double d;
/*    */   private double e;
/*    */   private final double f;
/*    */   private final float g;
/*    */   
/*    */   public PathfinderGoalMoveTowardsTarget(EntityCreature var0, double var1, float var3) {
/* 20 */     this.a = var0;
/* 21 */     this.f = var1;
/* 22 */     this.g = var3;
/* 23 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 28 */     this.b = this.a.getGoalTarget();
/* 29 */     if (this.b == null) {
/* 30 */       return false;
/*    */     }
/* 32 */     if (this.b.h(this.a) > (this.g * this.g)) {
/* 33 */       return false;
/*    */     }
/* 35 */     Vec3D var0 = RandomPositionGenerator.b(this.a, 16, 7, this.b.getPositionVector());
/* 36 */     if (var0 == null) {
/* 37 */       return false;
/*    */     }
/* 39 */     this.c = var0.x;
/* 40 */     this.d = var0.y;
/* 41 */     this.e = var0.z;
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 47 */     return (!this.a.getNavigation().m() && this.b.isAlive() && this.b.h(this.a) < (this.g * this.g));
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 52 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 57 */     this.a.getNavigation().a(this.c, this.d, this.e, this.f);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalMoveTowardsTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */