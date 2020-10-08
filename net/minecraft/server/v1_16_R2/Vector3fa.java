/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Vector3fa
/*     */ {
/*   8 */   public static Vector3fa a = new Vector3fa(-1.0F, 0.0F, 0.0F);
/*   9 */   public static Vector3fa b = new Vector3fa(1.0F, 0.0F, 0.0F);
/*  10 */   public static Vector3fa c = new Vector3fa(0.0F, -1.0F, 0.0F);
/*  11 */   public static Vector3fa d = new Vector3fa(0.0F, 1.0F, 0.0F);
/*  12 */   public static Vector3fa e = new Vector3fa(0.0F, 0.0F, -1.0F);
/*  13 */   public static Vector3fa f = new Vector3fa(0.0F, 0.0F, 1.0F);
/*     */   
/*     */   private float g;
/*     */   
/*     */   private float h;
/*     */   private float i;
/*     */   
/*     */   public Vector3fa() {}
/*     */   
/*     */   public Vector3fa(float var0, float var1, float var2) {
/*  23 */     this.g = var0;
/*  24 */     this.h = var1;
/*  25 */     this.i = var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector3fa(Vec3D var0) {
/*  33 */     this((float)var0.x, (float)var0.y, (float)var0.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  38 */     if (this == var0) {
/*  39 */       return true;
/*     */     }
/*  41 */     if (var0 == null || getClass() != var0.getClass()) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     Vector3fa var1 = (Vector3fa)var0;
/*  46 */     if (Float.compare(var1.g, this.g) != 0) {
/*  47 */       return false;
/*     */     }
/*  49 */     if (Float.compare(var1.h, this.h) != 0) {
/*  50 */       return false;
/*     */     }
/*  52 */     return (Float.compare(var1.i, this.i) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  57 */     int var0 = Float.floatToIntBits(this.g);
/*  58 */     var0 = 31 * var0 + Float.floatToIntBits(this.h);
/*  59 */     var0 = 31 * var0 + Float.floatToIntBits(this.i);
/*  60 */     return var0;
/*     */   }
/*     */   
/*     */   public float a() {
/*  64 */     return this.g;
/*     */   }
/*     */   
/*     */   public float b() {
/*  68 */     return this.h;
/*     */   }
/*     */   
/*     */   public float c() {
/*  72 */     return this.i;
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
/*     */   public void a(float var0, float var1, float var2) {
/* 100 */     this.g = var0;
/* 101 */     this.h = var1;
/* 102 */     this.i = var2;
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
/*     */   public void a(Quaternion var0) {
/* 180 */     Quaternion var1 = new Quaternion(var0);
/* 181 */     var1.a(new Quaternion(a(), b(), c(), 0.0F));
/* 182 */     Quaternion var2 = new Quaternion(var0);
/*     */     
/* 184 */     var2.e();
/* 185 */     var1.a(var2);
/* 186 */     a(var1.a(), var1.b(), var1.c());
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
/*     */   public String toString() {
/* 216 */     return "[" + this.g + ", " + this.h + ", " + this.i + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Vector3fa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */