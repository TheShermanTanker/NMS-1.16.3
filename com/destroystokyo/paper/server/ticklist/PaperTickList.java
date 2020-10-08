/*     */ package com.destroystokyo.paper.server.ticklist;
/*     */ 
/*     */ import co.aikar.timings.Timing;
/*     */ import co.aikar.timings.WorldTimingsHandler;
/*     */ import com.destroystokyo.paper.util.set.LinkedSortedSet;
/*     */ import com.tuinity.tuinity.util.TickThread;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectRBTreeSet;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.SortedSet;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.server.v1_16_R2.BaseBlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.ChunkProviderServer;
/*     */ import net.minecraft.server.v1_16_R2.CrashReport;
/*     */ import net.minecraft.server.v1_16_R2.CrashReportSystemDetails;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.MCUtil;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import net.minecraft.server.v1_16_R2.NextTickListEntry;
/*     */ import net.minecraft.server.v1_16_R2.ReportedException;
/*     */ import net.minecraft.server.v1_16_R2.StructureBoundingBox;
/*     */ import net.minecraft.server.v1_16_R2.TickListPriority;
/*     */ import net.minecraft.server.v1_16_R2.TickListServer;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ public final class PaperTickList<T>
/*     */   extends TickListServer<T> {
/*     */   public static final int STATE_UNSCHEDULED = 1;
/*     */   public static final int STATE_SCHEDULED = 2;
/*     */   public static final int STATE_PENDING_TICK = 4;
/*     */   public static final int STATE_TICKING = 8;
/*     */   public static final int STATE_TICKED = 16;
/*     */   public static final int STATE_CANCELLED_TICK = 32;
/*     */   private static final int SHORT_SCHEDULE_TICK_THRESHOLD = 401;
/*     */   private final WorldServer world;
/*     */   private final Predicate<T> excludeFromScheduling;
/*     */   private final Function<T, MinecraftKey> getMinecraftKeyFrom;
/*     */   private final Consumer<NextTickListEntry<T>> tickFunction;
/*     */   private final Timing timingCleanup;
/*     */   private final Timing timingTicking;
/*     */   private final Timing timingFinished;
/*  56 */   private final Long2ObjectOpenHashMap<ArrayList<NextTickListEntry<T>>> entriesByBlock = new Long2ObjectOpenHashMap(1024, 0.25F);
/*  57 */   private final Long2ObjectOpenHashMap<ObjectRBTreeSet<NextTickListEntry<T>>> entriesByChunk = new Long2ObjectOpenHashMap(1024, 0.25F);
/*  58 */   private final Long2ObjectOpenHashMap<ArrayList<NextTickListEntry<T>>> pendingChunkTickLoad = new Long2ObjectOpenHashMap(1024, 0.5F);
/*     */ 
/*     */   
/*  61 */   private final ObjectRBTreeSet<NextTickListEntry<T>> longScheduled = new ObjectRBTreeSet(TickListServerInterval.ENTRY_COMPARATOR);
/*     */   
/*  63 */   private final ArrayDeque<NextTickListEntry<T>> toTickThisTick = new ArrayDeque<>();
/*     */   
/*  65 */   private final TickListServerInterval<T>[] shortScheduled = (TickListServerInterval<T>[])new TickListServerInterval[401];
/*     */ 
/*     */ 
/*     */   
/*     */   private int shortScheduledIndex;
/*     */ 
/*     */   
/*     */   private long currentTick;
/*     */ 
/*     */   
/*  75 */   private static final boolean WARN_ON_EXCESSIVE_DELAY = Boolean.getBoolean("paper.ticklist-warn-on-excessive-delay");
/*  76 */   private static final long EXCESSIVE_DELAY_THRESHOLD = Long.getLong("paper.ticklist-excessive-delay-threshold", 1200L).longValue();
/*     */   private boolean warnedAboutDesync;
/*     */   
/*     */   private static int getWrappedIndex(int start, int length, int index) {
/*  80 */     int next = start + index;
/*  81 */     return (next < length) ? next : (next - length);
/*     */   }
/*     */   
/*     */   private static int getNextIndex(int curr, int length) {
/*  85 */     int next = curr + 1;
/*  86 */     return (next < length) ? next : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public PaperTickList(WorldServer world, Predicate<T> excludeFromScheduling, Function<T, MinecraftKey> getMinecraftKeyFrom, Consumer<NextTickListEntry<T>> tickFunction, String timingsType) {
/*  91 */     super(world, excludeFromScheduling, getMinecraftKeyFrom, tickFunction, timingsType); for (int i = 0, len = this.shortScheduled.length; i < len; i++)
/*  92 */       this.shortScheduled[i] = new TickListServerInterval<>();  this.world = world;
/*  93 */     this.excludeFromScheduling = excludeFromScheduling;
/*  94 */     this.getMinecraftKeyFrom = getMinecraftKeyFrom;
/*  95 */     this.tickFunction = tickFunction;
/*  96 */     this.timingCleanup = WorldTimingsHandler.getTickList(world, timingsType + " - Cleanup");
/*  97 */     this.timingTicking = WorldTimingsHandler.getTickList(world, timingsType + " - Ticking");
/*  98 */     this.timingFinished = WorldTimingsHandler.getTickList(world, timingsType + " - Finish");
/*  99 */     this.currentTick = this.world.getTime();
/*     */   }
/*     */   
/*     */   private void queueEntryForTick(NextTickListEntry<T> entry, ChunkProviderServer chunkProvider) {
/* 103 */     if (entry.tickState == 2) {
/* 104 */       if (chunkProvider.isTickingReadyMainThread(entry.getPosition())) {
/* 105 */         this.toTickThisTick.add(entry);
/* 106 */         entry.tickState = 4;
/*     */       } else {
/*     */         
/* 109 */         addToNotTickingReady(entry);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void addToNotTickingReady(NextTickListEntry<T> entry) {
/* 115 */     ((ArrayList<NextTickListEntry<T>>)this.pendingChunkTickLoad.computeIfAbsent(MCUtil.getCoordinateKey(entry.getPosition()), keyInMap -> new ArrayList()))
/*     */       
/* 117 */       .add(entry);
/*     */   }
/*     */   
/*     */   private void addToSchedule(NextTickListEntry<T> entry) {
/* 121 */     long delay = entry.getTargetTick() - this.currentTick + 1L;
/* 122 */     if (delay < 401L) {
/* 123 */       if (delay < 0L) {
/*     */         
/* 125 */         this.longScheduled.add(entry);
/*     */       } else {
/* 127 */         this.shortScheduled[getWrappedIndex(this.shortScheduledIndex, 401, (int)delay)].addEntryLast(entry);
/*     */       } 
/*     */     } else {
/* 130 */       this.longScheduled.add(entry);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeEntry(NextTickListEntry<T> entry) {
/* 135 */     entry.tickState = 32;
/*     */ 
/*     */     
/* 138 */     BlockPosition pos = entry.getPosition();
/* 139 */     long blockKey = MCUtil.getBlockKey(pos);
/*     */     
/* 141 */     ArrayList<NextTickListEntry<T>> currentEntries = (ArrayList<NextTickListEntry<T>>)this.entriesByBlock.get(blockKey);
/*     */     
/* 143 */     if (currentEntries.size() == 1) {
/*     */       
/* 145 */       this.entriesByBlock.remove(blockKey);
/*     */     } else {
/*     */       
/* 148 */       for (int i = 0, len = currentEntries.size(); i < len; i++) {
/* 149 */         NextTickListEntry<T> currentEntry = currentEntries.get(i);
/* 150 */         if (currentEntry == entry) {
/* 151 */           currentEntries.remove(i);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     long chunkKey = MCUtil.getCoordinateKey(entry.getPosition());
/*     */     
/* 159 */     ObjectRBTreeSet<NextTickListEntry<T>> set = (ObjectRBTreeSet<NextTickListEntry<T>>)this.entriesByChunk.get(chunkKey);
/*     */     
/* 161 */     set.remove(entry);
/*     */     
/* 163 */     if (set.isEmpty()) {
/* 164 */       this.entriesByChunk.remove(chunkKey);
/*     */     }
/*     */     
/* 167 */     ArrayList<NextTickListEntry<T>> pendingTickingLoad = (ArrayList<NextTickListEntry<T>>)this.pendingChunkTickLoad.get(chunkKey);
/*     */     
/* 169 */     if (pendingTickingLoad != null) {
/* 170 */       for (int i = 0, len = pendingTickingLoad.size(); i < len; i++) {
/* 171 */         if (pendingTickingLoad.get(i) == entry) {
/* 172 */           pendingTickingLoad.remove(i);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 177 */       if (pendingTickingLoad.isEmpty()) {
/* 178 */         this.pendingChunkTickLoad.remove(chunkKey);
/*     */       }
/*     */     } 
/*     */     
/* 182 */     long delay = entry.getTargetTick() - this.currentTick + 1L;
/* 183 */     if (delay >= 401L) {
/* 184 */       this.longScheduled.remove(entry);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onChunkSetTicking(int chunkX, int chunkZ) {
/* 189 */     TickThread.softEnsureTickThread("async tick list chunk ticking update");
/* 190 */     ArrayList<NextTickListEntry<T>> pending = (ArrayList<NextTickListEntry<T>>)this.pendingChunkTickLoad.remove(MCUtil.getCoordinateKey(chunkX, chunkZ));
/* 191 */     if (pending == null) {
/*     */       return;
/*     */     }
/*     */     
/* 195 */     for (int i = 0, size = pending.size(); i < size; i++) {
/* 196 */       NextTickListEntry<T> entry = pending.get(i);
/*     */       
/* 198 */       addToSchedule(entry);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void prepare() {
/* 203 */     long currentTick = this.currentTick;
/*     */     
/* 205 */     ChunkProviderServer chunkProvider = this.world.getChunkProvider();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     if (this.longScheduled.isEmpty() || ((NextTickListEntry)this.longScheduled.first()).getTargetTick() > currentTick) {
/*     */       
/* 213 */       TickListServerInterval<T> interval = this.shortScheduled[this.shortScheduledIndex];
/* 214 */       for (int i = 0, len = interval.byPriority.length; i < len; i++) {
/* 215 */         for (Iterator<NextTickListEntry<T>> iterator = interval.byPriority[i].iterator(); iterator.hasNext();) {
/* 216 */           queueEntryForTick(iterator.next(), chunkProvider);
/*     */         }
/*     */       } 
/*     */     } else {
/* 220 */       TickListServerInterval<T> interval = this.shortScheduled[this.shortScheduledIndex];
/*     */ 
/*     */       
/* 223 */       Comparator<NextTickListEntry<?>> comparator = TickListServerInterval.ENTRY_COMPARATOR;
/* 224 */       ObjectBidirectionalIterator<NextTickListEntry<T>> objectBidirectionalIterator = this.longScheduled.iterator();
/* 225 */       NextTickListEntry<T> longCurrent = objectBidirectionalIterator.next();
/*     */       
/* 227 */       for (int i = 0, len = interval.byPriority.length; i < len; i++) {
/* 228 */         for (Iterator<NextTickListEntry<T>> iterator = interval.byPriority[i].iterator(); iterator.hasNext(); ) {
/* 229 */           NextTickListEntry<T> shortCurrent = iterator.next();
/* 230 */           if (longCurrent != null)
/*     */           {
/* 232 */             while (comparator.compare(longCurrent, shortCurrent) <= 0) {
/* 233 */               queueEntryForTick(longCurrent, chunkProvider);
/* 234 */               objectBidirectionalIterator.remove();
/* 235 */               if (objectBidirectionalIterator.hasNext()) {
/* 236 */                 longCurrent = objectBidirectionalIterator.next();
/* 237 */                 if (longCurrent.getTargetTick() > currentTick) {
/* 238 */                   longCurrent = null; break;
/*     */                 } 
/*     */                 continue;
/*     */               } 
/* 242 */               longCurrent = null;
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 247 */           queueEntryForTick(shortCurrent, chunkProvider);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 253 */       while (longCurrent != null && longCurrent.getTargetTick() <= currentTick) {
/*     */ 
/*     */         
/* 256 */         objectBidirectionalIterator.remove();
/* 257 */         queueEntryForTick(longCurrent, chunkProvider);
/*     */         
/* 259 */         if (objectBidirectionalIterator.hasNext()) {
/* 260 */           longCurrent = objectBidirectionalIterator.next();
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void nextTick() {
/* 272 */     TickThread.softEnsureTickThread("async tick list tick");
/* 273 */     this.currentTick++;
/* 274 */     if (this.currentTick != this.world.getTime() && 
/* 275 */       !this.warnedAboutDesync) {
/* 276 */       this.warnedAboutDesync = true;
/* 277 */       MinecraftServer.LOGGER.error("World tick desync detected! Expected " + this.currentTick + " ticks, but got " + this.world.getTime() + " ticks for world '" + this.world.getWorld().getName() + "'", new Throwable());
/* 278 */       MinecraftServer.LOGGER.error("Preventing redstone from breaking by refusing to accept new tick time");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 285 */     TickThread.softEnsureTickThread("async tick list tick");
/* 286 */     ChunkProviderServer chunkProvider = this.world.getChunkProvider();
/*     */     
/* 288 */     this.world.getMethodProfiler().enter("cleaning");
/* 289 */     this.timingCleanup.startTiming();
/*     */     
/* 291 */     prepare();
/*     */ 
/*     */     
/* 294 */     this.shortScheduled[this.shortScheduledIndex].clear();
/* 295 */     this.shortScheduledIndex = getNextIndex(this.shortScheduledIndex, 401);
/*     */     
/* 297 */     this.timingCleanup.stopTiming();
/* 298 */     this.world.getMethodProfiler().exitEnter("ticking");
/* 299 */     this.timingTicking.startTiming();
/*     */     
/* 301 */     for (NextTickListEntry<T> toTick : this.toTickThisTick) {
/* 302 */       if (toTick.tickState != 4) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       try {
/* 307 */         if (chunkProvider.isTickingReadyMainThread(toTick.getPosition())) {
/* 308 */           toTick.tickState = 8;
/* 309 */           this.tickFunction.accept(toTick);
/* 310 */           if (toTick.tickState == 8) {
/* 311 */             toTick.tickState = 16;
/*     */           }
/* 313 */           MinecraftServer.getServer().executeMidTickTasks();
/*     */           continue;
/*     */         } 
/* 316 */         toTick.tickState = 2;
/* 317 */         addToNotTickingReady(toTick);
/*     */       }
/* 319 */       catch (Throwable thr) {
/*     */         
/* 321 */         CrashReport crashreport = CrashReport.a(thr, "Exception while ticking");
/* 322 */         CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being ticked");
/*     */         
/* 324 */         CrashReportSystemDetails.a(crashreportsystemdetails, toTick.getPosition(), (IBlockData)null);
/* 325 */         throw new ReportedException(crashreport);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 330 */     this.timingTicking.stopTiming();
/* 331 */     this.world.getMethodProfiler().exit();
/* 332 */     this.timingFinished.startTiming();
/*     */ 
/*     */     
/* 335 */     for (int i = 0, len = this.toTickThisTick.size(); i < len; i++) {
/* 336 */       NextTickListEntry<T> entry = this.toTickThisTick.poll();
/* 337 */       if (entry.tickState != 2)
/*     */       {
/*     */         
/* 340 */         onTickEnd(entry);
/*     */       }
/*     */     } 
/*     */     
/* 344 */     this.timingFinished.stopTiming();
/*     */   }
/*     */   
/*     */   private void onTickEnd(NextTickListEntry<T> entry) {
/* 348 */     if (entry.tickState == 32) {
/*     */       return;
/*     */     }
/* 351 */     entry.tickState = 1;
/*     */     
/* 353 */     BlockPosition pos = entry.getPosition();
/* 354 */     long blockKey = MCUtil.getBlockKey(pos);
/*     */     
/* 356 */     ArrayList<NextTickListEntry<T>> currentEntries = (ArrayList<NextTickListEntry<T>>)this.entriesByBlock.get(blockKey);
/*     */     
/* 358 */     if (currentEntries.size() == 1) {
/*     */       
/* 360 */       this.entriesByBlock.remove(blockKey);
/*     */     } else {
/*     */       
/* 363 */       for (int i = 0, len = currentEntries.size(); i < len; i++) {
/* 364 */         NextTickListEntry<T> currentEntry = currentEntries.get(i);
/* 365 */         if (currentEntry == entry) {
/* 366 */           currentEntries.remove(i);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 372 */     long chunkKey = MCUtil.getCoordinateKey(entry.getPosition());
/*     */     
/* 374 */     ObjectRBTreeSet<NextTickListEntry<T>> set = (ObjectRBTreeSet<NextTickListEntry<T>>)this.entriesByChunk.get(chunkKey);
/*     */     
/* 376 */     set.remove(entry);
/*     */     
/* 378 */     if (set.isEmpty()) {
/* 379 */       this.entriesByChunk.remove(chunkKey);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPendingTickThisTick(BlockPosition blockposition, T data) {
/* 387 */     ArrayList<NextTickListEntry<T>> entries = (ArrayList<NextTickListEntry<T>>)this.entriesByBlock.get(MCUtil.getBlockKey(blockposition));
/*     */     
/* 389 */     if (entries == null) {
/* 390 */       return false;
/*     */     }
/*     */     
/* 393 */     for (int i = 0, size = entries.size(); i < size; i++) {
/* 394 */       NextTickListEntry<T> entry = entries.get(i);
/* 395 */       if (entry.getData() == data && entry.tickState == 4) {
/* 396 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 400 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isScheduledForTick(BlockPosition blockposition, T data) {
/* 405 */     ArrayList<NextTickListEntry<T>> entries = (ArrayList<NextTickListEntry<T>>)this.entriesByBlock.get(MCUtil.getBlockKey(blockposition));
/*     */     
/* 407 */     if (entries == null) {
/* 408 */       return false;
/*     */     }
/*     */     
/* 411 */     for (int i = 0, size = entries.size(); i < size; i++) {
/* 412 */       NextTickListEntry<T> entry = entries.get(i);
/* 413 */       if (entry.getData() == data && entry.tickState == 2) {
/* 414 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 418 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void schedule(BlockPosition blockPosition, T t, int i, TickListPriority tickListPriority) {
/* 423 */     schedule(blockPosition, t, i + this.currentTick, tickListPriority);
/*     */   }
/*     */   
/*     */   public void schedule(NextTickListEntry<T> entry) {
/* 427 */     schedule(entry.getPosition(), (T)entry.getData(), entry.getTargetTick(), entry.getPriority());
/*     */   }
/*     */   
/*     */   public void schedule(BlockPosition pos, T data, long targetTick, TickListPriority priority) {
/* 431 */     TickThread.softEnsureTickThread("async tick list schedule");
/* 432 */     NextTickListEntry<T> entry = new NextTickListEntry(pos, data, targetTick, priority);
/* 433 */     if (this.excludeFromScheduling.test((T)entry.getData())) {
/*     */       return;
/*     */     }
/*     */     
/* 437 */     if (WARN_ON_EXCESSIVE_DELAY) {
/* 438 */       long delay = entry.getTargetTick() - this.currentTick;
/* 439 */       if (delay >= EXCESSIVE_DELAY_THRESHOLD) {
/* 440 */         MinecraftServer.LOGGER.warn("Entry " + entry.toString() + " has been scheduled with an excessive delay of: " + delay, new Throwable());
/*     */       }
/*     */     } 
/*     */     
/* 444 */     long blockKey = MCUtil.getBlockKey(pos);
/*     */     
/* 446 */     ArrayList<NextTickListEntry<T>> currentEntries = (ArrayList<NextTickListEntry<T>>)this.entriesByBlock.computeIfAbsent(blockKey, keyInMap -> new ArrayList(3));
/*     */     
/* 448 */     if (currentEntries.isEmpty()) {
/* 449 */       currentEntries.add(entry);
/*     */     } else {
/* 451 */       for (int i = 0, size = currentEntries.size(); i < size; i++) {
/* 452 */         NextTickListEntry<T> currentEntry = currentEntries.get(i);
/*     */ 
/*     */         
/* 455 */         if (currentEntry.getData() == entry.getData() && currentEntry.tickState == 2) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 460 */       currentEntries.add(entry);
/*     */     } 
/*     */     
/* 463 */     entry.tickState = 2;
/*     */     
/* 465 */     ((ObjectRBTreeSet)this.entriesByChunk.computeIfAbsent(MCUtil.getCoordinateKey(entry.getPosition()), keyInMap -> new ObjectRBTreeSet(TickListServerInterval.ENTRY_COMPARATOR)))
/*     */       
/* 467 */       .add(entry);
/*     */     
/* 469 */     addToSchedule(entry);
/*     */   }
/*     */   
/*     */   public void scheduleAll(Iterator<NextTickListEntry<T>> iterator) {
/* 473 */     while (iterator.hasNext()) {
/* 474 */       schedule(iterator.next());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isBlockInSortof(StructureBoundingBox boundingBox, BlockPosition pos) {
/* 482 */     return (pos.getX() >= boundingBox.getMinX() && pos.getX() < boundingBox.getMaxX() && pos.getZ() >= boundingBox.getMinZ() && pos.getZ() < boundingBox.getMaxZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public List<NextTickListEntry<T>> getEntriesInBoundingBox(StructureBoundingBox structureboundingbox, boolean removeReturned, boolean excludeTicked) {
/* 487 */     TickThread.softEnsureTickThread("async tick list get in bounding box");
/* 488 */     if (structureboundingbox.getMinX() == structureboundingbox.getMaxX() || structureboundingbox.getMinZ() == structureboundingbox.getMaxZ()) {
/* 489 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 492 */     int lowerChunkX = structureboundingbox.getMinX() >> 4;
/* 493 */     int upperChunkX = structureboundingbox.getMaxX() - 1 >> 4;
/* 494 */     int lowerChunkZ = structureboundingbox.getMinZ() >> 4;
/* 495 */     int upperChunkZ = structureboundingbox.getMaxZ() - 1 >> 4;
/*     */     
/* 497 */     int xChunksLength = upperChunkX - lowerChunkX + 1;
/* 498 */     int zChunksLength = upperChunkZ - lowerChunkZ + 1;
/*     */     
/* 500 */     ObjectRBTreeSet[] arrayOfObjectRBTreeSet = new ObjectRBTreeSet[xChunksLength * zChunksLength];
/*     */     
/* 502 */     int offset = xChunksLength * -lowerChunkZ - lowerChunkX;
/* 503 */     int totalEntries = 0;
/* 504 */     for (int currChunkX = lowerChunkX; currChunkX <= upperChunkX; currChunkX++) {
/* 505 */       for (int currChunkZ = lowerChunkZ; currChunkZ <= upperChunkZ; currChunkZ++) {
/*     */ 
/*     */         
/* 508 */         int index = offset + currChunkX + xChunksLength * currChunkZ;
/* 509 */         ObjectRBTreeSet<NextTickListEntry<T>> set = arrayOfObjectRBTreeSet[index] = (ObjectRBTreeSet<NextTickListEntry<T>>)this.entriesByChunk.get(MCUtil.getCoordinateKey(currChunkX, currChunkZ));
/* 510 */         if (set != null) {
/* 511 */           totalEntries += set.size();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 516 */     List<NextTickListEntry<T>> ret = new ArrayList<>(totalEntries);
/*     */     
/* 518 */     int matchOne = 0x6 | (excludeTicked ? 0 : 24);
/*     */     
/* 520 */     MCUtil.mergeSortedSets(entry -> { if (!isBlockInSortof(structureboundingbox, entry.getPosition())) return;  int tickState = entry.tickState; if ((tickState & matchOne) == 0) return;  ret.add(entry); }TickListServerInterval.ENTRY_COMPARATOR, (SortedSet[])arrayOfObjectRBTreeSet);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 533 */     if (removeReturned) {
/* 534 */       for (NextTickListEntry<T> entry : ret) {
/* 535 */         removeEntry(entry);
/*     */       }
/*     */     }
/*     */     
/* 539 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copy(StructureBoundingBox structureboundingbox, BlockPosition blockposition) {
/* 544 */     TickThread.softEnsureTickThread("async tick list copy");
/*     */     
/* 546 */     List<NextTickListEntry<T>> list = getEntriesInBoundingBox(structureboundingbox, false, false);
/* 547 */     Iterator<NextTickListEntry<T>> iterator = list.iterator();
/*     */     
/* 549 */     while (iterator.hasNext()) {
/* 550 */       NextTickListEntry<T> nextticklistentry = iterator.next();
/*     */       
/* 552 */       if (structureboundingbox.hasPoint((BaseBlockPosition)nextticklistentry.getPosition())) {
/* 553 */         BlockPosition blockposition1 = nextticklistentry.getPosition().add((BaseBlockPosition)blockposition);
/* 554 */         T t0 = (T)nextticklistentry.getData();
/*     */         
/* 556 */         schedule(new NextTickListEntry(blockposition1, t0, nextticklistentry.getTargetTick(), nextticklistentry.getPriority()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<NextTickListEntry<T>> getEntriesInChunk(ChunkCoordIntPair chunkPos, boolean removeReturned, boolean excludeTicked) {
/* 564 */     TickThread.softEnsureTickThread("async tick list get");
/*     */ 
/*     */ 
/*     */     
/* 568 */     int matchOne = 0x6 | (excludeTicked ? 0 : 24);
/*     */     
/* 570 */     ObjectRBTreeSet<NextTickListEntry<T>> entries = (ObjectRBTreeSet<NextTickListEntry<T>>)this.entriesByChunk.get(MCUtil.getCoordinateKey(chunkPos));
/*     */     
/* 572 */     if (entries == null) {
/* 573 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 576 */     List<NextTickListEntry<T>> ret = new ArrayList<>(entries.size());
/*     */     
/* 578 */     for (ObjectBidirectionalIterator<NextTickListEntry<T>> objectBidirectionalIterator = entries.iterator(); objectBidirectionalIterator.hasNext(); ) { NextTickListEntry<T> entry = objectBidirectionalIterator.next();
/* 579 */       if ((entry.tickState & matchOne) == 0) {
/*     */         continue;
/*     */       }
/* 582 */       ret.add(entry); }
/*     */ 
/*     */     
/* 585 */     if (removeReturned) {
/* 586 */       for (NextTickListEntry<T> entry : ret) {
/* 587 */         removeEntry(entry);
/*     */       }
/*     */     }
/*     */     
/* 591 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagList serialize(ChunkCoordIntPair chunkcoordintpair) {
/* 596 */     TickThread.softEnsureTickThread("async tick list serialize");
/*     */     
/* 598 */     List<NextTickListEntry<T>> list = getEntriesInChunk(chunkcoordintpair, false, true);
/*     */     
/* 600 */     return TickListServer.serialize(this.getMinecraftKeyFrom, list, this.currentTick);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalScheduledEntries() {
/* 606 */     TickThread.softEnsureTickThread("async tick list get size");
/*     */     
/* 608 */     int ret = 0;
/*     */     
/* 610 */     for (ObjectBidirectionalIterator<NextTickListEntry<T>> objectBidirectionalIterator = this.longScheduled.iterator(); objectBidirectionalIterator.hasNext(); ) { NextTickListEntry<T> entry = objectBidirectionalIterator.next();
/* 611 */       if (entry.tickState == 2) {
/* 612 */         ret++;
/*     */       } }
/*     */ 
/*     */     
/* 616 */     for (ObjectIterator<Long2ObjectMap.Entry> objectIterator = this.pendingChunkTickLoad.long2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) {
/* 617 */       ArrayList<NextTickListEntry<T>> list = (ArrayList<NextTickListEntry<T>>)((Long2ObjectMap.Entry)objectIterator.next()).getValue();
/*     */       
/* 619 */       for (NextTickListEntry<T> entry : list) {
/* 620 */         if (entry.tickState == 2) {
/* 621 */           ret++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 626 */     for (TickListServerInterval<T> interval : this.shortScheduled) {
/* 627 */       for (LinkedSortedSet<NextTickListEntry<T>> linkedSortedSet : interval.byPriority) {
/* 628 */         for (NextTickListEntry<T> entry : linkedSortedSet) {
/* 629 */           if (entry.tickState == 2) {
/* 630 */             ret++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 636 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\server\ticklist\PaperTickList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */