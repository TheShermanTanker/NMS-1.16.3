/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ public class CriterionTriggerTick
/*    */   extends CriterionTriggerAbstract<CriterionTriggerTick.a>
/*    */ {
/*  8 */   public static final MinecraftKey a = new MinecraftKey("tick");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 12 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 17 */     return new a(var1);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0) {
/* 21 */     a(var0, var0 -> true);
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     public a(CriterionConditionEntity.b var0) {
/* 26 */       super(CriterionTriggerTick.a, var0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerTick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */