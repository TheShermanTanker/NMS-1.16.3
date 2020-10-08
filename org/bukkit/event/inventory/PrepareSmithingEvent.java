/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.SmithingInventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PrepareSmithingEvent
/*    */   extends PrepareResultEvent
/*    */ {
/*    */   public PrepareSmithingEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
/* 21 */     super(inventory, result);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SmithingInventory getInventory() {
/* 29 */     return (SmithingInventory)super.getInventory();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ItemStack getResult() {
/* 39 */     return super.getResult();
/*    */   }
/*    */   
/*    */   public void setResult(@Nullable ItemStack result) {
/* 43 */     super.setResult(result);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\PrepareSmithingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */