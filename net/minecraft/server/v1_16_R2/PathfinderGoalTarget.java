/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public abstract class PathfinderGoalTarget
/*     */   extends PathfinderGoal {
/*     */   protected final EntityInsentient e;
/*     */   protected final boolean f;
/*     */   private final boolean a;
/*     */   private int b;
/*     */   private int c;
/*     */   private int d;
/*     */   protected EntityLiving g;
/*     */   protected int h;
/*     */   
/*     */   public PathfinderGoalTarget(EntityInsentient entityinsentient, boolean flag) {
/*  18 */     this(entityinsentient, flag, false);
/*     */   }
/*     */   
/*     */   public PathfinderGoalTarget(EntityInsentient entityinsentient, boolean flag, boolean flag1) {
/*  22 */     this.h = 60;
/*  23 */     this.e = entityinsentient;
/*  24 */     this.f = flag;
/*  25 */     this.a = flag1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  30 */     EntityLiving entityliving = this.e.getGoalTarget();
/*     */     
/*  32 */     if (entityliving == null) {
/*  33 */       entityliving = this.g;
/*     */     }
/*     */     
/*  36 */     if (entityliving == null)
/*  37 */       return false; 
/*  38 */     if (!entityliving.isAlive()) {
/*  39 */       return false;
/*     */     }
/*  41 */     ScoreboardTeamBase scoreboardteambase = this.e.getScoreboardTeam();
/*  42 */     ScoreboardTeamBase scoreboardteambase1 = entityliving.getScoreboardTeam();
/*     */     
/*  44 */     if (scoreboardteambase != null && scoreboardteambase1 == scoreboardteambase) {
/*  45 */       return false;
/*     */     }
/*  47 */     double d0 = k();
/*     */     
/*  49 */     if (this.e.h(entityliving) > d0 * d0) {
/*  50 */       return false;
/*     */     }
/*  52 */     if (this.f) {
/*  53 */       if (this.e.getEntitySenses().a(entityliving)) {
/*  54 */         this.d = 0;
/*  55 */       } else if (++this.d > this.h) {
/*  56 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*  60 */     if (entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.isInvulnerable) {
/*  61 */       return false;
/*     */     }
/*  63 */     this.e.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.CLOSEST_ENTITY, true);
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double k() {
/*  72 */     return this.e.b(GenericAttributes.FOLLOW_RANGE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  77 */     this.b = 0;
/*  78 */     this.c = 0;
/*  79 */     this.d = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  84 */     this.e.setGoalTarget((EntityLiving)null, EntityTargetEvent.TargetReason.FORGOT_TARGET, true);
/*  85 */     this.g = null;
/*     */   }
/*     */   
/*     */   protected boolean a(@Nullable EntityLiving entityliving, PathfinderTargetCondition pathfindertargetcondition) {
/*  89 */     if (entityliving == null)
/*  90 */       return false; 
/*  91 */     if (!pathfindertargetcondition.a(this.e, entityliving))
/*  92 */       return false; 
/*  93 */     if (!this.e.a(entityliving.getChunkCoordinates())) {
/*  94 */       return false;
/*     */     }
/*  96 */     if (this.a) {
/*  97 */       if (--this.c <= 0) {
/*  98 */         this.b = 0;
/*     */       }
/*     */       
/* 101 */       if (this.b == 0) {
/* 102 */         this.b = a(entityliving) ? 1 : 2;
/*     */       }
/*     */       
/* 105 */       if (this.b == 2) {
/* 106 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(EntityLiving entityliving) {
/* 115 */     this.c = 10 + this.e.getRandom().nextInt(5);
/* 116 */     PathEntity pathentity = this.e.getNavigation().a(entityliving, 0);
/*     */     
/* 118 */     if (pathentity == null) {
/* 119 */       return false;
/*     */     }
/* 121 */     PathPoint pathpoint = pathentity.d();
/*     */     
/* 123 */     if (pathpoint == null) {
/* 124 */       return false;
/*     */     }
/* 126 */     int i = pathpoint.a - MathHelper.floor(entityliving.locX());
/* 127 */     int j = pathpoint.c - MathHelper.floor(entityliving.locZ());
/*     */     
/* 129 */     return ((i * i + j * j) <= 2.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathfinderGoalTarget a(int i) {
/* 135 */     this.h = i;
/* 136 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */