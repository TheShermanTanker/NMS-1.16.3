/*    */ package org.bukkit.inventory;
/*    */ 
/*    */ import org.bukkit.block.Lectern;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface LecternInventory
/*    */   extends Inventory
/*    */ {
/*    */   @Nullable
/*    */   default ItemStack getBook() {
/* 23 */     return getItem(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setBook(@Nullable ItemStack book) {
/* 32 */     setItem(0, book);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   Lectern getHolder();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\LecternInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */