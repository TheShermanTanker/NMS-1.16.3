/*    */ package org.bukkit.enchantments;
/*    */ 
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EnchantmentWrapper
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentWrapper(@NotNull String name) {
/* 12 */     super(NamespacedKey.minecraft(name));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Enchantment getEnchantment() {
/* 22 */     return Enchantment.getByKey(getKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 27 */     return getEnchantment().getMaxLevel();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStartLevel() {
/* 32 */     return getEnchantment().getStartLevel();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EnchantmentTarget getItemTarget() {
/* 38 */     return getEnchantment().getItemTarget();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchantItem(@NotNull ItemStack item) {
/* 43 */     return getEnchantment().canEnchantItem(item);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getName() {
/* 49 */     return getEnchantment().getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTreasure() {
/* 54 */     return getEnchantment().isTreasure();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCursed() {
/* 59 */     return getEnchantment().isCursed();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean conflictsWith(@NotNull Enchantment other) {
/* 64 */     return getEnchantment().conflictsWith(other);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\enchantments\EnchantmentWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */