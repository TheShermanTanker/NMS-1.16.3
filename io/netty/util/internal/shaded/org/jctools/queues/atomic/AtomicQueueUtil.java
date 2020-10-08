/*    */ package io.netty.util.internal.shaded.org.jctools.queues.atomic;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicLongArray;
/*    */ import java.util.concurrent.atomic.AtomicReferenceArray;
/*    */ 
/*    */ 
/*    */ final class AtomicQueueUtil
/*    */ {
/*    */   static <E> E lvRefElement(AtomicReferenceArray<E> buffer, int offset) {
/* 10 */     return buffer.get(offset);
/*    */   }
/*    */ 
/*    */   
/*    */   static <E> E lpRefElement(AtomicReferenceArray<E> buffer, int offset) {
/* 15 */     return buffer.get(offset);
/*    */   }
/*    */ 
/*    */   
/*    */   static <E> void spRefElement(AtomicReferenceArray<E> buffer, int offset, E value) {
/* 20 */     buffer.lazySet(offset, value);
/*    */   }
/*    */ 
/*    */   
/*    */   static void soRefElement(AtomicReferenceArray<Object> buffer, int offset, Object value) {
/* 25 */     buffer.lazySet(offset, value);
/*    */   }
/*    */ 
/*    */   
/*    */   static <E> void svRefElement(AtomicReferenceArray<E> buffer, int offset, E value) {
/* 30 */     buffer.set(offset, value);
/*    */   }
/*    */ 
/*    */   
/*    */   static int calcRefElementOffset(long index) {
/* 35 */     return (int)index;
/*    */   }
/*    */ 
/*    */   
/*    */   static int calcCircularRefElementOffset(long index, long mask) {
/* 40 */     return (int)(index & mask);
/*    */   }
/*    */ 
/*    */   
/*    */   static <E> AtomicReferenceArray<E> allocateRefArray(int capacity) {
/* 45 */     return new AtomicReferenceArray<E>(capacity);
/*    */   }
/*    */ 
/*    */   
/*    */   static void spLongElement(AtomicLongArray buffer, int offset, long e) {
/* 50 */     buffer.lazySet(offset, e);
/*    */   }
/*    */ 
/*    */   
/*    */   static void soLongElement(AtomicLongArray buffer, int offset, long e) {
/* 55 */     buffer.lazySet(offset, e);
/*    */   }
/*    */ 
/*    */   
/*    */   static long lpLongElement(AtomicLongArray buffer, int offset) {
/* 60 */     return buffer.get(offset);
/*    */   }
/*    */ 
/*    */   
/*    */   static long lvLongElement(AtomicLongArray buffer, int offset) {
/* 65 */     return buffer.get(offset);
/*    */   }
/*    */ 
/*    */   
/*    */   static int calcLongElementOffset(long index) {
/* 70 */     return (int)index;
/*    */   }
/*    */ 
/*    */   
/*    */   static int calcCircularLongElementOffset(long index, int mask) {
/* 75 */     return (int)(index & mask);
/*    */   }
/*    */ 
/*    */   
/*    */   static AtomicLongArray allocateLongArray(int capacity) {
/* 80 */     return new AtomicLongArray(capacity);
/*    */   }
/*    */ 
/*    */   
/*    */   static int length(AtomicReferenceArray<?> buf) {
/* 85 */     return buf.length();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static int modifiedCalcCircularRefElementOffset(long index, long mask) {
/* 93 */     return (int)(index & mask) >> 1;
/*    */   }
/*    */ 
/*    */   
/*    */   static int nextArrayOffset(AtomicReferenceArray<?> curr) {
/* 98 */     return length(curr) - 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\shaded\org\jctools\queues\atomic\AtomicQueueUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */