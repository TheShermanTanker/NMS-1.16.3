/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ public class NavigationGuardian
/*     */   extends NavigationAbstract
/*     */ {
/*     */   private boolean p;
/*     */   
/*     */   public NavigationGuardian(EntityInsentient var0, World var1) {
/*  21 */     super(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Pathfinder a(int var0) {
/*  26 */     this.p = this.a instanceof EntityDolphin;
/*  27 */     this.o = new PathfinderWater(this.p);
/*  28 */     return new Pathfinder(this.o, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a() {
/*  33 */     return (this.p || p());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3D b() {
/*  38 */     return new Vec3D(this.a.locX(), this.a.e(0.5D), this.a.locZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  43 */     this.e++;
/*     */     
/*  45 */     if (this.m) {
/*  46 */       j();
/*     */     }
/*     */     
/*  49 */     if (m()) {
/*     */       return;
/*     */     }
/*     */     
/*  53 */     if (a()) {
/*  54 */       l();
/*  55 */     } else if (this.c != null && !this.c.c()) {
/*  56 */       Vec3D vec3D = this.c.a(this.a);
/*  57 */       if (MathHelper.floor(this.a.locX()) == MathHelper.floor(vec3D.x) && MathHelper.floor(this.a.locY()) == MathHelper.floor(vec3D.y) && MathHelper.floor(this.a.locZ()) == MathHelper.floor(vec3D.z)) {
/*  58 */         this.c.a();
/*     */       }
/*     */     } 
/*     */     
/*  62 */     PacketDebug.a(this.b, this.a, this.c, this.l);
/*     */     
/*  64 */     if (m()) {
/*     */       return;
/*     */     }
/*     */     
/*  68 */     Vec3D var0 = this.c.a(this.a);
/*  69 */     this.a.getControllerMove().a(var0.x, var0.y, var0.z, this.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void l() {
/*  74 */     if (this.c == null) {
/*     */       return;
/*     */     }
/*     */     
/*  78 */     Vec3D var0 = b();
/*     */ 
/*     */     
/*  81 */     float var1 = this.a.getWidth();
/*  82 */     float var2 = (var1 > 0.75F) ? (var1 / 2.0F) : (0.75F - var1 / 2.0F);
/*  83 */     Vec3D var3 = this.a.getMot();
/*  84 */     if (Math.abs(var3.x) > 0.2D || Math.abs(var3.z) > 0.2D) {
/*  85 */       var2 = (float)(var2 * var3.f() * 6.0D);
/*     */     }
/*     */     
/*  88 */     int var4 = 6;
/*  89 */     Vec3D var5 = Vec3D.c(this.c.g());
/*  90 */     if (Math.abs(this.a.locX() - var5.x) < var2 && Math.abs(this.a.locZ() - var5.z) < var2 && Math.abs(this.a.locY() - var5.y) < (var2 * 2.0F)) {
/*  91 */       this.c.a();
/*     */     }
/*     */     
/*  94 */     for (int var6 = Math.min(this.c.f() + 6, this.c.e() - 1); var6 > this.c.f(); var6--) {
/*  95 */       var5 = this.c.a(this.a, var6);
/*  96 */       if (var5.distanceSquared(var0) <= 36.0D)
/*     */       {
/*     */         
/*  99 */         if (a(var0, var5, 0, 0, 0)) {
/* 100 */           this.c.c(var6);
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/* 105 */     a(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(Vec3D var0) {
/* 111 */     if (this.e - this.f > 100) {
/* 112 */       if (var0.distanceSquared(this.g) < 2.25D) {
/* 113 */         o();
/*     */       }
/* 115 */       this.f = this.e;
/* 116 */       this.g = var0;
/*     */     } 
/*     */     
/* 119 */     if (this.c != null && !this.c.c()) {
/* 120 */       BaseBlockPosition var1 = this.c.g();
/*     */       
/* 122 */       if (var1.equals(this.h)) {
/* 123 */         this.i += SystemUtils.getMonotonicMillis() - this.j;
/*     */       } else {
/* 125 */         this.h = var1;
/* 126 */         double var2 = var0.f(Vec3D.a(this.h));
/* 127 */         this.k = (this.a.dM() > 0.0F) ? (var2 / this.a.dM() * 100.0D) : 0.0D;
/*     */       } 
/*     */       
/* 130 */       if (this.k > 0.0D && this.i > this.k * 2.0D) {
/* 131 */         this.h = BaseBlockPosition.ZERO;
/* 132 */         this.i = 0L;
/* 133 */         this.k = 0.0D;
/* 134 */         o();
/*     */       } 
/* 136 */       this.j = SystemUtils.getMonotonicMillis();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(Vec3D var0, Vec3D var1, int var2, int var3, int var4) {
/* 142 */     Vec3D var5 = new Vec3D(var1.x, var1.y + this.a.getHeight() * 0.5D, var1.z); return 
/* 143 */       (this.b.rayTrace(new RayTrace(var0, var5, RayTrace.BlockCollisionOption.COLLIDER, RayTrace.FluidCollisionOption.NONE, this.a)).getType() == MovingObjectPosition.EnumMovingObjectType.MISS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition var0) {
/* 148 */     return !this.b.getType(var0).i(this.b, var0);
/*     */   }
/*     */   
/*     */   public void d(boolean var0) {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NavigationGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */