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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimplexNoiseGenerator
/*     */   extends PerlinNoiseGenerator
/*     */ {
/*  17 */   protected static final double SQRT_3 = Math.sqrt(3.0D);
/*  18 */   protected static final double SQRT_5 = Math.sqrt(5.0D);
/*  19 */   protected static final double F2 = 0.5D * (SQRT_3 - 1.0D);
/*  20 */   protected static final double G2 = (3.0D - SQRT_3) / 6.0D;
/*  21 */   protected static final double G22 = G2 * 2.0D - 1.0D;
/*     */   protected static final double F3 = 0.3333333333333333D;
/*     */   protected static final double G3 = 0.16666666666666666D;
/*  24 */   protected static final double F4 = (SQRT_5 - 1.0D) / 4.0D;
/*  25 */   protected static final double G4 = (5.0D - SQRT_5) / 20.0D;
/*  26 */   protected static final double G42 = G4 * 2.0D;
/*  27 */   protected static final double G43 = G4 * 3.0D;
/*  28 */   protected static final double G44 = G4 * 4.0D - 1.0D;
/*  29 */   protected static final int[][] grad4 = new int[][] { { 0, 1, 1, 1 }, { 0, 1, 1, -1 }, { 0, 1, -1, 1 }, { 0, 1, -1, -1 }, { 0, -1, 1, 1 }, { 0, -1, 1, -1 }, { 0, -1, -1, 1 }, { 0, -1, -1, -1 }, { 1, 0, 1, 1 }, { 1, 0, 1, -1 }, { 1, 0, -1, 1 }, { 1, 0, -1, -1 }, { -1, 0, 1, 1 }, { -1, 0, 1, -1 }, { -1, 0, -1, 1 }, { -1, 0, -1, -1 }, { 1, 1, 0, 1 }, { 1, 1, 0, -1 }, { 1, -1, 0, 1 }, { 1, -1, 0, -1 }, { -1, 1, 0, 1 }, { -1, 1, 0, -1 }, { -1, -1, 0, 1 }, { -1, -1, 0, -1 }, { 1, 1, 1, 0 }, { 1, 1, -1, 0 }, { 1, -1, 1, 0 }, { 1, -1, -1, 0 }, { -1, 1, 1, 0 }, { -1, 1, -1, 0 }, { -1, -1, 1, 0 }, { -1, -1, -1, 0 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   protected static final int[][] simplex = new int[][] { { 0, 1, 2, 3 }, { 0, 1, 3, 2 }, { 0, 0, 0, 0 }, { 0, 2, 3, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 2, 3, 0 }, { 0, 2, 1, 3 }, { 0, 0, 0, 0 }, { 0, 3, 1, 2 }, { 0, 3, 2, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 3, 2, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 2, 0, 3 }, { 0, 0, 0, 0 }, { 1, 3, 0, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 3, 0, 1 }, { 2, 3, 1, 0 }, { 1, 0, 2, 3 }, { 1, 0, 3, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 3, 1 }, { 0, 0, 0, 0 }, { 2, 1, 3, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 1, 3 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 3, 0, 1, 2 }, { 3, 0, 2, 1 }, { 0, 0, 0, 0 }, { 3, 1, 2, 0 }, { 2, 1, 0, 3 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 3, 1, 0, 2 }, { 0, 0, 0, 0 }, { 3, 2, 0, 1 }, { 3, 2, 1, 0 } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double offsetW;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private static final SimplexNoiseGenerator instance = new SimplexNoiseGenerator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SimplexNoiseGenerator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimplexNoiseGenerator(@NotNull World world) {
/*  59 */     this(new Random(world.getSeed()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimplexNoiseGenerator(long seed) {
/*  68 */     this(new Random(seed));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimplexNoiseGenerator(@NotNull Random rand) {
/*  77 */     super(rand);
/*  78 */     this.offsetW = rand.nextDouble() * 256.0D;
/*     */   }
/*     */   
/*     */   protected static double dot(@NotNull int[] g, double x, double y) {
/*  82 */     return g[0] * x + g[1] * y;
/*     */   }
/*     */   
/*     */   protected static double dot(@NotNull int[] g, double x, double y, double z) {
/*  86 */     return g[0] * x + g[1] * y + g[2] * z;
/*     */   }
/*     */   
/*     */   protected static double dot(@NotNull int[] g, double x, double y, double z, double w) {
/*  90 */     return g[0] * x + g[1] * y + g[2] * z + g[3] * w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getNoise(double xin) {
/* 101 */     return instance.noise(xin);
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
/*     */   public static double getNoise(double xin, double yin) {
/* 113 */     return instance.noise(xin, yin);
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
/*     */   public static double getNoise(double xin, double yin, double zin) {
/* 126 */     return instance.noise(xin, yin, zin);
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
/*     */   public static double getNoise(double x, double y, double z, double w) {
/* 140 */     return instance.noise(x, y, z, w);
/*     */   }
/*     */   public double noise(double xin, double yin, double zin) {
/*     */     double n0, n1, n2, n3;
/*     */     int i1, j1, k1, i2, j2, k2;
/* 145 */     xin += this.offsetX;
/* 146 */     yin += this.offsetY;
/* 147 */     zin += this.offsetZ;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     double s = (xin + yin + zin) * 0.3333333333333333D;
/* 153 */     int i = floor(xin + s);
/* 154 */     int j = floor(yin + s);
/* 155 */     int k = floor(zin + s);
/* 156 */     double t = (i + j + k) * 0.16666666666666666D;
/* 157 */     double X0 = i - t;
/* 158 */     double Y0 = j - t;
/* 159 */     double Z0 = k - t;
/* 160 */     double x0 = xin - X0;
/* 161 */     double y0 = yin - Y0;
/* 162 */     double z0 = zin - Z0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     if (x0 >= y0) {
/* 170 */       if (y0 >= z0) {
/* 171 */         i1 = 1;
/* 172 */         j1 = 0;
/* 173 */         k1 = 0;
/* 174 */         i2 = 1;
/* 175 */         j2 = 1;
/* 176 */         k2 = 0;
/*     */       }
/* 178 */       else if (x0 >= z0) {
/* 179 */         i1 = 1;
/* 180 */         j1 = 0;
/* 181 */         k1 = 0;
/* 182 */         i2 = 1;
/* 183 */         j2 = 0;
/* 184 */         k2 = 1;
/*     */       } else {
/*     */         
/* 187 */         i1 = 0;
/* 188 */         j1 = 0;
/* 189 */         k1 = 1;
/* 190 */         i2 = 1;
/* 191 */         j2 = 0;
/* 192 */         k2 = 1;
/*     */       }
/*     */     
/* 195 */     } else if (y0 < z0) {
/* 196 */       i1 = 0;
/* 197 */       j1 = 0;
/* 198 */       k1 = 1;
/* 199 */       i2 = 0;
/* 200 */       j2 = 1;
/* 201 */       k2 = 1;
/*     */     }
/* 203 */     else if (x0 < z0) {
/* 204 */       i1 = 0;
/* 205 */       j1 = 1;
/* 206 */       k1 = 0;
/* 207 */       i2 = 0;
/* 208 */       j2 = 1;
/* 209 */       k2 = 1;
/*     */     } else {
/*     */       
/* 212 */       i1 = 0;
/* 213 */       j1 = 1;
/* 214 */       k1 = 0;
/* 215 */       i2 = 1;
/* 216 */       j2 = 1;
/* 217 */       k2 = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     double x1 = x0 - i1 + 0.16666666666666666D;
/* 226 */     double y1 = y0 - j1 + 0.16666666666666666D;
/* 227 */     double z1 = z0 - k1 + 0.16666666666666666D;
/* 228 */     double x2 = x0 - i2 + 0.3333333333333333D;
/* 229 */     double y2 = y0 - j2 + 0.3333333333333333D;
/* 230 */     double z2 = z0 - k2 + 0.3333333333333333D;
/* 231 */     double x3 = x0 - 1.0D + 0.5D;
/* 232 */     double y3 = y0 - 1.0D + 0.5D;
/* 233 */     double z3 = z0 - 1.0D + 0.5D;
/*     */ 
/*     */     
/* 236 */     int ii = i & 0xFF;
/* 237 */     int jj = j & 0xFF;
/* 238 */     int kk = k & 0xFF;
/* 239 */     int gi0 = this.perm[ii + this.perm[jj + this.perm[kk]]] % 12;
/* 240 */     int gi1 = this.perm[ii + i1 + this.perm[jj + j1 + this.perm[kk + k1]]] % 12;
/* 241 */     int gi2 = this.perm[ii + i2 + this.perm[jj + j2 + this.perm[kk + k2]]] % 12;
/* 242 */     int gi3 = this.perm[ii + 1 + this.perm[jj + 1 + this.perm[kk + 1]]] % 12;
/*     */ 
/*     */     
/* 245 */     double t0 = 0.6D - x0 * x0 - y0 * y0 - z0 * z0;
/* 246 */     if (t0 < 0.0D) {
/* 247 */       n0 = 0.0D;
/*     */     } else {
/* 249 */       t0 *= t0;
/* 250 */       n0 = t0 * t0 * dot(grad3[gi0], x0, y0, z0);
/*     */     } 
/*     */     
/* 253 */     double t1 = 0.6D - x1 * x1 - y1 * y1 - z1 * z1;
/* 254 */     if (t1 < 0.0D) {
/* 255 */       n1 = 0.0D;
/*     */     } else {
/* 257 */       t1 *= t1;
/* 258 */       n1 = t1 * t1 * dot(grad3[gi1], x1, y1, z1);
/*     */     } 
/*     */     
/* 261 */     double t2 = 0.6D - x2 * x2 - y2 * y2 - z2 * z2;
/* 262 */     if (t2 < 0.0D) {
/* 263 */       n2 = 0.0D;
/*     */     } else {
/* 265 */       t2 *= t2;
/* 266 */       n2 = t2 * t2 * dot(grad3[gi2], x2, y2, z2);
/*     */     } 
/*     */     
/* 269 */     double t3 = 0.6D - x3 * x3 - y3 * y3 - z3 * z3;
/* 270 */     if (t3 < 0.0D) {
/* 271 */       n3 = 0.0D;
/*     */     } else {
/* 273 */       t3 *= t3;
/* 274 */       n3 = t3 * t3 * dot(grad3[gi3], x3, y3, z3);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 279 */     return 32.0D * (n0 + n1 + n2 + n3);
/*     */   }
/*     */   public double noise(double xin, double yin) {
/*     */     double n0, n1, n2;
/*     */     int i1, j1;
/* 284 */     xin += this.offsetX;
/* 285 */     yin += this.offsetY;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     double s = (xin + yin) * F2;
/* 291 */     int i = floor(xin + s);
/* 292 */     int j = floor(yin + s);
/* 293 */     double t = (i + j) * G2;
/* 294 */     double X0 = i - t;
/* 295 */     double Y0 = j - t;
/* 296 */     double x0 = xin - X0;
/* 297 */     double y0 = yin - Y0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     if (x0 > y0) {
/* 304 */       i1 = 1;
/* 305 */       j1 = 0;
/*     */     } else {
/*     */       
/* 308 */       i1 = 0;
/* 309 */       j1 = 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     double x1 = x0 - i1 + G2;
/* 317 */     double y1 = y0 - j1 + G2;
/* 318 */     double x2 = x0 + G22;
/* 319 */     double y2 = y0 + G22;
/*     */ 
/*     */     
/* 322 */     int ii = i & 0xFF;
/* 323 */     int jj = j & 0xFF;
/* 324 */     int gi0 = this.perm[ii + this.perm[jj]] % 12;
/* 325 */     int gi1 = this.perm[ii + i1 + this.perm[jj + j1]] % 12;
/* 326 */     int gi2 = this.perm[ii + 1 + this.perm[jj + 1]] % 12;
/*     */ 
/*     */     
/* 329 */     double t0 = 0.5D - x0 * x0 - y0 * y0;
/* 330 */     if (t0 < 0.0D) {
/* 331 */       n0 = 0.0D;
/*     */     } else {
/* 333 */       t0 *= t0;
/* 334 */       n0 = t0 * t0 * dot(grad3[gi0], x0, y0);
/*     */     } 
/*     */     
/* 337 */     double t1 = 0.5D - x1 * x1 - y1 * y1;
/* 338 */     if (t1 < 0.0D) {
/* 339 */       n1 = 0.0D;
/*     */     } else {
/* 341 */       t1 *= t1;
/* 342 */       n1 = t1 * t1 * dot(grad3[gi1], x1, y1);
/*     */     } 
/*     */     
/* 345 */     double t2 = 0.5D - x2 * x2 - y2 * y2;
/* 346 */     if (t2 < 0.0D) {
/* 347 */       n2 = 0.0D;
/*     */     } else {
/* 349 */       t2 *= t2;
/* 350 */       n2 = t2 * t2 * dot(grad3[gi2], x2, y2);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 355 */     return 70.0D * (n0 + n1 + n2);
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
/*     */   public double noise(double x, double y, double z, double w) {
/*     */     double n0, n1, n2, n3, n4;
/* 369 */     x += this.offsetX;
/* 370 */     y += this.offsetY;
/* 371 */     z += this.offsetZ;
/* 372 */     w += this.offsetW;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 377 */     double s = (x + y + z + w) * F4;
/* 378 */     int i = floor(x + s);
/* 379 */     int j = floor(y + s);
/* 380 */     int k = floor(z + s);
/* 381 */     int l = floor(w + s);
/*     */     
/* 383 */     double t = (i + j + k + l) * G4;
/* 384 */     double X0 = i - t;
/* 385 */     double Y0 = j - t;
/* 386 */     double Z0 = k - t;
/* 387 */     double W0 = l - t;
/* 388 */     double x0 = x - X0;
/* 389 */     double y0 = y - Y0;
/* 390 */     double z0 = z - Z0;
/* 391 */     double w0 = w - W0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 401 */     int c1 = (x0 > y0) ? 32 : 0;
/* 402 */     int c2 = (x0 > z0) ? 16 : 0;
/* 403 */     int c3 = (y0 > z0) ? 8 : 0;
/* 404 */     int c4 = (x0 > w0) ? 4 : 0;
/* 405 */     int c5 = (y0 > w0) ? 2 : 0;
/* 406 */     int c6 = (z0 > w0) ? 1 : 0;
/* 407 */     int c = c1 + c2 + c3 + c4 + c5 + c6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 418 */     int i1 = (simplex[c][0] >= 3) ? 1 : 0;
/* 419 */     int j1 = (simplex[c][1] >= 3) ? 1 : 0;
/* 420 */     int k1 = (simplex[c][2] >= 3) ? 1 : 0;
/* 421 */     int l1 = (simplex[c][3] >= 3) ? 1 : 0;
/*     */ 
/*     */     
/* 424 */     int i2 = (simplex[c][0] >= 2) ? 1 : 0;
/* 425 */     int j2 = (simplex[c][1] >= 2) ? 1 : 0;
/* 426 */     int k2 = (simplex[c][2] >= 2) ? 1 : 0;
/* 427 */     int l2 = (simplex[c][3] >= 2) ? 1 : 0;
/*     */ 
/*     */     
/* 430 */     int i3 = (simplex[c][0] >= 1) ? 1 : 0;
/* 431 */     int j3 = (simplex[c][1] >= 1) ? 1 : 0;
/* 432 */     int k3 = (simplex[c][2] >= 1) ? 1 : 0;
/* 433 */     int l3 = (simplex[c][3] >= 1) ? 1 : 0;
/*     */ 
/*     */ 
/*     */     
/* 437 */     double x1 = x0 - i1 + G4;
/* 438 */     double y1 = y0 - j1 + G4;
/* 439 */     double z1 = z0 - k1 + G4;
/* 440 */     double w1 = w0 - l1 + G4;
/*     */     
/* 442 */     double x2 = x0 - i2 + G42;
/* 443 */     double y2 = y0 - j2 + G42;
/* 444 */     double z2 = z0 - k2 + G42;
/* 445 */     double w2 = w0 - l2 + G42;
/*     */     
/* 447 */     double x3 = x0 - i3 + G43;
/* 448 */     double y3 = y0 - j3 + G43;
/* 449 */     double z3 = z0 - k3 + G43;
/* 450 */     double w3 = w0 - l3 + G43;
/*     */     
/* 452 */     double x4 = x0 + G44;
/* 453 */     double y4 = y0 + G44;
/* 454 */     double z4 = z0 + G44;
/* 455 */     double w4 = w0 + G44;
/*     */ 
/*     */     
/* 458 */     int ii = i & 0xFF;
/* 459 */     int jj = j & 0xFF;
/* 460 */     int kk = k & 0xFF;
/* 461 */     int ll = l & 0xFF;
/*     */     
/* 463 */     int gi0 = this.perm[ii + this.perm[jj + this.perm[kk + this.perm[ll]]]] % 32;
/* 464 */     int gi1 = this.perm[ii + i1 + this.perm[jj + j1 + this.perm[kk + k1 + this.perm[ll + l1]]]] % 32;
/* 465 */     int gi2 = this.perm[ii + i2 + this.perm[jj + j2 + this.perm[kk + k2 + this.perm[ll + l2]]]] % 32;
/* 466 */     int gi3 = this.perm[ii + i3 + this.perm[jj + j3 + this.perm[kk + k3 + this.perm[ll + l3]]]] % 32;
/* 467 */     int gi4 = this.perm[ii + 1 + this.perm[jj + 1 + this.perm[kk + 1 + this.perm[ll + 1]]]] % 32;
/*     */ 
/*     */     
/* 470 */     double t0 = 0.6D - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
/* 471 */     if (t0 < 0.0D) {
/* 472 */       n0 = 0.0D;
/*     */     } else {
/* 474 */       t0 *= t0;
/* 475 */       n0 = t0 * t0 * dot(grad4[gi0], x0, y0, z0, w0);
/*     */     } 
/*     */     
/* 478 */     double t1 = 0.6D - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
/* 479 */     if (t1 < 0.0D) {
/* 480 */       n1 = 0.0D;
/*     */     } else {
/* 482 */       t1 *= t1;
/* 483 */       n1 = t1 * t1 * dot(grad4[gi1], x1, y1, z1, w1);
/*     */     } 
/*     */     
/* 486 */     double t2 = 0.6D - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
/* 487 */     if (t2 < 0.0D) {
/* 488 */       n2 = 0.0D;
/*     */     } else {
/* 490 */       t2 *= t2;
/* 491 */       n2 = t2 * t2 * dot(grad4[gi2], x2, y2, z2, w2);
/*     */     } 
/*     */     
/* 494 */     double t3 = 0.6D - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
/* 495 */     if (t3 < 0.0D) {
/* 496 */       n3 = 0.0D;
/*     */     } else {
/* 498 */       t3 *= t3;
/* 499 */       n3 = t3 * t3 * dot(grad4[gi3], x3, y3, z3, w3);
/*     */     } 
/*     */     
/* 502 */     double t4 = 0.6D - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
/* 503 */     if (t4 < 0.0D) {
/* 504 */       n4 = 0.0D;
/*     */     } else {
/* 506 */       t4 *= t4;
/* 507 */       n4 = t4 * t4 * dot(grad4[gi4], x4, y4, z4, w4);
/*     */     } 
/*     */ 
/*     */     
/* 511 */     return 27.0D * (n0 + n1 + n2 + n3 + n4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static SimplexNoiseGenerator getInstance() {
/* 521 */     return instance;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\noise\SimplexNoiseGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */