/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.event.inventory.InventoryType;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ 
/*    */ public final class CraftInventoryCreator
/*    */ {
/* 11 */   public static final CraftInventoryCreator INSTANCE = new CraftInventoryCreator();
/*    */   
/* 13 */   private final CraftCustomInventoryConverter DEFAULT_CONVERTER = new CraftCustomInventoryConverter();
/* 14 */   private final Map<InventoryType, InventoryConverter> converterMap = new HashMap<>();
/*    */   
/*    */   private CraftInventoryCreator() {
/* 17 */     this.converterMap.put(InventoryType.CHEST, this.DEFAULT_CONVERTER);
/* 18 */     this.converterMap.put(InventoryType.DISPENSER, new CraftTileInventoryConverter.Dispenser());
/* 19 */     this.converterMap.put(InventoryType.DROPPER, new CraftTileInventoryConverter.Dropper());
/* 20 */     this.converterMap.put(InventoryType.FURNACE, new CraftTileInventoryConverter.Furnace());
/* 21 */     this.converterMap.put(InventoryType.WORKBENCH, this.DEFAULT_CONVERTER);
/* 22 */     this.converterMap.put(InventoryType.ENCHANTING, this.DEFAULT_CONVERTER);
/* 23 */     this.converterMap.put(InventoryType.BREWING, new CraftTileInventoryConverter.BrewingStand());
/* 24 */     this.converterMap.put(InventoryType.PLAYER, this.DEFAULT_CONVERTER);
/* 25 */     this.converterMap.put(InventoryType.MERCHANT, this.DEFAULT_CONVERTER);
/* 26 */     this.converterMap.put(InventoryType.ENDER_CHEST, this.DEFAULT_CONVERTER);
/* 27 */     this.converterMap.put(InventoryType.ANVIL, this.DEFAULT_CONVERTER);
/* 28 */     this.converterMap.put(InventoryType.SMITHING, this.DEFAULT_CONVERTER);
/* 29 */     this.converterMap.put(InventoryType.BEACON, this.DEFAULT_CONVERTER);
/* 30 */     this.converterMap.put(InventoryType.HOPPER, new CraftTileInventoryConverter.Hopper());
/* 31 */     this.converterMap.put(InventoryType.SHULKER_BOX, this.DEFAULT_CONVERTER);
/* 32 */     this.converterMap.put(InventoryType.BARREL, this.DEFAULT_CONVERTER);
/* 33 */     this.converterMap.put(InventoryType.BLAST_FURNACE, new CraftTileInventoryConverter.BlastFurnace());
/* 34 */     this.converterMap.put(InventoryType.LECTERN, new CraftTileInventoryConverter.Lectern());
/* 35 */     this.converterMap.put(InventoryType.SMOKER, new CraftTileInventoryConverter.Smoker());
/* 36 */     this.converterMap.put(InventoryType.LOOM, this.DEFAULT_CONVERTER);
/* 37 */     this.converterMap.put(InventoryType.CARTOGRAPHY, this.DEFAULT_CONVERTER);
/* 38 */     this.converterMap.put(InventoryType.GRINDSTONE, this.DEFAULT_CONVERTER);
/* 39 */     this.converterMap.put(InventoryType.STONECUTTER, this.DEFAULT_CONVERTER);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory createInventory(InventoryHolder holder, InventoryType type) {
/* 44 */     if (holder != null) {
/* 45 */       return this.DEFAULT_CONVERTER.createInventory(holder, type);
/*    */     }
/*    */     
/* 48 */     return ((InventoryConverter)this.converterMap.get(type)).createInventory(holder, type);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
/* 53 */     if (holder != null) {
/* 54 */       return this.DEFAULT_CONVERTER.createInventory(holder, type, title);
/*    */     }
/*    */     
/* 57 */     return ((InventoryConverter)this.converterMap.get(type)).createInventory(holder, type, title);
/*    */   }
/*    */   
/*    */   public Inventory createInventory(InventoryHolder holder, int size) {
/* 61 */     return this.DEFAULT_CONVERTER.createInventory(holder, size);
/*    */   }
/*    */   
/*    */   public Inventory createInventory(InventoryHolder holder, int size, String title) {
/* 65 */     return this.DEFAULT_CONVERTER.createInventory(holder, size, title);
/*    */   }
/*    */   
/*    */   public static interface InventoryConverter {
/*    */     Inventory createInventory(InventoryHolder param1InventoryHolder, InventoryType param1InventoryType);
/*    */     
/*    */     Inventory createInventory(InventoryHolder param1InventoryHolder, InventoryType param1InventoryType, String param1String);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventor\\util\CraftInventoryCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */