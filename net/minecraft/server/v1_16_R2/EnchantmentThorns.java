/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentThorns
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentThorns(Enchantment.Rarity var0, EnumItemSlot... var1) {
/* 17 */     super(var0, EnchantmentSlotType.ARMOR_CHEST, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 22 */     return 10 + 20 * (var0 - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 27 */     return super.a(var0) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 32 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack var0) {
/* 37 */     if (var0.getItem() instanceof ItemArmor) {
/* 38 */       return true;
/*    */     }
/* 40 */     return super.canEnchant(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityLiving var0, Entity var1, int var2) {
/* 45 */     Random var3 = var0.getRandom();
/* 46 */     Map.Entry<EnumItemSlot, ItemStack> var4 = EnchantmentManager.b(Enchantments.THORNS, var0);
/*    */     
/* 48 */     if (a(var2, var3)) {
/* 49 */       if (var1 != null) {
/* 50 */         var1.damageEntity(DamageSource.a(var0), b(var2, var3));
/*    */       }
/*    */       
/* 53 */       if (var4 != null) {
/* 54 */         ((ItemStack)var4.getValue()).damage(2, var0, var1 -> var1.broadcastItemBreak((EnumItemSlot)var0.getKey()));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean a(int var0, Random var1) {
/* 60 */     if (var0 <= 0) {
/* 61 */       return false;
/*    */     }
/* 63 */     return (var1.nextFloat() < 0.15F * var0);
/*    */   }
/*    */   
/*    */   public static int b(int var0, Random var1) {
/* 67 */     if (var0 > 10) {
/* 68 */       return var0 - 10;
/*    */     }
/* 70 */     return 1 + var1.nextInt(4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentThorns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */