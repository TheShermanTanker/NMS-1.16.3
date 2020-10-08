/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.Timing;
/*     */ import co.aikar.timings.WorldTimingsHandler;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Queues;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class TickListServer<T> implements TickList<T> {
/*     */   protected final Predicate<T> a;
/*     */   private final Function<T, MinecraftKey> b;
/*  22 */   private final Set<NextTickListEntry<T>> nextTickListHash = Sets.newHashSet();
/*  23 */   private final TreeSet<NextTickListEntry<T>> nextTickList = Sets.newTreeSet(NextTickListEntry.a());
/*     */   private final WorldServer e;
/*  25 */   private final Queue<NextTickListEntry<T>> f = Queues.newArrayDeque();
/*  26 */   private final List<NextTickListEntry<T>> g = Lists.newArrayList();
/*     */   private final Consumer<NextTickListEntry<T>> h;
/*     */   
/*     */   public TickListServer(WorldServer worldserver, Predicate<T> predicate, Function<T, MinecraftKey> function, Consumer<NextTickListEntry<T>> consumer, String timingsType) {
/*  30 */     this.a = predicate;
/*  31 */     this.b = function;
/*  32 */     this.e = worldserver;
/*  33 */     this.h = consumer;
/*  34 */     this.timingCleanup = WorldTimingsHandler.getTickList(worldserver, timingsType + " - Cleanup");
/*  35 */     this.timingTicking = WorldTimingsHandler.getTickList(worldserver, timingsType + " - Ticking");
/*     */   }
/*     */ 
/*     */   
/*     */   private final Timing timingCleanup;
/*     */   
/*     */   private final Timing timingTicking;
/*     */ 
/*     */   
/*     */   protected void nextTick() {}
/*     */   
/*     */   public void b() {
/*  47 */     tick();
/*     */   }
/*     */   
/*     */   public void tick() {
/*  51 */     int i = this.nextTickList.size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     if (i > 65536)
/*     */     {
/*  58 */       if (i > 1310720) {
/*  59 */         i /= 20;
/*     */       } else {
/*  61 */         i = 65536;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  66 */     ChunkProviderServer chunkproviderserver = this.e.getChunkProvider();
/*  67 */     Iterator<NextTickListEntry<T>> iterator = this.nextTickList.iterator();
/*     */     
/*  69 */     this.e.getMethodProfiler().enter("cleaning");
/*     */     
/*  71 */     this.timingCleanup.startTiming();
/*     */ 
/*     */     
/*  74 */     while (i > 0 && iterator.hasNext()) {
/*  75 */       NextTickListEntry<T> nextTickListEntry = iterator.next();
/*  76 */       if (nextTickListEntry.b > this.e.getTime()) {
/*     */         break;
/*     */       }
/*     */       
/*  80 */       if (chunkproviderserver.a(nextTickListEntry.a)) {
/*  81 */         iterator.remove();
/*  82 */         this.nextTickListHash.remove(nextTickListEntry);
/*  83 */         this.f.add(nextTickListEntry);
/*  84 */         i--;
/*     */       } 
/*     */     } 
/*  87 */     this.timingCleanup.stopTiming();
/*     */     
/*  89 */     this.timingTicking.startTiming();
/*  90 */     this.e.getMethodProfiler().exitEnter("ticking");
/*     */     NextTickListEntry<T> nextticklistentry;
/*  92 */     while ((nextticklistentry = this.f.poll()) != null) {
/*  93 */       if (chunkproviderserver.a(nextticklistentry.a)) {
/*     */         try {
/*  95 */           this.g.add(nextticklistentry);
/*  96 */           this.h.accept(nextticklistentry);
/*  97 */         } catch (Throwable throwable) {
/*  98 */           CrashReport crashreport = CrashReport.a(throwable, "Exception while ticking");
/*  99 */           CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being ticked");
/*     */           
/* 101 */           CrashReportSystemDetails.a(crashreportsystemdetails, nextticklistentry.a, (IBlockData)null);
/* 102 */           throw new ReportedException(crashreport);
/*     */         }  continue;
/*     */       } 
/* 105 */       a(nextticklistentry.a, nextticklistentry.b(), 0);
/*     */     } 
/*     */ 
/*     */     
/* 109 */     this.timingTicking.stopTiming();
/* 110 */     this.e.getMethodProfiler().exit();
/* 111 */     this.g.clear();
/* 112 */     this.f.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(BlockPosition blockposition, T t0) {
/* 119 */     return isPendingTickThisTick(blockposition, t0);
/*     */   }
/*     */   
/*     */   public boolean isPendingTickThisTick(BlockPosition blockposition, T t0) {
/* 123 */     return this.f.contains(new NextTickListEntry<>(blockposition, t0));
/*     */   }
/*     */ 
/*     */   
/*     */   public List<NextTickListEntry<T>> a(ChunkCoordIntPair chunkcoordintpair, boolean flag, boolean flag1) {
/* 128 */     return getEntriesInChunk(chunkcoordintpair, flag, flag1);
/*     */   }
/*     */   
/*     */   public List<NextTickListEntry<T>> getEntriesInChunk(ChunkCoordIntPair chunkcoordintpair, boolean flag, boolean flag1) {
/* 132 */     int i = (chunkcoordintpair.x << 4) - 2;
/* 133 */     int j = i + 16 + 2;
/* 134 */     int k = (chunkcoordintpair.z << 4) - 2;
/* 135 */     int l = k + 16 + 2;
/*     */     
/* 137 */     return a(new StructureBoundingBox(i, 0, k, j, 256, l), flag, flag1);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<NextTickListEntry<T>> a(StructureBoundingBox structureboundingbox, boolean flag, boolean flag1) {
/* 142 */     return getEntriesInBoundingBox(structureboundingbox, flag, flag1);
/*     */   }
/*     */   
/*     */   public List<NextTickListEntry<T>> getEntriesInBoundingBox(StructureBoundingBox structureboundingbox, boolean flag, boolean flag1) {
/* 146 */     List<NextTickListEntry<T>> list = a((List<NextTickListEntry<T>>)null, this.nextTickList, structureboundingbox, flag);
/*     */     
/* 148 */     if (flag && list != null) {
/* 149 */       this.nextTickListHash.removeAll(list);
/*     */     }
/*     */     
/* 152 */     list = a(list, this.f, structureboundingbox, flag);
/* 153 */     if (!flag1) {
/* 154 */       list = a(list, this.g, structureboundingbox, flag);
/*     */     }
/*     */     
/* 157 */     return (list == null) ? Collections.<NextTickListEntry<T>>emptyList() : list;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private List<NextTickListEntry<T>> a(@Nullable List<NextTickListEntry<T>> list, Collection<NextTickListEntry<T>> collection, StructureBoundingBox structureboundingbox, boolean flag) {
/* 162 */     Iterator<NextTickListEntry<T>> iterator = collection.iterator();
/*     */     
/* 164 */     while (iterator.hasNext()) {
/* 165 */       NextTickListEntry<T> nextticklistentry = iterator.next();
/* 166 */       BlockPosition blockposition = nextticklistentry.a;
/*     */       
/* 168 */       if (blockposition.getX() >= structureboundingbox.a && blockposition.getX() < structureboundingbox.d && blockposition.getZ() >= structureboundingbox.c && blockposition.getZ() < structureboundingbox.f) {
/* 169 */         if (flag) {
/* 170 */           iterator.remove();
/*     */         }
/*     */         
/* 173 */         if (list == null) {
/* 174 */           list = Lists.newArrayList();
/*     */         }
/*     */         
/* 177 */         list.add(nextticklistentry);
/*     */       } 
/*     */     } 
/*     */     
/* 181 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(StructureBoundingBox structureboundingbox, BlockPosition blockposition) {
/* 186 */     copy(structureboundingbox, blockposition);
/*     */   }
/*     */   
/*     */   public void copy(StructureBoundingBox structureboundingbox, BlockPosition blockposition) {
/* 190 */     List<NextTickListEntry<T>> list = a(structureboundingbox, false, false);
/* 191 */     Iterator<NextTickListEntry<T>> iterator = list.iterator();
/*     */     
/* 193 */     while (iterator.hasNext()) {
/* 194 */       NextTickListEntry<T> nextticklistentry = iterator.next();
/*     */       
/* 196 */       if (structureboundingbox.b(nextticklistentry.a)) {
/* 197 */         BlockPosition blockposition1 = nextticklistentry.a.a(blockposition);
/* 198 */         T t0 = nextticklistentry.b();
/*     */         
/* 200 */         a(new NextTickListEntry<>(blockposition1, t0, nextticklistentry.b, nextticklistentry.c));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList a(ChunkCoordIntPair chunkcoordintpair) {
/* 208 */     return serialize(chunkcoordintpair);
/*     */   }
/*     */   
/*     */   public NBTTagList serialize(ChunkCoordIntPair chunkcoordintpair) {
/* 212 */     List<NextTickListEntry<T>> list = a(chunkcoordintpair, false, true);
/*     */     
/* 214 */     return a(this.b, list, this.e.getTime());
/*     */   }
/*     */   public static <T> NBTTagList serialize(Function<T, MinecraftKey> function, Iterable<NextTickListEntry<T>> iterable, long i) {
/* 217 */     return a(function, iterable, i);
/*     */   } private static <T> NBTTagList a(Function<T, MinecraftKey> function, Iterable<NextTickListEntry<T>> iterable, long i) {
/* 219 */     NBTTagList nbttaglist = new NBTTagList();
/* 220 */     Iterator<NextTickListEntry<T>> iterator = iterable.iterator();
/*     */     
/* 222 */     while (iterator.hasNext()) {
/* 223 */       NextTickListEntry<T> nextticklistentry = iterator.next();
/* 224 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */       
/* 226 */       nbttagcompound.setString("i", ((MinecraftKey)function.apply(nextticklistentry.b())).toString());
/* 227 */       nbttagcompound.setInt("x", nextticklistentry.a.getX());
/* 228 */       nbttagcompound.setInt("y", nextticklistentry.a.getY());
/* 229 */       nbttagcompound.setInt("z", nextticklistentry.a.getZ());
/* 230 */       nbttagcompound.setInt("t", (int)(nextticklistentry.b - i));
/* 231 */       nbttagcompound.setInt("p", nextticklistentry.c.a());
/* 232 */       nbttaglist.add(nbttagcompound);
/*     */     } 
/*     */     
/* 235 */     return nbttaglist;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, T t0) {
/* 241 */     return isScheduledForTick(blockposition, t0);
/*     */   }
/*     */   
/*     */   public boolean isScheduledForTick(BlockPosition blockposition, T t0) {
/* 245 */     return this.nextTickListHash.contains(new NextTickListEntry<>(blockposition, t0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(BlockPosition blockposition, T t0, int i, TickListPriority ticklistpriority) {
/* 251 */     schedule(blockposition, t0, i, ticklistpriority);
/*     */   }
/*     */   
/*     */   public void schedule(BlockPosition blockposition, T t0, int i, TickListPriority ticklistpriority) {
/* 255 */     if (!this.a.test(t0)) {
/* 256 */       a(new NextTickListEntry<>(blockposition, t0, i + this.e.getTime(), ticklistpriority));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(NextTickListEntry<T> nextticklistentry) {
/* 262 */     if (!this.nextTickListHash.contains(nextticklistentry)) {
/* 263 */       this.nextTickListHash.add(nextticklistentry);
/* 264 */       this.nextTickList.add(nextticklistentry);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 271 */     return getTotalScheduledEntries();
/*     */   }
/*     */   
/*     */   public int getTotalScheduledEntries() {
/* 275 */     return this.nextTickListHash.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickListServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */