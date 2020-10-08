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
/*     */ public class WorldGenPackedIce2
/*     */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*     */ {
/*     */   public WorldGenPackedIce2(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/*  17 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/*  22 */     while (var0.isEmpty(var3) && var3.getY() > 2) {
/*  23 */       var3 = var3.down();
/*     */     }
/*     */     
/*  26 */     if (!var0.getType(var3).a(Blocks.SNOW_BLOCK)) {
/*  27 */       return false;
/*     */     }
/*  29 */     var3 = var3.up(var2.nextInt(4));
/*     */     
/*  31 */     int var5 = var2.nextInt(4) + 7;
/*  32 */     int var6 = var5 / 4 + var2.nextInt(2);
/*     */     
/*  34 */     if (var6 > 1 && var2.nextInt(60) == 0) {
/*  35 */       var3 = var3.up(10 + var2.nextInt(30));
/*     */     }
/*     */     int var7;
/*  38 */     for (var7 = 0; var7 < var5; var7++) {
/*  39 */       float f = (1.0F - var7 / var5) * var6;
/*  40 */       int var9 = MathHelper.f(f);
/*     */       
/*  42 */       for (int var10 = -var9; var10 <= var9; var10++) {
/*  43 */         float var11 = MathHelper.a(var10) - 0.25F;
/*  44 */         for (int var12 = -var9; var12 <= var9; var12++) {
/*  45 */           float var13 = MathHelper.a(var12) - 0.25F;
/*  46 */           if ((var10 == 0 && var12 == 0) || var11 * var11 + var13 * var13 <= f * f)
/*     */           {
/*     */             
/*  49 */             if ((var10 != -var9 && var10 != var9 && var12 != -var9 && var12 != var9) || 
/*  50 */               var2.nextFloat() <= 0.75F) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  55 */               IBlockData var14 = var0.getType(var3.b(var10, var7, var12));
/*  56 */               Block var15 = var14.getBlock();
/*     */               
/*  58 */               if (var14.isAir() || b(var15) || var15 == Blocks.SNOW_BLOCK || var15 == Blocks.ICE) {
/*  59 */                 a(var0, var3.b(var10, var7, var12), Blocks.PACKED_ICE.getBlockData());
/*     */               }
/*     */               
/*  62 */               if (var7 != 0 && var9 > 1) {
/*  63 */                 var14 = var0.getType(var3.b(var10, -var7, var12));
/*  64 */                 var15 = var14.getBlock();
/*     */                 
/*  66 */                 if (var14.isAir() || b(var15) || var15 == Blocks.SNOW_BLOCK || var15 == Blocks.ICE)
/*  67 */                   a(var0, var3.b(var10, -var7, var12), Blocks.PACKED_ICE.getBlockData()); 
/*     */               } 
/*     */             }  } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  73 */     var7 = var6 - 1;
/*  74 */     if (var7 < 0) {
/*  75 */       var7 = 0;
/*  76 */     } else if (var7 > 1) {
/*  77 */       var7 = 1;
/*     */     } 
/*  79 */     for (int var8 = -var7; var8 <= var7; var8++) {
/*  80 */       for (int var9 = -var7; var9 <= var7; var9++) {
/*  81 */         BlockPosition var10 = var3.b(var8, -1, var9);
/*  82 */         int var11 = 50;
/*  83 */         if (Math.abs(var8) == 1 && Math.abs(var9) == 1) {
/*  84 */           var11 = var2.nextInt(5);
/*     */         }
/*  86 */         while (var10.getY() > 50) {
/*  87 */           IBlockData var12 = var0.getType(var10);
/*  88 */           Block var13 = var12.getBlock();
/*     */           
/*  90 */           if (var12.isAir() || b(var13) || var13 == Blocks.SNOW_BLOCK || var13 == Blocks.ICE || var13 == Blocks.PACKED_ICE) {
/*  91 */             a(var0, var10, Blocks.PACKED_ICE.getBlockData());
/*     */ 
/*     */ 
/*     */             
/*  95 */             var10 = var10.down();
/*  96 */             var11--;
/*  97 */             if (var11 <= 0) {
/*  98 */               var10 = var10.down(var2.nextInt(5) + 1);
/*  99 */               var11 = var2.nextInt(5);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 105 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenPackedIce2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */