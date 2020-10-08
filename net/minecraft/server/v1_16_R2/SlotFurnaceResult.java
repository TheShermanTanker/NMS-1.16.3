/*    */ package net.minecraft.server.v1_16_R2;public class SlotFurnaceResult extends Slot {
/*    */   private final EntityHuman a;
/*    */   
/*    */   public final EntityHuman getPlayer() {
/*  5 */     return this.a;
/*    */   }
/*    */   private int b;
/*    */   public SlotFurnaceResult(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
/*  9 */     super(iinventory, i, j, k);
/* 10 */     this.a = entityhuman;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAllowed(ItemStack itemstack) {
/* 15 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(int i) {
/* 20 */     if (hasItem()) {
/* 21 */       this.b += Math.min(i, getItem().getCount());
/*    */     }
/*    */     
/* 24 */     return super.a(i);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/* 29 */     c(itemstack);
/* 30 */     super.a(entityhuman, itemstack);
/* 31 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ItemStack itemstack, int i) {
/* 36 */     this.b += i;
/* 37 */     c(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(ItemStack itemstack) {
/* 42 */     itemstack.a(this.a.world, this.a, this.b);
/* 43 */     if (!this.a.world.isClientSide && this.inventory instanceof TileEntityFurnace) {
/* 44 */       ((TileEntityFurnace)this.inventory).d(this.a, itemstack, this.b);
/*    */     }
/*    */     
/* 47 */     this.b = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SlotFurnaceResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */