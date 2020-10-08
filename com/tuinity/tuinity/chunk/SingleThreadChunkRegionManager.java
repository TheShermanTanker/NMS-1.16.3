/*     */ package com.tuinity.tuinity.chunk;
/*     */ 
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import co.aikar.timings.Timing;
/*     */ import com.tuinity.tuinity.util.TickThread;
/*     */ import com.tuinity.tuinity.util.maplist.IteratorSafeOrderedReferenceSet;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.LongFunction;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.MCUtil;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SingleThreadChunkRegionManager<T extends Enum<T> & SingleThreadChunkRegionManager.RegionDataCreator<T>>
/*     */ {
/*     */   static final int REGION_SECTION_MERGE_RADIUS = 1;
/*     */   public static final int REGION_CHUNK_SIZE = 8;
/*     */   public static final int REGION_CHUNK_SIZE_SHIFT = 3;
/*     */   public final WorldServer world;
/*     */   public final Class<T> dataClass;
/*     */   public final String name;
/*     */   public final Timing addChunkTimings;
/*     */   public final Timing removeChunkTimings;
/*     */   public final Timing regionRecalculateTimings;
/*  34 */   protected final Long2ObjectOpenHashMap<RegionSection<T>> regionsBySection = new Long2ObjectOpenHashMap();
/*  35 */   protected final ReferenceLinkedOpenHashSet<Region<T>> needsRecalculation = new ReferenceLinkedOpenHashSet();
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int minSectionRecalcCount;
/*     */ 
/*     */ 
/*     */   
/*     */   protected final double maxDeadRegionPercent;
/*     */ 
/*     */ 
/*     */   
/*     */   private final List<Region<T>> toMerge;
/*     */ 
/*     */   
/*     */   protected final LongFunction<RegionSection<T>> createRegionIfAbsent;
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addToRecalcQueue(Region<T> region) {
/*  55 */     this.needsRecalculation.add(region);
/*     */   }
/*     */   
/*     */   protected void removeFromRecalcQueue(Region<T> region) {
/*  59 */     this.needsRecalculation.remove(region);
/*     */   }
/*     */   
/*     */   public RegionSection<T> getRegionSection(int chunkX, int chunkZ) {
/*  63 */     return (RegionSection<T>)this.regionsBySection.get(MCUtil.getCoordinateKey(chunkX >> 3, chunkZ >> 3));
/*     */   }
/*     */   
/*     */   public Region<T> getRegion(int chunkX, int chunkZ) {
/*  67 */     RegionSection<T> section = (RegionSection<T>)this.regionsBySection.get(MCUtil.getCoordinateKey(chunkX >> 3, chunkZ >> 3));
/*  68 */     return (section != null) ? section.region : null;
/*     */   }
/*     */   
/*  71 */   public SingleThreadChunkRegionManager(WorldServer world, Class<T> enumClass, int minSectionRecalcCount, double maxDeadRegionPercent, String name) { this.toMerge = new ArrayList<>(9);
/*  72 */     this.createRegionIfAbsent = (keyInMap -> new RegionSection<>(keyInMap, this)); this.world = world; this.dataClass = enumClass; this.name = name; this.minSectionRecalcCount = Math.max(2, minSectionRecalcCount);
/*     */     this.maxDeadRegionPercent = maxDeadRegionPercent;
/*     */     String prefix = world.getWorld().getName() + " - Region Manager - " + name + " - ";
/*     */     this.addChunkTimings = MinecraftTimings.getInternalTaskName(prefix.concat("add"));
/*     */     this.removeChunkTimings = MinecraftTimings.getInternalTaskName(prefix.concat("remove"));
/*     */     this.regionRecalculateTimings = MinecraftTimings.getInternalTaskName(prefix.concat("recalculate")); } protected RegionSection<T> getOrCreateAndMergeSection(int sectionX, int sectionZ, RegionSection<T> force) { RegionSection<T> section;
/*  78 */     int minX = sectionX - 1;
/*  79 */     int maxX = sectionX + 1;
/*  80 */     int minZ = sectionZ - 1;
/*  81 */     int maxZ = sectionZ + 1;
/*     */     
/*  83 */     int mergeCandidateSectionSize = -1;
/*  84 */     Region<T> mergeIntoCandidate = null;
/*     */     
/*  86 */     for (int currX = minX; currX <= maxX; currX++) {
/*  87 */       for (int currZ = minZ; currZ <= maxZ; currZ++) {
/*  88 */         section = (RegionSection<T>)this.regionsBySection.get(MCUtil.getCoordinateKey(currX, currZ));
/*  89 */         if (section != null) {
/*     */ 
/*     */           
/*  92 */           Region<T> region = section.region;
/*  93 */           if (region.dead) {
/*  94 */             throw new IllegalStateException("Dead region should not be in live region manager state: " + region);
/*     */           }
/*  96 */           int sections = region.sections.size();
/*     */           
/*  98 */           if (sections > mergeCandidateSectionSize) {
/*  99 */             mergeCandidateSectionSize = sections;
/* 100 */             mergeIntoCandidate = region;
/*     */           } 
/* 102 */           this.toMerge.add(region);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     if (mergeIntoCandidate != null) {
/* 108 */       for (int len = this.toMerge.size(), i = len - 1; i >= 0; i--) {
/* 109 */         Region<T> region = this.toMerge.remove(i);
/* 110 */         if (!region.dead && mergeIntoCandidate != region)
/*     */         {
/*     */           
/* 113 */           region.mergeInto(mergeIntoCandidate); } 
/*     */       } 
/*     */     } else {
/* 116 */       mergeIntoCandidate = new Region<>(this);
/*     */     } 
/*     */     
/* 119 */     long sectionKey = MCUtil.getCoordinateKey(sectionX, sectionZ);
/*     */     
/* 121 */     if (force == null) {
/* 122 */       section = (RegionSection<T>)this.regionsBySection.computeIfAbsent(sectionKey, this.createRegionIfAbsent);
/*     */     } else {
/* 124 */       RegionSection<T> existing = (RegionSection<T>)this.regionsBySection.putIfAbsent(sectionKey, force);
/* 125 */       if (existing != null) {
/* 126 */         throw new IllegalStateException("Attempting to override section '" + existing.toStringWithRegion() + ", with " + force
/* 127 */             .toStringWithRegion());
/*     */       }
/*     */       
/* 130 */       section = force;
/*     */     } 
/*     */     
/* 133 */     section.region = mergeIntoCandidate;
/* 134 */     mergeIntoCandidate.sections.add(section);
/*     */     
/* 136 */     return section; }
/*     */ 
/*     */   
/*     */   public void addChunk(int chunkX, int chunkZ) {
/* 140 */     TickThread.ensureTickThread("async region manager add chunk");
/* 141 */     this.addChunkTimings.startTiming();
/*     */     try {
/* 143 */       getOrCreateAndMergeSection(chunkX >> 3, chunkZ >> 3, null).addChunk(chunkX, chunkZ);
/*     */     } finally {
/* 145 */       this.addChunkTimings.stopTiming();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeChunk(int chunkX, int chunkZ) {
/* 150 */     TickThread.ensureTickThread("async region manager remove chunk");
/* 151 */     this.removeChunkTimings.startTiming();
/*     */     try {
/* 153 */       RegionSection<T> section = (RegionSection<T>)this.regionsBySection.get(
/* 154 */           MCUtil.getCoordinateKey(chunkX >> 3, chunkZ >> 3));
/* 155 */       if (section != null) {
/* 156 */         section.removeChunk(chunkX, chunkZ);
/*     */       } else {
/* 158 */         throw new IllegalStateException("Cannot remove chunk at (" + chunkX + "," + chunkZ + ") from region state, section does not exist");
/*     */       } 
/*     */     } finally {
/* 161 */       this.removeChunkTimings.stopTiming();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void recalculateRegions() {
/* 166 */     TickThread.ensureTickThread("async region recalculation");
/* 167 */     for (int i = 0, len = this.needsRecalculation.size(); i < len; i++) {
/* 168 */       Region<T> region = (Region<T>)this.needsRecalculation.removeFirst();
/*     */       
/* 170 */       recalculateRegion(region);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void recalculateRegion(Region<T> region) {
/* 175 */     this.regionRecalculateTimings.startTiming();
/*     */     try {
/* 177 */       region.markedForRecalc = false;
/*     */       
/* 179 */       for (ObjectIterator<RegionSection<T>> objectIterator = region.deadSections.iterator(); objectIterator.hasNext(); ) {
/* 180 */         RegionSection<T> deadSection = objectIterator.next();
/* 181 */         if (!this.regionsBySection.remove(deadSection.regionCoordinate, deadSection)) {
/* 182 */           throw new IllegalStateException("Cannot remove dead section '" + deadSection
/* 183 */               .toStringWithRegion() + "' from section state! State at section coordinate: " + this.regionsBySection
/* 184 */               .get(deadSection.regionCoordinate));
/*     */         }
/* 186 */         if (!region.sections.remove(deadSection)) {
/* 187 */           throw new IllegalStateException("Region " + region + " has inconsistent state, it should contain section " + deadSection);
/*     */         }
/*     */         
/* 190 */         objectIterator.remove();
/*     */       } 
/*     */ 
/*     */       
/* 194 */       if (region.sections.size() < this.minSectionRecalcCount) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       region.dead = true;
/*     */       
/*     */       Iterator<RegionSection<T>> iterator;
/* 205 */       for (iterator = region.sections.unsafeIterator(1); iterator.hasNext(); ) {
/* 206 */         RegionSection<T> aliveSection = iterator.next();
/* 207 */         if (!this.regionsBySection.remove(aliveSection.regionCoordinate, aliveSection)) {
/* 208 */           throw new IllegalStateException("Cannot remove alive section '" + aliveSection
/* 209 */               .toStringWithRegion() + "' from section state! State at section coordinate: " + this.regionsBySection
/* 210 */               .get(aliveSection.regionCoordinate));
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 215 */       for (iterator = region.sections.unsafeIterator(1); iterator.hasNext(); ) {
/* 216 */         RegionSection<T> aliveSection = iterator.next();
/* 217 */         getOrCreateAndMergeSection(aliveSection.getSectionX(), aliveSection.getSectionZ(), aliveSection);
/*     */       } 
/*     */     } finally {
/* 220 */       this.regionRecalculateTimings.stopTiming();
/*     */     } 
/*     */   }
/*     */   public static interface RegionDataCreator<E extends Enum<E> & RegionDataCreator<E>> {
/*     */     Object createData(SingleThreadChunkRegionManager.RegionSection<E> param1RegionSection, SingleThreadChunkRegionManager<E> param1SingleThreadChunkRegionManager); }
/* 225 */   public static final class Region<T extends Enum<T> & RegionDataCreator<T>> { protected final IteratorSafeOrderedReferenceSet<SingleThreadChunkRegionManager.RegionSection<T>> sections = new IteratorSafeOrderedReferenceSet(true);
/* 226 */     protected final ReferenceOpenHashSet<SingleThreadChunkRegionManager.RegionSection<T>> deadSections = new ReferenceOpenHashSet(16, 0.7F);
/*     */     
/*     */     protected boolean dead;
/*     */     protected boolean markedForRecalc;
/*     */     public final SingleThreadChunkRegionManager<T> regionManager;
/*     */     
/*     */     protected Region(SingleThreadChunkRegionManager<T> regionManager) {
/* 233 */       this.regionManager = regionManager;
/*     */     }
/*     */     
/*     */     public IteratorSafeOrderedReferenceSet.Iterator<SingleThreadChunkRegionManager.RegionSection<T>> getSections() {
/* 237 */       return this.sections.iterator(1);
/*     */     }
/*     */     
/*     */     protected final double getDeadSectionPercent() {
/* 241 */       return this.deadSections.size() / this.sections.size();
/*     */     }
/*     */     
/*     */     protected void mergeInto(Region<T> mergeTarget) {
/* 245 */       if (this.dead)
/* 246 */         throw new IllegalStateException("Source region is dead! Source " + this + ", target " + mergeTarget); 
/* 247 */       if (mergeTarget.dead) {
/* 248 */         throw new IllegalStateException("Target region is dead! Source " + this + ", target " + mergeTarget);
/*     */       }
/* 250 */       this.dead = true;
/*     */       
/* 252 */       for (Iterator<SingleThreadChunkRegionManager.RegionSection<T>> iterator = this.sections.unsafeIterator(1); iterator.hasNext(); ) {
/* 253 */         SingleThreadChunkRegionManager.RegionSection<T> section = iterator.next();
/*     */         
/* 255 */         if (!mergeTarget.sections.add(section)) {
/* 256 */           throw new IllegalStateException("Target cannot contain source's sections! Source " + this + ", target " + mergeTarget);
/*     */         }
/*     */         
/* 259 */         section.region = mergeTarget;
/*     */       } 
/*     */       
/* 262 */       for (ObjectIterator<SingleThreadChunkRegionManager.RegionSection<T>> objectIterator = this.deadSections.iterator(); objectIterator.hasNext(); ) { SingleThreadChunkRegionManager.RegionSection<T> deadSection = objectIterator.next();
/* 263 */         if (!this.sections.contains(deadSection)) {
/* 264 */           throw new IllegalStateException("Source region does not even contain its own dead sections! Missing " + deadSection + " from region " + this);
/*     */         }
/* 266 */         mergeTarget.deadSections.add(deadSection); }
/*     */     
/*     */     }
/*     */     
/*     */     protected void markSectionAlive(SingleThreadChunkRegionManager.RegionSection<T> section) {
/* 271 */       this.deadSections.remove(section);
/* 272 */       if (this.markedForRecalc && (this.sections.size() < this.regionManager.minSectionRecalcCount || getDeadSectionPercent() < this.regionManager.maxDeadRegionPercent)) {
/* 273 */         this.regionManager.removeFromRecalcQueue(this);
/* 274 */         this.markedForRecalc = false;
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void markSectionDead(SingleThreadChunkRegionManager.RegionSection<T> section) {
/* 279 */       this.deadSections.add(section);
/* 280 */       if (!this.markedForRecalc && (this.sections.size() >= this.regionManager.minSectionRecalcCount || this.sections.size() == this.deadSections.size()) && getDeadSectionPercent() >= this.regionManager.maxDeadRegionPercent) {
/* 281 */         this.regionManager.addToRecalcQueue(this);
/* 282 */         this.markedForRecalc = true;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 288 */       StringBuilder ret = new StringBuilder(128);
/*     */       
/* 290 */       ret.append("Region{");
/* 291 */       ret.append("dead=").append(this.dead).append(',');
/* 292 */       ret.append("markedForRecalc=").append(this.markedForRecalc).append(',');
/*     */       
/* 294 */       ret.append("sectionCount=").append(this.sections.size()).append(',');
/* 295 */       ret.append("sections=[");
/* 296 */       for (Iterator<SingleThreadChunkRegionManager.RegionSection<T>> iterator = this.sections.unsafeIterator(1); iterator.hasNext(); ) {
/* 297 */         SingleThreadChunkRegionManager.RegionSection<T> section = iterator.next();
/* 298 */         ret.append(section);
/* 299 */         if (iterator.hasNext()) {
/* 300 */           ret.append(',');
/*     */         }
/*     */       } 
/* 303 */       ret.append(']');
/*     */       
/* 305 */       ret.append('}');
/* 306 */       return ret.toString();
/*     */     } }
/*     */ 
/*     */   
/*     */   public static final class RegionSection<T extends Enum<T> & RegionDataCreator<T>>
/*     */   {
/*     */     protected final long regionCoordinate;
/*     */     protected long chunksBitset;
/*     */     protected SingleThreadChunkRegionManager.Region<T> region;
/*     */     protected final EnumMap<T, Object> data;
/*     */     protected final Function<? super T, Object> createIfAbsentFunction;
/*     */     public final SingleThreadChunkRegionManager<T> regionManager;
/*     */     
/*     */     protected RegionSection(long regionCoordinate, SingleThreadChunkRegionManager<T> regionManager) {
/* 320 */       this.regionCoordinate = regionCoordinate;
/* 321 */       this.data = new EnumMap<>(regionManager.dataClass);
/* 322 */       this.regionManager = regionManager;
/* 323 */       this.createIfAbsentFunction = (keyInMap -> ((SingleThreadChunkRegionManager.RegionDataCreator<T>)keyInMap).createData(this, regionManager));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getSectionX() {
/* 329 */       return MCUtil.getCoordinateX(this.regionCoordinate);
/*     */     }
/*     */     
/*     */     public int getSectionZ() {
/* 333 */       return MCUtil.getCoordinateZ(this.regionCoordinate);
/*     */     }
/*     */     
/*     */     public SingleThreadChunkRegionManager.Region<T> getRegion() {
/* 337 */       return this.region;
/*     */     }
/*     */     
/*     */     public Object getData(T key) {
/* 341 */       return this.data.get(key);
/*     */     }
/*     */     
/*     */     public Object getOrCreateData(T key) {
/* 345 */       return this.data.computeIfAbsent(key, this.createIfAbsentFunction);
/*     */     }
/*     */     
/*     */     public Object removeData(T key) {
/* 349 */       return this.data.remove(key);
/*     */     }
/*     */     
/*     */     public void setData(T key, Object data) {
/* 353 */       this.data.put(key, data);
/*     */     }
/*     */     
/*     */     private static int getChunkIndex(int chunkX, int chunkZ) {
/* 357 */       return chunkX & 0x7 | (chunkZ & 0x7) << 3;
/*     */     }
/*     */     
/*     */     protected void addChunk(int chunkX, int chunkZ) {
/* 361 */       long bitset = this.chunksBitset;
/* 362 */       long after = this.chunksBitset = bitset | 1L << getChunkIndex(chunkX, chunkZ);
/* 363 */       if (after == bitset) {
/* 364 */         throw new IllegalStateException("Cannot add a chunk to a section which already has the chunk! RegionSection: " + this + ", global chunk: " + (new ChunkCoordIntPair(chunkX, chunkZ)).toString());
/*     */       }
/* 366 */       if (bitset != 0L) {
/*     */         return;
/*     */       }
/* 369 */       this.region.markSectionAlive(this);
/*     */     }
/*     */     
/*     */     protected void removeChunk(int chunkX, int chunkZ) {
/* 373 */       long before = this.chunksBitset;
/* 374 */       long bitset = this.chunksBitset = before & (1L << getChunkIndex(chunkX, chunkZ) ^ 0xFFFFFFFFFFFFFFFFL);
/* 375 */       if (before == bitset) {
/* 376 */         throw new IllegalStateException("Cannot remove a chunk from a section which does not have that chunk! RegionSection: " + this + ", global chunk: " + (new ChunkCoordIntPair(chunkX, chunkZ)).toString());
/*     */       }
/* 378 */       if (bitset != 0L) {
/*     */         return;
/*     */       }
/* 381 */       this.region.markSectionDead(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 386 */       return "RegionSection{regionCoordinate=" + (new ChunkCoordIntPair(this.regionCoordinate))
/* 387 */         .toString() + ",chunksBitset=" + 
/* 388 */         Long.toHexString(this.chunksBitset) + ",hash=" + 
/* 389 */         hashCode() + ",}";
/*     */     }
/*     */ 
/*     */     
/*     */     public String toStringWithRegion() {
/* 394 */       return "RegionSection{regionCoordinate=" + (new ChunkCoordIntPair(this.regionCoordinate))
/* 395 */         .toString() + ",chunksBitset=" + 
/* 396 */         Long.toHexString(this.chunksBitset) + ",hash=" + 
/* 397 */         hashCode() + ",region=" + this.region + ",}";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\tuinity\tuinity\chunk\SingleThreadChunkRegionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */