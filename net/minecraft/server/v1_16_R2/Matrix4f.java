/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Matrix4f
/*     */ {
/*     */   protected float a;
/*     */   protected float b;
/*     */   protected float c;
/*     */   protected float d;
/*     */   protected float e;
/*     */   protected float f;
/*     */   protected float g;
/*     */   protected float h;
/*     */   protected float i;
/*     */   protected float j;
/*     */   protected float k;
/*     */   protected float l;
/*     */   protected float m;
/*     */   protected float n;
/*     */   protected float o;
/*     */   protected float p;
/*     */   
/*     */   public Matrix4f() {}
/*     */   
/*     */   public Matrix4f(Matrix4f var0) {
/*  32 */     this.a = var0.a;
/*  33 */     this.b = var0.b;
/*  34 */     this.c = var0.c;
/*  35 */     this.d = var0.d;
/*     */     
/*  37 */     this.e = var0.e;
/*  38 */     this.f = var0.f;
/*  39 */     this.g = var0.g;
/*  40 */     this.h = var0.h;
/*     */     
/*  42 */     this.i = var0.i;
/*  43 */     this.j = var0.j;
/*  44 */     this.k = var0.k;
/*  45 */     this.l = var0.l;
/*     */     
/*  47 */     this.m = var0.m;
/*  48 */     this.n = var0.n;
/*  49 */     this.o = var0.o;
/*  50 */     this.p = var0.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public Matrix4f(Quaternion var0) {
/*  55 */     float var1 = var0.a();
/*  56 */     float var2 = var0.b();
/*  57 */     float var3 = var0.c();
/*  58 */     float var4 = var0.d();
/*     */     
/*  60 */     float var5 = 2.0F * var1 * var1;
/*  61 */     float var6 = 2.0F * var2 * var2;
/*  62 */     float var7 = 2.0F * var3 * var3;
/*     */     
/*  64 */     this.a = 1.0F - var6 - var7;
/*  65 */     this.f = 1.0F - var7 - var5;
/*  66 */     this.k = 1.0F - var5 - var6;
/*  67 */     this.p = 1.0F;
/*     */     
/*  69 */     float var8 = var1 * var2;
/*  70 */     float var9 = var2 * var3;
/*  71 */     float var10 = var3 * var1;
/*     */     
/*  73 */     float var11 = var1 * var4;
/*  74 */     float var12 = var2 * var4;
/*  75 */     float var13 = var3 * var4;
/*     */     
/*  77 */     this.e = 2.0F * (var8 + var13);
/*  78 */     this.b = 2.0F * (var8 - var13);
/*     */     
/*  80 */     this.i = 2.0F * (var10 - var12);
/*  81 */     this.c = 2.0F * (var10 + var12);
/*     */     
/*  83 */     this.j = 2.0F * (var9 + var11);
/*  84 */     this.g = 2.0F * (var9 - var11);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 112 */     if (this == var0) {
/* 113 */       return true;
/*     */     }
/* 115 */     if (var0 == null || getClass() != var0.getClass()) {
/* 116 */       return false;
/*     */     }
/*     */     
/* 119 */     Matrix4f var1 = (Matrix4f)var0;
/* 120 */     return (Float.compare(var1.a, this.a) == 0 && Float.compare(var1.b, this.b) == 0 && Float.compare(var1.c, this.c) == 0 && Float.compare(var1.d, this.d) == 0 && 
/* 121 */       Float.compare(var1.e, this.e) == 0 && Float.compare(var1.f, this.f) == 0 && Float.compare(var1.g, this.g) == 0 && Float.compare(var1.h, this.h) == 0 && 
/* 122 */       Float.compare(var1.i, this.i) == 0 && Float.compare(var1.j, this.j) == 0 && Float.compare(var1.k, this.k) == 0 && Float.compare(var1.l, this.l) == 0 && 
/* 123 */       Float.compare(var1.m, this.m) == 0 && Float.compare(var1.n, this.n) == 0 && Float.compare(var1.o, this.o) == 0 && Float.compare(var1.p, this.p) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 128 */     int var0 = (this.a != 0.0F) ? Float.floatToIntBits(this.a) : 0;
/* 129 */     var0 = 31 * var0 + ((this.b != 0.0F) ? Float.floatToIntBits(this.b) : 0);
/* 130 */     var0 = 31 * var0 + ((this.c != 0.0F) ? Float.floatToIntBits(this.c) : 0);
/* 131 */     var0 = 31 * var0 + ((this.d != 0.0F) ? Float.floatToIntBits(this.d) : 0);
/* 132 */     var0 = 31 * var0 + ((this.e != 0.0F) ? Float.floatToIntBits(this.e) : 0);
/* 133 */     var0 = 31 * var0 + ((this.f != 0.0F) ? Float.floatToIntBits(this.f) : 0);
/* 134 */     var0 = 31 * var0 + ((this.g != 0.0F) ? Float.floatToIntBits(this.g) : 0);
/* 135 */     var0 = 31 * var0 + ((this.h != 0.0F) ? Float.floatToIntBits(this.h) : 0);
/* 136 */     var0 = 31 * var0 + ((this.i != 0.0F) ? Float.floatToIntBits(this.i) : 0);
/* 137 */     var0 = 31 * var0 + ((this.j != 0.0F) ? Float.floatToIntBits(this.j) : 0);
/* 138 */     var0 = 31 * var0 + ((this.k != 0.0F) ? Float.floatToIntBits(this.k) : 0);
/* 139 */     var0 = 31 * var0 + ((this.l != 0.0F) ? Float.floatToIntBits(this.l) : 0);
/* 140 */     var0 = 31 * var0 + ((this.m != 0.0F) ? Float.floatToIntBits(this.m) : 0);
/* 141 */     var0 = 31 * var0 + ((this.n != 0.0F) ? Float.floatToIntBits(this.n) : 0);
/* 142 */     var0 = 31 * var0 + ((this.o != 0.0F) ? Float.floatToIntBits(this.o) : 0);
/* 143 */     var0 = 31 * var0 + ((this.p != 0.0F) ? Float.floatToIntBits(this.p) : 0);
/* 144 */     return var0;
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
/*     */   public String toString() {
/* 227 */     StringBuilder var0 = new StringBuilder();
/* 228 */     var0.append("Matrix4f:\n");
/* 229 */     var0.append(this.a);
/* 230 */     var0.append(" ");
/* 231 */     var0.append(this.b);
/* 232 */     var0.append(" ");
/* 233 */     var0.append(this.c);
/* 234 */     var0.append(" ");
/* 235 */     var0.append(this.d);
/* 236 */     var0.append("\n");
/*     */     
/* 238 */     var0.append(this.e);
/* 239 */     var0.append(" ");
/* 240 */     var0.append(this.f);
/* 241 */     var0.append(" ");
/* 242 */     var0.append(this.g);
/* 243 */     var0.append(" ");
/* 244 */     var0.append(this.h);
/* 245 */     var0.append("\n");
/*     */     
/* 247 */     var0.append(this.i);
/* 248 */     var0.append(" ");
/* 249 */     var0.append(this.j);
/* 250 */     var0.append(" ");
/* 251 */     var0.append(this.k);
/* 252 */     var0.append(" ");
/* 253 */     var0.append(this.l);
/* 254 */     var0.append("\n");
/*     */     
/* 256 */     var0.append(this.m);
/* 257 */     var0.append(" ");
/* 258 */     var0.append(this.n);
/* 259 */     var0.append(" ");
/* 260 */     var0.append(this.o);
/* 261 */     var0.append(" ");
/* 262 */     var0.append(this.p);
/* 263 */     var0.append("\n");
/* 264 */     return var0.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Matrix4f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */