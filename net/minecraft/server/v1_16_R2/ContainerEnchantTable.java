/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryEnchanting;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.enchantments.EnchantmentOffer;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*     */ import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class ContainerEnchantTable
/*     */   extends Container
/*     */ {
/*     */   private final IInventory enchantSlots;
/*     */   private final ContainerAccess containerAccess;
/*     */   private final Random h;
/*  31 */   private CraftInventoryView bukkitEntity = null; private final ContainerProperty i; public final int[] costs; public final int[] enchantments;
/*     */   public final int[] levels;
/*     */   private Player player;
/*     */   
/*     */   public ContainerEnchantTable(int i, PlayerInventory playerinventory) {
/*  36 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerEnchantTable(int i, PlayerInventory playerinventory, final ContainerAccess containeraccess) {
/*  40 */     super(Containers.ENCHANTMENT, i);
/*  41 */     this.enchantSlots = new InventorySubcontainer(2)
/*     */       {
/*     */         public void update() {
/*  44 */           super.update();
/*  45 */           ContainerEnchantTable.this.a(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  51 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  55 */     this.h = new Random();
/*  56 */     this.i = ContainerProperty.a();
/*  57 */     this.costs = new int[3];
/*  58 */     this.enchantments = new int[] { -1, -1, -1 };
/*  59 */     this.levels = new int[] { -1, -1, -1 };
/*  60 */     this.containerAccess = containeraccess;
/*  61 */     a(new Slot(this.enchantSlots, 0, 15, 47)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  64 */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           public int getMaxStackSize() {
/*  69 */             return 1;
/*     */           }
/*     */         });
/*  72 */     a(new Slot(this.enchantSlots, 1, 35, 47)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  75 */             return (itemstack.getItem() == Items.LAPIS_LAZULI);
/*     */           }
/*     */         });
/*     */     
/*     */     int j;
/*     */     
/*  81 */     for (j = 0; j < 3; j++) {
/*  82 */       for (int k = 0; k < 9; k++) {
/*  83 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  87 */     for (j = 0; j < 9; j++) {
/*  88 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */     
/*  91 */     a(ContainerProperty.a(this.costs, 0));
/*  92 */     a(ContainerProperty.a(this.costs, 1));
/*  93 */     a(ContainerProperty.a(this.costs, 2));
/*  94 */     a(this.i).set(playerinventory.player.eF());
/*  95 */     a(ContainerProperty.a(this.enchantments, 0));
/*  96 */     a(ContainerProperty.a(this.enchantments, 1));
/*  97 */     a(ContainerProperty.a(this.enchantments, 2));
/*  98 */     a(ContainerProperty.a(this.levels, 0));
/*  99 */     a(ContainerProperty.a(this.levels, 1));
/* 100 */     a(ContainerProperty.a(this.levels, 2));
/*     */     
/* 102 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 108 */     if (iinventory == this.enchantSlots) {
/* 109 */       ItemStack itemstack = iinventory.getItem(0);
/*     */       
/* 111 */       if (!itemstack.isEmpty()) {
/* 112 */         this.containerAccess.a((world, blockposition) -> {
/*     */               int i = 0;
/*     */               
/*     */               int j;
/*     */               
/*     */               for (j = -1; j <= 1; j++) {
/*     */                 for (int k = -1; k <= 1; k++) {
/*     */                   if ((j != 0 || k != 0) && world.isEmpty(blockposition.b(k, 0, j)) && world.isEmpty(blockposition.b(k, 1, j))) {
/*     */                     if (world.getType(blockposition.b(k * 2, 0, j * 2)).a(Blocks.BOOKSHELF)) {
/*     */                       i++;
/*     */                     }
/*     */                     
/*     */                     if (world.getType(blockposition.b(k * 2, 1, j * 2)).a(Blocks.BOOKSHELF)) {
/*     */                       i++;
/*     */                     }
/*     */                     
/*     */                     if (k != 0 && j != 0) {
/*     */                       if (world.getType(blockposition.b(k * 2, 0, j)).a(Blocks.BOOKSHELF)) {
/*     */                         i++;
/*     */                       }
/*     */                       
/*     */                       if (world.getType(blockposition.b(k * 2, 1, j)).a(Blocks.BOOKSHELF)) {
/*     */                         i++;
/*     */                       }
/*     */                       
/*     */                       if (world.getType(blockposition.b(k, 0, j * 2)).a(Blocks.BOOKSHELF)) {
/*     */                         i++;
/*     */                       }
/*     */                       
/*     */                       if (world.getType(blockposition.b(k, 1, j * 2)).a(Blocks.BOOKSHELF)) {
/*     */                         i++;
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/*     */               this.h.setSeed(this.i.get());
/*     */               
/*     */               for (j = 0; j < 3; j++) {
/*     */                 this.costs[j] = EnchantmentManager.a(this.h, j, i, itemstack);
/*     */                 
/*     */                 this.enchantments[j] = -1;
/*     */                 
/*     */                 this.levels[j] = -1;
/*     */                 
/*     */                 if (this.costs[j] < j + 1) {
/*     */                   this.costs[j] = 0;
/*     */                 }
/*     */               } 
/*     */               
/*     */               for (j = 0; j < 3; j++) {
/*     */                 if (this.costs[j] > 0) {
/*     */                   List<WeightedRandomEnchant> list = a(itemstack, j, this.costs[j]);
/*     */                   
/*     */                   if (list != null && !list.isEmpty()) {
/*     */                     WeightedRandomEnchant weightedrandomenchant = list.get(this.h.nextInt(list.size()));
/*     */                     
/*     */                     this.enchantments[j] = IRegistry.ENCHANTMENT.a((T)weightedrandomenchant.enchantment);
/*     */                     
/*     */                     this.levels[j] = weightedrandomenchant.level;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/*     */               CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
/*     */               
/*     */               EnchantmentOffer[] offers = new EnchantmentOffer[3];
/*     */               
/*     */               for (j = 0; j < 3; j++) {
/*     */                 Enchantment enchantment = (this.enchantments[j] >= 0) ? Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(IRegistry.ENCHANTMENT.getKey(IRegistry.ENCHANTMENT.fromId(this.enchantments[j])))) : null;
/*     */                 offers[j] = (enchantment != null) ? new EnchantmentOffer(enchantment, this.levels[j], this.costs[j]) : null;
/*     */               } 
/*     */               PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(this.player, (InventoryView)getBukkitView(), this.containerAccess.getLocation().getBlock(), (ItemStack)item, offers, i);
/*     */               event.setCancelled(!itemstack.canEnchant());
/*     */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */               if (event.isCancelled()) {
/*     */                 for (j = 0; j < 3; j++) {
/*     */                   this.costs[j] = 0;
/*     */                   this.enchantments[j] = -1;
/*     */                   this.levels[j] = -1;
/*     */                 } 
/*     */                 return;
/*     */               } 
/*     */               for (j = 0; j < 3; j++) {
/*     */                 EnchantmentOffer offer = event.getOffers()[j];
/*     */                 if (offer != null) {
/*     */                   this.costs[j] = offer.getCost();
/*     */                   this.enchantments[j] = IRegistry.ENCHANTMENT.a(IRegistry.ENCHANTMENT.get(CraftNamespacedKey.toMinecraft(offer.getEnchantment().getKey())));
/*     */                   this.levels[j] = offer.getEnchantmentLevel();
/*     */                 } else {
/*     */                   this.costs[j] = 0;
/*     */                   this.enchantments[j] = -1;
/*     */                   this.levels[j] = -1;
/*     */                 } 
/*     */               } 
/*     */               c();
/*     */             });
/*     */       } else {
/* 211 */         for (int i = 0; i < 3; i++) {
/* 212 */           this.costs[i] = 0;
/* 213 */           this.enchantments[i] = -1;
/* 214 */           this.levels[i] = -1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/* 223 */     ItemStack itemstack = this.enchantSlots.getItem(0);
/* 224 */     ItemStack itemstack1 = this.enchantSlots.getItem(1);
/* 225 */     int j = i + 1;
/*     */     
/* 227 */     if ((itemstack1.isEmpty() || itemstack1.getCount() < j) && !entityhuman.abilities.canInstantlyBuild)
/* 228 */       return false; 
/* 229 */     if (this.costs[i] > 0 && !itemstack.isEmpty() && ((entityhuman.expLevel >= j && entityhuman.expLevel >= this.costs[i]) || entityhuman.abilities.canInstantlyBuild)) {
/* 230 */       this.containerAccess.a((world, blockposition) -> {
/*     */             ItemStack itemstack2 = itemstack;
/*     */             
/*     */             List<WeightedRandomEnchant> list = a(itemstack, i, this.costs[i]);
/*     */             
/*     */             boolean flag = (itemstack.getItem() == Items.BOOK);
/*     */             
/*     */             Map<Enchantment, Integer> enchants = new HashMap<>();
/*     */             
/*     */             for (WeightedRandomEnchant obj : list) {
/*     */               WeightedRandomEnchant instance = obj;
/*     */               
/*     */               enchants.put(Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(IRegistry.ENCHANTMENT.getKey(instance.enchantment))), Integer.valueOf(instance.level));
/*     */             } 
/*     */             
/*     */             CraftItemStack item = CraftItemStack.asCraftMirror(itemstack2);
/*     */             
/*     */             EnchantItemEvent event = new EnchantItemEvent((Player)entityhuman.getBukkitEntity(), (InventoryView)getBukkitView(), this.containerAccess.getLocation().getBlock(), (ItemStack)item, this.costs[i], enchants, i);
/*     */             
/*     */             world.getServer().getPluginManager().callEvent((Event)event);
/*     */             
/*     */             int level = event.getExpLevelCost();
/*     */             
/*     */             if (event.isCancelled() || (level > entityhuman.expLevel && !entityhuman.abilities.canInstantlyBuild) || event.getEnchantsToAdd().isEmpty()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (flag) {
/*     */               itemstack2 = new ItemStack(Items.ENCHANTED_BOOK);
/*     */               NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */               if (nbttagcompound != null) {
/*     */                 itemstack2.setTag(nbttagcompound.clone());
/*     */               }
/*     */               this.enchantSlots.setItem(0, itemstack2);
/*     */             } 
/*     */             for (Map.Entry<Enchantment, Integer> entry : (Iterable<Map.Entry<Enchantment, Integer>>)event.getEnchantsToAdd().entrySet()) {
/*     */               try {
/*     */                 if (flag) {
/*     */                   NamespacedKey enchantId = ((Enchantment)entry.getKey()).getKey();
/*     */                   Enchantment nms = IRegistry.ENCHANTMENT.get(CraftNamespacedKey.toMinecraft(enchantId));
/*     */                   if (nms == null) {
/*     */                     continue;
/*     */                   }
/*     */                   WeightedRandomEnchant weightedrandomenchant = new WeightedRandomEnchant(nms, ((Integer)entry.getValue()).intValue());
/*     */                   ItemEnchantedBook.a(itemstack2, weightedrandomenchant);
/*     */                   continue;
/*     */                 } 
/*     */                 item.addUnsafeEnchantment(entry.getKey(), ((Integer)entry.getValue()).intValue());
/* 278 */               } catch (IllegalArgumentException illegalArgumentException) {}
/*     */             } 
/*     */             
/*     */             entityhuman.enchantDone(itemstack, j);
/*     */             
/*     */             if (!entityhuman.abilities.canInstantlyBuild) {
/*     */               itemstack1.subtract(j);
/*     */               
/*     */               if (itemstack1.isEmpty()) {
/*     */                 this.enchantSlots.setItem(1, ItemStack.b);
/*     */               }
/*     */             } 
/*     */             
/*     */             entityhuman.a(StatisticList.ENCHANT_ITEM);
/*     */             
/*     */             if (entityhuman instanceof EntityPlayer) {
/*     */               CriterionTriggers.i.a((EntityPlayer)entityhuman, itemstack2, j);
/*     */             }
/*     */             
/*     */             this.enchantSlots.update();
/*     */             
/*     */             this.i.set(entityhuman.eF());
/*     */             
/*     */             a(this.enchantSlots);
/*     */             
/*     */             world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
/*     */           });
/*     */       
/* 306 */       return true;
/*     */     } 
/* 308 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<WeightedRandomEnchant> a(ItemStack itemstack, int i, int j) {
/* 313 */     this.h.setSeed((this.i.get() + i));
/* 314 */     List<WeightedRandomEnchant> list = EnchantmentManager.b(this.h, itemstack, j, false);
/*     */     
/* 316 */     if (itemstack.getItem() == Items.BOOK && list.size() > 1) {
/* 317 */       list.remove(this.h.nextInt(list.size()));
/*     */     }
/*     */     
/* 320 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 325 */     super.b(entityhuman);
/* 326 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, entityhuman.world, this.enchantSlots));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 333 */     if (!this.checkReachable) return true; 
/* 334 */     return a(this.containerAccess, entityhuman, Blocks.ENCHANTING_TABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 339 */     ItemStack itemstack = ItemStack.b;
/* 340 */     Slot slot = this.slots.get(i);
/*     */     
/* 342 */     if (slot != null && slot.hasItem()) {
/* 343 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 345 */       itemstack = itemstack1.cloneItemStack();
/* 346 */       if (i == 0) {
/* 347 */         if (!a(itemstack1, 2, 38, true)) {
/* 348 */           return ItemStack.b;
/*     */         }
/* 350 */       } else if (i == 1) {
/* 351 */         if (!a(itemstack1, 2, 38, true)) {
/* 352 */           return ItemStack.b;
/*     */         }
/* 354 */       } else if (itemstack1.getItem() == Items.LAPIS_LAZULI) {
/* 355 */         if (!a(itemstack1, 1, 2, true)) {
/* 356 */           return ItemStack.b;
/*     */         }
/*     */       } else {
/* 359 */         if (((Slot)this.slots.get(0)).hasItem() || !((Slot)this.slots.get(0)).isAllowed(itemstack1)) {
/* 360 */           return ItemStack.b;
/*     */         }
/*     */         
/* 363 */         ItemStack itemstack2 = itemstack1.cloneItemStack();
/*     */         
/* 365 */         itemstack2.setCount(1);
/* 366 */         itemstack1.subtract(1);
/* 367 */         ((Slot)this.slots.get(0)).set(itemstack2);
/*     */       } 
/*     */       
/* 370 */       if (itemstack1.isEmpty()) {
/* 371 */         slot.set(ItemStack.b);
/*     */       } else {
/* 373 */         slot.d();
/*     */       } 
/*     */       
/* 376 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 377 */         return ItemStack.b;
/*     */       }
/*     */       
/* 380 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 383 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 389 */     if (this.bukkitEntity != null) {
/* 390 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 393 */     CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.enchantSlots);
/* 394 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/* 395 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerEnchantTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */