/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryBrewer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerBrewingStand
/*     */   extends Container
/*     */ {
/*     */   private final IInventory brewingStand;
/*     */   private final IContainerProperties d;
/*     */   private final Slot e;
/*  15 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerBrewingStand(int i, PlayerInventory playerinventory) {
/*  20 */     this(i, playerinventory, new InventorySubcontainer(5), new ContainerProperties(2));
/*     */   }
/*     */   
/*     */   public ContainerBrewingStand(int i, PlayerInventory playerinventory, IInventory iinventory, IContainerProperties icontainerproperties) {
/*  24 */     super(Containers.BREWING_STAND, i);
/*  25 */     this.player = playerinventory;
/*  26 */     a(iinventory, 5);
/*  27 */     a(icontainerproperties, 2);
/*  28 */     this.brewingStand = iinventory;
/*  29 */     this.d = icontainerproperties;
/*  30 */     a(new SlotPotionBottle(iinventory, 0, 56, 51));
/*  31 */     a(new SlotPotionBottle(iinventory, 1, 79, 58));
/*  32 */     a(new SlotPotionBottle(iinventory, 2, 102, 51));
/*  33 */     this.e = a(new SlotBrewing(iinventory, 3, 79, 17));
/*  34 */     a(new a(iinventory, 4, 17, 17));
/*  35 */     a(icontainerproperties);
/*     */     
/*     */     int j;
/*     */     
/*  39 */     for (j = 0; j < 3; j++) {
/*  40 */       for (int k = 0; k < 9; k++) {
/*  41 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  45 */     for (j = 0; j < 9; j++) {
/*  46 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/*  53 */     if (!this.checkReachable) return true; 
/*  54 */     return this.brewingStand.a(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/*  59 */     ItemStack itemstack = ItemStack.b;
/*  60 */     Slot slot = this.slots.get(i);
/*     */     
/*  62 */     if (slot != null && slot.hasItem()) {
/*  63 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/*  65 */       itemstack = itemstack1.cloneItemStack();
/*  66 */       if ((i < 0 || i > 2) && i != 3 && i != 4) {
/*  67 */         if (a.a_(itemstack)) {
/*  68 */           if (a(itemstack1, 4, 5, false) || (this.e.isAllowed(itemstack1) && !a(itemstack1, 3, 4, false))) {
/*  69 */             return ItemStack.b;
/*     */           }
/*  71 */         } else if (this.e.isAllowed(itemstack1)) {
/*  72 */           if (!a(itemstack1, 3, 4, false)) {
/*  73 */             return ItemStack.b;
/*     */           }
/*  75 */         } else if (SlotPotionBottle.b_(itemstack) && itemstack.getCount() == 1) {
/*  76 */           if (!a(itemstack1, 0, 3, false)) {
/*  77 */             return ItemStack.b;
/*     */           }
/*  79 */         } else if (i >= 5 && i < 32) {
/*  80 */           if (!a(itemstack1, 32, 41, false)) {
/*  81 */             return ItemStack.b;
/*     */           }
/*  83 */         } else if (i >= 32 && i < 41) {
/*  84 */           if (!a(itemstack1, 5, 32, false)) {
/*  85 */             return ItemStack.b;
/*     */           }
/*  87 */         } else if (!a(itemstack1, 5, 41, false)) {
/*  88 */           return ItemStack.b;
/*     */         } 
/*     */       } else {
/*  91 */         if (!a(itemstack1, 5, 41, true)) {
/*  92 */           return ItemStack.b;
/*     */         }
/*     */         
/*  95 */         slot.a(itemstack1, itemstack);
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
/*     */   static class a
/*     */     extends Slot {
/*     */     public a(IInventory iinventory, int i, int j, int k) {
/* 117 */       super(iinventory, i, j, k);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 122 */       return a_(itemstack);
/*     */     }
/*     */     
/*     */     public static boolean a_(ItemStack itemstack) {
/* 126 */       return (itemstack.getItem() == Items.BLAZE_POWDER);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 131 */       return 64;
/*     */     }
/*     */   }
/*     */   
/*     */   static class SlotBrewing
/*     */     extends Slot {
/*     */     public SlotBrewing(IInventory iinventory, int i, int j, int k) {
/* 138 */       super(iinventory, i, j, k);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 143 */       return PotionBrewer.a(itemstack);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 148 */       return 64;
/*     */     }
/*     */   }
/*     */   
/*     */   static class SlotPotionBottle
/*     */     extends Slot {
/*     */     public SlotPotionBottle(IInventory iinventory, int i, int j, int k) {
/* 155 */       super(iinventory, i, j, k);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAllowed(ItemStack itemstack) {
/* 160 */       return b_(itemstack);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 165 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/* 170 */       PotionRegistry potionregistry = PotionUtil.d(itemstack);
/*     */       
/* 172 */       if (entityhuman instanceof EntityPlayer) {
/* 173 */         CriterionTriggers.k.a((EntityPlayer)entityhuman, potionregistry);
/*     */       }
/*     */       
/* 176 */       super.a(entityhuman, itemstack);
/* 177 */       return itemstack;
/*     */     }
/*     */     
/*     */     public static boolean b_(ItemStack itemstack) {
/* 181 */       Item item = itemstack.getItem();
/*     */       
/* 183 */       return (item == Items.POTION || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION || item == Items.GLASS_BOTTLE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 190 */     if (this.bukkitEntity != null) {
/* 191 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 194 */     CraftInventoryBrewer inventory = new CraftInventoryBrewer(this.brewingStand);
/* 195 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 196 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */