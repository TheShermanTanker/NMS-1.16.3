/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartContainer;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.loot.LootTable;
/*    */ 
/*    */ public class PaperMinecartLootableInventory
/*    */   implements PaperLootableEntityInventory {
/*    */   private EntityMinecartContainer entity;
/*    */   
/*    */   public PaperMinecartLootableInventory(EntityMinecartContainer entity) {
/* 15 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootTable getLootTable() {
/* 20 */     return (this.entity.lootTable != null) ? Bukkit.getLootTable(CraftNamespacedKey.fromMinecraft(this.entity.lootTable)) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table, long seed) {
/* 25 */     setLootTable(table);
/* 26 */     setSeed(seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSeed(long seed) {
/* 31 */     this.entity.lootTableSeed = seed;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSeed() {
/* 36 */     return this.entity.lootTableSeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table) {
/* 41 */     this.entity.lootTable = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public PaperLootableInventoryData getLootableData() {
/* 46 */     return this.entity.lootableData;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getHandle() {
/* 51 */     return (Entity)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootableInventory getAPILootableInventory() {
/* 56 */     return (LootableInventory)this.entity.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public World getNMSWorld() {
/* 61 */     return this.entity.world;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\PaperMinecartLootableInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */