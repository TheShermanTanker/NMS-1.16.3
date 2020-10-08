/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.IMerchant;
/*    */ import net.minecraft.server.v1_16_R2.InventoryMerchant;
/*    */ import net.minecraft.server.v1_16_R2.MerchantRecipe;
/*    */ import org.bukkit.inventory.Merchant;
/*    */ import org.bukkit.inventory.MerchantInventory;
/*    */ import org.bukkit.inventory.MerchantRecipe;
/*    */ 
/*    */ public class CraftInventoryMerchant
/*    */   extends CraftInventory implements MerchantInventory {
/*    */   public CraftInventoryMerchant(IMerchant merchant, InventoryMerchant inventory) {
/* 14 */     super((IInventory)inventory);
/* 15 */     this.merchant = merchant;
/*    */   }
/*    */   private final IMerchant merchant;
/*    */   
/*    */   public int getSelectedRecipeIndex() {
/* 20 */     return (getInventory()).selectedIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public MerchantRecipe getSelectedRecipe() {
/* 25 */     MerchantRecipe nmsRecipe = getInventory().getRecipe();
/* 26 */     return (nmsRecipe == null) ? null : nmsRecipe.asBukkit();
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryMerchant getInventory() {
/* 31 */     return (InventoryMerchant)this.inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public Merchant getMerchant() {
/* 36 */     return this.merchant.getCraftMerchant();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */