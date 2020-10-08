/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityHopper;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Hopper;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftHopper extends CraftLootable<TileEntityHopper> implements Hopper {
/*    */   public CraftHopper(Block block) {
/* 13 */     super(block, TileEntityHopper.class);
/*    */   }
/*    */   
/*    */   public CraftHopper(Material material, TileEntityHopper te) {
/* 17 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getSnapshotInventory() {
/* 22 */     return (Inventory)new CraftInventory((IInventory)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 27 */     if (!isPlaced()) {
/* 28 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 31 */     return (Inventory)new CraftInventory((IInventory)getTileEntity());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */