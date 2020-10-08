/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LootableInventoryReplenishEvent extends PlayerEvent implements Cancellable {
/*    */   @NotNull
/*    */   private final LootableInventory inventory;
/*    */   
/* 13 */   public LootableInventoryReplenishEvent(@NotNull Player player, @NotNull LootableInventory inventory) { super(player);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     this.cancelled = false;
/*    */     this.inventory = inventory; }
/*    */   @NotNull
/*    */   public LootableInventory getInventory() { return this.inventory; }
/* 38 */   private static final HandlerList handlers = new HandlerList(); public boolean isCancelled() { return this.cancelled; }
/*    */   private boolean cancelled;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() { return handlers; } @NotNull
/*    */   public static HandlerList getHandlerList() { return handlers; } public void setCancelled(boolean cancel) {
/* 43 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\LootableInventoryReplenishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */