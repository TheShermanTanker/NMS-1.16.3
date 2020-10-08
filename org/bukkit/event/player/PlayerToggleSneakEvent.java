/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerToggleSneakEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final boolean isSneaking;
/*    */   private boolean cancel = false;
/*    */   
/*    */   public PlayerToggleSneakEvent(@NotNull Player player, boolean isSneaking) {
/* 17 */     super(player);
/* 18 */     this.isSneaking = isSneaking;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSneaking() {
/* 27 */     return this.isSneaking;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 32 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 37 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 43 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 48 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerToggleSneakEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */