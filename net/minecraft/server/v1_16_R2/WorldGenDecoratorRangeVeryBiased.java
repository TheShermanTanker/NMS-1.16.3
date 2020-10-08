/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorRangeVeryBiased
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenFeatureChanceDecoratorRangeConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorRangeVeryBiased(Codec<WorldGenFeatureChanceDecoratorRangeConfiguration> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenFeatureChanceDecoratorRangeConfiguration var1, BlockPosition var2) {
/* 17 */     int var3 = var2.getX();
/* 18 */     int var4 = var2.getZ();
/* 19 */     int var5 = var0.nextInt(var0.nextInt(var0.nextInt(var1.e - var1.d) + var1.c) + var1.c);
/* 20 */     return Stream.of(new BlockPosition(var3, var5, var4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorRangeVeryBiased.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */