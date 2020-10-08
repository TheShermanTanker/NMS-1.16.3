/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface AnvilInventory
/*     */   extends Inventory
/*     */ {
/*     */   @Nullable
/*     */   String getRenameText();
/*     */   
/*     */   int getRepairCost();
/*     */   
/*     */   void setRepairCost(int paramInt);
/*     */   
/*     */   int getMaximumRepairCost();
/*     */   
/*     */   void setMaximumRepairCost(int paramInt);
/*     */   
/*     */   @Nullable
/*     */   default ItemStack getFirstItem() {
/*  61 */     return getItem(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void setFirstItem(@Nullable ItemStack firstItem) {
/*  70 */     setItem(0, firstItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default ItemStack getSecondItem() {
/*  80 */     return getItem(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void setSecondItem(@Nullable ItemStack secondItem) {
/*  89 */     setItem(1, secondItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default ItemStack getResult() {
/*  99 */     return getItem(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void setResult(@Nullable ItemStack result) {
/* 109 */     setItem(2, result);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\AnvilInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */