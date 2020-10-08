/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class VehicleCollisionEvent
/*    */   extends VehicleEvent
/*    */ {
/*    */   public VehicleCollisionEvent(@NotNull Vehicle vehicle) {
/* 11 */     super(vehicle);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleCollisionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */