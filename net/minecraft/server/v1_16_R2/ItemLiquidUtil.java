/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemLiquidUtil
/*    */ {
/*    */   public static InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 16 */     var1.c(var2);
/* 17 */     return InteractionResultWrapper.consume(var1.b(var2));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ItemStack a(ItemStack var0, EntityHuman var1, ItemStack var2, boolean var3) {
/* 26 */     boolean var4 = var1.abilities.canInstantlyBuild;
/* 27 */     if (var3 && var4) {
/* 28 */       if (!var1.inventory.h(var2)) {
/* 29 */         var1.inventory.pickup(var2);
/*    */       }
/* 31 */       return var0;
/*    */     } 
/*    */     
/* 34 */     if (!var4) {
/* 35 */       var0.subtract(1);
/*    */     }
/* 37 */     if (var0.isEmpty()) {
/* 38 */       return var2;
/*    */     }
/* 40 */     if (!var1.inventory.pickup(var2)) {
/* 41 */       var1.drop(var2, false);
/*    */     }
/* 43 */     return var0;
/*    */   }
/*    */   
/*    */   public static ItemStack a(ItemStack var0, EntityHuman var1, ItemStack var2) {
/* 47 */     return a(var0, var1, var2, true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemLiquidUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */