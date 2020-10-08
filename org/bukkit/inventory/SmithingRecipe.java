/*    */ package org.bukkit.inventory;
/*    */ 
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class SmithingRecipe
/*    */   implements Recipe, Keyed
/*    */ {
/*    */   private final NamespacedKey key;
/*    */   private final ItemStack result;
/*    */   private final RecipeChoice base;
/*    */   private final RecipeChoice addition;
/*    */   
/*    */   public SmithingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice base, @NotNull RecipeChoice addition) {
/* 26 */     this.key = key;
/* 27 */     this.result = result;
/* 28 */     this.base = base;
/* 29 */     this.addition = addition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RecipeChoice getBase() {
/* 39 */     return this.base.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RecipeChoice getAddition() {
/* 49 */     return this.addition.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getResult() {
/* 55 */     return this.result.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getKey() {
/* 61 */     return this.key;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\SmithingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */