/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerDropItemEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Item drop;
/*    */   private boolean cancel = false;
/*    */   
/*    */   public PlayerDropItemEvent(@NotNull Player player, @NotNull Item drop) {
/* 18 */     super(player);
/* 19 */     this.drop = drop;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Item getItemDrop() {
/* 29 */     return this.drop;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 34 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 39 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 45 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 50 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerDropItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */