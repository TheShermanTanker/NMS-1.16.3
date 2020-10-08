/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalLookAtPlayer
/*    */   extends PathfinderGoal
/*    */ {
/*    */   protected final EntityInsentient a;
/*    */   protected Entity b;
/*    */   protected final float c;
/*    */   private int g;
/*    */   protected final float d;
/*    */   protected final Class<? extends EntityLiving> e;
/*    */   protected final PathfinderTargetCondition f;
/*    */   
/*    */   public PathfinderGoalLookAtPlayer(EntityInsentient var0, Class<? extends EntityLiving> var1, float var2) {
/* 22 */     this(var0, var1, var2, 0.02F);
/*    */   }
/*    */   
/*    */   public PathfinderGoalLookAtPlayer(EntityInsentient var0, Class<? extends EntityLiving> var1, float var2, float var3) {
/* 26 */     this.a = var0;
/* 27 */     this.e = var1;
/* 28 */     this.c = var2;
/* 29 */     this.d = var3;
/* 30 */     a(EnumSet.of(PathfinderGoal.Type.LOOK));
/*    */     
/* 32 */     if (var1 == EntityHuman.class) {
/* 33 */       this.f = (new PathfinderTargetCondition()).a(var2).b().a().d().a(var1 -> IEntitySelector.b(var0).test(var1));
/*    */     } else {
/* 35 */       this.f = (new PathfinderTargetCondition()).a(var2).b().a().d();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 41 */     if (this.a.getRandom().nextFloat() >= this.d) {
/* 42 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 46 */     if (this.a.getGoalTarget() != null) {
/* 47 */       this.b = this.a.getGoalTarget();
/*    */     }
/* 49 */     if (this.e == EntityHuman.class) {
/* 50 */       this.b = this.a.world.a(this.f, this.a, this.a.locX(), this.a.getHeadY(), this.a.locZ());
/*    */     } else {
/* 52 */       this.b = this.a.world.b((Class)this.e, this.f, this.a, this.a.locX(), this.a.getHeadY(), this.a.locZ(), this.a.getBoundingBox().grow(this.c, 3.0D, this.c));
/*    */     } 
/*    */     
/* 55 */     return (this.b != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 60 */     if (!this.b.isAlive()) {
/* 61 */       return false;
/*    */     }
/* 63 */     if (this.a.h(this.b) > (this.c * this.c)) {
/* 64 */       return false;
/*    */     }
/* 66 */     return (this.g > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 71 */     this.g = 40 + this.a.getRandom().nextInt(40);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 76 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 81 */     this.a.getControllerLook().a(this.b.locX(), this.b.getHeadY(), this.b.locZ());
/* 82 */     this.g--;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalLookAtPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */