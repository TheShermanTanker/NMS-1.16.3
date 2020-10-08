/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class AxisAlignedBB
/*     */ {
/*     */   public final double minX;
/*     */   public final double minY;
/*     */   public final double minZ;
/*     */   public final double maxX;
/*     */   public final double maxY;
/*     */   public final double maxZ;
/*     */   
/*     */   public final boolean isEmpty() {
/*  18 */     return (this.maxX - this.minX < 1.0E-7D && this.maxY - this.minY < 1.0E-7D && this.maxZ - this.minZ < 1.0E-7D);
/*     */   }
/*     */   
/*     */   public static AxisAlignedBB getBoxForChunk(int chunkX, int chunkZ) {
/*  22 */     double x = (chunkX << 4);
/*  23 */     double z = (chunkZ << 4);
/*     */     
/*  25 */     return new AxisAlignedBB(x - 3.0E-7D, Double.NEGATIVE_INFINITY, z - 3.0E-7D, x + 16.0000003D, Double.POSITIVE_INFINITY, z + 16.0000003D, false);
/*     */   }
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
/*     */   public final boolean voxelShapeIntersect(AxisAlignedBB other) {
/*  38 */     return voxelShapeIntersect(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
/*     */   }
/*     */   
/*     */   public final boolean voxelShapeIntersect(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/*  42 */     return (this.minX - maxX < -1.0E-7D && this.maxX - minX > 1.0E-7D && this.minY - maxY < -1.0E-7D && this.maxY - minY > 1.0E-7D && this.minZ - maxZ < -1.0E-7D && this.maxZ - minZ > 1.0E-7D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double collideX(AxisAlignedBB target, AxisAlignedBB source, double source_move) {
/*  48 */     if (target.isEmpty() || source.isEmpty()) {
/*  49 */       return source_move;
/*     */     }
/*  51 */     if (Math.abs(source_move) < 1.0E-7D) {
/*  52 */       return 0.0D;
/*     */     }
/*     */     
/*  55 */     if (source.minY - target.maxY < -1.0E-7D && source.maxY - target.minY > 1.0E-7D && source.minZ - target.maxZ < -1.0E-7D && source.maxZ - target.minZ > 1.0E-7D) {
/*     */ 
/*     */       
/*  58 */       if (source_move >= 0.0D) {
/*  59 */         double d = target.minX - source.maxX;
/*  60 */         if (d < -1.0E-7D) {
/*  61 */           return source_move;
/*     */         }
/*  63 */         return Math.min(d, source_move);
/*     */       } 
/*  65 */       double max_move = target.maxX - source.minX;
/*  66 */       if (max_move > 1.0E-7D) {
/*  67 */         return source_move;
/*     */       }
/*  69 */       return Math.max(max_move, source_move);
/*     */     } 
/*     */     
/*  72 */     return source_move;
/*     */   }
/*     */   
/*     */   public static double collideY(AxisAlignedBB target, AxisAlignedBB source, double source_move) {
/*  76 */     if (target.isEmpty() || source.isEmpty()) {
/*  77 */       return source_move;
/*     */     }
/*  79 */     if (Math.abs(source_move) < 1.0E-7D) {
/*  80 */       return 0.0D;
/*     */     }
/*     */     
/*  83 */     if (source.minX - target.maxX < -1.0E-7D && source.maxX - target.minX > 1.0E-7D && source.minZ - target.maxZ < -1.0E-7D && source.maxZ - target.minZ > 1.0E-7D) {
/*     */       
/*  85 */       if (source_move >= 0.0D) {
/*  86 */         double d = target.minY - source.maxY;
/*  87 */         if (d < -1.0E-7D) {
/*  88 */           return source_move;
/*     */         }
/*  90 */         return Math.min(d, source_move);
/*     */       } 
/*  92 */       double max_move = target.maxY - source.minY;
/*  93 */       if (max_move > 1.0E-7D) {
/*  94 */         return source_move;
/*     */       }
/*  96 */       return Math.max(max_move, source_move);
/*     */     } 
/*     */     
/*  99 */     return source_move;
/*     */   }
/*     */   
/*     */   public static double collideZ(AxisAlignedBB target, AxisAlignedBB source, double source_move) {
/* 103 */     if (target.isEmpty() || source.isEmpty()) {
/* 104 */       return source_move;
/*     */     }
/* 106 */     if (Math.abs(source_move) < 1.0E-7D) {
/* 107 */       return 0.0D;
/*     */     }
/*     */     
/* 110 */     if (source.minX - target.maxX < -1.0E-7D && source.maxX - target.minX > 1.0E-7D && source.minY - target.maxY < -1.0E-7D && source.maxY - target.minY > 1.0E-7D) {
/*     */       
/* 112 */       if (source_move >= 0.0D) {
/* 113 */         double d = target.minZ - source.maxZ;
/* 114 */         if (d < -1.0E-7D) {
/* 115 */           return source_move;
/*     */         }
/* 117 */         return Math.min(d, source_move);
/*     */       } 
/* 119 */       double max_move = target.maxZ - source.minZ;
/* 120 */       if (max_move > 1.0E-7D) {
/* 121 */         return source_move;
/*     */       }
/* 123 */       return Math.max(max_move, source_move);
/*     */     } 
/*     */     
/* 126 */     return source_move;
/*     */   }
/*     */   
/*     */   public final AxisAlignedBB offsetX(double dx) {
/* 130 */     return new AxisAlignedBB(this.minX + dx, this.minY, this.minZ, this.maxX + dx, this.maxY, this.maxZ, false);
/*     */   }
/*     */   
/*     */   public final AxisAlignedBB offsetY(double dy) {
/* 134 */     return new AxisAlignedBB(this.minX, this.minY + dy, this.minZ, this.maxX, this.maxY + dy, this.maxZ, false);
/*     */   }
/*     */   
/*     */   public final AxisAlignedBB offsetZ(double dz) {
/* 138 */     return new AxisAlignedBB(this.minX, this.minY, this.minZ + dz, this.maxX, this.maxY, this.maxZ + dz, false);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB(double d0, double d1, double d2, double d3, double d4, double d5, boolean dummy) {
/* 142 */     this.minX = d0;
/* 143 */     this.minY = d1;
/* 144 */     this.minZ = d2;
/* 145 */     this.maxX = d3;
/* 146 */     this.maxY = d4;
/* 147 */     this.maxZ = d5;
/*     */   }
/*     */   
/*     */   public final AxisAlignedBB expandUpwards(double dy) {
/* 151 */     return new AxisAlignedBB(this.minX, this.minY, this.minZ, this.maxX, this.maxY + dy, this.maxZ, false);
/*     */   }
/*     */   
/*     */   public final AxisAlignedBB expandUpwardsAndCutBelow(double dy) {
/* 155 */     return new AxisAlignedBB(this.minX, this.maxY, this.minZ, this.maxX, this.maxY + dy, this.maxZ, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB(double d0, double d1, double d2, double d3, double d4, double d5) {
/* 160 */     this.minX = Math.min(d0, d3);
/* 161 */     this.minY = Math.min(d1, d4);
/* 162 */     this.minZ = Math.min(d2, d5);
/* 163 */     this.maxX = Math.max(d0, d3);
/* 164 */     this.maxY = Math.max(d1, d4);
/* 165 */     this.maxZ = Math.max(d2, d5);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB(BlockPosition blockposition) {
/* 169 */     this(blockposition.getX(), blockposition.getY(), blockposition.getZ(), (blockposition.getX() + 1), (blockposition.getY() + 1), (blockposition.getZ() + 1));
/*     */   }
/*     */   
/*     */   public AxisAlignedBB(BlockPosition blockposition, BlockPosition blockposition1) {
/* 173 */     this(blockposition.getX(), blockposition.getY(), blockposition.getZ(), blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/*     */   }
/*     */   
/*     */   public AxisAlignedBB(Vec3D vec3d, Vec3D vec3d1) {
/* 177 */     this(vec3d.x, vec3d.y, vec3d.z, vec3d1.x, vec3d1.y, vec3d1.z);
/*     */   }
/*     */   
/*     */   public static AxisAlignedBB a(StructureBoundingBox structureboundingbox) {
/* 181 */     return new AxisAlignedBB(structureboundingbox.a, structureboundingbox.b, structureboundingbox.c, (structureboundingbox.d + 1), (structureboundingbox.e + 1), (structureboundingbox.f + 1));
/*     */   }
/*     */   
/*     */   public static AxisAlignedBB a(Vec3D vec3d) {
/* 185 */     return new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d.x + 1.0D, vec3d.y + 1.0D, vec3d.z + 1.0D);
/*     */   }
/*     */   
/*     */   public double a(EnumDirection.EnumAxis enumdirection_enumaxis) {
/* 189 */     return enumdirection_enumaxis.a(this.minX, this.minY, this.minZ);
/*     */   }
/*     */   
/*     */   public double b(EnumDirection.EnumAxis enumdirection_enumaxis) {
/* 193 */     return enumdirection_enumaxis.a(this.maxX, this.maxY, this.maxZ);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 197 */     if (this == object)
/* 198 */       return true; 
/* 199 */     if (!(object instanceof AxisAlignedBB)) {
/* 200 */       return false;
/*     */     }
/* 202 */     AxisAlignedBB axisalignedbb = (AxisAlignedBB)object;
/*     */     
/* 204 */     return (Double.compare(axisalignedbb.minX, this.minX) != 0) ? false : ((Double.compare(axisalignedbb.minY, this.minY) != 0) ? false : ((Double.compare(axisalignedbb.minZ, this.minZ) != 0) ? false : ((Double.compare(axisalignedbb.maxX, this.maxX) != 0) ? false : ((Double.compare(axisalignedbb.maxY, this.maxY) != 0) ? false : ((Double.compare(axisalignedbb.maxZ, this.maxZ) == 0))))));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 209 */     long i = Double.doubleToLongBits(this.minX);
/* 210 */     int j = (int)(i ^ i >>> 32L);
/*     */     
/* 212 */     i = Double.doubleToLongBits(this.minY);
/* 213 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 214 */     i = Double.doubleToLongBits(this.minZ);
/* 215 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 216 */     i = Double.doubleToLongBits(this.maxX);
/* 217 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 218 */     i = Double.doubleToLongBits(this.maxY);
/* 219 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 220 */     i = Double.doubleToLongBits(this.maxZ);
/* 221 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 222 */     return j;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(double d0, double d1, double d2) {
/* 226 */     double d3 = this.minX;
/* 227 */     double d4 = this.minY;
/* 228 */     double d5 = this.minZ;
/* 229 */     double d6 = this.maxX;
/* 230 */     double d7 = this.maxY;
/* 231 */     double d8 = this.maxZ;
/*     */     
/* 233 */     if (d0 < 0.0D) {
/* 234 */       d3 -= d0;
/* 235 */     } else if (d0 > 0.0D) {
/* 236 */       d6 -= d0;
/*     */     } 
/*     */     
/* 239 */     if (d1 < 0.0D) {
/* 240 */       d4 -= d1;
/* 241 */     } else if (d1 > 0.0D) {
/* 242 */       d7 -= d1;
/*     */     } 
/*     */     
/* 245 */     if (d2 < 0.0D) {
/* 246 */       d5 -= d2;
/* 247 */     } else if (d2 > 0.0D) {
/* 248 */       d8 -= d2;
/*     */     } 
/*     */     
/* 251 */     return new AxisAlignedBB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(Vec3D vec3d) {
/* 255 */     return b(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   public final AxisAlignedBB expand(double x, double y, double z) {
/* 258 */     return b(x, y, z);
/*     */   } public AxisAlignedBB b(double d0, double d1, double d2) {
/* 260 */     double d3 = this.minX;
/* 261 */     double d4 = this.minY;
/* 262 */     double d5 = this.minZ;
/* 263 */     double d6 = this.maxX;
/* 264 */     double d7 = this.maxY;
/* 265 */     double d8 = this.maxZ;
/*     */     
/* 267 */     if (d0 < 0.0D) {
/* 268 */       d3 += d0;
/* 269 */     } else if (d0 > 0.0D) {
/* 270 */       d6 += d0;
/*     */     } 
/*     */     
/* 273 */     if (d1 < 0.0D) {
/* 274 */       d4 += d1;
/* 275 */     } else if (d1 > 0.0D) {
/* 276 */       d7 += d1;
/*     */     } 
/*     */     
/* 279 */     if (d2 < 0.0D) {
/* 280 */       d5 += d2;
/* 281 */     } else if (d2 > 0.0D) {
/* 282 */       d8 += d2;
/*     */     } 
/*     */     
/* 285 */     return new AxisAlignedBB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB grow(double d0) {
/* 290 */     return grow(d0, d0, d0);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB grow(double d0, double d1, double d2) {
/* 295 */     double d3 = this.minX - d0;
/* 296 */     double d4 = this.minY - d1;
/* 297 */     double d5 = this.minZ - d2;
/* 298 */     double d6 = this.maxX + d0;
/* 299 */     double d7 = this.maxY + d1;
/* 300 */     double d8 = this.maxZ + d2;
/*     */     
/* 302 */     return new AxisAlignedBB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB g(double d0) {
/* 306 */     return grow(d0, d0, d0);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(AxisAlignedBB axisalignedbb) {
/* 310 */     double d0 = Math.max(this.minX, axisalignedbb.minX);
/* 311 */     double d1 = Math.max(this.minY, axisalignedbb.minY);
/* 312 */     double d2 = Math.max(this.minZ, axisalignedbb.minZ);
/* 313 */     double d3 = Math.min(this.maxX, axisalignedbb.maxX);
/* 314 */     double d4 = Math.min(this.maxY, axisalignedbb.maxY);
/* 315 */     double d5 = Math.min(this.maxZ, axisalignedbb.maxZ);
/*     */     
/* 317 */     return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(AxisAlignedBB axisalignedbb) {
/* 321 */     double d0 = Math.min(this.minX, axisalignedbb.minX);
/* 322 */     double d1 = Math.min(this.minY, axisalignedbb.minY);
/* 323 */     double d2 = Math.min(this.minZ, axisalignedbb.minZ);
/* 324 */     double d3 = Math.max(this.maxX, axisalignedbb.maxX);
/* 325 */     double d4 = Math.max(this.maxY, axisalignedbb.maxY);
/* 326 */     double d5 = Math.max(this.maxZ, axisalignedbb.maxZ);
/*     */     
/* 328 */     return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
/*     */   }
/*     */   public final AxisAlignedBB offset(double d0, double d1, double d2) {
/* 331 */     return d(d0, d1, d2);
/*     */   } public AxisAlignedBB d(double d0, double d1, double d2) {
/* 333 */     return new AxisAlignedBB(this.minX + d0, this.minY + d1, this.minZ + d2, this.maxX + d0, this.maxY + d1, this.maxZ + d2);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(BlockPosition blockposition) {
/* 337 */     return new AxisAlignedBB(this.minX + blockposition.getX(), this.minY + blockposition.getY(), this.minZ + blockposition.getZ(), this.maxX + blockposition.getX(), this.maxY + blockposition.getY(), this.maxZ + blockposition.getZ());
/*     */   }
/*     */   public final AxisAlignedBB offset(Vec3D vec3d) {
/* 340 */     return b(vec3d);
/*     */   } public AxisAlignedBB c(Vec3D vec3d) {
/* 342 */     return d(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   public final boolean intersects(AxisAlignedBB axisalignedbb) {
/* 345 */     return c(axisalignedbb);
/*     */   } public boolean c(AxisAlignedBB axisalignedbb) {
/* 347 */     return a(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
/*     */   }
/*     */   public final boolean intersects(double d0, double d1, double d2, double d3, double d4, double d5) {
/* 350 */     return a(d0, d1, d2, d3, d4, d5);
/*     */   } public boolean a(double d0, double d1, double d2, double d3, double d4, double d5) {
/* 352 */     return (this.minX < d3 && this.maxX > d0 && this.minY < d4 && this.maxY > d1 && this.minZ < d5 && this.maxZ > d2);
/*     */   }
/*     */   public final boolean contains(Vec3D vec3d) {
/* 355 */     return d(vec3d);
/*     */   } public boolean d(Vec3D vec3d) {
/* 357 */     return e(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   public final boolean contains(double d0, double d1, double d2) {
/* 360 */     return e(d0, d1, d2);
/*     */   } public boolean e(double d0, double d1, double d2) {
/* 362 */     return (d0 >= this.minX && d0 < this.maxX && d1 >= this.minY && d1 < this.maxY && d2 >= this.minZ && d2 < this.maxZ);
/*     */   }
/*     */   public final double getAverageSideLength() {
/* 365 */     return a();
/*     */   } public double a() {
/* 367 */     double d0 = b();
/* 368 */     double d1 = c();
/* 369 */     double d2 = d();
/*     */     
/* 371 */     return (d0 + d1 + d2) / 3.0D;
/*     */   }
/*     */   
/*     */   public double b() {
/* 375 */     return this.maxX - this.minX;
/*     */   }
/*     */   
/*     */   public double c() {
/* 379 */     return this.maxY - this.minY;
/*     */   }
/*     */   
/*     */   public double d() {
/* 383 */     return this.maxZ - this.minZ;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB shrink(double d0) {
/* 387 */     return g(-d0);
/*     */   }
/*     */   public final Optional<Vec3D> calculateIntercept(Vec3D vec3d, Vec3D vec3d1) {
/* 390 */     return b(vec3d, vec3d1);
/*     */   } public Optional<Vec3D> b(Vec3D vec3d, Vec3D vec3d1) {
/* 392 */     double[] adouble = { 1.0D };
/* 393 */     double d0 = vec3d1.x - vec3d.x;
/* 394 */     double d1 = vec3d1.y - vec3d.y;
/* 395 */     double d2 = vec3d1.z - vec3d.z;
/* 396 */     EnumDirection enumdirection = a(this, vec3d, adouble, (EnumDirection)null, d0, d1, d2);
/*     */     
/* 398 */     if (enumdirection == null) {
/* 399 */       return Optional.empty();
/*     */     }
/* 401 */     double d3 = adouble[0];
/*     */     
/* 403 */     return Optional.of(vec3d.add(d3 * d0, d3 * d1, d3 * d2));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static MovingObjectPositionBlock a(Iterable<AxisAlignedBB> iterable, Vec3D vec3d, Vec3D vec3d1, BlockPosition blockposition) {
/* 409 */     double[] adouble = { 1.0D };
/* 410 */     EnumDirection enumdirection = null;
/* 411 */     double d0 = vec3d1.x - vec3d.x;
/* 412 */     double d1 = vec3d1.y - vec3d.y;
/* 413 */     double d2 = vec3d1.z - vec3d.z;
/*     */ 
/*     */ 
/*     */     
/* 417 */     for (Iterator<AxisAlignedBB> iterator = iterable.iterator(); iterator.hasNext(); enumdirection = a(axisalignedbb.a(blockposition), vec3d, adouble, enumdirection, d0, d1, d2)) {
/* 418 */       AxisAlignedBB axisalignedbb = iterator.next();
/*     */     }
/*     */     
/* 421 */     if (enumdirection == null) {
/* 422 */       return null;
/*     */     }
/* 424 */     double d3 = adouble[0];
/*     */     
/* 426 */     return new MovingObjectPositionBlock(vec3d.add(d3 * d0, d3 * d1, d3 * d2), enumdirection, blockposition, false);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static EnumDirection a(AxisAlignedBB axisalignedbb, Vec3D vec3d, double[] adouble, @Nullable EnumDirection enumdirection, double d0, double d1, double d2) {
/* 432 */     if (d0 > 1.0E-7D) {
/* 433 */       enumdirection = a(adouble, enumdirection, d0, d1, d2, axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxY, axisalignedbb.minZ, axisalignedbb.maxZ, EnumDirection.WEST, vec3d.x, vec3d.y, vec3d.z);
/* 434 */     } else if (d0 < -1.0E-7D) {
/* 435 */       enumdirection = a(adouble, enumdirection, d0, d1, d2, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxY, axisalignedbb.minZ, axisalignedbb.maxZ, EnumDirection.EAST, vec3d.x, vec3d.y, vec3d.z);
/*     */     } 
/*     */     
/* 438 */     if (d1 > 1.0E-7D) {
/* 439 */       enumdirection = a(adouble, enumdirection, d1, d2, d0, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxZ, axisalignedbb.minX, axisalignedbb.maxX, EnumDirection.DOWN, vec3d.y, vec3d.z, vec3d.x);
/* 440 */     } else if (d1 < -1.0E-7D) {
/* 441 */       enumdirection = a(adouble, enumdirection, d1, d2, d0, axisalignedbb.maxY, axisalignedbb.minZ, axisalignedbb.maxZ, axisalignedbb.minX, axisalignedbb.maxX, EnumDirection.UP, vec3d.y, vec3d.z, vec3d.x);
/*     */     } 
/*     */     
/* 444 */     if (d2 > 1.0E-7D) {
/* 445 */       enumdirection = a(adouble, enumdirection, d2, d0, d1, axisalignedbb.minZ, axisalignedbb.minX, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxY, EnumDirection.NORTH, vec3d.z, vec3d.x, vec3d.y);
/* 446 */     } else if (d2 < -1.0E-7D) {
/* 447 */       enumdirection = a(adouble, enumdirection, d2, d0, d1, axisalignedbb.maxZ, axisalignedbb.minX, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxY, EnumDirection.SOUTH, vec3d.z, vec3d.x, vec3d.y);
/*     */     } 
/*     */     
/* 450 */     return enumdirection;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static EnumDirection a(double[] adouble, @Nullable EnumDirection enumdirection, double d0, double d1, double d2, double d3, double d4, double d5, double d6, double d7, EnumDirection enumdirection1, double d8, double d9, double d10) {
/* 455 */     double d11 = (d3 - d8) / d0;
/* 456 */     double d12 = d9 + d11 * d1;
/* 457 */     double d13 = d10 + d11 * d2;
/*     */     
/* 459 */     if (0.0D < d11 && d11 < adouble[0] && d4 - 1.0E-7D < d12 && d12 < d5 + 1.0E-7D && d6 - 1.0E-7D < d13 && d13 < d7 + 1.0E-7D) {
/* 460 */       adouble[0] = d11;
/* 461 */       return enumdirection1;
/*     */     } 
/* 463 */     return enumdirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 468 */     return "AABB[" + this.minX + ", " + this.minY + ", " + this.minZ + "] -> [" + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
/*     */   }
/*     */   
/*     */   public Vec3D f() {
/* 472 */     return new Vec3D(MathHelper.d(0.5D, this.minX, this.maxX), MathHelper.d(0.5D, this.minY, this.maxY), MathHelper.d(0.5D, this.minZ, this.maxZ));
/*     */   }
/*     */   
/*     */   public static AxisAlignedBB g(double d0, double d1, double d2) {
/* 476 */     return new AxisAlignedBB(-d0 / 2.0D, -d1 / 2.0D, -d2 / 2.0D, d0 / 2.0D, d1 / 2.0D, d2 / 2.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AxisAlignedBB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */