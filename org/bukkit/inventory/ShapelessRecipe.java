/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapelessRecipe
/*     */   implements Recipe, Keyed
/*     */ {
/*     */   private final NamespacedKey key;
/*     */   private final ItemStack output;
/*  22 */   private final List<RecipeChoice> ingredients = new ArrayList<>();
/*  23 */   private String group = "";
/*     */   
/*     */   @Deprecated
/*     */   public ShapelessRecipe(@NotNull ItemStack result) {
/*  27 */     Preconditions.checkArgument((result.getType() != Material.AIR), "Recipe must have non-AIR result.");
/*  28 */     this.key = NamespacedKey.randomKey();
/*  29 */     (new Throwable("Warning: A plugin is creating a recipe using a Deprecated method. This will cause you to receive warnings stating 'Tried to load unrecognized recipe: bukkit:<ID>'. Please ask the author to give their recipe a static key using NamespacedKey.")).printStackTrace();
/*  30 */     this.output = new ItemStack(result);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShapelessRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
/*  48 */     Preconditions.checkArgument((result.getType() != Material.AIR), "Recipe must have non-AIR result.");
/*  49 */     this.key = key;
/*  50 */     this.output = new ItemStack(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(@NotNull MaterialData ingredient) {
/*  61 */     return addIngredient(1, ingredient);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(@NotNull Material ingredient) {
/*  72 */     return addIngredient(1, ingredient, 0);
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(@NotNull Material ingredient, int rawdata) {
/*  86 */     return addIngredient(1, ingredient, rawdata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(int count, @NotNull MaterialData ingredient) {
/*  98 */     return addIngredient(count, ingredient.getItemType(), ingredient.getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(int count, @NotNull Material ingredient) {
/* 110 */     return addIngredient(count, ingredient, 0);
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(int count, @NotNull Material ingredient, int rawdata) {
/* 125 */     Validate.isTrue((this.ingredients.size() + count <= 9), "Shapeless recipes cannot have more than 9 ingredients");
/*     */ 
/*     */     
/* 128 */     if (rawdata == -1) {
/* 129 */       rawdata = 32767;
/*     */     }
/*     */     
/* 132 */     while (count-- > 0) {
/* 133 */       this.ingredients.add(new RecipeChoice.MaterialChoice(Collections.singletonList(ingredient)));
/*     */     }
/* 135 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(@NotNull RecipeChoice ingredient) {
/* 140 */     Validate.isTrue((this.ingredients.size() + 1 <= 9), "Shapeless recipes cannot have more than 9 ingredients");
/*     */     
/* 142 */     this.ingredients.add(ingredient);
/* 143 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(@NotNull ItemStack item) {
/* 149 */     return addIngredient(1, item);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe addIngredient(int count, @NotNull ItemStack item) {
/* 154 */     Validate.isTrue((this.ingredients.size() + count <= 9), "Shapeless recipes cannot have more than 9 ingredients");
/* 155 */     while (count-- > 0) {
/* 156 */       this.ingredients.add(new RecipeChoice.ExactChoice(item));
/*     */     }
/* 158 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(@NotNull ItemStack item) {
/* 163 */     return removeIngredient(1, item);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(int count, @NotNull ItemStack item) {
/* 168 */     Iterator<RecipeChoice> iterator = this.ingredients.iterator();
/* 169 */     while (count > 0 && iterator.hasNext()) {
/* 170 */       ItemStack stack = ((RecipeChoice)iterator.next()).getItemStack();
/* 171 */       if (stack.equals(item)) {
/* 172 */         iterator.remove();
/* 173 */         count--;
/*     */       } 
/*     */     } 
/* 176 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(@NotNull RecipeChoice ingredient) {
/* 188 */     this.ingredients.remove(ingredient);
/*     */     
/* 190 */     return this;
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
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(@NotNull Material ingredient) {
/* 203 */     return removeIngredient(ingredient, 0);
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
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(@NotNull MaterialData ingredient) {
/* 216 */     return removeIngredient(ingredient.getItemType(), ingredient.getData());
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
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(int count, @NotNull Material ingredient) {
/* 230 */     return removeIngredient(count, ingredient, 0);
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
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(int count, @NotNull MaterialData ingredient) {
/* 244 */     return removeIngredient(count, ingredient.getItemType(), ingredient.getData());
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
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(@NotNull Material ingredient, int rawdata) {
/* 260 */     return removeIngredient(1, ingredient, rawdata);
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
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ShapelessRecipe removeIngredient(int count, @NotNull Material ingredient, int rawdata) {
/* 277 */     Iterator<RecipeChoice> iterator = this.ingredients.iterator();
/* 278 */     while (count > 0 && iterator.hasNext()) {
/* 279 */       ItemStack stack = ((RecipeChoice)iterator.next()).getItemStack();
/* 280 */       if (stack.getType() == ingredient && stack.getDurability() == rawdata) {
/* 281 */         iterator.remove();
/* 282 */         count--;
/*     */       } 
/*     */     } 
/* 285 */     return this;
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
/* 296 */     return this.output.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ItemStack> getIngredientList() {
/* 306 */     ArrayList<ItemStack> result = new ArrayList<>(this.ingredients.size());
/* 307 */     for (RecipeChoice ingredient : this.ingredients) {
/* 308 */       result.add(ingredient.getItemStack().clone());
/*     */     }
/* 310 */     return result;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<RecipeChoice> getChoiceList() {
/* 315 */     List<RecipeChoice> result = new ArrayList<>(this.ingredients.size());
/* 316 */     for (RecipeChoice ingredient : this.ingredients) {
/* 317 */       result.add(ingredient.clone());
/*     */     }
/* 319 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 325 */     return this.key;
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
/* 336 */     return this.group;
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
/* 347 */     Preconditions.checkArgument((group != null), "group");
/* 348 */     this.group = group;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\ShapelessRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */