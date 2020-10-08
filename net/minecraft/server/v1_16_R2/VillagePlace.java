/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.Timing;
/*     */ import com.destroystokyo.paper.io.IOUtil;
/*     */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ByteMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ public class VillagePlace extends RegionFileSection<VillagePlaceSection> {
/*  25 */   private final a a = new a();
/*  26 */   private final LongSet b = (LongSet)new LongOpenHashSet();
/*     */   
/*     */   private final WorldServer world;
/*     */ 
/*     */   
/*     */   public VillagePlace(File file, DataFixer datafixer, boolean flag) {
/*  32 */     this(file, datafixer, flag, (WorldServer)null);
/*     */   }
/*     */   public VillagePlace(File file, DataFixer datafixer, boolean flag, WorldServer world) {
/*  35 */     super(file, VillagePlaceSection::a, VillagePlaceSection::new, datafixer, DataFixTypes.POI_CHUNK, flag);
/*  36 */     this.world = world;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition blockposition, VillagePlaceType villageplacetype) {
/*  41 */     e(SectionPosition.a(blockposition).s()).a(blockposition, villageplacetype);
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition) {
/*  45 */     e(SectionPosition.a(blockposition).s()).a(blockposition);
/*     */   }
/*     */   
/*     */   public long a(Predicate<VillagePlaceType> predicate, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/*  49 */     return c(predicate, blockposition, i, villageplace_occupancy).count();
/*     */   }
/*     */   
/*     */   public boolean a(VillagePlaceType villageplacetype, BlockPosition blockposition) {
/*  53 */     Optional<VillagePlaceType> optional = e(SectionPosition.a(blockposition).s()).d(blockposition);
/*     */     
/*  55 */     return (optional.isPresent() && ((VillagePlaceType)optional.get()).equals(villageplacetype));
/*     */   }
/*     */   
/*     */   public Stream<VillagePlaceRecord> b(Predicate<VillagePlaceType> predicate, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/*  59 */     int j = Math.floorDiv(i, 16) + 1;
/*     */     
/*  61 */     return ChunkCoordIntPair.a(new ChunkCoordIntPair(blockposition), j).<VillagePlaceRecord>flatMap(chunkcoordintpair -> a(predicate, chunkcoordintpair, villageplace_occupancy))
/*     */       
/*  63 */       .filter(villageplacerecord -> {
/*     */           BlockPosition blockposition1 = villageplacerecord.f();
/*     */           
/*  66 */           return (Math.abs(blockposition1.getX() - blockposition.getX()) <= i && Math.abs(blockposition1.getZ() - blockposition.getZ()) <= i);
/*     */         });
/*     */   }
/*     */   
/*     */   public Stream<VillagePlaceRecord> c(Predicate<VillagePlaceType> predicate, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/*  71 */     int j = i * i;
/*     */     
/*  73 */     return b(predicate, blockposition, i, villageplace_occupancy).filter(villageplacerecord -> (villageplacerecord.f().j(blockposition) <= j));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<VillagePlaceRecord> a(Predicate<VillagePlaceType> predicate, ChunkCoordIntPair chunkcoordintpair, Occupancy villageplace_occupancy) {
/*  79 */     return IntStream.range(0, 16).boxed().map(integer -> d(SectionPosition.a(chunkcoordintpair, integer.intValue()).s()))
/*     */       
/*  81 */       .filter(Optional::isPresent).flatMap(optional -> ((VillagePlaceSection)optional.get()).a(predicate, villageplace_occupancy));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<BlockPosition> a(Predicate<VillagePlaceType> predicate, Predicate<BlockPosition> predicate1, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/*  87 */     return c(predicate, blockposition, i, villageplace_occupancy).<BlockPosition>map(VillagePlaceRecord::f).filter(predicate1);
/*     */   }
/*     */   
/*     */   public Stream<BlockPosition> b(Predicate<VillagePlaceType> predicate, Predicate<BlockPosition> predicate1, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/*  91 */     return a(predicate, predicate1, blockposition, i, villageplace_occupancy).sorted(Comparator.comparingDouble(blockposition1 -> blockposition1.j(blockposition)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<BlockPosition> c(Predicate<VillagePlaceType> predicate, Predicate<BlockPosition> predicate1, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/*  97 */     return a(predicate, predicate1, blockposition, i, villageplace_occupancy).findFirst();
/*     */   }
/*     */   
/*     */   public Optional<BlockPosition> d(Predicate<VillagePlaceType> predicate, BlockPosition blockposition, int i, Occupancy villageplace_occupancy) {
/* 101 */     return c(predicate, blockposition, i, villageplace_occupancy).<BlockPosition>map(VillagePlaceRecord::f).min(Comparator.comparingDouble(blockposition1 -> blockposition1.j(blockposition)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<BlockPosition> a(Predicate<VillagePlaceType> predicate, Predicate<BlockPosition> predicate1, BlockPosition blockposition, int i) {
/* 107 */     return c(predicate, blockposition, i, Occupancy.HAS_SPACE).filter(villageplacerecord -> predicate1.test(villageplacerecord.f()))
/*     */       
/* 109 */       .findFirst().map(villageplacerecord -> {
/*     */           villageplacerecord.b();
/*     */           return villageplacerecord.f();
/*     */         });
/*     */   }
/*     */   
/*     */   public Optional<BlockPosition> a(Predicate<VillagePlaceType> predicate, Predicate<BlockPosition> predicate1, Occupancy villageplace_occupancy, BlockPosition blockposition, int i, Random random) {
/* 116 */     List<VillagePlaceRecord> list = c(predicate, blockposition, i, villageplace_occupancy).collect((Collector)Collectors.toList());
/*     */     
/* 118 */     Collections.shuffle(list, random);
/* 119 */     return list.stream().filter(villageplacerecord -> predicate1.test(villageplacerecord.f()))
/*     */       
/* 121 */       .findFirst().map(VillagePlaceRecord::f);
/*     */   }
/*     */   
/*     */   public boolean b(BlockPosition blockposition) {
/* 125 */     return e(SectionPosition.a(blockposition).s()).c(blockposition);
/*     */   }
/*     */   
/*     */   public boolean a(BlockPosition blockposition, Predicate<VillagePlaceType> predicate) {
/* 129 */     return ((Boolean)d(SectionPosition.a(blockposition).s()).<Boolean>map(villageplacesection -> Boolean.valueOf(villageplacesection.a(blockposition, predicate)))
/*     */       
/* 131 */       .orElse(Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */   
/*     */   public Optional<VillagePlaceType> c(BlockPosition blockposition) {
/* 135 */     VillagePlaceSection villageplacesection = e(SectionPosition.a(blockposition).s());
/*     */     
/* 137 */     return villageplacesection.d(blockposition);
/*     */   }
/*     */   
/*     */   public int a(SectionPosition sectionposition) {
/* 141 */     this.a.a();
/* 142 */     return this.a.c(sectionposition.s());
/*     */   }
/*     */   
/*     */   private boolean f(long i) {
/* 146 */     Optional<VillagePlaceSection> optional = c(i);
/*     */     
/* 148 */     return (optional == null) ? false : ((Boolean)optional.<Boolean>map(villageplacesection -> Boolean.valueOf((villageplacesection.a(VillagePlaceType.b, Occupancy.IS_OCCUPIED).count() > 0L)))
/*     */       
/* 150 */       .orElse(Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(BooleanSupplier booleansupplier) {
/* 156 */     if (this.world == null) {
/* 157 */       super.a(booleansupplier);
/*     */     } else {
/*     */       
/* 160 */       while (!this.d.isEmpty() && booleansupplier.getAsBoolean()) {
/* 161 */         NBTTagCompound data; ChunkCoordIntPair chunkcoordintpair = SectionPosition.a(this.d.firstLong()).r();
/*     */ 
/*     */         
/* 164 */         Timing ignored1 = this.world.timings.poiSaveDataSerialization.startTiming(); 
/* 165 */         try { data = getData(chunkcoordintpair);
/* 166 */           if (ignored1 != null) ignored1.close();  } catch (Throwable throwable) { if (ignored1 != null)
/* 167 */             try { ignored1.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  PaperFileIOThread.Holder.INSTANCE.scheduleSave(this.world, chunkcoordintpair.x, chunkcoordintpair.z, data, null, 3);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 172 */     this.a.a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(long i) {
/* 177 */     super.a(i);
/* 178 */     this.a.b(i, this.a.b(i), false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(long i) {
/* 183 */     this.a.b(i, this.a.b(i), false);
/*     */   }
/*     */   
/*     */   public void a(ChunkCoordIntPair chunkcoordintpair, ChunkSection chunksection) {
/* 187 */     SectionPosition sectionposition = SectionPosition.a(chunkcoordintpair, chunksection.getYPosition() >> 4);
/*     */     
/* 189 */     SystemUtils.a(d(sectionposition.s()), villageplacesection -> villageplacesection.a(()), () -> {
/*     */           if (a(chunksection)) {
/*     */             VillagePlaceSection villageplacesection = e(sectionposition.s());
/*     */             Objects.requireNonNull(villageplacesection);
/*     */             a(chunksection, sectionposition, villageplacesection::a);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(ChunkSection chunksection) {
/* 207 */     Set<IBlockData> set = VillagePlaceType.x;
/*     */ 
/*     */     
/* 210 */     Objects.requireNonNull(set); return chunksection.a(set::contains);
/*     */   }
/*     */   
/*     */   private void a(ChunkSection chunksection, SectionPosition sectionposition, BiConsumer<BlockPosition, VillagePlaceType> biconsumer) {
/* 214 */     sectionposition.t().forEach(blockposition -> {
/*     */           IBlockData iblockdata = chunksection.getType(SectionPosition.b(blockposition.getX()), SectionPosition.b(blockposition.getY()), SectionPosition.b(blockposition.getZ()));
/*     */           VillagePlaceType.b(iblockdata).ifPresent(());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IWorldReader iworldreader, BlockPosition blockposition, int i) {
/* 224 */     SectionPosition.b(new ChunkCoordIntPair(blockposition), Math.floorDiv(i, 16)).map(sectionposition -> Pair.of(sectionposition, d(sectionposition.s())))
/*     */       
/* 226 */       .filter(pair -> !((Boolean)((Optional)pair.getSecond()).map(VillagePlaceSection::a).orElse(Boolean.valueOf(false))).booleanValue())
/*     */       
/* 228 */       .map(pair -> ((SectionPosition)pair.getFirst()).r())
/*     */       
/* 230 */       .filter(chunkcoordintpair -> this.b.add(chunkcoordintpair.pair()))
/*     */       
/* 232 */       .forEach(chunkcoordintpair -> iworldreader.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z, ChunkStatus.EMPTY));
/*     */   }
/*     */ 
/*     */   
/*     */   final class a
/*     */     extends LightEngineGraphSection
/*     */   {
/* 239 */     private final Long2ByteMap b = (Long2ByteMap)new Long2ByteOpenHashMap();
/*     */     
/*     */     protected a() {
/* 242 */       super(7, 16, 256);
/* 243 */       this.b.defaultReturnValue((byte)7);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int b(long i) {
/* 248 */       return VillagePlace.this.f(i) ? 0 : 7;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int c(long i) {
/* 253 */       return this.b.get(i);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(long i, int j) {
/* 258 */       if (j > 6) {
/* 259 */         this.b.remove(i);
/*     */       } else {
/* 261 */         this.b.put(i, (byte)j);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 267 */       b(2147483647);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound read(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/* 275 */     if (this.world != null && Thread.currentThread() != PaperFileIOThread.Holder.INSTANCE) {
/*     */ 
/*     */       
/* 278 */       NBTTagCompound ret = ((PaperFileIOThread.ChunkData)PaperFileIOThread.Holder.INSTANCE.loadChunkDataAsyncFuture(this.world, chunkcoordintpair.x, chunkcoordintpair.z, IOUtil.getPriorityForCurrentThread(), true, false, true).join()).poiData;
/*     */       
/* 280 */       if (ret == PaperFileIOThread.FAILURE_VALUE) {
/* 281 */         throw new IOException("See logs for further detail");
/*     */       }
/* 283 */       return ret;
/*     */     } 
/* 285 */     return super.read(chunkcoordintpair);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
/* 290 */     if (this.world != null && Thread.currentThread() != PaperFileIOThread.Holder.INSTANCE) {
/* 291 */       PaperFileIOThread.Holder.INSTANCE.scheduleSave(this.world, chunkcoordintpair.x, chunkcoordintpair.z, nbttagcompound, null, 
/*     */           
/* 293 */           IOUtil.getPriorityForCurrentThread());
/*     */       return;
/*     */     } 
/* 296 */     super.write(chunkcoordintpair, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Occupancy
/*     */   {
/* 302 */     HAS_SPACE((String)VillagePlaceRecord::d), IS_OCCUPIED((String)VillagePlaceRecord::e), ANY((String)(villageplacerecord -> true));
/*     */ 
/*     */     
/*     */     private final Predicate<? super VillagePlaceRecord> d;
/*     */ 
/*     */     
/*     */     Occupancy(Predicate<? super VillagePlaceRecord> predicate) {
/* 309 */       this.d = predicate;
/*     */     }
/*     */     
/*     */     public Predicate<? super VillagePlaceRecord> a() {
/* 313 */       return this.d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagePlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */