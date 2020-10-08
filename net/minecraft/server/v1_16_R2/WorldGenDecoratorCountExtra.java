/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenDecoratorCountExtra
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorFrequencyExtraChanceConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorCountExtra(Codec<WorldGenDecoratorFrequencyExtraChanceConfiguration> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorFrequencyExtraChanceConfiguration var1, BlockPosition var2) {
/* 17 */     int var3 = var1.c + ((var0.nextFloat() < var1.d) ? var1.e : 0);
/* 18 */     return IntStream.range(0, var3).mapToObj(var1 -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCountExtra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */