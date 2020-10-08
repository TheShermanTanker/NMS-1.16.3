/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class InventoryOpenEvent
/*    */   extends InventoryEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   
/*    */   public InventoryOpenEvent(@NotNull InventoryView transaction) {
/* 17 */     super(transaction);
/* 18 */     this.cancelled = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final HumanEntity getPlayer() {
/* 28 */     return this.transaction.getPlayer();
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
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 42 */     return this.cancelled;
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
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 56 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryOpenEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */