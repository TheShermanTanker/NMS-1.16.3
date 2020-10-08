/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionInverted
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final LootItemCondition a;
/*    */   
/*    */   private LootItemConditionInverted(LootItemCondition var0) {
/* 17 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 22 */     return LootItemConditions.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public final boolean test(LootTableInfo var0) {
/* 27 */     return !this.a.test(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 32 */     return this.a.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 37 */     super.a(var0);
/* 38 */     this.a.a(var0);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(LootItemCondition.a var0) {
/* 42 */     LootItemConditionInverted var1 = new LootItemConditionInverted(var0.build());
/* 43 */     return () -> var0;
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionInverted> {
/*    */     public void a(JsonObject var0, LootItemConditionInverted var1, JsonSerializationContext var2) {
/* 49 */       var0.add("term", var2.serialize(LootItemConditionInverted.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionInverted a(JsonObject var0, JsonDeserializationContext var1) {
/* 54 */       LootItemCondition var2 = ChatDeserializer.<LootItemCondition>a(var0, "term", var1, LootItemCondition.class);
/* 55 */       return new LootItemConditionInverted(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionInverted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */