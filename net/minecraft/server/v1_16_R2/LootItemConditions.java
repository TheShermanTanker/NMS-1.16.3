/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditions
/*    */ {
/* 11 */   public static final LootItemConditionType a = a("inverted", new LootItemConditionInverted.a());
/* 12 */   public static final LootItemConditionType b = a("alternative", new LootItemConditionAlternative.b());
/* 13 */   public static final LootItemConditionType c = a("random_chance", new LootItemConditionRandomChance.a());
/* 14 */   public static final LootItemConditionType d = a("random_chance_with_looting", new LootItemConditionRandomChanceWithLooting.a());
/* 15 */   public static final LootItemConditionType e = a("entity_properties", new LootItemConditionEntityProperty.a());
/* 16 */   public static final LootItemConditionType f = a("killed_by_player", new LootItemConditionKilledByPlayer.a());
/* 17 */   public static final LootItemConditionType g = a("entity_scores", new LootItemConditionEntityScore.b());
/* 18 */   public static final LootItemConditionType h = a("block_state_property", new LootItemConditionBlockStateProperty.b());
/* 19 */   public static final LootItemConditionType i = a("match_tool", new LootItemConditionMatchTool.a());
/* 20 */   public static final LootItemConditionType j = a("table_bonus", new LootItemConditionTableBonus.a());
/* 21 */   public static final LootItemConditionType k = a("survives_explosion", new LootItemConditionSurvivesExplosion.a());
/* 22 */   public static final LootItemConditionType l = a("damage_source_properties", new LootItemConditionDamageSourceProperties.a());
/* 23 */   public static final LootItemConditionType m = a("location_check", new LootItemConditionLocationCheck.a());
/* 24 */   public static final LootItemConditionType n = a("weather_check", new LootItemConditionWeatherCheck.b());
/* 25 */   public static final LootItemConditionType o = a("reference", new LootItemConditionReference.a());
/* 26 */   public static final LootItemConditionType p = a("time_check", new LootItemConditionTimeCheck.b());
/*    */   
/*    */   private static LootItemConditionType a(String var0, LootSerializer<? extends LootItemCondition> var1) {
/* 29 */     return IRegistry.<LootItemConditionType, LootItemConditionType>a(IRegistry.aq, new MinecraftKey(var0), new LootItemConditionType(var1));
/*    */   }
/*    */   
/*    */   public static Object a() {
/* 33 */     return JsonRegistry.a(IRegistry.aq, "condition", "condition", LootItemCondition::b).a();
/*    */   }
/*    */   
/*    */   public static <T> Predicate<T> a(Predicate<T>[] var0) {
/* 37 */     switch (var0.length) {
/*    */       case 0:
/* 39 */         return var0 -> true;
/*    */       case 1:
/* 41 */         return var0[0];
/*    */       case 2:
/* 43 */         return var0[0].and(var0[1]);
/*    */     } 
/* 45 */     return var1 -> {
/*    */         for (Predicate<T> var5 : var0) {
/*    */           if (!var5.test((T)var1)) {
/*    */             return false;
/*    */           }
/*    */         } 
/*    */         return true;
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T> Predicate<T> b(Predicate<T>[] var0) {
/* 57 */     switch (var0.length) {
/*    */       case 0:
/* 59 */         return var0 -> false;
/*    */       case 1:
/* 61 */         return var0[0];
/*    */       case 2:
/* 63 */         return var0[0].or(var0[1]);
/*    */     } 
/*    */     
/* 66 */     return var1 -> {
/*    */         for (Predicate<T> var5 : var0) {
/*    */           if (var5.test((T)var1))
/*    */             return true; 
/*    */         } 
/*    */         return false;
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */