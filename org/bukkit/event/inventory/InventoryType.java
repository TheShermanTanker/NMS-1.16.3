/*     */ package org.bukkit.event.inventory;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum InventoryType
/*     */ {
/*  28 */   CHEST(27, "Chest"),
/*     */ 
/*     */ 
/*     */   
/*  32 */   DISPENSER(9, "Dispenser"),
/*     */ 
/*     */ 
/*     */   
/*  36 */   DROPPER(9, "Dropper"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   FURNACE(3, "Furnace"),
/*     */ 
/*     */ 
/*     */   
/*  45 */   WORKBENCH(10, "Crafting"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   CRAFTING(5, "Crafting", false),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   ENCHANTING(2, "Enchanting"),
/*     */ 
/*     */ 
/*     */   
/*  59 */   BREWING(5, "Brewing"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   PLAYER(41, "Player"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   CREATIVE(9, "Creative", false),
/*     */ 
/*     */ 
/*     */   
/*  75 */   MERCHANT(3, "Villager", false),
/*     */ 
/*     */ 
/*     */   
/*  79 */   ENDER_CHEST(27, "Ender Chest"),
/*     */ 
/*     */ 
/*     */   
/*  83 */   ANVIL(3, "Repairing"),
/*     */ 
/*     */ 
/*     */   
/*  87 */   SMITHING(3, "Upgrade Gear"),
/*     */ 
/*     */ 
/*     */   
/*  91 */   BEACON(1, "container.beacon"),
/*     */ 
/*     */ 
/*     */   
/*  95 */   HOPPER(5, "Item Hopper"),
/*     */ 
/*     */ 
/*     */   
/*  99 */   SHULKER_BOX(27, "Shulker Box"),
/*     */ 
/*     */ 
/*     */   
/* 103 */   BARREL(27, "Barrel"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   BLAST_FURNACE(3, "Blast Furnace"),
/*     */ 
/*     */ 
/*     */   
/* 112 */   LECTERN(1, "Lectern"),
/*     */ 
/*     */ 
/*     */   
/* 116 */   SMOKER(3, "Smoker"),
/*     */ 
/*     */ 
/*     */   
/* 120 */   LOOM(4, "Loom"),
/*     */ 
/*     */ 
/*     */   
/* 124 */   CARTOGRAPHY(3, "Cartography Table"),
/*     */ 
/*     */ 
/*     */   
/* 128 */   GRINDSTONE(3, "Repair & Disenchant"),
/*     */ 
/*     */ 
/*     */   
/* 132 */   STONECUTTER(2, "Stonecutter");
/*     */ 
/*     */   
/*     */   private final int size;
/*     */ 
/*     */   
/*     */   private final String title;
/*     */   
/*     */   private final boolean isCreatable;
/*     */ 
/*     */   
/*     */   InventoryType(int defaultSize, String defaultTitle, boolean isCreatable) {
/* 144 */     this.size = defaultSize;
/* 145 */     this.title = defaultTitle;
/* 146 */     this.isCreatable = isCreatable;
/*     */   }
/*     */   
/*     */   public int getDefaultSize() {
/* 150 */     return this.size;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getDefaultTitle() {
/* 155 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCreatable() {
/* 165 */     return this.isCreatable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum SlotType
/*     */   {
/* 172 */     RESULT,
/*     */ 
/*     */ 
/*     */     
/* 176 */     CRAFTING,
/*     */ 
/*     */ 
/*     */     
/* 180 */     ARMOR,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     CONTAINER,
/*     */ 
/*     */ 
/*     */     
/* 189 */     QUICKBAR,
/*     */ 
/*     */ 
/*     */     
/* 193 */     OUTSIDE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     FUEL;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */