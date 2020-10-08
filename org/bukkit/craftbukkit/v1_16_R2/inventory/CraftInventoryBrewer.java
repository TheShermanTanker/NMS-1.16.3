/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.block.BrewingStand;
/*    */ import org.bukkit.inventory.BrewerInventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryBrewer extends CraftInventory implements BrewerInventory {
/*    */   public CraftInventoryBrewer(IInventory inventory) {
/* 10 */     super(inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getIngredient() {
/* 15 */     return getItem(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIngredient(ItemStack ingredient) {
/* 20 */     setItem(3, ingredient);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrewingStand getHolder() {
/* 25 */     return (BrewingStand)this.inventory.getOwner();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getFuel() {
/* 30 */     return getItem(4);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFuel(ItemStack fuel) {
/* 35 */     setItem(4, fuel);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryBrewer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */