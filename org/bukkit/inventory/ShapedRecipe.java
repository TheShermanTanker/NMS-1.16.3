/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class ShapedRecipe
/*     */   implements Recipe, Keyed
/*     */ {
/*     */   private final NamespacedKey key;
/*     */   private final ItemStack output;
/*     */   private String[] rows;
/*  21 */   private Map<Character, RecipeChoice> ingredients = new HashMap<>();
/*  22 */   private String group = "";
/*     */   
/*     */   @Deprecated
/*     */   public ShapedRecipe(@NotNull ItemStack result) {
/*  26 */     Preconditions.checkArgument((result.getType() != Material.AIR), "Recipe must have non-AIR result.");
/*  27 */     this.key = NamespacedKey.randomKey();
/*  28 */     (new Throwable("Warning: A plugin is creating a recipe using a Deprecated method. This will cause you to receive warnings stating 'Tried to load unrecognized recipe: bukkit:<ID>'. Please ask the author to give their recipe a static key using NamespacedKey.")).printStackTrace();
/*  29 */     this.output = new ItemStack(result);
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
/*     */   public ShapedRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
/*  45 */     Preconditions.checkArgument((key != null), "key");
/*  46 */     Preconditions.checkArgument((result.getType() != Material.AIR), "Recipe must have non-AIR result.");
/*     */     
/*  48 */     this.key = key;
/*  49 */     this.output = new ItemStack(result);
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
/*     */   @NotNull
/*     */   public ShapedRecipe shape(@NotNull String... shape) {
/*  65 */     Validate.notNull(shape, "Must provide a shape");
/*  66 */     Validate.isTrue((shape.length > 0 && shape.length < 4), "Crafting recipes should be 1, 2 or 3 rows, not ", shape.length);
/*     */     
/*  68 */     int lastLen = -1;
/*  69 */     for (String row : shape) {
/*  70 */       Validate.notNull(row, "Shape cannot have null rows");
/*  71 */       Validate.isTrue((row.length() > 0 && row.length() < 4), "Crafting rows should be 1, 2, or 3 characters, not ", row.length());
/*     */       
/*  73 */       Validate.isTrue((lastLen == -1 || lastLen == row.length()), "Crafting recipes must be rectangular");
/*  74 */       lastLen = row.length();
/*     */     } 
/*  76 */     this.rows = new String[shape.length];
/*  77 */     for (int i = 0; i < shape.length; i++) {
/*  78 */       this.rows[i] = shape[i];
/*     */     }
/*     */ 
/*     */     
/*  82 */     HashMap<Character, RecipeChoice> newIngredients = new HashMap<>();
/*  83 */     for (String row : shape) {
/*  84 */       char[] arrayOfChar; int j; byte b; for (arrayOfChar = row.toCharArray(), j = arrayOfChar.length, b = 0; b < j; ) { Character c = Character.valueOf(arrayOfChar[b]);
/*  85 */         newIngredients.put(c, this.ingredients.get(c)); b++; }
/*     */     
/*     */     } 
/*  88 */     this.ingredients = newIngredients;
/*     */     
/*  90 */     return this;
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
/*     */   public ShapedRecipe setIngredient(char key, @NotNull MaterialData ingredient) {
/* 102 */     return setIngredient(key, ingredient.getItemType(), ingredient.getData());
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
/*     */   public ShapedRecipe setIngredient(char key, @NotNull Material ingredient) {
/* 114 */     return setIngredient(key, ingredient, 0);
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
/*     */   public ShapedRecipe setIngredient(char key, @NotNull Material ingredient, int raw) {
/* 129 */     Validate.isTrue(this.ingredients.containsKey(Character.valueOf(key)), "Symbol does not appear in the shape:", key);
/*     */ 
/*     */     
/* 132 */     if (raw == -1) {
/* 133 */       raw = 32767;
/*     */     }
/*     */     
/* 136 */     this.ingredients.put(Character.valueOf(key), new RecipeChoice.MaterialChoice(Collections.singletonList(ingredient)));
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ShapedRecipe setIngredient(char key, @NotNull RecipeChoice ingredient) {
/* 142 */     Validate.isTrue(this.ingredients.containsKey(Character.valueOf(key)), "Symbol does not appear in the shape:", key);
/*     */     
/* 144 */     this.ingredients.put(Character.valueOf(key), ingredient);
/* 145 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShapedRecipe setIngredient(char key, @NotNull ItemStack item) {
/* 151 */     return setIngredient(key, new RecipeChoice.ExactChoice(item));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<Character, ItemStack> getIngredientMap() {
/* 162 */     HashMap<Character, ItemStack> result = new HashMap<>();
/* 163 */     for (Map.Entry<Character, RecipeChoice> ingredient : this.ingredients.entrySet()) {
/* 164 */       if (ingredient.getValue() == null) {
/* 165 */         result.put(ingredient.getKey(), null); continue;
/*     */       } 
/* 167 */       result.put(ingredient.getKey(), ((RecipeChoice)ingredient.getValue()).getItemStack().clone());
/*     */     } 
/*     */     
/* 170 */     return result;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Map<Character, RecipeChoice> getChoiceMap() {
/* 175 */     Map<Character, RecipeChoice> result = new HashMap<>();
/* 176 */     for (Map.Entry<Character, RecipeChoice> ingredient : this.ingredients.entrySet()) {
/* 177 */       if (ingredient.getValue() == null) {
/* 178 */         result.put(ingredient.getKey(), null); continue;
/*     */       } 
/* 180 */       result.put(ingredient.getKey(), ((RecipeChoice)ingredient.getValue()).clone());
/*     */     } 
/*     */     
/* 183 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String[] getShape() {
/* 194 */     return (String[])this.rows.clone();
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
/* 205 */     return this.output.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 211 */     return this.key;
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
/* 222 */     return this.group;
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
/* 233 */     Preconditions.checkArgument((group != null), "group");
/* 234 */     this.group = group;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\ShapedRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */