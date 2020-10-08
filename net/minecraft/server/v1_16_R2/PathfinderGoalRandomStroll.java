/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalRandomStroll
/*    */   extends PathfinderGoal
/*    */ {
/*    */   protected final EntityCreature a;
/*    */   protected double b;
/*    */   protected double c;
/*    */   protected double d;
/*    */   protected final double e;
/*    */   protected int f;
/*    */   protected boolean g;
/*    */   private boolean h;
/*    */   
/*    */   public PathfinderGoalRandomStroll(EntityCreature var0, double var1) {
/* 23 */     this(var0, var1, 120);
/*    */   }
/*    */   
/*    */   public PathfinderGoalRandomStroll(EntityCreature var0, double var1, int var3) {
/* 27 */     this(var0, var1, var3, true);
/*    */   }
/*    */   
/*    */   public PathfinderGoalRandomStroll(EntityCreature var0, double var1, int var3, boolean var4) {
/* 31 */     this.a = var0;
/* 32 */     this.e = var1;
/* 33 */     this.f = var3;
/* 34 */     this.h = var4;
/* 35 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 39 */     if (this.a.isVehicle()) {
/* 40 */       return false;
/*    */     }
/* 42 */     if (!this.g) {
/* 43 */       if (this.h && this.a.dc() >= 100) {
/* 44 */         return false;
/*    */       }
/* 46 */       if (this.a.getRandom().nextInt(this.f) != 0) {
/* 47 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 51 */     Vec3D var0 = g();
/*    */     
/* 53 */     if (var0 == null) {
/* 54 */       return false;
/*    */     }
/*    */     
/* 57 */     this.b = var0.x;
/* 58 */     this.c = var0.y;
/* 59 */     this.d = var0.z;
/* 60 */     this.g = false;
/* 61 */     return true;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected Vec3D g() {
/* 66 */     return RandomPositionGenerator.a(this.a, 10, 7);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 71 */     return (!this.a.getNavigation().m() && !this.a.isVehicle());
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 76 */     this.a.getNavigation().a(this.b, this.c, this.d, this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 81 */     this.a.getNavigation().o();
/* 82 */     super.d();
/*    */   }
/*    */   
/*    */   public void h() {
/* 86 */     this.g = true;
/*    */   }
/*    */   
/*    */   public void setTimeBetweenMovement(int var0) {
/* 90 */     this.f = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRandomStroll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */