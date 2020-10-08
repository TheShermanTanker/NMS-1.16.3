/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import com.destroystokyo.paper.loottable.PaperLootableBlockInventory;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityContainer;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityLootable;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Nameable;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ import org.bukkit.loot.LootTable;
/*    */ import org.bukkit.loot.Lootable;
/*    */ 
/*    */ public abstract class CraftLootable<T extends TileEntityLootable> extends CraftContainer<T> implements Nameable, Lootable, PaperLootableBlockInventory {
/*    */   public CraftLootable(Block block, Class<T> tileEntityClass) {
/* 17 */     super(block, tileEntityClass);
/*    */   }
/*    */   
/*    */   public CraftLootable(Material material, T tileEntity) {
/* 21 */     super(material, tileEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyTo(T lootable) {
/* 26 */     super.applyTo(lootable);
/*    */     
/* 28 */     if (((TileEntityLootable)getSnapshot()).lootTable == null) {
/* 29 */       lootable.setLootTable((MinecraftKey)null, 0L);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public LootTable getLootTable() {
/* 35 */     if (((TileEntityLootable)getSnapshot()).lootTable == null) {
/* 36 */       return null;
/*    */     }
/*    */     
/* 39 */     MinecraftKey key = ((TileEntityLootable)getSnapshot()).lootTable;
/* 40 */     return Bukkit.getLootTable(CraftNamespacedKey.fromMinecraft(key));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table) {
/* 45 */     setLootTable(table, getSeed());
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSeed() {
/* 50 */     return ((TileEntityLootable)getSnapshot()).lootTableSeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSeed(long seed) {
/* 55 */     setLootTable(getLootTable(), seed);
/*    */   }
/*    */   
/*    */   public void setLootTable(LootTable table, long seed) {
/* 59 */     MinecraftKey key = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
/* 60 */     ((TileEntityLootable)getSnapshot()).setLootTable(key, seed);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftLootable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */