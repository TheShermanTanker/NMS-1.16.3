/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.block.BlockEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrewingStandFuelEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final ItemStack fuel;
/*    */   private int fuelPower;
/*    */   private boolean cancelled;
/*    */   private boolean consuming = true;
/*    */   
/*    */   public BrewingStandFuelEvent(@NotNull Block brewingStand, @NotNull ItemStack fuel, int fuelPower) {
/* 23 */     super(brewingStand);
/* 24 */     this.fuel = fuel;
/* 25 */     this.fuelPower = fuelPower;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getFuel() {
/* 35 */     return this.fuel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFuelPower() {
/* 45 */     return this.fuelPower;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFuelPower(int fuelPower) {
/* 55 */     this.fuelPower = fuelPower;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isConsuming() {
/* 64 */     return this.consuming;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConsuming(boolean consuming) {
/* 73 */     this.consuming = consuming;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 78 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 83 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 89 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 94 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\BrewingStandFuelEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */