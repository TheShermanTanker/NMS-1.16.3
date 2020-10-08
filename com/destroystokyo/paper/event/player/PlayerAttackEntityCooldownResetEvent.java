/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerAttackEntityCooldownResetEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private final float cooledAttackStrength;
/*    */   private boolean cancel = false;
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */   
/*    */   public PlayerAttackEntityCooldownResetEvent(@NotNull Player who, @NotNull Entity attackedEntity, float cooledAttackStrength) {
/* 21 */     super(who);
/* 22 */     this.attackedEntity = attackedEntity;
/* 23 */     this.cooledAttackStrength = cooledAttackStrength;
/*    */   } @NotNull
/*    */   private final Entity attackedEntity;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 28 */     return handlers;
/*    */   }
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 32 */     return handlers;
/*    */   }
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
/*    */   public boolean isCancelled() {
/* 45 */     return this.cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 55 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getCooledAttackStrength() {
/* 64 */     return this.cooledAttackStrength;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getAttackedEntity() {
/* 74 */     return this.attackedEntity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerAttackEntityCooldownResetEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */