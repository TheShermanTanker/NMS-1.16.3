/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerLeashEntityEvent
/*    */   extends Event
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Entity leashHolder;
/*    */   private final Entity entity;
/*    */   private boolean cancelled = false;
/*    */   private final Player player;
/*    */   
/*    */   public PlayerLeashEntityEvent(@NotNull Entity what, @NotNull Entity leashHolder, @NotNull Player leasher) {
/* 21 */     this.leashHolder = leashHolder;
/* 22 */     this.entity = what;
/* 23 */     this.player = leasher;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getLeashHolder() {
/* 33 */     return this.leashHolder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getEntity() {
/* 43 */     return this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final Player getPlayer() {
/* 53 */     return this.player;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 59 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 64 */     return handlers;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 69 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 74 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\PlayerLeashEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */