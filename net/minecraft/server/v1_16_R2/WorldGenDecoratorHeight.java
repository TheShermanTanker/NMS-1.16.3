/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenDecoratorHeight<DC extends WorldGenFeatureDecoratorConfiguration>
/*    */   extends WorldGenDecoratorHeightAbstract<DC>
/*    */ {
/*    */   public WorldGenDecoratorHeight(Codec<DC> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, DC var2, BlockPosition var3) {
/* 17 */     int var4 = var3.getX();
/* 18 */     int var5 = var3.getZ();
/* 19 */     int var6 = var0.a(a(var2), var4, var5);
/* 20 */     if (var6 > 0) {
/* 21 */       return Stream.of(new BlockPosition(var4, var6, var5));
/*    */     }
/* 23 */     return Stream.of(new BlockPosition[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorHeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */