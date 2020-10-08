/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.entity.EntityUnleashEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerUnleashEntityEvent
/*    */   extends EntityUnleashEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private final Player player;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public PlayerUnleashEntityEvent(@NotNull Entity entity, @NotNull Player player) {
/* 17 */     super(entity, EntityUnleashEvent.UnleashReason.PLAYER_UNLEASH);
/* 18 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 28 */     return this.player;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 33 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 38 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerUnleashEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */