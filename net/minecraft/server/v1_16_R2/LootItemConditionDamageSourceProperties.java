/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionDamageSourceProperties
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final CriterionConditionDamageSource a;
/*    */   
/*    */   private LootItemConditionDamageSourceProperties(CriterionConditionDamageSource var0) {
/* 20 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 25 */     return LootItemConditions.l;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 30 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.ORIGIN, LootContextParameters.DAMAGE_SOURCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 35 */     DamageSource var1 = var0.<DamageSource>getContextParameter(LootContextParameters.DAMAGE_SOURCE);
/* 36 */     Vec3D var2 = var0.<Vec3D>getContextParameter(LootContextParameters.ORIGIN);
/*    */     
/* 38 */     return (var2 != null && var1 != null && this.a.a(var0.getWorld(), var2, var1));
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(CriterionConditionDamageSource.a var0) {
/* 42 */     return () -> new LootItemConditionDamageSourceProperties(var0.b());
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionDamageSourceProperties> {
/*    */     public void a(JsonObject var0, LootItemConditionDamageSourceProperties var1, JsonSerializationContext var2) {
/* 48 */       var0.add("predicate", LootItemConditionDamageSourceProperties.a(var1).a());
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionDamageSourceProperties a(JsonObject var0, JsonDeserializationContext var1) {
/* 53 */       CriterionConditionDamageSource var2 = CriterionConditionDamageSource.a(var0.get("predicate"));
/* 54 */       return new LootItemConditionDamageSourceProperties(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionDamageSourceProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */