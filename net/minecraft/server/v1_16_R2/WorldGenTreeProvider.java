/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.TreeType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenTreeProvider
/*    */ {
/*    */   @Nullable
/*    */   protected abstract WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random paramRandom, boolean paramBoolean);
/*    */   
/*    */   public boolean a(WorldServer worldserver, ChunkGenerator chunkgenerator, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 16 */     WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> worldgenfeatureconfigured = a(random, a(worldserver, blockposition));
/*    */     
/* 18 */     if (worldgenfeatureconfigured == null) {
/* 19 */       return false;
/*    */     }
/* 21 */     setTreeType(worldgenfeatureconfigured);
/* 22 */     worldserver.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 4);
/* 23 */     ((WorldGenFeatureTreeConfiguration)worldgenfeatureconfigured.f).b();
/* 24 */     if (worldgenfeatureconfigured.a(worldserver, chunkgenerator, random, blockposition)) {
/* 25 */       return true;
/*    */     }
/* 27 */     worldserver.setTypeAndData(blockposition, iblockdata, 4);
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/*    */     BlockPosition blockposition1;
/* 34 */     Iterator<BlockPosition> iterator = BlockPosition.MutableBlockPosition.a(blockposition.down().north(2).west(2), blockposition.up().south(2).east(2)).iterator();
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 39 */       if (!iterator.hasNext()) {
/* 40 */         return false;
/*    */       }
/*    */       
/* 43 */       blockposition1 = iterator.next();
/* 44 */     } while (!generatoraccess.getType(blockposition1).a(TagsBlock.FLOWERS));
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTreeType(WorldGenFeatureConfigured<?, ?> worldgentreeabstract) {
/* 51 */     if (worldgentreeabstract == BiomeDecoratorGroups.OAK || worldgentreeabstract == BiomeDecoratorGroups.OAK_BEES_005) {
/* 52 */       BlockSapling.treeType = TreeType.TREE;
/* 53 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.HUGE_RED_MUSHROOM) {
/* 54 */       BlockSapling.treeType = TreeType.RED_MUSHROOM;
/* 55 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.HUGE_BROWN_MUSHROOM) {
/* 56 */       BlockSapling.treeType = TreeType.BROWN_MUSHROOM;
/* 57 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.JUNGLE_TREE) {
/* 58 */       BlockSapling.treeType = TreeType.COCOA_TREE;
/* 59 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.JUNGLE_TREE_NO_VINE) {
/* 60 */       BlockSapling.treeType = TreeType.SMALL_JUNGLE;
/* 61 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.PINE) {
/* 62 */       BlockSapling.treeType = TreeType.TALL_REDWOOD;
/* 63 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.SPRUCE) {
/* 64 */       BlockSapling.treeType = TreeType.REDWOOD;
/* 65 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.ACACIA) {
/* 66 */       BlockSapling.treeType = TreeType.ACACIA;
/* 67 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.BIRCH || worldgentreeabstract == BiomeDecoratorGroups.BIRCH_BEES_005) {
/* 68 */       BlockSapling.treeType = TreeType.BIRCH;
/* 69 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.SUPER_BIRCH_BEES_0002) {
/* 70 */       BlockSapling.treeType = TreeType.TALL_BIRCH;
/* 71 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.SWAMP_TREE) {
/* 72 */       BlockSapling.treeType = TreeType.SWAMP;
/* 73 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.FANCY_OAK || worldgentreeabstract == BiomeDecoratorGroups.FANCY_OAK_BEES_005) {
/* 74 */       BlockSapling.treeType = TreeType.BIG_TREE;
/* 75 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.JUNGLE_BUSH) {
/* 76 */       BlockSapling.treeType = TreeType.JUNGLE_BUSH;
/* 77 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.DARK_OAK) {
/* 78 */       BlockSapling.treeType = TreeType.DARK_OAK;
/* 79 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.MEGA_SPRUCE) {
/* 80 */       BlockSapling.treeType = TreeType.MEGA_REDWOOD;
/* 81 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.MEGA_PINE) {
/* 82 */       BlockSapling.treeType = TreeType.MEGA_REDWOOD;
/* 83 */     } else if (worldgentreeabstract == BiomeDecoratorGroups.MEGA_JUNGLE_TREE) {
/* 84 */       BlockSapling.treeType = TreeType.JUNGLE;
/*    */     } else {
/* 86 */       throw new IllegalArgumentException("Unknown tree generator " + worldgentreeabstract);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenTreeProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */