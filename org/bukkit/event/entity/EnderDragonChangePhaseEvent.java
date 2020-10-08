/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class EnderDragonChangePhaseEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private final EnderDragon.Phase currentPhase;
/*    */   private EnderDragon.Phase newPhase;
/*    */   
/*    */   public EnderDragonChangePhaseEvent(@NotNull EnderDragon enderDragon, @Nullable EnderDragon.Phase currentPhase, @NotNull EnderDragon.Phase newPhase) {
/* 21 */     super((Entity)enderDragon);
/* 22 */     this.currentPhase = currentPhase;
/* 23 */     setNewPhase(newPhase);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EnderDragon getEntity() {
/* 29 */     return (EnderDragon)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public EnderDragon.Phase getCurrentPhase() {
/* 40 */     return this.currentPhase;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EnderDragon.Phase getNewPhase() {
/* 50 */     return this.newPhase;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNewPhase(@NotNull EnderDragon.Phase newPhase) {
/* 59 */     Validate.notNull(newPhase, "New dragon phase cannot be null");
/* 60 */     this.newPhase = newPhase;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 65 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 70 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 76 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 81 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EnderDragonChangePhaseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */