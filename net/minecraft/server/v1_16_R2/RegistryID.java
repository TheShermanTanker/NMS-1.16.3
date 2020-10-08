/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Iterators;
/*     */ import java.util.Arrays;
/*     */ import java.util.BitSet;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class RegistryID<K> implements Registry<K> {
/*  11 */   private static final Object a = null;
/*     */   private K[] b;
/*     */   private int[] c;
/*     */   private K[] d;
/*     */   private int e;
/*     */   private int f;
/*     */   private BitSet usedIds;
/*     */   
/*     */   public RegistryID(int i) {
/*  20 */     i = (int)(i / 0.8F);
/*  21 */     this.b = (K[])new Object[i];
/*  22 */     this.c = new int[i];
/*  23 */     this.d = (K[])new Object[i];
/*  24 */     this.usedIds = new BitSet();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(K k) {
/*  30 */     return getId(k);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId(@Nullable K k0) {
/*  35 */     return c(b(k0, d(k0)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public K fromId(int i) {
/*  41 */     return (i >= 0 && i < this.d.length) ? this.d[i] : null;
/*     */   }
/*     */   
/*     */   private int c(int i) {
/*  45 */     return (i == -1) ? -1 : this.c[i];
/*     */   }
/*     */   
/*     */   public int c(K k0) {
/*  49 */     int i = c();
/*     */     
/*  51 */     a(k0, i);
/*  52 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int c() {
/*  62 */     this.e = this.usedIds.nextClearBit(0);
/*     */ 
/*     */     
/*  65 */     return this.e;
/*     */   }
/*     */   
/*     */   private void d(int i) {
/*  69 */     K[] ak = this.b;
/*  70 */     int[] aint = this.c;
/*     */     
/*  72 */     this.b = (K[])new Object[i];
/*  73 */     this.c = new int[i];
/*  74 */     this.d = (K[])new Object[i];
/*  75 */     this.e = 0;
/*  76 */     this.f = 0;
/*  77 */     this.usedIds.clear();
/*     */     
/*  79 */     for (int j = 0; j < ak.length; j++) {
/*  80 */       if (ak[j] != null) {
/*  81 */         a(ak[j], aint[j]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(K k0, int i) {
/*  88 */     int j = Math.max(i, this.f + 1);
/*     */ 
/*     */     
/*  91 */     if (j >= this.b.length * 0.8F) {
/*  92 */       int m; for (m = this.b.length << 1; m < i; m <<= 1);
/*     */ 
/*     */ 
/*     */       
/*  96 */       d(m);
/*     */     } 
/*     */     
/*  99 */     int k = e(d(k0));
/* 100 */     this.b[k] = k0;
/* 101 */     this.c[k] = i;
/* 102 */     this.d[i] = k0;
/* 103 */     this.usedIds.set(i);
/* 104 */     this.f++;
/* 105 */     if (i == this.e) {
/* 106 */       this.e++;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int d(@Nullable K k0) {
/* 112 */     return (MathHelper.g(System.identityHashCode(k0)) & Integer.MAX_VALUE) % this.b.length;
/*     */   }
/*     */ 
/*     */   
/*     */   private int b(@Nullable K k0, int i) {
/*     */     int j;
/* 118 */     for (j = i; j < this.b.length; j++) {
/* 119 */       if (this.b[j] == k0) {
/* 120 */         return j;
/*     */       }
/*     */       
/* 123 */       if (this.b[j] == a) {
/* 124 */         return -1;
/*     */       }
/*     */     } 
/*     */     
/* 128 */     for (j = 0; j < i; j++) {
/* 129 */       if (this.b[j] == k0) {
/* 130 */         return j;
/*     */       }
/*     */       
/* 133 */       if (this.b[j] == a) {
/* 134 */         return -1;
/*     */       }
/*     */     } 
/*     */     
/* 138 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int e(int i) {
/*     */     int j;
/* 144 */     for (j = i; j < this.b.length; j++) {
/* 145 */       if (this.b[j] == a) {
/* 146 */         return j;
/*     */       }
/*     */     } 
/*     */     
/* 150 */     for (j = 0; j < i; j++) {
/* 151 */       if (this.b[j] == a) {
/* 152 */         return j;
/*     */       }
/*     */     } 
/*     */     
/* 156 */     throw new RuntimeException("Overflowed :(");
/*     */   }
/*     */   
/*     */   public Iterator<K> iterator() {
/* 160 */     return (Iterator<K>)Iterators.filter((Iterator)Iterators.forArray((Object[])this.d), Predicates.notNull());
/*     */   }
/*     */   
/*     */   public void a() {
/* 164 */     Arrays.fill((Object[])this.b, (Object)null);
/* 165 */     Arrays.fill((Object[])this.d, (Object)null);
/* 166 */     this.e = 0;
/* 167 */     this.f = 0;
/* 168 */     this.usedIds.clear();
/*     */   }
/*     */   
/*     */   public int b() {
/* 172 */     return this.f;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */