/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentTridentRiptide
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentTridentRiptide(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.TRIDENT, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 10 + var0 * 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment var0) {
/* 27 */     return (super.a(var0) && var0 != Enchantments.LOYALTY && var0 != Enchantments.CHANNELING);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentTridentRiptide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */