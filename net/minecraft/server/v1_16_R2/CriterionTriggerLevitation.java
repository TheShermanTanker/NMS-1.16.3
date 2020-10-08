/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerLevitation
/*    */   extends CriterionTriggerAbstract<CriterionTriggerLevitation.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("levitation");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionDistance var3 = CriterionConditionDistance.a(var0.get("distance"));
/* 19 */     CriterionConditionValue.IntegerRange var4 = CriterionConditionValue.IntegerRange.a(var0.get("duration"));
/* 20 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Vec3D var1, int var2) {
/* 24 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionDistance a;
/*    */     private final CriterionConditionValue.IntegerRange b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionDistance var1, CriterionConditionValue.IntegerRange var2) {
/* 32 */       super(CriterionTriggerLevitation.b(), var0);
/* 33 */       this.a = var1;
/* 34 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionDistance var0) {
/* 38 */       return new a(CriterionConditionEntity.b.a, var0, CriterionConditionValue.IntegerRange.e);
/*    */     }
/*    */     
/*    */     public boolean a(EntityPlayer var0, Vec3D var1, int var2) {
/* 42 */       if (!this.a.a(var1.x, var1.y, var1.z, var0.locX(), var0.locY(), var0.locZ())) {
/* 43 */         return false;
/*    */       }
/* 45 */       if (!this.b.d(var2)) {
/* 46 */         return false;
/*    */       }
/* 48 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 53 */       JsonObject var1 = super.a(var0);
/*    */       
/* 55 */       var1.add("distance", this.a.a());
/* 56 */       var1.add("duration", this.b.d());
/*    */       
/* 58 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerLevitation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */