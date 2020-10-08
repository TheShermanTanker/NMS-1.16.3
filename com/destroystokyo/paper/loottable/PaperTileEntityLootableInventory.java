/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.MCUtil;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityLootable;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.loot.LootTable;
/*    */ 
/*    */ public class PaperTileEntityLootableInventory implements PaperLootableBlockInventory {
/*    */   private TileEntityLootable tileEntityLootable;
/*    */   
/*    */   public PaperTileEntityLootableInventory(TileEntityLootable tileEntityLootable) {
/* 14 */     this.tileEntityLootable = tileEntityLootable;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootTable getLootTable() {
/* 19 */     return (this.tileEntityLootable.lootTable != null) ? Bukkit.getLootTable(CraftNamespacedKey.fromMinecraft(this.tileEntityLootable.lootTable)) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table, long seed) {
/* 24 */     setLootTable(table);
/* 25 */     setSeed(seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table) {
/* 30 */     this.tileEntityLootable.lootTable = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSeed(long seed) {
/* 35 */     this.tileEntityLootable.lootTableSeed = seed;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSeed() {
/* 40 */     return this.tileEntityLootable.lootTableSeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public PaperLootableInventoryData getLootableData() {
/* 45 */     return this.tileEntityLootable.lootableData;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntityLootable getTileEntity() {
/* 50 */     return this.tileEntityLootable;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootableInventory getAPILootableInventory() {
/* 55 */     World world = this.tileEntityLootable.getWorld();
/* 56 */     if (world == null) {
/* 57 */       return null;
/*    */     }
/* 59 */     return (LootableInventory)getBukkitWorld().getBlockAt(MCUtil.toLocation(world, this.tileEntityLootable.getPosition())).getState();
/*    */   }
/*    */ 
/*    */   
/*    */   public World getNMSWorld() {
/* 64 */     return this.tileEntityLootable.getWorld();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\PaperTileEntityLootableInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */