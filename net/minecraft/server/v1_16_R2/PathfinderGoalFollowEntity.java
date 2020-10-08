/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalFollowEntity
/*     */   extends PathfinderGoal
/*     */ {
/*     */   private final EntityInsentient a;
/*     */   private final Predicate<EntityInsentient> b;
/*     */   private EntityInsentient c;
/*     */   private final double d;
/*     */   private final NavigationAbstract e;
/*     */   private int f;
/*     */   private final float g;
/*     */   private float h;
/*     */   private final float i;
/*     */   
/*     */   public PathfinderGoalFollowEntity(EntityInsentient var0, double var1, float var3, float var4) {
/*  26 */     this.a = var0;
/*  27 */     this.b = (var1 -> (var1 != null && var0.getClass() != var1.getClass()));
/*  28 */     this.d = var1;
/*  29 */     this.e = var0.getNavigation();
/*  30 */     this.g = var3;
/*  31 */     this.i = var4;
/*     */     
/*  33 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */     
/*  35 */     if (!(var0.getNavigation() instanceof Navigation) && !(var0.getNavigation() instanceof NavigationFlying)) {
/*  36 */       throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  42 */     List<EntityInsentient> var0 = this.a.world.a(EntityInsentient.class, this.a.getBoundingBox().g(this.i), this.b);
/*  43 */     if (!var0.isEmpty()) {
/*  44 */       for (EntityInsentient var2 : var0) {
/*  45 */         if (var2.isInvisible()) {
/*     */           continue;
/*     */         }
/*     */         
/*  49 */         this.c = var2;
/*  50 */         return true;
/*     */       } 
/*     */     }
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  58 */     return (this.c != null && !this.e.m() && this.a.h(this.c) > (this.g * this.g));
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  63 */     this.f = 0;
/*  64 */     this.h = this.a.a(PathType.WATER);
/*  65 */     this.a.a(PathType.WATER, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  70 */     this.c = null;
/*  71 */     this.e.o();
/*  72 */     this.a.a(PathType.WATER, this.h);
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  77 */     if (this.c == null || this.a.isLeashed()) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     this.a.getControllerLook().a(this.c, 10.0F, this.a.O());
/*     */     
/*  83 */     if (--this.f > 0) {
/*     */       return;
/*     */     }
/*  86 */     this.f = 10;
/*     */     
/*  88 */     double var0 = this.a.locX() - this.c.locX();
/*  89 */     double var2 = this.a.locY() - this.c.locY();
/*  90 */     double var4 = this.a.locZ() - this.c.locZ();
/*     */     
/*  92 */     double var6 = var0 * var0 + var2 * var2 + var4 * var4;
/*  93 */     if (var6 <= (this.g * this.g)) {
/*  94 */       this.e.o();
/*     */       
/*  96 */       ControllerLook var8 = this.c.getControllerLook();
/*  97 */       if (var6 <= this.g || (var8.d() == this.a.locX() && var8.e() == this.a.locY() && var8.f() == this.a.locZ())) {
/*  98 */         double var9 = this.c.locX() - this.a.locX();
/*  99 */         double var11 = this.c.locZ() - this.a.locZ();
/* 100 */         this.e.a(this.a.locX() - var9, this.a.locY(), this.a.locZ() - var11, this.d);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 105 */     this.e.a(this.c, this.d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFollowEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */