/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.Collections;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CookingRecipe<T extends CookingRecipe>
/*     */   implements Recipe, Keyed
/*     */ {
/*     */   private final NamespacedKey key;
/*     */   private ItemStack output;
/*     */   private RecipeChoice ingredient;
/*     */   private float experience;
/*     */   private int cookingTime;
/*  20 */   private String group = "";
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
/*     */   public CookingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, float experience, int cookingTime) {
/*  32 */     this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)), experience, cookingTime);
/*     */   }
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
/*     */   public CookingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input, float experience, int cookingTime) {
/*  45 */     Preconditions.checkArgument((result.getType() != Material.AIR), "Recipe must have non-AIR result.");
/*  46 */     this.key = key;
/*  47 */     this.output = new ItemStack(result);
/*  48 */     this.ingredient = input;
/*  49 */     this.experience = experience;
/*  50 */     this.cookingTime = cookingTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CookingRecipe setInput(@NotNull Material input) {
/*  61 */     this.ingredient = new RecipeChoice.MaterialChoice(Collections.singletonList(input));
/*  62 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getInput() {
/*  72 */     return this.ingredient.getItemStack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public T setInputChoice(@NotNull RecipeChoice input) {
/*  83 */     this.ingredient = input;
/*  84 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RecipeChoice getInputChoice() {
/*  94 */     return this.ingredient.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getResult() {
/* 105 */     return this.output.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExperience(float experience) {
/* 114 */     this.experience = experience;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getExperience() {
/* 123 */     return this.experience;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCookingTime(int cookingTime) {
/* 132 */     Preconditions.checkArgument((cookingTime >= 0), "cookingTime must be >= 0");
/* 133 */     this.cookingTime = cookingTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCookingTime() {
/* 142 */     return this.cookingTime;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 148 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getGroup() {
/* 159 */     return this.group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroup(@NotNull String group) {
/* 170 */     Preconditions.checkArgument((group != null), "group");
/* 171 */     this.group = group;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\CookingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */