/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ 
/*    */ public class ContainerHopper
/*    */   extends Container
/*    */ {
/*    */   private final IInventory hopper;
/* 13 */   private CraftInventoryView bukkitEntity = null;
/*    */   
/*    */   private PlayerInventory player;
/*    */   
/*    */   public CraftInventoryView getBukkitView() {
/* 18 */     if (this.bukkitEntity != null) {
/* 19 */       return this.bukkitEntity;
/*    */     }
/*    */     
/* 22 */     CraftInventory inventory = new CraftInventory(this.hopper);
/* 23 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 24 */     return this.bukkitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public ContainerHopper(int i, PlayerInventory playerinventory) {
/* 29 */     this(i, playerinventory, new InventorySubcontainer(5));
/*    */   }
/*    */   
/*    */   public ContainerHopper(int i, PlayerInventory playerinventory, IInventory iinventory) {
/* 33 */     super(Containers.HOPPER, i);
/* 34 */     this.hopper = iinventory;
/* 35 */     this.player = playerinventory;
/* 36 */     a(iinventory, 5);
/* 37 */     iinventory.startOpen(playerinventory.player);
/* 38 */     boolean flag = true;
/*    */     
/*    */     int j;
/*    */     
/* 42 */     for (j = 0; j < 5; j++) {
/* 43 */       a(new Slot(iinventory, j, 44 + j * 18, 20));
/*    */     }
/*    */     
/* 46 */     for (j = 0; j < 3; j++) {
/* 47 */       for (int k = 0; k < 9; k++) {
/* 48 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, j * 18 + 51));
/*    */       }
/*    */     } 
/*    */     
/* 52 */     for (j = 0; j < 9; j++) {
/* 53 */       a(new Slot(playerinventory, j, 8 + j * 18, 109));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(EntityHuman entityhuman) {
/* 60 */     if (!this.checkReachable) return true; 
/* 61 */     return this.hopper.a(entityhuman);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 66 */     ItemStack itemstack = ItemStack.b;
/* 67 */     Slot slot = this.slots.get(i);
/*    */     
/* 69 */     if (slot != null && slot.hasItem()) {
/* 70 */       ItemStack itemstack1 = slot.getItem();
/*    */       
/* 72 */       itemstack = itemstack1.cloneItemStack();
/* 73 */       if (i < this.hopper.getSize()) {
/* 74 */         if (!a(itemstack1, this.hopper.getSize(), this.slots.size(), true)) {
/* 75 */           return ItemStack.b;
/*    */         }
/* 77 */       } else if (!a(itemstack1, 0, this.hopper.getSize(), false)) {
/* 78 */         return ItemStack.b;
/*    */       } 
/*    */       
/* 81 */       if (itemstack1.isEmpty()) {
/* 82 */         slot.set(ItemStack.b);
/*    */       } else {
/* 84 */         slot.d();
/*    */       } 
/*    */     } 
/*    */     
/* 88 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityHuman entityhuman) {
/* 93 */     super.b(entityhuman);
/* 94 */     this.hopper.closeContainer(entityhuman);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */