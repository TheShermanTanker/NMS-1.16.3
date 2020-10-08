/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.Warning;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ @Warning(false)
/*    */ public class PlayerPickupItemEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Item item;
/*    */   private boolean flyAtPlayer = true;
/*    */   private boolean cancel = false;
/*    */   private final int remaining;
/*    */   
/*    */   public PlayerPickupItemEvent(@NotNull Player player, @NotNull Item item, int remaining) {
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
/*    */   
/*    */   public void setFlyAtPlayer(boolean flyAtPlayer) {
/* 57 */     this.flyAtPlayer = flyAtPlayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getFlyAtPlayer() {
/* 66 */     return this.flyAtPlayer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 72 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 77 */     this.cancel = cancel;
/* 78 */     this.flyAtPlayer = !cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerPickupItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */