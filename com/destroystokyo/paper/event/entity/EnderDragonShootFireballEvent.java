/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.DragonFireball;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EnderDragonShootFireballEvent
/*    */   extends EntityEvent implements Cancellable {
/*    */   @NotNull
/*    */   private final DragonFireball fireball;
/*    */   
/*    */   public EnderDragonShootFireballEvent(@NotNull EnderDragon entity, @NotNull DragonFireball fireball) {
/* 17 */     super((Entity)entity);
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
/*    */ 
/*    */ 
/*    */     
/* 50 */     this.cancelled = false;
/*    */     this.fireball = fireball; }
/*    */   @NotNull public EnderDragon getEntity() { return (EnderDragon)super.getEntity(); }
/*    */   @NotNull
/* 54 */   public DragonFireball getFireball() { return this.fireball; } private static final HandlerList handlers = new HandlerList(); public boolean isCancelled() { return this.cancelled; }
/*    */   private boolean cancelled;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() { return handlers; } @NotNull
/*    */   public static HandlerList getHandlerList() { return handlers; } public void setCancelled(boolean cancel) {
/* 59 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EnderDragonShootFireballEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */