/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerVelocityEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel = false;
/*    */   private Vector velocity;
/*    */   
/*    */   public PlayerVelocityEvent(@NotNull Player player, @NotNull Vector velocity) {
/* 18 */     super(player);
/* 19 */     this.velocity = velocity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 24 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 29 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Vector getVelocity() {
/* 39 */     return this.velocity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVelocity(@NotNull Vector velocity) {
/* 48 */     this.velocity = velocity;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 54 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 59 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerVelocityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */