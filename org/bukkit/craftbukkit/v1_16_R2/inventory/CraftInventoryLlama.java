/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.LlamaInventory;
/*    */ 
/*    */ public class CraftInventoryLlama
/*    */   extends CraftInventoryAbstractHorse implements LlamaInventory {
/*    */   public CraftInventoryLlama(IInventory inventory) {
/* 10 */     super(inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getDecor() {
/* 15 */     return getItem(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDecor(ItemStack stack) {
/* 20 */     setItem(1, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */