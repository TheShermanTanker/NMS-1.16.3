/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Slime;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlimePathfindEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/*    */   public SlimePathfindEvent(@NotNull Slime slime) {
/* 17 */     super((Entity)slime);
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
/* 42 */     this.cancelled = false;
/*    */   } @NotNull
/*    */   public Slime getEntity() {
/*    */     return (Slime)this.entity;
/* 46 */   } private static final HandlerList handlers = new HandlerList(); public boolean isCancelled() { return this.cancelled; }
/*    */   private boolean cancelled;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() { return handlers; } @NotNull
/*    */   public static HandlerList getHandlerList() { return handlers; } public void setCancelled(boolean cancel) {
/* 51 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\SlimePathfindEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */