/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.serialization.DynamicLike;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ public class WorldBorder {
/*  10 */   private final List<IWorldBorderListener> a = Lists.newArrayList();
/*  11 */   private double b = 0.2D;
/*  12 */   private double d = 5.0D;
/*  13 */   private int e = 15;
/*  14 */   private int f = 5;
/*     */   private double g;
/*     */   private double h;
/*  17 */   private int i = 29999984;
/*  18 */   private a j = new d(6.0E7D);
/*  19 */   public static final c c = new c(0.0D, 0.0D, 0.2D, 5.0D, 5, 15, 6.0E7D, 0L, 0.0D);
/*     */   public WorldServer world;
/*     */   private final BlockPosition.MutableBlockPosition mutPos;
/*     */   
/*     */   public final boolean isInBounds(BlockPosition blockposition) {
/*  24 */     return a(blockposition);
/*     */   } public boolean a(BlockPosition blockposition) {
/*  26 */     return ((blockposition.getX() + 1) > e() && blockposition.getX() < g() && (blockposition.getZ() + 1) > f() && blockposition.getZ() < h());
/*     */   }
/*     */   
/*     */   public WorldBorder() {
/*  30 */     this.mutPos = new BlockPosition.MutableBlockPosition();
/*     */   } public boolean isBlockInBounds(int chunkX, int chunkZ) {
/*  32 */     this.mutPos.setValues(chunkX, 64, chunkZ);
/*  33 */     return isInBounds(this.mutPos);
/*     */   }
/*     */   public boolean isChunkInBounds(int chunkX, int chunkZ) {
/*  36 */     this.mutPos.setValues((chunkX << 4) + 15, 64, (chunkZ << 4) + 15);
/*  37 */     return isInBounds(this.mutPos);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInBounds(ChunkCoordIntPair chunkcoordintpair) {
/*  42 */     return (chunkcoordintpair.f() > e() && chunkcoordintpair.d() < g() && chunkcoordintpair.g() > f() && chunkcoordintpair.e() < h());
/*     */   }
/*     */   public final boolean isInBounds(AxisAlignedBB aabb) {
/*  45 */     return a(aabb);
/*     */   } public boolean a(AxisAlignedBB axisalignedbb) {
/*  47 */     return (axisalignedbb.maxX > e() && axisalignedbb.minX < g() && axisalignedbb.maxZ > f() && axisalignedbb.minZ < h());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isCollidingOnBorderEdge(AxisAlignedBB boundingBox) {
/*  53 */     return isCollidingOnBorderEdge(boundingBox.minX, boundingBox.maxX, boundingBox.minZ, boundingBox.maxZ);
/*     */   }
/*     */   
/*     */   public final boolean isCollidingOnBorderEdge(double boxMinX, double boxMaxX, double boxMinZ, double boxMaxZ) {
/*  57 */     double minX = getMinX() - 1.0E-7D;
/*  58 */     double maxX = getMaxX() + 1.0E-7D;
/*     */     
/*  60 */     double minZ = getMinZ() - 1.0E-7D;
/*  61 */     double maxZ = getMaxZ() + 1.0E-7D;
/*     */     
/*  63 */     return ((minX >= boxMinX || maxX <= boxMaxX || minZ >= boxMinZ || maxZ <= boxMaxZ) && minX < boxMaxX && maxX > boxMinX && minZ < boxMaxZ && maxZ > boxMinZ && (boxMinX >= minX || boxMaxX <= maxX || boxMinZ >= minZ || boxMaxZ <= maxZ));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double a(Entity entity) {
/*  82 */     return b(entity.locX(), entity.locZ());
/*     */   }
/*     */   
/*  85 */   public final VoxelShape asVoxelShape() { return c(); } public final VoxelShape getCollisionShape() {
/*  86 */     return c();
/*     */   } public VoxelShape c() {
/*  88 */     return this.j.m();
/*     */   }
/*     */   
/*     */   public double b(double d0, double d1) {
/*  92 */     double d2 = d1 - f();
/*  93 */     double d3 = h() - d1;
/*  94 */     double d4 = d0 - e();
/*  95 */     double d5 = g() - d0;
/*  96 */     double d6 = Math.min(d4, d5);
/*     */     
/*  98 */     d6 = Math.min(d6, d2);
/*  99 */     return Math.min(d6, d3);
/*     */   }
/*     */   public final double getMinX() {
/* 102 */     return e();
/*     */   } public double e() {
/* 104 */     return this.j.a();
/*     */   }
/*     */   public final double getMinZ() {
/* 107 */     return f();
/*     */   } public double f() {
/* 109 */     return this.j.c();
/*     */   }
/*     */   public final double getMaxX() {
/* 112 */     return g();
/*     */   } public double g() {
/* 114 */     return this.j.b();
/*     */   }
/*     */   public final double getMaxZ() {
/* 117 */     return h();
/*     */   } public double h() {
/* 119 */     return this.j.d();
/*     */   }
/*     */   
/*     */   public double getCenterX() {
/* 123 */     return this.g;
/*     */   }
/*     */   
/*     */   public double getCenterZ() {
/* 127 */     return this.h;
/*     */   }
/*     */   
/*     */   public void setCenter(double d0, double d1) {
/* 131 */     this.g = d0;
/* 132 */     this.h = d1;
/* 133 */     this.j.k();
/* 134 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 136 */     while (iterator.hasNext()) {
/* 137 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 139 */       iworldborderlistener.a(this, d0, d1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSize() {
/* 145 */     return this.j.e();
/*     */   }
/*     */   
/*     */   public long j() {
/* 149 */     return this.j.g();
/*     */   }
/*     */   
/*     */   public double k() {
/* 153 */     return this.j.h();
/*     */   }
/*     */   
/*     */   public void setSize(double d0) {
/* 157 */     this.j = new d(d0);
/* 158 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 160 */     while (iterator.hasNext()) {
/* 161 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 163 */       iworldborderlistener.a(this, d0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void transitionSizeBetween(double d0, double d1, long i) {
/* 169 */     this.j = (d0 == d1) ? new d(d1) : new b(d0, d1, i);
/* 170 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 172 */     while (iterator.hasNext()) {
/* 173 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 175 */       iworldborderlistener.a(this, d0, d1, i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<IWorldBorderListener> l() {
/* 181 */     return Lists.newArrayList(this.a);
/*     */   }
/*     */   
/*     */   public void a(IWorldBorderListener iworldborderlistener) {
/* 185 */     if (this.a.contains(iworldborderlistener))
/* 186 */       return;  this.a.add(iworldborderlistener);
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 190 */     this.i = i;
/* 191 */     this.j.j();
/*     */   }
/*     */   
/*     */   public int m() {
/* 195 */     return this.i;
/*     */   }
/*     */   
/*     */   public double getDamageBuffer() {
/* 199 */     return this.d;
/*     */   }
/*     */   
/*     */   public void setDamageBuffer(double d0) {
/* 203 */     this.d = d0;
/* 204 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 206 */     while (iterator.hasNext()) {
/* 207 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 209 */       iworldborderlistener.c(this, d0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamageAmount() {
/* 215 */     return this.b;
/*     */   }
/*     */   
/*     */   public void setDamageAmount(double d0) {
/* 219 */     this.b = d0;
/* 220 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 222 */     while (iterator.hasNext()) {
/* 223 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 225 */       iworldborderlistener.b(this, d0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarningTime() {
/* 231 */     return this.e;
/*     */   }
/*     */   
/*     */   public void setWarningTime(int i) {
/* 235 */     this.e = i;
/* 236 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 238 */     while (iterator.hasNext()) {
/* 239 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 241 */       iworldborderlistener.a(this, i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarningDistance() {
/* 247 */     return this.f;
/*     */   }
/*     */   
/*     */   public void setWarningDistance(int i) {
/* 251 */     this.f = i;
/* 252 */     Iterator<IWorldBorderListener> iterator = l().iterator();
/*     */     
/* 254 */     while (iterator.hasNext()) {
/* 255 */       IWorldBorderListener iworldborderlistener = iterator.next();
/*     */       
/* 257 */       iworldborderlistener.b(this, i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void s() {
/* 263 */     this.j = this.j.l();
/*     */   }
/*     */   
/*     */   public c t() {
/* 267 */     return new c(this);
/*     */   }
/*     */   
/*     */   public void a(c worldborder_c) {
/* 271 */     setCenter(worldborder_c.a(), worldborder_c.b());
/* 272 */     setDamageAmount(worldborder_c.c());
/* 273 */     setDamageBuffer(worldborder_c.d());
/* 274 */     setWarningDistance(worldborder_c.e());
/* 275 */     setWarningTime(worldborder_c.f());
/* 276 */     if (worldborder_c.h() > 0L) {
/* 277 */       transitionSizeBetween(worldborder_c.g(), worldborder_c.i(), worldborder_c.h());
/*     */     } else {
/* 279 */       setSize(worldborder_c.g());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class c
/*     */   {
/*     */     private final double a;
/*     */     private final double b;
/*     */     private final double c;
/*     */     private final double d;
/*     */     private final int e;
/*     */     private final int f;
/*     */     private final double g;
/*     */     private final long h;
/*     */     private final double i;
/*     */     
/*     */     private c(double d0, double d1, double d2, double d3, int i, int j, double d4, long k, double d5) {
/* 297 */       this.a = d0;
/* 298 */       this.b = d1;
/* 299 */       this.c = d2;
/* 300 */       this.d = d3;
/* 301 */       this.e = i;
/* 302 */       this.f = j;
/* 303 */       this.g = d4;
/* 304 */       this.h = k;
/* 305 */       this.i = d5;
/*     */     }
/*     */     
/*     */     private c(WorldBorder worldborder) {
/* 309 */       this.a = worldborder.getCenterX();
/* 310 */       this.b = worldborder.getCenterZ();
/* 311 */       this.c = worldborder.getDamageAmount();
/* 312 */       this.d = worldborder.getDamageBuffer();
/* 313 */       this.e = worldborder.getWarningDistance();
/* 314 */       this.f = worldborder.getWarningTime();
/* 315 */       this.g = worldborder.getSize();
/* 316 */       this.h = worldborder.j();
/* 317 */       this.i = worldborder.k();
/*     */     }
/*     */     
/*     */     public double a() {
/* 321 */       return this.a;
/*     */     }
/*     */     
/*     */     public double b() {
/* 325 */       return this.b;
/*     */     }
/*     */     
/*     */     public double c() {
/* 329 */       return this.c;
/*     */     }
/*     */     
/*     */     public double d() {
/* 333 */       return this.d;
/*     */     }
/*     */     
/*     */     public int e() {
/* 337 */       return this.e;
/*     */     }
/*     */     
/*     */     public int f() {
/* 341 */       return this.f;
/*     */     }
/*     */     
/*     */     public double g() {
/* 345 */       return this.g;
/*     */     }
/*     */     
/*     */     public long h() {
/* 349 */       return this.h;
/*     */     }
/*     */     
/*     */     public double i() {
/* 353 */       return this.i;
/*     */     }
/*     */     
/*     */     public static c a(DynamicLike<?> dynamiclike, c worldborder_c) {
/* 357 */       double d0 = dynamiclike.get("BorderCenterX").asDouble(worldborder_c.a);
/* 358 */       double d1 = dynamiclike.get("BorderCenterZ").asDouble(worldborder_c.b);
/* 359 */       double d2 = dynamiclike.get("BorderSize").asDouble(worldborder_c.g);
/* 360 */       long i = dynamiclike.get("BorderSizeLerpTime").asLong(worldborder_c.h);
/* 361 */       double d3 = dynamiclike.get("BorderSizeLerpTarget").asDouble(worldborder_c.i);
/* 362 */       double d4 = dynamiclike.get("BorderSafeZone").asDouble(worldborder_c.d);
/* 363 */       double d5 = dynamiclike.get("BorderDamagePerBlock").asDouble(worldborder_c.c);
/* 364 */       int j = dynamiclike.get("BorderWarningBlocks").asInt(worldborder_c.e);
/* 365 */       int k = dynamiclike.get("BorderWarningTime").asInt(worldborder_c.f);
/*     */       
/* 367 */       return new c(d0, d1, d5, d4, j, k, d2, i, d3);
/*     */     }
/*     */     
/*     */     public void a(NBTTagCompound nbttagcompound) {
/* 371 */       nbttagcompound.setDouble("BorderCenterX", this.a);
/* 372 */       nbttagcompound.setDouble("BorderCenterZ", this.b);
/* 373 */       nbttagcompound.setDouble("BorderSize", this.g);
/* 374 */       nbttagcompound.setLong("BorderSizeLerpTime", this.h);
/* 375 */       nbttagcompound.setDouble("BorderSafeZone", this.d);
/* 376 */       nbttagcompound.setDouble("BorderDamagePerBlock", this.c);
/* 377 */       nbttagcompound.setDouble("BorderSizeLerpTarget", this.i);
/* 378 */       nbttagcompound.setDouble("BorderWarningBlocks", this.e);
/* 379 */       nbttagcompound.setDouble("BorderWarningTime", this.f);
/*     */     }
/*     */   }
/*     */   
/*     */   class d
/*     */     implements a {
/*     */     private final double b;
/*     */     private double c;
/*     */     private double d;
/*     */     private double e;
/*     */     private double f;
/*     */     private VoxelShape g;
/*     */     
/*     */     public d(double d0) {
/* 393 */       this.b = d0;
/* 394 */       n();
/*     */     }
/*     */ 
/*     */     
/*     */     public double a() {
/* 399 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public double b() {
/* 404 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public double c() {
/* 409 */       return this.d;
/*     */     }
/*     */ 
/*     */     
/*     */     public double d() {
/* 414 */       return this.f;
/*     */     }
/*     */ 
/*     */     
/*     */     public double e() {
/* 419 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public long g() {
/* 424 */       return 0L;
/*     */     }
/*     */ 
/*     */     
/*     */     public double h() {
/* 429 */       return this.b;
/*     */     }
/*     */     
/*     */     private void n() {
/* 433 */       this.c = Math.max(WorldBorder.this.getCenterX() - this.b / 2.0D, -WorldBorder.this.i);
/* 434 */       this.d = Math.max(WorldBorder.this.getCenterZ() - this.b / 2.0D, -WorldBorder.this.i);
/* 435 */       this.e = Math.min(WorldBorder.this.getCenterX() + this.b / 2.0D, WorldBorder.this.i);
/* 436 */       this.f = Math.min(WorldBorder.this.getCenterZ() + this.b / 2.0D, WorldBorder.this.i);
/* 437 */       this.g = VoxelShapes.a(VoxelShapes.a, VoxelShapes.create(Math.floor(a()), Double.NEGATIVE_INFINITY, Math.floor(c()), Math.ceil(b()), Double.POSITIVE_INFINITY, Math.ceil(d())), OperatorBoolean.ONLY_FIRST);
/*     */     }
/*     */ 
/*     */     
/*     */     public void j() {
/* 442 */       n();
/*     */     }
/*     */ 
/*     */     
/*     */     public void k() {
/* 447 */       n();
/*     */     }
/*     */ 
/*     */     
/*     */     public WorldBorder.a l() {
/* 452 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public VoxelShape m() {
/* 457 */       return this.g;
/*     */     }
/*     */   }
/*     */   
/*     */   class b
/*     */     implements a {
/*     */     private final double b;
/*     */     private final double c;
/*     */     private final long d;
/*     */     private final long e;
/*     */     private final double f;
/*     */     
/*     */     private b(double d0, double d1, long i) {
/* 470 */       this.b = d0;
/* 471 */       this.c = d1;
/* 472 */       this.f = i;
/* 473 */       this.e = SystemUtils.getMonotonicMillis();
/* 474 */       this.d = this.e + i;
/*     */     }
/*     */ 
/*     */     
/*     */     public double a() {
/* 479 */       return Math.max(WorldBorder.this.getCenterX() - e() / 2.0D, -WorldBorder.this.i);
/*     */     }
/*     */ 
/*     */     
/*     */     public double c() {
/* 484 */       return Math.max(WorldBorder.this.getCenterZ() - e() / 2.0D, -WorldBorder.this.i);
/*     */     }
/*     */ 
/*     */     
/*     */     public double b() {
/* 489 */       return Math.min(WorldBorder.this.getCenterX() + e() / 2.0D, WorldBorder.this.i);
/*     */     }
/*     */ 
/*     */     
/*     */     public double d() {
/* 494 */       return Math.min(WorldBorder.this.getCenterZ() + e() / 2.0D, WorldBorder.this.i);
/*     */     }
/*     */ 
/*     */     
/*     */     public double e() {
/* 499 */       double d0 = (SystemUtils.getMonotonicMillis() - this.e) / this.f;
/*     */       
/* 501 */       return (d0 < 1.0D) ? MathHelper.d(d0, this.b, this.c) : this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public long g() {
/* 506 */       return this.d - SystemUtils.getMonotonicMillis();
/*     */     }
/*     */ 
/*     */     
/*     */     public double h() {
/* 511 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public void k() {}
/*     */ 
/*     */     
/*     */     public void j() {}
/*     */ 
/*     */     
/*     */     public WorldBorder.a l() {
/* 522 */       Objects.requireNonNull(WorldBorder.this); return (g() <= 0L) ? new WorldBorder.d(this.c) : this;
/*     */     }
/*     */ 
/*     */     
/*     */     public VoxelShape m() {
/* 527 */       return VoxelShapes.a(VoxelShapes.a, VoxelShapes.create(Math.floor(a()), Double.NEGATIVE_INFINITY, Math.floor(c()), Math.ceil(b()), Double.POSITIVE_INFINITY, Math.ceil(d())), OperatorBoolean.ONLY_FIRST);
/*     */     }
/*     */   }
/*     */   
/*     */   static interface a {
/*     */     double a();
/*     */     
/*     */     double b();
/*     */     
/*     */     double c();
/*     */     
/*     */     double d();
/*     */     
/*     */     double e();
/*     */     
/*     */     long g();
/*     */     
/*     */     double h();
/*     */     
/*     */     void j();
/*     */     
/*     */     void k();
/*     */     
/*     */     a l();
/*     */     
/*     */     VoxelShape m();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */