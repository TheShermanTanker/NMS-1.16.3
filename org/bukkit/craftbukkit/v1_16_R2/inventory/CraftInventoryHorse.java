/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.inventory.HorseInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryHorse
/*    */   extends CraftSaddledInventory implements HorseInventory {
/*    */   public CraftInventoryHorse(IInventory inventory) {
/* 10 */     super(inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getArmor() {
/* 15 */     return getItem(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setArmor(ItemStack stack) {
/* 20 */     setItem(1, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */