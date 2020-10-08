/*    */ package org.bukkit.inventory;
/*    */ 
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface SmithingInventory
/*    */   extends Inventory
/*    */ {
/*    */   @Nullable
/*    */   default ItemStack getInputEquipment() {
/* 18 */     return getItem(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setInputEquipment(@Nullable ItemStack itemStack) {
/* 27 */     setItem(0, itemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default ItemStack getInputMineral() {
/* 37 */     return getItem(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setInputMineral(@Nullable ItemStack itemStack) {
/* 46 */     setItem(1, itemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default ItemStack getResult() {
/* 56 */     return getItem(2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setResult(@Nullable ItemStack itemStack) {
/* 65 */     setItem(2, itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\SmithingInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */