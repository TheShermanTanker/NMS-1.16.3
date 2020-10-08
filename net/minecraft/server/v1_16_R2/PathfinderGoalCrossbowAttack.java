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
/*     */ public class PathfinderGoalCrossbowAttack<T extends EntityMonster & IRangedEntity & ICrossbow>
/*     */   extends PathfinderGoal
/*     */ {
/*     */   private final T b;
/*  18 */   public static final IntRange a = new IntRange(20, 40);
/*     */   
/*     */   enum State {
/*  21 */     UNCHARGED,
/*  22 */     CHARGING,
/*  23 */     CHARGED,
/*  24 */     READY_TO_ATTACK;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  29 */   private State c = State.UNCHARGED;
/*     */   private final double d;
/*     */   private final float e;
/*     */   private int f;
/*     */   private int g;
/*     */   private int h;
/*     */   
/*     */   public PathfinderGoalCrossbowAttack(T var0, double var1, float var3) {
/*  37 */     this.b = var0;
/*  38 */     this.d = var1;
/*  39 */     this.e = var3 * var3;
/*  40 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  45 */     return (h() && g());
/*     */   }
/*     */   
/*     */   private boolean g() {
/*  49 */     return this.b.a(Items.CROSSBOW);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  54 */     return (h() && (a() || !this.b.getNavigation().m()) && g());
/*     */   }
/*     */   
/*     */   private boolean h() {
/*  58 */     return (this.b.getGoalTarget() != null && this.b.getGoalTarget().isAlive());
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  63 */     super.d();
/*  64 */     this.b.setAggressive(false);
/*  65 */     this.b.setGoalTarget((EntityLiving)null);
/*  66 */     this.f = 0;
/*  67 */     if (this.b.isHandRaised()) {
/*  68 */       this.b.clearActiveItem();
/*  69 */       ((ICrossbow)this.b).b(false);
/*  70 */       ItemCrossbow.a(this.b.getActiveItem(), false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  76 */     EntityLiving var0 = this.b.getGoalTarget();
/*  77 */     if (var0 == null) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     boolean var1 = this.b.getEntitySenses().a(var0);
/*  82 */     boolean var2 = (this.f > 0);
/*     */     
/*  84 */     if (var1 != var2) {
/*  85 */       this.f = 0;
/*     */     }
/*     */     
/*  88 */     if (var1) {
/*  89 */       this.f++;
/*     */     } else {
/*  91 */       this.f--;
/*     */     } 
/*     */     
/*  94 */     double var3 = this.b.h(var0);
/*  95 */     boolean var5 = ((var3 > this.e || this.f < 5) && this.g == 0);
/*  96 */     if (var5) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 101 */       this.h--;
/* 102 */       if (this.h <= 0) {
/* 103 */         this.b.getNavigation().a(var0, j() ? this.d : (this.d * 0.5D));
/* 104 */         this.h = a.a(this.b.getRandom());
/*     */       } 
/*     */     } else {
/* 107 */       this.h = 0;
/* 108 */       this.b.getNavigation().o();
/*     */     } 
/*     */     
/* 111 */     this.b.getControllerLook().a(var0, 30.0F, 30.0F);
/*     */     
/* 113 */     if (this.c == State.UNCHARGED) {
/* 114 */       if (!var5) {
/* 115 */         this.b.c(ProjectileHelper.a((EntityLiving)this.b, Items.CROSSBOW));
/* 116 */         this.c = State.CHARGING;
/* 117 */         ((ICrossbow)this.b).b(true);
/*     */       } 
/* 119 */     } else if (this.c == State.CHARGING) {
/* 120 */       if (!this.b.isHandRaised()) {
/* 121 */         this.c = State.UNCHARGED;
/*     */       }
/* 123 */       int var6 = this.b.dZ();
/* 124 */       ItemStack var7 = this.b.getActiveItem();
/* 125 */       if (var6 >= ItemCrossbow.g(var7)) {
/* 126 */         this.b.releaseActiveItem();
/*     */         
/* 128 */         this.c = State.CHARGED;
/* 129 */         this.g = 20 + this.b.getRandom().nextInt(20);
/* 130 */         ((ICrossbow)this.b).b(false);
/*     */       } 
/* 132 */     } else if (this.c == State.CHARGED) {
/* 133 */       this.g--;
/* 134 */       if (this.g == 0) {
/* 135 */         this.c = State.READY_TO_ATTACK;
/*     */       }
/* 137 */     } else if (this.c == State.READY_TO_ATTACK && 
/* 138 */       var1) {
/* 139 */       ((IRangedEntity)this.b).a(var0, 1.0F);
/*     */       
/* 141 */       ItemStack var6 = this.b.b(ProjectileHelper.a((EntityLiving)this.b, Items.CROSSBOW));
/* 142 */       ItemCrossbow.a(var6, false);
/* 143 */       this.c = State.UNCHARGED;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean j() {
/* 149 */     return (this.c == State.UNCHARGED);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalCrossbowAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */