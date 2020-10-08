/*    */ package org.bukkit.enchantments;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentOffer
/*    */ {
/*    */   private Enchantment enchantment;
/*    */   private int enchantmentLevel;
/*    */   private int cost;
/*    */   
/*    */   public EnchantmentOffer(@NotNull Enchantment enchantment, int enchantmentLevel, int cost) {
/* 16 */     this.enchantment = enchantment;
/* 17 */     this.enchantmentLevel = enchantmentLevel;
/* 18 */     this.cost = cost;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Enchantment getEnchantment() {
/* 28 */     return this.enchantment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnchantment(@NotNull Enchantment enchantment) {
/* 37 */     Validate.notNull(enchantment, "The enchantment may not be null!");
/*    */     
/* 39 */     this.enchantment = enchantment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEnchantmentLevel() {
/* 48 */     return this.enchantmentLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnchantmentLevel(int enchantmentLevel) {
/* 57 */     Validate.isTrue((enchantmentLevel > 0), "The enchantment level must be greater than 0!");
/*    */     
/* 59 */     this.enchantmentLevel = enchantmentLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost() {
/* 69 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCost(int cost) {
/* 79 */     Validate.isTrue((cost > 0), "The cost must be greater than 0!");
/*    */     
/* 81 */     this.cost = cost;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\enchantments\EnchantmentOffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */