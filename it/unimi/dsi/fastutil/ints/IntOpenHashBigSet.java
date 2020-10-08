/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.Size64;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntOpenHashBigSet
/*     */   extends AbstractIntSet
/*     */   implements Serializable, Cloneable, Hash, Size64
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient int[][] key;
/*     */   protected transient long mask;
/*     */   protected transient int segmentMask;
/*     */   protected transient int baseMask;
/*     */   protected transient boolean containsNull;
/*     */   protected transient long n;
/*     */   protected transient long maxFill;
/*     */   protected final transient long minN;
/*     */   protected final float f;
/*     */   protected long size;
/*     */   
/*     */   private void initMasks() {
/*  83 */     this.mask = this.n - 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.segmentMask = (this.key[0]).length - 1;
/*  90 */     this.baseMask = this.key.length - 1;
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
/*     */   public IntOpenHashBigSet(long expected, float f) {
/* 106 */     if (f <= 0.0F || f > 1.0F)
/* 107 */       throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1"); 
/* 108 */     if (this.n < 0L)
/* 109 */       throw new IllegalArgumentException("The expected number of elements must be nonnegative"); 
/* 110 */     this.f = f;
/* 111 */     this.minN = this.n = HashCommon.bigArraySize(expected, f);
/* 112 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 113 */     this.key = IntBigArrays.newBigArray(this.n);
/* 114 */     initMasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(long expected) {
/* 124 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet() {
/* 132 */     this(16L, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(Collection<? extends Integer> c, float f) {
/* 143 */     this(c.size(), f);
/* 144 */     addAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(Collection<? extends Integer> c) {
/* 154 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(IntCollection c, float f) {
/* 165 */     this(c.size(), f);
/* 166 */     addAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(IntCollection c) {
/* 176 */     this(c, 0.75F);
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
/*     */   public IntOpenHashBigSet(IntIterator i, float f) {
/* 189 */     this(16L, f);
/* 190 */     while (i.hasNext()) {
/* 191 */       add(i.nextInt());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(IntIterator i) {
/* 202 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(Iterator<?> i, float f) {
/* 213 */     this(IntIterators.asIntIterator(i), f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(Iterator<?> i) {
/* 223 */     this(IntIterators.asIntIterator(i));
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
/*     */   public IntOpenHashBigSet(int[] a, int offset, int length, float f) {
/* 238 */     this((length < 0) ? 0L : length, f);
/* 239 */     IntArrays.ensureOffsetLength(a, offset, length);
/* 240 */     for (int i = 0; i < length; i++) {
/* 241 */       add(a[offset + i]);
/*     */     }
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
/*     */   public IntOpenHashBigSet(int[] a, int offset, int length) {
/* 255 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(int[] a, float f) {
/* 266 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntOpenHashBigSet(int[] a) {
/* 276 */     this(a, 0.75F);
/*     */   }
/*     */   private long realSize() {
/* 279 */     return this.containsNull ? (this.size - 1L) : this.size;
/*     */   }
/*     */   private void ensureCapacity(long capacity) {
/* 282 */     long needed = HashCommon.bigArraySize(capacity, this.f);
/* 283 */     if (needed > this.n)
/* 284 */       rehash(needed); 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Integer> c) {
/* 288 */     long size = (c instanceof Size64) ? ((Size64)c).size64() : c.size();
/*     */     
/* 290 */     if (this.f <= 0.5D) {
/* 291 */       ensureCapacity(size);
/*     */     } else {
/* 293 */       ensureCapacity(size64() + size);
/* 294 */     }  return super.addAll(c);
/*     */   }
/*     */   
/*     */   public boolean addAll(IntCollection c) {
/* 298 */     long size = (c instanceof Size64) ? ((Size64)c).size64() : c.size();
/* 299 */     if (this.f <= 0.5D) {
/* 300 */       ensureCapacity(size);
/*     */     } else {
/* 302 */       ensureCapacity(size64() + size);
/* 303 */     }  return super.addAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(int k) {
/* 308 */     if (k == 0) {
/* 309 */       if (this.containsNull)
/* 310 */         return false; 
/* 311 */       this.containsNull = true;
/*     */     } else {
/*     */       
/* 314 */       int[][] key = this.key;
/* 315 */       long h = HashCommon.mix(k);
/*     */       int displ, base, curr;
/* 317 */       if ((curr = key[base = (int)((h & this.mask) >>> 27L)][displ = (int)(h & this.segmentMask)]) != 0) {
/*     */         
/* 319 */         if (curr == k)
/* 320 */           return false;  while (true) {
/* 321 */           if ((curr = key[base = base + (((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0) & this.baseMask][displ]) != 0)
/*     */           
/* 323 */           { if (curr == k)
/* 324 */               return false;  continue; }  break;
/*     */         } 
/* 326 */       }  key[base][displ] = k;
/*     */     } 
/* 328 */     if (this.size++ >= this.maxFill) {
/* 329 */       rehash(2L * this.n);
/*     */     }
/*     */     
/* 332 */     return true;
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
/*     */   protected final void shiftKeys(long pos) {
/* 344 */     int[][] key = this.key; while (true) {
/*     */       long last;
/* 346 */       pos = (last = pos) + 1L & this.mask;
/*     */       while (true) {
/* 348 */         if (IntBigArrays.get(key, pos) == 0) {
/* 349 */           IntBigArrays.set(key, last, 0);
/*     */           return;
/*     */         } 
/* 352 */         long slot = HashCommon.mix(IntBigArrays.get(key, pos)) & this.mask;
/* 353 */         if ((last <= pos) ? (last >= slot || slot > pos) : (last >= slot && slot > pos))
/*     */           break; 
/* 355 */         pos = pos + 1L & this.mask;
/*     */       } 
/* 357 */       IntBigArrays.set(key, last, IntBigArrays.get(key, pos));
/*     */     } 
/*     */   }
/*     */   private boolean removeEntry(int base, int displ) {
/* 361 */     this.size--;
/* 362 */     shiftKeys(base * 134217728L + displ);
/* 363 */     if (this.n > this.minN && this.size < this.maxFill / 4L && this.n > 16L)
/* 364 */       rehash(this.n / 2L); 
/* 365 */     return true;
/*     */   }
/*     */   private boolean removeNullEntry() {
/* 368 */     this.containsNull = false;
/* 369 */     this.size--;
/* 370 */     if (this.n > this.minN && this.size < this.maxFill / 4L && this.n > 16L)
/* 371 */       rehash(this.n / 2L); 
/* 372 */     return true;
/*     */   }
/*     */   
/*     */   public boolean remove(int k) {
/* 376 */     if (k == 0) {
/* 377 */       if (this.containsNull)
/* 378 */         return removeNullEntry(); 
/* 379 */       return false;
/*     */     } 
/*     */     
/* 382 */     int[][] key = this.key;
/* 383 */     long h = HashCommon.mix(k);
/*     */     
/*     */     int curr, displ, base;
/* 386 */     if ((curr = key[base = (int)((h & this.mask) >>> 27L)][displ = (int)(h & this.segmentMask)]) == 0)
/*     */     {
/* 388 */       return false; } 
/* 389 */     if (curr == k)
/* 390 */       return removeEntry(base, displ); 
/*     */     while (true) {
/* 392 */       if ((curr = key[base = base + (((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0) & this.baseMask][displ]) == 0)
/*     */       {
/* 394 */         return false; } 
/* 395 */       if (curr == k)
/* 396 */         return removeEntry(base, displ); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(int k) {
/* 401 */     if (k == 0) {
/* 402 */       return this.containsNull;
/*     */     }
/* 404 */     int[][] key = this.key;
/* 405 */     long h = HashCommon.mix(k);
/*     */     
/*     */     int curr, displ, base;
/* 408 */     if ((curr = key[base = (int)((h & this.mask) >>> 27L)][displ = (int)(h & this.segmentMask)]) == 0)
/*     */     {
/* 410 */       return false; } 
/* 411 */     if (curr == k)
/* 412 */       return true; 
/*     */     while (true) {
/* 414 */       if ((curr = key[base = base + (((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0) & this.baseMask][displ]) == 0)
/*     */       {
/* 416 */         return false; } 
/* 417 */       if (curr == k) {
/* 418 */         return true;
/*     */       }
/*     */     } 
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
/*     */   public void clear() {
/* 434 */     if (this.size == 0L)
/*     */       return; 
/* 436 */     this.size = 0L;
/* 437 */     this.containsNull = false;
/* 438 */     IntBigArrays.fill(this.key, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class SetIterator
/*     */     implements IntIterator
/*     */   {
/* 447 */     int base = IntOpenHashBigSet.this.key.length;
/*     */ 
/*     */ 
/*     */     
/*     */     int displ;
/*     */ 
/*     */ 
/*     */     
/* 455 */     long last = -1L;
/*     */     
/* 457 */     long c = IntOpenHashBigSet.this.size;
/*     */     
/* 459 */     boolean mustReturnNull = IntOpenHashBigSet.this.containsNull;
/*     */ 
/*     */     
/*     */     IntArrayList wrapped;
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 467 */       return (this.c != 0L);
/*     */     }
/*     */     
/*     */     public int nextInt() {
/* 471 */       if (!hasNext())
/* 472 */         throw new NoSuchElementException(); 
/* 473 */       this.c--;
/* 474 */       if (this.mustReturnNull) {
/* 475 */         this.mustReturnNull = false;
/* 476 */         this.last = IntOpenHashBigSet.this.n;
/* 477 */         return 0;
/*     */       } 
/* 479 */       int[][] key = IntOpenHashBigSet.this.key;
/*     */       while (true) {
/* 481 */         if (this.displ == 0 && this.base <= 0) {
/*     */           
/* 483 */           this.last = Long.MIN_VALUE;
/* 484 */           return this.wrapped.getInt(---this.base - 1);
/*     */         } 
/* 486 */         if (this.displ-- == 0)
/* 487 */           this.displ = (key[--this.base]).length - 1; 
/* 488 */         int k = key[this.base][this.displ];
/* 489 */         if (k != 0) {
/* 490 */           this.last = this.base * 134217728L + this.displ;
/* 491 */           return k;
/*     */         } 
/*     */       } 
/*     */     }
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
/*     */     private final void shiftKeys(long pos) {
/* 506 */       int[][] key = IntOpenHashBigSet.this.key; while (true) {
/*     */         int curr; long last;
/* 508 */         pos = (last = pos) + 1L & IntOpenHashBigSet.this.mask;
/*     */         while (true) {
/* 510 */           if ((curr = IntBigArrays.get(key, pos)) == 0) {
/* 511 */             IntBigArrays.set(key, last, 0);
/*     */             return;
/*     */           } 
/* 514 */           long slot = HashCommon.mix(curr) & IntOpenHashBigSet.this.mask;
/* 515 */           if ((last <= pos) ? (last >= slot || slot > pos) : (last >= slot && slot > pos))
/*     */             break; 
/* 517 */           pos = pos + 1L & IntOpenHashBigSet.this.mask;
/*     */         } 
/* 519 */         if (pos < last) {
/* 520 */           if (this.wrapped == null)
/* 521 */             this.wrapped = new IntArrayList(); 
/* 522 */           this.wrapped.add(IntBigArrays.get(key, pos));
/*     */         } 
/* 524 */         IntBigArrays.set(key, last, curr);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void remove() {
/* 529 */       if (this.last == -1L)
/* 530 */         throw new IllegalStateException(); 
/* 531 */       if (this.last == IntOpenHashBigSet.this.n) {
/* 532 */         IntOpenHashBigSet.this.containsNull = false;
/* 533 */       } else if (this.base >= 0) {
/* 534 */         shiftKeys(this.last);
/*     */       } else {
/*     */         
/* 537 */         IntOpenHashBigSet.this.remove(this.wrapped.getInt(-this.base - 1));
/* 538 */         this.last = -1L;
/*     */         return;
/*     */       } 
/* 541 */       IntOpenHashBigSet.this.size--;
/* 542 */       this.last = -1L;
/*     */     }
/*     */     
/*     */     private SetIterator() {}
/*     */   }
/*     */   
/*     */   public IntIterator iterator() {
/* 549 */     return new SetIterator();
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
/*     */   
/*     */   public boolean trim() {
/* 566 */     long l = HashCommon.bigArraySize(this.size, this.f);
/* 567 */     if (l >= this.n || this.size > HashCommon.maxFill(l, this.f))
/* 568 */       return true; 
/*     */     try {
/* 570 */       rehash(l);
/* 571 */     } catch (OutOfMemoryError cantDoIt) {
/* 572 */       return false;
/*     */     } 
/* 574 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean trim(long n) {
/* 598 */     long l = HashCommon.bigArraySize(n, this.f);
/* 599 */     if (this.n <= l)
/* 600 */       return true; 
/*     */     try {
/* 602 */       rehash(l);
/* 603 */     } catch (OutOfMemoryError cantDoIt) {
/* 604 */       return false;
/*     */     } 
/* 606 */     return true;
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
/*     */   protected void rehash(long newN) {
/* 622 */     int[][] key = this.key;
/* 623 */     int[][] newKey = IntBigArrays.newBigArray(newN);
/* 624 */     long mask = newN - 1L;
/* 625 */     int newSegmentMask = (newKey[0]).length - 1;
/* 626 */     int newBaseMask = newKey.length - 1;
/* 627 */     int base = 0, displ = 0;
/*     */ 
/*     */     
/* 630 */     for (long i = realSize(); i-- != 0L; ) {
/* 631 */       while (key[base][displ] == 0)
/* 632 */         base += ((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0; 
/* 633 */       int k = key[base][displ];
/* 634 */       long h = HashCommon.mix(k);
/*     */       int b, d;
/* 636 */       if (newKey[b = (int)((h & mask) >>> 27L)][d = (int)(h & newSegmentMask)] != 0)
/* 637 */         while (true) { if (newKey[b = b + (((d = d + 1 & newSegmentMask) == 0) ? 1 : 0) & newBaseMask][d] != 0)
/* 638 */             continue;  break; }   newKey[b][d] = k;
/* 639 */       base += ((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0;
/*     */     } 
/* 641 */     this.n = newN;
/* 642 */     this.key = newKey;
/* 643 */     initMasks();
/* 644 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int size() {
/* 649 */     return (int)Math.min(2147483647L, this.size);
/*     */   }
/*     */   
/*     */   public long size64() {
/* 653 */     return this.size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 657 */     return (this.size == 0L);
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
/*     */   public IntOpenHashBigSet clone() {
/*     */     IntOpenHashBigSet c;
/*     */     try {
/* 674 */       c = (IntOpenHashBigSet)super.clone();
/* 675 */     } catch (CloneNotSupportedException cantHappen) {
/* 676 */       throw new InternalError();
/*     */     } 
/* 678 */     c.key = IntBigArrays.copy(this.key);
/* 679 */     c.containsNull = this.containsNull;
/* 680 */     return c;
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
/*     */   public int hashCode() {
/* 693 */     int[][] key = this.key;
/* 694 */     int h = 0, base = 0, displ = 0;
/* 695 */     for (long j = realSize(); j-- != 0L; ) {
/* 696 */       while (key[base][displ] == 0)
/* 697 */         base += ((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0; 
/* 698 */       h += key[base][displ];
/* 699 */       base += ((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0;
/*     */     } 
/* 701 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 704 */     IntIterator i = iterator();
/* 705 */     s.defaultWriteObject();
/* 706 */     for (long j = this.size; j-- != 0L;)
/* 707 */       s.writeInt(i.nextInt()); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 711 */     s.defaultReadObject();
/* 712 */     this.n = HashCommon.bigArraySize(this.size, this.f);
/* 713 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 714 */     int[][] key = this.key = IntBigArrays.newBigArray(this.n);
/* 715 */     initMasks();
/*     */ 
/*     */ 
/*     */     
/* 719 */     for (long i = this.size; i-- != 0L; ) {
/* 720 */       int k = s.readInt();
/* 721 */       if (k == 0) {
/* 722 */         this.containsNull = true; continue;
/*     */       } 
/* 724 */       long h = HashCommon.mix(k); int base, displ;
/* 725 */       if (key[base = (int)((h & this.mask) >>> 27L)][displ = (int)(h & this.segmentMask)] != 0)
/*     */         while (true) {
/* 727 */           if (key[base = base + (((displ = displ + 1 & this.segmentMask) == 0) ? 1 : 0) & this.baseMask][displ] != 0)
/*     */             continue;  break;
/* 729 */         }   key[base][displ] = k;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkTable() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\i\\unimi\dsi\fastutil\ints\IntOpenHashBigSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */