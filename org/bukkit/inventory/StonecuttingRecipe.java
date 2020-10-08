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
/*     */ public class StonecuttingRecipe
/*     */   implements Recipe, Keyed
/*     */ {
/*     */   private final NamespacedKey key;
/*     */   private ItemStack output;
/*     */   private RecipeChoice ingredient;
/*  17 */   private String group = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StonecuttingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source) {
/*  27 */     this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StonecuttingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input) {
/*  38 */     Preconditions.checkArgument((result.getType() != Material.AIR), "Recipe must have non-AIR result.");
/*  39 */     this.key = key;
/*  40 */     this.output = new ItemStack(result);
/*  41 */     this.ingredient = input;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StonecuttingRecipe setInput(@NotNull Material input) {
/*  52 */     this.ingredient = new RecipeChoice.MaterialChoice(Collections.singletonList(input));
/*  53 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getInput() {
/*  63 */     return this.ingredient.getItemStack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StonecuttingRecipe setInputChoice(@NotNull RecipeChoice input) {
/*  74 */     this.ingredient = input;
/*  75 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RecipeChoice getInputChoice() {
/*  85 */     return this.ingredient.clone();
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
/*  96 */     return this.output.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 102 */     return this.key;
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
/* 113 */     return this.group;
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
/* 124 */     Preconditions.checkArgument((group != null), "group");
/* 125 */     this.group = group;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\StonecuttingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */