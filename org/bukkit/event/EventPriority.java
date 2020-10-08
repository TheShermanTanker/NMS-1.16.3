/*    */ package org.bukkit.event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EventPriority
/*    */ {
/* 12 */   LOWEST(0),
/*    */ 
/*    */ 
/*    */   
/* 16 */   LOW(1),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   NORMAL(2),
/*    */ 
/*    */ 
/*    */   
/* 25 */   HIGH(3),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   HIGHEST(4),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   MONITOR(5);
/*    */   
/*    */   private final int slot;
/*    */   
/*    */   EventPriority(int slot) {
/* 41 */     this.slot = slot;
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 45 */     return this.slot;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\EventPriority.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */