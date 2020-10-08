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
/*    */ public interface GrindstoneInventory
/*    */   extends Inventory
/*    */ {
/*    */   @Nullable
/*    */   default ItemStack getUpperItem() {
/* 18 */     return getItem(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setUpperItem(@Nullable ItemStack upperItem) {
/* 27 */     setItem(0, upperItem);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default ItemStack getLowerItem() {
/* 37 */     return getItem(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setLowerItem(@Nullable ItemStack lowerItem) {
/* 46 */     setItem(1, lowerItem);
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
/*    */   default void setResult(@Nullable ItemStack result) {
/* 65 */     setItem(2, result);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\GrindstoneInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */