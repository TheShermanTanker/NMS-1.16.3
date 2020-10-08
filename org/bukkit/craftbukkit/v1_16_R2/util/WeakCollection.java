/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.lang.Validate;
/*     */ 
/*     */ public final class WeakCollection<T> implements Collection<T> {
/*  11 */   static final Object NO_VALUE = new Object();
/*     */ 
/*     */ 
/*     */   
/*  15 */   private final Collection<WeakReference<T>> collection = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(T value) {
/*  20 */     Validate.notNull(value, "Cannot add null value");
/*  21 */     return this.collection.add(new WeakReference<>(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends T> collection) {
/*  26 */     Collection<WeakReference<T>> values = this.collection;
/*  27 */     boolean ret = false;
/*  28 */     for (T value : collection) {
/*  29 */       Validate.notNull(value, "Cannot add null value");
/*  30 */       ret |= values.add(new WeakReference<>(value));
/*     */     } 
/*  32 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  37 */     this.collection.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object object) {
/*  42 */     if (object == null) {
/*  43 */       return false;
/*     */     }
/*  45 */     for (T compare : this) {
/*  46 */       if (object.equals(compare)) {
/*  47 */         return true;
/*     */       }
/*     */     } 
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> collection) {
/*  55 */     return toCollection().containsAll(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  60 */     return !iterator().hasNext();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<T> iterator() {
/*  65 */     return new Iterator<T>() {
/*  66 */         Iterator<WeakReference<T>> it = WeakCollection.this.collection.iterator();
/*  67 */         Object value = WeakCollection.NO_VALUE;
/*     */ 
/*     */         
/*     */         public boolean hasNext() {
/*  71 */           Object value = this.value;
/*  72 */           if (value != null && value != WeakCollection.NO_VALUE) {
/*  73 */             return true;
/*     */           }
/*     */           
/*  76 */           Iterator<WeakReference<T>> it = this.it;
/*  77 */           value = null;
/*     */           
/*  79 */           while (it.hasNext()) {
/*  80 */             WeakReference<T> ref = it.next();
/*  81 */             value = ref.get();
/*  82 */             if (value == null) {
/*  83 */               it.remove(); continue;
/*     */             } 
/*  85 */             this.value = value;
/*  86 */             return true;
/*     */           } 
/*     */           
/*  89 */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         public T next() throws NoSuchElementException {
/*  94 */           if (!hasNext()) {
/*  95 */             throw new NoSuchElementException("No more elements");
/*     */           }
/*     */ 
/*     */           
/*  99 */           T value = (T)this.value;
/* 100 */           this.value = WeakCollection.NO_VALUE;
/* 101 */           return value;
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() throws IllegalStateException {
/* 106 */           if (this.value != WeakCollection.NO_VALUE) {
/* 107 */             throw new IllegalStateException("No last element");
/*     */           }
/*     */           
/* 110 */           this.value = null;
/* 111 */           this.it.remove();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object object) {
/* 118 */     if (object == null) {
/* 119 */       return false;
/*     */     }
/*     */     
/* 122 */     Iterator<T> it = iterator();
/* 123 */     while (it.hasNext()) {
/* 124 */       if (object.equals(it.next())) {
/* 125 */         it.remove();
/* 126 */         return true;
/*     */       } 
/*     */     } 
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> collection) {
/* 134 */     Iterator<T> it = iterator();
/* 135 */     boolean ret = false;
/* 136 */     while (it.hasNext()) {
/* 137 */       if (collection.contains(it.next())) {
/* 138 */         ret = true;
/* 139 */         it.remove();
/*     */       } 
/*     */     } 
/* 142 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> collection) {
/* 147 */     Iterator<T> it = iterator();
/* 148 */     boolean ret = false;
/* 149 */     while (it.hasNext()) {
/* 150 */       if (!collection.contains(it.next())) {
/* 151 */         ret = true;
/* 152 */         it.remove();
/*     */       } 
/*     */     } 
/* 155 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 160 */     int s = 0;
/* 161 */     for (T value : this) {
/* 162 */       s++;
/*     */     }
/* 164 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 169 */     return toArray(new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(T[] array) {
/* 174 */     return toCollection().toArray(array);
/*     */   }
/*     */   
/*     */   private Collection<T> toCollection() {
/* 178 */     ArrayList<T> collection = new ArrayList<>();
/* 179 */     for (T value : this) {
/* 180 */       collection.add(value);
/*     */     }
/* 182 */     return collection;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\WeakCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */