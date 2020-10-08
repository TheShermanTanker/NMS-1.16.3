/*    */ package org.bukkit.event.vehicle;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class VehicleBlockCollisionEvent
/*    */   extends VehicleCollisionEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Block block;
/*    */   
/*    */   public VehicleBlockCollisionEvent(@NotNull Vehicle vehicle, @NotNull Block block) {
/* 16 */     super(vehicle);
/* 17 */     this.block = block;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getBlock() {
/* 27 */     return this.block;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 33 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 38 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\vehicle\VehicleBlockCollisionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */