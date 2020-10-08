/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class LazyHashSet<E> implements Set<E> {
/*   8 */   Set<E> reference = null;
/*     */ 
/*     */   
/*     */   public int size() {
/*  12 */     return getReference().size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  17 */     return getReference().isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/*  22 */     return getReference().contains(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/*  27 */     return getReference().iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  32 */     return getReference().toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/*  37 */     return getReference().toArray(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(E o) {
/*  42 */     return getReference().add(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*  47 */     return getReference().remove(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/*  52 */     return getReference().containsAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/*  57 */     return getReference().addAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/*  62 */     return getReference().retainAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/*  67 */     return getReference().removeAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  72 */     getReference().clear();
/*     */   }
/*     */   
/*     */   public Set<E> getReference() {
/*  76 */     Set<E> reference = this.reference;
/*  77 */     if (reference != null) {
/*  78 */       return reference;
/*     */     }
/*  80 */     return this.reference = makeReference();
/*     */   }
/*     */   
/*     */   abstract Set<E> makeReference();
/*     */   
/*     */   public boolean isLazy() {
/*  86 */     return (this.reference == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  91 */     return 157 * getReference().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  96 */     if (obj == this) {
/*  97 */       return true;
/*     */     }
/*  99 */     if (obj == null || getClass() != obj.getClass()) {
/* 100 */       return false;
/*     */     }
/* 102 */     LazyHashSet<?> that = (LazyHashSet)obj;
/* 103 */     return ((isLazy() && that.isLazy()) || getReference().equals(that.getReference()));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     return getReference().toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\LazyHashSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */