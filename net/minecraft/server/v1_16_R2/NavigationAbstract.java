/*     */ package net.minecraft.server.v1_16_R2;public abstract class NavigationAbstract { protected final EntityInsentient a; protected final World b; @Nullable
/*     */   protected PathEntity c; protected double d; protected int e; protected int f; protected Vec3D g; protected BaseBlockPosition h; protected long i; protected long j; protected double k; protected float l; protected boolean m; protected long n; protected PathfinderAbstract o; private BlockPosition p;
/*     */   private int q;
/*     */   private float r;
/*     */   private final Pathfinder s;
/*     */   private boolean t;
/*     */   private int lastFailure;
/*     */   private int pathfindFailures;
/*     */   
/*     */   public Entity getEntity() {
/*  11 */     return this.a;
/*     */   }
/*     */   protected final PathEntity getCurrentPath() {
/*  14 */     return this.c;
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
/*     */   public Pathfinder getPathfinder() {
/*  30 */     return this.s;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void g() {
/*     */     this.r = 1.0F;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float f) {
/*     */     this.r = f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPosition h() {
/*     */     return this.p;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Pathfinder a(int paramInt);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(double d0) {
/*     */     this.d = d0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NavigationAbstract(EntityInsentient entityinsentient, World world) {
/* 167 */     this.lastFailure = 0;
/* 168 */     this.pathfindFailures = 0; this.g = Vec3D.ORIGIN; this.h = BaseBlockPosition.ZERO; this.l = 0.5F; this.r = 1.0F; this.a = entityinsentient; this.b = world; int i = MathHelper.floor(entityinsentient.b(GenericAttributes.FOLLOW_RANGE) * 16.0D); this.s = a(i);
/*     */   } public boolean i() { return this.m; } public void j() { if (this.b.getTime() - this.n > 20L) { if (this.p != null) { this.c = null; this.c = a(this.p, this.q); this.n = this.b.getTime(); this.m = false; }  } else { this.m = true; }
/*     */      }
/*     */   @Nullable public final PathEntity calculateDestination(double d0, double d1, double d2) { return a(d0, d1, d2, 0); }
/*     */   public final PathEntity a(double d0, double d1, double d2, int i) { return a(new BlockPosition(d0, d1, d2), i); }
/* 173 */   public boolean a(Entity entity, double d0) { if (this.pathfindFailures > 10 && getCurrentPath() == null && MinecraftServer.currentTick < this.lastFailure + 40) {
/* 174 */       return false;
/*     */     }
/*     */     
/* 177 */     PathEntity pathentity = a(entity, 1);
/*     */ 
/*     */     
/* 180 */     if (pathentity != null && a(pathentity, d0)) {
/* 181 */       this.lastFailure = 0;
/* 182 */       this.pathfindFailures = 0;
/* 183 */       return true;
/*     */     } 
/* 185 */     this.pathfindFailures++;
/* 186 */     this.lastFailure = MinecraftServer.currentTick;
/* 187 */     return false; }
/*     */   @Nullable public PathEntity a(Stream<BlockPosition> stream, int i) { return a(stream.collect((Collector)Collectors.toSet()), 8, false, i); }
/*     */   @Nullable public PathEntity a(Set<BlockPosition> set, int i) { return a(set, 8, false, i); }
/*     */   @Nullable public PathEntity a(BlockPosition blockposition, int i) { return a(blockposition, null, i); }
/*     */   @Nullable
/* 192 */   public PathEntity a(BlockPosition blockposition, Entity target, int i) { return a((Set<BlockPosition>)ImmutableSet.of(blockposition), target, 8, false, i); } public boolean setDestination(@Nullable PathEntity pathentity, double speed) { return a(pathentity, speed); }
/*     */   @Nullable public final PathEntity calculateDestination(Entity entity) { return a(entity, 0); }
/*     */   public PathEntity a(Entity entity, int i) { return a((Set<BlockPosition>)ImmutableSet.of(entity.getChunkCoordinates()), entity, 16, true, i); } @Nullable protected PathEntity a(Set<BlockPosition> set, int i, boolean flag, int j) { return a(set, (Entity)null, i, flag, j); } @Nullable protected PathEntity a(Set<BlockPosition> set, Entity target, int i, boolean flag, int j) { // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokeinterface isEmpty : ()Z
/*     */     //   6: ifeq -> 11
/*     */     //   9: aconst_null
/*     */     //   10: areturn
/*     */     //   11: aload_0
/*     */     //   12: getfield a : Lnet/minecraft/server/v1_16_R2/EntityInsentient;
/*     */     //   15: invokevirtual locY : ()D
/*     */     //   18: dconst_0
/*     */     //   19: dcmpg
/*     */     //   20: ifge -> 25
/*     */     //   23: aconst_null
/*     */     //   24: areturn
/*     */     //   25: aload_0
/*     */     //   26: invokevirtual a : ()Z
/*     */     //   29: ifne -> 34
/*     */     //   32: aconst_null
/*     */     //   33: areturn
/*     */     //   34: aload_0
/*     */     //   35: getfield c : Lnet/minecraft/server/v1_16_R2/PathEntity;
/*     */     //   38: ifnull -> 69
/*     */     //   41: aload_0
/*     */     //   42: getfield c : Lnet/minecraft/server/v1_16_R2/PathEntity;
/*     */     //   45: invokevirtual c : ()Z
/*     */     //   48: ifne -> 69
/*     */     //   51: aload_1
/*     */     //   52: aload_0
/*     */     //   53: getfield p : Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   56: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   61: ifeq -> 69
/*     */     //   64: aload_0
/*     */     //   65: getfield c : Lnet/minecraft/server/v1_16_R2/PathEntity;
/*     */     //   68: areturn
/*     */     //   69: iconst_0
/*     */     //   70: istore #6
/*     */     //   72: aload_1
/*     */     //   73: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   78: astore #7
/*     */     //   80: aload #7
/*     */     //   82: invokeinterface hasNext : ()Z
/*     */     //   87: ifeq -> 204
/*     */     //   90: aload #7
/*     */     //   92: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   97: checkcast net/minecraft/server/v1_16_R2/BlockPosition
/*     */     //   100: astore #8
/*     */     //   102: aload_0
/*     */     //   103: invokevirtual getEntity : ()Lnet/minecraft/server/v1_16_R2/Entity;
/*     */     //   106: invokevirtual getWorld : ()Lnet/minecraft/server/v1_16_R2/World;
/*     */     //   109: invokevirtual getWorldBorder : ()Lnet/minecraft/server/v1_16_R2/WorldBorder;
/*     */     //   112: aload #8
/*     */     //   114: invokevirtual isInBounds : (Lnet/minecraft/server/v1_16_R2/BlockPosition;)Z
/*     */     //   117: ifeq -> 164
/*     */     //   120: new com/destroystokyo/paper/event/entity/EntityPathfindEvent
/*     */     //   123: dup
/*     */     //   124: aload_0
/*     */     //   125: invokevirtual getEntity : ()Lnet/minecraft/server/v1_16_R2/Entity;
/*     */     //   128: invokevirtual getBukkitEntity : ()Lorg/bukkit/craftbukkit/v1_16_R2/entity/CraftEntity;
/*     */     //   131: aload_0
/*     */     //   132: invokevirtual getEntity : ()Lnet/minecraft/server/v1_16_R2/Entity;
/*     */     //   135: getfield world : Lnet/minecraft/server/v1_16_R2/World;
/*     */     //   138: aload #8
/*     */     //   140: invokestatic toLocation : (Lnet/minecraft/server/v1_16_R2/World;Lnet/minecraft/server/v1_16_R2/BlockPosition;)Lorg/bukkit/Location;
/*     */     //   143: aload_2
/*     */     //   144: ifnonnull -> 151
/*     */     //   147: aconst_null
/*     */     //   148: goto -> 155
/*     */     //   151: aload_2
/*     */     //   152: invokevirtual getBukkitEntity : ()Lorg/bukkit/craftbukkit/v1_16_R2/entity/CraftEntity;
/*     */     //   155: invokespecial <init> : (Lorg/bukkit/entity/Entity;Lorg/bukkit/Location;Lorg/bukkit/entity/Entity;)V
/*     */     //   158: invokevirtual callEvent : ()Z
/*     */     //   161: ifne -> 201
/*     */     //   164: iload #6
/*     */     //   166: ifne -> 181
/*     */     //   169: iconst_1
/*     */     //   170: istore #6
/*     */     //   172: new java/util/HashSet
/*     */     //   175: dup
/*     */     //   176: aload_1
/*     */     //   177: invokespecial <init> : (Ljava/util/Collection;)V
/*     */     //   180: astore_1
/*     */     //   181: aload_1
/*     */     //   182: aload #8
/*     */     //   184: invokeinterface remove : (Ljava/lang/Object;)Z
/*     */     //   189: pop
/*     */     //   190: aload_1
/*     */     //   191: invokeinterface isEmpty : ()Z
/*     */     //   196: ifeq -> 201
/*     */     //   199: aconst_null
/*     */     //   200: areturn
/*     */     //   201: goto -> 80
/*     */     //   204: aload_0
/*     */     //   205: getfield b : Lnet/minecraft/server/v1_16_R2/World;
/*     */     //   208: invokevirtual getMethodProfiler : ()Lnet/minecraft/server/v1_16_R2/GameProfilerFiller;
/*     */     //   211: ldc_w 'pathfind'
/*     */     //   214: invokeinterface enter : (Ljava/lang/String;)V
/*     */     //   219: aload_0
/*     */     //   220: getfield a : Lnet/minecraft/server/v1_16_R2/EntityInsentient;
/*     */     //   223: getstatic net/minecraft/server/v1_16_R2/GenericAttributes.FOLLOW_RANGE : Lnet/minecraft/server/v1_16_R2/AttributeBase;
/*     */     //   226: invokevirtual b : (Lnet/minecraft/server/v1_16_R2/AttributeBase;)D
/*     */     //   229: d2f
/*     */     //   230: fstore #7
/*     */     //   232: iload #4
/*     */     //   234: ifeq -> 250
/*     */     //   237: aload_0
/*     */     //   238: getfield a : Lnet/minecraft/server/v1_16_R2/EntityInsentient;
/*     */     //   241: invokevirtual getChunkCoordinates : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   244: invokevirtual up : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   247: goto -> 257
/*     */     //   250: aload_0
/*     */     //   251: getfield a : Lnet/minecraft/server/v1_16_R2/EntityInsentient;
/*     */     //   254: invokevirtual getChunkCoordinates : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   257: astore #8
/*     */     //   259: fload #7
/*     */     //   261: iload_3
/*     */     //   262: i2f
/*     */     //   263: fadd
/*     */     //   264: f2i
/*     */     //   265: istore #9
/*     */     //   267: new net/minecraft/server/v1_16_R2/ChunkCache
/*     */     //   270: dup
/*     */     //   271: aload_0
/*     */     //   272: getfield b : Lnet/minecraft/server/v1_16_R2/World;
/*     */     //   275: aload #8
/*     */     //   277: iload #9
/*     */     //   279: ineg
/*     */     //   280: iload #9
/*     */     //   282: ineg
/*     */     //   283: iload #9
/*     */     //   285: ineg
/*     */     //   286: invokevirtual b : (III)Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   289: aload #8
/*     */     //   291: iload #9
/*     */     //   293: iload #9
/*     */     //   295: iload #9
/*     */     //   297: invokevirtual b : (III)Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   300: invokespecial <init> : (Lnet/minecraft/server/v1_16_R2/World;Lnet/minecraft/server/v1_16_R2/BlockPosition;Lnet/minecraft/server/v1_16_R2/BlockPosition;)V
/*     */     //   303: astore #10
/*     */     //   305: aload_0
/*     */     //   306: getfield s : Lnet/minecraft/server/v1_16_R2/Pathfinder;
/*     */     //   309: aload #10
/*     */     //   311: aload_0
/*     */     //   312: getfield a : Lnet/minecraft/server/v1_16_R2/EntityInsentient;
/*     */     //   315: aload_1
/*     */     //   316: fload #7
/*     */     //   318: iload #5
/*     */     //   320: aload_0
/*     */     //   321: getfield r : F
/*     */     //   324: invokevirtual a : (Lnet/minecraft/server/v1_16_R2/ChunkCache;Lnet/minecraft/server/v1_16_R2/EntityInsentient;Ljava/util/Set;FIF)Lnet/minecraft/server/v1_16_R2/PathEntity;
/*     */     //   327: astore #11
/*     */     //   329: aload_0
/*     */     //   330: getfield b : Lnet/minecraft/server/v1_16_R2/World;
/*     */     //   333: invokevirtual getMethodProfiler : ()Lnet/minecraft/server/v1_16_R2/GameProfilerFiller;
/*     */     //   336: invokeinterface exit : ()V
/*     */     //   341: aload #11
/*     */     //   343: ifnull -> 373
/*     */     //   346: aload #11
/*     */     //   348: invokevirtual m : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   351: ifnull -> 373
/*     */     //   354: aload_0
/*     */     //   355: aload #11
/*     */     //   357: invokevirtual m : ()Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   360: putfield p : Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   363: aload_0
/*     */     //   364: iload #5
/*     */     //   366: putfield q : I
/*     */     //   369: aload_0
/*     */     //   370: invokespecial f : ()V
/*     */     //   373: aload #11
/*     */     //   375: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #118	-> 0
/*     */     //   #119	-> 9
/*     */     //   #120	-> 11
/*     */     //   #121	-> 23
/*     */     //   #122	-> 25
/*     */     //   #123	-> 32
/*     */     //   #124	-> 34
/*     */     //   #125	-> 64
/*     */     //   #128	-> 69
/*     */     //   #129	-> 72
/*     */     //   #130	-> 102
/*     */     //   #131	-> 132
/*     */     //   #132	-> 164
/*     */     //   #133	-> 169
/*     */     //   #134	-> 172
/*     */     //   #137	-> 181
/*     */     //   #138	-> 190
/*     */     //   #139	-> 199
/*     */     //   #142	-> 201
/*     */     //   #144	-> 204
/*     */     //   #145	-> 219
/*     */     //   #146	-> 232
/*     */     //   #147	-> 259
/*     */     //   #148	-> 267
/*     */     //   #149	-> 305
/*     */     //   #151	-> 329
/*     */     //   #152	-> 341
/*     */     //   #153	-> 354
/*     */     //   #154	-> 363
/*     */     //   #155	-> 369
/*     */     //   #158	-> 373
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   102	99	8	possibleTarget	Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   72	304	6	copiedSet	Z
/*     */     //   232	144	7	f	F
/*     */     //   259	117	8	blockposition	Lnet/minecraft/server/v1_16_R2/BlockPosition;
/*     */     //   267	109	9	k	I
/*     */     //   305	71	10	chunkcache	Lnet/minecraft/server/v1_16_R2/ChunkCache;
/*     */     //   329	47	11	pathentity	Lnet/minecraft/server/v1_16_R2/PathEntity;
/*     */     //   0	376	0	this	Lnet/minecraft/server/v1_16_R2/NavigationAbstract;
/*     */     //   0	376	1	set	Ljava/util/Set;
/*     */     //   0	376	2	target	Lnet/minecraft/server/v1_16_R2/Entity;
/*     */     //   0	376	3	i	I
/*     */     //   0	376	4	flag	Z
/*     */     //   0	376	5	j	I
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/* 194 */     //   0	376	1	set	Ljava/util/Set<Lnet/minecraft/server/v1_16_R2/BlockPosition;>; } public boolean a(double d0, double d1, double d2, double d3) { return a(a(d0, d1, d2, 1), d3); } public boolean a(@Nullable PathEntity pathentity, double d0) { if (pathentity == null) {
/* 195 */       this.c = null;
/* 196 */       return false;
/*     */     } 
/* 198 */     if (!pathentity.a(this.c)) {
/* 199 */       this.c = pathentity;
/*     */     }
/*     */     
/* 202 */     if (m()) {
/* 203 */       return false;
/*     */     }
/* 205 */     D_();
/* 206 */     if (this.c.e() <= 0) {
/* 207 */       return false;
/*     */     }
/* 209 */     this.d = d0;
/* 210 */     Vec3D vec3d = b();
/*     */     
/* 212 */     this.f = this.e;
/* 213 */     this.g = vec3d;
/* 214 */     return true; }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PathEntity getPathEntity()
/*     */   {
/* 220 */     return k(); } @Nullable
/*     */   public PathEntity k() {
/* 222 */     return this.c;
/*     */   }
/*     */   
/*     */   public void c() {
/* 226 */     this.e++;
/* 227 */     if (this.m) {
/* 228 */       j();
/*     */     }
/*     */     
/* 231 */     if (!m()) {
/*     */ 
/*     */       
/* 234 */       if (a()) {
/* 235 */         l();
/* 236 */       } else if (this.c != null && !this.c.c()) {
/* 237 */         Vec3D vec3d = b();
/* 238 */         Vec3D vec3d1 = this.c.a(this.a);
/*     */         
/* 240 */         if (vec3d.y > vec3d1.y && !this.a.isOnGround() && MathHelper.floor(vec3d.x) == MathHelper.floor(vec3d1.x) && MathHelper.floor(vec3d.z) == MathHelper.floor(vec3d1.z)) {
/* 241 */           this.c.a();
/*     */         }
/*     */       } 
/*     */       
/* 245 */       PacketDebug.a(this.b, this.a, this.c, this.l);
/* 246 */       if (!m()) {
/* 247 */         Vec3D vec3d = this.c.a(this.a);
/* 248 */         BlockPosition blockposition = new BlockPosition(vec3d);
/*     */         
/* 250 */         this.a.getControllerMove().a(vec3d.x, this.b.getType(blockposition.down()).isAir() ? vec3d.y : PathfinderNormal.a(this.b, blockposition), vec3d.z, this.d);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void l() {
/* 256 */     Vec3D vec3d = b();
/*     */     
/* 258 */     this.l = (this.a.getWidth() > 0.75F) ? (this.a.getWidth() / 2.0F) : (0.75F - this.a.getWidth() / 2.0F);
/* 259 */     BlockPosition blockposition = this.c.g();
/* 260 */     double d0 = Math.abs(this.a.locX() - blockposition.getX() + 0.5D);
/* 261 */     double d1 = Math.abs(this.a.locY() - blockposition.getY());
/* 262 */     double d2 = Math.abs(this.a.locZ() - blockposition.getZ() + 0.5D);
/* 263 */     boolean flag = (d0 < this.l && d2 < this.l && d1 < 1.0D);
/*     */     
/* 265 */     if (flag || (this.a.b((this.c.h()).l) && b(vec3d))) {
/* 266 */       this.c.a();
/*     */     }
/*     */     
/* 269 */     a(vec3d);
/*     */   }
/*     */   
/*     */   private boolean b(Vec3D vec3d) {
/* 273 */     if (this.c.f() + 1 >= this.c.e()) {
/* 274 */       return false;
/*     */     }
/* 276 */     Vec3D vec3d1 = Vec3D.c(this.c.g());
/*     */     
/* 278 */     if (!vec3d.a(vec3d1, 2.0D)) {
/* 279 */       return false;
/*     */     }
/* 281 */     Vec3D vec3d2 = Vec3D.c(this.c.d(this.c.f() + 1));
/* 282 */     Vec3D vec3d3 = vec3d2.d(vec3d1);
/* 283 */     Vec3D vec3d4 = vec3d.d(vec3d1);
/*     */     
/* 285 */     return (vec3d3.b(vec3d4) > 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(Vec3D vec3d) {
/* 291 */     if (this.e - this.f > 100) {
/* 292 */       if (vec3d.distanceSquared(this.g) < 2.25D) {
/* 293 */         this.t = true;
/* 294 */         o();
/*     */       } else {
/* 296 */         this.t = false;
/*     */       } 
/*     */       
/* 299 */       this.f = this.e;
/* 300 */       this.g = vec3d;
/*     */     } 
/*     */     
/* 303 */     if (this.c != null && !this.c.c()) {
/* 304 */       BlockPosition blockposition = this.c.g();
/*     */       
/* 306 */       if (blockposition.equals(this.h)) {
/* 307 */         this.i += SystemUtils.getMonotonicMillis() - this.j;
/*     */       } else {
/* 309 */         this.h = blockposition;
/* 310 */         double d0 = vec3d.f(Vec3D.c(this.h));
/*     */         
/* 312 */         this.k = (this.a.dM() > 0.0F) ? (d0 / this.a.dM() * 1000.0D) : 0.0D;
/*     */       } 
/*     */       
/* 315 */       if (this.k > 0.0D && this.i > this.k * 3.0D) {
/* 316 */         e();
/*     */       }
/*     */       
/* 319 */       this.j = SystemUtils.getMonotonicMillis();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void e() {
/* 325 */     f();
/* 326 */     o();
/*     */   }
/*     */   
/*     */   private void f() {
/* 330 */     this.h = BaseBlockPosition.ZERO;
/* 331 */     this.i = 0L;
/* 332 */     this.k = 0.0D;
/* 333 */     this.t = false;
/*     */   }
/*     */   
/*     */   public boolean m() {
/* 337 */     return (this.c == null || this.c.c());
/*     */   }
/*     */   
/*     */   public boolean n() {
/* 341 */     return !m();
/*     */   }
/*     */   public void stopPathfinding() {
/* 344 */     o();
/*     */   } public void o() {
/* 346 */     this.c = null;
/*     */   }
/*     */   
/*     */   protected abstract Vec3D b();
/*     */   
/*     */   protected abstract boolean a();
/*     */   
/*     */   protected boolean p() {
/* 354 */     return (this.a.aG() || this.a.aP());
/*     */   }
/*     */   
/*     */   protected void D_() {
/* 358 */     if (this.c != null) {
/* 359 */       for (int i = 0; i < this.c.e(); i++) {
/* 360 */         PathPoint pathpoint = this.c.a(i);
/* 361 */         PathPoint pathpoint1 = (i + 1 < this.c.e()) ? this.c.a(i + 1) : null;
/* 362 */         IBlockData iblockdata = this.b.getType(new BlockPosition(pathpoint.a, pathpoint.b, pathpoint.c));
/*     */         
/* 364 */         if (iblockdata.a(Blocks.CAULDRON)) {
/* 365 */           this.c.a(i, pathpoint.a(pathpoint.a, pathpoint.b + 1, pathpoint.c));
/* 366 */           if (pathpoint1 != null && pathpoint.b >= pathpoint1.b) {
/* 367 */             this.c.a(i + 1, pathpoint.a(pathpoint1.a, pathpoint.b + 1, pathpoint1.c));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract boolean a(Vec3D paramVec3D1, Vec3D paramVec3D2, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public boolean a(BlockPosition blockposition) {
/* 378 */     BlockPosition blockposition1 = blockposition.down();
/*     */     
/* 380 */     return this.b.getType(blockposition1).i(this.b, blockposition1);
/*     */   }
/*     */   
/*     */   public PathfinderAbstract q() {
/* 384 */     return this.o;
/*     */   }
/*     */   
/*     */   public void d(boolean flag) {
/* 388 */     this.o.c(flag);
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 392 */     return this.o.e();
/*     */   }
/*     */   
/*     */   public void b(BlockPosition blockposition) {
/* 396 */     if (this.c != null && !this.c.c() && this.c.e() != 0) {
/* 397 */       PathPoint pathpoint = this.c.d();
/* 398 */       Vec3D vec3d = new Vec3D((pathpoint.a + this.a.locX()) / 2.0D, (pathpoint.b + this.a.locY()) / 2.0D, (pathpoint.c + this.a.locZ()) / 2.0D);
/*     */       
/* 400 */       if (blockposition.a(vec3d, (this.c.e() - this.c.f()))) {
/* 401 */         j();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean t() {
/* 408 */     return this.t;
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NavigationAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */