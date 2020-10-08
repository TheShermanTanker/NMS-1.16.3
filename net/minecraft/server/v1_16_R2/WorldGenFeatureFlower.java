/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureFlower
/*    */   extends WorldGenFlowers<WorldGenFeatureRandomPatchConfiguration>
/*    */ {
/*    */   public WorldGenFeatureFlower(Codec<WorldGenFeatureRandomPatchConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccess var0, BlockPosition var1, WorldGenFeatureRandomPatchConfiguration var2) {
/* 18 */     return !var2.e.contains(var0.getType(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(WorldGenFeatureRandomPatchConfiguration var0) {
/* 23 */     return var0.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPosition a(Random var0, BlockPosition var1, WorldGenFeatureRandomPatchConfiguration var2) {
/* 28 */     return var1.b(var0.nextInt(var2.g) - var0.nextInt(var2.g), var0.nextInt(var2.h) - var0.nextInt(var2.h), var0.nextInt(var2.i) - var0.nextInt(var2.i));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData b(Random var0, BlockPosition var1, WorldGenFeatureRandomPatchConfiguration var2) {
/* 33 */     return var2.b.a(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */