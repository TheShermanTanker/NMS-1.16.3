/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBrewingStand;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BrewingStand;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryBrewer;
/*    */ import org.bukkit.inventory.BrewerInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftBrewingStand extends CraftContainer<TileEntityBrewingStand> implements BrewingStand {
/*    */   public CraftBrewingStand(Block block) {
/* 13 */     super(block, TileEntityBrewingStand.class);
/*    */   }
/*    */   
/*    */   public CraftBrewingStand(Material material, TileEntityBrewingStand te) {
/* 17 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrewerInventory getSnapshotInventory() {
/* 22 */     return (BrewerInventory)new CraftInventoryBrewer((IInventory)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public BrewerInventory getInventory() {
/* 27 */     if (!isPlaced()) {
/* 28 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 31 */     return (BrewerInventory)new CraftInventoryBrewer((IInventory)getTileEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrewingTime() {
/* 36 */     return (getSnapshot()).brewTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBrewingTime(int brewTime) {
/* 41 */     (getSnapshot()).brewTime = brewTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFuelLevel() {
/* 46 */     return (getSnapshot()).fuelLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFuelLevel(int level) {
/* 51 */     (getSnapshot()).fuelLevel = level;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */