/*    */ package com.destroystokyo.paper.antixray;
/*    */ 
/*    */ public final class DataBitsWriter
/*    */ {
/*    */   private byte[] dataBits;
/*    */   private int bitsPerObject;
/*    */   private long mask;
/*    */   private int longInDataBitsIndex;
/*    */   private int bitInLongIndex;
/*    */   private long current;
/*    */   private boolean dirty;
/*    */   
/*    */   public void setDataBits(byte[] dataBits) {
/* 14 */     this.dataBits = dataBits;
/*    */   }
/*    */   
/*    */   public void setBitsPerObject(int bitsPerObject) {
/* 18 */     this.bitsPerObject = bitsPerObject;
/* 19 */     this.mask = ((1 << bitsPerObject) - 1);
/*    */   }
/*    */   
/*    */   public void setIndex(int index) {
/* 23 */     this.longInDataBitsIndex = index;
/* 24 */     this.bitInLongIndex = 0;
/* 25 */     init();
/*    */   }
/*    */   
/*    */   private void init() {
/* 29 */     if (this.dataBits.length > this.longInDataBitsIndex + 7) {
/* 30 */       this.current = this.dataBits[this.longInDataBitsIndex] << 56L | (this.dataBits[this.longInDataBitsIndex + 1] & 0xFFL) << 48L | (this.dataBits[this.longInDataBitsIndex + 2] & 0xFFL) << 40L | (this.dataBits[this.longInDataBitsIndex + 3] & 0xFFL) << 32L | (this.dataBits[this.longInDataBitsIndex + 4] & 0xFFL) << 24L | (this.dataBits[this.longInDataBitsIndex + 5] & 0xFFL) << 16L | (this.dataBits[this.longInDataBitsIndex + 6] & 0xFFL) << 8L | this.dataBits[this.longInDataBitsIndex + 7] & 0xFFL;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     this.dirty = false;
/*    */   }
/*    */   
/*    */   public void finish() {
/* 44 */     if (this.dirty && this.dataBits.length > this.longInDataBitsIndex + 7) {
/* 45 */       this.dataBits[this.longInDataBitsIndex] = (byte)(int)(this.current >> 56L & 0xFFL);
/* 46 */       this.dataBits[this.longInDataBitsIndex + 1] = (byte)(int)(this.current >> 48L & 0xFFL);
/* 47 */       this.dataBits[this.longInDataBitsIndex + 2] = (byte)(int)(this.current >> 40L & 0xFFL);
/* 48 */       this.dataBits[this.longInDataBitsIndex + 3] = (byte)(int)(this.current >> 32L & 0xFFL);
/* 49 */       this.dataBits[this.longInDataBitsIndex + 4] = (byte)(int)(this.current >> 24L & 0xFFL);
/* 50 */       this.dataBits[this.longInDataBitsIndex + 5] = (byte)(int)(this.current >> 16L & 0xFFL);
/* 51 */       this.dataBits[this.longInDataBitsIndex + 6] = (byte)(int)(this.current >> 8L & 0xFFL);
/* 52 */       this.dataBits[this.longInDataBitsIndex + 7] = (byte)(int)(this.current & 0xFFL);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void write(int value) {
/* 57 */     if (this.bitInLongIndex + this.bitsPerObject > 64) {
/* 58 */       finish();
/* 59 */       this.bitInLongIndex = 0;
/* 60 */       this.longInDataBitsIndex += 8;
/* 61 */       init();
/*    */     } 
/*    */     
/* 64 */     this.current = this.current & (this.mask << this.bitInLongIndex ^ 0xFFFFFFFFFFFFFFFFL) | (value & this.mask) << this.bitInLongIndex;
/* 65 */     this.dirty = true;
/* 66 */     this.bitInLongIndex += this.bitsPerObject;
/*    */   }
/*    */   
/*    */   public void skip() {
/* 70 */     this.bitInLongIndex += this.bitsPerObject;
/*    */     
/* 72 */     if (this.bitInLongIndex > 64) {
/* 73 */       finish();
/* 74 */       this.bitInLongIndex = this.bitsPerObject;
/* 75 */       this.longInDataBitsIndex += 8;
/* 76 */       init();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\antixray\DataBitsWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */