/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.BitSet;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenMinable
/*     */   extends WorldGenerator<WorldGenFeatureOreConfiguration>
/*     */ {
/*     */   public WorldGenMinable(Codec<WorldGenFeatureOreConfiguration> var0) {
/*  18 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureOreConfiguration var4) {
/*  23 */     float var5 = var2.nextFloat() * 3.1415927F;
/*     */     
/*  25 */     float var6 = var4.c / 8.0F;
/*  26 */     int var7 = MathHelper.f((var4.c / 16.0F * 2.0F + 1.0F) / 2.0F);
/*  27 */     double var8 = var3.getX() + Math.sin(var5) * var6;
/*  28 */     double var10 = var3.getX() - Math.sin(var5) * var6;
/*  29 */     double var12 = var3.getZ() + Math.cos(var5) * var6;
/*  30 */     double var14 = var3.getZ() - Math.cos(var5) * var6;
/*     */     
/*  32 */     int var16 = 2;
/*  33 */     double var17 = (var3.getY() + var2.nextInt(3) - 2);
/*  34 */     double var19 = (var3.getY() + var2.nextInt(3) - 2);
/*     */     
/*  36 */     int var21 = var3.getX() - MathHelper.f(var6) - var7;
/*  37 */     int var22 = var3.getY() - 2 - var7;
/*  38 */     int var23 = var3.getZ() - MathHelper.f(var6) - var7;
/*  39 */     int var24 = 2 * (MathHelper.f(var6) + var7);
/*  40 */     int var25 = 2 * (2 + var7);
/*     */ 
/*     */     
/*  43 */     for (int var26 = var21; var26 <= var21 + var24; var26++) {
/*  44 */       for (int var27 = var23; var27 <= var23 + var24; var27++) {
/*  45 */         if (var22 <= var0.a(HeightMap.Type.OCEAN_FLOOR_WG, var26, var27)) {
/*  46 */           return a(var0, var2, var4, var8, var10, var12, var14, var17, var19, var21, var22, var23, var24, var25);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  51 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean a(GeneratorAccess var0, Random var1, WorldGenFeatureOreConfiguration var2, double var3, double var5, double var7, double var9, double var11, double var13, int var15, int var16, int var17, int var18, int var19) {
/*  55 */     int var20 = 0;
/*     */     
/*  57 */     BitSet var21 = new BitSet(var18 * var19 * var18);
/*  58 */     BlockPosition.MutableBlockPosition var22 = new BlockPosition.MutableBlockPosition();
/*  59 */     int var23 = var2.c;
/*  60 */     double[] var24 = new double[var23 * 4];
/*     */     int var25;
/*  62 */     for (var25 = 0; var25 < var23; var25++) {
/*  63 */       float var26 = var25 / var23;
/*  64 */       double var27 = MathHelper.d(var26, var3, var5);
/*  65 */       double var29 = MathHelper.d(var26, var11, var13);
/*  66 */       double var31 = MathHelper.d(var26, var7, var9);
/*     */       
/*  68 */       double var33 = var1.nextDouble() * var23 / 16.0D;
/*  69 */       double var35 = ((MathHelper.sin(3.1415927F * var26) + 1.0F) * var33 + 1.0D) / 2.0D;
/*     */       
/*  71 */       var24[var25 * 4 + 0] = var27;
/*  72 */       var24[var25 * 4 + 1] = var29;
/*  73 */       var24[var25 * 4 + 2] = var31;
/*  74 */       var24[var25 * 4 + 3] = var35;
/*     */     } 
/*     */     
/*  77 */     for (var25 = 0; var25 < var23 - 1; var25++) {
/*  78 */       if (var24[var25 * 4 + 3] > 0.0D)
/*     */       {
/*     */ 
/*     */         
/*  82 */         for (int var26 = var25 + 1; var26 < var23; var26++) {
/*  83 */           if (var24[var26 * 4 + 3] > 0.0D) {
/*     */ 
/*     */ 
/*     */             
/*  87 */             double var27 = var24[var25 * 4 + 0] - var24[var26 * 4 + 0];
/*  88 */             double var29 = var24[var25 * 4 + 1] - var24[var26 * 4 + 1];
/*  89 */             double var31 = var24[var25 * 4 + 2] - var24[var26 * 4 + 2];
/*  90 */             double var33 = var24[var25 * 4 + 3] - var24[var26 * 4 + 3];
/*     */             
/*  92 */             if (var33 * var33 > var27 * var27 + var29 * var29 + var31 * var31)
/*  93 */               if (var33 > 0.0D) {
/*  94 */                 var24[var26 * 4 + 3] = -1.0D;
/*     */               } else {
/*  96 */                 var24[var25 * 4 + 3] = -1.0D;
/*     */               }  
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 102 */     for (var25 = 0; var25 < var23; var25++) {
/* 103 */       double var26 = var24[var25 * 4 + 3];
/* 104 */       if (var26 >= 0.0D) {
/*     */ 
/*     */ 
/*     */         
/* 108 */         double var28 = var24[var25 * 4 + 0];
/* 109 */         double var30 = var24[var25 * 4 + 1];
/* 110 */         double var32 = var24[var25 * 4 + 2];
/*     */ 
/*     */         
/* 113 */         int var34 = Math.max(MathHelper.floor(var28 - var26), var15);
/* 114 */         int var35 = Math.max(MathHelper.floor(var30 - var26), var16);
/* 115 */         int var36 = Math.max(MathHelper.floor(var32 - var26), var17);
/*     */         
/* 117 */         int var37 = Math.max(MathHelper.floor(var28 + var26), var34);
/* 118 */         int var38 = Math.max(MathHelper.floor(var30 + var26), var35);
/* 119 */         int var39 = Math.max(MathHelper.floor(var32 + var26), var36);
/*     */         
/* 121 */         for (int var40 = var34; var40 <= var37; var40++) {
/* 122 */           double var41 = (var40 + 0.5D - var28) / var26;
/* 123 */           if (var41 * var41 < 1.0D)
/* 124 */             for (int var43 = var35; var43 <= var38; var43++) {
/* 125 */               double var44 = (var43 + 0.5D - var30) / var26;
/* 126 */               if (var41 * var41 + var44 * var44 < 1.0D) {
/* 127 */                 for (int var46 = var36; var46 <= var39; var46++) {
/* 128 */                   double var47 = (var46 + 0.5D - var32) / var26;
/* 129 */                   if (var41 * var41 + var44 * var44 + var47 * var47 < 1.0D) {
/* 130 */                     int var49 = var40 - var15 + (var43 - var16) * var18 + (var46 - var17) * var18 * var19;
/* 131 */                     if (!var21.get(var49)) {
/*     */ 
/*     */                       
/* 134 */                       var21.set(var49);
/*     */                       
/* 136 */                       var22.d(var40, var43, var46);
/* 137 */                       if (var2.b.a(var0.getType(var22), var1)) {
/* 138 */                         var0.setTypeAndData(var22, var2.d, 2);
/* 139 */                         var20++;
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     } 
/* 149 */     return (var20 > 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMinable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */