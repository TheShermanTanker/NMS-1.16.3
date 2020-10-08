/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerHorse
/*     */   extends Container
/*     */ {
/*     */   private final IInventory c;
/*     */   private final EntityHorseAbstract d;
/*     */   CraftInventoryView bukkitEntity;
/*     */   PlayerInventory player;
/*     */   
/*     */   public InventoryView getBukkitView() {
/*  19 */     if (this.bukkitEntity != null) {
/*  20 */       return (InventoryView)this.bukkitEntity;
/*     */     }
/*     */     
/*  23 */     return (InventoryView)(this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), this.c.getOwner().getInventory(), this));
/*     */   }
/*     */   
/*     */   public ContainerHorse(int i, PlayerInventory playerinventory, IInventory iinventory, final EntityHorseAbstract entityhorseabstract) {
/*  27 */     super((Containers)null, i);
/*  28 */     this.player = playerinventory;
/*     */     
/*  30 */     this.c = iinventory;
/*  31 */     this.d = entityhorseabstract;
/*  32 */     boolean flag = true;
/*     */     
/*  34 */     iinventory.startOpen(playerinventory.player);
/*  35 */     boolean flag1 = true;
/*     */     
/*  37 */     a(new Slot(iinventory, 0, 8, 18)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  40 */             return (itemstack.getItem() == Items.SADDLE && !hasItem() && entityhorseabstract.canSaddle());
/*     */           }
/*     */         });
/*  43 */     a(new Slot(iinventory, 1, 8, 36)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  46 */             return entityhorseabstract.l(itemstack);
/*     */           }
/*     */ 
/*     */           
/*     */           public int getMaxStackSize() {
/*  51 */             return 1;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  57 */     if (entityhorseabstract instanceof EntityHorseChestedAbstract && ((EntityHorseChestedAbstract)entityhorseabstract).isCarryingChest()) {
/*  58 */       for (int k = 0; k < 3; k++) {
/*  59 */         for (int m = 0; m < ((EntityHorseChestedAbstract)entityhorseabstract).eU(); m++) {
/*  60 */           a(new Slot(iinventory, 2 + m + k * ((EntityHorseChestedAbstract)entityhorseabstract).eU(), 80 + m * 18, 18 + k * 18));
/*     */         }
/*     */       } 
/*     */     }
/*     */     int j;
/*  65 */     for (j = 0; j < 3; j++) {
/*  66 */       for (int k = 0; k < 9; k++) {
/*  67 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + -18));
/*     */       }
/*     */     } 
/*     */     
/*  71 */     for (j = 0; j < 9; j++) {
/*  72 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/*  79 */     return (this.c.a(entityhuman) && this.d.isAlive() && this.d.valid && this.d.g(entityhuman) < 8.0F);
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
/*  91 */       int j = this.c.getSize();
/*     */       
/*  93 */       if (i < j) {
/*  94 */         if (!a(itemstack1, j, this.slots.size(), true)) {
/*  95 */           return ItemStack.b;
/*     */         }
/*  97 */       } else if (getSlot(1).isAllowed(itemstack1) && !getSlot(1).hasItem()) {
/*  98 */         if (!a(itemstack1, 1, 2, false)) {
/*  99 */           return ItemStack.b;
/*     */         }
/* 101 */       } else if (getSlot(0).isAllowed(itemstack1)) {
/* 102 */         if (!a(itemstack1, 0, 1, false)) {
/* 103 */           return ItemStack.b;
/*     */         }
/* 105 */       } else if (j <= 2 || !a(itemstack1, 2, j, false)) {
/* 106 */         int k = j + 27;
/* 107 */         int l = k + 9;
/*     */         
/* 109 */         if (i >= k && i < l) {
/* 110 */           if (!a(itemstack1, j, k, false)) {
/* 111 */             return ItemStack.b;
/*     */           }
/* 113 */         } else if (i >= j && i < k) {
/* 114 */           if (!a(itemstack1, k, l, false)) {
/* 115 */             return ItemStack.b;
/*     */           }
/* 117 */         } else if (!a(itemstack1, k, k, false)) {
/* 118 */           return ItemStack.b;
/*     */         } 
/*     */         
/* 121 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 124 */       if (itemstack1.isEmpty()) {
/* 125 */         slot.set(ItemStack.b);
/*     */       } else {
/* 127 */         slot.d();
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 136 */     super.b(entityhuman);
/* 137 */     this.c.closeContainer(entityhuman);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */