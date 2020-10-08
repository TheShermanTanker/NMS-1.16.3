/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentSoulSpeed
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentSoulSpeed(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.ARMOR_FEET, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return var0 * 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return a(var0) + 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTreasure() {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean h() {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean i() {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 37 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentSoulSpeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */