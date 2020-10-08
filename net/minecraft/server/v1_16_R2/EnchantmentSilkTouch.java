/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class EnchantmentSilkTouch
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentSilkTouch(Enchantment.Rarity var0, EnumItemSlot... var1) {
/*  7 */     super(var0, EnchantmentSlotType.DIGGER, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 12 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 17 */     return super.a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment var0) {
/* 27 */     return (super.a(var0) && var0 != Enchantments.LOOT_BONUS_BLOCKS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentSilkTouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */