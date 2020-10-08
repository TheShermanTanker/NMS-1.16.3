/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class WeightedRandomEnchant
/*    */   extends WeightedRandom.WeightedRandomChoice
/*    */ {
/*    */   public final Enchantment enchantment;
/*    */   public final int level;
/*    */   
/*    */   public WeightedRandomEnchant(Enchantment var0, int var1) {
/* 10 */     super(var0.d().a());
/* 11 */     this.enchantment = var0;
/* 12 */     this.level = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WeightedRandomEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */