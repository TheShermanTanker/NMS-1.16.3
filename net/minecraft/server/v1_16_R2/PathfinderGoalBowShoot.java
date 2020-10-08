/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalBowShoot<T extends EntityMonster & IRangedEntity>
/*     */   extends PathfinderGoal
/*     */ {
/*     */   private final T a;
/*     */   private final double b;
/*     */   private int c;
/*     */   private final float d;
/*  18 */   private int e = -1;
/*     */   private int f;
/*     */   private boolean g;
/*     */   private boolean h;
/*  22 */   private int i = -1;
/*     */   
/*     */   public PathfinderGoalBowShoot(T var0, double var1, int var3, float var4) {
/*  25 */     this.a = var0;
/*  26 */     this.b = var1;
/*  27 */     this.c = var3;
/*  28 */     this.d = var4 * var4;
/*  29 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */   }
/*     */   
/*     */   public void a(int var0) {
/*  33 */     this.c = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  38 */     if (this.a.getGoalTarget() == null) {
/*  39 */       return false;
/*     */     }
/*  41 */     return g();
/*     */   }
/*     */   
/*     */   protected boolean g() {
/*  45 */     return this.a.a(Items.BOW);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  50 */     return ((a() || !this.a.getNavigation().m()) && g());
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  55 */     super.c();
/*     */     
/*  57 */     this.a.setAggressive(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  62 */     super.d();
/*     */     
/*  64 */     this.a.setAggressive(false);
/*  65 */     this.f = 0;
/*  66 */     this.e = -1;
/*  67 */     this.a.clearActiveItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  72 */     EntityLiving var0 = this.a.getGoalTarget();
/*  73 */     if (var0 == null) {
/*     */       return;
/*     */     }
/*  76 */     double var1 = this.a.h(var0.locX(), var0.locY(), var0.locZ());
/*  77 */     boolean var3 = this.a.getEntitySenses().a(var0);
/*  78 */     boolean var4 = (this.f > 0);
/*     */     
/*  80 */     if (var3 != var4) {
/*  81 */       this.f = 0;
/*     */     }
/*     */     
/*  84 */     if (var3) {
/*  85 */       this.f++;
/*     */     } else {
/*  87 */       this.f--;
/*     */     } 
/*     */     
/*  90 */     if (var1 > this.d || this.f < 20) {
/*  91 */       this.a.getNavigation().a(var0, this.b);
/*  92 */       this.i = -1;
/*     */     } else {
/*  94 */       this.a.getNavigation().o();
/*  95 */       this.i++;
/*     */     } 
/*     */     
/*  98 */     if (this.i >= 20) {
/*  99 */       if (this.a.getRandom().nextFloat() < 0.3D) {
/* 100 */         this.g = !this.g;
/*     */       }
/* 102 */       if (this.a.getRandom().nextFloat() < 0.3D) {
/* 103 */         this.h = !this.h;
/*     */       }
/* 105 */       this.i = 0;
/*     */     } 
/*     */     
/* 108 */     if (this.i > -1) {
/* 109 */       if (var1 > (this.d * 0.75F)) {
/* 110 */         this.h = false;
/* 111 */       } else if (var1 < (this.d * 0.25F)) {
/* 112 */         this.h = true;
/*     */       } 
/* 114 */       this.a.getControllerMove().a(this.h ? -0.5F : 0.5F, this.g ? 0.5F : -0.5F);
/* 115 */       this.a.a(var0, 30.0F, 30.0F);
/*     */     } else {
/* 117 */       this.a.getControllerLook().a(var0, 30.0F, 30.0F);
/*     */     } 
/*     */     
/* 120 */     if (this.a.isHandRaised()) {
/* 121 */       if (!var3 && this.f < -60) {
/* 122 */         this.a.clearActiveItem();
/* 123 */       } else if (var3) {
/* 124 */         int var5 = this.a.dZ();
/*     */         
/* 126 */         if (var5 >= 20) {
/* 127 */           this.a.clearActiveItem();
/* 128 */           ((IRangedEntity)this.a).a(var0, ItemBow.a(var5));
/* 129 */           this.e = this.c;
/*     */         } 
/*     */       } 
/* 132 */     } else if (--this.e <= 0 && this.f >= -60) {
/* 133 */       this.a.c(ProjectileHelper.a((EntityLiving)this.a, Items.BOW));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalBowShoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */