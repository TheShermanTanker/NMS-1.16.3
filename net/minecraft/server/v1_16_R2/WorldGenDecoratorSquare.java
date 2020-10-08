/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorSquare
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorSquare(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenFeatureEmptyConfiguration2 var1, BlockPosition var2) {
/* 17 */     int var3 = var0.nextInt(16) + var2.getX();
/* 18 */     int var4 = var0.nextInt(16) + var2.getZ();
/* 19 */     int var5 = var2.getY();
/*    */     
/* 21 */     return Stream.of(new BlockPosition(var3, var5, var4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorSquare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */