/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.util.pooled.PooledObjects;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NibbleArray
/*     */ {
/*  11 */   static final NibbleArray EMPTY_NIBBLE_ARRAY = new NibbleArray()
/*     */     {
/*     */       public byte[] asBytes() {
/*  14 */         throw new IllegalStateException();
/*     */       }
/*     */     };
/*  17 */   long lightCacheKey = Long.MIN_VALUE;
/*  18 */   public static byte[] EMPTY_NIBBLE = new byte[2048];
/*  19 */   private static final int nibbleBucketSizeMultiplier = Integer.getInteger("Paper.nibbleBucketSize", 3072).intValue();
/*  20 */   private static final int maxPoolSize = Integer.getInteger("Paper.maxNibblePoolSize", (int)Math.min(6L, Math.max(1L, Runtime.getRuntime().maxMemory() / 1024L / 1024L / 1024L)) * nibbleBucketSizeMultiplier * 8).intValue();
/*  21 */   public static final PooledObjects<byte[]> BYTE_2048 = new PooledObjects(() -> new byte[2048], maxPoolSize);
/*     */   public static void releaseBytes(byte[] bytes) {
/*  23 */     if (bytes != null && bytes != EMPTY_NIBBLE && bytes.length == 2048) {
/*  24 */       System.arraycopy(EMPTY_NIBBLE, 0, bytes, 0, 2048);
/*  25 */       BYTE_2048.release(bytes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public NibbleArray markPoolSafe(byte[] bytes) {
/*  30 */     if (bytes != EMPTY_NIBBLE) this.a = bytes; 
/*  31 */     return markPoolSafe();
/*     */   }
/*     */   public NibbleArray markPoolSafe() {
/*  34 */     this.poolSafe = true;
/*  35 */     return this;
/*     */   }
/*     */   public byte[] getIfSet() {
/*  38 */     return (this.a != null) ? this.a : EMPTY_NIBBLE;
/*     */   }
/*     */   public byte[] getCloneIfSet() {
/*  41 */     if (this.a == null) {
/*  42 */       return EMPTY_NIBBLE;
/*     */     }
/*  44 */     byte[] ret = (byte[])BYTE_2048.acquire();
/*  45 */     System.arraycopy(getIfSet(), 0, ret, 0, 2048);
/*  46 */     return ret;
/*     */   }
/*     */   
/*     */   public NibbleArray cloneAndSet(byte[] bytes) {
/*  50 */     if (bytes != null && bytes != EMPTY_NIBBLE) {
/*  51 */       this.a = (byte[])BYTE_2048.acquire();
/*  52 */       System.arraycopy(bytes, 0, this.a, 0, 2048);
/*     */     } 
/*  54 */     return this;
/*     */   }
/*     */   boolean poolSafe = false;
/*     */   
/*     */   private void registerCleaner() {
/*  59 */     if (!this.poolSafe) {
/*  60 */       this.cleaner = MCUtil.registerCleaner(this, this.a, NibbleArray::releaseBytes);
/*     */     } else {
/*  62 */       this.cleaner = MCUtil.once(() -> releaseBytes(this.a));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Runnable cleaner;
/*     */   
/*     */   @Nullable
/*     */   protected byte[] a;
/*     */   
/*     */   public NibbleArray(byte[] abyte) {
/*  73 */     this(abyte, false);
/*     */   }
/*     */   public NibbleArray(byte[] abyte, boolean isSafe) {
/*  76 */     this.a = abyte;
/*  77 */     if (!isSafe) this.a = getCloneIfSet(); 
/*  78 */     registerCleaner();
/*     */     
/*  80 */     if (abyte.length != 2048) {
/*  81 */       throw (IllegalArgumentException)SystemUtils.c(new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + abyte.length));
/*     */     }
/*     */   }
/*     */   
/*     */   protected NibbleArray(int i) {
/*  86 */     this.a = new byte[i];
/*     */   }
/*     */   
/*     */   public int a(int i, int j, int k) {
/*  90 */     return b(b(i, j, k));
/*     */   }
/*     */   
/*     */   public void a(int i, int j, int k, int l) {
/*  94 */     a(b(i, j, k), l);
/*     */   }
/*     */   
/*     */   protected int b(int i, int j, int k) {
/*  98 */     return j << 8 | k << 4 | i;
/*     */   }
/*     */   
/*     */   public int b(int i) {
/* 102 */     if (this.a == null) {
/* 103 */       return 0;
/*     */     }
/* 105 */     int j = d(i);
/*     */     
/* 107 */     return this.a[j] >> (i & 0x1) << 2 & 0xF;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, int j) {
/* 112 */     if (this.a == null) {
/* 113 */       this.a = (byte[])BYTE_2048.acquire();
/* 114 */       registerCleaner();
/*     */     } 
/*     */     
/* 117 */     int k = d(i);
/*     */ 
/*     */     
/* 120 */     int shift = (i & 0x1) << 2;
/* 121 */     this.a[k] = (byte)(this.a[k] & (15 << shift ^ 0xFFFFFFFF) | (j & 0xF) << shift);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean c(int i) {
/* 126 */     return ((i & 0x1) == 0);
/*     */   }
/*     */   
/*     */   private int d(int i) {
/* 130 */     return i >> 1;
/*     */   }
/*     */   
/*     */   public byte[] asBytes() {
/* 134 */     if (this.a == null) {
/* 135 */       this.a = new byte[2048];
/*     */     }
/*     */     else {
/*     */       
/* 139 */       Runnable cleaner = this.cleaner;
/* 140 */       if (cleaner != null) {
/* 141 */         this.a = (byte[])this.a.clone();
/* 142 */         cleaner.run();
/* 143 */         this.cleaner = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 148 */     return this.a;
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   public byte[] asBytesPoolSafe() {
/* 153 */     if (this.a == null) {
/* 154 */       this.a = (byte[])BYTE_2048.acquire();
/* 155 */       registerCleaner();
/*     */     } 
/*     */ 
/*     */     
/* 159 */     return this.a;
/*     */   }
/*     */   
/*     */   public NibbleArray copy() {
/* 163 */     return b();
/*     */   } public NibbleArray b() {
/* 165 */     return (this.a == null) ? new NibbleArray() : new NibbleArray(this.a);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 169 */     StringBuilder stringbuilder = new StringBuilder();
/*     */     
/* 171 */     for (int i = 0; i < 4096; i++) {
/* 172 */       stringbuilder.append(Integer.toHexString(b(i)));
/* 173 */       if ((i & 0xF) == 15) {
/* 174 */         stringbuilder.append("\n");
/*     */       }
/*     */       
/* 177 */       if ((i & 0xFF) == 255) {
/* 178 */         stringbuilder.append("\n");
/*     */       }
/*     */     } 
/*     */     
/* 182 */     return stringbuilder.toString();
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 186 */     return (this.a == null);
/*     */   }
/*     */   
/*     */   public NibbleArray() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NibbleArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */