/*     */ package com.tuinity.tuinity.util.maplist;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.Reference2IntLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Reference2IntMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.bukkit.Bukkit;
/*     */ 
/*     */ public final class IteratorSafeOrderedReferenceSet<E>
/*     */ {
/*     */   public static final int ITERATOR_FLAG_SEE_ADDITIONS = 1;
/*     */   protected final Reference2IntLinkedOpenHashMap<E> indexMap;
/*  14 */   protected int firstInvalidIndex = -1;
/*     */   
/*     */   protected E[] listElements;
/*     */   
/*     */   protected int listSize;
/*     */   
/*     */   protected final double maxFragFactor;
/*     */   
/*     */   protected int iteratorCount;
/*     */   
/*     */   private final boolean threadRestricted;
/*     */   
/*     */   public IteratorSafeOrderedReferenceSet() {
/*  27 */     this(16, 0.75F, 16, 0.2D);
/*     */   }
/*     */   
/*     */   public IteratorSafeOrderedReferenceSet(boolean threadRestricted) {
/*  31 */     this(16, 0.75F, 16, 0.2D, threadRestricted);
/*     */   }
/*     */ 
/*     */   
/*     */   public IteratorSafeOrderedReferenceSet(int setCapacity, float setLoadFactor, int arrayCapacity, double maxFragFactor) {
/*  36 */     this(setCapacity, setLoadFactor, arrayCapacity, maxFragFactor, false);
/*     */   }
/*     */   
/*     */   public IteratorSafeOrderedReferenceSet(int setCapacity, float setLoadFactor, int arrayCapacity, double maxFragFactor, boolean threadRestricted) {
/*  40 */     this.indexMap = new Reference2IntLinkedOpenHashMap(setCapacity, setLoadFactor);
/*  41 */     this.indexMap.defaultReturnValue(-1);
/*  42 */     this.maxFragFactor = maxFragFactor;
/*  43 */     this.listElements = (E[])new Object[arrayCapacity];
/*  44 */     this.threadRestricted = threadRestricted;
/*     */   }
/*     */   
/*     */   protected final boolean allowSafeIteration() {
/*  48 */     return (!this.threadRestricted || Bukkit.isPrimaryThread());
/*     */   }
/*     */   
/*     */   protected final double getFragFactor() {
/*  52 */     return 1.0D - this.indexMap.size() / this.listSize;
/*     */   }
/*     */   
/*     */   public int createRawIterator() {
/*  56 */     if (allowSafeIteration()) {
/*  57 */       this.iteratorCount++;
/*     */     }
/*  59 */     if (this.indexMap.isEmpty()) {
/*  60 */       return -1;
/*     */     }
/*  62 */     return (this.firstInvalidIndex == 0) ? this.indexMap.getInt(this.indexMap.firstKey()) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int advanceRawIterator(int index) {
/*  67 */     E[] elements = this.listElements;
/*  68 */     int ret = index + 1;
/*  69 */     for (int len = this.listSize; ret < len; ret++) {
/*  70 */       if (elements[ret] != null) {
/*  71 */         return ret;
/*     */       }
/*     */     } 
/*     */     
/*  75 */     return -1;
/*     */   }
/*     */   
/*     */   public void finishRawIterator() {
/*  79 */     if (allowSafeIteration() && --this.iteratorCount == 0 && 
/*  80 */       getFragFactor() >= this.maxFragFactor) {
/*  81 */       defrag();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(E element) {
/*  87 */     int index = this.indexMap.removeInt(element);
/*  88 */     if (index >= 0) {
/*  89 */       if (this.firstInvalidIndex < 0 || index < this.firstInvalidIndex) {
/*  90 */         this.firstInvalidIndex = index;
/*     */       }
/*  92 */       if (this.listElements[index] != element) {
/*  93 */         throw new IllegalStateException();
/*     */       }
/*  95 */       this.listElements[index] = null;
/*  96 */       if (allowSafeIteration() && this.iteratorCount == 0 && getFragFactor() >= this.maxFragFactor) {
/*  97 */         defrag();
/*     */       }
/*  99 */       return true;
/*     */     } 
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(E element) {
/* 105 */     return this.indexMap.containsKey(element);
/*     */   }
/*     */   
/*     */   public boolean add(E element) {
/* 109 */     int listSize = this.listSize;
/*     */     
/* 111 */     int previous = this.indexMap.putIfAbsent(element, listSize);
/* 112 */     if (previous != -1) {
/* 113 */       return false;
/*     */     }
/*     */     
/* 116 */     if (listSize >= this.listElements.length) {
/* 117 */       this.listElements = Arrays.copyOf(this.listElements, listSize * 2);
/*     */     }
/* 119 */     this.listElements[listSize] = element;
/* 120 */     this.listSize = listSize + 1;
/*     */     
/* 122 */     return true;
/*     */   } protected void defrag() {
/*     */     int lastValidIndex;
/*     */     ObjectBidirectionalIterator<Reference2IntMap.Entry<E>> objectBidirectionalIterator;
/* 126 */     if (this.firstInvalidIndex < 0) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     if (this.indexMap.isEmpty()) {
/* 131 */       Arrays.fill((Object[])this.listElements, 0, this.listSize, (Object)null);
/* 132 */       this.listSize = 0;
/* 133 */       this.firstInvalidIndex = -1;
/*     */       
/*     */       return;
/*     */     } 
/* 137 */     E[] backingArray = this.listElements;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (this.firstInvalidIndex == 0) {
/* 143 */       objectBidirectionalIterator = this.indexMap.reference2IntEntrySet().fastIterator();
/* 144 */       lastValidIndex = 0;
/*     */     } else {
/* 146 */       lastValidIndex = this.firstInvalidIndex;
/* 147 */       final E key = backingArray[lastValidIndex - 1];
/* 148 */       objectBidirectionalIterator = this.indexMap.reference2IntEntrySet().fastIterator(new Reference2IntMap.Entry<E>()
/*     */           {
/*     */             public int getIntValue() {
/* 151 */               throw new UnsupportedOperationException();
/*     */             }
/*     */ 
/*     */             
/*     */             public int setValue(int i) {
/* 156 */               throw new UnsupportedOperationException();
/*     */             }
/*     */ 
/*     */             
/*     */             public E getKey() {
/* 161 */               return (E)key;
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 166 */     while (objectBidirectionalIterator.hasNext()) {
/* 167 */       Reference2IntMap.Entry<E> entry = objectBidirectionalIterator.next();
/*     */       
/* 169 */       int newIndex = lastValidIndex++;
/* 170 */       backingArray[newIndex] = (E)entry.getKey();
/* 171 */       entry.setValue(newIndex);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     Arrays.fill((Object[])backingArray, lastValidIndex, this.listSize, (Object)null);
/* 176 */     this.listSize = lastValidIndex;
/* 177 */     this.firstInvalidIndex = -1;
/*     */   }
/*     */   
/*     */   public E rawGet(int index) {
/* 181 */     return this.listElements[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 186 */     return this.indexMap.size();
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 190 */     return iterator(0);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator(int flags) {
/* 194 */     if (allowSafeIteration()) {
/* 195 */       this.iteratorCount++;
/*     */     }
/* 197 */     return new BaseIterator<>(this, true, ((flags & 0x1) != 0) ? Integer.MAX_VALUE : this.listSize);
/*     */   }
/*     */   
/*     */   public java.util.Iterator<E> unsafeIterator() {
/* 201 */     return unsafeIterator(0);
/*     */   }
/*     */   public java.util.Iterator<E> unsafeIterator(int flags) {
/* 204 */     return new BaseIterator<>(this, false, ((flags & 0x1) != 0) ? Integer.MAX_VALUE : this.listSize);
/*     */   }
/*     */   
/*     */   public static interface Iterator<E>
/*     */     extends java.util.Iterator<E>
/*     */   {
/*     */     void finishedIterating();
/*     */   }
/*     */   
/*     */   protected static final class BaseIterator<E>
/*     */     implements Iterator<E> {
/*     */     protected final IteratorSafeOrderedReferenceSet<E> set;
/*     */     protected final boolean canFinish;
/*     */     protected final int maxIndex;
/*     */     protected int nextIndex;
/*     */     protected E pendingValue;
/*     */     protected boolean finished;
/*     */     protected E lastReturned;
/*     */     
/*     */     protected BaseIterator(IteratorSafeOrderedReferenceSet<E> set, boolean canFinish, int maxIndex) {
/* 224 */       this.set = set;
/* 225 */       this.canFinish = canFinish;
/* 226 */       this.maxIndex = maxIndex;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 231 */       if (this.finished) {
/* 232 */         return false;
/*     */       }
/* 234 */       if (this.pendingValue != null) {
/* 235 */         return true;
/*     */       }
/*     */       
/* 238 */       E[] elements = this.set.listElements;
/*     */       int index, len;
/* 240 */       for (index = this.nextIndex, len = Math.min(this.maxIndex, this.set.listSize); index < len; index++) {
/* 241 */         E element = elements[index];
/* 242 */         if (element != null) {
/* 243 */           this.pendingValue = element;
/* 244 */           this.nextIndex = index + 1;
/* 245 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 249 */       this.nextIndex = index;
/* 250 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public E next() {
/* 255 */       if (!hasNext()) {
/* 256 */         throw new NoSuchElementException();
/*     */       }
/* 258 */       E ret = this.pendingValue;
/*     */       
/* 260 */       this.pendingValue = null;
/* 261 */       this.lastReturned = ret;
/*     */       
/* 263 */       return ret;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 268 */       E lastReturned = this.lastReturned;
/* 269 */       if (lastReturned == null) {
/* 270 */         throw new IllegalStateException();
/*     */       }
/* 272 */       this.lastReturned = null;
/* 273 */       this.set.remove(lastReturned);
/*     */     }
/*     */ 
/*     */     
/*     */     public void finishedIterating() {
/* 278 */       if (this.finished || !this.canFinish) {
/* 279 */         throw new IllegalStateException();
/*     */       }
/* 281 */       this.lastReturned = null;
/* 282 */       this.finished = true;
/* 283 */       if (this.set.allowSafeIteration())
/* 284 */         this.set.finishRawIterator(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\tuinity\tuinit\\util\maplist\IteratorSafeOrderedReferenceSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */