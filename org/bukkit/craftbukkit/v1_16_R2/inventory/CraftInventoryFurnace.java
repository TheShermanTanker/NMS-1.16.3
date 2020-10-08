/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityFurnace;
/*    */ import org.bukkit.block.Furnace;
/*    */ import org.bukkit.inventory.FurnaceInventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryFurnace extends CraftInventory implements FurnaceInventory {
/*    */   public CraftInventoryFurnace(TileEntityFurnace inventory) {
/* 10 */     super((IInventory)inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getResult() {
/* 15 */     return getItem(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getFuel() {
/* 20 */     return getItem(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getSmelting() {
/* 25 */     return getItem(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFuel(ItemStack stack) {
/* 30 */     setItem(1, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResult(ItemStack stack) {
/* 35 */     setItem(2, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSmelting(ItemStack stack) {
/* 40 */     setItem(0, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public Furnace getHolder() {
/* 45 */     return (Furnace)this.inventory.getOwner();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */