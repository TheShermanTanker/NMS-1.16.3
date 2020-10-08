/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.inventory.AbstractHorseInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryAbstractHorse
/*    */   extends CraftInventory implements AbstractHorseInventory {
/*    */   public CraftInventoryAbstractHorse(IInventory inventory) {
/* 10 */     super(inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getSaddle() {
/* 15 */     return getItem(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSaddle(ItemStack stack) {
/* 20 */     setItem(0, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryAbstractHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */