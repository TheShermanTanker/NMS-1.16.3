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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalMeleeAttack
/*     */   extends PathfinderGoal
/*     */ {
/*     */   protected final EntityCreature a;
/*     */   private final double b;
/*     */   private final boolean c;
/*     */   private PathEntity d;
/*     */   private double e;
/*     */   private double f;
/*     */   private double g;
/*     */   private int h;
/*     */   private int i;
/*  26 */   private final int j = 20;
/*     */   
/*     */   private long k;
/*     */ 
/*     */   
/*     */   public PathfinderGoalMeleeAttack(EntityCreature var0, double var1, boolean var3) {
/*  32 */     this.a = var0;
/*  33 */     this.b = var1;
/*  34 */     this.c = var3;
/*  35 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  40 */     long var0 = this.a.world.getTime();
/*  41 */     if (var0 - this.k < 20L) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     this.k = var0;
/*     */     
/*  47 */     EntityLiving var2 = this.a.getGoalTarget();
/*  48 */     if (var2 == null) {
/*  49 */       return false;
/*     */     }
/*  51 */     if (!var2.isAlive()) {
/*  52 */       return false;
/*     */     }
/*  54 */     this.d = this.a.getNavigation().a(var2, 0);
/*  55 */     if (this.d != null) {
/*  56 */       return true;
/*     */     }
/*  58 */     if (a(var2) >= this.a.h(var2.locX(), var2.locY(), var2.locZ())) {
/*  59 */       return true;
/*     */     }
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  66 */     EntityLiving var0 = this.a.getGoalTarget();
/*  67 */     if (var0 == null) {
/*  68 */       return false;
/*     */     }
/*  70 */     if (!var0.isAlive()) {
/*  71 */       return false;
/*     */     }
/*  73 */     if (!this.c) {
/*  74 */       return !this.a.getNavigation().m();
/*     */     }
/*  76 */     if (!this.a.a(var0.getChunkCoordinates())) {
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     if (var0 instanceof EntityHuman && (var0.isSpectator() || ((EntityHuman)var0).isCreative())) {
/*  81 */       return false;
/*     */     }
/*     */     
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  89 */     this.a.getNavigation().a(this.d, this.b);
/*  90 */     this.a.setAggressive(true);
/*  91 */     this.h = 0;
/*  92 */     this.i = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  97 */     EntityLiving var0 = this.a.getGoalTarget();
/*  98 */     if (!IEntitySelector.e.test(var0)) {
/*  99 */       this.a.setGoalTarget((EntityLiving)null);
/*     */     }
/* 101 */     this.a.setAggressive(false);
/* 102 */     this.a.getNavigation().o();
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/* 107 */     EntityLiving var0 = this.a.getGoalTarget();
/* 108 */     this.a.getControllerLook().a(var0, 30.0F, 30.0F);
/* 109 */     double var1 = this.a.h(var0.locX(), var0.locY(), var0.locZ());
/* 110 */     this.h = Math.max(this.h - 1, 0);
/*     */     
/* 112 */     if ((this.c || this.a.getEntitySenses().a(var0)) && 
/* 113 */       this.h <= 0 && ((
/* 114 */       this.e == 0.0D && this.f == 0.0D && this.g == 0.0D) || var0.h(this.e, this.f, this.g) >= 1.0D || this.a.getRandom().nextFloat() < 0.05F)) {
/* 115 */       this.e = var0.locX();
/* 116 */       this.f = var0.locY();
/* 117 */       this.g = var0.locZ();
/* 118 */       this.h = 4 + this.a.getRandom().nextInt(7);
/*     */       
/* 120 */       if (var1 > 1024.0D) {
/* 121 */         this.h += 10;
/* 122 */       } else if (var1 > 256.0D) {
/* 123 */         this.h += 5;
/*     */       } 
/*     */       
/* 126 */       if (!this.a.getNavigation().a(var0, this.b)) {
/* 127 */         this.h += 15;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 133 */     this.i = Math.max(this.i - 1, 0);
/* 134 */     a(var0, var1);
/*     */   }
/*     */   
/*     */   protected void a(EntityLiving var0, double var1) {
/* 138 */     double var3 = a(var0);
/* 139 */     if (var1 <= var3 && this.i <= 0) {
/* 140 */       g();
/* 141 */       this.a.swingHand(EnumHand.MAIN_HAND);
/* 142 */       this.a.attackEntity(var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void g() {
/* 147 */     this.i = 20;
/*     */   }
/*     */   
/*     */   protected boolean h() {
/* 151 */     return (this.i <= 0);
/*     */   }
/*     */   
/*     */   protected int j() {
/* 155 */     return this.i;
/*     */   }
/*     */   
/*     */   protected int k() {
/* 159 */     return 20;
/*     */   }
/*     */   
/*     */   protected double a(EntityLiving var0) {
/* 163 */     return (this.a.getWidth() * 2.0F * this.a.getWidth() * 2.0F + var0.getWidth());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalMeleeAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */