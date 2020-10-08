/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorIceburg
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorIceburg(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenFeatureEmptyConfiguration2 var1, BlockPosition var2) {
/* 17 */     int var3 = var0.nextInt(8) + 4 + var2.getX();
/* 18 */     int var4 = var0.nextInt(8) + 4 + var2.getZ();
/* 19 */     return Stream.of(new BlockPosition(var3, var2.getY(), var4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorIceburg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */