/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import net.minecraft.server.v1_16_R2.Block;
/*    */ import net.minecraft.server.v1_16_R2.BlockChest;
/*    */ import net.minecraft.server.v1_16_R2.Blocks;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.ITileInventory;
/*    */ import net.minecraft.server.v1_16_R2.SoundEffects;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityChest;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Chest;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryDoubleChest;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftChest extends CraftLootable<TileEntityChest> implements Chest, PaperLootableBlockInventory {
/*    */   public CraftChest(Block block) {
/* 20 */     super(block, TileEntityChest.class);
/*    */   }
/*    */   
/*    */   public CraftChest(Material material, TileEntityChest te) {
/* 24 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getSnapshotInventory() {
/* 29 */     return (Inventory)new CraftInventory((IInventory)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getBlockInventory() {
/* 34 */     if (!isPlaced()) {
/* 35 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 38 */     return (Inventory)new CraftInventory((IInventory)getTileEntity());
/*    */   }
/*    */   
/*    */   public Inventory getInventory() {
/*    */     CraftInventoryDoubleChest craftInventoryDoubleChest;
/* 43 */     CraftInventory inventory = (CraftInventory)getBlockInventory();
/* 44 */     if (!isPlaced()) {
/* 45 */       return (Inventory)inventory;
/*    */     }
/*    */ 
/*    */     
/* 49 */     CraftWorld world = (CraftWorld)getWorld();
/*    */     
/* 51 */     BlockChest blockChest = (getType() == Material.CHEST) ? (BlockChest)Blocks.CHEST : (BlockChest)Blocks.TRAPPED_CHEST;
/* 52 */     ITileInventory nms = blockChest.getInventory(this.data, (World)world.getHandle(), getPosition());
/*    */     
/* 54 */     if (nms instanceof BlockChest.DoubleInventory) {
/* 55 */       craftInventoryDoubleChest = new CraftInventoryDoubleChest((BlockChest.DoubleInventory)nms);
/*    */     }
/* 57 */     return (Inventory)craftInventoryDoubleChest;
/*    */   }
/*    */ 
/*    */   
/*    */   public void open() {
/* 62 */     requirePlaced();
/* 63 */     if (!(getTileEntity()).opened) {
/* 64 */       Block block = getTileEntity().getBlock().getBlock();
/* 65 */       getTileEntity().getWorld().playBlockAction(getTileEntity().getPosition(), block, 1, (getTileEntity()).viewingCount + 1);
/* 66 */       getTileEntity().playOpenSound(SoundEffects.BLOCK_CHEST_OPEN);
/*    */     } 
/* 68 */     (getTileEntity()).opened = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 73 */     requirePlaced();
/* 74 */     if ((getTileEntity()).opened) {
/* 75 */       Block block = getTileEntity().getBlock().getBlock();
/* 76 */       getTileEntity().getWorld().playBlockAction(getTileEntity().getPosition(), block, 1, 0);
/* 77 */       getTileEntity().playOpenSound(SoundEffects.BLOCK_CHEST_CLOSE);
/*    */     } 
/* 79 */     (getTileEntity()).opened = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */