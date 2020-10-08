/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalFollowParent
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityAnimal a;
/*    */   private EntityAnimal b;
/*    */   private final double c;
/*    */   private int d;
/*    */   
/*    */   public PathfinderGoalFollowParent(EntityAnimal var0, double var1) {
/* 17 */     this.a = var0;
/* 18 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     if (this.a.getAge() >= 0) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     List<EntityAnimal> var0 = (List)this.a.world.a(this.a.getClass(), this.a.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
/*    */     
/* 29 */     EntityAnimal var1 = null;
/* 30 */     double var2 = Double.MAX_VALUE;
/* 31 */     for (EntityAnimal var5 : var0) {
/* 32 */       if (var5.getAge() < 0) {
/*    */         continue;
/*    */       }
/* 35 */       double var6 = this.a.h(var5);
/* 36 */       if (var6 > var2) {
/*    */         continue;
/*    */       }
/* 39 */       var2 = var6;
/* 40 */       var1 = var5;
/*    */     } 
/*    */     
/* 43 */     if (var1 == null) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (var2 < 9.0D) {
/* 47 */       return false;
/*    */     }
/* 49 */     this.b = var1;
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 55 */     if (this.a.getAge() >= 0) {
/* 56 */       return false;
/*    */     }
/* 58 */     if (!this.b.isAlive()) {
/* 59 */       return false;
/*    */     }
/* 61 */     double var0 = this.a.h(this.b);
/* 62 */     if (var0 < 9.0D || var0 > 256.0D) {
/* 63 */       return false;
/*    */     }
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 70 */     this.d = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 75 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 80 */     if (--this.d > 0) {
/*    */       return;
/*    */     }
/* 83 */     this.d = 10;
/* 84 */     this.a.getNavigation().a(this.b, this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFollowParent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */