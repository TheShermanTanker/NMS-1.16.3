/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class EntityTransformedEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Entity transformed;
/*    */   private final TransformedReason reason;
/*    */   
/*    */   public EntityTransformedEvent(Entity entity, Entity transformed, TransformedReason reason) {
/* 24 */     super(entity);
/* 25 */     this.transformed = transformed;
/* 26 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Entity getTransformed() {
/* 37 */     return this.transformed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public TransformedReason getReason() {
/* 46 */     return this.reason;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HandlerList getHandlers() {
/* 52 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 56 */     return handlers;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 61 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 66 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum TransformedReason
/*    */   {
/* 73 */     DROWNED,
/*    */ 
/*    */ 
/*    */     
/* 77 */     CURED,
/*    */ 
/*    */ 
/*    */     
/* 81 */     INFECTED,
/*    */ 
/*    */ 
/*    */     
/* 85 */     SHEARED,
/*    */ 
/*    */ 
/*    */     
/* 89 */     LIGHTNING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityTransformedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */