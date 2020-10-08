/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctions
/*    */ {
/*    */   public static final BiFunction<ItemStack, LootTableInfo, ItemStack> a;
/*    */   
/*    */   static {
/* 13 */     a = ((var0, var1) -> var0);
/*    */   }
/* 15 */   public static final LootItemFunctionType b = a("set_count", new LootItemFunctionSetCount.a());
/* 16 */   public static final LootItemFunctionType c = a("enchant_with_levels", new LootEnchantLevel.b());
/* 17 */   public static final LootItemFunctionType d = a("enchant_randomly", new LootItemFunctionEnchant.b());
/* 18 */   public static final LootItemFunctionType e = a("set_nbt", new LootItemFunctionSetTag.a());
/* 19 */   public static final LootItemFunctionType f = a("furnace_smelt", new LootItemFunctionSmelt.a());
/* 20 */   public static final LootItemFunctionType g = a("looting_enchant", new LootEnchantFunction.b());
/* 21 */   public static final LootItemFunctionType h = a("set_damage", new LootItemFunctionSetDamage.a());
/* 22 */   public static final LootItemFunctionType i = a("set_attributes", new LootItemFunctionSetAttribute.d());
/* 23 */   public static final LootItemFunctionType j = a("set_name", new LootItemFunctionSetName.a());
/* 24 */   public static final LootItemFunctionType k = a("exploration_map", new LootItemFunctionExplorationMap.b());
/* 25 */   public static final LootItemFunctionType l = a("set_stew_effect", new LootItemFunctionSetStewEffect.b());
/* 26 */   public static final LootItemFunctionType m = a("copy_name", new LootItemFunctionCopyName.b());
/* 27 */   public static final LootItemFunctionType n = a("set_contents", new LootItemFunctionSetContents.b());
/* 28 */   public static final LootItemFunctionType o = a("limit_count", new LootItemFunctionLimitCount.a());
/* 29 */   public static final LootItemFunctionType p = a("apply_bonus", new LootItemFunctionApplyBonus.e());
/* 30 */   public static final LootItemFunctionType q = a("set_loot_table", new LootItemFunctionSetTable.a());
/* 31 */   public static final LootItemFunctionType r = a("explosion_decay", new LootItemFunctionExplosionDecay.a());
/* 32 */   public static final LootItemFunctionType s = a("set_lore", new LootItemFunctionSetLore.b());
/* 33 */   public static final LootItemFunctionType t = a("fill_player_head", new LootItemFunctionFillPlayerHead.a());
/* 34 */   public static final LootItemFunctionType u = a("copy_nbt", new LootItemFunctionCopyNBT.e());
/* 35 */   public static final LootItemFunctionType v = a("copy_state", new LootItemFunctionCopyState.b());
/*    */   
/*    */   private static LootItemFunctionType a(String var0, LootSerializer<? extends LootItemFunction> var1) {
/* 38 */     return IRegistry.<LootItemFunctionType, LootItemFunctionType>a(IRegistry.ap, new MinecraftKey(var0), new LootItemFunctionType(var1));
/*    */   }
/*    */   
/*    */   public static Object a() {
/* 42 */     return JsonRegistry.a(IRegistry.ap, "function", "function", LootItemFunction::b).a();
/*    */   } public static BiFunction<ItemStack, LootTableInfo, ItemStack> a(BiFunction<ItemStack, LootTableInfo, ItemStack>[] var0) {
/*    */     BiFunction<ItemStack, LootTableInfo, ItemStack> var1;
/*    */     BiFunction<ItemStack, LootTableInfo, ItemStack> var2;
/* 46 */     switch (var0.length) {
/*    */       case 0:
/* 48 */         return a;
/*    */       case 1:
/* 50 */         return var0[0];
/*    */       case 2:
/* 52 */         var1 = var0[0];
/* 53 */         var2 = var0[1];
/* 54 */         return (var2, var3) -> (ItemStack)var0.apply(var1.apply(var2, var3), var3);
/*    */     } 
/*    */     
/* 57 */     return (var1, var2) -> {
/*    */         for (BiFunction<ItemStack, LootTableInfo, ItemStack> var6 : var0)
/*    */           var1 = var6.apply(var1, var2); 
/*    */         return var1;
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */