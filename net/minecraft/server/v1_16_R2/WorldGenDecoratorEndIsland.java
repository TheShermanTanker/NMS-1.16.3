/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorEndIsland
/*    */   extends WorldGenDecoratorFeatureSimple<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorEndIsland(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(Random var0, WorldGenFeatureEmptyConfiguration2 var1, BlockPosition var2) {
/* 17 */     Stream<BlockPosition> var3 = Stream.empty();
/*    */     
/* 19 */     if (var0.nextInt(14) == 0) {
/* 20 */       var3 = Stream.concat(var3, Stream.of(var2.b(var0.nextInt(16), 55 + var0.nextInt(16), var0.nextInt(16))));
/*    */       
/* 22 */       if (var0.nextInt(4) == 0) {
/* 23 */         var3 = Stream.concat(var3, Stream.of(var2.b(var0.nextInt(16), 55 + var0.nextInt(16), var0.nextInt(16))));
/*    */       }
/* 25 */       return var3;
/*    */     } 
/*    */     
/* 28 */     return Stream.empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorEndIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */