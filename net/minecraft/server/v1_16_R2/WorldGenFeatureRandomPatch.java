/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureRandomPatch
/*    */   extends WorldGenerator<WorldGenFeatureRandomPatchConfiguration>
/*    */ {
/*    */   public WorldGenFeatureRandomPatch(Codec<WorldGenFeatureRandomPatchConfiguration> var0) {
/* 16 */     super(var0);
/*    */   }
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureRandomPatchConfiguration var4) {
/*    */     BlockPosition var6;
/* 21 */     IBlockData var5 = var4.b.a(var2, var3);
/*    */     
/* 23 */     if (var4.l) {
/* 24 */       var6 = var0.getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE_WG, var3);
/*    */     } else {
/* 26 */       var6 = var3;
/*    */     } 
/*    */     
/* 29 */     int var7 = 0;
/*    */     
/* 31 */     BlockPosition.MutableBlockPosition var8 = new BlockPosition.MutableBlockPosition();
/* 32 */     for (int var9 = 0; var9 < var4.f; var9++) {
/* 33 */       var8.a(var6, var2.nextInt(var4.g + 1) - var2.nextInt(var4.g + 1), var2.nextInt(var4.h + 1) - var2.nextInt(var4.h + 1), var2.nextInt(var4.i + 1) - var2.nextInt(var4.i + 1));
/* 34 */       BlockPosition var10 = var8.down();
/* 35 */       IBlockData var11 = var0.getType(var10);
/* 36 */       if ((var0.isEmpty(var8) || (var4.j && var0.getType(var8).getMaterial().isReplaceable())) && var5
/* 37 */         .canPlace(var0, var8) && (var4.d
/* 38 */         .isEmpty() || var4.d.contains(var11.getBlock())) && 
/* 39 */         !var4.e.contains(var11) && (!var4.m || var0
/* 40 */         .getFluid(var10.west()).a(TagsFluid.WATER) || var0.getFluid(var10.east()).a(TagsFluid.WATER) || var0.getFluid(var10.north()).a(TagsFluid.WATER) || var0.getFluid(var10.south()).a(TagsFluid.WATER))) {
/*    */         
/* 42 */         var4.c.a(var0, var8, var5, var2);
/* 43 */         var7++;
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     return (var7 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandomPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */