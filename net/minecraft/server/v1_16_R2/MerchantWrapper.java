/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ public class MerchantWrapper
/*    */   implements IMerchant
/*    */ {
/*    */   private final InventoryMerchant a;
/*    */   private final EntityHuman b;
/* 19 */   private MerchantRecipeList c = new MerchantRecipeList();
/*    */   private int d;
/*    */   
/*    */   public MerchantWrapper(EntityHuman var0) {
/* 23 */     this.b = var0;
/* 24 */     this.a = new InventoryMerchant(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public EntityHuman getTrader() {
/* 34 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTradingPlayer(@Nullable EntityHuman var0) {}
/*    */ 
/*    */   
/*    */   public MerchantRecipeList getOffers() {
/* 43 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(MerchantRecipe var0) {
/* 53 */     var0.increaseUses();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void k(ItemStack var0) {}
/*    */ 
/*    */   
/*    */   public World getWorld() {
/* 62 */     return this.b.world;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperience() {
/* 67 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setForcedExperience(int var0) {
/* 72 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRegularVillager() {
/* 77 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundEffect getTradeSound() {
/* 82 */     return SoundEffects.ENTITY_VILLAGER_YES;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MerchantWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */