/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory.util;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryCustom;
/*    */ import org.bukkit.event.inventory.InventoryType;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ 
/*    */ public class CraftCustomInventoryConverter
/*    */   implements CraftInventoryCreator.InventoryConverter
/*    */ {
/*    */   public Inventory createInventory(InventoryHolder holder, InventoryType type) {
/* 12 */     return (Inventory)new CraftInventoryCustom(holder, type);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
/* 17 */     return (Inventory)new CraftInventoryCustom(owner, type, title);
/*    */   }
/*    */   
/*    */   public Inventory createInventory(InventoryHolder owner, int size) {
/* 21 */     return (Inventory)new CraftInventoryCustom(owner, size);
/*    */   }
/*    */   
/*    */   public Inventory createInventory(InventoryHolder owner, int size, String title) {
/* 25 */     return (Inventory)new CraftInventoryCustom(owner, size, title);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventor\\util\CraftCustomInventoryConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */