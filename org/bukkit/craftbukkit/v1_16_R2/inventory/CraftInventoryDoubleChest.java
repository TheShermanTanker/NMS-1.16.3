/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockChest;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.ITileInventory;
/*    */ import net.minecraft.server.v1_16_R2.InventoryLargeChest;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.DoubleChest;
/*    */ import org.bukkit.inventory.DoubleChestInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryDoubleChest extends CraftInventory implements DoubleChestInventory {
/*    */   public ITileInventory tile;
/*    */   
/*    */   public CraftInventoryDoubleChest(BlockChest.DoubleInventory block) {
/* 18 */     super((IInventory)block.inventorylargechest);
/* 19 */     this.tile = (ITileInventory)block;
/* 20 */     this.left = new CraftInventory(block.inventorylargechest.left);
/* 21 */     this.right = new CraftInventory(block.inventorylargechest.right);
/*    */   }
/*    */   private final CraftInventory left; private final CraftInventory right;
/*    */   public CraftInventoryDoubleChest(InventoryLargeChest largeChest) {
/* 25 */     super((IInventory)largeChest);
/* 26 */     if (largeChest.left instanceof InventoryLargeChest) {
/* 27 */       this.left = new CraftInventoryDoubleChest((InventoryLargeChest)largeChest.left);
/*    */     } else {
/* 29 */       this.left = new CraftInventory(largeChest.left);
/*    */     } 
/* 31 */     if (largeChest.right instanceof InventoryLargeChest) {
/* 32 */       this.right = new CraftInventoryDoubleChest((InventoryLargeChest)largeChest.right);
/*    */     } else {
/* 34 */       this.right = new CraftInventory(largeChest.right);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getLeftSide() {
/* 40 */     return this.left;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getRightSide() {
/* 45 */     return this.right;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setContents(ItemStack[] items) {
/* 50 */     if (getInventory().getSize() < items.length) {
/* 51 */       throw new IllegalArgumentException("Invalid inventory size; expected " + getInventory().getSize() + " or less");
/*    */     }
/* 53 */     ItemStack[] leftItems = new ItemStack[this.left.getSize()], rightItems = new ItemStack[this.right.getSize()];
/* 54 */     System.arraycopy(items, 0, leftItems, 0, Math.min(this.left.getSize(), items.length));
/* 55 */     this.left.setContents(leftItems);
/* 56 */     if (items.length >= this.left.getSize()) {
/* 57 */       System.arraycopy(items, this.left.getSize(), rightItems, 0, Math.min(this.right.getSize(), items.length - this.left.getSize()));
/* 58 */       this.right.setContents(rightItems);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DoubleChest getHolder() {
/* 64 */     return new DoubleChest(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DoubleChest getHolder(boolean useSnapshot) {
/* 70 */     return getHolder();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 76 */     return getLeftSide().getLocation().add(getRightSide().getLocation()).multiply(0.5D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryDoubleChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */