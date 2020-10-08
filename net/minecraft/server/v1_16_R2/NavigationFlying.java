/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class NavigationFlying
/*     */   extends NavigationAbstract {
/*     */   public NavigationFlying(EntityInsentient entityinsentient, World world) {
/*   6 */     super(entityinsentient, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Pathfinder a(int i) {
/*  11 */     this.o = new PathfinderFlying();
/*  12 */     this.o.a(true);
/*  13 */     return new Pathfinder(this.o, i);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a() {
/*  18 */     return ((r() && p()) || !this.a.isPassenger());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D b() {
/*  23 */     return this.a.getPositionVector();
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity a(Entity entity, int i) {
/*  28 */     return a(entity.getChunkCoordinates(), entity, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  33 */     this.e++;
/*  34 */     if (this.m) {
/*  35 */       j();
/*     */     }
/*     */     
/*  38 */     if (!m()) {
/*     */ 
/*     */       
/*  41 */       if (a()) {
/*  42 */         l();
/*  43 */       } else if (this.c != null && !this.c.c()) {
/*  44 */         Vec3D vec3d = this.c.a(this.a);
/*  45 */         if (MathHelper.floor(this.a.locX()) == MathHelper.floor(vec3d.x) && MathHelper.floor(this.a.locY()) == MathHelper.floor(vec3d.y) && MathHelper.floor(this.a.locZ()) == MathHelper.floor(vec3d.z)) {
/*  46 */           this.c.a();
/*     */         }
/*     */       } 
/*     */       
/*  50 */       PacketDebug.a(this.b, this.a, this.c, this.l);
/*  51 */       if (!m()) {
/*  52 */         Vec3D vec3d = this.c.a(this.a);
/*  53 */         this.a.getControllerMove().a(vec3d.x, vec3d.y, vec3d.z, this.d);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Vec3D vec3d, Vec3D vec3d1, int i, int j, int k) {
/*  60 */     int l = MathHelper.floor(vec3d.x);
/*  61 */     int i1 = MathHelper.floor(vec3d.y);
/*  62 */     int j1 = MathHelper.floor(vec3d.z);
/*  63 */     double d0 = vec3d1.x - vec3d.x;
/*  64 */     double d1 = vec3d1.y - vec3d.y;
/*  65 */     double d2 = vec3d1.z - vec3d.z;
/*  66 */     double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */     
/*  68 */     if (d3 < 1.0E-8D) {
/*  69 */       return false;
/*     */     }
/*  71 */     double d4 = 1.0D / Math.sqrt(d3);
/*     */     
/*  73 */     d0 *= d4;
/*  74 */     d1 *= d4;
/*  75 */     d2 *= d4;
/*  76 */     double d5 = 1.0D / Math.abs(d0);
/*  77 */     double d6 = 1.0D / Math.abs(d1);
/*  78 */     double d7 = 1.0D / Math.abs(d2);
/*  79 */     double d8 = l - vec3d.x;
/*  80 */     double d9 = i1 - vec3d.y;
/*  81 */     double d10 = j1 - vec3d.z;
/*     */     
/*  83 */     if (d0 >= 0.0D) {
/*  84 */       d8++;
/*     */     }
/*     */     
/*  87 */     if (d1 >= 0.0D) {
/*  88 */       d9++;
/*     */     }
/*     */     
/*  91 */     if (d2 >= 0.0D) {
/*  92 */       d10++;
/*     */     }
/*     */     
/*  95 */     d8 /= d0;
/*  96 */     d9 /= d1;
/*  97 */     d10 /= d2;
/*  98 */     int k1 = (d0 < 0.0D) ? -1 : 1;
/*  99 */     int l1 = (d1 < 0.0D) ? -1 : 1;
/* 100 */     int i2 = (d2 < 0.0D) ? -1 : 1;
/* 101 */     int j2 = MathHelper.floor(vec3d1.x);
/* 102 */     int k2 = MathHelper.floor(vec3d1.y);
/* 103 */     int l2 = MathHelper.floor(vec3d1.z);
/* 104 */     int i3 = j2 - l;
/* 105 */     int j3 = k2 - i1;
/* 106 */     int k3 = l2 - j1;
/*     */     
/* 108 */     while (i3 * k1 > 0 || j3 * l1 > 0 || k3 * i2 > 0) {
/* 109 */       if (d8 < d10 && d8 <= d9) {
/* 110 */         d8 += d5;
/* 111 */         l += k1;
/* 112 */         i3 = j2 - l; continue;
/* 113 */       }  if (d9 < d8 && d9 <= d10) {
/* 114 */         d9 += d6;
/* 115 */         i1 += l1;
/* 116 */         j3 = k2 - i1; continue;
/*     */       } 
/* 118 */       d10 += d7;
/* 119 */       j1 += i2;
/* 120 */       k3 = l2 - j1;
/*     */     } 
/*     */ 
/*     */     
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(boolean flag) {
/* 129 */     this.o.b(flag);
/*     */   }
/*     */   
/*     */   public void b(boolean flag) {
/* 133 */     this.o.a(flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition) {
/* 138 */     return this.b.getType(blockposition).a(this.b, blockposition, this.a);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NavigationFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */