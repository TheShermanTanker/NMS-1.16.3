/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderWater
/*     */   extends PathfinderAbstract
/*     */ {
/*     */   private final boolean j;
/*     */   
/*     */   public PathfinderWater(boolean var0) {
/*  18 */     this.j = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathPoint b() {
/*  23 */     return super.a(MathHelper.floor((this.b.getBoundingBox()).minX), MathHelper.floor((this.b.getBoundingBox()).minY + 0.5D), MathHelper.floor((this.b.getBoundingBox()).minZ));
/*     */   }
/*     */ 
/*     */   
/*     */   public PathDestination a(double var0, double var2, double var4) {
/*  28 */     return new PathDestination(super.a(MathHelper.floor(var0 - (this.b.getWidth() / 2.0F)), MathHelper.floor(var2 + 0.5D), MathHelper.floor(var4 - (this.b.getWidth() / 2.0F))));
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(PathPoint[] var0, PathPoint var1) {
/*  33 */     int var2 = 0;
/*     */     
/*  35 */     for (EnumDirection var6 : EnumDirection.values()) {
/*  36 */       PathPoint var7 = b(var1.a + var6.getAdjacentX(), var1.b + var6.getAdjacentY(), var1.c + var6.getAdjacentZ());
/*  37 */       if (var7 != null && !var7.i) {
/*  38 */         var0[var2++] = var7;
/*     */       }
/*     */     } 
/*     */     
/*  42 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess var0, int var1, int var2, int var3, EntityInsentient var4, int var5, int var6, int var7, boolean var8, boolean var9) {
/*  47 */     return a(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess var0, int var1, int var2, int var3) {
/*  52 */     BlockPosition var4 = new BlockPosition(var1, var2, var3);
/*  53 */     Fluid var5 = var0.getFluid(var4);
/*  54 */     IBlockData var6 = var0.getType(var4);
/*     */     
/*  56 */     if (var5.isEmpty() && var6.a(var0, var4.down(), PathMode.WATER) && var6.isAir())
/*  57 */       return PathType.BREACH; 
/*  58 */     if (!var5.a(TagsFluid.WATER) || !var6.a(var0, var4, PathMode.WATER)) {
/*  59 */       return PathType.BLOCKED;
/*     */     }
/*     */     
/*  62 */     return PathType.WATER;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private PathPoint b(int var0, int var1, int var2) {
/*  67 */     PathType var3 = c(var0, var1, var2);
/*     */     
/*  69 */     if ((this.j && var3 == PathType.BREACH) || var3 == PathType.WATER) {
/*  70 */       return a(var0, var1, var2);
/*     */     }
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected PathPoint a(int var0, int var1, int var2) {
/*  78 */     PathPoint var3 = null;
/*     */     
/*  80 */     PathType var4 = a(this.b.world, var0, var1, var2);
/*     */     
/*  82 */     float var5 = this.b.a(var4);
/*     */ 
/*     */     
/*  85 */     var3 = super.a(var0, var1, var2);
/*  86 */     var3.l = var4;
/*  87 */     var3.k = Math.max(var3.k, var5);
/*     */     
/*  89 */     if (var5 >= 0.0F && this.a.getFluid(new BlockPosition(var0, var1, var2)).isEmpty()) {
/*  90 */       var3.k += 8.0F;
/*     */     }
/*     */ 
/*     */     
/*  94 */     if (var4 == PathType.OPEN) {
/*  95 */       return var3;
/*     */     }
/*     */     
/*  98 */     return var3;
/*     */   }
/*     */   
/*     */   private PathType c(int var0, int var1, int var2) {
/* 102 */     BlockPosition.MutableBlockPosition var3 = new BlockPosition.MutableBlockPosition();
/* 103 */     for (int i = var0; i < var0 + this.d; i++) {
/* 104 */       for (int var5 = var1; var5 < var1 + this.e; var5++) {
/* 105 */         for (int var6 = var2; var6 < var2 + this.f; var6++) {
/* 106 */           Fluid var7 = this.a.getFluid(var3.d(i, var5, var6));
/* 107 */           IBlockData var8 = this.a.getType(var3.d(i, var5, var6));
/*     */           
/* 109 */           if (var7.isEmpty() && var8.a(this.a, var3.down(), PathMode.WATER) && var8.isAir())
/* 110 */             return PathType.BREACH; 
/* 111 */           if (!var7.a(TagsFluid.WATER)) {
/* 112 */             return PathType.BLOCKED;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     IBlockData var4 = this.a.getType(var3);
/*     */ 
/*     */     
/* 121 */     if (var4.a(this.a, var3, PathMode.WATER)) {
/* 122 */       return PathType.WATER;
/*     */     }
/*     */     
/* 125 */     return PathType.BLOCKED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */