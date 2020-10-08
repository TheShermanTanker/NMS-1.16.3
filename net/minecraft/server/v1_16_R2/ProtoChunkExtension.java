/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.util.BitSet;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ProtoChunkExtension
/*     */   extends ProtoChunk {
/*     */   private final Chunk a;
/*     */   
/*     */   public ProtoChunkExtension(Chunk chunk) {
/*  14 */     super(chunk.getPos(), ChunkConverter.a, chunk.world);
/*  15 */     this.a = chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity getTileEntity(BlockPosition blockposition) {
/*  21 */     return this.a.getTileEntity(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getType(BlockPosition blockposition) {
/*  27 */     return this.a.getType(blockposition);
/*     */   }
/*     */   
/*     */   public final IBlockData getType(int x, int y, int z) {
/*  31 */     return this.a.getBlockData(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid getFluid(BlockPosition blockposition) {
/*  37 */     return this.a.getFluid(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public int J() {
/*  42 */     return this.a.J();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData setType(BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTileEntity(BlockPosition blockposition, TileEntity tileentity) {}
/*     */ 
/*     */   
/*     */   public void a(Entity entity) {}
/*     */ 
/*     */   
/*     */   public void a(ChunkStatus chunkstatus) {}
/*     */ 
/*     */   
/*     */   public ChunkSection[] getSections() {
/*  62 */     return this.a.getSections();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LightEngine e() {
/*  68 */     return this.a.e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(HeightMap.Type heightmap_type, long[] along) {}
/*     */   
/*     */   private HeightMap.Type c(HeightMap.Type heightmap_type) {
/*  75 */     return (heightmap_type == HeightMap.Type.WORLD_SURFACE_WG) ? HeightMap.Type.WORLD_SURFACE : ((heightmap_type == HeightMap.Type.OCEAN_FLOOR_WG) ? HeightMap.Type.OCEAN_FLOOR : heightmap_type);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHighestBlock(HeightMap.Type heightmap_type, int i, int j) {
/*  80 */     return this.a.getHighestBlock(c(heightmap_type), i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkCoordIntPair getPos() {
/*  85 */     return this.a.getPos();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastSaved(long i) {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StructureStart<?> a(StructureGenerator<?> structuregenerator) {
/*  94 */     return this.a.a(structuregenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(StructureGenerator<?> structuregenerator, StructureStart<?> structurestart) {}
/*     */ 
/*     */   
/*     */   public Map<StructureGenerator<?>, StructureStart<?>> h() {
/* 102 */     return this.a.h();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Map<StructureGenerator<?>, StructureStart<?>> map) {}
/*     */ 
/*     */   
/*     */   public LongSet b(StructureGenerator<?> structuregenerator) {
/* 110 */     return this.a.b(structuregenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(StructureGenerator<?> structuregenerator, long i) {}
/*     */ 
/*     */   
/*     */   public Map<StructureGenerator<?>, LongSet> v() {
/* 118 */     return this.a.v();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(Map<StructureGenerator<?>, LongSet> map) {}
/*     */ 
/*     */   
/*     */   public BiomeStorage getBiomeIndex() {
/* 126 */     return this.a.getBiomeIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNeedsSaving(boolean flag) {}
/*     */ 
/*     */   
/*     */   public boolean isNeedsSaving() {
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkStatus getChunkStatus() {
/* 139 */     return this.a.getChunkStatus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTileEntity(BlockPosition blockposition) {}
/*     */ 
/*     */   
/*     */   public void e(BlockPosition blockposition) {}
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound i(BlockPosition blockposition) {
/* 154 */     return this.a.i(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound j(BlockPosition blockposition) {
/* 160 */     return this.a.j(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BiomeStorage biomestorage) {}
/*     */ 
/*     */   
/*     */   public Stream<BlockPosition> m() {
/* 168 */     return this.a.m();
/*     */   }
/*     */ 
/*     */   
/*     */   public ProtoChunkTickList<Block> n() {
/* 173 */     return new ProtoChunkTickList<>(block -> block.getBlockData().isAir(), 
/*     */         
/* 175 */         getPos());
/*     */   }
/*     */ 
/*     */   
/*     */   public ProtoChunkTickList<FluidType> o() {
/* 180 */     return new ProtoChunkTickList<>(fluidtype -> (fluidtype == FluidTypes.EMPTY), 
/*     */         
/* 182 */         getPos());
/*     */   }
/*     */ 
/*     */   
/*     */   public BitSet a(WorldGenStage.Features worldgenstage_features) {
/* 187 */     throw (UnsupportedOperationException)SystemUtils.c(new UnsupportedOperationException("Meaningless in this context"));
/*     */   }
/*     */ 
/*     */   
/*     */   public BitSet b(WorldGenStage.Features worldgenstage_features) {
/* 192 */     throw (UnsupportedOperationException)SystemUtils.c(new UnsupportedOperationException("Meaningless in this context"));
/*     */   }
/*     */   
/*     */   public Chunk u() {
/* 196 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r() {
/* 201 */     return this.a.r();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(boolean flag) {
/* 206 */     this.a.b(flag);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ProtoChunkExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */