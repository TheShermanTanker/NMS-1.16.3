/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import net.minecraft.server.v1_16_R2.BlockBarrel;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.SoundEffects;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBarrel;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Barrel;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftBarrel extends CraftLootable<TileEntityBarrel> implements Barrel {
/*    */   public CraftBarrel(Block block) {
/* 16 */     super(block, TileEntityBarrel.class);
/*    */   }
/*    */   
/*    */   public CraftBarrel(Material material, TileEntityBarrel te) {
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
/*    */   public void open() {
/* 39 */     requirePlaced();
/* 40 */     if (!(getTileEntity()).opened) {
/* 41 */       IBlockData blockData = getTileEntity().getBlock();
/* 42 */       boolean open = ((Boolean)blockData.get((IBlockState)BlockBarrel.OPEN)).booleanValue();
/*    */       
/* 44 */       if (!open) {
/* 45 */         getTileEntity().setOpenFlag(blockData, true);
/* 46 */         getTileEntity().playOpenSound(blockData, SoundEffects.BLOCK_BARREL_OPEN);
/*    */       } 
/*    */     } 
/* 49 */     (getTileEntity()).opened = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 54 */     requirePlaced();
/* 55 */     if ((getTileEntity()).opened) {
/* 56 */       IBlockData blockData = getTileEntity().getBlock();
/* 57 */       getTileEntity().setOpenFlag(blockData, false);
/* 58 */       getTileEntity().playOpenSound(blockData, SoundEffects.BLOCK_BARREL_CLOSE);
/*    */     } 
/* 60 */     (getTileEntity()).opened = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */