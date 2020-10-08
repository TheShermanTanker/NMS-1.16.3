/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class VehicleMoveEvent
/*    */   extends VehicleEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Location from;
/*    */   private final Location to;
/*    */   
/*    */   public VehicleMoveEvent(@NotNull Vehicle vehicle, @NotNull Location from, @NotNull Location to) {
/* 17 */     super(vehicle);
/*    */     
/* 19 */     this.from = from;
/* 20 */     this.to = to;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getFrom() {
/* 30 */     return this.from;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getTo() {
/* 40 */     return this.to;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 47 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 52 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleMoveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */