/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*    */ import org.bukkit.inventory.BlockInventoryHolder;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftBlockInventoryHolder
/*    */   implements BlockInventoryHolder {
/*    */   private final Block block;
/*    */   private final Inventory inventory;
/*    */   
/*    */   public CraftBlockInventoryHolder(GeneratorAccess world, BlockPosition pos, IInventory inv) {
/* 17 */     this.block = (Block)CraftBlock.at(world, pos);
/* 18 */     this.inventory = new CraftInventory(inv);
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlock() {
/* 23 */     return this.block;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 28 */     return this.inventory;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftBlockInventoryHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */