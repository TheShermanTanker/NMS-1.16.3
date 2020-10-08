/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.BitSet;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCanyonOcean
/*    */   extends WorldGenCanyon
/*    */ {
/*    */   public WorldGenCanyonOcean(Codec<WorldGenFeatureConfigurationChance> var0) {
/* 18 */     super(var0);
/* 19 */     this.j = (Set<Block>)ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, (Object[])new Block[] { Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.SAND, Blocks.GRAVEL, Blocks.WATER, Blocks.LAVA, Blocks.OBSIDIAN, Blocks.AIR, Blocks.CAVE_AIR });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(IChunkAccess var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, BitSet var2, Random var3, BlockPosition.MutableBlockPosition var4, BlockPosition.MutableBlockPosition var5, BlockPosition.MutableBlockPosition var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, MutableBoolean var15) {
/* 66 */     return WorldGenCavesOcean.a(this, var0, var2, var3, var4, var7, var8, var9, var10, var11, var12, var13, var14);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCanyonOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */