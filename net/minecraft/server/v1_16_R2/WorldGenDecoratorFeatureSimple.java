/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenDecoratorFeatureSimple<DC extends WorldGenFeatureDecoratorConfiguration>
/*    */   extends WorldGenDecorator<DC>
/*    */ {
/*    */   public WorldGenDecoratorFeatureSimple(Codec<DC> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public final Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, DC var2, BlockPosition var3) {
/* 17 */     return a(var1, var2, var3);
/*    */   }
/*    */   
/*    */   protected abstract Stream<BlockPosition> a(Random paramRandom, DC paramDC, BlockPosition paramBlockPosition);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorFeatureSimple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */