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
/*    */ public class WorldGenFeatureBamboo
/*    */   extends WorldGenerator<WorldGenFeatureConfigurationChance>
/*    */ {
/* 19 */   private static final IBlockData a = Blocks.BAMBOO.getBlockData().set(BlockBamboo.d, Integer.valueOf(1)).set(BlockBamboo.e, BlockPropertyBambooSize.NONE).set(BlockBamboo.f, Integer.valueOf(0));
/* 20 */   private static final IBlockData ab = a.set(BlockBamboo.e, BlockPropertyBambooSize.LARGE).set(BlockBamboo.f, Integer.valueOf(1));
/* 21 */   private static final IBlockData ac = a.set(BlockBamboo.e, BlockPropertyBambooSize.LARGE);
/* 22 */   private static final IBlockData ad = a.set(BlockBamboo.e, BlockPropertyBambooSize.SMALL);
/*    */   
/*    */   public WorldGenFeatureBamboo(Codec<WorldGenFeatureConfigurationChance> var0) {
/* 25 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureConfigurationChance var4) {
/* 30 */     int var5 = 0;
/*    */     
/* 32 */     BlockPosition.MutableBlockPosition var6 = var3.i();
/* 33 */     BlockPosition.MutableBlockPosition var7 = var3.i();
/* 34 */     if (var0.isEmpty(var6)) {
/* 35 */       if (Blocks.BAMBOO.getBlockData().canPlace(var0, var6)) {
/* 36 */         int var8 = var2.nextInt(12) + 5;
/*    */ 
/*    */         
/* 39 */         if (var2.nextFloat() < var4.c) {
/* 40 */           int i = var2.nextInt(4) + 1;
/* 41 */           for (int var10 = var3.getX() - i; var10 <= var3.getX() + i; var10++) {
/* 42 */             for (int var11 = var3.getZ() - i; var11 <= var3.getZ() + i; var11++) {
/* 43 */               int var12 = var10 - var3.getX();
/* 44 */               int var13 = var11 - var3.getZ();
/* 45 */               if (var12 * var12 + var13 * var13 <= i * i) {
/*    */ 
/*    */ 
/*    */                 
/* 49 */                 var7.d(var10, var0.a(HeightMap.Type.WORLD_SURFACE, var10, var11) - 1, var11);
/* 50 */                 if (b(var0.getType(var7).getBlock())) {
/* 51 */                   var0.setTypeAndData(var7, Blocks.PODZOL.getBlockData(), 2);
/*    */                 }
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/* 57 */         for (int var9 = 0; var9 < var8 && 
/* 58 */           var0.isEmpty(var6); var9++) {
/* 59 */           var0.setTypeAndData(var6, a, 2);
/*    */ 
/*    */ 
/*    */           
/* 63 */           var6.c(EnumDirection.UP, 1);
/*    */         } 
/*    */         
/* 66 */         if (var6.getY() - var3.getY() >= 3) {
/* 67 */           var0.setTypeAndData(var6, ab, 2);
/* 68 */           var0.setTypeAndData(var6.c(EnumDirection.DOWN, 1), ac, 2);
/* 69 */           var0.setTypeAndData(var6.c(EnumDirection.DOWN, 1), ad, 2);
/*    */         } 
/*    */       } 
/* 72 */       var5++;
/*    */     } 
/*    */     
/* 75 */     return (var5 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */