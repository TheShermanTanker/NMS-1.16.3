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
/*    */ public class WorldGenFeatureKelp
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureKelp(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 23 */     int var5 = 0;
/* 24 */     int var6 = var0.a(HeightMap.Type.OCEAN_FLOOR, var3.getX(), var3.getZ());
/* 25 */     BlockPosition var7 = new BlockPosition(var3.getX(), var6, var3.getZ());
/*    */     
/* 27 */     if (var0.getType(var7).a(Blocks.WATER)) {
/* 28 */       IBlockData var8 = Blocks.KELP.getBlockData();
/* 29 */       IBlockData var9 = Blocks.KELP_PLANT.getBlockData();
/* 30 */       int var10 = 1 + var2.nextInt(10);
/* 31 */       for (int var11 = 0; var11 <= var10; var11++) {
/* 32 */         if (var0.getType(var7).a(Blocks.WATER) && var0.getType(var7.up()).a(Blocks.WATER) && var9.canPlace(var0, var7)) {
/* 33 */           if (var11 == var10) {
/* 34 */             var0.setTypeAndData(var7, var8.set(BlockKelp.d, Integer.valueOf(var2.nextInt(4) + 20)), 2);
/* 35 */             var5++;
/*    */           } else {
/* 37 */             var0.setTypeAndData(var7, var9, 2);
/*    */           } 
/* 39 */         } else if (var11 > 0) {
/* 40 */           BlockPosition var12 = var7.down();
/* 41 */           if (var8.canPlace(var0, var12) && !var0.getType(var12.down()).a(Blocks.KELP)) {
/* 42 */             var0.setTypeAndData(var12, var8.set(BlockKelp.d, Integer.valueOf(var2.nextInt(4) + 20)), 2);
/* 43 */             var5++;
/*    */           } 
/*    */           
/*    */           break;
/*    */         } 
/* 48 */         var7 = var7.up();
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     return (var5 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureKelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */