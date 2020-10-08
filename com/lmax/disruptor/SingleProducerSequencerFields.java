/*    */ package com.lmax.disruptor;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class SingleProducerSequencerFields
/*    */   extends SingleProducerSequencerPad
/*    */ {
/*    */   long nextValue;
/*    */   long cachedValue;
/*    */   
/*    */   SingleProducerSequencerFields(int bufferSize, WaitStrategy waitStrategy) {
/* 36 */     super(bufferSize, waitStrategy);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.nextValue = -1L;
/* 43 */     this.cachedValue = -1L;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SingleProducerSequencerFields.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */