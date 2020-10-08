/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class PathfinderGoalPanic
/*     */   extends PathfinderGoal {
/*     */   protected final EntityCreature a;
/*     */   protected final double b;
/*     */   protected double c;
/*     */   protected double d;
/*     */   protected double e;
/*     */   protected boolean f;
/*     */   
/*     */   public PathfinderGoalPanic(EntityCreature entitycreature, double d0) {
/*  16 */     this.a = entitycreature;
/*  17 */     this.b = d0;
/*  18 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  23 */     if (this.a.getLastDamager() == null && !this.a.isBurning()) {
/*  24 */       return false;
/*     */     }
/*  26 */     if (this.a.isBurning()) {
/*  27 */       BlockPosition blockposition = a(this.a.world, this.a, 5, 4);
/*     */       
/*  29 */       if (blockposition != null) {
/*  30 */         this.c = blockposition.getX();
/*  31 */         this.d = blockposition.getY();
/*  32 */         this.e = blockposition.getZ();
/*  33 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  37 */     return g();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean g() {
/*  42 */     Vec3D vec3d = RandomPositionGenerator.a(this.a, 5, 4);
/*     */     
/*  44 */     if (vec3d == null) {
/*  45 */       return false;
/*     */     }
/*  47 */     this.c = vec3d.x;
/*  48 */     this.d = vec3d.y;
/*  49 */     this.e = vec3d.z;
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean h() {
/*  55 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  60 */     this.a.getNavigation().a(this.c, this.d, this.e, this.b);
/*  61 */     this.f = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  66 */     this.f = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  72 */     if (this.a.ticksLived - this.a.hurtTimestamp > 100) {
/*  73 */       this.a.setLastDamager((EntityLiving)null);
/*  74 */       return false;
/*     */     } 
/*     */     
/*  77 */     return !this.a.getNavigation().m();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected BlockPosition a(IBlockAccess iblockaccess, Entity entity, int i, int j) {
/*  82 */     BlockPosition blockposition = entity.getChunkCoordinates();
/*  83 */     int k = blockposition.getX();
/*  84 */     int l = blockposition.getY();
/*  85 */     int i1 = blockposition.getZ();
/*  86 */     float f = (i * i * j * 2);
/*  87 */     BlockPosition blockposition1 = null;
/*  88 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/*  90 */     for (int j1 = k - i; j1 <= k + i; j1++) {
/*  91 */       for (int k1 = l - j; k1 <= l + j; k1++) {
/*  92 */         for (int l1 = i1 - i; l1 <= i1 + i; l1++) {
/*  93 */           blockposition_mutableblockposition.d(j1, k1, l1);
/*  94 */           if (iblockaccess.getFluid(blockposition_mutableblockposition).a(TagsFluid.WATER)) {
/*  95 */             float f1 = ((j1 - k) * (j1 - k) + (k1 - l) * (k1 - l) + (l1 - i1) * (l1 - i1));
/*     */             
/*  97 */             if (f1 < f) {
/*  98 */               f = f1;
/*  99 */               blockposition1 = new BlockPosition(blockposition_mutableblockposition);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return blockposition1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalPanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */