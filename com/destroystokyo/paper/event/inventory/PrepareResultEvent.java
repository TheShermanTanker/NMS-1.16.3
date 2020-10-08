/*    */ package com.destroystokyo.paper.event.inventory;
/*    */ 
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.inventory.InventoryEvent;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PrepareResultEvent
/*    */   extends InventoryEvent
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private ItemStack result;
/*    */   
/*    */   public PrepareResultEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
/* 20 */     super(inventory);
/* 21 */     this.result = result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ItemStack getResult() {
/* 31 */     return this.result;
/*    */   }
/*    */   
/*    */   public void setResult(@Nullable ItemStack result) {
/* 35 */     this.result = result;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 41 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 46 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\inventory\PrepareResultEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */