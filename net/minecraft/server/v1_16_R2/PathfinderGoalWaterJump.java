/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalWaterJump
/*     */   extends PathfinderGoalWaterJumpAbstract
/*     */ {
/*  14 */   private static final int[] a = new int[] { 0, 1, 4, 5, 6, 7 };
/*     */   
/*     */   private final EntityDolphin b;
/*     */   
/*     */   private final int c;
/*     */   
/*     */   private boolean d;
/*     */   
/*     */   public PathfinderGoalWaterJump(EntityDolphin var0, int var1) {
/*  23 */     this.b = var0;
/*  24 */     this.c = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  29 */     if (this.b.getRandom().nextInt(this.c) != 0) {
/*  30 */       return false;
/*     */     }
/*     */     
/*  33 */     EnumDirection var0 = this.b.getAdjustedDirection();
/*  34 */     int var1 = var0.getAdjacentX();
/*  35 */     int var2 = var0.getAdjacentZ();
/*  36 */     BlockPosition var3 = this.b.getChunkCoordinates();
/*     */     
/*  38 */     for (int var7 : a) {
/*  39 */       if (!a(var3, var1, var2, var7) || !b(var3, var1, var2, var7)) {
/*  40 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  44 */     return true;
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition var0, int var1, int var2, int var3) {
/*  48 */     BlockPosition var4 = var0.b(var1 * var3, 0, var2 * var3);
/*  49 */     return (this.b.world.getFluid(var4).a(TagsFluid.WATER) && !this.b.world.getType(var4).getMaterial().isSolid());
/*     */   }
/*     */   
/*     */   private boolean b(BlockPosition var0, int var1, int var2, int var3) {
/*  53 */     return (this.b.world.getType(var0.b(var1 * var3, 1, var2 * var3)).isAir() && this.b.world
/*  54 */       .getType(var0.b(var1 * var3, 2, var2 * var3)).isAir());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  59 */     double var0 = (this.b.getMot()).y;
/*  60 */     return ((var0 * var0 >= 0.029999999329447746D || this.b.pitch == 0.0F || Math.abs(this.b.pitch) >= 10.0F || !this.b.isInWater()) && !this.b.isOnGround());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean C_() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/*  71 */     EnumDirection var0 = this.b.getAdjustedDirection();
/*  72 */     this.b.setMot(this.b.getMot().add(var0
/*  73 */           .getAdjacentX() * 0.6D, 0.7D, var0
/*     */           
/*  75 */           .getAdjacentZ() * 0.6D));
/*     */ 
/*     */     
/*  78 */     this.b.getNavigation().o();
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  83 */     this.b.pitch = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  88 */     boolean var0 = this.d;
/*  89 */     if (!var0) {
/*  90 */       Fluid fluid = this.b.world.getFluid(this.b.getChunkCoordinates());
/*  91 */       this.d = fluid.a(TagsFluid.WATER);
/*     */     } 
/*     */     
/*  94 */     if (this.d && !var0) {
/*  95 */       this.b.playSound(SoundEffects.ENTITY_DOLPHIN_JUMP, 1.0F, 1.0F);
/*     */     }
/*     */     
/*  98 */     Vec3D var1 = this.b.getMot();
/*  99 */     if (var1.y * var1.y < 0.029999999329447746D && this.b.pitch != 0.0F) {
/* 100 */       this.b.pitch = MathHelper.j(this.b.pitch, 0.0F, 0.2F);
/*     */     } else {
/* 102 */       double var2 = Math.sqrt(Entity.c(var1));
/* 103 */       double var4 = Math.signum(-var1.y) * Math.acos(var2 / var1.f()) * 57.2957763671875D;
/* 104 */       this.b.pitch = (float)var4;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalWaterJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */