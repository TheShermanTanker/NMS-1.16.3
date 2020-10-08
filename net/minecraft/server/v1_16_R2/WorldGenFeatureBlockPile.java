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
/*    */ public class WorldGenFeatureBlockPile
/*    */   extends WorldGenerator<WorldGenFeatureBlockPileConfiguration>
/*    */ {
/*    */   public WorldGenFeatureBlockPile(Codec<WorldGenFeatureBlockPileConfiguration> var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureBlockPileConfiguration var4) {
/* 23 */     if (var3.getY() < 5) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     int var5 = 2 + var2.nextInt(2);
/* 28 */     int var6 = 2 + var2.nextInt(2);
/*    */     
/* 30 */     for (BlockPosition var8 : BlockPosition.a(var3.b(-var5, 0, -var6), var3.b(var5, 1, var6))) {
/* 31 */       int var9 = var3.getX() - var8.getX();
/* 32 */       int var10 = var3.getZ() - var8.getZ();
/* 33 */       if ((var9 * var9 + var10 * var10) <= var2.nextFloat() * 10.0F - var2.nextFloat() * 6.0F) {
/* 34 */         a(var0, var8, var2, var4); continue;
/* 35 */       }  if (var2.nextFloat() < 0.031D) {
/* 36 */         a(var0, var8, var2, var4);
/*    */       }
/*    */     } 
/*    */     
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   private boolean a(GeneratorAccess var0, BlockPosition var1, Random var2) {
/* 44 */     BlockPosition var3 = var1.down();
/* 45 */     IBlockData var4 = var0.getType(var3);
/* 46 */     if (var4.a(Blocks.GRASS_PATH)) {
/* 47 */       return var2.nextBoolean();
/*    */     }
/*    */     
/* 50 */     return var4.d(var0, var3, EnumDirection.UP);
/*    */   }
/*    */   
/*    */   private void a(GeneratorAccess var0, BlockPosition var1, Random var2, WorldGenFeatureBlockPileConfiguration var3) {
/* 54 */     if (var0.isEmpty(var1) && a(var0, var1, var2))
/* 55 */       var0.setTypeAndData(var1, var3.b.a(var2, var1), 4); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBlockPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */