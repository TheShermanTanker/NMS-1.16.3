/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenFeatureBasaltColumns
/*     */   extends WorldGenerator<WorldGenFeatureBasaltColumnsConfiguration>
/*     */ {
/*  19 */   private static final ImmutableList<Block> a = ImmutableList.of(Blocks.LAVA, Blocks.BEDROCK, Blocks.MAGMA_BLOCK, Blocks.SOUL_SAND, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_WART, Blocks.CHEST, Blocks.SPAWNER);
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
/*     */   public WorldGenFeatureBasaltColumns(Codec<WorldGenFeatureBasaltColumnsConfiguration> var0) {
/*  33 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureBasaltColumnsConfiguration var4) {
/*  38 */     int var5 = var1.getSeaLevel();
/*  39 */     if (!a(var0, var5, var3.i())) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     int var6 = var4.b().a(var2);
/*     */     
/*  45 */     boolean var7 = (var2.nextFloat() < 0.9F);
/*  46 */     int var8 = Math.min(var6, var7 ? 5 : 8);
/*  47 */     int var9 = var7 ? 50 : 15;
/*     */ 
/*     */     
/*  50 */     boolean var10 = false;
/*  51 */     for (BlockPosition var12 : BlockPosition.a(var2, var9, var3.getX() - var8, var3.getY(), var3.getZ() - var8, var3.getX() + var8, var3.getY(), var3.getZ() + var8)) {
/*  52 */       int var13 = var6 - var12.k(var3);
/*  53 */       if (var13 >= 0) {
/*  54 */         var10 |= a(var0, var5, var12, var13, var4.am_().a(var2));
/*     */       }
/*     */     } 
/*     */     
/*  58 */     return var10;
/*     */   }
/*     */   
/*     */   private boolean a(GeneratorAccess var0, int var1, BlockPosition var2, int var3, int var4) {
/*  62 */     boolean var5 = false;
/*     */     
/*  64 */     for (BlockPosition var7 : BlockPosition.b(var2.getX() - var4, var2.getY(), var2.getZ() - var4, var2.getX() + var4, var2.getY(), var2.getZ() + var4)) {
/*  65 */       int var8 = var7.k(var2);
/*     */ 
/*     */ 
/*     */       
/*  69 */       BlockPosition var9 = a(var0, var1, var7) ? a(var0, var1, var7.i(), var8) : a(var0, var7.i(), var8);
/*  70 */       if (var9 == null) {
/*     */         continue;
/*     */       }
/*     */       
/*  74 */       int var10 = var3 - var8 / 2;
/*  75 */       BlockPosition.MutableBlockPosition var11 = var9.i();
/*  76 */       while (var10 >= 0) {
/*  77 */         if (a(var0, var1, var11)) {
/*  78 */           a(var0, var11, Blocks.BASALT.getBlockData());
/*  79 */           var11.c(EnumDirection.UP);
/*  80 */           var5 = true;
/*  81 */         } else if (var0.getType(var11).a(Blocks.BASALT)) {
/*  82 */           var11.c(EnumDirection.UP);
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */         
/*  87 */         var10--;
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     return var5;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static BlockPosition a(GeneratorAccess var0, int var1, BlockPosition.MutableBlockPosition var2, int var3) {
/*  96 */     while (var2.getY() > 1 && var3 > 0) {
/*  97 */       var3--;
/*  98 */       if (a(var0, var1, var2)) {
/*  99 */         return var2;
/*     */       }
/* 101 */       var2.c(EnumDirection.DOWN);
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean a(GeneratorAccess var0, int var1, BlockPosition.MutableBlockPosition var2) {
/* 107 */     if (a(var0, var1, var2)) {
/* 108 */       IBlockData var3 = var0.getType(var2.c(EnumDirection.DOWN));
/* 109 */       var2.c(EnumDirection.UP);
/* 110 */       return (!var3.isAir() && !a.contains(var3.getBlock()));
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static BlockPosition a(GeneratorAccess var0, BlockPosition.MutableBlockPosition var1, int var2) {
/* 117 */     while (var1.getY() < var0.getBuildHeight() && var2 > 0) {
/* 118 */       var2--;
/*     */       
/* 120 */       IBlockData var3 = var0.getType(var1);
/* 121 */       if (a.contains(var3.getBlock())) {
/* 122 */         return null;
/*     */       }
/*     */       
/* 125 */       if (var3.isAir()) {
/* 126 */         return var1;
/*     */       }
/*     */       
/* 129 */       var1.c(EnumDirection.UP);
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean a(GeneratorAccess var0, int var1, BlockPosition var2) {
/* 135 */     IBlockData var3 = var0.getType(var2);
/* 136 */     return (var3.isAir() || (var3.a(Blocks.LAVA) && var2.getY() <= var1));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBasaltColumns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */