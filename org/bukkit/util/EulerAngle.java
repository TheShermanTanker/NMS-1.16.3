/*     */ package org.bukkit.util;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EulerAngle
/*     */ {
/*  14 */   public static final EulerAngle ZERO = new EulerAngle(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */   
/*     */   private final double x;
/*     */ 
/*     */   
/*     */   private final double y;
/*     */ 
/*     */   
/*     */   private final double z;
/*     */ 
/*     */ 
/*     */   
/*     */   public EulerAngle(double x, double y, double z) {
/*  29 */     this.x = x;
/*  30 */     this.y = y;
/*  31 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getX() {
/*  40 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getY() {
/*  49 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZ() {
/*  58 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EulerAngle setX(double x) {
/*  70 */     return new EulerAngle(x, this.y, this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EulerAngle setY(double y) {
/*  82 */     return new EulerAngle(this.x, y, this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EulerAngle setZ(double z) {
/*  94 */     return new EulerAngle(this.x, this.y, z);
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
/*     */   @NotNull
/*     */   public EulerAngle add(double x, double y, double z) {
/* 108 */     return new EulerAngle(this.x + x, this.y + y, this.z + z);
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
/*     */   @NotNull
/*     */   public EulerAngle subtract(double x, double y, double z) {
/* 126 */     return add(-x, -y, -z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 131 */     if (this == o) return true; 
/* 132 */     if (o == null || getClass() != o.getClass()) return false;
/*     */     
/* 134 */     EulerAngle that = (EulerAngle)o;
/*     */     
/* 136 */     return (Double.compare(that.x, this.x) == 0 && 
/* 137 */       Double.compare(that.y, this.y) == 0 && 
/* 138 */       Double.compare(that.z, this.z) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 146 */     long temp = Double.doubleToLongBits(this.x);
/* 147 */     int result = (int)(temp ^ temp >>> 32L);
/* 148 */     temp = Double.doubleToLongBits(this.y);
/* 149 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 150 */     temp = Double.doubleToLongBits(this.z);
/* 151 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 152 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\EulerAngle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */