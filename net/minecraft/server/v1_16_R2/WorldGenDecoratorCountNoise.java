/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorCountNoise
/*    */   extends WorldGenDecorator<WorldGenFeatureDecoratorNoiseConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorCountNoise(Codec<WorldGenFeatureDecoratorNoiseConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenFeatureDecoratorNoiseConfiguration var2, BlockPosition var3) {
/* 19 */     double var4 = BiomeBase.f.a(var3.getX() / 200.0D, var3.getZ() / 200.0D, false);
/* 20 */     int var6 = (var4 < var2.c) ? var2.d : var2.e;
/* 21 */     return IntStream.range(0, var6).mapToObj(var1 -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCountNoise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */