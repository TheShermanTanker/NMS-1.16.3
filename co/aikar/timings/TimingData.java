/*     */ package co.aikar.timings;
/*     */ 
/*     */ import co.aikar.util.JSONUtil;
/*     */ import java.util.List;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ class TimingData
/*     */ {
/*     */   private final int id;
/*  38 */   private int count = 0;
/*  39 */   private int lagCount = 0;
/*  40 */   private long totalTime = 0L;
/*  41 */   private long lagTotalTime = 0L;
/*  42 */   private int curTickCount = 0;
/*  43 */   private long curTickTotal = 0L;
/*     */   
/*     */   TimingData(int id) {
/*  46 */     this.id = id;
/*     */   }
/*     */   
/*     */   private TimingData(TimingData data) {
/*  50 */     this.id = data.id;
/*  51 */     this.totalTime = data.totalTime;
/*  52 */     this.lagTotalTime = data.lagTotalTime;
/*  53 */     this.count = data.count;
/*  54 */     this.lagCount = data.lagCount;
/*     */   }
/*     */   
/*     */   void add(long diff) {
/*  58 */     this.curTickCount++;
/*  59 */     this.curTickTotal += diff;
/*     */   }
/*     */   
/*     */   void processTick(boolean violated) {
/*  63 */     this.totalTime += this.curTickTotal;
/*  64 */     this.count += this.curTickCount;
/*  65 */     if (violated) {
/*  66 */       this.lagTotalTime += this.curTickTotal;
/*  67 */       this.lagCount += this.curTickCount;
/*     */     } 
/*  69 */     this.curTickTotal = 0L;
/*  70 */     this.curTickCount = 0;
/*     */   }
/*     */   
/*     */   void reset() {
/*  74 */     this.count = 0;
/*  75 */     this.lagCount = 0;
/*  76 */     this.curTickTotal = 0L;
/*  77 */     this.curTickCount = 0;
/*  78 */     this.totalTime = 0L;
/*  79 */     this.lagTotalTime = 0L;
/*     */   }
/*     */   
/*     */   protected TimingData clone() {
/*  83 */     return new TimingData(this);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   List<Object> export() {
/*  88 */     List<Object> list = JSONUtil.toArray(new Object[] {
/*  89 */           Integer.valueOf(this.id), 
/*  90 */           Integer.valueOf(this.count), 
/*  91 */           Long.valueOf(this.totalTime) });
/*  92 */     if (this.lagCount > 0) {
/*  93 */       list.add(Integer.valueOf(this.lagCount));
/*  94 */       list.add(Long.valueOf(this.lagTotalTime));
/*     */     } 
/*  96 */     return list;
/*     */   }
/*     */   
/*     */   boolean hasData() {
/* 100 */     return (this.count > 0);
/*     */   }
/*     */   
/*     */   long getTotalTime() {
/* 104 */     return this.totalTime;
/*     */   }
/*     */   
/*     */   int getCurTickCount() {
/* 108 */     return this.curTickCount;
/*     */   }
/*     */   
/*     */   void setCurTickCount(int curTickCount) {
/* 112 */     this.curTickCount = curTickCount;
/*     */   }
/*     */   
/*     */   long getCurTickTotal() {
/* 116 */     return this.curTickTotal;
/*     */   }
/*     */   
/*     */   void setCurTickTotal(long curTickTotal) {
/* 120 */     this.curTickTotal = curTickTotal;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */