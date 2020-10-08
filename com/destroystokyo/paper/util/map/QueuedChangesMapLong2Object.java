/*     */ package com.destroystokyo.paper.util.map;
/*     */ 
/*     */ import com.destroystokyo.paper.util.concurrent.WeakSeqLock;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueuedChangesMapLong2Object<V>
/*     */ {
/*  16 */   protected static final Object REMOVED = new Object();
/*     */   
/*     */   protected final Long2ObjectLinkedOpenHashMap<V> updatingMap;
/*     */   
/*     */   protected final Long2ObjectLinkedOpenHashMap<V> visibleMap;
/*     */   
/*     */   protected final Long2ObjectLinkedOpenHashMap<Object> queuedChanges;
/*  23 */   protected final WeakSeqLock updatingMapSeqLock = new WeakSeqLock();
/*     */   
/*     */   public QueuedChangesMapLong2Object() {
/*  26 */     this(16, 0.75F);
/*     */   }
/*     */   
/*     */   public QueuedChangesMapLong2Object(int capacity, float loadFactor) {
/*  30 */     this.updatingMap = new Long2ObjectLinkedOpenHashMap(capacity, loadFactor);
/*  31 */     this.visibleMap = new Long2ObjectLinkedOpenHashMap(capacity, loadFactor);
/*  32 */     this.queuedChanges = new Long2ObjectLinkedOpenHashMap();
/*     */   }
/*     */   
/*     */   public V queueUpdate(long k, V value) {
/*  36 */     this.queuedChanges.put(k, value);
/*  37 */     return (V)this.updatingMap.put(k, value);
/*     */   }
/*     */   
/*     */   public V queueRemove(long k) {
/*  41 */     this.queuedChanges.put(k, REMOVED);
/*  42 */     return (V)this.updatingMap.remove(k);
/*     */   }
/*     */   
/*     */   public V getUpdating(long k) {
/*  46 */     return (V)this.updatingMap.get(k);
/*     */   }
/*     */   
/*     */   public boolean updatingContainsKey(long k) {
/*  50 */     return this.updatingMap.containsKey(k);
/*     */   }
/*     */   
/*     */   public V getVisible(long k) {
/*  54 */     return (V)this.visibleMap.get(k);
/*     */   }
/*     */   
/*     */   public boolean visibleContainsKey(long k) {
/*  58 */     return this.visibleMap.containsKey(k);
/*     */   }
/*     */   
/*     */   public V getVisibleAsync(long k) {
/*     */     long readlock;
/*  63 */     V ret = null;
/*     */     
/*     */     do {
/*  66 */       readlock = this.updatingMapSeqLock.acquireRead();
/*     */       
/*     */       try {
/*  69 */         ret = (V)this.visibleMap.get(k);
/*  70 */       } catch (Throwable thr) {
/*  71 */         if (thr instanceof ThreadDeath) {
/*  72 */           throw (ThreadDeath)thr;
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/*  78 */     while (!this.updatingMapSeqLock.tryReleaseRead(readlock));
/*     */     
/*  80 */     return ret;
/*     */   }
/*     */   
/*     */   public boolean visibleContainsKeyAsync(long k) {
/*     */     long readlock;
/*  85 */     boolean ret = false;
/*     */     
/*     */     do {
/*  88 */       readlock = this.updatingMapSeqLock.acquireRead();
/*     */       
/*     */       try {
/*  91 */         ret = this.visibleMap.containsKey(k);
/*  92 */       } catch (Throwable thr) {
/*  93 */         if (thr instanceof ThreadDeath) {
/*  94 */           throw (ThreadDeath)thr;
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 100 */     while (!this.updatingMapSeqLock.tryReleaseRead(readlock));
/*     */     
/* 102 */     return ret;
/*     */   }
/*     */   
/*     */   public Long2ObjectLinkedOpenHashMap<V> getVisibleMap() {
/* 106 */     return this.visibleMap;
/*     */   }
/*     */   
/*     */   public Long2ObjectLinkedOpenHashMap<V> getUpdatingMap() {
/* 110 */     return this.updatingMap;
/*     */   }
/*     */   
/*     */   public int getVisibleSize() {
/* 114 */     return this.visibleMap.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVisibleSizeAsync() {
/*     */     long readlock;
/*     */     int ret;
/*     */     do {
/* 122 */       readlock = this.updatingMapSeqLock.acquireRead();
/* 123 */       ret = this.visibleMap.size();
/* 124 */     } while (!this.updatingMapSeqLock.tryReleaseRead(readlock));
/*     */     
/* 126 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<V> getUpdatingValues() {
/* 131 */     return (Collection<V>)this.updatingMap.values();
/*     */   }
/*     */   
/*     */   public List<V> getUpdatingValuesCopy() {
/* 135 */     return new ArrayList<>((Collection<? extends V>)this.updatingMap.values());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<V> getVisibleValues() {
/* 140 */     return (Collection<V>)this.visibleMap.values();
/*     */   }
/*     */   
/*     */   public List<V> getVisibleValuesCopy() {
/* 144 */     return new ArrayList<>((Collection<? extends V>)this.visibleMap.values());
/*     */   }
/*     */   
/*     */   public boolean performUpdates() {
/* 148 */     if (this.queuedChanges.isEmpty()) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     ObjectBidirectionalIterator<Long2ObjectMap.Entry<Object>> iterator = this.queuedChanges.long2ObjectEntrySet().fastIterator();
/* 153 */     while (iterator.hasNext()) {
/* 154 */       Long2ObjectMap.Entry<Object> entry = (Long2ObjectMap.Entry<Object>)iterator.next();
/* 155 */       long key = entry.getLongKey();
/* 156 */       Object val = entry.getValue();
/*     */       
/* 158 */       this.updatingMapSeqLock.acquireWrite();
/*     */       try {
/* 160 */         if (val == REMOVED) {
/* 161 */           this.visibleMap.remove(key);
/*     */         } else {
/* 163 */           this.visibleMap.put(key, val);
/*     */         } 
/*     */       } finally {
/* 166 */         this.updatingMapSeqLock.releaseWrite();
/*     */       } 
/*     */     } 
/*     */     
/* 170 */     this.queuedChanges.clear();
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public boolean performUpdatesLockMap() {
/* 175 */     if (this.queuedChanges.isEmpty()) {
/* 176 */       return false;
/*     */     }
/*     */     
/* 179 */     ObjectBidirectionalIterator<Long2ObjectMap.Entry<Object>> iterator = this.queuedChanges.long2ObjectEntrySet().fastIterator();
/*     */     
/*     */     try {
/* 182 */       this.updatingMapSeqLock.acquireWrite();
/*     */       
/* 184 */       while (iterator.hasNext()) {
/* 185 */         Long2ObjectMap.Entry<Object> entry = (Long2ObjectMap.Entry<Object>)iterator.next();
/* 186 */         long key = entry.getLongKey();
/* 187 */         Object val = entry.getValue();
/*     */         
/* 189 */         if (val == REMOVED) {
/* 190 */           this.visibleMap.remove(key); continue;
/*     */         } 
/* 192 */         this.visibleMap.put(key, val);
/*     */       } 
/*     */     } finally {
/*     */       
/* 196 */       this.updatingMapSeqLock.releaseWrite();
/*     */     } 
/*     */     
/* 199 */     this.queuedChanges.clear();
/* 200 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\map\QueuedChangesMapLong2Object.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */