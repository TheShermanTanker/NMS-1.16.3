/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalFleeSun
/*    */   extends PathfinderGoal
/*    */ {
/*    */   protected final EntityCreature a;
/*    */   private double b;
/*    */   private double c;
/*    */   private double d;
/*    */   private final double e;
/*    */   private final World f;
/*    */   
/*    */   public PathfinderGoalFleeSun(EntityCreature var0, double var1) {
/* 22 */     this.a = var0;
/* 23 */     this.e = var1;
/* 24 */     this.f = var0.world;
/* 25 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 30 */     if (this.a.getGoalTarget() != null) {
/* 31 */       return false;
/*    */     }
/* 33 */     if (!this.f.isDay()) {
/* 34 */       return false;
/*    */     }
/* 36 */     if (!this.a.isBurning()) {
/* 37 */       return false;
/*    */     }
/* 39 */     if (!this.f.e(this.a.getChunkCoordinates())) {
/* 40 */       return false;
/*    */     }
/* 42 */     if (!this.a.getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     return g();
/*    */   }
/*    */   
/*    */   protected boolean g() {
/* 50 */     Vec3D var0 = h();
/* 51 */     if (var0 == null) {
/* 52 */       return false;
/*    */     }
/* 54 */     this.b = var0.x;
/* 55 */     this.c = var0.y;
/* 56 */     this.d = var0.z;
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 62 */     return !this.a.getNavigation().m();
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 67 */     this.a.getNavigation().a(this.b, this.c, this.d, this.e);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected Vec3D h() {
/* 72 */     Random var0 = this.a.getRandom();
/* 73 */     BlockPosition var1 = this.a.getChunkCoordinates();
/*    */     
/* 75 */     for (int var2 = 0; var2 < 10; var2++) {
/* 76 */       BlockPosition var3 = var1.b(var0.nextInt(20) - 10, var0.nextInt(6) - 3, var0.nextInt(20) - 10);
/*    */       
/* 78 */       if (!this.f.e(var3) && this.a.f(var3) < 0.0F) {
/* 79 */         return Vec3D.c(var3);
/*    */       }
/*    */     } 
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFleeSun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */