/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentDigging
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentDigging(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  9 */     super(var0, EnchantmentSlotType.DIGGER, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 14 */     return 1 + 10 * (var0 - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 19 */     return super.a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 24 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack var0) {
/* 29 */     if (var0.getItem() == Items.SHEARS) {
/* 30 */       return true;
/*    */     }
/* 32 */     return super.canEnchant(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentDigging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */