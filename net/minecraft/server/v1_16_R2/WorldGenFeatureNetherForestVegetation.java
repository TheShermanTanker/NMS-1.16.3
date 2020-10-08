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
/*    */ public class WorldGenFeatureNetherForestVegetation
/*    */   extends WorldGenerator<WorldGenFeatureBlockPileConfiguration>
/*    */ {
/*    */   public WorldGenFeatureNetherForestVegetation(Codec<WorldGenFeatureBlockPileConfiguration> var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureBlockPileConfiguration var4) {
/* 25 */     return a(var0, var2, var3, var4, 8, 4);
/*    */   }
/*    */   
/*    */   public static boolean a(GeneratorAccess var0, Random var1, BlockPosition var2, WorldGenFeatureBlockPileConfiguration var3, int var4, int var5) {
/* 29 */     Block var6 = var0.getType(var2.down()).getBlock();
/*    */     
/* 31 */     if (!var6.a(TagsBlock.NYLIUM)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     int var7 = var2.getY();
/*    */     
/* 37 */     if (var7 < 1 || var7 + 1 >= 256) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     int var8 = 0;
/*    */     
/* 43 */     for (int var9 = 0; var9 < var4 * var4; var9++) {
/* 44 */       BlockPosition var10 = var2.b(var1.nextInt(var4) - var1.nextInt(var4), var1.nextInt(var5) - var1.nextInt(var5), var1.nextInt(var4) - var1.nextInt(var4));
/* 45 */       IBlockData var11 = var3.b.a(var1, var10);
/* 46 */       if (var0.isEmpty(var10) && var10.getY() > 0 && 
/* 47 */         var11.canPlace(var0, var10)) {
/* 48 */         var0.setTypeAndData(var10, var11, 2);
/* 49 */         var8++;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 54 */     return (var8 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureNetherForestVegetation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */