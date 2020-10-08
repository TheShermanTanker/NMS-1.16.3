/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureRandom2Configuration
/*    */   extends WorldGenerator<WorldGenFeatureRandom2>
/*    */ {
/*    */   public WorldGenFeatureRandom2Configuration(Codec<WorldGenFeatureRandom2> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureRandom2 var4) {
/* 18 */     int var5 = var2.nextInt(var4.b.size());
/* 19 */     WorldGenFeatureConfigured<?, ?> var6 = ((Supplier<WorldGenFeatureConfigured<?, ?>>)var4.b.get(var5)).get();
/* 20 */     return var6.a(var0, var1, var2, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandom2Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */