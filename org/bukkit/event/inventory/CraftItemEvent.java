/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.inventory.CraftingInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class CraftItemEvent
/*    */   extends InventoryClickEvent
/*    */ {
/*    */   private Recipe recipe;
/*    */   
/*    */   public CraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView what, @NotNull InventoryType.SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action) {
/* 16 */     super(what, type, slot, click, action);
/* 17 */     this.recipe = recipe;
/*    */   }
/*    */   
/*    */   public CraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView what, @NotNull InventoryType.SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action, int key) {
/* 21 */     super(what, type, slot, click, action, key);
/* 22 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Recipe getRecipe() {
/* 30 */     return this.recipe;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public CraftingInventory getInventory() {
/* 36 */     return (CraftingInventory)super.getInventory();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\CraftItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */