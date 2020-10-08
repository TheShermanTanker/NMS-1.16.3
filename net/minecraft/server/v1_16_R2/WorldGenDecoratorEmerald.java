/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorEmerald
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorEmerald(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenFeatureEmptyConfiguration2 var1, BlockPosition var2) {
/* 18 */     int var3 = 3 + var0.nextInt(6);
/* 19 */     return IntStream.range(0, var3).mapToObj(var2 -> {
/*    */           int var3 = var0.nextInt(16) + var1.getX();
/*    */           int var4 = var0.nextInt(16) + var1.getZ();
/*    */           int var5 = var0.nextInt(28) + 4;
/*    */           return new BlockPosition(var3, var5, var4);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorEmerald.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */