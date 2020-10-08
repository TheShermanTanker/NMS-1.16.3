/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ProtoChunk
/*     */   implements IChunkAccess
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger(); private final ChunkCoordIntPair b;
/*     */   private volatile boolean c;
/*     */   @Nullable
/*     */   private BiomeStorage d;
/*     */   @Nullable
/*     */   private volatile LightEngine e;
/*     */   private final Map<HeightMap.Type, HeightMap> f;
/*     */   private volatile ChunkStatus g;
/*     */   private final Map<BlockPosition, TileEntity> h;
/*     */   private final Map<BlockPosition, NBTTagCompound> i;
/*     */   private final ChunkSection[] j;
/*     */   private final List<NBTTagCompound> k;
/*     */   private final List<BlockPosition> l;
/*     */   private final ShortList[] m;
/*     */   private final Map<StructureGenerator<?>, StructureStart<?>> n;
/*     */   private final Map<StructureGenerator<?>, LongSet> o;
/*     */   private final ChunkConverter p;
/*     */   private final ProtoChunkTickList<Block> q;
/*     */   private final ProtoChunkTickList<FluidType> r;
/*     */   private long s;
/*     */   private final Map<WorldGenStage.Features, BitSet> t;
/*     */   private volatile boolean u;
/*     */   final World world;
/*     */   
/*     */   @Deprecated
/*     */   public ProtoChunk(ChunkCoordIntPair chunkcoordintpair, ChunkConverter chunkconverter) {
/*  52 */     this(chunkcoordintpair, chunkconverter, null);
/*     */   }
/*     */   public ProtoChunk(ChunkCoordIntPair chunkcoordintpair, ChunkConverter chunkconverter, World world) {
/*  55 */     this(chunkcoordintpair, chunkconverter, (ChunkSection[])null, new ProtoChunkTickList<>(block -> 
/*  56 */           (block == null || block.getBlockData().isAir()), chunkcoordintpair), new ProtoChunkTickList<>(fluidtype -> 
/*     */           
/*  58 */           (fluidtype == null || fluidtype == FluidTypes.EMPTY), chunkcoordintpair), world);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ProtoChunk(ChunkCoordIntPair chunkcoordintpair, ChunkConverter chunkconverter, @Nullable ChunkSection[] achunksection, ProtoChunkTickList<Block> protochunkticklist, ProtoChunkTickList<FluidType> protochunkticklist1) {
/*  63 */     this(chunkcoordintpair, chunkconverter, achunksection, protochunkticklist, protochunkticklist1, null);
/*     */   } public ProtoChunk(ChunkCoordIntPair chunkcoordintpair, ChunkConverter chunkconverter, @Nullable ChunkSection[] achunksection, ProtoChunkTickList<Block> protochunkticklist, ProtoChunkTickList<FluidType> protochunkticklist1, World world) {
/*  65 */     this.world = world;
/*     */     
/*  67 */     this.f = Maps.newEnumMap(HeightMap.Type.class);
/*  68 */     this.g = ChunkStatus.EMPTY;
/*  69 */     this.h = Maps.newHashMap();
/*  70 */     this.i = Maps.newHashMap();
/*  71 */     this.j = new ChunkSection[16];
/*  72 */     this.k = Lists.newArrayList();
/*  73 */     this.l = Lists.newArrayList();
/*  74 */     this.m = new ShortList[16];
/*  75 */     this.n = Maps.newHashMap();
/*  76 */     this.o = Maps.newHashMap();
/*  77 */     this.t = (Map<WorldGenStage.Features, BitSet>)new Object2ObjectArrayMap();
/*  78 */     this.b = chunkcoordintpair;
/*  79 */     this.p = chunkconverter;
/*  80 */     this.q = protochunkticklist;
/*  81 */     this.r = protochunkticklist1;
/*  82 */     if (achunksection != null) {
/*  83 */       if (this.j.length == achunksection.length) {
/*  84 */         System.arraycopy(achunksection, 0, this.j, 0, this.j.length);
/*     */       } else {
/*  86 */         LOGGER.warn("Could not set level chunk sections, array length is {} instead of {}", Integer.valueOf(achunksection.length), Integer.valueOf(this.j.length));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid getFluidIfLoaded(BlockPosition blockposition) {
/*  95 */     return getFluid(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/* 100 */     return getType(blockposition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getType(BlockPosition blockposition) {
/* 106 */     return getType(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */   }
/*     */   
/*     */   public IBlockData getType(int x, int y, int z) {
/* 110 */     if (y < 0 || y >= 256) {
/* 111 */       return Blocks.VOID_AIR.getBlockData();
/*     */     }
/* 113 */     ChunkSection chunksection = getSections()[y >> 4];
/* 114 */     return (chunksection == Chunk.EMPTY_CHUNK_SECTION || chunksection.c()) ? Blocks.AIR.getBlockData() : chunksection.getType(x & 0xF, y & 0xF, z & 0xF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid getFluid(BlockPosition blockposition) {
/* 121 */     int i = blockposition.getY();
/*     */     
/* 123 */     if (World.b(i)) {
/* 124 */       return FluidTypes.EMPTY.h();
/*     */     }
/* 126 */     ChunkSection chunksection = getSections()[i >> 4];
/*     */     
/* 128 */     return ChunkSection.a(chunksection) ? FluidTypes.EMPTY.h() : chunksection.b(blockposition.getX() & 0xF, i & 0xF, blockposition.getZ() & 0xF);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<BlockPosition> m() {
/* 134 */     return this.l.stream();
/*     */   }
/*     */   
/*     */   public ShortList[] w() {
/* 138 */     ShortList[] ashortlist = new ShortList[16];
/* 139 */     Iterator<BlockPosition> iterator = this.l.iterator();
/*     */     
/* 141 */     while (iterator.hasNext()) {
/* 142 */       BlockPosition blockposition = iterator.next();
/*     */       
/* 144 */       IChunkAccess.a(ashortlist, blockposition.getY() >> 4).add(l(blockposition));
/*     */     } 
/*     */     
/* 147 */     return ashortlist;
/*     */   }
/*     */   
/*     */   public void b(short short0, int i) {
/* 151 */     k(a(short0, i, this.b));
/*     */   }
/*     */   
/*     */   public void k(BlockPosition blockposition) {
/* 155 */     this.l.add(blockposition.immutableCopy());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData setType(BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 161 */     int i = blockposition.getX();
/* 162 */     int j = blockposition.getY();
/* 163 */     int k = blockposition.getZ();
/*     */     
/* 165 */     if (j >= 0 && j < 256) {
/* 166 */       if (this.j[j >> 4] == Chunk.a && iblockdata.a(Blocks.AIR)) {
/* 167 */         return iblockdata;
/*     */       }
/* 169 */       if (iblockdata.f() > 0) {
/* 170 */         this.l.add(new BlockPosition((i & 0xF) + getPos().d(), j, (k & 0xF) + getPos().e()));
/*     */       }
/*     */       
/* 173 */       ChunkSection chunksection = a(j >> 4);
/* 174 */       IBlockData iblockdata1 = chunksection.setType(i & 0xF, j & 0xF, k & 0xF, iblockdata);
/*     */       
/* 176 */       if (this.g.b(ChunkStatus.FEATURES) && iblockdata != iblockdata1 && (iblockdata.b(this, blockposition) != iblockdata1.b(this, blockposition) || iblockdata.f() != iblockdata1.f() || iblockdata.e() || iblockdata1.e())) {
/* 177 */         LightEngine lightengine = e();
/*     */         
/* 179 */         lightengine.a(blockposition);
/*     */       } 
/*     */       
/* 182 */       HeightMap.Type[] enumset = (getChunkStatus()).heightMaps;
/* 183 */       EnumSet<HeightMap.Type> enumset1 = null;
/*     */ 
/*     */       
/* 186 */       for (HeightMap.Type heightmap_type : enumset) {
/* 187 */         HeightMap heightmap = this.f.get(heightmap_type);
/*     */         
/* 189 */         if (heightmap == null) {
/* 190 */           if (enumset1 == null) {
/* 191 */             enumset1 = EnumSet.noneOf(HeightMap.Type.class);
/*     */           }
/*     */           
/* 194 */           enumset1.add(heightmap_type);
/*     */         } 
/*     */       } 
/*     */       
/* 198 */       if (enumset1 != null) {
/* 199 */         HeightMap.a(this, enumset1);
/*     */       }
/*     */ 
/*     */       
/* 203 */       for (HeightMap.Type heightmap_type : enumset)
/*     */       {
/* 205 */         ((HeightMap)this.f.get(heightmap_type)).a(i & 0xF, j, k & 0xF, iblockdata);
/*     */       }
/*     */       
/* 208 */       return iblockdata1;
/*     */     } 
/*     */     
/* 211 */     return Blocks.VOID_AIR.getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkSection a(int i) {
/* 216 */     if (this.j[i] == Chunk.a) {
/* 217 */       this.j[i] = new ChunkSection(i << 4, this, this.world, true);
/*     */     }
/*     */     
/* 220 */     return this.j[i];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTileEntity(BlockPosition blockposition, TileEntity tileentity) {
/* 225 */     tileentity.setPosition(blockposition);
/* 226 */     this.h.put(blockposition, tileentity);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPosition> c() {
/* 231 */     Set<BlockPosition> set = Sets.newHashSet(this.i.keySet());
/*     */     
/* 233 */     set.addAll(this.h.keySet());
/* 234 */     return set;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity getTileEntity(BlockPosition blockposition) {
/* 240 */     return this.h.get(blockposition);
/*     */   }
/*     */   
/*     */   public Map<BlockPosition, TileEntity> x() {
/* 244 */     return this.h;
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 248 */     this.k.add(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Entity entity) {
/* 253 */     if (!entity.isPassenger()) {
/* 254 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */       
/* 256 */       entity.d(nbttagcompound);
/* 257 */       b(nbttagcompound);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<NBTTagCompound> y() {
/* 262 */     return this.k;
/*     */   }
/*     */   
/*     */   public void a(BiomeStorage biomestorage) {
/* 266 */     this.d = biomestorage;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BiomeStorage getBiomeIndex() {
/* 272 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNeedsSaving(boolean flag) {
/* 277 */     this.c = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNeedsSaving() {
/* 282 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkStatus getChunkStatus() {
/* 287 */     return this.g;
/*     */   }
/*     */   
/*     */   public void a(ChunkStatus chunkstatus) {
/* 291 */     this.g = chunkstatus;
/* 292 */     setNeedsSaving(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkSection[] getSections() {
/* 297 */     return this.j;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LightEngine e() {
/* 302 */     return this.e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<Map.Entry<HeightMap.Type, HeightMap>> f() {
/* 307 */     return Collections.unmodifiableSet(this.f.entrySet());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(HeightMap.Type heightmap_type, long[] along) {
/* 312 */     a(heightmap_type).a(along);
/*     */   }
/*     */ 
/*     */   
/*     */   public HeightMap a(HeightMap.Type heightmap_type) {
/* 317 */     return this.f.computeIfAbsent(heightmap_type, heightmap_type1 -> new HeightMap(this, heightmap_type1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHighestBlock(HeightMap.Type heightmap_type, int i, int j) {
/* 324 */     HeightMap heightmap = this.f.get(heightmap_type);
/*     */     
/* 326 */     if (heightmap == null) {
/* 327 */       HeightMap.a(this, EnumSet.of(heightmap_type));
/* 328 */       heightmap = this.f.get(heightmap_type);
/*     */     } 
/*     */     
/* 331 */     return heightmap.a(i & 0xF, j & 0xF) - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkCoordIntPair getPos() {
/* 336 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastSaved(long i) {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StructureStart<?> a(StructureGenerator<?> structuregenerator) {
/* 345 */     return this.n.get(structuregenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(StructureGenerator<?> structuregenerator, StructureStart<?> structurestart) {
/* 350 */     this.n.put(structuregenerator, structurestart);
/* 351 */     this.c = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<StructureGenerator<?>, StructureStart<?>> h() {
/* 356 */     return Collections.unmodifiableMap(this.n);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Map<StructureGenerator<?>, StructureStart<?>> map) {
/* 361 */     this.n.clear();
/* 362 */     this.n.putAll(map);
/* 363 */     this.c = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public LongSet b(StructureGenerator<?> structuregenerator) {
/* 368 */     return this.o.computeIfAbsent(structuregenerator, structuregenerator1 -> new LongOpenHashSet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(StructureGenerator<?> structuregenerator, long i) {
/* 375 */     ((LongSet)this.o.computeIfAbsent(structuregenerator, structuregenerator1 -> new LongOpenHashSet()))
/*     */       
/* 377 */       .add(i);
/* 378 */     this.c = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<StructureGenerator<?>, LongSet> v() {
/* 383 */     return Collections.unmodifiableMap(this.o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(Map<StructureGenerator<?>, LongSet> map) {
/* 388 */     this.o.clear();
/* 389 */     this.o.putAll(map);
/* 390 */     this.c = true;
/*     */   }
/*     */   
/*     */   public static short l(BlockPosition blockposition) {
/* 394 */     int i = blockposition.getX();
/* 395 */     int j = blockposition.getY();
/* 396 */     int k = blockposition.getZ();
/* 397 */     int l = i & 0xF;
/* 398 */     int i1 = j & 0xF;
/* 399 */     int j1 = k & 0xF;
/*     */     
/* 401 */     return (short)(l | i1 << 4 | j1 << 8);
/*     */   }
/*     */   
/*     */   public static BlockPosition a(short short0, int i, ChunkCoordIntPair chunkcoordintpair) {
/* 405 */     int j = (short0 & 0xF) + (chunkcoordintpair.x << 4);
/* 406 */     int k = (short0 >>> 4 & 0xF) + (i << 4);
/* 407 */     int l = (short0 >>> 8 & 0xF) + (chunkcoordintpair.z << 4);
/*     */     
/* 409 */     return new BlockPosition(j, k, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void e(BlockPosition blockposition) {
/* 414 */     if (!World.isOutsideWorld(blockposition)) {
/* 415 */       IChunkAccess.a(this.m, blockposition.getY() >> 4).add(l(blockposition));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ShortList[] l() {
/* 422 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(short short0, int i) {
/* 427 */     IChunkAccess.a(this.m, i).add(short0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ProtoChunkTickList<Block> n() {
/* 432 */     return this.q;
/*     */   }
/*     */ 
/*     */   
/*     */   public ProtoChunkTickList<FluidType> o() {
/* 437 */     return this.r;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkConverter p() {
/* 442 */     return this.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInhabitedTime(long i) {
/* 447 */     this.s = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getInhabitedTime() {
/* 452 */     return this.s;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 457 */     this.i.put(new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z")), nbttagcompound);
/*     */   }
/*     */   
/*     */   public Map<BlockPosition, NBTTagCompound> z() {
/* 461 */     return Collections.unmodifiableMap(this.i);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound i(BlockPosition blockposition) {
/* 466 */     return this.i.get(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound j(BlockPosition blockposition) {
/* 472 */     TileEntity tileentity = getTileEntity(blockposition);
/*     */     
/* 474 */     return (tileentity != null) ? tileentity.save(new NBTTagCompound()) : this.i.get(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTileEntity(BlockPosition blockposition) {
/* 479 */     this.h.remove(blockposition);
/* 480 */     this.i.remove(blockposition);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BitSet a(WorldGenStage.Features worldgenstage_features) {
/* 485 */     return this.t.get(worldgenstage_features);
/*     */   }
/*     */   
/*     */   public BitSet b(WorldGenStage.Features worldgenstage_features) {
/* 489 */     return this.t.computeIfAbsent(worldgenstage_features, worldgenstage_features1 -> new BitSet(65536));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(WorldGenStage.Features worldgenstage_features, BitSet bitset) {
/* 495 */     this.t.put(worldgenstage_features, bitset);
/*     */   }
/*     */   
/*     */   public void a(LightEngine lightengine) {
/* 499 */     this.e = lightengine;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r() {
/* 504 */     return this.u;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(boolean flag) {
/* 509 */     this.u = flag;
/* 510 */     setNeedsSaving(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ProtoChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */