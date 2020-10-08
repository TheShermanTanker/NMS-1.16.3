/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerDispenser
/*     */   extends Container {
/*     */   public final IInventory items;
/*  12 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerDispenser(int i, PlayerInventory playerinventory) {
/*  17 */     this(i, playerinventory, new InventorySubcontainer(9));
/*     */   }
/*     */   
/*     */   public ContainerDispenser(int i, PlayerInventory playerinventory, IInventory iinventory) {
/*  21 */     super(Containers.GENERIC_3X3, i);
/*     */     
/*  23 */     this.player = playerinventory;
/*     */ 
/*     */     
/*  26 */     a(iinventory, 9);
/*  27 */     this.items = iinventory;
/*  28 */     iinventory.startOpen(playerinventory.player);
/*     */ 
/*     */     
/*     */     int j;
/*     */     
/*  33 */     for (j = 0; j < 3; j++) {
/*  34 */       for (int k = 0; k < 3; k++) {
/*  35 */         a(new Slot(iinventory, k + j * 3, 62 + k * 18, 17 + j * 18));
/*     */       }
/*     */     } 
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
/*  54 */     return this.items.a(entityhuman);
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
/*  66 */       if (i < 9) {
/*  67 */         if (!a(itemstack1, 9, 45, true)) {
/*  68 */           return ItemStack.b;
/*     */         }
/*  70 */       } else if (!a(itemstack1, 0, 9, false)) {
/*  71 */         return ItemStack.b;
/*     */       } 
/*     */       
/*  74 */       if (itemstack1.isEmpty()) {
/*  75 */         slot.set(ItemStack.b);
/*     */       } else {
/*  77 */         slot.d();
/*     */       } 
/*     */       
/*  80 */       if (itemstack1.getCount() == itemstack.getCount()) {
/*  81 */         return ItemStack.b;
/*     */       }
/*     */       
/*  84 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/*  87 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/*  92 */     super.b(entityhuman);
/*  93 */     this.items.closeContainer(entityhuman);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  99 */     if (this.bukkitEntity != null) {
/* 100 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 103 */     CraftInventory inventory = new CraftInventory(this.items);
/* 104 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 105 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */