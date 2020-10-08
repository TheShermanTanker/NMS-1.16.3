/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorEmpty
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorEmpty(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenFeatureEmptyConfiguration2 var1, BlockPosition var2) {
/* 17 */     return Stream.of(var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */