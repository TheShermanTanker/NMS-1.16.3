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
/*     */ public final class Matrix3f
/*     */ {
/*  15 */   private static final float j = 3.0F + 2.0F * (float)Math.sqrt(2.0D);
/*  16 */   private static final float k = (float)Math.cos(0.39269908169872414D);
/*  17 */   private static final float l = (float)Math.sin(0.39269908169872414D);
/*  18 */   private static final float m = 1.0F / (float)Math.sqrt(2.0D);
/*     */   
/*     */   protected float a;
/*     */   
/*     */   protected float b;
/*     */   
/*     */   protected float c;
/*     */   
/*     */   protected float d;
/*     */   
/*     */   protected float e;
/*     */   protected float f;
/*     */   protected float g;
/*     */   protected float h;
/*     */   protected float i;
/*     */   
/*     */   public Matrix3f() {}
/*     */   
/*     */   public Matrix3f(Quaternion var0) {
/*  37 */     float var1 = var0.a();
/*  38 */     float var2 = var0.b();
/*  39 */     float var3 = var0.c();
/*  40 */     float var4 = var0.d();
/*     */     
/*  42 */     float var5 = 2.0F * var1 * var1;
/*  43 */     float var6 = 2.0F * var2 * var2;
/*  44 */     float var7 = 2.0F * var3 * var3;
/*     */     
/*  46 */     this.a = 1.0F - var6 - var7;
/*  47 */     this.e = 1.0F - var7 - var5;
/*  48 */     this.i = 1.0F - var5 - var6;
/*     */     
/*  50 */     float var8 = var1 * var2;
/*  51 */     float var9 = var2 * var3;
/*  52 */     float var10 = var3 * var1;
/*     */     
/*  54 */     float var11 = var1 * var4;
/*  55 */     float var12 = var2 * var4;
/*  56 */     float var13 = var3 * var4;
/*     */     
/*  58 */     this.d = 2.0F * (var8 + var13);
/*  59 */     this.b = 2.0F * (var8 - var13);
/*     */     
/*  61 */     this.g = 2.0F * (var10 - var12);
/*  62 */     this.c = 2.0F * (var10 + var12);
/*     */     
/*  64 */     this.h = 2.0F * (var9 + var11);
/*  65 */     this.f = 2.0F * (var9 - var11);
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
/*     */   public Matrix3f(Matrix4f var0) {
/*  77 */     this.a = var0.a;
/*  78 */     this.b = var0.b;
/*  79 */     this.c = var0.c;
/*     */     
/*  81 */     this.d = var0.e;
/*  82 */     this.e = var0.f;
/*  83 */     this.f = var0.g;
/*     */     
/*  85 */     this.g = var0.i;
/*  86 */     this.h = var0.j;
/*  87 */     this.i = var0.k;
/*     */   }
/*     */   
/*     */   public Matrix3f(Matrix3f var0) {
/*  91 */     this.a = var0.a;
/*  92 */     this.b = var0.b;
/*  93 */     this.c = var0.c;
/*     */     
/*  95 */     this.d = var0.d;
/*  96 */     this.e = var0.e;
/*  97 */     this.f = var0.f;
/*     */     
/*  99 */     this.g = var0.g;
/* 100 */     this.h = var0.h;
/* 101 */     this.i = var0.i;
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
/*     */   public boolean equals(Object var0) {
/* 388 */     if (this == var0) {
/* 389 */       return true;
/*     */     }
/* 391 */     if (var0 == null || getClass() != var0.getClass()) {
/* 392 */       return false;
/*     */     }
/*     */     
/* 395 */     Matrix3f var1 = (Matrix3f)var0;
/* 396 */     return (Float.compare(var1.a, this.a) == 0 && Float.compare(var1.b, this.b) == 0 && Float.compare(var1.c, this.c) == 0 && 
/* 397 */       Float.compare(var1.d, this.d) == 0 && Float.compare(var1.e, this.e) == 0 && Float.compare(var1.f, this.f) == 0 && 
/* 398 */       Float.compare(var1.g, this.g) == 0 && Float.compare(var1.h, this.h) == 0 && Float.compare(var1.i, this.i) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 403 */     int var0 = (this.a != 0.0F) ? Float.floatToIntBits(this.a) : 0;
/* 404 */     var0 = 31 * var0 + ((this.b != 0.0F) ? Float.floatToIntBits(this.b) : 0);
/* 405 */     var0 = 31 * var0 + ((this.c != 0.0F) ? Float.floatToIntBits(this.c) : 0);
/* 406 */     var0 = 31 * var0 + ((this.d != 0.0F) ? Float.floatToIntBits(this.d) : 0);
/* 407 */     var0 = 31 * var0 + ((this.e != 0.0F) ? Float.floatToIntBits(this.e) : 0);
/* 408 */     var0 = 31 * var0 + ((this.f != 0.0F) ? Float.floatToIntBits(this.f) : 0);
/* 409 */     var0 = 31 * var0 + ((this.g != 0.0F) ? Float.floatToIntBits(this.g) : 0);
/* 410 */     var0 = 31 * var0 + ((this.h != 0.0F) ? Float.floatToIntBits(this.h) : 0);
/* 411 */     var0 = 31 * var0 + ((this.i != 0.0F) ? Float.floatToIntBits(this.i) : 0);
/* 412 */     return var0;
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
/*     */   public String toString() {
/* 471 */     StringBuilder var0 = new StringBuilder();
/* 472 */     var0.append("Matrix3f:\n");
/* 473 */     var0.append(this.a);
/* 474 */     var0.append(" ");
/* 475 */     var0.append(this.b);
/* 476 */     var0.append(" ");
/* 477 */     var0.append(this.c);
/* 478 */     var0.append("\n");
/*     */     
/* 480 */     var0.append(this.d);
/* 481 */     var0.append(" ");
/* 482 */     var0.append(this.e);
/* 483 */     var0.append(" ");
/* 484 */     var0.append(this.f);
/* 485 */     var0.append("\n");
/*     */     
/* 487 */     var0.append(this.g);
/* 488 */     var0.append(" ");
/* 489 */     var0.append(this.h);
/* 490 */     var0.append(" ");
/* 491 */     var0.append(this.i);
/* 492 */     var0.append("\n");
/* 493 */     return var0.toString();
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
/*     */   public void a(int var0, int var1, float var2) {
/* 600 */     if (var0 == 0) {
/* 601 */       if (var1 == 0) {
/* 602 */         this.a = var2;
/* 603 */       } else if (var1 == 1) {
/* 604 */         this.b = var2;
/*     */       } else {
/* 606 */         this.c = var2;
/*     */       } 
/* 608 */     } else if (var0 == 1) {
/* 609 */       if (var1 == 0) {
/* 610 */         this.d = var2;
/* 611 */       } else if (var1 == 1) {
/* 612 */         this.e = var2;
/*     */       } else {
/* 614 */         this.f = var2;
/*     */       }
/*     */     
/* 617 */     } else if (var1 == 0) {
/* 618 */       this.g = var2;
/* 619 */     } else if (var1 == 1) {
/* 620 */       this.h = var2;
/*     */     } else {
/* 622 */       this.i = var2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(Matrix3f var0) {
/* 628 */     float var1 = this.a * var0.a + this.b * var0.d + this.c * var0.g;
/* 629 */     float var2 = this.a * var0.b + this.b * var0.e + this.c * var0.h;
/* 630 */     float var3 = this.a * var0.c + this.b * var0.f + this.c * var0.i;
/* 631 */     float var4 = this.d * var0.a + this.e * var0.d + this.f * var0.g;
/* 632 */     float var5 = this.d * var0.b + this.e * var0.e + this.f * var0.h;
/* 633 */     float var6 = this.d * var0.c + this.e * var0.f + this.f * var0.i;
/* 634 */     float var7 = this.g * var0.a + this.h * var0.d + this.i * var0.g;
/* 635 */     float var8 = this.g * var0.b + this.h * var0.e + this.i * var0.h;
/* 636 */     float var9 = this.g * var0.c + this.h * var0.f + this.i * var0.i;
/*     */     
/* 638 */     this.a = var1;
/* 639 */     this.b = var2;
/* 640 */     this.c = var3;
/* 641 */     this.d = var4;
/* 642 */     this.e = var5;
/* 643 */     this.f = var6;
/* 644 */     this.g = var7;
/* 645 */     this.h = var8;
/* 646 */     this.i = var9;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Matrix3f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */