/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.BitSet;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenCaves
/*     */   extends WorldGenCarverAbstract<WorldGenFeatureConfigurationChance>
/*     */ {
/*     */   public WorldGenCaves(Codec<WorldGenFeatureConfigurationChance> var0, int var1) {
/*  16 */     super(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Random var0, int var1, int var2, WorldGenFeatureConfigurationChance var3) {
/*  21 */     return (var0.nextFloat() <= var3.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, Random var2, int var3, int var4, int var5, int var6, int var7, BitSet var8, WorldGenFeatureConfigurationChance var9) {
/*  26 */     int var10 = (d() * 2 - 1) * 16;
/*  27 */     int var11 = var2.nextInt(var2.nextInt(var2.nextInt(a()) + 1) + 1);
/*     */     
/*  29 */     for (int var12 = 0; var12 < var11; var12++) {
/*     */       
/*  31 */       double var13 = (var4 * 16 + var2.nextInt(16));
/*  32 */       double var15 = b(var2);
/*  33 */       double var17 = (var5 * 16 + var2.nextInt(16));
/*     */       
/*  35 */       int var19 = 1;
/*  36 */       if (var2.nextInt(4) == 0) {
/*     */         
/*  38 */         double d = 0.5D;
/*  39 */         float var22 = 1.0F + var2.nextFloat() * 6.0F;
/*  40 */         a(var0, var1, var2.nextLong(), var3, var6, var7, var13, var15, var17, var22, 0.5D, var8);
/*  41 */         var19 += var2.nextInt(4);
/*     */       } 
/*     */       
/*  44 */       for (int var20 = 0; var20 < var19; var20++) {
/*     */         
/*  46 */         float var21 = var2.nextFloat() * 6.2831855F;
/*  47 */         float var22 = (var2.nextFloat() - 0.5F) / 4.0F;
/*  48 */         float var23 = a(var2);
/*  49 */         int var24 = var10 - var2.nextInt(var10 / 4);
/*  50 */         int var25 = 0;
/*  51 */         a(var0, var1, var2.nextLong(), var3, var6, var7, var13, var15, var17, var23, var21, var22, 0, var24, b(), var8);
/*     */       } 
/*     */     } 
/*     */     
/*  55 */     return true;
/*     */   }
/*     */   
/*     */   protected int a() {
/*  59 */     return 15;
/*     */   }
/*     */   
/*     */   protected float a(Random var0) {
/*  63 */     float var1 = var0.nextFloat() * 2.0F + var0.nextFloat();
/*  64 */     if (var0.nextInt(10) == 0) {
/*  65 */       var1 *= var0.nextFloat() * var0.nextFloat() * 3.0F + 1.0F;
/*     */     }
/*  67 */     return var1;
/*     */   }
/*     */   
/*     */   protected double b() {
/*  71 */     return 1.0D;
/*     */   }
/*     */   
/*     */   protected int b(Random var0) {
/*  75 */     return var0.nextInt(var0.nextInt(120) + 8);
/*     */   }
/*     */   
/*     */   protected void a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, long var2, int var4, int var5, int var6, double var7, double var9, double var11, float var13, double var14, BitSet var16) {
/*  79 */     double var17 = 1.5D + (MathHelper.sin(1.5707964F) * var13);
/*  80 */     double var19 = var17 * var14;
/*     */     
/*  82 */     a(var0, var1, var2, var4, var5, var6, var7 + 1.0D, var9, var11, var17, var19, var16);
/*     */   }
/*     */   
/*     */   protected void a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, long var2, int var4, int var5, int var6, double var7, double var9, double var11, float var13, float var14, float var15, int var16, int var17, double var18, BitSet var20) {
/*  86 */     Random var21 = new Random(var2);
/*     */     
/*  88 */     int var22 = var21.nextInt(var17 / 2) + var17 / 4;
/*  89 */     boolean var23 = (var21.nextInt(6) == 0);
/*     */     
/*  91 */     float var24 = 0.0F;
/*  92 */     float var25 = 0.0F;
/*     */     
/*  94 */     for (int var26 = var16; var26 < var17; var26++) {
/*  95 */       double var27 = 1.5D + (MathHelper.sin(3.1415927F * var26 / var17) * var13);
/*  96 */       double var29 = var27 * var18;
/*     */       
/*  98 */       float var31 = MathHelper.cos(var15);
/*  99 */       var7 += (MathHelper.cos(var14) * var31);
/* 100 */       var9 += MathHelper.sin(var15);
/* 101 */       var11 += (MathHelper.sin(var14) * var31);
/*     */       
/* 103 */       var15 *= var23 ? 0.92F : 0.7F;
/* 104 */       var15 += var25 * 0.1F;
/* 105 */       var14 += var24 * 0.1F;
/*     */       
/* 107 */       var25 *= 0.9F;
/* 108 */       var24 *= 0.75F;
/* 109 */       var25 += (var21.nextFloat() - var21.nextFloat()) * var21.nextFloat() * 2.0F;
/* 110 */       var24 += (var21.nextFloat() - var21.nextFloat()) * var21.nextFloat() * 4.0F;
/*     */ 
/*     */       
/* 113 */       if (var26 == var22 && var13 > 1.0F) {
/* 114 */         a(var0, var1, var21.nextLong(), var4, var5, var6, var7, var9, var11, var21.nextFloat() * 0.5F + 0.5F, var14 - 1.5707964F, var15 / 3.0F, var26, var17, 1.0D, var20);
/* 115 */         a(var0, var1, var21.nextLong(), var4, var5, var6, var7, var9, var11, var21.nextFloat() * 0.5F + 0.5F, var14 + 1.5707964F, var15 / 3.0F, var26, var17, 1.0D, var20);
/*     */         
/*     */         return;
/*     */       } 
/* 119 */       if (var21.nextInt(4) != 0) {
/*     */ 
/*     */ 
/*     */         
/* 123 */         if (!a(var5, var6, var7, var11, var26, var17, var13)) {
/*     */           return;
/*     */         }
/* 126 */         a(var0, var1, var2, var4, var5, var6, var7, var9, var11, var27, var29, var20);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean a(double var0, double var2, double var4, int var6) {
/* 132 */     return (var2 <= -0.7D || var0 * var0 + var2 * var2 + var4 * var4 >= 1.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */