/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureEmpty
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureEmpty(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */