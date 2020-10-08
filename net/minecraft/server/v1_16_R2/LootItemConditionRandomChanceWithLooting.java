/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LootItemConditionRandomChanceWithLooting
/*    */   implements LootItemCondition {
/*    */   private final float a;
/*    */   private final float b;
/*    */   
/*    */   private LootItemConditionRandomChanceWithLooting(float f, float f1) {
/* 15 */     this.a = f;
/* 16 */     this.b = f1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 21 */     return LootItemConditions.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 26 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.KILLER_ENTITY);
/*    */   }
/*    */   
/*    */   public boolean test(LootTableInfo loottableinfo) {
/* 30 */     Entity entity = loottableinfo.<Entity>getContextParameter(LootContextParameters.KILLER_ENTITY);
/* 31 */     int i = 0;
/*    */     
/* 33 */     if (entity instanceof EntityLiving) {
/* 34 */       i = EnchantmentManager.g((EntityLiving)entity);
/*    */     }
/*    */     
/* 37 */     if (loottableinfo.hasContextParameter(LootContextParameters.LOOTING_MOD)) {
/* 38 */       i = ((Integer)loottableinfo.<Integer>getContextParameter(LootContextParameters.LOOTING_MOD)).intValue();
/*    */     }
/*    */ 
/*    */     
/* 42 */     return (loottableinfo.a().nextFloat() < this.a + i * this.b);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(float f, float f1) {
/* 46 */     return () -> new LootItemConditionRandomChanceWithLooting(f, f1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionRandomChanceWithLooting>
/*    */   {
/*    */     public void a(JsonObject jsonobject, LootItemConditionRandomChanceWithLooting lootitemconditionrandomchancewithlooting, JsonSerializationContext jsonserializationcontext) {
/* 56 */       jsonobject.addProperty("chance", Float.valueOf(lootitemconditionrandomchancewithlooting.a));
/* 57 */       jsonobject.addProperty("looting_multiplier", Float.valueOf(lootitemconditionrandomchancewithlooting.b));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionRandomChanceWithLooting a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
/* 62 */       return new LootItemConditionRandomChanceWithLooting(ChatDeserializer.l(jsonobject, "chance"), ChatDeserializer.l(jsonobject, "looting_multiplier"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionRandomChanceWithLooting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */