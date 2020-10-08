/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.ItemStack;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftResultInventory extends CraftInventory {
/*    */   private final IInventory resultInventory;
/*    */   
/*    */   public CraftResultInventory(IInventory inventory, IInventory resultInventory) {
/* 11 */     super(inventory);
/* 12 */     this.resultInventory = resultInventory;
/*    */   }
/*    */   
/*    */   public IInventory getResultInventory() {
/* 16 */     return this.resultInventory;
/*    */   }
/*    */   
/*    */   public IInventory getIngredientsInventory() {
/* 20 */     return this.inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem(int slot) {
/* 25 */     if (slot < getIngredientsInventory().getSize()) {
/* 26 */       ItemStack itemStack = getIngredientsInventory().getItem(slot);
/* 27 */       return itemStack.isEmpty() ? null : CraftItemStack.asCraftMirror(itemStack);
/*    */     } 
/* 29 */     ItemStack item = getResultInventory().getItem(slot - getIngredientsInventory().getSize());
/* 30 */     return item.isEmpty() ? null : CraftItemStack.asCraftMirror(item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(int index, ItemStack item) {
/* 36 */     if (index < getIngredientsInventory().getSize()) {
/* 37 */       getIngredientsInventory().setItem(index, CraftItemStack.asNMSCopy(item));
/*    */     } else {
/* 39 */       getResultInventory().setItem(index - getIngredientsInventory().getSize(), CraftItemStack.asNMSCopy(item));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 45 */     return getResultInventory().getSize() + getIngredientsInventory().getSize();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftResultInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */