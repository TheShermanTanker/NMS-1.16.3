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
/*    */ public class WorldGenEndIsland
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenEndIsland(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 20 */     float var5 = (var2.nextInt(3) + 4);
/* 21 */     int var6 = 0;
/* 22 */     while (var5 > 0.5F) {
/* 23 */       for (int var7 = MathHelper.d(-var5); var7 <= MathHelper.f(var5); var7++) {
/* 24 */         for (int var8 = MathHelper.d(-var5); var8 <= MathHelper.f(var5); var8++) {
/* 25 */           if ((var7 * var7 + var8 * var8) <= (var5 + 1.0F) * (var5 + 1.0F)) {
/* 26 */             a(var0, var3.b(var7, var6, var8), Blocks.END_STONE.getBlockData());
/*    */           }
/*    */         } 
/*    */       } 
/* 30 */       var5 = (float)(var5 - var2.nextInt(2) + 0.5D);
/* 31 */       var6--;
/*    */     } 
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEndIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */