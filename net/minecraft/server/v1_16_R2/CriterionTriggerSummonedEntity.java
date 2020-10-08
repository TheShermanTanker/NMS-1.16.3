/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerSummonedEntity
/*    */   extends CriterionTriggerAbstract<CriterionTriggerSummonedEntity.a>
/*    */ {
/* 10 */   private static final MinecraftKey a = new MinecraftKey("summoned_entity");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 14 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 19 */     CriterionConditionEntity.b var3 = CriterionConditionEntity.b.a(var0, "entity", var2);
/* 20 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Entity var1) {
/* 24 */     LootTableInfo var2 = CriterionConditionEntity.b(var0, var1);
/* 25 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionEntity.b a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionEntity.b var1) {
/* 32 */       super(CriterionTriggerSummonedEntity.b(), var0);
/* 33 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity.a var0) {
/* 37 */       return new a(CriterionConditionEntity.b.a, CriterionConditionEntity.b.a(var0.b()));
/*    */     }
/*    */     
/*    */     public boolean a(LootTableInfo var0) {
/* 41 */       return this.a.a(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 46 */       JsonObject var1 = super.a(var0);
/*    */       
/* 48 */       var1.add("entity", this.a.a(var0));
/*    */       
/* 50 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerSummonedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */