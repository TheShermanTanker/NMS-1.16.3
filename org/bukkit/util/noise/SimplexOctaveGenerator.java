/*     */ package org.bukkit.util.noise;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.World;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class SimplexOctaveGenerator
/*     */   extends OctaveGenerator
/*     */ {
/*  11 */   private double wScale = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimplexOctaveGenerator(@NotNull World world, int octaves) {
/*  20 */     this(new Random(world.getSeed()), octaves);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimplexOctaveGenerator(long seed, int octaves) {
/*  30 */     this(new Random(seed), octaves);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimplexOctaveGenerator(@NotNull Random rand, int octaves) {
/*  40 */     super(createOctaves(rand, octaves));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScale(double scale) {
/*  45 */     super.setScale(scale);
/*  46 */     setWScale(scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getWScale() {
/*  55 */     return this.wScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWScale(double scale) {
/*  64 */     this.wScale = scale;
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
/*     */   public double noise(double x, double y, double z, double w, double frequency, double amplitude) {
/*  80 */     return noise(x, y, z, w, frequency, amplitude, false);
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
/*     */   public double noise(double x, double y, double z, double w, double frequency, double amplitude, boolean normalized) {
/*  97 */     double result = 0.0D;
/*  98 */     double amp = 1.0D;
/*  99 */     double freq = 1.0D;
/* 100 */     double max = 0.0D;
/*     */     
/* 102 */     x *= this.xScale;
/* 103 */     y *= this.yScale;
/* 104 */     z *= this.zScale;
/* 105 */     w *= this.wScale;
/*     */     
/* 107 */     for (NoiseGenerator octave : this.octaves) {
/* 108 */       result += ((SimplexNoiseGenerator)octave).noise(x * freq, y * freq, z * freq, w * freq) * amp;
/* 109 */       max += amp;
/* 110 */       freq *= frequency;
/* 111 */       amp *= amplitude;
/*     */     } 
/*     */     
/* 114 */     if (normalized) {
/* 115 */       result /= max;
/*     */     }
/*     */     
/* 118 */     return result;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static NoiseGenerator[] createOctaves(@NotNull Random rand, int octaves) {
/* 123 */     NoiseGenerator[] result = new NoiseGenerator[octaves];
/*     */     
/* 125 */     for (int i = 0; i < octaves; i++) {
/* 126 */       result[i] = new SimplexNoiseGenerator(rand);
/*     */     }
/*     */     
/* 129 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\noise\SimplexOctaveGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */