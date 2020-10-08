/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Codec;
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
/*     */ public class WorldGenSurfaceFrozenOcean
/*     */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase>
/*     */ {
/*  19 */   protected static final IBlockData a = Blocks.PACKED_ICE.getBlockData();
/*  20 */   protected static final IBlockData b = Blocks.SNOW_BLOCK.getBlockData();
/*  21 */   private static final IBlockData c = Blocks.AIR.getBlockData();
/*  22 */   private static final IBlockData d = Blocks.GRAVEL.getBlockData();
/*  23 */   private static final IBlockData e = Blocks.ICE.getBlockData();
/*     */   
/*     */   private NoiseGenerator3 K;
/*     */   private NoiseGenerator3 L;
/*     */   private long M;
/*     */   
/*     */   public WorldGenSurfaceFrozenOcean(Codec<WorldGenSurfaceConfigurationBase> var0) {
/*  30 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11, WorldGenSurfaceConfigurationBase var13) {
/*  35 */     double var14 = 0.0D;
/*  36 */     double var16 = 0.0D;
/*  37 */     BlockPosition.MutableBlockPosition var18 = new BlockPosition.MutableBlockPosition();
/*  38 */     float var19 = var2.getAdjustedTemperature(var18.d(var3, 63, var4));
/*     */     
/*  40 */     double var20 = Math.min(Math.abs(var6), this.K.a(var3 * 0.1D, var4 * 0.1D, false) * 15.0D);
/*     */     
/*  42 */     if (var20 > 1.8D) {
/*  43 */       double d1 = 0.09765625D;
/*  44 */       double d2 = Math.abs(this.L.a(var3 * 0.09765625D, var4 * 0.09765625D, false));
/*  45 */       var14 = var20 * var20 * 1.2D;
/*  46 */       double d3 = Math.ceil(d2 * 40.0D) + 14.0D;
/*  47 */       if (var14 > d3) {
/*  48 */         var14 = d3;
/*     */       }
/*     */       
/*  51 */       if (var19 > 0.1F) {
/*  52 */         var14 -= 2.0D;
/*     */       }
/*     */       
/*  55 */       if (var14 > 2.0D) {
/*  56 */         var16 = var10 - var14 - 7.0D;
/*  57 */         var14 += var10;
/*     */       } else {
/*  59 */         var14 = 0.0D;
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     int var22 = var3 & 0xF;
/*  64 */     int var23 = var4 & 0xF;
/*     */     
/*  66 */     WorldGenSurfaceConfiguration var24 = var2.e().e();
/*  67 */     IBlockData var25 = var24.b();
/*  68 */     IBlockData var26 = var24.a();
/*  69 */     IBlockData var27 = var25;
/*  70 */     IBlockData var28 = var26;
/*     */     
/*  72 */     int var29 = (int)(var6 / 3.0D + 3.0D + var0.nextDouble() * 0.25D);
/*  73 */     int var30 = -1;
/*  74 */     int var31 = 0;
/*  75 */     int var32 = 2 + var0.nextInt(4);
/*  76 */     int var33 = var10 + 18 + var0.nextInt(10);
/*     */     
/*  78 */     for (int var34 = Math.max(var5, (int)var14 + 1); var34 >= 0; var34--) {
/*  79 */       var18.d(var22, var34, var23);
/*     */       
/*  81 */       if (var1.getType(var18).isAir() && var34 < (int)var14 && var0.nextDouble() > 0.01D) {
/*  82 */         var1.setType(var18, a, false);
/*  83 */       } else if (var1.getType(var18).getMaterial() == Material.WATER && var34 > (int)var16 && var34 < var10 && var16 != 0.0D && var0.nextDouble() > 0.15D) {
/*  84 */         var1.setType(var18, a, false);
/*     */       } 
/*     */       
/*  87 */       IBlockData var35 = var1.getType(var18);
/*  88 */       if (var35.isAir()) {
/*  89 */         var30 = -1;
/*     */ 
/*     */       
/*     */       }
/*  93 */       else if (var35.a(var8.getBlock())) {
/*  94 */         if (var30 == -1) {
/*  95 */           if (var29 <= 0) {
/*  96 */             var28 = c;
/*  97 */             var27 = var8;
/*  98 */           } else if (var34 >= var10 - 4 && var34 <= var10 + 1) {
/*  99 */             var28 = var26;
/* 100 */             var27 = var25;
/*     */           } 
/*     */           
/* 103 */           if (var34 < var10 && (var28 == null || var28.isAir())) {
/* 104 */             if (var2.getAdjustedTemperature(var18.d(var3, var34, var4)) < 0.15F) {
/* 105 */               var28 = e;
/*     */             } else {
/* 107 */               var28 = var9;
/*     */             } 
/*     */           }
/*     */           
/* 111 */           var30 = var29;
/* 112 */           if (var34 >= var10 - 1) {
/* 113 */             var1.setType(var18, var28, false);
/* 114 */           } else if (var34 < var10 - 7 - var29) {
/* 115 */             var28 = c;
/* 116 */             var27 = var8;
/* 117 */             var1.setType(var18, d, false);
/*     */           } else {
/* 119 */             var1.setType(var18, var27, false);
/*     */           } 
/* 121 */         } else if (var30 > 0) {
/* 122 */           var30--;
/* 123 */           var1.setType(var18, var27, false);
/* 124 */           if (var30 == 0 && var27.a(Blocks.SAND) && var29 > 1) {
/* 125 */             var30 = var0.nextInt(4) + Math.max(0, var34 - 63);
/* 126 */             var27 = var27.a(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.getBlockData() : Blocks.SANDSTONE.getBlockData();
/*     */           } 
/*     */         } 
/* 129 */       } else if (var35.a(Blocks.PACKED_ICE) && 
/* 130 */         var31 <= var32 && var34 > var33) {
/* 131 */         var1.setType(var18, b, false);
/* 132 */         var31++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(long var0) {
/* 140 */     if (this.M != var0 || this.K == null || this.L == null) {
/* 141 */       SeededRandom var2 = new SeededRandom(var0);
/* 142 */       this.K = new NoiseGenerator3(var2, IntStream.rangeClosed(-3, 0));
/* 143 */       this.L = new NoiseGenerator3(var2, (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*     */     } 
/* 145 */     this.M = var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceFrozenOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */