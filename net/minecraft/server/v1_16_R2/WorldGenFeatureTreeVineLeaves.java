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
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureTreeVineLeaves
/*    */   extends WorldGenFeatureTree
/*    */ {
/*    */   protected WorldGenFeatureTrees<?> a() {
/* 19 */     return WorldGenFeatureTrees.b;
/*    */   }
/*    */   
/* 22 */   public static final Codec<WorldGenFeatureTreeVineLeaves> a = Codec.unit(() -> b);
/*    */   
/* 24 */   public static final WorldGenFeatureTreeVineLeaves b = new WorldGenFeatureTreeVineLeaves();
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccessSeed var0, Random var1, List<BlockPosition> var2, List<BlockPosition> var3, Set<BlockPosition> var4, StructureBoundingBox var5) {
/* 28 */     var3.forEach(var4 -> {
/*    */           if (var0.nextInt(4) == 0) {
/*    */             BlockPosition var5 = var4.west();
/*    */             if (WorldGenerator.b(var1, var5)) {
/*    */               a(var1, var5, BlockVine.EAST, var2, var3);
/*    */             }
/*    */           } 
/*    */           if (var0.nextInt(4) == 0) {
/*    */             BlockPosition var5 = var4.east();
/*    */             if (WorldGenerator.b(var1, var5)) {
/*    */               a(var1, var5, BlockVine.WEST, var2, var3);
/*    */             }
/*    */           } 
/*    */           if (var0.nextInt(4) == 0) {
/*    */             BlockPosition var5 = var4.north();
/*    */             if (WorldGenerator.b(var1, var5)) {
/*    */               a(var1, var5, BlockVine.SOUTH, var2, var3);
/*    */             }
/*    */           } 
/*    */           if (var0.nextInt(4) == 0) {
/*    */             BlockPosition var5 = var4.south();
/*    */             if (WorldGenerator.b(var1, var5)) {
/*    */               a(var1, var5, BlockVine.NORTH, var2, var3);
/*    */             }
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void a(VirtualLevelWritable var0, BlockPosition var1, BlockStateBoolean var2, Set<BlockPosition> var3, StructureBoundingBox var4) {
/* 60 */     a(var0, var1, var2, var3, var4);
/* 61 */     int var5 = 4;
/*    */     
/* 63 */     var1 = var1.down();
/* 64 */     while (WorldGenerator.b(var0, var1) && var5 > 0) {
/* 65 */       a(var0, var1, var2, var3, var4);
/* 66 */       var1 = var1.down();
/* 67 */       var5--;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTreeVineLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */