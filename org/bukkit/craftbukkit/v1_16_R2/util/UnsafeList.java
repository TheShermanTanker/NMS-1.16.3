/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Arrays;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public class UnsafeList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 8683452581112892191L;
/*     */   private transient Object[] data;
/*     */   
/*     */   public final Object[] getRawDataArray() {
/*  20 */     return this.data;
/*     */   }
/*     */   
/*     */   private int size;
/*  24 */   private Iterator[] iterPool = new Iterator[1]; private int initialCapacity;
/*     */   private int maxPool;
/*     */   private int poolCounter;
/*     */   protected transient int maxSize;
/*     */   
/*     */   public UnsafeList(int capacity, int maxIterPool) {
/*  30 */     if (capacity < 0) capacity = 32; 
/*  31 */     int rounded = Integer.highestOneBit(capacity - 1) << 1;
/*  32 */     this.data = new Object[rounded];
/*  33 */     this.initialCapacity = rounded;
/*  34 */     this.maxPool = maxIterPool;
/*  35 */     this.iterPool[0] = new Itr();
/*     */   }
/*     */   
/*     */   public UnsafeList(int capacity) {
/*  39 */     this(capacity, 5);
/*     */   }
/*     */   
/*     */   public UnsafeList() {
/*  43 */     this(32);
/*     */   }
/*     */ 
/*     */   
/*     */   public E get(int index) {
/*  48 */     rangeCheck(index);
/*     */     
/*  50 */     return (E)this.data[index];
/*     */   }
/*     */   
/*     */   public E unsafeGet(int index) {
/*  54 */     return (E)this.data[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public E set(int index, E element) {
/*  59 */     rangeCheck(index);
/*     */     
/*  61 */     E old = (E)this.data[index];
/*  62 */     this.data[index] = element;
/*  63 */     return old;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(E element) {
/*  68 */     growIfNeeded();
/*  69 */     this.data[this.size++] = element;
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, E element) {
/*  75 */     growIfNeeded();
/*  76 */     System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
/*  77 */     this.data[index] = element;
/*  78 */     this.size++;
/*     */   }
/*     */ 
/*     */   
/*     */   public E remove(int index) {
/*  83 */     rangeCheck(index);
/*     */     
/*  85 */     E old = (E)this.data[index];
/*  86 */     int movedCount = this.size - index - 1;
/*  87 */     if (movedCount > 0) {
/*  88 */       System.arraycopy(this.data, index + 1, this.data, index, movedCount);
/*     */     }
/*  90 */     this.data[--this.size] = null;
/*     */     
/*  92 */     return old;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*  97 */     int index = indexOf(o);
/*  98 */     if (index >= 0) {
/*  99 */       remove(index);
/* 100 */       return true;
/*     */     } 
/*     */     
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object o) {
/* 108 */     for (int i = 0; i < this.size; i++) {
/* 109 */       if (o == this.data[i] || o.equals(this.data[i])) {
/* 110 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 114 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/* 119 */     return (indexOf(o) >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int size) {
/* 125 */     if (this.maxSize < this.size) {
/* 126 */       this.maxSize = this.size;
/*     */     }
/* 128 */     this.size = size;
/*     */   }
/*     */   
/*     */   public void completeReset() {
/* 132 */     if (this.data != null) {
/* 133 */       Arrays.fill(this.data, 0, Math.max(this.size, this.maxSize), (Object)null);
/*     */     }
/* 135 */     this.size = 0;
/* 136 */     this.maxSize = 0;
/* 137 */     if (this.iterPool != null) {
/* 138 */       for (Iterator temp : this.iterPool) {
/* 139 */         if (temp != null)
/*     */         {
/*     */           
/* 142 */           ((Itr)temp).valid = false;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 151 */     this.size = 0;
/*     */ 
/*     */     
/* 154 */     if (this.data.length > this.initialCapacity << 3) {
/* 155 */       this.data = new Object[this.initialCapacity];
/*     */     } else {
/* 157 */       for (int i = 0; i < this.data.length; i++) {
/* 158 */         this.data[i] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trimToSize() {
/* 165 */     int old = this.data.length;
/* 166 */     int rounded = Integer.highestOneBit(this.size - 1) << 1;
/* 167 */     if (rounded < old) {
/* 168 */       this.data = Arrays.copyOf(this.data, rounded);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 174 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 179 */     return (this.size == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 184 */     UnsafeList<E> copy = (UnsafeList<E>)super.clone();
/* 185 */     copy.data = Arrays.copyOf(this.data, this.size);
/* 186 */     copy.size = this.size;
/* 187 */     copy.initialCapacity = this.initialCapacity;
/* 188 */     copy.iterPool = new Iterator[1];
/* 189 */     copy.iterPool[0] = new Itr();
/* 190 */     copy.maxPool = this.maxPool;
/* 191 */     copy.poolCounter = 0;
/* 192 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/* 198 */     for (Iterator iter : this.iterPool) {
/* 199 */       if (!((Itr)iter).valid) {
/* 200 */         Itr iterator = (Itr)iter;
/* 201 */         iterator.reset();
/* 202 */         return iterator;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (this.iterPool.length < this.maxPool) {
/* 208 */       Iterator[] newPool = new Iterator[this.iterPool.length + 1];
/* 209 */       System.arraycopy(this.iterPool, 0, newPool, 0, this.iterPool.length);
/* 210 */       this.iterPool = newPool;
/*     */       
/* 212 */       this.iterPool[this.iterPool.length - 1] = new Itr();
/* 213 */       return this.iterPool[this.iterPool.length - 1];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 218 */     this.poolCounter = ++this.poolCounter % this.iterPool.length;
/* 219 */     this.iterPool[this.poolCounter] = new Itr();
/* 220 */     return this.iterPool[this.poolCounter];
/*     */   }
/*     */   
/*     */   private void rangeCheck(int index) {
/* 224 */     if (index >= this.size || index < 0) {
/* 225 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
/*     */     }
/*     */   }
/*     */   
/*     */   private void growIfNeeded() {
/* 230 */     if (this.size == this.data.length) {
/* 231 */       Object[] newData = new Object[this.data.length << 1];
/* 232 */       System.arraycopy(this.data, 0, newData, 0, this.size);
/* 233 */       this.data = newData;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream os) throws IOException {
/* 238 */     os.defaultWriteObject();
/*     */     
/* 240 */     os.writeInt(this.size);
/* 241 */     os.writeInt(this.initialCapacity);
/* 242 */     for (int i = 0; i < this.size; i++) {
/* 243 */       os.writeObject(this.data[i]);
/*     */     }
/* 245 */     os.writeInt(this.maxPool);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
/* 249 */     is.defaultReadObject();
/*     */     
/* 251 */     this.size = is.readInt();
/* 252 */     this.initialCapacity = is.readInt();
/* 253 */     this.data = new Object[Integer.highestOneBit(this.size - 1) << 1];
/* 254 */     for (int i = 0; i < this.size; i++) {
/* 255 */       this.data[i] = is.readObject();
/*     */     }
/* 257 */     this.maxPool = is.readInt();
/* 258 */     this.iterPool = new Iterator[1];
/* 259 */     this.iterPool[0] = new Itr();
/*     */   }
/*     */   
/*     */   public class Itr implements Iterator<E> {
/*     */     int index;
/* 264 */     int lastRet = -1;
/* 265 */     int expectedModCount = UnsafeList.this.modCount;
/*     */     public boolean valid = true;
/*     */     
/*     */     public void reset() {
/* 269 */       this.index = 0;
/* 270 */       this.lastRet = -1;
/* 271 */       this.expectedModCount = UnsafeList.this.modCount;
/* 272 */       this.valid = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 277 */       this.valid = (this.index != UnsafeList.this.size);
/* 278 */       return this.valid;
/*     */     }
/*     */ 
/*     */     
/*     */     public E next() {
/* 283 */       if (UnsafeList.this.modCount != this.expectedModCount) {
/* 284 */         throw new ConcurrentModificationException();
/*     */       }
/*     */       
/* 287 */       int i = this.index;
/* 288 */       if (i >= UnsafeList.this.size) {
/* 289 */         throw new NoSuchElementException();
/*     */       }
/*     */       
/* 292 */       if (i >= UnsafeList.this.data.length) {
/* 293 */         throw new ConcurrentModificationException();
/*     */       }
/*     */       
/* 296 */       this.index = i + 1;
/* 297 */       return (E)UnsafeList.this.data[this.lastRet = i];
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 302 */       if (this.lastRet < 0) {
/* 303 */         throw new IllegalStateException();
/*     */       }
/*     */       
/* 306 */       if (UnsafeList.this.modCount != this.expectedModCount) {
/* 307 */         throw new ConcurrentModificationException();
/*     */       }
/*     */       
/*     */       try {
/* 311 */         UnsafeList.this.remove(this.lastRet);
/* 312 */         this.index = this.lastRet;
/* 313 */         this.lastRet = -1;
/* 314 */         this.expectedModCount = UnsafeList.this.modCount;
/* 315 */       } catch (IndexOutOfBoundsException ex) {
/* 316 */         throw new ConcurrentModificationException();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\UnsafeList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */