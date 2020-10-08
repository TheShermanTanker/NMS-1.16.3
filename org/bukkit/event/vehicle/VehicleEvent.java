/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class VehicleEvent
/*    */   extends Event
/*    */ {
/*    */   protected Vehicle vehicle;
/*    */   
/*    */   public VehicleEvent(@NotNull Vehicle vehicle) {
/* 14 */     this.vehicle = vehicle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final Vehicle getVehicle() {
/* 24 */     return this.vehicle;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */