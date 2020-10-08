/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerUsedEnderEye
/*    */   extends CriterionTriggerAbstract<CriterionTriggerUsedEnderEye.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("used_ender_eye");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionValue.FloatRange var3 = CriterionConditionValue.FloatRange.a(var0.get("distance"));
/* 19 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, BlockPosition var1) {
/* 23 */     double var2 = var0.locX() - var1.getX();
/* 24 */     double var4 = var0.locZ() - var1.getZ();
/* 25 */     double var6 = var2 * var2 + var4 * var4;
/* 26 */     a(var0, var2 -> var2.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionValue.FloatRange a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionValue.FloatRange var1) {
/* 33 */       super(CriterionTriggerUsedEnderEye.b(), var0);
/* 34 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public boolean a(double var0) {
/* 38 */       return this.a.a(var0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerUsedEnderEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */