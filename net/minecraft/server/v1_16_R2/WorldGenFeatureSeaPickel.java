/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureSeaPickel
/*    */   extends WorldGenerator<WorldGenDecoratorFrequencyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureSeaPickel(Codec<WorldGenDecoratorFrequencyConfiguration> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenDecoratorFrequencyConfiguration var4) {
/* 23 */     int var5 = 0;
/* 24 */     int var6 = var4.a().a(var2);
/* 25 */     for (int var7 = 0; var7 < var6; var7++) {
/* 26 */       int var8 = var2.nextInt(8) - var2.nextInt(8);
/* 27 */       int var9 = var2.nextInt(8) - var2.nextInt(8);
/* 28 */       int var10 = var0.a(HeightMap.Type.OCEAN_FLOOR, var3.getX() + var8, var3.getZ() + var9);
/* 29 */       BlockPosition var11 = new BlockPosition(var3.getX() + var8, var10, var3.getZ() + var9);
/*    */       
/* 31 */       IBlockData var12 = Blocks.SEA_PICKLE.getBlockData().set(BlockSeaPickle.a, Integer.valueOf(var2.nextInt(4) + 1));
/* 32 */       if (var0.getType(var11).a(Blocks.WATER) && var12.canPlace(var0, var11)) {
/* 33 */         var0.setTypeAndData(var11, var12, 2);
/* 34 */         var5++;
/*    */       } 
/*    */     } 
/* 37 */     return (var5 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureSeaPickel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */