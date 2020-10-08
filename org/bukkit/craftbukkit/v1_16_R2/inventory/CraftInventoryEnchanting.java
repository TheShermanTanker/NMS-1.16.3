/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.inventory.EnchantingInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryEnchanting extends CraftInventory implements EnchantingInventory {
/*    */   public CraftInventoryEnchanting(IInventory inventory) {
/*  9 */     super(inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItem(ItemStack item) {
/* 14 */     setItem(0, item);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 19 */     return getItem(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSecondary(ItemStack item) {
/* 24 */     setItem(1, item);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getSecondary() {
/* 29 */     return getItem(1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryEnchanting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */