/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.block.BlockEvent;
/*    */ import org.bukkit.inventory.BrewerInventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class BrewEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private BrewerInventory contents;
/*    */   private int fuelLevel;
/*    */   private boolean cancelled;
/*    */   
/*    */   public BrewEvent(@NotNull Block brewer, @NotNull BrewerInventory contents, int fuelLevel) {
/* 21 */     super(brewer);
/* 22 */     this.contents = contents;
/* 23 */     this.fuelLevel = fuelLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BrewerInventory getContents() {
/* 33 */     return this.contents;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFuelLevel() {
/* 42 */     return this.fuelLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 47 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 52 */     this.cancelled = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\BrewEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */