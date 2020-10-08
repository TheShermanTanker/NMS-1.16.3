/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockDropper;
/*    */ import net.minecraft.server.v1_16_R2.Blocks;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityDropper;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Dropper;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftDropper extends CraftLootable<TileEntityDropper> implements Dropper {
/*    */   public CraftDropper(Block block) {
/* 16 */     super(block, TileEntityDropper.class);
/*    */   }
/*    */   
/*    */   public CraftDropper(Material material, TileEntityDropper te) {
/* 20 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getSnapshotInventory() {
/* 25 */     return (Inventory)new CraftInventory((IInventory)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 30 */     if (!isPlaced()) {
/* 31 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 34 */     return (Inventory)new CraftInventory((IInventory)getTileEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   public void drop() {
/* 39 */     Block block = getBlock();
/*    */     
/* 41 */     if (block.getType() == Material.DROPPER) {
/* 42 */       CraftWorld world = (CraftWorld)getWorld();
/* 43 */       BlockDropper drop = (BlockDropper)Blocks.DROPPER;
/*    */       
/* 45 */       drop.dispense(world.getHandle(), getPosition());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftDropper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */