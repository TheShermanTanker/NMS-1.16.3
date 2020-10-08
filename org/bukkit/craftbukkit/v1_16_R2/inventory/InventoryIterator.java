/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class InventoryIterator implements ListIterator<ItemStack> {
/*    */   private final Inventory inventory;
/*    */   private int nextIndex;
/*    */   private Boolean lastDirection;
/*    */   
/*    */   InventoryIterator(Inventory craftInventory) {
/* 13 */     this.inventory = craftInventory;
/* 14 */     this.nextIndex = 0;
/*    */   }
/*    */   
/*    */   InventoryIterator(Inventory craftInventory, int index) {
/* 18 */     this.inventory = craftInventory;
/* 19 */     this.nextIndex = index;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 24 */     return (this.nextIndex < this.inventory.getSize());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack next() {
/* 29 */     this.lastDirection = Boolean.valueOf(true);
/* 30 */     return this.inventory.getItem(this.nextIndex++);
/*    */   }
/*    */ 
/*    */   
/*    */   public int nextIndex() {
/* 35 */     return this.nextIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPrevious() {
/* 40 */     return (this.nextIndex > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack previous() {
/* 45 */     this.lastDirection = Boolean.valueOf(false);
/* 46 */     return this.inventory.getItem(--this.nextIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public int previousIndex() {
/* 51 */     return this.nextIndex - 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(ItemStack item) {
/* 56 */     if (this.lastDirection == null) {
/* 57 */       throw new IllegalStateException("No current item!");
/*    */     }
/* 59 */     int i = this.lastDirection.booleanValue() ? (this.nextIndex - 1) : this.nextIndex;
/* 60 */     this.inventory.setItem(i, item);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(ItemStack item) {
/* 65 */     throw new UnsupportedOperationException("Can't change the size of an inventory!");
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove() {
/* 70 */     throw new UnsupportedOperationException("Can't change the size of an inventory!");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\InventoryIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */