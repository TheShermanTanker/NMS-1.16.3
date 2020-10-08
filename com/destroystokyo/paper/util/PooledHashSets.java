/*     */ package com.destroystokyo.paper.util;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PooledHashSets<E>
/*     */ {
/*  12 */   protected final Object2ObjectOpenHashMap<PooledObjectLinkedOpenHashSet<E>, PooledObjectLinkedOpenHashSet<E>> mapPool = new Object2ObjectOpenHashMap(64, 0.25F);
/*     */   
/*     */   protected void decrementReferenceCount(PooledObjectLinkedOpenHashSet<E> current) {
/*  15 */     if (current.referenceCount == 0) {
/*  16 */       throw new IllegalStateException("Cannot decrement reference count for " + current);
/*     */     }
/*  18 */     if (current.referenceCount == -1 || --current.referenceCount > 0) {
/*     */       return;
/*     */     }
/*     */     
/*  22 */     this.mapPool.remove(current);
/*     */   }
/*     */ 
/*     */   
/*     */   public PooledObjectLinkedOpenHashSet<E> findMapWith(PooledObjectLinkedOpenHashSet<E> current, E object) {
/*  27 */     PooledObjectLinkedOpenHashSet<E> cached = current.getAddCache(object);
/*     */     
/*  29 */     if (cached != null) {
/*  30 */       if (cached.referenceCount != -1) {
/*  31 */         cached.referenceCount++;
/*     */       }
/*     */       
/*  34 */       decrementReferenceCount(current);
/*     */       
/*  36 */       return cached;
/*     */     } 
/*     */     
/*  39 */     if (!current.add(object)) {
/*  40 */       return current;
/*     */     }
/*     */ 
/*     */     
/*  44 */     PooledObjectLinkedOpenHashSet<E> ret = (PooledObjectLinkedOpenHashSet<E>)this.mapPool.get(current);
/*     */     
/*  46 */     if (ret == null) {
/*  47 */       ret = new PooledObjectLinkedOpenHashSet<>(current);
/*  48 */       current.remove(object);
/*  49 */       this.mapPool.put(ret, ret);
/*  50 */       ret.referenceCount = 1;
/*     */     } else {
/*  52 */       if (ret.referenceCount != -1) {
/*  53 */         ret.referenceCount++;
/*     */       }
/*  55 */       current.remove(object);
/*     */     } 
/*     */     
/*  58 */     current.updateAddCache(object, ret);
/*     */     
/*  60 */     decrementReferenceCount(current);
/*  61 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public PooledObjectLinkedOpenHashSet<E> findMapWithout(PooledObjectLinkedOpenHashSet<E> current, E object) {
/*  66 */     if (current.set.size() == 1) {
/*  67 */       decrementReferenceCount(current);
/*  68 */       return null;
/*     */     } 
/*     */     
/*  71 */     PooledObjectLinkedOpenHashSet<E> cached = current.getRemoveCache(object);
/*     */     
/*  73 */     if (cached != null) {
/*  74 */       if (cached.referenceCount != -1) {
/*  75 */         cached.referenceCount++;
/*     */       }
/*     */       
/*  78 */       decrementReferenceCount(current);
/*     */       
/*  80 */       return cached;
/*     */     } 
/*     */     
/*  83 */     if (!current.remove(object)) {
/*  84 */       return current;
/*     */     }
/*     */ 
/*     */     
/*  88 */     PooledObjectLinkedOpenHashSet<E> ret = (PooledObjectLinkedOpenHashSet<E>)this.mapPool.get(current);
/*     */     
/*  90 */     if (ret == null) {
/*  91 */       ret = new PooledObjectLinkedOpenHashSet<>(current);
/*  92 */       current.add(object);
/*  93 */       this.mapPool.put(ret, ret);
/*  94 */       ret.referenceCount = 1;
/*     */     } else {
/*  96 */       if (ret.referenceCount != -1) {
/*  97 */         ret.referenceCount++;
/*     */       }
/*  99 */       current.add(object);
/*     */     } 
/*     */     
/* 102 */     current.updateRemoveCache(object, ret);
/*     */     
/* 104 */     decrementReferenceCount(current);
/* 105 */     return ret;
/*     */   }
/*     */   
/*     */   public static final class PooledObjectLinkedOpenHashSet<E>
/*     */     implements Iterable<E> {
/* 110 */     private static final WeakReference NULL_REFERENCE = new WeakReference(null);
/*     */     
/*     */     final ObjectLinkedOpenHashSet<E> set;
/*     */     
/*     */     int referenceCount;
/*     */     
/*     */     int hash;
/* 117 */     WeakReference<E> lastAddObject = NULL_REFERENCE;
/* 118 */     WeakReference<PooledObjectLinkedOpenHashSet<E>> lastAddMap = NULL_REFERENCE;
/*     */ 
/*     */     
/* 121 */     WeakReference<E> lastRemoveObject = NULL_REFERENCE;
/* 122 */     WeakReference<PooledObjectLinkedOpenHashSet<E>> lastRemoveMap = NULL_REFERENCE;
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet() {
/* 125 */       this.set = new ObjectLinkedOpenHashSet(2, 0.6F);
/*     */     }
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet(E single) {
/* 129 */       this();
/* 130 */       this.referenceCount = -1;
/* 131 */       add(single);
/*     */     }
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet(PooledObjectLinkedOpenHashSet<E> other) {
/* 135 */       this.set = other.set.clone();
/* 136 */       this.hash = other.hash;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     static int hash0(int x) {
/* 142 */       x *= 915625301;
/* 143 */       x ^= x >>> 16;
/* 144 */       return x;
/*     */     }
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet<E> getAddCache(E element) {
/* 148 */       E currentAdd = this.lastAddObject.get();
/*     */       
/* 150 */       if (currentAdd == null || (currentAdd != element && !currentAdd.equals(element))) {
/* 151 */         return null;
/*     */       }
/*     */       
/* 154 */       PooledObjectLinkedOpenHashSet<E> map = this.lastAddMap.get();
/* 155 */       if (map == null || map.referenceCount == 0)
/*     */       {
/* 157 */         return null;
/*     */       }
/*     */       
/* 160 */       return map;
/*     */     }
/*     */     
/*     */     public PooledObjectLinkedOpenHashSet<E> getRemoveCache(E element) {
/* 164 */       E currentRemove = this.lastRemoveObject.get();
/*     */       
/* 166 */       if (currentRemove == null || (currentRemove != element && !currentRemove.equals(element))) {
/* 167 */         return null;
/*     */       }
/*     */       
/* 170 */       PooledObjectLinkedOpenHashSet<E> map = this.lastRemoveMap.get();
/* 171 */       if (map == null || map.referenceCount == 0)
/*     */       {
/* 173 */         return null;
/*     */       }
/*     */       
/* 176 */       return map;
/*     */     }
/*     */     
/*     */     public void updateAddCache(E element, PooledObjectLinkedOpenHashSet<E> map) {
/* 180 */       this.lastAddObject = new WeakReference<>(element);
/* 181 */       this.lastAddMap = new WeakReference<>(map);
/*     */     }
/*     */     
/*     */     public void updateRemoveCache(E element, PooledObjectLinkedOpenHashSet<E> map) {
/* 185 */       this.lastRemoveObject = new WeakReference<>(element);
/* 186 */       this.lastRemoveMap = new WeakReference<>(map);
/*     */     }
/*     */     
/*     */     boolean add(E element) {
/* 190 */       boolean added = this.set.add(element);
/*     */       
/* 192 */       if (added) {
/* 193 */         this.hash += hash0(element.hashCode());
/*     */       }
/*     */       
/* 196 */       return added;
/*     */     }
/*     */     
/*     */     boolean remove(Object element) {
/* 200 */       boolean removed = this.set.remove(element);
/*     */       
/* 202 */       if (removed) {
/* 203 */         this.hash -= hash0(element.hashCode());
/*     */       }
/*     */       
/* 206 */       return removed;
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator<E> iterator() {
/* 211 */       return (Iterator<E>)this.set.iterator();
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 216 */       return this.hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 221 */       if (!(other instanceof PooledObjectLinkedOpenHashSet)) {
/* 222 */         return false;
/*     */       }
/* 224 */       if (this.referenceCount == 0) {
/* 225 */         return (other == this);
/*     */       }
/* 227 */       if (other == this)
/*     */       {
/* 229 */         return false;
/*     */       }
/* 231 */       return (this.hash == ((PooledObjectLinkedOpenHashSet)other).hash && this.set.equals(((PooledObjectLinkedOpenHashSet)other).set));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 237 */       return "PooledHashSet: size: " + this.set.size() + ", reference count: " + this.referenceCount + ", hash: " + 
/* 238 */         hashCode() + ", identity: " + System.identityHashCode(this) + " map: " + this.set.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\PooledHashSets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */