/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public class PlayerInventory implements IInventory, INamableTileEntity {
/*     */   public final NonNullList<ItemStack> items;
/*     */   public final NonNullList<ItemStack> armor;
/*     */   public final NonNullList<ItemStack> extraSlots;
/*     */   private final List<NonNullList<ItemStack>> f;
/*     */   
/*     */   List<NonNullList<ItemStack>> getComponents() {
/*  20 */     return this.f;
/*     */   }
/*     */   
/*     */   public int itemInHandIndex;
/*     */   public final EntityHuman player;
/*     */   private ItemStack carried;
/*     */   private int h;
/*  27 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  28 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  31 */     List<ItemStack> combined = new ArrayList<>(this.items.size() + this.armor.size() + this.extraSlots.size());
/*  32 */     for (List<ItemStack> sub : this.f) {
/*  33 */       combined.addAll(sub);
/*     */     }
/*     */     
/*  36 */     return combined;
/*     */   }
/*     */   
/*     */   public List<ItemStack> getArmorContents() {
/*  40 */     return this.armor;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  44 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  48 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  52 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  56 */     return (InventoryHolder)this.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  61 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  65 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  70 */     return this.player.getBukkitEntity().getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerInventory(EntityHuman entityhuman) {
/*  75 */     this.items = NonNullList.a(36, ItemStack.b);
/*  76 */     this.armor = NonNullList.a(4, ItemStack.b);
/*  77 */     this.extraSlots = NonNullList.a(1, ItemStack.b);
/*  78 */     this.f = (List<NonNullList<ItemStack>>)ImmutableList.of(this.items, this.armor, this.extraSlots);
/*  79 */     this.carried = ItemStack.b;
/*  80 */     this.player = entityhuman;
/*     */   }
/*     */   
/*     */   public ItemStack getItemInHand() {
/*  84 */     return d(this.itemInHandIndex) ? this.items.get(this.itemInHandIndex) : ItemStack.b;
/*     */   }
/*     */   
/*     */   public static int getHotbarSize() {
/*  88 */     return 9;
/*     */   }
/*     */   
/*     */   private boolean isSimilarAndNotFull(ItemStack itemstack, ItemStack itemstack1) {
/*  92 */     return (!itemstack.isEmpty() && b(itemstack, itemstack1) && itemstack.isStackable() && itemstack.getCount() < itemstack.getMaxStackSize() && itemstack.getCount() < getMaxStackSize());
/*     */   }
/*     */   
/*     */   private boolean b(ItemStack itemstack, ItemStack itemstack1) {
/*  96 */     return (itemstack.getItem() == itemstack1.getItem() && ItemStack.equals(itemstack, itemstack1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int canHold(ItemStack itemstack) {
/* 101 */     int remains = itemstack.getCount();
/* 102 */     for (int i = 0; i < this.items.size(); i++) {
/* 103 */       ItemStack itemstack1 = getItem(i);
/* 104 */       if (itemstack1.isEmpty()) return itemstack.getCount();
/*     */       
/* 106 */       if (isSimilarAndNotFull(itemstack1, itemstack)) {
/* 107 */         remains -= ((itemstack1.getMaxStackSize() < getMaxStackSize()) ? itemstack1.getMaxStackSize() : getMaxStackSize()) - itemstack1.getCount();
/*     */       }
/* 109 */       if (remains <= 0) return itemstack.getCount(); 
/*     */     } 
/* 111 */     ItemStack offhandItemStack = getItem(this.items.size() + this.armor.size());
/* 112 */     if (isSimilarAndNotFull(offhandItemStack, itemstack)) {
/* 113 */       remains -= ((offhandItemStack.getMaxStackSize() < getMaxStackSize()) ? offhandItemStack.getMaxStackSize() : getMaxStackSize()) - offhandItemStack.getCount();
/*     */     }
/* 115 */     if (remains <= 0) return itemstack.getCount();
/*     */     
/* 117 */     return itemstack.getCount() - remains;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFirstEmptySlotIndex() {
/* 122 */     for (int i = 0; i < this.items.size(); i++) {
/* 123 */       if (((ItemStack)this.items.get(i)).isEmpty()) {
/* 124 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 128 */     return -1;
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 132 */     this.itemInHandIndex = i();
/* 133 */     ItemStack itemstack = this.items.get(this.itemInHandIndex);
/*     */     
/* 135 */     this.items.set(this.itemInHandIndex, this.items.get(i));
/* 136 */     this.items.set(i, itemstack);
/*     */   }
/*     */   
/*     */   public static boolean d(int i) {
/* 140 */     return (i >= 0 && i < 9);
/*     */   }
/*     */   
/*     */   public int c(ItemStack itemstack) {
/* 144 */     for (int i = 0; i < this.items.size(); i++) {
/* 145 */       ItemStack itemstack1 = this.items.get(i);
/*     */       
/* 147 */       if (!((ItemStack)this.items.get(i)).isEmpty() && b(itemstack, this.items.get(i)) && !((ItemStack)this.items.get(i)).f() && !itemstack1.hasEnchantments() && !itemstack1.hasName()) {
/* 148 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 152 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int i() {
/*     */     int j;
/* 159 */     for (j = 0; j < 9; j++) {
/* 160 */       int i = (this.itemInHandIndex + j) % 9;
/* 161 */       if (((ItemStack)this.items.get(i)).isEmpty()) {
/* 162 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 166 */     for (j = 0; j < 9; j++) {
/* 167 */       int i = (this.itemInHandIndex + j) % 9;
/* 168 */       if (!((ItemStack)this.items.get(i)).hasEnchantments()) {
/* 169 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 173 */     return this.itemInHandIndex;
/*     */   }
/*     */   
/*     */   public int a(Predicate<ItemStack> predicate, int i, IInventory iinventory) {
/* 177 */     byte b0 = 0;
/* 178 */     boolean flag = (i == 0);
/* 179 */     int j = b0 + ContainerUtil.a(this, predicate, i - b0, flag);
/*     */     
/* 181 */     j += ContainerUtil.a(iinventory, predicate, i - j, flag);
/* 182 */     j += ContainerUtil.a(this.carried, predicate, i - j, flag);
/* 183 */     if (this.carried.isEmpty()) {
/* 184 */       this.carried = ItemStack.b;
/*     */     }
/*     */     
/* 187 */     return j;
/*     */   }
/*     */   
/*     */   private int i(ItemStack itemstack) {
/* 191 */     int i = firstPartial(itemstack);
/*     */     
/* 193 */     if (i == -1) {
/* 194 */       i = getFirstEmptySlotIndex();
/*     */     }
/*     */     
/* 197 */     return (i == -1) ? itemstack.getCount() : d(i, itemstack);
/*     */   }
/*     */   
/*     */   private int d(int i, ItemStack itemstack) {
/* 201 */     Item item = itemstack.getItem();
/* 202 */     int j = itemstack.getCount();
/* 203 */     ItemStack itemstack1 = getItem(i);
/*     */     
/* 205 */     if (itemstack1.isEmpty()) {
/* 206 */       itemstack1 = new ItemStack(item, 0);
/* 207 */       if (itemstack.hasTag()) {
/* 208 */         itemstack1.setTag(itemstack.getTag().clone());
/*     */       }
/*     */       
/* 211 */       setItem(i, itemstack1);
/*     */     } 
/*     */     
/* 214 */     int k = j;
/*     */     
/* 216 */     if (j > itemstack1.getMaxStackSize() - itemstack1.getCount()) {
/* 217 */       k = itemstack1.getMaxStackSize() - itemstack1.getCount();
/*     */     }
/*     */     
/* 220 */     if (k > getMaxStackSize() - itemstack1.getCount()) {
/* 221 */       k = getMaxStackSize() - itemstack1.getCount();
/*     */     }
/*     */     
/* 224 */     if (k == 0) {
/* 225 */       return j;
/*     */     }
/* 227 */     j -= k;
/* 228 */     itemstack1.add(k);
/* 229 */     itemstack1.d(5);
/* 230 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public int firstPartial(ItemStack itemstack) {
/* 235 */     if (isSimilarAndNotFull(getItem(this.itemInHandIndex), itemstack))
/* 236 */       return this.itemInHandIndex; 
/* 237 */     if (isSimilarAndNotFull(getItem(40), itemstack)) {
/* 238 */       return 40;
/*     */     }
/* 240 */     for (int i = 0; i < this.items.size(); i++) {
/* 241 */       if (isSimilarAndNotFull(this.items.get(i), itemstack)) {
/* 242 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 246 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void j() {
/* 251 */     Iterator<NonNullList<ItemStack>> iterator = this.f.iterator();
/*     */     
/* 253 */     while (iterator.hasNext()) {
/* 254 */       NonNullList<ItemStack> nonnulllist = iterator.next();
/*     */       
/* 256 */       for (int i = 0; i < nonnulllist.size(); i++) {
/* 257 */         if (!((ItemStack)nonnulllist.get(i)).isEmpty()) {
/* 258 */           ((ItemStack)nonnulllist.get(i)).a(this.player.world, this.player, i, (this.itemInHandIndex == i));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean pickup(ItemStack itemstack) {
/* 266 */     return c(-1, itemstack);
/*     */   }
/*     */   
/*     */   public boolean c(int i, ItemStack itemstack) {
/* 270 */     if (itemstack.isEmpty())
/* 271 */       return false; 
/*     */     try {
/*     */       int j;
/* 274 */       if (itemstack.f()) {
/* 275 */         if (i == -1) {
/* 276 */           i = getFirstEmptySlotIndex();
/*     */         }
/*     */         
/* 279 */         if (i >= 0) {
/* 280 */           this.items.set(i, itemstack.cloneItemStack());
/* 281 */           ((ItemStack)this.items.get(i)).d(5);
/* 282 */           itemstack.setCount(0);
/* 283 */           return true;
/* 284 */         }  if (this.player.abilities.canInstantlyBuild) {
/* 285 */           itemstack.setCount(0);
/* 286 */           return true;
/*     */         } 
/* 288 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 294 */         j = itemstack.getCount();
/* 295 */         if (i == -1) {
/* 296 */           itemstack.setCount(i(itemstack));
/*     */         } else {
/* 298 */           itemstack.setCount(d(i, itemstack));
/*     */         } 
/* 300 */       } while (!itemstack.isEmpty() && itemstack.getCount() < j);
/*     */       
/* 302 */       if (itemstack.getCount() == j && this.player.abilities.canInstantlyBuild) {
/* 303 */         itemstack.setCount(0);
/* 304 */         return true;
/*     */       } 
/* 306 */       return (itemstack.getCount() < j);
/*     */     
/*     */     }
/* 309 */     catch (Throwable throwable) {
/* 310 */       CrashReport crashreport = CrashReport.a(throwable, "Adding item to inventory");
/* 311 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Item being added");
/*     */       
/* 313 */       crashreportsystemdetails.a("Item ID", Integer.valueOf(Item.getId(itemstack.getItem())));
/* 314 */       crashreportsystemdetails.a("Item data", Integer.valueOf(itemstack.getDamage()));
/* 315 */       crashreportsystemdetails.a("Item name", () -> itemstack.getName().getString());
/*     */ 
/*     */       
/* 318 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, ItemStack itemstack) {
/* 324 */     if (!world.isClientSide) {
/* 325 */       while (!itemstack.isEmpty()) {
/* 326 */         int i = firstPartial(itemstack);
/*     */         
/* 328 */         if (i == -1) {
/* 329 */           i = getFirstEmptySlotIndex();
/*     */         }
/*     */         
/* 332 */         if (i == -1) {
/* 333 */           this.player.drop(itemstack, false);
/*     */           
/*     */           break;
/*     */         } 
/* 337 */         int j = itemstack.getMaxStackSize() - getItem(i).getCount();
/*     */         
/* 339 */         if (c(i, itemstack.cloneAndSubtract(j))) {
/* 340 */           ((EntityPlayer)this.player).playerConnection.sendPacket(new PacketPlayOutSetSlot(-2, i, getItem(i)));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 349 */     List<ItemStack> list = null;
/*     */ 
/*     */ 
/*     */     
/* 353 */     for (Iterator<NonNullList<ItemStack>> iterator = this.f.iterator(); iterator.hasNext(); i -= nonnulllist.size()) {
/* 354 */       NonNullList<ItemStack> nonnulllist = iterator.next();
/* 355 */       if (i < nonnulllist.size()) {
/* 356 */         list = nonnulllist;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 361 */     return (list != null && !((ItemStack)list.get(i)).isEmpty()) ? ContainerUtil.a(list, i, j) : ItemStack.b;
/*     */   }
/*     */   
/*     */   public void f(ItemStack itemstack) {
/* 365 */     Iterator<NonNullList<ItemStack>> iterator = this.f.iterator();
/*     */     
/* 367 */     while (iterator.hasNext()) {
/* 368 */       NonNullList<ItemStack> nonnulllist = iterator.next();
/*     */       
/* 370 */       for (int i = 0; i < nonnulllist.size(); i++) {
/* 371 */         if (nonnulllist.get(i) == itemstack) {
/* 372 */           nonnulllist.set(i, ItemStack.b);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 382 */     NonNullList<ItemStack> nonnulllist = null;
/*     */ 
/*     */ 
/*     */     
/* 386 */     for (Iterator<NonNullList<ItemStack>> iterator = this.f.iterator(); iterator.hasNext(); i -= nonnulllist1.size()) {
/* 387 */       NonNullList<ItemStack> nonnulllist1 = iterator.next();
/* 388 */       if (i < nonnulllist1.size()) {
/* 389 */         nonnulllist = nonnulllist1;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 394 */     if (nonnulllist != null && !((ItemStack)nonnulllist.get(i)).isEmpty()) {
/* 395 */       ItemStack itemstack = nonnulllist.get(i);
/*     */       
/* 397 */       nonnulllist.set(i, ItemStack.b);
/* 398 */       return itemstack;
/*     */     } 
/* 400 */     return ItemStack.b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 406 */     NonNullList<ItemStack> nonnulllist = null;
/*     */ 
/*     */ 
/*     */     
/* 410 */     for (Iterator<NonNullList<ItemStack>> iterator = this.f.iterator(); iterator.hasNext(); i -= nonnulllist1.size()) {
/* 411 */       NonNullList<ItemStack> nonnulllist1 = iterator.next();
/* 412 */       if (i < nonnulllist1.size()) {
/* 413 */         nonnulllist = nonnulllist1;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 418 */     if (nonnulllist != null) {
/* 419 */       nonnulllist.set(i, itemstack);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(IBlockData iblockdata) {
/* 425 */     return ((ItemStack)this.items.get(this.itemInHandIndex)).a(iblockdata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList a(NBTTagList nbttaglist) {
/*     */     int i;
/* 432 */     for (i = 0; i < this.items.size(); i++) {
/* 433 */       if (!((ItemStack)this.items.get(i)).isEmpty()) {
/* 434 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 435 */         nbttagcompound.setByte("Slot", (byte)i);
/* 436 */         ((ItemStack)this.items.get(i)).save(nbttagcompound);
/* 437 */         nbttaglist.add(nbttagcompound);
/*     */       } 
/*     */     } 
/*     */     
/* 441 */     for (i = 0; i < this.armor.size(); i++) {
/* 442 */       if (!((ItemStack)this.armor.get(i)).isEmpty()) {
/* 443 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 444 */         nbttagcompound.setByte("Slot", (byte)(i + 100));
/* 445 */         ((ItemStack)this.armor.get(i)).save(nbttagcompound);
/* 446 */         nbttaglist.add(nbttagcompound);
/*     */       } 
/*     */     } 
/*     */     
/* 450 */     for (i = 0; i < this.extraSlots.size(); i++) {
/* 451 */       if (!((ItemStack)this.extraSlots.get(i)).isEmpty()) {
/* 452 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 453 */         nbttagcompound.setByte("Slot", (byte)(i + 150));
/* 454 */         ((ItemStack)this.extraSlots.get(i)).save(nbttagcompound);
/* 455 */         nbttaglist.add(nbttagcompound);
/*     */       } 
/*     */     } 
/*     */     
/* 459 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   public void b(NBTTagList nbttaglist) {
/* 463 */     this.items.clear();
/* 464 */     this.armor.clear();
/* 465 */     this.extraSlots.clear();
/*     */     
/* 467 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 468 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/* 469 */       int j = nbttagcompound.getByte("Slot") & 0xFF;
/* 470 */       ItemStack itemstack = ItemStack.a(nbttagcompound);
/*     */       
/* 472 */       if (!itemstack.isEmpty()) {
/* 473 */         if (j >= 0 && j < this.items.size()) {
/* 474 */           this.items.set(j, itemstack);
/* 475 */         } else if (j >= 100 && j < this.armor.size() + 100) {
/* 476 */           this.armor.set(j - 100, itemstack);
/* 477 */         } else if (j >= 150 && j < this.extraSlots.size() + 150) {
/* 478 */           this.extraSlots.set(j - 150, itemstack);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 487 */     return this.items.size() + this.armor.size() + this.extraSlots.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/* 492 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 497 */       if (!iterator.hasNext()) {
/* 498 */         ItemStack itemStack; iterator = this.armor.iterator();
/*     */         
/*     */         do {
/* 501 */           if (!iterator.hasNext()) {
/* 502 */             ItemStack itemStack1; iterator = this.extraSlots.iterator();
/*     */             
/*     */             do {
/* 505 */               if (!iterator.hasNext()) {
/* 506 */                 return true;
/*     */               }
/*     */               
/* 509 */               itemStack1 = iterator.next();
/* 510 */             } while (itemStack1.isEmpty());
/*     */             
/* 512 */             return false;
/*     */           } 
/*     */           
/* 515 */           itemStack = iterator.next();
/* 516 */         } while (itemStack.isEmpty());
/*     */         
/* 518 */         return false;
/*     */       } 
/*     */       
/* 521 */       itemstack = iterator.next();
/* 522 */     } while (itemstack.isEmpty());
/*     */     
/* 524 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/* 529 */     List<ItemStack> list = null;
/*     */ 
/*     */ 
/*     */     
/* 533 */     for (Iterator<NonNullList<ItemStack>> iterator = this.f.iterator(); iterator.hasNext(); i -= nonnulllist.size()) {
/* 534 */       NonNullList<ItemStack> nonnulllist = iterator.next();
/* 535 */       if (i < nonnulllist.size()) {
/* 536 */         list = nonnulllist;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 541 */     return (list == null) ? ItemStack.b : list.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getDisplayName() {
/* 546 */     return new ChatMessage("container.inventory");
/*     */   }
/*     */   
/*     */   public void a(DamageSource damagesource, float f) {
/* 550 */     if (f > 0.0F) {
/* 551 */       f /= 4.0F;
/* 552 */       if (f < 1.0F) {
/* 553 */         f = 1.0F;
/*     */       }
/*     */       
/* 556 */       for (int i = 0; i < this.armor.size(); i++) {
/* 557 */         ItemStack itemstack = this.armor.get(i);
/*     */         
/* 559 */         if ((!damagesource.isFire() || !itemstack.getItem().u()) && itemstack.getItem() instanceof ItemArmor) {
/* 560 */           int finalI = i;
/* 561 */           itemstack.damage((int)f, this.player, entityhuman -> entityhuman.broadcastItemBreak(EnumItemSlot.a(EnumItemSlot.Function.ARMOR, finalI)));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropContents() {
/* 571 */     Iterator<NonNullList<ItemStack>> iterator = this.f.iterator();
/*     */     
/* 573 */     while (iterator.hasNext()) {
/* 574 */       List<ItemStack> list = iterator.next();
/*     */       
/* 576 */       for (int i = 0; i < list.size(); i++) {
/* 577 */         ItemStack itemstack = list.get(i);
/*     */         
/* 579 */         if (!itemstack.isEmpty()) {
/* 580 */           this.player.a(itemstack, true, false);
/* 581 */           list.set(i, ItemStack.b);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 590 */     this.h++;
/*     */   }
/*     */   
/*     */   public void setCarried(ItemStack itemstack) {
/* 594 */     this.carried = itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCarried() {
/* 599 */     if (this.carried.isEmpty()) {
/* 600 */       setCarried(ItemStack.b);
/*     */     }
/*     */     
/* 603 */     return this.carried;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 608 */     return this.player.dead ? false : ((entityhuman.h(this.player) <= 64.0D));
/*     */   }
/*     */   
/*     */   public boolean h(ItemStack itemstack) {
/* 612 */     Iterator<NonNullList<ItemStack>> iterator = this.f.iterator();
/*     */     
/* 614 */     while (iterator.hasNext()) {
/* 615 */       List<ItemStack> list = iterator.next();
/* 616 */       Iterator<ItemStack> iterator1 = list.iterator();
/*     */       
/* 618 */       while (iterator1.hasNext()) {
/* 619 */         ItemStack itemstack1 = iterator1.next();
/*     */         
/* 621 */         if (!itemstack1.isEmpty() && itemstack1.doMaterialsMatch(itemstack)) {
/* 622 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 627 */     return false;
/*     */   }
/*     */   
/*     */   public void a(PlayerInventory playerinventory) {
/* 631 */     for (int i = 0; i < getSize(); i++) {
/* 632 */       setItem(i, playerinventory.getItem(i));
/*     */     }
/*     */     
/* 635 */     this.itemInHandIndex = playerinventory.itemInHandIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 640 */     Iterator<NonNullList<ItemStack>> iterator = this.f.iterator();
/*     */     
/* 642 */     while (iterator.hasNext()) {
/* 643 */       List<ItemStack> list = iterator.next();
/*     */       
/* 645 */       list.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/* 651 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */     
/* 653 */     while (iterator.hasNext()) {
/* 654 */       ItemStack itemstack = iterator.next();
/*     */       
/* 656 */       autorecipestackmanager.a(itemstack);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */