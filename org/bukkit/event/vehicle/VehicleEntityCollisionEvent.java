/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VehicleEntityCollisionEvent
/*    */   extends VehicleCollisionEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Entity entity;
/*    */   private boolean cancelled = false;
/*    */   private boolean cancelledPickup = false;
/*    */   private boolean cancelledCollision = false;
/*    */   
/*    */   public VehicleEntityCollisionEvent(@NotNull Vehicle vehicle, @NotNull Entity entity) {
/* 20 */     super(vehicle);
/* 21 */     this.entity = entity;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Entity getEntity() {
/* 26 */     return this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 31 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 36 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   public boolean isPickupCancelled() {
/* 40 */     return this.cancelledPickup;
/*    */   }
/*    */   
/*    */   public void setPickupCancelled(boolean cancel) {
/* 44 */     this.cancelledPickup = cancel;
/*    */   }
/*    */   
/*    */   public boolean isCollisionCancelled() {
/* 48 */     return this.cancelledCollision;
/*    */   }
/*    */   
/*    */   public void setCollisionCancelled(boolean cancel) {
/* 52 */     this.cancelledCollision = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleEntityCollisionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */