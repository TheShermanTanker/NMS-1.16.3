/*     */ package co.aikar.timings;
/*     */ 
/*     */ import co.aikar.util.JSONUtil;
/*     */ import co.aikar.util.LoadingMap;
/*     */ import co.aikar.util.MRUMapCache;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimingHistory
/*     */ {
/*     */   public static long lastMinuteTime;
/*     */   public static long timedTicks;
/*     */   public static long playerTicks;
/*     */   public static long entityTicks;
/*     */   public static long tileEntityTicks;
/*     */   public static long activatedEntityTicks;
/*  61 */   private static int worldIdPool = 1;
/*  62 */   static Map<String, Integer> worldMap = LoadingMap.newHashMap((Function)new Function<String, Integer>()
/*     */       {
/*     */         @NotNull
/*     */         public Integer apply(@Nullable String input) {
/*  66 */           return Integer.valueOf(TimingHistory.worldIdPool++);
/*     */         }
/*     */       });
/*     */   
/*     */   private final long endTime;
/*     */   private final long startTime;
/*     */   private final long totalTicks;
/*     */   private final long totalTime;
/*     */   private final MinuteReport[] minuteReports;
/*     */   private final TimingHistoryEntry[] entries;
/*  76 */   final Set<Material> tileEntityTypeSet = Sets.newHashSet();
/*  77 */   final Set<EntityType> entityTypeSet = Sets.newHashSet();
/*     */   private final Map<Object, Object> worlds;
/*     */   
/*     */   TimingHistory() {
/*  81 */     this.endTime = System.currentTimeMillis() / 1000L;
/*  82 */     this.startTime = TimingsManager.historyStart / 1000L;
/*  83 */     if (timedTicks % 1200L != 0L || TimingsManager.MINUTE_REPORTS.isEmpty()) {
/*  84 */       this.minuteReports = TimingsManager.MINUTE_REPORTS.<MinuteReport>toArray(new MinuteReport[TimingsManager.MINUTE_REPORTS.size() + 1]);
/*  85 */       this.minuteReports[this.minuteReports.length - 1] = new MinuteReport();
/*     */     } else {
/*  87 */       this.minuteReports = TimingsManager.MINUTE_REPORTS.<MinuteReport>toArray(new MinuteReport[TimingsManager.MINUTE_REPORTS.size()]);
/*     */     } 
/*  89 */     long ticks = 0L;
/*  90 */     for (MinuteReport mp : this.minuteReports) {
/*  91 */       ticks += mp.ticksRecord.timed;
/*     */     }
/*  93 */     this.totalTicks = ticks;
/*  94 */     this.totalTime = TimingsManager.FULL_SERVER_TICK.record.getTotalTime();
/*  95 */     this.entries = new TimingHistoryEntry[TimingsManager.HANDLERS.size()];
/*     */     
/*  97 */     int i = 0;
/*  98 */     for (TimingHandler handler : TimingsManager.HANDLERS) {
/*  99 */       this.entries[i++] = new TimingHistoryEntry(handler);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.worlds = JSONUtil.toObjectMapper(Bukkit.getWorlds(), new Function<World, JSONUtil.JSONPair>()
/*     */         {
/*     */           @NotNull
/*     */           public JSONUtil.JSONPair apply(World world) {
/* 108 */             Map<TimingHistory.RegionData.RegionId, TimingHistory.RegionData> regions = LoadingMap.newHashMap((Function)TimingHistory.RegionData.LOADER);
/*     */             
/* 110 */             for (Chunk chunk : world.getLoadedChunks()) {
/* 111 */               TimingHistory.RegionData data = regions.get(new TimingHistory.RegionData.RegionId(chunk.getX(), chunk.getZ()));
/*     */               
/* 113 */               for (Entity entity : chunk.getEntities()) {
/* 114 */                 if (entity == null) {
/* 115 */                   Bukkit.getLogger().warning("Null entity detected in chunk at position x: " + chunk.getX() + ", z: " + chunk.getZ());
/*     */                 }
/*     */                 else {
/*     */                   
/* 119 */                   ((TimingHistory.Counter)data.entityCounts.get(entity.getType())).increment();
/*     */                 } 
/*     */               } 
/* 122 */               for (BlockState tileEntity : chunk.getTileEntities(false)) {
/* 123 */                 if (tileEntity == null) {
/* 124 */                   Bukkit.getLogger().warning("Null tileentity detected in chunk at position x: " + chunk.getX() + ", z: " + chunk.getZ());
/*     */                 }
/*     */                 else {
/*     */                   
/* 128 */                   ((TimingHistory.Counter)data.tileEntityCounts.get(tileEntity.getBlock().getType())).increment();
/*     */                 } 
/*     */               } 
/* 131 */             }  return JSONUtil.pair(((Integer)TimingHistory.worldMap
/* 132 */                 .get(world.getName())).intValue(), 
/* 133 */                 JSONUtil.toArrayMapper(regions.values(), new Function<TimingHistory.RegionData, Object>()
/*     */                   {
/*     */                     @NotNull
/*     */                     public Object apply(TimingHistory.RegionData input) {
/* 137 */                       return JSONUtil.toArray(new Object[] {
/* 138 */                             Integer.valueOf(input.regionId.x), 
/* 139 */                             Integer.valueOf(input.regionId.z), 
/* 140 */                             JSONUtil.toObjectMapper(input.entityCounts.entrySet(), new Function<Map.Entry<EntityType, TimingHistory.Counter>, JSONUtil.JSONPair>()
/*     */                               {
/*     */                                 @NotNull
/*     */                                 public JSONUtil.JSONPair apply(Map.Entry<EntityType, TimingHistory.Counter> entry)
/*     */                                 {
/* 145 */                                   TimingHistory.this.entityTypeSet.add(entry.getKey());
/* 146 */                                   return JSONUtil.pair(
/* 147 */                                       String.valueOf(((EntityType)entry.getKey()).ordinal()), 
/* 148 */                                       Integer.valueOf(((TimingHistory.Counter)entry.getValue()).count()));
/*     */                                 }
/* 153 */                               }), JSONUtil.toObjectMapper(input.tileEntityCounts.entrySet(), new Function<Map.Entry<Material, TimingHistory.Counter>, JSONUtil.JSONPair>()
/*     */                               {
/*     */                                 @NotNull
/*     */                                 public JSONUtil.JSONPair apply(Map.Entry<Material, TimingHistory.Counter> entry)
/*     */                                 {
/* 158 */                                   TimingHistory.this.tileEntityTypeSet.add(entry.getKey());
/* 159 */                                   return JSONUtil.pair(
/* 160 */                                       String.valueOf(((Material)entry.getKey()).ordinal()), 
/* 161 */                                       Integer.valueOf(((TimingHistory.Counter)entry.getValue()).count()));
/*     */                                 }
/*     */                               })
/*     */                           });
/*     */                     }
/*     */                   }));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static class RegionData
/*     */   {
/*     */     final RegionId regionId;
/*     */     
/* 176 */     static Function<RegionId, RegionData> LOADER = new Function<RegionId, RegionData>()
/*     */       {
/*     */         @NotNull
/*     */         public TimingHistory.RegionData apply(@NotNull TimingHistory.RegionData.RegionId id) {
/* 180 */           return new TimingHistory.RegionData(id);
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final Map<EntityType, TimingHistory.Counter> entityCounts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final Map<Material, TimingHistory.Counter> tileEntityCounts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     RegionData(@NotNull RegionId id) {
/* 207 */       this
/* 208 */         .entityCounts = MRUMapCache.of(LoadingMap.of(new EnumMap<>(EntityType.class), k -> new TimingHistory.Counter()));
/*     */ 
/*     */       
/* 211 */       this
/* 212 */         .tileEntityCounts = MRUMapCache.of(LoadingMap.of(new EnumMap<>(Material.class), k -> new TimingHistory.Counter()));
/*     */       this.regionId = id;
/*     */     } public boolean equals(Object o) { if (this == o)
/*     */         return true; 
/*     */       if (o == null || getClass() != o.getClass())
/*     */         return false; 
/*     */       RegionData that = (RegionData)o;
/*     */       return this.regionId.equals(that.regionId); } public int hashCode() { return this.regionId.hashCode(); } static class RegionId {
/* 220 */       final int x; RegionId(int x, int z) { this.x = x >> 5 << 5;
/* 221 */         this.z = z >> 5 << 5;
/* 222 */         this.regionId = (this.x << 32L) + (this.z >> 5 << 5) - -2147483648L; }
/*     */       
/*     */       final int z; final long regionId;
/*     */       
/*     */       public boolean equals(Object o) {
/* 227 */         if (this == o) return true; 
/* 228 */         if (o == null || getClass() != o.getClass()) return false;
/*     */         
/* 230 */         RegionId regionId1 = (RegionId)o;
/*     */         
/* 232 */         return (this.regionId == regionId1.regionId);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int hashCode() {
/* 238 */         return (int)(this.regionId ^ this.regionId >>> 32L);
/*     */       } }
/*     */   }
/*     */   
/*     */   static void resetTicks(boolean fullReset) {
/* 243 */     if (fullReset)
/*     */     {
/* 245 */       timedTicks = 0L;
/*     */     }
/* 247 */     lastMinuteTime = System.nanoTime();
/* 248 */     playerTicks = 0L;
/* 249 */     tileEntityTicks = 0L;
/* 250 */     entityTicks = 0L;
/* 251 */     activatedEntityTicks = 0L;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   Object export() {
/* 256 */     return JSONUtil.createObject(new JSONUtil.JSONPair[] {
/* 257 */           JSONUtil.pair("s", Long.valueOf(this.startTime)), 
/* 258 */           JSONUtil.pair("e", Long.valueOf(this.endTime)), 
/* 259 */           JSONUtil.pair("tk", Long.valueOf(this.totalTicks)), 
/* 260 */           JSONUtil.pair("tm", Long.valueOf(this.totalTime)), 
/* 261 */           JSONUtil.pair("w", this.worlds), 
/* 262 */           JSONUtil.pair("h", JSONUtil.toArrayMapper((Object[])this.entries, new Function<TimingHistoryEntry, Object>()
/*     */               {
/*     */                 @Nullable
/*     */                 public Object apply(TimingHistoryEntry entry) {
/* 266 */                   TimingData record = entry.data;
/* 267 */                   if (!record.hasData()) {
/* 268 */                     return null;
/*     */                   }
/* 270 */                   return entry.export();
/*     */                 }
/* 273 */               })), JSONUtil.pair("mp", JSONUtil.toArrayMapper((Object[])this.minuteReports, new Function<MinuteReport, Object>()
/*     */               {
/*     */                 @NotNull
/*     */                 public Object apply(TimingHistory.MinuteReport input) {
/* 277 */                   return input.export();
/*     */                 }
/*     */               }))
/*     */         });
/*     */   }
/*     */   
/*     */   static class MinuteReport {
/* 284 */     final long time = System.currentTimeMillis() / 1000L;
/*     */     
/* 286 */     final TimingHistory.TicksRecord ticksRecord = new TimingHistory.TicksRecord();
/* 287 */     final TimingHistory.PingRecord pingRecord = new TimingHistory.PingRecord();
/* 288 */     final TimingData fst = TimingsManager.FULL_SERVER_TICK.minuteData.clone();
/* 289 */     final double tps = 1.0E9D / (System.nanoTime() - TimingHistory.lastMinuteTime) * this.ticksRecord.timed;
/* 290 */     final double usedMemory = TimingsManager.FULL_SERVER_TICK.avgUsedMemory;
/* 291 */     final double freeMemory = TimingsManager.FULL_SERVER_TICK.avgFreeMemory;
/* 292 */     final double loadAvg = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
/*     */     
/*     */     @NotNull
/*     */     List<Object> export() {
/* 296 */       return JSONUtil.toArray(new Object[] {
/* 297 */             Long.valueOf(this.time), 
/* 298 */             Double.valueOf(Math.round(this.tps * 100.0D) / 100.0D), 
/* 299 */             Double.valueOf(Math.round(this.pingRecord.avg * 100.0D) / 100.0D), this.fst
/* 300 */             .export(), 
/* 301 */             JSONUtil.toArray(new Object[] { Long.valueOf(this.ticksRecord.timed), 
/* 302 */                 Long.valueOf(this.ticksRecord.player), 
/* 303 */                 Long.valueOf(this.ticksRecord.entity), 
/* 304 */                 Long.valueOf(this.ticksRecord.activatedEntity), 
/* 305 */                 Long.valueOf(this.ticksRecord.tileEntity)
/*     */               
/* 307 */               }), Double.valueOf(this.usedMemory), 
/* 308 */             Double.valueOf(this.freeMemory), 
/* 309 */             Double.valueOf(this.loadAvg)
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   private static class TicksRecord {
/*     */     final long timed;
/*     */     final long player;
/*     */     final long entity;
/*     */     final long tileEntity;
/*     */     final long activatedEntity;
/*     */     
/*     */     TicksRecord() {
/* 322 */       this.timed = TimingHistory.timedTicks - (TimingsManager.MINUTE_REPORTS.size() * 1200);
/* 323 */       this.player = TimingHistory.playerTicks;
/* 324 */       this.entity = TimingHistory.entityTicks;
/* 325 */       this.tileEntity = TimingHistory.tileEntityTicks;
/* 326 */       this.activatedEntity = TimingHistory.activatedEntityTicks;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class PingRecord
/*     */   {
/*     */     final double avg;
/*     */     
/*     */     PingRecord() {
/* 335 */       Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
/* 336 */       int totalPing = 0;
/* 337 */       for (Player player : onlinePlayers) {
/* 338 */         totalPing += player.spigot().getPing();
/*     */       }
/* 340 */       this.avg = onlinePlayers.isEmpty() ? 0.0D : (totalPing / onlinePlayers.size());
/*     */     } }
/*     */   
/*     */   private static class Counter { private int count;
/*     */     
/*     */     private Counter() {
/* 346 */       this.count = 0;
/*     */     } public int increment() {
/* 348 */       return ++this.count;
/*     */     }
/*     */     public int count() {
/* 351 */       return this.count;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingHistory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */