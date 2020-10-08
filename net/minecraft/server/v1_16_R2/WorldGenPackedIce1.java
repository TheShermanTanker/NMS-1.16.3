/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenPackedIce1
/*    */   extends WorldGenFeatureDisk
/*    */ {
/*    */   public WorldGenPackedIce1(Codec<WorldGenFeatureCircleConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureCircleConfiguration var4) {
/* 20 */     while (var0.isEmpty(var3) && var3.getY() > 2) {
/* 21 */       var3 = var3.down();
/*    */     }
/*    */     
/* 24 */     if (!var0.getType(var3).a(Blocks.SNOW_BLOCK)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     return super.a(var0, var1, var2, var3, var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenPackedIce1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */