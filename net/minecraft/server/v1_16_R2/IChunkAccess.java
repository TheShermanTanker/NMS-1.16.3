/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.shorts.ShortArrayList;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ 
/*     */ 
/*     */ public interface IChunkAccess
/*     */   extends IBlockAccess, IStructureAccess
/*     */ {
/*     */   default boolean generateFlatBedrock() {
/*  17 */     if (this instanceof ProtoChunk)
/*  18 */       return ((ProtoChunk)this).world.paperConfig.generateFlatBedrock; 
/*  19 */     if (this instanceof Chunk) {
/*  20 */       return ((Chunk)this).world.paperConfig.generateFlatBedrock;
/*     */     }
/*  22 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   IBlockData getType(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   @Nullable
/*     */   IBlockData setType(BlockPosition paramBlockPosition, IBlockData paramIBlockData, boolean paramBoolean);
/*     */   
/*     */   void setTileEntity(BlockPosition paramBlockPosition, TileEntity paramTileEntity);
/*     */   
/*     */   void a(Entity paramEntity);
/*     */   
/*     */   @Nullable
/*     */   default ChunkSection a() {
/*  37 */     ChunkSection[] achunksection = getSections();
/*     */     
/*  39 */     for (int i = achunksection.length - 1; i >= 0; i--) {
/*  40 */       ChunkSection chunksection = achunksection[i];
/*     */       
/*  42 */       if (!ChunkSection.a(chunksection)) {
/*  43 */         return chunksection;
/*     */       }
/*     */     } 
/*     */     
/*  47 */     return null;
/*     */   }
/*     */   
/*     */   default int b() {
/*  51 */     ChunkSection chunksection = a();
/*     */     
/*  53 */     return (chunksection == null) ? 0 : chunksection.getYPosition();
/*     */   }
/*     */   
/*     */   Set<BlockPosition> c();
/*     */   
/*     */   ChunkSection[] getSections();
/*     */   
/*     */   Collection<Map.Entry<HeightMap.Type, HeightMap>> f();
/*     */   
/*     */   void a(HeightMap.Type paramType, long[] paramArrayOflong);
/*     */   
/*     */   HeightMap a(HeightMap.Type paramType);
/*     */   
/*     */   int getHighestBlock(HeightMap.Type paramType, int paramInt1, int paramInt2);
/*     */   
/*     */   ChunkCoordIntPair getPos();
/*     */   
/*     */   void setLastSaved(long paramLong);
/*     */   
/*     */   Map<StructureGenerator<?>, StructureStart<?>> h();
/*     */   
/*     */   void a(Map<StructureGenerator<?>, StructureStart<?>> paramMap);
/*     */   
/*     */   default boolean a(int i, int j) {
/*  77 */     if (i < 0) {
/*  78 */       i = 0;
/*     */     }
/*     */     
/*  81 */     if (j >= 256) {
/*  82 */       j = 255;
/*     */     }
/*     */     
/*  85 */     for (int k = i; k <= j; k += 16) {
/*  86 */       if (!ChunkSection.a(getSections()[k >> 4])) {
/*  87 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   BiomeStorage getBiomeIndex();
/*     */   
/*     */   void setNeedsSaving(boolean paramBoolean);
/*     */   
/*     */   boolean isNeedsSaving();
/*     */   
/*     */   ChunkStatus getChunkStatus();
/*     */   
/*     */   void removeTileEntity(BlockPosition paramBlockPosition);
/*     */   
/*     */   default void e(BlockPosition blockposition) {
/* 106 */     LogManager.getLogger().warn("Trying to mark a block for PostProcessing @ {}, but this operation is not supported.", blockposition);
/*     */   }
/*     */   
/*     */   ShortList[] l();
/*     */   
/*     */   default void a(short short0, int i) {
/* 112 */     a(l(), i).add(short0);
/*     */   }
/*     */   
/*     */   default void a(NBTTagCompound nbttagcompound) {
/* 116 */     LogManager.getLogger().warn("Trying to set a BlockEntity, but this operation is not supported.");
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   NBTTagCompound i(BlockPosition paramBlockPosition);
/*     */   
/*     */   @Nullable
/*     */   NBTTagCompound j(BlockPosition paramBlockPosition);
/*     */   
/*     */   Stream<BlockPosition> m();
/*     */   
/*     */   TickList<Block> n();
/*     */   
/*     */   TickList<FluidType> o();
/*     */   
/*     */   ChunkConverter p();
/*     */   
/*     */   void setInhabitedTime(long paramLong);
/*     */   
/*     */   long getInhabitedTime();
/*     */   
/*     */   static ShortList a(ShortList[] ashortlist, int i) {
/* 138 */     if (ashortlist[i] == null) {
/* 139 */       ashortlist[i] = (ShortList)new ShortArrayList();
/*     */     }
/*     */     
/* 142 */     return ashortlist[i];
/*     */   }
/*     */   
/*     */   boolean r();
/*     */   
/*     */   void b(boolean paramBoolean);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IChunkAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */