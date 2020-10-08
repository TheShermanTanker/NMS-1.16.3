/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorEndGateway
/*    */   extends WorldGenDecorator<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorEndGateway(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenFeatureEmptyConfiguration2 var2, BlockPosition var3) {
/* 18 */     if (var1.nextInt(700) == 0) {
/* 19 */       int var4 = var1.nextInt(16) + var3.getX();
/* 20 */       int var5 = var1.nextInt(16) + var3.getZ();
/* 21 */       int var6 = var0.a(HeightMap.Type.MOTION_BLOCKING, var4, var5);
/* 22 */       if (var6 > 0) {
/* 23 */         int var7 = var6 + 3 + var1.nextInt(7);
/* 24 */         return Stream.of(new BlockPosition(var4, var7, var5));
/*    */       } 
/*    */     } 
/*    */     
/* 28 */     return Stream.empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorEndGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */