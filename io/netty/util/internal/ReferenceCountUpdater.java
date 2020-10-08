/*     */ package io.netty.util.internal;
/*     */ 
/*     */ import io.netty.util.IllegalReferenceCountException;
/*     */ import io.netty.util.ReferenceCounted;
/*     */ import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
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
/*     */ public abstract class ReferenceCountUpdater<T extends ReferenceCounted>
/*     */ {
/*     */   public static long getUnsafeOffset(Class<? extends ReferenceCounted> clz, String fieldName) {
/*     */     try {
/*  45 */       if (PlatformDependent.hasUnsafe()) {
/*  46 */         return PlatformDependent.objectFieldOffset(clz.getDeclaredField(fieldName));
/*     */       }
/*  48 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*  51 */     return -1L;
/*     */   }
/*     */   
/*     */   protected abstract AtomicIntegerFieldUpdater<T> updater();
/*     */   
/*     */   protected abstract long unsafeOffset();
/*     */   
/*     */   public final int initialValue() {
/*  59 */     return 2;
/*     */   }
/*     */   
/*     */   private static int realRefCnt(int rawCnt) {
/*  63 */     return (rawCnt != 2 && rawCnt != 4 && (rawCnt & 0x1) != 0) ? 0 : (rawCnt >>> 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int toLiveRealRefCnt(int rawCnt, int decrement) {
/*  70 */     if (rawCnt == 2 || rawCnt == 4 || (rawCnt & 0x1) == 0) {
/*  71 */       return rawCnt >>> 1;
/*     */     }
/*     */     
/*  74 */     throw new IllegalReferenceCountException(0, -decrement);
/*     */   }
/*     */ 
/*     */   
/*     */   private int nonVolatileRawCnt(T instance) {
/*  79 */     long offset = unsafeOffset();
/*  80 */     return (offset != -1L) ? PlatformDependent.getInt(instance, offset) : updater().get(instance);
/*     */   }
/*     */   
/*     */   public final int refCnt(T instance) {
/*  84 */     return realRefCnt(updater().get(instance));
/*     */   }
/*     */   
/*     */   public final boolean isLiveNonVolatile(T instance) {
/*  88 */     long offset = unsafeOffset();
/*  89 */     int rawCnt = (offset != -1L) ? PlatformDependent.getInt(instance, offset) : updater().get(instance);
/*     */ 
/*     */     
/*  92 */     return (rawCnt == 2 || rawCnt == 4 || rawCnt == 6 || rawCnt == 8 || (rawCnt & 0x1) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setRefCnt(T instance, int refCnt) {
/*  99 */     updater().set(instance, (refCnt > 0) ? (refCnt << 1) : 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void resetRefCnt(T instance) {
/* 106 */     updater().set(instance, initialValue());
/*     */   }
/*     */   
/*     */   public final T retain(T instance) {
/* 110 */     return retain0(instance, 1, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public final T retain(T instance, int increment) {
/* 115 */     int rawIncrement = ObjectUtil.checkPositive(increment, "increment") << 1;
/* 116 */     return retain0(instance, increment, rawIncrement);
/*     */   }
/*     */ 
/*     */   
/*     */   private T retain0(T instance, int increment, int rawIncrement) {
/* 121 */     int oldRef = updater().getAndAdd(instance, rawIncrement);
/* 122 */     if (oldRef != 2 && oldRef != 4 && (oldRef & 0x1) != 0) {
/* 123 */       throw new IllegalReferenceCountException(0, increment);
/*     */     }
/*     */     
/* 126 */     if ((oldRef <= 0 && oldRef + rawIncrement >= 0) || (oldRef >= 0 && oldRef + rawIncrement < oldRef)) {
/*     */ 
/*     */       
/* 129 */       updater().getAndAdd(instance, -rawIncrement);
/* 130 */       throw new IllegalReferenceCountException(realRefCnt(oldRef), increment);
/*     */     } 
/* 132 */     return instance;
/*     */   }
/*     */   
/*     */   public final boolean release(T instance) {
/* 136 */     int rawCnt = nonVolatileRawCnt(instance);
/* 137 */     return (rawCnt == 2) ? ((tryFinalRelease0(instance, 2) || retryRelease0(instance, 1))) : 
/* 138 */       nonFinalRelease0(instance, 1, rawCnt, toLiveRealRefCnt(rawCnt, 1));
/*     */   }
/*     */   
/*     */   public final boolean release(T instance, int decrement) {
/* 142 */     int rawCnt = nonVolatileRawCnt(instance);
/* 143 */     int realCnt = toLiveRealRefCnt(rawCnt, ObjectUtil.checkPositive(decrement, "decrement"));
/* 144 */     return (decrement == realCnt) ? ((tryFinalRelease0(instance, rawCnt) || retryRelease0(instance, decrement))) : 
/* 145 */       nonFinalRelease0(instance, decrement, rawCnt, realCnt);
/*     */   }
/*     */   
/*     */   private boolean tryFinalRelease0(T instance, int expectRawCnt) {
/* 149 */     return updater().compareAndSet(instance, expectRawCnt, 1);
/*     */   }
/*     */   
/*     */   private boolean nonFinalRelease0(T instance, int decrement, int rawCnt, int realCnt) {
/* 153 */     if (decrement < realCnt && 
/*     */       
/* 155 */       updater().compareAndSet(instance, rawCnt, rawCnt - (decrement << 1))) {
/* 156 */       return false;
/*     */     }
/* 158 */     return retryRelease0(instance, decrement);
/*     */   }
/*     */   
/*     */   private boolean retryRelease0(T instance, int decrement) {
/*     */     while (true) {
/* 163 */       int rawCnt = updater().get(instance), realCnt = toLiveRealRefCnt(rawCnt, decrement);
/* 164 */       if (decrement == realCnt) {
/* 165 */         if (tryFinalRelease0(instance, rawCnt)) {
/* 166 */           return true;
/*     */         }
/* 168 */       } else if (decrement < realCnt) {
/*     */         
/* 170 */         if (updater().compareAndSet(instance, rawCnt, rawCnt - (decrement << 1))) {
/* 171 */           return false;
/*     */         }
/*     */       } else {
/* 174 */         throw new IllegalReferenceCountException(realCnt, -decrement);
/*     */       } 
/* 176 */       Thread.yield();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\ReferenceCountUpdater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */