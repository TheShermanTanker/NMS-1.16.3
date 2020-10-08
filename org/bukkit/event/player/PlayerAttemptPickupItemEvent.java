/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerAttemptPickupItemEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   private final Item item;
/*    */   private final int remaining;
/*    */   
/*    */   @Deprecated
/*    */   public PlayerAttemptPickupItemEvent(@NotNull Player player, @NotNull Item item) {
/* 21 */     this(player, item, 0);
/*    */   }
/*    */   private boolean flyAtPlayer = true; private boolean isCancelled = false;
/*    */   public PlayerAttemptPickupItemEvent(@NotNull Player player, @NotNull Item item, int remaining) {
/* 25 */     super(player);
/* 26 */     this.item = item;
/* 27 */     this.remaining = remaining;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getItem() {
/* 37 */     return this.item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRemaining() {
/* 46 */     return this.remaining;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFlyAtPlayer(boolean flyAtPlayer) {
/* 56 */     this.flyAtPlayer = flyAtPlayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getFlyAtPlayer() {
/* 65 */     return this.flyAtPlayer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 71 */     return this.isCancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 76 */     this.isCancelled = cancel;
/* 77 */     this.flyAtPlayer = !cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 83 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerAttemptPickupItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */