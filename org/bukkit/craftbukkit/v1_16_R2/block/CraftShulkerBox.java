/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import net.minecraft.server.v1_16_R2.Block;
/*    */ import net.minecraft.server.v1_16_R2.BlockShulkerBox;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.SoundCategory;
/*    */ import net.minecraft.server.v1_16_R2.SoundEffects;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityShulkerBox;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.ShulkerBox;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftShulkerBox extends CraftLootable<TileEntityShulkerBox> implements ShulkerBox {
/*    */   public CraftShulkerBox(Block block) {
/* 19 */     super(block, TileEntityShulkerBox.class);
/*    */   }
/*    */   
/*    */   public CraftShulkerBox(Material material, TileEntityShulkerBox te) {
/* 23 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getSnapshotInventory() {
/* 28 */     return (Inventory)new CraftInventory((IInventory)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 33 */     if (!isPlaced()) {
/* 34 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 37 */     return (Inventory)new CraftInventory((IInventory)getTileEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor getColor() {
/* 42 */     Block block = CraftMagicNumbers.getBlock(getType());
/*    */     
/* 44 */     return DyeColor.getByWoolData((byte)((BlockShulkerBox)block).color.getColorIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public void open() {
/* 49 */     requirePlaced();
/* 50 */     if (!(getTileEntity()).opened) {
/* 51 */       World world = getTileEntity().getWorld();
/* 52 */       world.playBlockAction(getPosition(), getTileEntity().getBlock().getBlock(), 1, 1);
/* 53 */       world.playSound(null, getPosition(), SoundEffects.BLOCK_SHULKER_BOX_OPEN, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
/*    */     } 
/* 55 */     (getTileEntity()).opened = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 60 */     requirePlaced();
/* 61 */     if ((getTileEntity()).opened) {
/* 62 */       World world = getTileEntity().getWorld();
/* 63 */       world.playBlockAction(getPosition(), getTileEntity().getBlock().getBlock(), 1, 0);
/* 64 */       world.playSound(null, getPosition(), SoundEffects.BLOCK_SHULKER_BOX_OPEN, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
/*    */     } 
/* 66 */     (getTileEntity()).opened = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */