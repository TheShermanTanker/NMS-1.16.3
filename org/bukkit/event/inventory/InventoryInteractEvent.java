/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class InventoryInteractEvent
/*    */   extends InventoryEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private Event.Result result = Event.Result.DEFAULT;
/*    */   
/*    */   public InventoryInteractEvent(@NotNull InventoryView transaction) {
/* 16 */     super(transaction);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HumanEntity getWhoClicked() {
/* 26 */     return getView().getPlayer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setResult(@NotNull Event.Result newResult) {
/* 37 */     this.result = newResult;
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
/*    */   public Event.Result getResult() {
/* 49 */     return this.result;
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
/*    */   
/*    */   public boolean isCancelled() {
/* 64 */     return (getResult() == Event.Result.DENY);
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
/*    */   public void setCancelled(boolean toCancel) {
/* 78 */     setResult(toCancel ? Event.Result.DENY : Event.Result.ALLOW);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryInteractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */