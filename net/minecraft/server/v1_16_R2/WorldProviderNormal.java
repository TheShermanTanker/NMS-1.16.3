/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldProviderNormal
/*    */ {
/*    */   @Nullable
/*    */   protected static BlockPosition a(WorldServer var0, int var1, int var2, boolean var3) {
/* 16 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition(var1, 0, var2);
/* 17 */     BiomeBase var5 = var0.getBiome(var4);
/*    */     
/* 19 */     boolean var6 = var0.getDimensionManager().hasCeiling();
/*    */     
/* 21 */     IBlockData var7 = var5.e().e().a();
/* 22 */     if (var3 && !var7.getBlock().a(TagsBlock.VALID_SPAWN)) {
/* 23 */       return null;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 28 */     Chunk var8 = var0.getChunkAt(var1 >> 4, var2 >> 4);
/* 29 */     int var9 = var6 ? var0.getChunkProvider().getChunkGenerator().getSpawnHeight() : var8.getHighestBlock(HeightMap.Type.MOTION_BLOCKING, var1 & 0xF, var2 & 0xF);
/*    */ 
/*    */     
/* 32 */     if (var9 < 0) {
/* 33 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 37 */     int var10 = var8.getHighestBlock(HeightMap.Type.WORLD_SURFACE, var1 & 0xF, var2 & 0xF);
/* 38 */     if (var10 <= var9 && var10 > var8.getHighestBlock(HeightMap.Type.OCEAN_FLOOR, var1 & 0xF, var2 & 0xF)) {
/* 39 */       return null;
/*    */     }
/*    */     
/* 42 */     for (int var11 = var9 + 1; var11 >= 0; var11--) {
/* 43 */       var4.d(var1, var11, var2);
/* 44 */       IBlockData var12 = var0.getType(var4);
/*    */ 
/*    */       
/* 47 */       if (!var12.getFluid().isEmpty()) {
/*    */         break;
/*    */       }
/*    */       
/* 51 */       if (var12.equals(var7)) {
/* 52 */         return var4.up().immutableCopy();
/*    */       }
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static BlockPosition a(WorldServer var0, ChunkCoordIntPair var1, boolean var2) {
/* 60 */     for (int var3 = var1.d(); var3 <= var1.f(); var3++) {
/* 61 */       for (int var4 = var1.e(); var4 <= var1.g(); var4++) {
/* 62 */         BlockPosition var5 = a(var0, var3, var4, var2);
/* 63 */         if (var5 != null) {
/* 64 */           return var5;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldProviderNormal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */