/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.BitSet;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenCavesOcean
/*     */   extends WorldGenCaves
/*     */ {
/*     */   public WorldGenCavesOcean(Codec<WorldGenFeatureConfigurationChance> var0) {
/*  21 */     super(var0, 256);
/*  22 */     this.j = (Set<Block>)ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, (Object[])new Block[] { Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.SAND, Blocks.GRAVEL, Blocks.WATER, Blocks.LAVA, Blocks.OBSIDIAN, Blocks.AIR, Blocks.CAVE_AIR, Blocks.PACKED_ICE });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(IChunkAccess var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, BitSet var2, Random var3, BlockPosition.MutableBlockPosition var4, BlockPosition.MutableBlockPosition var5, BlockPosition.MutableBlockPosition var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, MutableBoolean var15) {
/*  70 */     return a(this, var0, var2, var3, var4, var7, var8, var9, var10, var11, var12, var13, var14);
/*     */   }
/*     */   
/*     */   protected static boolean a(WorldGenCarverAbstract<?> var0, IChunkAccess var1, BitSet var2, Random var3, BlockPosition.MutableBlockPosition var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
/*  74 */     if (var11 >= var5) {
/*  75 */       return false;
/*     */     }
/*     */     
/*  78 */     int var13 = var10 | var12 << 4 | var11 << 8;
/*  79 */     if (var2.get(var13)) {
/*  80 */       return false;
/*     */     }
/*  82 */     var2.set(var13);
/*     */     
/*  84 */     var4.d(var8, var11, var9);
/*     */     
/*  86 */     IBlockData var14 = var1.getType(var4);
/*  87 */     if (!var0.a(var14)) {
/*  88 */       return false;
/*     */     }
/*     */     
/*  91 */     if (var11 == 10) {
/*  92 */       float f = var3.nextFloat();
/*  93 */       if (f < 0.25D) {
/*  94 */         var1.setType(var4, Blocks.MAGMA_BLOCK.getBlockData(), false);
/*  95 */         var1.n().a(var4, Blocks.MAGMA_BLOCK, 0);
/*     */       } else {
/*  97 */         var1.setType(var4, Blocks.OBSIDIAN.getBlockData(), false);
/*     */       } 
/*  99 */       return true;
/*     */     } 
/*     */     
/* 102 */     if (var11 < 10) {
/* 103 */       var1.setType(var4, Blocks.LAVA.getBlockData(), false);
/* 104 */       return false;
/*     */     } 
/*     */     
/* 107 */     boolean var15 = false;
/* 108 */     for (EnumDirection var17 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 109 */       int var18 = var8 + var17.getAdjacentX();
/* 110 */       int var19 = var9 + var17.getAdjacentZ();
/* 111 */       if (var18 >> 4 != var6 || var19 >> 4 != var7 || var1.getType(var4.d(var18, var11, var19)).isAir()) {
/* 112 */         var1.setType(var4, h.getBlockData(), false);
/* 113 */         var1.o().a(var4, h.getType(), 0);
/* 114 */         var15 = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 119 */     var4.d(var8, var11, var9);
/* 120 */     if (!var15) {
/* 121 */       var1.setType(var4, h.getBlockData(), false);
/* 122 */       return true;
/*     */     } 
/*     */     
/* 125 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCavesOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */