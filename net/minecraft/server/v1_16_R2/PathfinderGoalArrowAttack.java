/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalArrowAttack
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityInsentient a;
/*    */   private final IRangedEntity b;
/*    */   private EntityLiving c;
/* 14 */   private int d = -1;
/*    */   private final double e;
/*    */   private int f;
/*    */   private final int g;
/*    */   private final int h;
/*    */   private final float i;
/*    */   private final float j;
/*    */   
/*    */   public PathfinderGoalArrowAttack(IRangedEntity var0, double var1, int var3, float var4) {
/* 23 */     this(var0, var1, var3, var3, var4);
/*    */   }
/*    */   
/*    */   public PathfinderGoalArrowAttack(IRangedEntity var0, double var1, int var3, int var4, float var5) {
/* 27 */     if (!(var0 instanceof EntityLiving)) {
/* 28 */       throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
/*    */     }
/* 30 */     this.b = var0;
/* 31 */     this.a = (EntityInsentient)var0;
/* 32 */     this.e = var1;
/* 33 */     this.g = var3;
/* 34 */     this.h = var4;
/* 35 */     this.i = var5;
/* 36 */     this.j = var5 * var5;
/* 37 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 42 */     EntityLiving var0 = this.a.getGoalTarget();
/* 43 */     if (var0 == null || !var0.isAlive()) {
/* 44 */       return false;
/*    */     }
/* 46 */     this.c = var0;
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 52 */     return (a() || !this.a.getNavigation().m());
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 57 */     this.c = null;
/* 58 */     this.f = 0;
/* 59 */     this.d = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 64 */     double var0 = this.a.h(this.c.locX(), this.c.locY(), this.c.locZ());
/* 65 */     boolean var2 = this.a.getEntitySenses().a(this.c);
/*    */     
/* 67 */     if (var2) {
/* 68 */       this.f++;
/*    */     } else {
/* 70 */       this.f = 0;
/*    */     } 
/*    */     
/* 73 */     if (var0 > this.j || this.f < 5) {
/* 74 */       this.a.getNavigation().a(this.c, this.e);
/*    */     } else {
/* 76 */       this.a.getNavigation().o();
/*    */     } 
/*    */     
/* 79 */     this.a.getControllerLook().a(this.c, 30.0F, 30.0F);
/*    */     
/* 81 */     if (--this.d == 0) {
/* 82 */       if (!var2) {
/*    */         return;
/*    */       }
/*    */       
/* 86 */       float var3 = MathHelper.sqrt(var0) / this.i;
/* 87 */       float var4 = var3;
/* 88 */       var4 = MathHelper.a(var4, 0.1F, 1.0F);
/*    */       
/* 90 */       this.b.a(this.c, var4);
/* 91 */       this.d = MathHelper.d(var3 * (this.h - this.g) + this.g);
/* 92 */     } else if (this.d < 0) {
/* 93 */       float var3 = MathHelper.sqrt(var0) / this.i;
/* 94 */       this.d = MathHelper.d(var3 * (this.h - this.g) + this.g);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalArrowAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */