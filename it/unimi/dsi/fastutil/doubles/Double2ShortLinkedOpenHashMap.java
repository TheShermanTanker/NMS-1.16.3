/*      */ package it.unimi.dsi.fastutil.doubles;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.SafeMath;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortListIterator;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Objects;
/*      */ import java.util.Set;
/*      */ import java.util.SortedSet;
/*      */ import java.util.function.BiFunction;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.DoubleConsumer;
/*      */ import java.util.function.DoubleFunction;
/*      */ import java.util.function.DoubleToIntFunction;
/*      */ import java.util.function.IntConsumer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Double2ShortLinkedOpenHashMap
/*      */   extends AbstractDouble2ShortSortedMap
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   private static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient double[] key;
/*      */   protected transient short[] value;
/*      */   protected transient int mask;
/*      */   protected transient boolean containsNullKey;
/*  107 */   protected transient int first = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   protected transient int last = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient long[] link;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient int n;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient int maxFill;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final transient int minN;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int size;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final float f;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient Double2ShortSortedMap.FastSortedEntrySet entries;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient DoubleSortedSet keys;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient ShortCollection values;
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(int expected, float f) {
/*  153 */     if (f <= 0.0F || f > 1.0F)
/*  154 */       throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1"); 
/*  155 */     if (expected < 0)
/*  156 */       throw new IllegalArgumentException("The expected number of elements must be nonnegative"); 
/*  157 */     this.f = f;
/*  158 */     this.minN = this.n = HashCommon.arraySize(expected, f);
/*  159 */     this.mask = this.n - 1;
/*  160 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  161 */     this.key = new double[this.n + 1];
/*  162 */     this.value = new short[this.n + 1];
/*  163 */     this.link = new long[this.n + 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(int expected) {
/*  172 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap() {
/*  180 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(Map<? extends Double, ? extends Short> m, float f) {
/*  191 */     this(m.size(), f);
/*  192 */     putAll(m);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(Map<? extends Double, ? extends Short> m) {
/*  202 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(Double2ShortMap m, float f) {
/*  213 */     this(m.size(), f);
/*  214 */     putAll(m);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(Double2ShortMap m) {
/*  224 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(double[] k, short[] v, float f) {
/*  239 */     this(k.length, f);
/*  240 */     if (k.length != v.length) {
/*  241 */       throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*      */     }
/*  243 */     for (int i = 0; i < k.length; i++) {
/*  244 */       put(k[i], v[i]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap(double[] k, short[] v) {
/*  258 */     this(k, v, 0.75F);
/*      */   }
/*      */   private int realSize() {
/*  261 */     return this.containsNullKey ? (this.size - 1) : this.size;
/*      */   }
/*      */   private void ensureCapacity(int capacity) {
/*  264 */     int needed = HashCommon.arraySize(capacity, this.f);
/*  265 */     if (needed > this.n)
/*  266 */       rehash(needed); 
/*      */   }
/*      */   private void tryCapacity(long capacity) {
/*  269 */     int needed = (int)Math.min(1073741824L, 
/*  270 */         Math.max(2L, HashCommon.nextPowerOfTwo((long)Math.ceil(((float)capacity / this.f)))));
/*  271 */     if (needed > this.n)
/*  272 */       rehash(needed); 
/*      */   }
/*      */   private short removeEntry(int pos) {
/*  275 */     short oldValue = this.value[pos];
/*  276 */     this.size--;
/*  277 */     fixPointers(pos);
/*  278 */     shiftKeys(pos);
/*  279 */     if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16)
/*  280 */       rehash(this.n / 2); 
/*  281 */     return oldValue;
/*      */   }
/*      */   private short removeNullEntry() {
/*  284 */     this.containsNullKey = false;
/*  285 */     short oldValue = this.value[this.n];
/*  286 */     this.size--;
/*  287 */     fixPointers(this.n);
/*  288 */     if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16)
/*  289 */       rehash(this.n / 2); 
/*  290 */     return oldValue;
/*      */   }
/*      */   
/*      */   public void putAll(Map<? extends Double, ? extends Short> m) {
/*  294 */     if (this.f <= 0.5D) {
/*  295 */       ensureCapacity(m.size());
/*      */     } else {
/*  297 */       tryCapacity((size() + m.size()));
/*      */     } 
/*  299 */     super.putAll(m);
/*      */   }
/*      */   
/*      */   private int find(double k) {
/*  303 */     if (Double.doubleToLongBits(k) == 0L) {
/*  304 */       return this.containsNullKey ? this.n : -(this.n + 1);
/*      */     }
/*  306 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  309 */     if (Double.doubleToLongBits(
/*  310 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  312 */       return -(pos + 1); } 
/*  313 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  314 */       return pos;
/*      */     }
/*      */     while (true) {
/*  317 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  318 */         return -(pos + 1); 
/*  319 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr))
/*  320 */         return pos; 
/*      */     } 
/*      */   }
/*      */   private void insert(int pos, double k, short v) {
/*  324 */     if (pos == this.n)
/*  325 */       this.containsNullKey = true; 
/*  326 */     this.key[pos] = k;
/*  327 */     this.value[pos] = v;
/*  328 */     if (this.size == 0) {
/*  329 */       this.first = this.last = pos;
/*      */       
/*  331 */       this.link[pos] = -1L;
/*      */     } else {
/*  333 */       this.link[this.last] = this.link[this.last] ^ (this.link[this.last] ^ pos & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/*  334 */       this.link[pos] = (this.last & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL;
/*  335 */       this.last = pos;
/*      */     } 
/*  337 */     if (this.size++ >= this.maxFill) {
/*  338 */       rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public short put(double k, short v) {
/*  344 */     int pos = find(k);
/*  345 */     if (pos < 0) {
/*  346 */       insert(-pos - 1, k, v);
/*  347 */       return this.defRetValue;
/*      */     } 
/*  349 */     short oldValue = this.value[pos];
/*  350 */     this.value[pos] = v;
/*  351 */     return oldValue;
/*      */   }
/*      */   private short addToValue(int pos, short incr) {
/*  354 */     short oldValue = this.value[pos];
/*  355 */     this.value[pos] = (short)(oldValue + incr);
/*  356 */     return oldValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short addTo(double k, short incr) {
/*      */     int pos;
/*  376 */     if (Double.doubleToLongBits(k) == 0L) {
/*  377 */       if (this.containsNullKey)
/*  378 */         return addToValue(this.n, incr); 
/*  379 */       pos = this.n;
/*  380 */       this.containsNullKey = true;
/*      */     } else {
/*      */       
/*  383 */       double[] key = this.key;
/*      */       double curr;
/*  385 */       if (Double.doubleToLongBits(
/*  386 */           curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) != 0L) {
/*      */         
/*  388 */         if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k))
/*  389 */           return addToValue(pos, incr); 
/*  390 */         while (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) != 0L) {
/*  391 */           if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k))
/*  392 */             return addToValue(pos, incr); 
/*      */         } 
/*      */       } 
/*  395 */     }  this.key[pos] = k;
/*  396 */     this.value[pos] = (short)(this.defRetValue + incr);
/*  397 */     if (this.size == 0) {
/*  398 */       this.first = this.last = pos;
/*      */       
/*  400 */       this.link[pos] = -1L;
/*      */     } else {
/*  402 */       this.link[this.last] = this.link[this.last] ^ (this.link[this.last] ^ pos & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/*  403 */       this.link[pos] = (this.last & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL;
/*  404 */       this.last = pos;
/*      */     } 
/*  406 */     if (this.size++ >= this.maxFill) {
/*  407 */       rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */     }
/*      */     
/*  410 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void shiftKeys(int pos) {
/*  423 */     double[] key = this.key; while (true) {
/*      */       double curr; int last;
/*  425 */       pos = (last = pos) + 1 & this.mask;
/*      */       while (true) {
/*  427 */         if (Double.doubleToLongBits(curr = key[pos]) == 0L) {
/*  428 */           key[last] = 0.0D;
/*      */           return;
/*      */         } 
/*  431 */         int slot = (int)HashCommon.mix(Double.doubleToRawLongBits(curr)) & this.mask;
/*  432 */         if ((last <= pos) ? (last >= slot || slot > pos) : (last >= slot && slot > pos))
/*      */           break; 
/*  434 */         pos = pos + 1 & this.mask;
/*      */       } 
/*  436 */       key[last] = curr;
/*  437 */       this.value[last] = this.value[pos];
/*  438 */       fixPointers(pos, last);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public short remove(double k) {
/*  444 */     if (Double.doubleToLongBits(k) == 0L) {
/*  445 */       if (this.containsNullKey)
/*  446 */         return removeNullEntry(); 
/*  447 */       return this.defRetValue;
/*      */     } 
/*      */     
/*  450 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  453 */     if (Double.doubleToLongBits(
/*  454 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  456 */       return this.defRetValue; } 
/*  457 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr))
/*  458 */       return removeEntry(pos); 
/*      */     while (true) {
/*  460 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  461 */         return this.defRetValue; 
/*  462 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr))
/*  463 */         return removeEntry(pos); 
/*      */     } 
/*      */   }
/*      */   private short setValue(int pos, short v) {
/*  467 */     short oldValue = this.value[pos];
/*  468 */     this.value[pos] = v;
/*  469 */     return oldValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short removeFirstShort() {
/*  480 */     if (this.size == 0)
/*  481 */       throw new NoSuchElementException(); 
/*  482 */     int pos = this.first;
/*      */     
/*  484 */     this.first = (int)this.link[pos];
/*  485 */     if (0 <= this.first)
/*      */     {
/*  487 */       this.link[this.first] = this.link[this.first] | 0xFFFFFFFF00000000L;
/*      */     }
/*  489 */     this.size--;
/*  490 */     short v = this.value[pos];
/*  491 */     if (pos == this.n) {
/*  492 */       this.containsNullKey = false;
/*      */     } else {
/*  494 */       shiftKeys(pos);
/*  495 */     }  if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16)
/*  496 */       rehash(this.n / 2); 
/*  497 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short removeLastShort() {
/*  507 */     if (this.size == 0)
/*  508 */       throw new NoSuchElementException(); 
/*  509 */     int pos = this.last;
/*      */     
/*  511 */     this.last = (int)(this.link[pos] >>> 32L);
/*  512 */     if (0 <= this.last)
/*      */     {
/*  514 */       this.link[this.last] = this.link[this.last] | 0xFFFFFFFFL;
/*      */     }
/*  516 */     this.size--;
/*  517 */     short v = this.value[pos];
/*  518 */     if (pos == this.n) {
/*  519 */       this.containsNullKey = false;
/*      */     } else {
/*  521 */       shiftKeys(pos);
/*  522 */     }  if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16)
/*  523 */       rehash(this.n / 2); 
/*  524 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  527 */     if (this.size == 1 || this.first == i)
/*      */       return; 
/*  529 */     if (this.last == i) {
/*  530 */       this.last = (int)(this.link[i] >>> 32L);
/*      */       
/*  532 */       this.link[this.last] = this.link[this.last] | 0xFFFFFFFFL;
/*      */     } else {
/*  534 */       long linki = this.link[i];
/*  535 */       int prev = (int)(linki >>> 32L);
/*  536 */       int next = (int)linki;
/*  537 */       this.link[prev] = this.link[prev] ^ (this.link[prev] ^ linki & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/*  538 */       this.link[next] = this.link[next] ^ (this.link[next] ^ linki & 0xFFFFFFFF00000000L) & 0xFFFFFFFF00000000L;
/*      */     } 
/*  540 */     this.link[this.first] = this.link[this.first] ^ (this.link[this.first] ^ (i & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/*  541 */     this.link[i] = 0xFFFFFFFF00000000L | this.first & 0xFFFFFFFFL;
/*  542 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  545 */     if (this.size == 1 || this.last == i)
/*      */       return; 
/*  547 */     if (this.first == i) {
/*  548 */       this.first = (int)this.link[i];
/*      */       
/*  550 */       this.link[this.first] = this.link[this.first] | 0xFFFFFFFF00000000L;
/*      */     } else {
/*  552 */       long linki = this.link[i];
/*  553 */       int prev = (int)(linki >>> 32L);
/*  554 */       int next = (int)linki;
/*  555 */       this.link[prev] = this.link[prev] ^ (this.link[prev] ^ linki & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/*  556 */       this.link[next] = this.link[next] ^ (this.link[next] ^ linki & 0xFFFFFFFF00000000L) & 0xFFFFFFFF00000000L;
/*      */     } 
/*  558 */     this.link[this.last] = this.link[this.last] ^ (this.link[this.last] ^ i & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/*  559 */     this.link[i] = (this.last & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL;
/*  560 */     this.last = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getAndMoveToFirst(double k) {
/*  572 */     if (Double.doubleToLongBits(k) == 0L) {
/*  573 */       if (this.containsNullKey) {
/*  574 */         moveIndexToFirst(this.n);
/*  575 */         return this.value[this.n];
/*      */       } 
/*  577 */       return this.defRetValue;
/*      */     } 
/*      */     
/*  580 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  583 */     if (Double.doubleToLongBits(
/*  584 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  586 */       return this.defRetValue; } 
/*  587 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  588 */       moveIndexToFirst(pos);
/*  589 */       return this.value[pos];
/*      */     } 
/*      */     
/*      */     while (true) {
/*  593 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  594 */         return this.defRetValue; 
/*  595 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  596 */         moveIndexToFirst(pos);
/*  597 */         return this.value[pos];
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getAndMoveToLast(double k) {
/*  611 */     if (Double.doubleToLongBits(k) == 0L) {
/*  612 */       if (this.containsNullKey) {
/*  613 */         moveIndexToLast(this.n);
/*  614 */         return this.value[this.n];
/*      */       } 
/*  616 */       return this.defRetValue;
/*      */     } 
/*      */     
/*  619 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  622 */     if (Double.doubleToLongBits(
/*  623 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  625 */       return this.defRetValue; } 
/*  626 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  627 */       moveIndexToLast(pos);
/*  628 */       return this.value[pos];
/*      */     } 
/*      */     
/*      */     while (true) {
/*  632 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  633 */         return this.defRetValue; 
/*  634 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  635 */         moveIndexToLast(pos);
/*  636 */         return this.value[pos];
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short putAndMoveToFirst(double k, short v) {
/*      */     int pos;
/*  653 */     if (Double.doubleToLongBits(k) == 0L) {
/*  654 */       if (this.containsNullKey) {
/*  655 */         moveIndexToFirst(this.n);
/*  656 */         return setValue(this.n, v);
/*      */       } 
/*  658 */       this.containsNullKey = true;
/*  659 */       pos = this.n;
/*      */     } else {
/*      */       
/*  662 */       double[] key = this.key;
/*      */       double curr;
/*  664 */       if (Double.doubleToLongBits(
/*  665 */           curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) != 0L) {
/*      */         
/*  667 */         if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k)) {
/*  668 */           moveIndexToFirst(pos);
/*  669 */           return setValue(pos, v);
/*      */         } 
/*  671 */         while (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) != 0L) {
/*  672 */           if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k)) {
/*  673 */             moveIndexToFirst(pos);
/*  674 */             return setValue(pos, v);
/*      */           } 
/*      */         } 
/*      */       } 
/*  678 */     }  this.key[pos] = k;
/*  679 */     this.value[pos] = v;
/*  680 */     if (this.size == 0) {
/*  681 */       this.first = this.last = pos;
/*      */       
/*  683 */       this.link[pos] = -1L;
/*      */     } else {
/*  685 */       this.link[this.first] = this.link[this.first] ^ (this.link[this.first] ^ (pos & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/*  686 */       this.link[pos] = 0xFFFFFFFF00000000L | this.first & 0xFFFFFFFFL;
/*  687 */       this.first = pos;
/*      */     } 
/*  689 */     if (this.size++ >= this.maxFill) {
/*  690 */       rehash(HashCommon.arraySize(this.size, this.f));
/*      */     }
/*      */     
/*  693 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short putAndMoveToLast(double k, short v) {
/*      */     int pos;
/*  708 */     if (Double.doubleToLongBits(k) == 0L) {
/*  709 */       if (this.containsNullKey) {
/*  710 */         moveIndexToLast(this.n);
/*  711 */         return setValue(this.n, v);
/*      */       } 
/*  713 */       this.containsNullKey = true;
/*  714 */       pos = this.n;
/*      */     } else {
/*      */       
/*  717 */       double[] key = this.key;
/*      */       double curr;
/*  719 */       if (Double.doubleToLongBits(
/*  720 */           curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) != 0L) {
/*      */         
/*  722 */         if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k)) {
/*  723 */           moveIndexToLast(pos);
/*  724 */           return setValue(pos, v);
/*      */         } 
/*  726 */         while (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) != 0L) {
/*  727 */           if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k)) {
/*  728 */             moveIndexToLast(pos);
/*  729 */             return setValue(pos, v);
/*      */           } 
/*      */         } 
/*      */       } 
/*  733 */     }  this.key[pos] = k;
/*  734 */     this.value[pos] = v;
/*  735 */     if (this.size == 0) {
/*  736 */       this.first = this.last = pos;
/*      */       
/*  738 */       this.link[pos] = -1L;
/*      */     } else {
/*  740 */       this.link[this.last] = this.link[this.last] ^ (this.link[this.last] ^ pos & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/*  741 */       this.link[pos] = (this.last & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL;
/*  742 */       this.last = pos;
/*      */     } 
/*  744 */     if (this.size++ >= this.maxFill) {
/*  745 */       rehash(HashCommon.arraySize(this.size, this.f));
/*      */     }
/*      */     
/*  748 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public short get(double k) {
/*  753 */     if (Double.doubleToLongBits(k) == 0L) {
/*  754 */       return this.containsNullKey ? this.value[this.n] : this.defRetValue;
/*      */     }
/*  756 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  759 */     if (Double.doubleToLongBits(
/*  760 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  762 */       return this.defRetValue; } 
/*  763 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  764 */       return this.value[pos];
/*      */     }
/*      */     while (true) {
/*  767 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  768 */         return this.defRetValue; 
/*  769 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  770 */         return this.value[pos];
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean containsKey(double k) {
/*  776 */     if (Double.doubleToLongBits(k) == 0L) {
/*  777 */       return this.containsNullKey;
/*      */     }
/*  779 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  782 */     if (Double.doubleToLongBits(
/*  783 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  785 */       return false; } 
/*  786 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  787 */       return true;
/*      */     }
/*      */     while (true) {
/*  790 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  791 */         return false; 
/*  792 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr))
/*  793 */         return true; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean containsValue(short v) {
/*  798 */     short[] value = this.value;
/*  799 */     double[] key = this.key;
/*  800 */     if (this.containsNullKey && value[this.n] == v)
/*  801 */       return true; 
/*  802 */     for (int i = this.n; i-- != 0;) {
/*  803 */       if (Double.doubleToLongBits(key[i]) != 0L && value[i] == v)
/*  804 */         return true; 
/*  805 */     }  return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short getOrDefault(double k, short defaultValue) {
/*  811 */     if (Double.doubleToLongBits(k) == 0L) {
/*  812 */       return this.containsNullKey ? this.value[this.n] : defaultValue;
/*      */     }
/*  814 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  817 */     if (Double.doubleToLongBits(
/*  818 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  820 */       return defaultValue; } 
/*  821 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  822 */       return this.value[pos];
/*      */     }
/*      */     while (true) {
/*  825 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  826 */         return defaultValue; 
/*  827 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/*  828 */         return this.value[pos];
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public short putIfAbsent(double k, short v) {
/*  834 */     int pos = find(k);
/*  835 */     if (pos >= 0)
/*  836 */       return this.value[pos]; 
/*  837 */     insert(-pos - 1, k, v);
/*  838 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(double k, short v) {
/*  844 */     if (Double.doubleToLongBits(k) == 0L) {
/*  845 */       if (this.containsNullKey && v == this.value[this.n]) {
/*  846 */         removeNullEntry();
/*  847 */         return true;
/*      */       } 
/*  849 */       return false;
/*      */     } 
/*      */     
/*  852 */     double[] key = this.key;
/*      */     double curr;
/*      */     int pos;
/*  855 */     if (Double.doubleToLongBits(
/*  856 */         curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask]) == 0L)
/*      */     {
/*  858 */       return false; } 
/*  859 */     if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr) && v == this.value[pos]) {
/*  860 */       removeEntry(pos);
/*  861 */       return true;
/*      */     } 
/*      */     while (true) {
/*  864 */       if (Double.doubleToLongBits(curr = key[pos = pos + 1 & this.mask]) == 0L)
/*  865 */         return false; 
/*  866 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr) && v == this.value[pos]) {
/*  867 */         removeEntry(pos);
/*  868 */         return true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean replace(double k, short oldValue, short v) {
/*  875 */     int pos = find(k);
/*  876 */     if (pos < 0 || oldValue != this.value[pos])
/*  877 */       return false; 
/*  878 */     this.value[pos] = v;
/*  879 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public short replace(double k, short v) {
/*  884 */     int pos = find(k);
/*  885 */     if (pos < 0)
/*  886 */       return this.defRetValue; 
/*  887 */     short oldValue = this.value[pos];
/*  888 */     this.value[pos] = v;
/*  889 */     return oldValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public short computeIfAbsent(double k, DoubleToIntFunction mappingFunction) {
/*  894 */     Objects.requireNonNull(mappingFunction);
/*  895 */     int pos = find(k);
/*  896 */     if (pos >= 0)
/*  897 */       return this.value[pos]; 
/*  898 */     short newValue = SafeMath.safeIntToShort(mappingFunction.applyAsInt(k));
/*  899 */     insert(-pos - 1, k, newValue);
/*  900 */     return newValue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short computeIfAbsentNullable(double k, DoubleFunction<? extends Short> mappingFunction) {
/*  906 */     Objects.requireNonNull(mappingFunction);
/*  907 */     int pos = find(k);
/*  908 */     if (pos >= 0)
/*  909 */       return this.value[pos]; 
/*  910 */     Short newValue = mappingFunction.apply(k);
/*  911 */     if (newValue == null)
/*  912 */       return this.defRetValue; 
/*  913 */     short v = newValue.shortValue();
/*  914 */     insert(-pos - 1, k, v);
/*  915 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short computeIfPresent(double k, BiFunction<? super Double, ? super Short, ? extends Short> remappingFunction) {
/*  921 */     Objects.requireNonNull(remappingFunction);
/*  922 */     int pos = find(k);
/*  923 */     if (pos < 0)
/*  924 */       return this.defRetValue; 
/*  925 */     Short newValue = remappingFunction.apply(Double.valueOf(k), Short.valueOf(this.value[pos]));
/*  926 */     if (newValue == null) {
/*  927 */       if (Double.doubleToLongBits(k) == 0L) {
/*  928 */         removeNullEntry();
/*      */       } else {
/*  930 */         removeEntry(pos);
/*  931 */       }  return this.defRetValue;
/*      */     } 
/*  933 */     this.value[pos] = newValue.shortValue(); return newValue.shortValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short compute(double k, BiFunction<? super Double, ? super Short, ? extends Short> remappingFunction) {
/*  939 */     Objects.requireNonNull(remappingFunction);
/*  940 */     int pos = find(k);
/*  941 */     Short newValue = remappingFunction.apply(Double.valueOf(k), (pos >= 0) ? Short.valueOf(this.value[pos]) : null);
/*  942 */     if (newValue == null) {
/*  943 */       if (pos >= 0)
/*  944 */         if (Double.doubleToLongBits(k) == 0L) {
/*  945 */           removeNullEntry();
/*      */         } else {
/*  947 */           removeEntry(pos);
/*      */         }  
/*  949 */       return this.defRetValue;
/*      */     } 
/*  951 */     short newVal = newValue.shortValue();
/*  952 */     if (pos < 0) {
/*  953 */       insert(-pos - 1, k, newVal);
/*  954 */       return newVal;
/*      */     } 
/*  956 */     this.value[pos] = newVal; return newVal;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short merge(double k, short v, BiFunction<? super Short, ? super Short, ? extends Short> remappingFunction) {
/*  962 */     Objects.requireNonNull(remappingFunction);
/*  963 */     int pos = find(k);
/*  964 */     if (pos < 0) {
/*  965 */       insert(-pos - 1, k, v);
/*  966 */       return v;
/*      */     } 
/*  968 */     Short newValue = remappingFunction.apply(Short.valueOf(this.value[pos]), Short.valueOf(v));
/*  969 */     if (newValue == null) {
/*  970 */       if (Double.doubleToLongBits(k) == 0L) {
/*  971 */         removeNullEntry();
/*      */       } else {
/*  973 */         removeEntry(pos);
/*  974 */       }  return this.defRetValue;
/*      */     } 
/*  976 */     this.value[pos] = newValue.shortValue(); return newValue.shortValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  987 */     if (this.size == 0)
/*      */       return; 
/*  989 */     this.size = 0;
/*  990 */     this.containsNullKey = false;
/*  991 */     Arrays.fill(this.key, 0.0D);
/*  992 */     this.first = this.last = -1;
/*      */   }
/*      */   
/*      */   public int size() {
/*  996 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1000 */     return (this.size == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   final class MapEntry
/*      */     implements Double2ShortMap.Entry, Map.Entry<Double, Short>
/*      */   {
/*      */     int index;
/*      */ 
/*      */     
/*      */     MapEntry(int index) {
/* 1012 */       this.index = index;
/*      */     }
/*      */     
/*      */     MapEntry() {}
/*      */     
/*      */     public double getDoubleKey() {
/* 1018 */       return Double2ShortLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     
/*      */     public short getShortValue() {
/* 1022 */       return Double2ShortLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     
/*      */     public short setValue(short v) {
/* 1026 */       short oldValue = Double2ShortLinkedOpenHashMap.this.value[this.index];
/* 1027 */       Double2ShortLinkedOpenHashMap.this.value[this.index] = v;
/* 1028 */       return oldValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public Double getKey() {
/* 1038 */       return Double.valueOf(Double2ShortLinkedOpenHashMap.this.key[this.index]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public Short getValue() {
/* 1048 */       return Short.valueOf(Double2ShortLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public Short setValue(Short v) {
/* 1058 */       return Short.valueOf(setValue(v.shortValue()));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object o) {
/* 1063 */       if (!(o instanceof Map.Entry))
/* 1064 */         return false; 
/* 1065 */       Map.Entry<Double, Short> e = (Map.Entry<Double, Short>)o;
/* 1066 */       return (Double.doubleToLongBits(Double2ShortLinkedOpenHashMap.this.key[this.index]) == Double.doubleToLongBits(((Double)e.getKey()).doubleValue()) && Double2ShortLinkedOpenHashMap.this.value[this.index] == ((Short)e
/* 1067 */         .getValue()).shortValue());
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1071 */       return HashCommon.double2int(Double2ShortLinkedOpenHashMap.this.key[this.index]) ^ Double2ShortLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1075 */       return Double2ShortLinkedOpenHashMap.this.key[this.index] + "=>" + Double2ShortLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fixPointers(int i) {
/* 1086 */     if (this.size == 0) {
/* 1087 */       this.first = this.last = -1;
/*      */       return;
/*      */     } 
/* 1090 */     if (this.first == i) {
/* 1091 */       this.first = (int)this.link[i];
/* 1092 */       if (0 <= this.first)
/*      */       {
/* 1094 */         this.link[this.first] = this.link[this.first] | 0xFFFFFFFF00000000L;
/*      */       }
/*      */       return;
/*      */     } 
/* 1098 */     if (this.last == i) {
/* 1099 */       this.last = (int)(this.link[i] >>> 32L);
/* 1100 */       if (0 <= this.last)
/*      */       {
/* 1102 */         this.link[this.last] = this.link[this.last] | 0xFFFFFFFFL;
/*      */       }
/*      */       return;
/*      */     } 
/* 1106 */     long linki = this.link[i];
/* 1107 */     int prev = (int)(linki >>> 32L);
/* 1108 */     int next = (int)linki;
/* 1109 */     this.link[prev] = this.link[prev] ^ (this.link[prev] ^ linki & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/* 1110 */     this.link[next] = this.link[next] ^ (this.link[next] ^ linki & 0xFFFFFFFF00000000L) & 0xFFFFFFFF00000000L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fixPointers(int s, int d) {
/* 1123 */     if (this.size == 1) {
/* 1124 */       this.first = this.last = d;
/*      */       
/* 1126 */       this.link[d] = -1L;
/*      */       return;
/*      */     } 
/* 1129 */     if (this.first == s) {
/* 1130 */       this.first = d;
/* 1131 */       this.link[(int)this.link[s]] = this.link[(int)this.link[s]] ^ (this.link[(int)this.link[s]] ^ (d & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/* 1132 */       this.link[d] = this.link[s];
/*      */       return;
/*      */     } 
/* 1135 */     if (this.last == s) {
/* 1136 */       this.last = d;
/* 1137 */       this.link[(int)(this.link[s] >>> 32L)] = this.link[(int)(this.link[s] >>> 32L)] ^ (this.link[(int)(this.link[s] >>> 32L)] ^ d & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/* 1138 */       this.link[d] = this.link[s];
/*      */       return;
/*      */     } 
/* 1141 */     long links = this.link[s];
/* 1142 */     int prev = (int)(links >>> 32L);
/* 1143 */     int next = (int)links;
/* 1144 */     this.link[prev] = this.link[prev] ^ (this.link[prev] ^ d & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/* 1145 */     this.link[next] = this.link[next] ^ (this.link[next] ^ (d & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/* 1146 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double firstDoubleKey() {
/* 1155 */     if (this.size == 0)
/* 1156 */       throw new NoSuchElementException(); 
/* 1157 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double lastDoubleKey() {
/* 1166 */     if (this.size == 0)
/* 1167 */       throw new NoSuchElementException(); 
/* 1168 */     return this.key[this.last];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortSortedMap tailMap(double from) {
/* 1177 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortSortedMap headMap(double to) {
/* 1186 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortSortedMap subMap(double from, double to) {
/* 1195 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DoubleComparator comparator() {
/* 1204 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class MapIterator
/*      */   {
/* 1219 */     int prev = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1225 */     int next = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1230 */     int curr = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1236 */     int index = -1;
/*      */     protected MapIterator() {
/* 1238 */       this.next = Double2ShortLinkedOpenHashMap.this.first;
/* 1239 */       this.index = 0;
/*      */     }
/*      */     private MapIterator(double from) {
/* 1242 */       if (Double.doubleToLongBits(from) == 0L) {
/* 1243 */         if (Double2ShortLinkedOpenHashMap.this.containsNullKey) {
/* 1244 */           this.next = (int)Double2ShortLinkedOpenHashMap.this.link[Double2ShortLinkedOpenHashMap.this.n];
/* 1245 */           this.prev = Double2ShortLinkedOpenHashMap.this.n;
/*      */           return;
/*      */         } 
/* 1248 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       } 
/* 1250 */       if (Double.doubleToLongBits(Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.last]) == Double.doubleToLongBits(from)) {
/* 1251 */         this.prev = Double2ShortLinkedOpenHashMap.this.last;
/* 1252 */         this.index = Double2ShortLinkedOpenHashMap.this.size;
/*      */         
/*      */         return;
/*      */       } 
/* 1256 */       int pos = (int)HashCommon.mix(Double.doubleToRawLongBits(from)) & Double2ShortLinkedOpenHashMap.this.mask;
/*      */       
/* 1258 */       while (Double.doubleToLongBits(Double2ShortLinkedOpenHashMap.this.key[pos]) != 0L) {
/* 1259 */         if (Double.doubleToLongBits(Double2ShortLinkedOpenHashMap.this.key[pos]) == Double.doubleToLongBits(from)) {
/*      */           
/* 1261 */           this.next = (int)Double2ShortLinkedOpenHashMap.this.link[pos];
/* 1262 */           this.prev = pos;
/*      */           return;
/*      */         } 
/* 1265 */         pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask;
/*      */       } 
/* 1267 */       throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */     }
/*      */     public boolean hasNext() {
/* 1270 */       return (this.next != -1);
/*      */     }
/*      */     public boolean hasPrevious() {
/* 1273 */       return (this.prev != -1);
/*      */     }
/*      */     private final void ensureIndexKnown() {
/* 1276 */       if (this.index >= 0)
/*      */         return; 
/* 1278 */       if (this.prev == -1) {
/* 1279 */         this.index = 0;
/*      */         return;
/*      */       } 
/* 1282 */       if (this.next == -1) {
/* 1283 */         this.index = Double2ShortLinkedOpenHashMap.this.size;
/*      */         return;
/*      */       } 
/* 1286 */       int pos = Double2ShortLinkedOpenHashMap.this.first;
/* 1287 */       this.index = 1;
/* 1288 */       while (pos != this.prev) {
/* 1289 */         pos = (int)Double2ShortLinkedOpenHashMap.this.link[pos];
/* 1290 */         this.index++;
/*      */       } 
/*      */     }
/*      */     public int nextIndex() {
/* 1294 */       ensureIndexKnown();
/* 1295 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/* 1298 */       ensureIndexKnown();
/* 1299 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/* 1302 */       if (!hasNext())
/* 1303 */         throw new NoSuchElementException(); 
/* 1304 */       this.curr = this.next;
/* 1305 */       this.next = (int)Double2ShortLinkedOpenHashMap.this.link[this.curr];
/* 1306 */       this.prev = this.curr;
/* 1307 */       if (this.index >= 0)
/* 1308 */         this.index++; 
/* 1309 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/* 1312 */       if (!hasPrevious())
/* 1313 */         throw new NoSuchElementException(); 
/* 1314 */       this.curr = this.prev;
/* 1315 */       this.prev = (int)(Double2ShortLinkedOpenHashMap.this.link[this.curr] >>> 32L);
/* 1316 */       this.next = this.curr;
/* 1317 */       if (this.index >= 0)
/* 1318 */         this.index--; 
/* 1319 */       return this.curr;
/*      */     }
/*      */     public void remove() {
/* 1322 */       ensureIndexKnown();
/* 1323 */       if (this.curr == -1)
/* 1324 */         throw new IllegalStateException(); 
/* 1325 */       if (this.curr == this.prev) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1330 */         this.index--;
/* 1331 */         this.prev = (int)(Double2ShortLinkedOpenHashMap.this.link[this.curr] >>> 32L);
/*      */       } else {
/* 1333 */         this.next = (int)Double2ShortLinkedOpenHashMap.this.link[this.curr];
/* 1334 */       }  Double2ShortLinkedOpenHashMap.this.size--;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1339 */       if (this.prev == -1) {
/* 1340 */         Double2ShortLinkedOpenHashMap.this.first = this.next;
/*      */       } else {
/* 1342 */         Double2ShortLinkedOpenHashMap.this.link[this.prev] = Double2ShortLinkedOpenHashMap.this.link[this.prev] ^ (Double2ShortLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/* 1343 */       }  if (this.next == -1) {
/* 1344 */         Double2ShortLinkedOpenHashMap.this.last = this.prev;
/*      */       } else {
/* 1346 */         Double2ShortLinkedOpenHashMap.this.link[this.next] = Double2ShortLinkedOpenHashMap.this.link[this.next] ^ (Double2ShortLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/* 1347 */       }  int pos = this.curr;
/* 1348 */       this.curr = -1;
/* 1349 */       if (pos == Double2ShortLinkedOpenHashMap.this.n) {
/* 1350 */         Double2ShortLinkedOpenHashMap.this.containsNullKey = false;
/*      */       } else {
/*      */         
/* 1353 */         double[] key = Double2ShortLinkedOpenHashMap.this.key;
/*      */         while (true) {
/*      */           double curr;
/*      */           int last;
/* 1357 */           pos = (last = pos) + 1 & Double2ShortLinkedOpenHashMap.this.mask;
/*      */           while (true) {
/* 1359 */             if (Double.doubleToLongBits(curr = key[pos]) == 0L) {
/* 1360 */               key[last] = 0.0D;
/*      */               return;
/*      */             } 
/* 1363 */             int slot = (int)HashCommon.mix(Double.doubleToRawLongBits(curr)) & Double2ShortLinkedOpenHashMap.this.mask;
/* 1364 */             if ((last <= pos) ? (last >= slot || slot > pos) : (last >= slot && slot > pos))
/*      */               break; 
/* 1366 */             pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask;
/*      */           } 
/* 1368 */           key[last] = curr;
/* 1369 */           Double2ShortLinkedOpenHashMap.this.value[last] = Double2ShortLinkedOpenHashMap.this.value[pos];
/* 1370 */           if (this.next == pos)
/* 1371 */             this.next = last; 
/* 1372 */           if (this.prev == pos)
/* 1373 */             this.prev = last; 
/* 1374 */           Double2ShortLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     public int skip(int n) {
/* 1379 */       int i = n;
/* 1380 */       while (i-- != 0 && hasNext())
/* 1381 */         nextEntry(); 
/* 1382 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/* 1385 */       int i = n;
/* 1386 */       while (i-- != 0 && hasPrevious())
/* 1387 */         previousEntry(); 
/* 1388 */       return n - i - 1;
/*      */     }
/*      */     public void set(Double2ShortMap.Entry ok) {
/* 1391 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     public void add(Double2ShortMap.Entry ok) {
/* 1394 */       throw new UnsupportedOperationException();
/*      */     } }
/*      */   
/*      */   private class EntryIterator extends MapIterator implements ObjectListIterator<Double2ShortMap.Entry> { private Double2ShortLinkedOpenHashMap.MapEntry entry;
/*      */     
/*      */     public EntryIterator() {}
/*      */     
/*      */     public EntryIterator(double from) {
/* 1402 */       super(from);
/*      */     }
/*      */     
/*      */     public Double2ShortLinkedOpenHashMap.MapEntry next() {
/* 1406 */       return this.entry = new Double2ShortLinkedOpenHashMap.MapEntry(nextEntry());
/*      */     }
/*      */     
/*      */     public Double2ShortLinkedOpenHashMap.MapEntry previous() {
/* 1410 */       return this.entry = new Double2ShortLinkedOpenHashMap.MapEntry(previousEntry());
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1414 */       super.remove();
/* 1415 */       this.entry.index = -1;
/*      */     } }
/*      */ 
/*      */   
/* 1419 */   private class FastEntryIterator extends MapIterator implements ObjectListIterator<Double2ShortMap.Entry> { final Double2ShortLinkedOpenHashMap.MapEntry entry = new Double2ShortLinkedOpenHashMap.MapEntry();
/*      */ 
/*      */     
/*      */     public FastEntryIterator(double from) {
/* 1423 */       super(from);
/*      */     }
/*      */     
/*      */     public Double2ShortLinkedOpenHashMap.MapEntry next() {
/* 1427 */       this.entry.index = nextEntry();
/* 1428 */       return this.entry;
/*      */     }
/*      */     
/*      */     public Double2ShortLinkedOpenHashMap.MapEntry previous() {
/* 1432 */       this.entry.index = previousEntry();
/* 1433 */       return this.entry;
/*      */     }
/*      */     
/*      */     public FastEntryIterator() {} }
/*      */   
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Double2ShortMap.Entry> implements Double2ShortSortedMap.FastSortedEntrySet { private MapEntrySet() {}
/*      */     
/*      */     public ObjectBidirectionalIterator<Double2ShortMap.Entry> iterator() {
/* 1441 */       return (ObjectBidirectionalIterator<Double2ShortMap.Entry>)new Double2ShortLinkedOpenHashMap.EntryIterator();
/*      */     }
/*      */     
/*      */     public Comparator<? super Double2ShortMap.Entry> comparator() {
/* 1445 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public ObjectSortedSet<Double2ShortMap.Entry> subSet(Double2ShortMap.Entry fromElement, Double2ShortMap.Entry toElement) {
/* 1450 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public ObjectSortedSet<Double2ShortMap.Entry> headSet(Double2ShortMap.Entry toElement) {
/* 1454 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public ObjectSortedSet<Double2ShortMap.Entry> tailSet(Double2ShortMap.Entry fromElement) {
/* 1458 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public Double2ShortMap.Entry first() {
/* 1462 */       if (Double2ShortLinkedOpenHashMap.this.size == 0)
/* 1463 */         throw new NoSuchElementException(); 
/* 1464 */       return new Double2ShortLinkedOpenHashMap.MapEntry(Double2ShortLinkedOpenHashMap.this.first);
/*      */     }
/*      */     
/*      */     public Double2ShortMap.Entry last() {
/* 1468 */       if (Double2ShortLinkedOpenHashMap.this.size == 0)
/* 1469 */         throw new NoSuchElementException(); 
/* 1470 */       return new Double2ShortLinkedOpenHashMap.MapEntry(Double2ShortLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean contains(Object o) {
/* 1475 */       if (!(o instanceof Map.Entry))
/* 1476 */         return false; 
/* 1477 */       Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
/* 1478 */       if (e.getKey() == null || !(e.getKey() instanceof Double))
/* 1479 */         return false; 
/* 1480 */       if (e.getValue() == null || !(e.getValue() instanceof Short))
/* 1481 */         return false; 
/* 1482 */       double k = ((Double)e.getKey()).doubleValue();
/* 1483 */       short v = ((Short)e.getValue()).shortValue();
/* 1484 */       if (Double.doubleToLongBits(k) == 0L) {
/* 1485 */         return (Double2ShortLinkedOpenHashMap.this.containsNullKey && Double2ShortLinkedOpenHashMap.this.value[Double2ShortLinkedOpenHashMap.this.n] == v);
/*      */       }
/* 1487 */       double[] key = Double2ShortLinkedOpenHashMap.this.key;
/*      */       double curr;
/*      */       int pos;
/* 1490 */       if (Double.doubleToLongBits(
/* 1491 */           curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & Double2ShortLinkedOpenHashMap.this.mask]) == 0L)
/*      */       {
/* 1493 */         return false; } 
/* 1494 */       if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/* 1495 */         return (Double2ShortLinkedOpenHashMap.this.value[pos] == v);
/*      */       }
/*      */       while (true) {
/* 1498 */         if (Double.doubleToLongBits(curr = key[pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask]) == 0L)
/* 1499 */           return false; 
/* 1500 */         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(curr)) {
/* 1501 */           return (Double2ShortLinkedOpenHashMap.this.value[pos] == v);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 1507 */       if (!(o instanceof Map.Entry))
/* 1508 */         return false; 
/* 1509 */       Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
/* 1510 */       if (e.getKey() == null || !(e.getKey() instanceof Double))
/* 1511 */         return false; 
/* 1512 */       if (e.getValue() == null || !(e.getValue() instanceof Short))
/* 1513 */         return false; 
/* 1514 */       double k = ((Double)e.getKey()).doubleValue();
/* 1515 */       short v = ((Short)e.getValue()).shortValue();
/* 1516 */       if (Double.doubleToLongBits(k) == 0L) {
/* 1517 */         if (Double2ShortLinkedOpenHashMap.this.containsNullKey && Double2ShortLinkedOpenHashMap.this.value[Double2ShortLinkedOpenHashMap.this.n] == v) {
/* 1518 */           Double2ShortLinkedOpenHashMap.this.removeNullEntry();
/* 1519 */           return true;
/*      */         } 
/* 1521 */         return false;
/*      */       } 
/*      */       
/* 1524 */       double[] key = Double2ShortLinkedOpenHashMap.this.key;
/*      */       double curr;
/*      */       int pos;
/* 1527 */       if (Double.doubleToLongBits(
/* 1528 */           curr = key[pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & Double2ShortLinkedOpenHashMap.this.mask]) == 0L)
/*      */       {
/* 1530 */         return false; } 
/* 1531 */       if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k)) {
/* 1532 */         if (Double2ShortLinkedOpenHashMap.this.value[pos] == v) {
/* 1533 */           Double2ShortLinkedOpenHashMap.this.removeEntry(pos);
/* 1534 */           return true;
/*      */         } 
/* 1536 */         return false;
/*      */       } 
/*      */       while (true) {
/* 1539 */         if (Double.doubleToLongBits(curr = key[pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask]) == 0L)
/* 1540 */           return false; 
/* 1541 */         if (Double.doubleToLongBits(curr) == Double.doubleToLongBits(k) && 
/* 1542 */           Double2ShortLinkedOpenHashMap.this.value[pos] == v) {
/* 1543 */           Double2ShortLinkedOpenHashMap.this.removeEntry(pos);
/* 1544 */           return true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public int size() {
/* 1551 */       return Double2ShortLinkedOpenHashMap.this.size;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1555 */       Double2ShortLinkedOpenHashMap.this.clear();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ObjectListIterator<Double2ShortMap.Entry> iterator(Double2ShortMap.Entry from) {
/* 1570 */       return new Double2ShortLinkedOpenHashMap.EntryIterator(from.getDoubleKey());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ObjectListIterator<Double2ShortMap.Entry> fastIterator() {
/* 1581 */       return new Double2ShortLinkedOpenHashMap.FastEntryIterator();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ObjectListIterator<Double2ShortMap.Entry> fastIterator(Double2ShortMap.Entry from) {
/* 1596 */       return new Double2ShortLinkedOpenHashMap.FastEntryIterator(from.getDoubleKey());
/*      */     }
/*      */ 
/*      */     
/*      */     public void forEach(Consumer<? super Double2ShortMap.Entry> consumer) {
/* 1601 */       for (int i = Double2ShortLinkedOpenHashMap.this.size, next = Double2ShortLinkedOpenHashMap.this.first; i-- != 0; ) {
/* 1602 */         int curr = next;
/* 1603 */         next = (int)Double2ShortLinkedOpenHashMap.this.link[curr];
/* 1604 */         consumer.accept(new AbstractDouble2ShortMap.BasicEntry(Double2ShortLinkedOpenHashMap.this.key[curr], Double2ShortLinkedOpenHashMap.this.value[curr]));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void fastForEach(Consumer<? super Double2ShortMap.Entry> consumer) {
/* 1610 */       AbstractDouble2ShortMap.BasicEntry entry = new AbstractDouble2ShortMap.BasicEntry();
/* 1611 */       for (int i = Double2ShortLinkedOpenHashMap.this.size, next = Double2ShortLinkedOpenHashMap.this.first; i-- != 0; ) {
/* 1612 */         int curr = next;
/* 1613 */         next = (int)Double2ShortLinkedOpenHashMap.this.link[curr];
/* 1614 */         entry.key = Double2ShortLinkedOpenHashMap.this.key[curr];
/* 1615 */         entry.value = Double2ShortLinkedOpenHashMap.this.value[curr];
/* 1616 */         consumer.accept(entry);
/*      */       } 
/*      */     } }
/*      */ 
/*      */   
/*      */   public Double2ShortSortedMap.FastSortedEntrySet double2ShortEntrySet() {
/* 1622 */     if (this.entries == null)
/* 1623 */       this.entries = new MapEntrySet(); 
/* 1624 */     return this.entries;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final class KeyIterator
/*      */     extends MapIterator
/*      */     implements DoubleListIterator
/*      */   {
/*      */     public KeyIterator(double k) {
/* 1637 */       super(k);
/*      */     }
/*      */     
/*      */     public double previousDouble() {
/* 1641 */       return Double2ShortLinkedOpenHashMap.this.key[previousEntry()];
/*      */     }
/*      */ 
/*      */     
/*      */     public KeyIterator() {}
/*      */     
/*      */     public double nextDouble() {
/* 1648 */       return Double2ShortLinkedOpenHashMap.this.key[nextEntry()];
/*      */     } }
/*      */   
/*      */   private final class KeySet extends AbstractDoubleSortedSet { private KeySet() {}
/*      */     
/*      */     public DoubleListIterator iterator(double from) {
/* 1654 */       return new Double2ShortLinkedOpenHashMap.KeyIterator(from);
/*      */     }
/*      */     
/*      */     public DoubleListIterator iterator() {
/* 1658 */       return new Double2ShortLinkedOpenHashMap.KeyIterator();
/*      */     }
/*      */ 
/*      */     
/*      */     public void forEach(DoubleConsumer consumer) {
/* 1663 */       if (Double2ShortLinkedOpenHashMap.this.containsNullKey)
/* 1664 */         consumer.accept(Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.n]); 
/* 1665 */       for (int pos = Double2ShortLinkedOpenHashMap.this.n; pos-- != 0; ) {
/* 1666 */         double k = Double2ShortLinkedOpenHashMap.this.key[pos];
/* 1667 */         if (Double.doubleToLongBits(k) != 0L)
/* 1668 */           consumer.accept(k); 
/*      */       } 
/*      */     }
/*      */     
/*      */     public int size() {
/* 1673 */       return Double2ShortLinkedOpenHashMap.this.size;
/*      */     }
/*      */     
/*      */     public boolean contains(double k) {
/* 1677 */       return Double2ShortLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     
/*      */     public boolean remove(double k) {
/* 1681 */       int oldSize = Double2ShortLinkedOpenHashMap.this.size;
/* 1682 */       Double2ShortLinkedOpenHashMap.this.remove(k);
/* 1683 */       return (Double2ShortLinkedOpenHashMap.this.size != oldSize);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1687 */       Double2ShortLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public double firstDouble() {
/* 1691 */       if (Double2ShortLinkedOpenHashMap.this.size == 0)
/* 1692 */         throw new NoSuchElementException(); 
/* 1693 */       return Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.first];
/*      */     }
/*      */     
/*      */     public double lastDouble() {
/* 1697 */       if (Double2ShortLinkedOpenHashMap.this.size == 0)
/* 1698 */         throw new NoSuchElementException(); 
/* 1699 */       return Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.last];
/*      */     }
/*      */     
/*      */     public DoubleComparator comparator() {
/* 1703 */       return null;
/*      */     }
/*      */     
/*      */     public DoubleSortedSet tailSet(double from) {
/* 1707 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public DoubleSortedSet headSet(double to) {
/* 1711 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public DoubleSortedSet subSet(double from, double to) {
/* 1715 */       throw new UnsupportedOperationException();
/*      */     } }
/*      */ 
/*      */   
/*      */   public DoubleSortedSet keySet() {
/* 1720 */     if (this.keys == null)
/* 1721 */       this.keys = new KeySet(); 
/* 1722 */     return this.keys;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final class ValueIterator
/*      */     extends MapIterator
/*      */     implements ShortListIterator
/*      */   {
/*      */     public short previousShort() {
/* 1736 */       return Double2ShortLinkedOpenHashMap.this.value[previousEntry()];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short nextShort() {
/* 1743 */       return Double2ShortLinkedOpenHashMap.this.value[nextEntry()];
/*      */     }
/*      */   }
/*      */   
/*      */   public ShortCollection values() {
/* 1748 */     if (this.values == null)
/* 1749 */       this.values = (ShortCollection)new AbstractShortCollection()
/*      */         {
/*      */           public ShortIterator iterator() {
/* 1752 */             return (ShortIterator)new Double2ShortLinkedOpenHashMap.ValueIterator();
/*      */           }
/*      */           
/*      */           public int size() {
/* 1756 */             return Double2ShortLinkedOpenHashMap.this.size;
/*      */           }
/*      */           
/*      */           public boolean contains(short v) {
/* 1760 */             return Double2ShortLinkedOpenHashMap.this.containsValue(v);
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1764 */             Double2ShortLinkedOpenHashMap.this.clear();
/*      */           }
/*      */ 
/*      */           
/*      */           public void forEach(IntConsumer consumer) {
/* 1769 */             if (Double2ShortLinkedOpenHashMap.this.containsNullKey)
/* 1770 */               consumer.accept(Double2ShortLinkedOpenHashMap.this.value[Double2ShortLinkedOpenHashMap.this.n]); 
/* 1771 */             for (int pos = Double2ShortLinkedOpenHashMap.this.n; pos-- != 0;) {
/* 1772 */               if (Double.doubleToLongBits(Double2ShortLinkedOpenHashMap.this.key[pos]) != 0L)
/* 1773 */                 consumer.accept(Double2ShortLinkedOpenHashMap.this.value[pos]); 
/*      */             }  }
/*      */         }; 
/* 1776 */     return this.values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean trim() {
/* 1793 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1794 */     if (l >= this.n || this.size > HashCommon.maxFill(l, this.f))
/* 1795 */       return true; 
/*      */     try {
/* 1797 */       rehash(l);
/* 1798 */     } catch (OutOfMemoryError cantDoIt) {
/* 1799 */       return false;
/*      */     } 
/* 1801 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean trim(int n) {
/* 1825 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil((n / this.f)));
/* 1826 */     if (l >= n || this.size > HashCommon.maxFill(l, this.f))
/* 1827 */       return true; 
/*      */     try {
/* 1829 */       rehash(l);
/* 1830 */     } catch (OutOfMemoryError cantDoIt) {
/* 1831 */       return false;
/*      */     } 
/* 1833 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void rehash(int newN) {
/* 1849 */     double[] key = this.key;
/* 1850 */     short[] value = this.value;
/* 1851 */     int mask = newN - 1;
/* 1852 */     double[] newKey = new double[newN + 1];
/* 1853 */     short[] newValue = new short[newN + 1];
/* 1854 */     int i = this.first, prev = -1, newPrev = -1;
/* 1855 */     long[] link = this.link;
/* 1856 */     long[] newLink = new long[newN + 1];
/* 1857 */     this.first = -1;
/* 1858 */     for (int j = this.size; j-- != 0; ) {
/* 1859 */       int pos; if (Double.doubleToLongBits(key[i]) == 0L) {
/* 1860 */         pos = newN;
/*      */       } else {
/* 1862 */         pos = (int)HashCommon.mix(Double.doubleToRawLongBits(key[i])) & mask;
/* 1863 */         while (Double.doubleToLongBits(newKey[pos]) != 0L)
/* 1864 */           pos = pos + 1 & mask; 
/*      */       } 
/* 1866 */       newKey[pos] = key[i];
/* 1867 */       newValue[pos] = value[i];
/* 1868 */       if (prev != -1) {
/* 1869 */         newLink[newPrev] = newLink[newPrev] ^ (newLink[newPrev] ^ pos & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/* 1870 */         newLink[pos] = newLink[pos] ^ (newLink[pos] ^ (newPrev & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/* 1871 */         newPrev = pos;
/*      */       } else {
/* 1873 */         newPrev = this.first = pos;
/*      */         
/* 1875 */         newLink[pos] = -1L;
/*      */       } 
/* 1877 */       int t = i;
/* 1878 */       i = (int)link[i];
/* 1879 */       prev = t;
/*      */     } 
/* 1881 */     this.link = newLink;
/* 1882 */     this.last = newPrev;
/* 1883 */     if (newPrev != -1)
/*      */     {
/* 1885 */       newLink[newPrev] = newLink[newPrev] | 0xFFFFFFFFL; } 
/* 1886 */     this.n = newN;
/* 1887 */     this.mask = mask;
/* 1888 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1889 */     this.key = newKey;
/* 1890 */     this.value = newValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double2ShortLinkedOpenHashMap clone() {
/*      */     Double2ShortLinkedOpenHashMap c;
/*      */     try {
/* 1907 */       c = (Double2ShortLinkedOpenHashMap)super.clone();
/* 1908 */     } catch (CloneNotSupportedException cantHappen) {
/* 1909 */       throw new InternalError();
/*      */     } 
/* 1911 */     c.keys = null;
/* 1912 */     c.values = null;
/* 1913 */     c.entries = null;
/* 1914 */     c.containsNullKey = this.containsNullKey;
/* 1915 */     c.key = (double[])this.key.clone();
/* 1916 */     c.value = (short[])this.value.clone();
/* 1917 */     c.link = (long[])this.link.clone();
/* 1918 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1931 */     int h = 0;
/* 1932 */     for (int j = realSize(), i = 0, t = 0; j-- != 0; ) {
/* 1933 */       while (Double.doubleToLongBits(this.key[i]) == 0L)
/* 1934 */         i++; 
/* 1935 */       t = HashCommon.double2int(this.key[i]);
/* 1936 */       t ^= this.value[i];
/* 1937 */       h += t;
/* 1938 */       i++;
/*      */     } 
/*      */     
/* 1941 */     if (this.containsNullKey)
/* 1942 */       h += this.value[this.n]; 
/* 1943 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1946 */     double[] key = this.key;
/* 1947 */     short[] value = this.value;
/* 1948 */     MapIterator i = new MapIterator();
/* 1949 */     s.defaultWriteObject();
/* 1950 */     for (int j = this.size; j-- != 0; ) {
/* 1951 */       int e = i.nextEntry();
/* 1952 */       s.writeDouble(key[e]);
/* 1953 */       s.writeShort(value[e]);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1958 */     s.defaultReadObject();
/* 1959 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1960 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1961 */     this.mask = this.n - 1;
/* 1962 */     double[] key = this.key = new double[this.n + 1];
/* 1963 */     short[] value = this.value = new short[this.n + 1];
/* 1964 */     long[] link = this.link = new long[this.n + 1];
/* 1965 */     int prev = -1;
/* 1966 */     this.first = this.last = -1;
/*      */ 
/*      */     
/* 1969 */     for (int i = this.size; i-- != 0; ) {
/* 1970 */       int pos; double k = s.readDouble();
/* 1971 */       short v = s.readShort();
/* 1972 */       if (Double.doubleToLongBits(k) == 0L) {
/* 1973 */         pos = this.n;
/* 1974 */         this.containsNullKey = true;
/*      */       } else {
/* 1976 */         pos = (int)HashCommon.mix(Double.doubleToRawLongBits(k)) & this.mask;
/* 1977 */         while (Double.doubleToLongBits(key[pos]) != 0L)
/* 1978 */           pos = pos + 1 & this.mask; 
/*      */       } 
/* 1980 */       key[pos] = k;
/* 1981 */       value[pos] = v;
/* 1982 */       if (this.first != -1) {
/* 1983 */         link[prev] = link[prev] ^ (link[prev] ^ pos & 0xFFFFFFFFL) & 0xFFFFFFFFL;
/* 1984 */         link[pos] = link[pos] ^ (link[pos] ^ (prev & 0xFFFFFFFFL) << 32L) & 0xFFFFFFFF00000000L;
/* 1985 */         prev = pos; continue;
/*      */       } 
/* 1987 */       prev = this.first = pos;
/*      */       
/* 1989 */       link[pos] = link[pos] | 0xFFFFFFFF00000000L;
/*      */     } 
/*      */     
/* 1992 */     this.last = prev;
/* 1993 */     if (prev != -1)
/*      */     {
/* 1995 */       link[prev] = link[prev] | 0xFFFFFFFFL;
/*      */     }
/*      */   }
/*      */   
/*      */   private void checkTable() {}
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\i\\unimi\dsi\fastutil\doubles\Double2ShortLinkedOpenHashMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */