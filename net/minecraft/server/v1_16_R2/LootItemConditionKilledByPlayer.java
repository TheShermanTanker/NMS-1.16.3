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
/*    */ public class LootItemConditionKilledByPlayer
/*    */   implements LootItemCondition
/*    */ {
/* 14 */   private static final LootItemConditionKilledByPlayer a = new LootItemConditionKilledByPlayer();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 21 */     return LootItemConditions.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 26 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.LAST_DAMAGE_PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 31 */     return var0.hasContextParameter(LootContextParameters.LAST_DAMAGE_PLAYER);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a c() {
/* 35 */     return () -> a;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionKilledByPlayer>
/*    */   {
/*    */     public void a(JsonObject var0, LootItemConditionKilledByPlayer var1, JsonSerializationContext var2) {}
/*    */     
/*    */     public LootItemConditionKilledByPlayer a(JsonObject var0, JsonDeserializationContext var1) {
/* 45 */       return LootItemConditionKilledByPlayer.d();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionKilledByPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */