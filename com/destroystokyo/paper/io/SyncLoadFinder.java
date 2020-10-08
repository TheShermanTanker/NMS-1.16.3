/*     */ package com.destroystokyo.paper.io;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ 
/*     */ public class SyncLoadFinder {
/*  19 */   public static final boolean ENABLED = Boolean.getBoolean("paper.debug-sync-loads");
/*     */   
/*  21 */   private static final WeakHashMap<World, Object2ObjectOpenHashMap<ThrowableWithEquals, SyncLoadInformation>> SYNC_LOADS = new WeakHashMap<>();
/*     */ 
/*     */   
/*     */   private static final class SyncLoadInformation
/*     */   {
/*     */     public int times;
/*  27 */     public final Long2IntOpenHashMap coordinateTimes = new Long2IntOpenHashMap();
/*     */     private SyncLoadInformation() {} }
/*     */   
/*     */   public static void logSyncLoad(World world, int chunkX, int chunkZ) {
/*  31 */     if (!ENABLED) {
/*     */       return;
/*     */     }
/*     */     
/*  35 */     ThrowableWithEquals stacktrace = new ThrowableWithEquals(Thread.currentThread().getStackTrace());
/*     */     
/*  37 */     SYNC_LOADS.compute(world, (keyInMap, map) -> {
/*     */           if (map == null) {
/*     */             map = new Object2ObjectOpenHashMap();
/*     */           }
/*     */           map.compute(stacktrace, ());
/*     */           return map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonObject serialize() {
/*  61 */     JsonObject ret = new JsonObject();
/*     */     
/*  63 */     JsonArray worldsData = new JsonArray();
/*     */     
/*  65 */     for (Iterator<Map.Entry<World, Object2ObjectOpenHashMap<ThrowableWithEquals, SyncLoadInformation>>> iterator = SYNC_LOADS.entrySet().iterator(); iterator.hasNext(); ) { Map.Entry<World, Object2ObjectOpenHashMap<ThrowableWithEquals, SyncLoadInformation>> entry = iterator.next();
/*  66 */       World world = entry.getKey();
/*     */       
/*  68 */       JsonObject worldData = new JsonObject();
/*     */       
/*  70 */       worldData.addProperty("name", world.getWorld().getName());
/*     */       
/*  72 */       List<Pair<ThrowableWithEquals, SyncLoadInformation>> data = new ArrayList<>();
/*     */       
/*  74 */       ((Object2ObjectOpenHashMap)entry.getValue()).forEach((stacktrace, times) -> data.add(new Pair(stacktrace, times)));
/*     */ 
/*     */ 
/*     */       
/*  78 */       data.sort((pair1, pair2) -> Integer.compare(((SyncLoadInformation)pair2.getSecond()).times, ((SyncLoadInformation)pair1.getSecond()).times));
/*     */ 
/*     */ 
/*     */       
/*  82 */       JsonArray stacktraces = new JsonArray();
/*     */       
/*  84 */       for (Pair<ThrowableWithEquals, SyncLoadInformation> pair : data) {
/*  85 */         JsonObject stacktrace = new JsonObject();
/*     */         
/*  87 */         stacktrace.addProperty("times", Integer.valueOf(((SyncLoadInformation)pair.getSecond()).times));
/*     */         
/*  89 */         JsonArray traces = new JsonArray();
/*     */         
/*  91 */         for (StackTraceElement element : ((ThrowableWithEquals)pair.getFirst()).stacktrace) {
/*  92 */           traces.add(String.valueOf(element));
/*     */         }
/*     */         
/*  95 */         stacktrace.add("stacktrace", (JsonElement)traces);
/*     */         
/*  97 */         JsonArray coordinates = new JsonArray();
/*     */         
/*  99 */         for (ObjectIterator<Long2IntMap.Entry> objectIterator = ((SyncLoadInformation)pair.getSecond()).coordinateTimes.long2IntEntrySet().iterator(); objectIterator.hasNext(); ) { Long2IntMap.Entry coordinate = objectIterator.next();
/* 100 */           long key = coordinate.getLongKey();
/* 101 */           int times = coordinate.getIntValue();
/* 102 */           coordinates.add("(" + IOUtil.getCoordinateX(key) + "," + IOUtil.getCoordinateZ(key) + "): " + times); }
/*     */ 
/*     */         
/* 105 */         stacktrace.add("coordinates", (JsonElement)coordinates);
/*     */         
/* 107 */         stacktraces.add((JsonElement)stacktrace);
/*     */       } 
/*     */ 
/*     */       
/* 111 */       worldData.add("stacktraces", (JsonElement)stacktraces);
/* 112 */       worldsData.add((JsonElement)worldData); }
/*     */ 
/*     */     
/* 115 */     ret.add("worlds", (JsonElement)worldsData);
/*     */     
/* 117 */     return ret;
/*     */   }
/*     */   
/*     */   static final class ThrowableWithEquals
/*     */   {
/*     */     private final StackTraceElement[] stacktrace;
/*     */     private final int hash;
/*     */     
/*     */     public ThrowableWithEquals(StackTraceElement[] stacktrace) {
/* 126 */       this.stacktrace = stacktrace;
/* 127 */       this.hash = hash(stacktrace);
/*     */     }
/*     */     
/*     */     public static int hash(StackTraceElement[] stacktrace) {
/* 131 */       int hash = 0;
/*     */       
/* 133 */       for (int i = 0; i < stacktrace.length; i++) {
/* 134 */         hash *= 31;
/* 135 */         hash += stacktrace[i].hashCode();
/*     */       } 
/*     */       
/* 138 */       return hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 143 */       return this.hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 148 */       if (obj == null || obj.getClass() != getClass()) {
/* 149 */         return false;
/*     */       }
/*     */       
/* 152 */       ThrowableWithEquals other = (ThrowableWithEquals)obj;
/* 153 */       StackTraceElement[] otherStackTrace = other.stacktrace;
/*     */       
/* 155 */       if (this.stacktrace.length != otherStackTrace.length || this.hash != other.hash) {
/* 156 */         return false;
/*     */       }
/*     */       
/* 159 */       if (this == obj) {
/* 160 */         return true;
/*     */       }
/*     */       
/* 163 */       for (int i = 0; i < this.stacktrace.length; i++) {
/* 164 */         if (!this.stacktrace[i].equals(otherStackTrace[i])) {
/* 165 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 169 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\SyncLoadFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */