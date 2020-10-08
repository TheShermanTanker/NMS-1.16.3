/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenDecoratorChance
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorDungeonConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorChance(Codec<WorldGenDecoratorDungeonConfiguration> var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenDecoratorDungeonConfiguration var1, BlockPosition var2) {
/* 16 */     if (var0.nextFloat() < 1.0F / var1.c) {
/* 17 */       return Stream.of(var2);
/*    */     }
/*    */     
/* 20 */     return Stream.empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorChance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */