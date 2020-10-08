/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class EnchantmentTridentImpaling
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentTridentImpaling(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  8 */     super(var0, EnchantmentSlotType.TRIDENT, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 13 */     return 1 + (var0 - 1) * 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 18 */     return a(var0) + 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 23 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(int var0, EnumMonsterType var1) {
/* 28 */     if (var1 == EnumMonsterType.WATER_MOB) {
/* 29 */       return var0 * 2.5F;
/*    */     }
/* 31 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentTridentImpaling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */