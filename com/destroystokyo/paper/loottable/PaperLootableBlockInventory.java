/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityLootable;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.Chunk;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public interface PaperLootableBlockInventory
/*    */   extends LootableBlockInventory, PaperLootableInventory
/*    */ {
/*    */   TileEntityLootable getTileEntity();
/*    */   
/*    */   default LootableInventory getAPILootableInventory() {
/* 15 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   default World getNMSWorld() {
/* 20 */     return getTileEntity().getWorld();
/*    */   }
/*    */   
/*    */   default Block getBlock() {
/* 24 */     BlockPosition position = getTileEntity().getPosition();
/* 25 */     Chunk bukkitChunk = (getTileEntity().getWorld().getChunkAtWorldCoords(position)).bukkitChunk;
/* 26 */     return bukkitChunk.getBlock(position.getX(), position.getY(), position.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   default PaperLootableInventoryData getLootableData() {
/* 31 */     return (getTileEntity()).lootableData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\PaperLootableBlockInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */