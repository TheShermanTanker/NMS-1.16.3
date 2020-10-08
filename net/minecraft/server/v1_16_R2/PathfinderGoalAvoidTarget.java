/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalAvoidTarget<T extends EntityLiving>
/*    */   extends PathfinderGoal
/*    */ {
/*    */   protected final EntityCreature a;
/*    */   private final double i;
/*    */   private final double j;
/*    */   protected T b;
/*    */   protected final float c;
/*    */   protected PathEntity d;
/*    */   protected final NavigationAbstract e;
/*    */   protected final Class<T> f;
/*    */   protected final Predicate<EntityLiving> g;
/*    */   protected final Predicate<EntityLiving> h;
/*    */   private final PathfinderTargetCondition k;
/*    */   
/*    */   public PathfinderGoalAvoidTarget(EntityCreature var0, Class<T> var1, float var2, double var3, double var5) {
/* 29 */     this(var0, var1, var0 -> true, var2, var3, var5, IEntitySelector.e::test);
/*    */   }
/*    */   
/*    */   public PathfinderGoalAvoidTarget(EntityCreature var0, Class<T> var1, Predicate<EntityLiving> var2, float var3, double var4, double var6, Predicate<EntityLiving> var8) {
/* 33 */     this.a = var0;
/* 34 */     this.f = var1;
/* 35 */     this.g = var2;
/* 36 */     this.c = var3;
/* 37 */     this.i = var4;
/* 38 */     this.j = var6;
/* 39 */     this.h = var8;
/* 40 */     this.e = var0.getNavigation();
/* 41 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */     
/* 43 */     this.k = (new PathfinderTargetCondition()).a(var3).a(var8.and(var2));
/*    */   }
/*    */   
/*    */   public PathfinderGoalAvoidTarget(EntityCreature var0, Class<T> var1, float var2, double var3, double var5, Predicate<EntityLiving> var7) {
/* 47 */     this(var0, var1, var0 -> true, var2, var3, var5, var7);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 52 */     this.b = this.a.world.b(this.f, this.k, this.a, this.a.locX(), this.a.locY(), this.a.locZ(), this.a.getBoundingBox().grow(this.c, 3.0D, this.c));
/* 53 */     if (this.b == null) {
/* 54 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     Vec3D var0 = RandomPositionGenerator.c(this.a, 16, 7, this.b.getPositionVector());
/* 61 */     if (var0 == null) {
/* 62 */       return false;
/*    */     }
/* 64 */     if (this.b.h(var0.x, var0.y, var0.z) < this.b.h(this.a)) {
/* 65 */       return false;
/*    */     }
/* 67 */     this.d = this.e.a(var0.x, var0.y, var0.z, 0);
/* 68 */     return (this.d != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 73 */     return !this.e.m();
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 78 */     this.e.a(this.d, this.i);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 83 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 88 */     if (this.a.h((Entity)this.b) < 49.0D) {
/* 89 */       this.a.getNavigation().a(this.j);
/*    */     } else {
/* 91 */       this.a.getNavigation().a(this.i);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalAvoidTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */