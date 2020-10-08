/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.MerchantRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ 
/*     */ public class CraftMerchantRecipe
/*     */   extends MerchantRecipe {
/*     */   public CraftMerchantRecipe(MerchantRecipe merchantRecipe) {
/*  13 */     super(CraftItemStack.asBukkitCopy(merchantRecipe.sellingItem), 0);
/*  14 */     this.handle = merchantRecipe;
/*  15 */     addIngredient(CraftItemStack.asBukkitCopy(merchantRecipe.buyingItem1));
/*  16 */     addIngredient(CraftItemStack.asBukkitCopy(merchantRecipe.buyingItem2));
/*     */   }
/*     */   private final MerchantRecipe handle;
/*     */   public CraftMerchantRecipe(ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier) {
/*  20 */     super(result, uses, maxUses, experienceReward, experience, priceMultiplier);
/*  21 */     this
/*     */ 
/*     */       
/*  24 */       .handle = new MerchantRecipe(ItemStack.b, ItemStack.b, CraftItemStack.asNMSCopy(result), uses, maxUses, experience, priceMultiplier, this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     setExperienceReward(experienceReward);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUses() {
/*  36 */     return this.handle.uses;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUses(int uses) {
/*  41 */     this.handle.uses = uses;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxUses() {
/*  46 */     return this.handle.maxUses;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxUses(int maxUses) {
/*  51 */     this.handle.maxUses = maxUses;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasExperienceReward() {
/*  56 */     return this.handle.rewardExp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExperienceReward(boolean flag) {
/*  61 */     this.handle.rewardExp = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVillagerExperience() {
/*  66 */     return this.handle.xp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerExperience(int villagerExperience) {
/*  71 */     this.handle.xp = villagerExperience;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPriceMultiplier() {
/*  76 */     return this.handle.priceMultiplier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPriceMultiplier(float priceMultiplier) {
/*  81 */     this.handle.priceMultiplier = priceMultiplier;
/*     */   }
/*     */   
/*     */   public MerchantRecipe toMinecraft() {
/*  85 */     List<ItemStack> ingredients = getIngredients();
/*  86 */     Preconditions.checkState(!ingredients.isEmpty(), "No offered ingredients");
/*  87 */     this.handle.buyingItem1 = CraftItemStack.asNMSCopy(ingredients.get(0));
/*  88 */     if (ingredients.size() > 1) {
/*  89 */       this.handle.buyingItem2 = CraftItemStack.asNMSCopy(ingredients.get(1));
/*     */     }
/*  91 */     return this.handle;
/*     */   }
/*     */   
/*     */   public static CraftMerchantRecipe fromBukkit(MerchantRecipe recipe) {
/*  95 */     if (recipe instanceof CraftMerchantRecipe) {
/*  96 */       return (CraftMerchantRecipe)recipe;
/*     */     }
/*  98 */     CraftMerchantRecipe craft = new CraftMerchantRecipe(recipe.getResult(), recipe.getUses(), recipe.getMaxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());
/*  99 */     craft.setIngredients(recipe.getIngredients());
/*     */     
/* 101 */     return craft;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMerchantRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */