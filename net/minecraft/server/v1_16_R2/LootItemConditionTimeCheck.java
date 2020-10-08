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
/*    */ 
/*    */ 
/*    */ public class LootItemConditionTimeCheck
/*    */   implements LootItemCondition
/*    */ {
/*    */   @Nullable
/*    */   private final Long a;
/*    */   private final LootValueBounds b;
/*    */   
/*    */   private LootItemConditionTimeCheck(@Nullable Long var0, LootValueBounds var1) {
/* 21 */     this.a = var0;
/* 22 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 27 */     return LootItemConditions.p;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 32 */     WorldServer var1 = var0.getWorld();
/*    */     
/* 34 */     long var2 = var1.getDayTime();
/*    */     
/* 36 */     if (this.a != null) {
/* 37 */       var2 %= this.a.longValue();
/*    */     }
/*    */     
/* 40 */     return this.b.a((int)var2);
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
/*    */   public static class b
/*    */     implements LootSerializer<LootItemConditionTimeCheck>
/*    */   {
/*    */     public void a(JsonObject var0, LootItemConditionTimeCheck var1, JsonSerializationContext var2) {
/* 71 */       var0.addProperty("period", LootItemConditionTimeCheck.a(var1));
/* 72 */       var0.add("value", var2.serialize(LootItemConditionTimeCheck.b(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionTimeCheck a(JsonObject var0, JsonDeserializationContext var1) {
/* 77 */       Long var2 = var0.has("period") ? Long.valueOf(ChatDeserializer.m(var0, "period")) : null;
/* 78 */       LootValueBounds var3 = ChatDeserializer.<LootValueBounds>a(var0, "value", var1, LootValueBounds.class);
/* 79 */       return new LootItemConditionTimeCheck(var2, var3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionTimeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */