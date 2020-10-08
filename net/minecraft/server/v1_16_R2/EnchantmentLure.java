/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentLure
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentLure(Enchantment.Rarity var0, EnchantmentSlotType var1, EnumItemSlot... var2) {
/*  7 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 15 + (var0 - 1) * 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return super.a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentLure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */