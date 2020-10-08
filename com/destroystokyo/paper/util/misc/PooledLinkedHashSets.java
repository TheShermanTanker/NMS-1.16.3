/*     */ package com.destroystokyo.paper.util.misc;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.lang.ref.WeakReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PooledLinkedHashSets<E>
/*     */ {
/*  13 */   protected final Object2ObjectOpenHashMap<PooledObjectLinkedOpenHashSet<E>, PooledObjectLinkedOpenHashSet<E>> mapPool = new Object2ObjectOpenHashMap(128, 0.25F);
/*     */   
/*     */   protected void decrementReferenceCount(PooledObjectLinkedOpenHashSet<E> current) {
/*  16 */     if (current.referenceCount == 0) {
/*  17 */       throw new IllegalStateException("Cannot decrement reference count for " + current);
/*     */     }
/*  19 */     if (current.referenceCount == -1 || --current.referenceCount > 0) {
/*     */       return;
/*     */     }
/*     */     
/*  23 */     this.mapPool.remove(current);
/*     */   }
/*     */ 
/*     */   
/*     */   public PooledObjectLinkedOpenHashSet<E> findMapWith(PooledObjectLinkedOpenHashSet<E> current, E object) {
/*  28 */     PooledObjectLinkedOpenHashSet<E> cached = current.getAddCache(object);
/*     */     
/*  30 */     if (cached != null) {
/*  31 */       decrementReferenceCount(current);
/*     */       
/*  33 */       if (cached.referenceCount == 0) {
/*     */         
/*  35 */         PooledObjectLinkedOpenHashSet<E> contending = (PooledObjectLinkedOpenHashSet<E>)this.mapPool.putIfAbsent(cached, cached);
/*  36 */         if (contending != null) {
/*     */           
/*  38 */           if (contending.referenceCount != -1) {
/*  39 */             contending.referenceCount++;
/*     */           }
/*  41 */           current.updateAddCache(object, contending);
/*  42 */           return contending;
/*     */         } 
/*     */         
/*  45 */         cached.referenceCount = 1;
/*  46 */       } else if (cached.referenceCount != -1) {
/*  47 */         cached.referenceCount++;
/*     */       } 
/*     */       
/*  50 */       return cached;
/*     */     } 
/*     */     
/*  53 */     if (!current.add(object)) {
/*  54 */       return current;
/*     */     }
/*     */ 
/*     */     
/*  58 */     PooledObjectLinkedOpenHashSet<E> ret = (PooledObjectLinkedOpenHashSet<E>)this.mapPool.get(current);
/*     */     
/*  60 */     if (ret == null) {
/*  61 */       ret = new PooledObjectLinkedOpenHashSet<>(current);
/*  62 */       current.remove(object);
/*  63 */       this.mapPool.put(ret, ret);
/*  64 */       ret.referenceCount = 1;
/*     */     } else {
/*  66 */       if (ret.referenceCount != -1) {
/*  67 */         ret.referenceCount++;
/*     */       }
/*  69 */       current.remove(object);
/*     */     } 
/*     */     
/*  72 */     current.updateAddCache(object, ret);
/*     */     
/*  74 */     decrementReferenceCount(current);
/*  75 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public PooledObjectLinkedOpenHashSet<E> findMapWithout(PooledObjectLinkedOpenHashSet<E> current, E object) {
/*  80 */     if (current.set.size() == 1) {
/*  81 */       decrementReferenceCount(current);
/*  82 */       return null;
/*     */     } 
/*     */     
/*  85 */     PooledObjectLinkedOpenHashSet<E> cached = current.getRemoveCache(object);
/*     */     
/*  87 */     if (cached != null) {
/*  88 */       decrementReferenceCount(current);
/*     */       
/*  90 */       if (cached.referenceCount == 0) {
/*     */         
/*  92 */         PooledObjectLinkedOpenHashSet<E> contending = (PooledObjectLinkedOpenHashSet<E>)this.mapPool.putIfAbsent(cached, cached);
/*  93 */         if (contending != null) {
/*     */           
/*  95 */           if (contending.referenceCount != -1) {
/*  96 */             contending.referenceCount++;
/*     */           }
/*  98 */           current.updateRemoveCache(object, contending);
/*  99 */           return contending;
/*     */         } 
/*     */         
/* 102 */         cached.referenceCount = 1;
/* 103 */       } else if (cached.referenceCount != -1) {
/* 104 */         cached.referenceCount++;
/*     */       } 
/*     */       
/* 107 */       return cached;
/*     */     } 
/*     */     
/* 110 */     if (!current.remove(object)) {
/* 111 */       return current;
/*     */     }
/*     */ 
/*     */     
/* 115 */     PooledObjectLinkedOpenHashSet<E> ret = (PooledObjectLinkedOpenHashSet<E>)this.mapPool.get(current);
/*     */     
/* 117 */     if (ret == null) {
/* 118 */       ret = new PooledObjectLinkedOpenHashSet<>(current);
/* 119 */       current.add(object);
/* 120 */       this.mapPool.put(ret, ret);
/* 121 */       ret.referenceCount = 1;
/*     */     } else {
/* 123 */       if (ret.referenceCount != -1) {
/* 124 */         ret.referenceCount++;
/*     */       }
/* 126 */       current.add(object);
/*     */     } 
/*     */     
/* 129 */     current.updateRemoveCache(object, ret);
/*     */     
/* 131 */     decrementReferenceCount(current);
/* 132 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   static final class RawSetObjectLinkedOpenHashSet<E>
/*     */     extends ObjectOpenHashSet<E>
/*     */   {
/*     */     public RawSetObjectLinkedOpenHashSet() {}
/*     */     
/*     */     public RawSetObjectLinkedOpenHashSet(int capacity) {
/* 142 */       super(capacity);
/*     */     }
/*     */     
/*     */     public RawSetObjectLinkedOpenHashSet(int capacity, float loadFactor) {
/* 146 */       super(capacity, loadFactor);
/*     */     }
/*     */ 
/*     */     
/*     */     public RawSetObjectLinkedOpenHashSet<E> clone() {
/* 151 */       return (RawSetObjectLinkedOpenHashSet<E>)super.clone();
/*     */     }
/*     */     
/*     */     public E[] getRawSet() {
/* 155 */       return (E[])this.key;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class PooledObjectLinkedOpenHashSet<E>
/*     */   {
/* 161 */     private static final WeakReference NULL_REFERENCE = new WeakReference(null);
/*     */     
/*     */     final PooledLinkedHashSets.RawSetObjectLinkedOpenHashSet<E> set;
/*     */     
/*     */     int referenceCount;
/*     */     
/*     */     int hash;
/* 168 */     WeakReference<E> lastAddObject = NULL_REFERENCE;
/* 169 */     WeakReference<PooledObjectLinkedOpenHashSet<E>> lastAddMap = NULL_REFERENCE;
/*     */ 
/*     */     
/* 172 */     WeakReference<E> lastRemoveObject = NULL_REFERENCE;
/* 173 */     WeakReference<PooledObjectLinkedOpenHashSet<E>> lastRemoveMap = NULL_REFERENCE;
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet(PooledLinkedHashSets<E> pooledSets) {
/* 176 */       this.set = new PooledLinkedHashSets.RawSetObjectLinkedOpenHashSet<>(2, 0.8F);
/*     */     }
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet(E single) {
/* 180 */       this((PooledLinkedHashSets<E>)null);
/* 181 */       this.referenceCount = -1;
/* 182 */       add(single);
/*     */     }
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet(PooledObjectLinkedOpenHashSet<E> other) {
/* 186 */       this.set = other.set.clone();
/* 187 */       this.hash = other.hash;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private static int hash0(int x) {
/* 193 */       x *= 915625301;
/* 194 */       x ^= x >>> 16;
/* 195 */       return x;
/*     */     }
/*     */     
/*     */     PooledObjectLinkedOpenHashSet<E> getAddCache(E element) {
/* 199 */       E currentAdd = this.lastAddObject.get();
/*     */       
/* 201 */       if (currentAdd == null || (currentAdd != element && !currentAdd.equals(element))) {
/* 202 */         return null;
/*     */       }
/*     */       
/* 205 */       return this.lastAddMap.get();
/*     */     }
/*     */     
/*     */     PooledObjectLinkedOpenHashSet<E> getRemoveCache(E element) {
/* 209 */       E currentRemove = this.lastRemoveObject.get();
/*     */       
/* 211 */       if (currentRemove == null || (currentRemove != element && !currentRemove.equals(element))) {
/* 212 */         return null;
/*     */       }
/*     */       
/* 215 */       return this.lastRemoveMap.get();
/*     */     }
/*     */     
/*     */     void updateAddCache(E element, PooledObjectLinkedOpenHashSet<E> map) {
/* 219 */       this.lastAddObject = new WeakReference<>(element);
/* 220 */       this.lastAddMap = new WeakReference<>(map);
/*     */     }
/*     */     
/*     */     void updateRemoveCache(E element, PooledObjectLinkedOpenHashSet<E> map) {
/* 224 */       this.lastRemoveObject = new WeakReference<>(element);
/* 225 */       this.lastRemoveMap = new WeakReference<>(map);
/*     */     }
/*     */     
/*     */     boolean add(E element) {
/* 229 */       boolean added = this.set.add(element);
/*     */       
/* 231 */       if (added) {
/* 232 */         this.hash += hash0(element.hashCode());
/*     */       }
/*     */       
/* 235 */       return added;
/*     */     }
/*     */     
/*     */     boolean remove(Object element) {
/* 239 */       boolean removed = this.set.remove(element);
/*     */       
/* 241 */       if (removed) {
/* 242 */         this.hash -= hash0(element.hashCode());
/*     */       }
/*     */       
/* 245 */       return removed;
/*     */     }
/*     */     
/*     */     public boolean contains(Object element) {
/* 249 */       return this.set.contains(element);
/*     */     }
/*     */     
/*     */     public E[] getBackingSet() {
/* 253 */       return this.set.getRawSet();
/*     */     }
/*     */     
/*     */     public int size() {
/* 257 */       return this.set.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 262 */       return this.hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 267 */       if (!(other instanceof PooledObjectLinkedOpenHashSet)) {
/* 268 */         return false;
/*     */       }
/* 270 */       if (this.referenceCount == 0) {
/* 271 */         return (other == this);
/*     */       }
/* 273 */       if (other == this)
/*     */       {
/* 275 */         return false;
/*     */       }
/* 277 */       return (this.hash == ((PooledObjectLinkedOpenHashSet)other).hash && this.set.equals(((PooledObjectLinkedOpenHashSet)other).set));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 283 */       return "PooledHashSet: size: " + this.set.size() + ", reference count: " + this.referenceCount + ", hash: " + 
/* 284 */         hashCode() + ", identity: " + System.identityHashCode(this) + " map: " + this.set.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\misc\PooledLinkedHashSets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */