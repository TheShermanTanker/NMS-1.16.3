/*    */ package com.destroystokyo.paper.antixray;
/*    */ 
/*    */ public final class DataBitsReader
/*    */ {
/*    */   private byte[] dataBits;
/*    */   private int bitsPerObject;
/*    */   private int mask;
/*    */   private int longInDataBitsIndex;
/*    */   private int bitInLongIndex;
/*    */   private long current;
/*    */   
/*    */   public void setDataBits(byte[] dataBits) {
/* 13 */     this.dataBits = dataBits;
/*    */   }
/*    */   
/*    */   public void setBitsPerObject(int bitsPerObject) {
/* 17 */     this.bitsPerObject = bitsPerObject;
/* 18 */     this.mask = (1 << bitsPerObject) - 1;
/*    */   }
/*    */   
/*    */   public void setIndex(int index) {
/* 22 */     this.longInDataBitsIndex = index;
/* 23 */     this.bitInLongIndex = 0;
/* 24 */     init();
/*    */   }
/*    */   
/*    */   private void init() {
/* 28 */     if (this.dataBits.length > this.longInDataBitsIndex + 7) {
/* 29 */       this.current = this.dataBits[this.longInDataBitsIndex] << 56L | (this.dataBits[this.longInDataBitsIndex + 1] & 0xFFL) << 48L | (this.dataBits[this.longInDataBitsIndex + 2] & 0xFFL) << 40L | (this.dataBits[this.longInDataBitsIndex + 3] & 0xFFL) << 32L | (this.dataBits[this.longInDataBitsIndex + 4] & 0xFFL) << 24L | (this.dataBits[this.longInDataBitsIndex + 5] & 0xFFL) << 16L | (this.dataBits[this.longInDataBitsIndex + 6] & 0xFFL) << 8L | this.dataBits[this.longInDataBitsIndex + 7] & 0xFFL;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int read() {
/* 41 */     if (this.bitInLongIndex + this.bitsPerObject > 64) {
/* 42 */       this.bitInLongIndex = 0;
/* 43 */       this.longInDataBitsIndex += 8;
/* 44 */       init();
/*    */     } 
/*    */     
/* 47 */     int value = (int)(this.current >>> this.bitInLongIndex) & this.mask;
/* 48 */     this.bitInLongIndex += this.bitsPerObject;
/* 49 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\antixray\DataBitsReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */