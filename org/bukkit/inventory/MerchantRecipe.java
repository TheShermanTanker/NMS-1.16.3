/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public class MerchantRecipe
/*     */   implements Recipe
/*     */ {
/*     */   private ItemStack result;
/*  25 */   private List<ItemStack> ingredients = new ArrayList<>();
/*     */   private int uses;
/*     */   private int maxUses;
/*     */   private boolean experienceReward;
/*     */   private int villagerExperience;
/*     */   private float priceMultiplier;
/*     */   
/*     */   public MerchantRecipe(@NotNull ItemStack result, int maxUses) {
/*  33 */     this(result, 0, maxUses, false);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward) {
/*  37 */     this(result, uses, maxUses, experienceReward, 0, 0.0F);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier) {
/*  41 */     this.result = result;
/*  42 */     this.uses = uses;
/*  43 */     this.maxUses = maxUses;
/*  44 */     this.experienceReward = experienceReward;
/*  45 */     this.villagerExperience = villagerExperience;
/*  46 */     this.priceMultiplier = priceMultiplier;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getResult() {
/*  52 */     return this.result;
/*     */   }
/*     */   
/*     */   public void addIngredient(@NotNull ItemStack item) {
/*  56 */     Preconditions.checkState((this.ingredients.size() < 2), "MerchantRecipe can only have maximum 2 ingredients");
/*  57 */     this.ingredients.add(item.clone());
/*     */   }
/*     */   
/*     */   public void removeIngredient(int index) {
/*  61 */     this.ingredients.remove(index);
/*     */   }
/*     */   
/*     */   public void setIngredients(@NotNull List<ItemStack> ingredients) {
/*  65 */     Preconditions.checkState((ingredients.size() <= 2), "MerchantRecipe can only have maximum 2 ingredients");
/*  66 */     this.ingredients = new ArrayList<>();
/*  67 */     for (ItemStack item : ingredients) {
/*  68 */       this.ingredients.add(item.clone());
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<ItemStack> getIngredients() {
/*  74 */     List<ItemStack> copy = new ArrayList<>();
/*  75 */     for (ItemStack item : this.ingredients) {
/*  76 */       copy.add(item.clone());
/*     */     }
/*  78 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUses() {
/*  87 */     return this.uses;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUses(int uses) {
/*  96 */     this.uses = uses;
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
/*     */   public int getMaxUses() {
/* 108 */     return this.maxUses;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxUses(int maxUses) {
/* 117 */     this.maxUses = maxUses;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExperienceReward() {
/* 127 */     return this.experienceReward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExperienceReward(boolean flag) {
/* 137 */     this.experienceReward = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVillagerExperience() {
/* 146 */     return this.villagerExperience;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVillagerExperience(int villagerExperience) {
/* 155 */     this.villagerExperience = villagerExperience;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPriceMultiplier() {
/* 164 */     return this.priceMultiplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPriceMultiplier(float priceMultiplier) {
/* 173 */     this.priceMultiplier = priceMultiplier;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\MerchantRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */