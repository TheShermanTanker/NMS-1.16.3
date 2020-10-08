/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Explosive;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ExplosionPrimeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private float radius;
/*    */   private boolean fire;
/*    */   
/*    */   public ExplosionPrimeEvent(@NotNull Entity what, float radius, boolean fire) {
/* 19 */     super(what);
/* 20 */     this.cancel = false;
/* 21 */     this.radius = radius;
/* 22 */     this.fire = fire;
/*    */   }
/*    */   
/*    */   public ExplosionPrimeEvent(@NotNull Explosive explosive) {
/* 26 */     this((Entity)explosive, explosive.getYield(), explosive.isIncendiary());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 31 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 36 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getRadius() {
/* 45 */     return this.radius;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRadius(float radius) {
/* 54 */     this.radius = radius;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getFire() {
/* 63 */     return this.fire;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFire(boolean fire) {
/* 72 */     this.fire = fire;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 78 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 83 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\ExplosionPrimeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */