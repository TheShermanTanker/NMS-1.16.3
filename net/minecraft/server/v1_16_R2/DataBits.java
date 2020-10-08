/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.IntConsumer;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class DataBits
/*     */ {
/*   9 */   private static final int[] a = new int[] { -1, -1, 0, Integer.MIN_VALUE, 0, 0, 1431655765, 1431655765, 0, Integer.MIN_VALUE, 0, 1, 858993459, 858993459, 0, 715827882, 715827882, 0, 613566756, 613566756, 0, Integer.MIN_VALUE, 0, 2, 477218588, 477218588, 0, 429496729, 429496729, 0, 390451572, 390451572, 0, 357913941, 357913941, 0, 330382099, 330382099, 0, 306783378, 306783378, 0, 286331153, 286331153, 0, Integer.MIN_VALUE, 0, 3, 252645135, 252645135, 0, 238609294, 238609294, 0, 226050910, 226050910, 0, 214748364, 214748364, 0, 204522252, 204522252, 0, 195225786, 195225786, 0, 186737708, 186737708, 0, 178956970, 178956970, 0, 171798691, 171798691, 0, 165191049, 165191049, 0, 159072862, 159072862, 0, 153391689, 153391689, 0, 148102320, 148102320, 0, 143165576, 143165576, 0, 138547332, 138547332, 0, Integer.MIN_VALUE, 0, 4, 130150524, 130150524, 0, 126322567, 126322567, 0, 122713351, 122713351, 0, 119304647, 119304647, 0, 116080197, 116080197, 0, 113025455, 113025455, 0, 110127366, 110127366, 0, 107374182, 107374182, 0, 104755299, 104755299, 0, 102261126, 102261126, 0, 99882960, 99882960, 0, 97612893, 97612893, 0, 95443717, 95443717, 0, 93368854, 93368854, 0, 91382282, 91382282, 0, 89478485, 89478485, 0, 87652393, 87652393, 0, 85899345, 85899345, 0, 84215045, 84215045, 0, 82595524, 82595524, 0, 81037118, 81037118, 0, 79536431, 79536431, 0, 78090314, 78090314, 0, 76695844, 76695844, 0, 75350303, 75350303, 0, 74051160, 74051160, 0, 72796055, 72796055, 0, 71582788, 71582788, 0, 70409299, 70409299, 0, 69273666, 69273666, 0, 68174084, 68174084, 0, Integer.MIN_VALUE, 0, 5 };
/*     */   private final long[] b;
/*     */   private final int c;
/*     */   private final long d;
/*     */   private final int e;
/*     */   private final int f;
/*     */   private final int g;
/*     */   private final int h;
/*     */   private final int i;
/*     */   
/*     */   public DataBits(int i, int j) {
/*  20 */     this(i, j, (long[])null);
/*     */   }
/*     */   
/*     */   public DataBits(int i, int j, @Nullable long[] along) {
/*  24 */     Validate.inclusiveBetween(1L, 32L, i);
/*  25 */     this.e = j;
/*  26 */     this.c = i;
/*  27 */     this.d = (1L << i) - 1L;
/*  28 */     this.f = (char)(64 / i);
/*  29 */     int k = 3 * (this.f - 1);
/*     */     
/*  31 */     this.g = a[k + 0];
/*  32 */     this.h = a[k + 1];
/*  33 */     this.i = a[k + 2];
/*  34 */     int l = (j + this.f - 1) / this.f;
/*     */     
/*  36 */     if (along != null) {
/*  37 */       if (along.length != l) {
/*  38 */         throw (RuntimeException)SystemUtils.c(new RuntimeException("Invalid length given for storage, got: " + along.length + " but expected: " + l));
/*     */       }
/*     */       
/*  41 */       this.b = along;
/*     */     } else {
/*  43 */       this.b = new long[l];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int b(int i) {
/*  49 */     long j = Integer.toUnsignedLong(this.g);
/*  50 */     long k = Integer.toUnsignedLong(this.h);
/*     */     
/*  52 */     return (int)(i * j + k >> 32L >> this.i);
/*     */   }
/*     */   public final int getAndSet(int index, int value) {
/*  55 */     return a(index, value);
/*     */   }
/*     */   
/*     */   public int a(int i, int j) {
/*  59 */     int k = b(i);
/*  60 */     long l = this.b[k];
/*  61 */     int i1 = (i - k * this.f) * this.c;
/*  62 */     int j1 = (int)(l >> i1 & this.d);
/*     */     
/*  64 */     this.b[k] = l & (this.d << i1 ^ 0xFFFFFFFFFFFFFFFFL) | (j & this.d) << i1;
/*  65 */     return j1;
/*     */   }
/*     */   public final void set(int index, int value) {
/*  68 */     b(index, value);
/*     */   }
/*     */   
/*     */   public void b(int i, int j) {
/*  72 */     int k = b(i);
/*  73 */     long l = this.b[k];
/*  74 */     int i1 = (i - k * this.f) * this.c;
/*     */     
/*  76 */     this.b[k] = l & (this.d << i1 ^ 0xFFFFFFFFFFFFFFFFL) | (j & this.d) << i1;
/*     */   }
/*     */   public final int get(int index) {
/*  79 */     return a(index);
/*     */   }
/*     */   public int a(int i) {
/*  82 */     int j = b(i);
/*  83 */     long k = this.b[j];
/*  84 */     int l = (i - j * this.f) * this.c;
/*     */     
/*  86 */     return (int)(k >> l & this.d);
/*     */   }
/*     */   public final long[] getDataBits() {
/*  89 */     return a();
/*     */   } public long[] a() {
/*  91 */     return this.b;
/*     */   }
/*     */   
/*     */   public int b() {
/*  95 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(IntConsumer intconsumer) {
/*  99 */     int i = 0;
/* 100 */     long[] along = this.b;
/* 101 */     int j = along.length;
/*     */     
/* 103 */     for (int k = 0; k < j; k++) {
/* 104 */       long l = along[k];
/*     */       
/* 106 */       for (int i1 = 0; i1 < this.f; i1++) {
/* 107 */         intconsumer.accept((int)(l & this.d));
/* 108 */         l >>= this.c;
/* 109 */         i++;
/* 110 */         if (i >= this.e) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void forEach(DataBitConsumer consumer) {
/* 120 */     int i = 0;
/* 121 */     long[] along = this.b;
/* 122 */     int j = along.length;
/*     */     
/* 124 */     for (int k = 0; k < j; k++) {
/* 125 */       long l = along[k];
/*     */       
/* 127 */       for (int i1 = 0; i1 < this.f; i1++) {
/* 128 */         consumer.accept(i, (int)(l & this.d));
/* 129 */         l >>= this.c;
/* 130 */         i++;
/* 131 */         if (i >= this.e)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface DataBitConsumer {
/*     */     void accept(int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataBits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */