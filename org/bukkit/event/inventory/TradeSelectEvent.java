/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.Merchant;
/*    */ import org.bukkit.inventory.MerchantInventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TradeSelectEvent
/*    */   extends InventoryInteractEvent
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final int index;
/*    */   
/*    */   public TradeSelectEvent(@NotNull InventoryView transaction, int newIndex) {
/* 23 */     super(transaction);
/* 24 */     this.index = newIndex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIndex() {
/* 33 */     return this.index;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MerchantInventory getInventory() {
/* 39 */     return (MerchantInventory)super.getInventory();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Merchant getMerchant() {
/* 49 */     return getInventory().getMerchant();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 55 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 60 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\TradeSelectEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */