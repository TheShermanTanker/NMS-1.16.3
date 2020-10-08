/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryDoubleChest;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryPlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerChest extends Container {
/*     */   private final IInventory container;
/*  13 */   private CraftInventoryView bukkitEntity = null; private final int d;
/*     */   private PlayerInventory player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*     */     CraftInventory inventory;
/*  18 */     if (this.bukkitEntity != null) {
/*  19 */       return this.bukkitEntity;
/*     */     }
/*     */ 
/*     */     
/*  23 */     if (this.container instanceof PlayerInventory) {
/*  24 */       CraftInventoryPlayer craftInventoryPlayer = new CraftInventoryPlayer((PlayerInventory)this.container);
/*  25 */     } else if (this.container instanceof InventoryLargeChest) {
/*  26 */       CraftInventoryDoubleChest craftInventoryDoubleChest = new CraftInventoryDoubleChest((InventoryLargeChest)this.container);
/*     */     } else {
/*  28 */       inventory = new CraftInventory(this.container);
/*     */     } 
/*     */     
/*  31 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/*  32 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   private ContainerChest(Containers<?> containers, int i, PlayerInventory playerinventory, int j) {
/*  37 */     this(containers, i, playerinventory, new InventorySubcontainer(9 * j), j);
/*     */   }
/*     */   
/*     */   public static ContainerChest a(int i, PlayerInventory playerinventory) {
/*  41 */     return new ContainerChest(Containers.GENERIC_9X1, i, playerinventory, 1);
/*     */   }
/*     */   
/*     */   public static ContainerChest b(int i, PlayerInventory playerinventory) {
/*  45 */     return new ContainerChest(Containers.GENERIC_9X2, i, playerinventory, 2);
/*     */   }
/*     */   
/*     */   public static ContainerChest c(int i, PlayerInventory playerinventory) {
/*  49 */     return new ContainerChest(Containers.GENERIC_9X3, i, playerinventory, 3);
/*     */   }
/*     */   
/*     */   public static ContainerChest d(int i, PlayerInventory playerinventory) {
/*  53 */     return new ContainerChest(Containers.GENERIC_9X4, i, playerinventory, 4);
/*     */   }
/*     */   
/*     */   public static ContainerChest e(int i, PlayerInventory playerinventory) {
/*  57 */     return new ContainerChest(Containers.GENERIC_9X5, i, playerinventory, 5);
/*     */   }
/*     */   
/*     */   public static ContainerChest f(int i, PlayerInventory playerinventory) {
/*  61 */     return new ContainerChest(Containers.GENERIC_9X6, i, playerinventory, 6);
/*     */   }
/*     */   
/*     */   public static ContainerChest a(int i, PlayerInventory playerinventory, IInventory iinventory) {
/*  65 */     return new ContainerChest(Containers.GENERIC_9X3, i, playerinventory, iinventory, 3);
/*     */   }
/*     */   
/*     */   public static ContainerChest b(int i, PlayerInventory playerinventory, IInventory iinventory) {
/*  69 */     return new ContainerChest(Containers.GENERIC_9X6, i, playerinventory, iinventory, 6);
/*     */   }
/*     */   
/*     */   public ContainerChest(Containers<?> containers, int i, PlayerInventory playerinventory, IInventory iinventory, int j) {
/*  73 */     super(containers, i);
/*  74 */     a(iinventory, j * 9);
/*  75 */     this.container = iinventory;
/*  76 */     this.d = j;
/*  77 */     iinventory.startOpen(playerinventory.player);
/*  78 */     int k = (this.d - 4) * 18;
/*     */ 
/*     */     
/*  81 */     this.player = playerinventory;
/*     */ 
/*     */     
/*     */     int l;
/*     */ 
/*     */     
/*  87 */     for (l = 0; l < this.d; l++) {
/*  88 */       for (int i1 = 0; i1 < 9; i1++) {
/*  89 */         a(new Slot(iinventory, i1 + l * 9, 8 + i1 * 18, 18 + l * 18));
/*     */       }
/*     */     } 
/*     */     
/*  93 */     for (l = 0; l < 3; l++) {
/*  94 */       for (int i1 = 0; i1 < 9; i1++) {
/*  95 */         a(new Slot(playerinventory, i1 + l * 9 + 9, 8 + i1 * 18, 103 + l * 18 + k));
/*     */       }
/*     */     } 
/*     */     
/*  99 */     for (l = 0; l < 9; l++) {
/* 100 */       a(new Slot(playerinventory, l, 8 + l * 18, 161 + k));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 107 */     if (!this.checkReachable) return true; 
/* 108 */     return this.container.a(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 113 */     ItemStack itemstack = ItemStack.b;
/* 114 */     Slot slot = this.slots.get(i);
/*     */     
/* 116 */     if (slot != null && slot.hasItem()) {
/* 117 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 119 */       itemstack = itemstack1.cloneItemStack();
/* 120 */       if (i < this.d * 9) {
/* 121 */         if (!a(itemstack1, this.d * 9, this.slots.size(), true)) {
/* 122 */           return ItemStack.b;
/*     */         }
/* 124 */       } else if (!a(itemstack1, 0, this.d * 9, false)) {
/* 125 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 128 */       if (itemstack1.isEmpty()) {
/* 129 */         slot.set(ItemStack.b);
/*     */       } else {
/* 131 */         slot.d();
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 140 */     super.b(entityhuman);
/* 141 */     this.container.closeContainer(entityhuman);
/*     */   }
/*     */   
/*     */   public IInventory e() {
/* 145 */     return this.container;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */