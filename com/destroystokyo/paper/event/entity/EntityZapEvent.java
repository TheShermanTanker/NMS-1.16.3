/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LightningStrike;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityTransformEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityZapEvent
/*    */   extends EntityTransformEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   
/*    */   public EntityZapEvent(@NotNull Entity entity, @NotNull LightningStrike bolt, @NotNull Entity replacementEntity) {
/* 23 */     super(entity, Collections.singletonList(replacementEntity), EntityTransformEvent.TransformReason.LIGHTNING);
/* 24 */     Validate.notNull(bolt);
/* 25 */     Validate.notNull(replacementEntity);
/* 26 */     this.bolt = bolt;
/*    */   } @NotNull
/*    */   private final LightningStrike bolt;
/*    */   public boolean isCancelled() {
/* 30 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 34 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LightningStrike getBolt() {
/* 43 */     return this.bolt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getReplacementEntity() {
/* 52 */     return getTransformedEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 58 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 63 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityZapEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */