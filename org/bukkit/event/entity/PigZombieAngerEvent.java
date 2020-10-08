/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.PigZombie;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PigZombieAngerEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean canceled;
/*    */   private final Entity target;
/*    */   private int newAnger;
/*    */   
/*    */   public PigZombieAngerEvent(@NotNull PigZombie pigZombie, @Nullable Entity target, int newAnger) {
/* 23 */     super((Entity)pigZombie);
/* 24 */     this.target = target;
/* 25 */     this.newAnger = newAnger;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getTarget() {
/* 35 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNewAnger() {
/* 45 */     return this.newAnger;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNewAnger(int newAnger) {
/* 55 */     this.newAnger = newAnger;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PigZombie getEntity() {
/* 61 */     return (PigZombie)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 66 */     return this.canceled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 71 */     this.canceled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 77 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 82 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\PigZombieAngerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */