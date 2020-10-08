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
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenFeatureTree
/*    */ {
/* 19 */   public static final Codec<WorldGenFeatureTree> c = IRegistry.TREE_DECORATOR_TYPE.dispatch(WorldGenFeatureTree::a, WorldGenFeatureTrees::a);
/*    */   
/*    */   protected abstract WorldGenFeatureTrees<?> a();
/*    */   
/*    */   public abstract void a(GeneratorAccessSeed paramGeneratorAccessSeed, Random paramRandom, List<BlockPosition> paramList1, List<BlockPosition> paramList2, Set<BlockPosition> paramSet, StructureBoundingBox paramStructureBoundingBox);
/*    */   
/*    */   protected void a(IWorldWriter var0, BlockPosition var1, BlockStateBoolean var2, Set<BlockPosition> var3, StructureBoundingBox var4) {
/* 26 */     a(var0, var1, Blocks.VINE.getBlockData().set(var2, Boolean.valueOf(true)), var3, var4);
/*    */   }
/*    */   
/*    */   protected void a(IWorldWriter var0, BlockPosition var1, IBlockData var2, Set<BlockPosition> var3, StructureBoundingBox var4) {
/* 30 */     var0.setTypeAndData(var1, var2, 19);
/* 31 */     var3.add(var1);
/* 32 */     var4.c(new StructureBoundingBox(var1, var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */