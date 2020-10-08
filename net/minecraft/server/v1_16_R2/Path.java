/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class Path {
/*   4 */   private PathPoint[] a = new PathPoint[128];
/*     */   
/*     */   private int b;
/*     */   
/*     */   public PathPoint a(PathPoint var0) {
/*   9 */     if (var0.d >= 0) {
/*  10 */       throw new IllegalStateException("OW KNOWS!");
/*     */     }
/*     */     
/*  13 */     if (this.b == this.a.length) {
/*  14 */       PathPoint[] var1 = new PathPoint[this.b << 1];
/*  15 */       System.arraycopy(this.a, 0, var1, 0, this.b);
/*  16 */       this.a = var1;
/*     */     } 
/*     */ 
/*     */     
/*  20 */     this.a[this.b] = var0;
/*  21 */     var0.d = this.b;
/*  22 */     a(this.b++);
/*     */     
/*  24 */     return var0;
/*     */   }
/*     */   
/*     */   public void a() {
/*  28 */     this.b = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint c() {
/*  36 */     PathPoint var0 = this.a[0];
/*  37 */     this.a[0] = this.a[--this.b];
/*  38 */     this.a[this.b] = null;
/*  39 */     if (this.b > 0) {
/*  40 */       b(0);
/*     */     }
/*  42 */     var0.d = -1;
/*  43 */     return var0;
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
/*     */   public void a(PathPoint var0, float var1) {
/*  62 */     float var2 = var0.g;
/*  63 */     var0.g = var1;
/*  64 */     if (var1 < var2) {
/*  65 */       a(var0.d);
/*     */     } else {
/*  67 */       b(var0.d);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(int var0) {
/*  76 */     PathPoint var1 = this.a[var0];
/*  77 */     float var2 = var1.g;
/*  78 */     while (var0 > 0) {
/*  79 */       int var3 = var0 - 1 >> 1;
/*  80 */       PathPoint var4 = this.a[var3];
/*  81 */       if (var2 < var4.g) {
/*  82 */         this.a[var0] = var4;
/*  83 */         var4.d = var0;
/*  84 */         var0 = var3;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  89 */     this.a[var0] = var1;
/*  90 */     var1.d = var0;
/*     */   }
/*     */   
/*     */   private void b(int var0) {
/*  94 */     PathPoint var1 = this.a[var0];
/*  95 */     float var2 = var1.g; while (true) {
/*     */       PathPoint var7;
/*     */       float var8;
/*  98 */       int var3 = 1 + (var0 << 1);
/*  99 */       int var4 = var3 + 1;
/*     */       
/* 101 */       if (var3 >= this.b) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 106 */       PathPoint var5 = this.a[var3];
/* 107 */       float var6 = var5.g;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       if (var4 >= this.b) {
/*     */         
/* 114 */         var7 = null;
/* 115 */         var8 = Float.POSITIVE_INFINITY;
/*     */       } else {
/* 117 */         var7 = this.a[var4];
/* 118 */         var8 = var7.g;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (var6 < var8) {
/* 124 */         if (var6 < var2) {
/* 125 */           this.a[var0] = var5;
/* 126 */           var5.d = var0;
/* 127 */           var0 = var3;
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 132 */       if (var8 < var2) {
/* 133 */         this.a[var0] = var7;
/* 134 */         var7.d = var0;
/* 135 */         var0 = var4;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*     */       break;
/*     */     } 
/* 142 */     this.a[var0] = var1;
/* 143 */     var1.d = var0;
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 147 */     return (this.b == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Path.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */