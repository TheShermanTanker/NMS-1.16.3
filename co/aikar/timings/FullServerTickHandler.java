/*    */ package co.aikar.timings;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FullServerTickHandler
/*    */   extends TimingHandler
/*    */ {
/*  9 */   private static final TimingIdentifier IDENTITY = new TimingIdentifier("Minecraft", "Full Server Tick", null);
/*    */   final TimingData minuteData;
/* 11 */   double avgFreeMemory = -1.0D;
/* 12 */   double avgUsedMemory = -1.0D;
/*    */   FullServerTickHandler() {
/* 14 */     super(IDENTITY);
/* 15 */     this.minuteData = new TimingData(this.id);
/*    */     
/* 17 */     TimingsManager.TIMING_MAP.put(IDENTITY, this);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Timing startTiming() {
/* 23 */     if (TimingsManager.needsFullReset) {
/* 24 */       TimingsManager.resetTimings();
/* 25 */     } else if (TimingsManager.needsRecheckEnabled) {
/* 26 */       TimingsManager.recheckEnabled();
/*    */     } 
/* 28 */     return super.startTiming();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopTiming() {
/* 33 */     super.stopTiming();
/* 34 */     if (!isEnabled() || Bukkit.isStopping()) {
/*    */       return;
/*    */     }
/* 37 */     if (TimingHistory.timedTicks % 20L == 0L) {
/* 38 */       Runtime runtime = Runtime.getRuntime();
/* 39 */       double usedMemory = (runtime.totalMemory() - runtime.freeMemory());
/* 40 */       double freeMemory = runtime.maxMemory() - usedMemory;
/* 41 */       if (this.avgFreeMemory == -1.0D) {
/* 42 */         this.avgFreeMemory = freeMemory;
/*    */       } else {
/* 44 */         this.avgFreeMemory = this.avgFreeMemory * 0.9833333333333333D + freeMemory * 0.016666666666666666D;
/*    */       } 
/*    */       
/* 47 */       if (this.avgUsedMemory == -1.0D) {
/* 48 */         this.avgUsedMemory = usedMemory;
/*    */       } else {
/* 50 */         this.avgUsedMemory = this.avgUsedMemory * 0.9833333333333333D + usedMemory * 0.016666666666666666D;
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     long start = System.nanoTime();
/* 55 */     TimingsManager.tick();
/* 56 */     long diff = System.nanoTime() - start;
/* 57 */     TimingsManager.TIMINGS_TICK.addDiff(diff, null);
/*    */     
/* 59 */     this.record.setCurTickCount(this.record.getCurTickCount() - 1);
/*    */     
/* 61 */     this.minuteData.setCurTickTotal(this.record.getCurTickTotal());
/* 62 */     this.minuteData.setCurTickCount(1);
/*    */     
/* 64 */     boolean violated = isViolated();
/* 65 */     this.minuteData.processTick(violated);
/* 66 */     TimingsManager.TIMINGS_TICK.processTick(violated);
/* 67 */     processTick(violated);
/*    */ 
/*    */     
/* 70 */     if (TimingHistory.timedTicks % 1200L == 0L) {
/* 71 */       TimingsManager.MINUTE_REPORTS.add(new TimingHistory.MinuteReport());
/* 72 */       TimingHistory.resetTicks(false);
/* 73 */       this.minuteData.reset();
/*    */     } 
/* 75 */     if (TimingHistory.timedTicks % Timings.getHistoryInterval() == 0L) {
/* 76 */       TimingsManager.HISTORY.add(new TimingHistory());
/* 77 */       TimingsManager.resetTimings();
/*    */     } 
/* 79 */     Bukkit.getUnsafe().reportTimings();
/*    */   }
/*    */   
/*    */   boolean isViolated() {
/* 83 */     return (this.record.getCurTickTotal() > 50000000L);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\FullServerTickHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */