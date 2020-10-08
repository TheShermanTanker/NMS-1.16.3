/*     */ package org.bukkit.util.noise;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.World;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PerlinNoiseGenerator
/*     */   extends NoiseGenerator
/*     */ {
/*  14 */   protected static final int[][] grad3 = new int[][] { { 1, 1, 0 }, { -1, 1, 0 }, { 1, -1, 0 }, { -1, -1, 0 }, { 1, 0, 1 }, { -1, 0, 1 }, { 1, 0, -1 }, { -1, 0, -1 }, { 0, 1, 1 }, { 0, -1, 1 }, { 0, 1, -1 }, { 0, -1, -1 } };
/*     */ 
/*     */   
/*  17 */   private static final PerlinNoiseGenerator instance = new PerlinNoiseGenerator();
/*     */   
/*     */   protected PerlinNoiseGenerator() {
/*  20 */     int[] p = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     for (int i = 0; i < 512; i++) {
/*  40 */       this.perm[i] = p[i & 0xFF];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PerlinNoiseGenerator(@NotNull World world) {
/*  50 */     this(new Random(world.getSeed()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PerlinNoiseGenerator(long seed) {
/*  59 */     this(new Random(seed));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PerlinNoiseGenerator(@NotNull Random rand) {
/*  68 */     this.offsetX = rand.nextDouble() * 256.0D;
/*  69 */     this.offsetY = rand.nextDouble() * 256.0D;
/*  70 */     this.offsetZ = rand.nextDouble() * 256.0D;
/*     */     int i;
/*  72 */     for (i = 0; i < 256; i++) {
/*  73 */       this.perm[i] = rand.nextInt(256);
/*     */     }
/*     */     
/*  76 */     for (i = 0; i < 256; i++) {
/*  77 */       int pos = rand.nextInt(256 - i) + i;
/*  78 */       int old = this.perm[i];
/*     */       
/*  80 */       this.perm[i] = this.perm[pos];
/*  81 */       this.perm[pos] = old;
/*  82 */       this.perm[i + 256] = this.perm[i];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getNoise(double x) {
/*  94 */     return instance.noise(x);
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
/*     */   public static double getNoise(double x, double y) {
/* 106 */     return instance.noise(x, y);
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
/*     */   public static double getNoise(double x, double y, double z) {
/* 119 */     return instance.noise(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static PerlinNoiseGenerator getInstance() {
/* 129 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public double noise(double x, double y, double z) {
/* 134 */     x += this.offsetX;
/* 135 */     y += this.offsetY;
/* 136 */     z += this.offsetZ;
/*     */     
/* 138 */     int floorX = floor(x);
/* 139 */     int floorY = floor(y);
/* 140 */     int floorZ = floor(z);
/*     */ 
/*     */     
/* 143 */     int X = floorX & 0xFF;
/* 144 */     int Y = floorY & 0xFF;
/* 145 */     int Z = floorZ & 0xFF;
/*     */ 
/*     */     
/* 148 */     x -= floorX;
/* 149 */     y -= floorY;
/* 150 */     z -= floorZ;
/*     */ 
/*     */     
/* 153 */     double fX = fade(x);
/* 154 */     double fY = fade(y);
/* 155 */     double fZ = fade(z);
/*     */ 
/*     */     
/* 158 */     int A = this.perm[X] + Y;
/* 159 */     int AA = this.perm[A] + Z;
/* 160 */     int AB = this.perm[A + 1] + Z;
/* 161 */     int B = this.perm[X + 1] + Y;
/* 162 */     int BA = this.perm[B] + Z;
/* 163 */     int BB = this.perm[B + 1] + Z;
/*     */     
/* 165 */     return lerp(fZ, lerp(fY, lerp(fX, grad(this.perm[AA], x, y, z), 
/* 166 */             grad(this.perm[BA], x - 1.0D, y, z)), 
/* 167 */           lerp(fX, grad(this.perm[AB], x, y - 1.0D, z), 
/* 168 */             grad(this.perm[BB], x - 1.0D, y - 1.0D, z))), 
/* 169 */         lerp(fY, lerp(fX, grad(this.perm[AA + 1], x, y, z - 1.0D), 
/* 170 */             grad(this.perm[BA + 1], x - 1.0D, y, z - 1.0D)), 
/* 171 */           lerp(fX, grad(this.perm[AB + 1], x, y - 1.0D, z - 1.0D), 
/* 172 */             grad(this.perm[BB + 1], x - 1.0D, y - 1.0D, z - 1.0D))));
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
/*     */   public static double getNoise(double x, int octaves, double frequency, double amplitude) {
/* 186 */     return instance.noise(x, octaves, frequency, amplitude);
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
/*     */   public static double getNoise(double x, double y, int octaves, double frequency, double amplitude) {
/* 201 */     return instance.noise(x, y, octaves, frequency, amplitude);
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
/*     */   public static double getNoise(double x, double y, double z, int octaves, double frequency, double amplitude) {
/* 217 */     return instance.noise(x, y, z, octaves, frequency, amplitude);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\noise\PerlinNoiseGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */