/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VehicleExitEvent
/*    */   extends VehicleEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final LivingEntity exited;
/*    */   private final boolean isCancellable;
/*    */   
/*    */   public VehicleExitEvent(@NotNull Vehicle vehicle, @NotNull LivingEntity exited, boolean isCancellable) {
/* 19 */     super(vehicle);
/* 20 */     this.exited = exited;
/*    */     
/* 22 */     this.isCancellable = isCancellable;
/*    */   }
/*    */   
/*    */   public VehicleExitEvent(@NotNull Vehicle vehicle, @NotNull LivingEntity exited) {
/* 26 */     this(vehicle, exited, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getExited() {
/* 37 */     return this.exited;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 42 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 48 */     if (cancel && !this.isCancellable) {
/*    */       return;
/*    */     }
/* 51 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   public boolean isCancellable() {
/* 55 */     return this.isCancellable;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleExitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */