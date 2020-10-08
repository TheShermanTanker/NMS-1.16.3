/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryMerchant;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerMerchant extends Container {
/*     */   private final IMerchant merchant;
/*  11 */   private CraftInventoryView bukkitEntity = null;
/*     */   private final InventoryMerchant inventoryMerchant;
/*     */   private PlayerInventory player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  16 */     if (this.bukkitEntity == null) {
/*  17 */       this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)new CraftInventoryMerchant(this.merchant, this.inventoryMerchant), this);
/*     */     }
/*  19 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerMerchant(int i, PlayerInventory playerinventory) {
/*  24 */     this(i, playerinventory, new MerchantWrapper(playerinventory.player));
/*     */   }
/*     */   
/*     */   public ContainerMerchant(int i, PlayerInventory playerinventory, IMerchant imerchant) {
/*  28 */     super(Containers.MERCHANT, i);
/*  29 */     this.merchant = imerchant;
/*  30 */     this.inventoryMerchant = new InventoryMerchant(imerchant);
/*  31 */     a(new Slot(this.inventoryMerchant, 0, 136, 37));
/*  32 */     a(new Slot(this.inventoryMerchant, 1, 162, 37));
/*  33 */     a(new SlotMerchantResult(playerinventory.player, imerchant, this.inventoryMerchant, 2, 220, 37));
/*  34 */     this.player = playerinventory;
/*     */     
/*     */     int j;
/*     */     
/*  38 */     for (j = 0; j < 3; j++) {
/*  39 */       for (int k = 0; k < 9; k++) {
/*  40 */         a(new Slot(playerinventory, k + j * 9 + 9, 108 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  44 */     for (j = 0; j < 9; j++) {
/*  45 */       a(new Slot(playerinventory, j, 108 + j * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/*  52 */     this.inventoryMerchant.f();
/*  53 */     super.a(iinventory);
/*     */   }
/*     */   
/*     */   public void d(int i) {
/*  57 */     this.inventoryMerchant.c(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/*  62 */     return (this.merchant.getTrader() == entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/*  72 */     ItemStack itemstack = ItemStack.b;
/*  73 */     Slot slot = this.slots.get(i);
/*     */     
/*  75 */     if (slot != null && slot.hasItem()) {
/*  76 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  78 */       itemstack = itemstack1.cloneItemStack();
/*  79 */       if (i == 2) {
/*  80 */         if (!a(itemstack1, 3, 39, true)) {
/*  81 */           return ItemStack.b;
/*     */         }
/*     */         
/*  84 */         slot.a(itemstack1, itemstack);
/*  85 */         k();
/*  86 */       } else if (i != 0 && i != 1) {
/*  87 */         if (i >= 3 && i < 30) {
/*  88 */           if (!a(itemstack1, 30, 39, false)) {
/*  89 */             return ItemStack.b;
/*     */           }
/*  91 */         } else if (i >= 30 && i < 39 && !a(itemstack1, 3, 30, false)) {
/*  92 */           return ItemStack.b;
/*     */         } 
/*  94 */       } else if (!a(itemstack1, 3, 39, false)) {
/*  95 */         return ItemStack.b;
/*     */       } 
/*     */       
/*  98 */       if (itemstack1.isEmpty()) {
/*  99 */         slot.set(ItemStack.b);
/*     */       } else {
/* 101 */         slot.d();
/*     */       } 
/*     */       
/* 104 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 105 */         return ItemStack.b;
/*     */       }
/*     */       
/* 108 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 111 */     return itemstack;
/*     */   }
/*     */   
/*     */   private void k() {
/* 115 */     if (!(this.merchant.getWorld()).isClientSide && this.merchant instanceof Entity) {
/* 116 */       Entity entity = (Entity)this.merchant;
/*     */       
/* 118 */       this.merchant.getWorld().a(entity.locX(), entity.locY(), entity.locZ(), this.merchant.getTradeSound(), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 125 */     super.b(entityhuman);
/* 126 */     this.merchant.setTradingPlayer((EntityHuman)null);
/* 127 */     if (!(this.merchant.getWorld()).isClientSide) {
/* 128 */       if (entityhuman.isAlive() && (!(entityhuman instanceof EntityPlayer) || !((EntityPlayer)entityhuman).q())) {
/* 129 */         entityhuman.inventory.a(entityhuman.world, this.inventoryMerchant.splitWithoutUpdate(0));
/* 130 */         entityhuman.inventory.a(entityhuman.world, this.inventoryMerchant.splitWithoutUpdate(1));
/*     */       } else {
/* 132 */         ItemStack itemstack = this.inventoryMerchant.splitWithoutUpdate(0);
/*     */         
/* 134 */         if (!itemstack.isEmpty()) {
/* 135 */           entityhuman.drop(itemstack, false);
/*     */         }
/*     */         
/* 138 */         itemstack = this.inventoryMerchant.splitWithoutUpdate(1);
/* 139 */         if (!itemstack.isEmpty()) {
/* 140 */           entityhuman.drop(itemstack, false);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(int i) {
/* 148 */     if (i().size() > i) {
/* 149 */       ItemStack itemstack = this.inventoryMerchant.getItem(0);
/*     */       
/* 151 */       if (!itemstack.isEmpty()) {
/* 152 */         if (!a(itemstack, 3, 39, true)) {
/*     */           return;
/*     */         }
/*     */         
/* 156 */         this.inventoryMerchant.setItem(0, itemstack);
/*     */       } 
/*     */       
/* 159 */       ItemStack itemstack1 = this.inventoryMerchant.getItem(1);
/*     */       
/* 161 */       if (!itemstack1.isEmpty()) {
/* 162 */         if (!a(itemstack1, 3, 39, true)) {
/*     */           return;
/*     */         }
/*     */         
/* 166 */         this.inventoryMerchant.setItem(1, itemstack1);
/*     */       } 
/*     */       
/* 169 */       if (this.inventoryMerchant.getItem(0).isEmpty() && this.inventoryMerchant.getItem(1).isEmpty()) {
/* 170 */         ItemStack itemstack2 = i().get(i).getBuyItem1();
/*     */         
/* 172 */         c(0, itemstack2);
/* 173 */         ItemStack itemstack3 = i().get(i).getBuyItem2();
/*     */         
/* 175 */         c(1, itemstack3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(int i, ItemStack itemstack) {
/* 182 */     if (!itemstack.isEmpty()) {
/* 183 */       for (int j = 3; j < 39; j++) {
/* 184 */         ItemStack itemstack1 = ((Slot)this.slots.get(j)).getItem();
/*     */         
/* 186 */         if (!itemstack1.isEmpty() && b(itemstack, itemstack1)) {
/* 187 */           ItemStack itemstack2 = this.inventoryMerchant.getItem(i);
/* 188 */           int k = itemstack2.isEmpty() ? 0 : itemstack2.getCount();
/* 189 */           int l = Math.min(itemstack.getMaxStackSize() - k, itemstack1.getCount());
/* 190 */           ItemStack itemstack3 = itemstack1.cloneItemStack();
/* 191 */           int i1 = k + l;
/*     */           
/* 193 */           itemstack1.subtract(l);
/* 194 */           itemstack3.setCount(i1);
/* 195 */           this.inventoryMerchant.setItem(i, itemstack3);
/* 196 */           if (i1 >= itemstack.getMaxStackSize()) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(ItemStack itemstack, ItemStack itemstack1) {
/* 206 */     return (itemstack.getItem() == itemstack1.getItem() && ItemStack.equals(itemstack, itemstack1));
/*     */   }
/*     */   
/*     */   public MerchantRecipeList i() {
/* 210 */     return this.merchant.getOffers();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */