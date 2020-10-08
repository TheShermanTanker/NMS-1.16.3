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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderTurtle
/*     */   extends PathfinderNormal
/*     */ {
/*     */   private float k;
/*     */   private float l;
/*     */   
/*     */   public void a(ChunkCache var0, EntityInsentient var1) {
/*  24 */     super.a(var0, var1);
/*  25 */     var1.a(PathType.WATER, 0.0F);
/*  26 */     this.k = var1.a(PathType.WALKABLE);
/*  27 */     var1.a(PathType.WALKABLE, 6.0F);
/*  28 */     this.l = var1.a(PathType.WATER_BORDER);
/*  29 */     var1.a(PathType.WATER_BORDER, 4.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  34 */     this.b.a(PathType.WALKABLE, this.k);
/*  35 */     this.b.a(PathType.WATER_BORDER, this.l);
/*  36 */     super.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public PathPoint b() {
/*  41 */     return a(MathHelper.floor((this.b.getBoundingBox()).minX), MathHelper.floor((this.b.getBoundingBox()).minY + 0.5D), MathHelper.floor((this.b.getBoundingBox()).minZ));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathDestination a(double var0, double var2, double var4) {
/*  47 */     return new PathDestination(a(MathHelper.floor(var0), MathHelper.floor(var2 + 0.5D), MathHelper.floor(var4)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(PathPoint[] var0, PathPoint var1) {
/*  53 */     int var2 = 0;
/*  54 */     int var3 = 1;
/*     */     
/*  56 */     BlockPosition var4 = new BlockPosition(var1.a, var1.b, var1.c);
/*  57 */     double var5 = b(var4);
/*     */     
/*  59 */     PathPoint var7 = a(var1.a, var1.b, var1.c + 1, 1, var5);
/*  60 */     PathPoint var8 = a(var1.a - 1, var1.b, var1.c, 1, var5);
/*  61 */     PathPoint var9 = a(var1.a + 1, var1.b, var1.c, 1, var5);
/*  62 */     PathPoint var10 = a(var1.a, var1.b, var1.c - 1, 1, var5);
/*  63 */     PathPoint var11 = a(var1.a, var1.b + 1, var1.c, 0, var5);
/*  64 */     PathPoint var12 = a(var1.a, var1.b - 1, var1.c, 1, var5);
/*     */     
/*  66 */     if (var7 != null && !var7.i) {
/*  67 */       var0[var2++] = var7;
/*     */     }
/*  69 */     if (var8 != null && !var8.i) {
/*  70 */       var0[var2++] = var8;
/*     */     }
/*  72 */     if (var9 != null && !var9.i) {
/*  73 */       var0[var2++] = var9;
/*     */     }
/*  75 */     if (var10 != null && !var10.i) {
/*  76 */       var0[var2++] = var10;
/*     */     }
/*  78 */     if (var11 != null && !var11.i) {
/*  79 */       var0[var2++] = var11;
/*     */     }
/*  81 */     if (var12 != null && !var12.i) {
/*  82 */       var0[var2++] = var12;
/*     */     }
/*     */     
/*  85 */     boolean var13 = (var10 == null || var10.l == PathType.OPEN || var10.k != 0.0F);
/*  86 */     boolean var14 = (var7 == null || var7.l == PathType.OPEN || var7.k != 0.0F);
/*  87 */     boolean var15 = (var9 == null || var9.l == PathType.OPEN || var9.k != 0.0F);
/*  88 */     boolean var16 = (var8 == null || var8.l == PathType.OPEN || var8.k != 0.0F);
/*     */     
/*  90 */     if (var13 && var16) {
/*  91 */       PathPoint var17 = a(var1.a - 1, var1.b, var1.c - 1, 1, var5);
/*  92 */       if (var17 != null && !var17.i) {
/*  93 */         var0[var2++] = var17;
/*     */       }
/*     */     } 
/*  96 */     if (var13 && var15) {
/*  97 */       PathPoint var17 = a(var1.a + 1, var1.b, var1.c - 1, 1, var5);
/*  98 */       if (var17 != null && !var17.i) {
/*  99 */         var0[var2++] = var17;
/*     */       }
/*     */     } 
/* 102 */     if (var14 && var16) {
/* 103 */       PathPoint var17 = a(var1.a - 1, var1.b, var1.c + 1, 1, var5);
/* 104 */       if (var17 != null && !var17.i) {
/* 105 */         var0[var2++] = var17;
/*     */       }
/*     */     } 
/* 108 */     if (var14 && var15) {
/* 109 */       PathPoint var17 = a(var1.a + 1, var1.b, var1.c + 1, 1, var5);
/* 110 */       if (var17 != null && !var17.i) {
/* 111 */         var0[var2++] = var17;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     return var2;
/*     */   }
/*     */   
/*     */   private double b(BlockPosition var0) {
/* 119 */     if (!this.b.isInWater()) {
/* 120 */       BlockPosition var1 = var0.down();
/* 121 */       VoxelShape var2 = this.a.getType(var1).getCollisionShape(this.a, var1);
/* 122 */       return var1.getY() + (var2.isEmpty() ? 0.0D : var2.c(EnumDirection.EnumAxis.Y));
/*     */     } 
/* 124 */     return var0.getY() + 0.5D;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private PathPoint a(int var0, int var1, int var2, int var3, double var4) {
/* 129 */     PathPoint var6 = null;
/*     */     
/* 131 */     BlockPosition var7 = new BlockPosition(var0, var1, var2);
/* 132 */     double var8 = b(var7);
/*     */ 
/*     */     
/* 135 */     if (var8 - var4 > 1.125D) {
/* 136 */       return null;
/*     */     }
/*     */     
/* 139 */     PathType var10 = a(this.a, var0, var1, var2, this.b, this.d, this.e, this.f, false, false);
/*     */     
/* 141 */     float var11 = this.b.a(var10);
/* 142 */     double var12 = this.b.getWidth() / 2.0D;
/*     */     
/* 144 */     if (var11 >= 0.0F) {
/* 145 */       var6 = a(var0, var1, var2);
/* 146 */       var6.l = var10;
/* 147 */       var6.k = Math.max(var6.k, var11);
/*     */     } 
/*     */     
/* 150 */     if (var10 == PathType.WATER || var10 == PathType.WALKABLE) {
/*     */       
/* 152 */       if (var1 < this.b.world.getSeaLevel() - 10 && var6 != null) {
/* 153 */         var6.k++;
/*     */       }
/*     */       
/* 156 */       return var6;
/*     */     } 
/*     */     
/* 159 */     if (var6 == null && var3 > 0 && var10 != PathType.FENCE && var10 != PathType.UNPASSABLE_RAIL && var10 != PathType.TRAPDOOR) {
/* 160 */       var6 = a(var0, var1 + 1, var2, var3 - 1, var4);
/*     */     }
/*     */     
/* 163 */     if (var10 == PathType.OPEN) {
/*     */       
/* 165 */       AxisAlignedBB var14 = new AxisAlignedBB(var0 - var12 + 0.5D, var1 + 0.001D, var2 - var12 + 0.5D, var0 + var12 + 0.5D, (var1 + this.b.getHeight()), var2 + var12 + 0.5D);
/* 166 */       if (!this.b.world.getCubes(this.b, var14)) {
/* 167 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 171 */       PathType var15 = a(this.a, var0, var1 - 1, var2, this.b, this.d, this.e, this.f, false, false);
/* 172 */       if (var15 == PathType.BLOCKED) {
/* 173 */         var6 = a(var0, var1, var2);
/* 174 */         var6.l = PathType.WALKABLE;
/* 175 */         var6.k = Math.max(var6.k, var11);
/* 176 */         return var6;
/*     */       } 
/* 178 */       if (var15 == PathType.WATER) {
/* 179 */         var6 = a(var0, var1, var2);
/* 180 */         var6.l = PathType.WATER;
/* 181 */         var6.k = Math.max(var6.k, var11);
/* 182 */         return var6;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 187 */       int var16 = 0;
/* 188 */       while (var1 > 0 && var10 == PathType.OPEN) {
/* 189 */         var1--;
/*     */         
/* 191 */         if (var16++ >= this.b.bO()) {
/* 192 */           return null;
/*     */         }
/*     */         
/* 195 */         var10 = a(this.a, var0, var1, var2, this.b, this.d, this.e, this.f, false, false);
/* 196 */         var11 = this.b.a(var10);
/*     */         
/* 198 */         if (var10 != PathType.OPEN && var11 >= 0.0F) {
/* 199 */           var6 = a(var0, var1, var2);
/* 200 */           var6.l = var10;
/* 201 */           var6.k = Math.max(var6.k, var11); break;
/*     */         } 
/* 203 */         if (var11 < 0.0F) {
/* 204 */           return null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 209 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathType a(IBlockAccess var0, boolean var1, boolean var2, BlockPosition var3, PathType var4) {
/* 214 */     if (var4 == PathType.RAIL && !(var0.getType(var3).getBlock() instanceof BlockMinecartTrackAbstract) && !(var0.getType(var3.down()).getBlock() instanceof BlockMinecartTrackAbstract)) {
/* 215 */       var4 = PathType.UNPASSABLE_RAIL;
/*     */     }
/* 217 */     if (var4 == PathType.DOOR_OPEN || var4 == PathType.DOOR_WOOD_CLOSED || var4 == PathType.DOOR_IRON_CLOSED) {
/* 218 */       var4 = PathType.BLOCKED;
/*     */     }
/* 220 */     if (var4 == PathType.LEAVES) {
/* 221 */       var4 = PathType.BLOCKED;
/*     */     }
/* 223 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess var0, int var1, int var2, int var3) {
/* 228 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition();
/* 229 */     PathType var5 = b(var0, var4.d(var1, var2, var3));
/*     */     
/* 231 */     if (var5 == PathType.WATER) {
/* 232 */       for (EnumDirection var9 : EnumDirection.values()) {
/* 233 */         PathType var10 = b(var0, var4.d(var1, var2, var3).c(var9));
/* 234 */         if (var10 == PathType.BLOCKED) {
/* 235 */           return PathType.WATER_BORDER;
/*     */         }
/*     */       } 
/*     */       
/* 239 */       return PathType.WATER;
/*     */     } 
/*     */     
/* 242 */     if (var5 == PathType.OPEN && var2 >= 1) {
/* 243 */       IBlockData var6 = var0.getType(new BlockPosition(var1, var2 - 1, var3));
/* 244 */       PathType var7 = b(var0, var4.d(var1, var2 - 1, var3));
/* 245 */       if (var7 == PathType.WALKABLE || var7 == PathType.OPEN || var7 == PathType.LAVA) {
/* 246 */         var5 = PathType.OPEN;
/*     */       } else {
/* 248 */         var5 = PathType.WALKABLE;
/*     */       } 
/*     */       
/* 251 */       if (var7 == PathType.DAMAGE_FIRE || var6.a(Blocks.MAGMA_BLOCK) || var6.a(TagsBlock.CAMPFIRES)) {
/* 252 */         var5 = PathType.DAMAGE_FIRE;
/*     */       }
/*     */       
/* 255 */       if (var7 == PathType.DAMAGE_CACTUS) {
/* 256 */         var5 = PathType.DAMAGE_CACTUS;
/*     */       }
/*     */       
/* 259 */       if (var7 == PathType.DAMAGE_OTHER) {
/* 260 */         var5 = PathType.DAMAGE_OTHER;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 265 */     if (var5 == PathType.WALKABLE) {
/* 266 */       var5 = a(var0, var4.d(var1, var2, var3), var5);
/*     */     }
/*     */     
/* 269 */     return var5;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */