/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public abstract class ContainerAnvilAbstract extends Container {
/*   7 */   protected final InventoryCraftResult resultInventory = new InventoryCraftResult();
/*   8 */   protected final IInventory repairInventory = new InventorySubcontainer(2)
/*     */     {
/*     */       public void update() {
/*  11 */         super.update();
/*  12 */         ContainerAnvilAbstract.this.a(this);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   protected final ContainerAccess containerAccess;
/*     */ 
/*     */   
/*     */   protected final EntityHuman player;
/*     */ 
/*     */   
/*     */   public ContainerAnvilAbstract(@Nullable Containers<?> containers, int i, PlayerInventory playerinventory, ContainerAccess containeraccess) {
/*  25 */     super(containers, i);
/*  26 */     this.containerAccess = containeraccess;
/*  27 */     this.player = playerinventory.player;
/*  28 */     a(new Slot(this.repairInventory, 0, 27, 47));
/*  29 */     a(new Slot(this.repairInventory, 1, 76, 47));
/*  30 */     a(new Slot(this.resultInventory, 2, 134, 47)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  33 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean isAllowed(EntityHuman entityhuman) {
/*  38 */             return ContainerAnvilAbstract.this.b(entityhuman, hasItem());
/*     */           }
/*     */ 
/*     */           
/*     */           public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/*  43 */             return ContainerAnvilAbstract.this.a(entityhuman, itemstack);
/*     */           }
/*     */         });
/*     */     
/*     */     int j;
/*     */     
/*  49 */     for (j = 0; j < 3; j++) {
/*  50 */       for (int k = 0; k < 9; k++) {
/*  51 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  55 */     for (j = 0; j < 9; j++) {
/*  56 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/*  65 */     super.a(iinventory);
/*  66 */     if (iinventory == this.repairInventory) {
/*  67 */       e();
/*  68 */       CraftEventFactory.callPrepareResultEvent(this, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/*  75 */     super.b(entityhuman);
/*  76 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, world, this.repairInventory));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/*  83 */     if (!this.checkReachable) return true; 
/*  84 */     return ((Boolean)this.containerAccess.<Boolean>a((world, blockposition) -> Boolean.valueOf(!a(world.getType(blockposition)) ? false : ((entityhuman.h(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D) <= 64.0D))), 
/*     */         
/*  86 */         Boolean.valueOf(true))).booleanValue();
/*     */   }
/*     */   
/*     */   protected boolean a(ItemStack itemstack) {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/*  95 */     ItemStack itemstack = ItemStack.b;
/*  96 */     Slot slot = this.slots.get(i);
/*     */     
/*  98 */     if (slot != null && slot.hasItem()) {
/*  99 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 101 */       itemstack = itemstack1.cloneItemStack();
/* 102 */       if (i == 2) {
/* 103 */         if (!a(itemstack1, 3, 39, true)) {
/* 104 */           return ItemStack.b;
/*     */         }
/*     */         
/* 107 */         slot.a(itemstack1, itemstack);
/* 108 */       } else if (i != 0 && i != 1) {
/* 109 */         if (i >= 3 && i < 39) {
/* 110 */           int j = a(itemstack) ? 1 : 0;
/*     */           
/* 112 */           if (!a(itemstack1, j, 2, false)) {
/* 113 */             return ItemStack.b;
/*     */           }
/*     */         } 
/* 116 */       } else if (!a(itemstack1, 3, 39, false)) {
/* 117 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 120 */       if (itemstack1.isEmpty()) {
/* 121 */         slot.set(ItemStack.b);
/*     */       } else {
/* 123 */         slot.d();
/*     */       } 
/*     */       
/* 126 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 127 */         return ItemStack.b;
/*     */       }
/*     */       
/* 130 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 133 */     return itemstack;
/*     */   }
/*     */   
/*     */   protected abstract boolean b(EntityHuman paramEntityHuman, boolean paramBoolean);
/*     */   
/*     */   protected abstract ItemStack a(EntityHuman paramEntityHuman, ItemStack paramItemStack);
/*     */   
/*     */   protected abstract boolean a(IBlockData paramIBlockData);
/*     */   
/*     */   public abstract void e();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerAnvilAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */