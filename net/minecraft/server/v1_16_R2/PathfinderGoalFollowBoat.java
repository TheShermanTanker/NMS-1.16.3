/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalFollowBoat
/*     */   extends PathfinderGoal
/*     */ {
/*     */   private int a;
/*     */   private final EntityCreature b;
/*     */   private EntityHuman c;
/*     */   private PathfinderGoalBoat d;
/*     */   
/*     */   public PathfinderGoalFollowBoat(EntityCreature var0) {
/*  28 */     this.b = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  33 */     List<EntityBoat> var0 = this.b.world.a(EntityBoat.class, this.b.getBoundingBox().g(5.0D));
/*  34 */     boolean var1 = false;
/*  35 */     for (EntityBoat var3 : var0) {
/*  36 */       Entity var4 = var3.getRidingPassenger();
/*  37 */       if (var4 instanceof EntityHuman && (
/*  38 */         MathHelper.e(((EntityHuman)var4).aR) > 0.0F || MathHelper.e(((EntityHuman)var4).aT) > 0.0F)) {
/*  39 */         var1 = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/*  45 */     return ((this.c != null && (MathHelper.e(this.c.aR) > 0.0F || MathHelper.e(this.c.aT) > 0.0F)) || var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean C_() {
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  55 */     return (this.c != null && this.c.isPassenger() && (MathHelper.e(this.c.aR) > 0.0F || MathHelper.e(this.c.aT) > 0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  60 */     List<EntityBoat> var0 = this.b.world.a(EntityBoat.class, this.b.getBoundingBox().g(5.0D));
/*  61 */     for (EntityBoat var2 : var0) {
/*  62 */       if (var2.getRidingPassenger() != null && var2.getRidingPassenger() instanceof EntityHuman) {
/*  63 */         this.c = (EntityHuman)var2.getRidingPassenger();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  68 */     this.a = 0;
/*  69 */     this.d = PathfinderGoalBoat.GO_TO_BOAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  74 */     this.c = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  79 */     boolean var0 = (MathHelper.e(this.c.aR) > 0.0F || MathHelper.e(this.c.aT) > 0.0F);
/*  80 */     float var1 = (this.d == PathfinderGoalBoat.GO_IN_BOAT_DIRECTION) ? (var0 ? 0.01F : 0.0F) : 0.015F;
/*     */     
/*  82 */     this.b.a(var1, new Vec3D(this.b.aR, this.b.aS, this.b.aT));
/*  83 */     this.b.move(EnumMoveType.SELF, this.b.getMot());
/*     */     
/*  85 */     if (--this.a > 0) {
/*     */       return;
/*     */     }
/*  88 */     this.a = 10;
/*     */     
/*  90 */     if (this.d == PathfinderGoalBoat.GO_TO_BOAT) {
/*  91 */       BlockPosition var2 = this.c.getChunkCoordinates().shift(this.c.getDirection().opposite());
/*  92 */       var2 = var2.b(0, -1, 0);
/*  93 */       this.b.getNavigation().a(var2.getX(), var2.getY(), var2.getZ(), 1.0D);
/*     */       
/*  95 */       if (this.b.g(this.c) < 4.0F) {
/*  96 */         this.a = 0;
/*  97 */         this.d = PathfinderGoalBoat.GO_IN_BOAT_DIRECTION;
/*     */       } 
/*  99 */     } else if (this.d == PathfinderGoalBoat.GO_IN_BOAT_DIRECTION) {
/*     */       
/* 101 */       EnumDirection var2 = this.c.getAdjustedDirection();
/* 102 */       BlockPosition var3 = this.c.getChunkCoordinates().shift(var2, 10);
/*     */ 
/*     */       
/* 105 */       this.b.getNavigation().a(var3.getX(), (var3.getY() - 1), var3.getZ(), 1.0D);
/*     */       
/* 107 */       if (this.b.g(this.c) > 12.0F) {
/* 108 */         this.a = 0;
/* 109 */         this.d = PathfinderGoalBoat.GO_TO_BOAT;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFollowBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */