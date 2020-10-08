/*     */ package com.destroystokyo.paper.util.maplist;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import net.minecraft.server.v1_16_R2.Chunk;
/*     */ 
/*     */ 
/*     */ public final class ChunkList
/*     */   implements Iterable<Chunk>
/*     */ {
/*     */   protected final Long2IntOpenHashMap chunkToIndex;
/*     */   
/*     */   public ChunkList() {
/*  16 */     this.chunkToIndex = new Long2IntOpenHashMap(2, 0.8F);
/*     */     
/*  18 */     this.chunkToIndex.defaultReturnValue(-2147483648);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  23 */     this.chunks = EMPTY_LIST;
/*     */   }
/*     */   protected static final Chunk[] EMPTY_LIST = new Chunk[0];
/*     */   public int size() {
/*  27 */     return this.count;
/*     */   }
/*     */   protected Chunk[] chunks; protected int count;
/*     */   public boolean contains(Chunk chunk) {
/*  31 */     return this.chunkToIndex.containsKey(chunk.coordinateKey);
/*     */   }
/*     */   
/*     */   public boolean remove(Chunk chunk) {
/*  35 */     int index = this.chunkToIndex.remove(chunk.coordinateKey);
/*  36 */     if (index == Integer.MIN_VALUE) {
/*  37 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  41 */     int endIndex = --this.count;
/*  42 */     Chunk end = this.chunks[endIndex];
/*  43 */     if (index != endIndex)
/*     */     {
/*  45 */       this.chunkToIndex.put(end.coordinateKey, index);
/*     */     }
/*  47 */     this.chunks[index] = end;
/*  48 */     this.chunks[endIndex] = null;
/*     */     
/*  50 */     return true;
/*     */   }
/*     */   
/*     */   public boolean add(Chunk chunk) {
/*  54 */     int count = this.count;
/*  55 */     int currIndex = this.chunkToIndex.putIfAbsent(chunk.coordinateKey, count);
/*     */     
/*  57 */     if (currIndex != Integer.MIN_VALUE) {
/*  58 */       return false;
/*     */     }
/*     */     
/*  61 */     Chunk[] list = this.chunks;
/*     */     
/*  63 */     if (list.length == count)
/*     */     {
/*  65 */       list = this.chunks = Arrays.copyOf(list, (int)Math.max(4L, count * 2L));
/*     */     }
/*     */     
/*  68 */     list[count] = chunk;
/*  69 */     this.count = count + 1;
/*     */     
/*  71 */     return true;
/*     */   }
/*     */   
/*     */   public Chunk getChecked(int index) {
/*  75 */     if (index < 0 || index >= this.count) {
/*  76 */       throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds, size: " + this.count);
/*     */     }
/*  78 */     return this.chunks[index];
/*     */   }
/*     */   
/*     */   public Chunk getUnchecked(int index) {
/*  82 */     return this.chunks[index];
/*     */   }
/*     */   
/*     */   public Chunk[] getRawData() {
/*  86 */     return this.chunks;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  90 */     this.chunkToIndex.clear();
/*  91 */     Arrays.fill((Object[])this.chunks, 0, this.count, (Object)null);
/*  92 */     this.count = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<Chunk> iterator() {
/*  97 */     return new Iterator<Chunk>()
/*     */       {
/*     */         Chunk lastRet;
/*     */         
/*     */         int current;
/*     */         
/*     */         public boolean hasNext() {
/* 104 */           return (this.current < ChunkList.this.count);
/*     */         }
/*     */ 
/*     */         
/*     */         public Chunk next() {
/* 109 */           if (this.current >= ChunkList.this.count) {
/* 110 */             throw new NoSuchElementException();
/*     */           }
/* 112 */           return this.lastRet = ChunkList.this.chunks[this.current++];
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 117 */           Chunk lastRet = this.lastRet;
/*     */           
/* 119 */           if (lastRet == null) {
/* 120 */             throw new IllegalStateException();
/*     */           }
/* 122 */           this.lastRet = null;
/*     */           
/* 124 */           ChunkList.this.remove(lastRet);
/* 125 */           this.current--;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\maplist\ChunkList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */