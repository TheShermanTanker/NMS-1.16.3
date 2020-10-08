/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartContainer;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.loot.LootTable;
/*    */ import org.bukkit.loot.Lootable;
/*    */ 
/*    */ public abstract class CraftMinecartContainer extends CraftMinecart implements Lootable {
/*    */   public CraftMinecartContainer(CraftServer server, EntityMinecartAbstract entity) {
/* 16 */     super(server, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartContainer getHandle() {
/* 21 */     return (EntityMinecartContainer)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table) {
/* 26 */     setLootTable(table, getSeed());
/*    */   }
/*    */ 
/*    */   
/*    */   public LootTable getLootTable() {
/* 31 */     MinecraftKey nmsTable = (getHandle()).lootTable;
/* 32 */     if (nmsTable == null) {
/* 33 */       return null;
/*    */     }
/*    */     
/* 36 */     NamespacedKey key = CraftNamespacedKey.fromMinecraft(nmsTable);
/* 37 */     return Bukkit.getLootTable(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSeed(long seed) {
/* 42 */     setLootTable(getLootTable(), seed);
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSeed() {
/* 47 */     return (getHandle()).lootTableSeed;
/*    */   }
/*    */   
/*    */   public void setLootTable(LootTable table, long seed) {
/* 51 */     MinecraftKey newKey = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
/* 52 */     getHandle().setLootTable(newKey, seed);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */