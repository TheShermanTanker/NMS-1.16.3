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
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureIceSnow
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureIceSnow(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 25 */     BlockPosition.MutableBlockPosition var5 = new BlockPosition.MutableBlockPosition();
/* 26 */     BlockPosition.MutableBlockPosition var6 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 28 */     for (int var7 = 0; var7 < 16; var7++) {
/* 29 */       for (int var8 = 0; var8 < 16; var8++) {
/* 30 */         int var9 = var3.getX() + var7;
/* 31 */         int var10 = var3.getZ() + var8;
/* 32 */         int var11 = var0.a(HeightMap.Type.MOTION_BLOCKING, var9, var10);
/*    */         
/* 34 */         var5.d(var9, var11, var10);
/* 35 */         var6.g(var5).c(EnumDirection.DOWN, 1);
/*    */         
/* 37 */         BiomeBase var12 = var0.getBiome(var5);
/*    */         
/* 39 */         if (var12.a(var0, var6, false)) {
/* 40 */           var0.setTypeAndData(var6, Blocks.ICE.getBlockData(), 2);
/*    */         }
/* 42 */         if (var12.b(var0, var5)) {
/* 43 */           var0.setTypeAndData(var5, Blocks.SNOW.getBlockData(), 2);
/*    */           
/* 45 */           IBlockData var13 = var0.getType(var6);
/* 46 */           if (var13.b(BlockDirtSnow.a)) {
/* 47 */             var0.setTypeAndData(var6, var13.set(BlockDirtSnow.a, Boolean.valueOf(true)), 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureIceSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */