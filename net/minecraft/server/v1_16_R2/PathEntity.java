/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PathEntity {
/*     */   private final List<PathPoint> a;
/*     */   
/*     */   public List<PathPoint> getPoints() {
/*   8 */     return this.a;
/*   9 */   } private PathPoint[] b = new PathPoint[0];
/*  10 */   private PathPoint[] c = new PathPoint[0]; private int e; private final BlockPosition f; public int getNextIndex() {
/*  11 */     return this.e;
/*     */   }
/*     */   private final float g; private final boolean h;
/*     */   public boolean hasNext() {
/*  15 */     return (getNextIndex() < getPoints().size());
/*     */   }
/*     */   public PathEntity(List<PathPoint> list, BlockPosition blockposition, boolean flag) {
/*  18 */     this.a = list;
/*  19 */     this.f = blockposition;
/*  20 */     this.g = list.isEmpty() ? Float.MAX_VALUE : ((PathPoint)this.a.get(this.a.size() - 1)).c(this.f);
/*  21 */     this.h = flag;
/*     */   }
/*     */   
/*     */   public void a() {
/*  25 */     this.e++;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  29 */     return (this.e <= 0);
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  33 */     return (this.e >= this.a.size());
/*     */   }
/*     */   
/*     */   @Nullable
/*  37 */   public PathPoint getFinalPoint() { return d(); } @Nullable
/*  38 */   public PathPoint d() { return !this.a.isEmpty() ? this.a.get(this.a.size() - 1) : null; }
/*     */ 
/*     */   
/*     */   public PathPoint a(int i) {
/*  42 */     return this.a.get(i);
/*     */   }
/*     */   
/*     */   public void b(int i) {
/*  46 */     if (this.a.size() > i) {
/*  47 */       this.a.subList(i, this.a.size()).clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, PathPoint pathpoint) {
/*  53 */     this.a.set(i, pathpoint);
/*     */   }
/*     */   
/*     */   public int e() {
/*  57 */     return this.a.size();
/*     */   }
/*     */   
/*     */   public int f() {
/*  61 */     return this.e;
/*     */   }
/*     */   
/*     */   public void c(int i) {
/*  65 */     this.e = i;
/*     */   }
/*     */   
/*     */   public Vec3D a(Entity entity, int i) {
/*  69 */     PathPoint pathpoint = this.a.get(i);
/*  70 */     double d0 = pathpoint.a + (int)(entity.getWidth() + 1.0F) * 0.5D;
/*  71 */     double d1 = pathpoint.b;
/*  72 */     double d2 = pathpoint.c + (int)(entity.getWidth() + 1.0F) * 0.5D;
/*     */     
/*  74 */     return new Vec3D(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public BlockPosition d(int i) {
/*  78 */     return ((PathPoint)this.a.get(i)).a();
/*     */   }
/*     */   
/*     */   public Vec3D a(Entity entity) {
/*  82 */     return a(entity, this.e);
/*     */   }
/*     */   
/*  85 */   public BlockPosition getNext() { return g(); } public BlockPosition g() {
/*  86 */     return ((PathPoint)this.a.get(this.e)).a();
/*     */   }
/*     */   
/*     */   public PathPoint h() {
/*  90 */     return this.a.get(this.e);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PathPoint i() {
/*  95 */     return (this.e > 0) ? this.a.get(this.e - 1) : null;
/*     */   }
/*     */   
/*     */   public boolean a(@Nullable PathEntity pathentity) {
/*  99 */     if (pathentity == null)
/* 100 */       return false; 
/* 101 */     if (pathentity.a.size() != this.a.size()) {
/* 102 */       return false;
/*     */     }
/* 104 */     for (int i = 0; i < this.a.size(); i++) {
/* 105 */       PathPoint pathpoint = this.a.get(i);
/* 106 */       PathPoint pathpoint1 = pathentity.a.get(i);
/*     */       
/* 108 */       if (pathpoint.a != pathpoint1.a || pathpoint.b != pathpoint1.b || pathpoint.c != pathpoint1.c) {
/* 109 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean j() {
/* 118 */     return this.h;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 122 */     return "Path(length=" + this.a.size() + ")";
/*     */   }
/*     */   
/*     */   public BlockPosition m() {
/* 126 */     return this.f;
/*     */   }
/*     */   
/*     */   public float n() {
/* 130 */     return this.g;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */