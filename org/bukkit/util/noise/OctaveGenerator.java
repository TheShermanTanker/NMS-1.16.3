/*     */ package org.bukkit.util.noise;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class OctaveGenerator
/*     */ {
/*     */   @NotNull
/*     */   protected final NoiseGenerator[] octaves;
/*  11 */   protected double xScale = 1.0D;
/*  12 */   protected double yScale = 1.0D;
/*  13 */   protected double zScale = 1.0D;
/*     */   
/*     */   protected OctaveGenerator(@NotNull NoiseGenerator[] octaves) {
/*  16 */     this.octaves = octaves;
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
/*     */   public void setScale(double scale) {
/*  28 */     setXScale(scale);
/*  29 */     setYScale(scale);
/*  30 */     setZScale(scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXScale() {
/*  39 */     return this.xScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXScale(double scale) {
/*  48 */     this.xScale = scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYScale() {
/*  57 */     return this.yScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYScale(double scale) {
/*  66 */     this.yScale = scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZScale() {
/*  75 */     return this.zScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZScale(double scale) {
/*  84 */     this.zScale = scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NoiseGenerator[] getOctaves() {
/*  94 */     return (NoiseGenerator[])this.octaves.clone();
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
/*     */   public double noise(double x, double frequency, double amplitude) {
/* 107 */     return noise(x, 0.0D, 0.0D, frequency, amplitude);
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
/*     */   public double noise(double x, double frequency, double amplitude, boolean normalized) {
/* 121 */     return noise(x, 0.0D, 0.0D, frequency, amplitude, normalized);
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
/*     */   public double noise(double x, double y, double frequency, double amplitude) {
/* 135 */     return noise(x, y, 0.0D, frequency, amplitude);
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
/*     */   public double noise(double x, double y, double frequency, double amplitude, boolean normalized) {
/* 150 */     return noise(x, y, 0.0D, frequency, amplitude, normalized);
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
/*     */   public double noise(double x, double y, double z, double frequency, double amplitude) {
/* 165 */     return noise(x, y, z, frequency, amplitude, false);
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
/*     */   public double noise(double x, double y, double z, double frequency, double amplitude, boolean normalized) {
/* 181 */     double result = 0.0D;
/* 182 */     double amp = 1.0D;
/* 183 */     double freq = 1.0D;
/* 184 */     double max = 0.0D;
/*     */     
/* 186 */     x *= this.xScale;
/* 187 */     y *= this.yScale;
/* 188 */     z *= this.zScale;
/*     */     
/* 190 */     for (NoiseGenerator octave : this.octaves) {
/* 191 */       result += octave.noise(x * freq, y * freq, z * freq) * amp;
/* 192 */       max += amp;
/* 193 */       freq *= frequency;
/* 194 */       amp *= amplitude;
/*     */     } 
/*     */     
/* 197 */     if (normalized) {
/* 198 */       result /= max;
/*     */     }
/*     */     
/* 201 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\noise\OctaveGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */