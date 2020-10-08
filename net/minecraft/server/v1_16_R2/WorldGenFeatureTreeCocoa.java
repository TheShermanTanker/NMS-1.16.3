/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureTreeCocoa
/*    */   extends WorldGenFeatureTree
/*    */ {
/*    */   public static final Codec<WorldGenFeatureTreeCocoa> a;
/*    */   private final float b;
/*    */   
/*    */   static {
/* 18 */     a = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(WorldGenFeatureTreeCocoa::new, var0 -> Float.valueOf(var0.b)).codec();
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureTreeCocoa(float var0) {
/* 23 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureTrees<?> a() {
/* 28 */     return WorldGenFeatureTrees.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccessSeed var0, Random var1, List<BlockPosition> var2, List<BlockPosition> var3, Set<BlockPosition> var4, StructureBoundingBox var5) {
/* 33 */     if (var1.nextFloat() >= this.b) {
/*    */       return;
/*    */     }
/*    */     
/* 37 */     int var6 = ((BlockPosition)var2.get(0)).getY();
/* 38 */     var2.stream()
/* 39 */       .filter(var1 -> (var1.getY() - var0 <= 2))
/* 40 */       .forEach(var4 -> {
/*    */           for (EnumDirection var6 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/*    */             if (var0.nextFloat() <= 0.25F) {
/*    */               EnumDirection var7 = var6.opposite();
/*    */               BlockPosition var8 = var4.b(var7.getAdjacentX(), 0, var7.getAdjacentZ());
/*    */               if (WorldGenerator.b(var1, var8)) {
/*    */                 IBlockData var9 = Blocks.COCOA.getBlockData().set(BlockCocoa.AGE, Integer.valueOf(var0.nextInt(3))).set(BlockCocoa.FACING, var6);
/*    */                 a(var1, var8, var9, var2, var3);
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTreeCocoa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */