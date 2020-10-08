/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.AbstractIterator;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import java.util.Iterator;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.IntStream;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
/*     */ import javax.annotation.concurrent.Immutable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ 
/*     */ @Immutable
/*     */ public class BlockPosition
/*     */   extends BaseBlockPosition
/*     */ {
/*     */   public static final Codec<BlockPosition> a;
/*     */   
/*     */   static {
/*  25 */     a = Codec.INT_STREAM.comapFlatMap(intstream -> SystemUtils.a(intstream, 3).map(()), blockposition -> IntStream.of(new int[] { blockposition.getX(), blockposition.getY(), blockposition.getZ() })).stable();
/*  26 */   } private static final Logger LOGGER = LogManager.getLogger();
/*  27 */   public static final BlockPosition ZERO = new BlockPosition(0, 0, 0);
/*     */   
/*     */   private static final int f = 26;
/*     */   
/*     */   private static final int g = 26;
/*     */   private static final int h = 12;
/*     */   private static final long i = 67108863L;
/*     */   private static final long j = 4095L;
/*     */   private static final long k = 67108863L;
/*     */   private static final int l = 12;
/*     */   private static final int m = 38;
/*     */   
/*     */   public BlockPosition(int i, int j, int k) {
/*  40 */     super(i, j, k);
/*     */   }
/*     */   
/*     */   public BlockPosition(double d0, double d1, double d2) {
/*  44 */     super(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public BlockPosition(Vec3D vec3d) {
/*  48 */     this(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   
/*     */   public BlockPosition(IPosition iposition) {
/*  52 */     this(iposition.getX(), iposition.getY(), iposition.getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition(BaseBlockPosition baseblockposition) {
/*  56 */     this(baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ());
/*     */   }
/*     */   public static long getAdjacent(int baseX, int baseY, int baseZ, EnumDirection enumdirection) {
/*  59 */     return asLong(baseX + enumdirection.getAdjacentX(), baseY + enumdirection.getAdjacentY(), baseZ + enumdirection.getAdjacentZ());
/*     */   } public static long a(long i, EnumDirection enumdirection) {
/*  61 */     return a(i, enumdirection.getAdjacentX(), enumdirection.getAdjacentY(), enumdirection.getAdjacentZ());
/*     */   }
/*     */   
/*     */   public static long a(long i, int j, int k, int l) {
/*  65 */     return a((int)(i >> 38L) + j, (int)(i << 52L >> 52L) + k, (int)(i << 26L >> 38L) + l);
/*     */   }
/*     */   
/*     */   public static int b(long i) {
/*  69 */     return (int)(i >> 38L);
/*     */   }
/*     */   
/*     */   public static int c(long i) {
/*  73 */     return (int)(i << 52L >> 52L);
/*     */   }
/*     */   
/*     */   public static int d(long i) {
/*  77 */     return (int)(i << 26L >> 38L);
/*     */   }
/*     */   
/*     */   public static BlockPosition fromLong(long i) {
/*  81 */     return new BlockPosition((int)(i >> 38L), (int)(i << 52L >> 52L), (int)(i << 26L >> 38L));
/*     */   }
/*     */   
/*     */   public long asLong() {
/*  85 */     return a(getX(), getY(), getZ());
/*     */   }
/*     */   public static long asLong(int x, int y, int z) {
/*  88 */     return a(x, y, z);
/*     */   } public static long a(int i, int j, int k) {
/*  90 */     return (i & 0x3FFFFFFL) << 38L | j & 0xFFFL | (k & 0x3FFFFFFL) << 12L;
/*     */   }
/*     */   
/*     */   public static long f(long i) {
/*  94 */     return i & 0xFFFFFFFFFFFFFFF0L;
/*     */   }
/*     */   
/*     */   public BlockPosition a(double d0, double d1, double d2) {
/*  98 */     return (d0 == 0.0D && d1 == 0.0D && d2 == 0.0D) ? this : new BlockPosition(getX() + d0, getY() + d1, getZ() + d2);
/*     */   }
/*     */   public final BlockPosition add(int i, int j, int k) {
/* 101 */     return b(i, j, k);
/*     */   } public BlockPosition b(int i, int j, int k) {
/* 103 */     return (i == 0 && j == 0 && k == 0) ? this : new BlockPosition(getX() + i, getY() + j, getZ() + k);
/*     */   }
/*     */   public final BlockPosition add(BaseBlockPosition baseblockposition) {
/* 106 */     return a(baseblockposition);
/*     */   } public BlockPosition a(BaseBlockPosition baseblockposition) {
/* 108 */     return b(baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition b(BaseBlockPosition baseblockposition) {
/* 112 */     return b(-baseblockposition.getX(), -baseblockposition.getY(), -baseblockposition.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition up() {
/* 117 */     return new BlockPosition(getX(), getY() + 1, getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition up(int i) {
/* 122 */     return (i == 0) ? this : new BlockPosition(getX(), getY() + i, getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition down() {
/* 127 */     return new BlockPosition(getX(), getY() - 1, getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition down(int i) {
/* 132 */     return (i == 0) ? this : new BlockPosition(getX(), getY() - i, getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition north() {
/* 136 */     return new BlockPosition(getX(), getY(), getZ() - 1);
/*     */   }
/*     */   
/*     */   public BlockPosition north(int i) {
/* 140 */     return (i == 0) ? this : new BlockPosition(getX(), getY(), getZ() - i);
/*     */   }
/*     */   
/*     */   public BlockPosition south() {
/* 144 */     return new BlockPosition(getX(), getY(), getZ() + 1);
/*     */   }
/*     */   
/*     */   public BlockPosition south(int i) {
/* 148 */     return (i == 0) ? this : new BlockPosition(getX(), getY(), getZ() + i);
/*     */   }
/*     */   
/*     */   public BlockPosition west() {
/* 152 */     return new BlockPosition(getX() - 1, getY(), getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition west(int i) {
/* 156 */     return (i == 0) ? this : new BlockPosition(getX() - i, getY(), getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition east() {
/* 160 */     return new BlockPosition(getX() + 1, getY(), getZ());
/*     */   }
/*     */   
/*     */   public BlockPosition east(int i) {
/* 164 */     return (i == 0) ? this : new BlockPosition(getX() + i, getY(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition shift(EnumDirection enumdirection) {
/* 169 */     switch (enumdirection) {
/*     */       case X:
/* 171 */         return new BlockPosition(getX(), getY() + 1, getZ());
/*     */       case Y:
/* 173 */         return new BlockPosition(getX(), getY() - 1, getZ());
/*     */       case Z:
/* 175 */         return new BlockPosition(getX(), getY(), getZ() - 1);
/*     */       case null:
/* 177 */         return new BlockPosition(getX(), getY(), getZ() + 1);
/*     */       case null:
/* 179 */         return new BlockPosition(getX() - 1, getY(), getZ());
/*     */       case null:
/* 181 */         return new BlockPosition(getX() + 1, getY(), getZ());
/*     */     } 
/* 183 */     return new BlockPosition(getX() + enumdirection.getAdjacentX(), getY() + enumdirection.getAdjacentY(), getZ() + enumdirection.getAdjacentZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPosition shift(EnumDirection enumdirection, int i) {
/* 190 */     return (i == 0) ? this : new BlockPosition(getX() + enumdirection.getAdjacentX() * i, getY() + enumdirection.getAdjacentY() * i, getZ() + enumdirection.getAdjacentZ() * i);
/*     */   }
/*     */   
/*     */   public BlockPosition a(EnumDirection.EnumAxis enumdirection_enumaxis, int i) {
/* 194 */     if (i == 0) {
/* 195 */       return this;
/*     */     }
/* 197 */     int j = (enumdirection_enumaxis == EnumDirection.EnumAxis.X) ? i : 0;
/* 198 */     int k = (enumdirection_enumaxis == EnumDirection.EnumAxis.Y) ? i : 0;
/* 199 */     int l = (enumdirection_enumaxis == EnumDirection.EnumAxis.Z) ? i : 0;
/*     */     
/* 201 */     return new BlockPosition(getX() + j, getY() + k, getZ() + l);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(EnumBlockRotation enumblockrotation) {
/* 206 */     switch (enumblockrotation)
/*     */     
/*     */     { default:
/* 209 */         return this;
/*     */       case Y:
/* 211 */         return new BlockPosition(-getZ(), getY(), getX());
/*     */       case Z:
/* 213 */         return new BlockPosition(-getX(), getY(), -getZ());
/*     */       case null:
/* 215 */         break; }  return new BlockPosition(getZ(), getY(), -getX());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPosition d(BaseBlockPosition baseblockposition) {
/* 221 */     return new BlockPosition(getY() * baseblockposition.getZ() - getZ() * baseblockposition.getY(), getZ() * baseblockposition.getX() - getX() * baseblockposition.getZ(), getX() * baseblockposition.getY() - getY() * baseblockposition.getX());
/*     */   }
/*     */   
/*     */   public BlockPosition immutableCopy() {
/* 225 */     return this;
/*     */   }
/*     */   
/*     */   public MutableBlockPosition i() {
/* 229 */     return new MutableBlockPosition(getX(), getY(), getZ());
/*     */   }
/*     */   
/*     */   public static Iterable<BlockPosition> a(final Random random, final int i, final int j, final int k, final int l, int i1, int j1, int k1) {
/* 233 */     final int l1 = i1 - j + 1;
/* 234 */     final int i2 = j1 - k + 1;
/* 235 */     final int j2 = k1 - l + 1;
/*     */     
/* 237 */     return () -> new AbstractIterator<BlockPosition>()
/*     */       {
/* 239 */         final BlockPosition.MutableBlockPosition a = new BlockPosition.MutableBlockPosition();
/* 240 */         int b = i;
/*     */         
/*     */         protected BlockPosition computeNext() {
/* 243 */           if (this.b <= 0) {
/* 244 */             return (BlockPosition)endOfData();
/*     */           }
/* 246 */           BlockPosition.MutableBlockPosition blockposition_mutableblockposition = this.a.d(j + random.nextInt(l1), k + random.nextInt(i2), l + random.nextInt(j2));
/*     */           
/* 248 */           this.b--;
/* 249 */           return blockposition_mutableblockposition;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable<BlockPosition> a(BlockPosition blockposition, final int p_i, final int p_j, final int p_k) {
/* 257 */     final int l_decompiled = p_i + p_j + p_k;
/* 258 */     final int i1 = blockposition.getX();
/* 259 */     final int j1 = blockposition.getY();
/* 260 */     final int k1 = blockposition.getZ();
/*     */     
/* 262 */     return () -> new AbstractIterator<BlockPosition>()
/*     */       {
/* 264 */         private final BlockPosition.MutableBlockPosition h = new BlockPosition.MutableBlockPosition();
/*     */         private int i;
/*     */         private int j;
/*     */         private int k;
/*     */         private int l;
/*     */         private int m;
/*     */         private boolean n;
/*     */         
/*     */         protected BlockPosition computeNext() {
/* 273 */           if (this.n) {
/* 274 */             this.n = false;
/* 275 */             this.h.q(k1 - this.h.getZ() - k1);
/* 276 */             return this.h;
/*     */           } 
/*     */           
/*     */           BlockPosition.MutableBlockPosition blockposition_mutableblockposition;
/* 280 */           for (blockposition_mutableblockposition = null; blockposition_mutableblockposition == null; this.m++) {
/* 281 */             if (this.m > this.k) {
/* 282 */               this.l++;
/* 283 */               if (this.l > this.j) {
/* 284 */                 this.i++;
/* 285 */                 if (this.i > l_decompiled) {
/* 286 */                   return (BlockPosition)endOfData();
/*     */                 }
/*     */                 
/* 289 */                 this.j = Math.min(p_i, this.i);
/* 290 */                 this.l = -this.j;
/*     */               } 
/*     */               
/* 293 */               this.k = Math.min(p_j, this.i - Math.abs(this.l));
/* 294 */               this.m = -this.k;
/*     */             } 
/*     */             
/* 297 */             int l1 = this.l;
/* 298 */             int i2 = this.m;
/* 299 */             int j2 = this.i - Math.abs(l1) - Math.abs(i2);
/*     */             
/* 301 */             if (j2 <= p_k) {
/* 302 */               this.n = (j2 != 0);
/* 303 */               blockposition_mutableblockposition = this.h.d(i1 + l1, j1 + i2, k1 + j2);
/*     */             } 
/*     */           } 
/*     */           
/* 307 */           return blockposition_mutableblockposition;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Optional<BlockPosition> a(BlockPosition blockposition, int i, int j, Predicate<BlockPosition> predicate) {
/* 315 */     return b(blockposition, i, j, i).filter(predicate).findFirst();
/*     */   }
/*     */   
/*     */   public static Stream<BlockPosition> b(BlockPosition blockposition, int i, int j, int k) {
/* 319 */     return StreamSupport.stream(a(blockposition, i, j, k).spliterator(), false);
/*     */   }
/*     */   
/*     */   public static Iterable<BlockPosition> a(BlockPosition blockposition, BlockPosition blockposition1) {
/* 323 */     return b(Math.min(blockposition.getX(), blockposition1.getX()), Math.min(blockposition.getY(), blockposition1.getY()), Math.min(blockposition.getZ(), blockposition1.getZ()), Math.max(blockposition.getX(), blockposition1.getX()), Math.max(blockposition.getY(), blockposition1.getY()), Math.max(blockposition.getZ(), blockposition1.getZ()));
/*     */   }
/*     */   
/*     */   public static Stream<BlockPosition> b(BlockPosition blockposition, BlockPosition blockposition1) {
/* 327 */     return StreamSupport.stream(a(blockposition, blockposition1).spliterator(), false);
/*     */   }
/*     */   
/*     */   public static Stream<BlockPosition> a(StructureBoundingBox structureboundingbox) {
/* 331 */     return a(Math.min(structureboundingbox.a, structureboundingbox.d), Math.min(structureboundingbox.b, structureboundingbox.e), Math.min(structureboundingbox.c, structureboundingbox.f), Math.max(structureboundingbox.a, structureboundingbox.d), Math.max(structureboundingbox.b, structureboundingbox.e), Math.max(structureboundingbox.c, structureboundingbox.f));
/*     */   }
/*     */   
/*     */   public static Stream<BlockPosition> a(AxisAlignedBB axisalignedbb) {
/* 335 */     return a(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ));
/*     */   }
/*     */   
/*     */   public static Stream<BlockPosition> a(int i, int j, int k, int l, int i1, int j1) {
/* 339 */     return StreamSupport.stream(b(i, j, k, l, i1, j1).spliterator(), false);
/*     */   }
/*     */   
/*     */   public static Iterable<BlockPosition> b(final int i, final int j, final int k, int l, int i1, int j1) {
/* 343 */     final int k1 = l - i + 1;
/* 344 */     final int l1 = i1 - j + 1;
/* 345 */     int i2 = j1 - k + 1;
/* 346 */     final int j2 = k1 * l1 * i2;
/*     */     
/* 348 */     return () -> new AbstractIterator<BlockPosition>()
/*     */       {
/* 350 */         private final BlockPosition.MutableBlockPosition g = new BlockPosition.MutableBlockPosition();
/*     */         private int h;
/*     */         
/*     */         protected BlockPosition computeNext() {
/* 354 */           if (this.h == j2) {
/* 355 */             return (BlockPosition)endOfData();
/*     */           }
/* 357 */           int k2 = this.h % k1;
/* 358 */           int l2 = this.h / k1;
/* 359 */           int i3 = l2 % l1;
/* 360 */           int j3 = l2 / l1;
/*     */           
/* 362 */           this.h++;
/* 363 */           return this.g.d(i + k2, j + i3, k + j3);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable<MutableBlockPosition> a(final BlockPosition blockposition, final int I, final EnumDirection enumdirection, final EnumDirection enumdirection1) {
/* 371 */     Validate.validState((enumdirection.n() != enumdirection1.n()), "The two directions cannot be on the same axis", new Object[0]);
/* 372 */     return () -> new AbstractIterator<MutableBlockPosition>()
/*     */       {
/* 374 */         private final EnumDirection[] e = new EnumDirection[] { this.val$enumdirection, this.val$enumdirection1, this.val$enumdirection.opposite(), this.val$enumdirection1.opposite() };
/* 375 */         private final BlockPosition.MutableBlockPosition f = blockposition.i().c(enumdirection1);
/* 376 */         private final int g = 4 * I;
/* 377 */         private int h = -1;
/*     */ 
/*     */         
/*     */         private int i;
/*     */ 
/*     */         
/*     */         private int j;
/*     */         
/* 385 */         private int k = this.f.getX();
/* 386 */         private int l = this.f.getY();
/* 387 */         private int m = this.f.getZ();
/*     */ 
/*     */         
/*     */         protected BlockPosition.MutableBlockPosition computeNext() {
/* 391 */           this.f.d(this.k, this.l, this.m).c(this.e[(this.h + 4) % 4]);
/* 392 */           this.k = this.f.getX();
/* 393 */           this.l = this.f.getY();
/* 394 */           this.m = this.f.getZ();
/* 395 */           if (this.j >= this.i) {
/* 396 */             if (this.h >= this.g) {
/* 397 */               return (BlockPosition.MutableBlockPosition)endOfData();
/*     */             }
/*     */             
/* 400 */             this.h++;
/* 401 */             this.j = 0;
/* 402 */             this.i = this.h / 2 + 1;
/*     */           } 
/*     */           
/* 405 */           this.j++;
/* 406 */           return this.f;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static class MutableBlockPosition
/*     */     extends BlockPosition
/*     */   {
/*     */     public MutableBlockPosition() {
/* 415 */       this(0, 0, 0);
/*     */     }
/*     */     
/*     */     public MutableBlockPosition(int i, int j, int k) {
/* 419 */       super(i, j, k);
/*     */     }
/*     */     
/*     */     public MutableBlockPosition(double d0, double d1, double d2) {
/* 423 */       this(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2));
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition a(double d0, double d1, double d2) {
/* 428 */       return super.a(d0, d1, d2).immutableCopy();
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition b(int i, int j, int k) {
/* 433 */       return super.b(i, j, k).immutableCopy();
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition shift(EnumDirection enumdirection, int i) {
/* 438 */       return super.shift(enumdirection, i).immutableCopy();
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition a(EnumDirection.EnumAxis enumdirection_enumaxis, int i) {
/* 443 */       return super.a(enumdirection_enumaxis, i).immutableCopy();
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPosition a(EnumBlockRotation enumblockrotation) {
/* 448 */       return super.a(enumblockrotation).immutableCopy();
/*     */     }
/*     */     public final MutableBlockPosition setValues(int i, int j, int k) {
/* 451 */       return d(i, j, k);
/*     */     } public final MutableBlockPosition d(int i, int j, int k) {
/* 453 */       this.a = i;
/* 454 */       this.b = j;
/* 455 */       this.e = k;
/* 456 */       return this;
/*     */     }
/*     */     public final MutableBlockPosition setValues(double d0, double d1, double d2) {
/* 459 */       return c(d0, d1, d2);
/*     */     } public MutableBlockPosition c(double d0, double d1, double d2) {
/* 461 */       return d(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2));
/*     */     }
/*     */     public final MutableBlockPosition setValues(BaseBlockPosition baseblockposition) {
/* 464 */       return g(baseblockposition);
/*     */     } public final MutableBlockPosition g(BaseBlockPosition baseblockposition) {
/* 466 */       this.a = baseblockposition.a;
/* 467 */       this.b = baseblockposition.b;
/* 468 */       this.e = baseblockposition.e;
/* 469 */       return this;
/*     */     }
/*     */     
/*     */     public final MutableBlockPosition g(long i) {
/* 473 */       this.a = (int)(i >> 38L);
/* 474 */       this.b = (int)(i << 52L >> 52L);
/* 475 */       this.e = (int)(i << 26L >> 38L);
/* 476 */       return this;
/*     */     }
/*     */     
/*     */     public MutableBlockPosition a(EnumAxisCycle enumaxiscycle, int i, int j, int k) {
/* 480 */       return d(enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.X), enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.Y), enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.Z));
/*     */     }
/*     */     
/*     */     public MutableBlockPosition a(BaseBlockPosition baseblockposition, EnumDirection enumdirection) {
/* 484 */       return d(baseblockposition.getX() + enumdirection.getAdjacentX(), baseblockposition.getY() + enumdirection.getAdjacentY(), baseblockposition.getZ() + enumdirection.getAdjacentZ());
/*     */     }
/*     */     
/*     */     public MutableBlockPosition a(BaseBlockPosition baseblockposition, int i, int j, int k) {
/* 488 */       return d(baseblockposition.getX() + i, baseblockposition.getY() + j, baseblockposition.getZ() + k);
/*     */     }
/*     */     
/*     */     public final MutableBlockPosition c(EnumDirection enumdirection) {
/* 492 */       this.a += enumdirection.getAdjacentX();
/* 493 */       this.b += enumdirection.getAdjacentY();
/* 494 */       this.e += enumdirection.getAdjacentZ();
/* 495 */       return this;
/*     */     }
/*     */     
/*     */     public MutableBlockPosition c(EnumDirection enumdirection, int i) {
/* 499 */       return d(getX() + enumdirection.getAdjacentX() * i, getY() + enumdirection.getAdjacentY() * i, getZ() + enumdirection.getAdjacentZ() * i);
/*     */     }
/*     */     
/*     */     public MutableBlockPosition e(int i, int j, int k) {
/* 503 */       return d(getX() + i, getY() + j, getZ() + k);
/*     */     }
/*     */     
/*     */     public MutableBlockPosition h(BaseBlockPosition baseblockposition) {
/* 507 */       return d(getX() + baseblockposition.getX(), getY() + baseblockposition.getY(), getZ() + baseblockposition.getZ());
/*     */     }
/*     */     
/*     */     public MutableBlockPosition a(EnumDirection.EnumAxis enumdirection_enumaxis, int i, int j) {
/* 511 */       switch (enumdirection_enumaxis) {
/*     */         case X:
/* 513 */           return d(MathHelper.clamp(getX(), i, j), getY(), getZ());
/*     */         case Y:
/* 515 */           return d(getX(), MathHelper.clamp(getY(), i, j), getZ());
/*     */         case Z:
/* 517 */           return d(getX(), getY(), MathHelper.clamp(getZ(), i, j));
/*     */       } 
/* 519 */       throw new IllegalStateException("Unable to clamp axis " + enumdirection_enumaxis);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final void setX(int value) {
/* 526 */       this.a = value;
/*     */     }
/*     */     public final void setY(int value) {
/* 529 */       this.b = value;
/*     */     }
/*     */     public final void setZ(int value) {
/* 532 */       this.e = value;
/*     */     }
/*     */     
/*     */     public final void o(int i) {
/* 536 */       this.a = i;
/*     */     }
/*     */     
/*     */     public final void p(int i) {
/* 540 */       this.b = i;
/*     */     }
/*     */     
/*     */     public final void q(int i) {
/* 544 */       this.e = i;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockPosition immutableCopy() {
/* 550 */       return new BlockPosition(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */