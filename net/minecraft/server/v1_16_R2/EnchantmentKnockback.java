/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentKnockback
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentKnockback(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.WEAPON, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 5 + 20 * (var0 - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return super.a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentKnockback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */