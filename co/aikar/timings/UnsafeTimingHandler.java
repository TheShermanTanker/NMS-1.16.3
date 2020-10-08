/*    */ package co.aikar.timings;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ class UnsafeTimingHandler
/*    */   extends TimingHandler
/*    */ {
/*    */   UnsafeTimingHandler(@NotNull TimingIdentifier id) {
/* 32 */     super(id);
/*    */   }
/*    */   
/*    */   private static void checkThread() {
/* 36 */     if (!Bukkit.isPrimaryThread()) {
/* 37 */       throw new IllegalStateException("Calling Timings from Async Operation");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Timing startTiming() {
/* 44 */     checkThread();
/* 45 */     return super.startTiming();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopTiming() {
/* 50 */     checkThread();
/* 51 */     super.stopTiming();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\UnsafeTimingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */