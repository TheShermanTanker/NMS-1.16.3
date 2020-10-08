/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionLocationCheck
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final CriterionConditionLocation a;
/*    */   private final BlockPosition b;
/*    */   
/*    */   private LootItemConditionLocationCheck(CriterionConditionLocation var0, BlockPosition var1) {
/* 19 */     this.a = var0;
/* 20 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 25 */     return LootItemConditions.m;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 30 */     Vec3D var1 = var0.<Vec3D>getContextParameter(LootContextParameters.ORIGIN);
/* 31 */     return (var1 != null && this.a.a(var0.getWorld(), var1.getX() + this.b.getX(), var1.getY() + this.b.getY(), var1.getZ() + this.b.getZ()));
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(CriterionConditionLocation.a var0) {
/* 35 */     return () -> new LootItemConditionLocationCheck(var0.b(), BlockPosition.ZERO);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(CriterionConditionLocation.a var0, BlockPosition var1) {
/* 39 */     return () -> new LootItemConditionLocationCheck(var0.b(), var1);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionLocationCheck> {
/*    */     public void a(JsonObject var0, LootItemConditionLocationCheck var1, JsonSerializationContext var2) {
/* 45 */       var0.add("predicate", LootItemConditionLocationCheck.a(var1).a());
/* 46 */       if (LootItemConditionLocationCheck.b(var1).getX() != 0) {
/* 47 */         var0.addProperty("offsetX", Integer.valueOf(LootItemConditionLocationCheck.b(var1).getX()));
/*    */       }
/* 49 */       if (LootItemConditionLocationCheck.b(var1).getY() != 0) {
/* 50 */         var0.addProperty("offsetY", Integer.valueOf(LootItemConditionLocationCheck.b(var1).getY()));
/*    */       }
/* 52 */       if (LootItemConditionLocationCheck.b(var1).getZ() != 0) {
/* 53 */         var0.addProperty("offsetZ", Integer.valueOf(LootItemConditionLocationCheck.b(var1).getZ()));
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionLocationCheck a(JsonObject var0, JsonDeserializationContext var1) {
/* 59 */       CriterionConditionLocation var2 = CriterionConditionLocation.a(var0.get("predicate"));
/* 60 */       int var3 = ChatDeserializer.a(var0, "offsetX", 0);
/* 61 */       int var4 = ChatDeserializer.a(var0, "offsetY", 0);
/* 62 */       int var5 = ChatDeserializer.a(var0, "offsetZ", 0);
/* 63 */       return new LootItemConditionLocationCheck(var2, new BlockPosition(var3, var4, var5));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionLocationCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */