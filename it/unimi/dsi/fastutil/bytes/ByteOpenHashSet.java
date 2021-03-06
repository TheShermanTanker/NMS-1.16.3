/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ByteOpenHashSet
/*     */   extends AbstractByteSet
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient byte[] key;
/*     */   protected transient int mask;
/*     */   protected transient boolean containsNull;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected final transient int minN;
/*     */   protected int size;
/*     */   protected final float f;
/*     */   
/*     */   public ByteOpenHashSet(int expected, float f) {
/*  86 */     if (f <= 0.0F || f > 1.0F)
/*  87 */       throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1"); 
/*  88 */     if (expected < 0)
/*  89 */       throw new IllegalArgumentException("The expected number of elements must be nonnegative"); 
/*  90 */     this.f = f;
/*  91 */     this.minN = this.n = HashCommon.arraySize(expected, f);
/*  92 */     this.mask = this.n - 1;
/*  93 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  94 */     this.key = new byte[this.n + 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(int expected) {
/* 103 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet() {
/* 111 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(Collection<? extends Byte> c, float f) {
/* 122 */     this(c.size(), f);
/* 123 */     addAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(Collection<? extends Byte> c) {
/* 133 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(ByteCollection c, float f) {
/* 144 */     this(c.size(), f);
/* 145 */     addAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(ByteCollection c) {
/* 155 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(ByteIterator i, float f) {
/* 166 */     this(16, f);
/* 167 */     while (i.hasNext()) {
/* 168 */       add(i.nextByte());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(ByteIterator i) {
/* 178 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(Iterator<?> i, float f) {
/* 189 */     this(ByteIterators.asByteIterator(i), f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(Iterator<?> i) {
/* 199 */     this(ByteIterators.asByteIterator(i));
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
/*     */   public ByteOpenHashSet(byte[] a, int offset, int length, float f) {
/* 214 */     this((length < 0) ? 0 : length, f);
/* 215 */     ByteArrays.ensureOffsetLength(a, offset, length);
/* 216 */     for (int i = 0; i < length; i++) {
/* 217 */       add(a[offset + i]);
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
/*     */   public ByteOpenHashSet(byte[] a, int offset, int length) {
/* 231 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(byte[] a, float f) {
/* 242 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOpenHashSet(byte[] a) {
/* 252 */     this(a, 0.75F);
/*     */   }
/*     */   private int realSize() {
/* 255 */     return this.containsNull ? (this.size - 1) : this.size;
/*     */   }
/*     */   private void ensureCapacity(int capacity) {
/* 258 */     int needed = HashCommon.arraySize(capacity, this.f);
/* 259 */     if (needed > this.n)
/* 260 */       rehash(needed); 
/*     */   }
/*     */   private void tryCapacity(long capacity) {
/* 263 */     int needed = (int)Math.min(1073741824L, 
/* 264 */         Math.max(2L, HashCommon.nextPowerOfTwo((long)Math.ceil(((float)capacity / this.f)))));
/* 265 */     if (needed > this.n)
/* 266 */       rehash(needed); 
/*     */   }
/*     */   
/*     */   public boolean addAll(ByteCollection c) {
/* 270 */     if (this.f <= 0.5D) {
/* 271 */       ensureCapacity(c.size());
/*     */     } else {
/* 273 */       tryCapacity((size() + c.size()));
/*     */     } 
/* 275 */     return super.addAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends Byte> c) {
/* 280 */     if (this.f <= 0.5D) {
/* 281 */       ensureCapacity(c.size());
/*     */     } else {
/* 283 */       tryCapacity((size() + c.size()));
/*     */     } 
/* 285 */     return super.addAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(byte k) {
/* 290 */     if (k == 0) {
/* 291 */       if (this.containsNull)
/* 292 */         return false; 
/* 293 */       this.containsNull = true;
/*     */     } else {
/*     */       
/* 296 */       byte[] key = this.key; int pos;
/*     */       byte curr;
/* 298 */       if ((curr = key[pos = HashCommon.mix(k) & this.mask]) != 0) {
/* 299 */         if (curr == k)
/* 300 */           return false; 
/* 301 */         while ((curr = key[pos = pos + 1 & this.mask]) != 0) {
/* 302 */           if (curr == k)
/* 303 */             return false; 
/*     */         } 
/* 305 */       }  key[pos] = k;
/*     */     } 
/* 307 */     if (this.size++ >= this.maxFill) {
/* 308 */       rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */     }
/*     */     
/* 311 */     return true;
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
/*     */   protected final void shiftKeys(int pos) {
/* 324 */     byte[] key = this.key; while (true) {
/*     */       byte curr; int last;
/* 326 */       pos = (last = pos) + 1 & this.mask;
/*     */       while (true) {
/* 328 */         if ((curr = key[pos]) == 0) {
/* 329 */           key[last] = 0;
/*     */           return;
/*     */         } 
/* 332 */         int slot = HashCommon.mix(curr) & this.mask;
/* 333 */         if ((last <= pos) ? (last >= slot || slot > pos) : (last >= slot && slot > pos))
/*     */           break; 
/* 335 */         pos = pos + 1 & this.mask;
/*     */       } 
/* 337 */       key[last] = curr;
/*     */     } 
/*     */   }
/*     */   private boolean removeEntry(int pos) {
/* 341 */     this.size--;
/* 342 */     shiftKeys(pos);
/* 343 */     if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16)
/* 344 */       rehash(this.n / 2); 
/* 345 */     return true;
/*     */   }
/*     */   private boolean removeNullEntry() {
/* 348 */     this.containsNull = false;
/* 349 */     this.key[this.n] = 0;
/* 350 */     this.size--;
/* 351 */     if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16)
/* 352 */       rehash(this.n / 2); 
/* 353 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(byte k) {
/* 358 */     if (k == 0) {
/* 359 */       if (this.containsNull)
/* 360 */         return removeNullEntry(); 
/* 361 */       return false;
/*     */     } 
/*     */     
/* 364 */     byte[] key = this.key;
/*     */     byte curr;
/*     */     int pos;
/* 367 */     if ((curr = key[pos = HashCommon.mix(k) & this.mask]) == 0)
/* 368 */       return false; 
/* 369 */     if (k == curr)
/* 370 */       return removeEntry(pos); 
/*     */     while (true) {
/* 372 */       if ((curr = key[pos = pos + 1 & this.mask]) == 0)
/* 373 */         return false; 
/* 374 */       if (k == curr) {
/* 375 */         return removeEntry(pos);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(byte k) {
/* 381 */     if (k == 0) {
/* 382 */       return this.containsNull;
/*     */     }
/* 384 */     byte[] key = this.key;
/*     */     byte curr;
/*     */     int pos;
/* 387 */     if ((curr = key[pos = HashCommon.mix(k) & this.mask]) == 0)
/* 388 */       return false; 
/* 389 */     if (k == curr)
/* 390 */       return true; 
/*     */     while (true) {
/* 392 */       if ((curr = key[pos = pos + 1 & this.mask]) == 0)
/* 393 */         return false; 
/* 394 */       if (k == curr) {
/* 395 */         return true;
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
/*     */   public void clear() {
/* 407 */     if (this.size == 0)
/*     */       return; 
/* 409 */     this.size = 0;
/* 410 */     this.containsNull = false;
/* 411 */     Arrays.fill(this.key, (byte)0);
/*     */   }
/*     */   
/*     */   public int size() {
/* 415 */     return this.size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 419 */     return (this.size == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class SetIterator
/*     */     implements ByteIterator
/*     */   {
/* 428 */     int pos = ByteOpenHashSet.this.n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 435 */     int last = -1;
/*     */     
/* 437 */     int c = ByteOpenHashSet.this.size;
/*     */     
/* 439 */     boolean mustReturnNull = ByteOpenHashSet.this.containsNull;
/*     */ 
/*     */     
/*     */     ByteArrayList wrapped;
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 447 */       return (this.c != 0);
/*     */     }
/*     */     
/*     */     public byte nextByte() {
/* 451 */       if (!hasNext())
/* 452 */         throw new NoSuchElementException(); 
/* 453 */       this.c--;
/* 454 */       if (this.mustReturnNull) {
/* 455 */         this.mustReturnNull = false;
/* 456 */         this.last = ByteOpenHashSet.this.n;
/* 457 */         return ByteOpenHashSet.this.key[ByteOpenHashSet.this.n];
/*     */       } 
/* 459 */       byte[] key = ByteOpenHashSet.this.key;
/*     */       while (true) {
/* 461 */         if (--this.pos < 0) {
/*     */           
/* 463 */           this.last = Integer.MIN_VALUE;
/* 464 */           return this.wrapped.getByte(-this.pos - 1);
/*     */         } 
/* 466 */         if (key[this.pos] != 0) {
/* 467 */           return key[this.last = this.pos];
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
/*     */     private final void shiftKeys(int pos) {
/* 481 */       byte[] key = ByteOpenHashSet.this.key; while (true) {
/*     */         byte curr; int last;
/* 483 */         pos = (last = pos) + 1 & ByteOpenHashSet.this.mask;
/*     */         while (true) {
/* 485 */           if ((curr = key[pos]) == 0) {
/* 486 */             key[last] = 0;
/*     */             return;
/*     */           } 
/* 489 */           int slot = HashCommon.mix(curr) & ByteOpenHashSet.this.mask;
/* 490 */           if ((last <= pos) ? (last >= slot || slot > pos) : (last >= slot && slot > pos))
/*     */             break; 
/* 492 */           pos = pos + 1 & ByteOpenHashSet.this.mask;
/*     */         } 
/* 494 */         if (pos < last) {
/* 495 */           if (this.wrapped == null)
/* 496 */             this.wrapped = new ByteArrayList(2); 
/* 497 */           this.wrapped.add(key[pos]);
/*     */         } 
/* 499 */         key[last] = curr;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void remove() {
/* 504 */       if (this.last == -1)
/* 505 */         throw new IllegalStateException(); 
/* 506 */       if (this.last == ByteOpenHashSet.this.n) {
/* 507 */         ByteOpenHashSet.this.containsNull = false;
/* 508 */         ByteOpenHashSet.this.key[ByteOpenHashSet.this.n] = 0;
/* 509 */       } else if (this.pos >= 0) {
/* 510 */         shiftKeys(this.last);
/*     */       } else {
/*     */         
/* 513 */         ByteOpenHashSet.this.remove(this.wrapped.getByte(-this.pos - 1));
/* 514 */         this.last = -1;
/*     */         return;
/*     */       } 
/* 517 */       ByteOpenHashSet.this.size--;
/* 518 */       this.last = -1;
/*     */     }
/*     */     
/*     */     private SetIterator() {}
/*     */   }
/*     */   
/*     */   public ByteIterator iterator() {
/* 525 */     return new SetIterator();
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
/* 542 */     int l = HashCommon.arraySize(this.size, this.f);
/* 543 */     if (l >= this.n || this.size > HashCommon.maxFill(l, this.f))
/* 544 */       return true; 
/*     */     try {
/* 546 */       rehash(l);
/* 547 */     } catch (OutOfMemoryError cantDoIt) {
/* 548 */       return false;
/*     */     } 
/* 550 */     return true;
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
/*     */   public boolean trim(int n) {
/* 574 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil((n / this.f)));
/* 575 */     if (l >= n || this.size > HashCommon.maxFill(l, this.f))
/* 576 */       return true; 
/*     */     try {
/* 578 */       rehash(l);
/* 579 */     } catch (OutOfMemoryError cantDoIt) {
/* 580 */       return false;
/*     */     } 
/* 582 */     return true;
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
/*     */   protected void rehash(int newN) {
/* 598 */     byte[] key = this.key;
/* 599 */     int mask = newN - 1;
/* 600 */     byte[] newKey = new byte[newN + 1];
/* 601 */     int i = this.n;
/* 602 */     for (int j = realSize(); j-- != 0; ) {
/* 603 */       while (key[--i] == 0); int pos;
/* 604 */       if (newKey[pos = HashCommon.mix(key[i]) & mask] != 0)
/* 605 */         while (newKey[pos = pos + 1 & mask] != 0); 
/* 606 */       newKey[pos] = key[i];
/*     */     } 
/* 608 */     this.n = newN;
/* 609 */     this.mask = mask;
/* 610 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 611 */     this.key = newKey;
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
/*     */   public ByteOpenHashSet clone() {
/*     */     ByteOpenHashSet c;
/*     */     try {
/* 628 */       c = (ByteOpenHashSet)super.clone();
/* 629 */     } catch (CloneNotSupportedException cantHappen) {
/* 630 */       throw new InternalError();
/*     */     } 
/* 632 */     c.key = (byte[])this.key.clone();
/* 633 */     c.containsNull = this.containsNull;
/* 634 */     return c;
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
/* 647 */     int h = 0;
/* 648 */     for (int j = realSize(), i = 0; j-- != 0; ) {
/* 649 */       while (this.key[i] == 0)
/* 650 */         i++; 
/* 651 */       h += this.key[i];
/* 652 */       i++;
/*     */     } 
/*     */     
/* 655 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 658 */     ByteIterator i = iterator();
/* 659 */     s.defaultWriteObject();
/* 660 */     for (int j = this.size; j-- != 0;)
/* 661 */       s.writeByte(i.nextByte()); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 665 */     s.defaultReadObject();
/* 666 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 667 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 668 */     this.mask = this.n - 1;
/* 669 */     byte[] key = this.key = new byte[this.n + 1];
/*     */     
/* 671 */     for (int i = this.size; i-- != 0; ) {
/* 672 */       int pos; byte k = s.readByte();
/* 673 */       if (k == 0) {
/* 674 */         pos = this.n;
/* 675 */         this.containsNull = true;
/*     */       }
/* 677 */       else if (key[pos = HashCommon.mix(k) & this.mask] != 0) {
/* 678 */         while (key[pos = pos + 1 & this.mask] != 0);
/*     */       } 
/* 680 */       key[pos] = k;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkTable() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\i\\unimi\dsi\fastutil\bytes\ByteOpenHashSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */