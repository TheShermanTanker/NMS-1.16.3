/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAddToWorldEvent
/*    */   extends EntityEvent
/*    */ {
/*    */   public EntityAddToWorldEvent(@NotNull Entity entity) {
/* 18 */     super(entity);
/*    */   }
/*    */   
/* 21 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 25 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 30 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityAddToWorldEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */