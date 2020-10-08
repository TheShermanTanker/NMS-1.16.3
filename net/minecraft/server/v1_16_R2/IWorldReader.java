/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IWorldReader
/*     */   extends IBlockLightAccess, ICollisionAccess, BiomeManager.Provider
/*     */ {
/*     */   @Nullable
/*     */   IChunkAccess getChunkIfLoadedImmediately(int paramInt1, int paramInt2);
/*     */   
/*     */   @Nullable
/*     */   IChunkAccess getChunkAt(int paramInt1, int paramInt2, ChunkStatus paramChunkStatus, boolean paramBoolean);
/*     */   
/*     */   @Deprecated
/*     */   boolean isChunkLoaded(int paramInt1, int paramInt2);
/*     */   
/*     */   default BiomeBase getBiome(BlockPosition blockposition) {
/*  22 */     return d().a(blockposition);
/*     */   } int a(HeightMap.Type paramType, int paramInt1, int paramInt2); int c();
/*     */   BiomeManager d();
/*     */   default Stream<IBlockData> c(AxisAlignedBB axisalignedbb) {
/*  26 */     int i = MathHelper.floor(axisalignedbb.minX);
/*  27 */     int j = MathHelper.floor(axisalignedbb.maxX);
/*  28 */     int k = MathHelper.floor(axisalignedbb.minY);
/*  29 */     int l = MathHelper.floor(axisalignedbb.maxY);
/*  30 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/*  31 */     int j1 = MathHelper.floor(axisalignedbb.maxZ);
/*     */     
/*  33 */     return isAreaLoaded(i, k, i1, j, l, j1) ? a(axisalignedbb) : Stream.<IBlockData>empty();
/*     */   }
/*     */ 
/*     */   
/*     */   default BiomeBase getBiome(int i, int j, int k) {
/*  38 */     IChunkAccess ichunkaccess = getChunkAt(i >> 2, k >> 2, ChunkStatus.BIOMES, false);
/*     */     
/*  40 */     return (ichunkaccess != null && ichunkaccess.getBiomeIndex() != null) ? ichunkaccess.getBiomeIndex().getBiome(i, j, k) : a(i, j, k);
/*     */   }
/*     */   
/*     */   BiomeBase a(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   boolean s_();
/*     */   
/*     */   @Deprecated
/*     */   int getSeaLevel();
/*     */   
/*     */   DimensionManager getDimensionManager();
/*     */   
/*     */   default BlockPosition getHighestBlockYAt(HeightMap.Type heightmap_type, BlockPosition blockposition) {
/*  53 */     return new BlockPosition(blockposition.getX(), a(heightmap_type, blockposition.getX(), blockposition.getZ()), blockposition.getZ());
/*     */   }
/*     */   
/*     */   default boolean isEmpty(BlockPosition blockposition) {
/*  57 */     return getType(blockposition).isAir();
/*     */   }
/*     */   
/*     */   default boolean x(BlockPosition blockposition) {
/*  61 */     if (blockposition.getY() >= getSeaLevel()) {
/*  62 */       return e(blockposition);
/*     */     }
/*  64 */     BlockPosition blockposition1 = new BlockPosition(blockposition.getX(), getSeaLevel(), blockposition.getZ());
/*     */     
/*  66 */     if (!e(blockposition1)) {
/*  67 */       return false;
/*     */     }
/*  69 */     for (blockposition1 = blockposition1.down(); blockposition1.getY() > blockposition.getY(); blockposition1 = blockposition1.down()) {
/*  70 */       IBlockData iblockdata = getType(blockposition1);
/*     */       
/*  72 */       if (iblockdata.b(this, blockposition1) > 0 && !iblockdata.getMaterial().isLiquid()) {
/*  73 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   default float y(BlockPosition blockposition) {
/*  84 */     return getDimensionManager().a(getLightLevel(blockposition));
/*     */   }
/*     */   
/*     */   default int c(BlockPosition blockposition, EnumDirection enumdirection) {
/*  88 */     return getType(blockposition).c(this, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   default IChunkAccess z(BlockPosition blockposition) {
/*  92 */     return getChunkAt(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*     */   }
/*     */   
/*     */   default IChunkAccess getChunkAt(int i, int j) {
/*  96 */     return getChunkAt(i, j, ChunkStatus.FULL, true);
/*     */   }
/*     */   
/*     */   default IChunkAccess getChunkAt(int i, int j, ChunkStatus chunkstatus) {
/* 100 */     return getChunkAt(i, j, chunkstatus, true);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default IBlockAccess c(int i, int j) {
/* 106 */     return getChunkAt(i, j, ChunkStatus.EMPTY, false);
/*     */   }
/*     */   
/*     */   default boolean A(BlockPosition blockposition) {
/* 110 */     return getFluid(blockposition).a(TagsFluid.WATER);
/*     */   }
/*     */   
/*     */   default boolean containsLiquid(AxisAlignedBB axisalignedbb) {
/* 114 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 115 */     int j = MathHelper.f(axisalignedbb.maxX);
/* 116 */     int k = MathHelper.floor(axisalignedbb.minY);
/* 117 */     int l = MathHelper.f(axisalignedbb.maxY);
/* 118 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/* 119 */     int j1 = MathHelper.f(axisalignedbb.maxZ);
/* 120 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 122 */     for (int k1 = i; k1 < j; k1++) {
/* 123 */       for (int l1 = k; l1 < l; l1++) {
/* 124 */         for (int i2 = i1; i2 < j1; i2++) {
/* 125 */           IBlockData iblockdata = getType(blockposition_mutableblockposition.d(k1, l1, i2));
/*     */           
/* 127 */           if (!iblockdata.getFluid().isEmpty()) {
/* 128 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   default int getLightLevel(BlockPosition blockposition) {
/* 138 */     return c(blockposition, c());
/*     */   }
/*     */   
/*     */   default int c(BlockPosition blockposition, int i) {
/* 142 */     return (blockposition.getX() >= -30000000 && blockposition.getZ() >= -30000000 && blockposition.getX() < 30000000 && blockposition.getZ() < 30000000) ? getLightLevel(blockposition, i) : 15;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   default boolean isLoaded(BlockPosition blockposition) {
/* 147 */     return isChunkLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   default boolean areChunksLoadedBetween(BlockPosition blockposition, BlockPosition blockposition1) {
/* 152 */     return isAreaLoaded(blockposition.getX(), blockposition.getY(), blockposition.getZ(), blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   default boolean isAreaLoaded(int i, int j, int k, int l, int i1, int j1) {
/* 157 */     if (i1 >= 0 && j < 256) {
/* 158 */       i >>= 4;
/* 159 */       k >>= 4;
/* 160 */       l >>= 4;
/* 161 */       j1 >>= 4;
/*     */       
/* 163 */       for (int k1 = i; k1 <= l; k1++) {
/* 164 */         for (int l1 = k; l1 <= j1; l1++) {
/* 165 */           if (!isChunkLoaded(k1, l1)) {
/* 166 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 171 */       return true;
/*     */     } 
/* 173 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IWorldReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */