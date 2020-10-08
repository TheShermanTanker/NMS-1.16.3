/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureBlock
/*    */   extends WorldGenerator<WorldGenFeatureBlockConfiguration>
/*    */ {
/*    */   public WorldGenFeatureBlock(Codec<WorldGenFeatureBlockConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureBlockConfiguration var4) {
/* 19 */     if (var4.c.contains(var0.getType(var3.down())) && var4.d.contains(var0.getType(var3)) && var4.e.contains(var0.getType(var3.up()))) {
/* 20 */       var0.setTypeAndData(var3, var4.b, 2);
/* 21 */       return true;
/*    */     } 
/* 23 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */