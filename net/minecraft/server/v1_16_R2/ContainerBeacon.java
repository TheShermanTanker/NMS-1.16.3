/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryBeacon;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerBeacon extends Container {
/*     */   private final IInventory beacon;
/*     */   private final SlotBeacon d;
/*  12 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private final ContainerAccess containerAccess;
/*     */   
/*     */   public ContainerBeacon(int i, IInventory iinventory) {
/*  17 */     this(i, iinventory, new ContainerProperties(3), ContainerAccess.a);
/*     */   }
/*     */   private final IContainerProperties containerProperties; private PlayerInventory player;
/*     */   public ContainerBeacon(int i, IInventory iinventory, IContainerProperties icontainerproperties, ContainerAccess containeraccess) {
/*  21 */     super(Containers.BEACON, i);
/*  22 */     this.player = (PlayerInventory)iinventory;
/*  23 */     this.beacon = new InventorySubcontainer(1)
/*     */       {
/*     */         public boolean b(int j, ItemStack itemstack) {
/*  26 */           return itemstack.getItem().a(TagsItem.BEACON_PAYMENT_ITEMS);
/*     */         }
/*     */ 
/*     */         
/*     */         public int getMaxStackSize() {
/*  31 */           return 1;
/*     */         }
/*     */       };
/*  34 */     a(icontainerproperties, 3);
/*  35 */     this.containerProperties = icontainerproperties;
/*  36 */     this.containerAccess = containeraccess;
/*  37 */     this.d = new SlotBeacon(this.beacon, 0, 136, 110);
/*  38 */     a(this.d);
/*  39 */     a(icontainerproperties);
/*  40 */     boolean flag = true;
/*  41 */     boolean flag1 = true;
/*     */     
/*     */     int j;
/*     */     
/*  45 */     for (j = 0; j < 3; j++) {
/*  46 */       for (int k = 0; k < 9; k++) {
/*  47 */         a(new Slot(iinventory, k + j * 9 + 9, 36 + k * 18, 137 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  51 */     for (j = 0; j < 9; j++) {
/*  52 */       a(new Slot(iinventory, j, 36 + j * 18, 195));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/*  59 */     super.b(entityhuman);
/*  60 */     if (!entityhuman.world.isClientSide) {
/*  61 */       ItemStack itemstack = this.d.a(this.d.getMaxStackSize());
/*     */       
/*  63 */       if (!itemstack.isEmpty()) {
/*  64 */         entityhuman.drop(itemstack, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/*  72 */     if (!this.checkReachable) return true; 
/*  73 */     return a(this.containerAccess, entityhuman, Blocks.BEACON);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, int j) {
/*  78 */     super.a(i, j);
/*  79 */     c();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/*  84 */     ItemStack itemstack = ItemStack.b;
/*  85 */     Slot slot = this.slots.get(i);
/*     */     
/*  87 */     if (slot != null && slot.hasItem()) {
/*  88 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  90 */       itemstack = itemstack1.cloneItemStack();
/*  91 */       if (i == 0) {
/*  92 */         if (!a(itemstack1, 1, 37, true)) {
/*  93 */           return ItemStack.b;
/*     */         }
/*     */         
/*  96 */         slot.a(itemstack1, itemstack);
/*  97 */       } else if (!this.d.hasItem() && this.d.isAllowed(itemstack1) && itemstack1.getCount() == 1) {
/*  98 */         if (!a(itemstack1, 0, 1, false)) {
/*  99 */           return ItemStack.b;
/*     */         }
/* 101 */       } else if (i >= 1 && i < 28) {
/* 102 */         if (!a(itemstack1, 28, 37, false)) {
/* 103 */           return ItemStack.b;
/*     */         }
/* 105 */       } else if (i >= 28 && i < 37) {
/* 106 */         if (!a(itemstack1, 1, 28, false)) {
/* 107 */           return ItemStack.b;
/*     */         }
/* 109 */       } else if (!a(itemstack1, 1, 37, false)) {
/* 110 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 113 */       if (itemstack1.isEmpty()) {
/* 114 */         slot.set(ItemStack.b);
/*     */       } else {
/* 116 */         slot.d();
/*     */       } 
/*     */       
/* 119 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 120 */         return ItemStack.b;
/*     */       }
/*     */       
/* 123 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 126 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void c(int i, int j) {
/* 130 */     if (this.d.hasItem()) {
/* 131 */       this.containerProperties.setProperty(1, i);
/* 132 */       this.containerProperties.setProperty(2, j);
/* 133 */       this.d.a(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   class SlotBeacon
/*     */     extends Slot
/*     */   {
/*     */     public SlotBeacon(IInventory iinventory, int i, int j, int k) {
/* 141 */       super(iinventory, i, j, k);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 146 */       return itemstack.getItem().a(TagsItem.BEACON_PAYMENT_ITEMS);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 151 */       return 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 158 */     if (this.bukkitEntity != null) {
/* 159 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 162 */     CraftInventoryBeacon craftInventoryBeacon = new CraftInventoryBeacon(this.beacon);
/* 163 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)craftInventoryBeacon, this);
/* 164 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */