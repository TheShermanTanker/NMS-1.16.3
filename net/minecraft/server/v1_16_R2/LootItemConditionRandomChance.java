/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ 
/*    */ public class LootItemConditionRandomChance
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final float a;
/*    */   
/*    */   private LootItemConditionRandomChance(float var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 18 */     return LootItemConditions.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 23 */     return (var0.a().nextFloat() < this.a);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(float var0) {
/* 27 */     return () -> new LootItemConditionRandomChance(var0);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionRandomChance> {
/*    */     public void a(JsonObject var0, LootItemConditionRandomChance var1, JsonSerializationContext var2) {
/* 33 */       var0.addProperty("chance", Float.valueOf(LootItemConditionRandomChance.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionRandomChance a(JsonObject var0, JsonDeserializationContext var1) {
/* 38 */       return new LootItemConditionRandomChance(ChatDeserializer.l(var0, "chance"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionRandomChance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */