/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ 
/*    */ public class ContainerShulkerBox
/*    */   extends Container
/*    */ {
/*    */   private final IInventory c;
/*    */   private CraftInventoryView bukkitEntity;
/*    */   private PlayerInventory player;
/*    */   
/*    */   public CraftInventoryView getBukkitView() {
/* 17 */     if (this.bukkitEntity != null) {
/* 18 */       return this.bukkitEntity;
/*    */     }
/*    */     
/* 21 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)new CraftInventory(this.c), this);
/* 22 */     return this.bukkitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public ContainerShulkerBox(int i, PlayerInventory playerinventory) {
/* 27 */     this(i, playerinventory, new InventorySubcontainer(27));
/*    */   }
/*    */   
/*    */   public ContainerShulkerBox(int i, PlayerInventory playerinventory, IInventory iinventory) {
/* 31 */     super(Containers.SHULKER_BOX, i);
/* 32 */     a(iinventory, 27);
/* 33 */     this.c = iinventory;
/* 34 */     this.player = playerinventory;
/* 35 */     iinventory.startOpen(playerinventory.player);
/* 36 */     boolean flag = true;
/* 37 */     boolean flag1 = true;
/*    */ 
/*    */     
/*    */     int j;
/*    */     
/* 42 */     for (j = 0; j < 3; j++) {
/* 43 */       for (int k = 0; k < 9; k++) {
/* 44 */         a(new SlotShulkerBox(iinventory, k + j * 9, 8 + k * 18, 18 + j * 18));
/*    */       }
/*    */     } 
/*    */     
/* 48 */     for (j = 0; j < 3; j++) {
/* 49 */       for (int k = 0; k < 9; k++) {
/* 50 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*    */       }
/*    */     } 
/*    */     
/* 54 */     for (j = 0; j < 9; j++) {
/* 55 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(EntityHuman entityhuman) {
/* 62 */     return this.c.a(entityhuman);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 67 */     ItemStack itemstack = ItemStack.b;
/* 68 */     Slot slot = this.slots.get(i);
/*    */     
/* 70 */     if (slot != null && slot.hasItem()) {
/* 71 */       ItemStack itemstack1 = slot.getItem();
/*    */       
/* 73 */       itemstack = itemstack1.cloneItemStack();
/* 74 */       if (i < this.c.getSize()) {
/* 75 */         if (!a(itemstack1, this.c.getSize(), this.slots.size(), true)) {
/* 76 */           return ItemStack.b;
/*    */         }
/* 78 */       } else if (!a(itemstack1, 0, this.c.getSize(), false)) {
/* 79 */         return ItemStack.b;
/*    */       } 
/*    */       
/* 82 */       if (itemstack1.isEmpty()) {
/* 83 */         slot.set(ItemStack.b);
/*    */       } else {
/* 85 */         slot.d();
/*    */       } 
/*    */     } 
/*    */     
/* 89 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityHuman entityhuman) {
/* 94 */     super.b(entityhuman);
/* 95 */     this.c.closeContainer(entityhuman);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */