/*     */ package net.minecraft.server.v1_16_R2;
/*     */ public class PathPoint { public final int a;
/*     */   public final int b;
/*     */   
/*   5 */   public final int getX() { return this.a; } public final int c; private final int m; public final int getY() {
/*   6 */     return this.b; } public final int getZ() {
/*   7 */     return this.c;
/*     */   }
/*   9 */   public int d = -1;
/*     */   public float e;
/*     */   public float f;
/*     */   public float g;
/*     */   public PathPoint h;
/*     */   public boolean i;
/*     */   public float j;
/*     */   public float k;
/*     */   public PathType l;
/*     */   
/*     */   public PathPoint(int i, int j, int k) {
/*  20 */     this.l = PathType.BLOCKED;
/*  21 */     this.a = i;
/*  22 */     this.b = j;
/*  23 */     this.c = k;
/*  24 */     this.m = b(i, j, k);
/*     */   }
/*     */   
/*     */   public PathPoint a(int i, int j, int k) {
/*  28 */     PathPoint pathpoint = new PathPoint(i, j, k);
/*     */     
/*  30 */     pathpoint.d = this.d;
/*  31 */     pathpoint.e = this.e;
/*  32 */     pathpoint.f = this.f;
/*  33 */     pathpoint.g = this.g;
/*  34 */     pathpoint.h = this.h;
/*  35 */     pathpoint.i = this.i;
/*  36 */     pathpoint.j = this.j;
/*  37 */     pathpoint.k = this.k;
/*  38 */     pathpoint.l = this.l;
/*  39 */     return pathpoint;
/*     */   }
/*     */   
/*     */   public static int b(int i, int j, int k) {
/*  43 */     return j & 0xFF | (i & 0x7FFF) << 8 | (k & 0x7FFF) << 24 | ((i < 0) ? Integer.MIN_VALUE : 0) | ((k < 0) ? 32768 : 0);
/*     */   }
/*     */   
/*     */   public float a(PathPoint pathpoint) {
/*  47 */     float f = (pathpoint.a - this.a);
/*  48 */     float f1 = (pathpoint.b - this.b);
/*  49 */     float f2 = (pathpoint.c - this.c);
/*     */     
/*  51 */     return MathHelper.c(f * f + f1 * f1 + f2 * f2);
/*     */   }
/*     */   
/*     */   public float b(PathPoint pathpoint) {
/*  55 */     float f = (pathpoint.a - this.a);
/*  56 */     float f1 = (pathpoint.b - this.b);
/*  57 */     float f2 = (pathpoint.c - this.c);
/*     */     
/*  59 */     return f * f + f1 * f1 + f2 * f2;
/*     */   }
/*     */   
/*     */   public float c(PathPoint pathpoint) {
/*  63 */     float f = Math.abs(pathpoint.a - this.a);
/*  64 */     float f1 = Math.abs(pathpoint.b - this.b);
/*  65 */     float f2 = Math.abs(pathpoint.c - this.c);
/*     */     
/*  67 */     return f + f1 + f2;
/*     */   }
/*     */   
/*     */   public float c(BlockPosition blockposition) {
/*  71 */     float f = Math.abs(blockposition.getX() - this.a);
/*  72 */     float f1 = Math.abs(blockposition.getY() - this.b);
/*  73 */     float f2 = Math.abs(blockposition.getZ() - this.c);
/*     */     
/*  75 */     return f + f1 + f2;
/*     */   }
/*     */   
/*     */   public BlockPosition a() {
/*  79 */     return new BlockPosition(this.a, this.b, this.c);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  83 */     if (!(object instanceof PathPoint)) {
/*  84 */       return false;
/*     */     }
/*  86 */     PathPoint pathpoint = (PathPoint)object;
/*     */     
/*  88 */     return (this.m == pathpoint.m && this.a == pathpoint.a && this.b == pathpoint.b && this.c == pathpoint.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  93 */     return this.m;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  97 */     return (this.d >= 0);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 101 */     return "Node{x=" + this.a + ", y=" + this.b + ", z=" + this.c + '}';
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */