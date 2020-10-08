/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftLegacy;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
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
/*     */ public class CraftInventory
/*     */   implements Inventory
/*     */ {
/*     */   protected final IInventory inventory;
/*     */   
/*     */   public CraftInventory(IInventory inventory) {
/*  35 */     this.inventory = inventory;
/*     */   }
/*     */   
/*     */   public IInventory getInventory() {
/*  39 */     return this.inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  44 */     return getInventory().getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int index) {
/*  49 */     ItemStack item = getInventory().getItem(index);
/*  50 */     return item.isEmpty() ? null : CraftItemStack.asCraftMirror(item);
/*     */   }
/*     */   
/*     */   protected ItemStack[] asCraftMirror(List<ItemStack> mcItems) {
/*  54 */     int size = mcItems.size();
/*  55 */     ItemStack[] items = new ItemStack[size];
/*     */     
/*  57 */     for (int i = 0; i < size; i++) {
/*  58 */       ItemStack mcItem = mcItems.get(i);
/*  59 */       items[i] = mcItem.isEmpty() ? null : CraftItemStack.asCraftMirror(mcItem);
/*     */     } 
/*     */     
/*  62 */     return items;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getStorageContents() {
/*  67 */     return getContents();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
/*  72 */     setContents(items);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getContents() {
/*  77 */     List<ItemStack> mcItems = getInventory().getContents();
/*     */     
/*  79 */     return asCraftMirror(mcItems);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContents(ItemStack[] items) {
/*  84 */     if (getSize() < items.length) {
/*  85 */       throw new IllegalArgumentException("Invalid inventory size; expected " + getSize() + " or less");
/*     */     }
/*     */     
/*  88 */     for (int i = 0; i < getSize(); i++) {
/*  89 */       if (i >= items.length) {
/*  90 */         setItem(i, null);
/*     */       } else {
/*  92 */         setItem(i, items[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int index, ItemStack item) {
/*  99 */     getInventory().setItem(index, CraftItemStack.asNMSCopy(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Material material) {
/* 104 */     Validate.notNull(material, "Material cannot be null");
/* 105 */     material = CraftLegacy.fromLegacy(material);
/* 106 */     for (ItemStack item : getStorageContents()) {
/* 107 */       if (item != null && item.getType() == material) {
/* 108 */         return true;
/*     */       }
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(ItemStack item) {
/* 116 */     if (item == null) {
/* 117 */       return false;
/*     */     }
/* 119 */     for (ItemStack i : getStorageContents()) {
/* 120 */       if (item.equals(i)) {
/* 121 */         return true;
/*     */       }
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Material material, int amount) {
/* 129 */     Validate.notNull(material, "Material cannot be null");
/* 130 */     material = CraftLegacy.fromLegacy(material);
/* 131 */     if (amount <= 0) {
/* 132 */       return true;
/*     */     }
/* 134 */     for (ItemStack item : getStorageContents()) {
/* 135 */       if (item != null && item.getType() == material && (
/* 136 */         amount -= item.getAmount()) <= 0) {
/* 137 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(ItemStack item, int amount) {
/* 146 */     if (item == null) {
/* 147 */       return false;
/*     */     }
/* 149 */     if (amount <= 0) {
/* 150 */       return true;
/*     */     }
/* 152 */     for (ItemStack i : getStorageContents()) {
/* 153 */       if (item.equals(i) && --amount <= 0) {
/* 154 */         return true;
/*     */       }
/*     */     } 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAtLeast(ItemStack item, int amount) {
/* 162 */     if (item == null) {
/* 163 */       return false;
/*     */     }
/* 165 */     if (amount <= 0) {
/* 166 */       return true;
/*     */     }
/* 168 */     for (ItemStack i : getStorageContents()) {
/* 169 */       if (item.isSimilar(i) && (amount -= i.getAmount()) <= 0) {
/* 170 */         return true;
/*     */       }
/*     */     } 
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, ItemStack> all(Material material) {
/* 178 */     Validate.notNull(material, "Material cannot be null");
/* 179 */     material = CraftLegacy.fromLegacy(material);
/* 180 */     HashMap<Integer, ItemStack> slots = new HashMap<>();
/*     */     
/* 182 */     ItemStack[] inventory = getStorageContents();
/* 183 */     for (int i = 0; i < inventory.length; i++) {
/* 184 */       ItemStack item = inventory[i];
/* 185 */       if (item != null && item.getType() == material) {
/* 186 */         slots.put(Integer.valueOf(i), item);
/*     */       }
/*     */     } 
/* 189 */     return slots;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, ItemStack> all(ItemStack item) {
/* 194 */     HashMap<Integer, ItemStack> slots = new HashMap<>();
/* 195 */     if (item != null) {
/* 196 */       ItemStack[] inventory = getStorageContents();
/* 197 */       for (int i = 0; i < inventory.length; i++) {
/* 198 */         if (item.equals(inventory[i])) {
/* 199 */           slots.put(Integer.valueOf(i), inventory[i]);
/*     */         }
/*     */       } 
/*     */     } 
/* 203 */     return slots;
/*     */   }
/*     */ 
/*     */   
/*     */   public int first(Material material) {
/* 208 */     Validate.notNull(material, "Material cannot be null");
/* 209 */     material = CraftLegacy.fromLegacy(material);
/* 210 */     ItemStack[] inventory = getStorageContents();
/* 211 */     for (int i = 0; i < inventory.length; i++) {
/* 212 */       ItemStack item = inventory[i];
/* 213 */       if (item != null && item.getType() == material) {
/* 214 */         return i;
/*     */       }
/*     */     } 
/* 217 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int first(ItemStack item) {
/* 222 */     return first(item, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private int first(ItemStack item, boolean withAmount) {
/* 227 */     return first(item, withAmount, getStorageContents());
/*     */   }
/*     */ 
/*     */   
/*     */   private int first(ItemStack item, boolean withAmount, ItemStack[] inventory) {
/* 232 */     if (item == null) {
/* 233 */       return -1;
/*     */     }
/*     */     
/* 236 */     for (int i = 0; i < inventory.length; i++) {
/* 237 */       if (inventory[i] != null)
/*     */       {
/* 239 */         if (withAmount ? item.equals(inventory[i]) : item.isSimilar(inventory[i]))
/* 240 */           return i; 
/*     */       }
/*     */     } 
/* 243 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int firstEmpty() {
/* 248 */     ItemStack[] inventory = getStorageContents();
/* 249 */     for (int i = 0; i < inventory.length; i++) {
/* 250 */       if (inventory[i] == null) {
/* 251 */         return i;
/*     */       }
/*     */     } 
/* 254 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 259 */     return this.inventory.isEmpty();
/*     */   }
/*     */   
/*     */   public int firstPartial(Material material) {
/* 263 */     Validate.notNull(material, "Material cannot be null");
/* 264 */     material = CraftLegacy.fromLegacy(material);
/* 265 */     ItemStack[] inventory = getStorageContents();
/* 266 */     for (int i = 0; i < inventory.length; i++) {
/* 267 */       ItemStack item = inventory[i];
/* 268 */       if (item != null && item.getType() == material && item.getAmount() < item.getMaxStackSize()) {
/* 269 */         return i;
/*     */       }
/*     */     } 
/* 272 */     return -1;
/*     */   }
/*     */   
/*     */   private int firstPartial(ItemStack item) {
/* 276 */     ItemStack[] inventory = getStorageContents();
/* 277 */     ItemStack filteredItem = CraftItemStack.asCraftCopy(item);
/* 278 */     if (item == null) {
/* 279 */       return -1;
/*     */     }
/* 281 */     for (int i = 0; i < inventory.length; i++) {
/* 282 */       ItemStack cItem = inventory[i];
/* 283 */       if (cItem != null && cItem.getAmount() < cItem.getMaxStackSize() && cItem.isSimilar(filteredItem)) {
/* 284 */         return i;
/*     */       }
/*     */     } 
/* 287 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, ItemStack> addItem(ItemStack... items) {
/* 292 */     Validate.noNullElements((Object[])items, "Item cannot be null");
/* 293 */     HashMap<Integer, ItemStack> leftover = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     for (int i = 0; i < items.length; i++) {
/* 302 */       ItemStack item = items[i];
/*     */       
/*     */       while (true) {
/* 305 */         int firstPartial = firstPartial(item);
/*     */ 
/*     */         
/* 308 */         if (firstPartial == -1) {
/*     */           
/* 310 */           int firstFree = firstEmpty();
/*     */           
/* 312 */           if (firstFree == -1) {
/*     */             
/* 314 */             leftover.put(Integer.valueOf(i), item);
/*     */             
/*     */             break;
/*     */           } 
/* 318 */           if (item.getAmount() > getMaxItemStack()) {
/* 319 */             CraftItemStack stack = CraftItemStack.asCraftCopy(item);
/* 320 */             stack.setAmount(getMaxItemStack());
/* 321 */             setItem(firstFree, stack);
/* 322 */             item.setAmount(item.getAmount() - getMaxItemStack());
/*     */             continue;
/*     */           } 
/* 325 */           setItem(firstFree, item);
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 331 */         ItemStack partialItem = getItem(firstPartial);
/*     */         
/* 333 */         int amount = item.getAmount();
/* 334 */         int partialAmount = partialItem.getAmount();
/* 335 */         int maxAmount = partialItem.getMaxStackSize();
/*     */ 
/*     */         
/* 338 */         if (amount + partialAmount <= maxAmount) {
/* 339 */           partialItem.setAmount(amount + partialAmount);
/*     */           
/* 341 */           setItem(firstPartial, partialItem);
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 346 */         partialItem.setAmount(maxAmount);
/*     */         
/* 348 */         setItem(firstPartial, partialItem);
/* 349 */         item.setAmount(amount + partialAmount - maxAmount);
/*     */       } 
/*     */     } 
/*     */     
/* 353 */     return leftover;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<Integer, ItemStack> removeItem(ItemStack... items) {
/* 359 */     return removeItem(false, items);
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, ItemStack> removeItemAnySlot(ItemStack... items) {
/* 364 */     return removeItem(true, items);
/*     */   }
/*     */ 
/*     */   
/*     */   private HashMap<Integer, ItemStack> removeItem(boolean searchEntire, ItemStack... items) {
/* 369 */     Validate.notNull(items, "Items cannot be null");
/* 370 */     HashMap<Integer, ItemStack> leftover = new HashMap<>();
/*     */ 
/*     */ 
/*     */     
/* 374 */     for (int i = 0; i < items.length; i++) {
/* 375 */       ItemStack item = items[i];
/* 376 */       int toDelete = item.getAmount();
/*     */ 
/*     */       
/*     */       do {
/* 380 */         ItemStack[] toSearch = searchEntire ? getContents() : getStorageContents();
/* 381 */         int first = first(item, false, toSearch);
/*     */ 
/*     */ 
/*     */         
/* 385 */         if (first == -1) {
/* 386 */           item.setAmount(toDelete);
/* 387 */           leftover.put(Integer.valueOf(i), item);
/*     */           break;
/*     */         } 
/* 390 */         ItemStack itemStack = getItem(first);
/* 391 */         int amount = itemStack.getAmount();
/*     */         
/* 393 */         if (amount <= toDelete) {
/* 394 */           toDelete -= amount;
/*     */           
/* 396 */           clear(first);
/*     */         } else {
/*     */           
/* 399 */           itemStack.setAmount(amount - toDelete);
/* 400 */           setItem(first, itemStack);
/* 401 */           toDelete = 0;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 406 */       while (toDelete > 0);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 411 */     return leftover;
/*     */   }
/*     */   
/*     */   private int getMaxItemStack() {
/* 415 */     return getInventory().getMaxStackSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(Material material) {
/* 420 */     Validate.notNull(material, "Material cannot be null");
/* 421 */     material = CraftLegacy.fromLegacy(material);
/* 422 */     ItemStack[] items = getStorageContents();
/* 423 */     for (int i = 0; i < items.length; i++) {
/* 424 */       if (items[i] != null && items[i].getType() == material) {
/* 425 */         clear(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(ItemStack item) {
/* 432 */     ItemStack[] items = getStorageContents();
/* 433 */     for (int i = 0; i < items.length; i++) {
/* 434 */       if (items[i] != null && items[i].equals(item)) {
/* 435 */         clear(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear(int index) {
/* 442 */     setItem(index, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 447 */     for (int i = 0; i < getSize(); i++) {
/* 448 */       clear(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator<ItemStack> iterator() {
/* 454 */     return new InventoryIterator(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator<ItemStack> iterator(int index) {
/* 459 */     if (index < 0) {
/* 460 */       index += getSize() + 1;
/*     */     }
/* 462 */     return new InventoryIterator(this, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/* 467 */     return this.inventory.getViewers();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryType getType() {
/* 473 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.InventoryCrafting)
/* 474 */       return (this.inventory.getSize() >= 9) ? InventoryType.WORKBENCH : InventoryType.CRAFTING; 
/* 475 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.PlayerInventory)
/* 476 */       return InventoryType.PLAYER; 
/* 477 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityDropper)
/* 478 */       return InventoryType.DROPPER; 
/* 479 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityDispenser)
/* 480 */       return InventoryType.DISPENSER; 
/* 481 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityBlastFurnace)
/* 482 */       return InventoryType.BLAST_FURNACE; 
/* 483 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntitySmoker)
/* 484 */       return InventoryType.SMOKER; 
/* 485 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityFurnace)
/* 486 */       return InventoryType.FURNACE; 
/* 487 */     if (this instanceof CraftInventoryEnchanting)
/* 488 */       return InventoryType.ENCHANTING; 
/* 489 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityBrewingStand)
/* 490 */       return InventoryType.BREWING; 
/* 491 */     if (this.inventory instanceof CraftInventoryCustom.MinecraftInventory)
/* 492 */       return ((CraftInventoryCustom.MinecraftInventory)this.inventory).getType(); 
/* 493 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.InventoryEnderChest)
/* 494 */       return InventoryType.ENDER_CHEST; 
/* 495 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.InventoryMerchant)
/* 496 */       return InventoryType.MERCHANT; 
/* 497 */     if (this instanceof CraftInventoryBeacon)
/* 498 */       return InventoryType.BEACON; 
/* 499 */     if (this instanceof CraftInventoryAnvil)
/* 500 */       return InventoryType.ANVIL; 
/* 501 */     if (this instanceof CraftInventorySmithing)
/* 502 */       return InventoryType.SMITHING; 
/* 503 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.IHopper)
/* 504 */       return InventoryType.HOPPER; 
/* 505 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityShulkerBox)
/* 506 */       return InventoryType.SHULKER_BOX; 
/* 507 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityBarrel)
/* 508 */       return InventoryType.BARREL; 
/* 509 */     if (this.inventory instanceof net.minecraft.server.v1_16_R2.TileEntityLectern.LecternInventory)
/* 510 */       return InventoryType.LECTERN; 
/* 511 */     if (this instanceof CraftInventoryLoom)
/* 512 */       return InventoryType.LOOM; 
/* 513 */     if (this instanceof CraftInventoryCartography)
/* 514 */       return InventoryType.CARTOGRAPHY; 
/* 515 */     if (this instanceof CraftInventoryGrindstone)
/* 516 */       return InventoryType.GRINDSTONE; 
/* 517 */     if (this instanceof CraftInventoryStonecutter) {
/* 518 */       return InventoryType.STONECUTTER;
/*     */     }
/* 520 */     return InventoryType.CHEST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryHolder getHolder() {
/* 526 */     return this.inventory.getOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryHolder getHolder(boolean useSnapshot) {
/* 532 */     return (this.inventory instanceof TileEntity) ? ((TileEntity)this.inventory).getOwner(useSnapshot) : getHolder();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 538 */     return this.inventory.getMaxStackSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxStackSize(int size) {
/* 543 */     this.inventory.setMaxStackSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 548 */     return this.inventory.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 553 */     return (obj instanceof CraftInventory && ((CraftInventory)obj).inventory.equals(this.inventory));
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/* 558 */     return this.inventory.getLocation();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */