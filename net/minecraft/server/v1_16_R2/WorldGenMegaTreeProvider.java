/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenMegaTreeProvider
/*    */   extends WorldGenTreeProvider
/*    */ {
/*    */   public boolean a(WorldServer worldserver, ChunkGenerator chunkgenerator, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 12 */     for (int i = 0; i >= -1; i--) {
/* 13 */       for (int j = 0; j >= -1; j--) {
/* 14 */         if (a(iblockdata, worldserver, blockposition, i, j)) {
/* 15 */           return a(worldserver, chunkgenerator, blockposition, iblockdata, random, i, j);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 20 */     return super.a(worldserver, chunkgenerator, blockposition, iblockdata, random);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected abstract WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random paramRandom);
/*    */   
/*    */   public boolean a(WorldServer worldserver, ChunkGenerator chunkgenerator, BlockPosition blockposition, IBlockData iblockdata, Random random, int i, int j) {
/* 27 */     WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> worldgenfeatureconfigured = a(random);
/*    */     
/* 29 */     if (worldgenfeatureconfigured == null) {
/* 30 */       return false;
/*    */     }
/* 32 */     ((WorldGenFeatureTreeConfiguration)worldgenfeatureconfigured.f).b();
/* 33 */     setTreeType(worldgenfeatureconfigured);
/* 34 */     IBlockData iblockdata1 = Blocks.AIR.getBlockData();
/*    */     
/* 36 */     worldserver.setTypeAndData(blockposition.b(i, 0, j), iblockdata1, 4);
/* 37 */     worldserver.setTypeAndData(blockposition.b(i + 1, 0, j), iblockdata1, 4);
/* 38 */     worldserver.setTypeAndData(blockposition.b(i, 0, j + 1), iblockdata1, 4);
/* 39 */     worldserver.setTypeAndData(blockposition.b(i + 1, 0, j + 1), iblockdata1, 4);
/* 40 */     if (worldgenfeatureconfigured.a(worldserver, chunkgenerator, random, blockposition.b(i, 0, j))) {
/* 41 */       return true;
/*    */     }
/* 43 */     worldserver.setTypeAndData(blockposition.b(i, 0, j), iblockdata, 4);
/* 44 */     worldserver.setTypeAndData(blockposition.b(i + 1, 0, j), iblockdata, 4);
/* 45 */     worldserver.setTypeAndData(blockposition.b(i, 0, j + 1), iblockdata, 4);
/* 46 */     worldserver.setTypeAndData(blockposition.b(i + 1, 0, j + 1), iblockdata, 4);
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, int i, int j) {
/* 53 */     Block block = iblockdata.getBlock();
/*    */     
/* 55 */     return (block == iblockaccess.getType(blockposition.b(i, 0, j)).getBlock() && block == iblockaccess.getType(blockposition.b(i + 1, 0, j)).getBlock() && block == iblockaccess.getType(blockposition.b(i, 0, j + 1)).getBlock() && block == iblockaccess.getType(blockposition.b(i + 1, 0, j + 1)).getBlock());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMegaTreeProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */