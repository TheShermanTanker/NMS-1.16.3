/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ArrowBodyCountChangeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private final boolean isReset;
/*    */   private final int oldAmount;
/*    */   private int newAmount;
/*    */   
/*    */   public ArrowBodyCountChangeEvent(@NotNull LivingEntity entity, int oldAmount, int newAmount, boolean isReset) {
/* 22 */     super((Entity)entity);
/*    */     
/* 24 */     this.oldAmount = oldAmount;
/* 25 */     this.newAmount = newAmount;
/* 26 */     this.isReset = isReset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReset() {
/* 35 */     return this.isReset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOldAmount() {
/* 44 */     return this.oldAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNewAmount() {
/* 53 */     return this.newAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNewAmount(int newAmount) {
/* 62 */     Preconditions.checkArgument((newAmount >= 0), "New arrow amount must be >= 0");
/* 63 */     this.newAmount = newAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 69 */     return (LivingEntity)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 74 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 79 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 85 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 90 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ArrowBodyCountChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */