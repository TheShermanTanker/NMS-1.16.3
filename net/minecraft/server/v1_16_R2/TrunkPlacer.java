/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.Products;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TrunkPlacer
/*    */ {
/* 25 */   public static final Codec<TrunkPlacer> c = IRegistry.TRUNK_PLACER_TYPE.dispatch(TrunkPlacer::a, TrunkPlacers::a);
/*    */   
/*    */   protected final int d;
/*    */   protected final int e;
/*    */   protected final int f;
/*    */   
/*    */   protected static <P extends TrunkPlacer> Products.P3<RecordCodecBuilder.Mu<P>, Integer, Integer, Integer> a(RecordCodecBuilder.Instance<P> var0) {
/* 32 */     return var0.group(
/* 33 */         (App)Codec.intRange(0, 32).fieldOf("base_height").forGetter(var0 -> Integer.valueOf(var0.d)), 
/* 34 */         (App)Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(var0 -> Integer.valueOf(var0.e)), 
/* 35 */         (App)Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(var0 -> Integer.valueOf(var0.f)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TrunkPlacer(int var0, int var1, int var2) {
/* 43 */     this.d = var0;
/* 44 */     this.e = var1;
/* 45 */     this.f = var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(Random var0) {
/* 53 */     return this.d + var0.nextInt(this.e + 1) + var0.nextInt(this.f + 1);
/*    */   }
/*    */   
/*    */   protected static void a(IWorldWriter var0, BlockPosition var1, IBlockData var2, StructureBoundingBox var3) {
/* 57 */     WorldGenTrees.b(var0, var1, var2);
/* 58 */     var3.c(new StructureBoundingBox(var1, var1));
/*    */   }
/*    */   
/*    */   private static boolean a(VirtualLevelReadable var0, BlockPosition var1) {
/* 62 */     return var0.a(var1, var0 -> {
/*    */           Block var1 = var0.getBlock();
/* 64 */           return (WorldGenerator.b(var1) && !var0.a(Blocks.GRASS_BLOCK) && !var0.a(Blocks.MYCELIUM));
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static void a(VirtualLevelWritable var0, BlockPosition var1) {
/* 71 */     if (!a(var0, var1)) {
/* 72 */       WorldGenTrees.b(var0, var1, Blocks.DIRT.getBlockData());
/*    */     }
/*    */   }
/*    */   
/*    */   protected static boolean a(VirtualLevelWritable var0, Random var1, BlockPosition var2, Set<BlockPosition> var3, StructureBoundingBox var4, WorldGenFeatureTreeConfiguration var5) {
/* 77 */     if (WorldGenTrees.e(var0, var2)) {
/* 78 */       a(var0, var2, var5.b.a(var1, var2), var4);
/* 79 */       var3.add(var2.immutableCopy());
/* 80 */       return true;
/*    */     } 
/* 82 */     return false;
/*    */   }
/*    */   
/*    */   protected static void a(VirtualLevelWritable var0, Random var1, BlockPosition.MutableBlockPosition var2, Set<BlockPosition> var3, StructureBoundingBox var4, WorldGenFeatureTreeConfiguration var5) {
/* 86 */     if (WorldGenTrees.c(var0, var2))
/* 87 */       a(var0, var1, var2, var3, var4, var5); 
/*    */   }
/*    */   
/*    */   protected abstract TrunkPlacers<?> a();
/*    */   
/*    */   public abstract List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable paramVirtualLevelWritable, Random paramRandom, int paramInt, BlockPosition paramBlockPosition, Set<BlockPosition> paramSet, StructureBoundingBox paramStructureBoundingBox, WorldGenFeatureTreeConfiguration paramWorldGenFeatureTreeConfiguration);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */