/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.inventory.BeaconInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryBeacon extends CraftInventory implements BeaconInventory {
/*    */   public CraftInventoryBeacon(IInventory beacon) {
/*  9 */     super(beacon);
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
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */