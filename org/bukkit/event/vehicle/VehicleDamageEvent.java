/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class VehicleDamageEvent
/*    */   extends VehicleEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Entity attacker;
/*    */   private double damage;
/*    */   private boolean cancelled;
/*    */   
/*    */   public VehicleDamageEvent(@NotNull Vehicle vehicle, @Nullable Entity attacker, double damage) {
/* 20 */     super(vehicle);
/* 21 */     this.attacker = attacker;
/* 22 */     this.damage = damage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getAttacker() {
/* 32 */     return this.attacker;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getDamage() {
/* 41 */     return this.damage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDamage(double damage) {
/* 50 */     this.damage = damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 55 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 60 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 66 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 71 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleDamageEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */