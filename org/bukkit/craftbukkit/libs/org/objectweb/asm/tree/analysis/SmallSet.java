/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.AbstractSet;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
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
/*     */ final class SmallSet<T>
/*     */   extends AbstractSet<T>
/*     */ {
/*     */   private final T element1;
/*     */   private final T element2;
/*     */   
/*     */   SmallSet() {
/*  59 */     this.element1 = null;
/*  60 */     this.element2 = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SmallSet(T element) {
/*  69 */     this.element1 = element;
/*  70 */     this.element2 = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SmallSet(T element1, T element2) {
/*  80 */     this.element1 = element1;
/*  81 */     this.element2 = element2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<T> iterator() {
/*  90 */     return new IteratorImpl<T>(this.element1, this.element2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  95 */     if (this.element1 == null)
/*  96 */       return 0; 
/*  97 */     if (this.element2 == null) {
/*  98 */       return 1;
/*     */     }
/* 100 */     return 2;
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
/*     */   Set<T> union(SmallSet<T> otherSet) {
/* 116 */     if ((otherSet.element1 == this.element1 && otherSet.element2 == this.element2) || (otherSet.element1 == this.element2 && otherSet.element2 == this.element1))
/*     */     {
/* 118 */       return this;
/*     */     }
/*     */     
/* 121 */     if (otherSet.element1 == null) {
/* 122 */       return this;
/*     */     }
/* 124 */     if (this.element1 == null) {
/* 125 */       return otherSet;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (otherSet.element2 == null) {
/*     */       
/* 132 */       if (this.element2 == null) {
/* 133 */         return new SmallSet(this.element1, otherSet.element1);
/*     */       }
/*     */       
/* 136 */       if (otherSet.element1 == this.element1 || otherSet.element1 == this.element2) {
/* 137 */         return this;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 142 */     if (this.element2 == null && (this.element1 == otherSet.element1 || this.element1 == otherSet.element2)) {
/* 143 */       return otherSet;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 148 */     HashSet<T> result = new HashSet<T>(4);
/* 149 */     result.add(this.element1);
/* 150 */     if (this.element2 != null) {
/* 151 */       result.add(this.element2);
/*     */     }
/* 153 */     result.add(otherSet.element1);
/* 154 */     if (otherSet.element2 != null) {
/* 155 */       result.add(otherSet.element2);
/*     */     }
/* 157 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class IteratorImpl<T>
/*     */     implements Iterator<T>
/*     */   {
/*     */     private T firstElement;
/*     */ 
/*     */     
/*     */     private T secondElement;
/*     */ 
/*     */ 
/*     */     
/*     */     IteratorImpl(T firstElement, T secondElement) {
/* 173 */       this.firstElement = firstElement;
/* 174 */       this.secondElement = secondElement;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 179 */       return (this.firstElement != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public T next() {
/* 184 */       if (this.firstElement == null) {
/* 185 */         throw new NoSuchElementException();
/*     */       }
/* 187 */       T element = this.firstElement;
/* 188 */       this.firstElement = this.secondElement;
/* 189 */       this.secondElement = null;
/* 190 */       return element;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 195 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\SmallSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */