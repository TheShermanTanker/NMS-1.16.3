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
/*    */ public class WorldGenFeatureSeaGrass
/*    */   extends WorldGenerator<WorldGenFeatureConfigurationChance>
/*    */ {
/*    */   public WorldGenFeatureSeaGrass(Codec<WorldGenFeatureConfigurationChance> var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureConfigurationChance var4) {
/* 24 */     boolean var5 = false;
/* 25 */     int var6 = var2.nextInt(8) - var2.nextInt(8);
/* 26 */     int var7 = var2.nextInt(8) - var2.nextInt(8);
/* 27 */     int var8 = var0.a(HeightMap.Type.OCEAN_FLOOR, var3.getX() + var6, var3.getZ() + var7);
/* 28 */     BlockPosition var9 = new BlockPosition(var3.getX() + var6, var8, var3.getZ() + var7);
/*    */     
/* 30 */     if (var0.getType(var9).a(Blocks.WATER)) {
/* 31 */       boolean var10 = (var2.nextDouble() < var4.c);
/* 32 */       IBlockData var11 = var10 ? Blocks.TALL_SEAGRASS.getBlockData() : Blocks.SEAGRASS.getBlockData();
/* 33 */       if (var11.canPlace(var0, var9)) {
/* 34 */         if (var10) {
/* 35 */           IBlockData var12 = var11.set(BlockTallSeaGrass.b, BlockPropertyDoubleBlockHalf.UPPER);
/* 36 */           BlockPosition var13 = var9.up();
/* 37 */           if (var0.getType(var13).a(Blocks.WATER)) {
/* 38 */             var0.setTypeAndData(var9, var11, 2);
/* 39 */             var0.setTypeAndData(var13, var12, 2);
/*    */           } 
/*    */         } else {
/* 42 */           var0.setTypeAndData(var9, var11, 2);
/*    */         } 
/* 44 */         var5 = true;
/*    */       } 
/*    */     } 
/* 47 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureSeaGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */