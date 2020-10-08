/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.Timing;
/*      */ import co.aikar.util.Counter;
/*      */ import com.destroystokyo.paper.exception.ServerInternalException;
/*      */ import com.destroystokyo.paper.util.maplist.EntityList;
/*      */ import com.destroystokyo.paper.util.misc.PooledLinkedHashSets;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.tuinity.tuinity.util.TickThread;
/*      */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*      */ import it.unimi.dsi.fastutil.longs.LongSet;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortListIterator;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.world.ChunkUnloadEvent;
/*      */ import org.bukkit.generator.BlockPopulator;
/*      */ 
/*      */ public class Chunk implements IChunkAccess {
/*   28 */   private static final Logger LOGGER = LogManager.getLogger(); @Nullable
/*      */   public static final ChunkSection a;
/*   30 */   public static final ChunkSection EMPTY_CHUNK_SECTION = a = null; private final ChunkSection[] sections;
/*      */   private BiomeStorage d;
/*      */   private final Map<BlockPosition, NBTTagCompound> e;
/*      */   public boolean loaded;
/*      */   public final WorldServer world;
/*      */   public final Map<HeightMap.Type, HeightMap> heightMap;
/*      */   private final ChunkConverter i;
/*      */   public final Map<BlockPosition, TileEntity> tileEntities;
/*      */   public final List<Entity>[] entitySlices;
/*      */   private final Map<StructureGenerator<?>, StructureStart<?>> l;
/*      */   private final Map<StructureGenerator<?>, LongSet> m;
/*      */   private final ShortList[] n;
/*      */   private TickList<Block> o;
/*      */   private TickList<FluidType> p;
/*      */   private boolean q;
/*      */   public long lastSaved;
/*      */   private volatile boolean s;
/*      */   private long inhabitedTime;
/*      */   @Nullable
/*      */   private Supplier<PlayerChunk.State> u;
/*      */   @Nullable
/*      */   private Consumer<Chunk> v;
/*      */   private final ChunkCoordIntPair loc;
/*      */   public final long coordinateKey;
/*      */   private volatile boolean x;
/*      */   
/*      */   public Chunk(World world, ChunkCoordIntPair chunkcoordintpair, BiomeStorage biomestorage) {
/*   57 */     this(world, chunkcoordintpair, biomestorage, ChunkConverter.a, TickListEmpty.b(), TickListEmpty.b(), 0L, (ChunkSection[])null, (Consumer<Chunk>)null);
/*      */   }
/*      */ 
/*      */   
/*   61 */   public final Counter<String> entityCounts = new Counter();
/*   62 */   public final Counter<String> tileEntityCounts = new Counter();
/*      */   private class TileEntityHashMap extends HashMap<BlockPosition, TileEntity> { private TileEntityHashMap() {}
/*      */     
/*      */     public TileEntity put(BlockPosition key, TileEntity value) {
/*   66 */       TileEntity replaced = super.put(key, value);
/*   67 */       if (replaced != null) {
/*   68 */         replaced.setCurrentChunk(null);
/*   69 */         Chunk.this.tileEntityCounts.decrement(replaced.getMinecraftKeyString());
/*      */       } 
/*   71 */       if (value != null) {
/*   72 */         value.setCurrentChunk(Chunk.this);
/*   73 */         Chunk.this.tileEntityCounts.increment(value.getMinecraftKeyString());
/*      */       } 
/*   75 */       return replaced;
/*      */     }
/*      */ 
/*      */     
/*      */     public TileEntity remove(Object key) {
/*   80 */       TileEntity removed = super.remove(key);
/*   81 */       if (removed != null) {
/*   82 */         removed.setCurrentChunk(null);
/*   83 */         Chunk.this.tileEntityCounts.decrement(removed.getMinecraftKeyString());
/*      */       } 
/*   85 */       return removed;
/*      */     } }
/*      */ 
/*      */ 
/*      */   
/*   90 */   private final int[] itemCounts = new int[16];
/*   91 */   private final int[] inventoryEntityCounts = new int[16];
/*      */ 
/*      */ 
/*      */   
/*   95 */   final EntityList[] hardCollidingEntities = new EntityList[16]; public org.bukkit.Chunk bukkitChunk; public boolean mustNotSave; public boolean needsDecoration; public final EntityList entities; public PlayerChunk playerChunk; static final int NEIGHBOUR_CACHE_RADIUS = 3; boolean loadedTicketLevel; private long neighbourChunksLoadedBitset;
/*      */   private final Chunk[] loadedNeighbourChunks;
/*      */   
/*   98 */   public Chunk(World world, ChunkCoordIntPair chunkcoordintpair, BiomeStorage biomestorage, ChunkConverter chunkconverter, TickList<Block> ticklist, TickList<FluidType> ticklist1, long i, @Nullable ChunkSection[] achunksection, @Nullable Consumer<Chunk> consumer) { for (int m = 0, len = this.hardCollidingEntities.length; m < len; m++) {
/*   99 */       this.hardCollidingEntities[m] = new EntityList();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  198 */     this.entities = new EntityList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  208 */     this.loadedNeighbourChunks = new Chunk[49]; this.sections = new ChunkSection[16]; this.e = Maps.newHashMap(); this.heightMap = Maps.newEnumMap(HeightMap.Type.class); this.tileEntities = new TileEntityHashMap(); this.l = Maps.newHashMap(); this.m = Maps.newHashMap(); this.n = new ShortList[16]; this.entitySlices = (List<Entity>[])new List[16]; this.world = (WorldServer)world; this.loc = chunkcoordintpair; this.coordinateKey = MCUtil.getCoordinateKey(chunkcoordintpair); this.i = chunkconverter; HeightMap.Type[] aheightmap_type = HeightMap.Type.values(); int j = aheightmap_type.length; for (int k = 0; k < j; k++) { HeightMap.Type heightmap_type = aheightmap_type[k]; if (ChunkStatus.FULL.h().contains(heightmap_type)) this.heightMap.put(heightmap_type, new HeightMap(this, heightmap_type));  }  for (int l = 0; l < this.entitySlices.length; l++) this.entitySlices[l] = (List<Entity>)new UnsafeList();  this.d = biomestorage; this.o = ticklist; this.p = ticklist1; this.inhabitedTime = i; this.v = consumer; if (achunksection != null)
/*      */       if (this.sections.length == achunksection.length) { System.arraycopy(achunksection, 0, this.sections, 0, this.sections.length); } else { LOGGER.warn("Could not set level chunk sections, array length is {} instead of {}", Integer.valueOf(achunksection.length), Integer.valueOf(this.sections.length)); }   this.bukkitChunk = (org.bukkit.Chunk)new CraftChunk(this); }
/*      */   public final void getHardCollidingEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, List<Entity> into, Predicate<Entity> predicate) { int min = MathHelper.floor((axisalignedbb.minY - 2.0D) / 16.0D); int max = MathHelper.floor((axisalignedbb.maxY + 2.0D) / 16.0D); min = MathHelper.clamp(min, 0, this.hardCollidingEntities.length - 1); max = MathHelper.clamp(max, 0, this.hardCollidingEntities.length - 1); for (int k = min; k <= max; k++) { EntityList entityList = this.hardCollidingEntities[k]; Entity[] entities = entityList.getRawData(); for (int i = 0, len = entityList.size(); i < len; i++) { Entity entity1 = entities[i]; if (!entity1.shouldBeRemoved)
/*      */           if (entity1 != entity && entity1.getBoundingBox().intersects(axisalignedbb)) { if (predicate == null || predicate.test(entity1))
/*      */               into.add(entity1);  if (entity1 instanceof EntityEnderDragon) { EntityComplexPart[] aentitycomplexpart = ((EntityEnderDragon)entity1).children; int l = aentitycomplexpart.length; for (int i1 = 0; i1 < l; i1++) { EntityComplexPart entitycomplexpart = aentitycomplexpart[i1]; if (entitycomplexpart != entity && entitycomplexpart.getBoundingBox().intersects(axisalignedbb) && (predicate == null || predicate.test(entitycomplexpart)))
/*  213 */                   into.add(entitycomplexpart);  }  }  }   }  }  } private static int getNeighbourIndex(int relativeX, int relativeZ) { return relativeX + relativeZ * 7 + 24; }
/*      */   public org.bukkit.Chunk getBukkitChunk() { return this.bukkitChunk; } public static int getNeighbourCacheRadius() {
/*      */     return 3;
/*      */   } public final Chunk getRelativeNeighbourIfLoaded(int relativeX, int relativeZ) {
/*  217 */     return this.loadedNeighbourChunks[getNeighbourIndex(relativeX, relativeZ)];
/*      */   }
/*      */   
/*      */   public final boolean isNeighbourLoaded(int relativeX, int relativeZ) {
/*  221 */     return ((this.neighbourChunksLoadedBitset & 1L << getNeighbourIndex(relativeX, relativeZ)) != 0L);
/*      */   }
/*      */   
/*      */   public final void setNeighbourLoaded(int relativeX, int relativeZ, Chunk chunk) {
/*  225 */     if (chunk == null) {
/*  226 */       throw new IllegalArgumentException("Chunk must be non-null, neighbour: (" + relativeX + "," + relativeZ + "), chunk: " + this.loc);
/*      */     }
/*  228 */     long before = this.neighbourChunksLoadedBitset;
/*  229 */     int index = getNeighbourIndex(relativeX, relativeZ);
/*  230 */     this.loadedNeighbourChunks[index] = chunk;
/*  231 */     this.neighbourChunksLoadedBitset |= 1L << index;
/*  232 */     onNeighbourChange(before, this.neighbourChunksLoadedBitset);
/*      */   }
/*      */   
/*      */   public final void setNeighbourUnloaded(int relativeX, int relativeZ) {
/*  236 */     long before = this.neighbourChunksLoadedBitset;
/*  237 */     int index = getNeighbourIndex(relativeX, relativeZ);
/*  238 */     this.loadedNeighbourChunks[index] = null;
/*  239 */     this.neighbourChunksLoadedBitset &= 1L << index ^ 0xFFFFFFFFFFFFFFFFL;
/*  240 */     onNeighbourChange(before, this.neighbourChunksLoadedBitset);
/*      */   }
/*      */   
/*      */   public final void resetNeighbours() {
/*  244 */     long before = this.neighbourChunksLoadedBitset;
/*  245 */     this.neighbourChunksLoadedBitset = 0L;
/*  246 */     Arrays.fill((Object[])this.loadedNeighbourChunks, (Object)null);
/*  247 */     onNeighbourChange(before, 0L);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onNeighbourChange(long bitsetBefore, long bitsetAfter) {
/*  252 */     ChunkProviderServer chunkProviderServer = this.world.getChunkProvider();
/*  253 */     PlayerChunkMap chunkMap = chunkProviderServer.playerChunkMap;
/*      */     
/*  255 */     if (!areNeighboursLoaded(bitsetBefore, 2) && areNeighboursLoaded(bitsetAfter, 2) && 
/*  256 */       chunkMap.playerViewDistanceTickMap.getObjectsInRange(this.coordinateKey) != null)
/*      */     {
/*  258 */       chunkProviderServer.serverThreadQueue.execute(() -> {
/*      */             if (areNeighboursLoaded(2) && chunkMap.playerViewDistanceTickMap.getObjectsInRange(this.coordinateKey) != null) {
/*      */               chunkProviderServer.addTicketAtLevel(TicketType.PLAYER, this.loc, 31, this.loc);
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  268 */     if (!areNeighboursLoaded(bitsetBefore, 1) && areNeighboursLoaded(bitsetAfter, 1) && 
/*  269 */       chunkMap.playerViewDistanceBroadcastMap.getObjectsInRange(this.coordinateKey) != null)
/*      */     {
/*  271 */       chunkMap.mailboxMain.a(ChunkTaskQueueSorter.a(chunkMap.getUpdatingChunk(this.coordinateKey), () -> {
/*      */               if (!areNeighboursLoaded(1)) {
/*      */                 return;
/*      */               }
/*      */               PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> inRange = chunkMap.playerViewDistanceBroadcastMap.getObjectsInRange(this.coordinateKey);
/*      */               if (inRange == null) {
/*      */                 return;
/*      */               }
/*      */               Object[] backingSet = inRange.getBackingSet();
/*      */               Packet[] chunkPackets = new Packet[10];
/*      */               int index = 0;
/*      */               int len = backingSet.length;
/*      */               while (index < len) {
/*      */                 Object temp = backingSet[index];
/*      */                 if (temp instanceof EntityPlayer) {
/*      */                   EntityPlayer player = (EntityPlayer)temp;
/*      */                   chunkMap.sendChunk(player, (Packet<?>[])chunkPackets, this);
/*      */                 } 
/*      */                 index++;
/*      */               } 
/*      */             }));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isAnyNeighborsLoaded() {
/*  299 */     return (this.neighbourChunksLoadedBitset != 0L);
/*      */   }
/*      */   public final boolean areNeighboursLoaded(int radius) {
/*  302 */     return areNeighboursLoaded(this.neighbourChunksLoadedBitset, radius);
/*      */   }
/*      */   public static boolean areNeighboursLoaded(long bitset, int radius) {
/*      */     long mask;
/*      */     int dx;
/*  307 */     switch (radius) {
/*      */       case 0:
/*  309 */         return ((bitset & 1L << getNeighbourIndex(0, 0)) != 0L);
/*      */       
/*      */       case 1:
/*  312 */         mask = 0L;
/*  313 */         for (dx = -1; dx <= 1; dx++) {
/*  314 */           for (int dz = -1; dz <= 1; dz++) {
/*  315 */             mask |= 1L << getNeighbourIndex(dx, dz);
/*      */           }
/*      */         } 
/*  318 */         return ((bitset & mask) == mask);
/*      */       
/*      */       case 2:
/*  321 */         mask = 0L;
/*  322 */         for (dx = -2; dx <= 2; dx++) {
/*  323 */           for (int dz = -2; dz <= 2; dz++) {
/*  324 */             mask |= 1L << getNeighbourIndex(dx, dz);
/*      */           }
/*      */         } 
/*  327 */         return ((bitset & mask) == mask);
/*      */       
/*      */       case 3:
/*  330 */         mask = 0L;
/*  331 */         for (dx = -3; dx <= 3; dx++) {
/*  332 */           for (int dz = -3; dz <= 3; dz++) {
/*  333 */             mask |= 1L << getNeighbourIndex(dx, dz);
/*      */           }
/*      */         } 
/*  336 */         return ((bitset & mask) == mask);
/*      */     } 
/*      */ 
/*      */     
/*  340 */     throw new IllegalArgumentException("Radius not recognized: " + radius);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk(World world, ProtoChunk protochunk) {
/*  346 */     this(world, protochunk.getPos(), protochunk.getBiomeIndex(), protochunk.p(), protochunk.n(), protochunk.o(), protochunk.getInhabitedTime(), protochunk.getSections(), (Consumer<Chunk>)null);
/*  347 */     Iterator<NBTTagCompound> iterator = protochunk.y().iterator();
/*      */     
/*  349 */     while (iterator.hasNext()) {
/*  350 */       NBTTagCompound nbttagcompound = iterator.next();
/*      */       
/*  352 */       EntityTypes.a(nbttagcompound, world, entity -> {
/*      */             a(entity);
/*      */             
/*      */             return entity;
/*      */           });
/*      */     } 
/*  358 */     iterator = protochunk.x().values().iterator();
/*      */     
/*  360 */     while (iterator.hasNext()) {
/*  361 */       TileEntity tileentity = (TileEntity)iterator.next();
/*      */       
/*  363 */       a(tileentity);
/*      */     } 
/*      */     
/*  366 */     this.e.putAll(protochunk.z());
/*      */     
/*  368 */     for (int i = 0; i < (protochunk.l()).length; i++) {
/*  369 */       this.n[i] = protochunk.l()[i];
/*      */     }
/*      */     
/*  372 */     a(protochunk.h());
/*  373 */     b(protochunk.v());
/*  374 */     iterator = (Iterator)protochunk.f().iterator();
/*      */     
/*  376 */     while (iterator.hasNext()) {
/*  377 */       Map.Entry<HeightMap.Type, HeightMap> entry = (Map.Entry<HeightMap.Type, HeightMap>)iterator.next();
/*      */       
/*  379 */       if (ChunkStatus.FULL.h().contains(entry.getKey())) {
/*  380 */         a(entry.getKey()).copyFrom(entry.getValue());
/*      */       }
/*      */     } 
/*      */     
/*  384 */     b(protochunk.r());
/*  385 */     this.s = true;
/*  386 */     this.needsDecoration = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public HeightMap a(HeightMap.Type heightmap_type) {
/*  391 */     return this.heightMap.computeIfAbsent(heightmap_type, heightmap_type1 -> new HeightMap(this, heightmap_type1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<BlockPosition> c() {
/*  398 */     Set<BlockPosition> set = Sets.newHashSet(this.e.keySet());
/*      */     
/*  400 */     set.addAll(this.tileEntities.keySet());
/*  401 */     return set;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkSection[] getSections() {
/*  406 */     return this.sections;
/*      */   }
/*      */   
/*      */   public final IBlockData getBlockData(BlockPosition pos) {
/*  410 */     return getBlockData(pos.getX(), pos.getY(), pos.getZ());
/*      */   } public IBlockData getType(BlockPosition blockposition) {
/*  412 */     return getBlockData(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */   }
/*      */   
/*      */   public IBlockData getType(int x, int y, int z) {
/*  416 */     return getBlockData(x, y, z);
/*      */   }
/*      */   
/*      */   public final IBlockData getBlockData(int x, int y, int z) {
/*  420 */     int i = y >> 4;
/*  421 */     if (y < 0 || i >= this.sections.length || this.sections[i] == null || (this.sections[i]).nonEmptyBlockCount == 0) {
/*  422 */       return Blocks.AIR.getBlockData();
/*      */     }
/*      */     
/*  425 */     return (this.sections[i]).blockIds.a((y & 0xF) << 8 | (z & 0xF) << 4 | x & 0xF);
/*      */   }
/*      */ 
/*      */   
/*      */   public IBlockData getBlockData_unused(int i, int j, int k) {
/*  430 */     if (this.world.isDebugWorld()) {
/*  431 */       IBlockData iblockdata = null;
/*      */       
/*  433 */       if (j == 60) {
/*  434 */         iblockdata = Blocks.BARRIER.getBlockData();
/*      */       }
/*      */       
/*  437 */       if (j == 70) {
/*  438 */         iblockdata = ChunkProviderDebug.b(i, k);
/*      */       }
/*      */       
/*  441 */       return (iblockdata == null) ? Blocks.AIR.getBlockData() : iblockdata;
/*      */     } 
/*      */     try {
/*  444 */       if (j >= 0 && j >> 4 < this.sections.length) {
/*  445 */         ChunkSection chunksection = this.sections[j >> 4];
/*      */         
/*  447 */         if (!ChunkSection.a(chunksection)) {
/*  448 */           return chunksection.getType(i & 0xF, j & 0xF, k & 0xF);
/*      */         }
/*      */       } 
/*      */       
/*  452 */       return Blocks.AIR.getBlockData();
/*  453 */     } catch (Throwable throwable) {
/*  454 */       CrashReport crashreport = CrashReport.a(throwable, "Getting block state");
/*  455 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being got");
/*      */       
/*  457 */       crashreportsystemdetails.a("Location", () -> CrashReportSystemDetails.a(i, j, k));
/*      */ 
/*      */       
/*  460 */       throw new ReportedException(crashreport);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Fluid getFluidIfLoaded(BlockPosition blockposition) {
/*  468 */     return getFluid(blockposition);
/*      */   }
/*      */ 
/*      */   
/*      */   public IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/*  473 */     return getType(blockposition);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Fluid getFluid(BlockPosition blockposition) {
/*  479 */     return a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Fluid a(int i, int j, int k) {
/*  485 */     int index = j >> 4;
/*  486 */     if (index >= 0 && index < this.sections.length) {
/*  487 */       ChunkSection chunksection = this.sections[index];
/*      */       
/*  489 */       if (chunksection != null) {
/*  490 */         return ((IBlockData)chunksection.blockIds.a((j & 0xF) << 8 | (k & 0xF) << 4 | i & 0xF)).getFluid();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  495 */     return FluidTypes.EMPTY.h();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public IBlockData setType(BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  512 */     return setType(blockposition, iblockdata, flag, true);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public IBlockData setType(BlockPosition blockposition, IBlockData iblockdata, boolean flag, boolean doPlace) {
/*  518 */     int i = blockposition.getX() & 0xF;
/*  519 */     int j = blockposition.getY();
/*  520 */     int k = blockposition.getZ() & 0xF;
/*  521 */     ChunkSection chunksection = this.sections[j >> 4];
/*      */     
/*  523 */     if (chunksection == a) {
/*  524 */       if (iblockdata.isAir()) {
/*  525 */         return null;
/*      */       }
/*      */       
/*  528 */       chunksection = new ChunkSection(j >> 4 << 4, this, this.world, true);
/*  529 */       this.sections[j >> 4] = chunksection;
/*      */     } 
/*      */     
/*  532 */     boolean flag1 = chunksection.c();
/*  533 */     IBlockData iblockdata1 = chunksection.setType(i, j & 0xF, k, iblockdata);
/*      */     
/*  535 */     if (iblockdata1 == iblockdata) {
/*  536 */       return null;
/*      */     }
/*  538 */     Block block = iblockdata.getBlock();
/*  539 */     Block block1 = iblockdata1.getBlock();
/*      */     
/*  541 */     ((HeightMap)this.heightMap.get(HeightMap.Type.MOTION_BLOCKING)).a(i, j, k, iblockdata);
/*  542 */     ((HeightMap)this.heightMap.get(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES)).a(i, j, k, iblockdata);
/*  543 */     ((HeightMap)this.heightMap.get(HeightMap.Type.OCEAN_FLOOR)).a(i, j, k, iblockdata);
/*  544 */     ((HeightMap)this.heightMap.get(HeightMap.Type.WORLD_SURFACE)).a(i, j, k, iblockdata);
/*  545 */     boolean flag2 = chunksection.c();
/*      */     
/*  547 */     if (flag1 != flag2) {
/*  548 */       this.world.getChunkProvider().getLightEngine().a(blockposition, flag2);
/*      */     }
/*      */     
/*  551 */     if (!this.world.isClientSide) {
/*  552 */       iblockdata1.remove(this.world, blockposition, iblockdata, flag);
/*  553 */     } else if (block1 != block && block1 instanceof ITileEntity) {
/*  554 */       this.world.removeTileEntity(blockposition);
/*      */     } 
/*      */     
/*  557 */     if (!chunksection.getType(i, j & 0xF, k).a(block)) {
/*  558 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  562 */     if (block1 instanceof ITileEntity) {
/*  563 */       TileEntity tileentity = a(blockposition, EnumTileEntityState.CHECK);
/*  564 */       if (tileentity != null) {
/*  565 */         tileentity.invalidateBlockCache();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  570 */     if (!this.world.isClientSide && doPlace && (!this.world.captureBlockStates || block instanceof BlockTileEntity)) {
/*  571 */       iblockdata.onPlace(this.world, blockposition, iblockdata1, flag);
/*      */     }
/*      */     
/*  574 */     if (block instanceof ITileEntity) {
/*  575 */       TileEntity tileentity = a(blockposition, EnumTileEntityState.CHECK);
/*  576 */       if (tileentity == null) {
/*  577 */         tileentity = ((ITileEntity)block).createTile(this.world);
/*  578 */         this.world.setTileEntity(blockposition, tileentity);
/*      */       } else {
/*  580 */         tileentity.invalidateBlockCache();
/*      */       } 
/*      */     } 
/*      */     
/*  584 */     this.s = true;
/*  585 */     return iblockdata1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public LightEngine e() {
/*  592 */     return this.world.getChunkProvider().getLightEngine();
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(Entity entity) {
/*  597 */     TickThread.softEnsureTickThread("Async addEntity call");
/*  598 */     this.q = true;
/*  599 */     int i = MathHelper.floor(entity.locX() / 16.0D);
/*  600 */     int j = MathHelper.floor(entity.locZ() / 16.0D);
/*      */     
/*  602 */     if (i != this.loc.x || j != this.loc.z) {
/*  603 */       LOGGER.warn("Wrong location! ({}, {}) should be ({}, {}), {}", Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(this.loc.x), Integer.valueOf(this.loc.z), entity);
/*  604 */       entity.dead = true;
/*      */       
/*      */       return;
/*      */     } 
/*  608 */     int k = MathHelper.floor(entity.locY() / 16.0D);
/*      */     
/*  610 */     if (k < 0) {
/*  611 */       k = 0;
/*      */     }
/*      */     
/*  614 */     if (k >= this.entitySlices.length) {
/*  615 */       k = this.entitySlices.length - 1;
/*      */     }
/*      */     
/*  618 */     List<Entity> nextSlice = this.entitySlices[k];
/*  619 */     List<Entity> currentSlice = entity.entitySlice;
/*  620 */     if (nextSlice == currentSlice) {
/*  621 */       if (World.DEBUG_ENTITIES) MinecraftServer.LOGGER.warn("Entity was already in this chunk!" + entity, new Throwable()); 
/*      */       return;
/*      */     } 
/*  624 */     if (currentSlice != null && currentSlice.contains(entity)) {
/*      */       
/*  626 */       if (World.DEBUG_ENTITIES) MinecraftServer.LOGGER.warn("Entity is still in another chunk!" + entity, new Throwable()); 
/*  627 */       Chunk chunk = entity.getCurrentChunk();
/*  628 */       if (chunk != null) {
/*  629 */         chunk.removeEntity(entity);
/*      */       } else {
/*  631 */         removeEntity(entity);
/*      */       } 
/*  633 */       currentSlice.remove(entity);
/*      */     } 
/*      */ 
/*      */     
/*  637 */     if (!entity.inChunk || entity.getCurrentChunk() != this) this.entityCounts.increment(entity.getMinecraftKeyString()); 
/*  638 */     entity.inChunk = true;
/*  639 */     entity.setCurrentChunk(this);
/*  640 */     entity.chunkX = this.loc.x;
/*  641 */     entity.chunkY = k;
/*  642 */     entity.chunkZ = this.loc.z;
/*  643 */     this.entities.add(entity);
/*  644 */     this.entitySlices[k].add(entity); if (entity.hardCollides()) this.hardCollidingEntities[k].add(entity);
/*      */     
/*  646 */     if (entity instanceof EntityItem) {
/*  647 */       this.itemCounts[k] = this.itemCounts[k] + 1;
/*  648 */     } else if (entity instanceof IInventory) {
/*  649 */       this.inventoryEntityCounts[k] = this.inventoryEntityCounts[k] + 1;
/*      */     } 
/*      */     
/*  652 */     entity.entitySlice = this.entitySlices[k];
/*  653 */     markDirty();
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(HeightMap.Type heightmap_type, long[] along) {
/*  658 */     ((HeightMap)this.heightMap.get(heightmap_type)).a(along);
/*      */   }
/*      */   public final void removeEntity(Entity entity) {
/*  661 */     b(entity);
/*      */   } public void b(Entity entity) {
/*  663 */     a(entity, entity.chunkY);
/*      */   }
/*      */   
/*      */   public void a(Entity entity, int i) {
/*  667 */     TickThread.softEnsureTickThread("Async removeEntity call");
/*  668 */     if (i < 0) {
/*  669 */       i = 0;
/*      */     }
/*      */     
/*  672 */     if (i >= this.entitySlices.length) {
/*  673 */       i = this.entitySlices.length - 1;
/*      */     }
/*      */ 
/*      */     
/*  677 */     if (entity.currentChunk != null && entity.currentChunk.get() == this) entity.setCurrentChunk(null); 
/*  678 */     if (this.entitySlices[i] == entity.entitySlice) {
/*  679 */       entity.entitySlice = null;
/*  680 */       entity.inChunk = false;
/*      */     } 
/*  682 */     if (entity.hardCollides()) this.hardCollidingEntities[i].remove(entity);  if (!this.entitySlices[i].remove(entity)) {
/*      */       return;
/*      */     }
/*  685 */     if (entity instanceof EntityItem) {
/*  686 */       this.itemCounts[i] = this.itemCounts[i] - 1;
/*  687 */     } else if (entity instanceof IInventory) {
/*  688 */       this.inventoryEntityCounts[i] = this.inventoryEntityCounts[i] - 1;
/*      */     } 
/*  690 */     this.entityCounts.decrement(entity.getMinecraftKeyString());
/*  691 */     markDirty();
/*      */     
/*  693 */     this.entities.remove(entity);
/*      */   }
/*      */   public final int getHighestBlockY(HeightMap.Type heightmap_type, int i, int j) {
/*  696 */     return getHighestBlock(heightmap_type, i, j) + 1;
/*      */   } public int getHighestBlock(HeightMap.Type heightmap_type, int i, int j) {
/*  698 */     return ((HeightMap)this.heightMap.get(heightmap_type)).a(i & 0xF, j & 0xF) - 1;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   private TileEntity k(BlockPosition blockposition) {
/*  703 */     IBlockData iblockdata = getType(blockposition);
/*  704 */     Block block = iblockdata.getBlock();
/*      */     
/*  706 */     return !block.isTileEntity() ? null : ((ITileEntity)block).createTile(this.world);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public TileEntity getTileEntity(BlockPosition blockposition) {
/*  712 */     return a(blockposition, EnumTileEntityState.CHECK);
/*      */   } @Nullable
/*      */   public final TileEntity getTileEntityImmediately(BlockPosition pos) {
/*  715 */     return a(pos, EnumTileEntityState.IMMEDIATE);
/*      */   }
/*      */   @Nullable
/*      */   public TileEntity a(BlockPosition blockposition, EnumTileEntityState chunk_enumtileentitystate) {
/*  719 */     TileEntity tileentity = this.world.capturedTileEntities.get(blockposition);
/*  720 */     if (tileentity == null) {
/*  721 */       tileentity = this.tileEntities.get(blockposition);
/*      */     }
/*      */ 
/*      */     
/*  725 */     if (tileentity == null) {
/*  726 */       NBTTagCompound nbttagcompound = this.e.remove(blockposition);
/*      */       
/*  728 */       if (nbttagcompound != null) {
/*  729 */         TileEntity tileentity1 = a(blockposition, nbttagcompound);
/*      */         
/*  731 */         if (tileentity1 != null) {
/*  732 */           return tileentity1;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  737 */     if (tileentity == null) {
/*  738 */       if (chunk_enumtileentitystate == EnumTileEntityState.IMMEDIATE) {
/*  739 */         tileentity = k(blockposition);
/*  740 */         this.world.setTileEntity(blockposition, tileentity);
/*      */       } 
/*  742 */     } else if (tileentity.isRemoved()) {
/*  743 */       this.tileEntities.remove(blockposition);
/*  744 */       return null;
/*      */     } 
/*      */     
/*  747 */     return tileentity;
/*      */   }
/*      */   
/*      */   public void a(TileEntity tileentity) {
/*  751 */     setTileEntity(tileentity.getPosition(), tileentity);
/*  752 */     if (this.loaded || this.world.s_()) {
/*  753 */       this.world.setTileEntity(tileentity.getPosition(), tileentity);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTileEntity(BlockPosition blockposition, TileEntity tileentity) {
/*  760 */     if (getType(blockposition).getBlock() instanceof ITileEntity) {
/*  761 */       tileentity.setLocation(this.world, blockposition);
/*  762 */       tileentity.r();
/*  763 */       TileEntity tileentity1 = this.tileEntities.put(blockposition.immutableCopy(), tileentity);
/*      */       
/*  765 */       if (tileentity1 != null && tileentity1 != tileentity) {
/*  766 */         tileentity1.al_();
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  771 */     else if (tileentity instanceof TileEntityMobSpawner && !(getBlockData(blockposition.getX(), blockposition.getY(), blockposition.getZ()).getBlock() instanceof BlockMobSpawner)) {
/*  772 */       this.tileEntities.remove(blockposition);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  779 */       ServerInternalException e = new ServerInternalException("Attempted to place a tile entity (" + tileentity + ") at " + tileentity.position.getX() + "," + tileentity.position.getY() + "," + tileentity.position.getZ() + " (" + getType(blockposition) + ") where there was no entity tile!\nChunk coordinates: " + (this.loc.x * 16) + "," + (this.loc.z * 16));
/*      */       
/*  781 */       e.printStackTrace();
/*  782 */       ServerInternalException.reportInternalException((Throwable)e);
/*      */       
/*  784 */       if (this.world.paperConfig.removeCorruptTEs) {
/*  785 */         removeTileEntity(tileentity.getPosition());
/*  786 */         markDirty();
/*  787 */         Bukkit.getLogger().info("Removing corrupt tile entity");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(NBTTagCompound nbttagcompound) {
/*  796 */     this.e.put(new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z")), nbttagcompound);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound j(BlockPosition blockposition) {
/*  802 */     TileEntity tileentity = getTileEntity(blockposition);
/*      */ 
/*      */     
/*  805 */     if (tileentity != null && !tileentity.isRemoved()) {
/*  806 */       NBTTagCompound nBTTagCompound = tileentity.save(new NBTTagCompound());
/*  807 */       nBTTagCompound.setBoolean("keepPacked", false);
/*  808 */       return nBTTagCompound;
/*      */     } 
/*  810 */     NBTTagCompound nbttagcompound = this.e.get(blockposition);
/*  811 */     if (nbttagcompound != null) {
/*  812 */       nbttagcompound = nbttagcompound.clone();
/*  813 */       nbttagcompound.setBoolean("keepPacked", true);
/*      */     } 
/*      */     
/*  816 */     return nbttagcompound;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeTileEntity(BlockPosition blockposition) {
/*  822 */     if (this.loaded || this.world.s_()) {
/*  823 */       TileEntity tileentity = this.tileEntities.remove(blockposition);
/*      */       
/*  825 */       if (tileentity != null) {
/*  826 */         tileentity.al_();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void addEntities() {
/*  833 */     if (this.v != null) {
/*  834 */       this.v.accept(this);
/*  835 */       this.v = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadCallback()
/*      */   {
/*  843 */     int chunkX = this.loc.x;
/*  844 */     int chunkZ = this.loc.z;
/*  845 */     ChunkProviderServer chunkProvider = this.world.getChunkProvider();
/*      */     
/*  847 */     for (int dx = -3; dx <= 3; dx++) {
/*  848 */       for (int dz = -3; dz <= 3; dz++) {
/*  849 */         Chunk neighbour = chunkProvider.getChunkAtIfLoadedMainThreadNoCache(chunkX + dx, chunkZ + dz);
/*  850 */         if (neighbour != null) {
/*  851 */           neighbour.setNeighbourLoaded(-dx, -dz, this);
/*      */           
/*  853 */           setNeighbourLoaded(dx, dz, neighbour);
/*      */         } 
/*      */       } 
/*      */     } 
/*  857 */     setNeighbourLoaded(0, 0, this);
/*  858 */     this.loadedTicketLevel = true;
/*      */     
/*  860 */     CraftServer craftServer = this.world.getServer();
/*  861 */     this.world.getChunkProvider().addLoadedChunk(this);
/*  862 */     if (craftServer != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  868 */       craftServer.getPluginManager().callEvent((Event)new ChunkLoadEvent(this.bukkitChunk, this.needsDecoration));
/*      */       
/*  870 */       if (this.needsDecoration) {
/*  871 */         Timing ignored = this.world.timings.chunkLoadPopulate.startTiming(); 
/*  872 */         try { this.needsDecoration = false;
/*  873 */           Random random = new Random();
/*  874 */           random.setSeed(this.world.getSeed());
/*  875 */           long xRand = random.nextLong() / 2L * 2L + 1L;
/*  876 */           long zRand = random.nextLong() / 2L * 2L + 1L;
/*  877 */           random.setSeed(this.loc.x * xRand + this.loc.z * zRand ^ this.world.getSeed());
/*      */           
/*  879 */           CraftWorld craftWorld = this.world.getWorld();
/*  880 */           if (craftWorld != null) {
/*  881 */             this.world.populating = true;
/*      */             try {
/*  883 */               for (BlockPopulator populator : craftWorld.getPopulators()) {
/*  884 */                 populator.populate((World)craftWorld, random, this.bukkitChunk);
/*      */               }
/*      */             } finally {
/*  887 */               this.world.populating = false;
/*      */             } 
/*      */           } 
/*  890 */           craftServer.getPluginManager().callEvent((Event)new ChunkPopulateEvent(this.bukkitChunk));
/*  891 */           if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null)
/*      */             try { ignored.close(); }
/*      */             catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */               throw throwable; }
/*      */       
/*      */       } 
/*  897 */     }  } public void unloadCallback() { CraftServer craftServer = this.world.getServer();
/*  898 */     ChunkUnloadEvent unloadEvent = new ChunkUnloadEvent(this.bukkitChunk, isNeedsSaving());
/*  899 */     craftServer.getPluginManager().callEvent((Event)unloadEvent);
/*      */     
/*  901 */     this.mustNotSave = !unloadEvent.isSaveChunk();
/*  902 */     this.world.getChunkProvider().removeLoadedChunk(this);
/*      */     
/*  904 */     int chunkX = this.loc.x;
/*  905 */     int chunkZ = this.loc.z;
/*  906 */     ChunkProviderServer chunkProvider = this.world.getChunkProvider();
/*  907 */     for (int dx = -3; dx <= 3; dx++) {
/*  908 */       for (int dz = -3; dz <= 3; dz++) {
/*  909 */         Chunk neighbour = chunkProvider.getChunkAtIfLoadedMainThreadNoCache(chunkX + dx, chunkZ + dz);
/*  910 */         if (neighbour != null) {
/*  911 */           neighbour.setNeighbourUnloaded(-dx, -dz);
/*      */         }
/*      */       } 
/*      */     } 
/*  915 */     this.loadedTicketLevel = false;
/*  916 */     resetNeighbours(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markDirty() {
/*  929 */     this.s = true;
/*      */   }
/*      */   
/*      */   public void a(@Nullable Entity entity, AxisAlignedBB axisalignedbb, List<Entity> list, @Nullable Predicate<? super Entity> predicate) {
/*  933 */     TickThread.softEnsureTickThread("Async getEntities call");
/*  934 */     int i = MathHelper.floor((axisalignedbb.minY - 2.0D) / 16.0D);
/*  935 */     int j = MathHelper.floor((axisalignedbb.maxY + 2.0D) / 16.0D);
/*      */     
/*  937 */     i = MathHelper.clamp(i, 0, this.entitySlices.length - 1);
/*  938 */     j = MathHelper.clamp(j, 0, this.entitySlices.length - 1);
/*      */     
/*  940 */     for (int k = i; k <= j; k++) {
/*  941 */       List<Entity> entityslice = this.entitySlices[k];
/*  942 */       List<Entity> list1 = entityslice;
/*  943 */       int l = list1.size();
/*      */       
/*  945 */       for (int i1 = 0; i1 < l; i1++) {
/*  946 */         Entity entity1 = list1.get(i1);
/*  947 */         if (!entity1.shouldBeRemoved)
/*      */         {
/*  949 */           if (entity1.getBoundingBox().c(axisalignedbb) && entity1 != entity) {
/*  950 */             if (predicate == null || predicate.test(entity1)) {
/*  951 */               list.add(entity1);
/*      */             }
/*      */             
/*  954 */             if (entity1 instanceof EntityEnderDragon) {
/*  955 */               EntityComplexPart[] aentitycomplexpart = ((EntityEnderDragon)entity1).eJ();
/*  956 */               int j1 = aentitycomplexpart.length;
/*      */               
/*  958 */               for (int k1 = 0; k1 < j1; k1++) {
/*  959 */                 EntityComplexPart entitycomplexpart = aentitycomplexpart[k1];
/*      */                 
/*  961 */                 if (entitycomplexpart != entity && entitycomplexpart.getBoundingBox().c(axisalignedbb) && (predicate == null || predicate.test(entitycomplexpart))) {
/*  962 */                   list.add(entitycomplexpart);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public <T extends Entity> void a(@Nullable EntityTypes<?> entitytypes, AxisAlignedBB axisalignedbb, List<? super T> list, Predicate<? super T> predicate) {
/*  973 */     TickThread.softEnsureTickThread("Async getEntities call");
/*  974 */     int i = MathHelper.floor((axisalignedbb.minY - 2.0D) / 16.0D);
/*  975 */     int j = MathHelper.floor((axisalignedbb.maxY + 2.0D) / 16.0D);
/*      */     
/*  977 */     i = MathHelper.clamp(i, 0, this.entitySlices.length - 1);
/*  978 */     j = MathHelper.clamp(j, 0, this.entitySlices.length - 1);
/*      */     
/*  980 */     for (int k = i; k <= j; k++) {
/*  981 */       Iterator<Entity> iterator = this.entitySlices[k].iterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  990 */       if (predicate != IEntitySelector.isInventory() || this.inventoryEntityCounts[k] > 0)
/*  991 */         while (iterator.hasNext()) {
/*  992 */           Entity entity = iterator.next();
/*  993 */           if (entity.shouldBeRemoved)
/*      */             continue; 
/*  995 */           if ((entitytypes == null || entity.getEntityType() == entitytypes) && entity.getBoundingBox().c(axisalignedbb) && predicate.test((T)entity)) {
/*  996 */             list.add((T)entity);
/*      */           }
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public <T extends Entity> void a(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, List<T> list, @Nullable Predicate<? super T> predicate) {
/*      */     int[] counts;
/* 1004 */     TickThread.softEnsureTickThread("Async getEntities call");
/* 1005 */     int i = MathHelper.floor((axisalignedbb.minY - 2.0D) / 16.0D);
/* 1006 */     int j = MathHelper.floor((axisalignedbb.maxY + 2.0D) / 16.0D);
/*      */     
/* 1008 */     i = MathHelper.clamp(i, 0, this.entitySlices.length - 1);
/* 1009 */     j = MathHelper.clamp(j, 0, this.entitySlices.length - 1);
/*      */ 
/*      */ 
/*      */     
/* 1013 */     if (EntityItem.class.isAssignableFrom(oclass)) {
/* 1014 */       counts = this.itemCounts;
/* 1015 */     } else if (IInventory.class.isAssignableFrom(oclass)) {
/* 1016 */       counts = this.inventoryEntityCounts;
/*      */     } else {
/* 1018 */       counts = null;
/*      */     } 
/*      */     
/* 1021 */     for (int k = i; k <= j; k++) {
/* 1022 */       if (counts == null || counts[k] > 0) {
/* 1023 */         Iterator<Entity> iterator = this.entitySlices[k].iterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1032 */         if (predicate != IEntitySelector.isInventory() || this.inventoryEntityCounts[k] > 0)
/*      */         {
/* 1034 */           while (iterator.hasNext()) {
/* 1035 */             Entity entity = iterator.next();
/* 1036 */             if (entity.shouldBeRemoved)
/*      */               continue; 
/* 1038 */             if (oclass.isInstance(entity) && entity.getBoundingBox().c(axisalignedbb) && (predicate == null || predicate.test((T)entity)))
/* 1039 */               list.add((T)entity); 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1047 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkCoordIntPair getPos() {
/* 1052 */     return this.loc;
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeStorage getBiomeIndex() {
/* 1057 */     return this.d;
/*      */   }
/*      */   
/*      */   public void setLoaded(boolean flag) {
/* 1061 */     this.loaded = flag;
/*      */   }
/*      */   
/*      */   public World getWorld() {
/* 1065 */     return this.world;
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Map.Entry<HeightMap.Type, HeightMap>> f() {
/* 1070 */     return Collections.unmodifiableSet(this.heightMap.entrySet());
/*      */   }
/*      */   
/*      */   public Map<BlockPosition, TileEntity> getTileEntities() {
/* 1074 */     return this.tileEntities;
/*      */   }
/*      */   
/*      */   public List<Entity>[] getEntitySlices() {
/* 1078 */     return this.entitySlices;
/*      */   }
/*      */ 
/*      */   
/*      */   public NBTTagCompound i(BlockPosition blockposition) {
/* 1083 */     return this.e.get(blockposition);
/*      */   }
/*      */ 
/*      */   
/*      */   public Stream<BlockPosition> m() {
/* 1088 */     return StreamSupport.<BlockPosition>stream(BlockPosition.b(this.loc.d(), 0, this.loc.e(), this.loc.f(), 255, this.loc.g()).spliterator(), false).filter(blockposition -> (getType(blockposition).f() != 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TickList<Block> n() {
/* 1095 */     return this.o;
/*      */   }
/*      */ 
/*      */   
/*      */   public TickList<FluidType> o() {
/* 1100 */     return this.p;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNeedsSaving(boolean flag) {
/* 1105 */     this.s = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNeedsSaving() {
/* 1110 */     return ((this.s || (this.q && this.world.getTime() != this.lastSaved)) && !this.mustNotSave);
/*      */   }
/*      */   
/*      */   public void d(boolean flag) {
/* 1114 */     this.q = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastSaved(long i) {
/* 1119 */     this.lastSaved = i;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public StructureStart<?> a(StructureGenerator<?> structuregenerator) {
/* 1125 */     return this.l.get(structuregenerator);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(StructureGenerator<?> structuregenerator, StructureStart<?> structurestart) {
/* 1130 */     this.l.put(structuregenerator, structurestart);
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<StructureGenerator<?>, StructureStart<?>> h() {
/* 1135 */     return this.l;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(Map<StructureGenerator<?>, StructureStart<?>> map) {
/* 1140 */     this.l.clear();
/* 1141 */     this.l.putAll(map);
/*      */   }
/*      */ 
/*      */   
/*      */   public LongSet b(StructureGenerator<?> structuregenerator) {
/* 1146 */     return this.m.computeIfAbsent(structuregenerator, structuregenerator1 -> new LongOpenHashSet());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(StructureGenerator<?> structuregenerator, long i) {
/* 1153 */     ((LongSet)this.m.computeIfAbsent(structuregenerator, structuregenerator1 -> new LongOpenHashSet()))
/*      */       
/* 1155 */       .add(i);
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<StructureGenerator<?>, LongSet> v() {
/* 1160 */     return this.m;
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(Map<StructureGenerator<?>, LongSet> map) {
/* 1165 */     this.m.clear();
/* 1166 */     this.m.putAll(map);
/*      */   }
/*      */ 
/*      */   
/*      */   public long getInhabitedTime() {
/* 1171 */     return (this.world.paperConfig.fixedInhabitedTime < 0) ? this.inhabitedTime : this.world.paperConfig.fixedInhabitedTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInhabitedTime(long i) {
/* 1176 */     this.inhabitedTime = i;
/*      */   }
/*      */   
/*      */   public void A() {
/* 1180 */     ChunkCoordIntPair chunkcoordintpair = getPos();
/*      */     
/* 1182 */     for (int i = 0; i < this.n.length; i++) {
/* 1183 */       if (this.n[i] != null) {
/* 1184 */         ShortListIterator shortlistiterator = this.n[i].iterator();
/*      */         
/* 1186 */         while (shortlistiterator.hasNext()) {
/* 1187 */           Short oshort = shortlistiterator.next();
/* 1188 */           BlockPosition blockposition = ProtoChunk.a(oshort.shortValue(), i, chunkcoordintpair);
/* 1189 */           IBlockData iblockdata = getType(blockposition);
/* 1190 */           IBlockData iblockdata1 = Block.b(iblockdata, this.world, blockposition);
/*      */           
/* 1192 */           this.world.setTypeAndData(blockposition, iblockdata1, 22);
/*      */         } 
/*      */         
/* 1195 */         this.n[i].clear();
/*      */       } 
/*      */     } 
/*      */     
/* 1199 */     B();
/* 1200 */     Iterator<BlockPosition> iterator = Sets.newHashSet(this.e.keySet()).iterator();
/*      */     
/* 1202 */     while (iterator.hasNext()) {
/* 1203 */       BlockPosition blockposition1 = iterator.next();
/*      */       
/* 1205 */       getTileEntity(blockposition1);
/*      */     } 
/*      */     
/* 1208 */     this.e.clear();
/* 1209 */     this.i.a(this);
/*      */   }
/*      */   @Nullable
/*      */   private TileEntity a(BlockPosition blockposition, NBTTagCompound nbttagcompound) {
/*      */     TileEntity tileentity;
/* 1214 */     IBlockData iblockdata = getType(blockposition);
/*      */ 
/*      */     
/* 1217 */     if ("DUMMY".equals(nbttagcompound.getString("id"))) {
/* 1218 */       Block block = iblockdata.getBlock();
/*      */       
/* 1220 */       if (block instanceof ITileEntity) {
/* 1221 */         tileentity = ((ITileEntity)block).createTile(this.world);
/*      */       } else {
/* 1223 */         tileentity = null;
/* 1224 */         LOGGER.warn("Tried to load a DUMMY block entity @ {} but found not block entity block {} at location", blockposition, iblockdata);
/*      */       } 
/*      */     } else {
/* 1227 */       tileentity = TileEntity.create(iblockdata, nbttagcompound);
/*      */     } 
/*      */     
/* 1230 */     if (tileentity != null) {
/* 1231 */       tileentity.setLocation(this.world, blockposition);
/* 1232 */       a(tileentity);
/*      */     } else {
/* 1234 */       LOGGER.warn("Tried to load a block entity for block {} but failed at location {}", iblockdata, blockposition);
/*      */     } 
/*      */     
/* 1237 */     return tileentity;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkConverter p() {
/* 1242 */     return this.i;
/*      */   }
/*      */ 
/*      */   
/*      */   public ShortList[] l() {
/* 1247 */     return this.n;
/*      */   }
/*      */   
/*      */   public void B() {
/* 1251 */     if (this.o instanceof ProtoChunkTickList) {
/* 1252 */       ((ProtoChunkTickList<Block>)this.o).a(this.world.getBlockTickList(), blockposition -> getType(blockposition).getBlock());
/*      */ 
/*      */       
/* 1255 */       this.o = TickListEmpty.b();
/* 1256 */     } else if (this.o instanceof TickListChunk) {
/* 1257 */       ((TickListChunk<Block>)this.o).a(this.world.getBlockTickList());
/* 1258 */       this.o = TickListEmpty.b();
/*      */     } 
/*      */     
/* 1261 */     if (this.p instanceof ProtoChunkTickList) {
/* 1262 */       ((ProtoChunkTickList<FluidType>)this.p).a(this.world.getFluidTickList(), blockposition -> getFluid(blockposition).getType());
/*      */ 
/*      */       
/* 1265 */       this.p = TickListEmpty.b();
/* 1266 */     } else if (this.p instanceof TickListChunk) {
/* 1267 */       ((TickListChunk<FluidType>)this.p).a(this.world.getFluidTickList());
/* 1268 */       this.p = TickListEmpty.b();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(WorldServer worldserver) {
/* 1274 */     if (this.o == TickListEmpty.b()) {
/* 1275 */       Objects.requireNonNull(IRegistry.BLOCK); this.o = new TickListChunk<>(IRegistry.BLOCK::getKey, worldserver.getBlockTickList().a(this.loc, true, false), worldserver.getTime());
/* 1276 */       setNeedsSaving(true);
/*      */     } 
/*      */     
/* 1279 */     if (this.p == TickListEmpty.b()) {
/* 1280 */       Objects.requireNonNull(IRegistry.FLUID); this.p = new TickListChunk<>(IRegistry.FLUID::getKey, worldserver.getFluidTickList().a(this.loc, true, false), worldserver.getTime());
/* 1281 */       setNeedsSaving(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkStatus getChunkStatus() {
/* 1288 */     return ChunkStatus.FULL;
/*      */   }
/*      */   
/*      */   public PlayerChunk.State getState() {
/* 1292 */     return (this.u == null) ? PlayerChunk.State.BORDER : this.u.get();
/*      */   }
/*      */   
/*      */   public void a(Supplier<PlayerChunk.State> supplier) {
/* 1296 */     this.u = supplier;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean r() {
/* 1301 */     return this.x;
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(boolean flag) {
/* 1306 */     this.x = flag;
/* 1307 */     setNeedsSaving(true);
/*      */   }
/*      */   
/*      */   public enum EnumTileEntityState
/*      */   {
/* 1312 */     IMMEDIATE, QUEUED, CHECK;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Chunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */