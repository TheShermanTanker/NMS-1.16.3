/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentFire
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentFire(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.WEAPON, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 10 + 20 * (var0 - 1);
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */