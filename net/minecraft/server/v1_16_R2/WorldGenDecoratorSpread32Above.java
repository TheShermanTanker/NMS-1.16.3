/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorSpread32Above
/*    */   extends WorldGenDecorator<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorSpread32Above(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenFeatureEmptyConfiguration2 var2, BlockPosition var3) {
/* 17 */     int var4 = var1.nextInt(var3.getY() + 32);
/* 18 */     return Stream.of(new BlockPosition(var3.getX(), var4, var3.getZ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorSpread32Above.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */