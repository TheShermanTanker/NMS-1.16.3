/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class PathfinderTargetCondition
/*     */ {
/*   8 */   public static final PathfinderTargetCondition a = new PathfinderTargetCondition();
/*   9 */   private double b = -1.0D;
/*     */   
/*     */   private boolean c;
/*     */   private boolean d;
/*     */   private boolean e;
/*     */   private boolean f;
/*     */   private boolean g = true;
/*     */   private Predicate<EntityLiving> h;
/*     */   private boolean useFollowRange;
/*     */   
/*     */   public PathfinderTargetCondition a(double d0) {
/*  20 */     this.b = d0;
/*  21 */     return this;
/*     */   }
/*     */   
/*     */   public PathfinderTargetCondition a() {
/*  25 */     this.c = true;
/*  26 */     return this;
/*     */   }
/*     */   
/*     */   public PathfinderTargetCondition b() {
/*  30 */     this.d = true;
/*  31 */     return this;
/*     */   }
/*     */   
/*     */   public PathfinderTargetCondition c() {
/*  35 */     this.e = true;
/*  36 */     return this;
/*     */   }
/*     */   
/*     */   public PathfinderTargetCondition d() {
/*  40 */     this.f = true;
/*  41 */     return this;
/*     */   }
/*     */   
/*     */   public PathfinderTargetCondition e() {
/*  45 */     this.g = false;
/*  46 */     return this;
/*     */   }
/*     */   
/*     */   public PathfinderTargetCondition a(@Nullable Predicate<EntityLiving> predicate) {
/*  50 */     this.h = predicate;
/*  51 */     return this;
/*     */   }
/*     */   
/*     */   public boolean a(@Nullable EntityLiving entityliving, EntityLiving entityliving1) {
/*  55 */     if (entityliving == entityliving1)
/*  56 */       return false; 
/*  57 */     if (entityliving1.isSpectator())
/*  58 */       return false; 
/*  59 */     if (!entityliving1.isAlive())
/*  60 */       return false; 
/*  61 */     if (!this.c && entityliving1.isInvulnerable())
/*  62 */       return false; 
/*  63 */     if (this.h != null && !this.h.test(entityliving1)) {
/*  64 */       return false;
/*     */     }
/*  66 */     if (entityliving != null) {
/*  67 */       if (!this.f) {
/*  68 */         if (!entityliving.c(entityliving1)) {
/*  69 */           return false;
/*     */         }
/*     */         
/*  72 */         if (!entityliving.a(entityliving1.getEntityType())) {
/*  73 */           return false;
/*     */         }
/*     */       } 
/*     */       
/*  77 */       if (!this.d && entityliving.r(entityliving1)) {
/*  78 */         return false;
/*     */       }
/*     */       
/*  81 */       if (this.b > 0.0D) {
/*  82 */         double d0 = this.g ? entityliving1.A(entityliving) : 1.0D;
/*  83 */         double d1 = Math.max((this.useFollowRange ? getFollowRange(entityliving) : this.b) * d0, 2.0D);
/*  84 */         double d2 = entityliving.h(entityliving1.locX(), entityliving1.locY(), entityliving1.locZ());
/*     */         
/*  86 */         if (d2 > d1 * d1) {
/*  87 */           return false;
/*     */         }
/*     */       } 
/*     */       
/*  91 */       if (!this.e && entityliving instanceof EntityInsentient && !((EntityInsentient)entityliving).getEntitySenses().a(entityliving1)) {
/*  92 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathfinderTargetCondition() {
/* 101 */     this.useFollowRange = false;
/*     */   }
/*     */   public PathfinderTargetCondition useFollowRange() {
/* 104 */     this.useFollowRange = true;
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   private double getFollowRange(EntityLiving entityliving) {
/* 109 */     AttributeModifiable attributeinstance = entityliving.getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
/* 110 */     return (attributeinstance == null) ? 16.0D : attributeinstance.getValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderTargetCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */