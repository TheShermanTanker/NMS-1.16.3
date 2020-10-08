/*     */ package com.destroystokyo.paper.util.maplist;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2LongOpenHashMap;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.server.v1_16_R2.ChunkSection;
/*     */ import net.minecraft.server.v1_16_R2.DataPaletteGlobal;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IBlockDataList
/*     */ {
/*     */   public IBlockDataList() {
/*  18 */     this.map = new Short2LongOpenHashMap(2, 0.8F);
/*     */     
/*  20 */     this.map.defaultReturnValue(Long.MAX_VALUE);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  25 */     this.byIndex = EMPTY_LIST;
/*     */   }
/*     */   static final DataPaletteGlobal<IBlockData> GLOBAL_PALETTE = (DataPaletteGlobal<IBlockData>)ChunkSection.GLOBAL_PALETTE; private final Short2LongOpenHashMap map;
/*     */   public static int getLocationKey(int x, int y, int z) {
/*  29 */     return x & 0xF | (z & 0xF) << 4 | (y & 0xFF) << 8;
/*     */   }
/*     */   private static final long[] EMPTY_LIST = new long[0]; private long[] byIndex; private int size;
/*     */   public static IBlockData getBlockDataFromRaw(long raw) {
/*  33 */     return (IBlockData)GLOBAL_PALETTE.getObject((int)(raw >>> 32L));
/*     */   }
/*     */   
/*     */   public static int getIndexFromRaw(long raw) {
/*  37 */     return (int)(raw & 0xFFFFL);
/*     */   }
/*     */   
/*     */   public static int getLocationFromRaw(long raw) {
/*  41 */     return (int)(raw >>> 16L & 0xFFFFL);
/*     */   }
/*     */   
/*     */   public static long getRawFromValues(int index, int location, IBlockData data) {
/*  45 */     return index | location << 16L | GLOBAL_PALETTE.getOrCreateIdFor(data) << 32L;
/*     */   }
/*     */   
/*     */   public static long setIndexRawValues(long value, int index) {
/*  49 */     return value & 0xFFFFFFFFFFFF0000L | index;
/*     */   }
/*     */   
/*     */   public long add(int x, int y, int z, IBlockData data) {
/*  53 */     return add(getLocationKey(x, y, z), data);
/*     */   }
/*     */   
/*     */   public long add(int location, IBlockData data) {
/*  57 */     long curr = this.map.get((short)location);
/*     */     
/*  59 */     if (curr == Long.MAX_VALUE) {
/*  60 */       int i = this.size++;
/*  61 */       long l = getRawFromValues(i, location, data);
/*  62 */       this.map.put((short)location, l);
/*     */       
/*  64 */       if (i >= this.byIndex.length) {
/*  65 */         this.byIndex = Arrays.copyOf(this.byIndex, (int)Math.max(4L, this.byIndex.length * 2L));
/*     */       }
/*     */       
/*  68 */       this.byIndex[i] = l;
/*  69 */       return l;
/*     */     } 
/*  71 */     int index = getIndexFromRaw(curr);
/*  72 */     long raw = this.byIndex[index] = getRawFromValues(index, location, data);
/*     */     
/*  74 */     this.map.put((short)location, raw);
/*     */     
/*  76 */     return raw;
/*     */   }
/*     */ 
/*     */   
/*     */   public long remove(int x, int y, int z) {
/*  81 */     return remove(getLocationKey(x, y, z));
/*     */   }
/*     */   
/*     */   public long remove(int location) {
/*  85 */     long ret = this.map.remove((short)location);
/*  86 */     int index = getIndexFromRaw(ret);
/*  87 */     if (ret == Long.MAX_VALUE) {
/*  88 */       return ret;
/*     */     }
/*     */ 
/*     */     
/*  92 */     int endIndex = --this.size;
/*  93 */     long end = this.byIndex[endIndex];
/*  94 */     if (index != endIndex)
/*     */     {
/*  96 */       this.map.put((short)getLocationFromRaw(end), setIndexRawValues(end, index));
/*     */     }
/*  98 */     this.byIndex[index] = end;
/*  99 */     this.byIndex[endIndex] = 0L;
/*     */     
/* 101 */     return ret;
/*     */   }
/*     */   
/*     */   public int size() {
/* 105 */     return this.size;
/*     */   }
/*     */   
/*     */   public long getRaw(int index) {
/* 109 */     return this.byIndex[index];
/*     */   }
/*     */   
/*     */   public int getLocation(int index) {
/* 113 */     return getLocationFromRaw(getRaw(index));
/*     */   }
/*     */   
/*     */   public IBlockData getData(int index) {
/* 117 */     return getBlockDataFromRaw(getRaw(index));
/*     */   }
/*     */   
/*     */   public void clear() {
/* 121 */     this.size = 0;
/* 122 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public LongIterator getRawIterator() {
/* 126 */     return this.map.values().iterator();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\maplist\IBlockDataList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */