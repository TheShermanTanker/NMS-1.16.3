/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAgeable;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityVillagerAbstract;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.entity.AbstractVillager;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.MerchantRecipe;
/*    */ 
/*    */ public class CraftAbstractVillager extends CraftAgeable implements AbstractVillager, InventoryHolder {
/*    */   public CraftAbstractVillager(CraftServer server, EntityVillagerAbstract entity) {
/* 18 */     super(server, (EntityAgeable)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityVillagerAbstract getHandle() {
/* 23 */     return (EntityVillagerAbstract)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 28 */     return "CraftAbstractVillager";
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 33 */     return (Inventory)new CraftInventory((IInventory)getHandle().getInventory());
/*    */   }
/*    */   
/*    */   private CraftMerchant getMerchant() {
/* 37 */     return getHandle().getCraftMerchant();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<MerchantRecipe> getRecipes() {
/* 42 */     return getMerchant().getRecipes();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipes(List<MerchantRecipe> recipes) {
/* 47 */     getMerchant().setRecipes(recipes);
/*    */   }
/*    */ 
/*    */   
/*    */   public MerchantRecipe getRecipe(int i) {
/* 52 */     return getMerchant().getRecipe(i);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipe(int i, MerchantRecipe merchantRecipe) {
/* 57 */     getMerchant().setRecipe(i, merchantRecipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRecipeCount() {
/* 62 */     return getMerchant().getRecipeCount();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTrading() {
/* 67 */     return (getTrader() != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public HumanEntity getTrader() {
/* 72 */     return getMerchant().getTrader();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftAbstractVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */