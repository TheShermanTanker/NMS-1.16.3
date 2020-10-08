/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityAirChangeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private int amount;
/*    */   
/*    */   private boolean cancelled;
/*    */   
/*    */   public EntityAirChangeEvent(@NotNull Entity what, int amount) {
/* 20 */     super(what);
/* 21 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAmount() {
/* 30 */     return this.amount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAmount(int amount) {
/* 39 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 44 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 49 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 55 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 60 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityAirChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */