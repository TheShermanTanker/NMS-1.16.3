/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorRoofedTree
/*    */   extends WorldGenDecoratorHeightAbstract<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorRoofedTree(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected HeightMap.Type a(WorldGenFeatureEmptyConfiguration2 var0) {
/* 19 */     return HeightMap.Type.MOTION_BLOCKING;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenFeatureEmptyConfiguration2 var2, BlockPosition var3) {
/* 26 */     return IntStream.range(0, 16).mapToObj(var4 -> {
/*    */           int var5 = var4 / 4;
/*    */           int var6 = var4 % 4;
/*    */           int var7 = var5 * 4 + 1 + var0.nextInt(3) + var1.getX();
/*    */           int var8 = var6 * 4 + 1 + var0.nextInt(3) + var1.getZ();
/*    */           int var9 = var2.a(a(var3), var7, var8);
/*    */           return new BlockPosition(var7, var9, var8);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorRoofedTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */