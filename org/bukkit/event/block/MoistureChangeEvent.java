/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class MoistureChangeEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final BlockState newState;
/*    */   
/*    */   public MoistureChangeEvent(@NotNull Block block, @NotNull BlockState newState) {
/* 19 */     super(block);
/* 20 */     this.newState = newState;
/* 21 */     this.cancelled = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockState getNewState() {
/* 31 */     return this.newState;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 36 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 41 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 47 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 52 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\MoistureChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */