/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class RegionLimitedWorldAccess implements GeneratorAccessSeed {
/*  14 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final List<IChunkAccess> b;
/*     */   private final int c;
/*     */   private final int d;
/*     */   private final int e;
/*     */   private final WorldServer f;
/*     */   private final long g;
/*     */   private final WorldData h;
/*     */   
/*     */   public RegionLimitedWorldAccess(WorldServer worldserver, List<IChunkAccess> list) {
/*  24 */     this.k = new TickListWorldGen<>(blockposition -> z(blockposition).n());
/*     */ 
/*     */     
/*  27 */     this.l = new TickListWorldGen<>(blockposition -> z(blockposition).o());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     int i = MathHelper.floor(Math.sqrt(list.size()));
/*     */     
/*  38 */     if (i * i != list.size()) {
/*  39 */       throw (IllegalStateException)SystemUtils.c(new IllegalStateException("Cache size is not a square."));
/*     */     }
/*  41 */     ChunkCoordIntPair chunkcoordintpair = ((IChunkAccess)list.get(list.size() / 2)).getPos();
/*     */     
/*  43 */     this.b = list;
/*  44 */     this.c = chunkcoordintpair.x;
/*  45 */     this.d = chunkcoordintpair.z;
/*  46 */     this.e = i;
/*  47 */     this.f = worldserver;
/*  48 */     this.g = worldserver.getSeed();
/*  49 */     this.h = worldserver.getWorldData();
/*  50 */     this.i = worldserver.getRandom();
/*  51 */     this.j = worldserver.getDimensionManager();
/*  52 */     this.m = new BiomeManager(this, BiomeManager.a(this.g), worldserver.getDimensionManager().getGenLayerZoomer());
/*  53 */     this.n = ((IChunkAccess)list.get(0)).getPos();
/*  54 */     this.o = ((IChunkAccess)list.get(list.size() - 1)).getPos();
/*  55 */     this.structureManager = this.f.getStructureManager().a(this);
/*     */   }
/*     */   private final Random i; private final DimensionManager j; private final TickList<Block> k; private final TickList<FluidType> l; private final BiomeManager m; private final ChunkCoordIntPair n; private final ChunkCoordIntPair o; private final StructureManager structureManager;
/*     */   
/*     */   public int a() {
/*  60 */     return this.c;
/*     */   }
/*     */   
/*     */   public int b() {
/*  64 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChunkAccess getChunkAt(int i, int j) {
/*  69 */     return getChunkAt(i, j, ChunkStatus.EMPTY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IChunkAccess getChunkAt(int i, int j, ChunkStatus chunkstatus, boolean flag) {
/*     */     IChunkAccess ichunkaccess;
/*  77 */     if (isChunkLoaded(i, j)) {
/*  78 */       int k = i - this.n.x;
/*  79 */       int l = j - this.n.z;
/*     */       
/*  81 */       ichunkaccess = this.b.get(k + l * this.e);
/*  82 */       if (ichunkaccess.getChunkStatus().b(chunkstatus)) {
/*  83 */         return ichunkaccess;
/*     */       }
/*     */     } else {
/*  86 */       ichunkaccess = null;
/*     */     } 
/*     */     
/*  89 */     if (!flag) {
/*  90 */       return null;
/*     */     }
/*  92 */     LOGGER.error("Requested chunk : {} {}", Integer.valueOf(i), Integer.valueOf(j));
/*  93 */     LOGGER.error("Region bounds : {} {} | {} {}", Integer.valueOf(this.n.x), Integer.valueOf(this.n.z), Integer.valueOf(this.o.x), Integer.valueOf(this.o.z));
/*  94 */     if (ichunkaccess != null) {
/*  95 */       throw (RuntimeException)SystemUtils.c(new RuntimeException(String.format("Chunk is not of correct status. Expecting %s, got %s | %s %s", new Object[] { chunkstatus, ichunkaccess.getChunkStatus(), Integer.valueOf(i), Integer.valueOf(j) })));
/*     */     }
/*  97 */     throw (RuntimeException)SystemUtils.c(new RuntimeException(String.format("We are asking a region for a chunk out of bound | %s %s", new Object[] { Integer.valueOf(i), Integer.valueOf(j) })));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChunkLoaded(int i, int j) {
/* 104 */     return (i >= this.n.x && i <= this.o.x && j >= this.n.z && j <= this.o.z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IChunkAccess getChunkIfLoadedImmediately(int x, int z) {
/* 111 */     return getChunkAt(x, z, ChunkStatus.FULL, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/* 116 */     IChunkAccess chunk = getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/* 117 */     return (chunk == null) ? null : chunk.getType(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid getFluidIfLoaded(BlockPosition blockposition) {
/* 122 */     IChunkAccess chunk = getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/* 123 */     return (chunk == null) ? null : chunk.getFluid(blockposition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getType(BlockPosition blockposition) {
/* 129 */     return getChunkAt(blockposition.getX() >> 4, blockposition.getZ() >> 4).getType(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid getFluid(BlockPosition blockposition) {
/* 134 */     return z(blockposition).getFluid(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityHuman a(double d0, double d1, double d2, double d3, Predicate<Entity> predicate) {
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 145 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeManager d() {
/* 150 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeBase a(int i, int j, int k) {
/* 155 */     return this.f.a(i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   public LightEngine e() {
/* 160 */     return this.f.e();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, boolean flag, @Nullable Entity entity, int i) {
/* 165 */     IBlockData iblockdata = getType(blockposition);
/*     */     
/* 167 */     if (iblockdata.isAir()) {
/* 168 */       return false;
/*     */     }
/* 170 */     if (flag) {
/* 171 */       TileEntity tileentity = iblockdata.getBlock().isTileEntity() ? getTileEntity(blockposition) : null;
/*     */       
/* 173 */       Block.dropItems(iblockdata, this.f, blockposition, tileentity, entity, ItemStack.b);
/*     */     } 
/*     */     
/* 176 */     return a(blockposition, Blocks.AIR.getBlockData(), 3, i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity getTileEntity(BlockPosition blockposition) {
/* 183 */     IChunkAccess ichunkaccess = z(blockposition);
/* 184 */     TileEntity tileentity = ichunkaccess.getTileEntity(blockposition);
/*     */     
/* 186 */     if (tileentity != null) {
/* 187 */       return tileentity;
/*     */     }
/* 189 */     NBTTagCompound nbttagcompound = ichunkaccess.i(blockposition);
/* 190 */     IBlockData iblockdata = ichunkaccess.getType(blockposition);
/*     */     
/* 192 */     if (nbttagcompound != null) {
/* 193 */       if ("DUMMY".equals(nbttagcompound.getString("id"))) {
/* 194 */         Block block = iblockdata.getBlock();
/*     */         
/* 196 */         if (!(block instanceof ITileEntity)) {
/* 197 */           return null;
/*     */         }
/*     */         
/* 200 */         tileentity = ((ITileEntity)block).createTile(this.f);
/*     */       } else {
/* 202 */         tileentity = TileEntity.create(iblockdata, nbttagcompound);
/*     */       } 
/*     */       
/* 205 */       if (tileentity != null) {
/* 206 */         ichunkaccess.setTileEntity(blockposition, tileentity);
/* 207 */         return tileentity;
/*     */       } 
/*     */     } 
/*     */     
/* 211 */     if (iblockdata.getBlock() instanceof ITileEntity) {
/* 212 */       LOGGER.warn("Tried to access a block entity before it was created. {}", blockposition);
/*     */     }
/*     */     
/* 215 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, IBlockData iblockdata, int i, int j) {
/* 221 */     IChunkAccess ichunkaccess = z(blockposition);
/* 222 */     IBlockData iblockdata1 = ichunkaccess.setType(blockposition, iblockdata, false);
/*     */     
/* 224 */     if (iblockdata1 != null) {
/* 225 */       this.f.a(blockposition, iblockdata1, iblockdata);
/*     */     }
/*     */     
/* 228 */     Block block = iblockdata.getBlock();
/*     */     
/* 230 */     if (block.isTileEntity()) {
/* 231 */       if (ichunkaccess.getChunkStatus().getType() == ChunkStatus.Type.LEVELCHUNK) {
/* 232 */         ichunkaccess.setTileEntity(blockposition, ((ITileEntity)block).createTile(this));
/*     */       } else {
/* 234 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */         
/* 236 */         nbttagcompound.setInt("x", blockposition.getX());
/* 237 */         nbttagcompound.setInt("y", blockposition.getY());
/* 238 */         nbttagcompound.setInt("z", blockposition.getZ());
/* 239 */         nbttagcompound.setString("id", "DUMMY");
/* 240 */         ichunkaccess.a(nbttagcompound);
/*     */       } 
/* 242 */     } else if (iblockdata1 != null && iblockdata1.getBlock().isTileEntity()) {
/* 243 */       ichunkaccess.removeTileEntity(blockposition);
/*     */     } 
/*     */     
/* 246 */     if (iblockdata.q(this, blockposition)) {
/* 247 */       j(blockposition);
/*     */     }
/*     */     
/* 250 */     return true;
/*     */   }
/*     */   
/*     */   private void j(BlockPosition blockposition) {
/* 254 */     z(blockposition).e(blockposition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEntity(Entity entity) {
/* 260 */     return addEntity(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
/* 266 */     int i = MathHelper.floor(entity.locX() / 16.0D);
/* 267 */     int j = MathHelper.floor(entity.locZ() / 16.0D);
/*     */     
/* 269 */     getChunkAt(i, j).a(entity);
/* 270 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, boolean flag) {
/* 275 */     return setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldBorder getWorldBorder() {
/* 280 */     return this.f.getWorldBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean s_() {
/* 285 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public WorldServer getMinecraftWorld() {
/* 291 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRegistryCustom r() {
/* 296 */     return this.f.r();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldData getWorldData() {
/* 301 */     return this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   public DifficultyDamageScaler getDamageScaler(BlockPosition blockposition) {
/* 306 */     if (!isChunkLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4)) {
/* 307 */       throw new RuntimeException("We are asking a region for a chunk out of bound");
/*     */     }
/* 309 */     return new DifficultyDamageScaler(this.f.getDifficulty(), this.f.getDayTime(), 0L, this.f.ae());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider getChunkProvider() {
/* 315 */     return this.f.getChunkProvider();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/* 320 */     return this.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public TickList<Block> getBlockTickList() {
/* 325 */     return this.k;
/*     */   }
/*     */ 
/*     */   
/*     */   public TickList<FluidType> getFluidTickList() {
/* 330 */     return this.l;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeaLevel() {
/* 335 */     return this.f.getSeaLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public Random getRandom() {
/* 340 */     return this.i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(HeightMap.Type heightmap_type, int i, int j) {
/* 345 */     return getChunkAt(i >> 4, j >> 4).getHighestBlock(heightmap_type, i & 0xF, j & 0xF) + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSound(@Nullable EntityHuman entityhuman, BlockPosition blockposition, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) {}
/*     */ 
/*     */   
/*     */   public void addParticle(ParticleParam particleparam, double d0, double d1, double d2, double d3, double d4, double d5) {}
/*     */ 
/*     */   
/*     */   public void a(@Nullable EntityHuman entityhuman, int i, BlockPosition blockposition, int j) {}
/*     */ 
/*     */   
/*     */   public DimensionManager getDimensionManager() {
/* 359 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, Predicate<IBlockData> predicate) {
/* 364 */     return predicate.test(getType(blockposition));
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Entity> List<T> a(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super T> predicate) {
/* 369 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Entity> getEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super Entity> predicate) {
/* 374 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<EntityHuman> getPlayers() {
/* 379 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<? extends StructureStart<?>> a(SectionPosition sectionposition, StructureGenerator<?> structuregenerator) {
/* 384 */     return this.structureManager.a(sectionposition, structuregenerator);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegionLimitedWorldAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */