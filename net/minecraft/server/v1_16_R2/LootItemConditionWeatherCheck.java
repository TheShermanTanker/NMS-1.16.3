/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionWeatherCheck
/*    */   implements LootItemCondition
/*    */ {
/*    */   @Nullable
/*    */   private final Boolean a;
/*    */   @Nullable
/*    */   private final Boolean b;
/*    */   
/*    */   private LootItemConditionWeatherCheck(@Nullable Boolean var0, @Nullable Boolean var1) {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 26 */     return LootItemConditions.n;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 31 */     WorldServer var1 = var0.getWorld();
/*    */     
/* 33 */     if (this.a != null && this.a.booleanValue() != var1.isRaining()) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (this.b != null && this.b.booleanValue() != var1.V()) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     return true;
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class b
/*    */     implements LootSerializer<LootItemConditionWeatherCheck>
/*    */   {
/*    */     public void a(JsonObject var0, LootItemConditionWeatherCheck var1, JsonSerializationContext var2) {
/* 74 */       var0.addProperty("raining", LootItemConditionWeatherCheck.a(var1));
/* 75 */       var0.addProperty("thundering", LootItemConditionWeatherCheck.b(var1));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionWeatherCheck a(JsonObject var0, JsonDeserializationContext var1) {
/* 80 */       Boolean var2 = var0.has("raining") ? Boolean.valueOf(ChatDeserializer.j(var0, "raining")) : null;
/* 81 */       Boolean var3 = var0.has("thundering") ? Boolean.valueOf(ChatDeserializer.j(var0, "thundering")) : null;
/* 82 */       return new LootItemConditionWeatherCheck(var2, var3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionWeatherCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */