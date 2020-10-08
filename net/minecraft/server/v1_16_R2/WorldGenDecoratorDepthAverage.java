/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenDecoratorDepthAverage
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorHeightAverageConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorDepthAverage(Codec<WorldGenDecoratorHeightAverageConfiguration> var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorHeightAverageConfiguration var1, BlockPosition var2) {
/* 16 */     int var3 = var1.c;
/* 17 */     int var4 = var1.d;
/*    */     
/* 19 */     int var5 = var2.getX();
/* 20 */     int var6 = var2.getZ();
/* 21 */     int var7 = var0.nextInt(var4) + var0.nextInt(var4) - var4 + var3;
/* 22 */     return Stream.of(new BlockPosition(var5, var7, var6));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorDepthAverage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */