/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryEvent
/*    */   extends Event
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   protected InventoryView transaction;
/*    */   
/*    */   public InventoryEvent(@NotNull InventoryView transaction) {
/* 20 */     this.transaction = transaction;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Inventory getInventory() {
/* 30 */     return this.transaction.getTopInventory();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<HumanEntity> getViewers() {
/* 41 */     return this.transaction.getTopInventory().getViewers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public InventoryView getView() {
/* 51 */     return this.transaction;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */