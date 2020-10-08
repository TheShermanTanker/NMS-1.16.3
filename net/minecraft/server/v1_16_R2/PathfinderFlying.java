/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class PathfinderFlying
/*     */   extends PathfinderNormal
/*     */ {
/*     */   public void a(ChunkCache var0, EntityInsentient var1) {
/*  22 */     super.a(var0, var1);
/*  23 */     this.j = var1.a(PathType.WATER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  28 */     this.b.a(PathType.WATER, this.j);
/*  29 */     super.a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint b() {
/*     */     int var0;
/*  36 */     if (e() && this.b.isInWater()) {
/*  37 */       var0 = MathHelper.floor(this.b.locY());
/*  38 */       BlockPosition.MutableBlockPosition mutableBlockPosition = new BlockPosition.MutableBlockPosition(this.b.locX(), var0, this.b.locZ());
/*  39 */       Block block = this.a.getType(mutableBlockPosition).getBlock();
/*  40 */       while (block == Blocks.WATER) {
/*  41 */         var0++;
/*  42 */         mutableBlockPosition.c(this.b.locX(), var0, this.b.locZ());
/*  43 */         block = this.a.getType(mutableBlockPosition).getBlock();
/*     */       } 
/*     */     } else {
/*  46 */       var0 = MathHelper.floor(this.b.locY() + 0.5D);
/*     */     } 
/*     */     
/*  49 */     BlockPosition var1 = this.b.getChunkCoordinates();
/*  50 */     PathType var2 = a(this.b, var1.getX(), var0, var1.getZ());
/*     */     
/*  52 */     if (this.b.a(var2) < 0.0F) {
/*  53 */       Set<BlockPosition> var3 = Sets.newHashSet();
/*  54 */       var3.add(new BlockPosition((this.b.getBoundingBox()).minX, var0, (this.b.getBoundingBox()).minZ));
/*  55 */       var3.add(new BlockPosition((this.b.getBoundingBox()).minX, var0, (this.b.getBoundingBox()).maxZ));
/*  56 */       var3.add(new BlockPosition((this.b.getBoundingBox()).maxX, var0, (this.b.getBoundingBox()).minZ));
/*  57 */       var3.add(new BlockPosition((this.b.getBoundingBox()).maxX, var0, (this.b.getBoundingBox()).maxZ));
/*     */       
/*  59 */       for (BlockPosition var5 : var3) {
/*  60 */         PathType var6 = a(this.b, var5);
/*  61 */         if (this.b.a(var6) >= 0.0F) {
/*  62 */           return super.a(var5.getX(), var5.getY(), var5.getZ());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     return super.a(var1.getX(), var0, var1.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public PathDestination a(double var0, double var2, double var4) {
/*  72 */     return new PathDestination(super.a(MathHelper.floor(var0), MathHelper.floor(var2), MathHelper.floor(var4)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(PathPoint[] var0, PathPoint var1) {
/*  77 */     int var2 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     PathPoint var3 = a(var1.a, var1.b, var1.c + 1);
/*  83 */     if (b(var3)) {
/*  84 */       var0[var2++] = var3;
/*     */     }
/*     */     
/*  87 */     PathPoint var4 = a(var1.a - 1, var1.b, var1.c);
/*  88 */     if (b(var4)) {
/*  89 */       var0[var2++] = var4;
/*     */     }
/*     */     
/*  92 */     PathPoint var5 = a(var1.a + 1, var1.b, var1.c);
/*  93 */     if (b(var5)) {
/*  94 */       var0[var2++] = var5;
/*     */     }
/*     */     
/*  97 */     PathPoint var6 = a(var1.a, var1.b, var1.c - 1);
/*  98 */     if (b(var6)) {
/*  99 */       var0[var2++] = var6;
/*     */     }
/*     */     
/* 102 */     PathPoint var7 = a(var1.a, var1.b + 1, var1.c);
/* 103 */     if (b(var7)) {
/* 104 */       var0[var2++] = var7;
/*     */     }
/*     */     
/* 107 */     PathPoint var8 = a(var1.a, var1.b - 1, var1.c);
/* 108 */     if (b(var8)) {
/* 109 */       var0[var2++] = var8;
/*     */     }
/*     */     
/* 112 */     PathPoint var9 = a(var1.a, var1.b + 1, var1.c + 1);
/* 113 */     if (b(var9) && a(var3) && a(var7)) {
/* 114 */       var0[var2++] = var9;
/*     */     }
/*     */     
/* 117 */     PathPoint var10 = a(var1.a - 1, var1.b + 1, var1.c);
/* 118 */     if (b(var10) && a(var4) && a(var7)) {
/* 119 */       var0[var2++] = var10;
/*     */     }
/*     */     
/* 122 */     PathPoint var11 = a(var1.a + 1, var1.b + 1, var1.c);
/* 123 */     if (b(var11) && a(var5) && a(var7)) {
/* 124 */       var0[var2++] = var11;
/*     */     }
/*     */     
/* 127 */     PathPoint var12 = a(var1.a, var1.b + 1, var1.c - 1);
/* 128 */     if (b(var12) && a(var6) && a(var7)) {
/* 129 */       var0[var2++] = var12;
/*     */     }
/*     */     
/* 132 */     PathPoint var13 = a(var1.a, var1.b - 1, var1.c + 1);
/* 133 */     if (b(var13) && a(var3) && a(var8)) {
/* 134 */       var0[var2++] = var13;
/*     */     }
/*     */     
/* 137 */     PathPoint var14 = a(var1.a - 1, var1.b - 1, var1.c);
/* 138 */     if (b(var14) && a(var4) && a(var8)) {
/* 139 */       var0[var2++] = var14;
/*     */     }
/*     */     
/* 142 */     PathPoint var15 = a(var1.a + 1, var1.b - 1, var1.c);
/* 143 */     if (b(var15) && a(var5) && a(var8)) {
/* 144 */       var0[var2++] = var15;
/*     */     }
/*     */     
/* 147 */     PathPoint var16 = a(var1.a, var1.b - 1, var1.c - 1);
/* 148 */     if (b(var16) && a(var6) && a(var8)) {
/* 149 */       var0[var2++] = var16;
/*     */     }
/*     */     
/* 152 */     PathPoint var17 = a(var1.a + 1, var1.b, var1.c - 1);
/* 153 */     if (b(var17) && a(var6) && a(var5)) {
/* 154 */       var0[var2++] = var17;
/*     */     }
/*     */     
/* 157 */     PathPoint var18 = a(var1.a + 1, var1.b, var1.c + 1);
/* 158 */     if (b(var18) && a(var3) && a(var5)) {
/* 159 */       var0[var2++] = var18;
/*     */     }
/*     */     
/* 162 */     PathPoint var19 = a(var1.a - 1, var1.b, var1.c - 1);
/* 163 */     if (b(var19) && a(var6) && a(var4)) {
/* 164 */       var0[var2++] = var19;
/*     */     }
/*     */     
/* 167 */     PathPoint var20 = a(var1.a - 1, var1.b, var1.c + 1);
/* 168 */     if (b(var20) && a(var3) && a(var4)) {
/* 169 */       var0[var2++] = var20;
/*     */     }
/*     */     
/* 172 */     PathPoint var21 = a(var1.a + 1, var1.b + 1, var1.c - 1);
/* 173 */     if (b(var21) && a(var17) && a(var6) && a(var5) && a(var7) && a(var12) && a(var11)) {
/* 174 */       var0[var2++] = var21;
/*     */     }
/*     */     
/* 177 */     PathPoint var22 = a(var1.a + 1, var1.b + 1, var1.c + 1);
/* 178 */     if (b(var22) && a(var18) && a(var3) && a(var5) && a(var7) && a(var9) && a(var11)) {
/* 179 */       var0[var2++] = var22;
/*     */     }
/*     */     
/* 182 */     PathPoint var23 = a(var1.a - 1, var1.b + 1, var1.c - 1);
/* 183 */     if (b(var23) && a(var19) && a(var6) && (a(var4) & a(var7)) != 0 && a(var12) && a(var10)) {
/* 184 */       var0[var2++] = var23;
/*     */     }
/*     */     
/* 187 */     PathPoint var24 = a(var1.a - 1, var1.b + 1, var1.c + 1);
/* 188 */     if (b(var24) && a(var20) && a(var3) && (a(var4) & a(var7)) != 0 && a(var9) && a(var10)) {
/* 189 */       var0[var2++] = var24;
/*     */     }
/*     */     
/* 192 */     PathPoint var25 = a(var1.a + 1, var1.b - 1, var1.c - 1);
/* 193 */     if (b(var25) && a(var17) && a(var6) && a(var5) && a(var8) && a(var16) && a(var15)) {
/* 194 */       var0[var2++] = var25;
/*     */     }
/*     */     
/* 197 */     PathPoint var26 = a(var1.a + 1, var1.b - 1, var1.c + 1);
/* 198 */     if (b(var26) && a(var18) && a(var3) && a(var5) && a(var8) && a(var13) && a(var15)) {
/* 199 */       var0[var2++] = var26;
/*     */     }
/*     */     
/* 202 */     PathPoint var27 = a(var1.a - 1, var1.b - 1, var1.c - 1);
/* 203 */     if (b(var27) && a(var19) && a(var6) && a(var4) && a(var8) && a(var16) && a(var14)) {
/* 204 */       var0[var2++] = var27;
/*     */     }
/*     */     
/* 207 */     PathPoint var28 = a(var1.a - 1, var1.b - 1, var1.c + 1);
/* 208 */     if (b(var28) && a(var20) && a(var3) && a(var4) && a(var8) && a(var13) && a(var14)) {
/* 209 */       var0[var2++] = var28;
/*     */     }
/*     */     
/* 212 */     return var2;
/*     */   }
/*     */   
/*     */   private boolean a(@Nullable PathPoint var0) {
/* 216 */     return (var0 != null && var0.k >= 0.0F);
/*     */   }
/*     */   
/*     */   private boolean b(@Nullable PathPoint var0) {
/* 220 */     return (var0 != null && !var0.i);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected PathPoint a(int var0, int var1, int var2) {
/* 226 */     PathPoint var3 = null;
/*     */     
/* 228 */     PathType var4 = a(this.b, var0, var1, var2);
/*     */     
/* 230 */     float var5 = this.b.a(var4);
/*     */     
/* 232 */     if (var5 >= 0.0F) {
/* 233 */       var3 = super.a(var0, var1, var2);
/* 234 */       var3.l = var4;
/* 235 */       var3.k = Math.max(var3.k, var5);
/*     */       
/* 237 */       if (var4 == PathType.WALKABLE) {
/* 238 */         var3.k++;
/*     */       }
/*     */     } 
/*     */     
/* 242 */     if (var4 == PathType.OPEN || var4 == PathType.WALKABLE) {
/* 243 */       return var3;
/*     */     }
/*     */     
/* 246 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess var0, int var1, int var2, int var3, EntityInsentient var4, int var5, int var6, int var7, boolean var8, boolean var9) {
/* 252 */     EnumSet<PathType> var10 = EnumSet.noneOf(PathType.class);
/* 253 */     PathType var11 = PathType.BLOCKED;
/*     */     
/* 255 */     BlockPosition var12 = var4.getChunkCoordinates();
/*     */     
/* 257 */     var11 = a(var0, var1, var2, var3, var5, var6, var7, var8, var9, var10, var11, var12);
/*     */     
/* 259 */     if (var10.contains(PathType.FENCE)) {
/* 260 */       return PathType.FENCE;
/*     */     }
/*     */     
/* 263 */     PathType var13 = PathType.BLOCKED;
/* 264 */     for (PathType var15 : var10) {
/*     */       
/* 266 */       if (var4.a(var15) < 0.0F) {
/* 267 */         return var15;
/*     */       }
/*     */ 
/*     */       
/* 271 */       if (var4.a(var15) >= var4.a(var13)) {
/* 272 */         var13 = var15;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 277 */     if (var11 == PathType.OPEN && var4.a(var13) == 0.0F) {
/* 278 */       return PathType.OPEN;
/*     */     }
/*     */     
/* 281 */     return var13;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess var0, int var1, int var2, int var3) {
/* 286 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition();
/* 287 */     PathType var5 = b(var0, var4.d(var1, var2, var3));
/*     */     
/* 289 */     if (var5 == PathType.OPEN && var2 >= 1) {
/* 290 */       IBlockData var6 = var0.getType(var4.d(var1, var2 - 1, var3));
/* 291 */       PathType var7 = b(var0, var4.d(var1, var2 - 1, var3));
/*     */       
/* 293 */       if (var7 == PathType.DAMAGE_FIRE || var6.a(Blocks.MAGMA_BLOCK) || var7 == PathType.LAVA || var6.a(TagsBlock.CAMPFIRES)) {
/* 294 */         var5 = PathType.DAMAGE_FIRE;
/* 295 */       } else if (var7 == PathType.DAMAGE_CACTUS) {
/* 296 */         var5 = PathType.DAMAGE_CACTUS;
/* 297 */       } else if (var7 == PathType.DAMAGE_OTHER) {
/* 298 */         var5 = PathType.DAMAGE_OTHER;
/* 299 */       } else if (var7 == PathType.COCOA) {
/* 300 */         var5 = PathType.COCOA;
/* 301 */       } else if (var7 == PathType.FENCE) {
/* 302 */         var5 = PathType.FENCE;
/*     */       } else {
/* 304 */         var5 = (var7 == PathType.WALKABLE || var7 == PathType.OPEN || var7 == PathType.WATER) ? PathType.OPEN : PathType.WALKABLE;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 309 */     if (var5 == PathType.WALKABLE || var5 == PathType.OPEN) {
/* 310 */       var5 = a(var0, var4.d(var1, var2, var3), var5);
/*     */     }
/*     */     
/* 313 */     return var5;
/*     */   }
/*     */   
/*     */   private PathType a(EntityInsentient var0, BlockPosition var1) {
/* 317 */     return a(var0, var1.getX(), var1.getY(), var1.getZ());
/*     */   }
/*     */   
/*     */   private PathType a(EntityInsentient var0, int var1, int var2, int var3) {
/* 321 */     return a(this.a, var1, var2, var3, var0, this.d, this.e, this.f, d(), c());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */