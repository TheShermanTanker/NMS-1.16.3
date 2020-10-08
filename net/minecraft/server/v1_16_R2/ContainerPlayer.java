/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryCrafting;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerPlayer extends ContainerRecipeBook<InventoryCrafting> {
/*  10 */   public static final MinecraftKey c = new MinecraftKey("textures/atlas/blocks.png");
/*  11 */   public static final MinecraftKey d = new MinecraftKey("item/empty_armor_slot_helmet");
/*  12 */   public static final MinecraftKey e = new MinecraftKey("item/empty_armor_slot_chestplate");
/*  13 */   public static final MinecraftKey f = new MinecraftKey("item/empty_armor_slot_leggings");
/*  14 */   public static final MinecraftKey g = new MinecraftKey("item/empty_armor_slot_boots");
/*  15 */   public static final MinecraftKey h = new MinecraftKey("item/empty_armor_slot_shield");
/*  16 */   private static final MinecraftKey[] j = new MinecraftKey[] { g, f, e, d };
/*  17 */   private static final EnumItemSlot[] k = new EnumItemSlot[] { EnumItemSlot.HEAD, EnumItemSlot.CHEST, EnumItemSlot.LEGS, EnumItemSlot.FEET };
/*     */   
/*     */   private final InventoryCrafting craftInventory;
/*     */   
/*     */   private final InventoryCraftResult resultInventory;
/*     */   
/*     */   public final boolean i;
/*     */   private final EntityHuman owner;
/*  25 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerPlayer(PlayerInventory playerinventory, boolean flag, EntityHuman entityhuman) {
/*  30 */     super((Containers)null, 0);
/*  31 */     this.i = flag;
/*  32 */     this.owner = entityhuman;
/*     */     
/*  34 */     this.resultInventory = new InventoryCraftResult();
/*  35 */     this.craftInventory = new InventoryCrafting(this, 2, 2, playerinventory.player);
/*  36 */     this.craftInventory.resultInventory = this.resultInventory;
/*  37 */     this.player = playerinventory;
/*  38 */     setTitle(new ChatMessage("container.crafting"));
/*     */     
/*  40 */     a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 154, 28));
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/*  45 */     for (i = 0; i < 2; i++) {
/*  46 */       for (int j = 0; j < 2; j++) {
/*  47 */         a(new Slot(this.craftInventory, j + i * 2, 98 + j * 18, 18 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  51 */     for (i = 0; i < 4; i++) {
/*  52 */       final EnumItemSlot enumitemslot = k[i];
/*     */       
/*  54 */       a(new Slot(playerinventory, 39 - i, 8, 8 + i * 18)
/*     */           {
/*     */             public int getMaxStackSize() {
/*  57 */               return 1;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean isAllowed(ItemStack itemstack) {
/*  62 */               return (enumitemslot == EntityInsentient.j(itemstack));
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean isAllowed(EntityHuman entityhuman1) {
/*  67 */               ItemStack itemstack = getItem();
/*     */               
/*  69 */               return (!itemstack.isEmpty() && !entityhuman1.isCreative() && EnchantmentManager.d(itemstack)) ? false : super.isAllowed(entityhuman1);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  74 */     for (i = 0; i < 3; i++) {
/*  75 */       for (int j = 0; j < 9; j++) {
/*  76 */         a(new Slot(playerinventory, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  80 */     for (i = 0; i < 9; i++) {
/*  81 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */     
/*  84 */     a(new Slot(playerinventory, 40, 77, 62) {
/*     */         
/*     */         });
/*     */   }
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/*  90 */     this.craftInventory.a(autorecipestackmanager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  95 */     this.resultInventory.clear();
/*  96 */     this.craftInventory.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IRecipe<? super InventoryCrafting> irecipe) {
/* 101 */     return irecipe.a(this.craftInventory, this.owner.world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 106 */     ContainerWorkbench.a(this.windowId, this.owner.world, this.owner, this.craftInventory, this.resultInventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 111 */     super.b(entityhuman);
/* 112 */     this.resultInventory.clear();
/* 113 */     if (!entityhuman.world.isClientSide) {
/* 114 */       a(entityhuman, entityhuman.world, this.craftInventory);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 125 */     ItemStack itemstack = ItemStack.b;
/* 126 */     Slot slot = this.slots.get(i);
/*     */     
/* 128 */     if (slot != null && slot.hasItem()) {
/* 129 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 131 */       itemstack = itemstack1.cloneItemStack();
/* 132 */       EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*     */       
/* 134 */       if (i == 0) {
/* 135 */         if (!a(itemstack1, 9, 45, true)) {
/* 136 */           return ItemStack.b;
/*     */         }
/*     */         
/* 139 */         slot.a(itemstack1, itemstack);
/* 140 */       } else if (i >= 1 && i < 5) {
/* 141 */         if (!a(itemstack1, 9, 45, false)) {
/* 142 */           return ItemStack.b;
/*     */         }
/* 144 */       } else if (i >= 5 && i < 9) {
/* 145 */         if (!a(itemstack1, 9, 45, false)) {
/* 146 */           return ItemStack.b;
/*     */         }
/* 148 */       } else if (enumitemslot.a() == EnumItemSlot.Function.ARMOR && !((Slot)this.slots.get(8 - enumitemslot.b())).hasItem()) {
/* 149 */         int j = 8 - enumitemslot.b();
/*     */         
/* 151 */         if (!a(itemstack1, j, j + 1, false)) {
/* 152 */           return ItemStack.b;
/*     */         }
/* 154 */       } else if (enumitemslot == EnumItemSlot.OFFHAND && !((Slot)this.slots.get(45)).hasItem()) {
/* 155 */         if (!a(itemstack1, 45, 46, false)) {
/* 156 */           return ItemStack.b;
/*     */         }
/* 158 */       } else if (i >= 9 && i < 36) {
/* 159 */         if (!a(itemstack1, 36, 45, false)) {
/* 160 */           return ItemStack.b;
/*     */         }
/* 162 */       } else if (i >= 36 && i < 45) {
/* 163 */         if (!a(itemstack1, 9, 36, false)) {
/* 164 */           return ItemStack.b;
/*     */         }
/* 166 */       } else if (!a(itemstack1, 9, 45, false)) {
/* 167 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 170 */       if (itemstack1.isEmpty()) {
/* 171 */         slot.set(ItemStack.b);
/*     */       } else {
/* 173 */         slot.d();
/*     */       } 
/*     */       
/* 176 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 177 */         return ItemStack.b;
/*     */       }
/*     */       
/* 180 */       ItemStack itemstack2 = slot.a(entityhuman, itemstack1);
/*     */       
/* 182 */       if (i == 0) {
/* 183 */         entityhuman.drop(itemstack2, false);
/*     */       }
/*     */     } 
/*     */     
/* 187 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 192 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public int f() {
/* 197 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int g() {
/* 202 */     return this.craftInventory.g();
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/* 207 */     return this.craftInventory.f();
/*     */   }
/*     */   
/*     */   public InventoryCrafting j() {
/* 211 */     return this.craftInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 217 */     if (this.bukkitEntity != null) {
/* 218 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 221 */     CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
/* 222 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 223 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */