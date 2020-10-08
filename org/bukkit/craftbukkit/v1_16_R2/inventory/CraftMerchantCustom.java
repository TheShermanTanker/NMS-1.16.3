/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.IMerchant;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.MerchantRecipe;
/*     */ import net.minecraft.server.v1_16_R2.MerchantRecipeList;
/*     */ import net.minecraft.server.v1_16_R2.SoundEffect;
/*     */ import net.minecraft.server.v1_16_R2.SoundEffects;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.apache.commons.lang.Validate;
/*     */ 
/*     */ public class CraftMerchantCustom
/*     */   extends CraftMerchant {
/*     */   public CraftMerchantCustom(String title) {
/*  18 */     super(new MinecraftMerchant(title));
/*  19 */     (getMerchant()).craftMerchant = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  24 */     return "CraftMerchantCustom";
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftMerchant getMerchant() {
/*  29 */     return (MinecraftMerchant)super.getMerchant();
/*     */   }
/*     */   
/*     */   public static class MinecraftMerchant
/*     */     implements IMerchant {
/*     */     private final IChatBaseComponent title;
/*  35 */     private final MerchantRecipeList trades = new MerchantRecipeList();
/*     */     private EntityHuman tradingPlayer;
/*     */     private World tradingWorld;
/*     */     protected CraftMerchant craftMerchant;
/*     */     
/*     */     public MinecraftMerchant(String title) {
/*  41 */       Validate.notNull(title, "Title cannot be null");
/*  42 */       this.title = (IChatBaseComponent)new ChatComponentText(title);
/*     */     }
/*     */ 
/*     */     
/*     */     public CraftMerchant getCraftMerchant() {
/*  47 */       return this.craftMerchant;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTradingPlayer(EntityHuman entityhuman) {
/*  52 */       this.tradingPlayer = entityhuman;
/*  53 */       if (entityhuman != null) {
/*  54 */         this.tradingWorld = entityhuman.world;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public EntityHuman getTrader() {
/*  60 */       return this.tradingPlayer;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipeList getOffers() {
/*  65 */       return this.trades;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(MerchantRecipe merchantrecipe) {
/*  71 */       merchantrecipe.increaseUses();
/*     */     }
/*     */ 
/*     */     
/*     */     public void k(ItemStack itemstack) {}
/*     */ 
/*     */     
/*     */     public IChatBaseComponent getScoreboardDisplayName() {
/*  79 */       return this.title;
/*     */     }
/*     */ 
/*     */     
/*     */     public World getWorld() {
/*  84 */       return this.tradingWorld;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getExperience() {
/*  89 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setForcedExperience(int i) {}
/*     */ 
/*     */     
/*     */     public boolean isRegularVillager() {
/*  98 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public SoundEffect getTradeSound() {
/* 103 */       return SoundEffects.ENTITY_VILLAGER_YES;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMerchantCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */