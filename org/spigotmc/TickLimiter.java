/*    */ package org.spigotmc;
/*    */ 
/*    */ public class TickLimiter
/*    */ {
/*    */   private final int maxTime;
/*    */   private long startTime;
/*    */   
/*    */   public TickLimiter(int maxtime) {
/*  9 */     this.maxTime = maxtime;
/*    */   }
/*    */   
/*    */   public void initTick() {
/* 13 */     this.startTime = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public boolean shouldContinue() {
/* 17 */     long remaining = System.currentTimeMillis() - this.startTime;
/* 18 */     return (remaining < this.maxTime);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\TickLimiter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */