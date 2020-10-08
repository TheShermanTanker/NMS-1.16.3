/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerConstructBeacon
/*    */   extends CriterionTriggerAbstract<CriterionTriggerConstructBeacon.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("construct_beacon");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionValue.IntegerRange var3 = CriterionConditionValue.IntegerRange.a(var0.get("level"));
/* 19 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, TileEntityBeacon var1) {
/* 23 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionValue.IntegerRange a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionValue.IntegerRange var1) {
/* 30 */       super(CriterionTriggerConstructBeacon.b(), var0);
/* 31 */       this.a = var1;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static a a(CriterionConditionValue.IntegerRange var0) {
/* 39 */       return new a(CriterionConditionEntity.b.a, var0);
/*    */     }
/*    */     
/*    */     public boolean a(TileEntityBeacon var0) {
/* 43 */       return this.a.d(var0.h());
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 48 */       JsonObject var1 = super.a(var0);
/*    */       
/* 50 */       var1.add("level", this.a.d());
/*    */       
/* 52 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerConstructBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */