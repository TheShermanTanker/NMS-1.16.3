/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ public class ArraySetSorted<T> extends AbstractSet<T> {
/*     */   private final Comparator<T> a;
/*     */   
/*  13 */   private final T[] getBackingArray() { return this.b; } private T[] b; private int c; private final int getSize() {
/*  14 */     return this.c; } private final void setSize(int value) { this.c = value; }
/*     */   
/*     */   private ArraySetSorted(int i, Comparator<T> comparator) {
/*  17 */     this.a = comparator;
/*  18 */     if (i < 0) {
/*  19 */       throw new IllegalArgumentException("Initial capacity (" + i + ") is negative");
/*     */     }
/*  21 */     this.b = a(new Object[i]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeIf(Predicate<? super T> filter) {
/*  29 */     int i = 0, len = getSize();
/*  30 */     T[] backingArray = getBackingArray();
/*     */     
/*     */     while (true) {
/*  33 */       if (i >= len) {
/*  34 */         return false;
/*     */       }
/*  36 */       if (!filter.test(backingArray[i])) {
/*  37 */         i++;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*     */       break;
/*     */     } 
/*     */     
/*  45 */     int lastIndex = i;
/*     */     
/*  47 */     for (; i < len; i++) {
/*  48 */       T curr = backingArray[i];
/*  49 */       if (!filter.test(curr)) {
/*  50 */         backingArray[lastIndex++] = curr;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  55 */     Arrays.fill((Object[])backingArray, lastIndex, len, (Object)null);
/*  56 */     setSize(lastIndex);
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Comparable<T>> ArraySetSorted<T> a(int i) {
/*  62 */     return new ArraySetSorted<>(i, (Comparator)Comparator.naturalOrder());
/*     */   }
/*     */   
/*     */   private static <T> T[] a(Object[] aobject) {
/*  66 */     return (T[])aobject;
/*     */   }
/*     */   
/*     */   private int c(T t0) {
/*  70 */     return Arrays.binarySearch(this.b, 0, this.c, t0, this.a);
/*     */   }
/*     */   
/*     */   private static int b(int i) {
/*  74 */     return -i - 1;
/*     */   }
/*     */   
/*     */   public boolean add(T t0) {
/*  78 */     int i = c(t0);
/*     */     
/*  80 */     if (i >= 0) {
/*  81 */       return false;
/*     */     }
/*  83 */     int j = b(i);
/*     */     
/*  85 */     a(t0, j);
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(int i) {
/*  91 */     if (i > this.b.length) {
/*  92 */       if (this.b != ObjectArrays.DEFAULT_EMPTY_ARRAY) {
/*  93 */         i = (int)Math.max(Math.min(this.b.length + (this.b.length >> 1), 2147483639L), i);
/*  94 */       } else if (i < 10) {
/*  95 */         i = 10;
/*     */       } 
/*     */       
/*  98 */       Object[] aobject = new Object[i];
/*     */       
/* 100 */       System.arraycopy(this.b, 0, aobject, 0, this.c);
/* 101 */       this.b = a(aobject);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(T t0, int i) {
/* 106 */     c(this.c + 1);
/* 107 */     if (i != this.c) {
/* 108 */       System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
/*     */     }
/*     */     
/* 111 */     this.b[i] = t0;
/* 112 */     this.c++;
/*     */   }
/*     */   
/*     */   private void d(int i) {
/* 116 */     this.c--;
/* 117 */     if (i != this.c) {
/* 118 */       System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
/*     */     }
/*     */     
/* 121 */     this.b[this.c] = null;
/*     */   }
/*     */   
/*     */   private T e(int i) {
/* 125 */     return this.b[i];
/*     */   }
/*     */   
/*     */   public T a(T t0) {
/* 129 */     int i = c(t0);
/*     */     
/* 131 */     if (i >= 0) {
/* 132 */       return e(i);
/*     */     }
/* 134 */     a(t0, b(i));
/* 135 */     return t0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object object) {
/* 140 */     int i = c((T)object);
/*     */     
/* 142 */     if (i >= 0) {
/* 143 */       d(i);
/* 144 */       return true;
/*     */     } 
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public T b() {
/* 151 */     return e(0);
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 155 */     int i = c((T)object);
/*     */     
/* 157 */     return (i >= 0);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 161 */     return new a();
/*     */   }
/*     */   
/*     */   public int size() {
/* 165 */     return this.c;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 169 */     return (Object[])this.b.clone();
/*     */   }
/*     */   
/*     */   public <U> U[] toArray(U[] au) {
/* 173 */     if (au.length < this.c) {
/* 174 */       return Arrays.copyOf(this.b, this.c, (Class)au.getClass());
/*     */     }
/* 176 */     System.arraycopy(this.b, 0, au, 0, this.c);
/* 177 */     if (au.length > this.c) {
/* 178 */       au[this.c] = null;
/*     */     }
/*     */     
/* 181 */     return au;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 186 */     Arrays.fill((Object[])this.b, 0, this.c, (Object)null);
/* 187 */     this.c = 0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 191 */     if (this == object) {
/* 192 */       return true;
/*     */     }
/* 194 */     if (object instanceof ArraySetSorted) {
/* 195 */       ArraySetSorted<?> arraysetsorted = (ArraySetSorted)object;
/*     */       
/* 197 */       if (this.a.equals(arraysetsorted.a)) {
/* 198 */         return (this.c == arraysetsorted.c && Arrays.equals((Object[])this.b, (Object[])arraysetsorted.b));
/*     */       }
/*     */     } 
/*     */     
/* 202 */     return super.equals(object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class a
/*     */     implements Iterator<T>
/*     */   {
/*     */     private int b;
/*     */     
/* 212 */     private int c = -1;
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 216 */       return (this.b < ArraySetSorted.this.c);
/*     */     }
/*     */     
/*     */     public T next() {
/* 220 */       if (this.b >= ArraySetSorted.this.c) {
/* 221 */         throw new NoSuchElementException();
/*     */       }
/* 223 */       this.c = this.b++;
/* 224 */       return (T)ArraySetSorted.this.b[this.c];
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 229 */       if (this.c == -1) {
/* 230 */         throw new IllegalStateException();
/*     */       }
/* 232 */       ArraySetSorted.this.d(this.c);
/* 233 */       this.b--;
/* 234 */       this.c = -1;
/*     */     }
/*     */     
/*     */     private a() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArraySetSorted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */