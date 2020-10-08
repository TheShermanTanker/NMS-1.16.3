/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class PlayerReadyArrowEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final ItemStack bow;
/*    */   @NotNull
/*    */   private final ItemStack arrow;
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getBow() {
/*    */     return this.bow;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getArrow() {
/*    */     return this.arrow;
/*    */   }
/*    */   
/*    */   public PlayerReadyArrowEvent(@NotNull Player player, @NotNull ItemStack bow, @NotNull ItemStack arrow) {
/* 42 */     super(player);
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
/* 75 */     this.cancelled = false;
/*    */     this.bow = bow;
/*    */     this.arrow = arrow;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 82 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 91 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/*    */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/*    */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerReadyArrowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */