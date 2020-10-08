/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerNaturallySpawnCreaturesEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private byte radius;
/*    */   
/*    */   public PlayerNaturallySpawnCreaturesEvent(@NotNull Player player, byte radius) {
/* 17 */     super(player);
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
/* 47 */     this.cancelled = false;
/*    */     this.radius = radius;
/*    */   } public byte getSpawnRadius() {
/*    */     return this.radius;
/*    */   } public void setSpawnRadius(byte radius) {
/*    */     this.radius = radius;
/*    */   } private static final HandlerList handlers = new HandlerList(); public boolean isCancelled() {
/* 54 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean cancelled;
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 62 */     this.cancelled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\PlayerNaturallySpawnCreaturesEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */