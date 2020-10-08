/*    */ package com.destroystokyo.paper.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ public class GraphData {
/*    */   private long total;
/*    */   private long free;
/*    */   private long max;
/*    */   private long usedMem;
/*    */   private int usedPercent;
/*    */   
/*    */   public GraphData(long total, long free, long max) {
/* 13 */     this.total = total;
/* 14 */     this.free = free;
/* 15 */     this.max = max;
/* 16 */     this.usedMem = total - free;
/* 17 */     this.usedPercent = (this.usedMem == 0L) ? 0 : (int)(this.usedMem * 100L / max);
/*    */   }
/*    */   
/*    */   public long getTotal() {
/* 21 */     return this.total;
/*    */   }
/*    */   
/*    */   public long getFree() {
/* 25 */     return this.free;
/*    */   }
/*    */   
/*    */   public long getMax() {
/* 29 */     return this.max;
/*    */   }
/*    */   
/*    */   public long getUsedMem() {
/* 33 */     return this.usedMem;
/*    */   }
/*    */   
/*    */   public int getUsedPercent() {
/* 37 */     return this.usedPercent;
/*    */   }
/*    */   
/*    */   public Color getFillColor() {
/* 41 */     return GraphColor.getFillColor(this.usedPercent);
/*    */   }
/*    */   
/*    */   public Color getLineColor() {
/* 45 */     return GraphColor.getLineColor(this.usedPercent);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\gui\GraphData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */