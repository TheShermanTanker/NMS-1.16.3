/*     */ package com.destroystokyo.paper.util.map;
/*     */ 
/*     */ import com.destroystokyo.paper.util.concurrent.WeakSeqLock;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueuedChangesMapLong2Int
/*     */ {
/*     */   protected final Long2IntOpenHashMap updatingMap;
/*     */   protected final Long2IntOpenHashMap visibleMap;
/*     */   protected final Long2IntOpenHashMap queuedPuts;
/*     */   protected final LongOpenHashSet queuedRemove;
/*     */   protected int queuedDefaultReturnValue;
/*  23 */   protected final WeakSeqLock updatingMapSeqLock = new WeakSeqLock();
/*     */   
/*     */   public QueuedChangesMapLong2Int() {
/*  26 */     this(16, 0.75F);
/*     */   }
/*     */   
/*     */   public QueuedChangesMapLong2Int(int capacity, float loadFactor) {
/*  30 */     this.updatingMap = new Long2IntOpenHashMap(capacity, loadFactor);
/*  31 */     this.visibleMap = new Long2IntOpenHashMap(capacity, loadFactor);
/*  32 */     this.queuedPuts = new Long2IntOpenHashMap();
/*  33 */     this.queuedRemove = new LongOpenHashSet();
/*     */   }
/*     */   
/*     */   public void queueDefaultReturnValue(int dfl) {
/*  37 */     this.queuedDefaultReturnValue = dfl;
/*  38 */     this.updatingMap.defaultReturnValue(dfl);
/*     */   }
/*     */   
/*     */   public int queueUpdate(long k, int v) {
/*  42 */     this.queuedRemove.remove(k);
/*  43 */     this.queuedPuts.put(k, v);
/*     */     
/*  45 */     return this.updatingMap.put(k, v);
/*     */   }
/*     */   
/*     */   public int queueRemove(long k) {
/*  49 */     this.queuedPuts.remove(k);
/*  50 */     this.queuedRemove.add(k);
/*     */     
/*  52 */     return this.updatingMap.remove(k);
/*     */   }
/*     */   
/*     */   public int getUpdating(long k) {
/*  56 */     return this.updatingMap.get(k);
/*     */   }
/*     */   
/*     */   public int getVisible(long k) {
/*  60 */     return this.visibleMap.get(k);
/*     */   }
/*     */   
/*     */   public int getVisibleAsync(long k) {
/*     */     long readlock;
/*  65 */     int ret = 0;
/*     */     
/*     */     do {
/*  68 */       readlock = this.updatingMapSeqLock.acquireRead();
/*     */       try {
/*  70 */         ret = this.visibleMap.get(k);
/*  71 */       } catch (Throwable thr) {
/*  72 */         if (thr instanceof ThreadDeath) {
/*  73 */           throw (ThreadDeath)thr;
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/*  79 */     while (!this.updatingMapSeqLock.tryReleaseRead(readlock));
/*     */     
/*  81 */     return ret;
/*     */   }
/*     */   
/*     */   public boolean performUpdates() {
/*  85 */     this.updatingMapSeqLock.acquireWrite();
/*  86 */     this.visibleMap.defaultReturnValue(this.queuedDefaultReturnValue);
/*  87 */     this.updatingMapSeqLock.releaseWrite();
/*     */     
/*  89 */     if (this.queuedPuts.isEmpty() && this.queuedRemove.isEmpty()) {
/*  90 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  94 */     ObjectIterator<Long2IntMap.Entry> iterator0 = this.queuedPuts.long2IntEntrySet().fastIterator();
/*  95 */     while (iterator0.hasNext()) {
/*  96 */       Long2IntMap.Entry entry = (Long2IntMap.Entry)iterator0.next();
/*  97 */       long key = entry.getLongKey();
/*  98 */       int val = entry.getIntValue();
/*     */       
/* 100 */       this.updatingMapSeqLock.acquireWrite();
/*     */       try {
/* 102 */         this.visibleMap.put(key, val);
/*     */       } finally {
/* 104 */         this.updatingMapSeqLock.releaseWrite();
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     this.queuedPuts.clear();
/*     */     
/* 110 */     LongIterator iterator1 = this.queuedRemove.iterator();
/* 111 */     while (iterator1.hasNext()) {
/* 112 */       long key = iterator1.nextLong();
/*     */       
/* 114 */       this.updatingMapSeqLock.acquireWrite();
/*     */       try {
/* 116 */         this.visibleMap.remove(key);
/*     */       } finally {
/* 118 */         this.updatingMapSeqLock.releaseWrite();
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     this.queuedRemove.clear();
/*     */     
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   public boolean performUpdatesLockMap() {
/* 128 */     this.updatingMapSeqLock.acquireWrite();
/*     */     try {
/* 130 */       this.visibleMap.defaultReturnValue(this.queuedDefaultReturnValue);
/*     */       
/* 132 */       if (this.queuedPuts.isEmpty() && this.queuedRemove.isEmpty()) {
/* 133 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 137 */       ObjectIterator<Long2IntMap.Entry> iterator0 = this.queuedPuts.long2IntEntrySet().fastIterator();
/* 138 */       while (iterator0.hasNext()) {
/* 139 */         Long2IntMap.Entry entry = (Long2IntMap.Entry)iterator0.next();
/* 140 */         long key = entry.getLongKey();
/* 141 */         int val = entry.getIntValue();
/*     */         
/* 143 */         this.visibleMap.put(key, val);
/*     */       } 
/*     */       
/* 146 */       this.queuedPuts.clear();
/*     */       
/* 148 */       LongIterator iterator1 = this.queuedRemove.iterator();
/* 149 */       while (iterator1.hasNext()) {
/* 150 */         long key = iterator1.nextLong();
/*     */         
/* 152 */         this.visibleMap.remove(key);
/*     */       } 
/*     */       
/* 155 */       this.queuedRemove.clear();
/*     */       
/* 157 */       return true;
/*     */     } finally {
/* 159 */       this.updatingMapSeqLock.releaseWrite();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\map\QueuedChangesMapLong2Int.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */