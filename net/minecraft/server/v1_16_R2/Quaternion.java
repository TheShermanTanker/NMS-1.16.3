/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Quaternion
/*     */ {
/*   8 */   public static final Quaternion a = new Quaternion(0.0F, 0.0F, 0.0F, 1.0F);
/*     */   
/*     */   private float b;
/*     */   private float c;
/*     */   private float d;
/*     */   private float e;
/*     */   
/*     */   public Quaternion(float var0, float var1, float var2, float var3) {
/*  16 */     this.b = var0;
/*  17 */     this.c = var1;
/*  18 */     this.d = var2;
/*  19 */     this.e = var3;
/*     */   }
/*     */   
/*     */   public Quaternion(Vector3fa var0, float var1, boolean var2) {
/*  23 */     if (var2) {
/*  24 */       var1 *= 0.017453292F;
/*     */     }
/*  26 */     float var3 = c(var1 / 2.0F);
/*  27 */     this.b = var0.a() * var3;
/*  28 */     this.c = var0.b() * var3;
/*  29 */     this.d = var0.c() * var3;
/*  30 */     this.e = b(var1 / 2.0F);
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
/*     */   public Quaternion(Quaternion var0) {
/*  58 */     this.b = var0.b;
/*  59 */     this.c = var0.c;
/*  60 */     this.d = var0.d;
/*  61 */     this.e = var0.e;
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
/*     */   public boolean equals(Object var0) {
/* 144 */     if (this == var0) {
/* 145 */       return true;
/*     */     }
/* 147 */     if (var0 == null || getClass() != var0.getClass()) {
/* 148 */       return false;
/*     */     }
/* 150 */     Quaternion var1 = (Quaternion)var0;
/* 151 */     if (Float.compare(var1.b, this.b) != 0) {
/* 152 */       return false;
/*     */     }
/* 154 */     if (Float.compare(var1.c, this.c) != 0) {
/* 155 */       return false;
/*     */     }
/* 157 */     if (Float.compare(var1.d, this.d) != 0) {
/* 158 */       return false;
/*     */     }
/* 160 */     return (Float.compare(var1.e, this.e) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 165 */     int var0 = Float.floatToIntBits(this.b);
/* 166 */     var0 = 31 * var0 + Float.floatToIntBits(this.c);
/* 167 */     var0 = 31 * var0 + Float.floatToIntBits(this.d);
/* 168 */     var0 = 31 * var0 + Float.floatToIntBits(this.e);
/* 169 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 174 */     StringBuilder var0 = new StringBuilder();
/* 175 */     var0.append("Quaternion[").append(d()).append(" + ");
/* 176 */     var0.append(a()).append("i + ");
/* 177 */     var0.append(b()).append("j + ");
/* 178 */     var0.append(c()).append("k]");
/* 179 */     return var0.toString();
/*     */   }
/*     */   
/*     */   public float a() {
/* 183 */     return this.b;
/*     */   }
/*     */   
/*     */   public float b() {
/* 187 */     return this.c;
/*     */   }
/*     */   
/*     */   public float c() {
/* 191 */     return this.d;
/*     */   }
/*     */   
/*     */   public float d() {
/* 195 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Quaternion var0) {
/* 202 */     float var1 = a();
/* 203 */     float var2 = b();
/* 204 */     float var3 = c();
/* 205 */     float var4 = d();
/*     */     
/* 207 */     float var5 = var0.a();
/* 208 */     float var6 = var0.b();
/* 209 */     float var7 = var0.c();
/* 210 */     float var8 = var0.d();
/*     */     
/* 212 */     this.b = var4 * var5 + var1 * var8 + var2 * var7 - var3 * var6;
/* 213 */     this.c = var4 * var6 - var1 * var7 + var2 * var8 + var3 * var5;
/* 214 */     this.d = var4 * var7 + var1 * var6 - var2 * var5 + var3 * var8;
/* 215 */     this.e = var4 * var8 - var1 * var5 - var2 * var6 - var3 * var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e() {
/* 226 */     this.b = -this.b;
/* 227 */     this.c = -this.c;
/* 228 */     this.d = -this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float b(float var0) {
/* 239 */     return (float)Math.cos(var0);
/*     */   }
/*     */   
/*     */   private static float c(float var0) {
/* 243 */     return (float)Math.sin(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Quaternion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */