/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalBreed
/*    */   extends PathfinderGoal
/*    */ {
/* 13 */   private static final PathfinderTargetCondition d = (new PathfinderTargetCondition()).a(8.0D).a().b().c();
/*    */   
/*    */   protected final EntityAnimal animal;
/*    */   private final Class<? extends EntityAnimal> e;
/*    */   protected final World b;
/*    */   protected EntityAnimal partner;
/*    */   private int f;
/*    */   private final double g;
/*    */   
/*    */   public PathfinderGoalBreed(EntityAnimal var0, double var1) {
/* 23 */     this(var0, var1, (Class)var0.getClass());
/*    */   }
/*    */   
/*    */   public PathfinderGoalBreed(EntityAnimal var0, double var1, Class<? extends EntityAnimal> var3) {
/* 27 */     this.animal = var0;
/* 28 */     this.b = var0.world;
/* 29 */     this.e = var3;
/* 30 */     this.g = var1;
/* 31 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 36 */     if (!this.animal.isInLove()) {
/* 37 */       return false;
/*    */     }
/* 39 */     this.partner = h();
/* 40 */     return (this.partner != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 45 */     return (this.partner.isAlive() && this.partner.isInLove() && this.f < 60);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 50 */     this.partner = null;
/* 51 */     this.f = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 56 */     this.animal.getControllerLook().a(this.partner, 10.0F, this.animal.O());
/* 57 */     this.animal.getNavigation().a(this.partner, this.g);
/* 58 */     this.f++;
/* 59 */     if (this.f >= 60 && this.animal.h(this.partner) < 9.0D) {
/* 60 */       g();
/*    */     }
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private EntityAnimal h() {
/* 66 */     List<EntityAnimal> var0 = this.b.a(this.e, d, this.animal, this.animal.getBoundingBox().g(8.0D));
/* 67 */     double var1 = Double.MAX_VALUE;
/* 68 */     EntityAnimal var3 = null;
/* 69 */     for (EntityAnimal var5 : var0) {
/* 70 */       if (this.animal.mate(var5) && this.animal.h(var5) < var1) {
/* 71 */         var3 = var5;
/* 72 */         var1 = this.animal.h(var5);
/*    */       } 
/*    */     } 
/* 75 */     return var3;
/*    */   }
/*    */   
/*    */   protected void g() {
/* 79 */     this.animal.a((WorldServer)this.b, this.partner);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalBreed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */