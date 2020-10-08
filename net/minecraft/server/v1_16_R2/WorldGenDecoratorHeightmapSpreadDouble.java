/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorHeightmapSpreadDouble<DC extends WorldGenFeatureDecoratorConfiguration>
/*    */   extends WorldGenDecoratorHeightAbstract<DC>
/*    */ {
/*    */   public WorldGenDecoratorHeightmapSpreadDouble(Codec<DC> var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected HeightMap.Type a(DC var0) {
/* 18 */     return HeightMap.Type.MOTION_BLOCKING;
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, DC var2, BlockPosition var3) {
/* 23 */     int var4 = var3.getX();
/* 24 */     int var5 = var3.getZ();
/* 25 */     int var6 = var0.a(a(var2), var4, var5);
/* 26 */     if (var6 == 0) {
/* 27 */       return Stream.of(new BlockPosition[0]);
/*    */     }
/* 29 */     return Stream.of(new BlockPosition(var4, var1.nextInt(var6 * 2), var5));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorHeightmapSpreadDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */