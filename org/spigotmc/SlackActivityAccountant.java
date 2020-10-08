/*    */ package org.spigotmc;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlackActivityAccountant
/*    */ {
/* 10 */   private double prevTickSlackWeightReciprocal = 65536.0D;
/*    */   private static final double MIN_SLACK_WEIGHT = 1.52587890625E-5D;
/* 12 */   private double averageTickNonSlackNanos = 0.0D;
/*    */   
/*    */   private static final double AVERAGING_FACTOR = 0.375D;
/*    */   private long currentActivityStartNanos;
/*    */   private static final long OFF = -1L;
/*    */   private long currentActivityEndNanos;
/*    */   private double tickSlackWeight;
/*    */   private long tickSlackNanos;
/*    */   
/*    */   private double getSlackFraction(double slackWeight) {
/* 22 */     return Math.min(slackWeight * this.prevTickSlackWeightReciprocal, 1.0D);
/*    */   }
/*    */   
/*    */   private int getEstimatedSlackNanos() {
/* 26 */     return (int)Math.max(50000000L - (long)this.averageTickNonSlackNanos, 0L);
/*    */   }
/*    */   
/*    */   public void tickStarted() {
/* 30 */     this.currentActivityStartNanos = -1L;
/* 31 */     this.tickSlackWeight = 0.0D;
/* 32 */     this.tickSlackNanos = 0L;
/*    */   }
/*    */   
/*    */   public void startActivity(double slackWeight) {
/* 36 */     double slackFraction0 = getSlackFraction(this.tickSlackWeight);
/* 37 */     this.tickSlackWeight += slackWeight;
/* 38 */     double slackFraction1 = getSlackFraction(this.tickSlackWeight);
/*    */     
/* 40 */     long t = System.nanoTime();
/* 41 */     this.currentActivityStartNanos = t;
/* 42 */     this.currentActivityEndNanos = t + (int)((slackFraction1 - slackFraction0) * getEstimatedSlackNanos());
/*    */   }
/*    */   
/*    */   private void endActivity(long endNanos) {
/* 46 */     this.tickSlackNanos += endNanos - this.currentActivityStartNanos;
/* 47 */     this.currentActivityStartNanos = -1L;
/*    */   }
/*    */   
/*    */   public boolean activityTimeIsExhausted() {
/* 51 */     if (this.currentActivityStartNanos == -1L) {
/* 52 */       return true;
/*    */     }
/*    */     
/* 55 */     long t = System.nanoTime();
/* 56 */     if (t <= this.currentActivityEndNanos) {
/* 57 */       return false;
/*    */     }
/* 59 */     endActivity(this.currentActivityEndNanos);
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void endActivity() {
/* 65 */     if (this.currentActivityStartNanos == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 69 */     endActivity(Math.min(System.nanoTime(), this.currentActivityEndNanos));
/*    */   }
/*    */   
/*    */   public void tickEnded(long tickNanos) {
/* 73 */     this.prevTickSlackWeightReciprocal = 1.0D / Math.max(this.tickSlackWeight, 1.52587890625E-5D);
/*    */     
/* 75 */     long tickNonSlackNanos = tickNanos - this.tickSlackNanos;
/* 76 */     this.averageTickNonSlackNanos = this.averageTickNonSlackNanos * 0.625D + tickNonSlackNanos * 0.375D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\SlackActivityAccountant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */