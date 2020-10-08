/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
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
/*    */ public class BlockGrowEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 25 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final BlockState newState;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public BlockGrowEvent(@NotNull Block block, @NotNull BlockState newState) {
/* 30 */     super(block);
/* 31 */     this.newState = newState;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockState getNewState() {
/* 41 */     return this.newState;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 46 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 51 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 57 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 62 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockGrowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */