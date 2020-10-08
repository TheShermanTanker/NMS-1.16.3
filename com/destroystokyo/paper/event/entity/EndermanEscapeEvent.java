/*    */ package com.destroystokyo.paper.event.entity;public class EndermanEscapeEvent extends EntityEvent implements Cancellable { @NotNull
/*    */   private final Reason reason;
/*    */   
/*    */   @NotNull
/*    */   public Enderman getEntity() {
/*    */     return (Enderman)super.getEntity();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Reason getReason() {
/*    */     return this.reason;
/*    */   }
/*    */   
/*    */   public EndermanEscapeEvent(@NotNull Enderman entity, @NotNull Reason reason) {
/* 15 */     super((Entity)entity);
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
/* 45 */     this.cancelled = false;
/*    */     this.reason = reason;
/*    */   } private static final HandlerList handlers = new HandlerList();
/*    */   public boolean isCancelled() {
/* 49 */     return this.cancelled;
/*    */   }
/*    */   private boolean cancelled;
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/*    */     return handlers;
/*    */   }
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/*    */     return handlers;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 62 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Reason
/*    */   {
/* 69 */     RUNAWAY,
/*    */ 
/*    */ 
/*    */     
/* 73 */     INDIRECT,
/*    */ 
/*    */ 
/*    */     
/* 77 */     CRITICAL_HIT,
/*    */ 
/*    */ 
/*    */     
/* 81 */     STARE,
/*    */ 
/*    */ 
/*    */     
/* 85 */     DROWN;
/*    */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EndermanEscapeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */