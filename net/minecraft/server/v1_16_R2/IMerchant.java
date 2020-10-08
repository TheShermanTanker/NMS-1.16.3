/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.OptionalInt;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMerchant;
/*    */ 
/*    */ public interface IMerchant {
/*    */   void setTradingPlayer(@Nullable EntityHuman paramEntityHuman);
/*    */   
/*    */   @Nullable
/*    */   EntityHuman getTrader();
/*    */   
/*    */   MerchantRecipeList getOffers();
/*    */   
/*    */   void a(MerchantRecipe paramMerchantRecipe);
/*    */   
/*    */   void k(ItemStack paramItemStack);
/*    */   
/*    */   World getWorld();
/*    */   
/*    */   int getExperience();
/*    */   
/*    */   void setForcedExperience(int paramInt);
/*    */   
/*    */   boolean isRegularVillager();
/*    */   
/*    */   SoundEffect getTradeSound();
/*    */   
/*    */   default boolean fa() {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   default void openTrade(EntityHuman entityhuman, IChatBaseComponent ichatbasecomponent, int i) {
/* 34 */     OptionalInt optionalint = entityhuman.openContainer(new TileInventory((j, playerinventory, entityhuman1) -> new ContainerMerchant(j, playerinventory, this), ichatbasecomponent));
/*    */ 
/*    */ 
/*    */     
/* 38 */     if (optionalint.isPresent()) {
/* 39 */       MerchantRecipeList merchantrecipelist = getOffers();
/*    */       
/* 41 */       if (!merchantrecipelist.isEmpty())
/* 42 */         entityhuman.openTrade(optionalint.getAsInt(), merchantrecipelist, i, getExperience(), isRegularVillager(), fa()); 
/*    */     } 
/*    */   }
/*    */   
/*    */   CraftMerchant getCraftMerchant();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */