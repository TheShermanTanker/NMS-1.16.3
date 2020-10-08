/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.stream.IntStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenSurfaceMesa
/*     */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase>
/*     */ {
/*  21 */   private static final IBlockData K = Blocks.WHITE_TERRACOTTA.getBlockData();
/*  22 */   private static final IBlockData L = Blocks.ORANGE_TERRACOTTA.getBlockData();
/*  23 */   private static final IBlockData M = Blocks.TERRACOTTA.getBlockData();
/*  24 */   private static final IBlockData N = Blocks.YELLOW_TERRACOTTA.getBlockData();
/*  25 */   private static final IBlockData O = Blocks.BROWN_TERRACOTTA.getBlockData();
/*  26 */   private static final IBlockData P = Blocks.RED_TERRACOTTA.getBlockData();
/*  27 */   private static final IBlockData Q = Blocks.LIGHT_GRAY_TERRACOTTA.getBlockData();
/*     */   
/*     */   protected IBlockData[] a;
/*     */   protected long b;
/*     */   protected NoiseGenerator3 c;
/*     */   protected NoiseGenerator3 d;
/*     */   protected NoiseGenerator3 e;
/*     */   
/*     */   public WorldGenSurfaceMesa(Codec<WorldGenSurfaceConfigurationBase> var0) {
/*  36 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/*  41 */     int var14 = var3 & 0xF;
/*  42 */     int var15 = var4 & 0xF;
/*     */     
/*  44 */     IBlockData var16 = K;
/*  45 */     WorldGenSurfaceConfiguration var17 = var2.e().e();
/*  46 */     IBlockData var18 = var17.b();
/*  47 */     IBlockData var19 = var17.a();
/*  48 */     IBlockData var20 = var18;
/*     */     
/*  50 */     int var21 = (int)(var6 / 3.0D + 3.0D + var0.nextDouble() * 0.25D);
/*  51 */     boolean var22 = (Math.cos(var6 / 3.0D * Math.PI) > 0.0D);
/*  52 */     int var23 = -1;
/*  53 */     boolean var24 = false;
/*  54 */     int var25 = 0;
/*     */     
/*  56 */     BlockPosition.MutableBlockPosition var26 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  58 */     for (int var27 = var5; var27 >= 0; var27--) {
/*  59 */       if (var25 < 15) {
/*  60 */         var26.d(var14, var27, var15);
/*  61 */         IBlockData var28 = var1.getType(var26);
/*     */         
/*  63 */         if (var28.isAir()) {
/*  64 */           var23 = -1;
/*  65 */         } else if (var28.a(var8.getBlock())) {
/*  66 */           if (var23 == -1) {
/*  67 */             var24 = false;
/*  68 */             if (var21 <= 0) {
/*  69 */               var16 = Blocks.AIR.getBlockData();
/*  70 */               var20 = var8;
/*  71 */             } else if (var27 >= var10 - 4 && var27 <= var10 + 1) {
/*  72 */               var16 = K;
/*  73 */               var20 = var18;
/*     */             } 
/*     */             
/*  76 */             if (var27 < var10 && (var16 == null || var16.isAir())) {
/*  77 */               var16 = var9;
/*     */             }
/*     */             
/*  80 */             var23 = var21 + Math.max(0, var27 - var10);
/*  81 */             if (var27 >= var10 - 1) {
/*  82 */               if (var27 > var10 + 3 + var21) {
/*     */                 IBlockData var29;
/*  84 */                 if (var27 < 64 || var27 > 127) {
/*  85 */                   var29 = L;
/*  86 */                 } else if (var22) {
/*  87 */                   var29 = M;
/*     */                 } else {
/*  89 */                   var29 = a(var3, var27, var4);
/*     */                 } 
/*  91 */                 var1.setType(var26, var29, false);
/*     */               } else {
/*  93 */                 var1.setType(var26, var19, false);
/*  94 */                 var24 = true;
/*     */               } 
/*     */             } else {
/*  97 */               var1.setType(var26, var20, false);
/*  98 */               Block var29 = var20.getBlock();
/*  99 */               if (var29 == Blocks.WHITE_TERRACOTTA || var29 == Blocks.ORANGE_TERRACOTTA || var29 == Blocks.MAGENTA_TERRACOTTA || var29 == Blocks.LIGHT_BLUE_TERRACOTTA || var29 == Blocks.YELLOW_TERRACOTTA || var29 == Blocks.LIME_TERRACOTTA || var29 == Blocks.PINK_TERRACOTTA || var29 == Blocks.GRAY_TERRACOTTA || var29 == Blocks.LIGHT_GRAY_TERRACOTTA || var29 == Blocks.CYAN_TERRACOTTA || var29 == Blocks.PURPLE_TERRACOTTA || var29 == Blocks.BLUE_TERRACOTTA || var29 == Blocks.BROWN_TERRACOTTA || var29 == Blocks.GREEN_TERRACOTTA || var29 == Blocks.RED_TERRACOTTA || var29 == Blocks.BLACK_TERRACOTTA)
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 116 */                 var1.setType(var26, L, false);
/*     */               }
/*     */             } 
/* 119 */           } else if (var23 > 0) {
/* 120 */             var23--;
/*     */             
/* 122 */             if (var24) {
/* 123 */               var1.setType(var26, L, false);
/*     */             } else {
/* 125 */               var1.setType(var26, a(var3, var27, var4), false);
/*     */             } 
/*     */           } 
/* 128 */           var25++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(long var0) {
/* 137 */     if (this.b != var0 || this.a == null) {
/* 138 */       b(var0);
/*     */     }
/* 140 */     if (this.b != var0 || this.c == null || this.d == null) {
/* 141 */       SeededRandom var2 = new SeededRandom(var0);
/* 142 */       this.c = new NoiseGenerator3(var2, IntStream.rangeClosed(-3, 0));
/* 143 */       this.d = new NoiseGenerator3(var2, (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*     */     } 
/* 145 */     this.b = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(long var0) {
/* 150 */     this.a = new IBlockData[64];
/* 151 */     Arrays.fill((Object[])this.a, M);
/*     */     
/* 153 */     SeededRandom var2 = new SeededRandom(var0);
/* 154 */     this.e = new NoiseGenerator3(var2, (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*     */     int var3;
/* 156 */     for (var3 = 0; var3 < 64; var3++) {
/* 157 */       var3 += var2.nextInt(5) + 1;
/* 158 */       if (var3 < 64) {
/* 159 */         this.a[var3] = L;
/*     */       }
/*     */     } 
/*     */     
/* 163 */     var3 = var2.nextInt(4) + 2; int var4;
/* 164 */     for (var4 = 0; var4 < var3; var4++) {
/* 165 */       int i = var2.nextInt(3) + 1;
/* 166 */       int j = var2.nextInt(64);
/*     */       
/* 168 */       for (int k = 0; j + k < 64 && k < i; k++) {
/* 169 */         this.a[j + k] = N;
/*     */       }
/*     */     } 
/* 172 */     var4 = var2.nextInt(4) + 2; int var5;
/* 173 */     for (var5 = 0; var5 < var4; var5++) {
/* 174 */       int i = var2.nextInt(3) + 2;
/* 175 */       int j = var2.nextInt(64);
/*     */       
/* 177 */       for (int k = 0; j + k < 64 && k < i; k++) {
/* 178 */         this.a[j + k] = O;
/*     */       }
/*     */     } 
/* 181 */     var5 = var2.nextInt(4) + 2; int var6;
/* 182 */     for (var6 = 0; var6 < var5; var6++) {
/* 183 */       int i = var2.nextInt(3) + 1;
/* 184 */       int j = var2.nextInt(64);
/*     */       
/* 186 */       for (int var9 = 0; j + var9 < 64 && var9 < i; var9++) {
/* 187 */         this.a[j + var9] = P;
/*     */       }
/*     */     } 
/* 190 */     var6 = var2.nextInt(3) + 3;
/* 191 */     int var7 = 0;
/* 192 */     for (int var8 = 0; var8 < var6; var8++) {
/* 193 */       int var9 = 1;
/* 194 */       var7 += var2.nextInt(16) + 4;
/*     */       
/* 196 */       for (int var10 = 0; var7 + var10 < 64 && var10 < 1; var10++) {
/* 197 */         this.a[var7 + var10] = K;
/* 198 */         if (var7 + var10 > 1 && var2.nextBoolean()) {
/* 199 */           this.a[var7 + var10 - 1] = Q;
/*     */         }
/* 201 */         if (var7 + var10 < 63 && var2.nextBoolean()) {
/* 202 */           this.a[var7 + var10 + 1] = Q;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected IBlockData a(int var0, int var1, int var2) {
/* 209 */     int var3 = (int)Math.round(this.e.a(var0 / 512.0D, var2 / 512.0D, false) * 2.0D);
/* 210 */     return this.a[(var1 + var3 + 64) % 64];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceMesa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */