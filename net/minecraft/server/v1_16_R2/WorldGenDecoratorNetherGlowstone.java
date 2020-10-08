/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorNetherGlowstone
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorFrequencyConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorNetherGlowstone(Codec<WorldGenDecoratorFrequencyConfiguration> var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorFrequencyConfiguration var1, BlockPosition var2) {
/* 21 */     return IntStream.range(0, var0.nextInt(var0.nextInt(var1.a().a(var0)) + 1)).mapToObj(var2 -> {
/*    */           int var3 = var0.nextInt(16) + var1.getX();
/*    */           int var4 = var0.nextInt(16) + var1.getZ();
/*    */           int var5 = var0.nextInt(120) + 4;
/*    */           return new BlockPosition(var3, var5, var4);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorNetherGlowstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */