/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentTridentChanneling
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentTridentChanneling(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.TRIDENT, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 25;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment var0) {
/* 27 */     return super.a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentTridentChanneling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */