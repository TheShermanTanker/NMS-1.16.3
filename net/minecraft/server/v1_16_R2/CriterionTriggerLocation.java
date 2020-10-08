/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerLocation
/*    */   extends CriterionTriggerAbstract<CriterionTriggerLocation.a>
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public CriterionTriggerLocation(MinecraftKey var0) {
/* 14 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 19 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 25 */     JsonObject var3 = ChatDeserializer.a(var0, "location", var0);
/* 26 */     CriterionConditionLocation var4 = CriterionConditionLocation.a((JsonElement)var3);
/* 27 */     return new a(this.a, var1, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0) {
/* 31 */     a(var0, var1 -> var1.a(var0.getWorldServer(), var0.locX(), var0.locY(), var0.locZ()));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionLocation a;
/*    */     
/*    */     public a(MinecraftKey var0, CriterionConditionEntity.b var1, CriterionConditionLocation var2) {
/* 38 */       super(var0, var1);
/* 39 */       this.a = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionLocation var0) {
/* 43 */       return new a(CriterionTriggerLocation.a(CriterionTriggers.p), CriterionConditionEntity.b.a, var0);
/*    */     }
/*    */     
/*    */     public static a c() {
/* 47 */       return new a(CriterionTriggerLocation.a(CriterionTriggers.q), CriterionConditionEntity.b.a, CriterionConditionLocation.a);
/*    */     }
/*    */     
/*    */     public static a d() {
/* 51 */       return new a(CriterionTriggerLocation.a(CriterionTriggers.H), CriterionConditionEntity.b.a, CriterionConditionLocation.a);
/*    */     }
/*    */     
/*    */     public boolean a(WorldServer var0, double var1, double var3, double var5) {
/* 55 */       return this.a.a(var0, var1, var3, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 60 */       JsonObject var1 = super.a(var0);
/* 61 */       var1.add("location", this.a.a());
/* 62 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */