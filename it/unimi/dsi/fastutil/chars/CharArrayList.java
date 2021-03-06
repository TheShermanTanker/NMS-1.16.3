/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Arrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.RandomAccess;
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
/*     */ public class CharArrayList
/*     */   extends AbstractCharList
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7046029254386353130L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 10;
/*     */   protected transient char[] a;
/*     */   protected int size;
/*     */   
/*     */   protected CharArrayList(char[] a, boolean dummy) {
/*  66 */     this.a = a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(int capacity) {
/*  76 */     if (capacity < 0)
/*  77 */       throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative"); 
/*  78 */     if (capacity == 0) {
/*  79 */       this.a = CharArrays.EMPTY_ARRAY;
/*     */     } else {
/*  81 */       this.a = new char[capacity];
/*     */     } 
/*     */   }
/*     */   
/*     */   public CharArrayList() {
/*  86 */     this.a = CharArrays.DEFAULT_EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(Collection<? extends Character> c) {
/*  95 */     this(c.size());
/*  96 */     this.size = CharIterators.unwrap(CharIterators.asCharIterator(c.iterator()), this.a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(CharCollection c) {
/* 106 */     this(c.size());
/* 107 */     this.size = CharIterators.unwrap(c.iterator(), this.a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(CharList l) {
/* 116 */     this(l.size());
/* 117 */     l.getElements(0, this.a, 0, this.size = l.size());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(char[] a) {
/* 126 */     this(a, 0, a.length);
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
/*     */   public CharArrayList(char[] a, int offset, int length) {
/* 139 */     this(length);
/* 140 */     System.arraycopy(a, offset, this.a, 0, length);
/* 141 */     this.size = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(Iterator<? extends Character> i) {
/* 151 */     this();
/* 152 */     while (i.hasNext()) {
/* 153 */       add(((Character)i.next()).charValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharArrayList(CharIterator i) {
/* 164 */     this();
/* 165 */     while (i.hasNext()) {
/* 166 */       add(i.nextChar());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] elements() {
/* 174 */     return this.a;
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
/*     */   public static CharArrayList wrap(char[] a, int length) {
/* 191 */     if (length > a.length) {
/* 192 */       throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
/*     */     }
/* 194 */     CharArrayList l = new CharArrayList(a, false);
/* 195 */     l.size = length;
/* 196 */     return l;
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
/*     */   public static CharArrayList wrap(char[] a) {
/* 211 */     return wrap(a, a.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ensureCapacity(int capacity) {
/* 222 */     if (capacity <= this.a.length || this.a == CharArrays.DEFAULT_EMPTY_ARRAY)
/*     */       return; 
/* 224 */     this.a = CharArrays.ensureCapacity(this.a, capacity, this.size);
/* 225 */     assert this.size <= this.a.length;
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
/*     */   private void grow(int capacity) {
/* 237 */     if (capacity <= this.a.length)
/*     */       return; 
/* 239 */     if (this.a != CharArrays.DEFAULT_EMPTY_ARRAY) {
/* 240 */       capacity = (int)Math.max(
/* 241 */           Math.min(this.a.length + (this.a.length >> 1), 2147483639L), capacity);
/* 242 */     } else if (capacity < 10) {
/* 243 */       capacity = 10;
/* 244 */     }  this.a = CharArrays.forceCapacity(this.a, capacity, this.size);
/* 245 */     assert this.size <= this.a.length;
/*     */   }
/*     */   
/*     */   public void add(int index, char k) {
/* 249 */     ensureIndex(index);
/* 250 */     grow(this.size + 1);
/* 251 */     if (index != this.size)
/* 252 */       System.arraycopy(this.a, index, this.a, index + 1, this.size - index); 
/* 253 */     this.a[index] = k;
/* 254 */     this.size++;
/* 255 */     assert this.size <= this.a.length;
/*     */   }
/*     */   
/*     */   public boolean add(char k) {
/* 259 */     grow(this.size + 1);
/* 260 */     this.a[this.size++] = k;
/* 261 */     assert this.size <= this.a.length;
/* 262 */     return true;
/*     */   }
/*     */   
/*     */   public char getChar(int index) {
/* 266 */     if (index >= this.size) {
/* 267 */       throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/*     */     }
/* 269 */     return this.a[index];
/*     */   }
/*     */   
/*     */   public int indexOf(char k) {
/* 273 */     for (int i = 0; i < this.size; i++) {
/* 274 */       if (k == this.a[i])
/* 275 */         return i; 
/* 276 */     }  return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(char k) {
/* 280 */     for (int i = this.size; i-- != 0;) {
/* 281 */       if (k == this.a[i])
/* 282 */         return i; 
/* 283 */     }  return -1;
/*     */   }
/*     */   
/*     */   public char removeChar(int index) {
/* 287 */     if (index >= this.size) {
/* 288 */       throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/*     */     }
/* 290 */     char old = this.a[index];
/* 291 */     this.size--;
/* 292 */     if (index != this.size)
/* 293 */       System.arraycopy(this.a, index + 1, this.a, index, this.size - index); 
/* 294 */     assert this.size <= this.a.length;
/* 295 */     return old;
/*     */   }
/*     */   
/*     */   public boolean rem(char k) {
/* 299 */     int index = indexOf(k);
/* 300 */     if (index == -1)
/* 301 */       return false; 
/* 302 */     removeChar(index);
/* 303 */     assert this.size <= this.a.length;
/* 304 */     return true;
/*     */   }
/*     */   
/*     */   public char set(int index, char k) {
/* 308 */     if (index >= this.size) {
/* 309 */       throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/*     */     }
/* 311 */     char old = this.a[index];
/* 312 */     this.a[index] = k;
/* 313 */     return old;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 317 */     this.size = 0;
/* 318 */     assert this.size <= this.a.length;
/*     */   }
/*     */   
/*     */   public int size() {
/* 322 */     return this.size;
/*     */   }
/*     */   
/*     */   public void size(int size) {
/* 326 */     if (size > this.a.length)
/* 327 */       ensureCapacity(size); 
/* 328 */     if (size > this.size)
/* 329 */       Arrays.fill(this.a, this.size, size, false); 
/* 330 */     this.size = size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 334 */     return (this.size == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trim() {
/* 342 */     trim(0);
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
/*     */   public void trim(int n) {
/* 363 */     if (n >= this.a.length || this.size == this.a.length)
/*     */       return; 
/* 365 */     char[] t = new char[Math.max(n, this.size)];
/* 366 */     System.arraycopy(this.a, 0, t, 0, this.size);
/* 367 */     this.a = t;
/* 368 */     assert this.size <= this.a.length;
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
/*     */   public void getElements(int from, char[] a, int offset, int length) {
/* 386 */     CharArrays.ensureOffsetLength(a, offset, length);
/* 387 */     System.arraycopy(this.a, from, a, offset, length);
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
/*     */   public void removeElements(int from, int to) {
/* 399 */     Arrays.ensureFromTo(this.size, from, to);
/* 400 */     System.arraycopy(this.a, to, this.a, from, this.size - to);
/* 401 */     this.size -= to - from;
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
/*     */   public void addElements(int index, char[] a, int offset, int length) {
/* 417 */     ensureIndex(index);
/* 418 */     CharArrays.ensureOffsetLength(a, offset, length);
/* 419 */     grow(this.size + length);
/* 420 */     System.arraycopy(this.a, index, this.a, index + length, this.size - index);
/* 421 */     System.arraycopy(a, offset, this.a, index, length);
/* 422 */     this.size += length;
/*     */   }
/*     */   
/*     */   public char[] toArray(char[] a) {
/* 426 */     if (a == null || a.length < this.size)
/* 427 */       a = new char[this.size]; 
/* 428 */     System.arraycopy(this.a, 0, a, 0, this.size);
/* 429 */     return a;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, CharCollection c) {
/* 433 */     ensureIndex(index);
/* 434 */     int n = c.size();
/* 435 */     if (n == 0)
/* 436 */       return false; 
/* 437 */     grow(this.size + n);
/* 438 */     if (index != this.size)
/* 439 */       System.arraycopy(this.a, index, this.a, index + n, this.size - index); 
/* 440 */     CharIterator i = c.iterator();
/* 441 */     this.size += n;
/* 442 */     while (n-- != 0)
/* 443 */       this.a[index++] = i.nextChar(); 
/* 444 */     assert this.size <= this.a.length;
/* 445 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, CharList l) {
/* 449 */     ensureIndex(index);
/* 450 */     int n = l.size();
/* 451 */     if (n == 0)
/* 452 */       return false; 
/* 453 */     grow(this.size + n);
/* 454 */     if (index != this.size)
/* 455 */       System.arraycopy(this.a, index, this.a, index + n, this.size - index); 
/* 456 */     l.getElements(0, this.a, index, n);
/* 457 */     this.size += n;
/* 458 */     assert this.size <= this.a.length;
/* 459 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeAll(CharCollection c) {
/* 463 */     char[] a = this.a;
/* 464 */     int j = 0;
/* 465 */     for (int i = 0; i < this.size; i++) {
/* 466 */       if (!c.contains(a[i]))
/* 467 */         a[j++] = a[i]; 
/* 468 */     }  boolean modified = (this.size != j);
/* 469 */     this.size = j;
/* 470 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 474 */     char[] a = this.a;
/* 475 */     int j = 0;
/* 476 */     for (int i = 0; i < this.size; i++) {
/* 477 */       if (!c.contains(Character.valueOf(a[i])))
/* 478 */         a[j++] = a[i]; 
/* 479 */     }  boolean modified = (this.size != j);
/* 480 */     this.size = j;
/* 481 */     return modified;
/*     */   }
/*     */   
/*     */   public CharListIterator listIterator(final int index) {
/* 485 */     ensureIndex(index);
/* 486 */     return new CharListIterator() {
/* 487 */         int pos = index; int last = -1;
/*     */         
/*     */         public boolean hasNext() {
/* 490 */           return (this.pos < CharArrayList.this.size);
/*     */         }
/*     */         
/*     */         public boolean hasPrevious() {
/* 494 */           return (this.pos > 0);
/*     */         }
/*     */         
/*     */         public char nextChar() {
/* 498 */           if (!hasNext())
/* 499 */             throw new NoSuchElementException(); 
/* 500 */           return CharArrayList.this.a[this.last = this.pos++];
/*     */         }
/*     */         
/*     */         public char previousChar() {
/* 504 */           if (!hasPrevious())
/* 505 */             throw new NoSuchElementException(); 
/* 506 */           return CharArrayList.this.a[this.last = --this.pos];
/*     */         }
/*     */         
/*     */         public int nextIndex() {
/* 510 */           return this.pos;
/*     */         }
/*     */         
/*     */         public int previousIndex() {
/* 514 */           return this.pos - 1;
/*     */         }
/*     */         
/*     */         public void add(char k) {
/* 518 */           CharArrayList.this.add(this.pos++, k);
/* 519 */           this.last = -1;
/*     */         }
/*     */         
/*     */         public void set(char k) {
/* 523 */           if (this.last == -1)
/* 524 */             throw new IllegalStateException(); 
/* 525 */           CharArrayList.this.set(this.last, k);
/*     */         }
/*     */         
/*     */         public void remove() {
/* 529 */           if (this.last == -1)
/* 530 */             throw new IllegalStateException(); 
/* 531 */           CharArrayList.this.removeChar(this.last);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 536 */           if (this.last < this.pos)
/* 537 */             this.pos--; 
/* 538 */           this.last = -1;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public CharArrayList clone() {
/* 544 */     CharArrayList c = new CharArrayList(this.size);
/* 545 */     System.arraycopy(this.a, 0, c.a, 0, this.size);
/* 546 */     c.size = this.size;
/* 547 */     return c;
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
/*     */   public boolean equals(CharArrayList l) {
/* 562 */     if (l == this)
/* 563 */       return true; 
/* 564 */     int s = size();
/* 565 */     if (s != l.size())
/* 566 */       return false; 
/* 567 */     char[] a1 = this.a;
/* 568 */     char[] a2 = l.a;
/* 569 */     while (s-- != 0) {
/* 570 */       if (a1[s] != a2[s])
/* 571 */         return false; 
/* 572 */     }  return true;
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
/*     */   public int compareTo(CharArrayList l) {
/* 588 */     int s1 = size(), s2 = l.size();
/* 589 */     char[] a1 = this.a, a2 = l.a;
/*     */     
/*     */     int i;
/* 592 */     for (i = 0; i < s1 && i < s2; i++) {
/* 593 */       char e1 = a1[i];
/* 594 */       char e2 = a2[i]; int r;
/* 595 */       if ((r = Character.compare(e1, e2)) != 0)
/* 596 */         return r; 
/*     */     } 
/* 598 */     return (i < s2) ? -1 : ((i < s1) ? 1 : 0);
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 601 */     s.defaultWriteObject();
/* 602 */     for (int i = 0; i < this.size; i++)
/* 603 */       s.writeChar(this.a[i]); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 607 */     s.defaultReadObject();
/* 608 */     this.a = new char[this.size];
/* 609 */     for (int i = 0; i < this.size; i++)
/* 610 */       this.a[i] = s.readChar(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\i\\unimi\dsi\fastutil\chars\CharArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */