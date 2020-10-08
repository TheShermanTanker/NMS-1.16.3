/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureCircle
/*    */   extends WorldGenFeatureDisk
/*    */ {
/*    */   public WorldGenFeatureCircle(Codec<WorldGenFeatureCircleConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureCircleConfiguration var4) {
/* 20 */     if (!var0.getFluid(var3).a(TagsFluid.WATER)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     return super.a(var0, var1, var2, var3, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCircle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */