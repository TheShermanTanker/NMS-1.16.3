/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureChoice
/*    */   extends WorldGenerator<WorldGenFeatureChoiceConfiguration>
/*    */ {
/*    */   public WorldGenFeatureChoice(Codec<WorldGenFeatureChoiceConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureChoiceConfiguration var4) {
/* 18 */     boolean var5 = var2.nextBoolean();
/* 19 */     if (var5) {
/* 20 */       return ((WorldGenFeatureConfigured)var4.b.get()).a(var0, var1, var2, var3);
/*    */     }
/* 22 */     return ((WorldGenFeatureConfigured)var4.c.get()).a(var0, var1, var2, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */