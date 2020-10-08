/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentPiercing
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentPiercing(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.CROSSBOW, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 1 + (var0 - 1) * 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment var0) {
/* 27 */     return (super.a(var0) && var0 != Enchantments.MULTISHOT);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentPiercing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */