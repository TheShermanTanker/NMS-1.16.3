/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class PathfinderNormal
/*     */   extends PathfinderAbstract {
/*     */   protected float j;
/*  14 */   private final Long2ObjectMap<PathType> k = (Long2ObjectMap<PathType>)new Long2ObjectOpenHashMap();
/*  15 */   private final Object2BooleanMap<AxisAlignedBB> l = (Object2BooleanMap<AxisAlignedBB>)new Object2BooleanOpenHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(ChunkCache chunkcache, EntityInsentient entityinsentient) {
/*  21 */     super.a(chunkcache, entityinsentient);
/*  22 */     this.j = entityinsentient.a(PathType.WATER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  27 */     this.b.a(PathType.WATER, this.j);
/*  28 */     this.k.clear();
/*  29 */     this.l.clear();
/*  30 */     super.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public PathPoint b() {
/*  35 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*  36 */     int i = MathHelper.floor(this.b.locY());
/*  37 */     IBlockData iblockdata = this.a.getType(blockposition_mutableblockposition.c(this.b.locX(), i, this.b.locZ()));
/*     */ 
/*     */     
/*  40 */     if (this.b.a(iblockdata.getFluid().getType())) {
/*  41 */       while (this.b.a(iblockdata.getFluid().getType())) {
/*  42 */         i++;
/*  43 */         iblockdata = this.a.getType(blockposition_mutableblockposition.c(this.b.locX(), i, this.b.locZ()));
/*     */       } 
/*     */       
/*  46 */       i--;
/*  47 */     } else if (e() && this.b.isInWater()) {
/*  48 */       while (iblockdata.getBlock() == Blocks.WATER || iblockdata.getFluid() == FluidTypes.WATER.a(false)) {
/*  49 */         i++;
/*  50 */         iblockdata = this.a.getType(blockposition_mutableblockposition.c(this.b.locX(), i, this.b.locZ()));
/*     */       } 
/*     */       
/*  53 */       i--;
/*  54 */     } else if (this.b.isOnGround()) {
/*  55 */       i = MathHelper.floor(this.b.locY() + 0.5D);
/*     */     } else {
/*  57 */       BlockPosition blockPosition; for (blockPosition = this.b.getChunkCoordinates(); (this.a.getType(blockPosition).isAir() || this.a.getType(blockPosition).a(this.a, blockPosition, PathMode.LAND)) && blockPosition.getY() > 0; blockPosition = blockPosition.down());
/*     */ 
/*     */ 
/*     */       
/*  61 */       i = blockPosition.up().getY();
/*     */     } 
/*     */     
/*  64 */     BlockPosition blockposition = this.b.getChunkCoordinates();
/*  65 */     PathType pathtype = a(this.b, blockposition.getX(), i, blockposition.getZ());
/*     */     
/*  67 */     if (this.b.a(pathtype) < 0.0F) {
/*  68 */       AxisAlignedBB axisalignedbb = this.b.getBoundingBox();
/*     */       
/*  70 */       if (b(blockposition_mutableblockposition.c(axisalignedbb.minX, i, axisalignedbb.minZ)) || b(blockposition_mutableblockposition.c(axisalignedbb.minX, i, axisalignedbb.maxZ)) || b(blockposition_mutableblockposition.c(axisalignedbb.maxX, i, axisalignedbb.minZ)) || b(blockposition_mutableblockposition.c(axisalignedbb.maxX, i, axisalignedbb.maxZ))) {
/*  71 */         PathPoint pathpoint = a(blockposition_mutableblockposition);
/*     */         
/*  73 */         pathpoint.l = a(this.b, pathpoint.a());
/*  74 */         pathpoint.k = this.b.a(pathpoint.l);
/*  75 */         return pathpoint;
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     PathPoint pathpoint1 = a(blockposition.getX(), i, blockposition.getZ());
/*     */     
/*  81 */     pathpoint1.l = a(this.b, pathpoint1.a());
/*  82 */     pathpoint1.k = this.b.a(pathpoint1.l);
/*  83 */     return pathpoint1;
/*     */   }
/*     */   
/*     */   private boolean b(BlockPosition blockposition) {
/*  87 */     PathType pathtype = a(this.b, blockposition);
/*     */     
/*  89 */     return (this.b.a(pathtype) >= 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathDestination a(double d0, double d1, double d2) {
/*  94 */     return new PathDestination(a(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(PathPoint[] apathpoint, PathPoint pathpoint) {
/*  99 */     int i = 0;
/* 100 */     int j = 0;
/* 101 */     PathType pathtype = a(this.b, pathpoint.a, pathpoint.b + 1, pathpoint.c);
/* 102 */     PathType pathtype1 = a(this.b, pathpoint.a, pathpoint.b, pathpoint.c);
/*     */     
/* 104 */     if (this.b.a(pathtype) >= 0.0F && pathtype1 != PathType.STICKY_HONEY) {
/* 105 */       j = MathHelper.d(Math.max(1.0F, this.b.G));
/*     */     }
/*     */     
/* 108 */     double d0 = a(this.a, new BlockPosition(pathpoint.a, pathpoint.b, pathpoint.c));
/* 109 */     PathPoint pathpoint1 = a(pathpoint.a, pathpoint.b, pathpoint.c + 1, j, d0, EnumDirection.SOUTH, pathtype1);
/*     */     
/* 111 */     if (a(pathpoint1, pathpoint)) {
/* 112 */       apathpoint[i++] = pathpoint1;
/*     */     }
/*     */     
/* 115 */     PathPoint pathpoint2 = a(pathpoint.a - 1, pathpoint.b, pathpoint.c, j, d0, EnumDirection.WEST, pathtype1);
/*     */     
/* 117 */     if (a(pathpoint2, pathpoint)) {
/* 118 */       apathpoint[i++] = pathpoint2;
/*     */     }
/*     */     
/* 121 */     PathPoint pathpoint3 = a(pathpoint.a + 1, pathpoint.b, pathpoint.c, j, d0, EnumDirection.EAST, pathtype1);
/*     */     
/* 123 */     if (a(pathpoint3, pathpoint)) {
/* 124 */       apathpoint[i++] = pathpoint3;
/*     */     }
/*     */     
/* 127 */     PathPoint pathpoint4 = a(pathpoint.a, pathpoint.b, pathpoint.c - 1, j, d0, EnumDirection.NORTH, pathtype1);
/*     */     
/* 129 */     if (a(pathpoint4, pathpoint)) {
/* 130 */       apathpoint[i++] = pathpoint4;
/*     */     }
/*     */     
/* 133 */     PathPoint pathpoint5 = a(pathpoint.a - 1, pathpoint.b, pathpoint.c - 1, j, d0, EnumDirection.NORTH, pathtype1);
/*     */     
/* 135 */     if (a(pathpoint, pathpoint2, pathpoint4, pathpoint5)) {
/* 136 */       apathpoint[i++] = pathpoint5;
/*     */     }
/*     */     
/* 139 */     PathPoint pathpoint6 = a(pathpoint.a + 1, pathpoint.b, pathpoint.c - 1, j, d0, EnumDirection.NORTH, pathtype1);
/*     */     
/* 141 */     if (a(pathpoint, pathpoint3, pathpoint4, pathpoint6)) {
/* 142 */       apathpoint[i++] = pathpoint6;
/*     */     }
/*     */     
/* 145 */     PathPoint pathpoint7 = a(pathpoint.a - 1, pathpoint.b, pathpoint.c + 1, j, d0, EnumDirection.SOUTH, pathtype1);
/*     */     
/* 147 */     if (a(pathpoint, pathpoint2, pathpoint1, pathpoint7)) {
/* 148 */       apathpoint[i++] = pathpoint7;
/*     */     }
/*     */     
/* 151 */     PathPoint pathpoint8 = a(pathpoint.a + 1, pathpoint.b, pathpoint.c + 1, j, d0, EnumDirection.SOUTH, pathtype1);
/*     */     
/* 153 */     if (a(pathpoint, pathpoint3, pathpoint1, pathpoint8)) {
/* 154 */       apathpoint[i++] = pathpoint8;
/*     */     }
/*     */     
/* 157 */     return i;
/*     */   }
/*     */   
/*     */   private boolean a(PathPoint pathpoint, PathPoint pathpoint1) {
/* 161 */     return (pathpoint != null && !pathpoint.i && (pathpoint.k >= 0.0F || pathpoint1.k < 0.0F));
/*     */   }
/*     */   
/*     */   private boolean a(PathPoint pathpoint, @Nullable PathPoint pathpoint1, @Nullable PathPoint pathpoint2, @Nullable PathPoint pathpoint3) {
/* 165 */     if (pathpoint3 != null && pathpoint2 != null && pathpoint1 != null) {
/* 166 */       if (pathpoint3.i)
/* 167 */         return false; 
/* 168 */       if (pathpoint2.b <= pathpoint.b && pathpoint1.b <= pathpoint.b) {
/* 169 */         if (pathpoint1.l != PathType.WALKABLE_DOOR && pathpoint2.l != PathType.WALKABLE_DOOR && pathpoint3.l != PathType.WALKABLE_DOOR) {
/* 170 */           boolean flag = (pathpoint2.l == PathType.FENCE && pathpoint1.l == PathType.FENCE && this.b.getWidth() < 0.5D);
/*     */           
/* 172 */           return (pathpoint3.k >= 0.0F && (pathpoint2.b < pathpoint.b || pathpoint2.k >= 0.0F || flag) && (pathpoint1.b < pathpoint.b || pathpoint1.k >= 0.0F || flag));
/*     */         } 
/* 174 */         return false;
/*     */       } 
/*     */       
/* 177 */       return false;
/*     */     } 
/*     */     
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(PathPoint pathpoint) {
/* 185 */     Vec3D vec3d = new Vec3D(pathpoint.a - this.b.locX(), pathpoint.b - this.b.locY(), pathpoint.c - this.b.locZ());
/* 186 */     AxisAlignedBB axisalignedbb = this.b.getBoundingBox();
/* 187 */     int i = MathHelper.f(vec3d.f() / axisalignedbb.a());
/*     */     
/* 189 */     vec3d = vec3d.a((1.0F / i));
/*     */     
/* 191 */     for (int j = 1; j <= i; j++) {
/* 192 */       axisalignedbb = axisalignedbb.c(vec3d);
/* 193 */       if (a(axisalignedbb)) {
/* 194 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 198 */     return true;
/*     */   }
/*     */   
/*     */   public static double a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 202 */     BlockPosition blockposition1 = blockposition.down();
/* 203 */     VoxelShape voxelshape = iblockaccess.getType(blockposition1).getCollisionShape(iblockaccess, blockposition1);
/*     */     
/* 205 */     return blockposition1.getY() + (voxelshape.isEmpty() ? 0.0D : voxelshape.c(EnumDirection.EnumAxis.Y));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private PathPoint a(int i, int j, int k, int l, double d0, EnumDirection enumdirection, PathType pathtype) {
/* 210 */     PathPoint pathpoint = null;
/* 211 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 212 */     double d1 = a(this.a, blockposition_mutableblockposition.d(i, j, k));
/*     */     
/* 214 */     if (d1 - d0 > 1.125D) {
/* 215 */       return null;
/*     */     }
/* 217 */     PathType pathtype1 = a(this.b, i, j, k);
/* 218 */     float f = this.b.a(pathtype1);
/* 219 */     double d2 = this.b.getWidth() / 2.0D;
/*     */     
/* 221 */     if (f >= 0.0F) {
/* 222 */       pathpoint = a(i, j, k);
/* 223 */       pathpoint.l = pathtype1;
/* 224 */       pathpoint.k = Math.max(pathpoint.k, f);
/*     */     } 
/*     */     
/* 227 */     if (pathtype == PathType.FENCE && pathpoint != null && pathpoint.k >= 0.0F && !a(pathpoint)) {
/* 228 */       pathpoint = null;
/*     */     }
/*     */     
/* 231 */     if (pathtype1 == PathType.WALKABLE) {
/* 232 */       return pathpoint;
/*     */     }
/* 234 */     if ((pathpoint == null || pathpoint.k < 0.0F) && l > 0 && pathtype1 != PathType.FENCE && pathtype1 != PathType.UNPASSABLE_RAIL && pathtype1 != PathType.TRAPDOOR) {
/* 235 */       pathpoint = a(i, j + 1, k, l - 1, d0, enumdirection, pathtype);
/* 236 */       if (pathpoint != null && (pathpoint.l == PathType.OPEN || pathpoint.l == PathType.WALKABLE) && this.b.getWidth() < 1.0F) {
/* 237 */         double d3 = (i - enumdirection.getAdjacentX()) + 0.5D;
/* 238 */         double d4 = (k - enumdirection.getAdjacentZ()) + 0.5D;
/* 239 */         AxisAlignedBB axisalignedbb = new AxisAlignedBB(d3 - d2, a(this.a, blockposition_mutableblockposition.c(d3, (j + 1), d4)) + 0.001D, d4 - d2, d3 + d2, this.b.getHeight() + a(this.a, blockposition_mutableblockposition.c(pathpoint.a, pathpoint.b, pathpoint.c)) - 0.002D, d4 + d2);
/*     */         
/* 241 */         if (a(axisalignedbb)) {
/* 242 */           pathpoint = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     if (pathtype1 == PathType.WATER && !e()) {
/* 248 */       if (a(this.b, i, j - 1, k) != PathType.WATER) {
/* 249 */         return pathpoint;
/*     */       }
/*     */       
/* 252 */       while (j > 0) {
/* 253 */         j--;
/* 254 */         pathtype1 = a(this.b, i, j, k);
/* 255 */         if (pathtype1 != PathType.WATER) {
/* 256 */           return pathpoint;
/*     */         }
/*     */         
/* 259 */         pathpoint = a(i, j, k);
/* 260 */         pathpoint.l = pathtype1;
/* 261 */         pathpoint.k = Math.max(pathpoint.k, this.b.a(pathtype1));
/*     */       } 
/*     */     } 
/*     */     
/* 265 */     if (pathtype1 == PathType.OPEN) {
/* 266 */       int i1 = 0;
/* 267 */       int j1 = j;
/*     */       
/* 269 */       while (pathtype1 == PathType.OPEN) {
/* 270 */         j--;
/*     */ 
/*     */         
/* 273 */         if (j < 0) {
/* 274 */           PathPoint pathpoint1 = a(i, j1, k);
/* 275 */           pathpoint1.l = PathType.BLOCKED;
/* 276 */           pathpoint1.k = -1.0F;
/* 277 */           return pathpoint1;
/*     */         } 
/*     */         
/* 280 */         if (i1++ >= this.b.bO()) {
/* 281 */           PathPoint pathpoint1 = a(i, j, k);
/* 282 */           pathpoint1.l = PathType.BLOCKED;
/* 283 */           pathpoint1.k = -1.0F;
/* 284 */           return pathpoint1;
/*     */         } 
/*     */         
/* 287 */         pathtype1 = a(this.b, i, j, k);
/* 288 */         f = this.b.a(pathtype1);
/* 289 */         if (pathtype1 != PathType.OPEN && f >= 0.0F) {
/* 290 */           pathpoint = a(i, j, k);
/* 291 */           pathpoint.l = pathtype1;
/* 292 */           pathpoint.k = Math.max(pathpoint.k, f);
/*     */           
/*     */           break;
/*     */         } 
/* 296 */         if (f < 0.0F) {
/* 297 */           PathPoint pathpoint1 = a(i, j, k);
/* 298 */           pathpoint1.l = PathType.BLOCKED;
/* 299 */           pathpoint1.k = -1.0F;
/* 300 */           return pathpoint1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 305 */     if (pathtype1 == PathType.FENCE) {
/* 306 */       pathpoint = a(i, j, k);
/* 307 */       pathpoint.i = true;
/* 308 */       pathpoint.l = pathtype1;
/* 309 */       pathpoint.k = pathtype1.a();
/*     */     } 
/*     */     
/* 312 */     return pathpoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(AxisAlignedBB axisalignedbb) {
/* 318 */     return ((Boolean)this.l.computeIfAbsent(axisalignedbb, axisalignedbb1 -> Boolean.valueOf(!this.a.getCubes(this.b, axisalignedbb)))).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess iblockaccess, int i, int j, int k, EntityInsentient entityinsentient, int l, int i1, int j1, boolean flag, boolean flag1) {
/* 325 */     EnumSet<PathType> enumset = EnumSet.noneOf(PathType.class);
/* 326 */     PathType pathtype = PathType.BLOCKED;
/* 327 */     BlockPosition blockposition = entityinsentient.getChunkCoordinates();
/*     */     
/* 329 */     pathtype = a(iblockaccess, i, j, k, l, i1, j1, flag, flag1, enumset, pathtype, blockposition);
/* 330 */     if (enumset.contains(PathType.FENCE))
/* 331 */       return PathType.FENCE; 
/* 332 */     if (enumset.contains(PathType.UNPASSABLE_RAIL)) {
/* 333 */       return PathType.UNPASSABLE_RAIL;
/*     */     }
/* 335 */     PathType pathtype1 = PathType.BLOCKED;
/* 336 */     Iterator<PathType> iterator = enumset.iterator();
/*     */     
/* 338 */     while (iterator.hasNext()) {
/* 339 */       PathType pathtype2 = iterator.next();
/*     */       
/* 341 */       if (entityinsentient.a(pathtype2) < 0.0F) {
/* 342 */         return pathtype2;
/*     */       }
/*     */       
/* 345 */       if (entityinsentient.a(pathtype2) >= entityinsentient.a(pathtype1)) {
/* 346 */         pathtype1 = pathtype2;
/*     */       }
/*     */     } 
/*     */     
/* 350 */     if (pathtype == PathType.OPEN && entityinsentient.a(pathtype1) == 0.0F && l <= 1) {
/* 351 */       return PathType.OPEN;
/*     */     }
/* 353 */     return pathtype1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess iblockaccess, int i, int j, int k, int l, int i1, int j1, boolean flag, boolean flag1, EnumSet<PathType> enumset, PathType pathtype, BlockPosition blockposition) {
/* 359 */     for (int k1 = 0; k1 < l; k1++) {
/* 360 */       for (int l1 = 0; l1 < i1; l1++) {
/* 361 */         for (int i2 = 0; i2 < j1; i2++) {
/* 362 */           int j2 = k1 + i;
/* 363 */           int k2 = l1 + j;
/* 364 */           int l2 = i2 + k;
/* 365 */           PathType pathtype1 = a(iblockaccess, j2, k2, l2);
/*     */           
/* 367 */           pathtype1 = a(iblockaccess, flag, flag1, blockposition, pathtype1);
/* 368 */           if (k1 == 0 && l1 == 0 && i2 == 0) {
/* 369 */             pathtype = pathtype1;
/*     */           }
/*     */           
/* 372 */           enumset.add(pathtype1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 377 */     return pathtype;
/*     */   }
/*     */   
/*     */   protected PathType a(IBlockAccess iblockaccess, boolean flag, boolean flag1, BlockPosition blockposition, PathType pathtype) {
/* 381 */     if (pathtype == PathType.DOOR_WOOD_CLOSED && flag && flag1) {
/* 382 */       pathtype = PathType.WALKABLE_DOOR;
/*     */     }
/*     */     
/* 385 */     if (pathtype == PathType.DOOR_OPEN && !flag1) {
/* 386 */       pathtype = PathType.BLOCKED;
/*     */     }
/*     */     
/* 389 */     if (pathtype == PathType.RAIL && !(iblockaccess.getType(blockposition).getBlock() instanceof BlockMinecartTrackAbstract) && !(iblockaccess.getType(blockposition.down()).getBlock() instanceof BlockMinecartTrackAbstract)) {
/* 390 */       pathtype = PathType.UNPASSABLE_RAIL;
/*     */     }
/*     */     
/* 393 */     if (pathtype == PathType.LEAVES) {
/* 394 */       pathtype = PathType.BLOCKED;
/*     */     }
/*     */     
/* 397 */     return pathtype;
/*     */   }
/*     */   
/*     */   private PathType a(EntityInsentient entityinsentient, BlockPosition blockposition) {
/* 401 */     return a(entityinsentient, blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */   }
/*     */   
/*     */   private PathType a(EntityInsentient entityinsentient, int i, int j, int k) {
/* 405 */     return (PathType)this.k.computeIfAbsent(BlockPosition.a(i, j, k), l -> a(this.a, i, j, k, entityinsentient, this.d, this.e, this.f, d(), c()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathType a(IBlockAccess iblockaccess, int i, int j, int k) {
/* 412 */     return a(iblockaccess, new BlockPosition.MutableBlockPosition(i, j, k));
/*     */   }
/*     */   
/*     */   public static PathType a(IBlockAccess iblockaccess, BlockPosition.MutableBlockPosition blockposition_mutableblockposition) {
/* 416 */     int i = blockposition_mutableblockposition.getX();
/* 417 */     int j = blockposition_mutableblockposition.getY();
/* 418 */     int k = blockposition_mutableblockposition.getZ();
/* 419 */     PathType pathtype = b(iblockaccess, blockposition_mutableblockposition);
/*     */     
/* 421 */     if (pathtype == PathType.OPEN && j >= 1) {
/* 422 */       PathType pathtype1 = b(iblockaccess, blockposition_mutableblockposition.d(i, j - 1, k));
/*     */       
/* 424 */       pathtype = (pathtype1 != PathType.WALKABLE && pathtype1 != PathType.OPEN && pathtype1 != PathType.WATER && pathtype1 != PathType.LAVA) ? PathType.WALKABLE : PathType.OPEN;
/* 425 */       if (pathtype1 == PathType.DAMAGE_FIRE) {
/* 426 */         pathtype = PathType.DAMAGE_FIRE;
/*     */       }
/*     */       
/* 429 */       if (pathtype1 == PathType.DAMAGE_CACTUS) {
/* 430 */         pathtype = PathType.DAMAGE_CACTUS;
/*     */       }
/*     */       
/* 433 */       if (pathtype1 == PathType.DAMAGE_OTHER) {
/* 434 */         pathtype = PathType.DAMAGE_OTHER;
/*     */       }
/*     */       
/* 437 */       if (pathtype1 == PathType.STICKY_HONEY) {
/* 438 */         pathtype = PathType.STICKY_HONEY;
/*     */       }
/*     */     } 
/*     */     
/* 442 */     if (pathtype == PathType.WALKABLE) {
/* 443 */       pathtype = a(iblockaccess, blockposition_mutableblockposition.d(i, j, k), pathtype);
/*     */     }
/*     */     
/* 446 */     return pathtype;
/*     */   }
/*     */   
/*     */   public static PathType a(IBlockAccess iblockaccess, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, PathType pathtype) {
/* 450 */     int i = blockposition_mutableblockposition.getX();
/* 451 */     int j = blockposition_mutableblockposition.getY();
/* 452 */     int k = blockposition_mutableblockposition.getZ();
/*     */     
/* 454 */     for (int l = -1; l <= 1; l++) {
/* 455 */       for (int i1 = -1; i1 <= 1; i1++) {
/* 456 */         for (int j1 = -1; j1 <= 1; j1++) {
/* 457 */           if (l != 0 || j1 != 0) {
/* 458 */             blockposition_mutableblockposition.d(i + l, j + i1, k + j1);
/*     */             
/* 460 */             IBlockData iblockdata = iblockaccess.getTypeIfLoaded(blockposition_mutableblockposition);
/* 461 */             if (iblockdata == null) {
/* 462 */               pathtype = PathType.BLOCKED;
/*     */             }
/*     */             else {
/*     */               
/* 466 */               if (iblockdata.a(Blocks.CACTUS)) {
/* 467 */                 return PathType.DANGER_CACTUS;
/*     */               }
/*     */               
/* 470 */               if (iblockdata.a(Blocks.SWEET_BERRY_BUSH)) {
/* 471 */                 return PathType.DANGER_OTHER;
/*     */               }
/*     */               
/* 474 */               if (a(iblockdata)) {
/* 475 */                 return PathType.DANGER_FIRE;
/*     */               }
/*     */               
/* 478 */               if (iblockdata.getFluid().a(TagsFluid.WATER)) {
/* 479 */                 return PathType.WATER_BORDER;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 487 */     return pathtype;
/*     */   }
/*     */   
/*     */   protected static PathType b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 491 */     IBlockData iblockdata = iblockaccess.getTypeIfLoaded(blockposition);
/* 492 */     if (iblockdata == null) return PathType.BLOCKED; 
/* 493 */     Block block = iblockdata.getBlock();
/* 494 */     Material material = iblockdata.getMaterial();
/*     */     
/* 496 */     if (iblockdata.isAir())
/* 497 */       return PathType.OPEN; 
/* 498 */     if (!iblockdata.a(TagsBlock.TRAPDOORS) && !iblockdata.a(Blocks.LILY_PAD)) {
/* 499 */       if (iblockdata.a(Blocks.CACTUS))
/* 500 */         return PathType.DAMAGE_CACTUS; 
/* 501 */       if (iblockdata.a(Blocks.SWEET_BERRY_BUSH))
/* 502 */         return PathType.DAMAGE_OTHER; 
/* 503 */       if (iblockdata.a(Blocks.HONEY_BLOCK))
/* 504 */         return PathType.STICKY_HONEY; 
/* 505 */       if (iblockdata.a(Blocks.COCOA)) {
/* 506 */         return PathType.COCOA;
/*     */       }
/* 508 */       Fluid fluid = iblockdata.getFluid();
/*     */       
/* 510 */       return fluid.a(TagsFluid.WATER) ? PathType.WATER : (fluid.a(TagsFluid.LAVA) ? PathType.LAVA : (a(iblockdata) ? PathType.DAMAGE_FIRE : ((BlockDoor.l(iblockdata) && !((Boolean)iblockdata.get(BlockDoor.OPEN)).booleanValue()) ? PathType.DOOR_WOOD_CLOSED : ((block instanceof BlockDoor && material == Material.ORE && !((Boolean)iblockdata.get(BlockDoor.OPEN)).booleanValue()) ? PathType.DOOR_IRON_CLOSED : ((block instanceof BlockDoor && ((Boolean)iblockdata.get(BlockDoor.OPEN)).booleanValue()) ? PathType.DOOR_OPEN : ((block instanceof BlockMinecartTrackAbstract) ? PathType.RAIL : ((block instanceof BlockLeaves) ? PathType.LEAVES : ((!block.a(TagsBlock.FENCES) && !block.a(TagsBlock.WALLS) && (!(block instanceof BlockFenceGate) || ((Boolean)iblockdata.get(BlockFenceGate.OPEN)).booleanValue())) ? (!iblockdata.a(iblockaccess, blockposition, PathMode.LAND) ? PathType.BLOCKED : PathType.OPEN) : PathType.FENCE))))))));
/*     */     } 
/*     */     
/* 513 */     return PathType.TRAPDOOR;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(IBlockData iblockdata) {
/* 518 */     return (iblockdata.a(TagsBlock.FIRE) || iblockdata.a(Blocks.LAVA) || iblockdata.a(Blocks.MAGMA_BLOCK) || BlockCampfire.g(iblockdata));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderNormal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */