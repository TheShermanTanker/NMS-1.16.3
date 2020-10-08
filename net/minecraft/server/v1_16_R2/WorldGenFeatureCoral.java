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
/*    */ 
/*    */ public abstract class WorldGenFeatureCoral
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureCoral(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 26 */     IBlockData var5 = ((Block)TagsBlock.CORAL_BLOCKS.a(var2)).getBlockData();
/* 27 */     return a(var0, var2, var3, var5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean b(GeneratorAccess var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 33 */     BlockPosition var4 = var2.up();
/* 34 */     IBlockData var5 = var0.getType(var2);
/*    */     
/* 36 */     if ((!var5.a(Blocks.WATER) && !var5.a(TagsBlock.CORALS)) || !var0.getType(var4).a(Blocks.WATER)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     var0.setTypeAndData(var2, var3, 3);
/* 41 */     if (var1.nextFloat() < 0.25F) {
/* 42 */       var0.setTypeAndData(var4, ((Block)TagsBlock.CORALS.a(var1)).getBlockData(), 2);
/* 43 */     } else if (var1.nextFloat() < 0.05F) {
/* 44 */       var0.setTypeAndData(var4, Blocks.SEA_PICKLE.getBlockData().set(BlockSeaPickle.a, Integer.valueOf(var1.nextInt(4) + 1)), 2);
/*    */     } 
/*    */     
/* 47 */     for (EnumDirection var7 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 48 */       if (var1.nextFloat() < 0.2F) {
/* 49 */         BlockPosition var8 = var2.shift(var7);
/* 50 */         if (var0.getType(var8).a(Blocks.WATER)) {
/* 51 */           IBlockData var9 = ((Block)TagsBlock.WALL_CORALS.a(var1)).getBlockData().set(BlockCoralFanWallAbstract.a, var7);
/* 52 */           var0.setTypeAndData(var8, var9, 2);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   protected abstract boolean a(GeneratorAccess paramGeneratorAccess, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCoral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */