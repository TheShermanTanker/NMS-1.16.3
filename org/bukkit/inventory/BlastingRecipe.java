/*    */ package org.bukkit.inventory;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlastingRecipe
/*    */   extends CookingRecipe<BlastingRecipe>
/*    */ {
/*    */   public BlastingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, float experience, int cookingTime) {
/* 13 */     super(key, result, source, experience, cookingTime);
/*    */   }
/*    */   
/*    */   public BlastingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input, float experience, int cookingTime) {
/* 17 */     super(key, result, input, experience, cookingTime);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\BlastingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */