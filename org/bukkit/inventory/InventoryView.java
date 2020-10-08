/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public abstract class InventoryView
/*     */ {
/*     */   public static final int OUTSIDE = -999;
/*     */   
/*     */   @NotNull
/*     */   public abstract Inventory getTopInventory();
/*     */   
/*     */   @NotNull
/*     */   public abstract Inventory getBottomInventory();
/*     */   
/*     */   @NotNull
/*     */   public abstract HumanEntity getPlayer();
/*     */   
/*     */   @NotNull
/*     */   public abstract InventoryType getType();
/*     */   
/*     */   public enum Property {
/*  26 */     BREW_TIME(0, InventoryType.BREWING),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     FUEL_TIME(1, InventoryType.BREWING),
/*     */ 
/*     */ 
/*     */     
/*  37 */     BURN_TIME(0, InventoryType.FURNACE),
/*     */ 
/*     */ 
/*     */     
/*  41 */     TICKS_FOR_CURRENT_FUEL(1, InventoryType.FURNACE),
/*     */ 
/*     */ 
/*     */     
/*  45 */     COOK_TIME(2, InventoryType.FURNACE),
/*     */ 
/*     */ 
/*     */     
/*  49 */     TICKS_FOR_CURRENT_SMELTING(3, InventoryType.FURNACE),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     ENCHANT_BUTTON1(0, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     ENCHANT_BUTTON2(1, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     ENCHANT_BUTTON3(2, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  68 */     ENCHANT_XP_SEED(3, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  72 */     ENCHANT_ID1(4, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  76 */     ENCHANT_ID2(5, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  80 */     ENCHANT_ID3(6, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  84 */     ENCHANT_LEVEL1(7, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  88 */     ENCHANT_LEVEL2(8, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  92 */     ENCHANT_LEVEL3(9, InventoryType.ENCHANTING),
/*     */ 
/*     */ 
/*     */     
/*  96 */     LEVELS(0, InventoryType.BEACON),
/*     */ 
/*     */ 
/*     */     
/* 100 */     PRIMARY_EFFECT(1, InventoryType.BEACON),
/*     */ 
/*     */ 
/*     */     
/* 104 */     SECONDARY_EFFECT(2, InventoryType.BEACON),
/*     */ 
/*     */ 
/*     */     
/* 108 */     REPAIR_COST(0, InventoryType.ANVIL),
/*     */ 
/*     */ 
/*     */     
/* 112 */     BOOK_PAGE(0, InventoryType.LECTERN); int id;
/*     */     InventoryType style;
/*     */     
/*     */     Property(int id, InventoryType appliesTo) {
/* 116 */       this.id = id;
/* 117 */       this.style = appliesTo;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public InventoryType getType() {
/* 122 */       return this.style;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public int getId() {
/* 133 */       return this.id;
/*     */     }
/*     */   }
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
/*     */   public void setItem(int slot, @Nullable ItemStack item) {
/* 180 */     Inventory inventory = getInventory(slot);
/* 181 */     if (inventory != null) {
/* 182 */       inventory.setItem(convertSlot(slot), item);
/* 183 */     } else if (item != null) {
/* 184 */       getPlayer().getWorld().dropItemNaturally(getPlayer().getLocation(), item);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItem(int slot) {
/* 196 */     Inventory inventory = getInventory(slot);
/* 197 */     return (inventory == null) ? null : inventory.getItem(convertSlot(slot));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setCursor(@Nullable ItemStack item) {
/* 207 */     getPlayer().setItemOnCursor(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final ItemStack getCursor() {
/* 218 */     return getPlayer().getItemOnCursor();
/*     */   }
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
/*     */   @Nullable
/*     */   public final Inventory getInventory(int rawSlot) {
/* 237 */     if (rawSlot == -999 || rawSlot == -1) {
/* 238 */       return null;
/*     */     }
/* 240 */     Preconditions.checkArgument((rawSlot >= 0), "Negative, non outside slot %s", rawSlot);
/* 241 */     Preconditions.checkArgument((rawSlot < countSlots()), "Slot %s greater than inventory slot count", rawSlot);
/*     */     
/* 243 */     if (rawSlot < getTopInventory().getSize()) {
/* 244 */       return getTopInventory();
/*     */     }
/* 246 */     return getBottomInventory();
/*     */   }
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
/*     */   public final int convertSlot(int rawSlot) {
/* 263 */     int numInTop = getTopInventory().getSize();
/*     */     
/* 265 */     if (rawSlot < numInTop) {
/* 266 */       return rawSlot;
/*     */     }
/*     */ 
/*     */     
/* 270 */     int slot = rawSlot - numInTop;
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (getType() == InventoryType.CRAFTING || getType() == InventoryType.CREATIVE) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 301 */       if (slot < 4)
/*     */       {
/* 303 */         return 39 - slot; } 
/* 304 */       if (slot > 39)
/*     */       {
/* 306 */         return slot;
/*     */       }
/*     */       
/* 309 */       slot -= 4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 314 */     if (slot >= 27) {
/*     */       
/* 316 */       slot -= 27;
/*     */     }
/*     */     else {
/*     */       
/* 320 */       slot += 9;
/*     */     } 
/*     */     
/* 323 */     return slot;
/*     */   }
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
/*     */   @NotNull
/*     */   public final InventoryType.SlotType getSlotType(int slot) {
/* 337 */     InventoryType.SlotType type = InventoryType.SlotType.CONTAINER;
/* 338 */     if (slot >= 0 && slot < getTopInventory().getSize()) {
/* 339 */       switch (getType()) {
/*     */         case BLAST_FURNACE:
/*     */         case FURNACE:
/*     */         case SMOKER:
/* 343 */           if (slot == 2) {
/* 344 */             type = InventoryType.SlotType.RESULT; break;
/* 345 */           }  if (slot == 1) {
/* 346 */             type = InventoryType.SlotType.FUEL; break;
/*     */           } 
/* 348 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         
/*     */         case BREWING:
/* 352 */           if (slot == 3) {
/* 353 */             type = InventoryType.SlotType.FUEL; break;
/*     */           } 
/* 355 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         
/*     */         case ENCHANTING:
/* 359 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         case WORKBENCH:
/*     */         case CRAFTING:
/* 363 */           if (slot == 0) {
/* 364 */             type = InventoryType.SlotType.RESULT; break;
/*     */           } 
/* 366 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         
/*     */         case BEACON:
/* 370 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         case ANVIL:
/*     */         case SMITHING:
/*     */         case CARTOGRAPHY:
/*     */         case GRINDSTONE:
/*     */         case MERCHANT:
/* 377 */           if (slot == 2) {
/* 378 */             type = InventoryType.SlotType.RESULT; break;
/*     */           } 
/* 380 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         
/*     */         case STONECUTTER:
/* 384 */           if (slot == 1) {
/* 385 */             type = InventoryType.SlotType.RESULT; break;
/*     */           } 
/* 387 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */         
/*     */         case LOOM:
/* 391 */           if (slot == 3) {
/* 392 */             type = InventoryType.SlotType.RESULT; break;
/*     */           } 
/* 394 */           type = InventoryType.SlotType.CRAFTING;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 401 */     } else if (slot < 0) {
/* 402 */       type = InventoryType.SlotType.OUTSIDE;
/* 403 */     } else if (getType() == InventoryType.CRAFTING) {
/* 404 */       if (slot < 9) {
/* 405 */         type = InventoryType.SlotType.ARMOR;
/* 406 */       } else if (slot > 35) {
/* 407 */         type = InventoryType.SlotType.QUICKBAR;
/*     */       } 
/* 409 */     } else if (slot >= countSlots() - 14) {
/* 410 */       type = InventoryType.SlotType.QUICKBAR;
/*     */     } 
/*     */     
/* 413 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void close() {
/* 420 */     getPlayer().closeInventory();
/*     */   }
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
/*     */   public final int countSlots() {
/* 433 */     return getTopInventory().getSize() + getBottomInventory().getSize();
/*     */   }
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
/*     */   public final boolean setProperty(@NotNull Property prop, int value) {
/* 446 */     return getPlayer().setWindowProperty(prop, value);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public abstract String getTitle();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\InventoryView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */