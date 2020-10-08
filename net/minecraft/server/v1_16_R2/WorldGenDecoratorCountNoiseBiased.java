/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorCountNoiseBiased
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorNoiseConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorCountNoiseBiased(Codec<WorldGenDecoratorNoiseConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorNoiseConfiguration var1, BlockPosition var2) {
/* 18 */     double var3 = BiomeBase.f.a(var2.getX() / var1.d, var2.getZ() / var1.d, false);
/* 19 */     int var5 = (int)Math.ceil((var3 + var1.e) * var1.c);
/* 20 */     return IntStream.range(0, var5).mapToObj(var1 -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCountNoiseBiased.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */