/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
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
/*     */ public class WorldGenFeatureHugeFungi
/*     */   extends WorldGenerator<WorldGenFeatureHugeFungiConfiguration>
/*     */ {
/*     */   public WorldGenFeatureHugeFungi(Codec<WorldGenFeatureHugeFungiConfiguration> var0) {
/*  22 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureHugeFungiConfiguration var4) {
/*  27 */     Block var5 = var4.f.getBlock();
/*  28 */     BlockPosition var6 = null;
/*     */     
/*  30 */     Block var7 = var0.getType(var3.down()).getBlock();
/*  31 */     if (var7 == var5) {
/*  32 */       var6 = var3;
/*     */     }
/*     */     
/*  35 */     if (var6 == null) {
/*  36 */       return false;
/*     */     }
/*     */     
/*  39 */     int var8 = MathHelper.nextInt(var2, 4, 13);
/*  40 */     if (var2.nextInt(12) == 0) {
/*  41 */       var8 *= 2;
/*     */     }
/*     */     
/*  44 */     if (!var4.j) {
/*  45 */       int i = var1.getGenerationDepth();
/*  46 */       if (var6.getY() + var8 + 1 >= i) {
/*  47 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  51 */     boolean var9 = (!var4.j && var2.nextFloat() < 0.06F);
/*     */     
/*  53 */     var0.setTypeAndData(var3, Blocks.AIR.getBlockData(), 4);
/*     */     
/*  55 */     a(var0, var2, var4, var6, var8, var9);
/*  56 */     b(var0, var2, var4, var6, var8, var9);
/*     */     
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean a(GeneratorAccess var0, BlockPosition var1, boolean var2) {
/*  62 */     return var0.a(var1, var1 -> {
/*     */           Material var2 = var1.getMaterial();
/*  64 */           return (var1.getMaterial().isReplaceable() || (var0 && var2 == Material.PLANT));
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(GeneratorAccess var0, Random var1, WorldGenFeatureHugeFungiConfiguration var2, BlockPosition var3, int var4, boolean var5) {
/*  70 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/*  71 */     IBlockData var7 = var2.g;
/*  72 */     int var8 = var5 ? 1 : 0;
/*     */     
/*  74 */     for (int var9 = -var8; var9 <= var8; var9++) {
/*  75 */       for (int var10 = -var8; var10 <= var8; var10++) {
/*  76 */         boolean var11 = (var5 && MathHelper.a(var9) == var8 && MathHelper.a(var10) == var8);
/*     */         
/*  78 */         for (int var12 = 0; var12 < var4; var12++) {
/*  79 */           var6.a(var3, var9, var12, var10);
/*  80 */           if (a(var0, var6, true)) {
/*  81 */             if (var2.j) {
/*  82 */               if (!var0.getType(var6.down()).isAir()) {
/*  83 */                 var0.b(var6, true);
/*     */               }
/*     */               
/*  86 */               var0.setTypeAndData(var6, var7, 3);
/*     */             }
/*  88 */             else if (var11) {
/*  89 */               if (var1.nextFloat() < 0.1F) {
/*  90 */                 a(var0, var6, var7);
/*     */               }
/*     */             } else {
/*  93 */               a(var0, var6, var7);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(GeneratorAccess var0, Random var1, WorldGenFeatureHugeFungiConfiguration var2, BlockPosition var3, int var4, boolean var5) {
/* 103 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/* 104 */     boolean var7 = var2.h.a(Blocks.NETHER_WART_BLOCK);
/* 105 */     int var8 = Math.min(var1.nextInt(1 + var4 / 3) + 5, var4);
/* 106 */     int var9 = var4 - var8;
/* 107 */     for (int var10 = var9; var10 <= var4; var10++) {
/* 108 */       int var11 = (var10 < var4 - var1.nextInt(3)) ? 2 : 1;
/* 109 */       if (var8 > 8 && var10 < var9 + 4) {
/* 110 */         var11 = 3;
/*     */       }
/*     */       
/* 113 */       if (var5) {
/* 114 */         var11++;
/*     */       }
/*     */       
/* 117 */       for (int var12 = -var11; var12 <= var11; var12++) {
/* 118 */         for (int var13 = -var11; var13 <= var11; var13++) {
/* 119 */           boolean var14 = (var12 == -var11 || var12 == var11);
/* 120 */           boolean var15 = (var13 == -var11 || var13 == var11);
/* 121 */           boolean var16 = (!var14 && !var15 && var10 != var4);
/* 122 */           boolean var17 = (var14 && var15);
/* 123 */           boolean var18 = (var10 < var9 + 3);
/*     */           
/* 125 */           var6.a(var3, var12, var10, var13);
/* 126 */           if (a(var0, var6, false)) {
/* 127 */             if (var2.j && !var0.getType(var6.down()).isAir()) {
/* 128 */               var0.b(var6, true);
/*     */             }
/*     */             
/* 131 */             if (var18) {
/* 132 */               if (!var16) {
/* 133 */                 a(var0, var1, var6, var2.h, var7);
/*     */               }
/* 135 */             } else if (var16) {
/* 136 */               a(var0, var1, var2, var6, 0.1F, 0.2F, var7 ? 0.1F : 0.0F);
/* 137 */             } else if (var17) {
/* 138 */               a(var0, var1, var2, var6, 0.01F, 0.7F, var7 ? 0.083F : 0.0F);
/*     */             } else {
/* 140 */               a(var0, var1, var2, var6, 5.0E-4F, 0.98F, var7 ? 0.07F : 0.0F);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess var0, Random var1, WorldGenFeatureHugeFungiConfiguration var2, BlockPosition.MutableBlockPosition var3, float var4, float var5, float var6) {
/* 149 */     if (var1.nextFloat() < var4) {
/* 150 */       a(var0, var3, var2.i);
/* 151 */     } else if (var1.nextFloat() < var5) {
/* 152 */       a(var0, var3, var2.h);
/* 153 */       if (var1.nextFloat() < var6) {
/* 154 */         a(var3, var0, var1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess var0, Random var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 160 */     if (var0.getType(var2.down()).a(var3.getBlock())) {
/* 161 */       a(var0, var2, var3);
/* 162 */     } else if (var1.nextFloat() < 0.15D) {
/* 163 */       a(var0, var2, var3);
/* 164 */       if (var4 && var1.nextInt(11) == 0) {
/* 165 */         a(var2, var0, var1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(BlockPosition var0, GeneratorAccess var1, Random var2) {
/* 171 */     BlockPosition.MutableBlockPosition var3 = var0.i().c(EnumDirection.DOWN);
/*     */     
/* 173 */     if (!var1.isEmpty(var3)) {
/*     */       return;
/*     */     }
/*     */     
/* 177 */     int var4 = MathHelper.nextInt(var2, 1, 5);
/* 178 */     if (var2.nextInt(7) == 0) {
/* 179 */       var4 *= 2;
/*     */     }
/*     */     
/* 182 */     int var5 = 23;
/* 183 */     int var6 = 25;
/* 184 */     WorldGenFeatureWeepingVines.a(var1, var2, var3, var4, 23, 25);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureHugeFungi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */