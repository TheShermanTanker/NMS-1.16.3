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
/*    */ abstract class SingleProducerSequencerPad
/*    */   extends AbstractSequencer
/*    */ {
/*    */   protected long p1;
/*    */   protected long p2;
/*    */   protected long p3;
/*    */   protected long p4;
/*    */   protected long p5;
/*    */   protected long p6;
/*    */   protected long p7;
/*    */   
/*    */   SingleProducerSequencerPad(int bufferSize, WaitStrategy waitStrategy) {
/* 28 */     super(bufferSize, waitStrategy);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disruptor\SingleProducerSequencerPad.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */