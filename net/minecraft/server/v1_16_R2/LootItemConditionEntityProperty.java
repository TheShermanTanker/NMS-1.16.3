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
/*    */ 
/*    */ public class LootItemConditionEntityProperty
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final CriterionConditionEntity a;
/*    */   private final LootTableInfo.EntityTarget b;
/*    */   
/*    */   private LootItemConditionEntityProperty(CriterionConditionEntity var0, LootTableInfo.EntityTarget var1) {
/* 22 */     this.a = var0;
/* 23 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 28 */     return LootItemConditions.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 33 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.ORIGIN, this.b.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 38 */     Entity var1 = var0.<Entity>getContextParameter((LootContextParameter)this.b.a());
/* 39 */     Vec3D var2 = var0.<Vec3D>getContextParameter(LootContextParameters.ORIGIN);
/* 40 */     return this.a.a(var0.getWorld(), var2, var1);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(LootTableInfo.EntityTarget var0) {
/* 44 */     return a(var0, CriterionConditionEntity.a.a());
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(LootTableInfo.EntityTarget var0, CriterionConditionEntity.a var1) {
/* 48 */     return () -> new LootItemConditionEntityProperty(var0.b(), var1);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(LootTableInfo.EntityTarget var0, CriterionConditionEntity var1) {
/* 52 */     return () -> new LootItemConditionEntityProperty(var0, var1);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionEntityProperty> {
/*    */     public void a(JsonObject var0, LootItemConditionEntityProperty var1, JsonSerializationContext var2) {
/* 58 */       var0.add("predicate", LootItemConditionEntityProperty.a(var1).a());
/* 59 */       var0.add("entity", var2.serialize(LootItemConditionEntityProperty.b(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionEntityProperty a(JsonObject var0, JsonDeserializationContext var1) {
/* 64 */       CriterionConditionEntity var2 = CriterionConditionEntity.a(var0.get("predicate"));
/* 65 */       return new LootItemConditionEntityProperty(var2, ChatDeserializer.<LootTableInfo.EntityTarget>a(var0, "entity", var1, LootTableInfo.EntityTarget.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionEntityProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */