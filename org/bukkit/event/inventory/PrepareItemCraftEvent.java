/*    */ package org.bukkit.event.inventory;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.CraftingInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class PrepareItemCraftEvent extends InventoryEvent {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean repair;
/*    */   private CraftingInventory matrix;
/*    */   
/*    */   public PrepareItemCraftEvent(@NotNull CraftingInventory what, @NotNull InventoryView view, boolean isRepair) {
/* 16 */     super(view);
/* 17 */     this.matrix = what;
/* 18 */     this.repair = isRepair;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Recipe getRecipe() {
/* 30 */     return this.matrix.getRecipe();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public CraftingInventory getInventory() {
/* 39 */     return this.matrix;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRepair() {
/* 49 */     return this.repair;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 55 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 60 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\PrepareItemCraftEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */