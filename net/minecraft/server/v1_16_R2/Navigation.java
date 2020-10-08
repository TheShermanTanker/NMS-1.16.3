/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Navigation
/*     */   extends NavigationAbstract {
/*     */   private boolean p;
/*     */   
/*     */   public Navigation(EntityInsentient entityinsentient, World world) {
/*  10 */     super(entityinsentient, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Pathfinder a(int i) {
/*  15 */     this.o = new PathfinderNormal();
/*  16 */     this.o.a(true);
/*  17 */     return new Pathfinder(this.o, i);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a() {
/*  22 */     return (this.a.isOnGround() || p() || this.a.isPassenger());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D b() {
/*  27 */     return new Vec3D(this.a.locX(), u(), this.a.locZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity a(BlockPosition blockposition, int i) {
/*  34 */     if (this.b.getType(blockposition).isAir()) {
/*  35 */       BlockPosition blockPosition; for (blockPosition = blockposition.down(); blockPosition.getY() > 0 && this.b.getType(blockPosition).isAir(); blockPosition = blockPosition.down());
/*     */ 
/*     */ 
/*     */       
/*  39 */       if (blockPosition.getY() > 0) {
/*  40 */         return super.a(blockPosition.up(), i);
/*     */       }
/*     */       
/*  43 */       while (blockPosition.getY() < this.b.getBuildHeight() && this.b.getType(blockPosition).isAir()) {
/*  44 */         blockPosition = blockPosition.up();
/*     */       }
/*     */       
/*  47 */       blockposition = blockPosition;
/*     */     } 
/*     */     
/*  50 */     if (!this.b.getType(blockposition).getMaterial().isBuildable())
/*  51 */       return super.a(blockposition, i); 
/*     */     BlockPosition blockposition1;
/*  53 */     for (blockposition1 = blockposition.up(); blockposition1.getY() < this.b.getBuildHeight() && this.b.getType(blockposition1).getMaterial().isBuildable(); blockposition1 = blockposition1.up());
/*     */ 
/*     */ 
/*     */     
/*  57 */     return super.a(blockposition1, i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity a(Entity entity, int i) {
/*  63 */     return a(entity.getChunkCoordinates(), entity, i);
/*     */   }
/*     */   
/*     */   private int u() {
/*  67 */     if (this.a.isInWater() && r()) {
/*  68 */       int i = MathHelper.floor(this.a.locY());
/*  69 */       Block block = this.b.getType(new BlockPosition(this.a.locX(), i, this.a.locZ())).getBlock();
/*  70 */       int j = 0;
/*     */       
/*     */       do {
/*  73 */         if (block != Blocks.WATER) {
/*  74 */           return i;
/*     */         }
/*     */         
/*  77 */         i++;
/*  78 */         block = this.b.getType(new BlockPosition(this.a.locX(), i, this.a.locZ())).getBlock();
/*  79 */         ++j;
/*  80 */       } while (j <= 16);
/*     */       
/*  82 */       return MathHelper.floor(this.a.locY());
/*     */     } 
/*  84 */     return MathHelper.floor(this.a.locY() + 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void D_() {
/*  90 */     super.D_();
/*  91 */     if (this.p) {
/*  92 */       if (this.b.e(new BlockPosition(this.a.locX(), this.a.locY() + 0.5D, this.a.locZ()))) {
/*     */         return;
/*     */       }
/*     */       
/*  96 */       for (int i = 0; i < this.c.e(); i++) {
/*  97 */         PathPoint pathpoint = this.c.a(i);
/*     */         
/*  99 */         if (this.b.e(new BlockPosition(pathpoint.a, pathpoint.b, pathpoint.c))) {
/* 100 */           this.c.b(i);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(Vec3D vec3d, Vec3D vec3d1, int i, int j, int k) {
/* 110 */     int l = MathHelper.floor(vec3d.x);
/* 111 */     int i1 = MathHelper.floor(vec3d.z);
/* 112 */     double d0 = vec3d1.x - vec3d.x;
/* 113 */     double d1 = vec3d1.z - vec3d.z;
/* 114 */     double d2 = d0 * d0 + d1 * d1;
/*     */     
/* 116 */     if (d2 < 1.0E-8D) {
/* 117 */       return false;
/*     */     }
/* 119 */     double d3 = 1.0D / Math.sqrt(d2);
/*     */     
/* 121 */     d0 *= d3;
/* 122 */     d1 *= d3;
/* 123 */     i += 2;
/* 124 */     k += 2;
/* 125 */     if (!a(l, MathHelper.floor(vec3d.y), i1, i, j, k, vec3d, d0, d1)) {
/* 126 */       return false;
/*     */     }
/* 128 */     i -= 2;
/* 129 */     k -= 2;
/* 130 */     double d4 = 1.0D / Math.abs(d0);
/* 131 */     double d5 = 1.0D / Math.abs(d1);
/* 132 */     double d6 = l - vec3d.x;
/* 133 */     double d7 = i1 - vec3d.z;
/*     */     
/* 135 */     if (d0 >= 0.0D) {
/* 136 */       d6++;
/*     */     }
/*     */     
/* 139 */     if (d1 >= 0.0D) {
/* 140 */       d7++;
/*     */     }
/*     */     
/* 143 */     d6 /= d0;
/* 144 */     d7 /= d1;
/* 145 */     int j1 = (d0 < 0.0D) ? -1 : 1;
/* 146 */     int k1 = (d1 < 0.0D) ? -1 : 1;
/* 147 */     int l1 = MathHelper.floor(vec3d1.x);
/* 148 */     int i2 = MathHelper.floor(vec3d1.z);
/* 149 */     int j2 = l1 - l;
/* 150 */     int k2 = i2 - i1;
/*     */     
/*     */     do {
/* 153 */       if (j2 * j1 <= 0 && k2 * k1 <= 0) {
/* 154 */         return true;
/*     */       }
/*     */       
/* 157 */       if (d6 < d7) {
/* 158 */         d6 += d4;
/* 159 */         l += j1;
/* 160 */         j2 = l1 - l;
/*     */       } else {
/* 162 */         d7 += d5;
/* 163 */         i1 += k1;
/* 164 */         k2 = i2 - i1;
/*     */       } 
/* 166 */     } while (a(l, MathHelper.floor(vec3d.y), i1, i, j, k, vec3d, d0, d1));
/*     */     
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(int i, int j, int k, int l, int i1, int j1, Vec3D vec3d, double d0, double d1) {
/* 174 */     int k1 = i - l / 2;
/* 175 */     int l1 = k - j1 / 2;
/*     */     
/* 177 */     if (!b(k1, j, l1, l, i1, j1, vec3d, d0, d1)) {
/* 178 */       return false;
/*     */     }
/* 180 */     for (int i2 = k1; i2 < k1 + l; i2++) {
/* 181 */       for (int j2 = l1; j2 < l1 + j1; j2++) {
/* 182 */         double d2 = i2 + 0.5D - vec3d.x;
/* 183 */         double d3 = j2 + 0.5D - vec3d.z;
/*     */         
/* 185 */         if (d2 * d0 + d3 * d1 >= 0.0D) {
/* 186 */           PathType pathtype = this.o.a(this.b, i2, j - 1, j2, this.a, l, i1, j1, true, true);
/*     */           
/* 188 */           if (!a(pathtype)) {
/* 189 */             return false;
/*     */           }
/*     */           
/* 192 */           pathtype = this.o.a(this.b, i2, j, j2, this.a, l, i1, j1, true, true);
/* 193 */           float f = this.a.a(pathtype);
/*     */           
/* 195 */           if (f < 0.0F || f >= 8.0F) {
/* 196 */             return false;
/*     */           }
/*     */           
/* 199 */           if (pathtype == PathType.DAMAGE_FIRE || pathtype == PathType.DANGER_FIRE || pathtype == PathType.DAMAGE_OTHER) {
/* 200 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 206 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(PathType pathtype) {
/* 211 */     return (pathtype == PathType.WATER) ? false : ((pathtype == PathType.LAVA) ? false : ((pathtype != PathType.OPEN)));
/*     */   } private boolean b(int i, int j, int k, int l, int i1, int j1, Vec3D vec3d, double d0, double d1) {
/*     */     BlockPosition blockposition;
/*     */     double d2, d3;
/* 215 */     Iterator<BlockPosition> iterator = BlockPosition.a(new BlockPosition(i, j, k), new BlockPosition(i + l - 1, j + i1 - 1, k + j1 - 1)).iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 222 */       if (!iterator.hasNext()) {
/* 223 */         return true;
/*     */       }
/*     */       
/* 226 */       blockposition = iterator.next();
/* 227 */       d2 = blockposition.getX() + 0.5D - vec3d.x;
/* 228 */       d3 = blockposition.getZ() + 0.5D - vec3d.z;
/* 229 */     } while (d2 * d0 + d3 * d1 < 0.0D || this.b.getType(blockposition).a(this.b, blockposition, PathMode.LAND));
/*     */     
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 235 */     this.o.b(flag);
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 239 */     return this.o.c();
/*     */   }
/*     */   
/*     */   public void c(boolean flag) {
/* 243 */     this.p = flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Navigation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */