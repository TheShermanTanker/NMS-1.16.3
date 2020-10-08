/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.IntStream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryDoubleChest;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryMoveItemEvent;
/*     */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class TileEntityHopper extends TileEntityLootable implements IHopper, ITickable {
/*     */   private NonNullList<ItemStack> items;
/*     */   private int j;
/*     */   private long k;
/*  26 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  27 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  30 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  34 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  38 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  42 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  47 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  51 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityHopper() {
/*  56 */     super(TileEntityTypes.HOPPER);
/*  57 */     this.items = NonNullList.a(5, ItemStack.b);
/*  58 */     this.j = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  63 */     super.load(iblockdata, nbttagcompound);
/*  64 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/*  65 */     if (!b(nbttagcompound)) {
/*  66 */       ContainerUtil.b(nbttagcompound, this.items);
/*     */     }
/*     */     
/*  69 */     this.j = nbttagcompound.getInt("TransferCooldown");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  74 */     super.save(nbttagcompound);
/*  75 */     if (!c(nbttagcompound)) {
/*  76 */       ContainerUtil.a(nbttagcompound, this.items);
/*     */     }
/*     */     
/*  79 */     nbttagcompound.setInt("TransferCooldown", this.j);
/*  80 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  85 */     return this.items.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  90 */     d((EntityHuman)null);
/*  91 */     return ContainerUtil.a(f(), i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/*  96 */     d((EntityHuman)null);
/*  97 */     f().set(i, itemstack);
/*  98 */     if (itemstack.getCount() > getMaxStackSize()) {
/*  99 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent getContainerName() {
/* 106 */     return new ChatMessage("container.hopper");
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 111 */     if (this.world != null && !this.world.isClientSide) {
/* 112 */       this.j--;
/* 113 */       this.k = this.world.getTime();
/* 114 */       if (!m()) {
/* 115 */         setCooldown(0);
/*     */         
/* 117 */         boolean result = a(() -> Boolean.valueOf(a(this)));
/*     */ 
/*     */         
/* 120 */         if (!result && this.world.spigotConfig.hopperCheck > 1) {
/* 121 */           setCooldown(this.world.spigotConfig.hopperCheck);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(Supplier<Boolean> supplier) {
/* 130 */     if (this.world != null && !this.world.isClientSide) {
/* 131 */       if (!m() && ((Boolean)getBlock().get(BlockHopper.ENABLED)).booleanValue()) {
/* 132 */         boolean flag = false;
/*     */         
/* 134 */         if (!isEmpty()) {
/* 135 */           flag = k();
/*     */         }
/*     */         
/* 138 */         if (!j()) {
/* 139 */           flag |= ((Boolean)supplier.get()).booleanValue();
/*     */         }
/*     */         
/* 142 */         if (flag) {
/* 143 */           setCooldown(this.world.spigotConfig.hopperTransfer);
/* 144 */           update();
/* 145 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 149 */       return false;
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   private boolean j() {
/*     */     ItemStack itemstack;
/* 156 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 161 */       if (!iterator.hasNext()) {
/* 162 */         return true;
/*     */       }
/*     */       
/* 165 */       itemstack = iterator.next();
/* 166 */     } while (!itemstack.isEmpty() && itemstack.getCount() == itemstack.getMaxStackSize());
/*     */     
/* 168 */     return false;
/*     */   }
/*     */   private static boolean skipPullModeEventFire = false;
/*     */   private static boolean skipPushModeEventFire = false;
/*     */   static boolean skipHopperEvents = false;
/*     */   private static final BiPredicate<ItemStack, Integer> STACK_SIZE_TEST;
/*     */   private static final BiPredicate<ItemStack, Integer> IS_EMPTY_TEST;
/*     */   
/*     */   private boolean hopperPush(IInventory iinventory, EnumDirection enumdirection) {
/* 177 */     skipPushModeEventFire = skipHopperEvents;
/* 178 */     boolean foundItem = false;
/* 179 */     for (int i = 0; i < getSize(); i++) {
/* 180 */       ItemStack item = getItem(i);
/* 181 */       if (!item.isEmpty()) {
/* 182 */         foundItem = true;
/* 183 */         ItemStack origItemStack = item;
/* 184 */         ItemStack itemstack = origItemStack;
/*     */         
/* 186 */         int origCount = origItemStack.getCount();
/* 187 */         int moved = Math.min(this.world.spigotConfig.hopperAmount, origCount);
/* 188 */         origItemStack.setCount(moved);
/*     */ 
/*     */ 
/*     */         
/* 192 */         if (!skipPushModeEventFire) {
/* 193 */           itemstack = callPushMoveEvent(iinventory, itemstack);
/* 194 */           if (itemstack == null) {
/* 195 */             origItemStack.setCount(origCount);
/* 196 */             return false;
/*     */           } 
/*     */         } 
/* 199 */         ItemStack itemstack2 = addItem(this, iinventory, itemstack, enumdirection);
/* 200 */         int remaining = itemstack2.getCount();
/* 201 */         if (remaining != moved) {
/* 202 */           origItemStack = origItemStack.cloneItemStack(true);
/* 203 */           origItemStack.setCount(origCount);
/* 204 */           if (!origItemStack.isEmpty()) {
/* 205 */             origItemStack.setCount(origCount - moved + remaining);
/*     */           }
/* 207 */           setItem(i, origItemStack);
/* 208 */           iinventory.update();
/* 209 */           return true;
/*     */         } 
/* 211 */         origItemStack.setCount(origCount);
/*     */       } 
/*     */     } 
/* 214 */     if (foundItem && this.world.paperConfig.cooldownHopperWhenFull) {
/* 215 */       setCooldown(this.world.spigotConfig.hopperTransfer);
/*     */     }
/* 217 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean hopperPull(IHopper ihopper, IInventory iinventory, ItemStack origItemStack, int i) {
/* 221 */     ItemStack itemstack = origItemStack;
/* 222 */     int origCount = origItemStack.getCount();
/* 223 */     World world = ihopper.getWorld();
/* 224 */     int moved = Math.min(world.spigotConfig.hopperAmount, origCount);
/* 225 */     itemstack.setCount(moved);
/*     */     
/* 227 */     if (!skipPullModeEventFire) {
/* 228 */       itemstack = callPullMoveEvent(ihopper, iinventory, itemstack);
/* 229 */       if (itemstack == null) {
/* 230 */         origItemStack.setCount(origCount);
/*     */ 
/*     */ 
/*     */         
/* 234 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 238 */     ItemStack itemstack2 = addItem(iinventory, ihopper, itemstack, (EnumDirection)null);
/* 239 */     int remaining = itemstack2.getCount();
/* 240 */     if (remaining != moved) {
/* 241 */       origItemStack = origItemStack.cloneItemStack(true);
/* 242 */       origItemStack.setCount(origCount);
/* 243 */       if (!origItemStack.isEmpty()) {
/* 244 */         origItemStack.setCount(origCount - moved + remaining);
/*     */       }
/* 246 */       IGNORE_TILE_UPDATES = true;
/* 247 */       iinventory.setItem(i, origItemStack);
/* 248 */       IGNORE_TILE_UPDATES = false;
/* 249 */       iinventory.update();
/* 250 */       return true;
/*     */     } 
/* 252 */     origItemStack.setCount(origCount);
/*     */     
/* 254 */     if (world.paperConfig.cooldownHopperWhenFull) {
/* 255 */       cooldownHopper(ihopper);
/*     */     }
/*     */     
/* 258 */     return false;
/*     */   }
/*     */   
/*     */   private ItemStack callPushMoveEvent(IInventory iinventory, ItemStack itemstack) {
/* 262 */     Inventory destinationInventory = getInventory(iinventory);
/*     */     
/* 264 */     InventoryMoveItemEvent event = new InventoryMoveItemEvent(getOwner(false).getInventory(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), destinationInventory, true);
/* 265 */     boolean result = event.callEvent();
/* 266 */     if (!event.calledGetItem && !event.calledSetItem) {
/* 267 */       skipPushModeEventFire = true;
/*     */     }
/* 269 */     if (!result) {
/* 270 */       cooldownHopper(this);
/* 271 */       return null;
/*     */     } 
/*     */     
/* 274 */     if (event.calledSetItem) {
/* 275 */       return CraftItemStack.asNMSCopy(event.getItem());
/*     */     }
/* 277 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack callPullMoveEvent(IHopper hopper, IInventory iinventory, ItemStack itemstack) {
/* 282 */     Inventory sourceInventory = getInventory(iinventory);
/* 283 */     Inventory destination = getInventory(hopper);
/*     */ 
/*     */ 
/*     */     
/* 287 */     InventoryMoveItemEvent event = new InventoryMoveItemEvent(sourceInventory, (ItemStack)CraftItemStack.asCraftMirror(itemstack), destination, false);
/* 288 */     boolean result = event.callEvent();
/* 289 */     if (!event.calledGetItem && !event.calledSetItem) {
/* 290 */       skipPullModeEventFire = true;
/*     */     }
/* 292 */     if (!result) {
/* 293 */       cooldownHopper(hopper);
/* 294 */       return null;
/*     */     } 
/*     */     
/* 297 */     if (event.calledSetItem) {
/* 298 */       return CraftItemStack.asNMSCopy(event.getItem());
/*     */     }
/* 300 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Inventory getInventory(IInventory iinventory) {
/*     */     Inventory sourceInventory;
/* 306 */     if (iinventory instanceof InventoryLargeChest) {
/* 307 */       CraftInventoryDoubleChest craftInventoryDoubleChest = new CraftInventoryDoubleChest((InventoryLargeChest)iinventory);
/* 308 */     } else if (iinventory instanceof TileEntity) {
/* 309 */       sourceInventory = ((TileEntity)iinventory).getOwner(false).getInventory();
/*     */     } else {
/* 311 */       sourceInventory = iinventory.getOwner().getInventory();
/*     */     } 
/* 313 */     return sourceInventory;
/*     */   }
/*     */   
/*     */   private static void cooldownHopper(IHopper hopper) {
/* 317 */     if (hopper instanceof TileEntityHopper) {
/* 318 */       ((TileEntityHopper)hopper).setCooldown((hopper.getWorld()).spigotConfig.hopperTransfer);
/* 319 */     } else if (hopper instanceof EntityMinecartHopper) {
/* 320 */       ((EntityMinecartHopper)hopper).setCooldown((hopper.getWorld()).spigotConfig.hopperTransfer / 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean k() {
/* 326 */     IInventory iinventory = l();
/*     */     
/* 328 */     if (iinventory == null) {
/* 329 */       return false;
/*     */     }
/* 331 */     EnumDirection enumdirection = ((EnumDirection)getBlock().get(BlockHopper.FACING)).opposite();
/*     */     
/* 333 */     if (b(iinventory, enumdirection)) {
/* 334 */       return false;
/*     */     }
/* 336 */     return hopperPush(iinventory, enumdirection);
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
/*     */   private static IntStream a(IInventory iinventory, EnumDirection enumdirection) {
/* 380 */     return (iinventory instanceof IWorldInventory) ? IntStream.of(((IWorldInventory)iinventory).getSlotsForFace(enumdirection)) : IntStream.range(0, iinventory.getSize());
/*     */   }
/*     */   
/*     */   private static boolean allMatch(IInventory iinventory, EnumDirection enumdirection, BiPredicate<ItemStack, Integer> test) {
/* 384 */     if (iinventory instanceof IWorldInventory) {
/* 385 */       for (int i : ((IWorldInventory)iinventory).getSlotsForFace(enumdirection)) {
/* 386 */         if (!test.test(iinventory.getItem(i), Integer.valueOf(i))) {
/* 387 */           return false;
/*     */         }
/*     */       } 
/*     */     } else {
/* 391 */       int size = iinventory.getSize();
/* 392 */       for (int i = 0; i < size; i++) {
/* 393 */         if (!test.test(iinventory.getItem(i), Integer.valueOf(i))) {
/* 394 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 398 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean anyMatch(IInventory iinventory, EnumDirection enumdirection, BiPredicate<ItemStack, Integer> test) {
/* 402 */     if (iinventory instanceof IWorldInventory) {
/* 403 */       for (int i : ((IWorldInventory)iinventory).getSlotsForFace(enumdirection)) {
/* 404 */         if (test.test(iinventory.getItem(i), Integer.valueOf(i))) {
/* 405 */           return true;
/*     */         }
/*     */       } 
/*     */     } else {
/* 409 */       int size = iinventory.getSize();
/* 410 */       for (int i = 0; i < size; i++) {
/* 411 */         if (test.test(iinventory.getItem(i), Integer.valueOf(i))) {
/* 412 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 416 */     return true;
/*     */   } static {
/* 418 */     STACK_SIZE_TEST = ((itemstack, i) -> (itemstack.getCount() >= itemstack.getMaxStackSize()));
/* 419 */     IS_EMPTY_TEST = ((itemstack, i) -> itemstack.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean b(IInventory iinventory, EnumDirection enumdirection) {
/* 425 */     return allMatch(iinventory, enumdirection, STACK_SIZE_TEST);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean c(IInventory iinventory, EnumDirection enumdirection) {
/* 430 */     return allMatch(iinventory, enumdirection, IS_EMPTY_TEST);
/*     */   }
/*     */   public static boolean a(IHopper ihopper) {
/*     */     EntityItem entityitem;
/* 434 */     IInventory iinventory = b(ihopper);
/*     */     
/* 436 */     if (iinventory != null) {
/* 437 */       EnumDirection enumdirection = EnumDirection.DOWN;
/*     */ 
/*     */       
/* 440 */       skipPullModeEventFire = skipHopperEvents;
/* 441 */       return (!c(iinventory, enumdirection) && anyMatch(iinventory, enumdirection, (item, i) -> 
/*     */           
/* 443 */           (!item.isEmpty() && canTakeItem(iinventory, item, i.intValue(), enumdirection)) ? hopperPull(ihopper, iinventory, item, i.intValue()) : false));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 451 */     Iterator<EntityItem> iterator = c(ihopper).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 456 */       if (!iterator.hasNext()) {
/* 457 */         return false;
/*     */       }
/*     */       
/* 460 */       entityitem = iterator.next();
/* 461 */     } while (!a(ihopper, entityitem));
/*     */     
/* 463 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(IHopper ihopper, IInventory iinventory, int i, EnumDirection enumdirection) {
/* 468 */     ItemStack itemstack = iinventory.getItem(i);
/*     */     
/* 470 */     if (!itemstack.isEmpty() && b(iinventory, itemstack, i, enumdirection)) {
/* 471 */       return hopperPull(ihopper, iinventory, itemstack, i);
/*     */     }
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
/* 511 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean a(IInventory iinventory, EntityItem entityitem) {
/* 515 */     boolean flag = false;
/*     */     
/* 517 */     InventoryPickupItemEvent event = new InventoryPickupItemEvent(getInventory(iinventory), (Item)entityitem.getBukkitEntity());
/* 518 */     entityitem.world.getServer().getPluginManager().callEvent((Event)event);
/* 519 */     if (event.isCancelled()) {
/* 520 */       return false;
/*     */     }
/*     */     
/* 523 */     ItemStack itemstack = entityitem.getItemStack().cloneItemStack();
/* 524 */     ItemStack itemstack1 = addItem((IInventory)null, iinventory, itemstack, (EnumDirection)null);
/*     */     
/* 526 */     if (itemstack1.isEmpty()) {
/* 527 */       flag = true;
/* 528 */       entityitem.die();
/*     */     } else {
/* 530 */       entityitem.setItemStack(itemstack1);
/*     */     } 
/*     */     
/* 533 */     return flag;
/*     */   }
/*     */   
/*     */   public static ItemStack addItem(@Nullable IInventory iinventory, IInventory iinventory1, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 537 */     if (iinventory1 instanceof IWorldInventory && enumdirection != null) {
/* 538 */       IWorldInventory iworldinventory = (IWorldInventory)iinventory1;
/* 539 */       int[] aint = iworldinventory.getSlotsForFace(enumdirection);
/*     */       
/* 541 */       for (int i = 0; i < aint.length && !itemstack.isEmpty(); i++) {
/* 542 */         itemstack = a(iinventory, iinventory1, itemstack, aint[i], enumdirection);
/*     */       }
/*     */     } else {
/* 545 */       int j = iinventory1.getSize();
/*     */       
/* 547 */       for (int k = 0; k < j && !itemstack.isEmpty(); k++) {
/* 548 */         itemstack = a(iinventory, iinventory1, itemstack, k, enumdirection);
/*     */       }
/*     */     } 
/*     */     
/* 552 */     return itemstack;
/*     */   }
/*     */   
/*     */   private static boolean a(IInventory iinventory, ItemStack itemstack, int i, @Nullable EnumDirection enumdirection) {
/* 556 */     return !iinventory.b(i, itemstack) ? false : ((!(iinventory instanceof IWorldInventory) || ((IWorldInventory)iinventory).canPlaceItemThroughFace(i, itemstack, enumdirection)));
/*     */   }
/*     */   private static boolean canTakeItem(IInventory iinventory, ItemStack itemstack, int i, EnumDirection enumdirection) {
/* 559 */     return b(iinventory, itemstack, i, enumdirection);
/*     */   } private static boolean b(IInventory iinventory, ItemStack itemstack, int i, EnumDirection enumdirection) {
/* 561 */     return (!(iinventory instanceof IWorldInventory) || ((IWorldInventory)iinventory).canTakeItemThroughFace(i, itemstack, enumdirection));
/*     */   }
/*     */   
/*     */   private static ItemStack a(@Nullable IInventory iinventory, IInventory iinventory1, ItemStack itemstack, int i, @Nullable EnumDirection enumdirection) {
/* 565 */     ItemStack itemstack1 = iinventory1.getItem(i);
/*     */     
/* 567 */     if (a(iinventory1, itemstack, i, enumdirection)) {
/* 568 */       boolean flag = false;
/* 569 */       boolean flag1 = iinventory1.isEmpty();
/*     */       
/* 571 */       if (itemstack1.isEmpty()) {
/* 572 */         IGNORE_TILE_UPDATES = true;
/* 573 */         iinventory1.setItem(i, itemstack);
/* 574 */         IGNORE_TILE_UPDATES = false;
/* 575 */         itemstack = ItemStack.b;
/* 576 */         flag = true;
/* 577 */       } else if (a(itemstack1, itemstack)) {
/* 578 */         int j = itemstack.getMaxStackSize() - itemstack1.getCount();
/* 579 */         int k = Math.min(itemstack.getCount(), j);
/*     */         
/* 581 */         itemstack.subtract(k);
/* 582 */         itemstack1.add(k);
/* 583 */         flag = (k > 0);
/*     */       } 
/*     */       
/* 586 */       if (flag) {
/* 587 */         if (flag1 && iinventory1 instanceof TileEntityHopper) {
/* 588 */           TileEntityHopper tileentityhopper = (TileEntityHopper)iinventory1;
/*     */           
/* 590 */           if (!tileentityhopper.y()) {
/* 591 */             byte b0 = 0;
/*     */             
/* 593 */             if (iinventory instanceof TileEntityHopper) {
/* 594 */               TileEntityHopper tileentityhopper1 = (TileEntityHopper)iinventory;
/*     */               
/* 596 */               if (tileentityhopper.k >= tileentityhopper1.k) {
/* 597 */                 b0 = 1;
/*     */               }
/*     */             } 
/*     */             
/* 601 */             tileentityhopper.setCooldown(tileentityhopper.world.spigotConfig.hopperTransfer - b0);
/*     */           } 
/*     */         } 
/*     */         
/* 605 */         iinventory1.update();
/*     */       } 
/*     */     } 
/*     */     
/* 609 */     return itemstack;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IInventory l() {
/* 614 */     EnumDirection enumdirection = (EnumDirection)getBlock().get(BlockHopper.FACING);
/*     */     
/* 616 */     return b(getWorld(), this.position.shift(enumdirection));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static IInventory b(IHopper ihopper) {
/* 621 */     return a(ihopper.getWorld(), ihopper.x(), ihopper.z() + 1.0D, ihopper.A());
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<EntityItem> c(IHopper ihopper) {
/* 626 */     World world = ihopper.getWorld();
/* 627 */     double d0 = ihopper.getX();
/* 628 */     double d1 = ihopper.getY();
/* 629 */     double d2 = ihopper.getZ();
/* 630 */     AxisAlignedBB bb = new AxisAlignedBB(d0 - 0.5D, d1, d2 - 0.5D, d0 + 0.5D, d1 + 1.5D, d2 + 0.5D);
/* 631 */     return world.getEntities(EntityItem.class, bb, Entity::isAlive);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static IInventory b(World world, BlockPosition blockposition) {
/* 637 */     return a(world, blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, true);
/*     */   }
/*     */   @Nullable
/*     */   public static IInventory a(World world, double d0, double d1, double d2) {
/* 641 */     return a(world, d0, d1, d2, false);
/*     */   } public static IInventory a(World world, double d0, double d1, double d2, boolean optimizeEntities) {
/* 643 */     Object object = null;
/* 644 */     BlockPosition blockposition = new BlockPosition(d0, d1, d2);
/* 645 */     if (!world.isLoaded(blockposition)) return null; 
/* 646 */     IBlockData iblockdata = world.getType(blockposition);
/* 647 */     Block block = iblockdata.getBlock();
/*     */     
/* 649 */     if (block instanceof IInventoryHolder) {
/* 650 */       object = ((IInventoryHolder)block).a(iblockdata, world, blockposition);
/* 651 */     } else if (block.isTileEntity()) {
/* 652 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 654 */       if (tileentity instanceof IInventory) {
/* 655 */         object = tileentity;
/* 656 */         if (object instanceof TileEntityChest && block instanceof BlockChest) {
/* 657 */           object = BlockChest.getInventory((BlockChest)block, iblockdata, world, blockposition, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 662 */     if (object == null && (!optimizeEntities || !CraftMagicNumbers.getMaterial(block).isOccluding())) {
/* 663 */       List<Entity> list = world.getEntities((Entity)null, new AxisAlignedBB(d0 - 0.5D, d1 - 0.5D, d2 - 0.5D, d0 + 0.5D, d1 + 0.5D, d2 + 0.5D), IEntitySelector.d);
/*     */       
/* 665 */       if (!list.isEmpty()) {
/* 666 */         object = list.get(world.random.nextInt(list.size()));
/*     */       }
/*     */     } 
/*     */     
/* 670 */     return (IInventory)object;
/*     */   }
/*     */   
/*     */   private static boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 674 */     return (itemstack.getItem() != itemstack1.getItem()) ? false : ((itemstack.getDamage() != itemstack1.getDamage()) ? false : ((itemstack.getCount() > itemstack.getMaxStackSize()) ? false : ItemStack.equals(itemstack, itemstack1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public double x() {
/* 679 */     return this.position.getX() + 0.5D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double z() {
/* 684 */     return this.position.getY() + 0.5D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double A() {
/* 689 */     return this.position.getZ() + 0.5D;
/*     */   }
/*     */   
/*     */   private void setCooldown(int i) {
/* 693 */     this.j = i;
/*     */   }
/*     */   
/*     */   private boolean m() {
/* 697 */     return (this.j > 0);
/*     */   }
/*     */   
/*     */   private boolean y() {
/* 701 */     return (this.j > 8);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> f() {
/* 706 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NonNullList<ItemStack> nonnulllist) {
/* 711 */     this.items = nonnulllist;
/*     */   }
/*     */   
/*     */   public void a(Entity entity) {
/* 715 */     if (entity instanceof EntityItem) {
/* 716 */       BlockPosition blockposition = getPosition();
/*     */       
/* 718 */       if (VoxelShapes.c(VoxelShapes.a(entity.getBoundingBox().d(-blockposition.getX(), -blockposition.getY(), -blockposition.getZ())), aa_(), OperatorBoolean.AND)) {
/* 719 */         a(() -> Boolean.valueOf(a(this, (EntityItem)entity)));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Container createContainer(int i, PlayerInventory playerinventory) {
/* 729 */     return new ContainerHopper(i, playerinventory, this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */