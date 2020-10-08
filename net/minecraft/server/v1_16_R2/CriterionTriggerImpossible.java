/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerImpossible
/*    */   implements CriterionTrigger<CriterionTriggerImpossible.a>
/*    */ {
/* 10 */   private static final MinecraftKey a = new MinecraftKey("impossible");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 14 */     return a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(AdvancementDataPlayer var0, CriterionTrigger.a<a> var1) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(AdvancementDataPlayer var0, CriterionTrigger.a<a> var1) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(AdvancementDataPlayer var0) {}
/*    */ 
/*    */   
/*    */   public a a(JsonObject var0, LootDeserializationContext var1) {
/* 31 */     return new a();
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements CriterionInstance {
/*    */     public MinecraftKey a() {
/* 37 */       return CriterionTriggerImpossible.b();
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 42 */       return new JsonObject();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerImpossible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */