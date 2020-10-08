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
/*     */ public class WorldGenLakes
/*     */   extends WorldGenerator<WorldGenFeatureLakeConfiguration>
/*     */ {
/*  19 */   private static final IBlockData a = Blocks.CAVE_AIR.getBlockData();
/*     */   
/*     */   public WorldGenLakes(Codec<WorldGenFeatureLakeConfiguration> var0) {
/*  22 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureLakeConfiguration var4) {
/*  27 */     while (var3.getY() > 5 && var0.isEmpty(var3)) {
/*  28 */       var3 = var3.down();
/*     */     }
/*  30 */     if (var3.getY() <= 4) {
/*  31 */       return false;
/*     */     }
/*     */     
/*  34 */     var3 = var3.down(4);
/*     */     
/*  36 */     if (var0.a(SectionPosition.a(var3), StructureGenerator.VILLAGE).findAny().isPresent()) {
/*  37 */       return false;
/*     */     }
/*     */     
/*  40 */     boolean[] var5 = new boolean[2048];
/*     */     
/*  42 */     int var6 = var2.nextInt(4) + 4; int var7;
/*  43 */     for (var7 = 0; var7 < var6; var7++) {
/*  44 */       double var8 = var2.nextDouble() * 6.0D + 3.0D;
/*  45 */       double var10 = var2.nextDouble() * 4.0D + 2.0D;
/*  46 */       double var12 = var2.nextDouble() * 6.0D + 3.0D;
/*     */       
/*  48 */       double var14 = var2.nextDouble() * (16.0D - var8 - 2.0D) + 1.0D + var8 / 2.0D;
/*  49 */       double var16 = var2.nextDouble() * (8.0D - var10 - 4.0D) + 2.0D + var10 / 2.0D;
/*  50 */       double var18 = var2.nextDouble() * (16.0D - var12 - 2.0D) + 1.0D + var12 / 2.0D;
/*     */       
/*  52 */       for (int var20 = 1; var20 < 15; var20++) {
/*  53 */         for (int var21 = 1; var21 < 15; var21++) {
/*  54 */           for (int var22 = 1; var22 < 7; var22++) {
/*  55 */             double var23 = (var20 - var14) / var8 / 2.0D;
/*  56 */             double var25 = (var22 - var16) / var10 / 2.0D;
/*  57 */             double var27 = (var21 - var18) / var12 / 2.0D;
/*  58 */             double var29 = var23 * var23 + var25 * var25 + var27 * var27;
/*  59 */             if (var29 < 1.0D) {
/*  60 */               var5[(var20 * 16 + var21) * 8 + var22] = true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     for (var7 = 0; var7 < 16; var7++) {
/*  68 */       for (int var8 = 0; var8 < 16; var8++) {
/*  69 */         for (int var9 = 0; var9 < 8; var9++) {
/*  70 */           boolean var10 = (!var5[(var7 * 16 + var8) * 8 + var9] && ((var7 < 15 && var5[((var7 + 1) * 16 + var8) * 8 + var9]) || (var7 > 0 && var5[((var7 - 1) * 16 + var8) * 8 + var9]) || (var8 < 15 && var5[(var7 * 16 + var8 + 1) * 8 + var9]) || (var8 > 0 && var5[(var7 * 16 + var8 - 1) * 8 + var9]) || (var9 < 7 && var5[(var7 * 16 + var8) * 8 + var9 + 1]) || (var9 > 0 && var5[(var7 * 16 + var8) * 8 + var9 - 1])));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  79 */           if (var10) {
/*  80 */             Material var11 = var0.getType(var3.b(var7, var9, var8)).getMaterial();
/*  81 */             if (var9 >= 4 && var11.isLiquid()) {
/*  82 */               return false;
/*     */             }
/*  84 */             if (var9 < 4 && !var11.isBuildable() && var0.getType(var3.b(var7, var9, var8)) != var4.b) {
/*  85 */               return false;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     for (var7 = 0; var7 < 16; var7++) {
/*  93 */       for (int var8 = 0; var8 < 16; var8++) {
/*  94 */         for (int var9 = 0; var9 < 8; var9++) {
/*  95 */           if (var5[(var7 * 16 + var8) * 8 + var9]) {
/*  96 */             var0.setTypeAndData(var3.b(var7, var9, var8), (var9 >= 4) ? a : var4.b, 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     for (var7 = 0; var7 < 16; var7++) {
/* 103 */       for (int var8 = 0; var8 < 16; var8++) {
/* 104 */         for (int var9 = 4; var9 < 8; var9++) {
/* 105 */           if (var5[(var7 * 16 + var8) * 8 + var9]) {
/* 106 */             BlockPosition var10 = var3.b(var7, var9 - 1, var8);
/*     */             
/* 108 */             if (b(var0.getType(var10).getBlock()) && var0.getBrightness(EnumSkyBlock.SKY, var3.b(var7, var9, var8)) > 0) {
/* 109 */               BiomeBase var11 = var0.getBiome(var10);
/* 110 */               if (var11.e().e().a().a(Blocks.MYCELIUM)) {
/* 111 */                 var0.setTypeAndData(var10, Blocks.MYCELIUM.getBlockData(), 2);
/*     */               } else {
/* 113 */                 var0.setTypeAndData(var10, Blocks.GRASS_BLOCK.getBlockData(), 2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     if (var4.b.getMaterial() == Material.LAVA) {
/* 122 */       for (var7 = 0; var7 < 16; var7++) {
/* 123 */         for (int var8 = 0; var8 < 16; var8++) {
/* 124 */           for (int var9 = 0; var9 < 8; var9++) {
/* 125 */             boolean var10 = (!var5[(var7 * 16 + var8) * 8 + var9] && ((var7 < 15 && var5[((var7 + 1) * 16 + var8) * 8 + var9]) || (var7 > 0 && var5[((var7 - 1) * 16 + var8) * 8 + var9]) || (var8 < 15 && var5[(var7 * 16 + var8 + 1) * 8 + var9]) || (var8 > 0 && var5[(var7 * 16 + var8 - 1) * 8 + var9]) || (var9 < 7 && var5[(var7 * 16 + var8) * 8 + var9 + 1]) || (var9 > 0 && var5[(var7 * 16 + var8) * 8 + var9 - 1])));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 134 */             if (var10 && (
/* 135 */               var9 < 4 || var2.nextInt(2) != 0) && var0.getType(var3.b(var7, var9, var8)).getMaterial().isBuildable()) {
/* 136 */               var0.setTypeAndData(var3.b(var7, var9, var8), Blocks.STONE.getBlockData(), 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 144 */     if (var4.b.getMaterial() == Material.WATER) {
/* 145 */       for (var7 = 0; var7 < 16; var7++) {
/* 146 */         for (int var8 = 0; var8 < 16; var8++) {
/* 147 */           int var9 = 4;
/* 148 */           BlockPosition var10 = var3.b(var7, 4, var8);
/* 149 */           if (var0.getBiome(var10).a(var0, var10, false)) {
/* 150 */             var0.setTypeAndData(var10, Blocks.ICE.getBlockData(), 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 156 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenLakes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */