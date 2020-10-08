/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.AbstractHorse;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class HorseJumpEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private float power;
/*    */   
/*    */   public HorseJumpEvent(@NotNull AbstractHorse horse, float power) {
/* 17 */     super((Entity)horse);
/* 18 */     this.power = power;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 23 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setCancelled(boolean cancel) {
/* 32 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractHorse getEntity() {
/* 38 */     return (AbstractHorse)this.entity;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getPower() {
/* 59 */     return this.power;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setPower(float power) {
/* 77 */     this.power = power;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 83 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\HorseJumpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */