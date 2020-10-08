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
/*    */ public class LootItemConditionMatchTool
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final CriterionConditionItem a;
/*    */   
/*    */   public LootItemConditionMatchTool(CriterionConditionItem var0) {
/* 19 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 24 */     return LootItemConditions.i;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 29 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.TOOL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 34 */     ItemStack var1 = var0.<ItemStack>getContextParameter(LootContextParameters.TOOL);
/* 35 */     return (var1 != null && this.a.a(var1));
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(CriterionConditionItem.a var0) {
/* 39 */     return () -> new LootItemConditionMatchTool(var0.b());
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionMatchTool> {
/*    */     public void a(JsonObject var0, LootItemConditionMatchTool var1, JsonSerializationContext var2) {
/* 45 */       var0.add("predicate", LootItemConditionMatchTool.a(var1).a());
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionMatchTool a(JsonObject var0, JsonDeserializationContext var1) {
/* 50 */       CriterionConditionItem var2 = CriterionConditionItem.a(var0.get("predicate"));
/* 51 */       return new LootItemConditionMatchTool(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionMatchTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */