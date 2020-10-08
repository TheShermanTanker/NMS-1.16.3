/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentMending
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentMending(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.BREAKABLE, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return var0 * 25;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTreasure() {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 27 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentMending.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */