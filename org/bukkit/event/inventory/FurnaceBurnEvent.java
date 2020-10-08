/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.block.BlockEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FurnaceBurnEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final ItemStack fuel;
/*    */   private int burnTime;
/*    */   private boolean cancelled;
/*    */   private boolean burning;
/*    */   
/*    */   public FurnaceBurnEvent(@NotNull Block furnace, @NotNull ItemStack fuel, int burnTime) {
/* 21 */     super(furnace);
/* 22 */     this.fuel = fuel;
/* 23 */     this.burnTime = burnTime;
/* 24 */     this.cancelled = false;
/* 25 */     this.burning = true;
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
/*    */   public int getBurnTime() {
/* 44 */     return this.burnTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBurnTime(int burnTime) {
/* 53 */     this.burnTime = burnTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBurning() {
/* 62 */     return this.burning;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBurning(boolean burning) {
/* 71 */     this.burning = burning;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 76 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 81 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 87 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 92 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\FurnaceBurnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */