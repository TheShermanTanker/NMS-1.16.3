/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureBastionRemnant
/*    */   extends WorldGenFeatureJigsaw
/*    */ {
/*    */   public WorldGenFeatureBastionRemnant(Codec<WorldGenFeatureVillageConfiguration> var0) {
/* 15 */     super(var0, 33, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureVillageConfiguration var9) {
/* 21 */     return (var4.nextInt(5) >= 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionRemnant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */