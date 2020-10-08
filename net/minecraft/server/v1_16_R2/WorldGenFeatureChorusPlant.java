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
/*    */ public class WorldGenFeatureChorusPlant
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureChorusPlant(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 20 */     if (var0.isEmpty(var3) && var0.getType(var3.down()).a(Blocks.END_STONE)) {
/* 21 */       BlockChorusFlower.a(var0, var3, var2, 8);
/* 22 */       return true;
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureChorusPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */