/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockDispenser;
/*    */ import net.minecraft.server.v1_16_R2.Blocks;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityDispenser;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Dispenser;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.projectiles.CraftBlockProjectileSource;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.projectiles.BlockProjectileSource;
/*    */ 
/*    */ public class CraftDispenser extends CraftLootable<TileEntityDispenser> implements Dispenser {
/*    */   public CraftDispenser(Block block) {
/* 18 */     super(block, TileEntityDispenser.class);
/*    */   }
/*    */   
/*    */   public CraftDispenser(Material material, TileEntityDispenser te) {
/* 22 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getSnapshotInventory() {
/* 27 */     return (Inventory)new CraftInventory((IInventory)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 32 */     if (!isPlaced()) {
/* 33 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 36 */     return (Inventory)new CraftInventory((IInventory)getTileEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockProjectileSource getBlockProjectileSource() {
/* 41 */     Block block = getBlock();
/*    */     
/* 43 */     if (block.getType() != Material.DISPENSER) {
/* 44 */       return null;
/*    */     }
/*    */     
/* 47 */     return (BlockProjectileSource)new CraftBlockProjectileSource((TileEntityDispenser)getTileEntityFromWorld());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean dispense() {
/* 52 */     Block block = getBlock();
/*    */     
/* 54 */     if (block.getType() == Material.DISPENSER) {
/* 55 */       CraftWorld world = (CraftWorld)getWorld();
/* 56 */       BlockDispenser dispense = (BlockDispenser)Blocks.DISPENSER;
/*    */       
/* 58 */       dispense.dispense(world.getHandle(), getPosition());
/* 59 */       return true;
/*    */     } 
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */