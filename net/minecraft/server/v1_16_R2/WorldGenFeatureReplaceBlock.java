/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureReplaceBlock
/*    */   extends WorldGenerator<WorldGenFeatureReplaceBlockConfiguration>
/*    */ {
/*    */   public WorldGenFeatureReplaceBlock(Codec<WorldGenFeatureReplaceBlockConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureReplaceBlockConfiguration var4) {
/* 19 */     if (var0.getType(var3).a(var4.b.getBlock())) {
/* 20 */       var0.setTypeAndData(var3, var4.c, 2);
/*    */     }
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureReplaceBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */