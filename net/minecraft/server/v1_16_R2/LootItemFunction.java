/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface LootItemFunction
/*    */   extends LootItemUser, BiFunction<ItemStack, LootTableInfo, ItemStack>
/*    */ {
/*    */   LootItemFunctionType b();
/*    */   
/*    */   static Consumer<ItemStack> a(BiFunction<ItemStack, LootTableInfo, ItemStack> var0, Consumer<ItemStack> var1, LootTableInfo var2) {
/* 14 */     return var3 -> var0.accept(var1.apply(var3, var2));
/*    */   }
/*    */   
/*    */   public static interface a {
/*    */     LootItemFunction b();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */