/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerTargetHit
/*    */   extends CriterionTriggerAbstract<CriterionTriggerTargetHit.a>
/*    */ {
/* 11 */   private static final MinecraftKey a = new MinecraftKey("target_hit");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 15 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 20 */     CriterionConditionValue.IntegerRange var3 = CriterionConditionValue.IntegerRange.a(var0.get("signal_strength"));
/* 21 */     CriterionConditionEntity.b var4 = CriterionConditionEntity.b.a(var0, "projectile", var2);
/* 22 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Entity var1, Vec3D var2, int var3) {
/* 26 */     LootTableInfo var4 = CriterionConditionEntity.b(var0, var1);
/* 27 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionValue.IntegerRange a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionValue.IntegerRange var1, CriterionConditionEntity.b var2) {
/* 35 */       super(CriterionTriggerTargetHit.b(), var0);
/* 36 */       this.a = var1;
/* 37 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionValue.IntegerRange var0, CriterionConditionEntity.b var1) {
/* 41 */       return new a(CriterionConditionEntity.b.a, var0, var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 46 */       JsonObject var1 = super.a(var0);
/* 47 */       var1.add("signal_strength", this.a.d());
/* 48 */       var1.add("projectile", this.b.a(var0));
/* 49 */       return var1;
/*    */     }
/*    */     
/*    */     public boolean a(LootTableInfo var0, Vec3D var1, int var2) {
/* 53 */       if (!this.a.d(var2)) {
/* 54 */         return false;
/*    */       }
/* 56 */       if (!this.b.a(var0)) {
/* 57 */         return false;
/*    */       }
/* 59 */       return true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerTargetHit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */