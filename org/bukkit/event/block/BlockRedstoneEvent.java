/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class BlockRedstoneEvent
/*    */   extends BlockEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final int oldCurrent;
/*    */   private int newCurrent;
/*    */   
/*    */   public BlockRedstoneEvent(@NotNull Block block, int oldCurrent, int newCurrent) {
/* 16 */     super(block);
/* 17 */     this.oldCurrent = oldCurrent;
/* 18 */     this.newCurrent = newCurrent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOldCurrent() {
/* 27 */     return this.oldCurrent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNewCurrent() {
/* 36 */     return this.newCurrent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNewCurrent(int newCurrent) {
/* 45 */     this.newCurrent = newCurrent;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 51 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 56 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockRedstoneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */