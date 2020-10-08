/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureRandomChoice
/*    */   extends WorldGenerator<WorldGenFeatureRandomChoiceConfiguration>
/*    */ {
/*    */   public WorldGenFeatureRandomChoice(Codec<WorldGenFeatureRandomChoiceConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureRandomChoiceConfiguration var4) {
/* 18 */     for (WorldGenFeatureRandomChoiceConfigurationWeight var6 : var4.b) {
/* 19 */       if (var2.nextFloat() < var6.c) {
/* 20 */         return var6.a(var0, var1, var2, var3);
/*    */       }
/*    */     } 
/* 23 */     return ((WorldGenFeatureConfigured)var4.c.get()).a(var0, var1, var2, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandomChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */