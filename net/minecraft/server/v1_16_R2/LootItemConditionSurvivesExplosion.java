/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LootItemConditionSurvivesExplosion
/*    */   implements LootItemCondition {
/* 12 */   private static final LootItemConditionSurvivesExplosion a = new LootItemConditionSurvivesExplosion();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 18 */     return LootItemConditions.k;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 23 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.EXPLOSION_RADIUS);
/*    */   }
/*    */   
/*    */   public boolean test(LootTableInfo loottableinfo) {
/* 27 */     Float ofloat = loottableinfo.<Float>getContextParameter(LootContextParameters.EXPLOSION_RADIUS);
/*    */     
/* 29 */     if (ofloat != null) {
/* 30 */       Random random = loottableinfo.a();
/* 31 */       float f = 1.0F / ofloat.floatValue();
/*    */ 
/*    */       
/* 34 */       return (random.nextFloat() < f);
/*    */     } 
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootItemCondition.a c() {
/* 41 */     return () -> a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionSurvivesExplosion>
/*    */   {
/*    */     public void a(JsonObject jsonobject, LootItemConditionSurvivesExplosion lootitemconditionsurvivesexplosion, JsonSerializationContext jsonserializationcontext) {}
/*    */ 
/*    */ 
/*    */     
/*    */     public LootItemConditionSurvivesExplosion a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
/* 54 */       return LootItemConditionSurvivesExplosion.a;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionSurvivesExplosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */