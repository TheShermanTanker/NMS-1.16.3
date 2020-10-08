/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDispenseEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled = false;
/*    */   private ItemStack item;
/*    */   private Vector velocity;
/*    */   
/*    */   public BlockDispenseEvent(@NotNull Block block, @NotNull ItemStack dispensed, @NotNull Vector velocity) {
/* 23 */     super(block);
/* 24 */     this.item = dispensed;
/* 25 */     this.velocity = velocity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItem() {
/* 37 */     return this.item.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(@NotNull ItemStack item) {
/* 46 */     this.item = item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Vector getVelocity() {
/* 59 */     return this.velocity.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVelocity(@NotNull Vector vel) {
/* 68 */     this.velocity = vel;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 73 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 78 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 84 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 89 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockDispenseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */