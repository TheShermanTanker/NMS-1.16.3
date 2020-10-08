/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*    */ import net.minecraft.server.v1_16_R2.IMerchant;
/*    */ import net.minecraft.server.v1_16_R2.MerchantRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MerchantRecipeList;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Merchant;
/*    */ import org.bukkit.inventory.MerchantRecipe;
/*    */ 
/*    */ public class CraftMerchant implements Merchant {
/*    */   protected final IMerchant merchant;
/*    */   
/*    */   public CraftMerchant(IMerchant merchant) {
/* 19 */     this.merchant = merchant;
/*    */   }
/*    */   
/*    */   public IMerchant getMerchant() {
/* 23 */     return this.merchant;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<MerchantRecipe> getRecipes() {
/* 28 */     return Collections.unmodifiableList(Lists.transform((List)this.merchant.getOffers(), new Function<MerchantRecipe, MerchantRecipe>()
/*    */           {
/*    */             public MerchantRecipe apply(MerchantRecipe recipe) {
/* 31 */               return recipe.asBukkit();
/*    */             }
/*    */           }));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipes(List<MerchantRecipe> recipes) {
/* 38 */     MerchantRecipeList recipesList = this.merchant.getOffers();
/* 39 */     recipesList.clear();
/* 40 */     for (MerchantRecipe recipe : recipes) {
/* 41 */       recipesList.add(CraftMerchantRecipe.fromBukkit(recipe).toMinecraft());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MerchantRecipe getRecipe(int i) {
/* 47 */     return ((MerchantRecipe)this.merchant.getOffers().get(i)).asBukkit();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipe(int i, MerchantRecipe merchantRecipe) {
/* 52 */     this.merchant.getOffers().set(i, CraftMerchantRecipe.fromBukkit(merchantRecipe).toMinecraft());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRecipeCount() {
/* 57 */     return this.merchant.getOffers().size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTrading() {
/* 62 */     return (getTrader() != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public HumanEntity getTrader() {
/* 67 */     EntityHuman eh = this.merchant.getTrader();
/* 68 */     return (eh == null) ? null : (HumanEntity)eh.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 73 */     return this.merchant.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 78 */     return (obj instanceof CraftMerchant && ((CraftMerchant)obj).merchant.equals(this.merchant));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */