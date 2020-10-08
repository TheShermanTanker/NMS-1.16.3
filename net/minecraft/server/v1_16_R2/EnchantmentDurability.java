/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentDurability
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentDurability(Enchantment.Rarity var0, EnumItemSlot... var1) {
/* 11 */     super(var0, EnchantmentSlotType.BREAKABLE, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 16 */     return 5 + (var0 - 1) * 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 21 */     return super.a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 26 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack var0) {
/* 31 */     if (var0.e()) {
/* 32 */       return true;
/*    */     }
/* 34 */     return super.canEnchant(var0);
/*    */   }
/*    */   
/*    */   public static boolean a(ItemStack var0, int var1, Random var2) {
/* 38 */     if (var0.getItem() instanceof ItemArmor && var2.nextFloat() < 0.6F) {
/* 39 */       return false;
/*    */     }
/* 41 */     return (var2.nextInt(var1 + 1) > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentDurability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */