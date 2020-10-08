/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerAnimationEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */   
/*    */   private final PlayerAnimationType animationType;
/*    */ 
/*    */   
/*    */   private boolean isCancelled = false;
/*    */ 
/*    */   
/*    */   public PlayerAnimationEvent(@NotNull Player player) {
/* 22 */     super(player);
/*    */ 
/*    */     
/* 25 */     this.animationType = PlayerAnimationType.ARM_SWING;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PlayerAnimationType getAnimationType() {
/* 35 */     return this.animationType;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 40 */     return this.isCancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 45 */     this.isCancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 51 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 56 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerAnimationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */