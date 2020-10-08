/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentDepthStrider
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentDepthStrider(Enchantment.Rarity var0, EnumItemSlot... var1) {
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
/*    */   public int getMaxLevel() {
/* 22 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment var0) {
/* 27 */     return (super.a(var0) && var0 != Enchantments.FROST_WALKER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentDepthStrider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */