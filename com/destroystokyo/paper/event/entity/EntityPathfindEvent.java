/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityPathfindEvent extends EntityEvent implements Cancellable {
/*    */   @Nullable
/*    */   private final Entity targetEntity;
/*    */   @NotNull
/*    */   private final Location loc;
/*    */   
/*    */   @NotNull
/*    */   public Entity getEntity() {
/*    */     return this.entity;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity getTargetEntity() {
/*    */     return this.targetEntity;
/*    */   }
/*    */   
/* 22 */   public EntityPathfindEvent(@NotNull Entity entity, @NotNull Location loc, @Nullable Entity targetEntity) { super(entity);
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
/* 71 */     this.cancelled = false;
/*    */     this.targetEntity = targetEntity;
/*    */     this.loc = loc; }
/*    */    public boolean isCancelled() {
/* 75 */     return this.cancelled;
/*    */   } @NotNull
/*    */   public Location getLoc() {
/*    */     return this.loc;
/*    */   } private static final HandlerList handlers = new HandlerList(); private boolean cancelled; public void setCancelled(boolean cancel) {
/* 80 */     this.cancelled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityPathfindEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */