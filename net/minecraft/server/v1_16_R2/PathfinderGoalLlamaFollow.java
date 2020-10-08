/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
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
/*     */ public class PathfinderGoalLlamaFollow
/*     */   extends PathfinderGoal
/*     */ {
/*     */   public final EntityLlama a;
/*     */   private double b;
/*     */   private int c;
/*     */   
/*     */   public PathfinderGoalLlamaFollow(EntityLlama var0, double var1) {
/*  23 */     this.a = var0;
/*  24 */     this.b = var1;
/*  25 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  30 */     if (this.a.isLeashed() || this.a.fC()) {
/*  31 */       return false;
/*     */     }
/*     */     
/*  34 */     List<Entity> var0 = this.a.world.getEntities(this.a, this.a.getBoundingBox().grow(9.0D, 4.0D, 9.0D), var0 -> {
/*     */           EntityTypes<?> var1 = var0.getEntityType();
/*  36 */           return (var1 == EntityTypes.LLAMA || var1 == EntityTypes.TRADER_LLAMA);
/*     */         });
/*     */     
/*  39 */     EntityLlama var1 = null;
/*  40 */     double var2 = Double.MAX_VALUE;
/*  41 */     for (Entity var5 : var0) {
/*  42 */       EntityLlama var6 = (EntityLlama)var5;
/*     */       
/*  44 */       if (!var6.fC() || var6.fB()) {
/*     */         continue;
/*     */       }
/*     */       
/*  48 */       double var7 = this.a.h(var6);
/*  49 */       if (var7 > var2) {
/*     */         continue;
/*     */       }
/*     */       
/*  53 */       var2 = var7;
/*  54 */       var1 = var6;
/*     */     } 
/*     */     
/*  57 */     if (var1 == null)
/*     */     {
/*  59 */       for (Entity var5 : var0) {
/*  60 */         EntityLlama var6 = (EntityLlama)var5;
/*     */         
/*  62 */         if (!var6.isLeashed()) {
/*     */           continue;
/*     */         }
/*     */         
/*  66 */         if (var6.fB()) {
/*     */           continue;
/*     */         }
/*     */         
/*  70 */         double var7 = this.a.h(var6);
/*  71 */         if (var7 > var2) {
/*     */           continue;
/*     */         }
/*     */         
/*  75 */         var2 = var7;
/*  76 */         var1 = var6;
/*     */       } 
/*     */     }
/*     */     
/*  80 */     if (var1 == null) {
/*  81 */       return false;
/*     */     }
/*  83 */     if (var2 < 4.0D) {
/*  84 */       return false;
/*     */     }
/*     */     
/*  87 */     if (!var1.isLeashed() && !a(var1, 1)) {
/*  88 */       return false;
/*     */     }
/*     */     
/*  91 */     this.a.a(var1);
/*     */     
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  98 */     if (!this.a.fC() || !this.a.fD().isAlive() || !a(this.a, 0)) {
/*  99 */       return false;
/*     */     }
/*     */     
/* 102 */     double var0 = this.a.h(this.a.fD());
/* 103 */     if (var0 > 676.0D) {
/* 104 */       if (this.b <= 3.0D) {
/* 105 */         this.b *= 1.2D;
/* 106 */         this.c = 40;
/* 107 */         return true;
/*     */       } 
/*     */       
/* 110 */       if (this.c == 0) {
/* 111 */         return false;
/*     */       }
/*     */     } 
/* 114 */     if (this.c > 0) {
/* 115 */       this.c--;
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/* 122 */     this.a.fA();
/* 123 */     this.b = 2.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/* 128 */     if (!this.a.fC()) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     if (this.a.getLeashHolder() instanceof EntityLeash) {
/*     */       return;
/*     */     }
/*     */     
/* 136 */     EntityLlama var0 = this.a.fD();
/* 137 */     double var1 = this.a.g(var0);
/*     */     
/* 139 */     float var3 = 2.0F;
/* 140 */     Vec3D var4 = (new Vec3D(var0.locX() - this.a.locX(), var0.locY() - this.a.locY(), var0.locZ() - this.a.locZ())).d().a(Math.max(var1 - 2.0D, 0.0D));
/* 141 */     this.a.getNavigation().a(this.a.locX() + var4.x, this.a.locY() + var4.y, this.a.locZ() + var4.z, this.b);
/*     */   }
/*     */   
/*     */   private boolean a(EntityLlama var0, int var1) {
/* 145 */     if (var1 > 8) {
/* 146 */       return false;
/*     */     }
/*     */     
/* 149 */     if (var0.fC()) {
/* 150 */       if (var0.fD().isLeashed()) {
/* 151 */         return true;
/*     */       }
/* 153 */       return a(var0.fD(), ++var1);
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalLlamaFollow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */