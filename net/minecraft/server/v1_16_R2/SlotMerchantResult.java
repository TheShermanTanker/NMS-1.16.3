/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotMerchantResult
/*    */   extends Slot
/*    */ {
/*    */   private final InventoryMerchant a;
/*    */   private final EntityHuman b;
/*    */   private int g;
/*    */   private final IMerchant h;
/*    */   
/*    */   public SlotMerchantResult(EntityHuman var0, IMerchant var1, InventoryMerchant var2, int var3, int var4, int var5) {
/* 16 */     super(var2, var3, var4, var5);
/* 17 */     this.b = var0;
/* 18 */     this.h = var1;
/* 19 */     this.a = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAllowed(ItemStack var0) {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(int var0) {
/* 29 */     if (hasItem()) {
/* 30 */       this.g += Math.min(var0, getItem().getCount());
/*    */     }
/* 32 */     return super.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ItemStack var0, int var1) {
/* 37 */     this.g += var1;
/* 38 */     c(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(ItemStack var0) {
/* 43 */     var0.a(this.b.world, this.b, this.g);
/* 44 */     this.g = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(EntityHuman var0, ItemStack var1) {
/* 49 */     c(var1);
/*    */     
/* 51 */     MerchantRecipe var2 = this.a.getRecipe();
/*    */     
/* 53 */     if (var2 != null) {
/* 54 */       ItemStack var3 = this.a.getItem(0);
/* 55 */       ItemStack var4 = this.a.getItem(1);
/*    */ 
/*    */       
/* 58 */       if (var2.b(var3, var4) || var2.b(var4, var3)) {
/* 59 */         this.h.a(var2);
/* 60 */         var0.a(StatisticList.TRADED_WITH_VILLAGER);
/*    */         
/* 62 */         this.a.setItem(0, var3);
/* 63 */         this.a.setItem(1, var4);
/*    */       } 
/* 65 */       this.h.setForcedExperience(this.h.getExperience() + var2.getXp());
/*    */     } 
/* 67 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SlotMerchantResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */