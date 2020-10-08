/*    */ package org.bukkit.block;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.inventory.DoubleChestInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class DoubleChest
/*    */   implements InventoryHolder
/*    */ {
/*    */   private DoubleChestInventory inventory;
/*    */   
/*    */   public DoubleChest(@NotNull DoubleChestInventory chest) {
/* 18 */     this.inventory = chest;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Inventory getInventory() {
/* 24 */     return (Inventory)this.inventory;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public InventoryHolder getLeftSide() {
/* 29 */     return this.inventory.getLeftSide().getHolder();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public InventoryHolder getRightSide() {
/* 34 */     return this.inventory.getRightSide().getHolder();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public InventoryHolder getLeftSide(boolean useSnapshot) {
/* 40 */     return this.inventory.getLeftSide().getHolder(useSnapshot);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public InventoryHolder getRightSide(boolean useSnapshot) {
/* 45 */     return this.inventory.getRightSide().getHolder(useSnapshot);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getLocation() {
/* 51 */     return getInventory().getLocation();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public World getWorld() {
/* 56 */     return getLocation().getWorld();
/*    */   }
/*    */   
/*    */   public double getX() {
/* 60 */     return getLocation().getX();
/*    */   }
/*    */   
/*    */   public double getY() {
/* 64 */     return getLocation().getY();
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 68 */     return getLocation().getZ();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\DoubleChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */