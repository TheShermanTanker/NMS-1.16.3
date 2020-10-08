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
/*    */ public interface StonecutterInventory
/*    */   extends Inventory
/*    */ {
/*    */   @Nullable
/*    */   default ItemStack getInputItem() {
/* 18 */     return getItem(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setInputItem(@Nullable ItemStack itemStack) {
/* 27 */     setItem(0, itemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default ItemStack getResult() {
/* 37 */     return getItem(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setResult(@Nullable ItemStack itemStack) {
/* 46 */     setItem(1, itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\StonecutterInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */