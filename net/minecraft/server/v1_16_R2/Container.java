/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public abstract class Container
/*     */ {
/*  24 */   public NonNullList<ItemStack> items = NonNullList.a();
/*  25 */   public List<Slot> slots = Lists.newArrayList();
/*  26 */   private final List<ContainerProperty> d = Lists.newArrayList();
/*     */   @Nullable
/*     */   private final Containers<?> e;
/*     */   public final int windowId;
/*  30 */   private int dragType = -1;
/*     */   private int h;
/*  32 */   private final Set<Slot> i = Sets.newHashSet();
/*  33 */   private final List<ICrafting> listeners = Lists.newArrayList();
/*  34 */   private final Set<EntityHuman> k = Sets.newHashSet();
/*     */   
/*     */   public boolean checkReachable = true;
/*     */   private IChatBaseComponent title;
/*     */   
/*     */   public void transferTo(Container other, CraftHumanEntity player) {
/*  40 */     InventoryView source = getBukkitView(), destination = other.getBukkitView();
/*  41 */     ((CraftInventory)source.getTopInventory()).getInventory().onClose(player);
/*  42 */     ((CraftInventory)source.getBottomInventory()).getInventory().onClose(player);
/*  43 */     ((CraftInventory)destination.getTopInventory()).getInventory().onOpen(player);
/*  44 */     ((CraftInventory)destination.getBottomInventory()).getInventory().onOpen(player);
/*     */   }
/*     */   
/*     */   public final IChatBaseComponent getTitle() {
/*  48 */     Preconditions.checkState((this.title != null), "Title not set");
/*  49 */     return this.title;
/*     */   }
/*     */   public final void setTitle(IChatBaseComponent title) {
/*  52 */     Preconditions.checkState((this.title == null), "Title already set");
/*  53 */     this.title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container(@Nullable Containers<?> containers, int i) {
/*  58 */     this.e = containers;
/*  59 */     this.windowId = i;
/*     */   }
/*     */   
/*     */   protected static boolean a(ContainerAccess containeraccess, EntityHuman entityhuman, Block block) {
/*  63 */     return ((Boolean)containeraccess.<Boolean>a((world, blockposition) -> Boolean.valueOf(!world.getType(blockposition).a(block) ? false : ((entityhuman.h(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D) <= 64.0D))), 
/*     */         
/*  65 */         Boolean.valueOf(true))).booleanValue();
/*     */   }
/*     */   
/*     */   public Containers<?> getType() {
/*  69 */     if (this.e == null) {
/*  70 */       throw new UnsupportedOperationException("Unable to construct this menu by type");
/*     */     }
/*  72 */     return this.e;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(IInventory iinventory, int i) {
/*  77 */     int j = iinventory.getSize();
/*     */     
/*  79 */     if (j < i) {
/*  80 */       throw new IllegalArgumentException("Container size " + j + " is smaller than expected " + i);
/*     */     }
/*     */   }
/*     */   
/*     */   protected static void a(IContainerProperties icontainerproperties, int i) {
/*  85 */     int j = icontainerproperties.a();
/*     */     
/*  87 */     if (j < i) {
/*  88 */       throw new IllegalArgumentException("Container data count " + j + " is smaller than expected " + i);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Slot a(Slot slot) {
/*  93 */     slot.rawSlotIndex = this.slots.size();
/*  94 */     this.slots.add(slot);
/*  95 */     this.items.add(ItemStack.b);
/*  96 */     return slot;
/*     */   }
/*     */   
/*     */   protected ContainerProperty a(ContainerProperty containerproperty) {
/* 100 */     this.d.add(containerproperty);
/* 101 */     return containerproperty;
/*     */   }
/*     */   
/*     */   protected void a(IContainerProperties icontainerproperties) {
/* 105 */     for (int i = 0; i < icontainerproperties.a(); i++) {
/* 106 */       a(ContainerProperty.a(icontainerproperties, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSlotListener(ICrafting icrafting) {
/* 112 */     if (!this.listeners.contains(icrafting)) {
/* 113 */       this.listeners.add(icrafting);
/* 114 */       icrafting.a(this, b());
/* 115 */       c();
/*     */     } 
/*     */   }
/*     */   
/*     */   public NonNullList<ItemStack> b() {
/* 120 */     NonNullList<ItemStack> nonnulllist = NonNullList.a();
/*     */     
/* 122 */     for (int i = 0; i < this.slots.size(); i++) {
/* 123 */       nonnulllist.add(((Slot)this.slots.get(i)).getItem());
/*     */     }
/*     */     
/* 126 */     return nonnulllist;
/*     */   }
/*     */   public final void notifyListeners() {
/* 129 */     c();
/*     */   }
/*     */   public void c() {
/*     */     int i;
/* 133 */     for (i = 0; i < this.slots.size(); i++) {
/* 134 */       ItemStack itemstack = ((Slot)this.slots.get(i)).getItem();
/* 135 */       ItemStack itemstack1 = this.items.get(i);
/*     */       
/* 137 */       if (!ItemStack.matches(itemstack1, itemstack)) {
/* 138 */         ItemStack itemstack2 = itemstack.cloneItemStack();
/*     */         
/* 140 */         this.items.set(i, itemstack2);
/* 141 */         Iterator<ICrafting> iterator = this.listeners.iterator();
/*     */         
/* 143 */         while (iterator.hasNext()) {
/* 144 */           ICrafting icrafting = iterator.next();
/*     */           
/* 146 */           icrafting.a(this, i, itemstack2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     for (i = 0; i < this.d.size(); i++) {
/* 152 */       ContainerProperty containerproperty = this.d.get(i);
/*     */       
/* 154 */       if (containerproperty.c()) {
/* 155 */         Iterator<ICrafting> iterator1 = this.listeners.iterator();
/*     */         
/* 157 */         while (iterator1.hasNext()) {
/* 158 */           ICrafting icrafting1 = iterator1.next();
/*     */           
/* 160 */           icrafting1.setContainerData(this, i, containerproperty.get());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public Slot getSlot(int i) {
/* 172 */     return this.slots.get(i);
/*     */   }
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 176 */     Slot slot = this.slots.get(i);
/*     */     
/* 178 */     return (slot != null) ? slot.getItem() : ItemStack.b;
/*     */   }
/*     */   
/*     */   public ItemStack a(int i, int j, InventoryClickType inventoryclicktype, EntityHuman entityhuman) {
/*     */     try {
/* 183 */       return b(i, j, inventoryclicktype, entityhuman);
/* 184 */     } catch (Exception exception) {
/* 185 */       CrashReport crashreport = CrashReport.a(exception, "Container click");
/* 186 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Click info");
/*     */       
/* 188 */       crashreportsystemdetails.a("Menu Type", () -> (this.e != null) ? IRegistry.MENU.getKey(this.e).toString() : "<no type>");
/*     */ 
/*     */       
/* 191 */       crashreportsystemdetails.a("Menu Class", () -> getClass().getCanonicalName());
/*     */ 
/*     */       
/* 194 */       crashreportsystemdetails.a("Slot Count", Integer.valueOf(this.slots.size()));
/* 195 */       crashreportsystemdetails.a("Slot", Integer.valueOf(i));
/* 196 */       crashreportsystemdetails.a("Button", Integer.valueOf(j));
/* 197 */       crashreportsystemdetails.a("Type", inventoryclicktype);
/* 198 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ItemStack b(int i, int j, InventoryClickType inventoryclicktype, EntityHuman entityhuman) {
/* 203 */     ItemStack itemstack = ItemStack.b;
/* 204 */     PlayerInventory playerinventory = entityhuman.inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     if (inventoryclicktype == InventoryClickType.QUICK_CRAFT) {
/* 211 */       int i1 = this.h;
/*     */       
/* 213 */       this.h = c(j);
/* 214 */       if ((i1 != 1 || this.h != 2) && i1 != this.h) {
/* 215 */         d();
/* 216 */       } else if (playerinventory.getCarried().isEmpty()) {
/* 217 */         d();
/* 218 */       } else if (this.h == 0) {
/* 219 */         this.dragType = b(j);
/* 220 */         if (a(this.dragType, entityhuman)) {
/* 221 */           this.h = 1;
/* 222 */           this.i.clear();
/*     */         } else {
/* 224 */           d();
/*     */         } 
/* 226 */       } else if (this.h == 1) {
/* 227 */         Slot slot = (i < this.slots.size()) ? this.slots.get(i) : null;
/*     */         
/* 229 */         ItemStack itemstack1 = playerinventory.getCarried();
/* 230 */         if (slot != null && a(slot, itemstack1, true) && slot.isAllowed(itemstack1) && (this.dragType == 2 || itemstack1.getCount() > this.i.size()) && b(slot)) {
/* 231 */           this.i.add(slot);
/*     */         }
/* 233 */       } else if (this.h == 2) {
/* 234 */         if (!this.i.isEmpty()) {
/* 235 */           ItemStack itemstack2 = playerinventory.getCarried().cloneItemStack();
/* 236 */           int k = playerinventory.getCarried().getCount();
/* 237 */           Iterator<Slot> iterator = this.i.iterator();
/*     */           
/* 239 */           Map<Integer, ItemStack> draggedSlots = new HashMap<>();
/* 240 */           while (iterator.hasNext()) {
/* 241 */             Slot slot1 = iterator.next();
/* 242 */             ItemStack itemstack3 = playerinventory.getCarried();
/*     */             
/* 244 */             if (slot1 != null && a(slot1, itemstack3, true) && slot1.isAllowed(itemstack3) && (this.dragType == 2 || itemstack3.getCount() >= this.i.size()) && b(slot1)) {
/* 245 */               ItemStack itemstack4 = itemstack2.cloneItemStack();
/* 246 */               int j1 = slot1.hasItem() ? slot1.getItem().getCount() : 0;
/*     */               
/* 248 */               a(this.i, this.dragType, itemstack4, j1);
/* 249 */               int l = Math.min(itemstack4.getMaxStackSize(), slot1.getMaxStackSize(itemstack4));
/* 250 */               if (itemstack4.getCount() > l) {
/* 251 */                 itemstack4.setCount(l);
/*     */               }
/*     */               
/* 254 */               k -= itemstack4.getCount() - j1;
/*     */               
/* 256 */               draggedSlots.put(Integer.valueOf(slot1.rawSlotIndex), itemstack4);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 261 */           InventoryView view = getBukkitView();
/* 262 */           CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(itemstack2);
/* 263 */           craftItemStack.setAmount(k);
/* 264 */           Map<Integer, ItemStack> eventmap = new HashMap<>();
/* 265 */           for (Map.Entry<Integer, ItemStack> ditem : draggedSlots.entrySet()) {
/* 266 */             eventmap.put(ditem.getKey(), CraftItemStack.asBukkitCopy(ditem.getValue()));
/*     */           }
/*     */ 
/*     */           
/* 270 */           ItemStack oldCursor = playerinventory.getCarried();
/* 271 */           playerinventory.setCarried(CraftItemStack.asNMSCopy((ItemStack)craftItemStack));
/*     */           
/* 273 */           InventoryDragEvent event = new InventoryDragEvent(view, (craftItemStack.getType() != Material.AIR) ? (ItemStack)craftItemStack : null, CraftItemStack.asBukkitCopy(oldCursor), (this.dragType == 1), eventmap);
/* 274 */           entityhuman.world.getServer().getPluginManager().callEvent((Event)event);
/*     */ 
/*     */           
/* 277 */           boolean needsUpdate = (event.getResult() != Event.Result.DEFAULT);
/*     */           
/* 279 */           if (event.getResult() != Event.Result.DENY) {
/* 280 */             for (Map.Entry<Integer, ItemStack> dslot : draggedSlots.entrySet()) {
/* 281 */               view.setItem(((Integer)dslot.getKey()).intValue(), CraftItemStack.asBukkitCopy(dslot.getValue()));
/*     */             }
/*     */ 
/*     */             
/* 285 */             if (playerinventory.getCarried() != null) {
/* 286 */               playerinventory.setCarried(CraftItemStack.asNMSCopy(event.getCursor()));
/* 287 */               needsUpdate = true;
/*     */             } 
/*     */           } else {
/* 290 */             playerinventory.setCarried(oldCursor);
/*     */           } 
/*     */           
/* 293 */           if (needsUpdate && entityhuman instanceof EntityPlayer) {
/* 294 */             ((EntityPlayer)entityhuman).updateInventory(this);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 299 */         d();
/*     */       } else {
/* 301 */         d();
/*     */       } 
/* 303 */     } else if (this.h != 0) {
/* 304 */       d();
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 309 */     else if ((inventoryclicktype == InventoryClickType.PICKUP || inventoryclicktype == InventoryClickType.QUICK_MOVE) && (j == 0 || j == 1)) {
/* 310 */       if (i == -999) {
/* 311 */         if (!playerinventory.getCarried().isEmpty()) {
/* 312 */           if (j == 0) {
/*     */             
/* 314 */             ItemStack carried = playerinventory.getCarried();
/* 315 */             playerinventory.setCarried(ItemStack.b);
/* 316 */             entityhuman.drop(carried, true);
/*     */           } 
/*     */ 
/*     */           
/* 320 */           if (j == 1) {
/* 321 */             entityhuman.drop(playerinventory.getCarried().cloneAndSubtract(1), true);
/*     */           }
/*     */         } 
/* 324 */       } else if (inventoryclicktype == InventoryClickType.QUICK_MOVE) {
/* 325 */         if (i < 0) {
/* 326 */           return ItemStack.b;
/*     */         }
/*     */         
/* 329 */         Slot slot2 = this.slots.get(i);
/* 330 */         if (slot2 == null || !slot2.isAllowed(entityhuman)) {
/* 331 */           return ItemStack.b;
/*     */         }
/*     */         
/* 334 */         for (ItemStack itemstack2 = shiftClick(entityhuman, i); !itemstack2.isEmpty() && ItemStack.c(slot2.getItem(), itemstack2); itemstack2 = shiftClick(entityhuman, i)) {
/* 335 */           itemstack = itemstack2.cloneItemStack();
/*     */         }
/*     */       } else {
/* 338 */         if (i < 0) {
/* 339 */           return ItemStack.b;
/*     */         }
/*     */         
/* 342 */         Slot slot2 = this.slots.get(i);
/* 343 */         if (slot2 != null) {
/* 344 */           ItemStack itemstack2 = slot2.getItem();
/* 345 */           ItemStack itemstack1 = playerinventory.getCarried();
/* 346 */           if (!itemstack2.isEmpty()) {
/* 347 */             itemstack = itemstack2.cloneItemStack();
/*     */           }
/*     */           
/* 350 */           if (itemstack2.isEmpty()) {
/* 351 */             if (!itemstack1.isEmpty() && slot2.isAllowed(itemstack1)) {
/* 352 */               int k1 = (j == 0) ? itemstack1.getCount() : 1;
/* 353 */               if (k1 > slot2.getMaxStackSize(itemstack1)) {
/* 354 */                 k1 = slot2.getMaxStackSize(itemstack1);
/*     */               }
/*     */               
/* 357 */               slot2.set(itemstack1.cloneAndSubtract(k1));
/*     */             } 
/* 359 */           } else if (slot2.isAllowed(entityhuman)) {
/* 360 */             if (itemstack1.isEmpty()) {
/* 361 */               if (itemstack2.isEmpty()) {
/* 362 */                 slot2.set(ItemStack.b);
/* 363 */                 playerinventory.setCarried(ItemStack.b);
/*     */               } else {
/* 365 */                 int k1 = (j == 0) ? itemstack2.getCount() : ((itemstack2.getCount() + 1) / 2);
/* 366 */                 playerinventory.setCarried(slot2.a(k1));
/* 367 */                 if (itemstack2.isEmpty()) {
/* 368 */                   slot2.set(ItemStack.b);
/*     */                 }
/*     */                 
/* 371 */                 slot2.a(entityhuman, playerinventory.getCarried());
/*     */               } 
/* 373 */             } else if (slot2.isAllowed(itemstack1)) {
/* 374 */               if (a(itemstack2, itemstack1)) {
/* 375 */                 int k1 = (j == 0) ? itemstack1.getCount() : 1;
/* 376 */                 if (k1 > slot2.getMaxStackSize(itemstack1) - itemstack2.getCount()) {
/* 377 */                   k1 = slot2.getMaxStackSize(itemstack1) - itemstack2.getCount();
/*     */                 }
/*     */                 
/* 380 */                 if (k1 > itemstack1.getMaxStackSize() - itemstack2.getCount()) {
/* 381 */                   k1 = itemstack1.getMaxStackSize() - itemstack2.getCount();
/*     */                 }
/*     */                 
/* 384 */                 itemstack1.subtract(k1);
/* 385 */                 itemstack2.add(k1);
/* 386 */               } else if (itemstack1.getCount() <= slot2.getMaxStackSize(itemstack1)) {
/* 387 */                 slot2.set(itemstack1);
/* 388 */                 playerinventory.setCarried(itemstack2);
/*     */               } 
/* 390 */             } else if (itemstack1.getMaxStackSize() > 1 && a(itemstack2, itemstack1) && !itemstack2.isEmpty()) {
/* 391 */               int k1 = itemstack2.getCount();
/* 392 */               if (k1 + itemstack1.getCount() <= itemstack1.getMaxStackSize()) {
/* 393 */                 itemstack1.add(k1);
/* 394 */                 itemstack2 = slot2.a(k1);
/* 395 */                 if (itemstack2.isEmpty()) {
/* 396 */                   slot2.set(ItemStack.b);
/*     */                 }
/*     */                 
/* 399 */                 slot2.a(entityhuman, playerinventory.getCarried());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 404 */           slot2.d();
/*     */           
/* 406 */           if (entityhuman instanceof EntityPlayer && slot2.getMaxStackSize() != 64) {
/* 407 */             ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutSetSlot(this.windowId, slot2.rawSlotIndex, slot2.getItem()));
/*     */             
/* 409 */             if (getBukkitView().getType() == InventoryType.WORKBENCH || getBukkitView().getType() == InventoryType.CRAFTING) {
/* 410 */               ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutSetSlot(this.windowId, 0, getSlot(0).getItem()));
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 416 */     } else if (inventoryclicktype == InventoryClickType.SWAP) {
/* 417 */       Slot slot2 = this.slots.get(i);
/* 418 */       ItemStack itemstack2 = playerinventory.getItem(j);
/* 419 */       ItemStack itemstack1 = slot2.getItem();
/* 420 */       if (!itemstack2.isEmpty() || !itemstack1.isEmpty()) {
/* 421 */         if (itemstack2.isEmpty()) {
/* 422 */           if (slot2.isAllowed(entityhuman)) {
/* 423 */             playerinventory.setItem(j, itemstack1);
/* 424 */             slot2.b(itemstack1.getCount());
/* 425 */             slot2.set(ItemStack.b);
/* 426 */             slot2.a(entityhuman, itemstack1);
/*     */           } 
/* 428 */         } else if (itemstack1.isEmpty()) {
/* 429 */           if (slot2.isAllowed(itemstack2)) {
/* 430 */             int k1 = slot2.getMaxStackSize(itemstack2);
/* 431 */             if (itemstack2.getCount() > k1) {
/* 432 */               slot2.set(itemstack2.cloneAndSubtract(k1));
/*     */             } else {
/* 434 */               slot2.set(itemstack2);
/* 435 */               playerinventory.setItem(j, ItemStack.b);
/*     */             } 
/*     */           } 
/* 438 */         } else if (slot2.isAllowed(entityhuman) && slot2.isAllowed(itemstack2)) {
/* 439 */           int k1 = slot2.getMaxStackSize(itemstack2);
/* 440 */           if (itemstack2.getCount() > k1) {
/* 441 */             slot2.set(itemstack2.cloneAndSubtract(k1));
/* 442 */             slot2.a(entityhuman, itemstack1);
/* 443 */             if (!playerinventory.pickup(itemstack1)) {
/* 444 */               entityhuman.drop(itemstack1, true);
/*     */             }
/*     */           } else {
/* 447 */             slot2.set(itemstack2);
/* 448 */             playerinventory.setItem(j, itemstack1);
/* 449 */             slot2.a(entityhuman, itemstack1);
/*     */           } 
/*     */         } 
/*     */       }
/* 453 */     } else if (inventoryclicktype == InventoryClickType.CLONE && entityhuman.abilities.canInstantlyBuild && playerinventory.getCarried().isEmpty() && i >= 0) {
/* 454 */       Slot slot2 = this.slots.get(i);
/* 455 */       if (slot2 != null && slot2.hasItem()) {
/* 456 */         ItemStack itemstack2 = slot2.getItem().cloneItemStack();
/* 457 */         itemstack2.setCount(itemstack2.getMaxStackSize());
/* 458 */         playerinventory.setCarried(itemstack2);
/*     */       } 
/* 460 */     } else if (inventoryclicktype == InventoryClickType.THROW && playerinventory.getCarried().isEmpty() && i >= 0) {
/* 461 */       Slot slot2 = this.slots.get(i);
/* 462 */       if (slot2 != null && slot2.hasItem() && slot2.isAllowed(entityhuman)) {
/* 463 */         ItemStack itemstack2 = slot2.a((j == 0) ? 1 : slot2.getItem().getCount());
/* 464 */         slot2.a(entityhuman, itemstack2);
/* 465 */         entityhuman.drop(itemstack2, true);
/*     */       } 
/* 467 */     } else if (inventoryclicktype == InventoryClickType.PICKUP_ALL && i >= 0) {
/* 468 */       Slot slot2 = this.slots.get(i);
/* 469 */       ItemStack itemstack2 = playerinventory.getCarried();
/* 470 */       if (!itemstack2.isEmpty() && (slot2 == null || !slot2.hasItem() || !slot2.isAllowed(entityhuman))) {
/* 471 */         int k = (j == 0) ? 0 : (this.slots.size() - 1);
/* 472 */         int k1 = (j == 0) ? 1 : -1;
/*     */         
/* 474 */         for (int l1 = 0; l1 < 2; l1++) {
/* 475 */           int i2; for (i2 = k; i2 >= 0 && i2 < this.slots.size() && itemstack2.getCount() < itemstack2.getMaxStackSize(); i2 += k1) {
/* 476 */             Slot slot3 = this.slots.get(i2);
/*     */             
/* 478 */             if (slot3.hasItem() && a(slot3, itemstack2, true) && slot3.isAllowed(entityhuman) && a(itemstack2, slot3)) {
/* 479 */               ItemStack itemstack5 = slot3.getItem();
/*     */               
/* 481 */               if (l1 != 0 || itemstack5.getCount() != itemstack5.getMaxStackSize()) {
/* 482 */                 int l = Math.min(itemstack2.getMaxStackSize() - itemstack2.getCount(), itemstack5.getCount());
/* 483 */                 ItemStack itemstack6 = slot3.a(l);
/*     */                 
/* 485 */                 itemstack2.add(l);
/* 486 */                 if (itemstack6.isEmpty()) {
/* 487 */                   slot3.set(ItemStack.b);
/*     */                 }
/*     */                 
/* 490 */                 slot3.a(entityhuman, itemstack6);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 497 */       c();
/*     */     } 
/*     */ 
/*     */     
/* 501 */     return itemstack;
/*     */   }
/*     */   
/*     */   public static boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 505 */     return (itemstack.getItem() == itemstack1.getItem() && ItemStack.equals(itemstack, itemstack1));
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 509 */     return true;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 513 */     PlayerInventory playerinventory = entityhuman.inventory;
/*     */     
/* 515 */     if (!playerinventory.getCarried().isEmpty()) {
/*     */       
/* 517 */       ItemStack carried = playerinventory.getCarried();
/* 518 */       playerinventory.setCarried(ItemStack.b);
/* 519 */       entityhuman.drop(carried, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman entityhuman, World world, IInventory iinventory) {
/* 528 */     if (entityhuman.isAlive() && (!(entityhuman instanceof EntityPlayer) || !((EntityPlayer)entityhuman).q())) {
/* 529 */       for (int i = 0; i < iinventory.getSize(); i++) {
/* 530 */         entityhuman.inventory.a(world, iinventory.splitWithoutUpdate(i));
/*     */       }
/*     */     } else {
/*     */       
/* 534 */       for (int i = 0; i < iinventory.getSize(); i++) {
/* 535 */         entityhuman.drop(iinventory.splitWithoutUpdate(i), false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 542 */     c();
/*     */   }
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 546 */     getSlot(i).set(itemstack);
/*     */   }
/*     */   
/*     */   public void a(int i, int j) {
/* 550 */     ((ContainerProperty)this.d.get(i)).set(j);
/*     */   }
/*     */   
/*     */   public boolean c(EntityHuman entityhuman) {
/* 554 */     return !this.k.contains(entityhuman);
/*     */   }
/*     */   
/*     */   public void a(EntityHuman entityhuman, boolean flag) {
/* 558 */     if (flag) {
/* 559 */       this.k.remove(entityhuman);
/*     */     } else {
/* 561 */       this.k.add(entityhuman);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(ItemStack itemstack, int i, int j, boolean flag) {
/* 569 */     boolean flag1 = false;
/* 570 */     int k = i;
/*     */     
/* 572 */     if (flag) {
/* 573 */       k = j - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 579 */     if (itemstack.isStackable()) {
/* 580 */       while (!itemstack.isEmpty() && (
/* 581 */         flag ? (
/* 582 */         k < i) : (
/*     */ 
/*     */         
/* 585 */         k >= j))) {
/*     */ 
/*     */ 
/*     */         
/* 589 */         Slot slot = this.slots.get(k);
/* 590 */         ItemStack itemstack1 = slot.getItem();
/* 591 */         if (!itemstack1.isEmpty() && a(itemstack, itemstack1)) {
/* 592 */           int l = itemstack1.getCount() + itemstack.getCount();
/*     */           
/* 594 */           if (l <= itemstack.getMaxStackSize()) {
/* 595 */             itemstack.setCount(0);
/* 596 */             itemstack1.setCount(l);
/* 597 */             slot.d();
/* 598 */             flag1 = true;
/* 599 */           } else if (itemstack1.getCount() < itemstack.getMaxStackSize()) {
/* 600 */             itemstack.subtract(itemstack.getMaxStackSize() - itemstack1.getCount());
/* 601 */             itemstack1.setCount(itemstack.getMaxStackSize());
/* 602 */             slot.d();
/* 603 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 607 */         if (flag) {
/* 608 */           k--; continue;
/*     */         } 
/* 610 */         k++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 615 */     if (!itemstack.isEmpty()) {
/* 616 */       if (flag) {
/* 617 */         k = j - 1;
/*     */       } else {
/* 619 */         k = i;
/*     */       } 
/*     */ 
/*     */       
/* 623 */       while (flag ? (
/* 624 */         k < i) : (
/*     */ 
/*     */         
/* 627 */         k >= j)) {
/*     */ 
/*     */ 
/*     */         
/* 631 */         Slot slot = this.slots.get(k);
/* 632 */         ItemStack itemstack1 = slot.getItem();
/* 633 */         if (itemstack1.isEmpty() && slot.isAllowed(itemstack)) {
/* 634 */           if (itemstack.getCount() > slot.getMaxStackSize()) {
/* 635 */             slot.set(itemstack.cloneAndSubtract(slot.getMaxStackSize()));
/*     */           } else {
/* 637 */             slot.set(itemstack.cloneAndSubtract(itemstack.getCount()));
/*     */           } 
/*     */           
/* 640 */           slot.d();
/* 641 */           flag1 = true;
/*     */           
/*     */           break;
/*     */         } 
/* 645 */         if (flag) {
/* 646 */           k--; continue;
/*     */         } 
/* 648 */         k++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 653 */     return flag1;
/*     */   }
/*     */   
/*     */   public static int b(int i) {
/* 657 */     return i >> 2 & 0x3;
/*     */   }
/*     */   
/*     */   public static int c(int i) {
/* 661 */     return i & 0x3;
/*     */   }
/*     */   
/*     */   public static boolean a(int i, EntityHuman entityhuman) {
/* 665 */     return (i == 0) ? true : ((i == 1) ? true : ((i == 2 && entityhuman.abilities.canInstantlyBuild)));
/*     */   }
/*     */   
/*     */   protected void d() {
/* 669 */     this.h = 0;
/* 670 */     this.i.clear();
/*     */   }
/*     */   
/*     */   public static boolean a(@Nullable Slot slot, ItemStack itemstack, boolean flag) {
/* 674 */     boolean flag1 = (slot == null || !slot.hasItem());
/*     */     
/* 676 */     return (!flag1 && itemstack.doMaterialsMatch(slot.getItem()) && ItemStack.equals(slot.getItem(), itemstack)) ? ((slot.getItem().getCount() + (flag ? 0 : itemstack.getCount()) <= itemstack.getMaxStackSize())) : flag1;
/*     */   }
/*     */   
/*     */   public static void a(Set<Slot> set, int i, ItemStack itemstack, int j) {
/* 680 */     switch (i) {
/*     */       case 0:
/* 682 */         itemstack.setCount(MathHelper.d(itemstack.getCount() / set.size()));
/*     */         break;
/*     */       case 1:
/* 685 */         itemstack.setCount(1);
/*     */         break;
/*     */       case 2:
/* 688 */         itemstack.setCount(itemstack.getItem().getMaxStackSize());
/*     */         break;
/*     */     } 
/* 691 */     itemstack.add(j);
/*     */   }
/*     */   
/*     */   public boolean b(Slot slot) {
/* 695 */     return true;
/*     */   }
/*     */   
/*     */   public static int a(@Nullable TileEntity tileentity) {
/* 699 */     return (tileentity instanceof IInventory) ? b((IInventory)tileentity) : 0;
/*     */   }
/*     */   
/*     */   public static int b(@Nullable IInventory iinventory) {
/* 703 */     if (iinventory == null) {
/* 704 */       return 0;
/*     */     }
/* 706 */     int i = 0;
/* 707 */     float f = 0.0F;
/*     */     
/* 709 */     for (int j = 0; j < iinventory.getSize(); j++) {
/* 710 */       ItemStack itemstack = iinventory.getItem(j);
/*     */       
/* 712 */       if (!itemstack.isEmpty()) {
/* 713 */         f += itemstack.getCount() / Math.min(iinventory.getMaxStackSize(), itemstack.getMaxStackSize());
/* 714 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 718 */     f /= iinventory.getSize();
/* 719 */     return MathHelper.d(f * 14.0F) + ((i > 0) ? 1 : 0);
/*     */   }
/*     */   
/*     */   public abstract InventoryView getBukkitView();
/*     */   
/*     */   public abstract boolean canUse(EntityHuman paramEntityHuman);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Container.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */