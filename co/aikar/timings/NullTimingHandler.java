/*    */ package co.aikar.timings;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public final class NullTimingHandler
/*    */   implements Timing
/*    */ {
/* 30 */   public static final Timing NULL = new NullTimingHandler();
/*    */   
/*    */   @NotNull
/*    */   public Timing startTiming() {
/* 34 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopTiming() {}
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Timing startTimingIfSync() {
/* 45 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopTimingIfSync() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void abort() {}
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public TimingHandler getTimingHandler() {
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\NullTimingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */