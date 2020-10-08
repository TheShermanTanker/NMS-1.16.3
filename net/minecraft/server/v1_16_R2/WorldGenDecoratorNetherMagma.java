/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorNetherMagma
/*    */   extends WorldGenDecorator<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorNetherMagma(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenFeatureEmptyConfiguration2 var2, BlockPosition var3) {
/* 19 */     int var4 = var0.b();
/* 20 */     int var5 = var4 - 5 + var1.nextInt(10);
/* 21 */     return Stream.of(new BlockPosition(var3.getX(), var5, var3.getZ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorNetherMagma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */