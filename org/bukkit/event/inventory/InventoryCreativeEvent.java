/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryCreativeEvent
/*    */   extends InventoryClickEvent
/*    */ {
/*    */   private ItemStack item;
/*    */   
/*    */   public InventoryCreativeEvent(@NotNull InventoryView what, @NotNull InventoryType.SlotType type, int slot, @NotNull ItemStack newItem) {
/* 17 */     super(what, type, slot, ClickType.CREATIVE, InventoryAction.PLACE_ALL);
/* 18 */     this.item = newItem;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getCursor() {
/* 24 */     return this.item;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCursor(@NotNull ItemStack item) {
/* 29 */     this.item = item;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryCreativeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */