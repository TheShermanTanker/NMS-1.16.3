/*    */ package com.destroystokyo.paper.inventory;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class ItemStackRecipeChoice
/*    */   implements RecipeChoice
/*    */ {
/* 16 */   protected final List<ItemStack> choices = new ArrayList<>();
/*    */   
/*    */   public ItemStackRecipeChoice(ItemStack choices) {
/* 19 */     this.choices.add(choices);
/*    */   }
/*    */   
/*    */   public ItemStackRecipeChoice(List<ItemStack> choices) {
/* 23 */     this.choices.addAll(choices);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack() {
/* 28 */     return this.choices.isEmpty() ? null : this.choices.get(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeChoice clone() {
/*    */     try {
/* 34 */       ItemStackRecipeChoice clone = (ItemStackRecipeChoice)super.clone();
/* 35 */       clone.choices.addAll(this.choices);
/* 36 */       return clone;
/* 37 */     } catch (CloneNotSupportedException ex) {
/* 38 */       throw new AssertionError(ex);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(ItemStack itemStack) {
/* 44 */     for (ItemStack stack : this.choices) {
/* 45 */       if (stack.isSimilar(itemStack)) {
/* 46 */         return true;
/*    */       }
/*    */     } 
/* 49 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\inventory\ItemStackRecipeChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */