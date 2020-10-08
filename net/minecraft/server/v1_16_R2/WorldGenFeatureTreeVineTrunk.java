/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureTreeVineTrunk
/*    */   extends WorldGenFeatureTree
/*    */ {
/*    */   protected WorldGenFeatureTrees<?> a() {
/* 17 */     return WorldGenFeatureTrees.a;
/*    */   }
/*    */   
/* 20 */   public static final Codec<WorldGenFeatureTreeVineTrunk> a = Codec.unit(() -> b);
/*    */   
/* 22 */   public static final WorldGenFeatureTreeVineTrunk b = new WorldGenFeatureTreeVineTrunk();
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccessSeed var0, Random var1, List<BlockPosition> var2, List<BlockPosition> var3, Set<BlockPosition> var4, StructureBoundingBox var5) {
/* 26 */     var2.forEach(var4 -> {
/*    */           if (var0.nextInt(3) > 0) {
/*    */             BlockPosition var5 = var4.west();
/*    */             if (WorldGenerator.b(var1, var5))
/*    */               a(var1, var5, BlockVine.EAST, var2, var3); 
/*    */           } 
/*    */           if (var0.nextInt(3) > 0) {
/*    */             BlockPosition var5 = var4.east();
/*    */             if (WorldGenerator.b(var1, var5))
/*    */               a(var1, var5, BlockVine.WEST, var2, var3); 
/*    */           } 
/*    */           if (var0.nextInt(3) > 0) {
/*    */             BlockPosition var5 = var4.north();
/*    */             if (WorldGenerator.b(var1, var5))
/*    */               a(var1, var5, BlockVine.SOUTH, var2, var3); 
/*    */           } 
/*    */           if (var0.nextInt(3) > 0) {
/*    */             BlockPosition var5 = var4.south();
/*    */             if (WorldGenerator.b(var1, var5))
/*    */               a(var1, var5, BlockVine.NORTH, var2, var3); 
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTreeVineTrunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */