/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorCount
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorFrequencyConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorCount(Codec<WorldGenDecoratorFrequencyConfiguration> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorFrequencyConfiguration var1, BlockPosition var2) {
/* 18 */     return IntStream.range(0, var1.a().a(var0)).mapToObj(var1 -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */