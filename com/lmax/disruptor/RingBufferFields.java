/*    */ package com.lmax.disruptor;
/*    */ 
/*    */ import com.lmax.disruptor.util.Util;
/*    */ import sun.misc.Unsafe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class RingBufferFields<E>
/*    */   extends RingBufferPad
/*    */ {
/*    */   private static final int BUFFER_PAD;
/*    */   private static final long REF_ARRAY_BASE;
/*    */   private static final int REF_ELEMENT_SHIFT;
/* 34 */   private static final Unsafe UNSAFE = Util.getUnsafe();
/*    */   private final long indexMask;
/*    */   
/*    */   static {
/* 38 */     int scale = UNSAFE.arrayIndexScale(Object[].class);
/* 39 */     if (4 == scale) {
/*    */       
/* 41 */       REF_ELEMENT_SHIFT = 2;
/*    */     }
/* 43 */     else if (8 == scale) {
/*    */       
/* 45 */       REF_ELEMENT_SHIFT = 3;
/*    */     }
/*    */     else {
/*    */       
/* 49 */       throw new IllegalStateException("Unknown pointer size");
/*    */     } 
/* 51 */     BUFFER_PAD = 128 / scale;
/*    */     
/* 53 */     REF_ARRAY_BASE = (UNSAFE.arrayBaseOffset(Object[].class) + (BUFFER_PAD << REF_ELEMENT_SHIFT));
/*    */   }
/*    */ 
/*    */   
/*    */   private final Object[] entries;
/*    */   
/*    */   protected final int bufferSize;
/*    */   
/*    */   protected final Sequencer sequencer;
/*    */ 
/*    */   
/*    */   RingBufferFields(EventFactory<E> eventFactory, Sequencer sequencer) {
/* 65 */     this.sequencer = sequencer;
/* 66 */     this.bufferSize = sequencer.getBufferSize();
/*    */     
/* 68 */     if (this.bufferSize < 1)
/*    */     {
/* 70 */       throw new IllegalArgumentException("bufferSize must not be less than 1");
/*    */     }
/* 72 */     if (Integer.bitCount(this.bufferSize) != 1)
/*    */     {
/* 74 */       throw new IllegalArgumentException("bufferSize must be a power of 2");
/*    */     }
/*    */     
/* 77 */     this.indexMask = (this.bufferSize - 1);
/* 78 */     this.entries = new Object[sequencer.getBufferSize() + 2 * BUFFER_PAD];
/* 79 */     fill(eventFactory);
/*    */   }
/*    */ 
/*    */   
/*    */   private void fill(EventFactory<E> eventFactory) {
/* 84 */     for (int i = 0; i < this.bufferSize; i++)
/*    */     {
/* 86 */       this.entries[BUFFER_PAD + i] = eventFactory.newInstance();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected final E elementAt(long sequence) {
/* 93 */     return (E)UNSAFE.getObject(this.entries, REF_ARRAY_BASE + ((sequence & this.indexMask) << REF_ELEMENT_SHIFT));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\RingBufferFields.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */