/*    */ package org.bukkit.inventory;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.material.MaterialData;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FurnaceRecipe
/*    */   extends CookingRecipe<FurnaceRecipe>
/*    */ {
/*    */   @Deprecated
/*    */   public FurnaceRecipe(@NotNull ItemStack result, @NotNull Material source) {
/* 16 */     this(NamespacedKey.randomKey(), result, source, 0, 0.0F, 200);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public FurnaceRecipe(@NotNull ItemStack result, @NotNull MaterialData source) {
/* 21 */     this(NamespacedKey.randomKey(), result, source.getItemType(), source.getData(), 0.0F, 200);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public FurnaceRecipe(@NotNull ItemStack result, @NotNull MaterialData source, float experience) {
/* 26 */     this(NamespacedKey.randomKey(), result, source.getItemType(), source.getData(), experience, 200);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public FurnaceRecipe(@NotNull ItemStack result, @NotNull Material source, int data) {
/* 31 */     this(NamespacedKey.randomKey(), result, source, data, 0.0F, 200);
/*    */   }
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
/*    */   public FurnaceRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, float experience, int cookingTime) {
/* 44 */     this(key, result, source, 0, experience, cookingTime);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public FurnaceRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, int data, float experience, int cookingTime) {
/* 49 */     this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)), experience, cookingTime);
/*    */   }
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
/*    */   public FurnaceRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input, float experience, int cookingTime) {
/* 62 */     super(key, result, input, experience, cookingTime);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public FurnaceRecipe setInput(@NotNull MaterialData input) {
/* 73 */     return setInput(input.getItemType(), input.getData());
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public FurnaceRecipe setInput(@NotNull Material input) {
/* 79 */     return (FurnaceRecipe)super.setInput(input);
/*    */   }
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
/*    */   @Deprecated
/*    */   public FurnaceRecipe setInput(@NotNull Material input, int data) {
/* 93 */     return setInputChoice(new RecipeChoice.MaterialChoice(Collections.singletonList(input)));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public FurnaceRecipe setInputChoice(@NotNull RecipeChoice input) {
/* 99 */     return super.setInputChoice(input);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\FurnaceRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */