/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalMoveTowardsRestriction
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreature a;
/*    */   private double b;
/*    */   private double c;
/*    */   private double d;
/*    */   private final double e;
/*    */   
/*    */   public PathfinderGoalMoveTowardsRestriction(EntityCreature var0, double var1) {
/* 17 */     this.a = var0;
/* 18 */     this.e = var1;
/* 19 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 24 */     if (this.a.ev()) {
/* 25 */       return false;
/*    */     }
/* 27 */     Vec3D var0 = RandomPositionGenerator.b(this.a, 16, 7, Vec3D.c(this.a.ew()));
/* 28 */     if (var0 == null) {
/* 29 */       return false;
/*    */     }
/* 31 */     this.b = var0.x;
/* 32 */     this.c = var0.y;
/* 33 */     this.d = var0.z;
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 39 */     return !this.a.getNavigation().m();
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 44 */     this.a.getNavigation().a(this.b, this.c, this.d, this.e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalMoveTowardsRestriction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */